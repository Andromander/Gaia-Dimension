package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AgateArrowItem extends ArrowItem {

    public AgateArrowItem() {
        super(new Properties().tab(GaiaItemGroups.GAIA_TOOLS));
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity entity) {
        return new AgateArrowEntity(worldIn, entity);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        int enchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow);
        return enchant > 0 && this.getClass() == AgateArrowItem.class;
    }
}
