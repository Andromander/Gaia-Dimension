package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenCrystalBloom;
import androsa.gaiadimension.world.gen.GDGenCrystalGrowth;
import androsa.gaiadimension.world.gen.GDGenFossilizedTree;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDFossilWoodland extends GDBiomeBase {

    private WorldGenTrees GaiaGenFossilTrees;

    public GDFossilWoodland(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDRockyLuggeroth.class, 10, 4, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDRuggedLurmorus.class, 10, 1, 3));

        GaiaGenFossilTrees = new GDGenFossilizedTree(false);

        topBlock = GDBlocks.oldGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(6) == 0 ? new GDGenNoTrees() : par1Random.nextInt(4) == 0 ? GaiaGenFossilTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalBloom(GDBlocks.ouzium);
            } else {
                return new GDGenCrystalBloom(GDBlocks.thiscus);
            }
        } else {
            return new GDGenCrystalGrowth(GDBlocks.growthOld);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xEFC673;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xEFC673;
    }
}
