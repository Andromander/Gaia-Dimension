package androsa.gaiadimension.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDMalachitePulseBricks extends GDBlock {

    public GDMalachitePulseBricks() {
        super(Material.ROCK, MapColor.GREEN);
        setHardness(20.0F);
        setResistance(200.0F);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
