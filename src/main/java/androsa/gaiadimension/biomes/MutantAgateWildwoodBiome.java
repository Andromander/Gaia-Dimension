package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MutantAgateWildwoodBiome extends BaseGaiaBiome {

    public MutantAgateWildwoodBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.MUTANT_AGATE;
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
        GaiaBiomeFeatures.addRedOpals(this);
        GaiaBiomeFeatures.addBlueOpals(this);
        GaiaBiomeFeatures.addGreenOpals(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addVariousTrees(this);
        GaiaBiomeFeatures.addBloomsMutant(this);
        GaiaBiomeFeatures.addMysteryMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER.get(), 40, 3, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.MUTANT_GROWTH_EXTRACTOR.get(), 5, 2, 4));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0xD4D7B0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0xD4D7B0;
    }
}
