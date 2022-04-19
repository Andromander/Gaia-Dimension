package androsa.gaiadimension.world;

import androsa.gaiadimension.world.biomegen.TerrainPoint;
import androsa.gaiadimension.world.layer.GaiaLayerUtil;
import androsa.gaiadimension.world.layer.oldgen.Layer;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static androsa.gaiadimension.registry.ModBiomes.*;

public class GaiaBiomeSource extends BiomeSource {

    public static final Codec<GaiaBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            RecordCodecBuilder.<Pair<TerrainPoint, Holder<Biome>>>create((pair) -> pair.group(
                    TerrainPoint.CODEC.fieldOf("parameters").forGetter(Pair::getFirst),
                    Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)
            ).apply(pair, Pair::of)).listOf().fieldOf("biomes").forGetter((object) -> object.list),
            Codec.LONG.fieldOf("seed").forGetter((object) -> object.seed),
            RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY)
                    .forGetter((obj) -> obj.registry)
    ).apply(instance, GaiaBiomeSource::new));

    private final long seed;
    private final Registry<Biome> registry;
    private final Layer genBiomes;
    private final List<Pair<TerrainPoint, Holder<Biome>>> list;
    private static final List<ResourceKey<Biome>> biomes = ImmutableList.of(
            pink_agate_forest,
            blue_agate_taiga,
            green_agate_jungle,
            purple_agate_swamp,
            fossil_woodland,
            mutant_agate_wildwood,
            igneous_plains,
            volcanic_lands,
            wasteland_hills,
            static_wasteland,
            weirded_goldstone_lands,
            goldstone_lands,
            crystal_plains,
            salt_dunes,
            crystal_salt_dunes,
            mookaite_mesa,
            shining_grove,
            smoldering_bog,
            hotspot,
            prismatic_steppe,
            mineral_reservoir,
            aquamarine_trench,
            salty_coast,
            tourmaline_coast,
            mineral_river,
            golden_forest,
            golden_plains,
            golden_hills,
            golden_sands,
            golden_marsh);

    public GaiaBiomeSource(List<Pair<TerrainPoint, Holder<Biome>>> list, long seed, Registry<Biome> registry) {
//        super(biomes.stream().map(define -> () -> registry.getOrThrow(define)));
        super(biomes
                .stream()
                .map(ResourceKey::location)
                .map(registry::getOptional)
                .filter(Optional::isPresent)
                .map(opt -> Holder.direct(opt.get())));
        this.seed = seed;
        this.registry = registry;
        this.genBiomes = GaiaLayerUtil.makeLayers(seed, registry);
        this.list = list;

//        getBiomesToSpawnIn().clear();
//        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
//        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
//        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
//        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
//        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public BiomeSource withSeed(long s) {
        return new GaiaBiomeSource(list, s, registry);
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        return this.genBiomes.get(registry, x, z);
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
