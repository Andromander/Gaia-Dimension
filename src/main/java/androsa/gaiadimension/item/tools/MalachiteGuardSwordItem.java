package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class MalachiteGuardSwordItem extends SwordItem {

    public MalachiteGuardSwordItem(IItemTier material) {
        super(material, 3, -3.0F, new Properties().maxStackSize(1).defaultMaxDamage(material.getMaxUses()).rarity(Rarity.RARE).group(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getTranslationKey() + ".tooltip"));
    }

    //TODO: Knocks back hit targets
    //TODO: [FUTURE] Can be used to unlock Predator Dungeons?
}
