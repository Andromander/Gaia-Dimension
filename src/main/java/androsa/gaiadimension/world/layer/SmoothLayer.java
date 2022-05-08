package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.util.CastleTransformer;
import androsa.gaiadimension.world.layer.util.Context;

public enum SmoothLayer implements CastleTransformer {
    INSTANCE;

    @Override
    public int apply(Context context, int north, int east, int south, int west, int center) {
        boolean we = west == east;
        boolean ns = north == south;

        if (we == ns) {
            if (we) {
                return context.nextRandom(2) == 0 ? east : north;
            } else {
                return center;
            }
        } else {
            return we ? east : north;
        }
    }
}
