package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static androsa.gaiadimension.registry.GDItems.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

@Mod.EventBusSubscriber()
public class GDItemsRegister {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ItemRegistryHelper items = new ItemRegistryHelper(event.getRegistry());

        items.register("crystallized_redstone", new GDItem());
        items.register("crystallized_lapis_lazuli", new GDItem());
        items.register("glint_and_gold", new GDFlintAndGold());
        items.register("agate_stick", new GDItem());
        items.register("hot_dust", new GDItem() {
            @Override
            public int getItemBurnTime(ItemStack stack) {
                return 100;
            }
        });
        items.register("goldstone_dust", new GDItem());
        items.register("fine_dust", new GDItem());
        items.register("cloudy_shard", new GDItem());
        items.register("agate_cup", new GDItem());
        items.register("scaynyx_ingot", new GDItem());
        items.register("sweet_muckball", new GDItem());
        items.register("sugar_crystals", new GDItem());
        items.register("sugar_cluster", new GDItem());
        items.register("shiny_bone", new GDItem());
        items.register("fine_thread", new GDItem());
        items.register("twined_thread", new GDItem());
        items.register("pink_essence", new GDItem());
        items.register("pink_goo", new GDItem());
        items.register("agate_fabric", new GDItem());
        items.register("sturdy_pebble", new GDSturdyPebble());

        items.register("pink_geode", new GDGeode());
        items.register("blue_geode", new GDGeode());
        items.register("green_geode", new GDGeode());
        items.register("purple_geode", new GDGeode());
        items.register("pink_geode_juice", new GDGeodeJuice(8, 0.7F, false));
        items.register("blue_geode_tea", new GDGeodeJuice(8, 0.7F, false));
        items.register("green_geode_ale", new GDGeodeJuice(8, 0.7F, false));
        items.register("purple_geode_soda", new GDGeodeJuice(8, 0.7F, false));
        items.register("pearly_geode_elixir", new GDGeodeJuice(8, 0.7F, false));
        items.register("pink_geode_slice", new GDGeodeSlice(3, 0.5F));
        items.register("blue_geode_slice", new GDGeodeSlice(4, 0.4F));
        items.register("green_geode_slice", new GDGeodeSlice(5, 0.3F));
        items.register("purple_geode_slice", new GDGeodeSlice(6, 0.2F));
        items.register("lurmorus_meat", new GDFood(4, 0.4F, false));
        items.register("lurmorus_steak", new GDFood(9, 0.9F, false));
        items.register("small_tentacle", new GDFood(3, 0.2F, false));
        items.register("small_calamari", new GDFood(6, 0.6F, false));
        items.register("large_tentacle", new GDFood(4, 0.3F, false));
        items.register("large_calamari", new GDFood(8, 0.7F, false));
        items.register("markuzar_mint", new GDFood(2, 0.4F, false));
        items.register("luggeroth_chop", new GDFood(3, 0.3F, false));
        items.register("cooked_luggeroth_chop", new GDFood(8, 0.8F, false));

        items.register("hematite_powder", new GDGroundGem());
        items.register("pyrite_powder", new GDGroundGem());
        items.register("labradorite_powder", new GDGroundGem());
        items.register("moonstone_powder", new GDGroundGem());
        items.register("cinnabar_powder", new GDGroundGem());
        items.register("red_opal_powder", new GDGroundGem());
        items.register("blue_opal_powder", new GDGroundGem());
        items.register("green_opal_powder", new GDGroundGem());
        items.register("white_opal_grit", new GDGroundGem());

