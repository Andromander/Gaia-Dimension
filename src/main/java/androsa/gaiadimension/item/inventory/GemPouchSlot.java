package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GemPouchSlot extends Slot {

    public GemPouchSlot(IInventory inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        Item item = stack.getItem();

        return (item == ModItems.sugilite ||
        item == ModItems.hematite ||
        item == ModItems.pyrite ||
        item == ModItems.labradorite ||
        item == ModItems.moonstone ||
        item == ModItems.cinnabar ||
        item == ModItems.red_opal ||
        item == ModItems.blue_opal ||
        item == ModItems.green_opal ||
        item == ModItems.white_opal ||
        item == ModItems.ixiolite ||
        item == ModItems.proustite ||
        item == ModItems.euclase ||
        item == ModItems.leucite ||
        item == ModItems.carnelian ||
        item == ModItems.benitoite ||
        item == ModItems.diopside ||
        item == ModItems.chalcedony ||
        item == ModItems.tektite ||
        item == ModItems.goldstone ||
        item == ModItems.aura_cluster ||
        item == ModItems.bismuth_crystal);
    }
}
