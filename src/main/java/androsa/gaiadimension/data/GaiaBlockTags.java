package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBlockTagsProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaBlockTags extends GaiaBlockTagsProvider {

    private static final ImmutableList<Supplier<? extends Block>> BEACON_BASES = ImmutableList.of(
            ModBlocks.sugilite_block, ModBlocks.hematite_block, ModBlocks.cinnabar_block, ModBlocks.labradorite_block, ModBlocks.moonstone_block, ModBlocks.opal_block_red,
            ModBlocks.opal_block_blue, ModBlocks.opal_block_green,  ModBlocks.opal_block_white, ModBlocks.pyrite_block, ModBlocks.tektite_block, ModBlocks.goldstone_block,
            ModBlocks.aura_block, ModBlocks.bismuth_block, ModBlocks.ixiolite_block, ModBlocks.proustite_block, ModBlocks.euclase_block, ModBlocks.leucite_block,
            ModBlocks.carnelian_block, ModBlocks.benitoite_block, ModBlocks.diopside_block, ModBlocks.chalcedony_block
    );
    private static final ImmutableList<Supplier<? extends Block>> FLOWER_POTS = ImmutableList.of(
            ModBlocks.potted_thiscus, ModBlocks.potted_ouzium, ModBlocks.potted_agathum, ModBlocks.potted_varloom, ModBlocks.potted_corrupted_varloom, ModBlocks.potted_missingno_plant,
            ModBlocks.potted_spotted_kersei, ModBlocks.potted_thorny_wiltha, ModBlocks.potted_roofed_agaric, ModBlocks.potted_bulbous_hobina, ModBlocks.potted_stickly_cupsir,
            ModBlocks.potted_mystical_murgni, ModBlocks.potted_corrupted_gaia_eye, ModBlocks.potted_elder_imklia, ModBlocks.potted_gold_orb_tucher, ModBlocks.potted_missingno_fungus,
            ModBlocks.potted_pink_agate_sapling, ModBlocks.potted_blue_agate_sapling, ModBlocks.potted_green_agate_sapling, ModBlocks.potted_purple_agate_sapling,
            ModBlocks.potted_fossilized_sapling, ModBlocks.potted_corrupted_sapling, ModBlocks.potted_burnt_sapling, ModBlocks.potted_burning_sapling, ModBlocks.potted_aura_sapling
    );
    private static final ImmutableList<Supplier<? extends Block>> GUARDED_BY_PIGLINS = ImmutableList.of(
            ModBlocks.crude_storage_crate, ModBlocks.mega_storage_crate
    );
    private static final ImmutableList<Supplier<? extends Block>> IMPERMEABLE = ImmutableList.of(
            ModBlocks.foggy_glass, ModBlocks.cloudy_glass, ModBlocks.frail_glitter_block
    );
    private static final ImmutableList<Supplier<? extends Block>> LEAVES = ImmutableList.of(
            ModBlocks.pink_agate_leaves, ModBlocks.blue_agate_leaves, ModBlocks.green_agate_leaves, ModBlocks.purple_agate_leaves, ModBlocks.fossilized_leaves,
            ModBlocks.corrupted_leaves, ModBlocks.burnt_leaves, ModBlocks.burning_leaves, ModBlocks.aura_leaves
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
            ModBlocks.burning_log, ModBlocks.stripped_burning_log, ModBlocks.burning_wood, ModBlocks.stripped_burning_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> AURA_LOGS = ImmutableList.of(
            ModBlocks.aura_log, ModBlocks.stripped_aura_log, ModBlocks.aura_wood, ModBlocks.stripped_aura_wood
    );
    private static final ImmutableList<Supplier<? extends Block>> TILES = ImmutableList.of(
            ModBlocks.pink_agate_planks, ModBlocks.blue_agate_planks, ModBlocks.green_agate_planks, ModBlocks.purple_agate_planks,
            ModBlocks.fossilized_planks, ModBlocks.corrupted_planks, ModBlocks.burnt_planks, ModBlocks.burning_planks, ModBlocks.aura_planks
    );
    private static final ImmutableList<Supplier<? extends Block>> SAPLINGS = ImmutableList.of(
            ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling,
            ModBlocks.fossilized_sapling, ModBlocks.corrupted_sapling, ModBlocks.burnt_sapling, ModBlocks.burning_sapling, ModBlocks.aura_sapling
    );
    private static final ImmutableList<Supplier<? extends Block>> SLABS = ImmutableList.of(
            ModBlocks.pink_agate_plank_slab, ModBlocks.blue_agate_plank_slab, ModBlocks.green_agate_plank_slab, ModBlocks.purple_agate_plank_slab, ModBlocks.fossilized_plank_slab,
            ModBlocks.corrupted_plank_slab, ModBlocks.burnt_plank_slab, ModBlocks.burning_plank_slab, ModBlocks.aura_plank_slab, ModBlocks.jade_brick_slab,
            ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_brick_slab, ModBlocks.copal_brick_slab, ModBlocks.cracked_copal_brick_slab,ModBlocks.crusted_copal_brick_slab,
            ModBlocks.jet_brick_slab, ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_brick_slab, ModBlocks.amethyst_brick_slab,  ModBlocks.cracked_amethyst_brick_slab,
            ModBlocks.crusted_amethyst_brick_slab, ModBlocks.malachite_brick_slab, ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_floor_slab
    );
    private static final ImmutableList<Supplier<? extends Block>> SMALL_FLOWERS = ImmutableList.of(
            ModBlocks.thiscus, ModBlocks.ouzium, ModBlocks.agathum, ModBlocks.varloom, ModBlocks.corrupted_varloom, ModBlocks.missingno_plant
    );
    private static final ImmutableList<Supplier<? extends Block>> STAIRS = ImmutableList.of(
            ModBlocks.pink_agate_plank_stairs, ModBlocks.blue_agate_plank_stairs, ModBlocks.green_agate_plank_stairs, ModBlocks.purple_agate_plank_stairs, ModBlocks.fossilized_plank_stairs,
            ModBlocks.corrupted_plank_stairs, ModBlocks.burnt_plank_stairs, ModBlocks.burning_plank_stairs, ModBlocks.aura_plank_stairs, ModBlocks.jade_brick_stairs,
            ModBlocks.cracked_jade_brick_stairs, ModBlocks.crusted_jade_brick_stairs, ModBlocks.copal_brick_stairs, ModBlocks.cracked_copal_brick_stairs,ModBlocks.crusted_copal_brick_stairs,
            ModBlocks.jet_brick_stairs, ModBlocks.cracked_jet_brick_stairs, ModBlocks.crusted_jet_brick_stairs, ModBlocks.amethyst_brick_stairs,  ModBlocks.cracked_amethyst_brick_stairs,
            ModBlocks.crusted_amethyst_brick_stairs, ModBlocks.malachite_brick_stairs, ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_floor_stairs,
            ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pillar_stairs
    );
    private static final ImmutableList<Supplier<? extends Block>> DIRT = ImmutableList.of(
            ModBlocks.heavy_soil, ModBlocks.glitter_grass, ModBlocks.corrupt_soil, ModBlocks.corrupt_grass,
            ModBlocks.boggy_soil, ModBlocks.murky_grass, ModBlocks.light_soil, ModBlocks.soft_grass
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

    public GaiaBlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, existingFileHelper);
    }

    @Override
    protected void addTags() {
        addTag(BlockTags.BEACON_BASE_BLOCKS, BEACON_BASES);
        addTag(BlockTags.FLOWER_POTS, FLOWER_POTS);
        addTag(BlockTags.GUARDED_BY_PIGLINS, GUARDED_BY_PIGLINS);
        addTag(BlockTags.IMPERMEABLE, IMPERMEABLE);
        addTag(BlockTags.LEAVES, LEAVES);
        tag(BlockTags.LOGS).addTags(
                GaiaTags.Blocks.PINK_AGATE_LOGS, GaiaTags.Blocks.BLUE_AGATE_LOGS, GaiaTags.Blocks.GREEN_AGATE_LOGS, GaiaTags.Blocks.PURPLE_AGATE_LOGS,
                GaiaTags.Blocks.FOSSILIZED_LOGS, GaiaTags.Blocks.CORRUPTED_LOGS, GaiaTags.Blocks.BURNT_LOGS, GaiaTags.Blocks.BURNING_LOGS, GaiaTags.Blocks.AURA_LOGS);
        tag(BlockTags.NON_FLAMMABLE_WOOD).addTags(
                GaiaTags.Blocks.PINK_AGATE_LOGS, GaiaTags.Blocks.BLUE_AGATE_LOGS, GaiaTags.Blocks.GREEN_AGATE_LOGS, GaiaTags.Blocks.PURPLE_AGATE_LOGS,
                GaiaTags.Blocks.FOSSILIZED_LOGS, GaiaTags.Blocks.CORRUPTED_LOGS, GaiaTags.Blocks.BURNT_LOGS, GaiaTags.Blocks.BURNING_LOGS, GaiaTags.Blocks.AURA_LOGS);
        tag(BlockTags.PORTALS).add(ModBlocks.gaia_portal.get());
        addTag(BlockTags.SAPLINGS, SAPLINGS);
        addTag(BlockTags.SLABS, SLABS);
        addTag(BlockTags.SMALL_FLOWERS, SMALL_FLOWERS);
        addTag(BlockTags.STAIRS, STAIRS);

        tag(GaiaTags.Blocks.ORES_SUGILITE).add(ModBlocks.sugilite_ore.get());
        tag(GaiaTags.Blocks.ORES_HEMATITE).add(ModBlocks.hematite_ore.get());
        tag(GaiaTags.Blocks.ORES_CINNABAR).add(ModBlocks.cinnabar_ore.get());
        tag(GaiaTags.Blocks.ORES_LABRADORITE).add(ModBlocks.labradorite_ore.get());
        tag(GaiaTags.Blocks.ORES_MOONSTONE).add(ModBlocks.moonstone_ore.get());
        tag(GaiaTags.Blocks.ORES_RED_OPAL).add(ModBlocks.opal_ore_red.get());
        tag(GaiaTags.Blocks.ORES_BLUE_OPAL).add(ModBlocks.opal_ore_blue.get());
        tag(GaiaTags.Blocks.ORES_GREEN_OPAL).add(ModBlocks.opal_ore_green.get());
        tag(GaiaTags.Blocks.ORES_WHITE_OPAL).add(ModBlocks.opal_ore_white.get());
        tag(GaiaTags.Blocks.ORES_PYRITE).add(ModBlocks.pyrite_ore.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE).add(ModBlocks.sugilite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE).add(ModBlocks.hematite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR).add(ModBlocks.cinnabar_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE).add(ModBlocks.labradorite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE).add(ModBlocks.moonstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL).add(ModBlocks.opal_block_red.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL).add(ModBlocks.opal_block_blue.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL).add(ModBlocks.opal_block_green.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL).add(ModBlocks.opal_block_white.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE).add(ModBlocks.pyrite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE).add(ModBlocks.tektite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE).add(ModBlocks.goldstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL).add(ModBlocks.aura_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH).add(ModBlocks.bismuth_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_IXIOLITE).add(ModBlocks.ixiolite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE).add(ModBlocks.proustite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE).add(ModBlocks.euclase_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_LEUCITE).add(ModBlocks.leucite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN).add(ModBlocks.carnelian_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE).add(ModBlocks.benitoite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE).add(ModBlocks.diopside_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CHALCEDONY).add(ModBlocks.chalcedony_block.get());
        addTag(GaiaTags.Blocks.TILES, TILES);

        addTag(Tags.Blocks.DIRT, DIRT);
        tag(Tags.Blocks.GLASS).add(ModBlocks.foggy_glass.get(), ModBlocks.cloudy_glass.get());
        tag(Tags.Blocks.ORES).addTags(
                GaiaTags.Blocks.ORES_SUGILITE, GaiaTags.Blocks.ORES_HEMATITE, GaiaTags.Blocks.ORES_CINNABAR, GaiaTags.Blocks.ORES_LABRADORITE, GaiaTags.Blocks.ORES_MOONSTONE,
                GaiaTags.Blocks.ORES_RED_OPAL, GaiaTags.Blocks.ORES_BLUE_OPAL, GaiaTags.Blocks.ORES_GREEN_OPAL, GaiaTags.Blocks.ORES_WHITE_OPAL,GaiaTags.Blocks.ORES_PYRITE);
        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE, GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE, GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR,GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE,
                GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL,GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL,
                GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE, GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE, GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE,
                GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH, GaiaTags.Blocks.STORAGE_BLOCKS_IXIOLITE,GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE,
                GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE, GaiaTags.Blocks.STORAGE_BLOCKS_LEUCITE, GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE,
                GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Blocks.STORAGE_BLOCKS_CHALCEDONY);

        addTag(GaiaTags.Blocks.PINK_AGATE_LOGS, PINK_AGATE_LOGS);
        addTag(GaiaTags.Blocks.BLUE_AGATE_LOGS, BLUE_AGATE_LOGS);
        addTag(GaiaTags.Blocks.GREEN_AGATE_LOGS, GREEN_AGATE_LOGS);
        addTag(GaiaTags.Blocks.PURPLE_AGATE_LOGS, PURPLE_AGATE_LOGS);
        addTag(GaiaTags.Blocks.FOSSILIZED_LOGS, FOSSILIZED_LOGS);
        addTag(GaiaTags.Blocks.CORRUPTED_LOGS, CORRUPTED_LOGS);
        addTag(GaiaTags.Blocks.BURNT_LOGS, BURNT_LOGS);
        addTag(GaiaTags.Blocks.BURNING_LOGS, BURNING_LOGS);
        addTag(GaiaTags.Blocks.AURA_LOGS, AURA_LOGS);
        tag(GaiaTags.Blocks.STATIC).add(ModBlocks.gaia_stone.get(), ModBlocks.wasteland_stone.get());
        tag(GaiaTags.Blocks.VOLCANIC).add(ModBlocks.gaia_stone.get(), ModBlocks.volcanic_rock.get());
        addTag(GaiaTags.Blocks.GAIA_BRICKS, GAIA_BRICKS);
        addTag(GaiaTags.Blocks.AMETHYST_BRICKS, AMETHYST_BRICKS);
        addTag(GaiaTags.Blocks.COPAL_BRICKS, COPAL_BRICKS);
        addTag(GaiaTags.Blocks.JADE_BRICKS, JADE_BRICKS);
        addTag(GaiaTags.Blocks.JET_BRICKS, JET_BRICKS);
    }
}
