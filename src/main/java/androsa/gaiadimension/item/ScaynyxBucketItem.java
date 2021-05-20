package androsa.gaiadimension.item;

import androsa.gaiadimension.block.GaiaFluidBlock;
import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Supplier;

public class ScaynyxBucketItem extends BucketItem {

    public ScaynyxBucketItem(Supplier<? extends Fluid> fluid) {
        super(fluid, new Properties().stacksTo(1).tab(GaiaItemGroups.GAIA_ITEMS));
    }

    @Override
    protected ItemStack getEmptySuccessItem(ItemStack stack, PlayerEntity entity) {
        return !entity.abilities.instabuild ? new ItemStack(ModItems.scaynyx_bucket.get()) : stack;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, player, getFluid() == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
        ActionResult<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, itemstack, raytraceresult);
        if (ret != null)
            return ret;
        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
            BlockPos blockpos = blockraytraceresult.getBlockPos();
            Direction direction = blockraytraceresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {

                if (getFluid() == Fluids.EMPTY) {
                    //Empty Bucket to fill
                    BlockState fluidblock = world.getBlockState(blockpos);
                    if (fluidblock.getBlock() instanceof GaiaFluidBlock) {
                        Fluid fluid = ((GaiaFluidBlock)fluidblock.getBlock()).takeLiquid(world, blockpos, fluidblock);
                        if (fluid != Fluids.EMPTY) {
                            player.awardStat(Stats.ITEM_USED.get(this));

                            SoundEvent soundevent = getFluid().getAttributes().getFillSound();
                            if (soundevent == null) soundevent = fluid.is(FluidTags.LAVA) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL;
                            player.playSound(soundevent, 1.0F, 1.0F);
                            ItemStack itemstack1 = DrinkHelper.createFilledResult(itemstack, player, new ItemStack(fluid.getBucket()));
                            if (!world.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity)player, new ItemStack(fluid.getBucket()));
                            }

                            return ActionResult.sidedSuccess(itemstack1, world.isClientSide());
                        }
                    }

                    return ActionResult.fail(itemstack);
                } else {
                    //Full Bucket to Empty
                    BlockState blockstate = world.getBlockState(blockpos);
                    BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
                    if (this.emptyBucket(player, world, blockpos2, blockraytraceresult)) {
                        this.checkExtraContent(world, itemstack, blockpos2);
                        if (player instanceof ServerPlayerEntity) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)player, blockpos2, itemstack);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        return ActionResult.sidedSuccess(this.getEmptySuccessItem(itemstack, player), world.isClientSide());
                    } else {
                        return ActionResult.fail(itemstack);
                    }
                }
            } else {
                return ActionResult.fail(itemstack);
            }
        }
    }

    private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, getFluid());
    }

    /*
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!this.isInCreativeTab(tab))
            return;

        subItems.add(empty);

        for (final Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            // Add all fluids that the bucket can be filled with
            final FluidStack fStack = new FluidStack(fluid, getCapacity());
            final ItemStack stack = new ItemStack(this);
            final IFluidHandlerItem fluidHandler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);

            if (fluidHandler != null && fluidHandler.fill(fStack, true) == fStack.amount) {
                final ItemStack filled = fluidHandler.getMenuProvider();
                subItems.add(filled);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        final ItemStack heldItem = player.getItemInHand(hand);
        final FluidStack fluidStack = getFluid(heldItem);

        // If the bucket is full, call the super method to try and empty it
        if (fluidStack != null)
            return super.use(world, player, hand);

        // If the bucket is empty, try and fill it
        final RayTraceResult target = this.rayTrace(world, player, true);

        if (target == null || target.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(ActionResultType.PASS, heldItem);
        }

        final BlockPos pos = target.getBlockPos();
        final ItemStack singleBucket = heldItem.copy();
        singleBucket.setCount(1);
        final FluidActionResult filledResult = FluidUtil.tryPickUpFluid(singleBucket, player, world, pos, target.sideHit);

        if (filledResult.isSuccess()) {
            final ItemStack filledBucket = filledResult.result;

            if (player.isCreative())
                return new ActionResult<>(ActionResultType.SUCCESS, heldItem);

            heldItem.shrink(1);
            if (heldItem.isEmpty())
                return new ActionResult<>(ActionResultType.SUCCESS, filledBucket);

            ItemHandlerHelper.giveItemToPlayer(player, filledBucket);

            return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
        }

        return new ActionResult<>(ActionResultType.PASS, heldItem);
    }*/

    /*
    @Nullable
    @Override
    public FluidStack getFluid(ItemStack container) {
        return FluidUtil.getFluidContained(container);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
        // FluidBucketWrapper only works with Forge's UniversalBucket instance, use a different IFluidHandlerItem implementation instead
        return new GDFluidHandlerBucket(stack, getCapacity());
    }*/
}
