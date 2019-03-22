package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.GDItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotGemPouch extends Slot {

    public SlotGemPouch(IInventory inv, int index, int xPosition, int yPosition) {
        super(inv, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        Item item = stack.getItem();

        return (item == GDItems.sugilite ||
        item == GDItems.hematite ||
        item == GDItems.pyrite ||
        item == GDItems.labradorite ||
        item == GDItems.moonstone ||
        item == GDItems.cinnabar ||
        item == GDItems.red_opal ||
        item == GDItems.blue_opal ||
        item == GDItems.green_opal ||
        item == GDItems.white_opal ||
        item == GDItems.ixiolite ||
        item == GDItems.proustite ||
        item == GDItems.euclase ||
        item == GDItems.leucite ||
        item == GDItems.carnelian ||
        item == GDItems.benitoite ||
        item == GDItems.diopside ||
        item == GDItems.chalcedony ||
        item == GDItems.tektite ||
        item == GDItems.goldstone ||
        item == GDItems.aura_cluster ||
        item == GDItems.bismuth_crystal);
    }
}
