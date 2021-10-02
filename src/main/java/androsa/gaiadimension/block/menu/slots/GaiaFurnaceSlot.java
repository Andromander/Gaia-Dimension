package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GaiaFurnaceSlot extends Slot {

    //Since Furnace Slot requires me to use AbstractFurnaceContainer and that adds the dumb book, this is an edited FurnaceFuelSlot
    public GaiaFurnaceSlot(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return GaiaStoneFurnaceMenu.isFuel(stack) || isBucket(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }
}
