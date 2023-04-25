package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.AreaTransformer0;
import androsa.gaiadimension.world.layer.util.Context;

public enum OceanLayer implements AreaTransformer0 {
    INSTANCE;

    @Override
    public int applyPixel(Context context, int x, int z) {
        return GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir);
    }
}
