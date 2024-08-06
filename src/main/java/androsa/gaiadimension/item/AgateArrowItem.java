package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class AgateArrowItem extends ArrowItem {

    public AgateArrowItem(Properties props) {
        super(props);
    }

    @Override
    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity entity, ItemStack weapon) {
        return new AgateArrowEntity(worldIn, entity, stack.copyWithCount(1));
    }
}
