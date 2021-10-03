package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class GlintAndGoldItem extends Item {

    public GlintAndGoldItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player playerentity = context.getPlayer();
        LevelAccessor iworld = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(context.getClickedFace());

        if (canCreatePortal(iworld.getBlockState(blockpos1), iworld, blockpos1)) {
            iworld.playSound(playerentity, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, iworld.getRandom().nextFloat() * 0.4F + 0.8F);
            BlockState blockstate1 = ModBlocks.gold_fire.get().defaultBlockState();
            iworld.setBlock(blockpos1, blockstate1, 11);
            ItemStack itemstack = context.getItemInHand();

            if (playerentity instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerentity, blockpos1, itemstack);
                itemstack.hurtAndBreak(1, playerentity, (entity) -> entity.broadcastBreakEvent(context.getHand()));
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.FAIL;
        }
    }

    //TODO: Remove on new portal
    public static boolean canCreatePortal(BlockState state, LevelAccessor world, BlockPos pos) {
        BlockState blockstate = ModBlocks.gold_fire.get().defaultBlockState();
        boolean flag = false;

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (world.getBlockState(pos.relative(direction)).getBlock() == ModBlocks.keystone_block.get() && ModBlocks.gaia_portal.get().isPortal(world, pos) != null) {
                flag = true;
            }
        }

        return state.isAir() && (blockstate.canSurvive(world, pos) || flag);
    }
}
