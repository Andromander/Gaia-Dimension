package androsa.gaiadimension.asm;

import androsa.gaiadimension.world.chunk.GaiaBiomeSource;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.Optional;

public class ASMHooks {

    /**
     * Injection Point:<br>
     * {@link net.minecraft.world.level.levelgen.WorldGenSettings#WorldGenSettings(long, boolean, boolean, net.minecraft.core.Registry, Optional)}<br>
     * [BEFORE FIRST PUTFIELD]
     */
    public static long seed(long seed) {
        GaiaBiomeSource.hackseed = seed;
        return seed;
    }

    /**
     * Injection Point:<br>
     * {@link net.minecraft.world.level.storage.LevelStorageSource#readWorldGenSettings(Dynamic, DataFixer, int)}<br>
     * [BEFORE FIRST ASTORE]
     */
    public static Dynamic<Tag> seed(Dynamic<Tag> seed) {
        GaiaBiomeSource.hackseed = ((CompoundTag) seed.getValue()).getLong("seed");
        return seed;
    }
}
