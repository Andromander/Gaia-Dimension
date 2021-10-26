package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class PurpleAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public PurpleAgateTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(WorldGenLevel worldIn, Random rand, BlockPos position, BiConsumer<BlockPos, BlockState> logPos, BiConsumer<BlockPos, BlockState> leavesPos, T config) {
        int height = rand.nextInt(3) + rand.nextInt(3) + config.minHeight;
        boolean isValidBonemealTarget = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int cy = position.getY(); cy <= position.getY() + 1 + height; ++cy) {
                int k = 1;

                if (cy == position.getY()) {
                    k = 0;
                }

                if (cy >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int cx = position.getX() - k; cx <= position.getX() + k && isValidBonemealTarget; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && isValidBonemealTarget; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!validTreePos(worldIn, blockpos$mutableblockpos.set(cx, cy, cz))) {
                                isValidBonemealTarget = false;
                            }
                        } else {
                            isValidBonemealTarget = false;
                        }
                    }
                }
            }

            if (!isValidBonemealTarget) {
                return false;
            } else if (isSoil(worldIn, position.below(), config.getSapling(rand, position)) && position.getY() < worldIn.getHeight() - height - 1) {
                this.setBlock(worldIn, position.below(), ModBlocks.heavy_soil.get().defaultBlockState());
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;
                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);

                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == height - 2) {
                            for (int length = 1; length <= 2; ++length) {
                                this.placeLogAt(rand, blockpos.north(length), Direction.Axis.Z, logPos, config);
                                this.placeLogAt(rand, blockpos.south(length), Direction.Axis.Z, logPos, config);
                                this.placeLogAt(rand, blockpos.east(length), Direction.Axis.X, logPos, config);
                                this.placeLogAt(rand, blockpos.west(length), Direction.Axis.X, logPos, config);
                            }
                        } else if (base == height - 1) {
                            for (int length = 3; length <= 4; ++length) {
                                this.placeLogAt(rand, blockpos.north(length), Direction.Axis.Z, logPos, config);
                                this.placeLogAt(rand, blockpos.south(length), Direction.Axis.Z, logPos, config);
                                this.placeLogAt(rand, blockpos.east(length), Direction.Axis.X, logPos, config);
                                this.placeLogAt(rand, blockpos.west(length), Direction.Axis.X, logPos, config);
                            }
                        } else {
                            this.setLog(worldIn, rand, blockpos, logPos, config);
                        }
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        for (int l5 = -1; l5 <= 1; ++l5) {
                            if (Math.abs(k3) != 1 || Math.abs(j4) != 1 || Math.abs(l5) != 1){
                                this.setLeaves(worldIn, rand, blockpos2.offset(k3 + 4, l5, j4), leavesPos, config);
                                this.setLeaves(worldIn, rand, blockpos2.offset(k3 - 4, l5, j4), leavesPos, config);
                                this.setLeaves(worldIn, rand, blockpos2.offset(k3 , l5, j4 + 4), leavesPos, config);
                                this.setLeaves(worldIn, rand, blockpos2.offset(k3, l5, j4 - 4), leavesPos, config);
                            }
                        }
                    }
                }

                BlockPos blockpos3 = blockpos2.below(2);
                this.setLeaves(worldIn, rand, blockpos3.north(1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos3.south(1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos3.east(1), leavesPos, config);
                this.setLeaves(worldIn, rand, blockpos3.west(1), leavesPos, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void placeLogAt(Random rand, BlockPos pos, Direction.Axis axis, BiConsumer<BlockPos, BlockState> logPos, T config) {
        Function<BlockState, BlockState> function = Function.identity();
        logPos.accept(pos, function.apply(config.trunkProvider.getState(rand, pos).setValue(RotatedPillarBlock.AXIS, axis)));
    }
}
