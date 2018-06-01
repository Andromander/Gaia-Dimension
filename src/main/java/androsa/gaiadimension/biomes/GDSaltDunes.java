package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDSaltDunes extends GDBiomeBase {

    private short[] skyColorRGB = new short[] { 230, 193, 110 };

    public GDSaltDunes(BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.saltBlock.getDefaultState();
        fillerBlock = GDBlocks.saltBlock.getDefaultState();

        this.decorator.treesPerChunk = -1;
        this.decorator.grassPerChunk = -1;
        getGDBiomeDecorator().lakesPerChunk = -1;
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }
}
