package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenGreenAgateTree;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDGreenAgateJungle extends GDBiomeBase {

    private WorldGenTrees GaiaGenGreenTrees;
    private short[] skyColorRGB = new short[] { 128, 191, 158 };

    public GDGreenAgateJungle(Biome.BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDNutrientGrowthSapper.class, 9, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDMarkuzarPlant.class, 5, 1, 3));

        GaiaGenGreenTrees = new GDGenGreenAgateTree(false);

        getGDBiomeDecorator().grassPerChunk = 4;
        getGDBiomeDecorator().treesPerChunk = 20;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {

        super.decorate(world, rand, pos);

        for (int i = 0; i < 4; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.opal_ore_green.getDefaultState(), 8, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {

        if (rand.nextInt(3) == 0) {
            if (rand.nextInt(2) == 0) {
                return new WorldGenShrub(GDBlocks.green_agate_log.getDefaultState(), GDBlocks.green_agate_leaves.getDefaultState());
            } else {
                return GaiaGenGreenTrees;
            }
        } else {
            return new GDGenNoTrees();
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(32) == 0) {
            return new GDGenCrystalPlants(GDBlocks.roofed_agaric);
        } else if (rand.nextInt(8) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalPlants(GDBlocks.ouzium);
            } else {
                return new GDGenCrystalPlants(GDBlocks.thiscus);
            }
        } else {
            return new GDGenCrystalPlants(GDBlocks.crystal_growth_green);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x4BB64E;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x4BB64E;
    }
}
