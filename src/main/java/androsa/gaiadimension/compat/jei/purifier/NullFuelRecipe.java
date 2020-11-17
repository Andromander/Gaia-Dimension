package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.base.Preconditions;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NullFuelRecipe {
    private final List<ItemStack> inputs;
    private final String smeltCountString;
    private final IDrawableAnimated flame;
    private final ResourceLocation firetex = new ResourceLocation(GaiaDimensionMod.MODID, "textures/gui/jei/nulling.png");

    public NullFuelRecipe(IGuiHelper guiHelper, Collection<ItemStack> input, int burnTime) {
        Preconditions.checkArgument(burnTime > 0, "burn time must be greater than 0");
        this.inputs = new ArrayList<>(input);

        if (burnTime == 200) {
            this.smeltCountString = I18n.format("gui.gaiadimension.category.fuel.single_average");
        } else {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String smeltCount = numberInstance.format(burnTime / 200f);
            this.smeltCountString = I18n.format("gui.gaiadimension.category.fuel.smelt_average", smeltCount);
        }

        this.flame = guiHelper.drawableBuilder(firetex, 0, 0, 14, 14)
                .setTextureSize(14, 14)
                .buildAnimated(burnTime, IDrawableAnimated.StartDirection.TOP, true);
    }

    public List<ItemStack> getInputs() {
        return this.inputs;
    }

    public String getNullCountString() {
        return this.smeltCountString;
    }

    public IDrawableAnimated getFlame() {
        return this.flame;
    }
}
