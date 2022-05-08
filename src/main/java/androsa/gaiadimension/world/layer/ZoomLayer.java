package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.util.Area;
import androsa.gaiadimension.world.layer.util.AreaTransformer1;
import androsa.gaiadimension.world.layer.util.BigContext;

public enum ZoomLayer implements AreaTransformer1 {
    NORMAL,
    FUZZY {
        @Override
        protected int modeOrRandom(BigContext<?> context, int first, int second, int third, int fourth) {
            return context.random(first, second, third, fourth);
        }
    };

    public int getParentX(int x) {
        return x >> 1;
    }

    @Override
    public int getParentY(int y) {
        return y >> 1;
    }

    @Override
    public int applyPixel(BigContext<?> context, Area area, int x, int z) {
        int first = area.get(this.getParentX(x), this.getParentY(z));
        context.initRandom(x >> 1 << 1, z >> 1 << 1);
        int pX = x & 1;
        int pZ = x & 1;

        if (pX == 0 && pZ == 0) {
            return first;
        } else {
            int second = area.get(this.getParentX(x), this.getParentY(z + 1));
            int randFS = context.random(first, second);

            if (pX == 0 && pZ == 1) {
                return randFS;
            } else {
                int third = area.get(this.getParentX(x + 1), this.getParentY(z));
                int randFT = context.random(first, third);

                if (pX == 1 && pZ == 0) {
                    return randFT;
                } else {
                    int fourth = area.get(this.getParentX(x + 1), this.getParentY(z + 1));
                    return this.modeOrRandom(context, first, second, third, fourth);
                }
            }
        }
    }

    protected int modeOrRandom(BigContext<?> context, int first, int second, int third, int fourth) {
        if (second == third && third == fourth) {
            return second;
        } else if (first == second && first == third) {
            return first;
        } else if (first == second && first == fourth) {
            return first;
        } else if (first == third && first == fourth) {
            return first;
        } else if (first == second && third != fourth) {
            return first;
        } else if (first == third && second != fourth) {
            return first;
        } else if (first == fourth && second != third) {
            return first;
        } else if (second == third && first != fourth) {
            return second;
        } else if (second == fourth && first != third) {
            return second;
        } else {
            return third == fourth && first != second ? third : context.random(first, second, third, fourth);
        }
    }
}
