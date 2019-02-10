package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDCrystalGolem;
import androsa.gaiadimension.entity.GDGrowthSapper;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import androsa.gaiadimension.world.gen.GDGenPinkAgateTree;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDCrystalPlains extends GDBiomeBase {

    private WorldGenAbstractTree GaiaGenPinkTrees;

    public GDCrystalPlains(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(GDGrowthSapper.class, 20, 4, 6));
        spawnableCreatureList.add(new SpawnListEntry(GDCrystalGolem.class, 15, 1, 3));

        GaiaGenPinkTrees = new GDGenPinkAgateTree(false);

        biomeDecorator.grassPerChunk = 5;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(3) == 0 ? new GDGenNoTrees() : par1Random.nextInt(12) == 0 ? GaiaGenPinkTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth);
    }
}
