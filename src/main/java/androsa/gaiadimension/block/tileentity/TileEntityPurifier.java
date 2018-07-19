package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.GDPurifier;
import androsa.gaiadimension.recipe.PurifierRecipes;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityPurifier extends TileEntity implements ISidedInventory, ITickable {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 2, 1, 3 };
    private static final int[] slotsSides = new int [] { 1 };
    private NonNullList<ItemStack> purifyItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public int purifyTimer;
    public int currentItemPurifyTime;
    public int purifyClearTime;
    private String name;

    @Override
    public int getSizeInventory() {
        return purifyItemStacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return purifyItemStacks.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(purifyItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(purifyItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        purifyItemStacks.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? name : "container.gaiadimension.purifier";
    }

    @Override
    public boolean hasCustomName() {
        return name != null && name.length() > 0;
    }

    public void setCustomInventoryName(String invName) {
        name = invName;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        purifyItemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(par1, purifyItemStacks);
        purifyTimer = par1.getShort("purifyTimer");
        purifyClearTime = par1.getShort("FormTime");
        currentItemPurifyTime = getPurifyingTime(purifyItemStacks.get(1));

        if (par1.hasKey("CustomName", 8))
            name = par1.getString("CustomName");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setShort("purifyTimer", (short)purifyTimer);
        par1.setShort("FormTime", (short)purifyClearTime);
        ItemStackHelper.saveAllItems(par1, purifyItemStacks);

        if (hasCustomName())
            par1.setString("CustomName", name);

        return par1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getPurifyProgressScaled(int par1) {
        return purifyClearTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getPurifyTimeRemainingScaled(int par1) {
        if (currentItemPurifyTime == 0)
            currentItemPurifyTime = 200;

        return purifyTimer * par1 / currentItemPurifyTime;
    }

    public boolean isPurifying() {
        return purifyTimer > 0;
    }

    @Override
    public void update() {
        boolean flag = purifyTimer > 0;
        boolean flag1 = false;

        if (purifyTimer > 0)
            --purifyTimer;

        if (!world.isRemote) {
            if (purifyTimer == 0 && canRefactor()) {
                currentItemPurifyTime = purifyTimer = getPurifyingTime(purifyItemStacks.get(1));

                if (purifyTimer > 0) {
                    flag1 = true;

                    if (!purifyItemStacks.get(1).isEmpty()) {
                        Item item = purifyItemStacks.get(1).getItem();
                        purifyItemStacks.get(1).shrink(1);

                        if (purifyItemStacks.get(1).isEmpty())
                            purifyItemStacks.set(1, item.getContainerItem(purifyItemStacks.get(1)));
                    }
                }
            }

            if (isPurifying() && canRefactor()) {
                ++purifyClearTime;

                if (purifyClearTime == 200) {
                    purifyClearTime = 0;
                    purifyItem();
                    flag1 = true;
                }
            } else
                purifyClearTime = 0;

            if (flag != purifyTimer > 0) {
                flag1 = true;
                GDPurifier.updatePurifyBlockState(purifyTimer > 0, world, pos);
            }
        }

        if (flag1)
            markDirty();
    }

    private boolean canRefactor() {
        if (purifyItemStacks.get(0).isEmpty() || PurifierRecipes.instance().getPurifyingResult(purifyItemStacks.get(0)) == null)
            return false;
        else {
            ItemStack[] itemstack = PurifierRecipes.instance().getPurifyingResult(purifyItemStacks.get(0));

            ItemStack stack = purifyItemStacks.get(2), stack1 = purifyItemStacks.get(3);

            if(itemstack[0].isEmpty() && itemstack[1].isEmpty() || itemstack[0].isEmpty()) return false;
            if(stack.isEmpty() && stack1.isEmpty()) return true;
            if(itemstack[1].isEmpty()){
                if(stack.isEmpty()) return true;
                if(!stack.isItemEqual(itemstack[0])) return false;

                int result = stack.getCount() + itemstack[0].getCount();
                return result <= getInventoryStackLimit() && result <= stack.getMaxStackSize();
            } else {
                if(stack.isEmpty() && stack1.isEmpty()) return true;
                if(!stack.isEmpty() && !stack.isItemEqual(itemstack[0])) return false;
                if(!stack1.isEmpty() && !stack1.isItemEqual(itemstack[1])) return false;

                int result = stack.getCount() + itemstack[0].getCount();
                int result2 = stack1.getCount() + itemstack[1].getCount();
                return result <= getInventoryStackLimit() && result2 <= getInventoryStackLimit() && result <= stack.getMaxStackSize() && result2 <= stack1.getMaxStackSize();
            }
        }
    }

    public void purifyItem() {
        if (canRefactor()) {
            ItemStack[] itemstack = PurifierRecipes.instance().getPurifyingResult(purifyItemStacks.get(0));

            if (purifyItemStacks.get(2).isEmpty())
                purifyItemStacks.set(2, itemstack[0].copy());
            else if (purifyItemStacks.get(2).getItem() == itemstack[0].getItem())
                purifyItemStacks.get(2).grow(itemstack[0].getCount());
            if(!itemstack[1].isEmpty())
                if (purifyItemStacks.get(3).isEmpty())
                    purifyItemStacks.set(3, itemstack[1].copy());
                else if (purifyItemStacks.get(3).getItem() == itemstack[1].getItem())
                    purifyItemStacks.get(3).grow(itemstack[1].getCount());

            purifyItemStacks.get(0).shrink(1);

            if (purifyItemStacks.get(0).getCount() <= 0)
                purifyItemStacks.set(0, ItemStack.EMPTY);
        }
    }

    public static int getPurifyingTime(ItemStack stack) {
        if (stack.isEmpty())
            return 0;
        else {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            //TODO: Fuel Sources
            if(item == Items.GOLD_NUGGET)
                return 20;
            if(item == Items.GOLD_INGOT)
                return 200;
            if(item == Items.GOLDEN_AXE ||
                    item == Items.GOLDEN_HOE ||
                    item == Items.GOLDEN_PICKAXE ||
                    item == Items.GOLDEN_SHOVEL ||
                    item == Items.GOLDEN_SWORD)
                return 150;
            if(item == Items.GOLDEN_HELMET ||
                    item == Items.GOLDEN_CHESTPLATE ||
                    item == Items.GOLDEN_LEGGINGS ||
                    item == Items.GOLDEN_BOOTS)
                return 500;
            if(item == Items.GOLDEN_HORSE_ARMOR)
                return 1000;
            if(item == Item.getItemFromBlock(Blocks.GOLD_BLOCK))
                return 2000;
            if(item == Item.getItemFromBlock(Blocks.GOLD_ORE))
                return 150;
            if(item == GDItems.tektite)
                return 500;
            if(item == Item.getItemFromBlock(GDBlocks.tektite_block))
                return 5000;
            if(item == Item.getItemFromBlock(GDBlocks.frail_glitter_block))
                return 100;
            if(item == Item.getItemFromBlock(GDBlocks.thick_glitter_block))
                return 3000;
            if(item == Items.BLAZE_POWDER)
                return 1200;
            if(item == Items.BLAZE_ROD)
                return 2400;
            return 0;
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getPurifyingTime(stack) > 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) != this ? false : player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack stack) {
        return par1 == 2 ? false : par1 == 1 ? isItemFuel(stack) : true;
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

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
                return false;
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
        return hasCustomName() ? new TextComponentString(getName()) : new TextComponentTranslation(getName(), new Object[0]);
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
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
        for (ItemStack itemstack : purifyItemStacks)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }
}
