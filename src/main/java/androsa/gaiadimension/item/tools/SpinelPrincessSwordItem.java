package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModItemGroups;
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

    public SpinelPrincessSwordItem(IItemTier material) {
        super(material, 3, -2.5F, new Properties().maxStackSize(1).defaultMaxDamage(material.getMaxUses()).rarity(Rarity.RARE).group(ModItemGroups.GAIA_TOOLS));
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
                double px = entity.posX + random.nextFloat() * entity.getWidth();
                double py = entity.posY + random.nextFloat() * entity.getHeight();
                double pz = entity.posZ + random.nextFloat() * entity.getWidth();
                entity.world.addParticle(ParticleTypes.FLAME, px, py, pz, 1, 1, 1);
            }
        }

        entity.setFire(10);

        return false;
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure?
}
