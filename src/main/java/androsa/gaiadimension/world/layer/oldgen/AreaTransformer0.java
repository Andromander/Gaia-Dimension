package androsa.gaiadimension.world.layer.oldgen;

public interface AreaTransformer0 {
    default <A extends Area> AreaFactory<A> run(BigContext<A> context) {
        return () -> context.createResult((x, z) -> {
            context.initRandom(x, z);
            return this.applyPixel(context, x, z);
        });
    }

    int applyPixel(Context context, int x, int z);
}
