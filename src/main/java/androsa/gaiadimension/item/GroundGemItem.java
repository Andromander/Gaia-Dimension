package androsa.gaiadimension.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class GroundGemItem extends BasicGaiaItem {

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(super.getName(stack).getString(), TextFormatting.GRAY);
    }
}
