package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBlockStateProvider;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;

public class GaiaBlockStates extends GaiaBlockStateProvider {

    public GaiaBlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GaiaDimensionMod.MODID, helper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Gaia Blockstates and Models";
    }

    @Override
    protected void registerStatesAndModels() {
        basicBlock(ModBlocks.keystone_block);
        crossBlock(ModBlocks.gold_fire);
        torchBlock(ModBlocks.pyrite_torch, ModBlocks.pyrite_wall_torch);
        sidedBlock(ModBlocks.agate_crafting_table, "agate_table_top", "agate_table_bottom", "agate_table_front", "agate_table_side", "agate_table_side", "agate_table_side");
        basicBlock(ModBlocks.crude_storage_crate);
        basicBlock(ModBlocks.mega_storage_crate);
        orientableBlockLit(ModBlocks.gaia_stone_furnace);
        orientableBlockBasicLit(ModBlocks.restructurer);
        orientableBlockBasicLit(ModBlocks.purifier);
        basicBlockRotated(ModBlocks.heavy_soil);
        basicBlockRotated(ModBlocks.corrupt_soil);
        basicBlockRotated(ModBlocks.boggy_soil);
        basicBlockRotated(ModBlocks.light_soil);
        grassBlock(ModBlocks.glitter_grass, "heavy_soil");
        grassBlock(ModBlocks.corrupt_grass, "corrupt_soil");
        grassBlock(ModBlocks.murky_grass, "boggy_soil");
        grassBlock(ModBlocks.soft_grass, "light_soil");
        basicBlockRotated(ModBlocks.frail_glitter_block);
        basicBlockRotated(ModBlocks.thick_glitter_block);
        basicBlockRotated(ModBlocks.gummy_glitter_block);
        basicBlockRotated(ModBlocks.pink_sludge_block);
        crossBlockTinted(ModBlocks.crystal_growth);
        crossBlock(ModBlocks.crystal_growth_red);
        crossBlock(ModBlocks.crystal_growth_black);
        crossBlock(ModBlocks.crystal_growth_seared);
        crossBlock(ModBlocks.crystal_growth_mutant);
        crossBlock(ModBlocks.crystal_growth_aura);
        crossBlock(ModBlocks.thiscus);
        crossBlock(ModBlocks.ouzium);
        crossBlock(ModBlocks.agathum);
        crossBlock(ModBlocks.varloom);
        crossBlock(ModBlocks.corrupted_varloom);
        crossBlock(ModBlocks.missingno_plant);
        crossBlock(ModBlocks.spotted_kersei);
        crossBlock(ModBlocks.thorny_wiltha);
        crossBlock(ModBlocks.roofed_agaric);
        crossBlock(ModBlocks.bulbous_hobina);
        crossBlock(ModBlocks.stickly_cupsir);
        crossBlock(ModBlocks.mystical_murgni);
        crossBlock(ModBlocks.corrupted_gaia_eye);
        crossBlock(ModBlocks.elder_imklia);
        crossBlock(ModBlocks.gold_orb_tucher);
        crossBlock(ModBlocks.missingno_fungus);
        crossBlock(ModBlocks.pink_agate_sapling);
        crossBlock(ModBlocks.blue_agate_sapling);
        crossBlock(ModBlocks.green_agate_sapling);
        crossBlock(ModBlocks.purple_agate_sapling);
        crossBlock(ModBlocks.fossilized_sapling);
        crossBlock(ModBlocks.corrupted_sapling);
        crossBlock(ModBlocks.burnt_sapling);
        crossBlock(ModBlocks.burning_sapling);
        crossBlock(ModBlocks.aura_sapling);
        basicBlock(ModBlocks.pink_agate_leaves);
        basicBlock(ModBlocks.blue_agate_leaves);
        basicBlock(ModBlocks.green_agate_leaves);
        basicBlock(ModBlocks.purple_agate_leaves);
        basicBlock(ModBlocks.fossilized_leaves);
        basicBlock(ModBlocks.corrupted_leaves);
        basicBlock(ModBlocks.burnt_leaves);
        basicBlock(ModBlocks.burning_leaves);
        basicBlock(ModBlocks.aura_leaves);
        logBlock(ModBlocks.pink_agate_log, "pink_agate_log");
        logBlock(ModBlocks.blue_agate_log, "blue_agate_log");
        logBlock(ModBlocks.green_agate_log, "green_agate_log");
        logBlock(ModBlocks.purple_agate_log, "purple_agate_log");
        logBlock(ModBlocks.fossilized_log, "fossilized_log");
        logBlock(ModBlocks.corrupted_log, "corrupted_log");
        logBlock(ModBlocks.burnt_log, "burnt_log");
        logBlock(ModBlocks.burning_log, "burning_log");
        logBlock(ModBlocks.aura_log, "aura_log");
        strippedLogBlock(ModBlocks.stripped_pink_agate_log, "pink_agate_log");
        strippedLogBlock(ModBlocks.stripped_blue_agate_log, "blue_agate_log");
        strippedLogBlock(ModBlocks.stripped_green_agate_log, "green_agate_log");
        strippedLogBlock(ModBlocks.stripped_purple_agate_log, "purple_agate_log");
        strippedLogBlock(ModBlocks.stripped_fossilized_log, "fossilized_log");
        strippedLogBlock(ModBlocks.stripped_corrupted_log, "corrupted_log");
        strippedLogBlock(ModBlocks.stripped_burnt_log, "burnt_log");
        strippedLogBlock(ModBlocks.stripped_burning_log, "burning_log");
        strippedLogBlock(ModBlocks.stripped_aura_log, "aura_log");
        woodBlock(ModBlocks.pink_agate_wood, "pink_agate_log");
        woodBlock(ModBlocks.blue_agate_wood, "blue_agate_log");
        woodBlock(ModBlocks.green_agate_wood, "green_agate_log");
        woodBlock(ModBlocks.purple_agate_wood, "purple_agate_log");
        woodBlock(ModBlocks.fossilized_wood, "fossilized_log");
        woodBlock(ModBlocks.corrupted_wood, "corrupted_log");
        woodBlock(ModBlocks.burnt_wood, "burnt_log");
        woodBlock(ModBlocks.burning_wood, "burning_log");
        woodBlock(ModBlocks.aura_wood, "aura_log");
        strippedWoodBlock(ModBlocks.stripped_pink_agate_wood, "pink_agate");
        strippedWoodBlock(ModBlocks.stripped_blue_agate_wood, "blue_agate");
        strippedWoodBlock(ModBlocks.stripped_green_agate_wood, "green_agate");
        strippedWoodBlock(ModBlocks.stripped_purple_agate_wood, "purple_agate");
        strippedWoodBlock(ModBlocks.stripped_fossilized_wood, "fossilized");
        strippedWoodBlock(ModBlocks.stripped_corrupted_wood, "corrupted");
        strippedWoodBlock(ModBlocks.stripped_burnt_wood, "burnt");
        strippedWoodBlock(ModBlocks.stripped_burning_wood, "burning");
        strippedWoodBlock(ModBlocks.stripped_aura_wood, "aura");
        basicBlockRotated(ModBlocks.salt);
        sidedBlock(ModBlocks.saltstone, "salt_rock_top", "salt_rock_bottom", "salt_rock_side", "salt_rock_side", "salt_rock_side", "salt_rock_side");
        basicBlock(ModBlocks.pebbles);
        basicBlockRotated(ModBlocks.gaia_stone);
        basicBlock(ModBlocks.gaia_cobblestone);
        basicBlock(ModBlocks.wasteland_stone);
        basicBlockLayered(ModBlocks.static_stone, "wasteland_stone", "static_stone");
        basicBlock(ModBlocks.charged_mineral);
        basicBlock(ModBlocks.volcanic_rock);
        basicBlock(ModBlocks.searing_rock);
        basicBlock(ModBlocks.primal_mass);
        basicBlock(ModBlocks.impure_rock);
        basicBlockLayered(ModBlocks.active_rock, "active_rock", "active_rock_overlay");
        basicBlock(ModBlocks.impure_sludge);
        sidedBlock(ModBlocks.geyser_block, "geyser_block_top", "bismuth_block", "geyser_block_side", "geyser_block_side", "geyser_block_side", "geyser_block_side");
        basicBlock(ModBlocks.sparkling_rock);
        basicBlock(ModBlocks.pink_agate_planks);
        basicBlock(ModBlocks.blue_agate_planks);
        basicBlock(ModBlocks.green_agate_planks);
        basicBlock(ModBlocks.purple_agate_planks);
        basicBlock(ModBlocks.fossilized_planks);
        basicBlock(ModBlocks.corrupted_planks);
        basicBlock(ModBlocks.burnt_planks);
        basicBlock(ModBlocks.burning_planks);
        basicBlock(ModBlocks.aura_planks);
        slabBlock(ModBlocks.pink_agate_plank_slab, ModBlocks.pink_agate_planks);
        slabBlock(ModBlocks.blue_agate_plank_slab, ModBlocks.blue_agate_planks);
        slabBlock(ModBlocks.green_agate_plank_slab, ModBlocks.green_agate_planks);
        slabBlock(ModBlocks.purple_agate_plank_slab, ModBlocks.purple_agate_planks);
        slabBlock(ModBlocks.fossilized_plank_slab, ModBlocks.fossilized_planks);
        slabBlock(ModBlocks.corrupted_plank_slab, ModBlocks.corrupted_planks);
        slabBlock(ModBlocks.burnt_plank_slab, ModBlocks.burnt_planks);
        slabBlock(ModBlocks.burning_plank_slab, ModBlocks.burning_planks);
        slabBlock(ModBlocks.aura_plank_slab, ModBlocks.aura_planks);
        stairsBlock(ModBlocks.pink_agate_plank_stairs, "pink_agate_planks");
        stairsBlock(ModBlocks.blue_agate_plank_stairs, "blue_agate_planks");
        stairsBlock(ModBlocks.green_agate_plank_stairs, "green_agate_planks");
        stairsBlock(ModBlocks.purple_agate_plank_stairs, "purple_agate_planks");
        stairsBlock(ModBlocks.fossilized_plank_stairs, "fossilized_planks");
        stairsBlock(ModBlocks.corrupted_plank_stairs, "corrupted_planks");
        stairsBlock(ModBlocks.burnt_plank_stairs, "burnt_planks");
        stairsBlock(ModBlocks.burning_plank_stairs, "burning_planks");
        stairsBlock(ModBlocks.aura_plank_stairs, "aura_planks");
        basicBlock(ModBlocks.cloudy_glass);
        basicBlock(ModBlocks.foggy_glass);
        basicBlock(ModBlocks.gaia_stone_bricks);
        basicBlock(ModBlocks.cracked_gaia_stone_bricks);
        basicBlock(ModBlocks.crusted_gaia_stone_bricks);

        basicBlock(ModBlocks.raw_jade);
        basicBlock(ModBlocks.jade_bricks);
        slabBlock(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks);
        stairsBlock(ModBlocks.jade_brick_stairs, "jade_bricks");
        basicBlock(ModBlocks.cracked_jade_bricks);
        slabBlock(ModBlocks.cracked_jade_brick_slab, ModBlocks.cracked_jade_bricks);
        stairsBlock(ModBlocks.cracked_jade_brick_stairs, "cracked_jade_bricks");
        basicBlock(ModBlocks.crusted_jade_bricks);
        slabBlock(ModBlocks.crusted_jade_brick_slab, ModBlocks.crusted_jade_bricks);
        stairsBlock(ModBlocks.crusted_jade_brick_stairs, "crusted_jade_bricks");

        basicBlock(ModBlocks.raw_copal);
        basicBlock(ModBlocks.copal_bricks);
        slabBlock(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks);
        stairsBlock(ModBlocks.copal_brick_stairs, "copal_bricks");
        basicBlock(ModBlocks.cracked_copal_bricks);
        slabBlock(ModBlocks.cracked_copal_brick_slab, ModBlocks.cracked_copal_bricks);
        stairsBlock(ModBlocks.cracked_copal_brick_stairs, "cracked_copal_bricks");
        basicBlock(ModBlocks.crusted_copal_bricks);
        slabBlock(ModBlocks.crusted_copal_brick_slab, ModBlocks.crusted_copal_bricks);
        stairsBlock(ModBlocks.crusted_copal_brick_stairs, "crusted_copal_bricks");

        basicBlock(ModBlocks.raw_jet);
        basicBlock(ModBlocks.jet_bricks);
        slabBlock(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks);
        stairsBlock(ModBlocks.jet_brick_stairs, "jet_bricks");
        basicBlock(ModBlocks.cracked_jet_bricks);
        slabBlock(ModBlocks.cracked_jet_brick_slab, ModBlocks.cracked_jet_bricks);
        stairsBlock(ModBlocks.cracked_jet_brick_stairs, "cracked_jet_bricks");
        basicBlock(ModBlocks.crusted_jet_bricks);
        slabBlock(ModBlocks.crusted_jet_brick_slab, ModBlocks.crusted_jet_bricks);
        stairsBlock(ModBlocks.crusted_jet_brick_stairs, "crusted_jet_bricks");

        basicBlock(ModBlocks.raw_amethyst);
        basicBlock(ModBlocks.amethyst_bricks);
        slabBlock(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks);
        stairsBlock(ModBlocks.amethyst_brick_stairs, "amethyst_bricks");
        basicBlock(ModBlocks.cracked_amethyst_bricks);
        slabBlock(ModBlocks.cracked_amethyst_brick_slab, ModBlocks.cracked_amethyst_bricks);
        stairsBlock(ModBlocks.cracked_amethyst_brick_stairs, "cracked_amethyst_bricks");
        basicBlock(ModBlocks.crusted_amethyst_bricks);
        slabBlock(ModBlocks.crusted_amethyst_brick_slab, ModBlocks.crusted_amethyst_bricks);
        stairsBlock(ModBlocks.crusted_amethyst_brick_stairs, "crusted_amethyst_bricks");

        basicBlock(ModBlocks.reinforced_bricks);
        basicBlock(ModBlocks.bolstered_bricks);
        basicBlock(ModBlocks.malachite_bricks);
        basicBlock(ModBlocks.malachite_cracked_bricks);
        basicBlock(ModBlocks.malachite_crusted_bricks);
        basicBlock(ModBlocks.malachite_floor_tiles);
        basicBlock(ModBlocks.malachite_chisel_bricks);
        basicBlockLayered(ModBlocks.malachite_pulsing_bricks, "malachite_bricks", "malachite_green_pulse_corner");
        basicBlockLayered(ModBlocks.malachite_pulsing_tiles, "malachite_floor_tiles", "malachite_blue_pulse_corner");
        basicBlockLayered(ModBlocks.malachite_pulsing_chisel, "malachite_chisel_bricks", "malachite_lime_pulse_corner");
        slabBlock(ModBlocks.malachite_brick_slab, ModBlocks.malachite_bricks);
        slabBlock(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_cracked_bricks);
        slabBlock(ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_crusted_bricks);
        slabBlock(ModBlocks.malachite_floor_slab, ModBlocks.malachite_floor_tiles);
        axisBlock(ModBlocks.malachite_pillar.get());
        stairsBlock(ModBlocks.malachite_brick_stairs, "malachite_bricks");
        stairsBlock(ModBlocks.malachite_cracked_brick_stairs, "malachite_cracked_bricks");
        stairsBlock(ModBlocks.malachite_crusted_brick_stairs, "malachite_crusted_bricks");
        stairsBlock(ModBlocks.malachite_floor_stairs, "malachite_floor_tiles");
        stairsBlock(ModBlocks.malachite_chisel_stairs, "malachite_chisel_bricks");
        stairsBlockLayered(ModBlocks.malachite_pulsing_brick_stairs, "malachite_bricks", "malachite_green_pulse_corner");
        stairsBlockLayered(ModBlocks.malachite_pulsing_floor_stairs, "malachite_floor_tiles", "malachite_blue_pulse_corner");
        stairsBlockLayered(ModBlocks.malachite_pulsing_chisel_stairs, "malachite_chisel_bricks", "malachite_lime_pulse_corner");
        stairsBlock(ModBlocks.malachite_pillar_stairs.get(), tLocGaia("malachite_pillar_side"), tLocGaia("malachite_pillar_end"), tLocGaia("malachite_pillar_end"));
        basicBlock(ModBlocks.sugilite_block);
        basicBlock(ModBlocks.hematite_block);
        basicBlock(ModBlocks.cinnabar_block);
        basicBlock(ModBlocks.labradorite_block);
        basicBlock(ModBlocks.moonstone_block);
        basicBlock(ModBlocks.opal_block_red);
        basicBlock(ModBlocks.opal_block_blue);
        basicBlock(ModBlocks.opal_block_green);
        basicBlock(ModBlocks.opal_block_white);
        basicBlock(ModBlocks.pyrite_block);
        basicBlock(ModBlocks.tektite_block);
        basicBlock(ModBlocks.goldstone_block);
        basicBlock(ModBlocks.aura_block);
        basicBlock(ModBlocks.bismuth_block);
        basicBlock(ModBlocks.ixiolite_block);
        basicBlock(ModBlocks.proustite_block);
        basicBlock(ModBlocks.euclase_block);
        basicBlock(ModBlocks.leucite_block);
        basicBlock(ModBlocks.carnelian_block);
        basicBlock(ModBlocks.benitoite_block);
        basicBlock(ModBlocks.diopside_block);
        basicBlock(ModBlocks.chalcedony_block);
        basicBlock(ModBlocks.sugilite_ore);
        basicBlock(ModBlocks.hematite_ore);
        basicBlock(ModBlocks.cinnabar_ore);
        basicBlock(ModBlocks.labradorite_ore);
        basicBlock(ModBlocks.moonstone_ore);
        basicBlock(ModBlocks.opal_ore_red);
        basicBlock(ModBlocks.opal_ore_blue);
        basicBlock(ModBlocks.opal_ore_green);
        basicBlock(ModBlocks.opal_ore_white);
        basicBlock(ModBlocks.pyrite_ore);
        basicBlock(ModBlocks.speckled_rock);
        basicBlock(ModBlocks.coarse_rock);
        basicBlock(ModBlocks.precious_rock);
        pottedPlantBlock(ModBlocks.potted_thiscus);
        pottedPlantBlock(ModBlocks.potted_ouzium);
        pottedPlantBlock(ModBlocks.potted_agathum);
        pottedPlantBlock(ModBlocks.potted_varloom);
        pottedPlantBlock(ModBlocks.potted_corrupted_varloom);
        pottedPlantBlock(ModBlocks.potted_missingno_plant);
        pottedPlantBlock(ModBlocks.potted_spotted_kersei);
        pottedPlantBlock(ModBlocks.potted_thorny_wiltha);
        pottedPlantBlock(ModBlocks.potted_roofed_agaric);
        pottedPlantBlock(ModBlocks.potted_bulbous_hobina);
        pottedPlantBlock(ModBlocks.potted_stickly_cupsir);
        pottedPlantBlock(ModBlocks.potted_mystical_murgni);
        pottedPlantBlock(ModBlocks.potted_corrupted_gaia_eye);
        pottedPlantBlock(ModBlocks.potted_elder_imklia);
        pottedPlantBlock(ModBlocks.potted_gold_orb_tucher);
        pottedPlantBlock(ModBlocks.potted_missingno_fungus);
        pottedPlantBlock(ModBlocks.potted_pink_agate_sapling);
        pottedPlantBlock(ModBlocks.potted_blue_agate_sapling);
        pottedPlantBlock(ModBlocks.potted_green_agate_sapling);
        pottedPlantBlock(ModBlocks.potted_purple_agate_sapling);
        pottedPlantBlock(ModBlocks.potted_fossilized_sapling);
        pottedPlantBlock(ModBlocks.potted_corrupted_sapling);
        pottedPlantBlock(ModBlocks.potted_burnt_sapling);
        pottedPlantBlock(ModBlocks.potted_burning_sapling);
        pottedPlantBlock(ModBlocks.potted_aura_sapling);
    }
}
