package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PurpleAgateSwampBiome extends BaseGaiaBiome {

    public PurpleAgateSwampBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.PURPLE_AGATE;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMuckLakes(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addThickGlitterUnderground(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addPurpleAgateTrees(this);
        GaiaBiomeFeatures.addGummyBlobs(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 2);
        GaiaBiomeFeatures.addBloomsRare(this);
        GaiaBiomeFeatures.addPurpleMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER.get(), 10, 3, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.SPELLBOUND_ELEMENTAL.get(), 10, 1, 2));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0x806FB9;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0x806FB9;
    }
}
