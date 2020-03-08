package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class PurpleAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends AbstractTreeFeature<T> {

    public PurpleAgateTreeFeature(Function<Dynamic<?>, T> configIn) {
        super(configIn);
    }

    @Override
    protected boolean generate(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 7;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int cy = position.getY(); cy <= position.getY() + 1 + height; ++cy) {
                int k = 1;

                if (cy == position.getY()) {
                    k = 0;
                }

                if (cy >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for (int cx = position.getX() - k; cx <= position.getX() + k && canGrow; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && canGrow; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
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
            } else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight() - height - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;
                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);

                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == height - 2) {
                            for (int length = 1; length <= 2; ++length) {
                                this.placeLogAt(worldIn, rand, blockpos.north(length), Direction.Axis.Z, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.south(length), Direction.Axis.Z, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.east(length), Direction.Axis.X, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.west(length), Direction.Axis.X, logPos, boundingBox, config);
                            }
                        } else if (base == height - 1) {
                            for (int length = 3; length <= 4; ++length) {
                                this.placeLogAt(worldIn, rand, blockpos.north(length), Direction.Axis.Z, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.south(length), Direction.Axis.Z, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.east(length), Direction.Axis.X, logPos, boundingBox, config);
                                this.placeLogAt(worldIn, rand, blockpos.west(length), Direction.Axis.X, logPos, boundingBox, config);
                            }
                        } else {
                            this.setLogBlockState(worldIn, rand, blockpos, logPos, boundingBox, config);
                        }
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        for (int l5 = -1; l5 <= 1; ++l5) {
                            if (Math.abs(k3) != 1 || Math.abs(j4) != 1 || Math.abs(l5) != 1){
                                this.setLeavesBlockState(worldIn, rand, blockpos2.add(k3 + 4, l5, j4), leavesPos, boundingBox, config);
                                this.setLeavesBlockState(worldIn, rand, blockpos2.add(k3 - 4, l5, j4), leavesPos, boundingBox, config);
                                this.setLeavesBlockState(worldIn, rand, blockpos2.add(k3 , l5, j4 + 4), leavesPos, boundingBox, config);
                                this.setLeavesBlockState(worldIn, rand, blockpos2.add(k3, l5, j4 - 4), leavesPos, boundingBox, config);
                            }
                        }
                    }
                }

                BlockPos blockpos3 = blockpos2.down(2);
                this.setLeavesBlockState(worldIn, rand, blockpos3.north(1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos3.south(1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos3.east(1), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos3.west(1), leavesPos, boundingBox, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void placeLogAt(IWorldGenerationReader reader, Random rand, BlockPos pos, Direction.Axis axis, Set<BlockPos> logPos, MutableBoundingBox boundingBox, T config) {
        this.setBlockState(reader, pos, config.trunkProvider.getBlockState(rand, pos).with(RotatedPillarBlock.AXIS, axis), boundingBox);
        logPos.add(pos.toImmutable());
    }
}
