package androsa.gaiadimension.world.chunk;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.world.chunk.warp.*;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class GaiaChunkGenerator extends NoiseBasedChunkGenerator {
    public static final MapCodec<GaiaChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter((object) -> object.biomeSource),
            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((object) -> object.settings)
    ).apply(instance, instance.stable(GaiaChunkGenerator::new)));

    protected final Holder<NoiseGeneratorSettings> settings;
    protected final Climate.Sampler sampler;
    protected final GaiaTerrainWarp warper;
    private final int cellWidth;
    private final int cellHeight;
    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private GaiaSurfaceSystem surface;

    public GaiaChunkGenerator(BiomeSource mainsource, Holder<NoiseGeneratorSettings> noisesettings) {
        super(mainsource, noisesettings);

        this.settings = noisesettings;
        if (noisesettings.isBound()) {
            this.defaultBlock = noisesettings.value().defaultBlock();
            this.defaultFluid = noisesettings.value().defaultFluid();
            NoiseSettings noise = noisesettings.value().noiseSettings();
            this.cellWidth = noise.getCellWidth();
            this.cellHeight = noise.getCellHeight();
            NoiseSlider topSlide = new NoiseSlider(-10.0D, 3, 0);
            NoiseSlider bottomSlide = new NoiseSlider(15.0D, 3, 0);
            BlendedNoise blendedNoise = BlendedNoise.createUnseeded(1.0F, 1.0F, 80.0F, 160.0F, 0.0D);
            NoiseModifier modifier = NoiseModifier.PASS;
            this.warper = new GaiaTerrainWarp(this.cellWidth, this.cellHeight, noise.height() / this.cellHeight, mainsource, noise, topSlide, bottomSlide, blendedNoise, modifier);
        } else {
            this.defaultBlock = ModBlocks.gaia_stone.get().defaultBlockState();
            this.defaultFluid = ModBlocks.mineral_water.get().defaultBlockState();
            this.warper = null;
            this.cellWidth = 0;
            this.cellHeight = 0;
        }

        this.sampler = new Climate.Sampler(DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), List.of()); //let's be real, this is a dummy
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void buildSurface(ChunkAccess chunk, WorldGenerationContext context, RandomState random, StructureManager structures, BiomeManager biomes, Registry<Biome> registry, Blender blender) {
        if (!SharedConstants.debugVoidTerrain(chunk.getPos())) {
            NoiseGeneratorSettings settings = this.settings.value();
            NoiseChunk noisechunk = chunk.getOrCreateNoiseChunk((access) -> this.createNoiseChunk(access, structures, blender, random));
            if (this.surface == null) {
                this.surface = new GaiaSurfaceSystem(random, this.settings.value().defaultBlock(), this.settings.value().seaLevel(), settings.getRandomSource().newInstance(0L).forkPositional());
            }
            this.surface.buildSurface(random, biomes, registry, settings.useLegacyRandomSource(), context, chunk, noisechunk, settings.surfaceRule());
        }
    }

    @Override
    public CompletableFuture<ChunkAccess> createBiomes(RandomState random, Blender blender, StructureManager manager, ChunkAccess access) {
        return CompletableFuture.supplyAsync(Util.wrapThreadWithTaskName("init_biomes", () -> {
            access.fillBiomesFromNoise(this.getBiomeSource(), Climate.empty());
            return access;
        }), Util.backgroundExecutor());
    }

    @Override
    public ChunkAccess doFill(Blender blender, StructureManager manager, RandomState random, ChunkAccess access, int min, int max) {
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

        for (int cellX = 0; cellX < cellCountX; cellX++) {
            int advX = cellX;
            list.forEach((noiseint) -> noiseint.advanceX(advX));

            for (int cellZ = 0; cellZ < cellCountZ; cellZ++) {
                int sections = access.getSectionsCount() - 1;
                LevelChunkSection section = access.getSection(sections);

                for (int cellY = max - 1; cellY >= 0; cellY--) {
                    int advY = cellY;
                    int advZ = cellZ;
                    list.forEach((noiseint) -> noiseint.selectYZ(advY, advZ));

                    for(int height = this.cellHeight - 1; height >= 0; height--) {
                        int minheight = (min + cellY) * this.cellHeight + height;
                        int mincellY = minheight & 15;
                        int minindexY = access.getSectionIndex(minheight);

                        if (sections != minindexY) {
                            sections = minindexY;
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
                                //BlockState state = this.updateNoiseAndGenerateBaseState(beardifier, this.emptyAquifier, NoiseModifier.PASS, minwidthX, minheight, minwidthZ, noiseval);
                                BlockState state = this.generateBaseState(noiseval, minheight);

                                if (state != Blocks.AIR.defaultBlockState()) {
                                    section.setBlockState(mincellX, mincellY, mincellZ, state, false);
                                    oceanfloor.update(mincellX, minheight, mincellZ, state);
                                    surface.update(mincellX, minheight, mincellZ, state);
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
    protected OptionalInt iterateNoiseColumn(LevelHeightAccessor accessor, RandomState random, int x, int z, MutableObject<NoiseColumn> object, Predicate<BlockState> predicate) {
        NoiseSettings settings = this.settings.value().noiseSettings().clampToHeightAccessor(accessor);
        int min = Math.floorDiv(settings.minY(), this.cellHeight);
        int max = Math.floorDiv(settings.height(), this.cellHeight);

        if (max <= 0) {
            return OptionalInt.empty();
        } else {
            BlockState[] states = null;
            if (object != null) {
                states = new BlockState[max * settings.getCellHeight()];
                object.setValue(new NoiseColumn(settings.minY(), states));
            }
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
            //Aquifers?

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
    }

    private double[] makeAndFillNoiseColumn(int x, int z, int min, int max) {
        double[] columns = new double[max + 1];
        this.fillNoiseColumn(columns, x, z, min, max);
        return columns;
    }

    private void fillNoiseColumn(double[] columns, int x, int z, int min, int max) {
        this.warper.fillNoiseColumn(columns, x, z, sampler, this.getSeaLevel(), min, max);
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

    private NoiseChunk createNoiseChunk(ChunkAccess access, StructureManager manager, Blender blender, RandomState random) {
        return NoiseChunk.forChunk(access, random, Beardifier.forStructuresInChunk(manager, access.getPos()), this.settings.value(), this.globalFluidPicker.get(), blender);
    }
}
