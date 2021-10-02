package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.block.blockentity.PurifierBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NullSlot extends Slot {

    public NullSlot(Container iInventoryIn, int index, int xPosition, int yPosition) {
        super(iInventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return PurifierBlockEntity.getThirdFuelBurnTime().getOrDefault(stack.getItem(), 0) > 0;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }
}
