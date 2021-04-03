package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public class CorruptWarriorSwordItem extends SwordItem {

    public CorruptWarriorSwordItem() {
        super(GaiaToolMaterials.CORRUPT, 3, -3.5F, new Properties().rarity(Rarity.RARE).tab(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @Nonnull
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(super.getName(stack).getString(), TextFormatting.DARK_PURPLE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deal extra damage to Gaian Mobs, but half damage to bosses. May inflict Corrupt Mania
    //TODO: [FUTURE] Can be used to unlock Unknown Stage 1 Final Boss Structure?
}
