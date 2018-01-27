package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

public class GDPinkAgateForest extends GDBiomeBase {

    public GDPinkAgateForest(Biome.BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.glitterGrass.getDefaultState();
        this.fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }

    @Override
    public IBlockState getStoneReplacementState() {
        return GDBlocks.gaiaStone.getDefaultState();
    }
}
