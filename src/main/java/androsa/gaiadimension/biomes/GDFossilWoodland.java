package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GDGenCrystalBloom;
import androsa.gaiadimension.world.GDGenCrystalGrowth;
import androsa.gaiadimension.world.GDGenFossilizedTree;
import androsa.gaiadimension.world.GDGenNoTrees;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDFossilWoodland extends GDBiomeBase {

    private WorldGenTrees GaiaGenFossilTrees;

    public GDFossilWoodland(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDRockyLuggeroth.class, 10, 4, 5));

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
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.OUZIUM);
            } else {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.THISCUS);
            }
        } else {
            return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.OLD);
        }
    }
}
