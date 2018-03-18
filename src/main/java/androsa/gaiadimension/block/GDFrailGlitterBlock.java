package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDFrailGlitterBlock extends BlockGlass implements ModelRegisterCallback {

    public GDFrailGlitterBlock() {
        super(Material.GLASS, false);

        this.setSoundType(SoundType.GLASS);
        this.setHardness(1.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

}
