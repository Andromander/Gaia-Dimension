package androsa.gaiadimension.block.menu.slots;

import androsa.gaiadimension.block.blockentity.PurifierBlockEntity;
import androsa.gaiadimension.registry.helpers.EventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;

public class PurifyOutputSlot extends Slot {

    private final Player thePlayer;
    private int stackSize;

    public PurifyOutputSlot(Player player, Container inv, int index, int positionX, int positionY) {
        super(inv, index, positionX, positionY);
        thePlayer = player;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack remove(int par1) {
        if (hasItem())
            stackSize += Math.min(par1, getItem().getCount());

        return super.remove(par1);
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(player, stack);
    }

    @Override
    protected void onQuickCraft(ItemStack par1ItemStack, int par2) {
        stackSize += par2;
        this.checkTakeAchievements(par1ItemStack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack par1ItemStack) {
        par1ItemStack.onCraftedBy(thePlayer.level(), thePlayer, stackSize);

        if (thePlayer instanceof ServerPlayer serverPlayer) {
            ((PurifierBlockEntity)this.container).awardRecipe(serverPlayer);
        }

        stackSize = 0;

        NeoForge.EVENT_BUS.post(new EventHandler.ItemPurifiedEvent(thePlayer, par1ItemStack));
    }
}
