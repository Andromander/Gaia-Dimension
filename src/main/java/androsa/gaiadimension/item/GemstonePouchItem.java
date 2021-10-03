package androsa.gaiadimension.item;

import androsa.gaiadimension.item.inventory.GemPouchInventory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GemstonePouchItem extends Item {

    public GemstonePouchItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if(!worldIn.isClientSide()) {
            playerIn.openMenu(new GemPouchInventory(stack));
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }
}
