package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class GaiaGenerationSettings extends GenerationSettings {
    public static int SEALEVEL = 63;

    public GaiaGenerationSettings() {
        setDefaultBlock(ModBlocks.gaia_stone.get().getDefaultState());
        setDefaultFluid(ModBlocks.mineral_water.get().getDefaultState());
    }

    @Override
    public int getBedrockFloorHeight() {
        return 0;
    }

    public int getWatchtowerFeatureDistance() {
        return 32;
    }

    public int getWatchtowerFeatureSeparation() {
        return 8;
    }
}
