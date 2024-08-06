package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaAdvancementProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.bootstrap.GaiaDimensions;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModStructures;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GaiaAdvancements extends GaiaAdvancementProvider {

    public GaiaAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, helper, List.of(new Advancements()));
    }

    public static class Advancements implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper fileHelper) {
            //Boolean 1 = show Toast
            //Boolean 2 = announce to chat
            //Boolean 3 = hide

            //Enter Gaia
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(ModBlocks.keystone_block.get(), title("root"), description("root"), ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/block/gaia_stone.png"), AdvancementType.TASK, true, true, false)
                    .addCriterion("entered_gaia", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(GaiaDimensions.gaia_world))
                    .save(consumer, loc("root"));
            //Collect a Gemstone
            AdvancementHolder gemstone1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.sugilite.get(), title("collect_gemstone"), description("collect_gemstone"), null, AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_sugilite", item(ModItems.sugilite))
                    .addCriterion("get_hematite", item(ModItems.hematite))
                    .addCriterion("get_cinnabar", item(ModItems.cinnabar))
                    .addCriterion("get_labradorite", item(ModItems.labradorite))
                    .addCriterion("get_moonstone", item(ModItems.moonstone))
                    .addCriterion("get_red_opal", item(ModItems.red_opal))
                    .addCriterion("get_blue_opal", item(ModItems.blue_opal))
                    .addCriterion("get_green_opal", item(ModItems.green_opal))
                    .addCriterion("get_white_opal", item(ModItems.white_opal))
                    .addCriterion("get_stibnite", item(ModItems.stibnite))
                    .addCriterion("get_proustite", item(ModItems.proustite))
                    .addCriterion("get_euclase", item(ModItems.euclase))
                    .addCriterion("get_albite", item(ModItems.albite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_goshenite", item(ModItems.goshenite))
                    .addCriterion("get_pyrite", item(ModItems.pyrite))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .addCriterion("get_goldstone", item(ModItems.goldstone))
                    .addCriterion("get_aura_cluster", item(ModItems.aura_cluster))
                    .addCriterion("get_bismuth_crystal", item(ModItems.bismuth_crystal))
                    .addCriterion("get_opalite", item(ModItems.opalite))
                    .addCriterion("get_celestine", item(ModItems.celestine))
                    .save(consumer, loc("collect_gemstone"));
            //Restructure a Gemstone
            AdvancementHolder gemstone2 = Advancement.Builder.advancement()
                    .parent(gemstone1)
                    .display(ModItems.stibnite.get(), title("restructure_gemstone"), description("restructure_gemstone"), null, AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("get_stibnite", item(ModItems.stibnite))
                    .addCriterion("get_proustite", item(ModItems.proustite))
                    .addCriterion("get_euclase", item(ModItems.euclase))
                    .addCriterion("get_albite", item(ModItems.albite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_goshenite", item(ModItems.goshenite))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .save(consumer, loc("restructure_gemstone"));
            //Collect all mineable Gemstones
            AdvancementHolder gemstone3 = Advancement.Builder.advancement()
                    .parent(gemstone2)
                    .display(ModItems.white_opal.get(), title("mine_all_gemstones"), description("mine_all_gemstones"), null, AdvancementType.TASK, true, true, false)
                    .addCriterion("get_sugilite", item(ModItems.sugilite))
                    .addCriterion("get_hematite", item(ModItems.hematite))
                    .addCriterion("get_cinnabar", item(ModItems.cinnabar))
                    .addCriterion("get_labradorite", item(ModItems.labradorite))
                    .addCriterion("get_moonstone", item(ModItems.moonstone))
                    .addCriterion("get_red_opal", item(ModItems.red_opal))
                    .addCriterion("get_blue_opal", item(ModItems.blue_opal))
                    .addCriterion("get_green_opal", item(ModItems.green_opal))
                    .addCriterion("get_white_opal", item(ModItems.white_opal))
                    .addCriterion("get_celestine", item(ModItems.celestine))
                    .save(consumer, loc("mine_all_gemstones"));
            //Collect all Gemstones
            Advancement.Builder.advancement()
                    .parent(gemstone3)
                    .display(ModItems.goshenite.get(), title("get_all_gemstones"), description("get_all_gemstones"), null, AdvancementType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(250))
                    .addCriterion("get_sugilite", item(ModItems.sugilite))
                    .addCriterion("get_hematite", item(ModItems.hematite))
                    .addCriterion("get_cinnabar", item(ModItems.cinnabar))
                    .addCriterion("get_labradorite", item(ModItems.labradorite))
                    .addCriterion("get_moonstone", item(ModItems.moonstone))
                    .addCriterion("get_red_opal", item(ModItems.red_opal))
                    .addCriterion("get_blue_opal", item(ModItems.blue_opal))
                    .addCriterion("get_green_opal", item(ModItems.green_opal))
                    .addCriterion("get_white_opal", item(ModItems.white_opal))
                    .addCriterion("get_stibnite", item(ModItems.stibnite))
                    .addCriterion("get_proustite", item(ModItems.proustite))
                    .addCriterion("get_euclase", item(ModItems.euclase))
                    .addCriterion("get_albite", item(ModItems.albite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_goshenite", item(ModItems.goshenite))
                    .addCriterion("get_pyrite", item(ModItems.pyrite))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .addCriterion("get_goldstone", item(ModItems.goldstone))
                    .addCriterion("get_aura_cluster", item(ModItems.aura_cluster))
                    .addCriterion("get_bismuth_crystal", item(ModItems.bismuth_crystal))
                    .addCriterion("get_opalite", item(ModItems.opalite))
                    .addCriterion("get_celestine", item(ModItems.celestine))
                    .save(consumer, loc("get_all_gemstones"));
            //Craft a weapon from Gaia
            AdvancementHolder tool1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.agate_sword.get(), title("get_tool"), description("get_tool"), null, AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("agate_sword", item(ModItems.agate_sword))
                    .addCriterion("agate_pickaxe", item(ModItems.agate_pickaxe))
                    .addCriterion("agate_axe", item(ModItems.agate_axe))
                    .addCriterion("agate_shovel", item(ModItems.agate_shovel))
                    .addCriterion("sugilite_sword", item(ModItems.sugilite_sword))
                    .addCriterion("sugilite_pickaxe", item(ModItems.sugilite_pickaxe))
                    .addCriterion("sugilite_axe", item(ModItems.sugilite_axe))
                    .addCriterion("sugilite_shovel", item(ModItems.sugilite_shovel))
                    .addCriterion("stibnite_sword", item(ModItems.stibnite_sword))
                    .addCriterion("stibnite_pickaxe", item(ModItems.stibnite_pickaxe))
                    .addCriterion("stibnite_axe", item(ModItems.stibnite_axe))
                    .addCriterion("stibnite_shovel", item(ModItems.stibnite_shovel))
                    .addCriterion("euclase_sword", item(ModItems.euclase_sword))
                    .addCriterion("euclase_pickaxe", item(ModItems.euclase_pickaxe))
                    .addCriterion("euclase_axe", item(ModItems.euclase_axe))
                    .addCriterion("euclase_shovel", item(ModItems.euclase_shovel))
                    .addCriterion("carnelian_sword", item(ModItems.carnelian_sword))
                    .addCriterion("carnelian_pickaxe", item(ModItems.carnelian_pickaxe))
                    .addCriterion("carnelian_axe", item(ModItems.carnelian_axe))
                    .addCriterion("carnelian_shovel", item(ModItems.carnelian_shovel))
                    .addCriterion("benitoite_sword", item(ModItems.benitoite_sword))
                    .addCriterion("benitoite_pickaxe", item(ModItems.benitoite_pickaxe))
                    .addCriterion("benitoite_axe", item(ModItems.benitoite_axe))
                    .addCriterion("benitoite_shovel", item(ModItems.benitoite_shovel))
                    .addCriterion("goshenite_sword", item(ModItems.goshenite_sword))
                    .addCriterion("goshenite_pickaxe", item(ModItems.goshenite_pickaxe))
                    .addCriterion("goshenite_axe", item(ModItems.goshenite_axe))
                    .addCriterion("goshenite_shovel", item(ModItems.goshenite_shovel))
                    .save(consumer, loc("get_tool"));
            //Craft all weapons from Gaia
            Advancement.Builder.advancement()
                    .parent(tool1)
                    .display(ModItems.goshenite_sword.get(), title("get_all_tools"), description("get_all_tools"), null, AdvancementType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(500))
                    .addCriterion("agate_sword", item(ModItems.agate_sword))
                    .addCriterion("agate_pickaxe", item(ModItems.agate_pickaxe))
                    .addCriterion("agate_axe", item(ModItems.agate_axe))
                    .addCriterion("agate_shovel", item(ModItems.agate_shovel))
                    .addCriterion("sugilite_sword", item(ModItems.sugilite_sword))
                    .addCriterion("sugilite_pickaxe", item(ModItems.sugilite_pickaxe))
                    .addCriterion("sugilite_axe", item(ModItems.sugilite_axe))
                    .addCriterion("sugilite_shovel", item(ModItems.sugilite_shovel))
                    .addCriterion("stibnite_sword", item(ModItems.stibnite_sword))
                    .addCriterion("stibnite_pickaxe", item(ModItems.stibnite_pickaxe))
                    .addCriterion("stibnite_axe", item(ModItems.stibnite_axe))
                    .addCriterion("stibnite_shovel", item(ModItems.stibnite_shovel))
                    .addCriterion("euclase_sword", item(ModItems.euclase_sword))
                    .addCriterion("euclase_pickaxe", item(ModItems.euclase_pickaxe))
                    .addCriterion("euclase_axe", item(ModItems.euclase_axe))
                    .addCriterion("euclase_shovel", item(ModItems.euclase_shovel))
                    .addCriterion("carnelian_sword", item(ModItems.carnelian_sword))
                    .addCriterion("carnelian_pickaxe", item(ModItems.carnelian_pickaxe))
                    .addCriterion("carnelian_axe", item(ModItems.carnelian_axe))
                    .addCriterion("carnelian_shovel", item(ModItems.carnelian_shovel))
                    .addCriterion("benitoite_sword", item(ModItems.benitoite_sword))
                    .addCriterion("benitoite_pickaxe", item(ModItems.benitoite_pickaxe))
                    .addCriterion("benitoite_axe", item(ModItems.benitoite_axe))
                    .addCriterion("benitoite_shovel", item(ModItems.benitoite_shovel))
                    .addCriterion("goshenite_sword", item(ModItems.goshenite_sword))
                    .addCriterion("goshenite_pickaxe", item(ModItems.goshenite_pickaxe))
                    .addCriterion("goshenite_axe", item(ModItems.goshenite_axe))
                    .addCriterion("goshenite_shovel", item(ModItems.goshenite_shovel))
                    .save(consumer, loc("get_all_tools"));
            //Craft a piece of armour from Gaia
            AdvancementHolder armor1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.sugilite_helmet.get(), title("get_armor"), description("get_armor"), null, AdvancementType.TASK, true, true, false)
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("sugilite_helmet", item(ModItems.sugilite_helmet))
                    .addCriterion("sugilite_chestplate", item(ModItems.sugilite_chestplate))
                    .addCriterion("sugilite_legs", item(ModItems.sugilite_legs))
                    .addCriterion("sugilite_boots", item(ModItems.sugilite_boots))
                    .addCriterion("proustite_helmet", item(ModItems.proustite_helmet))
                    .addCriterion("proustite_chestplate", item(ModItems.proustite_chestplate))
                    .addCriterion("proustite_legs", item(ModItems.proustite_legs))
                    .addCriterion("proustite_boots", item(ModItems.proustite_boots))
                    .addCriterion("albite_helmet", item(ModItems.albite_helmet))
                    .addCriterion("albite_chestplate", item(ModItems.albite_chestplate))
                    .addCriterion("albite_legs", item(ModItems.albite_legs))
                    .addCriterion("albite_boots", item(ModItems.albite_boots))
                    .addCriterion("carnelian_helmet", item(ModItems.carnelian_helmet))
                    .addCriterion("carnelian_chestplate", item(ModItems.carnelian_chestplate))
                    .addCriterion("carnelian_legs", item(ModItems.carnelian_legs))
                    .addCriterion("carnelian_boots", item(ModItems.carnelian_boots))
                    .addCriterion("diopside_helmet", item(ModItems.diopside_helmet))
                    .addCriterion("diopside_chestplate", item(ModItems.diopside_chestplate))
                    .addCriterion("diopside_legs", item(ModItems.diopside_legs))
                    .addCriterion("diopside_boots", item(ModItems.diopside_boots))
                    .addCriterion("goshenite_helmet", item(ModItems.goshenite_helmet))
                    .addCriterion("goshenite_chestplate", item(ModItems.goshenite_chestplate))
                    .addCriterion("goshenite_legs", item(ModItems.goshenite_legs))
                    .addCriterion("goshenite_boots", item(ModItems.goshenite_boots))
                    .save(consumer, loc("get_armor"));
            //Craft all armour in Gaia
            Advancement.Builder.advancement()
                    .parent(armor1)
                    .display(ModItems.goshenite_helmet.get(), title("get_all_armor"), description("get_all_armor"), null, AdvancementType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(500))
                    .addCriterion("sugilite_helmet", item(ModItems.sugilite_helmet))
                    .addCriterion("sugilite_chestplate", item(ModItems.sugilite_chestplate))
                    .addCriterion("sugilite_legs", item(ModItems.sugilite_legs))
                    .addCriterion("sugilite_boots", item(ModItems.sugilite_boots))
                    .addCriterion("proustite_helmet", item(ModItems.proustite_helmet))
                    .addCriterion("proustite_chestplate", item(ModItems.proustite_chestplate))
                    .addCriterion("proustite_legs", item(ModItems.proustite_legs))
                    .addCriterion("proustite_boots", item(ModItems.proustite_boots))
                    .addCriterion("albite_helmet", item(ModItems.albite_helmet))
                    .addCriterion("albite_chestplate", item(ModItems.albite_chestplate))
                    .addCriterion("albite_legs", item(ModItems.albite_legs))
                    .addCriterion("albite_boots", item(ModItems.albite_boots))
                    .addCriterion("carnelian_helmet", item(ModItems.carnelian_helmet))
                    .addCriterion("carnelian_chestplate", item(ModItems.carnelian_chestplate))
                    .addCriterion("carnelian_legs", item(ModItems.carnelian_legs))
                    .addCriterion("carnelian_boots", item(ModItems.carnelian_boots))
                    .addCriterion("diopside_helmet", item(ModItems.diopside_helmet))
                    .addCriterion("diopside_chestplate", item(ModItems.diopside_chestplate))
                    .addCriterion("diopside_legs", item(ModItems.diopside_legs))
                    .addCriterion("diopside_boots", item(ModItems.diopside_boots))
                    .addCriterion("goshenite_helmet", item(ModItems.goshenite_helmet))
                    .addCriterion("goshenite_chestplate", item(ModItems.goshenite_chestplate))
                    .addCriterion("goshenite_legs", item(ModItems.goshenite_legs))
                    .addCriterion("goshenite_boots", item(ModItems.goshenite_boots))
                    .save(consumer, loc("get_all_armor"));
            //Explore all of Gaia
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.glitter_grass.get(), title("explore_gaia"), description("explore_gaia"), null, AdvancementType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(1000))
                    .addCriterion("pink_agate_forest", biome(provider, GaiaBiomes.pink_agate_forest))
                    .addCriterion("blue_agate_taiga", biome(provider, GaiaBiomes.blue_agate_taiga))
                    .addCriterion("green_agate_jungle", biome(provider, GaiaBiomes.green_agate_jungle))
                    .addCriterion("purple_agate_swamp", biome(provider, GaiaBiomes.purple_agate_swamp))
                    .addCriterion("fossil_woodland", biome(provider, GaiaBiomes.fossil_woodland))
                    .addCriterion("mutant_agate_wildwood", biome(provider, GaiaBiomes.mutant_agate_wildwood))
                    .addCriterion("volcanic_lands", biome(provider, GaiaBiomes.volcanic_lands))
                    .addCriterion("static_wasteland", biome(provider, GaiaBiomes.static_wasteland))
                    .addCriterion("goldstone_lands", biome(provider, GaiaBiomes.goldstone_lands))
                    .addCriterion("crystal_plains", biome(provider, GaiaBiomes.crystal_plains))
                    .addCriterion("salt_dunes", biome(provider, GaiaBiomes.salt_dunes))
                    .addCriterion("shining_grove", biome(provider, GaiaBiomes.shining_grove))
                    .addCriterion("smoldering_bog", biome(provider, GaiaBiomes.smoldering_bog))
                    .addCriterion("mookaite_mesa", biome(provider, GaiaBiomes.mookaite_mesa))
                    .addCriterion("mineral_reservoir", biome(provider, GaiaBiomes.mineral_reservoir))
                    .addCriterion("mineral_river", biome(provider, GaiaBiomes.mineral_river))
                    .addCriterion("golden_forest", biome(provider, GaiaBiomes.golden_forest))
                    .addCriterion("golden_plains", biome(provider, GaiaBiomes.golden_plains))
                    .addCriterion("golden_hills", biome(provider, GaiaBiomes.golden_hills))
                    .addCriterion("golden_marsh", biome(provider, GaiaBiomes.golden_marsh))
                    .addCriterion("golden_sands", biome(provider, GaiaBiomes.golden_sands))
                    .save(consumer, loc("explore_gaia"));
            //Find a Gaia Mini Tower
            AdvancementHolder progress1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.amethyst_bricks.get(), title("find_mini_tower"), description("find_mini_tower"), null, AdvancementType.TASK, true, true, false)
                    .addCriterion("mini_tower", structure(provider, ModStructures.MINI_TOWER))
                    .save(consumer, loc("find_mini_tower"));
            //Find a Malachite Watchtower
            AdvancementHolder progress2 = Advancement.Builder.advancement()
                    .parent(progress1)
                    .display(ModBlocks.malachite_bricks.get(), title("find_malachite_watchtower"), description("find_malachite_watchtower"), null, AdvancementType.TASK, true, true, false)
                    .addCriterion("malachite_watchtower", structure(provider, ModStructures.MALACHITE_WATCHTOWER))
                    .save(consumer, loc("find_malachite_watchtower"));
            //Slay the Malachite Guard
            AdvancementHolder progress3 = Advancement.Builder.advancement()
                    .parent(progress2)
                    .display(ModItems.malachite_guard_baton.get(), title("slay_malachite_guard"), description("slay_malachite_guard"), null, AdvancementType.GOAL, true, true, false)
                    .addCriterion("malachite_guard", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntities.MALACHITE_GUARD.get())))
                    .save(consumer, loc("slay_malachite_guard"));
            //Get all of the Malachite gear
            Advancement.Builder.advancement()
                    .parent(progress3)
                    .display(ModItems.malachite_guard_headgear.get(), title("all_malachite_gear"), description("all_malachite_gear"), null, AdvancementType.CHALLENGE, true, true, false)
                    .addCriterion("malachite_guard_headgear", item(ModItems.malachite_guard_headgear))
                    .addCriterion("malachite_guard_brace", item(ModItems.malachite_guard_brace))
                    .addCriterion("malachite_guard_gear", item(ModItems.malachite_guard_gear))
                    .addCriterion("malachite_guard_boots", item(ModItems.malachite_guard_boots))
                    .addCriterion("malachite_guard_baton", item(ModItems.malachite_guard_baton))
                    .save(consumer, loc("all_malachite_gear"));
        }
    }


}
