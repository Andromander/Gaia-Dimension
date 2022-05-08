package androsa.gaiadimension.world.layer.util;

public interface CastleTransformer extends AreaTransformer1, DimensionOffset1Transformer {
    int apply(Context context, int north, int east, int south, int west, int center);

    default int applyPixel(BigContext<?> context, Area area, int x, int z) {
        return this.apply(context,
                area.get(this.getParentX(x + 1), this.getParentY(z + 0)),
                area.get(this.getParentX(x + 2), this.getParentY(z + 1)),
                area.get(this.getParentX(x + 1), this.getParentY(z + 2)),
                area.get(this.getParentX(x + 0), this.getParentY(z + 1)),
                area.get(this.getParentX(x + 1), this.getParentY(z + 1)));
    }
}
