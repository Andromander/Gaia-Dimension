package androsa.gaiadimension.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class GlitterFuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {
        if(fuel.getItem() == Items.GOLD_NUGGET)
            return 20;
        if(fuel.getItem() == Items.GOLD_INGOT)
            return 200;
        if(fuel.getItem() == Items.GOLDEN_AXE ||
                fuel.getItem() == Items.GOLDEN_HOE ||
                fuel.getItem() == Items.GOLDEN_PICKAXE ||
                fuel.getItem() == Items.GOLDEN_SHOVEL ||
                fuel.getItem() == Items.GOLDEN_SWORD)
            return 150;
        if(fuel.getItem() == Items.GOLDEN_HELMET ||
                fuel.getItem() == Items.GOLDEN_CHESTPLATE ||
                fuel.getItem() == Items.GOLDEN_LEGGINGS ||
                fuel.getItem() == Items.GOLDEN_BOOTS)
            return 500;
        if(fuel.getItem() == Items.GOLDEN_HORSE_ARMOR)
            return 1000;
        if(fuel.getItem() == Item.getItemFromBlock(Blocks.GOLD_BLOCK))
            return 2000;
        if(fuel.getItem() == Item.getItemFromBlock(Blocks.GOLD_ORE))
            return 150;
        return 0;
    }
}
