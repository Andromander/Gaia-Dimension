package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class AuraTreeFeature<T extends GaiaTreeFeatureConfig> extends AbstractTreeFeature<T> {
    //TODO: Move to Builder
    private static final BlockState TRUNK = ModBlocks.aura_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.aura_leaves.get().getDefaultState();

    public AuraTreeFeature(Function<Dynamic<?>, T> configIn) {
        super(configIn);
    }

    @Override
    protected boolean generate(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int baseHeight = rand.nextInt(3) + rand.nextInt(3) + 10;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + baseHeight + 1 <= 256) {
            for (int yMark = position.getY(); yMark <= position.getY() + 1 + baseHeight; ++yMark) {
                int offset = 1;

                if (yMark == position.getY()) {
                    offset = 0;
                }

                if (yMark >= position.getY() + 1 + baseHeight - 2) {
                    offset = 2;
                }

                BlockPos.Mutable mutablePos = new BlockPos.Mutable();

                for (int xMark = position.getX() - offset; xMark <= position.getX() + offset && canGrow; ++xMark) {
                    for (int zMark = position.getZ() - offset; zMark <= position.getZ() + offset && canGrow; ++zMark) {
                        if (yMark >= 0 && yMark < 256) {
                            if (!func_214587_a(worldIn,mutablePos.setPos(xMark, yMark, zMark))) {
                                canGrow = false;
                            }
                        } else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            } else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight() - baseHeight - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int posX = position.getX();
                int posZ = position.getZ();
                int k1 = 0;
                int offset = 1;

                for (int base = 0; base < baseHeight; ++base) {
                    int i2 = position.getY() + base;
                    BlockPos blockpos = new BlockPos(posX, i2, posZ);

                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.setLogBlockState(worldIn, rand, blockpos, logPos, boundingBox, config);
                        k1 = i2;
                    }

                    if (base > baseHeight / 2) {
                        if (isAirOrLeaves(worldIn, blockpos)) {
                            this.setLogBlockState(worldIn, rand, new BlockPos(posX - offset, i2, posZ), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, new BlockPos(posX + offset, i2, posZ), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, new BlockPos(posX, i2, posZ - offset), logPos, boundingBox, config);
                            this.setLogBlockState(worldIn, rand, new BlockPos(posX, i2, posZ + offset), logPos, boundingBox, config);
                            if (base % 2 == 0) {
                                offset += 1;
                            }
                        }
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, k1 + 1, posZ);

                for (int j3 = -1; j3 <= 1; ++j3) {
                    for (int i4 = -1; i4 <= 1; ++i4) {
                        if (Math.abs(j3) != 1 || Math.abs(i4) != 1){
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(j3 - 3, 0, i4), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(j3 + 3, 0, i4), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(j3, 0, i4 - 3), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(j3, 0, i4 + 3), leavesPos, boundingBox, config);
                        }
                    }
                }

                BlockPos blockpos3 = new BlockPos(posX, k1, posZ);

                for (int j3 = -2; j3 <= 2; ++j3) {
                    for (int i4 = -2; i4 <= 2; ++i4) {
                        if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                            this.setLeavesBlockState(worldIn, rand, blockpos3.add(j3 - 3, 0, i4), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos3.add(j3 + 3, 0, i4), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos3.add(j3, 0, i4 - 3), leavesPos, boundingBox, config);
                            this.setLeavesBlockState(worldIn, rand, blockpos3.add(j3, 0, i4 + 3), leavesPos, boundingBox, config);
                        }
                    }
                }

                this.setLeavesBlockState(worldIn, rand, blockpos3.up(), leavesPos, boundingBox, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
