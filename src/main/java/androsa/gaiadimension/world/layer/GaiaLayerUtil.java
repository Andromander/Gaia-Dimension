package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.oldgen.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.function.LongFunction;

public class GaiaLayerUtil {

    private static Registry<Biome> biomeRegistry;

    static int getBiomeId(ResourceKey<Biome> define) {
        Biome biome = biomeRegistry.get(define);
        return biomeRegistry.getId(biome);
    }

    public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> contextFactory, Registry<Biome> registry) {
        biomeRegistry = registry;

        AreaFactory<T> biomes = new GaiaBiomesLayer().run(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1005), biomes);

        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001), biomes);

        AreaFactory<T> riverLayer = MineralRiverLayer.INSTANCE.run(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.run(contextFactory.apply(7000L), riverLayer);
        biomes = MineralRiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        biomeRegistry = registry;
        AreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaContext(25, seed, contextSeed), registry);
        return new Layer(areaFactory);
    }
}