        items.register("sugilite", new GDItem());
        items.register("hematite", new GDItem());
        items.register("pyrite", new GDItem());
        items.register("labradorite", new GDItem());
        items.register("moonstone", new GDItem());
        items.register("cinnabar", new GDItem());
        items.register("red_opal", new GDItem());
        items.register("blue_opal", new GDItem());
        items.register("green_opal", new GDItem());
        items.register("white_opal", new GDItem());
        items.register("ixiolite", new GDItem());
        items.register("proustite", new GDItem());
        items.register("euclase", new GDItem());
        items.register("leucite", new GDItem());
        items.register("carnelian", new GDItem());
        items.register("benitoite", new GDItem());
        items.register("diopside", new GDItem());
        items.register("chalcedony", new GDItem());
        items.register("black_residue", new GDItem());
        items.register("tektite", new GDItem());
        items.register("goldstone_residue", new GDItem());
        items.register("goldstone", new GDItem());
        items.register("aura_residue", new GDItem());
        items.register("aura_cluster", new GDItem());
        items.register("bismuth_residue", new GDItem());
        items.register("bismuth_crystal", new GDItem());

        items.register("agate_sword", new GDGenericSword(TOOL_AGATE));
        items.register("agate_pickaxe", new GDGenericPickaxe(TOOL_AGATE));
        items.register("agate_axe", new GDGenericAxe(TOOL_AGATE, 5F, -3.2F));
        items.register("agate_shovel", new GDGenericShovel(TOOL_AGATE));
        items.register("sugilite_sword", new GDGenericSword(TOOL_SUGILITE));
        items.register("sugilite_pickaxe", new GDGenericPickaxe(TOOL_SUGILITE));
        items.register("sugilite_axe", new GDGenericAxe(TOOL_SUGILITE, 6F, -3.2F));
        items.register("sugilite_shovel", new GDGenericShovel(TOOL_SUGILITE));
        items.register("ixiolite_sword", new GDGenericSword(TOOL_IXIOLITE));
        items.register("ixiolite_pickaxe", new GDGenericPickaxe(TOOL_IXIOLITE));
        items.register("ixiolite_axe", new GDGenericAxe(TOOL_IXIOLITE, 6F, -3.2F));
        items.register("ixiolite_shovel", new GDGenericShovel(TOOL_IXIOLITE));
        items.register("euclase_sword", new GDGenericSword(TOOL_EUCLASE));
        items.register("euclase_pickaxe", new GDGenericPickaxe(TOOL_EUCLASE));
        items.register("euclase_axe", new GDGenericAxe(TOOL_EUCLASE, 6F, -3.2F));
        items.register("euclase_shovel", new GDGenericShovel(TOOL_EUCLASE));
        items.register("carnelian_sword", new GDGenericSword(TOOL_CARNELIAN));
        items.register("carnelian_pickaxe", new GDGenericPickaxe(TOOL_CARNELIAN));
        items.register("carnelian_axe", new GDGenericAxe(TOOL_CARNELIAN, 6F, -3.2F));
        items.register("carnelian_shovel", new GDGenericShovel(TOOL_CARNELIAN));
        items.register("benitoite_sword", new GDGenericSword(TOOL_BENITOITE));
        items.register("benitoite_pickaxe", new GDGenericPickaxe(TOOL_BENITOITE));
        items.register("benitoite_axe", new GDGenericAxe(TOOL_BENITOITE, 6F, -3.2F));
        items.register("benitoite_shovel", new GDGenericShovel(TOOL_BENITOITE));
        items.register("chalcedony_sword", new GDGenericSword(TOOL_CHALCEDONY));
        items.register("chalcedony_pickaxe", new GDGenericPickaxe(TOOL_CHALCEDONY));
        items.register("chalcedony_axe", new GDGenericAxe(TOOL_CHALCEDONY, 6F, -3.2F));
        items.register("chalcedony_shovel", new GDGenericShovel(TOOL_CHALCEDONY));
        items.register("old_bow", new GDBow());
        items.register("agate_arrow", new GDAgateArrow());

