package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class GeodeJuiceItem extends BasicGaiaItem {

    public GeodeJuiceItem(Food food) {
        super(food);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity)entityLiving;
        ItemStack cup = new ItemStack(ModItems.agate_cup.get());

        super.finishUsingItem(stack, worldIn, entityLiving);

        if (!player.inventory.add(cup.copy())) {
            player.drop(cup, false);
        }

        return stack;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }
}
