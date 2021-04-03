package androsa.gaiadimension.world.layer;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class GaiaLayerUtil {

    private static Registry<Biome> biomeRegistry;

    static int getBiomeId(RegistryKey<Biome> define) {
        Biome biome = biomeRegistry.get(define);
        return biomeRegistry.getId(biome);
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory, Registry<Biome> registry) {
        biomeRegistry = registry;

        IAreaFactory<T> biomes = new GaiaBiomesLayer().run(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1005), biomes);

        biomes = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        IAreaFactory<T> riverLayer = MineralRiverLayer.INSTANCE.run(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.run(contextFactory.apply(7000L), riverLayer);
        biomes = MineralRiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        biomeRegistry = registry;
        IAreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaLayerContext(25, seed, contextSeed), registry);
        return new Layer(areaFactory);
    }
}
