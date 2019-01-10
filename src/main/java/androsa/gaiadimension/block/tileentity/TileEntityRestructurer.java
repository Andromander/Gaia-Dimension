package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.block.GDRestructurer;
import androsa.gaiadimension.recipe.RestructurerRecipes;
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
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TileEntityRestructurer extends TileEntity implements ISidedInventory, ITickable {

    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 3, 1, 2, 4 };
    private static final int[] slotsSides = new int [] { 1, 2 };
    /**
     * 0 = Input
     * 1 = Gold Slot
     * 2 = Pink Essence Slot
     * 3 = Output Slot
     * 4 = Byproduct Slot
     */
    private NonNullList<ItemStack> restructurerItemStacks = NonNullList.withSize(5, ItemStack.EMPTY);
    /** The number of ticks that the furnace will keep burning */
    public int restructuringTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentRestructuringTime;
    /** The number of ticks that an item requires to burn */
    public int changeTime;
    /** The number of ticks that an item has left to burn */
    public int totalChangeTime;
    private String name;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.restructurerItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.restructurerItemStacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.restructurerItemStacks.get(index);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.restructurerItemStacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.restructurerItemStacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.restructurerItemStacks.get(index);
        boolean burning = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.restructurerItemStacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !burning) {
            this.totalChangeTime = 200;
            this.changeTime = 0;
            this.markDirty();
        }
    }

    /**
     * Gets the name of this thing.
     */
    @Override
    public String getName() {
        return this.hasCustomName() ? this.name : "container.gaiadimension.restructurer";
    }

    /**
     * Checks if this thing has a custom name.
     */
    @Override
    public boolean hasCustomName() {
        return this.name != null && !this.name.isEmpty();
    }

    public void setCustomInventoryName(String name) {
        this.name = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.restructurerItemStacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.restructurerItemStacks);
        this.restructuringTime = compound.getInteger("BurnTime");
        this.changeTime = compound.getInteger("CookTime");
        this.totalChangeTime = compound.getInteger("CookTimeTotal");
        this.currentRestructuringTime = getItemBurnTime(this.restructurerItemStacks.get(1), this.restructurerItemStacks.get(2));

        if (compound.hasKey("CustomName", 8)) {
            this.name = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.restructuringTime);
        compound.setInteger("CookTime", (short)this.changeTime);
        compound.setInteger("CookTimeTotal", (short)this.totalChangeTime);
        ItemStackHelper.saveAllItems(compound, this.restructurerItemStacks);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.name);
        }

        return compound;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getChangeProgressScaled(int par1) {
        return changeTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getChangeTimeRemainingScaled(int par1) {
        if (currentRestructuringTime == 0)
            currentRestructuringTime = 200;

        return restructuringTime * par1 / currentRestructuringTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.restructuringTime > 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {
        boolean burning = this.isBurning();
        boolean burn = false;

        if (this.isBurning()) {
            --this.restructuringTime;
        }

        if (!this.world.isRemote) {
            ItemStack goldStack = this.restructurerItemStacks.get(1);
            ItemStack essenceStack = this.restructurerItemStacks.get(2);

            if (this.isBurning() || !goldStack.isEmpty() && !essenceStack.isEmpty() && !this.restructurerItemStacks.get(0).isEmpty()) {
                if (!this.isBurning() && this.canChange()) {
                    this.restructuringTime = getItemBurnTime(goldStack, essenceStack);
                    this.currentRestructuringTime = this.restructuringTime;

                    if (this.isBurning()) {
                        burn = true;

                        if (!goldStack.isEmpty()) {
                            Item item = goldStack.getItem();
                            goldStack.shrink(1);

                            if (goldStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(goldStack);
                                this.restructurerItemStacks.set(1, item1);
                            }
                        }

                        if (!essenceStack.isEmpty()) {
                            Item item = essenceStack.getItem();
                            essenceStack.shrink(1);

                            if (essenceStack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(essenceStack);
                                this.restructurerItemStacks.set(2, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canChange()) {
                    ++this.changeTime;

                    if (this.changeTime == this.totalChangeTime) {
                        this.changeTime = 0;
                        this.totalChangeTime = 200;
                        this.changeItem();
                        burn = true;
                    }
                } else {
                    this.changeTime = 0;
                }
            } else if (!this.isBurning() && this.changeTime > 0) {
                this.changeTime = MathHelper.clamp(this.changeTime - 2, 0, this.totalChangeTime);
            }

            if (burning != this.isBurning()) {
                burn = true;
                GDRestructurer.updateGlitterBlockState(this.isBurning(), this.world, this.pos);
            }
        }

        if (burn) {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canChange() {
        if (this.restructurerItemStacks.get(0).isEmpty()) {
            return false;
        } else {
            ItemStack[] itemstack = RestructurerRecipes.instance().getRefactoringResult(this.restructurerItemStacks.get(0));
            ItemStack output = restructurerItemStacks.get(3), byproduct = restructurerItemStacks.get(4);

            if(itemstack[0].isEmpty() && itemstack[1].isEmpty() || itemstack[0].isEmpty()) return false;
            if(output.isEmpty() && byproduct.isEmpty()) return true;
            if(itemstack[1].isEmpty()){
                if(output.isEmpty()) return true;
                if(!output.isItemEqual(itemstack[0])) return false;

                int result = output.getCount() + itemstack[0].getCount();
                return result <= getInventoryStackLimit() && result <= output.getMaxStackSize();
            } else {
                if(output.isEmpty() && byproduct.isEmpty()) return true;
                if(!output.isEmpty() && !output.isItemEqual(itemstack[0])) return false;
                if(!byproduct.isEmpty() && !byproduct.isItemEqual(itemstack[1])) return false;

                int result = output.getCount() + itemstack[0].getCount();
                int result2 = byproduct.getCount() + itemstack[1].getCount();
                return result <= getInventoryStackLimit() && result2 <= getInventoryStackLimit() && result <= output.getMaxStackSize() && result2 <= byproduct.getMaxStackSize();
            }
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void changeItem() {
        if (canChange()) {
            ItemStack[] itemstack = RestructurerRecipes.instance().getRefactoringResult(restructurerItemStacks.get(0));

            if (restructurerItemStacks.get(3).isEmpty())
                restructurerItemStacks.set(3, itemstack[0].copy());
            else if (restructurerItemStacks.get(3).getItem() == itemstack[0].getItem())
                restructurerItemStacks.get(3).grow(itemstack[0].getCount());
            if(!itemstack[1].isEmpty())
                if (restructurerItemStacks.get(4).isEmpty())
                    restructurerItemStacks.set(4, itemstack[1].copy());
                else if (restructurerItemStacks.get(4).getItem() == itemstack[1].getItem())
                    restructurerItemStacks.get(4).grow(itemstack[1].getCount());

            restructurerItemStacks.get(0).shrink(1);

            if (restructurerItemStacks.get(0).getCount() <= 0)
                restructurerItemStacks.set(0, ItemStack.EMPTY);
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack1, ItemStack stack2) {
        return (getFuelBurnTime(stack1) + getSecondFuelBurnTime(stack2)) / 2;
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getFuelBurnTime(stack) > 0 || getSecondFuelBurnTime(stack) > 0;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index != 2 && (index != 1 || isItemFuel(stack));
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return slotsBottom;
        } else {
            return side == EnumFacing.UP ? slotsTop : slotsSides;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     */
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     */
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1 || index == 2) {
            Item item = stack.getItem();

            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }

        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.restructurerItemStacks.clear();
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

    /** Burn Times for the first slot */
    public static int getFuelBurnTime(ItemStack stack) {
        if (stack.isEmpty())
            return 0;
        else {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

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
            if(item == GDItems.pyrite)
                return 500;
            if(item == Item.getItemFromBlock(GDBlocks.pyrite_block))
                return 5000;
            if(item == GDItems.sweet_muckball)
                return 250;
            if(item == Item.getItemFromBlock(GDBlocks.frail_glitter_block))
                return 1000;
            if(item == Item.getItemFromBlock(GDBlocks.thick_glitter_block))
                return 2000;
            if(item == Item.getItemFromBlock(GDBlocks.gummy_glitter_block))
                return 4000;
            if(item == Items.BLAZE_POWDER)
                return 1200;
            if(item == Items.BLAZE_ROD)
                return 2400;
            return 0;
        }
    }

    /** Burn times for the second slot */
    public static int getSecondFuelBurnTime(ItemStack stack) {
        if (stack.isEmpty())
            return 0;
        else {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            /*
            if(item == GDItems.pink_essence)
                return 100;
            if(item == GDItems.pink_goo)
                return 900;
            if(item == GDItems.pink_sludge)
                return 8100;
            if(item == GDItems.aura_shard)
                return 200;
            if(item == GDItems.aura_geode)
                return 1800;
            if(item == Item.getItemFromBlock(GDBlocks.aura_block))
                return 16200;
                */
            if(item == GDItems.white_opal)
                return 200;
            return 0;
        }
    }
}
