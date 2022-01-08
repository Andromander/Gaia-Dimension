package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Map;

public final class GaiaConfiguredStructures extends GaiaBiomeFeatures {

    //StructureFeatures
    public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> mini_tower = registerStructureFeature("mini_tower", ModWorldgen.MINI_TOWER.get().configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredStructureFeature<NoneFeatureConfiguration, ? extends StructureFeature<NoneFeatureConfiguration>> malachite_watchtower = registerStructureFeature("malachite_watchtower", ModWorldgen.MALACHITE_WATCHTOWER.get().configured(NoneFeatureConfiguration.INSTANCE));

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> registerStructureFeature(String name, ConfiguredStructureFeature<FC, F> structure) {
        RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.put(structure, name);
        return structure;
    }

    public static void registerStructureFeatures(Registry<ConfiguredStructureFeature<?,?>> registry) {
        for (Map.Entry<ConfiguredStructureFeature<?,?>, String> entry : RegistryHelper.CONFIGURED_STRUCTURE_FEATURES.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }
}
