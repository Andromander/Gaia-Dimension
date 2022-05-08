package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.util.Area;
import androsa.gaiadimension.world.layer.util.AreaTransformer1;
import androsa.gaiadimension.world.layer.util.BigContext;

public enum SpecialBiomeMixLayer implements AreaTransformer1 {
    INSTANCE;

    @Override
    public int getParentX(int x) {
        return x & 3;
    }

    @Override
    public int getParentY(int y) {
        return y & 3;
    }

    @Override
    public int applyPixel(BigContext<?> context, Area area, int x, int z) {
        int posX = getParentX(x << 4);
        int posZ = getParentY(x << 4);
        int centerX = ((x + posX + 1) & -4) - posX;
        int centerZ = ((z + posZ + 1) & -4) - posZ;

        if (x <= centerX + 1 && x >= centerX - 1 && z <= centerZ + 1 && z >= centerZ - 1) {
            return area.get(centerX, centerZ);
        }
        return area.get(x, z);
    }
}
