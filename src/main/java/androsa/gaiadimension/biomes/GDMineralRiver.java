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

        topBlock = GDBlocks.saltBlock.getDefaultState();
        fillerBlock = GDBlocks.saltBlock.getDefaultState();

        getGDBiomeDecorator().treesPerChunk = -1;
        getGDBiomeDecorator().grassPerChunk = -1;
    }
}
