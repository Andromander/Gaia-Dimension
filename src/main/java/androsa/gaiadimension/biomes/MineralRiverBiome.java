package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;

public class MineralRiverBiome extends BaseGaiaBiome {

    public MineralRiverBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        //TODO: Salt Growth

        //topBlock = GDBlocks.salt.getDefaultState();
        //fillerBlock = GDBlocks.salt.getDefaultState();
    }
}
