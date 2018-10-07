package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralRiver extends GDBiomeBase {

    public GDMineralRiver(BiomeProperties props) {
        super(props);

        spawnableWaterCreatureList.clear();
        spawnableCreatureList.clear();

        //TODO: Whenever required, add a Salty Angler

        topBlock = GDBlocks.salt.getDefaultState();
        fillerBlock = GDBlocks.salt.getDefaultState();

        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
    }
}
