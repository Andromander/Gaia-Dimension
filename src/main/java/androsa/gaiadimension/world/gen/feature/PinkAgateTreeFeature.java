package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.config.GaiaTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;

@ParametersAreNonnullByDefault
public class PinkAgateTreeFeature<T extends GaiaTreeFeatureConfig> extends GaiaTreeFeature<T> {

    public PinkAgateTreeFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean generate(ISeedReader worldIn, Random rand, BlockPos position, Set<BlockPos> logPos, Set<BlockPos> leavesPos, MutableBoundingBox boundingBox, T config) {
        int height = rand.nextInt(3) + rand.nextInt(3) + config.minHeight;
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
                            if (!isReplaceableAt(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
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
            } else if (isSoil(worldIn, position.down(), config.getSapling(rand, position)) && position.getY() < worldIn.getHeight() - height - 1) {
                this.setBlockState(worldIn, position.down(), ModBlocks.heavy_soil.get().getDefaultState(), boundingBox);
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int i2 = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, i2, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.setLogBlockState(worldIn, rand, blockpos, logPos, boundingBox, config);
                        posY = i2;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);

                for (int j3 = -3; j3 <= 3; ++j3) {
                    for (int i4 = -3; i4 <= 3; ++i4) {
                        if (Math.abs(j3) != 3 || Math.abs(i4) != 3){
                            this.setLeavesBlockState(worldIn, rand, blockpos2.add(j3, 0, i4), leavesPos, boundingBox, config);
                        }
                    }
                }

                blockpos2 = blockpos2.up();

                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        this.setLeavesBlockState(worldIn, rand, blockpos2.add(k3, 0, j4), leavesPos, boundingBox, config);
                    }
                }

                this.setLeavesBlockState(worldIn, rand, blockpos2.east(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.west(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.south(2), leavesPos, boundingBox, config);
                this.setLeavesBlockState(worldIn, rand, blockpos2.north(2), leavesPos, boundingBox, config);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
