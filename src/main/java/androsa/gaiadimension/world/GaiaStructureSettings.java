package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredStructures;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

public class GaiaStructureSettings extends StructureSettings {

    public GaiaStructureSettings(Map<StructureFeature<?>, StructureFeatureConfiguration> structures) {
        super(Optional.empty(), structures);

        HashMap<StructureFeature<?>, ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> hashmap = new HashMap<>();
        registerStructures((feature, biome) -> hashmap.computeIfAbsent(feature.feature, (p_189374_) -> ImmutableMultimap.builder()).put(feature, biome));
    }

    private static void registerStructures(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biconsumer) {
        Set<ResourceKey<Biome>> minitowerbiomes = ImmutableSet.of(ModBiomes.pink_agate_forest, ModBiomes.blue_agate_taiga, ModBiomes.green_agate_jungle, ModBiomes.purple_agate_swamp, ModBiomes.fossil_woodland, ModBiomes.mutant_agate_wildwood, ModBiomes.crystal_plains);
        Set<ResourceKey<Biome>> malachitetowerbiomes = ImmutableSet.of(ModBiomes.pink_agate_forest, ModBiomes.green_agate_jungle, ModBiomes.crystal_plains);

        register(biconsumer, GaiaConfiguredStructures.mini_tower, minitowerbiomes);
        register(biconsumer, GaiaConfiguredStructures.malachite_watchtower, malachitetowerbiomes);
    }

    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biconsumer, ConfiguredStructureFeature<?, ?> feature, Set<ResourceKey<Biome>> biomes) {
        biomes.forEach((biome) -> biconsumer.accept(feature, biome));
    }
}
