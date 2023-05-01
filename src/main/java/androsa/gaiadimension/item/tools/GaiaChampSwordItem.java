package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaToolMaterials;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class GaiaChampSwordItem extends SwordItem {

    public GaiaChampSwordItem(Properties props) {
        super(GaiaToolMaterials.GAIA_CHAMP, 3, -2.4F, props);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltips, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(Component.translatable(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals extra damage to Corrupt and Non-Gaian mobs
    //TODO: [FUTURE] Can this be used to unlock every strucure?

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.gaia_portal.get().defaultBlockState()), target.getX(), target.getY(), target.getZ(), 1, 1, 1);
        return super.hurtEnemy(stack, target, attacker);
    }
}
