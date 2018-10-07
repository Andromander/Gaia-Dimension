package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralReservoir extends GDBiomeBase {

    public GDMineralReservoir(BiomeProperties props) {
        super(props);

        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();

        //TODO: Whenever required, add a Mineral Arenthis and Salty Angler

        this.topBlock = GDBlocks.salt.getDefaultState();
        this.fillerBlock = GDBlocks.salt.getDefaultState();

        this.biomeDecorator.grassPerChunk = -1;
        this.biomeDecorator.treesPerChunk = -1;
    }
}
