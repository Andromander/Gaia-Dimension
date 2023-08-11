package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaAdvancementProvider;
import androsa.gaiadimension.registry.registration.ModStructures;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.bootstrap.GaiaDimensions;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GaiaAdvancements extends GaiaAdvancementProvider {

    public GaiaAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, helper, List.of(new Advancements()));
    }

    public static class Advancements implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
            //Boolean 1 = show Toast
            //Boolean 2 = announce to chat
            //Boolean 3 = hide

            //Enter Gaia
            Advancement root = Advancement.Builder.advancement()
                    .display(ModBlocks.keystone_block.get(), title("root"), description("root"), new ResourceLocation(GaiaDimensionMod.MODID, "textures/block/gaia_stone.png"), FrameType.TASK, true, true, false)
                    .addCriterion("entered_gaia", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(GaiaDimensions.gaia_world))
                    .save(consumer, loc("root"));
            //Collect a Gemstone
            Advancement gemstone1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.sugilite.get(), title("collect_gemstone"), description("collect_gemstone"), null, FrameType.TASK, true, true, false)
                    .requirements(RequirementsStrategy.OR)
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
                    .addCriterion("get_leucite", item(ModItems.leucite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_chalcedony", item(ModItems.chalcedony))
                    .addCriterion("get_pyrite", item(ModItems.pyrite))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .addCriterion("get_goldstone", item(ModItems.goldstone))
                    .addCriterion("get_aura_cluster", item(ModItems.aura_cluster))
                    .addCriterion("get_bismuth_crystal", item(ModItems.bismuth_crystal))
                    .addCriterion("get_opalite", item(ModItems.opalite))
                    .addCriterion("get_celestine", item(ModItems.celestine))
                    .save(consumer, loc("collect_gemstone"));
            //Restructure a Gemstone
            Advancement gemstone2 = Advancement.Builder.advancement()
                    .parent(gemstone1)
                    .display(ModItems.stibnite.get(), title("restructure_gemstone"), description("restructure_gemstone"), null, FrameType.TASK, true, true, false)
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("get_stibnite", item(ModItems.stibnite))
                    .addCriterion("get_proustite", item(ModItems.proustite))
                    .addCriterion("get_euclase", item(ModItems.euclase))
                    .addCriterion("get_leucite", item(ModItems.leucite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_chalcedony", item(ModItems.chalcedony))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .save(consumer, loc("restructure_gemstone"));
            //Collect all mineable Gemstones
            Advancement gemstone3 = Advancement.Builder.advancement()
                    .parent(gemstone2)
                    .display(ModItems.white_opal.get(), title("mine_all_gemstones"), description("mine_all_gemstones"), null, FrameType.TASK, true, true, false)
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
                    .display(ModItems.chalcedony.get(), title("get_all_gemstones"), description("get_all_gemstones"), null, FrameType.CHALLENGE, true, true, false)
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
                    .addCriterion("get_leucite", item(ModItems.leucite))
                    .addCriterion("get_carnelian", item(ModItems.carnelian))
                    .addCriterion("get_benitoite", item(ModItems.benitoite))
                    .addCriterion("get_diopside", item(ModItems.diopside))
                    .addCriterion("get_chalcedony", item(ModItems.chalcedony))
                    .addCriterion("get_pyrite", item(ModItems.pyrite))
                    .addCriterion("get_tektite", item(ModItems.tektite))
                    .addCriterion("get_goldstone", item(ModItems.goldstone))
                    .addCriterion("get_aura_cluster", item(ModItems.aura_cluster))
                    .addCriterion("get_bismuth_crystal", item(ModItems.bismuth_crystal))
                    .addCriterion("get_opalite", item(ModItems.opalite))
                    .addCriterion("get_celestine", item(ModItems.celestine))
                    .save(consumer, loc("get_all_gemstones"));
            //Craft a weapon from Gaia
            Advancement tool1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.agate_sword.get(), title("get_tool"), description("get_tool"), null, FrameType.TASK, true, true, false)
                    .requirements(RequirementsStrategy.OR)
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
                    .addCriterion("chalcedony_sword", item(ModItems.chalcedony_sword))
                    .addCriterion("chalcedony_pickaxe", item(ModItems.chalcedony_pickaxe))
                    .addCriterion("chalcedony_axe", item(ModItems.chalcedony_axe))
                    .addCriterion("chalcedony_shovel", item(ModItems.chalcedony_shovel))
                    .save(consumer, loc("get_tool"));
            //Craft all weapons from Gaia
            Advancement.Builder.advancement()
                    .parent(tool1)
                    .display(ModItems.chalcedony_sword.get(), title("get_all_tools"), description("get_all_tools"), null, FrameType.CHALLENGE, true, true, false)
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
                    .addCriterion("chalcedony_sword", item(ModItems.chalcedony_sword))
                    .addCriterion("chalcedony_pickaxe", item(ModItems.chalcedony_pickaxe))
                    .addCriterion("chalcedony_axe", item(ModItems.chalcedony_axe))
                    .addCriterion("chalcedony_shovel", item(ModItems.chalcedony_shovel))
                    .save(consumer, loc("get_all_tools"));
            //Craft a piece of armour from Gaia
            Advancement armor1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.sugilite_helmet.get(), title("get_armor"), description("get_armor"), null, FrameType.TASK, true, true, false)
                    .requirements(RequirementsStrategy.OR)
                    .addCriterion("sugilite_helmet", item(ModItems.sugilite_helmet))
                    .addCriterion("sugilite_chestplate", item(ModItems.sugilite_chestplate))
                    .addCriterion("sugilite_legs", item(ModItems.sugilite_legs))
                    .addCriterion("sugilite_boots", item(ModItems.sugilite_boots))
                    .addCriterion("proustite_helmet", item(ModItems.proustite_helmet))
                    .addCriterion("proustite_chestplate", item(ModItems.proustite_chestplate))
                    .addCriterion("proustite_legs", item(ModItems.proustite_legs))
                    .addCriterion("proustite_boots", item(ModItems.proustite_boots))
                    .addCriterion("leucite_helmet", item(ModItems.leucite_helmet))
                    .addCriterion("leucite_chestplate", item(ModItems.leucite_chestplate))
                    .addCriterion("leucite_legs", item(ModItems.leucite_legs))
                    .addCriterion("leucite_boots", item(ModItems.leucite_boots))
                    .addCriterion("carnelian_helmet", item(ModItems.carnelian_helmet))
                    .addCriterion("carnelian_chestplate", item(ModItems.carnelian_chestplate))
                    .addCriterion("carnelian_legs", item(ModItems.carnelian_legs))
                    .addCriterion("carnelian_boots", item(ModItems.carnelian_boots))
                    .addCriterion("diopside_helmet", item(ModItems.diopside_helmet))
                    .addCriterion("diopside_chestplate", item(ModItems.diopside_chestplate))
                    .addCriterion("diopside_legs", item(ModItems.diopside_legs))
                    .addCriterion("diopside_boots", item(ModItems.diopside_boots))
                    .addCriterion("chalcedony_helmet", item(ModItems.chalcedony_helmet))
                    .addCriterion("chalcedony_chestplate", item(ModItems.chalcedony_chestplate))
                    .addCriterion("chalcedony_legs", item(ModItems.chalcedony_legs))
                    .addCriterion("chalcedony_boots", item(ModItems.chalcedony_boots))
                    .save(consumer, loc("get_armor"));
            //Craft all armour in Gaia
            Advancement.Builder.advancement()
                    .parent(armor1)
                    .display(ModItems.chalcedony_helmet.get(), title("get_all_armor"), description("get_all_armor"), null, FrameType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(500))
                    .addCriterion("sugilite_helmet", item(ModItems.sugilite_helmet))
                    .addCriterion("sugilite_chestplate", item(ModItems.sugilite_chestplate))
                    .addCriterion("sugilite_legs", item(ModItems.sugilite_legs))
                    .addCriterion("sugilite_boots", item(ModItems.sugilite_boots))
                    .addCriterion("proustite_helmet", item(ModItems.proustite_helmet))
                    .addCriterion("proustite_chestplate", item(ModItems.proustite_chestplate))
                    .addCriterion("proustite_legs", item(ModItems.proustite_legs))
                    .addCriterion("proustite_boots", item(ModItems.proustite_boots))
                    .addCriterion("leucite_helmet", item(ModItems.leucite_helmet))
                    .addCriterion("leucite_chestplate", item(ModItems.leucite_chestplate))
                    .addCriterion("leucite_legs", item(ModItems.leucite_legs))
                    .addCriterion("leucite_boots", item(ModItems.leucite_boots))
                    .addCriterion("carnelian_helmet", item(ModItems.carnelian_helmet))
                    .addCriterion("carnelian_chestplate", item(ModItems.carnelian_chestplate))
                    .addCriterion("carnelian_legs", item(ModItems.carnelian_legs))
                    .addCriterion("carnelian_boots", item(ModItems.carnelian_boots))
                    .addCriterion("diopside_helmet", item(ModItems.diopside_helmet))
                    .addCriterion("diopside_chestplate", item(ModItems.diopside_chestplate))
                    .addCriterion("diopside_legs", item(ModItems.diopside_legs))
                    .addCriterion("diopside_boots", item(ModItems.diopside_boots))
                    .addCriterion("chalcedony_helmet", item(ModItems.chalcedony_helmet))
                    .addCriterion("chalcedony_chestplate", item(ModItems.chalcedony_chestplate))
                    .addCriterion("chalcedony_legs", item(ModItems.chalcedony_legs))
                    .addCriterion("chalcedony_boots", item(ModItems.chalcedony_boots))
                    .save(consumer, loc("get_all_armor"));
            //Explore all of Gaia
            Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.glitter_grass.get(), title("explore_gaia"), description("explore_gaia"), null, FrameType.CHALLENGE, true, true, false)
                    .rewards(AdvancementRewards.Builder.experience(1000))
                    .addCriterion("pink_agate_forest", biome(GaiaBiomes.pink_agate_forest))
                    .addCriterion("blue_agate_taiga", biome(GaiaBiomes.blue_agate_taiga))
                    .addCriterion("green_agate_jungle", biome(GaiaBiomes.green_agate_jungle))
                    .addCriterion("purple_agate_swamp", biome(GaiaBiomes.purple_agate_swamp))
                    .addCriterion("fossil_woodland", biome(GaiaBiomes.fossil_woodland))
                    .addCriterion("mutant_agate_wildwood", biome(GaiaBiomes.mutant_agate_wildwood))
                    .addCriterion("volcanic_lands", biome(GaiaBiomes.volcanic_lands))
                    .addCriterion("static_wasteland", biome(GaiaBiomes.static_wasteland))
                    .addCriterion("goldstone_lands", biome(GaiaBiomes.goldstone_lands))
                    .addCriterion("crystal_plains", biome(GaiaBiomes.crystal_plains))
                    .addCriterion("salt_dunes", biome(GaiaBiomes.salt_dunes))
                    .addCriterion("shining_grove", biome(GaiaBiomes.shining_grove))
                    .addCriterion("smoldering_bog", biome(GaiaBiomes.smoldering_bog))
                    .addCriterion("mookaite_mesa", biome(GaiaBiomes.mookaite_mesa))
                    .addCriterion("mineral_reservoir", biome(GaiaBiomes.mineral_reservoir))
                    .addCriterion("mineral_river", biome(GaiaBiomes.mineral_river))
                    .addCriterion("golden_forest", biome(GaiaBiomes.golden_forest))
                    .addCriterion("golden_plains", biome(GaiaBiomes.golden_plains))
                    .addCriterion("golden_hills", biome(GaiaBiomes.golden_hills))
                    .addCriterion("golden_marsh", biome(GaiaBiomes.golden_marsh))
                    .addCriterion("golden_sands", biome(GaiaBiomes.golden_sands))
                    .save(consumer, loc("explore_gaia"));
            //Find a Gaia Mini Tower
            Advancement progress1 = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.amethyst_bricks.get(), title("find_mini_tower"), description("find_mini_tower"), null, FrameType.TASK, true, true, false)
                    .addCriterion("mini_tower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.MINI_TOWER)))
                    .save(consumer, loc("find_mini_tower"));
            //Find a Malachite Watchtower
            Advancement progress2 = Advancement.Builder.advancement()
                    .parent(progress1)
                    .display(ModBlocks.malachite_bricks.get(), title("find_malachite_watchtower"), description("find_malachite_watchtower"), null, FrameType.TASK, true, true, false)
                    .addCriterion("malachite_watchtower", PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(ModStructures.MALACHITE_WATCHTOWER)))
                    .save(consumer, loc("find_malachite_watchtower"));
            //Slay the Malachite Guard
            Advancement progress3 = Advancement.Builder.advancement()
                    .parent(progress2)
                    .display(ModItems.malachite_guard_baton.get(), title("slay_malachite_guard"), description("slay_malachite_guard"), null, FrameType.GOAL, true, true, false)
                    .addCriterion("malachite_guard", KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(ModEntities.MALACHITE_GUARD.get())))
                    .save(consumer, loc("slay_malachite_guard"));
            //Get all of the Malachite gear
            Advancement.Builder.advancement()
                    .parent(progress3)
                    .display(ModItems.malachite_guard_headgear.get(), title("all_malachite_gear"), description("all_malachite_gear"), null, FrameType.CHALLENGE, true, true, false)
                    .addCriterion("malachite_guard_headgear", item(ModItems.malachite_guard_headgear))
                    .addCriterion("malachite_guard_brace", item(ModItems.malachite_guard_brace))
                    .addCriterion("malachite_guard_gear", item(ModItems.malachite_guard_gear))
                    .addCriterion("malachite_guard_boots", item(ModItems.malachite_guard_boots))
                    .addCriterion("malachite_guard_baton", item(ModItems.malachite_guard_baton))
                    .save(consumer, loc("all_malachite_gear"));
        }
    }


}
