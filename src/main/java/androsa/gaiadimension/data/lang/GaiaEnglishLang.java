package androsa.gaiadimension.data.lang;

import androsa.gaiadimension.data.provider.GaiaLangProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;

import java.util.function.Supplier;

public abstract class GaiaEnglishLang extends GaiaLangProvider {

    public GaiaEnglishLang(PackOutput output, String locale) {
        super(output, locale);
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.gaia_portal, "Gaia Portal");
        addBlock(ModBlocks.keystone_block, "Keystone Block");
        addBlock(ModBlocks.gold_fire, "Gold Fire");
        addBlock(ModBlocks.pyrite_torch, "Pyrite Torch");
        addBlock(ModBlocks.agate_crafting_table, "Agate Crafting Table");
        addBlock(ModBlocks.crude_storage_crate, "Crude Storage Crate");
        addBlock(ModBlocks.mega_storage_crate, "Mega Storage Crate");
        addBlock(ModBlocks.gaia_stone_furnace, "Gaia Stone Furnace");
        addBlock(ModBlocks.restructurer, "Restructurer");
        addBlock(ModBlocks.purifier, "Purifier");
        addBlock(ModBlocks.augmenter, "Augmenter");

        addFluid(ModBlocks.mineral_water, ModFluids.MINERAL_WATER, "Mineral Water");
        addFluid(ModBlocks.superhot_magma, ModFluids.SUPERHOT_MAGMA, "Superhot Magma");
        addFluid(ModBlocks.sweet_muck, ModFluids.SWEET_MUCK, "Sweet Muck");
        addFluid(ModBlocks.liquid_bismuth, ModFluids.LIQUID_BISMUTH, "Liquid Bismuth");
        addFluid(ModBlocks.liquid_aura, ModFluids.LIQUID_AURA, "Liquid Aura");

        addSoil(ModBlocks.heavy_soil, "Heavy");
        addSoil(ModBlocks.corrupted_soil, "Corrupted");
        addSoil(ModBlocks.boggy_soil, "Boggy");
        addSoil(ModBlocks.light_soil, "Light");
        addSoil(ModBlocks.aurum_soil, "Aurum");
        addGrass(ModBlocks.glitter_grass, "Glitter");
        addGrass(ModBlocks.corrupted_grass, "Corrupted");
        addGrass(ModBlocks.murky_grass, "Murky");
        addGrass(ModBlocks.soft_grass, "Soft");
        addGrass(ModBlocks.gilded_grass, "Gilded");
        addBlock(ModBlocks.frail_glitter_block, "Frail Glitter Block");
        addBlock(ModBlocks.thick_glitter_block, "Thick Glitter Block");
        addBlock(ModBlocks.gummy_glitter_block, "Gummy Glitter Block");
        addBlock(ModBlocks.pink_sludge_block, "Pink Sludge Block");

        addBlock(ModBlocks.crystal_growth, "Crystal Growth");
        addGrowth(ModBlocks.crystal_growth_red, "Red");
        addGrowth(ModBlocks.crystal_growth_black, "Black");
        addGrowth(ModBlocks.crystal_growth_seared, "Seared");
        addGrowth(ModBlocks.crystal_growth_mutant, "Mutant");
        addGrowth(ModBlocks.crystal_growth_aura, "Aura");
        addBlock(ModBlocks.golden_grass, "Golden Grass");
        addBlock(ModBlocks.tall_golden_grass, "Tall Golden Grass");
        addPotted(ModBlocks.thiscus, ModBlocks.potted_thiscus, "Thiscus");
        addPotted(ModBlocks.ouzium, ModBlocks.potted_ouzium, "Ouzium");
        addPotted(ModBlocks.agathum, ModBlocks.potted_agathum, "Agathum");
        addPotted(ModBlocks.varloom, ModBlocks.potted_varloom, "Varloom");
        addPotted(ModBlocks.corrupted_varloom, ModBlocks.potted_corrupted_varloom, "Corrupted Varloom");
        addBlock(ModBlocks.glamelea, "Glamelea");
        addPotted(ModBlocks.missingno_plant, ModBlocks.potted_missingno_plant, "Missing Texture Plant");
        addPotted(ModBlocks.spotted_kersei, ModBlocks.potted_spotted_kersei, "Spotted Kersei");
        addPotted(ModBlocks.thorny_wiltha, ModBlocks.potted_thorny_wiltha, "Thorny Wiltha");
        addPotted(ModBlocks.roofed_agaric, ModBlocks.potted_roofed_agaric, "Roofed Agaric");
        addPotted(ModBlocks.bulbous_hobina, ModBlocks.potted_bulbous_hobina, "Bulbous Hobina");
        addPotted(ModBlocks.stickly_cupsir, ModBlocks.potted_stickly_cupsir, "Stickly Cupsir");
        addPotted(ModBlocks.mystical_murgni, ModBlocks.potted_mystical_murgni, "Mystical Murgni");
        addPotted(ModBlocks.corrupted_gaia_eye, ModBlocks.potted_corrupted_gaia_eye, "Corrupted Gaia Eye");
        addPotted(ModBlocks.twinkling_gilsri, ModBlocks.potted_twinkling_gilsri, "Twinkling Gilsri");
        addPotted(ModBlocks.elder_imklia, ModBlocks.potted_elder_imklia, "Elder Imklia");
        addPotted(ModBlocks.gold_orb_tucher, ModBlocks.potted_gold_orb_tucher, "Gold Orb Tucher");
        addPotted(ModBlocks.missingno_fungus, ModBlocks.potted_missingno_fungus, "Missing Texture Fungus");
        addBlock(ModBlocks.golden_vine, "Golden Vine");
        addBlock(ModBlocks.sombre_cacti, "Sombre Cacti");
        addBlock(ModBlocks.sombre_shrub, "Sombre Shrub");

        addCalcite(ModBlocks.pink_calcite, ModBlocks.smooth_pink_calcite, "Pink");
        addCalcite(ModBlocks.red_calcite, ModBlocks.smooth_red_calcite, "Red");
        addCalcite(ModBlocks.blue_calcite, ModBlocks.smooth_blue_calcite, "Blue");
        addCalcite(ModBlocks.light_blue_calcite, ModBlocks.smooth_light_blue_calcite, "Light Blue");
        addCalcite(ModBlocks.green_calcite, ModBlocks.smooth_green_calcite, "Green");
        addCalcite(ModBlocks.purple_calcite, ModBlocks.smooth_purple_calcite, "Purple");
        addCalcite(ModBlocks.beige_calcite, ModBlocks.smooth_beige_calcite, "Beige");
        addCalcite(ModBlocks.yellow_calcite, ModBlocks.smooth_yellow_calcite, "Yellow");
        addCalcite(ModBlocks.black_calcite, ModBlocks.smooth_black_calcite, "Black");
        addCalcite(ModBlocks.orange_calcite, ModBlocks.smooth_orange_calcite, "Orange");
        addCalcite(ModBlocks.navy_calcite, ModBlocks.smooth_navy_calcite, "Navy");
        addCalcite(ModBlocks.gold_calcite, ModBlocks.smooth_gold_calcite, "Gold");
        addCalcite(ModBlocks.white_calcite, ModBlocks.smooth_white_calcite, "White");
        addCalcite(ModBlocks.clear_calcite, ModBlocks.smooth_clear_calcite, "Clear");
        addAragonite(ModBlocks.pink_aragonite, ModBlocks.smooth_pink_aragonite, "Pink");
        addAragonite(ModBlocks.blue_aragonite, ModBlocks.smooth_blue_aragonite, "Blue");
        addAragonite(ModBlocks.light_blue_aragonite, ModBlocks.smooth_light_blue_aragonite, "Light Blue");
        addAragonite(ModBlocks.green_aragonite, ModBlocks.smooth_green_aragonite, "Green");
        addAragonite(ModBlocks.purple_aragonite, ModBlocks.smooth_purple_aragonite, "Purple");
        addAragonite(ModBlocks.tan_aragonite, ModBlocks.smooth_tan_aragonite, "Tan");
        addAragonite(ModBlocks.yellow_aragonite, ModBlocks.smooth_yellow_aragonite, "Yellow");
        addAragonite(ModBlocks.black_aragonite, ModBlocks.smooth_black_aragonite, "Black");
        addAragonite(ModBlocks.brown_aragonite, ModBlocks.smooth_brown_aragonite, "Brown");
        addAragonite(ModBlocks.white_aragonite, ModBlocks.smooth_white_aragonite, "White");
        addAragonite(ModBlocks.clear_aragonite, ModBlocks.smooth_clear_aragonite, "Clear");

