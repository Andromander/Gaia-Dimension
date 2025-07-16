package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class GaiaChampSwordItem extends Item {

    public GaiaChampSwordItem(Properties props) {
        super(props.sword(GaiaToolMaterials.GAIA_CHAMP, 3, -2.4F));
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

    //TODO: Deals extra damage to Corrupt and Non-Gaian mobs
    //TODO: [FUTURE] Can this be used to unlock every strucure?

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.gaia_portal.get().defaultBlockState()), target.getX(), target.getY(), target.getZ(), 1, 1, 1);
    }
}
