package androsa.gaiadimension.compat.jei.restructurer;

import com.google.common.base.Preconditions;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GlitterFuelRecipe {
    private final List<ItemStack> inputs;
    private final int burnTime;

    public GlitterFuelRecipe(Collection<ItemStack> input, int burnTime) {
        Preconditions.checkArgument(burnTime > 0, "burn time must be greater than 0");
        this.inputs = new ArrayList<>(input);
        this.burnTime = burnTime;
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public List<ItemStack> getInputs() {
        return this.inputs;
    }
}
