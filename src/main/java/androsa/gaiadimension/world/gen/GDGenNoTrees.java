package androsa.gaiadimension.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class GDGenNoTrees extends WorldGenTrees {

    public GDGenNoTrees() {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        return false;
    }
}
