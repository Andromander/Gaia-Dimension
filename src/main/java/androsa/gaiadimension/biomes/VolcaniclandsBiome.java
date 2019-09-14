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

public class VolcaniclandsBiome extends BaseGaiaBiome implements IDryBiome {

    public VolcaniclandsBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addSearingRockUnderground(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addBlueAgateTrees(this);
        GaiaBiomeFeatures.addBurningTrees(this);
        GaiaBiomeFeatures.addBurntTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthBurnt(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        //TODO: Fire Geysers
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.LESSER_SPITFIRE, 10, 2, 4));

        flowers.clear();
        skyColor = GaiaSkyColors.VOLCANICLAND;
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        if (par1Random.nextInt(16) == 0) {
            if (par1Random.nextInt(5) == 0) {
                return GaiaGenFireTrees;
            } else {
                return GaiaGenBurntTrees;
            }
        } else {
            return new GDGenNoTrees();
        }
    }*/

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
