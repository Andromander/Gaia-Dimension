package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class GDPebblesBlock extends BlockFalling implements ModelRegisterCallback {

    public GDPebblesBlock() {
        super(Material.SAND);

        setHardness(1.3F);
        setHarvestLevel("shovel", -1);
        setSoundType(SoundType.GROUND);
        setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (fortune > 3) {
            fortune = 3;
        }

        return rand.nextInt(10 - fortune * 3) == 0 ? GDItems.sturdy_pebble : super.getItemDropped(state, rand, fortune);
    }
}
