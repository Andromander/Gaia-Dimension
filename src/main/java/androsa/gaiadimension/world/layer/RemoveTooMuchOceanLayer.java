package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;

public enum RemoveTooMuchOceanLayer implements CastleTransformer {
    INSTANCE;

    private HolderGetter<Biome> registry;

    public RemoveTooMuchOceanLayer setup(HolderGetter<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        if (isOcean(center) && isOcean(north) && isOcean(east) && isOcean(west) && isOcean(south)) {
            int rand = context.nextRandom(2);
            return rand == 0 ? center : 1;
        }

        return 1;
    }

    private boolean isOcean(int biome) {
        return biome == GaiaLayerUtil.getBiomeId(ModBiomes.mineral_reservoir, registry) || biome == 0;
    }
}
