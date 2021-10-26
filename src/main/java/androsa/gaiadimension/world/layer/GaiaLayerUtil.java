package androsa.gaiadimension.world.layer;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.area.LazyArea;
import net.minecraft.world.level.newbiome.context.BigContext;
import net.minecraft.world.level.newbiome.context.LazyAreaContext;
import net.minecraft.world.level.newbiome.layer.Layer;
import net.minecraft.world.level.newbiome.layer.Layers;
import net.minecraft.world.level.newbiome.layer.SmoothLayer;
import net.minecraft.world.level.newbiome.layer.ZoomLayer;

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

        biomes = Layers.zoom(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

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
