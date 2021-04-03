package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class GaiaFurnaceSlot extends Slot {

    //Since Furnace Slot requires me to use AbstractFurnaceContainer and that adds the dumb book, this is an edited FurnaceFuelSlot
    public GaiaFurnaceSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return GaiaStoneFurnaceContainer.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }
}
