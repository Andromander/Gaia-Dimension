package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SmolderingBogBiome extends BaseGaiaBiome implements IDryBiome {

    public SmolderingBogBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.BISMUTH;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        GaiaBiomeFeatures.addBismuthLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addBogPatches(this);
        GaiaBiomeFeatures.addBismuthSpires(this);
        GaiaBiomeFeatures.addBismuthGeysers(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.BISMUTH_ULETRUS.get(), 15, 2, 3));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0x262627;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0x111112;
    }
}
