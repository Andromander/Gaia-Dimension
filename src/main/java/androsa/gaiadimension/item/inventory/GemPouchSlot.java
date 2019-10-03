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

    //TODO: Make a tag for this?
    @Override
    public boolean isItemValid(ItemStack stack) {
        Item item = stack.getItem();

        return (item == ModItems.sugilite.get() ||
        item == ModItems.hematite.get() ||
        item == ModItems.pyrite.get() ||
        item == ModItems.labradorite.get() ||
        item == ModItems.moonstone.get() ||
        item == ModItems.cinnabar.get() ||
        item == ModItems.red_opal.get() ||
        item == ModItems.blue_opal.get() ||
        item == ModItems.green_opal.get() ||
        item == ModItems.white_opal.get() ||
        item == ModItems.ixiolite.get() ||
        item == ModItems.proustite.get() ||
        item == ModItems.euclase.get() ||
        item == ModItems.leucite.get() ||
        item == ModItems.carnelian.get() ||
        item == ModItems.benitoite.get() ||
        item == ModItems.diopside.get() ||
        item == ModItems.chalcedony.get() ||
        item == ModItems.tektite.get() ||
        item == ModItems.goldstone.get() ||
        item == ModItems.aura_cluster.get() ||
        item == ModItems.bismuth_crystal.get());
    }
}
