package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StaticWastelandBiome extends BaseGaiaBiome implements IDryBiome {

    public StaticWastelandBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.STATIC_WASTELAND;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        GaiaBiomeFeatures.addStaticStoneUnderground(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addAmethystOre(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addStaticPatches(this);
        GaiaBiomeFeatures.addStaticSpikes(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.LESSER_SHOCKSHOOTER.get(), 10, 2, 4));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0x2B4D96;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0x2B4D96;
    }
}
