package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class GeodeJuiceItem extends Item {

    public GeodeJuiceItem(Properties props) {
        super(props);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player player = (Player)entityLiving;
        ItemStack cup = new ItemStack(ModItems.agate_cup.get());

        super.finishUsingItem(stack, worldIn, entityLiving);

        if (!player.getInventory().add(cup.copy())) {
            player.drop(cup, false);
        }

        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
}
