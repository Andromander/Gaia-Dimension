package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GDGenCrystalBloom;
import androsa.gaiadimension.world.GDGenCrystalGrowth;
import androsa.gaiadimension.world.GDGenGoldstoneCorruptTree;
import androsa.gaiadimension.world.GDGenNoTrees;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGoldstoneLands extends GDBiomeBase {

    private WorldGenTrees GaiaGenCorruptTrees;

    public GDGoldstoneLands(BiomeProperties props) {
        super(props);

        GaiaGenCorruptTrees = new GDGenGoldstoneCorruptTree(false);

        topBlock = GDBlocks.corruptGrass.getDefaultState();
        fillerBlock = GDBlocks.corruptSoil.getDefaultState();
    }

    //TODO: Generate veins of Corrupt Blocks

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(5) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenCorruptTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        //TODO: Find a way to not generate poppies and dandelions
        if (rand.nextInt(16) == 0) {
            return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.CORRUPT_VARLOOM);
        } else {
            if (rand.nextInt(1) == 0) {
                return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.BLACK_GOLDSTONE);
            } else {
                return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.RED_GOLDSTONE);
            }
        }
    }
}
