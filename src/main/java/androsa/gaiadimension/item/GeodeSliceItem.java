package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeodeSliceItem extends Item {

    public GeodeSliceItem(Properties props) {
        super(props);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player player = (Player)entityLiving;
        ItemStack item = new ItemStack(ModItems.agate_fabric.get());

        super.finishUsingItem(stack, worldIn, entityLiving);

        if (!player.getInventory().add(item.copy())) {
            player.drop(item, false);
        }

        return stack;
    }
}
