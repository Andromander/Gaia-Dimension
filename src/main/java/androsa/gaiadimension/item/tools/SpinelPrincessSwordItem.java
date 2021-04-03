package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaItemGroups;
import androsa.gaiadimension.registry.GaiaToolMaterials;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class SpinelPrincessSwordItem extends SwordItem {

    public SpinelPrincessSwordItem() {
        super(GaiaToolMaterials.SPINEL, 3, -2.5F, new Properties().rarity(Rarity.RARE).tab(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getDescriptionId() + ".tooltip"));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (player.level.isClientSide()) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = entity.getX() + random.nextFloat() * entity.getBbWidth() * 2.0F - entity.getBbWidth();
                double py = entity.getY() + random.nextFloat() * entity.getBbHeight();
                double pz = entity.getZ() + random.nextFloat() * entity.getBbWidth() * 2.0F - entity.getBbWidth();
                entity.level.addParticle(ParticleTypes.FLAME, px, py, pz, 0, 0, 0);
            }
        }

        entity.setSecondsOnFire(10);

        return false;
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure?
}
