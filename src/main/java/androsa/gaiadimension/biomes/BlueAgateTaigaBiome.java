package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlueAgateTaigaBiome extends BaseGaiaBiome {

    public BlueAgateTaigaBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addBlueOpals(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addBlueAgateTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 3);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addBlueMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER, 30, 3, 5));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.HOWLITE_WOLF, 15, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.BLUE_HOWLITE_WOLF, 1, 1, 1));

        skyColor = GaiaSkyColors.BLUE_AGATE;
        //flowers.add(new FlowerEntry(GDBlocks.thorny_wiltha.getDefaultState(), 5));
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(8) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenBlueTrees : new GDGenNoTrees();
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x688AC8;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x688AC8;
    }
}
