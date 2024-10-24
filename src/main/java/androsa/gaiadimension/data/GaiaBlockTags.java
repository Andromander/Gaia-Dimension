package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBlockTagsProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GaiaBlockTags extends GaiaBlockTagsProvider {

    private static final ImmutableList<Supplier<? extends Block>> BEACON_BASES = ImmutableList.of(
            ModBlocks.sugilite_block, ModBlocks.hematite_block, ModBlocks.cinnabar_block, ModBlocks.labradorite_block, ModBlocks.moonstone_block, ModBlocks.red_opal_block,
            ModBlocks.blue_opal_block, ModBlocks.green_opal_block,  ModBlocks.white_opal_block, ModBlocks.pyrite_block, ModBlocks.tektite_block, ModBlocks.goldstone_block,
            ModBlocks.aura_block, ModBlocks.bismuth_block, ModBlocks.stibnite_block, ModBlocks.proustite_block, ModBlocks.euclase_block, ModBlocks.albite_block,
            ModBlocks.carnelian_block, ModBlocks.benitoite_block, ModBlocks.diopside_block, ModBlocks.goshenite_block, ModBlocks.opalite_block, ModBlocks.celestine_block
    );
    private static final ImmutableList<Supplier<? extends Block>> FLOWER_POTS = ImmutableList.of(
            ModBlocks.potted_thiscus, ModBlocks.potted_ouzium, ModBlocks.potted_agathum, ModBlocks.potted_varloom, ModBlocks.potted_corrupted_varloom, ModBlocks.potted_missingno_plant,
            ModBlocks.potted_spotted_kersei, ModBlocks.potted_thorny_wiltha, ModBlocks.potted_roofed_agaric, ModBlocks.potted_bulbous_hobina, ModBlocks.potted_stickly_cupsir,
            ModBlocks.potted_mystical_murgni, ModBlocks.potted_corrupted_gaia_eye, ModBlocks.potted_twinkling_gilsri, ModBlocks.potted_elder_imklia, ModBlocks.potted_gold_orb_tucher,
            ModBlocks.potted_missingno_fungus,ModBlocks.potted_pink_agate_sapling, ModBlocks.potted_blue_agate_sapling, ModBlocks.potted_green_agate_sapling, ModBlocks.potted_purple_agate_sapling,
            ModBlocks.potted_fossilized_sapling, ModBlocks.potted_corrupted_sapling, ModBlocks.potted_burnt_sapling, ModBlocks.potted_fire_agate_sapling, ModBlocks.potted_aura_sapling,
            ModBlocks.potted_golden_sapling
    );
    private static final ImmutableList<Supplier<? extends Block>> GUARDED_BY_PIGLINS = ImmutableList.of(
            ModBlocks.crude_storage_crate, ModBlocks.mega_storage_crate
    );
    private static final ImmutableList<Supplier<? extends Block>> IMPERMEABLE = ImmutableList.of(
            ModBlocks.foggy_glass, ModBlocks.cloudy_glass, ModBlocks.frail_glitter_block
    );
    private static final ImmutableList<Supplier<? extends Block>> LEAVES = ImmutableList.of(
            ModBlocks.pink_agate_leaves, ModBlocks.blue_agate_leaves, ModBlocks.green_agate_leaves, ModBlocks.purple_agate_leaves, ModBlocks.fossilized_leaves,
            ModBlocks.corrupted_leaves, ModBlocks.burnt_leaves, ModBlocks.fire_agate_leaves, ModBlocks.aura_leaves, ModBlocks.golden_leaves
    );
    private static final ImmutableList<Supplier<? extends Block>> PINK_AGATE_LOGS = ImmutableList.of(
            ModBlocks.pink_agate_log, ModBlocks.stripped_pink_agate_log, ModBlocks.pink_agate_wood, ModBlocks.stripped_pink_agate_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> BLUE_AGATE_LOGS = ImmutableList.of(
            ModBlocks.blue_agate_log, ModBlocks.stripped_blue_agate_log, ModBlocks.blue_agate_wood, ModBlocks.stripped_blue_agate_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> GREEN_AGATE_LOGS = ImmutableList.of(
            ModBlocks.green_agate_log, ModBlocks.stripped_green_agate_log, ModBlocks.green_agate_wood, ModBlocks.stripped_green_agate_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> PURPLE_AGATE_LOGS = ImmutableList.of(
            ModBlocks.purple_agate_log, ModBlocks.stripped_purple_agate_log, ModBlocks.purple_agate_wood, ModBlocks.stripped_purple_agate_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> FOSSILIZED_LOGS = ImmutableList.of(
            ModBlocks.fossilized_log, ModBlocks.stripped_fossilized_log, ModBlocks.fossilized_wood, ModBlocks.stripped_fossilized_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> CORRUPTED_LOGS = ImmutableList.of(
            ModBlocks.corrupted_log, ModBlocks.stripped_corrupted_log, ModBlocks.corrupted_wood, ModBlocks.stripped_corrupted_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> BURNT_LOGS = ImmutableList.of(
            ModBlocks.burnt_log, ModBlocks.stripped_burnt_log, ModBlocks.burnt_wood, ModBlocks.stripped_burnt_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> BURNING_LOGS = ImmutableList.of(
            ModBlocks.fire_agate_log, ModBlocks.stripped_fire_agate_log, ModBlocks.fire_agate_wood, ModBlocks.stripped_fire_agate_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> AURA_LOGS = ImmutableList.of(
            ModBlocks.aura_log, ModBlocks.stripped_aura_log, ModBlocks.aura_wood, ModBlocks.stripped_aura_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> GOLDEN_LOGS = ImmutableList.of(
            ModBlocks.golden_log, ModBlocks.stripped_golden_log, ModBlocks.golden_wood, ModBlocks.stripped_golden_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> TILES = ImmutableList.of(
            ModBlocks.pink_agate_tiles, ModBlocks.blue_agate_tiles, ModBlocks.green_agate_tiles, ModBlocks.purple_agate_tiles, ModBlocks.fossilized_tiles,
            ModBlocks.corrupted_tiles, ModBlocks.burnt_tiles, ModBlocks.fire_agate_tiles, ModBlocks.aura_tiles, ModBlocks.golden_tiles
    );
    private static final ImmutableList<Supplier<? extends Block>> SAPLINGS = ImmutableList.of(
            ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling,
            ModBlocks.fossilized_sapling, ModBlocks.corrupted_sapling, ModBlocks.burnt_sapling, ModBlocks.fire_agate_sapling, ModBlocks.aura_sapling, ModBlocks.golden_sapling
    );
    private static final ImmutableList<Supplier<? extends Block>> SLABS = ImmutableList.of(
            ModBlocks.pink_agate_tile_slab, ModBlocks.blue_agate_tile_slab, ModBlocks.green_agate_tile_slab, ModBlocks.purple_agate_tile_slab, ModBlocks.fossilized_tile_slab,
            ModBlocks.corrupted_tile_slab, ModBlocks.burnt_tile_slab, ModBlocks.fire_agate_tile_slab, ModBlocks.aura_tile_slab, ModBlocks.golden_tile_slab, ModBlocks.jade_brick_slab,
            ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_brick_slab, ModBlocks.copal_brick_slab, ModBlocks.cracked_copal_brick_slab,ModBlocks.crusted_copal_brick_slab,
            ModBlocks.jet_brick_slab, ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_brick_slab, ModBlocks.amethyst_brick_slab,  ModBlocks.cracked_amethyst_brick_slab,
            ModBlocks.crusted_amethyst_brick_slab, ModBlocks.malachite_brick_slab, ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_tile_slab
    );
    private static final ImmutableList<Supplier<? extends Block>> SMALL_FLOWERS = ImmutableList.of(
            ModBlocks.thiscus, ModBlocks.ouzium, ModBlocks.agathum, ModBlocks.varloom, ModBlocks.corrupted_varloom, ModBlocks.missingno_plant, ModBlocks.glamelea
    );
    private static final ImmutableList<Supplier<? extends Block>> STAIRS = ImmutableList.of(
            ModBlocks.pink_agate_tile_stairs, ModBlocks.blue_agate_tile_stairs, ModBlocks.green_agate_tile_stairs, ModBlocks.purple_agate_tile_stairs, ModBlocks.fossilized_tile_stairs,
            ModBlocks.corrupted_tile_stairs, ModBlocks.burnt_tile_stairs, ModBlocks.fire_agate_tile_stairs, ModBlocks.aura_tile_stairs, ModBlocks.golden_tile_stairs, ModBlocks.jade_brick_stairs,
            ModBlocks.cracked_jade_brick_stairs, ModBlocks.crusted_jade_brick_stairs, ModBlocks.copal_brick_stairs, ModBlocks.cracked_copal_brick_stairs, ModBlocks.crusted_copal_brick_stairs,
            ModBlocks.jet_brick_stairs, ModBlocks.cracked_jet_brick_stairs, ModBlocks.crusted_jet_brick_stairs, ModBlocks.amethyst_brick_stairs,  ModBlocks.cracked_amethyst_brick_stairs,
            ModBlocks.crusted_amethyst_brick_stairs, ModBlocks.malachite_brick_stairs, ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_tile_stairs,
            ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pillar_stairs
    );
    private static final ImmutableList<Supplier<? extends Block>> DIRT = ImmutableList.of(
            ModBlocks.heavy_soil, ModBlocks.glitter_grass, ModBlocks.corrupted_soil, ModBlocks.corrupted_grass,
            ModBlocks.boggy_soil, ModBlocks.murky_grass, ModBlocks.light_soil, ModBlocks.soft_grass, ModBlocks.aurum_soil, ModBlocks.gilded_grass
    );
    private static final ImmutableList<Supplier<? extends Block>> GAIA_BRICKS = ImmutableList.of(
            ModBlocks.gaia_stone_bricks, ModBlocks.cracked_gaia_stone_bricks, ModBlocks.crusted_gaia_stone_bricks
    );
    private static final ImmutableList<Supplier<? extends Block>> AMETHYST_BRICKS = ImmutableList.of(
            ModBlocks.amethyst_bricks, ModBlocks.cracked_amethyst_bricks, ModBlocks.crusted_amethyst_bricks
    );
    private static final ImmutableList<Supplier<? extends Block>> COPAL_BRICKS = ImmutableList.of(
            ModBlocks.copal_bricks, ModBlocks.cracked_copal_bricks, ModBlocks.crusted_copal_bricks
    );
    private static final ImmutableList<Supplier<? extends Block>> JADE_BRICKS = ImmutableList.of(
            ModBlocks.jade_bricks, ModBlocks.cracked_jade_bricks, ModBlocks.crusted_jade_bricks
    );
    private static final ImmutableList<Supplier<? extends Block>> JET_BRICKS = ImmutableList.of(
            ModBlocks.jet_bricks, ModBlocks.cracked_jet_bricks, ModBlocks.crusted_jet_bricks
    );
    private static final ImmutableList<Supplier<? extends Block>> GAIA_PLANTS = ImmutableList.of(
            ModBlocks.thiscus, ModBlocks.ouzium, ModBlocks.agathum, ModBlocks.varloom, ModBlocks.corrupted_varloom, ModBlocks.missingno_plant, ModBlocks.glamelea,
            ModBlocks.spotted_kersei, ModBlocks.thorny_wiltha, ModBlocks.roofed_agaric, ModBlocks.bulbous_hobina, ModBlocks.stickly_cupsir, ModBlocks.corrupted_gaia_eye, ModBlocks.mystical_murgni,
            ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling,
            ModBlocks.fossilized_sapling, ModBlocks.corrupted_sapling, ModBlocks.burnt_sapling, ModBlocks.fire_agate_sapling, ModBlocks.aura_sapling, ModBlocks.golden_sapling,
            ModBlocks.crystal_growth, ModBlocks.crystal_growth_mutant, ModBlocks.crystal_growth_black, ModBlocks.crystal_growth_red, ModBlocks.crystal_growth_seared, ModBlocks.crystal_growth_aura,
            ModBlocks.golden_grass, ModBlocks.tall_golden_grass, ModBlocks.twinkling_gilsri
    );

    private static final ImmutableList<Supplier<? extends Block>> AXE_TOOL = ImmutableList.of(
            ModBlocks.agate_crafting_table, ModBlocks.crude_storage_crate, ModBlocks.mega_storage_crate, ModBlocks.pink_agate_log, ModBlocks.stripped_pink_agate_log, ModBlocks.pink_agate_wood,
            ModBlocks.stripped_pink_agate_wood, ModBlocks.blue_agate_log, ModBlocks.stripped_blue_agate_log, ModBlocks.blue_agate_wood, ModBlocks.stripped_blue_agate_wood, ModBlocks.green_agate_log,
            ModBlocks.stripped_green_agate_log, ModBlocks.green_agate_wood, ModBlocks.stripped_green_agate_wood, ModBlocks.purple_agate_log, ModBlocks.stripped_purple_agate_log, ModBlocks.purple_agate_wood,
            ModBlocks.stripped_purple_agate_wood, ModBlocks.fossilized_log, ModBlocks.stripped_fossilized_log, ModBlocks.fossilized_wood, ModBlocks.stripped_fossilized_wood, ModBlocks.corrupted_log,
            ModBlocks.stripped_corrupted_log, ModBlocks.corrupted_wood, ModBlocks.stripped_corrupted_wood, ModBlocks.burnt_log, ModBlocks.stripped_burnt_log, ModBlocks.burnt_wood, ModBlocks.stripped_burnt_wood,
            ModBlocks.fire_agate_log, ModBlocks.stripped_fire_agate_log, ModBlocks.fire_agate_wood, ModBlocks.stripped_fire_agate_wood, ModBlocks.aura_log, ModBlocks.stripped_aura_log, ModBlocks.aura_wood,
            ModBlocks.stripped_aura_wood, ModBlocks.golden_log, ModBlocks.stripped_golden_log, ModBlocks.golden_wood, ModBlocks.stripped_golden_wood, ModBlocks.pink_agate_tiles, ModBlocks.blue_agate_tiles,
            ModBlocks.green_agate_tiles, ModBlocks.purple_agate_tiles, ModBlocks.fossilized_tiles, ModBlocks.corrupted_tiles, ModBlocks.burnt_tiles, ModBlocks.fire_agate_tiles, ModBlocks.aura_tiles, ModBlocks.golden_tiles,
            ModBlocks.pink_agate_tile_stairs, ModBlocks.blue_agate_tile_stairs, ModBlocks.green_agate_tile_stairs, ModBlocks.purple_agate_tile_stairs, ModBlocks.fossilized_tile_stairs,
            ModBlocks.corrupted_tile_stairs, ModBlocks.burnt_tile_stairs, ModBlocks.fire_agate_tile_stairs, ModBlocks.aura_tile_stairs, ModBlocks.golden_tile_stairs, ModBlocks.pink_agate_tile_slab,
            ModBlocks.blue_agate_tile_slab, ModBlocks.green_agate_tile_slab, ModBlocks.purple_agate_tile_slab, ModBlocks.fossilized_tile_slab, ModBlocks.corrupted_tile_slab, ModBlocks.burnt_tile_slab,
            ModBlocks.fire_agate_tile_slab, ModBlocks.aura_tile_slab, ModBlocks.golden_tile_slab
    );
    private static final ImmutableList<Supplier<? extends Block>> PICKAXE_TOOL = ImmutableList.of(
            ModBlocks.cloudy_glass, ModBlocks.foggy_glass, ModBlocks.gaia_stone_furnace, ModBlocks.saltstone, ModBlocks.scarlet_mookaite, ModBlocks.auburn_mookaite, ModBlocks.gold_mookaite,
            ModBlocks.mauve_mookaite, ModBlocks.beige_mookaite, ModBlocks.ivory_mookaite, ModBlocks.restructurer, ModBlocks.thick_glitter_block, ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone,
            ModBlocks.charged_mineral, ModBlocks.geyser_block, ModBlocks.sparkling_rock, ModBlocks.golden_stone, ModBlocks.brilliant_stone, ModBlocks.aura_shoot, ModBlocks.raw_jade,
            ModBlocks.raw_copal, ModBlocks.raw_jet, ModBlocks.raw_amethyst, ModBlocks.reinforced_bricks, ModBlocks.sugilite_ore, ModBlocks.pyrite_ore, ModBlocks.speckled_rock, ModBlocks.gaia_stone_bricks,
            ModBlocks.cracked_gaia_stone_bricks, ModBlocks.crusted_gaia_stone_bricks, ModBlocks.jade_bricks, ModBlocks.jade_brick_stairs, ModBlocks.jade_brick_slab, ModBlocks.cracked_jade_bricks,
            ModBlocks.cracked_jade_brick_stairs, ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_bricks, ModBlocks.crusted_jade_brick_stairs, ModBlocks.crusted_jade_brick_slab, ModBlocks.copal_bricks,
            ModBlocks.copal_brick_stairs, ModBlocks.copal_brick_slab, ModBlocks.cracked_copal_bricks, ModBlocks.cracked_copal_brick_stairs, ModBlocks.cracked_copal_brick_slab, ModBlocks.crusted_copal_bricks,
            ModBlocks.crusted_copal_brick_stairs, ModBlocks.crusted_copal_brick_slab, ModBlocks.jet_brick_stairs, ModBlocks.jet_brick_slab, ModBlocks.cracked_jet_bricks, ModBlocks.cracked_jet_brick_stairs,
            ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_bricks, ModBlocks.crusted_jet_brick_stairs, ModBlocks.crusted_jet_brick_slab, ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_brick_slab,
            ModBlocks.cracked_amethyst_bricks, ModBlocks.cracked_amethyst_brick_stairs, ModBlocks.cracked_amethyst_brick_slab, ModBlocks.crusted_amethyst_bricks, ModBlocks.crusted_amethyst_brick_stairs,
            ModBlocks.crusted_amethyst_brick_slab, ModBlocks.keystone_block, ModBlocks.purifier, ModBlocks.wasteland_stone, ModBlocks.static_stone, ModBlocks.volcanic_rock, ModBlocks.searing_rock,
            ModBlocks.primal_mass, ModBlocks.impure_rock, ModBlocks.active_rock, ModBlocks.tough_golden_stone, ModBlocks.gilded_brilliant_stone, ModBlocks.bolstered_bricks, ModBlocks.hematite_ore,
            ModBlocks.cinnabar_ore, ModBlocks.labradorite_ore, ModBlocks.moonstone_ore, ModBlocks.red_opal_ore, ModBlocks.blue_opal_ore, ModBlocks.green_opal_ore, ModBlocks.coarse_rock, ModBlocks.malachite_bricks,
            ModBlocks.malachite_cracked_bricks, ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_tiles, ModBlocks.malachite_chisel_bricks, ModBlocks.malachite_pulsing_bricks,
            ModBlocks.malachite_pulsing_tiles, ModBlocks.malachite_pulsing_chisel, ModBlocks.malachite_brick_slab, ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab,
            ModBlocks.malachite_tile_slab, ModBlocks.malachite_pillar, ModBlocks.malachite_brick_stairs, ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs,
            ModBlocks.malachite_tile_stairs, ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_chisel_stairs,
            ModBlocks.malachite_pillar_stairs, ModBlocks.sugilite_block, ModBlocks.hematite_block, ModBlocks.cinnabar_block, ModBlocks.labradorite_block, ModBlocks.moonstone_block, ModBlocks.red_opal_block,
            ModBlocks.blue_opal_block, ModBlocks.green_opal_block, ModBlocks.white_opal_block, ModBlocks.pyrite_block, ModBlocks.tektite_block, ModBlocks.goldstone_block, ModBlocks.aura_block,
            ModBlocks.bismuth_block, ModBlocks.stibnite_block, ModBlocks.proustite_block, ModBlocks.euclase_block, ModBlocks.albite_block, ModBlocks.carnelian_block, ModBlocks.benitoite_block,
            ModBlocks.diopside_block, ModBlocks.goshenite_block, ModBlocks.nexustone, ModBlocks.white_opal_ore, ModBlocks.precious_rock, ModBlocks.scarlet_opalite_ore, ModBlocks.auburn_opalite_ore,
            ModBlocks.gold_opalite_ore, ModBlocks.mauve_opalite_ore, ModBlocks.beige_opalite_ore, ModBlocks.ivory_opalite_ore, ModBlocks.opalite_block, ModBlocks.celestine_block, ModBlocks.celestine_ore
    );
    private static final ImmutableList<Supplier<? extends Block>> SHOVEL_TOOL = ImmutableList.of(
            ModBlocks.heavy_soil, ModBlocks.corrupted_soil, ModBlocks.boggy_soil, ModBlocks.light_soil, ModBlocks.aurum_soil, ModBlocks.glitter_grass, ModBlocks.corrupted_grass, ModBlocks.murky_grass,
            ModBlocks.soft_grass, ModBlocks.gilded_grass, ModBlocks.salt, ModBlocks.pebbles, ModBlocks.impure_sludge, ModBlocks.aurum_mud, ModBlocks.golden_sand
    );

    private static final ImmutableList<Supplier<? extends Block>> NEEDS_STONE = ImmutableList.of(
            ModBlocks.restructurer, ModBlocks.thick_glitter_block, ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone, ModBlocks.charged_mineral, ModBlocks.geyser_block, ModBlocks.sparkling_rock, ModBlocks.golden_stone,
            ModBlocks.brilliant_stone, ModBlocks.aura_shoot, ModBlocks.raw_jade, ModBlocks.raw_copal, ModBlocks.raw_jet, ModBlocks.raw_amethyst, ModBlocks.reinforced_bricks, ModBlocks.sugilite_ore,
            ModBlocks.pyrite_ore, ModBlocks.scarlet_opalite_ore, ModBlocks.auburn_opalite_ore, ModBlocks.gold_opalite_ore, ModBlocks.mauve_opalite_ore, ModBlocks.beige_opalite_ore, ModBlocks.ivory_opalite_ore,
            ModBlocks.speckled_rock, ModBlocks.gaia_stone_bricks, ModBlocks.cracked_gaia_stone_bricks, ModBlocks.crusted_gaia_stone_bricks, ModBlocks.jade_bricks, ModBlocks.jade_brick_stairs,
            ModBlocks.jade_brick_slab, ModBlocks.cracked_jade_bricks, ModBlocks.cracked_jade_brick_stairs, ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_bricks, ModBlocks.crusted_jade_brick_stairs,
            ModBlocks.crusted_jade_brick_slab, ModBlocks.copal_bricks, ModBlocks.copal_brick_stairs, ModBlocks.copal_brick_slab, ModBlocks.cracked_copal_bricks, ModBlocks.cracked_copal_brick_stairs,
            ModBlocks.cracked_copal_brick_slab, ModBlocks.crusted_copal_bricks, ModBlocks.crusted_copal_brick_stairs, ModBlocks.crusted_copal_brick_slab, ModBlocks.jet_brick_stairs, ModBlocks.jet_brick_slab,
            ModBlocks.cracked_jet_bricks, ModBlocks.cracked_jet_brick_stairs, ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_bricks, ModBlocks.crusted_jet_brick_stairs, ModBlocks.crusted_jet_brick_slab,
            ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_brick_slab, ModBlocks.cracked_amethyst_bricks, ModBlocks.cracked_amethyst_brick_stairs, ModBlocks.cracked_amethyst_brick_slab, ModBlocks.crusted_amethyst_bricks,
            ModBlocks.crusted_amethyst_brick_stairs, ModBlocks.crusted_amethyst_brick_slab
    );
    private static final ImmutableList<Supplier<? extends Block>> NEEDS_IRON = ImmutableList.of(
            ModBlocks.keystone_block, ModBlocks.purifier, ModBlocks.wasteland_stone, ModBlocks.static_stone, ModBlocks.volcanic_rock, ModBlocks.searing_rock, ModBlocks.primal_mass, ModBlocks.impure_rock,
            ModBlocks.active_rock, ModBlocks.tough_golden_stone, ModBlocks.gilded_brilliant_stone, ModBlocks.bolstered_bricks, ModBlocks.hematite_ore, ModBlocks.cinnabar_ore, ModBlocks.labradorite_ore, ModBlocks.moonstone_ore,
            ModBlocks.red_opal_ore, ModBlocks.blue_opal_ore, ModBlocks.green_opal_ore, ModBlocks.coarse_rock, ModBlocks.malachite_bricks, ModBlocks.malachite_cracked_bricks, ModBlocks.malachite_crusted_bricks,
            ModBlocks.malachite_tiles, ModBlocks.malachite_chisel_bricks, ModBlocks.malachite_pulsing_bricks, ModBlocks.malachite_pulsing_tiles, ModBlocks.malachite_pulsing_chisel, ModBlocks.malachite_brick_slab,
            ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_tile_slab, ModBlocks.malachite_pillar, ModBlocks.malachite_brick_stairs, ModBlocks.malachite_cracked_brick_stairs,
            ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_tile_stairs, ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_floor_stairs,
            ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pillar_stairs, ModBlocks.sugilite_block, ModBlocks.hematite_block, ModBlocks.cinnabar_block, ModBlocks.labradorite_block, ModBlocks.moonstone_block,
            ModBlocks.red_opal_block, ModBlocks.blue_opal_block, ModBlocks.green_opal_block, ModBlocks.white_opal_block, ModBlocks.pyrite_block, ModBlocks.tektite_block, ModBlocks.goldstone_block, ModBlocks.aura_block,
            ModBlocks.bismuth_block, ModBlocks.stibnite_block, ModBlocks.proustite_block, ModBlocks.euclase_block, ModBlocks.albite_block, ModBlocks.carnelian_block, ModBlocks.benitoite_block, ModBlocks.diopside_block,
            ModBlocks.goshenite_block, ModBlocks.opalite_block, ModBlocks.celestine_block
    );
    private static final ImmutableList<Supplier<? extends Block>> NEEDS_DIAMOND = ImmutableList.of(
            ModBlocks.nexustone, ModBlocks.white_opal_ore, ModBlocks.precious_rock, ModBlocks.celestine_ore
    );

    public GaiaBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, GaiaDimensionMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTag(BlockTags.BEACON_BASE_BLOCKS, BEACON_BASES);
        tag(BlockTags.CLIMBABLE).add(ModBlocks.golden_vine.get());
        addTag(BlockTags.DIRT, DIRT);
        addTag(BlockTags.FLOWER_POTS, FLOWER_POTS);
        addTag(BlockTags.GUARDED_BY_PIGLINS, GUARDED_BY_PIGLINS);
        addTag(BlockTags.IMPERMEABLE, IMPERMEABLE);
        addTag(BlockTags.LEAVES, LEAVES);
        tag(BlockTags.LOGS).addTags(
                GaiaTags.Blocks.PINK_AGATE_LOGS, GaiaTags.Blocks.BLUE_AGATE_LOGS, GaiaTags.Blocks.GREEN_AGATE_LOGS, GaiaTags.Blocks.PURPLE_AGATE_LOGS,
                GaiaTags.Blocks.FOSSILIZED_LOGS, GaiaTags.Blocks.CORRUPTED_LOGS, GaiaTags.Blocks.BURNT_LOGS, GaiaTags.Blocks.BURNING_LOGS, GaiaTags.Blocks.AURA_LOGS, GaiaTags.Blocks.GOLDEN_LOGS);
        tag(BlockTags.PORTALS).add(ModBlocks.gaia_portal.get());
        addTag(BlockTags.SAPLINGS, SAPLINGS);
        addTag(BlockTags.SLABS, SLABS);
        addTag(BlockTags.SMALL_FLOWERS, SMALL_FLOWERS);
        addTag(BlockTags.STAIRS, STAIRS);

        addTag(BlockTags.MINEABLE_WITH_AXE, AXE_TOOL);
        addTag(BlockTags.MINEABLE_WITH_PICKAXE, PICKAXE_TOOL);
        addTag(BlockTags.MINEABLE_WITH_SHOVEL, SHOVEL_TOOL);
        addTag(BlockTags.NEEDS_STONE_TOOL, NEEDS_STONE);
        addTag(BlockTags.NEEDS_IRON_TOOL, NEEDS_IRON);
        addTag(BlockTags.NEEDS_DIAMOND_TOOL, NEEDS_DIAMOND);

        tag(GaiaTags.Blocks.ORES_SUGILITE).add(ModBlocks.sugilite_ore.get());
        tag(GaiaTags.Blocks.ORES_HEMATITE).add(ModBlocks.hematite_ore.get());
        tag(GaiaTags.Blocks.ORES_CINNABAR).add(ModBlocks.cinnabar_ore.get());
        tag(GaiaTags.Blocks.ORES_LABRADORITE).add(ModBlocks.labradorite_ore.get());
        tag(GaiaTags.Blocks.ORES_MOONSTONE).add(ModBlocks.moonstone_ore.get());
        tag(GaiaTags.Blocks.ORES_RED_OPAL).add(ModBlocks.red_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_BLUE_OPAL).add(ModBlocks.blue_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_GREEN_OPAL).add(ModBlocks.green_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_WHITE_OPAL).add(ModBlocks.white_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_PYRITE).add(ModBlocks.pyrite_ore.get());
        tag(GaiaTags.Blocks.ORES_OPALITE).add(ModBlocks.scarlet_opalite_ore.get(), ModBlocks.auburn_opalite_ore.get(), ModBlocks.gold_opalite_ore.get(), ModBlocks.mauve_opalite_ore.get(), ModBlocks.beige_opalite_ore.get(), ModBlocks.ivory_opalite_ore.get());
        tag(GaiaTags.Blocks.ORES_CELESTINE).add(ModBlocks.celestine_ore.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE).add(ModBlocks.sugilite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE).add(ModBlocks.hematite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR).add(ModBlocks.cinnabar_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE).add(ModBlocks.labradorite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE).add(ModBlocks.moonstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL).add(ModBlocks.red_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL).add(ModBlocks.blue_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL).add(ModBlocks.green_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL).add(ModBlocks.white_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE).add(ModBlocks.pyrite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE).add(ModBlocks.tektite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE).add(ModBlocks.goldstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL).add(ModBlocks.aura_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH).add(ModBlocks.bismuth_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_OPALITE).add(ModBlocks.opalite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_STIBNITE).add(ModBlocks.stibnite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE).add(ModBlocks.proustite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE).add(ModBlocks.euclase_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_ALBITE).add(ModBlocks.albite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN).add(ModBlocks.carnelian_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE).add(ModBlocks.benitoite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE).add(ModBlocks.diopside_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GOSHENITE).add(ModBlocks.goshenite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CELESTINE).add(ModBlocks.celestine_block.get());
        addTag(GaiaTags.Blocks.TILES, TILES);

        //addTag(Tags.Blocks.DIRT, DIRT);
        tag(Tags.Blocks.GLASS_BLOCKS).add(ModBlocks.foggy_glass.get(), ModBlocks.cloudy_glass.get());
        tag(Tags.Blocks.ORES).addTags(
                GaiaTags.Blocks.ORES_SUGILITE, GaiaTags.Blocks.ORES_HEMATITE, GaiaTags.Blocks.ORES_CINNABAR, GaiaTags.Blocks.ORES_LABRADORITE, GaiaTags.Blocks.ORES_MOONSTONE,
                GaiaTags.Blocks.ORES_RED_OPAL, GaiaTags.Blocks.ORES_BLUE_OPAL, GaiaTags.Blocks.ORES_GREEN_OPAL, GaiaTags.Blocks.ORES_WHITE_OPAL, GaiaTags.Blocks.ORES_PYRITE, GaiaTags.Blocks.ORES_OPALITE);
        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE, GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE, GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR,GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE,
                GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL,GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL,
                GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE, GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE, GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE,
                GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH, GaiaTags.Blocks.STORAGE_BLOCKS_OPALITE, GaiaTags.Blocks.STORAGE_BLOCKS_STIBNITE,
                GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE, GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE, GaiaTags.Blocks.STORAGE_BLOCKS_ALBITE, GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN,
                GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE, GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Blocks.STORAGE_BLOCKS_GOSHENITE, GaiaTags.Blocks.STORAGE_BLOCKS_CELESTINE);


        addTag(GaiaTags.Blocks.PINK_AGATE_LOGS, PINK_AGATE_LOGS);
        addTag(GaiaTags.Blocks.BLUE_AGATE_LOGS, BLUE_AGATE_LOGS);
        addTag(GaiaTags.Blocks.GREEN_AGATE_LOGS, GREEN_AGATE_LOGS);
        addTag(GaiaTags.Blocks.PURPLE_AGATE_LOGS, PURPLE_AGATE_LOGS);
        addTag(GaiaTags.Blocks.FOSSILIZED_LOGS, FOSSILIZED_LOGS);
        addTag(GaiaTags.Blocks.CORRUPTED_LOGS, CORRUPTED_LOGS);
        addTag(GaiaTags.Blocks.BURNT_LOGS, BURNT_LOGS);
        addTag(GaiaTags.Blocks.BURNING_LOGS, BURNING_LOGS);
        addTag(GaiaTags.Blocks.AURA_LOGS, AURA_LOGS);
        addTag(GaiaTags.Blocks.GOLDEN_LOGS, GOLDEN_LOGS);
        tag(GaiaTags.Blocks.GAIA_STONE).add(ModBlocks.gaia_stone.get());
        tag(GaiaTags.Blocks.STATIC).add(ModBlocks.gaia_stone.get(), ModBlocks.wasteland_stone.get());
        tag(GaiaTags.Blocks.VOLCANIC).add(ModBlocks.gaia_stone.get(), ModBlocks.volcanic_rock.get());
        tag(GaiaTags.Blocks.PRIMAL).add(ModBlocks.primal_mass.get());
        tag(GaiaTags.Blocks.MOOKAITE).add(ModBlocks.scarlet_mookaite.get(), ModBlocks.auburn_mookaite.get(), ModBlocks.gold_mookaite.get(), ModBlocks.mauve_mookaite.get(), ModBlocks.beige_mookaite.get(), ModBlocks.ivory_mookaite.get());
        tag(GaiaTags.Blocks.GAIA_GRASS).add(ModBlocks.glitter_grass.get(), ModBlocks.corrupted_grass.get(), ModBlocks.murky_grass.get(), ModBlocks.soft_grass.get(), ModBlocks.gilded_grass.get());
        tag(GaiaTags.Blocks.GAIA_SOIL).add(ModBlocks.heavy_soil.get(), ModBlocks.corrupted_soil.get(), ModBlocks.boggy_soil.get(), ModBlocks.light_soil.get(), ModBlocks.aurum_soil.get());
        tag(GaiaTags.Blocks.GAIA_CARVER_REPLACEABLES).addTags(GaiaTags.Blocks.GAIA_GRASS, GaiaTags.Blocks.GAIA_SOIL)
                .add(ModBlocks.saltstone.get(), ModBlocks.gaia_stone.get(), ModBlocks.wasteland_stone.get(), ModBlocks.volcanic_rock.get(), ModBlocks.primal_mass.get(), ModBlocks.nexustone.get());
        addTag(GaiaTags.Blocks.GAIA_PLANTS, GAIA_PLANTS);
        addTag(GaiaTags.Blocks.GAIA_BRICKS, GAIA_BRICKS);
        addTag(GaiaTags.Blocks.AMETHYST_BRICKS, AMETHYST_BRICKS);
        addTag(GaiaTags.Blocks.COPAL_BRICKS, COPAL_BRICKS);
        addTag(GaiaTags.Blocks.JADE_BRICKS, JADE_BRICKS);
        addTag(GaiaTags.Blocks.JET_BRICKS, JET_BRICKS);

        tag(GaiaTags.Blocks.INCORRECT_FOR_AGATE).addTag(BlockTags.INCORRECT_FOR_STONE_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_SUGILITE).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_STIBNITE).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_EUCLASE).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_CARNELIAN).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_BENITOITE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_GOSHENITE).addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_MALACHITE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_TIGER_EYE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_SPINEL).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_ZIRCON).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_CORRUPT).addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_BIXBITE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_TSAVORITE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_LARVIKITE).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);
        tag(GaiaTags.Blocks.INCORRECT_FOR_CHAMPION).addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
    }
}
