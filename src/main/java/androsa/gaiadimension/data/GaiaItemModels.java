package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaItemModelProvider;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GaiaItemModels extends GaiaItemModelProvider {

    public GaiaItemModels(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, helper);
    }

    @Override
    public String getName() {
        return "Gaia Item Models";
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.keystone_block);
        blockItemTexture(ModBlocks.pyrite_torch);
        blockItem(ModBlocks.agate_crafting_table);
        blockItem(ModBlocks.crude_storage_crate);
        blockItem(ModBlocks.mega_storage_crate);
        blockItem(ModBlocks.gaia_stone_furnace);
        blockItem(ModBlocks.restructurer);
        blockItem(ModBlocks.purifier);
        blockItem(ModBlocks.heavy_soil);
        blockItem(ModBlocks.corrupt_soil);
        blockItem(ModBlocks.boggy_soil);
        blockItem(ModBlocks.light_soil);
        blockItem(ModBlocks.glitter_grass);
        blockItem(ModBlocks.corrupt_grass);
        blockItem(ModBlocks.murky_grass);
        blockItem(ModBlocks.soft_grass);
        blockItem(ModBlocks.frail_glitter_block);
        blockItem(ModBlocks.thick_glitter_block);
        blockItem(ModBlocks.gummy_glitter_block);
        blockItem(ModBlocks.pink_sludge_block);
        blockItemTexture(ModBlocks.crystal_growth);
        blockItemTexture(ModBlocks.crystal_growth_red);
        blockItemTexture(ModBlocks.crystal_growth_black);
        blockItemTexture(ModBlocks.crystal_growth_seared);
        blockItemTexture(ModBlocks.crystal_growth_mutant);
        blockItemTexture(ModBlocks.crystal_growth_aura);
        blockItemTexture(ModBlocks.thiscus);
        blockItemTexture(ModBlocks.ouzium);
        blockItemTexture(ModBlocks.agathum);
        blockItemTexture(ModBlocks.varloom);
        blockItemTexture(ModBlocks.corrupted_varloom);
        blockItemTexture(ModBlocks.missingno_plant);
        blockItemTexture(ModBlocks.spotted_kersei);
        blockItemTexture(ModBlocks.thorny_wiltha);
        blockItemTexture(ModBlocks.roofed_agaric);
        blockItemTexture(ModBlocks.bulbous_hobina);
        blockItemTexture(ModBlocks.stickly_cupsir);
        blockItemTexture(ModBlocks.mystical_murgni);
        blockItemTexture(ModBlocks.corrupted_gaia_eye);
        blockItemTexture(ModBlocks.elder_imklia);
        blockItemTexture(ModBlocks.gold_orb_tucher);
        blockItemTexture(ModBlocks.missingno_fungus);
        blockItemTexture(ModBlocks.pink_agate_sapling);
        blockItemTexture(ModBlocks.blue_agate_sapling);
        blockItemTexture(ModBlocks.green_agate_sapling);
        blockItemTexture(ModBlocks.purple_agate_sapling);
        blockItemTexture(ModBlocks.fossilized_sapling);
        blockItemTexture(ModBlocks.corrupted_sapling);
        blockItemTexture(ModBlocks.burnt_sapling);
        blockItemTexture(ModBlocks.burning_sapling);
        blockItemTexture(ModBlocks.aura_sapling);
        blockItem(ModBlocks.pink_agate_leaves);
        blockItem(ModBlocks.blue_agate_leaves);
        blockItem(ModBlocks.green_agate_leaves);
        blockItem(ModBlocks.purple_agate_leaves);
        blockItem(ModBlocks.fossilized_leaves);
        blockItem(ModBlocks.corrupted_leaves);
        blockItem(ModBlocks.burnt_leaves);
        blockItem(ModBlocks.burning_leaves);
        blockItem(ModBlocks.aura_leaves);
        blockItem(ModBlocks.pink_agate_log);
        blockItem(ModBlocks.blue_agate_log);
        blockItem(ModBlocks.green_agate_log);
        blockItem(ModBlocks.purple_agate_log);
        blockItem(ModBlocks.fossilized_log);
        blockItem(ModBlocks.corrupted_log);
        blockItem(ModBlocks.burnt_log);
        blockItem(ModBlocks.burning_log);
        blockItem(ModBlocks.aura_log);
        blockItem(ModBlocks.stripped_pink_agate_log);
        blockItem(ModBlocks.stripped_blue_agate_log);
        blockItem(ModBlocks.stripped_green_agate_log);
        blockItem(ModBlocks.stripped_purple_agate_log);
        blockItem(ModBlocks.stripped_fossilized_log);
        blockItem(ModBlocks.stripped_corrupted_log);
        blockItem(ModBlocks.stripped_burnt_log);
        blockItem(ModBlocks.stripped_burning_log);
        blockItem(ModBlocks.stripped_aura_log);
        blockItem(ModBlocks.pink_agate_wood);
        blockItem(ModBlocks.blue_agate_wood);
        blockItem(ModBlocks.green_agate_wood);
        blockItem(ModBlocks.purple_agate_wood);
        blockItem(ModBlocks.fossilized_wood);
        blockItem(ModBlocks.corrupted_wood);
        blockItem(ModBlocks.burnt_wood);
        blockItem(ModBlocks.burning_wood);
        blockItem(ModBlocks.aura_wood);
        blockItem(ModBlocks.stripped_pink_agate_wood);
        blockItem(ModBlocks.stripped_blue_agate_wood);
        blockItem(ModBlocks.stripped_green_agate_wood);
        blockItem(ModBlocks.stripped_purple_agate_wood);
        blockItem(ModBlocks.stripped_fossilized_wood);
        blockItem(ModBlocks.stripped_corrupted_wood);
        blockItem(ModBlocks.stripped_burnt_wood);
        blockItem(ModBlocks.stripped_burning_wood);
        blockItem(ModBlocks.stripped_aura_wood);
        blockItem(ModBlocks.salt);
        blockItem(ModBlocks.saltstone);
        blockItem(ModBlocks.pebbles);
        blockItem(ModBlocks.gaia_stone);
        blockItem(ModBlocks.gaia_cobblestone);
        blockItem(ModBlocks.wasteland_stone);
        blockItem(ModBlocks.static_stone);
        blockItem(ModBlocks.charged_mineral);
        blockItem(ModBlocks.volcanic_rock);
        blockItem(ModBlocks.searing_rock);
        blockItem(ModBlocks.primal_mass);
        blockItem(ModBlocks.impure_rock);
        blockItem(ModBlocks.active_rock);
        blockItem(ModBlocks.impure_sludge);
        blockItem(ModBlocks.geyser_block);
        blockItem(ModBlocks.sparkling_rock);
        blockItem(ModBlocks.aura_shoot, "aura_shoot_top");
        blockItem(ModBlocks.pink_agate_planks);
        blockItem(ModBlocks.blue_agate_planks);
        blockItem(ModBlocks.green_agate_planks);
        blockItem(ModBlocks.purple_agate_planks);
        blockItem(ModBlocks.fossilized_planks);
        blockItem(ModBlocks.corrupted_planks);
        blockItem(ModBlocks.burnt_planks);
        blockItem(ModBlocks.burning_planks);
        blockItem(ModBlocks.aura_planks);
        blockItem(ModBlocks.pink_agate_plank_slab);
        blockItem(ModBlocks.blue_agate_plank_slab);
        blockItem(ModBlocks.green_agate_plank_slab);
        blockItem(ModBlocks.purple_agate_plank_slab);
        blockItem(ModBlocks.fossilized_plank_slab);
        blockItem(ModBlocks.corrupted_plank_slab);
        blockItem(ModBlocks.burnt_plank_slab);
        blockItem(ModBlocks.burning_plank_slab);
        blockItem(ModBlocks.aura_plank_slab);
        blockItem(ModBlocks.pink_agate_plank_stairs);
        blockItem(ModBlocks.blue_agate_plank_stairs);
        blockItem(ModBlocks.green_agate_plank_stairs);
        blockItem(ModBlocks.purple_agate_plank_stairs);
        blockItem(ModBlocks.fossilized_plank_stairs);
        blockItem(ModBlocks.corrupted_plank_stairs);
        blockItem(ModBlocks.burnt_plank_stairs);
        blockItem(ModBlocks.burning_plank_stairs);
        blockItem(ModBlocks.aura_plank_stairs);
        blockItem(ModBlocks.cloudy_glass);
        blockItem(ModBlocks.foggy_glass);
        blockItem(ModBlocks.gaia_stone_bricks);
        blockItem(ModBlocks.cracked_gaia_stone_bricks);
        blockItem(ModBlocks.crusted_gaia_stone_bricks);

        blockItem(ModBlocks.raw_jade);
        blockItem(ModBlocks.jade_bricks);
        blockItem(ModBlocks.jade_brick_slab);
        blockItem(ModBlocks.jade_brick_stairs);
        blockItem(ModBlocks.cracked_jade_bricks);
        blockItem(ModBlocks.cracked_jade_brick_slab);
        blockItem(ModBlocks.cracked_jade_brick_stairs);
        blockItem(ModBlocks.crusted_jade_bricks);
        blockItem(ModBlocks.crusted_jade_brick_slab);
        blockItem(ModBlocks.crusted_jade_brick_stairs);
        blockItem(ModBlocks.raw_copal);
        blockItem(ModBlocks.copal_bricks);
        blockItem(ModBlocks.copal_brick_slab);
        blockItem(ModBlocks.copal_brick_stairs);
        blockItem(ModBlocks.cracked_copal_bricks);
        blockItem(ModBlocks.cracked_copal_brick_slab);
        blockItem(ModBlocks.cracked_copal_brick_stairs);
        blockItem(ModBlocks.crusted_copal_bricks);
        blockItem(ModBlocks.crusted_copal_brick_slab);
        blockItem(ModBlocks.crusted_copal_brick_stairs);
        blockItem(ModBlocks.raw_jet);
        blockItem(ModBlocks.jet_bricks);
        blockItem(ModBlocks.jet_brick_slab);
        blockItem(ModBlocks.jet_brick_stairs);
        blockItem(ModBlocks.cracked_jet_bricks);
        blockItem(ModBlocks.cracked_jet_brick_slab);
        blockItem(ModBlocks.cracked_jet_brick_stairs);
        blockItem(ModBlocks.crusted_jet_bricks);
        blockItem(ModBlocks.crusted_jet_brick_slab);
        blockItem(ModBlocks.crusted_jet_brick_stairs);
        blockItem(ModBlocks.raw_amethyst);
        blockItem(ModBlocks.amethyst_bricks);
        blockItem(ModBlocks.amethyst_brick_slab);
        blockItem(ModBlocks.amethyst_brick_stairs);
        blockItem(ModBlocks.cracked_amethyst_bricks);
        blockItem(ModBlocks.cracked_amethyst_brick_slab);
        blockItem(ModBlocks.cracked_amethyst_brick_stairs);
        blockItem(ModBlocks.crusted_amethyst_bricks);
        blockItem(ModBlocks.crusted_amethyst_brick_slab);
        blockItem(ModBlocks.crusted_amethyst_brick_stairs);

        blockItem(ModBlocks.reinforced_bricks);
        blockItem(ModBlocks.bolstered_bricks);
        blockItem(ModBlocks.malachite_bricks);
        blockItem(ModBlocks.malachite_cracked_bricks);
        blockItem(ModBlocks.malachite_crusted_bricks);
        blockItem(ModBlocks.malachite_floor_tiles);
        blockItem(ModBlocks.malachite_chisel_bricks);
        blockItem(ModBlocks.malachite_pulsing_bricks);
        blockItem(ModBlocks.malachite_pulsing_tiles);
        blockItem(ModBlocks.malachite_pulsing_chisel);
        blockItem(ModBlocks.malachite_brick_slab);
        blockItem(ModBlocks.malachite_cracked_brick_slab);
        blockItem(ModBlocks.malachite_crusted_brick_slab);
        blockItem(ModBlocks.malachite_floor_slab);
        blockItem(ModBlocks.malachite_pillar);
        blockItem(ModBlocks.malachite_brick_stairs);
        blockItem(ModBlocks.malachite_cracked_brick_stairs);
        blockItem(ModBlocks.malachite_crusted_brick_stairs);
        blockItem(ModBlocks.malachite_floor_stairs);
        blockItem(ModBlocks.malachite_chisel_stairs);
        blockItem(ModBlocks.malachite_pulsing_brick_stairs);
        blockItem(ModBlocks.malachite_pulsing_floor_stairs);
        blockItem(ModBlocks.malachite_pulsing_chisel_stairs);
        blockItem(ModBlocks.malachite_pillar_stairs);
        blockItem(ModBlocks.sugilite_block);
        blockItem(ModBlocks.hematite_block);
        blockItem(ModBlocks.cinnabar_block);
        blockItem(ModBlocks.labradorite_block);
        blockItem(ModBlocks.moonstone_block);
        blockItem(ModBlocks.opal_block_red);
        blockItem(ModBlocks.opal_block_blue);
        blockItem(ModBlocks.opal_block_green);
        blockItem(ModBlocks.opal_block_white);
        blockItem(ModBlocks.pyrite_block);
        blockItem(ModBlocks.tektite_block);
        blockItem(ModBlocks.goldstone_block);
        blockItem(ModBlocks.aura_block);
        blockItem(ModBlocks.bismuth_block);
        blockItem(ModBlocks.ixiolite_block);
        blockItem(ModBlocks.proustite_block);
        blockItem(ModBlocks.euclase_block);
        blockItem(ModBlocks.leucite_block);
        blockItem(ModBlocks.carnelian_block);
        blockItem(ModBlocks.benitoite_block);
        blockItem(ModBlocks.diopside_block);
        blockItem(ModBlocks.chalcedony_block);
        blockItem(ModBlocks.sugilite_ore);
        blockItem(ModBlocks.hematite_ore);
        blockItem(ModBlocks.cinnabar_ore);
        blockItem(ModBlocks.labradorite_ore);
        blockItem(ModBlocks.moonstone_ore);
        blockItem(ModBlocks.opal_ore_red);
        blockItem(ModBlocks.opal_ore_blue);
        blockItem(ModBlocks.opal_ore_green);
        blockItem(ModBlocks.opal_ore_white);
        blockItem(ModBlocks.pyrite_ore);
        blockItem(ModBlocks.speckled_rock);
        blockItem(ModBlocks.coarse_rock);
        blockItem(ModBlocks.precious_rock);

        basicItem(ModItems.crystallized_redstone);
        basicItem(ModItems.crystallized_lapis_lazuli);
        basicItem(ModItems.glint_and_gold);
        heldItem(ModItems.agate_stick);
        basicItem(ModItems.hot_dust);
        basicItem(ModItems.goldstone_dust);
        basicItem(ModItems.fine_dust);
        basicItem(ModItems.cloudy_shard);
        basicItem(ModItems.agate_cup);
        basicItem(ModItems.scaynyx_ingot);
        basicItem(ModItems.sweet_muckball);
        basicItem(ModItems.sugar_crystals);
        basicItem(ModItems.sugar_cluster);
        heldItem(ModItems.shiny_bone);
        basicItem(ModItems.fine_thread);
        basicItem(ModItems.twined_thread);
        basicItem(ModItems.pink_essence);
        basicItem(ModItems.pink_goo);
        basicItem(ModItems.gemstone_pouch);
        basicItem(ModItems.agate_fabric);
        basicItem(ModItems.sturdy_pebble);
        basicItem(ModItems.scaynyx_bucket);
        basicItem(ModItems.mineral_water_bucket);
        basicItem(ModItems.superhot_magma_bucket);
        basicItem(ModItems.sweet_muck_bucket);
        basicItem(ModItems.liquid_bismuth_bucket);
        basicItem(ModItems.liquid_aura_bucket);
        geodeItem(ModItems.pink_geode);
        geodeItem(ModItems.blue_geode);
        geodeItem(ModItems.green_geode);
        geodeItem(ModItems.purple_geode);
        basicItem(ModItems.pink_geode_slice);
        basicItem(ModItems.blue_geode_slice);
        basicItem(ModItems.green_geode_slice);
        basicItem(ModItems.purple_geode_slice);
        basicItem(ModItems.pink_geode_juice);
        basicItem(ModItems.blue_geode_tea);
        basicItem(ModItems.green_geode_ale);
        basicItem(ModItems.purple_geode_soda);
        basicItem(ModItems.pearly_geode_elixir);
        basicItem(ModItems.lurmorus_meat);
        basicItem(ModItems.lurmorus_steak);
        basicItem(ModItems.small_tentacle);
        basicItem(ModItems.small_calamari);
        basicItem(ModItems.large_tentacle);
        basicItem(ModItems.large_calamari);
        basicItem(ModItems.markuzar_mint);
        basicItem(ModItems.luggeroth_chop);
        basicItem(ModItems.cooked_luggeroth_chop);
        basicItem(ModItems.tilipi);
        basicItem(ModItems.tilibl);
        basicItem(ModItems.tiligr);
        basicItem(ModItems.tilipu);
        basicItem(ModItems.tiliol);
        basicItem(ModItems.tilimy);
        basicItem(ModItems.plagued_tiliey);
        basicItem(ModItems.tiliou);
        basicItem(ModItems.hematite_powder);
        basicItem(ModItems.cinnabar_powder);
        basicItem(ModItems.labradorite_powder);
        basicItem(ModItems.moonstone_powder);
        basicItem(ModItems.red_opal_powder);
        basicItem(ModItems.blue_opal_powder);
        basicItem(ModItems.green_opal_powder);
        basicItem(ModItems.white_opal_grit);
        basicItem(ModItems.pyrite_powder);
        basicItem(ModItems.sugilite);
        basicItem(ModItems.hematite);
        basicItem(ModItems.cinnabar);
        basicItem(ModItems.labradorite);
        basicItem(ModItems.moonstone);
        basicItem(ModItems.red_opal);
        basicItem(ModItems.blue_opal);
        basicItem(ModItems.green_opal);
        basicItem(ModItems.white_opal);
        basicItem(ModItems.ixiolite);
        basicItem(ModItems.proustite);
        basicItem(ModItems.euclase);
        basicItem(ModItems.leucite);
        basicItem(ModItems.carnelian);
        basicItem(ModItems.benitoite);
        basicItem(ModItems.diopside);
        basicItem(ModItems.chalcedony);
        basicItem(ModItems.pyrite);
        basicItem(ModItems.black_residue);
        basicItem(ModItems.tektite);
        basicItem(ModItems.goldstone_residue);
        basicItem(ModItems.goldstone);
        basicItem(ModItems.aura_residue);
        basicItem(ModItems.aura_cluster);
        basicItem(ModItems.bismuth_residue);
        basicItem(ModItems.bismuth_crystal);
        basicItem(ModItems.sugilite_helmet);
        basicItem(ModItems.sugilite_chestplate);
        basicItem(ModItems.sugilite_legs);
        basicItem(ModItems.sugilite_boots);
        basicItem(ModItems.proustite_helmet);
        basicItem(ModItems.proustite_chestplate);
        basicItem(ModItems.proustite_legs);
        basicItem(ModItems.proustite_boots);
        basicItem(ModItems.leucite_helmet);
        basicItem(ModItems.leucite_chestplate);
        basicItem(ModItems.leucite_legs);
        basicItem(ModItems.leucite_boots);
        basicItem(ModItems.carnelian_helmet);
        basicItem(ModItems.carnelian_chestplate);
        basicItem(ModItems.carnelian_legs);
        basicItem(ModItems.carnelian_boots);
        basicItem(ModItems.diopside_helmet);
        basicItem(ModItems.diopside_chestplate);
        basicItem(ModItems.diopside_legs);
        basicItem(ModItems.diopside_boots);
        basicItem(ModItems.chalcedony_helmet);
        basicItem(ModItems.chalcedony_chestplate);
        basicItem(ModItems.chalcedony_legs);
        basicItem(ModItems.chalcedony_boots);
        basicItem(ModItems.malachite_guard_headgear);
        basicItem(ModItems.malachite_guard_brace);
        basicItem(ModItems.malachite_guard_gear);
        basicItem(ModItems.malachite_guard_boots);
        basicItem(ModItems.apex_predator_hood);
        basicItem(ModItems.apex_predator_jacket);
        basicItem(ModItems.apex_predator_trousers);
        basicItem(ModItems.apex_predator_boots);
        basicItem(ModItems.spinel_princess_cowl);
        basicItem(ModItems.spinel_princess_cloak);
        basicItem(ModItems.spinel_princess_dress);
        basicItem(ModItems.spinel_princess_heels);
        basicItem(ModItems.zircon_prince_crown);
        basicItem(ModItems.zircon_prince_chestpiece);
        basicItem(ModItems.zircon_prince_gear);
        basicItem(ModItems.zircon_prince_boots);
        basicItem(ModItems.corrupt_warrior_helm);
        basicItem(ModItems.corrupt_warrior_guard);
        basicItem(ModItems.corrupt_warrior_greaves);
        basicItem(ModItems.corrupt_warrior_boots);
        basicItem(ModItems.gaia_duchess_helm);
        basicItem(ModItems.gaia_duchess_guard);
        basicItem(ModItems.gaia_duchess_greaves);
        basicItem(ModItems.gaia_duchess_boots);
        basicItem(ModItems.gaia_baron_mask);
        basicItem(ModItems.gaia_baron_tuxedo);
        basicItem(ModItems.gaia_baron_pants);
        basicItem(ModItems.gaia_baron_shoes);
        basicItem(ModItems.gaia_duke_helm);
        basicItem(ModItems.gaia_duke_guard);
        basicItem(ModItems.gaia_duke_greaves);
        basicItem(ModItems.gaia_duke_boots);
        basicItem(ModItems.gaia_champion_helm);
        basicItem(ModItems.gaia_champion_guard);
        basicItem(ModItems.gaia_champion_greaves);
        basicItem(ModItems.gaia_champion_boots);
        heldItem(ModItems.agate_sword);
        heldItem(ModItems.agate_pickaxe);
        heldItem(ModItems.agate_axe);
        heldItem(ModItems.agate_shovel);
        heldItem(ModItems.sugilite_sword);
        heldItem(ModItems.sugilite_pickaxe);
        heldItem(ModItems.sugilite_axe);
        heldItem(ModItems.sugilite_shovel);
        heldItem(ModItems.ixiolite_sword);
        heldItem(ModItems.ixiolite_pickaxe);
        heldItem(ModItems.ixiolite_axe);
        heldItem(ModItems.ixiolite_shovel);
        heldItem(ModItems.euclase_sword);
        heldItem(ModItems.euclase_pickaxe);
        heldItem(ModItems.euclase_axe);
        heldItem(ModItems.euclase_shovel);
        heldItem(ModItems.carnelian_sword);
        heldItem(ModItems.carnelian_pickaxe);
        heldItem(ModItems.carnelian_axe);
        heldItem(ModItems.carnelian_shovel);
        heldItem(ModItems.benitoite_sword);
        heldItem(ModItems.benitoite_pickaxe);
        heldItem(ModItems.benitoite_axe);
        heldItem(ModItems.benitoite_shovel);
        heldItem(ModItems.chalcedony_sword);
        heldItem(ModItems.chalcedony_pickaxe);
        heldItem(ModItems.chalcedony_axe);
        heldItem(ModItems.chalcedony_shovel);
        basicItem(ModItems.agate_arrow);
        basicItem(ModItems.mock_malachite);

        heldItem(ModItems.malachite_guard_baton);
        heldItem(ModItems.apex_predator_mace);
        heldItem(ModItems.spinel_princess_flamberge);
        heldItem(ModItems.zircon_prince_razor);
        heldItem(ModItems.corrupt_warrior_sword);
        heldItem(ModItems.gaia_duchess_khopesh);
        heldItem(ModItems.gaia_baron_dagger);
        heldItem(ModItems.gaia_duke_blade);
        heldItem(ModItems.gaia_champion_sword);
        eggItem(ModItems.growth_sapper_spawn_egg);
        eggItem(ModItems.mutant_growth_extractor_spawn_egg);
        eggItem(ModItems.howlite_wolf_spawn_egg);
        eggItem(ModItems.spellbound_elemental_spawn_egg);
        eggItem(ModItems.rocky_luggeroth_spawn_egg);
        eggItem(ModItems.shalurker_spawn_egg);
        eggItem(ModItems.muckling_spawn_egg);
        eggItem(ModItems.markuzar_plant_spawn_egg);
        eggItem(ModItems.rugged_lurmorus_spawn_egg);
        eggItem(ModItems.agate_golem_spawn_egg);
        eggItem(ModItems.ancient_lagrahk_spawn_egg);
        eggItem(ModItems.crystal_golem_spawn_egg);
        eggItem(ModItems.saltion_spawn_egg);
        eggItem(ModItems.nomadic_lagrahk_spawn_egg);
        eggItem(ModItems.shallow_arenthis_spawn_egg);
        eggItem(ModItems.corrupt_sapper_spawn_egg);
        eggItem(ModItems.contorted_naga_spawn_egg);
        eggItem(ModItems.lesser_spitfire_spawn_egg);
        eggItem(ModItems.lesser_shockshooter_spawn_egg);
        eggItem(ModItems.mineral_arenthis_spawn_egg);
        eggItem(ModItems.bismuth_uletrus_spawn_egg);
        eggItem(ModItems.archaic_warrior_spawn_egg);
        eggItem(ModItems.primal_beast_spawn_egg);
        eggItem(ModItems.cavern_tick_spawn_egg);
        eggItem(ModItems.malachite_drone_spawn_egg);
        eggItem(ModItems.blue_howlite_wolf_spawn_egg);
        eggItem(ModItems.malachite_guard_spawn_egg);
    }
}
