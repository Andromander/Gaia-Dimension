package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenGeyser extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int cx = 0; cx < 5; cx++) {
            for (int cz = 0; cz < 5; cz++) {
                BlockPos pos = position.add(cx - 2, 0, cz - 2);

                if (worldIn.isBlockLoaded(pos)) {
                    Block blockBelow = worldIn.getBlockState(pos.down()).getBlock();

                    if (blockBelow != GDBlocks.murky_grass) {
                        return false;
                    }
                }
            }
        }

        for (int bx = -2; bx <= 2; bx++) {
            for (int bz = -2; bz <= 2; bz++) {
                if (Math.abs(bx) != 2 || Math.abs(bz) != 2) {
                    if (bx == 0 && bz == 0) {
                        worldIn.setBlockState(position.add(bx, 0, bz), GDBlocks.geyser_block.getDefaultState());
                    } else {
                        if (bx < 2 && bz < 2 && bx > -2 && bz > -2) {
                            worldIn.setBlockState(position.add(bx, 0, bz), GDBlocks.boggy_soil.getDefaultState());
                        } else {
                            worldIn.setBlockState(position.add(bx, 0, bz), GDBlocks.murky_grass.getDefaultState());
                        }
                    }
                }
            }
        }
        for (int tx = -1; tx <= 1; tx++) {
            for (int tz = -1; tz <= 1; tz++) {
                if (!(tx == 0 && tz == 0)) {
                    worldIn.setBlockState(position.add(tx, 1, tz), GDBlocks.murky_grass.getDefaultState());
                }
            }
        }
        return true;
    }
}