        addTree(ModBlocks.pink_agate_sapling, ModBlocks.pink_agate_leaves,
                ModBlocks.pink_agate_log, ModBlocks.stripped_pink_agate_log,
                ModBlocks.pink_agate_wood, ModBlocks.stripped_pink_agate_wood,
                ModBlocks.pink_agate_tiles, ModBlocks.pink_agate_tile_stairs, ModBlocks.pink_agate_tile_slab, ModBlocks.pink_agate_curtain,
                ModBlocks.potted_pink_agate_sapling, "Pink Agate");
        addTree(ModBlocks.blue_agate_sapling, ModBlocks.blue_agate_leaves,
                ModBlocks.blue_agate_log, ModBlocks.stripped_blue_agate_log,
                ModBlocks.blue_agate_wood, ModBlocks.stripped_blue_agate_wood,
                ModBlocks.blue_agate_tiles, ModBlocks.blue_agate_tile_stairs, ModBlocks.blue_agate_tile_slab, ModBlocks.blue_agate_curtain,
                ModBlocks.potted_blue_agate_sapling, "Blue Agate");
        addTree(ModBlocks.green_agate_sapling, ModBlocks.green_agate_leaves,
                ModBlocks.green_agate_log, ModBlocks.stripped_green_agate_log,
                ModBlocks.green_agate_wood, ModBlocks.stripped_green_agate_wood,
                ModBlocks.green_agate_tiles, ModBlocks.green_agate_tile_stairs, ModBlocks.green_agate_tile_slab, ModBlocks.green_agate_curtain,
                ModBlocks.potted_green_agate_sapling, "Green Agate");
        addTree(ModBlocks.purple_agate_sapling, ModBlocks.purple_agate_leaves,
                ModBlocks.purple_agate_log, ModBlocks.stripped_purple_agate_log,
                ModBlocks.purple_agate_wood, ModBlocks.stripped_purple_agate_wood,
                ModBlocks.purple_agate_tiles, ModBlocks.purple_agate_tile_stairs, ModBlocks.purple_agate_tile_slab, ModBlocks.purple_agate_curtain,
                ModBlocks.potted_purple_agate_sapling, "Purple Agate");
        addTree(ModBlocks.corrupted_sapling, ModBlocks.corrupted_leaves,
                ModBlocks.corrupted_log, ModBlocks.stripped_corrupted_log,
                ModBlocks.corrupted_wood, ModBlocks.stripped_corrupted_wood,
                ModBlocks.corrupted_tiles, ModBlocks.corrupted_tile_stairs, ModBlocks.corrupted_tile_slab, ModBlocks.corrupted_curtain,
                ModBlocks.potted_corrupted_sapling, "Goldstone Corrupted");
        addTree(ModBlocks.burnt_sapling, ModBlocks.burnt_leaves,
                ModBlocks.burnt_log, ModBlocks.stripped_burnt_log,
                ModBlocks.burnt_wood, ModBlocks.stripped_burnt_wood,
                ModBlocks.burnt_tiles, ModBlocks.burnt_tile_stairs, ModBlocks.burnt_tile_slab, ModBlocks.burnt_agate_curtain,
                ModBlocks.potted_burnt_sapling, "Burnt Agate");
        addTree(ModBlocks.fire_agate_sapling, ModBlocks.fire_agate_leaves,
                ModBlocks.fire_agate_log, ModBlocks.stripped_fire_agate_log,
                ModBlocks.fire_agate_wood, ModBlocks.stripped_fire_agate_wood,
                ModBlocks.fire_agate_tiles, ModBlocks.fire_agate_tile_stairs, ModBlocks.fire_agate_tile_slab, ModBlocks.fire_agate_curtain,
                ModBlocks.potted_fire_agate_sapling, "Fire Agate");
        addTree(ModBlocks.aura_sapling, ModBlocks.aura_leaves,
                ModBlocks.aura_log, ModBlocks.stripped_aura_log,
                ModBlocks.aura_wood, ModBlocks.stripped_aura_wood,
                ModBlocks.aura_tiles, ModBlocks.aura_tile_stairs, ModBlocks.aura_tile_slab, ModBlocks.aura_curtain,
                ModBlocks.potted_aura_sapling, "Aura");
        addTree(ModBlocks.golden_sapling, ModBlocks.golden_leaves,
                ModBlocks.golden_log, ModBlocks.stripped_golden_log,
                ModBlocks.golden_wood, ModBlocks.stripped_golden_wood,
                ModBlocks.golden_tiles, ModBlocks.golden_tile_stairs, ModBlocks.golden_tile_slab, ModBlocks.golden_curtain,
                ModBlocks.potted_golden_sapling, "Golden");

        addBlock(ModBlocks.salt, "Salt");
        addBlock(ModBlocks.saltstone, "Saltstone");
        addBlock(ModBlocks.pebbles, "Pebbles");
        addBlock(ModBlocks.gaia_stone, "Gaia Stone");
        addBlock(ModBlocks.gaia_cobblestone, "Gaia Cobblestone");
        addBlock(ModBlocks.wasteland_stone, "Wasteland Stone");
        addBlock(ModBlocks.static_stone, "Static Stone");
        addBlock(ModBlocks.charged_mineral, "Charged Mineral");
        addBlock(ModBlocks.volcanic_rock, "Volcanic Rock");
        addBlock(ModBlocks.searing_rock, "Searing Rock");
        addBlock(ModBlocks.primal_mass, "Primal Mass");
        addBlock(ModBlocks.nexustone, "Nexustone");
        addBlock(ModBlocks.impure_rock, "Impure Rock");
        addBlock(ModBlocks.active_rock, "Active Rock");
        addBlock(ModBlocks.impure_sludge, "Impure Sludge");
        addBlock(ModBlocks.geyser_block, "Geyser Block");
        addBlock(ModBlocks.sparkling_rock, "Sparkling Rock");
        addBlock(ModBlocks.aura_shoot, "Aura Shoot");
        addBlock(ModBlocks.golden_stone, "Golden Stone");
        addBlock(ModBlocks.tough_golden_stone, "Tough Golden Stone");
        addBlock(ModBlocks.brilliant_stone, "Brilliant Stone");
        addBlock(ModBlocks.gilded_brilliant_stone, "Gilded Brilliant Stone");
        addBlock(ModBlocks.aurum_mud, "Aurum Mud");
        addBlock(ModBlocks.golden_sand, "Golden Sand");
        addMookaite(ModBlocks.scarlet_mookaite, "Scarlet");
        addMookaite(ModBlocks.auburn_mookaite, "Auburn");
        addMookaite(ModBlocks.gold_mookaite, "Gold");
        addMookaite(ModBlocks.mauve_mookaite, "Mauve");
        addMookaite(ModBlocks.beige_mookaite, "Beige");
        addMookaite(ModBlocks.ivory_mookaite, "Ivory");

        addBlock(ModBlocks.cloudy_glass, "Cloudy Glass");
        addBlock(ModBlocks.foggy_glass, "Foggy Glass");
        addBlock(ModBlocks.gaia_stone_bricks, "Gaia Stone Bricks");
        addBlock(ModBlocks.cracked_gaia_stone_bricks, "Cracked Gaia Stone Bricks");
        addBlock(ModBlocks.crusted_gaia_stone_bricks, "Crusted Gaia Stone Bricks");

