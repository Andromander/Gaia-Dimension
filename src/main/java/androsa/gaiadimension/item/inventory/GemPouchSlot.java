package androsa.gaiadimension.item.inventory;

import androsa.gaiadimension.registry.ModBlocks;
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

        return (item == ModBlocks.sugilite ||
        item == ModBlocks.hematite ||
        item == ModBlocks.pyrite ||
        item == ModBlocks.labradorite ||
        item == ModBlocks.moonstone ||
        item == ModBlocks.cinnabar ||
        item == ModBlocks.red_opal ||
        item == ModBlocks.blue_opal ||
        item == ModBlocks.green_opal ||
        item == ModBlocks.white_opal ||
        item == ModBlocks.ixiolite ||
        item == ModBlocks.proustite ||
        item == ModBlocks.euclase ||
        item == ModBlocks.leucite ||
        item == ModBlocks.carnelian ||
        item == ModBlocks.benitoite ||
        item == ModBlocks.diopside ||
        item == ModBlocks.chalcedony ||
        item == ModBlocks.tektite ||
        item == ModBlocks.goldstone ||
        item == ModBlocks.aura_cluster ||
        item == ModBlocks.bismuth_crystal);
    }
}