        items.register("sugilite_helmet", new GDSugiliteArmor(ARMOR_SUGILITE, HEAD));
        items.register("sugilite_chestplate", new GDSugiliteArmor(ARMOR_SUGILITE, CHEST));
        items.register("sugilite_legs", new GDSugiliteArmor(ARMOR_SUGILITE, LEGS));
        items.register("sugilite_boots", new GDSugiliteArmor(ARMOR_SUGILITE, FEET));
        items.register("proustite_helmet", new GDProustiteArmor(ARMOR_PROUSTITE, HEAD));
        items.register("proustite_chestplate", new GDProustiteArmor(ARMOR_PROUSTITE, CHEST));
        items.register("proustite_legs", new GDProustiteArmor(ARMOR_PROUSTITE, LEGS));
        items.register("proustite_boots", new GDProustiteArmor(ARMOR_PROUSTITE, FEET));
        items.register("leucite_helmet", new GDLeuciteArmor(ARMOR_LEUCITE, HEAD));
        items.register("leucite_chestplate", new GDLeuciteArmor(ARMOR_LEUCITE, CHEST));
        items.register("leucite_legs", new GDLeuciteArmor(ARMOR_LEUCITE, LEGS));
        items.register("leucite_boots", new GDLeuciteArmor(ARMOR_LEUCITE, FEET));
        items.register("carnelian_helmet", new GDCarnelianArmor(ARMOR_CARNELIAN, HEAD));
        items.register("carnelian_chestplate", new GDCarnelianArmor(ARMOR_CARNELIAN, CHEST));
        items.register("carnelian_legs", new GDCarnelianArmor(ARMOR_CARNELIAN, LEGS));
        items.register("carnelian_boots", new GDCarnelianArmor(ARMOR_CARNELIAN, FEET));
        items.register("diopside_helmet", new GDDiopsideArmor(ARMOR_DIOPSIDE, HEAD));
        items.register("diopside_chestplate", new GDDiopsideArmor(ARMOR_DIOPSIDE, CHEST));
        items.register("diopside_legs", new GDDiopsideArmor(ARMOR_DIOPSIDE, LEGS));
        items.register("diopside_boots", new GDDiopsideArmor(ARMOR_DIOPSIDE, FEET));
        items.register("chalcedony_helmet", new GDChalcedonyArmor(ARMOR_CHALCEDONY, HEAD));
        items.register("chalcedony_chestplate", new GDChalcedonyArmor(ARMOR_CHALCEDONY, CHEST));
        items.register("chalcedony_legs", new GDChalcedonyArmor(ARMOR_CHALCEDONY, LEGS));
        items.register("chalcedony_boots", new GDChalcedonyArmor(ARMOR_CHALCEDONY, FEET));

