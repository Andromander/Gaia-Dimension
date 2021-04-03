package androsa.gaiadimension.item;

import androsa.gaiadimension.item.inventory.GemPouchInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GemstonePouchItem extends BasicGaiaItem {

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);

        if(!worldIn.isClientSide()) {
            playerIn.openMenu(new GemPouchInventory(stack));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
