package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.world.GaiaGenerationSettings;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.VoroniZoomLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

import java.util.function.LongFunction;

public class GaiaLayerUtil {

    protected static final int FOREST = Registry.BIOME.getId(ModBiomes.pink_agate_forest);

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> ImmutableList<IAreaFactory<T>> makeLayers(WorldType worldTypeIn, GaiaGenerationSettings settings, LongFunction<C> contextFactory) {
        //IAreaFactory<T> biomes = worldTypeIn.getBiomeLayer(iareafactory, settings, contextFactory);
        IAreaFactory<T> factory = GaiaBaseLayer.INSTANCE.apply(contextFactory.apply(1L));
        IAreaFactory<T> biomes = new GaiaBiomesLayer().apply(contextFactory.apply(1L), factory);
        //Layer biomes = new GenLayerGDBiomes();

        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1000L), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1001), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1004), biomes);
        biomes = ZoomLayer.NORMAL.apply(contextFactory.apply(1005), biomes);

        //biomes = GenLayerZoom.magnify(1000L, biomes, 1);

        IAreaFactory<T> riverLayer = MineralRiverLayer.INSTANCE.apply(contextFactory.apply(1L), biomes);
        riverLayer = SmoothLayer.INSTANCE.apply(contextFactory.apply(7000L), riverLayer);
        biomes = MineralRiverMixLayer.INSTANCE.apply(contextFactory.apply(100L), biomes, riverLayer);

        IAreaFactory<T> genlayervoronoizoom = VoroniZoomLayer.INSTANCE.apply(contextFactory.apply(10L), biomes);
        return ImmutableList.of(biomes, genlayervoronoizoom, biomes);
    }

    public static Layer[] makeLayers(long seed, WorldType typeIn, GaiaGenerationSettings settings) {
        ImmutableList<IAreaFactory<LazyArea>> immutablelist = makeLayers(typeIn, settings, (p_215737_2_) -> new LazyAreaLayerContext(25, seed, p_215737_2_));
        Layer layer = new Layer(immutablelist.get(0));
        Layer layer1 = new Layer(immutablelist.get(1));
        Layer layer2 = new Layer(immutablelist.get(2));
        return new Layer[]{layer, layer1, layer2};
    }
}
