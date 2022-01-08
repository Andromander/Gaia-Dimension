package androsa.gaiadimension.world.layer.oldgen;

public interface DimensionOffset0Transformer extends DimensionTransformer {
    @Override
    default int getParentX(int x) {
        return x;
    }

    @Override
    default int getParentY(int y) {
        return y;
    }
}
