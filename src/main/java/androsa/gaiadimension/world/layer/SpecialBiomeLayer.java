package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.layer.oldgen.Area;
import androsa.gaiadimension.world.layer.oldgen.AreaTransformer1;
import androsa.gaiadimension.world.layer.oldgen.BigContext;

import java.util.Random;

public enum SpecialBiomeLayer implements AreaTransformer1 {
    INSTANCE;

    private long seed;
    private final Random random = new Random();
    private final int[] centralbiomes = new int[]{
            GaiaLayerUtil.getBiomeId(ModBiomes.volcanic_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.static_wasteland),
            GaiaLayerUtil.getBiomeId(ModBiomes.crystal_salt_dunes),
            GaiaLayerUtil.getBiomeId(ModBiomes.goldstone_lands),
            GaiaLayerUtil.getBiomeId(ModBiomes.hotspot),
    };

    public SpecialBiomeLayer setup(long seed) {
        this.seed = seed;
        return this;
    }

    @Override
    public int getParentX(int x) {
        return x | 3;
    }

    @Override
    public int getParentY(int y) {
        return y | 3;
    }

    @Override
    public int applyPixel(BigContext<?> context, Area area, int x, int z) {
        this.random.setSeed(seed + (x & -4) * 25117L + (z & -4) * 151121L);
        int posX = random.nextInt(2) + 1;
        int posZ = random.nextInt(2) + 1;

        this.random.setSeed(seed + (x / 8) * 25117L + (z / 8) * 151121L);
        int offset = random.nextInt(3);

        if ((x & 3) == posX && (z & 3) == posZ) {
            if (random.nextInt(3) == 0) {
                return area.get(x, z);
            } else {
                return this.centralbiomes[random.nextInt(this.centralbiomes.length)];
            }
        }

        return area.get(x, z);
    }
}
