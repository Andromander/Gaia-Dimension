package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class SpinelPrincessSwordItem extends Item {

    public SpinelPrincessSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.SPINEL, 3, -2.5F));
    }

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext world, TooltipDisplay display, Consumer<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, display, tooltips, flags);
        tooltips.accept(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    @Override
    public boolean isCombineRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.level().addParticle(ParticleTypes.FLAME, target.getRandomX(target.getBbWidth()), target.getRandomY(), target.getRandomZ(target.getBbWidth()), 0, 0, 0);
        target.igniteForSeconds(10.0F);
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure?
}
