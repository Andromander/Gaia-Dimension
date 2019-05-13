package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenBogPatch extends WorldGenerator {
    private int numberOfBlocks;

    public GDGenBogPatch(int i) {
        numberOfBlocks = i;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {
        int range = random.nextInt(numberOfBlocks - 2) + 2;
        int yRange = 1;

        for (int dx = pos.getX() - range; dx <= pos.getX() + range; dx++) {
            for (int dz = pos.getZ() - range; dz <= pos.getZ() + range; dz++) {
                int l1 = dx - pos.getX();
                int i2 = dz - pos.getZ();

                if (l1 * l1 + i2 * i2 > range * range) {
                    continue;
                }

                for (int dy = pos.getY() - yRange; dy <= pos.getY() + yRange; dy++) {
                    BlockPos dPos = new BlockPos(dx, dy, dz);
                    Block blockThere = world.getBlockState(dPos).getBlock();
                    if (blockThere == GDBlocks.murky_grass) {
                        if (random.nextInt(6) != 0) {
                            world.setBlockState(dPos, GDBlocks.impure_sludge.getDefaultState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
