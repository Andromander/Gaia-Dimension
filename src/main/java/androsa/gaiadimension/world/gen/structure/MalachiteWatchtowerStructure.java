package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class MalachiteWatchtowerStructure<T extends NoFeatureConfig> extends Structure<T> {

    public MalachiteWatchtowerStructure(Codec<T> config) {
        super(config);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":MalachiteWatchtower";
//    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return Lists.newArrayList(
                new MobSpawnInfo.Spawners(ModEntities.MALACHITE_DRONE, 10, 1, 1),
                new MobSpawnInfo.Spawners(ModEntities.SHALURKER, 5, 1, 2),
                new MobSpawnInfo.Spawners(ModEntities.ARCHAIC_WARRIOR, 8, 1, 2),
                new MobSpawnInfo.Spawners(ModEntities.CAVERN_TICK, 3, 2, 3));
    }

    @Override
    public IStartFactory<T> getStartFactory() {
        return MalachiteWatchtowerStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom random, int chunkX, int chunkZ, Biome biomeIn, ChunkPos chunkpos, T config) {
        for(Biome biome : provider.getBiomesWithin(chunkX * 16 + 9, generator.getSeaLevel(), chunkZ * 16 + 9, 16)) {
            if (!biome.getGenerationSettings().isValidStart(this)) {
                return false;
            }
        }

        return true;
    }

    public static class Start<T extends NoFeatureConfig> extends StructureStart<T> {

        public Start(Structure<T> structure, int chunkX, int chunkZ, MutableBoundingBox mbb, int ref, long seed) {
            super(structure, chunkX, chunkZ, mbb, ref, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, T config) {
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

            int cX = (chunkX << 4) + 7;
            int cZ = (chunkZ << 4) + 7;
            int c1 = generator.getFirstOccupiedHeight(cX, cZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c2 = generator.getFirstOccupiedHeight(cX, cZ + oZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c3 = generator.getFirstOccupiedHeight(cX + oX, cZ, Heightmap.Type.WORLD_SURFACE_WG);
            int c4 = generator.getFirstOccupiedHeight(cX + oX, cZ + oZ, Heightmap.Type.WORLD_SURFACE_WG);
            int height = Math.min(Math.min(c1, c2), Math.min(c3, c4));

            if (height >= 60) {
                int x = chunkX * 16;
                int z = chunkZ * 16;
                BlockPos blockpos = new BlockPos(x + 8, height + 1, z + 8);
                MalachiteWatchtowerPieces.buildStructure(manager, blockpos, rotation, this.pieces, this.random);
                this.calculateBoundingBox();
            }
        }

        @Override
        public void placeInChunk(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox mbb, ChunkPos chunkpos) {
            super.placeInChunk(world, manager, generator, random, mbb, chunkpos);
            int minY = this.boundingBox.y0;

            //Let me ask: do towers overhang cliffs? I didn't think so
            for(int x = mbb.x0; x <= mbb.x1; ++x) {
                for(int z = mbb.z0; z <= mbb.z1; ++z) {
                    BlockPos blockpos = new BlockPos(x, minY, z);
                    if (!world.isEmptyBlock(blockpos) && this.boundingBox.isInside(blockpos)) {
                        boolean isAirBelow = false;

                        for(StructurePiece structurepiece : this.pieces) {
                            if (structurepiece.getBoundingBox().isInside(blockpos)) {
                                isAirBelow = true;
                                break;
                            }
                        }

                        if (isAirBelow) {
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
}
