package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;

public class SaltDunesBiome extends BaseGaiaBiome {

    public SaltDunesBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.SALTION, 15, 1, 3));

        //topBlock = GDBlocks.salt.getDefaultState();
        //fillerBlock = GDBlocks.salt.getDefaultState();
        //flowers.clear();
        skyColor = GaiaSkyColors.SALT_DUNES;
    }
}
