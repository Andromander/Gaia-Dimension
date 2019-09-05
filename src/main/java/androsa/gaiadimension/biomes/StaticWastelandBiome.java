package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StaticWastelandBiome extends BaseGaiaBiome implements IDryBiome {

    public StaticWastelandBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        GaiaBiomeFeatures.addCarverUncoated(this);
        GaiaBiomeFeatures.addStaticStoneUnderground(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addStaticPatches(this);
        GaiaBiomeFeatures.addStaticSpikes(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.LESSER_SHOCKSHOOTER, 10, 2, 4));

        flowers.clear();
        skyColor = GaiaSkyColors.STATIC_WASTELAND;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x2B4D96;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x2B4D96;
    }
}
