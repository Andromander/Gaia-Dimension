package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.GDGlitterFurnace;
import androsa.gaiadimension.recipe.GlitterFurnaceRecipes;
import androsa.gaiadimension.registry.GDBlocks;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
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

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityGlitterFurnace extends TileEntity implements ISidedInventory, ITickable {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 2, 1, 3 };
    private static final int[] slotsSides = new int [] { 1 };
    private NonNullList<ItemStack> glitterItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public int glitterTimer;
    public int currentItemRefactorTime; //Good enough of a name
    public int glitterFormTime;
    private String name;

    @Override
    public int getSizeInventory() {
        return glitterItemStacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return glitterItemStacks.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(glitterItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(glitterItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        glitterItemStacks.set(index, stack);

        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public String getName() {
        return hasCustomName() ? name : "container.gaiadimension.glitter_furnace";
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
        glitterItemStacks = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(par1, glitterItemStacks);
        glitterTimer = par1.getShort("GlitterTimer");
        glitterFormTime = par1.getShort("FormTime");
        currentItemRefactorTime = getRefactoringTime(glitterItemStacks.get(1));

        if (par1.hasKey("CustomName", 8))
            name = par1.getString("CustomName");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setShort("GlitterTimer", (short)glitterTimer);
        par1.setShort("FormTime", (short)glitterFormTime);
        ItemStackHelper.saveAllItems(par1, glitterItemStacks);

        if (hasCustomName())
            par1.setString("CustomName", name);

        return par1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getGlitterProgressScaled(int par1) {
        return glitterFormTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getGlitterTimeRemainingScaled(int par1) {
        if (currentItemRefactorTime == 0)
            currentItemRefactorTime = 200;

        return glitterTimer * par1 / currentItemRefactorTime;
    }

    public boolean isGlittering() {
        return glitterTimer > 0;
    }

    @Override
    public void update() {
        boolean flag = glitterTimer > 0;
        boolean flag1 = false;

        if (glitterTimer > 0)
            --glitterTimer;

        if (!world.isRemote) {
            if (glitterTimer == 0 && canRefactor()) {
                currentItemRefactorTime = glitterTimer = getRefactoringTime(glitterItemStacks.get(1));

                if (glitterTimer > 0) {
                    flag1 = true;

                    if (!glitterItemStacks.get(1).isEmpty()) {
                        Item item = glitterItemStacks.get(1).getItem();
                        glitterItemStacks.get(1).shrink(1);

                        if (glitterItemStacks.get(1).isEmpty())
                            glitterItemStacks.set(1, item.getContainerItem(glitterItemStacks.get(1)));
                    }
                }
            }

            if (isGlittering() && canRefactor()) {
                ++glitterFormTime;

                if (glitterFormTime == 200) {
                    glitterFormTime = 0;
                    refactorItem();
                    flag1 = true;
                }
            } else
                glitterFormTime = 0;

            if (flag != glitterTimer > 0) {
                flag1 = true;
                GDGlitterFurnace.updateGlitterBlockState(glitterTimer > 0, world, pos);
            }
        }

        if (flag1)
            markDirty();
    }

    private boolean canRefactor() {
        if (glitterItemStacks.get(0).isEmpty() || GlitterFurnaceRecipes.instance().getRefactoringResult(glitterItemStacks.get(0)) == null)
            return false;
        else {
            ItemStack[] itemstack = GlitterFurnaceRecipes.instance().getRefactoringResult(glitterItemStacks.get(0));

            ItemStack stack = glitterItemStacks.get(2), stack1 = glitterItemStacks.get(3);

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

    public void refactorItem() {
        if (canRefactor()) {
            ItemStack[] itemstack = GlitterFurnaceRecipes.instance().getRefactoringResult(glitterItemStacks.get(0));

            if (glitterItemStacks.get(2).isEmpty())
                glitterItemStacks.set(2, itemstack[0].copy());
            else if (glitterItemStacks.get(2).getItem() == itemstack[0].getItem())
                glitterItemStacks.get(2).grow(itemstack[0].getCount());
            if(!itemstack[1].isEmpty())
                if (glitterItemStacks.get(3).isEmpty())
                    glitterItemStacks.set(3, itemstack[1].copy());
                else if (glitterItemStacks.get(3).getItem() == itemstack[1].getItem())
                    glitterItemStacks.get(3).grow(itemstack[1].getCount());

            glitterItemStacks.get(0).shrink(1);

            if (glitterItemStacks.get(0).getCount() <= 0)
                glitterItemStacks.set(0, ItemStack.EMPTY);
        }
    }

    public static int getRefactoringTime(ItemStack stack) {
        if (stack.isEmpty())
            return 0;
        else {
            Item item = stack.getItem();

            //TODO: Fuel Sources
            if (item == Item.getItemFromBlock(GDBlocks.frailGlitterBlock)) return 100;
            if (item == Item.getItemFromBlock(GDBlocks.thickGlitterBlock)) return 3000;
            if (item == Items.BLAZE_POWDER) return 1200;
            if (item == Items.BLAZE_ROD) return 2400;
            return GaiaDimension.getFuelValue(stack, GaiaDimension.FuelType.GLITTER_FURNACE);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getRefactoringTime(stack) > 0;
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
        for (ItemStack itemstack : glitterItemStacks)
            if (!itemstack.isEmpty())
                return false;

        return true;
    }
}
