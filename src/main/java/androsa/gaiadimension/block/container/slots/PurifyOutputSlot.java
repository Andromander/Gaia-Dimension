package androsa.gaiadimension.block.container.slots;

import androsa.gaiadimension.block.tileentity.PurifierTileEntity;
import androsa.gaiadimension.registry.ModEvents;
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
    public boolean mayPlace(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public ItemStack remove(int par1) {
        if (hasItem())
            stackSize += Math.min(par1, getItem().getCount());

        return super.remove(par1);
    }

    @Override
    public ItemStack onTake(PlayerEntity player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(player, stack);
        return stack;
    }

    @Override
    protected void onQuickCraft(ItemStack par1ItemStack, int par2) {
        stackSize += par2;
        this.checkTakeAchievements(par1ItemStack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack par1ItemStack) {
        par1ItemStack.onCraftedBy(thePlayer.level, thePlayer, stackSize);

        if (!thePlayer.level.isClientSide()) {
            ((PurifierTileEntity)this.container).unlockRecipe(thePlayer);
        }

        stackSize = 0;

        MinecraftForge.EVENT_BUS.post(new ModEvents.ItemPurifiedEvent(thePlayer, par1ItemStack));
    }
}
