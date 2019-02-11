package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDMineralArenthis;
import androsa.gaiadimension.registry.GDBlocks;

public class GDMineralReservoir extends GDBiomeBase {

    public GDMineralReservoir(BiomeProperties props) {
        super(props);

        this.spawnableCreatureList.clear();

        this.spawnableWaterCreatureList.add(new SpawnListEntry(GDMineralArenthis.class, 10, 1, 3));

        this.topBlock = GDBlocks.salt.getDefaultState();
        this.fillerBlock = GDBlocks.salt.getDefaultState();

        this.biomeDecorator.grassPerChunk = -1;
        this.biomeDecorator.treesPerChunk = -1;
    }
}
