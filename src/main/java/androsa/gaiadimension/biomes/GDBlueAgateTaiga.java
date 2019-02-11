package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDGrowthSapper;
import androsa.gaiadimension.entity.GDHowliteWolf;
import androsa.gaiadimension.entity.boss.GDBlueHowliteWolf;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenBlueAgateTree;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDBlueAgateTaiga extends GDBiomeBase {

    private static final GDGenBlueAgateTree GaiaGenBlueTrees = new GDGenBlueAgateTree(false);
    private short[] skyColorRGB = new short[] { 149, 197, 231 };

    public GDBlueAgateTaiga(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(GDGrowthSapper.class, 30, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(GDHowliteWolf.class, 15, 2, 4));
        spawnableCreatureList.add(new SpawnListEntry(GDBlueHowliteWolf.class, 1, 1, 1));
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {

        super.decorate(world, rand, pos);

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore_blue.getDefaultState(), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(8) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenBlueTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth);
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x688AC8;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x688AC8;
    }
}
