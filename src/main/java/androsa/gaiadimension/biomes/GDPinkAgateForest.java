package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GDGenCrystalBloom;
import androsa.gaiadimension.world.GDGenCrystalGrowth;
import androsa.gaiadimension.world.GDGenNoTrees;
import androsa.gaiadimension.world.GDGenPinkAgateTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDPinkAgateForest extends GDBiomeBase {

    private WorldGenTrees GaiaGenPinkTrees;

    public GDPinkAgateForest(Biome.BiomeProperties props) {
        super(props);
        GaiaGenPinkTrees = new GDGenPinkAgateTree(false);

        topBlock = GDBlocks.glitterGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }
/*
    @Override
    public void decorate(World par1World, Random par2Random, BlockPos pos) {
        for (int l3 = 0; l3 < 4; ++l3) {
            if (par2Random.nextInt(4) == 0) {
                int i4 = par2Random.nextInt(16) + 8;
                int k8 = par2Random.nextInt(16) + 8;
                int j12 = par1World.getHeight(pos.add(14, 0, k8)).getY() * 2;

                if (j12 > 0) {
                    int k15 = par2Random.nextInt(j12);
                    new WorldGenBush((BlockBush)GDBlocks.crystalGrowth).generate(par1World, par2Random, pos.add(i4, k15, k8));
                }
            }
        }
    }
*/
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(5) == 0 ? new GDGenNoTrees() : par1Random.nextInt(3) == 0 ? GaiaGenPinkTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        //TODO: Find a way to not generate poppies and dandelions
        if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.OUZIUM);
            } else {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.THISCUS);
            }
        } else {
            return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.PINK);
        }
    }
}
