package androsa.gaiadimension.asm;

import androsa.gaiadimension.world.chunk.GaiaBiomeSource;

import java.util.Optional;

public class ASMHooks {

    /**
     * Injection Point:<br>
     * {@link net.minecraft.world.level.levelgen.WorldOptions#WorldOptions(long, boolean, boolean, Optional)}<br>
     * [BEFORE FIRST PUTFIELD]
     */
    public static long seed(long seed) {
        GaiaBiomeSource.hackseed = seed;
        return seed;
    }
}
