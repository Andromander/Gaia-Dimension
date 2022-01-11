package androsa.gaiadimension.world;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class GaiaBiomeProvider extends BiomeSource {

//    public static final Codec<GaiaBiomeProvider> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
//            Codec.LONG.fieldOf("seed")
//                    .orElse(GaiaChunkGenerator.hackSeed)
//                    .forGetter((obj) -> obj.seed),
//            RegistryLookupCodec.create(Registry.BIOME_REGISTRY)
//                    .forGetter((obj) -> obj.registry)
//    ).apply(instance, instance.stable(GaiaBiomeProvider::new)));

    public static final MapCodec<GaiaBiomeProvider> DIRECT_CODEC = RecordCodecBuilder.mapCodec((codec) -> codec.group(
            ExtraCodecs.nonEmptyList(RecordCodecBuilder.<Pair<Climate.ParameterPoint, Supplier<Biome>>>create((extra) -> extra.group(
                    Climate.ParameterPoint.CODEC.fieldOf("parameters").forGetter(Pair::getFirst),
                    Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)
            ).apply(extra, Pair::of)).listOf()).xmap(Climate.ParameterList::new, Climate.ParameterList::values).fieldOf("biomes")
                    .forGetter((source) -> source.biomes)
    ).apply(codec, GaiaBiomeProvider::new));
    public static final Codec<GaiaBiomeProvider> CODEC = Codec.mapEither(PresetInstance.CODEC, DIRECT_CODEC).xmap(
            (either) -> either.map(PresetInstance::biomeSource, Function.identity()),
            (provider) -> provider.getInstance().map(Either::<PresetInstance, GaiaBiomeProvider>left)
                    .orElseGet(() -> Either.right(provider))
    ).codec();

    private final Climate.ParameterList<Supplier<Biome>> biomes;
    private final Optional<PresetInstance> instance;

    private GaiaBiomeProvider(Climate.ParameterList<Supplier<Biome>> list) {
        this(list, Optional.empty());
    }

    public GaiaBiomeProvider(Climate.ParameterList<Supplier<Biome>> list, Optional<PresetInstance> instance) {
//        super(biomes.stream().map(define -> () -> registry.getOrThrow(define)));
        super(list.values().stream().map(Pair::getSecond));
        this.biomes = list;
        this.instance = instance;
//        this.genBiomes = GaiaLayerUtil.makeLayers(seed, registry);

//        getBiomesToSpawnIn().clear();
//        getBiomesToSpawnIn().add(ModBiomes.pink_agate_forest.get());
//        getBiomesToSpawnIn().add(ModBiomes.blue_agate_taiga.get());
//        getBiomesToSpawnIn().add(ModBiomes.green_agate_jungle.get());
//        getBiomesToSpawnIn().add(ModBiomes.purple_agate_swamp.get());
//        getBiomesToSpawnIn().add(ModBiomes.crystal_plains.get());
    }

    @Override
    public BiomeSource withSeed(long s) {
        return this;
    }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    public Optional<PresetInstance> getInstance() {
        return instance;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        return this.getBiomeFromPos(sampler.sample(x, y, z));
    }

    public Biome getBiomeFromPos(Climate.TargetPoint target) {
        return biomes.findValue(target, OverworldBiomes::theVoid).get();
    }

    public static class Preset {
        static final Map<ResourceLocation, Preset> BY_NAME = Maps.newHashMap();
        public static final Preset OVERWORLD = new Preset(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), (p_187108_) -> {
            ImmutableList.Builder<Pair<Climate.ParameterPoint, Supplier<Biome>>> builder = ImmutableList.builder();
            (new GaiaBiomeBuilder()).addBiomes((biome) -> { //TODO
                builder.add(biome.mapSecond((name) -> () -> p_187108_.getOrThrow(name)));
            });
            return new Climate.ParameterList<>(builder.build());
        });
        final ResourceLocation name;
        private final Function<Registry<Biome>, Climate.ParameterList<Supplier<Biome>>> parameterSource;

        public Preset(ResourceLocation name, Function<Registry<Biome>, Climate.ParameterList<Supplier<Biome>>> biomes) {
            this.name = name;
            this.parameterSource = biomes;
            BY_NAME.put(name, this);
        }

        GaiaBiomeProvider biomeSource(PresetInstance instance, boolean hasInstance) {
            Climate.ParameterList<Supplier<Biome>> parameterlist = this.parameterSource.apply(instance.biomes());
            return new GaiaBiomeProvider(parameterlist, hasInstance ? Optional.of(instance) : Optional.empty());
        }

        public GaiaBiomeProvider biomeSource(Registry<Biome> registry, boolean hasInstance) {
            return this.biomeSource(new PresetInstance(this, registry), hasInstance);
        }

        public GaiaBiomeProvider biomeSource(Registry<Biome> registry) {
            return this.biomeSource(registry, true);
        }
    }

    static record PresetInstance(Preset preset, Registry<Biome> biomes) {
        public static final MapCodec<PresetInstance> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                ResourceLocation.CODEC.flatXmap(
                        (rl) -> Optional.ofNullable(Preset.BY_NAME.get(rl))
                                .map(DataResult::success)
                                .orElseGet(() -> DataResult.error("Unknown preset: " + rl)),
                        (preset) -> DataResult.success(preset.name))
                        .fieldOf("preset")
                        .stable()
                        .forGetter(PresetInstance::preset),
                RegistryLookupCodec.create(Registry.BIOME_REGISTRY)
                        .forGetter(PresetInstance::biomes))
                .apply(instance, instance.stable(PresetInstance::new)));

        public GaiaBiomeProvider biomeSource() {
            return this.preset.biomeSource(this, true);
        }
    }
}
