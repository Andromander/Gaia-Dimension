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
        super(GaiaToolMaterials.SPINEL, 3, -2.5F, new Properties().rarity(Rarity.RARE).group(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getTranslationKey() + ".tooltip"));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (player.world.isRemote) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = entity.getX() + random.nextFloat() * entity.getWidth();
                double py = entity.getY() + random.nextFloat() * entity.getHeight();
                double pz = entity.getZ() + random.nextFloat() * entity.getWidth();
                entity.world.addParticle(ParticleTypes.FLAME, px, py, pz, 1, 1, 1);
            }
        }

        entity.setFire(10);

        return false;
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure?
}
