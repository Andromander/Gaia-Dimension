package androsa.gaiadimension.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

public enum GaiaBaseLayer implements IAreaTransformer0 {

    INSTANCE;

    public int apply(INoiseRandom noise, int rand1, int rand2) {
        if (rand1 == 0 && rand2 == 0) {
            return 1;
        } else {
            return noise.random(10) == 0 ? 1 : GaiaLayerUtil.FOREST;
        }
    }
}
