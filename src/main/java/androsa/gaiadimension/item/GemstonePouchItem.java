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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(!worldIn.isRemote) {
            playerIn.openContainer(new GemPouchInventory(stack));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }
}
