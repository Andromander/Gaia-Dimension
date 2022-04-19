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

    public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> contextFactory, long seed, Registry<Biome> registry) {
        biomeRegistry = registry;

        AreaFactory<T> islands = IslandLayer.INSTANCE.run(contextFactory.apply(1L));
        islands = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), islands);
        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(1L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2001L), islands);
        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(2L), islands);
        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(50L), islands);
        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(70L), islands);
        islands = RemoveTooMuchOceanLayer.INSTANCE.run(contextFactory.apply(2L), islands);

        AreaFactory<T> ocean = OceanLayer.INSTANCE.run(contextFactory.apply(2L));
        ocean = ZoomLayer.FUZZY.run(contextFactory.apply(2001L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2002L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2003L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2004L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2005L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2006L), ocean);

        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2002), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2003), islands);
        islands = AddIslandLayer.INSTANCE.run(contextFactory.apply(4L), islands);
        islands = GoldIslandLayer.INSTANCE.run(contextFactory.apply(5L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(1000), islands);

        AreaFactory<T> biomes = new GaiaBiomesLayer().run(contextFactory.apply(1L), islands);
        biomes = SpecialBiomeLayer.INSTANCE.setup(seed).run(contextFactory.apply(1000L), biomes);
        biomes = SurroundingBiomeLayer.INSTANCE.run(contextFactory.apply(1000L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001L), biomes);
        biomes = SpecialBiomeMixLayer.INSTANCE.run(contextFactory.apply(700L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1002L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1003L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1004L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1005L), biomes);

        AreaFactory<T> river = MineralRiverLayer.INSTANCE.run(contextFactory.apply(1L), biomes);
        river = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), river);

        for (int i = 0; i < 4; i++) {
            //biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000L + i), biomes);
            if (i == 0) {
                biomes = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), biomes);
            }
            if (i == 1) {
                biomes = CoastLayer.INSTANCE.run(contextFactory.apply(1000L), biomes);
            }
        }

        biomes = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomes);
        biomes = MineralRiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomes, river);
        return OceanMixerLayer.INSTANCE.run(contextFactory.apply(100L), biomes, ocean);
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        biomeRegistry = registry;
        AreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaContext(25, seed, contextSeed), seed, registry);
        return new Layer(areaFactory);
    }
}
