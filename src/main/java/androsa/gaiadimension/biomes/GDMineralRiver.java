package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralRiver extends GDBiomeBase {

    public GDMineralRiver(BiomeProperties props) {
        super(props);
        spawnableCreatureList.clear();

        topBlock = GDBlocks.salt.getDefaultState();
        fillerBlock = GDBlocks.salt.getDefaultState();

        getGDBiomeDecorator().treesPerChunk = -1;
        getGDBiomeDecorator().grassPerChunk = -1;
    }
}
