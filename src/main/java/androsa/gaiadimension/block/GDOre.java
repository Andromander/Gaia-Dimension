package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class GDOre extends Block implements ModelRegisterCallback {

    private final Supplier<Item> itemSupplier;

    public GDOre(MapColor color, @Nullable Supplier<Item> item) {
        super(Material.ROCK, color);

        setSoundType(SoundType.STONE);
        setCreativeTab(GDTabs.tabBlock);

        itemSupplier = item;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int j) {
        if (itemSupplier != null) {
            return itemSupplier.get();
        } else {
            return Item.getItemFromBlock(this);
        }
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return super.quantityDropped(state, fortune, random);
    }
}
