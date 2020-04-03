package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class MiniTowerStructure<T extends NoFeatureConfig> extends ScatteredStructure<T> {

    public MiniTowerStructure(Function<Dynamic<?>, T> config) {
        super(config);
    }

    @Override
    public String getStructureName() {
        return GaiaDimensionMod.MODID + ":GaiaMiniTower";
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public IStartFactory getStartFactory() {
        return MiniTowerStructure.Start::new;
    }

    @Override
    protected int getSeedModifier() {
        return 19348230;
    }

    public static class Start extends StructureStart {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox mbb, int ref, long seed) {
            super(structure, chunkX, chunkZ, mbb, ref, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            MiniTowerPieces.buildStructure(manager, blockpos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
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
