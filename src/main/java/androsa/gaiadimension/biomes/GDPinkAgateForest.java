package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

public class GDPinkAgateForest extends GDBiomeBase {

    public GDPinkAgateForest(Biome.BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.glitterGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }
}
