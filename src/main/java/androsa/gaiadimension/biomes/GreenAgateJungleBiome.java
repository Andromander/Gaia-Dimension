package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GreenAgateJungleBiome extends BaseGaiaBiome {

    public GreenAgateJungleBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.GREEN_AGATE;
    }

    @Override
    public void addFeatures() {
        this.addStructureFeature(ModWorldgen.MINI_TOWER.get().configure(IFeatureConfig.NO_FEATURE_CONFIG));
        super.addFeatures();
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addGreenOpals(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addGreenAgateTrees(this);
        GaiaBiomeFeatures.addGreenAgateBushes(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 4);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addGreenMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        //TODO: Moss Agate Lizard
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER.get(), 30, 2, 4));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(ModEntities.MARKUZAR_PLANT.get(), 15, 3, 3));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0x4BB64E;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0x4BB64E;
    }
}