        items.register("malachite_guard_headgear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, HEAD));
        items.register("malachite_guard_brace", new GDMalachiteGuardArmor(ARMOR_MALACHITE, CHEST));
        items.register("malachite_guard_gear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, LEGS));
        items.register("malachite_guard_boots", new GDMalachiteGuardArmor(ARMOR_MALACHITE, FEET));
        items.register("apex_predator_hood", new GDApexPredatorArmor(ARMOR_TIGER_EYE, HEAD));
        items.register("apex_predator_jacket", new GDApexPredatorArmor(ARMOR_TIGER_EYE, CHEST));
        items.register("apex_predator_trousers", new GDApexPredatorArmor(ARMOR_TIGER_EYE, LEGS));
        items.register("apex_predator_boots", new GDApexPredatorArmor(ARMOR_TIGER_EYE, FEET));
        items.register("spinel_princess_cowl", new GDSpinelPrincessArmor(ARMOR_SPINEL, HEAD));
        items.register("spinel_princess_cloak", new GDSpinelPrincessArmor(ARMOR_SPINEL, CHEST));
        items.register("spinel_princess_dress", new GDSpinelPrincessArmor(ARMOR_SPINEL, LEGS));
        items.register("spinel_princess_heels", new GDSpinelPrincessArmor(ARMOR_SPINEL, FEET));
        items.register("zircon_prince_crown", new GDZirconPrinceArmor(ARMOR_ZIRCON, HEAD));
        items.register("zircon_prince_chestpiece", new GDZirconPrinceArmor(ARMOR_ZIRCON, CHEST));
        items.register("zircon_prince_gear", new GDZirconPrinceArmor(ARMOR_ZIRCON, LEGS));
        items.register("zircon_prince_boots", new GDZirconPrinceArmor(ARMOR_ZIRCON, FEET));
        items.register("corrupt_warrior_helm", new GDCorruptWarriorArmor(ARMOR_CORRUPT, HEAD));
        items.register("corrupt_warrior_guard", new GDCorruptWarriorArmor(ARMOR_CORRUPT, CHEST));
        items.register("corrupt_warrior_greaves", new GDCorruptWarriorArmor(ARMOR_CORRUPT, LEGS));
        items.register("corrupt_warrior_boots", new GDCorruptWarriorArmor(ARMOR_CORRUPT, FEET));
        items.register("gaia_duchess_helm", new GDGaiaDuchessArmor(ARMOR_BIXBITE, HEAD));
        items.register("gaia_duchess_guard", new GDGaiaDuchessArmor(ARMOR_BIXBITE, CHEST));
        items.register("gaia_duchess_greaves", new GDGaiaDuchessArmor(ARMOR_BIXBITE, LEGS));
        items.register("gaia_duchess_boots", new GDGaiaDuchessArmor(ARMOR_BIXBITE, FEET));
        items.register("gaia_baron_mask", new GDGaiaBaronArmor(ARMOR_TSAVORITE, HEAD));
        items.register("gaia_baron_tuxedo", new GDGaiaBaronArmor(ARMOR_TSAVORITE,CHEST));
        items.register("gaia_baron_pants", new GDGaiaBaronArmor(ARMOR_TSAVORITE, LEGS));
        items.register("gaia_baron_shoes", new GDGaiaBaronArmor(ARMOR_TSAVORITE, FEET));
        items.register("gaia_duke_helm", new GDGaiaDukeArmor(ARMOR_LARVIKITE, HEAD));
        items.register("gaia_duke_guard", new GDGaiaDukeArmor(ARMOR_LARVIKITE, CHEST));
        items.register("gaia_duke_greaves", new GDGaiaDukeArmor(ARMOR_LARVIKITE, LEGS));
        items.register("gaia_duke_boots", new GDGaiaDukeArmor(ARMOR_LARVIKITE, FEET));
        items.register("gaia_champion_helm", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, HEAD));
        items.register("gaia_champion_guard", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, CHEST));
        items.register("gaia_champion_greaves", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, LEGS));
        items.register("gaia_champion_boots", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, FEET));

        items.register("malachite_guard_baton", new GDMalachiteGuardSword(TOOL_MALACHITE));
        items.register("apex_predator_mace", new GDApexPredatorSword(TOOL_TIGER_EYE));
        items.register("spinel_princess_flamberge", new GDSpinelPrincessSword(TOOL_SPINEL));
        items.register("zircon_prince_razor", new GDZirconPrinceSword(TOOL_ZIRCON));
        items.register("corrupt_warrior_sword", new GDCorruptWarriorSword(TOOL_CORRUPT));
        items.register("gaia_duchess_khopesh", new GDGaiaDuchessSword(TOOL_BIXBITE));
        items.register("gaia_baron_dagger", new GDGaiaBaronSword(TOOL_TSAVORITE));
        items.register("gaia_duke_blade", new GDGaiaDukeSword(TOOL_LARVIKITE));
        items.register("gaia_champion_sword", new GDGaiaChampSword(TOOL_GAIA_CHAMP));

        /*
         * Blocks
         */

        //Utility Blocks
        items.registerBlock(GDBlocks.gaia_portal);
        items.registerBlock(GDBlocks.keystone_block);
        items.registerBlock(GDBlocks.gold_fire);
        items.registerBlock(GDBlocks.pyrite_torch);
        items.registerBlock(GDBlocks.agate_crafting_table);
        items.registerBlock(GDBlocks.crude_storage_crate);
        items.registerBlock(GDBlocks.mega_storage_crate);
        items.registerBlock(GDBlocks.gaia_stone_furnace_idle);
        items.registerBlock(GDBlocks.gaia_stone_furnace_lit); //Fallback
        items.registerBlock(GDBlocks.restructurer_idle);
        items.registerBlock(GDBlocks.restructurer_lit); //Fallback
        items.registerBlock(GDBlocks.purifier_idle);
        items.registerBlock(GDBlocks.purifier_lit); //Fallback

        //Natural Blocks
        items.registerBlock(GDBlocks.heavy_soil);
        items.registerBlock(GDBlocks.corrupt_soil);
        items.registerBlock(GDBlocks.glitter_grass);
        items.registerBlock(GDBlocks.corrupt_grass);
        items.registerBlock(GDBlocks.frail_glitter_block);
        items.registerBlock(GDBlocks.thick_glitter_block);
        items.registerBlock(GDBlocks.gummy_glitter_block);
        items.registerBlock(GDBlocks.pink_sludge_block);

        //Plants
        items.registerBlock(GDBlocks.crystal_growth);
        items.registerBlock(GDBlocks.crystal_growth_red);
        items.registerBlock(GDBlocks.crystal_growth_black);
        items.registerBlock(GDBlocks.crystal_growth_seared);
        items.registerBlock(GDBlocks.crystal_growth_mutant);
        items.registerBlock(GDBlocks.thiscus);
        items.registerBlock(GDBlocks.ouzium);
        items.registerBlock(GDBlocks.agathum);
        items.registerBlock(GDBlocks.varloom);
        items.registerBlock(GDBlocks.corrupt_varloom);
        items.registerBlock(GDBlocks.missingno_plant);
        items.registerBlock(GDBlocks.spotted_kersei);
        items.registerBlock(GDBlocks.thorny_wiltha);
        items.registerBlock(GDBlocks.roofed_agaric);
        items.registerBlock(GDBlocks.bulbous_hobina);
        items.registerBlock(GDBlocks.stickly_cupsir);
        items.registerBlock(GDBlocks.mystical_murgni);
        items.registerBlock(GDBlocks.corrupted_gaia_eye);
        //items.registerBlock(GDBlocks.sacred_gaia_eye);
        items.registerBlock(GDBlocks.elder_imklia);
        items.registerBlock(GDBlocks.gold_orb_tucher);
        items.registerBlock(GDBlocks.missingno_fungus);

        items.registerBlock(GDBlocks.pink_agate_sapling);
        items.registerBlock(GDBlocks.blue_agate_sapling);
        items.registerBlock(GDBlocks.green_agate_sapling);
        items.registerBlock(GDBlocks.purple_agate_sapling);
        items.registerBlock(GDBlocks.fossilized_sapling);
        items.registerBlock(GDBlocks.corrupted_sapling);
        items.registerBlock(GDBlocks.burnt_sapling);
        items.registerBlock(GDBlocks.burning_sapling);
        items.registerBlock(GDBlocks.pink_agate_leaves);
        items.registerBlock(GDBlocks.blue_agate_leaves);
        items.registerBlock(GDBlocks.green_agate_leaves);
        items.registerBlock(GDBlocks.purple_agate_leaves);
        items.registerBlock(GDBlocks.fossilized_leaves);
        items.registerBlock(GDBlocks.corrupted_leaves);
        items.registerBlock(GDBlocks.burnt_leaves);
        items.registerBlock(GDBlocks.burning_leaves);
        items.registerBlock(GDBlocks.pink_agate_log);
        items.registerBlock(GDBlocks.blue_agate_log);
        items.registerBlock(GDBlocks.green_agate_log);
        items.registerBlock(GDBlocks.purple_agate_log);
        items.registerBlock(GDBlocks.fossilized_log);
        items.registerBlock(GDBlocks.corrupted_log);
        items.registerBlock(GDBlocks.burnt_log);
        items.registerBlock(GDBlocks.burning_log);
        items.registerBlock(GDBlocks.salt);
        items.registerBlock(GDBlocks.saltstone);
        items.registerBlock(GDBlocks.pebbles);
        items.registerBlock(GDBlocks.gaia_stone);
        items.registerBlock(GDBlocks.gaia_cobblestone);
        items.registerBlock(GDBlocks.wasteland_stone);
        items.registerBlock(GDBlocks.static_stone);
        items.registerBlock(GDBlocks.charged_mineral);
        items.registerBlock(GDBlocks.volcanic_rock);
        items.registerBlock(GDBlocks.searing_rock);
        items.registerBlock(GDBlocks.primal_mass);

        //Planks
        items.registerBlock(GDBlocks.pink_agate_planks);
        items.registerBlock(GDBlocks.blue_agate_planks);
        items.registerBlock(GDBlocks.green_agate_planks);
        items.registerBlock(GDBlocks.purple_agate_planks);
        items.registerBlock(GDBlocks.fossilized_planks);
        items.registerBlock(GDBlocks.corrupted_planks);
        items.registerBlock(GDBlocks.burnt_planks);
        items.registerBlock(GDBlocks.burning_planks);
        items.register(new ItemSlab(GDBlocks.pink_agate_plank_slab, GDBlocks.pink_agate_plank_slab, GDBlocks.double_pink_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.blue_agate_plank_slab, GDBlocks.blue_agate_plank_slab, GDBlocks.double_blue_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.green_agate_plank_slab, GDBlocks.green_agate_plank_slab, GDBlocks.double_green_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.purple_agate_plank_slab, GDBlocks.purple_agate_plank_slab, GDBlocks.double_purple_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.fossilized_plank_slab, GDBlocks.fossilized_plank_slab, GDBlocks.double_fossilized_plank_slab));
        items.register(new ItemSlab(GDBlocks.corrupted_plank_slab, GDBlocks.corrupted_plank_slab, GDBlocks.double_corrupted_plank_slab));
        items.register(new ItemSlab(GDBlocks.burnt_plank_slab, GDBlocks.burnt_plank_slab, GDBlocks.double_burnt_plank_slab));
        items.register(new ItemSlab(GDBlocks.burning_plank_slab, GDBlocks.burning_plank_slab, GDBlocks.double_burning_plank_slab));
        items.registerBlock(GDBlocks.pink_agate_plank_stairs);
        items.registerBlock(GDBlocks.blue_agate_plank_stairs);
        items.registerBlock(GDBlocks.green_agate_plank_stairs);
        items.registerBlock(GDBlocks.purple_agate_plank_stairs);
        items.registerBlock(GDBlocks.fossilized_plank_stairs);
        items.registerBlock(GDBlocks.corrupted_plank_stairs);
        items.registerBlock(GDBlocks.burnt_plank_stairs);
        items.registerBlock(GDBlocks.burning_plank_stairs);

        items.registerBlock(GDBlocks.cloudy_glass);
        items.registerBlock(GDBlocks.foggy_glass);
        items.registerBlock(GDBlocks.gaia_stone_bricks);
        items.registerBlock(GDBlocks.cracked_gaia_stone_bricks);
        items.registerBlock(GDBlocks.crusted_gaia_stone_bricks);
        items.registerBlock(GDBlocks.reinforced_bricks);
        items.registerBlock(GDBlocks.malachite_bricks);
        items.registerBlock(GDBlocks.malachite_cracked_bricks);
        items.registerBlock(GDBlocks.malachite_crusted_bricks);
        items.registerBlock(GDBlocks.malachite_floor_tiles);
        items.registerBlock(GDBlocks.malachite_chisel_bricks);
        items.registerBlock(GDBlocks.malachite_pulsing_bricks);
        items.registerBlock(GDBlocks.malachite_pulsing_tiles);
        items.registerBlock(GDBlocks.malachite_pulsing_chisel);
        items.register(new ItemSlab(GDBlocks.malachite_brick_slab, GDBlocks.malachite_brick_slab, GDBlocks.double_malachite_brick_slab));
        items.register(new ItemSlab(GDBlocks.malachite_floor_slab, GDBlocks.malachite_floor_slab, GDBlocks.double_malachite_floor_slab));
        items.registerBlock(GDBlocks.malachite_pillar);
        items.registerBlock(GDBlocks.malachite_brick_stairs);
        items.registerBlock(GDBlocks.malachite_chisel_stairs);
        items.registerBlock(GDBlocks.malachite_pulsing_brick_stairs);
        items.registerBlock(GDBlocks.malachite_pillar_stairs);
        items.registerBlock(GDBlocks.malachite_floor_stairs);
        items.registerBlock(GDBlocks.malachite_pulsing_floor_stairs);
        items.registerBlock(GDBlocks.malachite_pulsing_chisel_stairs);
        items.registerBlock(GDBlocks.bolstered_bricks);

        items.registerBlock(GDBlocks.opal_block_red);
        items.registerBlock(GDBlocks.opal_block_blue);
        items.registerBlock(GDBlocks.opal_block_green);
        items.registerBlock(GDBlocks.opal_block_white);
        items.registerBlock(GDBlocks.sugilite_block);
        items.registerBlock(GDBlocks.hematite_block);
        items.registerBlock(GDBlocks.labradorite_block);
        items.registerBlock(GDBlocks.pyrite_block);
        items.registerBlock(GDBlocks.moonstone_block);
        items.registerBlock(GDBlocks.cinnabar_block);
        items.registerBlock(GDBlocks.tektite_block);
        items.registerBlock(GDBlocks.goldstone_block);
        items.registerBlock(GDBlocks.aura_block);
        items.registerBlock(GDBlocks.bismuth_block);
        items.registerBlock(GDBlocks.ixiolite_block);
        items.registerBlock(GDBlocks.proustite_block);
        items.registerBlock(GDBlocks.euclase_block);
        items.registerBlock(GDBlocks.leucite_block);
        items.registerBlock(GDBlocks.carnelian_block);
        items.registerBlock(GDBlocks.benitoite_block);
        items.registerBlock(GDBlocks.diopside_block);
        items.registerBlock(GDBlocks.chalcedony_block);

        items.registerBlock(GDBlocks.sugilite_ore);
        items.registerBlock(GDBlocks.hematite_ore);
        items.registerBlock(GDBlocks.pyrite_ore);
        items.registerBlock(GDBlocks.opal_ore_red);
        items.registerBlock(GDBlocks.opal_ore_blue);
        items.registerBlock(GDBlocks.opal_ore_green);
        items.registerBlock(GDBlocks.opal_ore_white);
        items.registerBlock(GDBlocks.labradorite_ore);
        items.registerBlock(GDBlocks.moonstone_ore);
        items.registerBlock(GDBlocks.cinnabar_ore);
        items.registerBlock(GDBlocks.speckled_rock);
        items.registerBlock(GDBlocks.coarse_rock);
        items.registerBlock(GDBlocks.precious_rock);
    }

    public static class ItemRegistryHelper {
        private final IForgeRegistry<Item> registry;

        private static List<ModelRegisterCallback> itemModels = new ArrayList<>();

        public static List<ModelRegisterCallback> getItemModels() {
            return ImmutableList.copyOf(itemModels);
        }

        ItemRegistryHelper(IForgeRegistry<Item> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Item item) {
            item.setRegistryName(GaiaDimension.MODID, registryName);
            item.setTranslationKey(GaiaDimension.MODID + "." + registryName);

            if (item instanceof ModelRegisterCallback) {
                itemModels.add((ModelRegisterCallback) item);
            }
            registry.register(item);
        }

        private void registerBlock(Block block) {
            ItemBlock metaItemBlock = new ItemBlock(block);
            register(metaItemBlock);
        }

        private void register(ItemBlock item) {
            item.setRegistryName(item.getBlock().getRegistryName());
            item.setTranslationKey(item.getBlock().getTranslationKey());
            registry.register(item);
        }
    }
}
