package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static androsa.gaiadimension.registry.GDItems.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

@Mod.EventBusSubscriber()
public class GDItemsRegister {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ItemRegistryHelper items = new ItemRegistryHelper(event.getRegistry());

        items.register("glint_and_gold", new GDFlintAndGold().setUnlocalizedName("glint_and_gold"));
        items.register("agate_stick", new GDItem().setUnlocalizedName("agate_stick"));
        items.register("hot_dust", new GDItem().setUnlocalizedName("hot_dust"));
        items.register("goldstone_dust", new GDItem().setUnlocalizedName("goldstone_dust"));
        items.register("fine_dust", new GDItem().setUnlocalizedName("fine_dust"));
        items.register("cloudy_shard", new GDItem().setUnlocalizedName("cloudy_shard"));
        items.register("goldstone_residue", new GDItem().setUnlocalizedName("goldstone_residue"));
        //items.register("scaynyx_ingot", new GDItem().setUnlocalizedName("scaynyx_ingot"));

        items.register("pink_geode", new GDGeode().setUnlocalizedName("pink_geode"));
        items.register("blue_geode", new GDGeode().setUnlocalizedName("blue_geode"));
        items.register("green_geode", new GDGeode().setUnlocalizedName("green_geode"));
        items.register("purple_geode", new GDGeode().setUnlocalizedName("purple_geode"));
        items.register("pink_geode_slice", new GDFood(3, 0.5F, false).setUnlocalizedName("pink_geode_slice"));
        items.register("blue_geode_slice", new GDFood(4, 0.4F, false).setUnlocalizedName("blue_geode_slice"));
        items.register("green_geode_slice", new GDFood(5, 0.3F, false).setUnlocalizedName("green_geode_slice"));
        items.register("purple_geode_slice", new GDFood(6, 0.2F, false).setUnlocalizedName("purple_geode_slice"));
        items.register("lurmorus_meat", new GDFood(4, 0.4F, false).setUnlocalizedName("lurmorus_meat"));
        items.register("lurmorus_steak", new GDFood(9, 0.9F, false).setUnlocalizedName("lurmorus_steak"));
        //items.register("markuzar_mint", new GDFood(1, 0.2F, false).setUnlocalizedName("markuzar_mint"));

        items.register("hematite_powder", new GDGroundGem().setUnlocalizedName("hematite_powder"));
        items.register("pyrite_powder", new GDGroundGem().setUnlocalizedName("pyrite_powder"));
        items.register("labradorite_powder", new GDGroundGem().setUnlocalizedName("labradorite_powder"));
        items.register("moonstone_powder", new GDGroundGem().setUnlocalizedName("moonstone_powder"));
        items.register("cinnabar_powder", new GDGroundGem().setUnlocalizedName("cinnabar_powder"));
        items.register("red_opal_powder", new GDGroundGem().setUnlocalizedName("red_opal_powder"));
        items.register("blue_opal_powder", new GDGroundGem().setUnlocalizedName("blue_opal_powder"));
        items.register("green_opal_powder", new GDGroundGem().setUnlocalizedName("green_opal_powder"));
        items.register("white_opal_grit", new GDGroundGem().setUnlocalizedName("white_opal_grit"));

