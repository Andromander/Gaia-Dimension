package androsa.gaiadimension.item;

import androsa.gaiadimension.fluid.GDFluidHandlerBucket;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class GDScaynyxBucket extends UniversalBucket implements ModelRegisterCallback {
    private final ItemStack empty = new ItemStack(this);

    public GDScaynyxBucket() {
        this(Fluid.BUCKET_VOLUME);
    }

    public GDScaynyxBucket(int capacity) {
        super(capacity, ItemStack.EMPTY, true);
    }

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
                final ItemStack filled = fluidHandler.getContainer();
                subItems.add(filled);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public String getItemStackDisplayName(final ItemStack stack) {
        final FluidStack fluidStack = getFluid(stack);
        final String unlocalizedName = this.getUnlocalizedNameInefficiently(stack);

        // If the bucket is empty, translate the unlocalised name directly
        if (fluidStack == null) {
            return I18n.translateToLocal(unlocalizedName + ".name").trim();
        }

        // If there's a fluid-specific translation, use it
        final String fluidUnlocalisedName = unlocalizedName + ".filled." + fluidStack.getFluid().getName() + ".name";

        if (I18n.canTranslate(fluidUnlocalisedName)) {
            return I18n.translateToLocal(fluidUnlocalisedName);
        }

        // Else translate the filled name directly, formatting it with the fluid name
        return I18n.translateToLocalFormatted(unlocalizedName + ".filled.name", fluidStack.getLocalizedName());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        final ItemStack heldItem = player.getHeldItem(hand);
        final FluidStack fluidStack = getFluid(heldItem);

        // If the bucket is full, call the super method to try and empty it
        if (fluidStack != null)
            return super.onItemRightClick(world, player, hand);

        // If the bucket is empty, try and fill it
        final RayTraceResult target = this.rayTrace(world, player, true);

        if (target == null || target.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, heldItem);
        }

        final BlockPos pos = target.getBlockPos();
        final ItemStack singleBucket = heldItem.copy();
        singleBucket.setCount(1);
        final FluidActionResult filledResult = FluidUtil.tryPickUpFluid(singleBucket, player, world, pos, target.sideHit);

        if (filledResult.isSuccess()) {
            final ItemStack filledBucket = filledResult.result;

            if (player.capabilities.isCreativeMode)
                return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);

            heldItem.shrink(1);
            if (heldItem.isEmpty())
                return new ActionResult<>(EnumActionResult.SUCCESS, filledBucket);

            ItemHandlerHelper.giveItemToPlayer(player, filledBucket);

            return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
        }

        return new ActionResult<>(EnumActionResult.PASS, heldItem);
    }

    @Nullable
    @Override
    public FluidStack getFluid(ItemStack container) {
        return FluidUtil.getFluidContained(container);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        // FluidBucketWrapper only works with Forge's UniversalBucket instance, use a different IFluidHandlerItem implementation instead
        return new GDFluidHandlerBucket(stack, getCapacity());
    }
}
