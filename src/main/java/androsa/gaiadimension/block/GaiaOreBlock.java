package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class GaiaOreBlock extends Block {

    private final int minDrop;
    private final int maxDrop;

    public GaiaOreBlock(Properties props) {
        this(props, 0, 0);
    }

    public GaiaOreBlock(Properties props, int minExp, int maxExp) {
        super(props);

        minDrop = minExp;
        maxDrop = maxExp;
    }

    @Override
    @Deprecated
    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 0) {
            if (minDrop != 0 && maxDrop != 0) {
                return MathHelper.nextInt(RANDOM, minDrop, maxDrop);
            }
        }
        return 0;
    }
}
