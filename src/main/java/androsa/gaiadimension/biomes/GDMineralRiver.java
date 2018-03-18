package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBush;

import java.util.Random;

public class GDMineralRiver extends GDBiomeBase {

    public GDMineralRiver(BiomeProperties props) {
        super(props);
        spawnableCreatureList.clear();

        topBlock = GDBlocks.glitterGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        for (int l3 = 0; l3 < 4; ++l3) {
            if (rand.nextInt(4) == 0) {
                int i4 = rand.nextInt(16) + 8;
                int k8 = rand.nextInt(16) + 8;
                int j12 = world.getHeight(pos.add(i4, 0, k8)).getY() * 2;

                if (j12 > 0) {
                    int k15 = rand.nextInt(j12);
                    new WorldGenBush((BlockBush)GDBlocks.crystalBloom).generate(world, rand, pos.add(i4, k15, k8));
                }
            }
        }
    }
}
