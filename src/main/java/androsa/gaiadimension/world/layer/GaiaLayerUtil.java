package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.layer.util.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.function.LongFunction;

public class GaiaLayerUtil {

    static int getBiomeId(ResourceKey<Biome> define, HolderGetter<Biome> registry) {
        return ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME).getId(registry.get(define).get().get());
    }

    public static <T extends Area, C extends BigContext<T>> AreaFactory<T> makeLayers(LongFunction<C> contextFactory, HolderGetter<Biome> registry) {
        AreaFactory<T> islands = IslandLayer.INSTANCE.run(contextFactory.apply(1L));
        islands = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), islands);
        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(1L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2001L), islands);
        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(2L), islands);
        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(50L), islands);
        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(70L), islands);
        islands = RemoveTooMuchOceanLayer.INSTANCE.setup(registry).run(contextFactory.apply(2L), islands);

        AreaFactory<T> ocean = OceanLayer.INSTANCE.setup(registry).run(contextFactory.apply(2L));
        ocean = ZoomLayer.FUZZY.run(contextFactory.apply(2001L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2002L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2003L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2004L), ocean);
        ocean = ZoomLayer.NORMAL.run(contextFactory.apply(2005L), ocean);
        ocean = SmoothLayer.INSTANCE.run(contextFactory.apply(1003L), ocean);

        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(3L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2002), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(2003), islands);
        islands = AddIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(4L), islands);
        islands = GoldIslandLayer.INSTANCE.setup(registry).run(contextFactory.apply(5L), islands);
        islands = ZoomLayer.NORMAL.run(contextFactory.apply(1000), islands);

        AreaFactory<T> biomes = new GaiaBiomesLayer(registry).run(contextFactory.apply(1L), islands);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1000L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1001L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1002L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1003L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1004L), biomes);
        biomes = ZoomLayer.NORMAL.run(contextFactory.apply(1005L), biomes);

        AreaFactory<T> river = MineralRiverLayer.INSTANCE.setup(registry).run(contextFactory.apply(1L), biomes);
        river = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), river);

        biomes = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomes);
        biomes = MineralRiverMixLayer.INSTANCE.setup(registry).run(contextFactory.apply(100L), biomes, river);
        return OceanMixerLayer.INSTANCE.setup(registry).run(contextFactory.apply(100L), biomes, ocean);
    }

    public static Layer makeLayers(long seed, HolderGetter<Biome> registry) {
        AreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaContext(25, seed, contextSeed), registry);
        return new Layer(areaFactory);
    }
}
