package androsa.gaiadimension.world.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum MineralRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer {

    INSTANCE;

    MineralRiverMixLayer() { }

    @Override
    public int apply(INoiseRandom random, IArea area1, IArea area2, int val1, int val2) {
        int i = area1.getValue(this.func_215721_a(val1), this.func_215722_b(val2));
        int j = area2.getValue(this.func_215721_a(val1), this.func_215722_b(val2));

        if (i == GaiaLayerUtil.RESERVOIR) {
            return i;
        } else if (j == GaiaLayerUtil.RIVER) {
            return j;
        } else {
            return i;
        }
    }
}
