package androsa.gaiadimension.world.layer.oldgen;

import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

public interface Context {
    int nextRandom(int bound);

    ImprovedNoise getBiomeNoise();
}
