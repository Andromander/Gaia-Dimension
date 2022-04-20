package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.AreaTransformer0;
import androsa.gaiadimension.world.layer.oldgen.Context;

public enum OceanLayer implements AreaTransformer0 {
    INSTANCE;

    @Override
    public int applyPixel(Context context, int x, int z) {
        return context.nextRandom(2) == 0 ? GaiaLayerUtil.getBiomeId(ModBiomes.aquamarine_trench) : GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir);
    }
}