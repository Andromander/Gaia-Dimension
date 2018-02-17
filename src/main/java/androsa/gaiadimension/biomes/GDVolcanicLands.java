package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.world.biome.Biome;

public class GDVolcanicLands extends GDBiomeBase {

    public GDVolcanicLands(Biome.BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.volcanicRock.getDefaultState();
        fillerBlock = GDBlocks.tektiteBlock.getDefaultState();
    }

}
