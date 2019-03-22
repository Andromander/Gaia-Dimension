package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDMutantAgateWildwood extends GDBiomeBase {

    private static final GDGenPinkAgateTree GaiaGenPinkTrees = new GDGenPinkAgateTree(false);
    private static final GDGenBlueAgateTree GaiaGenBlueTrees = new GDGenBlueAgateTree(false);
    private static final GDGenGreenAgateTree GaiaGenGreenTrees = new GDGenGreenAgateTree(false);
    private static final GDGenPurpleAgateTree GaiaGenPurpleTrees = new GDGenPurpleAgateTree(false);

    public GDMutantAgateWildwood(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(GDGrowthSapper.class, 40, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(GDMutantGrowthExtractor.class, 5, 2, 4));

        skyColorRGB = new short[] { 241, 154, 193 };
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {

        super.decorate(world, rand, pos);

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore_red.getDefaultState(), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore_blue.getDefaultState(), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore_green.getDefaultState(), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @Override
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
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth_mutant);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xD4D7B0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xD4D7B0;
    }
}
