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
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity)entityLiving;
        ItemStack cup = new ItemStack(ModItems.agate_cup);

        super.onItemUseFinish(stack, worldIn, entityLiving);

        if (!player.inventory.addItemStackToInventory(cup.copy())) {
            player.dropItem(cup, false);
        }

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
