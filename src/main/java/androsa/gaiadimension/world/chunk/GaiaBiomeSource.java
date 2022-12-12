package androsa.gaiadimension.world.chunk;

import androsa.gaiadimension.world.chunk.warp.TerrainPoint;
import androsa.gaiadimension.world.layer.GaiaLayerUtil;
import androsa.gaiadimension.world.layer.util.Layer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.function.Function;

public class GaiaBiomeSource extends BiomeSource {

    public static final Codec<GaiaBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            RecordCodecBuilder.<Pair<TerrainPoint, Holder<Biome>>>create((pair) -> pair.group(
                    TerrainPoint.CODEC.fieldOf("parameters").forGetter(Pair::getFirst),
                    Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)
            ).apply(pair, Pair::of)).listOf().fieldOf("biomes").forGetter((object) -> object.list),
            Codec.LONG.fieldOf("seed").stable().orElseGet(() -> GaiaBiomeSource.hackseed).forGetter((object) -> object.seed),
            Codec.FLOAT.fieldOf("base_offset").forGetter((object) -> object.offset),
            Codec.FLOAT.fieldOf("base_factor").forGetter((object) -> object.factor),
            RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY)
                    .forGetter((obj) -> obj.registry)
    ).apply(instance, GaiaBiomeSource::new));

    public static long hackseed; //DON'T TOUCH ME
    private final long seed;
    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private final float offset;
    private final float factor;
    private final List<Pair<TerrainPoint, Holder<Biome>>> list;

    public GaiaBiomeSource(List<Pair<TerrainPoint, Holder<Biome>>> list, long seed, float offset, float factor, Registry<Biome> registry) {
        super(list.stream().map(Pair::getSecond));
        this.seed = seed;
        this.registry = registry;
        this.genBiomes = GaiaLayerUtil.makeLayers(seed, registry);
        this.list = list;
        this.offset = offset;
        this.factor = factor;

//        getBiomesToSpawnIn().clear();
//        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
//        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
//        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
//        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
//        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public BiomeSource withSeed(long s) {
        return new GaiaBiomeSource(list, s, offset, factor, registry);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        return this.genBiomes.get(registry, x, z);
    }

    public float getBaseOffset() {
        return this.offset;
    }

    public float getBaseFactor() {
        return this.factor;
    }

    public float getBiomeDepth(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = this.getNoiseBiome(x, y, z, sampler).value();
        return this.getBiomeDepth(biome);
    }

    public float getBiomeDepth(Biome biome) {
        return this.getBiomeValue(biome, TerrainPoint::depth);
    }

    public float getBiomeScale(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = this.getNoiseBiome(x, y, z, sampler).value();
        return this.getBiomeScale(biome);
    }

    public float getBiomeScale(Biome biome) {
        return getBiomeValue(biome, TerrainPoint::scale);
    }

    private float getBiomeValue(Biome biome, Function<? super TerrainPoint, Float> function) {
        return this.list.stream().filter(p -> p.getSecond().value().equals(biome)).map(Pair::getFirst).map(function).findFirst().orElse(0.0F);
    }
}
