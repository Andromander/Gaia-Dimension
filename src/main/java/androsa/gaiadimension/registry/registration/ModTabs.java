package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GaiaDimensionMod.MODID);

    public static final RegistryObject<CreativeModeTab> GAIA_BLOCKS = CREATIVE_TABS.register("gaia_blocks", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gaiadimension.gaia_blocks"))
            .icon(() -> new ItemStack(ModBlocks.glitter_grass.get()))
            .displayItems((parameters, output) -> {
                add(output, ModBlocks.keystone_block);
                add(output, ModBlocks.heavy_soil, ModBlocks.corrupted_soil, ModBlocks.boggy_soil, ModBlocks.light_soil, ModBlocks.aurum_soil);
                add(output, ModBlocks.glitter_grass, ModBlocks.corrupted_grass, ModBlocks.murky_grass, ModBlocks.soft_grass, ModBlocks.gilded_grass);
                add(output, ModBlocks.crystal_growth, ModBlocks.crystal_growth_red, ModBlocks.crystal_growth_black, ModBlocks.crystal_growth_seared, ModBlocks.crystal_growth_mutant, ModBlocks.crystal_growth_aura, ModBlocks.golden_grass, ModBlocks.tall_golden_grass);
                add(output, ModBlocks.thiscus, ModBlocks.ouzium, ModBlocks.varloom, ModBlocks.corrupted_varloom, ModBlocks.agathum, ModBlocks.glamelea, ModBlocks.missingno_plant);
                add(output, ModBlocks.spotted_kersei, ModBlocks.thorny_wiltha, ModBlocks.roofed_agaric, ModBlocks.bulbous_hobina, ModBlocks.stickly_cupsir, ModBlocks.mystical_murgni, ModBlocks.corrupted_gaia_eye, ModBlocks.twinkling_gilsri, ModBlocks.elder_imklia, ModBlocks.gold_orb_tucher, ModBlocks.missingno_fungus);
                add(output, ModBlocks.aura_shoot, ModBlocks.golden_vine, ModBlocks.sombre_cacti, ModBlocks.sombre_shrub);
                add(output, ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling, ModBlocks.fossilized_sapling, ModBlocks.corrupted_sapling, ModBlocks.burnt_sapling, ModBlocks.fire_agate_sapling, ModBlocks.aura_sapling, ModBlocks.golden_sapling);
                add(output, ModBlocks.pink_agate_leaves, ModBlocks.blue_agate_leaves, ModBlocks.green_agate_leaves, ModBlocks.purple_agate_leaves, ModBlocks.fossilized_leaves, ModBlocks.corrupted_leaves, ModBlocks.burnt_leaves, ModBlocks.fire_agate_leaves, ModBlocks.aura_leaves, ModBlocks.golden_leaves);
                add(output, ModBlocks.pink_agate_log, ModBlocks.blue_agate_log, ModBlocks.green_agate_log, ModBlocks.purple_agate_log, ModBlocks.fossilized_log, ModBlocks.corrupted_log, ModBlocks.burnt_log, ModBlocks.fire_agate_log, ModBlocks.aura_log, ModBlocks.golden_log);
                add(output, ModBlocks.stripped_pink_agate_log, ModBlocks.stripped_blue_agate_log, ModBlocks.stripped_green_agate_log, ModBlocks.stripped_purple_agate_log, ModBlocks.stripped_fossilized_log, ModBlocks.stripped_corrupted_log, ModBlocks.stripped_burnt_log, ModBlocks.stripped_fire_agate_log, ModBlocks.stripped_aura_log, ModBlocks.stripped_golden_log);
                add(output, ModBlocks.pink_agate_wood, ModBlocks.blue_agate_wood, ModBlocks.green_agate_wood, ModBlocks.purple_agate_wood, ModBlocks.fossilized_wood, ModBlocks.corrupted_wood, ModBlocks.burnt_wood, ModBlocks.fire_agate_wood, ModBlocks.aura_wood, ModBlocks.golden_wood);
                add(output, ModBlocks.stripped_pink_agate_wood, ModBlocks.stripped_blue_agate_wood, ModBlocks.stripped_green_agate_wood, ModBlocks.stripped_purple_agate_wood, ModBlocks.stripped_fossilized_wood, ModBlocks.stripped_corrupted_wood, ModBlocks.stripped_burnt_wood, ModBlocks.stripped_fire_agate_wood, ModBlocks.stripped_aura_wood, ModBlocks.stripped_golden_wood);
                add(output, ModBlocks.frail_glitter_block, ModBlocks.thick_glitter_block, ModBlocks.gummy_glitter_block, ModBlocks.pink_sludge_block);
                add(output, ModBlocks.pyrite_torch);
                add(output, ModBlocks.agate_crafting_table, ModBlocks.crude_storage_crate, ModBlocks.mega_storage_crate, ModBlocks.gaia_stone_furnace, ModBlocks.restructurer, ModBlocks.purifier);
                add(output, ModBlocks.salt, ModBlocks.saltstone);
                add(output, ModBlocks.wasteland_stone, ModBlocks.static_stone, ModBlocks.charged_mineral);
                add(output, ModBlocks.volcanic_rock, ModBlocks.searing_rock);
                add(output, ModBlocks.impure_rock, ModBlocks.active_rock, ModBlocks.impure_sludge, ModBlocks.geyser_block);
                add(output, ModBlocks.sparkling_rock);
                add(output, ModBlocks.scarlet_mookaite, ModBlocks.auburn_mookaite, ModBlocks.gold_mookaite, ModBlocks.mauve_mookaite, ModBlocks.beige_mookaite, ModBlocks.ivory_mookaite);
                add(output, ModBlocks.golden_sand, ModBlocks.aurum_mud, ModBlocks.golden_stone, ModBlocks.tough_golden_stone, ModBlocks.brilliant_stone, ModBlocks.gilded_brilliant_stone);
                add(output, ModBlocks.pebbles, ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone, ModBlocks.primal_mass, ModBlocks.nexustone);
                add(output, ModBlocks.speckled_rock, ModBlocks.coarse_rock, ModBlocks.precious_rock, ModBlocks.scarlet_opalite_ore, ModBlocks.auburn_opalite_ore, ModBlocks.gold_opalite_ore, ModBlocks.mauve_opalite_ore, ModBlocks.beige_opalite_ore, ModBlocks.ivory_opalite_ore, ModBlocks.pyrite_ore, ModBlocks.sugilite_ore, ModBlocks.hematite_ore, ModBlocks.cinnabar_ore, ModBlocks.labradorite_ore, ModBlocks.moonstone_ore, ModBlocks.red_opal_ore, ModBlocks.blue_opal_ore, ModBlocks.green_opal_ore, ModBlocks.white_opal_ore);
                add(output, ModBlocks.pink_agate_tiles, ModBlocks.pink_agate_tile_stairs, ModBlocks.pink_agate_tile_slab);
                add(output, ModBlocks.blue_agate_tiles, ModBlocks.blue_agate_tile_stairs, ModBlocks.blue_agate_tile_slab);
                add(output, ModBlocks.green_agate_tiles, ModBlocks.green_agate_tile_stairs, ModBlocks.green_agate_tile_slab);
                add(output, ModBlocks.purple_agate_tiles, ModBlocks.purple_agate_tile_stairs, ModBlocks.purple_agate_tile_slab);
                add(output, ModBlocks.fossilized_tiles, ModBlocks.fossilized_tile_stairs, ModBlocks.fossilized_tile_slab);
                add(output, ModBlocks.corrupted_tiles, ModBlocks.corrupted_tile_stairs, ModBlocks.corrupted_tile_slab);
                add(output, ModBlocks.burnt_tiles, ModBlocks.burnt_tile_stairs, ModBlocks.burnt_tile_slab);
                add(output, ModBlocks.fire_agate_tiles, ModBlocks.fire_agate_tile_stairs, ModBlocks.fire_agate_tile_slab);
                add(output, ModBlocks.aura_tiles, ModBlocks.aura_tile_stairs, ModBlocks.aura_tile_slab);
                add(output, ModBlocks.golden_tiles, ModBlocks.golden_tile_stairs, ModBlocks.golden_tile_slab);
                add(output, ModBlocks.cloudy_glass, ModBlocks.foggy_glass);
                add(output, ModBlocks.gaia_stone_bricks, ModBlocks.cracked_gaia_stone_bricks, ModBlocks.crusted_gaia_stone_bricks, ModBlocks.reinforced_bricks, ModBlocks.bolstered_bricks);
                add(output, ModBlocks.raw_jade, ModBlocks.jade_bricks, ModBlocks.jade_brick_stairs, ModBlocks.jade_brick_slab, ModBlocks.cracked_jade_bricks, ModBlocks.cracked_jade_brick_stairs, ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_bricks, ModBlocks.crusted_jade_brick_stairs, ModBlocks.crusted_jade_brick_slab);
                add(output, ModBlocks.raw_copal, ModBlocks.copal_bricks, ModBlocks.copal_brick_stairs, ModBlocks.copal_brick_slab, ModBlocks.cracked_copal_bricks, ModBlocks.cracked_copal_brick_stairs, ModBlocks.cracked_copal_brick_slab, ModBlocks.crusted_copal_bricks, ModBlocks.crusted_copal_brick_stairs, ModBlocks.crusted_copal_brick_slab);
                add(output, ModBlocks.raw_jet, ModBlocks.jet_bricks, ModBlocks.jet_brick_stairs, ModBlocks.jet_brick_slab, ModBlocks.cracked_jet_bricks, ModBlocks.cracked_jet_brick_stairs, ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_bricks, ModBlocks.crusted_jet_brick_stairs, ModBlocks.crusted_jet_brick_slab);
                add(output, ModBlocks.raw_amethyst, ModBlocks.amethyst_bricks, ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_brick_slab, ModBlocks.cracked_amethyst_bricks, ModBlocks.cracked_amethyst_brick_stairs, ModBlocks.cracked_amethyst_brick_slab, ModBlocks.crusted_amethyst_bricks, ModBlocks.crusted_amethyst_brick_stairs, ModBlocks.crusted_amethyst_brick_slab);
                add(output, ModBlocks.malachite_bricks, ModBlocks.malachite_cracked_bricks, ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_chisel_bricks, ModBlocks.malachite_tiles, ModBlocks.malachite_pillar);
                add(output, ModBlocks.malachite_pulsing_bricks, ModBlocks.malachite_pulsing_chisel, ModBlocks.malachite_pulsing_tiles);
                add(output, ModBlocks.malachite_brick_stairs, ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_tile_stairs, ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_pillar_stairs);
                add(output, ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pulsing_floor_stairs);
                add(output, ModBlocks.malachite_brick_slab, ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_tile_slab);
                add(output, ModBlocks.pyrite_block, ModBlocks.sugilite_block, ModBlocks.hematite_block, ModBlocks.cinnabar_block, ModBlocks.labradorite_block, ModBlocks.moonstone_block, ModBlocks.red_opal_block, ModBlocks.blue_opal_block, ModBlocks.green_opal_block, ModBlocks.white_opal_block);
                add(output, ModBlocks.goldstone_block, ModBlocks.bismuth_block, ModBlocks.aura_block, ModBlocks.tektite_block, ModBlocks.opalite_block);
                add(output, ModBlocks.ixiolite_block, ModBlocks.proustite_block, ModBlocks.euclase_block, ModBlocks.leucite_block, ModBlocks.carnelian_block, ModBlocks.benitoite_block, ModBlocks.diopside_block, ModBlocks.chalcedony_block);
            }).build());

    //Tab for generic Items
    public static final RegistryObject<CreativeModeTab> GAIA_ITEMS = CREATIVE_TABS.register("gaia_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gaiadimension.gaia_items"))
            .withTabsBefore(GAIA_BLOCKS.getId())
            .icon(() -> new ItemStack(ModItems.hematite.get()))
            .displayItems((parameters, output) -> {
                add(output, ModItems.crystallized_redstone, ModItems.crystallized_lapis_lazuli, ModItems.glint_and_gold);
                add(output, ModItems.hot_dust, ModItems.goldstone_dust, ModItems.fine_dust);
                add(output, ModItems.crystal_shard, ModItems.agate_stick, ModItems.agate_fabric, ModItems.sugar_crystals, ModItems.sugar_cluster, ModItems.sturdy_pebble, ModItems.shiny_bone, ModItems.fine_thread, ModItems.sweet_muckball, ModItems.cloudy_shard);
                add(output, ModItems.agate_cup, ModItems.twined_thread, ModItems.pink_essence, ModItems.pink_goo, ModItems.scaynyx_ingot);
                add(output, ModItems.goldstone_residue, ModItems.goldstone, ModItems.bismuth_residue, ModItems.bismuth_crystal, ModItems.aura_residue, ModItems.aura_cluster, ModItems.black_residue, ModItems.tektite);
                add(output, ModItems.opalite, ModItems.pyrite, ModItems.sugilite, ModItems.hematite, ModItems.cinnabar, ModItems.labradorite, ModItems.moonstone, ModItems.red_opal, ModItems.blue_opal, ModItems.green_opal, ModItems.white_opal);
                add(output, ModItems.ixiolite, ModItems.proustite, ModItems.euclase, ModItems.leucite, ModItems.carnelian, ModItems.benitoite, ModItems.diopside, ModItems.chalcedony);
                add(output, ModItems.pyrite_powder, ModItems.hematite_powder, ModItems.cinnabar_powder, ModItems.labradorite_powder, ModItems.moonstone_powder, ModItems.red_opal_powder, ModItems.blue_opal_powder, ModItems.green_opal_powder, ModItems.white_opal_grit);
                add(output, ModItems.tilipi, ModItems.tilibl, ModItems.tiligr, ModItems.tilipu, ModItems.tiliol, ModItems.tilimy, ModItems.plagued_tiliey, ModItems.tiliou);
                add(output, ModItems.pink_geode, ModItems.blue_geode, ModItems.green_geode, ModItems.purple_geode);
                add(output, ModItems.pink_geode_slice, ModItems.blue_geode_slice, ModItems.green_geode_slice, ModItems.purple_geode_slice, ModItems.pink_geode_juice, ModItems.blue_geode_tea, ModItems.green_geode_ale, ModItems.purple_geode_soda, ModItems.pearly_geode_elixir);
                add(output, ModItems.markuzar_mint, ModItems.luggeroth_chop, ModItems.cooked_luggeroth_chop, ModItems.lurmorus_meat, ModItems.lurmorus_steak, ModItems.small_tentacle, ModItems.small_calamari, ModItems.large_tentacle, ModItems.large_calamari);
                add(output, ModItems.gemstone_pouch, ModItems.construct_charm, ModItems.blank_kit);
                addKits(output, ModItems.repair_kit, ModItems.scarlet_augment_kit, ModItems.auburn_augment_kit, ModItems.gold_augment_kit, ModItems.mauve_augment_kit, ModItems.beige_augment_kit, ModItems.ivory_augment_kit, ModItems.scarlet_replace_kit, ModItems.auburn_replace_kit, ModItems.gold_replace_kit, ModItems.mauve_replace_kit, ModItems.beige_replace_kit, ModItems.ivory_replace_kit);
                add(output, ModItems.scaynyx_bucket, ModItems.mineral_water_bucket, ModItems.superhot_magma_bucket, ModItems.sweet_muck_bucket, ModItems.liquid_bismuth_bucket , ModItems.liquid_aura_bucket);
                add(output, ModItems.mock_malachite);
                add(output, ModItems.growth_sapper_spawn_egg, ModItems.nomadic_lagrahk_spawn_egg, ModItems.shallow_arenthis_spawn_egg, ModItems.mineral_arenthis_spawn_egg);
                add(output, ModItems.agate_golem_spawn_egg, ModItems.crystal_golem_spawn_egg, ModItems.howlite_wolf_spawn_egg, ModItems.markuzar_plant_spawn_egg, ModItems.spellbound_elemental_spawn_egg, ModItems.rocky_luggeroth_spawn_egg, ModItems.rugged_lurmorus_spawn_egg, ModItems.ancient_lagrahk_spawn_egg, ModItems.mutant_growth_extractor_spawn_egg, ModItems.lesser_shockshooter_spawn_egg, ModItems.lesser_spitfire_spawn_egg, ModItems.saltion_spawn_egg, ModItems.corrupt_sapper_spawn_egg, ModItems.contorted_naga_spawn_egg, ModItems.bismuth_uletrus_spawn_egg, ModItems.mookaite_construct_spawn_egg, ModItems.opalite_construct_spawn_egg);
                add(output, ModItems.shalurker_spawn_egg, ModItems.archaic_warrior_spawn_egg, ModItems.cavern_tick_spawn_egg, ModItems.muckling_spawn_egg, ModItems.primal_beast_spawn_egg);
                add(output, ModItems.malachite_drone_spawn_egg);
                add(output, ModItems.blue_howlite_wolf_spawn_egg, ModItems.malachite_guard_spawn_egg);
            }).build());

    //Tab for Tools, including Swords. Basically anything able to be used in combat
    //Yes, I'm saying that Hoes can be used for combat
    public static final RegistryObject<CreativeModeTab> GAIA_TOOLS = CREATIVE_TABS.register("gaia_tools", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gaiadimension.gaia_tools"))
            .withTabsBefore(GAIA_ITEMS.getId())
            .icon(() -> new ItemStack(ModItems.gaia_champion_sword.get()))
            .displayItems((parameters, output) -> {
                add(output, ModItems.old_bow, ModItems.agate_arrow);
                add(output, ModItems.agate_sword, ModItems.agate_pickaxe, ModItems.agate_axe, ModItems.agate_shovel);
                add(output, ModItems.sugilite_sword, ModItems.sugilite_pickaxe, ModItems.sugilite_axe, ModItems.sugilite_shovel);
                add(output, ModItems.ixiolite_sword, ModItems.ixiolite_pickaxe, ModItems.ixiolite_axe, ModItems.ixiolite_shovel);
                add(output, ModItems.euclase_sword, ModItems.euclase_pickaxe, ModItems.euclase_axe, ModItems.euclase_shovel);
                add(output, ModItems.carnelian_sword, ModItems.carnelian_pickaxe, ModItems.carnelian_axe, ModItems.carnelian_shovel);
                add(output, ModItems.benitoite_sword, ModItems.benitoite_pickaxe, ModItems.benitoite_axe, ModItems.benitoite_shovel);
                add(output, ModItems.chalcedony_sword, ModItems.chalcedony_pickaxe, ModItems.chalcedony_axe, ModItems.chalcedony_shovel);
                add(output, ModItems.malachite_guard_baton, ModItems.apex_predator_mace, ModItems.spinel_princess_flamberge, ModItems.zircon_prince_razor, ModItems.corrupt_warrior_sword, ModItems.gaia_duchess_khopesh, ModItems.gaia_baron_dagger, ModItems.gaia_duke_blade, ModItems.gaia_champion_sword);
            }).build());

    //Tab for anything wearable
    //Look, I'm just going to say that I will not look at Baubles, but decorative Armor
    public static final RegistryObject<CreativeModeTab> GAIA_ARMOR = CREATIVE_TABS.register("gaia_armor", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gaiadimension.gaia_armor"))
            .withTabsBefore(GAIA_TOOLS.getId())
            .icon(() -> new ItemStack(ModItems.gaia_champion_helm.get()))
            .displayItems((parameters, output) -> {
                add(output, ModItems.sugilite_helmet, ModItems.sugilite_chestplate, ModItems.sugilite_legs, ModItems.sugilite_boots);
                add(output, ModItems.proustite_helmet, ModItems.proustite_chestplate, ModItems.proustite_legs, ModItems.proustite_boots);
                add(output, ModItems.leucite_helmet, ModItems.leucite_chestplate, ModItems.leucite_legs, ModItems.leucite_boots);
                add(output, ModItems.carnelian_helmet, ModItems.carnelian_chestplate, ModItems.carnelian_legs, ModItems.carnelian_boots);
                add(output, ModItems.diopside_helmet, ModItems.diopside_chestplate, ModItems.diopside_legs, ModItems.diopside_boots);
                add(output, ModItems.chalcedony_helmet, ModItems.chalcedony_chestplate, ModItems.chalcedony_legs, ModItems.chalcedony_boots);
                add(output, ModItems.malachite_guard_headgear, ModItems.malachite_guard_brace, ModItems.malachite_guard_gear, ModItems.malachite_guard_boots);
                add(output, ModItems.apex_predator_hood, ModItems.apex_predator_jacket, ModItems.apex_predator_trousers, ModItems.apex_predator_boots);
                add(output, ModItems.spinel_princess_cowl, ModItems.spinel_princess_cloak, ModItems.spinel_princess_dress, ModItems.spinel_princess_heels);
                add(output, ModItems.zircon_prince_crown, ModItems.zircon_prince_chestpiece, ModItems.zircon_prince_gear, ModItems.zircon_prince_boots);
                add(output, ModItems.corrupt_warrior_helm, ModItems.corrupt_warrior_guard, ModItems.corrupt_warrior_greaves, ModItems.corrupt_warrior_boots);
                add(output, ModItems.gaia_duchess_helm, ModItems.gaia_duchess_guard, ModItems.gaia_duchess_greaves, ModItems.gaia_duchess_boots);
                add(output, ModItems.gaia_baron_mask, ModItems.gaia_baron_tuxedo, ModItems.gaia_baron_pants, ModItems.gaia_baron_shoes);
                add(output, ModItems.gaia_duke_helm, ModItems.gaia_duke_guard, ModItems.gaia_duke_greaves, ModItems.gaia_duke_boots);
                add(output, ModItems.gaia_champion_helm, ModItems.gaia_champion_guard, ModItems.gaia_champion_greaves, ModItems.gaia_champion_boots);
            }).build());

    @SafeVarargs
    private static void add(CreativeModeTab.Output output, RegistryObject<? extends ItemLike>... objects) {
        for (RegistryObject<? extends ItemLike> object : objects) {
            output.accept(object.get());
        }
    }

    @SafeVarargs
    private static void addKits(CreativeModeTab.Output output, RegistryObject<? extends Item>... items) {
        for (RegistryObject<? extends Item> item : items) {
            ItemStack stack = new ItemStack(item.get());
            stack.getOrCreateTag();
            if (!stack.getTag().contains("Part")) {
                stack.getTag().putInt("Part", 0);
            }
            output.accept(stack);
        }
    }
}