        addBrickSet(ModBlocks.raw_jade, ModBlocks.jade_bricks, ModBlocks.jade_brick_stairs, ModBlocks.jade_brick_slab,
                ModBlocks.cracked_jade_bricks, ModBlocks.cracked_jade_brick_stairs, ModBlocks.cracked_jade_brick_slab,
                ModBlocks.crusted_jade_bricks, ModBlocks.crusted_jade_brick_stairs, ModBlocks.crusted_jade_brick_slab,
                "Jade");
        addBrickSet(ModBlocks.raw_copal, ModBlocks.copal_bricks, ModBlocks.copal_brick_stairs, ModBlocks.copal_brick_slab,
                ModBlocks.cracked_copal_bricks, ModBlocks.cracked_copal_brick_stairs, ModBlocks.cracked_copal_brick_slab,
                ModBlocks.crusted_copal_bricks, ModBlocks.crusted_copal_brick_stairs, ModBlocks.crusted_copal_brick_slab,
                "Copal");
        addBrickSet(ModBlocks.raw_jet, ModBlocks.jet_bricks, ModBlocks.jet_brick_stairs, ModBlocks.jet_brick_slab,
                ModBlocks.cracked_jet_bricks, ModBlocks.cracked_jet_brick_stairs, ModBlocks.cracked_jet_brick_slab,
                ModBlocks.crusted_jet_bricks, ModBlocks.crusted_jet_brick_stairs, ModBlocks.crusted_jet_brick_slab,
                "Jet");
        addBrickSet(ModBlocks.raw_amethyst, ModBlocks.amethyst_bricks, ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_brick_slab,
                ModBlocks.cracked_amethyst_bricks, ModBlocks.cracked_amethyst_brick_stairs, ModBlocks.cracked_amethyst_brick_slab,
                ModBlocks.crusted_amethyst_bricks, ModBlocks.crusted_amethyst_brick_stairs, ModBlocks.crusted_amethyst_brick_slab,
                "Amethyst");

        addBlock(ModBlocks.reinforced_bricks, "Reinforced Bricks");
        addBlock(ModBlocks.bolstered_bricks, "Bolstered Bricks");

        //TODO: Compression
        addBlock(ModBlocks.malachite_bricks, "Malachite Bricks");
        addSet(ModBlocks.malachite_brick_stairs , ModBlocks.malachite_brick_slab, "Malachite Brick");
        addBlock(ModBlocks.malachite_cracked_bricks, "Cracked Malachite Bricks");
        addSet(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_cracked_brick_slab, "Cracked Malachite Brick");
        addBlock(ModBlocks.malachite_crusted_bricks, "Crusted Malachite Bricks");
        addSet(ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_crusted_brick_slab, "Crusted Malachite Brick");
        addBlock(ModBlocks.malachite_tiles, "Malachite Tiles");
        addSet(ModBlocks.malachite_tile_stairs, ModBlocks.malachite_tile_slab, "Malachite Tile");
        addBlock(ModBlocks.malachite_chisel_bricks, "Malachite Chisel Bricks");
        addBlock(ModBlocks.malachite_pulsing_bricks, "Malachite Pulsing Bricks");
        addBlock(ModBlocks.malachite_pulsing_tiles, "Malachite Pulsing Tiles");
        addBlock(ModBlocks.malachite_pulsing_chisel, "Malachite Pulsing Chisel");
        addBlock(ModBlocks.malachite_pillar, "Malachite Pillar");
        addBlock(ModBlocks.malachite_chisel_stairs, "Malachite Chisel_stairs");
        addBlock(ModBlocks.malachite_pulsing_brick_stairs, "Malachite Pulsing Brick Stairs");
        addBlock(ModBlocks.malachite_pulsing_floor_stairs, "Malachite Pulsing Tile Stairs");
        addBlock(ModBlocks.malachite_pulsing_chisel_stairs, "Malachite Pulsing Chisel Stairs");
        addBlock(ModBlocks.malachite_pillar_stairs, "Malachite Pillar Stairs");

        addStorage(ModBlocks.scaynyx_block, "Scaynyx");
        addOreSet(ModBlocks.sugilite_ore, ModBlocks.sugilite_block, "Sugilite");
        addOreSet(ModBlocks.hematite_ore, ModBlocks.hematite_block, "Hematite");
        addOreSet(ModBlocks.cinnabar_ore, ModBlocks.cinnabar_block, "Cinnabar");
        addOreSet(ModBlocks.labradorite_ore, ModBlocks.labradorite_block, "Labradorite");
        addOreSet(ModBlocks.moonstone_ore, ModBlocks.moonstone_block, "Moonstone");
        addOreSet(ModBlocks.red_opal_ore, ModBlocks.red_opal_block, "Red Opal");
        addOreSet(ModBlocks.blue_opal_ore, ModBlocks.blue_opal_block, "Blue Opal");
        addOreSet(ModBlocks.green_opal_ore, ModBlocks.green_opal_block, "Green Opal");
        addOreSet(ModBlocks.white_opal_ore, ModBlocks.white_opal_block, "White Opal");
        addOreSet(ModBlocks.pyrite_ore, ModBlocks.pyrite_block, "Pyrite");
        addStorage(ModBlocks.tektite_block, "Tektite");
        addStorage(ModBlocks.goldstone_block, "Goldstone");
        addStorage(ModBlocks.aura_block, "Aura");
        addStorage(ModBlocks.bismuth_block, "Bismuth");
        addStorage(ModBlocks.opalite_block, "Opalite");
        addStorage(ModBlocks.stibnite_block, "Stibnite");
        addStorage(ModBlocks.proustite_block, "Proustite");
        addStorage(ModBlocks.euclase_block, "Euclase");
        addStorage(ModBlocks.albite_block, "Albite");
        addStorage(ModBlocks.carnelian_block, "Carnelian");
        addStorage(ModBlocks.benitoite_block, "Benitoite");
        addStorage(ModBlocks.diopside_block, "Diopside");
        addStorage(ModBlocks.goshenite_block, "Goshenite");
        addOreSet(ModBlocks.celestine_block, ModBlocks.celestine_ore, "Celestine");
        addStorage(ModBlocks.magnetite_block, "Magnetite");

        addBlock(ModBlocks.speckled_rock, "Speckled Rock");
        addBlock(ModBlocks.coarse_rock, "Coarse Rock");
        addBlock(ModBlocks.precious_rock, "Precious Rock");
        addOre(ModBlocks.scarlet_opalite_ore, "Scarlet Opalite");
        addOre(ModBlocks.auburn_opalite_ore, "Auburn Opalite");
        addOre(ModBlocks.gold_opalite_ore, "Gold Opalite");
        addOre(ModBlocks.mauve_opalite_ore, "Mauve Opalite");
        addOre(ModBlocks.beige_opalite_ore, "Beige Opalite");
        addOre(ModBlocks.ivory_opalite_ore, "Ivory Opalite");

