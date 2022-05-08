package androsa.gaiadimension.world.layer.util;

public interface AreaTransformer1 extends DimensionTransformer {
    default <A extends Area> AreaFactory<A> run (BigContext<A> context, AreaFactory<A> factory) {
        return () -> {
            A area = factory.make();
            return context.createResult((x, z) -> {
                context.initRandom(x, z);
                return this.applyPixel(context, area, x, z);
            }, area);
        };
    }

    int applyPixel(BigContext<?> context, Area area, int x, int z);
}
