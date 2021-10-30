package androsa.gaiadimension.asm;

import androsa.gaiadimension.world.GaiaChunkGenerator;
import net.minecraft.core.MappedRegistry;

import java.util.Optional;

public class ASMHooks {

    /**
     * Injection Point:<br>
     * {@link net.minecraft.world.level.levelgen.WorldGenSettings#WorldGenSettings(long, boolean, boolean, MappedRegistry, Optional)}<br>
     * [BEFORE FIRST PUTFIELD]
     */
    public static long getSeed(long seed) {
        GaiaChunkGenerator.hackSeed = seed;
        return seed;
    }
}
