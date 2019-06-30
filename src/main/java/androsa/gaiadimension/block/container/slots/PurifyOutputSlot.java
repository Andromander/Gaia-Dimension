package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.tileentity.PurifierTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class PurifyOutputSlot extends Slot {

    private final PlayerEntity thePlayer;
    private int stackSize;

    public PurifyOutputSlot(PlayerEntity player, IInventory inv, int index, int positionX, int positionY) {
        super(inv, index, positionX, positionY);
        thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int par1) {
        if (getHasStack())
            stackSize += Math.min(par1, getStack().getCount());

        return super.decrStackSize(par1);
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        this.onCrafting(stack);
        super.onTake(player, stack);
        return stack;
    }

    @Override
    protected void onCrafting(ItemStack par1ItemStack, int par2) {
        stackSize += par2;
        this.onCrafting(par1ItemStack);
    }

    @Override
    protected void onCrafting(ItemStack par1ItemStack) {
        par1ItemStack.onCrafting(thePlayer.world, thePlayer, stackSize);

        if (!thePlayer.world.isRemote) {
            ((PurifierTileEntity)this.inventory).unlockRecipe(thePlayer);
        }

        stackSize = 0;

        MinecraftForge.EVENT_BUS.post(new GDEvents.ItemPurifiedEvent(thePlayer, par1ItemStack));
    }
}
