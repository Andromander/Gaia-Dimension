package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class MalachiteGuardSwordItem extends SwordItem {

    public MalachiteGuardSwordItem() {
        super(GaiaToolMaterials.MALACHITE, 3, -3.0F, new Properties().rarity(Rarity.RARE).group(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getTranslationKey() + ".tooltip"));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean hit = super.hitEntity(stack, target, attacker);

        if (hit) {
            target.applyKnockback(1.0F,
                    (double) MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F)),
                    (double)(-MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F))));
        }

        return hit;
    }
}
