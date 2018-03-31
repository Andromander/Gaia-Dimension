package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDSaltDunes extends GDBiomeBase {

    public GDSaltDunes(BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.saltBlock.getDefaultState();
        fillerBlock = GDBlocks.saltBlock.getDefaultState();

        this.decorator.treesPerChunk = -1;
        this.decorator.grassPerChunk = -1;
    }
}
