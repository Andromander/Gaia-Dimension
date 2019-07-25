package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModBlocks;
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
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity)entityLiving;
        ItemStack item = new ItemStack(ModBlocks.agate_fabric);

        super.onItemUseFinish(stack, worldIn, entityLiving);

        if (!player.inventory.addItemStackToInventory(item.copy())) {
            player.dropItem(item, false);
        }

        return stack;
    }
}
