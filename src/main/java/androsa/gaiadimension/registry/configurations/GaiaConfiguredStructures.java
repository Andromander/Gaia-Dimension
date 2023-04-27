package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModWorldgen;
import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.Map;
import java.util.function.Function;

public final class GaiaConfiguredStructures extends GaiaBiomeFeatures {

    public static final Map<ResourceLocation, StructureSet> STRUCTURE_SETS = Maps.newHashMap();

    public static final Holder<ConfiguredStructureFeature<?, ?>> mini_tower = registerStructureFeature(ModWorldgen.MINI_TOWER.get(), structure -> structure.configured(NoneFeatureConfiguration.INSTANCE, GaiaTags.Biomes.HAS_MINI_TOWER), 30, 10, 420);
    public static final Holder<ConfiguredStructureFeature<?, ?>> malachite_watchtower = registerStructureFeature(ModWorldgen.MALACHITE_WATCHTOWER.get(), structure -> structure.configured(NoneFeatureConfiguration.INSTANCE, GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER), 35, 15, 621);

    private static <FC extends FeatureConfiguration> Holder<ConfiguredStructureFeature<?, ?>> registerStructureFeature(StructureFeature<FC> feature, Function<StructureFeature<FC>, ConfiguredStructureFeature<?, ?>> config, int spacing, int separation, int salt) {
        ResourceLocation name = new ResourceLocation(GaiaDimensionMod.MODID, feature.getRegistryName().getPath());
        Holder<ConfiguredStructureFeature<?, ?>> holder = BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name.toString(), config.apply(feature));
        STRUCTURE_SETS.put(name, new StructureSet(holder, new RandomSpreadStructurePlacement(spacing, separation, RandomSpreadType.LINEAR, salt)));
        return holder;
    }
}
