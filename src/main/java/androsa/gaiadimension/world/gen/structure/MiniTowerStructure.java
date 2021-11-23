package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class MiniTowerStructure<T extends NoneFeatureConfiguration> extends StructureFeature<T> {

    public MiniTowerStructure(Codec<T> config) {
        super(config);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":GaiaMiniTower";
//    }

    @Override
    public StructureStartFactory<T> getStartFactory() {
        return MiniTowerStructure.Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start<T extends NoneFeatureConfiguration> extends StructureStart<T> {

        public Start(StructureFeature<T> structure, ChunkPos pos, int ref, long seed) {
            super(structure, pos, ref, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registries, ChunkGenerator generator, StructureManager manager, ChunkPos pos, Biome biome, T config, LevelHeightAccessor height) {
            BlockPos blockpos = new BlockPos(pos.getMinBlockX(), 90, pos.getMinBlockZ());
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            MiniTowerPieces.buildStructure(manager, blockpos, rotation, this.pieces, this.random);
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
