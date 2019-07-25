package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class GaiaGenerationSettings extends GenerationSettings {
    public static int SEALEVEL = 63;

    public GaiaGenerationSettings() {
        setDefaultBlock(ModBlocks.gaia_stone.getDefaultState());
        setDefaultFluid(Blocks.WATER.getDefaultState());
    }

    public int getBiomeSize() {
        return 4;
    }

    public int getRiverSize() {
        return 4;
    }

    public int getBiomeId() {
        return -1;
    }

    public int getBedrockFloorHeight() {
        return 0;
    }
}
