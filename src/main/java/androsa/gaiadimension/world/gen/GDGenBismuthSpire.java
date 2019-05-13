package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenBismuthSpire extends WorldGenerator {

    private final int startHeight;

    public GDGenBismuthSpire(int startHeightIn) {
        this.startHeight = startHeightIn;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int cx = 0; cx < 3; cx++) {
            for (int cz = 0; cz < 3; cz++) {
                BlockPos pos = position.add(cx - 1, 0, cz - 1);

                if (worldIn.isBlockLoaded(pos)) {
                    Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();

                    if (blockBelow != GDBlocks.murky_grass) {
                        return false;
                    }
                }
            }
        }

        int height = startHeight + rand.nextInt(4);
        int heightA = height - rand.nextInt(4) - 2;
        int heightD = height / 2  - (rand.nextInt(5) - 2);
        int heightE = rand.nextInt(3);

        for (int i = 0; i < height; i++) {
            if (i < heightE) {
                for (int ex = -2; ex <= 2; ex++) {
                    for (int ez = -2; ez <= 2; ez++) {
                        if (Math.abs(ex) != 2 || Math.abs(ez) != 2)
                        setBismuthType(worldIn, rand, position.add(ex, i, ez));
                    }
                }
            } else if (i < heightD) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        setBismuthType(worldIn, rand, position.add(dx, i, dz));
                    }
                }
            } else if (i < heightA) {
                for (int ax = -1; ax <= 1; ax++) {
                    for (int az = -1; az <= 1; az++) {
                        if (Math.abs(ax) != 1 || Math.abs(az) != 1) {
                            setBismuthType(worldIn, rand, position.add(ax, i, az));
                        }
                    }
                }
            } else {
                setBismuthType(worldIn, rand, position.up(i));
            }
        }
        return true;
    }

    private void setBismuthType(World world, Random random, BlockPos pos) {
        IBlockState state;

        if (random.nextInt(30) == 0) {
            state = GDBlocks.bismuth_block.getDefaultState();
        } else if (random.nextInt(15) == 0) {
            state = GDBlocks.active_rock.getDefaultState();
        } else {
            state = GDBlocks.impure_rock.getDefaultState();
        }
        world.setBlockState(pos, state);
    }
}

