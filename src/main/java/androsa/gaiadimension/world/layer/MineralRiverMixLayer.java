package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.Area;
import androsa.gaiadimension.world.layer.util.AreaTransformer2;
import androsa.gaiadimension.world.layer.util.Context;
import androsa.gaiadimension.world.layer.util.DimensionOffset0Transformer;

public enum MineralRiverMixLayer implements AreaTransformer2, DimensionOffset0Transformer {

    INSTANCE;

    MineralRiverMixLayer() { }

    @Override
    public int applyPixel(Context random, Area area1, Area area2, int val1, int val2) {
        int i = area1.get(this.getParentX(val1), this.getParentY(val2));
        int j = area2.get(this.getParentX(val1), this.getParentY(val2));

        if (i == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir)) {
            return i;
        } else if (j == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_river)) {
            return j;
        } else {
            return i;
        }
    }
}
