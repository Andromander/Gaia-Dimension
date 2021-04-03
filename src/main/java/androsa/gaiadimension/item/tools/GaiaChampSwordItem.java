package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GaiaToolMaterials;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class GaiaChampSwordItem extends SwordItem {

    public GaiaChampSwordItem() {
        super(GaiaToolMaterials.GAIA_CHAMP, 3, -2.4F, new Properties().rarity(Rarity.EPIC).tab(GaiaItemGroups.GAIA_TOOLS));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.appendHoverText(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent(getDescriptionId() + ".tooltip"));
    }

    //TODO: Deals extra damage to Corrupt and Non-Gaian mobs
    //TODO: [FUTURE] Can this be used to unlock every strucure?

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (player.level.isClientSide()) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = entity.getX() + random.nextFloat() * entity.getBbWidth() * 2.0F - entity.getBbWidth();
                double py = entity.getY() + random.nextFloat() * entity.getBbHeight();
                double pz = entity.getZ() + random.nextFloat() * entity.getBbWidth() * 2.0F - entity.getBbWidth();
                entity.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, ModBlocks.gaia_portal.get().defaultBlockState()), px, py, pz, 1, 1, 1);
            }
        }
        return false;
    }
}
