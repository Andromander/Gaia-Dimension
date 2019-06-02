package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDAuraShoot;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenAuraShoots extends WorldGenerator {

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        for (int i = 0; i < 20; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (worldIn.isAirBlock(blockpos)) {
                    int j = 7 + rand.nextInt(5);

                    for (int k = 0; k < j; ++k) {
                        if (((GDAuraShoot)GDBlocks.aura_shoot).canBlockStay(worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos.up(k), GDBlocks.aura_shoot.getDefaultState(), 2);
                        }
                    }
                }
            }

        return true;
    }
}
