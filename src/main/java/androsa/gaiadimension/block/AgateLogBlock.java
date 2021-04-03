package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class AgateLogBlock extends RotatedPillarBlock {

    private final Supplier<Block> strippedBlock;

    public AgateLogBlock(Properties props) {
        super(props);

        strippedBlock = null;
    }

    public AgateLogBlock(Supplier<Block> stripped, Properties props) {
        super(props);

        strippedBlock = stripped;
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (strippedBlock != null) {
            if (player.getItemInHand(hand).getItem() instanceof AxeItem) {
                if (!world.isClientSide()) {
                    world.setBlock(pos, strippedBlock.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)), 11);
                    if (player != null) {
                        player.getItemInHand(hand).hurtAndBreak(1, player, (consumer) -> consumer.broadcastBreakEvent(hand));
                    }
                }
                world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }
}
