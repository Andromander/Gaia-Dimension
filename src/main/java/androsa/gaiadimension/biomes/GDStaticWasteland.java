package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDStaticWasteland extends GDBiomeBase {

    private short[] skyColorRGB = new short[]{40, 47, 82};

    public GDStaticWasteland(BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.wastelandStone.getDefaultState();
        this.fillerBlock = GDBlocks.wastelandStone.getDefaultState();

        this.decorator.treesPerChunk = -1;
        this.decorator.grassPerChunk = -1;

        getGDBiomeDecorator().staticPerChunk = 2;

        //TODO: Have random junk generate on the ground
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x2B4D96;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x2B4D96;
    }
}
