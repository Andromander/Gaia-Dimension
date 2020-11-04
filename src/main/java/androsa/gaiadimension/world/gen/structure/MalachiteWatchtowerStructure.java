package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.world.GaiaGenerationSettings;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class MalachiteWatchtowerStructure<T extends NoFeatureConfig> extends Structure<T> {

    private static final List<Biome.SpawnListEntry> spawnList = Lists.newArrayList(
            new Biome.SpawnListEntry(ModEntities.MALACHITE_DRONE.get(), 10, 1, 1),
            new Biome.SpawnListEntry(ModEntities.SHALURKER.get(), 5, 1, 2),
            new Biome.SpawnListEntry(ModEntities.ARCHAIC_WARRIOR.get(), 8, 1, 2),
            new Biome.SpawnListEntry(ModEntities.CAVERN_TICK.get(), 3, 2, 3));

    public MalachiteWatchtowerStructure(Function<Dynamic<?>, T> config) {
        super(config);
    }

    @Override
    public String getStructureName() {
        return GaiaDimensionMod.MODID + ":MalachiteWatchtower";
    }

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public List<Biome.SpawnListEntry> getCreatureSpawnList() {
        return spawnList;
    }

    @Override
    public IStartFactory getStartFactory() {
        return MalachiteWatchtowerStructure.Start::new;
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int minX, int minZ, int maxX, int maxZ) {
        int dist = this.getBiomeFeatureDistance(generator);
        int sep = this.getBiomeFeatureSeparation(generator);
        int k = minX + dist * maxX;
        int l = minZ + dist * maxZ;
        int i1 = k < 0 ? k - dist + 1 : k;
        int j1 = l < 0 ? l - dist + 1 : l;
        int k1 = i1 / dist;
        int l1 = j1 / dist;
        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(generator.getSeed(), k1, l1, 1294754);
        k1 = k1 * dist;
        l1 = l1 * dist;
        k1 = k1 + random.nextInt(dist - sep);
        l1 = l1 + random.nextInt(dist - sep);
        return new ChunkPos(k1, l1);
    }

    @Override
    public boolean shouldStartAt(BiomeManager manager, ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ, Biome biomeIn) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generator, random, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            for(Biome biome : generator.getBiomeProvider().getBiomesInArea(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, 16)) {
                if (!generator.hasStructure(biome, this)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected int getBiomeFeatureDistance(ChunkGenerator<?> generator) {
        GenerationSettings settings = generator.getSettings();

        if (settings instanceof GaiaGenerationSettings)
            return ((GaiaGenerationSettings) settings).getWatchtowerFeatureDistance();
        else
            return settings.getBiomeFeatureDistance();
    }

    protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator) {
        GenerationSettings settings = generator.getSettings();

        if (settings instanceof GaiaGenerationSettings)
            return ((GaiaGenerationSettings) settings).getWatchtowerFeatureSeparation();
        else
            return settings.getBiomeFeatureSeparation();
    }

    public static class Start extends StructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox mbb, int ref, long seed) {
            super(structure, chunkX, chunkZ, mbb, ref, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome) {
            Rotation rotation = Rotation.randomRotation(this.rand);
            int oX = 5;
            int oZ = 5;
            if (rotation == Rotation.CLOCKWISE_90) {
                oX = -5;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                oX = -5;
                oZ = -5;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                oZ = -5;
            }

            int cX = (chunkX << 4) + 7;
            int cZ = (chunkZ << 4) + 7;
            int c1 = generator.func_222531_c(cX, cZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c2 = generator.func_222531_c(cX, cZ + oZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c3 = generator.func_222531_c(cX + oX, cZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c4 = generator.func_222531_c(cX + oX, cZ + oZ, Heightmap.Type.WORLD_SURFACE_WG);
            int height = Math.min(Math.min(c1, c2), Math.min(c3, c4));

            if (height >= 60) {
                int x = chunkX * 16;
                int z = chunkZ * 16;
                BlockPos blockpos = new BlockPos(x + 8, height + 1, z + 8);
                MalachiteWatchtowerPieces.buildStructure(manager, blockpos, rotation, this.components, this.rand);
                this.recalculateStructureSize();
            }
        }

        @Override
        public void generateStructure(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox mbb, ChunkPos chunkpos) {
            super.generateStructure(world, generator, random, mbb, chunkpos);
            int minY = this.bounds.minY;

            //Let me ask: do towers overhang cliffs? I didn't think so
            for(int x = mbb.minX; x <= mbb.maxX; ++x) {
                for(int z = mbb.minZ; z <= mbb.maxZ; ++z) {
                    BlockPos blockpos = new BlockPos(x, minY, z);
                    if (!world.isAirBlock(blockpos) && this.bounds.isVecInside(blockpos)) {
                        boolean isAirBelow = false;

                        for(StructurePiece structurepiece : this.components) {
                            if (structurepiece.getBoundingBox().isVecInside(blockpos)) {
                                isAirBelow = true;
                                break;
                            }
                        }

                        if (isAirBelow) {
                            for(int lowY = minY - 1; lowY > 1; --lowY) {
                                BlockPos blockpos1 = new BlockPos(x, lowY, z);
                                if (!world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
                                    break;
                                }

                                world.setBlockState(blockpos1, world.getBiome(blockpos1).getSurfaceBuilderConfig().getUnder(), 2);
                            }
                        }
                    }
                }
            }
        }
    }
}
