package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PurpleAgateSwampBiome extends BaseGaiaBiome {

    public PurpleAgateSwampBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addMuckLakes(this);
        GaiaBiomeFeatures.addMagmaLakes(this);
        GaiaBiomeFeatures.addMineralLakes(this);
        GaiaBiomeFeatures.addThickGlitterUnderground(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addWhiteOpals(this);
        GaiaBiomeFeatures.addPurpleAgateTrees(this);
        GaiaBiomeFeatures.addGummyBlobs(this);
        GaiaBiomeFeatures.addCrystalGrowthNormal(this, 3);
        GaiaBiomeFeatures.addBloomsRare(this);
        GaiaBiomeFeatures.addPurpleMushrooms(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.GROWTH_SAPPER, 10, 3, 5));
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.SPELLBOUND_ELEMENTAL, 10, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.MUCKLING, 10, 1, 1));

        //flowers.clear();
        //flowers.add(new FlowerEntry(GDBlocks.ouzium.getDefaultState(), 20));
        //flowers.add(new FlowerEntry(GDBlocks.thiscus.getDefaultState(), 10));
        //flowers.add(new FlowerEntry(GDBlocks.bulbous_hobina.getDefaultState(), 5));
        skyColor = GaiaSkyColors.PURPLE_AGATE;
    }

    /*@Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(5) == 0 ? new GDGenNoTrees() : par1Random.nextInt(3) == 0 ? GaiaGenPurpleTrees : new GDGenNoTrees();
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x806FB9;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x806FB9;
    }
}
