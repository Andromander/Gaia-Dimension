package androsa.gaiadimension.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class GDOre extends GDBlock {

    private final int minDrop;
    private final int maxDrop;
    private final Supplier<Item> itemSupplier;

    public GDOre(MapColor color, String toolClass, int harvestLevel) {
        this(color, toolClass, harvestLevel, 0, 0, null);
    }

    public GDOre(MapColor color, String toolClass, int harvestLevel, int minExp, int maxExp, Supplier<Item> item) {
        super(Material.ROCK, color);

        setHarvestLevel(toolClass, harvestLevel);
        minDrop = minExp;
        maxDrop = maxExp;
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
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            return MathHelper.getInt(rand, minDrop, maxDrop);
        }
        return 0;
    }
}
