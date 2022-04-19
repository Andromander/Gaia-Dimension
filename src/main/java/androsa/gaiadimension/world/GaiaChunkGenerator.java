package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.biomegen.GaiaBlendedNoise;
import androsa.gaiadimension.world.biomegen.NoiseModifier;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;

public class GaiaChunkGenerator extends NoiseBasedChunkGenerator {
    public static final Codec<GaiaChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter((object) -> object.biomeSource),
            RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter((object) -> object.structureRegistry),
            RegistryOps.retrieveRegistry(Registry.NOISE_REGISTRY).forGetter((object) -> object.noiseRegistry),
            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((object) -> object.settings),
            Codec.LONG.fieldOf("seed").forGetter((object) -> object.seed)
    ).apply(instance, instance.stable(GaiaChunkGenerator::new)));

    protected final long seed;
    protected final Holder<NoiseGeneratorSettings> settings;
    protected final Registry<StructureSet> structureRegistry;
    protected final Registry<NormalNoise.NoiseParameters> noiseRegistry;
    protected final NoiseRouter router;
    protected final Climate.Sampler sampler;
    protected final GaiaTerrainWarp warper;
    private final int cellWidth;
    private final int cellHeight;
    private static final BlockState[] EMPTY = new BlockState[0];
    private final BlockState defaultFluid = ModBlocks.mineral_water.get().defaultBlockState();
    private final Aquifer.FluidPicker globalFluidPicker;
    private final Aquifer emptyAquifier;
    private final SurfaceSystem surfaceSystem;

    public GaiaChunkGenerator(BiomeSource mainsource, Registry<StructureSet> setregistry, Registry<NormalNoise.NoiseParameters> noiseregistry, Holder<NoiseGeneratorSettings> noisesettings, long seed) {
        super(setregistry, noiseregistry, mainsource, seed, noisesettings);

        this.seed = seed;
        this.settings = noisesettings;
        this.structureRegistry = setregistry;
        this.noiseRegistry = noiseregistry;
        NoiseSettings noise = noisesettings.value().noiseSettings();
        this.cellWidth = noise.getCellWidth();
        this.cellHeight = noise.getCellHeight();
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(seed));
        BlendedNoise blendedNoise = new GaiaBlendedNoise(random, noise.noiseSamplingSettings(), this.cellWidth, this.cellHeight);
        NoiseModifier modifier = NoiseModifier.PASS;
        this.router = noisesettings.value().createNoiseRouter(noiseregistry, seed);
        this.sampler = new Climate.Sampler(DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), List.of()); //let's be real, this is a dummy
        this.warper = new GaiaTerrainWarp(this.cellWidth, this.cellHeight, noise.getCellCountY(), mainsource, noise, blendedNoise, modifier);
        int i = noisesettings.value().seaLevel();
        Aquifer.FluidStatus topfluid = new Aquifer.FluidStatus(i, noisesettings.value().defaultFluid());
        Aquifer.FluidStatus lowfluid = new Aquifer.FluidStatus(-54, Blocks.LAVA.defaultBlockState());
        this.globalFluidPicker = (x, y, z) -> y < Math.min(-54, i) ? lowfluid : topfluid;
        this.emptyAquifier = Aquifer.createDisabled(this.globalFluidPicker);
        BlockState defaultBlock = this.settings.value().defaultBlock();
        this.surfaceSystem = new SurfaceSystem(noiseregistry, defaultBlock, i, seed, this.settings.value().getRandomSource());
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new GaiaChunkGenerator(this.biomeSource.withSeed(seed), this.structureRegistry, this.noiseRegistry, this.settings, seed);
    }

    @Override
    public Climate.Sampler climateSampler() {
        return this.sampler;
    }

    @Override
    public CompletableFuture<ChunkAccess> createBiomes(Registry<Biome> registry, Executor executor, Blender blender, StructureFeatureManager manager, ChunkAccess access) {
        return CompletableFuture.supplyAsync(Util.wrapThreadWithTaskName("init_biomes", () -> {
            access.fillBiomesFromNoise(this.getBiomeSource(), this.climateSampler());
            return access;
        }), Util.backgroundExecutor());
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager manager, ChunkAccess access) {
        NoiseSettings settings = this.settings.value().noiseSettings();
        int minY = Math.max(settings.minY(), access.getMinBuildHeight());
        int maxY = Math.min(settings.minY() + settings.height(), access.getMaxBuildHeight());
        int mincell = Mth.intFloorDiv(minY, this.cellHeight);
        int maxcell = Mth.intFloorDiv(maxY - minY, this.cellHeight);

        if (maxcell <= 0) {
            return CompletableFuture.completedFuture(access);
        } else {
            int maxIndex = access.getSectionIndex(maxcell * this.cellHeight - 1 + minY);
            int minIndex = access.getSectionIndex(minY);
            Set<LevelChunkSection> sections = Sets.newHashSet();

            for (int index = maxIndex; index >= minIndex; index--) {
                LevelChunkSection section = access.getSection(index);
                section.acquire();
                sections.add(section);
            }

            return CompletableFuture.supplyAsync(() -> this.doFill(access, mincell, maxcell), Util.backgroundExecutor()).whenCompleteAsync((chunk, throwable) -> {
                for (LevelChunkSection section : sections) {
                    section.release();
                }
            }, executor);
        }
    }

    private ChunkAccess doFill(ChunkAccess access, int min, int max) {
        int cellCountX = 16 / this.cellWidth;
        int cellCountZ = 16 / this.cellWidth;
        Heightmap oceanfloor = access.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap surface = access.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        ChunkPos chunkpos = access.getPos();
        int minX = chunkpos.getMinBlockX();
        int minZ = chunkpos.getMinBlockZ();
        GaiaNoiseInterpolator interpolator = new GaiaNoiseInterpolator(cellCountX, max, cellCountZ, chunkpos, min, this::fillNoiseColumn);
        List<GaiaNoiseInterpolator> list = Lists.newArrayList(interpolator);
        list.forEach(GaiaNoiseInterpolator::initialiseFirstX);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int cellX = 0; cellX < cellCountX; cellX++) {
            int advX = cellX;
            list.forEach((noiseint) -> noiseint.advanceX(advX));

            for (int cellZ = 0; cellZ < cellCountZ; cellZ++) {
                LevelChunkSection section = access.getSection(access.getSectionsCount() - 1);

                for (int cellY = max - 1; cellY >= 0; cellY--) {
                    int advY = cellY;
                    int advZ = cellZ;
                    list.forEach((noiseint) -> noiseint.selectYZ(advY, advZ));

                    for(int height = this.cellHeight - 1; height >= 0; height--) {
                        int minheight = (min + cellY) * this.cellHeight + height;
                        int mincellY = minheight & 15;
                        int minindexY = access.getSectionIndex(minheight);

                        if (access.getSectionIndex(section.bottomBlockY()) != minindexY) {
                            section = access.getSection(minindexY);
                        }

                        double heightdiv = (double)height / (double)this.cellHeight;
                        list.forEach((noiseint) -> noiseint.updateY(heightdiv));

                        for (int widthX = 0; widthX < this.cellWidth; widthX++) {
                            int minwidthX = minX + cellX * this.cellWidth + widthX;
                            int mincellX = minwidthX & 15;
                            double widthdivX = (double)widthX / (double)this.cellWidth;
                            list.forEach((noiseint) -> noiseint.updateX(widthdivX));

                            for (int widthZ = 0; widthZ < this.cellWidth; widthZ++) {
                                int minwidthZ = minZ + cellZ * this.cellWidth + widthZ;
                                int mincellZ = minwidthZ & 15;
                                double widthdivZ = (double)widthZ / (double)this.cellWidth;
                                double noiseval = interpolator.updateZ(widthdivZ);
                                //BlockState state = this.updateNoiseAndGenerateBaseState(beardifier, this.emptyAquifier, NoiseModifier.PASS, minwidthX, minheight, minwidthZ, noiseval); //TODO
                                BlockState state = this.generateBaseState(noiseval, minheight);

                                if (state != Blocks.AIR.defaultBlockState()) {
                                    if (state.getLightEmission() != 0 && access instanceof ProtoChunk proto) {
                                        mutable.set(minwidthX, minheight, minwidthZ);
                                        proto.addLight(mutable);
                                    }

                                    section.setBlockState(mincellX, mincellY, mincellZ, state, false);
                                    oceanfloor.update(mincellX, minheight, mincellZ, state);
                                    surface.update(mincellX, minheight, mincellZ, state);

                                    if (emptyAquifier.shouldScheduleFluidUpdate() && !state.getFluidState().isEmpty()) {
                                        mutable.set(minwidthX, minheight, minwidthZ);
                                        access.markPosForPostprocessing(mutable);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            list.forEach(GaiaNoiseInterpolator::swapSlices);
        }

        return access;
    }

    @Override
    public int getSeaLevel() {
        return this.settings.value().seaLevel();
    }

    @Override
    public int getMinY() {
        return this.settings.value().noiseSettings().minY();
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types type, LevelHeightAccessor accessor) {
        NoiseSettings settings = this.settings.value().noiseSettings();
        int minY = Math.max(settings.minY(), accessor.getMinBuildHeight());
        int maxY = Math.min(settings.minY() + settings.height(), accessor.getMaxBuildHeight());
        int mincell = Mth.intFloorDiv(minY, settings.getCellHeight());
        int maxcell = Mth.intFloorDiv(maxY - minY, settings.getCellHeight());
        return maxcell <= 0 ? accessor.getMinBuildHeight() : this.iterateNoiseColumn(x, z, null, type.isOpaque(), mincell, maxcell).orElse(accessor.getMinBuildHeight());
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor accessor) {
        NoiseSettings noise = this.settings.value().noiseSettings();
        int minheight = Math.max(noise.minY(), accessor.getMinBuildHeight());
        int maxheight = Math.min(noise.minY() + noise.height(), accessor.getMaxBuildHeight());
        int mincells = Mth.intFloorDiv(minheight, noise.getCellHeight());
        int maxcells = Mth.intFloorDiv(maxheight - minheight, noise.getCellHeight());

        if (maxcells <= 0) {
            return new NoiseColumn(minheight, EMPTY);
        } else {
            BlockState[] states = new BlockState[maxcells * noise.getCellHeight()];
            this.iterateNoiseColumn(x, z, states, null, mincells, maxcells);
            return new NoiseColumn(minheight, states);
        }
    }

    @Override
    protected OptionalInt iterateNoiseColumn(int x, int z, BlockState[] states, Predicate<BlockState> predicate, int min, int max) {
        int xDiv = Math.floorDiv(x, this.cellWidth);
        int zDiv = Math.floorDiv(z, this.cellWidth);
        int xMod = Math.floorMod(x, this.cellWidth);
        int zMod = Math.floorMod(z, this.cellWidth);
        int xMin = xMod / this.cellWidth;
        int zMin = zMod / this.cellWidth;
        double[][] columns = new double[][]{
                this.makeAndFillNoiseColumn(xDiv, zDiv, min, max),
                this.makeAndFillNoiseColumn(xDiv, zDiv + 1, min, max),
                this.makeAndFillNoiseColumn(xDiv + 1, zDiv, min, max),
                this.makeAndFillNoiseColumn(xDiv + 1, zDiv + 1, min, max)
        };
        //TODO Aquifers?

        for (int cell = max - 1; cell >= 0; cell--) {
            double d10 = columns[0][cell];
            double d20 = columns[1][cell];
            double d30 = columns[2][cell];
            double d40 = columns[3][cell];
            double d11 = columns[0][cell + 1];
            double d21 = columns[1][cell + 1];
            double d31 = columns[2][cell + 1];
            double d41 = columns[3][cell + 1];

            for (int height = this.cellHeight - 1; height >= 0; height--) {
                double dcell = height / (double)this.cellHeight;
                double lcell = Mth.lerp3(dcell, xMin, zMin, d10, d11, d30, d31, d20, d21, d40, d41);
                int layer = cell * this.cellHeight + height;
                int maxlayer = layer + min * this.cellHeight;
                //BlockState state = this.updateNoiseAndGenerateBaseState(GaiaBeardifier.NO_OP, this.emptyAquifier, NoiseModifier.PASS, x, maxlayer, z, lcell);
                BlockState state = this.generateBaseState(lcell, layer);
                if (states != null) {
                    states[layer] = state;
                }

                if (predicate != null && predicate.test(state)) {
                    return OptionalInt.of(maxlayer + 1);
                }
            }
        }

        return OptionalInt.empty();
    }

    private double[] makeAndFillNoiseColumn(int x, int z, int min, int max) {
        double[] columns = new double[max + 1];
        this.fillNoiseColumn(columns, x, z, min, max);
        return columns;
    }

    private void fillNoiseColumn(double[] columns, int x, int z, int min, int max) {
        NoiseSettings settings = this.settings.value().noiseSettings();
        this.warper.fillNoiseColumn(this, columns, x, z, settings, this.getSeaLevel(), min, max);
    }

    private BlockState generateBaseState(double a, double b) {
        BlockState state;

        if (a > 0.0D) {
            state = this.defaultBlock;
        } else if (b < this.getSeaLevel()) {
            state = this.defaultFluid;
        } else {
            state = Blocks.AIR.defaultBlockState();
        }

        return state;
    }
}
