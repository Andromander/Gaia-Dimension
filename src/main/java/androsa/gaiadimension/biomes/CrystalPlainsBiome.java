package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class CrystalPlainsBiome extends BaseGaiaBiome {

    public CrystalPlainsBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(surface, config, category, depth, scale, temp);

        //TODO: Add Crystal Spires
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addPinkAgateTreesSparse(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 5);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addPinkMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER, 20, 4, 6));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.CRYSTAL_GOLEM, 15, 1, 3));

        //flowers.add(new FlowerEntry(GDBlocks.spotted_kersei.getDefaultState(), 5));
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(3) == 0 ? new GDGenNoTrees() : par1Random.nextInt(12) == 0 ? GaiaGenPinkTrees : new GDGenNoTrees();
    }*/
}
