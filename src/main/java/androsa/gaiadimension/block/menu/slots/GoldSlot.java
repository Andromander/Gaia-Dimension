package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GoldSlot extends Slot {

    public GoldSlot(Container container, int index, int xPosition, int yPosition) {
        super(container, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return RestructurerBlockEntity.getFuelBurnTime().getOrDefault(stack.getItem(), 0) > 0;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}
