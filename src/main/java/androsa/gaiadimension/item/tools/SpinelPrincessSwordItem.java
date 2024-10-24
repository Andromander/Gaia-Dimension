package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpinelPrincessSwordItem extends SwordItem {

    public SpinelPrincessSwordItem(Properties props) {
        super(GaiaToolMaterials.SPINEL, props.attributes(createAttributes(GaiaToolMaterials.SPINEL, 3, -2.5F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (super.hurtEnemy(stack, target, attacker)) {
            target.level().addParticle(ParticleTypes.FLAME, target.getRandomX(target.getBbWidth()), target.getRandomY(), target.getRandomZ(target.getBbWidth()), 0, 0, 0);
            target.igniteForSeconds(10.0F);
            return true;
        }
        return false;
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure?
}
