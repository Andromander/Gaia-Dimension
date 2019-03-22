package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.GDGaiaStoneFurnace;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityGaiaStoneFurnace extends TileEntity implements ITickable, ISidedInventory {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 2, 1 };
    private static final int[] slotsSides = new int [] { 1 };
    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(4, ItemStack.EMPTY);
    public int furnaceTimer;
    public int currentItemSmeltTime;
    public int cookTime;
    private String name;

    @Override
    public int getSizeInventory() {
        return furnaceItemStacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return furnaceItemStacks.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(furnaceItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(furnaceItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        furnaceItemStacks.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? name : "container.gaiadimension.gaia_stone_furnace";
    }

    @Override
    public boolean hasCustomName() {
        return name != null && name.length() > 0;
    }

    public void setCustomInventoryName(String invName) {
        name = invName;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        furnaceItemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, furnaceItemStacks);
        furnaceTimer = compound.getShort("furnaceTimer");
        cookTime = compound.getShort("CookTime");
        currentItemSmeltTime = getItemBurnTime(furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
            name = compound.getString("CustomName");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setShort("furnaceTimer", (short)furnaceTimer);
        par1.setShort("CookTime", (short)cookTime);
        ItemStackHelper.saveAllItems(par1, furnaceItemStacks);

        if (hasCustomName())
            par1.setString("CustomName", name);

        return par1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getSmeltProgressScaled(int par1) {
        return cookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getSmeltTimeRemainingScaled(int par1) {
        if (currentItemSmeltTime == 0)
            currentItemSmeltTime = 200;

        return furnaceTimer * par1 / currentItemSmeltTime;
    }

    public boolean isBurning() {
        return furnaceTimer > 0;
    }

    @Override
    public void update() {
        boolean flag = furnaceTimer > 0;
        boolean flag1 = false;

        if (furnaceTimer > 0)
            --furnaceTimer;

        if (!world.isRemote) {
            if (furnaceTimer == 0 && canSmelt()) {
                currentItemSmeltTime = furnaceTimer = getItemBurnTime(furnaceItemStacks.get(1));

                if (furnaceTimer > 0) {
                    flag1 = true;

                    if (!furnaceItemStacks.get(1).isEmpty()) {
                        Item item = furnaceItemStacks.get(1).getItem();
                        furnaceItemStacks.get(1).shrink(1);

                        if (furnaceItemStacks.get(1).isEmpty())
                            furnaceItemStacks.set(1, item.getContainerItem(furnaceItemStacks.get(1)));
                    }
                }
            }

            if (isBurning() && canSmelt()) {
                ++cookTime;

                if (cookTime == 200) {
                    cookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else
                cookTime = 0;

            if (flag != furnaceTimer > 0) {
                flag1 = true;
                GDGaiaStoneFurnace.setState(furnaceTimer > 0, world, pos);
            }
        }

        if (flag1)
            markDirty();
    }

    private boolean canSmelt() {
        if ((this.furnaceItemStacks.get(0)).isEmpty()) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks.get(0));

            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = this.furnaceItemStacks.get(2);

                return itemstack1.isEmpty() ||
                        itemstack1.isItemEqual(itemstack) && (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize() ||
                        itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize());
            }
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = this.furnaceItemStacks.get(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = this.furnaceItemStacks.get(2);

            if (itemstack2.isEmpty()) {
                this.furnaceItemStacks.set(2, itemstack1.copy());
            } else if (itemstack2.getItem() == itemstack1.getItem()) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !(this.furnaceItemStacks.get(1)).isEmpty() && (this.furnaceItemStacks.get(1)).getItem() == Items.BUCKET) {
                this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack);
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) == this && player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack stack) {
        return par1 != 2 && (par1 != 1 || isItemFuel(stack));
    }

    @Override
    public int[] getSlotsForFace(EnumFacing face) {
        return face == EnumFacing.DOWN ? slotsBottom : face == EnumFacing.UP ? slotsTop : slotsSides;
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack stack, EnumFacing face) {
        return isItemValidForSlot(par1, stack);
    }

    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, EnumFacing face) {
        if (face == EnumFacing.DOWN && par1 == 1) {
            Item item = par2ItemStack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {

        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public ITextComponent getDisplayName() {
        return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName());
    }

    private IItemHandler handlerTop = new SidedInvWrapper(this, EnumFacing.UP);
    private IItemHandler handlerBottom = new SidedInvWrapper(this, EnumFacing.DOWN);
    private IItemHandler handlerSide = new SidedInvWrapper(this, EnumFacing.WEST);

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : furnaceItemStacks)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }
}