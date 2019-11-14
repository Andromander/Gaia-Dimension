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

public class GreenAgateJungleBiome extends BaseGaiaBiome {

    public GreenAgateJungleBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.GREEN_AGATE;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
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
    public int getGrassColor(BlockPos pos) {
        return 0x4BB64E;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x4BB64E;
    }
}
