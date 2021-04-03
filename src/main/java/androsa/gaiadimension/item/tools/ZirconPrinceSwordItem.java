package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ZirconPrinceSwordItem extends SwordItem {

    public ZirconPrinceSwordItem() {
        super(GaiaToolMaterials.ZIRCON, 3, -2.3F, new Properties().rarity(Rarity.RARE).tab(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals lightning damage to those hit
    //TODO: [FUTURE] Can be used to unlock Unknown Goldstone Structure?
}
