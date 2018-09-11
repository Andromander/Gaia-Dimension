package androsa.gaiadimension.block.blocksore;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;
import java.util.function.Supplier;

public class GDOreOpal extends Block implements ModelRegisterCallback {

    private final Supplier<Item> itemSupplier;

    public GDOreOpal(Supplier<Item> item) {
        super(Material.ROCK);

        this.setHardness(2.0F);
        this.setResistance(15.0F);
        this.setCreativeTab(GDTabs.tabBlock);

        itemSupplier = item;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j) {
        return itemSupplier.get();
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return super.quantityDropped(state, fortune, random);
    }
}
