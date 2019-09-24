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
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER, 30, 2, 4));
        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(ModEntities.MARKUZAR_PLANT, 15, 1, 3));
        //TODO: Moss Agate Lizard

        //flowers.add(new FlowerEntry(GDBlocks.roofed_agaric.getDefaultState(), 5));
        skyColor = GaiaSkyColors.GREEN_AGATE;
        //biomeDecorator.treesPerChunk = 20;
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(2) == 0) {
            if (rand.nextInt(2) == 0) {
                return new WorldGenShrub(GDBlocks.green_agate_log.getDefaultState(), GDBlocks.green_agate_leaves.getDefaultState());
            } else {
                return GaiaGenGreenTrees;
            }
        } else {
            return new GDGenNoTrees();
        }
    }*/

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
