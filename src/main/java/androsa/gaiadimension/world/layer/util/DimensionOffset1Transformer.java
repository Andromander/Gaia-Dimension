package androsa.gaiadimension.world.layer.util;

public interface DimensionOffset1Transformer extends DimensionTransformer {
    @Override
    default int getParentX(int x) {
        return x - 1;
    }

    @Override
    default int getParentY(int y) {
        return y - 1;
    }
}
