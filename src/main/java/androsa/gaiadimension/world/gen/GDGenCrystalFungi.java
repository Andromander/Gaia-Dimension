package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.block.GDCrystalFungus;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenCrystalFungi extends WorldGenerator {
    private final GDCrystalFungus block;

    public GDGenCrystalFungi(GDCrystalFungus blockIn) {
        this.block = blockIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (blockpos.getY() < worldIn.getHeight() - 1) && this.block.canBlockStay(worldIn, blockpos, this.block.getDefaultState())) {
                worldIn.setBlockState(blockpos, this.block.getDefaultState(), 2);
            }
        }

        return true;
    }
}
