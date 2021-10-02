package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ShineSlot extends Slot {

    public ShineSlot(Container container, int index, int xPosition, int yPosition) {
        super(container, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
    return RestructurerBlockEntity.getSecondFuelBurnTime().getOrDefault(stack.getItem(), 0) > 0;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}
