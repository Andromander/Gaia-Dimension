package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class PinkAgateForestBiome extends BaseGaiaBiome {

    public PinkAgateForestBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addRedOpals(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addPinkAgateTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 3);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addPinkMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER.get(), 20, 3, 5));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.AGATE_GOLEM.get(), 15, 1, 3));

        //flowers.add(new FlowerEntry(GDBlocks.spotted_kersei.getDefaultState(), 5));
    }
}
