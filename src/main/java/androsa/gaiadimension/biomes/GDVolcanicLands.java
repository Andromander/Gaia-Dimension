package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDLesserSpitfire;
import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenBurntAgateTree;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenFieryAgateTree;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDVolcanicLands extends GDBiomeBase implements IDangerousBiome {

    private WorldGenAbstractTree GaiaGenBurntTrees = new GDGenBurntAgateTree(false);
    private WorldGenAbstractTree GaiaGenFireTrees = new GDGenFieryAgateTree(false);

    public GDVolcanicLands(Biome.BiomeProperties props) {
        super(props);

        this.spawnableCreatureList.add(new SpawnListEntry(GDLesserSpitfire.class, 10, 2, 4));

        flowers.clear();

        skyColor = EnumSkyColors.VOLCANICLAND;
        biomeDecorator.lavaPoolChance = 0.25F;
        biomeDecorator.grassPerChunk = 1;
        biomeDecorator.flowersPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {

        super.decorate(world, rand, pos);

        //Searing Rock gen
        for (int i = 0; i < 20; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.searing_rock.getDefaultState(), 33, input -> input == GDBlocks.volcanic_rock.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        if (par1Random.nextInt(16) == 0) {
            if (par1Random.nextInt(5) == 0) {
                return GaiaGenFireTrees;
            } else {
                return GaiaGenBurntTrees;
            }
        } else {
            return new GDGenNoTrees();
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth_seared);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x232323;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x232323;
    }

    @Override
    public IBlockState getStoneReplacement() {
        return GDBlocks.volcanic_rock.getDefaultState();
    }
}