        items.register("sugilite", new GDItem().setUnlocalizedName("sugilite"));
        items.register("hematite", new GDItem().setUnlocalizedName("hematite"));
        items.register("pyrite", new GDItem().setUnlocalizedName("pyrite"));
        items.register("labradorite", new GDItem().setUnlocalizedName("labradorite"));
        items.register("moonstone", new GDItem().setUnlocalizedName("moonstone"));
        items.register("cinnabar", new GDItem().setUnlocalizedName("cinnabar"));
        items.register("red_opal", new GDItem().setUnlocalizedName("red_opal"));
        items.register("blue_opal", new GDItem().setUnlocalizedName("blue_opal"));
        items.register("green_opal", new GDItem().setUnlocalizedName("green_opal"));
        items.register("white_opal", new GDItem().setUnlocalizedName("white_opal"));
        items.register("ixiolite", new GDItem().setUnlocalizedName("ixiolite"));
        items.register("proustite", new GDItem().setUnlocalizedName("proustite"));
        items.register("euclase", new GDItem().setUnlocalizedName("euclase"));
        items.register("leucite", new GDItem().setUnlocalizedName("leucite"));
        items.register("carnelian", new GDItem().setUnlocalizedName("carnelian"));
        items.register("benitoite", new GDItem().setUnlocalizedName("benitoite"));
        items.register("diopside", new GDItem().setUnlocalizedName("diopside"));
        items.register("chalcedony", new GDItem().setUnlocalizedName("chalcedony"));
        items.register("black_residue", new GDItem().setUnlocalizedName("black_residue"));
        items.register("tektite", new GDItem().setUnlocalizedName("tektite"));
        items.register("goldstone", new GDItem().setUnlocalizedName("goldstone"));

        items.register("agate_sword", new GDGenericSword(TOOL_AGATE).setUnlocalizedName("agate_sword").setMaxStackSize(1));
        items.register("agate_pickaxe", new GDGenericPickaxe(TOOL_AGATE).setUnlocalizedName("agate_pickaxe").setMaxStackSize(1));
        items.register("agate_axe", new GDAgateAxe(TOOL_AGATE).setUnlocalizedName("agate_axe").setMaxStackSize(1));
        items.register("agate_shovel", new GDGenericShovel(TOOL_AGATE).setUnlocalizedName("agate_shovel").setMaxStackSize(1));
        items.register("sugilite_sword", new GDGenericSword(TOOL_SUGILITE).setUnlocalizedName("sugilite_sword").setMaxStackSize(1));
        items.register("sugilite_pickaxe", new GDGenericPickaxe(TOOL_SUGILITE).setUnlocalizedName("sugilite_pickaxe").setMaxStackSize(1));
        items.register("sugilite_axe", new GDSugiliteAxe(TOOL_SUGILITE).setUnlocalizedName("sugilite_axe").setMaxStackSize(1));
        items.register("sugilite_shovel", new GDGenericShovel(TOOL_SUGILITE).setUnlocalizedName("sugilite_shovel").setMaxStackSize(1));
        items.register("ixiolite_sword", new GDGenericSword(TOOL_IXIOLITE).setUnlocalizedName("ixiolite_sword").setMaxStackSize(1));
        items.register("ixiolite_pickaxe", new GDGenericPickaxe(TOOL_IXIOLITE).setUnlocalizedName("ixiolite_pickaxe").setMaxStackSize(1));
        items.register("ixiolite_axe", new GDIxioliteAxe(TOOL_IXIOLITE).setUnlocalizedName("ixiolite_axe").setMaxStackSize(1));
        items.register("ixiolite_shovel", new GDGenericShovel(TOOL_IXIOLITE).setUnlocalizedName("ixiolite_shovel").setMaxStackSize(1));
        items.register("euclase_sword", new GDGenericSword(TOOL_EUCLASE).setUnlocalizedName("euclase_sword").setMaxStackSize(1));
        items.register("euclase_pickaxe", new GDGenericPickaxe(TOOL_EUCLASE).setUnlocalizedName("euclase_pickaxe").setMaxStackSize(1));
        items.register("euclase_axe", new GDEuclaseAxe(TOOL_EUCLASE).setUnlocalizedName("euclase_axe").setMaxStackSize(1));
        items.register("euclase_shovel", new GDGenericShovel(TOOL_EUCLASE).setUnlocalizedName("euclase_shovel").setMaxStackSize(1));

