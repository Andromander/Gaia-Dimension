package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GaiaChampSwordItem extends SwordItem {

    public GaiaChampSwordItem(Properties props) {
        super(GaiaToolMaterials.GAIA_CHAMP, props.attributes(createAttributes(GaiaToolMaterials.GAIA_CHAMP, 3, -2.4F)));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals extra damage to Corrupt and Non-Gaian mobs
    //TODO: [FUTURE] Can this be used to unlock every strucure?

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.gaia_portal.get().defaultBlockState()), target.getX(), target.getY(), target.getZ(), 1, 1, 1);
        return super.hurtEnemy(stack, target, attacker);
    }
}
