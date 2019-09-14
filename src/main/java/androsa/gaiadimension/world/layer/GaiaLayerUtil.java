package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class GaiaLayerUtil {

    protected static final int RIVER = Registry.BIOME.getId(ModBiomes.mineral_river);
    protected static final int RESERVOIR = Registry.BIOME.getId(ModBiomes.mineral_reservoir);
    protected static final int FOREST = Registry.BIOME.getId(ModBiomes.pink_agate_forest);

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> makeLayers(LongFunction<C> contextFactory) {
        //IAreaFactory<T> factory = GaiaBaseLayer.INSTANCE.apply(contextFactory.apply(1L));
        IAreaFactory<T> biomes = new GaiaBiomesLayer().apply(contextFactory.apply(1L));

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);

        biomes = LayerUtil.repeat(1000L, ZoomLayer.NORMAL, biomes, 1, contextFactory);

        //IAreaFactory<T> riverLayer = MineralRiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        //riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        //biomes = MineralRiverMixLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        IAreaFactory<T> genlayervoronoizoom = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), biomes);
        return ImmutableList.of(biomes, genlayervoronoizoom, biomes);
    }

    public static Layer[] makeLayers(long seed) {
        ImmutableList<IAreaFactory<LazyArea>> immutablelist = makeLayers((context) -> new LazyAreaLayerContext(25, seed, context));
        Layer layer = new Layer(immutablelist.get(0));
        Layer layer1 = new Layer(immutablelist.get(1));
        Layer layer2 = new Layer(immutablelist.get(2));
        return new Layer[]{layer, layer1, layer2};
    }
}
