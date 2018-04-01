package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralReservoir extends GDBiomeBase {

    public GDMineralReservoir(BiomeProperties props) {
        super(props);

        this.spawnableWaterCreatureList.clear();

        this.topBlock = GDBlocks.saltBlock.getDefaultState();
        this.fillerBlock = GDBlocks.heavySoil.getDefaultState();

        this.decorator.grassPerChunk = -1;
        this.decorator.treesPerChunk = -1;
    }
}
