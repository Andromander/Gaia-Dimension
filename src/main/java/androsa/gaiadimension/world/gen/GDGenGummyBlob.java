package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class GDGenGummyBlob extends WorldGenerator {
    private final Block block;
    private final int startRadius;

    public GDGenGummyBlob(Block blockIn, int startRadiusIn) {
        super(false);
        this.block = blockIn;
        this.startRadius = startRadiusIn;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        while (true) {
            label50:{
                if (position.getY() > 3) {
                    if (worldIn.isAirBlock(position.down())) {
                        break label50;
                    }

                    Block block = worldIn.getBlockState(position.down()).getBlock();

                    if (block != GDBlocks.glitter_grass && block != GDBlocks.heavy_soil && block != GDBlocks.gaia_stone) {
                        break label50;
                    }
                }

                if (position.getY() <= 3) {
                    return false;
                }

                int radius = this.startRadius;

                for (int i = 0; radius >= 0 && i < 3; ++i) {
                    int x = radius + rand.nextInt(2);
                    int y = radius + rand.nextInt(2);
                    int z = radius + rand.nextInt(2);
                    float f = (float)(x + y + z) * 0.333F + 0.5F;

                    for (BlockPos blockpos : BlockPos.getAllInBoxMutable(position.add(-x, -y, -z), position.add(x, y, z))) {
                        if (blockpos.distanceSq(position) <= (double)(f * f)) {
                            worldIn.setBlockState(blockpos, this.block.getDefaultState(), 4);
                        }
                    }

                    position = position.add(-(radius + 1) + rand.nextInt(2 + radius * 2), 0 - rand.nextInt(2), -(radius + 1) + rand.nextInt(2 + radius * 2));
                }

                return true;
            }
            position = position.down();
        }
    }
}
