package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GeodeSliceItem extends BasicGaiaItem {

    public GeodeSliceItem(Food food) {
        super(food);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity)entityLiving;
        ItemStack item = new ItemStack(ModItems.agate_fabric.get());

        super.finishUsingItem(stack, worldIn, entityLiving);

        if (!player.inventory.add(item.copy())) {
            player.drop(item, false);
        }

        return stack;
    }
}
