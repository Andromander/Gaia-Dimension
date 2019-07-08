package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MutantAgateWildwoodBiome extends BaseGaiaBiome {

    public MutantAgateWildwoodBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addRedOpals(this);
        GaiaBiomeFeatures.addBlueOpals(this);
        GaiaBiomeFeatures.addGreenOpals(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addPinkAgateTreesSparse(this);
        GaiaBiomeFeatures.addBlueAgateTreesSparse(this);
        GaiaBiomeFeatures.addGreenAgateTreesSparse(this);
        GaiaBiomeFeatures.addPurpleAgateTreesSparse(this);
        GaiaBiomeFeatures.addCrystalGrowthMutant(this);
        GaiaBiomeFeatures.addBloomsMutant(this);
        GaiaBiomeFeatures.addMysteryMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER, 40, 3, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.MUTANT_EXTRACTOR, 5, 2, 4));

        //flowers.clear();
        //flowers.add(new FlowerEntry(GDBlocks.ouzium.getDefaultState(), 20));
        //flowers.add(new FlowerEntry(GDBlocks.agathum.getDefaultState(), 10));
        //flowers.add(new FlowerEntry(GDBlocks.mystical_murgni.getDefaultState(), 5));
        skyColor = GaiaSkyColors.MUTANT_AGATE;
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {

        if (par1Random.nextInt(8) == 0) {
            switch (par1Random.nextInt(4)) {
                case 3:
                    return GaiaGenPurpleTrees;
                case 2:
                    return GaiaGenGreenTrees;
                case 1:
                    return GaiaGenBlueTrees;
                case 0:
                default:
                    return GaiaGenPinkTrees;
            }
        } else {
            return new GDGenNoTrees();
        }
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0xD4D7B0;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0xD4D7B0;
    }
}
