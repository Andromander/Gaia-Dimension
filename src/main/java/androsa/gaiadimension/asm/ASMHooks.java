package androsa.gaiadimension.asm;

import androsa.gaiadimension.world.GaiaChunkGenerator;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.Optional;

public class ASMHooks {

    /**
     * Injection Point:<br>
     * {@link net.minecraft.world.gen.settings.DimensionGeneratorSettings#DimensionGeneratorSettings(long, boolean, boolean, SimpleRegistry, Optional)}<br>
     * [FIRST INST]
     */
    public static long getSeed(long seed) {
        GaiaChunkGenerator.hackSeed = seed;
        return seed;
    }
}
