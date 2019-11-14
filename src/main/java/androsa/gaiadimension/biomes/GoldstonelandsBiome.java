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

public class GoldstonelandsBiome extends BaseGaiaBiome {

    public GoldstonelandsBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        skyColor = GaiaSkyColors.GOLDSTONE;
    }

    @Override
    public void addFeatures() {
        super.addFeatures();
        //TODO: Generate veins of Corrupt Blocks
        GaiaBiomeFeatures.addGlitterBlobUnderground(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addGoldstoneTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthCorrupt(this);
        GaiaBiomeFeatures.addBloomsCorrupt(this);
        GaiaBiomeFeatures.addCorruptMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        //TODO: Corrupt Lagrahk
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.CONTORTED_NAGA.get(), 10, 2, 3));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.CORRUPT_SAPPER.get(), 20, 2, 4));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x232323;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x232323;
    }
}
