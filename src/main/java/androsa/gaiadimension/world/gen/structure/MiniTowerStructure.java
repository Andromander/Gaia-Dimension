package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import com.mojang.serialization.Codec;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class MiniTowerStructure<T extends NoFeatureConfig> extends Structure<T> {

    public MiniTowerStructure(Codec<T> config) {
        super(config);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":GaiaMiniTower";
//    }

    @Override
    public IStartFactory<T> getStartFactory() {
        return MiniTowerStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start<T extends NoFeatureConfig> extends StructureStart<T> {

        public Start(Structure<T> structure, int chunkX, int chunkZ, MutableBoundingBox mbb, int ref, long seed) {
            super(structure, chunkX, chunkZ, mbb, ref, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, T config) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];
            MiniTowerPieces.buildStructure(manager, blockpos, rotation, this.pieces, this.random);
            this.calculateBoundingBox();
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
