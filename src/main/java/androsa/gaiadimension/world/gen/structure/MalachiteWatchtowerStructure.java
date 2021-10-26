package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class MalachiteWatchtowerStructure<T extends NoneFeatureConfiguration> extends StructureFeature<T> {

    public MalachiteWatchtowerStructure(Codec<T> config) {
        super(config);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":MalachiteWatchtower";
//    }

    @Override
    public List<MobSpawnSettings.SpawnerData> getDefaultSpawnList() {
        return Lists.newArrayList(
                new MobSpawnSettings.SpawnerData(ModEntities.MALACHITE_DRONE, 10, 1, 1),
                new MobSpawnSettings.SpawnerData(ModEntities.SHALURKER, 5, 1, 2),
                new MobSpawnSettings.SpawnerData(ModEntities.ARCHAIC_WARRIOR, 8, 1, 2),
                new MobSpawnSettings.SpawnerData(ModEntities.CAVERN_TICK, 3, 2, 3));
    }

    @Override
    public StructureStartFactory<T> getStartFactory() {
        return MalachiteWatchtowerStructure.Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeSource provider, long seed, WorldgenRandom random, ChunkPos chunkpos, Biome biomeIn, ChunkPos potential, T config, LevelHeightAccessor height) {
        for(Biome biome : provider.getBiomesWithin(chunkpos.getBlockX(9), generator.getSeaLevel(), chunkpos.getBlockZ(9), 16)) {
            if (!biome.getGenerationSettings().isValidStart(this)) {
                return false;
            }
        }

        return true;
    }

    public static class Start<T extends NoneFeatureConfiguration> extends StructureStart<T> {

        public Start(StructureFeature<T> structure, ChunkPos pos, int ref, long seed) {
            super(structure, pos, ref, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registries, ChunkGenerator generator, StructureManager manager, ChunkPos pos, Biome biome, T config, LevelHeightAccessor height) {
            Rotation rotation = Rotation.getRandom(this.random);
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

            int cX = pos.getBlockX(7);
            int cZ = pos.getBlockZ(7);
            int c1 = generator.getFirstOccupiedHeight(cX, cZ, Heightmap.Types.WORLD_SURFACE_WG, height);
            int c2 = generator.getFirstOccupiedHeight(cX, cZ + oZ, Heightmap.Types.WORLD_SURFACE_WG, height);
            int c3 = generator.getFirstOccupiedHeight(cX + oX, cZ, Heightmap.Types.WORLD_SURFACE_WG, height);
            int c4 = generator.getFirstOccupiedHeight(cX + oX, cZ + oZ, Heightmap.Types.WORLD_SURFACE_WG, height);
            int level = Math.min(Math.min(c1, c2), Math.min(c3, c4));

            if (level >= 60) {
                BlockPos blockpos = new BlockPos(pos.getBlockX(8), level + 1, pos.getBlockZ(8));
                MalachiteWatchtowerPieces.buildStructure(manager, blockpos, rotation, this.pieces, this.random);
            }
        }

        @Override
        public void placeInChunk(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random random, BoundingBox mbb, ChunkPos chunkpos) {
            super.placeInChunk(world, manager, generator, random, mbb, chunkpos);
            BoundingBox boundingbox = this.getBoundingBox();
            int minY = boundingbox.minY();

            //Let me ask: do towers overhang cliffs? I didn't think so
            for(int x = mbb.minX(); x <= mbb.maxX(); ++x) {
                for(int z = mbb.minZ(); z <= mbb.maxZ(); ++z) {
                    BlockPos blockpos = new BlockPos(x, minY, z);
                    if (!world.isEmptyBlock(blockpos) && boundingbox.isInside(blockpos) && this.isInsidePiece(blockpos)) {

                        for(int lowY = minY - 1; lowY > 1; --lowY) {
                            BlockPos blockpos1 = new BlockPos(x, lowY, z);
                            if (!world.isEmptyBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
                                break;
                            }

                            world.setBlock(blockpos1, world.getBiome(blockpos1).getGenerationSettings().getSurfaceBuilderConfig().getUnderMaterial(), 2);
                        }
                    }
                }
            }
        }
    }
}
