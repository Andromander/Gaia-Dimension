package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralRiver extends GDBiomeBase {

    public GDMineralRiver(BiomeProperties props) {
        super(props);

        spawnableCreatureList.clear();

        topBlock = GDBlocks.salt.getDefaultState();
        fillerBlock = GDBlocks.salt.getDefaultState();

        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
    }
}
