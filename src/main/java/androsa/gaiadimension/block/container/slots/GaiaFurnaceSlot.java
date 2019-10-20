package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GaiaFurnaceSlot extends Slot {

    //Since Furnace Slot requires me to use AbstractFurnaceContainer and that adds the dumb book, this is an edited FurnaceFuelSlot
    public GaiaFurnaceSlot(IInventory inventory, int p_i50084_3_, int p_i50084_4_, int p_i50084_5_) {
        super(inventory, p_i50084_3_, p_i50084_4_, p_i50084_5_);
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean isItemValid(ItemStack stack) {
        return GaiaStoneFurnaceContainer.isFuel(stack) || isBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }

}
