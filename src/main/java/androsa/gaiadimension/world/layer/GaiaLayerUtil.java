package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLModIdMappingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class GaiaLayerUtil {

    private static final List<LazyInt> CACHES = new ArrayList<>();

    protected static final LazyInt RIVER = lazyId(ModBiomes.mineral_river);
    protected static final LazyInt RESERVOIR = lazyId(ModBiomes.mineral_reservoir);
    protected static final LazyInt PINK_FOREST = lazyId(ModBiomes.pink_agate_forest);
    protected static final LazyInt BLUE_FOREST = lazyId(ModBiomes.blue_agate_taiga);
    protected static final LazyInt GREEN_FOREST = lazyId(ModBiomes.green_agate_jungle);
    protected static final LazyInt PURPLE_FOREST = lazyId(ModBiomes.purple_agate_swamp);
    protected static final LazyInt PLAINS = lazyId(ModBiomes.crystal_plains);
    protected static final LazyInt DUNES = lazyId(ModBiomes.salt_dunes);
    protected static final LazyInt WILDWOOD = lazyId(ModBiomes.mutant_agate_wildwood);
    protected static final LazyInt BOG = lazyId(ModBiomes.smoldering_bog);
    protected static final LazyInt STATIC = lazyId(ModBiomes.static_wasteland);
    protected static final LazyInt VOLCANIC = lazyId(ModBiomes.volcanic_lands);

    static LazyInt lazyId(RegistryKey<Biome> key) {
        Biome biome = WorldGenRegistries.BIOME.getValueForKey(key);
        LazyInt lazyInt = new LazyInt(() -> WorldGenRegistries.BIOME.getId(biome));
        CACHES.add(lazyInt);
        return lazyInt;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> contextFactory) {
        IAreaFactory<T> biomes = new GaiaBiomesLayer().apply(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);

        biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        IAreaFactory<T> riverLayer = MineralRiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        biomes = MineralRiverMixLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        return biomes;
    }

    public static Layer makeLayers(long seed) {
        IAreaFactory<LazyArea> areaFactory = makeLayers((contextSeed) -> new LazyAreaLayerContext(25, seed, contextSeed));
        return new Layer(areaFactory);
    }

    @SubscribeEvent
    public static void onModIdMapped(FMLModIdMappingEvent e) {
        CACHES.forEach(LazyInt::invalidate);
    }
}
