package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.util.AreaTransformer0;
import androsa.gaiadimension.world.layer.util.Context;

public enum IslandLayer implements AreaTransformer0 {
    INSTANCE;

    @Override
    public int applyPixel(Context context, int x, int z) {
        if (x == 0 && z == 0) {
            return 1;
        } else {
            int rand = context.nextRandom(10);
            return rand == 0 ? 1 : 0;
        }
    }
}