        addItem(ModItems.crystallized_redstone, "Crystallized Redstone");
        addItem(ModItems.crystallized_lapis_lazuli, "Crystallized Lapis Lazuli");
        addItem(ModItems.glint_and_gold, "Glint and Gold");
        addItem(ModItems.agate_stick, "Agate Stick");
        addItem(ModItems.hot_dust, "Hot Dust");
        addItem(ModItems.goldstone_dust, "Goldstone Dust");
        addItem(ModItems.fine_dust, "Fine Dust");
        addItem(ModItems.cloudy_shard, "Cloudy Shard");
        addItem(ModItems.agate_cup, "Agate Cup");
        addItem(ModItems.scaynyx_ingot, "Scaynyx Ingot");
        addItem(ModItems.sweet_muckball, "Sweet Muckball");
        addItem(ModItems.sugar_crystals, "Sugar Crystals");
        addItem(ModItems.sugar_cluster, "Sugar Cluster");
        addItem(ModItems.shiny_bone, "Shiny Bone");
        addItem(ModItems.fine_thread, "Fine Thread");
        addItem(ModItems.twined_thread, "Twined Thread");
        addItem(ModItems.pink_essence, "Pink Essence");
        addItem(ModItems.pink_goo, "Pink Goo");
        addItem(ModItems.gemstone_pouch, "Gemstone Pouch");
        addItem(ModItems.agate_fabric, "Agate Fabric");
        addItem(ModItems.sturdy_pebble, "Sturdy Pebble");
        addKit(ModItems.blank_kit, "Blank");
        addKit(ModItems.repair_kit, "Repair");
        addKits(ModItems.scarlet_augment_kit, ModItems.scarlet_replace_kit, "Scarlet");
        addKits(ModItems.auburn_augment_kit, ModItems.auburn_replace_kit, "Auburn");
        addKits(ModItems.gold_augment_kit, ModItems.gold_replace_kit, "Gold");
        addKits(ModItems.mauve_augment_kit, ModItems.mauve_replace_kit, "Mauve");
        addKits(ModItems.beige_augment_kit, ModItems.beige_replace_kit, "Beige");
        addKits(ModItems.ivory_augment_kit, ModItems.ivory_replace_kit, "Ivory");
        addKitInstructions();
        addConstructCharm();
        addItem(ModItems.scaynyx_bucket, "Scaynyx Bucket");
        addBucket(ModItems.mineral_water_bucket, "Mineral Water");
        addBucket(ModItems.superhot_magma_bucket, "Superhot Magma");
        addBucket(ModItems.sweet_muck_bucket, "Sweet Muck");
        addBucket(ModItems.liquid_bismuth_bucket, "Liquid Bismuth");
        addBucket(ModItems.liquid_aura_bucket, "Liquid Aura");
        addItem(ModItems.crystal_shard, "Crystal Shard");
        addItem(ModItems.crystal_core, "Crystal Core");
        addItem(ModItems.spitfire_heart, "Spitfire Heart");
        addItem(ModItems.shockshooter_soul, "Shockshooter Soul");
        addItem(ModItems.moss_agate_claw, "Moss Agate Claw");
        addItem(ModItems.howlite_fang, "Howlite Fang");
        addItem(ModItems.spellbound_core, "Spellbound Core");
        addItem(ModItems.bismuth_horn, "Bismuth Horn");
        addItem(ModItems.glitter_dust, "Glitter Dust");
        addItem(ModItems.glitter_rod, "Glitter Rod");
        addItem(ModItems.magnetite_rod, "Magnetite Rod");
        addItem(ModItems.aura_rod, "Aura Rod");

        addGeode(ModItems.pink_geode, ModItems.pink_geode_slice, ModItems.pink_geode_juice, "Pink", "Juice");
        addGeode(ModItems.blue_geode, ModItems.blue_geode_slice, ModItems.blue_geode_tea, "Blue", "Tea");
        addGeode(ModItems.green_geode, ModItems.green_geode_slice, ModItems.green_geode_ale, "Green", "Ale");
        addGeode(ModItems.purple_geode, ModItems.purple_geode_slice, ModItems.purple_geode_soda, "Purple", "Soda");
        addItem(ModItems.pearly_geode_elixir, "Pearly Geode Elixir");
        addItem(ModItems.lurmorus_meat, "Lurmorus Meat");
        addItem(ModItems.lurmorus_steak, "Lurmorus Steak");
        addItem(ModItems.small_tentacle, "Small Tentacle");
        addItem(ModItems.small_calamari, "Small Calamari");
        addItem(ModItems.large_tentacle, "Large Tentacle");
        addItem(ModItems.large_calamari, "Large Calamari");
        addItem(ModItems.markuzar_mint, "Markuzar Mint");
        addItem(ModItems.luggeroth_chop, "Luggeroth Chop");
        addItem(ModItems.cooked_luggeroth_chop, "Cooked Luggeroth Chop");
        addItem(ModItems.tilipi, "Tilipi");
        addItem(ModItems.tilibl, "Tilibl");
        addItem(ModItems.tiligr, "Tiligr");
        addItem(ModItems.tilipu, "Tilipu");
        addItem(ModItems.tiliol, "Tiliol");
        addItem(ModItems.tilimy, "Tilimy");
        addItem(ModItems.plagued_tiliey, "Plagued Tiliey");
        addItem(ModItems.tiliou, "Tiliou");

        addItem(ModItems.sugilite, "Sugilite");
        addGemDust(ModItems.hematite, ModItems.hematite_powder, "Hematite", "Powder");
        addGemDust(ModItems.cinnabar, ModItems.cinnabar_powder, "Cinnabar", "Powder");
        addGemDust(ModItems.labradorite, ModItems.labradorite_powder, "Labradorite", "Powder");
        addGemDust(ModItems.moonstone, ModItems.moonstone_powder, "Moonstone", "Powder");
        addGemDust(ModItems.red_opal, ModItems.red_opal_powder, "Red Opal", "Powder");
        addGemDust(ModItems.blue_opal, ModItems.blue_opal_powder, "Blue Opal", "Powder");
        addGemDust(ModItems.green_opal, ModItems.green_opal_powder, "Green Opal", "Powder");
        addGemDust(ModItems.white_opal, ModItems.white_opal_grit, "White Opal", "Grit");
        addItem(ModItems.stibnite, "Stibnite");
        addItem(ModItems.proustite, "Proustite");
        addItem(ModItems.euclase, "Euclase");
        addItem(ModItems.albite, "Albite");
        addItem(ModItems.carnelian, "Carnelian");
        addItem(ModItems.benitoite, "Benitoite");
        addItem(ModItems.diopside, "Diopside");
        addItem(ModItems.goshenite, "Goshenite");
        addGemDust(ModItems.pyrite, ModItems.pyrite_powder, "Pyrite", "Powder");
        addItem(ModItems.black_residue, "Black Residue");
        addItem(ModItems.tektite, "Tektite");
        addItem(ModItems.goldstone_residue, "Goldstone Residue");
        addItem(ModItems.goldstone, "Goldstone");
        addItem(ModItems.aura_residue, "Aura Residue");
        addItem(ModItems.aura_cluster, "Aura Cluster");
        addItem(ModItems.bismuth_residue, "Bismuth Residue");
        addItem(ModItems.bismuth_crystal, "Bismuth Crystal");
        addItem(ModItems.opalite, "Opalite");
        addItem(ModItems.celestine, "Celestine");
        addItem(ModItems.metallic_fragment, "Metallic Fragment");
        addItem(ModItems.magnetite, "Magnetite");

        addTools(ModItems.agate_axe, ModItems.agate_sword, ModItems.agate_shovel, ModItems.agate_pickaxe, "Agate");
        addTools(ModItems.sugilite_axe, ModItems.sugilite_sword, ModItems.sugilite_shovel, ModItems.sugilite_pickaxe, "Sugilite");
        addTools(ModItems.stibnite_axe, ModItems.stibnite_sword, ModItems.stibnite_shovel, ModItems.stibnite_pickaxe, "Stibnite");
        addTools(ModItems.euclase_axe, ModItems.euclase_sword, ModItems.euclase_shovel, ModItems.euclase_pickaxe, "Euclase");
        addTools(ModItems.carnelian_axe, ModItems.carnelian_sword, ModItems.carnelian_shovel, ModItems.carnelian_pickaxe, "Carnelian");
        addTools(ModItems.benitoite_axe, ModItems.benitoite_sword, ModItems.benitoite_shovel, ModItems.benitoite_pickaxe, "Benitoite");
        addTools(ModItems.goshenite_axe, ModItems.goshenite_sword, ModItems.goshenite_shovel, ModItems.goshenite_pickaxe, "Goshenite");
        addItem(ModItems.old_bow, "Old Bow");
        addItem(ModItems.agate_arrow, "Agate Arrow");
        addMagicStaff();

