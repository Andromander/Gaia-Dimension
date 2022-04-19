package androsa.gaiadimension.world.layer.oldgen;

public interface C0Transformer extends AreaTransformer1, DimensionOffset0Transformer {
    int apply(Context context, int biome);

    default int applyPixel(BigContext<?> context, Area area, int x, int z) {
        return this.apply(context, area.get(this.getParentX(x), this.getParentY(z)));
    }
}
