package androsa.gaiadimension.item;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class GDGemstonePouch extends Item implements ModelRegisterCallback {

    public GDGemstonePouch() {
        setCreativeTab(GDTabs.tabItem);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(!worldIn.isRemote) {
            playerIn.openGui(GaiaDimension.instance, CommonProxy.GuiID.GEM_POUCH.ordinal(), worldIn, 0, 0, 0);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