        addArmor(ModItems.sugilite_helmet, ModItems.sugilite_chestplate, ModItems.sugilite_legs, ModItems.sugilite_boots, "Sugilite");
        addArmor(ModItems.proustite_helmet, ModItems.proustite_chestplate, ModItems.proustite_legs, ModItems.proustite_boots, "Proustite");
        addArmor(ModItems.albite_helmet, ModItems.albite_chestplate, ModItems.albite_legs, ModItems.albite_boots, "Albite");
        addArmor(ModItems.carnelian_helmet, ModItems.carnelian_chestplate, ModItems.carnelian_legs, ModItems.carnelian_boots, "Carnelian");
        addArmor(ModItems.diopside_helmet, ModItems.diopside_chestplate, ModItems.diopside_legs, ModItems.diopside_boots, "Diopside");
        addArmor(ModItems.goshenite_helmet, ModItems.goshenite_chestplate, ModItems.goshenite_legs, ModItems.goshenite_boots, "Goshenite");

        addMalachite(ModItems.malachite_guard_headgear, ModItems.malachite_guard_brace, ModItems.malachite_guard_gear, ModItems.malachite_guard_boots, ModItems.malachite_guard_baton);
        addTigerEye(ModItems.apex_predator_hood, ModItems.apex_predator_jacket, ModItems.apex_predator_trousers, ModItems.apex_predator_boots, ModItems.apex_predator_mace);
        addSpinelPrincess(ModItems.spinel_princess_cowl, ModItems.spinel_princess_cloak, ModItems.spinel_princess_dress, ModItems.spinel_princess_heels, ModItems.spinel_princess_flamberge);
        addZirconPrince(ModItems.zircon_prince_crown, ModItems.zircon_prince_chestpiece, ModItems.zircon_prince_gear, ModItems.zircon_prince_boots, ModItems.zircon_prince_razor);
        addCorruptWarrior(ModItems.corrupt_warrior_helm, ModItems.corrupt_warrior_guard, ModItems.corrupt_warrior_greaves, ModItems.corrupt_warrior_boots, ModItems.corrupt_warrior_sword);
        addGaiaDuchess(ModItems.gaia_duchess_helm, ModItems.gaia_duchess_guard, ModItems.gaia_duchess_greaves, ModItems.gaia_duchess_boots, ModItems.gaia_duchess_khopesh);
        addGaiaBaron(ModItems.gaia_baron_mask, ModItems.gaia_baron_tuxedo, ModItems.gaia_baron_pants, ModItems.gaia_baron_shoes, ModItems.gaia_baron_dagger);
        addGaiaDuke(ModItems.gaia_duke_helm, ModItems.gaia_duke_guard, ModItems.gaia_duke_greaves, ModItems.gaia_duke_boots, ModItems.gaia_duke_blade);
        addGaiaChampion(ModItems.gaia_champion_helm, ModItems.gaia_champion_guard, ModItems.gaia_champion_greaves, ModItems.gaia_champion_boots, ModItems.gaia_champion_sword);

        add("item.gaiadimension.mock_gem.tooltip", "The only reward from forfeiting the battle");
        addItem(ModItems.mock_malachite, "Mock Malachite");

        addEntityType(ModEntities.AGATE_ARROW, "Agate Arrow");
        addEntityType(ModEntities.THROWN_PEBBLE, "ThrownPebble");
        addEntityType(ModEntities.MOOKAITE_AMMO_BULLET, "Mookaite Ammo Bullet");
        addEntityType(ModEntities.MOOKAITE_MAGIC_BULLET, "Mookaite Magic Bullet");
        addEntityType(ModEntities.MOOKAITE_MAGIC_AREA, "Mookaite Magic Area");
        addEntityType(ModEntities.STAFF_PROJECTILE, "Staff Projectile");
        addEntityType(ModEntities.STAFF_AREA_EFFECT, "Staff Area Effect");

        addEntityEgg(ModEntities.AGATE_GOLEM, ModItems.agate_golem_spawn_egg, "Agate Golem");
        addEntityEgg(ModEntities.ANCIENT_LAGRAHK, ModItems.ancient_lagrahk_spawn_egg, "Ancient Lagrahk");
        addEntityEgg(ModEntities.ARCHAIC_WARRIOR, ModItems.archaic_warrior_spawn_egg, "Archaic Warrior");
        addEntityEgg(ModEntities.BISMUTH_ULETRUS, ModItems.bismuth_uletrus_spawn_egg, "Bismuth Uletrus");
        addEntityEgg(ModEntities.CAVERN_TICK,ModItems.cavern_tick_spawn_egg , "Cavern Tick");
        addEntityEgg(ModEntities.CONTORTED_NAGA, ModItems.contorted_naga_spawn_egg, "Contorted Naga");
        addEntityEgg(ModEntities.CORRUPT_SAPPER, ModItems.corrupt_sapper_spawn_egg, "Corrupt Sapper");
        addEntityEgg(ModEntities.CRYSTAL_GOLEM, ModItems.crystal_golem_spawn_egg, "Crystal Golem");
        addEntityEgg(ModEntities.GROWTH_SAPPER, ModItems.growth_sapper_spawn_egg, "Growth Sapper");
        addEntityEgg(ModEntities.HOWLITE_WOLF, ModItems.howlite_wolf_spawn_egg, "Howlite Wolf");
        addEntityEgg(ModEntities.LESSER_SHOCKSHOOTER, ModItems.lesser_shockshooter_spawn_egg, "Lesser Shockshooter");
        addEntityEgg(ModEntities.LESSER_SPITFIRE, ModItems.lesser_spitfire_spawn_egg, "Lesser Spitfire");
        addEntityEgg(ModEntities.MARKUZAR_PLANT, ModItems.markuzar_plant_spawn_egg, "Markuzar Plant");
        addEntityEgg(ModEntities.MINERAL_ARENTHIS, ModItems.mineral_arenthis_spawn_egg, "Mineral Arenthis");
        addEntityEgg(ModEntities.MUCKLING, ModItems.muckling_spawn_egg, "Muckling");
        addEntityEgg(ModEntities.MUTANT_GROWTH_EXTRACTOR, ModItems.mutant_growth_extractor_spawn_egg, "Mutant Growth Extractor");
        addEntityEgg(ModEntities.NOMADIC_LAGRAHK, ModItems.nomadic_lagrahk_spawn_egg, "Nomadic Lagrahk");
        addEntityEgg(ModEntities.PRIMAL_BEAST, ModItems.primal_beast_spawn_egg, "Primal Beast");
        addEntityEgg(ModEntities.ROCKY_LUGGEROTH, ModItems.rocky_luggeroth_spawn_egg, "Rocky Luggeroth");
        addEntityEgg(ModEntities.RUGGED_LURMORUS, ModItems.rugged_lurmorus_spawn_egg, "Rugged Lurmorus");
        addEntityEgg(ModEntities.SALTION, ModItems.saltion_spawn_egg, "Saltion");
        addEntityEgg(ModEntities.SHALLOW_ARENTHIS, ModItems.shallow_arenthis_spawn_egg, "Shallow Arenthis");
        addEntityEgg(ModEntities.SHALURKER, ModItems.shalurker_spawn_egg, "Shalurker");
        addEntityEgg(ModEntities.SPELLBOUND_ELEMENTAL, ModItems.spellbound_elemental_spawn_egg, "Spellbound Elemental");
        addEntityEgg(ModEntities.MALACHITE_DRONE, ModItems.malachite_drone_spawn_egg, "Malachite Drone");
        addEntityEgg(ModEntities.MOOKAITE_CONSTRUCT, ModItems.mookaite_construct_spawn_egg, "Mookaite Construct");
        addEntityEgg(ModEntities.OPALITE_CONSTRUCT, ModItems.opalite_construct_spawn_egg, "Opalite Construct");
        addEntityEgg(ModEntities.GROWTH_GRAZER, ModItems.growth_grazer_spawn_egg, "Growth Grazer");
        addEntityEgg(ModEntities.AUREATE_EVRAUN, ModItems.aureate_evraun_spawn_egg, "Aureate Evraun");
        addEntityEgg(ModEntities.MOSS_AGATE_MONITOR, ModItems.moss_agate_monitor_spawn_egg, "Moss Agate Monitor");

        addEntityEgg(ModEntities.BLUE_HOWLITE_WOLF, ModItems.blue_howlite_wolf_spawn_egg, "Blue Howlite Wolf");

