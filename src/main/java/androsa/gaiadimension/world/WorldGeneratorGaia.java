package androsa.gaiadimension.world;

import androsa.gaiadimension.GDConfig;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGeneratorGaia implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int id = world.provider.getDimension();
        if (id == GDConfig.dimension.dimensionID) {
            generateGaia(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateGaia(World world, Random random, int BlockX, int BlockZ) {
        for (int i = 0; i < 10; i ++) {
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(100);
            new WorldGenMinable(GDBlocks.hematiteOre.getDefaultState(), 10, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }
}
