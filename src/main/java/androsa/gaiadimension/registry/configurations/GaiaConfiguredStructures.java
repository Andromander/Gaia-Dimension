package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Map;

public final class GaiaConfiguredStructures extends GaiaBiomeFeatures {

    //ConfiguredStructureFeatures
    public static final ResourceKey<ConfiguredStructureFeature<?, ?>> MINI_TOWER = createKey("mini_tower");
    public static final ResourceKey<ConfiguredStructureFeature<?, ?>> MALACHITE_WATCHTOWER = createKey("malachite_watchtower");

    public static final Holder<ConfiguredStructureFeature<?, ?>> mini_tower = registerStructureFeature(MINI_TOWER, ModWorldgen.MINI_TOWER.get().configured(NoneFeatureConfiguration.INSTANCE, GaiaTags.Biomes.HAS_MINI_TOWER));
    public static final Holder<ConfiguredStructureFeature<?, ?>> malachite_watchtower = registerStructureFeature(MALACHITE_WATCHTOWER, ModWorldgen.MALACHITE_WATCHTOWER.get().configured(NoneFeatureConfiguration.INSTANCE, GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER));

    private static ResourceKey<ConfiguredStructureFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }
    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> Holder<ConfiguredStructureFeature<?, ?>> registerStructureFeature(ResourceKey<ConfiguredStructureFeature<?, ?>> name, ConfiguredStructureFeature<FC, F> structure) {
        RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.put(structure, name.location().getPath());
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name, structure);
    }

    public static void registerStructureFeatures(Registry<ConfiguredStructureFeature<?,?>> registry) {
        for (Map.Entry<ConfiguredStructureFeature<?,?>, String> entry : RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
