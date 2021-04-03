package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.ConfiguredFeaturesProvider;
import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import com.google.common.collect.ImmutableMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Map;

public class GaiaConfiguredFeatures extends ConfiguredFeaturesProvider {

    public GaiaConfiguredFeatures(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected Map<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> registerFeatures() {
        final ImmutableMap.Builder<RegistryKey<ConfiguredFeature<?, ?>>, ConfiguredFeature<?, ?>> features = ImmutableMap.builder();

        features.put(getKey("lake_superhot_magma_common"), GaiaBiomeFeatures.lake_superhot_magma_common);
        features.put(getKey("lake_superhot_magma_rare"), GaiaBiomeFeatures.lake_superhot_magma_rare);
        features.put(getKey("lake_mineral_water_common"), GaiaBiomeFeatures.lake_mineral_water_common);
        features.put(getKey("lake_mineral_water_uncommon"), GaiaBiomeFeatures.lake_mineral_water_uncommon);
        features.put(getKey("lake_mineral_water_rare"), GaiaBiomeFeatures.lake_mineral_water_rare);
        features.put(getKey("lake_sweet_muck"), GaiaBiomeFeatures.lake_sweet_muck);
        features.put(getKey("lake_liquid_aura"), GaiaBiomeFeatures.lake_liquid_aura);
        features.put(getKey("lake_liquid_bismuth"), GaiaBiomeFeatures.lake_liquid_bismuth);

        features.put(getKey("gummy_glitter_blob"), GaiaBiomeFeatures.gummy_glitter_blob);
        features.put(getKey("static_spikes"), GaiaBiomeFeatures.static_spikes);
        features.put(getKey("bismuth_spires"), GaiaBiomeFeatures.bismuth_spires);
        features.put(getKey("bismuth_geysers"), GaiaBiomeFeatures.bismuth_geysers);

        features.put(getKey("ore_primal_mass"), GaiaBiomeFeatures.ore_primal_mass);
        features.put(getKey("ore_thick_glitter"), GaiaBiomeFeatures.ore_thick_glitter);
        features.put(getKey("ore_searing_rock"), GaiaBiomeFeatures.ore_searing_rock);
        features.put(getKey("ore_static_stone"), GaiaBiomeFeatures.ore_static_stone);
        features.put(getKey("ore_pebbles"), GaiaBiomeFeatures.ore_pebbles);
        features.put(getKey("ore_speckled_rock"), GaiaBiomeFeatures.ore_speckled_rock);
        features.put(getKey("ore_coarse_rock"), GaiaBiomeFeatures.ore_coarse_rock);
        features.put(getKey("ore_precious_rock"), GaiaBiomeFeatures.ore_precious_rock);
        features.put(getKey("ore_raw_amethyst"), GaiaBiomeFeatures.ore_raw_amethyst);
        features.put(getKey("ore_raw_copal"), GaiaBiomeFeatures.ore_raw_copal);
        features.put(getKey("ore_raw_jade"), GaiaBiomeFeatures.ore_raw_jade);
        features.put(getKey("ore_raw_jet"), GaiaBiomeFeatures.ore_raw_jet);
        features.put(getKey("ore_sugilite"), GaiaBiomeFeatures.ore_sugilite);
        features.put(getKey("ore_hematite"), GaiaBiomeFeatures.ore_hematite);
        features.put(getKey("ore_pyrite"), GaiaBiomeFeatures.ore_pyrite);
        features.put(getKey("ore_cinnabar"), GaiaBiomeFeatures.ore_cinnabar);
        features.put(getKey("ore_labradorite"), GaiaBiomeFeatures.ore_labradorite);
        features.put(getKey("ore_moonstone"), GaiaBiomeFeatures.ore_moonstone);
        features.put(getKey("ore_red_opal"), GaiaBiomeFeatures.ore_red_opal);
        features.put(getKey("ore_blue_opal"), GaiaBiomeFeatures.ore_blue_opal);
        features.put(getKey("ore_green_opal"), GaiaBiomeFeatures.ore_green_opal);
        features.put(getKey("ore_white_opal_common"), GaiaBiomeFeatures.ore_white_opal_common);
        features.put(getKey("ore_white_opal_rare"), GaiaBiomeFeatures.ore_white_opal_rare);
        features.put(getKey("disk_static_stone"), GaiaBiomeFeatures.disk_static_stone);
        features.put(getKey("disk_bog_patch"), GaiaBiomeFeatures.disk_bog_patch);

        features.put(getKey("underground_glitter_blob"), GaiaBiomeFeatures.underground_glitter_blob);
        features.put(getKey("crystal_fungi_caves"), GaiaBiomeFeatures.crystal_fungi_caves);

        features.put(getKey("pink_agate_tree_common"), GaiaBiomeFeatures.pink_agate_tree_common);
        features.put(getKey("pink_agate_tree_rare"), GaiaBiomeFeatures.pink_agate_tree_rare);
        features.put(getKey("blue_agate_tree"), GaiaBiomeFeatures.blue_agate_tree);
        features.put(getKey("green_agate_tree"), GaiaBiomeFeatures.green_agate_tree);
        features.put(getKey("green_agate_bush"), GaiaBiomeFeatures.green_agate_bush);
        features.put(getKey("purple_agate_tree"), GaiaBiomeFeatures.purple_agate_tree);
        features.put(getKey("various_agate_trees"), GaiaBiomeFeatures.various_agate_trees);
        features.put(getKey("fossilized_tree"), GaiaBiomeFeatures.fossilized_tree);
        features.put(getKey("goldstone_tree"), GaiaBiomeFeatures.goldstone_tree);
        features.put(getKey("burnt_agate_tree"), GaiaBiomeFeatures.burnt_agate_tree);
        features.put(getKey("fiery_agate_tree"), GaiaBiomeFeatures.fiery_agate_tree);
        features.put(getKey("aura_tree"), GaiaBiomeFeatures.aura_tree);
        features.put(getKey("aura_shoots"), GaiaBiomeFeatures.aura_shoots);
        features.put(getKey("crystal_growth_02"), GaiaBiomeFeatures.crystal_growth_02);
        features.put(getKey("crystal_growth_03"), GaiaBiomeFeatures.crystal_growth_03);
        features.put(getKey("crystal_growth_04"), GaiaBiomeFeatures.crystal_growth_04);
        features.put(getKey("crystal_growth_05"), GaiaBiomeFeatures.crystal_growth_05);
        features.put(getKey("crystal_growth_seared"), GaiaBiomeFeatures.crystal_growth_seared);
        features.put(getKey("crystal_growth_corrupt"), GaiaBiomeFeatures.crystal_growth_corrupt);
        features.put(getKey("crystal_growth_aura"), GaiaBiomeFeatures.crystal_growth_aura);
        features.put(getKey("crystal_blooms_common"), GaiaBiomeFeatures.crystal_blooms_common);
        features.put(getKey("crystal_blooms_rare"), GaiaBiomeFeatures.crystal_blooms_rare);
        features.put(getKey("crystal_blooms_mutant"), GaiaBiomeFeatures.crystal_blooms_mutant);
        features.put(getKey("crystal_blooms_corrupt"), GaiaBiomeFeatures.crystal_blooms_corrupt);
        features.put(getKey("spotted_kersei"), GaiaBiomeFeatures.spotted_kersei);
        features.put(getKey("thorny_wiltha"), GaiaBiomeFeatures.thorny_wiltha);
        features.put(getKey("roofed_agaric"), GaiaBiomeFeatures.roofed_agaric);
        features.put(getKey("bulbous_hobina"), GaiaBiomeFeatures.bulbous_hobina);
        features.put(getKey("stickly_cupsir"), GaiaBiomeFeatures.stickly_cupsir);
        features.put(getKey("mystical_murgni"), GaiaBiomeFeatures.mystical_murgni);
        features.put(getKey("corrupted_gaia_eye"), GaiaBiomeFeatures.corrupted_gaia_eye);

        return features.build();
    }

    private static RegistryKey<ConfiguredFeature<?,?>> getKey(String name) {
        return RegistryKey.create(WorldGenRegistries.CONFIGURED_FEATURE.key(), new ResourceLocation(GaiaDimensionMod.MODID, name));
    }
}
