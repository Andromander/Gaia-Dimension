package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class GDPyriteOre extends BlockOre implements ModelRegisterCallback {

    public GDPyriteOre() {
        this.setLightLevel(0.5F);
        this.setHardness(2.0F);
        this.setResistance(15.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j) {
        return GDItems.pyrite;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return super.quantityDropped(state, fortune, random);
    }

}