        addEntityEgg(ModEntities.MALACHITE_GUARD, ModItems.malachite_guard_spawn_egg, "Malachite Guard");

        add("itemGroup.gaiadimension.gaia_blocks", "Gaia Blocks");
        add("itemGroup.gaiadimension.gaia_items", "Gaia Items");
        add("itemGroup.gaiadimension.gaia_tools", "Gaia Tools");
        add("itemGroup.gaiadimension.gaia_armor", "Gaia Armor");

        add("entity.gaiadimension.opalite_construct.too_many_opalite", "You can't give it any more Opalite");
        add("entity.gaiadimension.opalite_construct.too_many_mookaite", "You can't give it any more Mookaite of this color");

        addEffect(ModEffects.goldstone_plague, "Corrupt Mania");

        addDeathMessage("energetic", "%1$s was flooded with immense energy", "%1$s was flooded with immense energy whilst escaping %2$s");
        addDeathMessage("static", "%1$s was electrocuted", "%1$s was electrocuted whilst escaping %2$s");
        addDeathMessage("corruption", "%1$s succumbed to the Goldstone Plague", "%1$s succumbed to the Goldstone Plague before %2$s");

        addGui("fuel.single_average", "Burns ~1 item");
        addGui("fuel.smelt_average", "Burns ~%s items");
        addGui("glitter_fuel", "Glittering");
        addGui("shine_fuel", "Shining");
        addGui("null_fuel", "Nulling");
        addGui("restructuring", "Restructuring");
        addGui("purifying", "Purifying");

        addBiome(GaiaBiomes.pink_agate_forest, "Pink Agate Forest");
        addBiome(GaiaBiomes.blue_agate_taiga, "Blue Agate Taiga");
        addBiome(GaiaBiomes.green_agate_jungle, "Green Agate Jungle");
        addBiome(GaiaBiomes.purple_agate_swamp, "Purple Agate Swamp");
        addBiome(GaiaBiomes.fossil_woodland, "Fossil Woodland");
        addBiome(GaiaBiomes.mutant_agate_wildwood, "Mutant Agate Wildwood");
        addBiome(GaiaBiomes.volcanic_lands, "Volcanic Lands");
        addBiome(GaiaBiomes.static_wasteland, "Static Wasteland");
        addBiome(GaiaBiomes.goldstone_lands, "Goldstone Lands");
        addBiome(GaiaBiomes.crystal_plains, "Crystal Plains");
        addBiome(GaiaBiomes.salt_dunes, "Salt Dunes");
        addBiome(GaiaBiomes.shining_grove, "Shining Grove");
        addBiome(GaiaBiomes.mookaite_mesa, "Mookaite Mesa");
        addBiome(GaiaBiomes.golden_forest, "Golden Forest");
        addBiome(GaiaBiomes.golden_plains, "Golden Plains");
        addBiome(GaiaBiomes.golden_hills, "Golden Hills");
        addBiome(GaiaBiomes.golden_marsh, "Golden Marsh");
        addBiome(GaiaBiomes.golden_sands, "Golden Sands");
        addBiome(GaiaBiomes.mineral_reservoir, "Mineral Reservoir");
        addBiome(GaiaBiomes.mineral_river, "Mineral River");

        addAdvancement("all_malachite_gear", "In Servitude", "Claim all of the Malachite Guard equipment for yourself");
        addAdvancement("collect_gemstone", "Haven't Always Been Glam", "Find a gemstone, the very essence of Gaia");
        addAdvancement("explore_gaia", "Terrestrial Geologist", "Explore the world of Gaia, locating every biome across the land");
        addAdvancement("find_malachite_watchtower", "The Unusual Construct", "Discover a Malachite Watchtower, a mysterious tower with an eerie glow");
        addAdvancement("find_mini_tower", "Looking Out", "Discover a Gaia Mini Tower, a dungeon once a patrol station");
        addAdvancement("get_all_armor", "Just to be Safe", "Craft every piece of armor in Gaia");
        addAdvancement("get_all_gemstones", "I'm too Shiny!", "Discover each and every gemstone of Gaia");
        addAdvancement("get_all_tools", "A Full Toolbox", "Craft every axe, pickaxe, shovel, and sword in Gaia");
        addAdvancement("get_armor", "Sturdy Protection", "Craft a piece of armor made from a material in Gaia");
        addAdvancement("get_tool", "Getting a Better Upgrade", "Craft a tool using materials found in Gaia");
        addAdvancement("mine_all_gemstones", "Excavator", "Scour the depths of Gaia to unearth every gemstone found underground.");
        addAdvancement("restructure_gemstone", "Is This Science?", "Use a Restructurer to turn one gemstone into another");
        addAdvancement("root", "All that Glitters and Shines", "Step foot into the Gaia Dimension for the first time");
        addAdvancement("slay_malachite_guard", "Lowering Guard", "Defeat the Malachite Guard atop the Malachite Watchtower");

        addPebble();
        addEntitySubtitles("agate_golem", "Agate Golem");
        addEntitySubtitles("ancient_lagrahk", "Ancient Lagrahk");
        addArchaicWarrior("archaic_warrior", "Archaic Warrior");
        addEntitySubtitles("bismuth_uletrus", "Bismuth Uletrus");
        addCavernTick("cavern_tick", "Cavern Tick");
        addEntitySubtitles("contorted_naga", "Contorted Naga");
        addEntitySubtitles("corrupt_sapper", "Corrupted Growth Sapper");
        addEntitySubtitles("crystal_golem", "Crystal Golem");
        addEntitySubtitles("growth_sapper", "Growth Sapper");
        addEntitySubtitles("howlite_wolf", "Howlite Wolf");
        addEntitySubtitles("lesser_shockshooter", "Lesser Shockshooter");
        addEntitySubtitles("lesser_spitfire", "Lesser Spitfire");
        addEntitySubtitles("markuzar_plant", "Markuzar Plant");
        addEntitySubtitles("mineral_arenthis", "Mineral Arenthis");
        addMuckling("muckling", "Muckling");
        addMookaiteConstruct("mookaite_construct", "Mookaite Construct");
        addEntitySubtitles("mutant_growth_extractor", "Mutant Growth Extractor");
        addEntitySubtitles("nomadic_lagrahk", "Nomadic Lagrahk");
        addEntitySubtitles("opalite_construct", "Opalite Construct");
        addPrimalBeast("primal_beast", "Primal Beast");
        addEntitySubtitles("rocky_luggeroth", "Rocky Luggeroth");
        addEntitySubtitles("rugged_lurmorus", "Rugged Lurmorus");
        addEntitySubtitles("saltion", "Saltion");
        addEntitySubtitles("shallow_arenthis", "Shallow Arenthis");
        addEntitySubtitles("shalurker", "Shalurker");
        addEntitySubtitles("spellbound_elemental", "Spellbound Elemental");
        addMalachiteDrone("malachite_drone", "Malachite Drone");
        addEntitySubtitles("blue_howlite_wolf", "Blue Howlite Wolf");
        addMalachiteGuard("malachite_guard", "Malachite Guard");
        addEntitySubtitles("moss_agate_monitor", "Moss Agate Monitor");
        addMagic("magic_projectile", "Magic");

