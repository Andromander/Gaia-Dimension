package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilWoodlandBiome extends BaseGaiaBiome {

    public FossilWoodlandBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);
    }

    @Override
    public void addFeatures() {
        this.addStructureFeature(ModWorldgen.MINI_TOWER.get().configure(IFeatureConfig.NO_FEATURE_CONFIG));
        GaiaBiomeFeatures.addStructures(this);
        super.addFeatures();
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addFossilTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 2);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addFossilMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.ROCKY_LUGGEROTH.get(), 10, 4, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.RUGGED_LURMORUS.get(), 10, 1, 3));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.ANCIENT_LAGRAHK.get(), 10, 1, 1));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0xBBA779;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0xBBA779;
    }
}
