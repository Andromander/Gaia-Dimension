package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDPinkAgateForest extends GDBiomeBase {

    private WorldGenAbstractTree GaiaGenPinkTrees;

    public GDPinkAgateForest(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDCommonGrowthSapper.class, 9, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDAgateGolem.class, 15, 1, 2));

        GaiaGenPinkTrees = new GDGenPinkAgateTree(false);

        topBlock = GDBlocks.glitter_grass.getDefaultState();
        fillerBlock = GDBlocks.heavy_soil.getDefaultState();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {

        super.decorate(world, rand, pos);

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore.getStateFromMeta(0), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(5) == 0 ? new GDGenNoTrees() : par1Random.nextInt(3) == 0 ? GaiaGenPinkTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(32) == 0) {
            return new GDGenCrystalPlants(GDBlocks.spotted_kersei);
        } else if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalPlants(GDBlocks.ouzium);
            } else {
                return new GDGenCrystalPlants(GDBlocks.thiscus);
            }
        } else {
            return new GDGenCrystalPlants(GDBlocks.crystal_growth_pink);
        }
    }
}
