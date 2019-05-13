package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDSaltion;
import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBlocks;

public class GDSaltDunes extends GDBiomeBase {

    public GDSaltDunes(BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.salt.getDefaultState();
        fillerBlock = GDBlocks.salt.getDefaultState();

        spawnableCreatureList.add(new SpawnListEntry(GDSaltion.class, 15, 1, 3));

        skyColor = EnumSkyColors.SALT_DUNES;
        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
        biomeDecorator.lakesPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
    }
}
