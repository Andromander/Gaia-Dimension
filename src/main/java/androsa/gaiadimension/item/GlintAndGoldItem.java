package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class GlintAndGoldItem extends Item {

    public GlintAndGoldItem() {
        super(new Properties().durability(32).tab(GaiaItemGroups.GAIA_ITEMS));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        IWorld iworld = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(context.getClickedFace());

        if (canCreatePortal(iworld.getBlockState(blockpos1), iworld, blockpos1)) {
            iworld.playSound(playerentity, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate1 = ModBlocks.gold_fire.get().defaultBlockState();
            iworld.setBlock(blockpos1, blockstate1, 11);
            ItemStack itemstack = context.getItemInHand();

            if (playerentity instanceof ServerPlayerEntity) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos1, itemstack);
                itemstack.hurtAndBreak(1, playerentity, (entity) -> entity.broadcastBreakEvent(context.getHand()));
            }

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.FAIL;
        }
    }

    //TODO: Remove on new portal
    public static boolean canCreatePortal(BlockState state, IWorld world, BlockPos pos) {
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