        genSpecific();
    }

    //Dear Americans: cope and seethe already so we can have one English
    public abstract void genSpecific();

    public void addSoil(Supplier<? extends Block> block, String name) {
        addBlock(block, name + " Soil");
    }

    public void addGrass(Supplier<? extends Block> block, String name) {
        addBlock(block, name + " Grass");
    }

    public void addGrowth(Supplier<? extends Block> block, String name) {
        addBlock(block, name + " Crystal Growth");
    }

    public void addCalcite(Supplier<? extends Block> raw, Supplier<? extends Block> smooth, String name) {
        addBlock(raw, name + " Calcite");
        addBlock(smooth, "Smooth " + name + " Calcite");
    }

    public void addAragonite(Supplier<? extends Block> raw, Supplier<? extends Block> smooth, String name) {
        addBlock(raw, name + " Aragonite");
        addBlock(smooth, "Smooth " + name + " Aragonite");
    }

    public void addTree(Supplier<? extends Block> sapling, Supplier<? extends Block> leaves, Supplier<? extends Block> log, Supplier<? extends Block> strippedLog, Supplier<? extends Block> wood, Supplier<? extends Block> strippedWood, Supplier<? extends Block> tiles, Supplier<? extends Block> stairs, Supplier<? extends Block> slab, Supplier<? extends Block> curtain, Supplier<? extends Block> pot, String name) {
        addPotted(sapling, pot, name + " Sapling");
        addBlock(leaves, name + " Leaves");
        addBlock(log, name + " Log");
        addBlock(strippedLog, "Stripped " + name + " Log");
        addBlock(wood, name + " Wood");
        addBlock(strippedWood, "Stripped " + name + " Wood");
        addBlock(tiles, name + " Tiles");
        addSet(stairs, slab, name + " Tile");
        addBlock(curtain, name + " Curtain");
    }

    public void addMookaite(Supplier<? extends Block> block, String name) {
        addBlock(block, name + " Mookaite");
    }

    public void addBrickSet(Supplier<? extends Block> raw, Supplier<? extends Block> brick, Supplier<? extends Block> brickStairs, Supplier<? extends Block> brickSlab, Supplier<? extends Block> crackedBrick, Supplier<? extends Block> crackedBrickStairs, Supplier<? extends Block> crackedBrickSlab, Supplier<? extends Block> crustBrick, Supplier<? extends Block> crustBrickStairs, Supplier<? extends Block> crustBrickSlab, String name) {
        addBlock(raw, "Raw " + name);
        addBlock(brick, name + " Bricks");
        addSet(brickStairs, brickSlab, name + " Brick");
        addBlock(crackedBrick, "Cracked " + name + " Bricks");
        addSet(crackedBrickStairs, crackedBrickSlab, "Cracked " + name + " Brick");
        addBlock(crustBrick, "Crusted " + name + " Bricks");
        addSet(crustBrickStairs, crustBrickSlab, "Crusted " + name + " Brick");
    }

    public void addOreSet(Supplier<? extends Block> ore, Supplier<? extends Block> block, String name) {
        addOre(ore, name + " Ore");
        addStorage(block, name);
    }

    public void addOre(Supplier<? extends Block> block, String name) {
        addBlock(block, name + " Ore");
    }

    public void addStorage(Supplier<? extends Block> block, String name) {
        addBlock(block, "Block of " + name);
    }

    public void addSet(Supplier<? extends Block> stairs, Supplier<? extends Block> slab, String name) {
        addStairs(stairs, name);
        addSlab(slab, name);
    }

    public void addStairs(Supplier<? extends Block> stairs, String name) {
        addBlock(stairs, name + " Stairs");
    }

    public void addSlab(Supplier<? extends Block> slab, String name) {
        addBlock(slab, name + " Slab");
    }

    public void addPotted(Supplier<? extends Block> plant, Supplier<? extends Block> pot, String name) {
        addBlock(plant, name);
        addBlock(pot, "Potted " + name);
    }

    public void addKits(Supplier<? extends Item> augment, Supplier<? extends Item> repair, String name) {
        addKit(augment, name + " Augment");
        addKit(repair, name + " Repair");
    }

    public void addKit(Supplier<? extends Item> item, String name) {
        addItem(item, name + " Kit");
    }

    public void addKitInstructions() {
        addKitTooltips();
        addKitPart("left_horn", "Left Horn");
        addKitPart("right_horn", "Right Horn");
        addKitPart("left_eye", "Left Eye");
        addKitPart("right_eye", "Right Eye");
        addKitPart("left_shoulder", "Left Shoulder");
        addKitPart("right_shoulder", "Right Shoulder");
        addKitPart("left_arm_brace", "Left Arm Brace");
        addKitPart("right_arm_brace", "Right Arm Brace");
        addKitPart("left_leg_brace", "Left Leg Brace");
        addKitPart("right_leg_brace", "Right Leg Brace");
        addKitColor("scarlet", "Scarlet");
        addKitColor("auburn", "Auburn");
        addKitColor("gold", "Gold");
        addKitColor("mauve", "Mauve");
        addKitColor("beige", "Beige");
        addKitColor("ivory", "Ivory");
        addKitError("no_bond", "There is no bond with a Mookaite Construct");
        addKitError("follower", "This bond is not with a Mookaite Construct");
        addKitError("in_use", "The Opalite Construct is at work already");
        addKitError("no_part", "There's no part in this Kit");
        addKitError("resources", "There's not enough materials to use this Kit");
        addKitError("incompatible", "This Kit cannot be used because it's incompatible");
        addKitError("in_combat", "The Mookaite Construct is too distracted");
    }

    public void addKitTooltips() {
        addKitTip("blank", "A blank Opalite Construct kit.");
        addKitTip("repair", "Use this to turn a Mookaite part into Opalite");
        addKitTip("augment", "Use this to repair a missing Mookaite part");
        addKitTip("part.instruction", "Hold Shift and Right-Click to change part");
        addKitTip("part.swap", "Kit part changed to:");
        addKitTip("part", "This kit will affect:");
    }

    public void addKitPart(String key, String part) {
        addKitTip("part." + key, part);
    }

    public void addKitColor(String key, String color) {
        addKitTip("color." + key, color);
    }

    public void addKitError(String key, String tooltip) {
        addKitTip("invalid." + key, tooltip);
    }

    public void addKitTip(String key, String tooltip) {
        add("item.gaiadimension.construct_kit." + key, tooltip);
    }

    public void addConstructCharm() {
        addItem(ModItems.construct_charm, "Construct Charm");
        addCharmMessage("invalid_entity", "Cannot bond this entity");
        addCharmMessage("mookaite_already_bonded", "This Mookaite Construct has a bond already");
        addCharmMessage("has_mookaite_bond", "Already bonding with a Mookaite Construct");
        addCharmMessage("opalite_already_bonded", "This Opalite Construct has a bond already");
        addCharmMessage("has_opalite_bond", "Already bonding with an Opalite Construct");
        addCharmMessage("both_out_of_range", "Could not bond. Both Constructs out of range");
        addCharmMessage("mookaite_out_of_range", "Could not bond. Mookaite Construct out of range");
        addCharmMessage("opalite_out_of_range", "Could not bond. Opalite Construct out of range");
        addCharmMessage("both_invalid", "Could not bond. Both Constructs are not valid Construct entities");
        addCharmMessage("mookaite_invalid", "Could not bond. Mookaite Construct is not a valid Mookaite Construct entity");
        addCharmMessage("opalite_invalid", "Could not bond. Opalite Construct is not a valid Opalite Construct entity");
        addCharmMessage("success", "Successfully bonded Mookaite and Opalite Constructs");
        addCharmMessage("reset", "Resetting bonds");
    }

    public void addCharmMessage(String message, String tooltip) {
        add(ModItems.construct_charm.get().getDescriptionId() + ".message." + message, tooltip);
    }

    public void addBucket(Supplier<? extends Item> item, String name) {
        addItem(item, name + " Scaynyx Bucket");
    }

    public void addGeode(Supplier<? extends Item> geode, Supplier<? extends Item> slice, Supplier<? extends Item> drink, String name, String drinktype) {
        addItem(geode, name + " Geode");
        addItem(slice, name + " Geode Slice");
        addItem(drink, name + " Geode " + drinktype);
    }

    public void addGemDust(Supplier<? extends Item> gem, Supplier<? extends Item> dust, String name, String dusttype) {
        addItem(gem, name);
        addItem(dust, name + " " + dusttype);
    }

    public void addTools(Supplier<? extends Item> axe, Supplier<? extends Item> sword, Supplier<? extends Item> shovel, Supplier<? extends Item> pickaxe, String name) {
        addItem(axe, name + " Axe");
        addItem(sword, name + " Sword");
        addItem(shovel, name + " Shovel");
        addItem(pickaxe, name + " Pickaxe");
    }

    public void addArmor(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, String name) {
        addItem(helmet, name + " Helmet");
        addItem(chest, name + " Chestplate");
        addItem(legs, name + " Legs");
        addItem(boots, name + " Boots");
    }

    public void addMalachite(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Headgear", chest, "Brace", legs, "Gear", boots, "Boots", "malachite", "Sturdy, heavy, and eerily polished",
                weapon, "Malachite Guard Baton", "A good swing could knock targets away", "Malachite");
    }

    public void addTigerEye(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Hood", chest, "Jacket", legs, "Trousers", boots, "Boots", "tigereye", "Who knew something old was still this dangerous?",
                weapon, "Apex Predator Mace", "Those claws could dig deep into skin", "Tiger's Eye");
    }

    public void addSpinelPrincess(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Cowl", chest, "Cloak", legs, "Dress", boots, "Heels", "spinel", "It looks thin, but it's warm to wear",
                weapon, "Spinel Princess Flamberge", "Every swing leaves an entrail of fire", "Spinel");
    }

    public void addZirconPrince(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Crown", chest, "Chestpiece", legs, "Gear", boots, "Boots", "zircon", "It hums slightly, and it zaps when you touch it",
                weapon, "Zircon Prince Razor", "Those two prongs channel electricity", "Zicron");
    }

    public void addCorruptWarrior(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Helm", chest, "Guard", legs, "Greaves", boots, "Boots", "corrupt", "It hurts to wear it, both mentally and physically",
                weapon, "Corrupt Warrior Sword", "It's so heavy, and it hurts to use it", "Corrupted Gaia");
    }

    public void addGaiaDuchess(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Helm", chest, "Guard", legs, "Greaves", boots, "Boots", "bixbite", "Nothing about it looks remotely like Gaia",
                weapon, "Gaia Duchess Khopesh", "It looks like it could fly apart", "Bixbite");
    }

    public void addGaiaBaron(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Mask", chest, "Tuxedo", legs, "Pants", boots, "Shoes", "tsavorite", "So stylish, yet so mysterious",
                weapon, "Gaia Baron Dagger", "Though the blade is small, it could break armor", "Tsavorite");
    }

    public void addGaiaDuke(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Helm", chest, "Guard", legs, "Greaves", boots, "Boots", "larvikite", "It looks like it went through some intense battles",
                weapon, "Gaia Duke Blade", "That blade could hurt at high velocity", "Larvikite");
    }

    public void addGaiaChampion(Supplier<? extends Item> helmet, Supplier<? extends Item> chest, Supplier<? extends Item> legs, Supplier<? extends Item> boots, Supplier<? extends Item> weapon) {
        addBossGear(helmet, "Helm", chest, "Guard", legs, "Greaves", boots, "Boots", "champion", "Amazing! This feels so pure!",
                weapon, "Gaia Champion Sword", "She bestowed it upon you", "Gaia Champion");
    }

    public void addBossGear(Supplier<? extends Item> helmet, String helmetname, Supplier<? extends Item> chest, String chestname, Supplier<? extends Item> legs, String legname, Supplier<? extends Item> boots, String bootname, String armor, String armordesc, Supplier<? extends Item> weapon, String weaponname, String flavour, String name) {
        addSpecialArmor(helmet, name, helmetname);
        addSpecialArmor(chest, name, chestname);
        addSpecialArmor(legs, name, legname);
        addSpecialArmor(boots, name, bootname);
        addItem(weapon, weaponname);
        addFlavour(weapon, flavour);
        addFlavour("item.gaiadimension." + armor + "_armor", armordesc);
    }

    public void addMagicStaff() {
        addItem(ModItems.magic_staff, "Magic Staff");
        addStaffHeadings();
        addStaffElements();
        addStaffBehavior();
        addStaffStats();
    }

    public void addStaffHeadings() {
        addStaffTip("desc.element", "Element");
        addStaffTip("desc.stat", "Stat");
        addStaffTip("help", "Hold Shift for component descriptions.");
    }

    public void addStaffElements() {
        String component = "element";
        addHelpTip(component, "physical", "Physical", "Deals regular magic damage.");
        addHelpTip(component, "fire", "Fire", "Deals fiery magic damage.");
        addHelpTip(component, "electric", "Electric", "Deals electric magic damage.");
        addHelpTip(component, "poison", "Poison", "Deals poison magic damage.");
        addHelpTip(component, "frost", "Frost", "Deals freezing magic damage.");
        addHelpTip(component, "magic", "Magic", "Deals damage that bypasses effects.");
        addHelpTip(component, "energy", "Energy", "Deals damage of unreal proportions.");
    }

    public void addStaffBehavior() {
        String component = "behavior";
        addHelpTip(component, "basic", "Basic", "Shoots a ball of energy.");
        addHelpTip(component, "scatter", "Scatter", "Shoots three balls of energy.");
        addHelpTip(component, "ricochet", "Ricochet", "Bounces on contact up to 5 times.");
        addHelpTip(component, "blast", "Blast", "Explodes on contact that only hurts mobs.");
        addHelpTip(component, "linger", "Linger", "Leaves a lingering effect on contact.");
        addHelpTip(component, "burst", "Burst", "Splits into 5 on contact.");
    }

    public void addStaffStats() {
        String component = "stat";
        addHelpTip(component, "standard", "Standard", "No additional stat bonus.");
        addHelpTip(component, "power", "Power", "Magic deals additional damage.");
        addHelpTip(component, "speed", "Speed", "Magic travels faster.");
        addHelpTip(component, "recharge", "Recharge", "Staff cooldown is reduced.");
        addHelpTip(component, "force", "Force", "Magic deals stronger knockback.");
        addHelpTip(component, "sustain", "Sustain", "Magic decays slower when travelling.");
    }

    public void addHelpTip(String component, String property, String tooltip, String help) {
        addStaffTip(component + "." + property, tooltip);
        addStaffTip(component + ".help." + property, help);
    }

    public void addStaffTip(String key, String tooltip) {
        add(ModItems.magic_staff.get().getDescriptionId() + "." + key, tooltip);
    }

    public void addSpecialArmor(Supplier<? extends Item> gear, String name, String article) {
        addItem(gear, name + " " + article);
    }

    public void addFlavour(Supplier<? extends Item> item, String text) {
        add(item.get().getDescriptionId() + ".tooltip", text);
    }

    public void addFlavour(String key, String text) {
        add(key + ".tooltip", text);
    }

    public void addEntityEgg(Supplier<? extends EntityType<?>> entity, Supplier<? extends Item> egg, String name) {
        addEntityType(entity, name);
        addItem(egg, name + " Spawn Egg");
    }

    public void addPebble() {
        addEntitySubtitle("sturdy_pebble", "throw", "Sturdy Pebble", "flies");
    }

    public void addMagic(String key, String name) {
        addEntitySubtitle(key, "shoot", name, "shoot");
        addEntitySubtitle(key, "break", name, "bursts");
        addEntitySubtitle(key, "bounce", name, "bounces");
        addEntitySubtitle(key, "explode", name, "explodes");
    }

    public void addArchaicWarrior(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "ambient", name, "rattles");
        addEntitySubtitle(key, "step", name, "steps");
    }

    public void addCavernTick(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "ambient", name, "hisses");
        addEntitySubtitle(key, "step", name, "steps");
    }

    public void addMuckling(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "squish", name, "squishes");
    }

    public void addMookaiteConstruct(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "stomp", name, "stomps");
        addEntitySubtitle(key, "breath", name, "breathes fire");
        addEntitySubtitle(key, "cast", name, "casts magic");
        addEntitySubtitle(key, "shoot", name, "shoots");
    }

    public void addPrimalBeast(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "ambient", name, "breathes");
    }

    public void addMalachiteDrone(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "desync", name, "desyncs");
    }

    public void addMalachiteGuard(String key, String name) {
        addEntitySubtitles(key, name);
        addEntitySubtitle(key, "blast", name,"blasts");
        addEntitySubtitle(key, "stomp", name,"stomps");
    }
}
