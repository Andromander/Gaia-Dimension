package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShiningGroveBiome extends BaseGaiaBiome {

    public ShiningGroveBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.AURA;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addAuraLake(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addAuraTrees(this);
        GaiaBiomeFeatures.addAuraShoots(this);
        GaiaBiomeFeatures.addCrystalGrowthAura(this);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0x79CEAD;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0xDDF7FF;
    }
}
