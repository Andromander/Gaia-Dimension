package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CrystalPlainsBiome extends BaseGaiaBiome {

    public CrystalPlainsBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);
    }

    @Override
    public void addFeatures() {
        this.addStructureFeature(ModWorldgen.MINI_TOWER.get().configure(IFeatureConfig.NO_FEATURE_CONFIG));
        super.addFeatures();
        //TODO: Add Crystal Spires
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addPinkAgateTreesSparse(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 5);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addPinkMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER.get(), 20, 4, 6));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.CRYSTAL_GOLEM.get(), 15, 1, 3));
    }
}
