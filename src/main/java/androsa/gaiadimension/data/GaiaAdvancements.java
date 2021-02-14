package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaAdvancementProvider;
import androsa.gaiadimension.registry.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class GaiaAdvancements extends GaiaAdvancementProvider {

    public GaiaAdvancements(DataGenerator generator) {
        super(generator);

        this.advancements = ImmutableList.of(new GaiaDimAdvancements());
    }

    public static class GaiaDimAdvancements implements Consumer<Consumer<Advancement>> {
        //Boolean 1 = show Toast
        //Boolean 2 = announce to chat
        //Boolean 3 = hide

        @Override
        public void accept(Consumer<Advancement> consumer) {
            //Enter Gaia
            Advancement root = Advancement.Builder.builder()
                    .withDisplay(ModBlocks.keystone_block, title("root"), description("root"), new ResourceLocation(GaiaDimensionMod.MODID, "textures/block/gaia_stone.png"), FrameType.TASK, true, true, false)
                    .withCriterion("entered_gaia", ChangeDimensionTrigger.Instance.toWorld(ModDimensions.gaia_world))
                    .register(consumer, loc("root"));
            //Collect a Gemstone
            Advancement gemstone1 = Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(ModItems.sugilite, title("collect_gemstone"), description("collect_gemstone"), null, FrameType.TASK, true, true, false)
                    .withRequirementsStrategy(IRequirementsStrategy.OR)
                    .withCriterion("get_sugilite", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite))
                    .withCriterion("get_hematite", InventoryChangeTrigger.Instance.forItems(ModItems.hematite))
                    .withCriterion("get_cinnabar", InventoryChangeTrigger.Instance.forItems(ModItems.cinnabar))
                    .withCriterion("get_labradorite", InventoryChangeTrigger.Instance.forItems(ModItems.labradorite))
                    .withCriterion("get_moonstone", InventoryChangeTrigger.Instance.forItems(ModItems.moonstone))
                    .withCriterion("get_red_opal", InventoryChangeTrigger.Instance.forItems(ModItems.red_opal))
                    .withCriterion("get_blue_opal", InventoryChangeTrigger.Instance.forItems(ModItems.blue_opal))
                    .withCriterion("get_green_opal", InventoryChangeTrigger.Instance.forItems(ModItems.green_opal))
                    .withCriterion("get_white_opal", InventoryChangeTrigger.Instance.forItems(ModItems.white_opal))
                    .withCriterion("get_ixiolite", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite))
                    .withCriterion("get_proustite", InventoryChangeTrigger.Instance.forItems(ModItems.proustite))
                    .withCriterion("get_euclase", InventoryChangeTrigger.Instance.forItems(ModItems.euclase))
                    .withCriterion("get_leucite", InventoryChangeTrigger.Instance.forItems(ModItems.leucite))
                    .withCriterion("get_carnelian", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian))
                    .withCriterion("get_benitoite", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite))
                    .withCriterion("get_diopside", InventoryChangeTrigger.Instance.forItems(ModItems.diopside))
                    .withCriterion("get_chalcedony", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony))
                    .withCriterion("get_pyrite", InventoryChangeTrigger.Instance.forItems(ModItems.pyrite))
                    .withCriterion("get_tektite", InventoryChangeTrigger.Instance.forItems(ModItems.tektite))
                    .withCriterion("get_goldstone", InventoryChangeTrigger.Instance.forItems(ModItems.goldstone))
                    .withCriterion("get_aura_cluster", InventoryChangeTrigger.Instance.forItems(ModItems.aura_cluster))
                    .withCriterion("get_bismuth_crystal", InventoryChangeTrigger.Instance.forItems(ModItems.bismuth_crystal))
                    .register(consumer, loc("collect_gemstone"));
            //Restructure a Gemstone
            Advancement gemstone2 = Advancement.Builder.builder()
                    .withParent(gemstone1)
                    .withDisplay(ModItems.ixiolite, title("restructure_gemstone"), description("restructure_gemstone"), null, FrameType.TASK, true, true, false)
                    .withRequirementsStrategy(IRequirementsStrategy.OR)
                    .withCriterion("get_ixiolite", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite))
                    .withCriterion("get_proustite", InventoryChangeTrigger.Instance.forItems(ModItems.proustite))
                    .withCriterion("get_euclase", InventoryChangeTrigger.Instance.forItems(ModItems.euclase))
                    .withCriterion("get_leucite", InventoryChangeTrigger.Instance.forItems(ModItems.leucite))
                    .withCriterion("get_carnelian", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian))
                    .withCriterion("get_benitoite", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite))
                    .withCriterion("get_diopside", InventoryChangeTrigger.Instance.forItems(ModItems.diopside))
                    .withCriterion("get_chalcedony", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony))
                    .withCriterion("get_tektite", InventoryChangeTrigger.Instance.forItems(ModItems.tektite))
                    .register(consumer, loc("restructure_gemstone"));
            //Collect all mineable Gemstones
            Advancement gemstone3 = Advancement.Builder.builder()
                    .withParent(gemstone2)
                    .withDisplay(ModItems.white_opal, title("mine_all_gemstones"), description("mine_all_gemstones"), null, FrameType.TASK, true, true, false)
                    .withCriterion("get_sugilite", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite))
                    .withCriterion("get_hematite", InventoryChangeTrigger.Instance.forItems(ModItems.hematite))
                    .withCriterion("get_cinnabar", InventoryChangeTrigger.Instance.forItems(ModItems.cinnabar))
                    .withCriterion("get_labradorite", InventoryChangeTrigger.Instance.forItems(ModItems.labradorite))
                    .withCriterion("get_moonstone", InventoryChangeTrigger.Instance.forItems(ModItems.moonstone))
                    .withCriterion("get_red_opal", InventoryChangeTrigger.Instance.forItems(ModItems.red_opal))
                    .withCriterion("get_blue_opal", InventoryChangeTrigger.Instance.forItems(ModItems.blue_opal))
                    .withCriterion("get_green_opal", InventoryChangeTrigger.Instance.forItems(ModItems.green_opal))
                    .withCriterion("get_white_opal", InventoryChangeTrigger.Instance.forItems(ModItems.white_opal))
                    .register(consumer, loc("mine_all_gemstones"));
            //Collect all Gemstones
            Advancement.Builder.builder()
                    .withParent(gemstone3)
                    .withDisplay(ModItems.chalcedony, title("get_all_gemstones"), description("get_all_gemstones"), null, FrameType.CHALLENGE, true, true, false)
                    .withCriterion("get_sugilite", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite))
                    .withCriterion("get_hematite", InventoryChangeTrigger.Instance.forItems(ModItems.hematite))
                    .withCriterion("get_cinnabar", InventoryChangeTrigger.Instance.forItems(ModItems.cinnabar))
                    .withCriterion("get_labradorite", InventoryChangeTrigger.Instance.forItems(ModItems.labradorite))
                    .withCriterion("get_moonstone", InventoryChangeTrigger.Instance.forItems(ModItems.moonstone))
                    .withCriterion("get_red_opal", InventoryChangeTrigger.Instance.forItems(ModItems.red_opal))
                    .withCriterion("get_blue_opal", InventoryChangeTrigger.Instance.forItems(ModItems.blue_opal))
                    .withCriterion("get_green_opal", InventoryChangeTrigger.Instance.forItems(ModItems.green_opal))
                    .withCriterion("get_white_opal", InventoryChangeTrigger.Instance.forItems(ModItems.white_opal))
                    .withCriterion("get_ixiolite", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite))
                    .withCriterion("get_proustite", InventoryChangeTrigger.Instance.forItems(ModItems.proustite))
                    .withCriterion("get_euclase", InventoryChangeTrigger.Instance.forItems(ModItems.euclase))
                    .withCriterion("get_leucite", InventoryChangeTrigger.Instance.forItems(ModItems.leucite))
                    .withCriterion("get_carnelian", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian))
                    .withCriterion("get_benitoite", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite))
                    .withCriterion("get_diopside", InventoryChangeTrigger.Instance.forItems(ModItems.diopside))
                    .withCriterion("get_chalcedony", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony))
                    .withCriterion("get_pyrite", InventoryChangeTrigger.Instance.forItems(ModItems.pyrite))
                    .withCriterion("get_tektite", InventoryChangeTrigger.Instance.forItems(ModItems.tektite))
                    .withCriterion("get_goldstone", InventoryChangeTrigger.Instance.forItems(ModItems.goldstone))
                    .withCriterion("get_aura_cluster", InventoryChangeTrigger.Instance.forItems(ModItems.aura_cluster))
                    .withCriterion("get_bismuth_crystal", InventoryChangeTrigger.Instance.forItems(ModItems.bismuth_crystal))
                    .register(consumer, loc("get_all_gemstones"));
            //Craft a weapon from Gaia
            Advancement tool1 = Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(ModItems.agate_sword, title("get_tool"), description("get_tool"), null, FrameType.TASK, true, true, false)
                    .withRequirementsStrategy(IRequirementsStrategy.OR)
                    .withCriterion("agate_sword", InventoryChangeTrigger.Instance.forItems(ModItems.agate_sword))
                    .withCriterion("agate_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.agate_pickaxe))
                    .withCriterion("agate_axe", InventoryChangeTrigger.Instance.forItems(ModItems.agate_axe))
                    .withCriterion("agate_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.agate_shovel))
                    .withCriterion("sugilite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_sword))
                    .withCriterion("sugilite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_pickaxe))
                    .withCriterion("sugilite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_axe))
                    .withCriterion("sugilite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_shovel))
                    .withCriterion("ixiolite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_sword))
                    .withCriterion("ixiolite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_pickaxe))
                    .withCriterion("ixiolite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_axe))
                    .withCriterion("ixiolite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_shovel))
                    .withCriterion("euclase_sword", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_sword))
                    .withCriterion("euclase_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_pickaxe))
                    .withCriterion("euclase_axe", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_axe))
                    .withCriterion("euclase_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_shovel))
                    .withCriterion("carnelian_sword", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_sword))
                    .withCriterion("carnelian_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_pickaxe))
                    .withCriterion("carnelian_axe", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_axe))
                    .withCriterion("carnelian_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_shovel))
                    .withCriterion("benitoite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_sword))
                    .withCriterion("benitoite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_pickaxe))
                    .withCriterion("benitoite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_axe))
                    .withCriterion("benitoite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_shovel))
                    .withCriterion("chalcedony_sword", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_sword))
                    .withCriterion("chalcedony_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_pickaxe))
                    .withCriterion("chalcedony_axe", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_axe))
                    .withCriterion("chalcedony_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_shovel))
                    .register(consumer, loc("get_tool"));
            //Craft all weapons from Gaia
            Advancement.Builder.builder()
                    .withParent(tool1)
                    .withDisplay(ModItems.chalcedony_sword, title("get_all_tools"), description("get_all_tools"), null, FrameType.CHALLENGE, true, true, false)
                    .withCriterion("agate_sword", InventoryChangeTrigger.Instance.forItems(ModItems.agate_sword))
                    .withCriterion("agate_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.agate_pickaxe))
                    .withCriterion("agate_axe", InventoryChangeTrigger.Instance.forItems(ModItems.agate_axe))
                    .withCriterion("agate_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.agate_shovel))
                    .withCriterion("sugilite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_sword))
                    .withCriterion("sugilite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_pickaxe))
                    .withCriterion("sugilite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_axe))
                    .withCriterion("sugilite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_shovel))
                    .withCriterion("ixiolite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_sword))
                    .withCriterion("ixiolite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_pickaxe))
                    .withCriterion("ixiolite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_axe))
                    .withCriterion("ixiolite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.ixiolite_shovel))
                    .withCriterion("euclase_sword", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_sword))
                    .withCriterion("euclase_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_pickaxe))
                    .withCriterion("euclase_axe", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_axe))
                    .withCriterion("euclase_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.euclase_shovel))
                    .withCriterion("carnelian_sword", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_sword))
                    .withCriterion("carnelian_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_pickaxe))
                    .withCriterion("carnelian_axe", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_axe))
                    .withCriterion("carnelian_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_shovel))
                    .withCriterion("benitoite_sword", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_sword))
                    .withCriterion("benitoite_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_pickaxe))
                    .withCriterion("benitoite_axe", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_axe))
                    .withCriterion("benitoite_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.benitoite_shovel))
                    .withCriterion("chalcedony_sword", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_sword))
                    .withCriterion("chalcedony_pickaxe", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_pickaxe))
                    .withCriterion("chalcedony_axe", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_axe))
                    .withCriterion("chalcedony_shovel", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_shovel))
                    .register(consumer, loc("get_all_tools"));
            //Craft a piece of armour from Gaia
            Advancement armor1 = Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(ModItems.sugilite_helmet, title("get_armor"), description("get_armor"), null, FrameType.TASK, true, true, false)
                    .withRequirementsStrategy(IRequirementsStrategy.OR)
                    .withCriterion("sugilite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_helmet))
                    .withCriterion("sugilite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_chestplate))
                    .withCriterion("sugilite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_legs))
                    .withCriterion("sugilite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_boots))
                    .withCriterion("proustite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_helmet))
                    .withCriterion("proustite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_chestplate))
                    .withCriterion("proustite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_legs))
                    .withCriterion("proustite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_boots))
                    .withCriterion("leucite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_helmet))
                    .withCriterion("leucite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_chestplate))
                    .withCriterion("leucite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_legs))
                    .withCriterion("leucite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_boots))
                    .withCriterion("carnelian_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_helmet))
                    .withCriterion("carnelian_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_chestplate))
                    .withCriterion("carnelian_legs", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_legs))
                    .withCriterion("carnelian_boots", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_boots))
                    .withCriterion("diopside_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_helmet))
                    .withCriterion("diopside_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_chestplate))
                    .withCriterion("diopside_legs", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_legs))
                    .withCriterion("diopside_boots", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_boots))
                    .withCriterion("chalcedony_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_helmet))
                    .withCriterion("chalcedony_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_chestplate))
                    .withCriterion("chalcedony_legs", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_legs))
                    .withCriterion("chalcedony_boots", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_boots))
                    .register(consumer, loc("get_armor"));
            //Craft all armour in Gaia
            Advancement.Builder.builder()
                    .withParent(armor1)
                    .withDisplay(ModItems.chalcedony_helmet, title("get_all_armor"), description("get_all_armor"), null, FrameType.CHALLENGE, true, true, false)
                    .withCriterion("sugilite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_helmet))
                    .withCriterion("sugilite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_chestplate))
                    .withCriterion("sugilite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_legs))
                    .withCriterion("sugilite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.sugilite_boots))
                    .withCriterion("proustite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_helmet))
                    .withCriterion("proustite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_chestplate))
                    .withCriterion("proustite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_legs))
                    .withCriterion("proustite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.proustite_boots))
                    .withCriterion("leucite_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_helmet))
                    .withCriterion("leucite_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_chestplate))
                    .withCriterion("leucite_legs", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_legs))
                    .withCriterion("leucite_boots", InventoryChangeTrigger.Instance.forItems(ModItems.leucite_boots))
                    .withCriterion("carnelian_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_helmet))
                    .withCriterion("carnelian_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_chestplate))
                    .withCriterion("carnelian_legs", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_legs))
                    .withCriterion("carnelian_boots", InventoryChangeTrigger.Instance.forItems(ModItems.carnelian_boots))
                    .withCriterion("diopside_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_helmet))
                    .withCriterion("diopside_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_chestplate))
                    .withCriterion("diopside_legs", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_legs))
                    .withCriterion("diopside_boots", InventoryChangeTrigger.Instance.forItems(ModItems.diopside_boots))
                    .withCriterion("chalcedony_helmet", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_helmet))
                    .withCriterion("chalcedony_chestplate", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_chestplate))
                    .withCriterion("chalcedony_legs", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_legs))
                    .withCriterion("chalcedony_boots", InventoryChangeTrigger.Instance.forItems(ModItems.chalcedony_boots))
                    .register(consumer, loc("get_all_armor"));
            //Explore all of Gaia
            Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(ModBlocks.glitter_grass, title("explore_gaia"), description("explore_gaia"), null, FrameType.CHALLENGE, true, true, false)
                    .withRewards(AdvancementRewards.Builder.experience(1000))
                    .withCriterion("pink_agate_forest", biome(ModBiomes.pink_agate_forest))
                    .withCriterion("blue_agate_taiga", biome(ModBiomes.blue_agate_taiga))
                    .withCriterion("green_agate_jungle", biome(ModBiomes.green_agate_jungle))
                    .withCriterion("purple_agate_swamp", biome(ModBiomes.purple_agate_swamp))
                    .withCriterion("fossil_woodland", biome(ModBiomes.fossil_woodland))
                    .withCriterion("mutant_agate_wildwood", biome(ModBiomes.mutant_agate_wildwood))
                    .withCriterion("volcanic_lands", biome(ModBiomes.volcanic_lands))
                    .withCriterion("static_wasteland", biome(ModBiomes.static_wasteland))
                    .withCriterion("goldstone_lands", biome(ModBiomes.goldstone_lands))
                    .withCriterion("crystal_plains", biome(ModBiomes.crystal_plains))
                    .withCriterion("salt_dunes", biome(ModBiomes.salt_dunes))
                    .withCriterion("shining_grove", biome(ModBiomes.shining_grove))
                    .withCriterion("smoldering_bog", biome(ModBiomes.smoldering_bog))
                    .withCriterion("mineral_reservoir", biome(ModBiomes.mineral_reservoir))
                    .withCriterion("mineral_river", biome(ModBiomes.mineral_river))
                    .register(consumer, loc("explore_gaia"));
            //Find a Gaia Mini Tower
            Advancement progress1 = Advancement.Builder.builder()
                    .withParent(root)
                    .withDisplay(ModBlocks.amethyst_bricks, title("find_mini_tower"), description("find_mini_tower"), null, FrameType.TASK, true, true, false)
                    .withCriterion("mini_tower", PositionTrigger.Instance.forLocation(LocationPredicate.forFeature(ModWorldgen.MINI_TOWER)))
                    .register(consumer, loc("find_mini_tower"));
            //Find a Malachite Watchtower
            Advancement progress2 = Advancement.Builder.builder()
                    .withParent(progress1)
                    .withDisplay(ModBlocks.malachite_bricks, title("find_malachite_watchtower"), description("find_malachite_watchtower"), null, FrameType.TASK, true, true, false)
                    .withCriterion("malachite_watchtower", PositionTrigger.Instance.forLocation(LocationPredicate.forFeature(ModWorldgen.MALACHITE_WATCHTOWER)))
                    .register(consumer, loc("find_malachite_watchtower"));
            //Slay the Malachite Guard
            Advancement progress3 = Advancement.Builder.builder()
                    .withParent(progress2)
                    .withDisplay(ModItems.malachite_guard_baton, title("slay_malachite_guard"), description("slay_malachite_guard"), null, FrameType.GOAL, true, true, false)
                    .withCriterion("malachite_guard", KilledTrigger.Instance.playerKilledEntity(EntityPredicate.Builder.create().type(ModEntities.MALACHITE_GUARD)))
                    .register(consumer, loc("slay_malachite_guard"));
            //Get all of the Malachite gear
            Advancement.Builder.builder()
                    .withParent(progress3)
                    .withDisplay(ModItems.malachite_guard_headgear, title("all_malachite_gear"), description("all_malachite_gear"), null, FrameType.CHALLENGE, true, true, false)
                    .withCriterion("malachite_guard_headgear", InventoryChangeTrigger.Instance.forItems(ModItems.malachite_guard_headgear))
                    .withCriterion("malachite_guard_brace", InventoryChangeTrigger.Instance.forItems(ModItems.malachite_guard_brace))
                    .withCriterion("malachite_guard_gear", InventoryChangeTrigger.Instance.forItems(ModItems.malachite_guard_gear))
                    .withCriterion("malachite_guard_boots", InventoryChangeTrigger.Instance.forItems(ModItems.malachite_guard_boots))
                    .withCriterion("malachite_guard_baton", InventoryChangeTrigger.Instance.forItems(ModItems.malachite_guard_baton))
                    .register(consumer, loc("all_malachite_gear"));
        }
    }
}
