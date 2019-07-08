package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.entity.EntityClassification;

public class MineralReservoirBiome extends BaseGaiaBiome {

    public MineralReservoirBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        //TODO: Salt Growth
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(ModEntities.MINERAL_ARENTHIS, 10, 1, 3));

        //this.topBlock = GDBlocks.salt.getDefaultState();
        //this.fillerBlock = GDBlocks.salt.getDefaultState();
    }
}
