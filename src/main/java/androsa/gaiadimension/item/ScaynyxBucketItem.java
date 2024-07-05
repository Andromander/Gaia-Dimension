package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class ScaynyxBucketItem extends BucketItem {

    public ScaynyxBucketItem(Properties props, Supplier<? extends Fluid> fluid) {
        super(fluid.get(), props);
    }

    public static ItemStack getEmptySuccessItem(ItemStack stack, Player entity) {
        return !entity.getAbilities().instabuild ? new ItemStack(ModItems.scaynyx_bucket.get()) : stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult raytraceresult = getPlayerPOVHitResult(world, player, content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        if (raytraceresult.getType() == BlockHitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (raytraceresult.getType() != BlockHitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            BlockPos blockpos = raytraceresult.getBlockPos();
            Direction direction = raytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {

                if (content == Fluids.EMPTY) {
                    //Empty Bucket to fill
                    BlockState fluidblock = world.getBlockState(blockpos);
                    if (fluidblock.getBlock() instanceof BucketPickup fluid) {
                        ItemStack fluidstack = fluid.pickupBlock(player, world, blockpos, fluidblock);
                        if (!fluidstack.isEmpty()) {
                            player.awardStat(Stats.ITEM_USED.get(this));
                            fluid.getPickupSound(fluidblock).ifPresent((event) -> player.playSound(event, 1.0F, 1.0F));
                            world.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, fluidstack);
                            if (!world.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, fluidstack);
                            }

                            return InteractionResultHolder.sidedSuccess(itemstack1, world.isClientSide());
                        }
                    }

                    return InteractionResultHolder.fail(itemstack);
                } else {
                    //Full Bucket to Empty
                    BlockState blockstate = world.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(player, world, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyContents(player, world, blockpos2, raytraceresult, itemstack)) {
                        this.checkExtraContent(player, world, itemstack, blockpos2);
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos2, itemstack);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, player), world.isClientSide());
                    } else {
                        return InteractionResultHolder.fail(itemstack);
                    }
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    @Override
    public boolean canBlockContainFluid(Player player, Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer)blockstate.getBlock()).canPlaceLiquid(player, worldIn, posIn, blockstate, content);
    }

    /*
    @Nullable
    @Override
    public FluidStack getFluid(ItemStack container) {
        return FluidUtil.getFluidContained(container);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        // FluidBucketWrapper only works with Forge's UniversalBucket instance, use a different IFluidHandlerItem implementation instead
        return new GDFluidHandlerBucket(stack, getCapacity());
    }*/
}