        items.register("malachite_guard_headgear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, HEAD).setUnlocalizedName("malachite_guard_headgear").setMaxStackSize(1));
        items.register("malachite_guard_brace", new GDMalachiteGuardArmor(ARMOR_MALACHITE, CHEST).setUnlocalizedName("malachite_guard_brace").setMaxStackSize(1));
        items.register("malachite_guard_gear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, LEGS).setUnlocalizedName("malachite_guard_gear").setMaxStackSize(1));
        items.register("malachite_guard_boots", new GDMalachiteGuardArmor(ARMOR_MALACHITE, FEET).setUnlocalizedName("malachite_guard_boots").setMaxStackSize(1));
        items.register("apex_predator_hood", new GDApexPredatorArmor(ARMOR_TIGER_EYE, HEAD).setUnlocalizedName("apex_predator_hood").setMaxStackSize(1));
        items.register("apex_predator_jacket", new GDApexPredatorArmor(ARMOR_TIGER_EYE, CHEST).setUnlocalizedName("apex_predator_jacket").setMaxStackSize(1));
        items.register("apex_predator_trousers", new GDApexPredatorArmor(ARMOR_TIGER_EYE, LEGS).setUnlocalizedName("apex_predator_trousers").setMaxStackSize(1));
        items.register("apex_predator_boots", new GDApexPredatorArmor(ARMOR_TIGER_EYE, FEET).setUnlocalizedName("apex_predator_boots").setMaxStackSize(1));
        items.register("spinel_princess_cowl", new GDSpinelPrincessArmor(ARMOR_SPINEL, HEAD).setUnlocalizedName("spinel_princess_cowl").setMaxStackSize(1));
        items.register("spinel_princess_cloak", new GDSpinelPrincessArmor(ARMOR_SPINEL, CHEST).setUnlocalizedName("spinel_princess_cloak").setMaxStackSize(1));
        items.register("spinel_princess_dress", new GDSpinelPrincessArmor(ARMOR_SPINEL, LEGS).setUnlocalizedName("spinel_princess_dress").setMaxStackSize(1));
        items.register("spinel_princess_heels", new GDSpinelPrincessArmor(ARMOR_SPINEL, FEET).setUnlocalizedName("spinel_princess_heel").setMaxStackSize(1));
        items.register("zircon_prince_crown", new GDZirconPrinceArmor(ARMOR_ZIRCON, HEAD).setUnlocalizedName("zircon_prince_crown").setMaxStackSize(1));
        items.register("zircon_prince_chestpiece", new GDZirconPrinceArmor(ARMOR_ZIRCON, CHEST).setUnlocalizedName("zircon_prince_chestpiece").setMaxStackSize(1));
        items.register("zircon_prince_gear", new GDZirconPrinceArmor(ARMOR_ZIRCON, LEGS).setUnlocalizedName("zircon_prince_gear").setMaxStackSize(1));
        items.register("zircon_prince_boots", new GDZirconPrinceArmor(ARMOR_ZIRCON, FEET).setUnlocalizedName("zircon_prince_boots").setMaxStackSize(1));
        items.register("corrupt_warrior_helm", new GDCorruptWarriorArmor(ARMOR_CORRUPT, HEAD).setUnlocalizedName("corrupt_warrior_helm").setMaxStackSize(1));
        items.register("corrupt_warrior_guard", new GDCorruptWarriorArmor(ARMOR_CORRUPT, CHEST).setUnlocalizedName("corrupt_warrior_guard").setMaxStackSize(1));
        items.register("corrupt_warrior_greaves", new GDCorruptWarriorArmor(ARMOR_CORRUPT, LEGS).setUnlocalizedName("corrupt_warrior_greaves").setMaxStackSize(1));
        items.register("corrupt_warrior_boots", new GDCorruptWarriorArmor(ARMOR_CORRUPT, FEET).setUnlocalizedName("corrupt_warrior_boots").setMaxStackSize(1));
        items.register("gaia_duchess_helm", new GDGaiaDuchessArmor(ARMOR_BIXBITE, HEAD).setUnlocalizedName("gaia_duchess_helm").setMaxStackSize(1));
        items.register("gaia_duchess_guard", new GDGaiaDuchessArmor(ARMOR_BIXBITE, CHEST).setUnlocalizedName("gaia_duchess_guard").setMaxStackSize(1));
        items.register("gaia_duchess_greaves", new GDGaiaDuchessArmor(ARMOR_BIXBITE, LEGS).setUnlocalizedName("gaia_duchess_greaves").setMaxStackSize(1));
        items.register("gaia_duchess_boots", new GDGaiaDuchessArmor(ARMOR_BIXBITE, FEET).setUnlocalizedName("gaia_duchess_boots").setMaxStackSize(1));
        items.register("gaia_baron_mask", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, HEAD).setUnlocalizedName("gaia_baron_mask").setMaxStackSize(1));
        items.register("gaia_baron_tuxedo", new GDGaiaBaronArmor(ARMOR_TSVAROVITE,CHEST).setUnlocalizedName("gaia_baron_tuxedo").setMaxStackSize(1));
        items.register("gaia_baron_pants", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, LEGS).setUnlocalizedName("gaia_baron_pants").setMaxStackSize(1));
        items.register("gaia_baron_shoes", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, FEET).setUnlocalizedName("gaia_baron_shoes").setMaxStackSize(1));
        items.register("gaia_duke_helm", new GDGaiaDukeArmor(ARMOR_LARVIKITE, HEAD).setUnlocalizedName("gaia_duke_helm").setMaxStackSize(1));
        items.register("gaia_duke_guard", new GDGaiaDukeArmor(ARMOR_LARVIKITE, CHEST).setUnlocalizedName("gaia_duke_guard").setMaxStackSize(1));
        items.register("gaia_duke_greaves", new GDGaiaDukeArmor(ARMOR_LARVIKITE, LEGS).setUnlocalizedName("gaia_duke_greaves").setMaxStackSize(1));
        items.register("gaia_duke_boots", new GDGaiaDukeArmor(ARMOR_LARVIKITE, FEET).setUnlocalizedName("gaia_duke_boots").setMaxStackSize(1));
        items.register("gaia_champion_helm", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, HEAD).setUnlocalizedName("gaia_champion_helm").setMaxStackSize(1));
        items.register("gaia_champion_guard", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, CHEST).setUnlocalizedName("gaia_champion_guard").setMaxStackSize(1));
        items.register("gaia_champion_greaves", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, LEGS).setUnlocalizedName("gaia_champion_greaves").setMaxStackSize(1));
        items.register("gaia_champion_boots", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, FEET).setUnlocalizedName("gaia_champion_boots").setMaxStackSize(1));

        items.register("malachite_guard_sword", new GDMalachiteGuardSword(TOOL_MALACHITE).setUnlocalizedName("malachite_guard_baton").setMaxStackSize(1));
        items.register("apex_predator_sword", new GDApexPredatorSword(TOOL_TIGER_EYE).setUnlocalizedName("apex_predator_mace").setMaxStackSize(1));
        items.register("spinel_princess_sword", new GDSpinelPrincessSword(TOOL_SPINEL).setUnlocalizedName("spinel_princess_flamberge").setMaxStackSize(1));
        items.register("zircon_prince_sword", new GDZirconPrinceSword(TOOL_ZIRCON).setUnlocalizedName("zircon_prince_razor").setMaxStackSize(1));
        items.register("corrupt_warrior_sword", new GDCorruptWarriorSword(TOOL_CORRUPT).setUnlocalizedName("corrupt_warrior_sword").setMaxStackSize(1));
        items.register("gaia_duchess_sword", new GDGaiaDuchessSword(TOOL_BIXBITE).setUnlocalizedName("gaia_duchess_khopesh").setMaxStackSize(1));
        items.register("gaia_baron_sword", new GDGaiaBaronSword(TOOL_TSVAROVITE).setUnlocalizedName("gaia_baron_dagger").setMaxStackSize(1));
        items.register("gaia_duke_sword", new GDGaiaDukeSword(TOOL_LARVIKITE).setUnlocalizedName("gaia_duke_blade").setMaxStackSize(1));
        items.register("gaia_champion_sword", new GDGaiaChampSword(TOOL_GAIA_CHAMP).setUnlocalizedName("gaia_champion_sword").setMaxStackSize(1));

        /* Blocks
         *
         */

        //Utility Blocks
        items.registerSubItemBlock(GDBlocks.gaia_portal);
        items.registerBlock(GDBlocks.gold_fire);
        items.registerBlock(GDBlocks.pyrite_torch);
        items.registerBlock(GDBlocks.agate_crafting_table);
        items.registerBlock(GDBlocks.gaia_stone_furnace_idle);
        items.registerBlock(GDBlocks.gaia_stone_furnace_lit); //Fallback
        items.registerBlock(GDBlocks.glitter_furnace_idle);
        items.registerBlock(GDBlocks.glitter_furnace_lit); //Fallback
        items.registerBlock(GDBlocks.purifier_idle);
        items.registerBlock(GDBlocks.purifier_lit); //Fallback

        //Natural Blocks
        items.registerBlock(GDBlocks.heavy_soil);
        items.registerBlock(GDBlocks.corrupt_soil);
        items.registerBlock(GDBlocks.glitter_grass);
        items.registerBlock(GDBlocks.cool_grass);
        items.registerBlock(GDBlocks.verdant_grass);
        items.registerBlock(GDBlocks.scented_grass);
        items.registerBlock(GDBlocks.old_grass);
        items.registerBlock(GDBlocks.corrupt_grass);
        items.registerBlock(GDBlocks.singed_grass);
        items.registerBlock(GDBlocks.mutated_grass);
        items.registerBlock(GDBlocks.frail_glitter_block);
        items.registerBlock(GDBlocks.thick_glitter_block);
        items.registerBlock(GDBlocks.gummy_glitter_block);

        //Crystal Growth
        items.registerBlock(GDBlocks.crystal_growth_pink);
        items.registerBlock(GDBlocks.crystal_growth_blue);
        items.registerBlock(GDBlocks.crystal_growth_green);
        items.registerBlock(GDBlocks.crystal_growth_purple);
        items.registerBlock(GDBlocks.crystal_growth_old);
        items.registerBlock(GDBlocks.crystal_growth_red);
        items.registerBlock(GDBlocks.crystal_growth_black);
        items.registerBlock(GDBlocks.crystal_growth_seared);
        items.registerBlock(GDBlocks.crystal_growth_mutant);

        //Crystal Bloom
        items.registerBlock(GDBlocks.thiscus);
        items.registerBlock(GDBlocks.ouzium);
        items.registerBlock(GDBlocks.agathum);
        items.registerBlock(GDBlocks.varloom);
        items.registerBlock(GDBlocks.corrupt_varloom);
        items.registerBlock(GDBlocks.missingno_plant);

        items.registerSubItemBlock(GDBlocks.gaia_sapling);
        items.registerSubItemBlock(GDBlocks.gaia_leaves);
        items.registerSubItemBlock(GDBlocks.special_gaia_leaves);
        items.registerSubItemBlock(GDBlocks.gaia_log);
        items.registerSubItemBlock(GDBlocks.special_gaia_log);
        items.registerBlock(GDBlocks.salt);
        items.registerBlock(GDBlocks.saltstone);
        items.registerBlock(GDBlocks.gaia_stone);
        items.registerBlock(GDBlocks.gaia_cobblestone);
        items.registerBlock(GDBlocks.wasteland_stone);
        items.registerBlock(GDBlocks.static_stone);
        items.registerBlock(GDBlocks.charged_mineral);
        items.registerBlock(GDBlocks.volcanic_rock);
        items.registerBlock(GDBlocks.searing_rock);

        //Planks
        items.registerBlock(GDBlocks.pink_agate_planks);
        items.registerBlock(GDBlocks.blue_agate_planks);
        items.registerBlock(GDBlocks.green_agate_planks);
        items.registerBlock(GDBlocks.purple_agate_planks);
        items.registerBlock(GDBlocks.fossilized_planks);
        items.registerBlock(GDBlocks.corrupted_planks);
        items.registerBlock(GDBlocks.crusty_planks);
        items.registerBlock(GDBlocks.heated_planks);
        items.register(new ItemSlab(GDBlocks.pink_agate_plank_slab, GDBlocks.pink_agate_plank_slab, GDBlocks.double_pink_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.blue_agate_plank_slab, GDBlocks.blue_agate_plank_slab, GDBlocks.double_blue_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.green_agate_plank_slab, GDBlocks.green_agate_plank_slab, GDBlocks.double_green_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.purple_agate_plank_slab, GDBlocks.purple_agate_plank_slab, GDBlocks.double_purple_agate_plank_slab));
        items.register(new ItemSlab(GDBlocks.fossilized_plank_slab, GDBlocks.fossilized_plank_slab, GDBlocks.double_fossilized_plank_slab));
        items.register(new ItemSlab(GDBlocks.corrupted_plank_slab, GDBlocks.corrupted_plank_slab, GDBlocks.double_corrupted_plank_slab));
        items.register(new ItemSlab(GDBlocks.crusty_plank_slab, GDBlocks.crusty_plank_slab, GDBlocks.double_crusty_plank_slab));
        items.register(new ItemSlab(GDBlocks.heated_plank_slab, GDBlocks.heated_plank_slab, GDBlocks.double_heated_plank_slab));
        items.registerBlock(GDBlocks.pink_agate_plank_stairs);
        items.registerBlock(GDBlocks.blue_agate_plank_stairs);
        items.registerBlock(GDBlocks.green_agate_plank_stairs);
        items.registerBlock(GDBlocks.purple_agate_plank_stairs);
        items.registerBlock(GDBlocks.fossilized_plank_stairs);
        items.registerBlock(GDBlocks.corrupted_plank_stairs);
        items.registerBlock(GDBlocks.crusty_plank_stairs);
        items.registerBlock(GDBlocks.heated_plank_stairs);

        items.registerBlock(GDBlocks.gaia_stone_bricks);
        items.registerBlock(GDBlocks.cracked_gaia_stone_bricks);
        items.registerBlock(GDBlocks.crusted_gaia_stone_bricks);
        items.registerBlock(GDBlocks.reinforced_bricks);
        items.registerSubItemBlock(GDBlocks.malachite_bricks);
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
        items.registerSubItemBlock(GDBlocks.opal_ore);
        items.registerBlock(GDBlocks.labradorite_ore);
        items.registerBlock(GDBlocks.moonstone_ore);
        items.registerBlock(GDBlocks.cinnabar_ore);
    }

    private static class ItemRegistryHelper {
        private final IForgeRegistry<Item> registry;

        ItemRegistryHelper(IForgeRegistry<Item> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Item item) {
            item.setRegistryName(GaiaDimension.MODID, registryName);
            registry.register(item);
        }

        private void registerBlock(Block block) {
            ItemBlock metaItemBlock = new ItemBlock(block);
            register(metaItemBlock);
        }

        private void registerSubItemBlock(Block block) {
            registerSubItemBlock(block, true);
        }

        private void registerSubItemBlock(Block block, boolean shouldAppendNumber) {
            GDItemBlockMeta metaItemBlock = new GDItemBlockMeta(block).setAppend(shouldAppendNumber);
            register(metaItemBlock);
        }

        private void register(ItemBlock item) {
            item.setRegistryName(item.getBlock().getRegistryName());
            item.setUnlocalizedName(item.getBlock().getUnlocalizedName());
            registry.register(item);
        }
    }
}
