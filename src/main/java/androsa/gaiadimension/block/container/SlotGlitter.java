package androsa.gaiadimension.block.container;

import androsa.gaiadimension.recipe.GlitterFurnaceRecipes;
import androsa.gaiadimension.registry.GDEvents;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;

public class SlotGlitter extends Slot {

    private EntityPlayer thePlayer;
    private int stackSize;

    public SlotGlitter(EntityPlayer player, IInventory inv, int hitX, int hitY, int hitZ) {
        super(inv, hitX, hitY, hitZ);
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
    public ItemStack onTake(EntityPlayer player, ItemStack stack) {
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
            int i = stackSize;
            float f = GlitterFurnaceRecipes.instance().getExperience(par1ItemStack);
            int j;

            if (f == 0.0F)
                i = 0;
            else if (f < 1.0F) {
                j = MathHelper.floor(i * f);

                if (j < MathHelper.ceil(i * f) && (float)Math.random() < i * f - j)
                    ++j;

                i = j;
            }

            while (i > 0) {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                thePlayer.world.spawnEntity(new EntityXPOrb(thePlayer.world, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
            }
        }

        stackSize = 0;

        MinecraftForge.EVENT_BUS.post(new GDEvents.ItemGlitteredEvent(thePlayer, par1ItemStack));
    }
}
