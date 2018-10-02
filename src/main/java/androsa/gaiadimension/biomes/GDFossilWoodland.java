package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenFossilizedTree;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDFossilWoodland extends GDBiomeBase {

    private WorldGenAbstractTree GaiaGenFossilTrees;

    public GDFossilWoodland(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDRockyLuggeroth.class, 10, 4, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDRuggedLurmorus.class, 10, 1, 3));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDAncientLagrahk.class, 10, 1, 1));

        GaiaGenFossilTrees = new GDGenFossilizedTree(false);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(6) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenFossilTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(32) == 0) {
            return new GDGenCrystalPlants(GDBlocks.stickly_cupsir);
        } else if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalPlants(GDBlocks.ouzium);
            } else {
                return new GDGenCrystalPlants(GDBlocks.thiscus);
            }
        } else {
            return new GDGenCrystalPlants(GDBlocks.crystal_growth_old);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xBBA779;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xBBA779;
    }
}
