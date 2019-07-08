package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FossilWoodlandBiome extends BaseGaiaBiome {

    public FossilWoodlandBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addFossilTrees(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 3);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addFossilMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.ROCKY_LUGGEROTH, 10, 4, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.RUGGED_LURMORUS, 10, 1, 3));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.ANCIENT_LAGRAHK, 10, 1, 1));

        //this.flowers.add(new FlowerEntry(GDBlocks.stickly_cupsir.getDefaultState(), 5));
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(6) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenFossilTrees : new GDGenNoTrees();
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0xBBA779;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0xBBA779;
    }
}
