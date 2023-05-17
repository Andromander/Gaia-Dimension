package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaRecipeProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class GaiaRecipes extends GaiaRecipeProvider {

    public GaiaRecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        planksRecipe(ModBlocks.pink_agate_tiles, GaiaTags.Items.PINK_AGATE_LOGS).save(consumer, locWood("pink_agate_tiles"));
        planksRecipe(ModBlocks.blue_agate_tiles, GaiaTags.Items.BLUE_AGATE_LOGS).save(consumer, locWood("blue_agate_tiles"));
        planksRecipe(ModBlocks.green_agate_tiles, GaiaTags.Items.GREEN_AGATE_LOGS).save(consumer, locWood("green_agate_tiles"));
        planksRecipe(ModBlocks.purple_agate_tiles, GaiaTags.Items.PURPLE_AGATE_LOGS).save(consumer, locWood("purple_agate_tiles"));
        planksRecipe(ModBlocks.fossilized_tiles, GaiaTags.Items.FOSSILIZED_LOGS).save(consumer, locWood("fossilized_tiles"));
        planksRecipe(ModBlocks.corrupted_tiles, GaiaTags.Items.CORRUPTED_LOGS).save(consumer, locWood("corrupted_tiles"));
        planksRecipe(ModBlocks.burnt_tiles, GaiaTags.Items.BURNT_LOGS).save(consumer, locWood("burnt_agate_tiles"));
        planksRecipe(ModBlocks.burning_tiles, GaiaTags.Items.BURNING_LOGS).save(consumer, locWood("fire_agate_tiles"));
        planksRecipe(ModBlocks.aura_tiles, GaiaTags.Items.AURA_LOGS).save(consumer, locWood("aura_tiles"));
        planksRecipe(ModBlocks.golden_tiles, GaiaTags.Items.GOLDEN_LOGS).save(consumer, locWood("golden_tiles"));
        slabRecipe(ModBlocks.pink_agate_tile_slab, ModBlocks.pink_agate_tiles).save(consumer, locWood("pink_agate_tile_slab"));
        slabRecipe(ModBlocks.blue_agate_tile_slab, ModBlocks.blue_agate_tiles).save(consumer, locWood("blue_agate_tile_slab"));
        slabRecipe(ModBlocks.green_agate_tile_slab, ModBlocks.green_agate_tiles).save(consumer, locWood("green_agate_tile_slab"));
        slabRecipe(ModBlocks.purple_agate_tile_slab, ModBlocks.purple_agate_tiles).save(consumer, locWood("purple_agate_tile_slab"));
        slabRecipe(ModBlocks.fossilized_tile_slab, ModBlocks.fossilized_tiles).save(consumer, locWood("fossilized_tile_slab"));
        slabRecipe(ModBlocks.corrupted_tile_slab, ModBlocks.corrupted_tiles).save(consumer, locWood("corrupted_tile_slab"));
        slabRecipe(ModBlocks.burnt_tile_slab, ModBlocks.burnt_tiles).save(consumer, locWood("burnt_agate_tile_slab"));
        slabRecipe(ModBlocks.burning_tile_slab, ModBlocks.burning_tiles).save(consumer, locWood("fire_agate_tile_slab"));
        slabRecipe(ModBlocks.aura_tile_slab, ModBlocks.aura_tiles).save(consumer, locWood("aura_tile_slab"));
        slabRecipe(ModBlocks.golden_tile_slab, ModBlocks.golden_tiles).save(consumer, locWood("golden_tile_slab"));
        stairsRecipe(ModBlocks.pink_agate_tile_stairs, ModBlocks.pink_agate_tiles).save(consumer, locWood("pink_agate_tile_stairs"));
        stairsRecipe(ModBlocks.blue_agate_tile_stairs, ModBlocks.blue_agate_tiles).save(consumer, locWood("blue_agate_tile_stairs"));
        stairsRecipe(ModBlocks.green_agate_tile_stairs, ModBlocks.green_agate_tiles).save(consumer, locWood("green_agate_tile_stairs"));
        stairsRecipe(ModBlocks.purple_agate_tile_stairs, ModBlocks.purple_agate_tiles).save(consumer, locWood("purple_agate_tile_stairs"));
        stairsRecipe(ModBlocks.fossilized_tile_stairs, ModBlocks.fossilized_tiles).save(consumer, locWood("fossilized_tile_stairs"));
        stairsRecipe(ModBlocks.corrupted_tile_stairs, ModBlocks.corrupted_tiles).save(consumer, locWood("corrupted_tile_stairs"));
        stairsRecipe(ModBlocks.burnt_tile_stairs, ModBlocks.burnt_tiles).save(consumer, locWood("burnt_tile_stairs"));
        stairsRecipe(ModBlocks.burning_tile_stairs, ModBlocks.burning_tiles).save(consumer, locWood("fire_agate_tile_stairs"));
        stairsRecipe(ModBlocks.aura_tile_stairs, ModBlocks.aura_tiles).save(consumer, locWood("aura_tile_stairs"));
        smallCompressRecipe(ModBlocks.pink_agate_wood.get(), ModBlocks.pink_agate_log.get(), 3).save(consumer, locWood("pink_agate_wood"));
        smallCompressRecipe(ModBlocks.blue_agate_wood.get(), ModBlocks.blue_agate_log.get(), 3).save(consumer, locWood("blue_agate_wood"));
        smallCompressRecipe(ModBlocks.green_agate_wood.get(), ModBlocks.green_agate_log.get(), 3).save(consumer, locWood("green_agate_wood"));
        smallCompressRecipe(ModBlocks.purple_agate_wood.get(), ModBlocks.purple_agate_log.get(), 3).save(consumer, locWood("purple_agate_wood"));
        smallCompressRecipe(ModBlocks.fossilized_wood.get(), ModBlocks.fossilized_log.get(), 3).save(consumer, locWood("fossilized_wood"));
        smallCompressRecipe(ModBlocks.corrupted_wood.get(), ModBlocks.corrupted_log.get(), 3).save(consumer, locWood("corrupted_wood"));
        smallCompressRecipe(ModBlocks.burnt_wood.get(), ModBlocks.burnt_log.get(), 3).save(consumer, locWood("burnt_agate_wood"));
        smallCompressRecipe(ModBlocks.fire_agate_wood.get(), ModBlocks.fire_agate_log.get(), 3).save(consumer, locWood("fire_agate_wood"));
        smallCompressRecipe(ModBlocks.aura_wood.get(), ModBlocks.aura_log.get(), 3).save(consumer, locWood("aura_wood"));
        smallCompressRecipe(ModBlocks.golden_wood.get(), ModBlocks.golden_log.get(), 3).save(consumer, locWood("golden_wood"));

        largeCompressRecipe(ModBlocks.sugilite_block.get(), ModItems.sugilite.get()).save(consumer, locStorage("sugilite_block"));
        largeCompressRecipe(ModBlocks.hematite_block.get(), ModItems.hematite.get()).save(consumer, locStorage("hematite_block"));
        largeCompressRecipe(ModBlocks.cinnabar_block.get(), ModItems.cinnabar.get()).save(consumer, locStorage("cinnabar_block"));
        largeCompressRecipe(ModBlocks.labradorite_block.get(), ModItems.labradorite.get()).save(consumer, locStorage("labradorite_block"));
        largeCompressRecipe(ModBlocks.moonstone_block.get(), ModItems.moonstone.get()).save(consumer, locStorage("moonstone_block"));
        largeCompressRecipe(ModBlocks.red_opal_block.get(), ModItems.red_opal.get()).save(consumer, locStorage("red_opal_block"));
        largeCompressRecipe(ModBlocks.blue_opal_block.get(), ModItems.blue_opal.get()).save(consumer, locStorage("blue_opal_block"));
        largeCompressRecipe(ModBlocks.green_opal_block.get(), ModItems.green_opal.get()).save(consumer, locStorage("green_opal_block"));
        largeCompressRecipe(ModBlocks.white_opal_block.get(), ModItems.white_opal.get()).save(consumer, locStorage("white_opal_block"));
        largeCompressRecipe(ModBlocks.pyrite_block.get(), ModItems.pyrite.get()).save(consumer, locStorage("pyrite_block"));
        largeCompressRecipe(ModBlocks.tektite_block.get(), ModItems.tektite.get()).save(consumer, locStorage("tektite_block"));
        largeCompressRecipe(ModBlocks.goldstone_block.get(), ModItems.goldstone.get()).save(consumer, locStorage("goldstone_block"));
        largeCompressRecipe(ModBlocks.aura_block.get(), ModItems.aura_cluster.get()).save(consumer, locStorage("aura_block"));
        largeCompressRecipe(ModBlocks.bismuth_block.get(), ModItems.bismuth_crystal.get()).save(consumer, locStorage("bismuth_block"));
        largeCompressRecipe(ModBlocks.ixiolite_block.get(), ModItems.ixiolite.get()).save(consumer, locStorage("ixiolite_block"));
        largeCompressRecipe(ModBlocks.proustite_block.get(), ModItems.proustite.get()).save(consumer, locStorage("proustite_block"));
        largeCompressRecipe(ModBlocks.euclase_block.get(), ModItems.euclase.get()).save(consumer, locStorage("euclase_block"));
        largeCompressRecipe(ModBlocks.leucite_block.get(), ModItems.leucite.get()).save(consumer, locStorage("leucite_block"));
        largeCompressRecipe(ModBlocks.carnelian_block.get(), ModItems.carnelian.get()).save(consumer, locStorage("carnelian_block"));
        largeCompressRecipe(ModBlocks.benitoite_block.get(), ModItems.benitoite.get()).save(consumer, locStorage("benitoite_block"));
        largeCompressRecipe(ModBlocks.diopside_block.get(), ModItems.diopside.get()).save(consumer, locStorage("diopside_block"));
        largeCompressRecipe(ModBlocks.chalcedony_block.get(), ModItems.chalcedony.get()).save(consumer, locStorage("chalcedony_block"));

        blockToItemRecipe(ModItems.sugilite, ModBlocks.sugilite_block).save(consumer, locStorage("sugilite_block_item"));
        blockToItemRecipe(ModItems.hematite, ModBlocks.hematite_block).save(consumer, locStorage("hematite_block_item"));
        blockToItemRecipe(ModItems.cinnabar, ModBlocks.cinnabar_block).save(consumer, locStorage("cinnabar_block_item"));
        blockToItemRecipe(ModItems.labradorite, ModBlocks.labradorite_block).save(consumer, locStorage("labradorite_block_item"));
        blockToItemRecipe(ModItems.moonstone, ModBlocks.moonstone_block).save(consumer, locStorage("moonstone_block_item"));
        blockToItemRecipe(ModItems.red_opal, ModBlocks.red_opal_block).save(consumer, locStorage("red_opal_block_item"));
        blockToItemRecipe(ModItems.blue_opal, ModBlocks.blue_opal_block).save(consumer, locStorage("blue_opal_block_item"));
        blockToItemRecipe(ModItems.green_opal, ModBlocks.green_opal_block).save(consumer, locStorage("green_opal_block_item"));
        blockToItemRecipe(ModItems.white_opal, ModBlocks.white_opal_block).save(consumer, locStorage("white_opal_block_item"));
        blockToItemRecipe(ModItems.ixiolite, ModBlocks.ixiolite_block).save(consumer, locStorage("ixiolite_block_item"));
        blockToItemRecipe(ModItems.proustite, ModBlocks.proustite_block).save(consumer, locStorage("proustite_block_item"));
        blockToItemRecipe(ModItems.euclase, ModBlocks.euclase_block).save(consumer, locStorage("euclase_block_item"));
        blockToItemRecipe(ModItems.leucite, ModBlocks.leucite_block).save(consumer, locStorage("leucite_block_item"));
        blockToItemRecipe(ModItems.carnelian, ModBlocks.carnelian_block).save(consumer, locStorage("carnelian_block_item"));
        blockToItemRecipe(ModItems.benitoite, ModBlocks.benitoite_block).save(consumer, locStorage("benitoite_block_item"));
        blockToItemRecipe(ModItems.diopside, ModBlocks.diopside_block).save(consumer, locStorage("diopside_block_item"));
        blockToItemRecipe(ModItems.chalcedony, ModBlocks.chalcedony_block).save(consumer, locStorage("chalcedony_block_item"));
        blockToItemRecipe(ModItems.pyrite, ModBlocks.pyrite_block).save(consumer, locStorage("pyrite_block_item"));
        blockToItemRecipe(ModItems.tektite, ModBlocks.tektite_block).save(consumer, locStorage("tektite_block_item"));
        blockToItemRecipe(ModItems.goldstone, ModBlocks.goldstone_block).save(consumer, locStorage("goldstone_block_item"));
        blockToItemRecipe(ModItems.aura_cluster, ModBlocks.aura_block).save(consumer, locStorage("aura_cluster_block_item"));
        blockToItemRecipe(ModItems.bismuth_crystal, ModBlocks.bismuth_block).save(consumer, locStorage("bismuth_crystal_block_item"));

        helmetRecipe(ModItems.sugilite_helmet, ModItems.sugilite).save(consumer, locArmor("sugilite_helmet"));
        chestRecipe(ModItems.sugilite_chestplate, ModItems.sugilite).save(consumer, locArmor("sugilite_chestplate"));
        legsRecipe(ModItems.sugilite_legs, ModItems.sugilite).save(consumer, locArmor("sugilite_legs"));
        bootsRecipe(ModItems.sugilite_boots, ModItems.sugilite).save(consumer, locArmor("sugilite_boots"));
        helmetRecipe(ModItems.proustite_helmet, ModItems.proustite).save(consumer, locArmor("proustite_helmet"));
        chestRecipe(ModItems.proustite_chestplate, ModItems.proustite).save(consumer, locArmor("proustite_chestplate"));
        legsRecipe(ModItems.proustite_legs, ModItems.proustite).save(consumer, locArmor("proustite_legs"));
        bootsRecipe(ModItems.proustite_boots, ModItems.proustite).save(consumer, locArmor("proustite_boots"));
        helmetRecipe(ModItems.leucite_helmet, ModItems.leucite).save(consumer, locArmor("leucite_helmet"));
        chestRecipe(ModItems.leucite_chestplate, ModItems.leucite).save(consumer, locArmor("leucite_chestplate"));
        legsRecipe(ModItems.leucite_legs, ModItems.leucite).save(consumer, locArmor("leucite_legs"));
        bootsRecipe(ModItems.leucite_boots, ModItems.leucite).save(consumer, locArmor("leucite_boots"));
        helmetRecipe(ModItems.carnelian_helmet, ModItems.carnelian).save(consumer, locArmor("carnelian_helmet"));
        chestRecipe(ModItems.carnelian_chestplate, ModItems.carnelian).save(consumer, locArmor("carnelian_chestplate"));
        legsRecipe(ModItems.carnelian_legs, ModItems.carnelian).save(consumer, locArmor("carnelian_legs"));
        bootsRecipe(ModItems.carnelian_boots, ModItems.carnelian).save(consumer, locArmor("carnelian_boots"));
        helmetRecipe(ModItems.diopside_helmet, ModItems.diopside).save(consumer, locArmor("diopside_helmet"));
        chestRecipe(ModItems.diopside_chestplate, ModItems.diopside).save(consumer, locArmor("diopside_chestplate"));
        legsRecipe(ModItems.diopside_legs, ModItems.diopside).save(consumer, locArmor("diopside_legs"));
        bootsRecipe(ModItems.diopside_boots, ModItems.diopside).save(consumer, locArmor("diopside_boots"));
        helmetRecipe(ModItems.chalcedony_helmet, ModItems.chalcedony).save(consumer, locArmor("chalcedony_helmet"));
        chestRecipe(ModItems.chalcedony_chestplate, ModItems.chalcedony).save(consumer, locArmor("chalcedony_chestplate"));
        legsRecipe(ModItems.chalcedony_legs, ModItems.chalcedony).save(consumer, locArmor("chalcedony_legs"));
        bootsRecipe(ModItems.chalcedony_boots, ModItems.chalcedony).save(consumer, locArmor("chalcedony_boots"));

        swordRecipeTag(ModItems.agate_sword, GaiaTags.Items.TILES).save(consumer, locTools("agate_sword"));
        pickaxeRecipeTag(ModItems.agate_pickaxe, GaiaTags.Items.TILES).save(consumer, locTools("agate_pickaxe"));
        axeRecipeTag(ModItems.agate_axe, GaiaTags.Items.TILES).save(consumer, locTools("agate_axe"));
        shovelRecipeTag(ModItems.agate_shovel, GaiaTags.Items.TILES).save(consumer, locTools("agate_shovel"));
        swordRecipe(ModItems.sugilite_sword, ModItems.sugilite).save(consumer, locTools("sugilite_sword"));
        pickaxeRecipe(ModItems.sugilite_pickaxe, ModItems.sugilite).save(consumer, locTools("sugilite_pickaxe"));
        axeRecipe(ModItems.sugilite_axe, ModItems.sugilite).save(consumer, locTools("sugilite_axe"));
        shovelRecipe(ModItems.sugilite_shovel, ModItems.sugilite).save(consumer, locTools("sugilite_shovel"));
        swordRecipe(ModItems.ixiolite_sword, ModItems.ixiolite).save(consumer, locTools("ixiolite_sword"));
        pickaxeRecipe(ModItems.ixiolite_pickaxe, ModItems.ixiolite).save(consumer, locTools("ixiolite_pickaxe"));
        axeRecipe(ModItems.ixiolite_axe, ModItems.ixiolite).save(consumer, locTools("ixiolite_axe"));
        shovelRecipe(ModItems.ixiolite_shovel, ModItems.ixiolite).save(consumer, locTools("ixiolite_shovel"));
        swordRecipe(ModItems.euclase_sword, ModItems.euclase).save(consumer, locTools("euclase_sword"));
        pickaxeRecipe(ModItems.euclase_pickaxe, ModItems.euclase).save(consumer, locTools("euclase_pickaxe"));
        axeRecipe(ModItems.euclase_axe, ModItems.euclase).save(consumer, locTools("euclase_axe"));
        shovelRecipe(ModItems.euclase_shovel, ModItems.euclase).save(consumer, locTools("euclase_shovel"));
        swordRecipe(ModItems.carnelian_sword, ModItems.carnelian).save(consumer, locTools("carnelian_sword"));
        pickaxeRecipe(ModItems.carnelian_pickaxe, ModItems.carnelian).save(consumer, locTools("carnelian_pickaxe"));
        axeRecipe(ModItems.carnelian_axe, ModItems.carnelian).save(consumer, locTools("carnelian_axe"));
        shovelRecipe(ModItems.carnelian_shovel, ModItems.carnelian).save(consumer, locTools("carnelian_shovel"));
        swordRecipe(ModItems.benitoite_sword, ModItems.benitoite).save(consumer, locTools("benitoite_sword"));
        pickaxeRecipe(ModItems.benitoite_pickaxe, ModItems.benitoite).save(consumer, locTools("benitoite_pickaxe"));
        axeRecipe(ModItems.benitoite_axe, ModItems.benitoite).save(consumer, locTools("benitoite_axe"));
        shovelRecipe(ModItems.benitoite_shovel, ModItems.benitoite).save(consumer, locTools("benitoite_shovel"));
        swordRecipe(ModItems.chalcedony_sword, ModItems.chalcedony).save(consumer, locTools("chalcedony_sword"));
        pickaxeRecipe(ModItems.chalcedony_pickaxe, ModItems.chalcedony).save(consumer, locTools("chalcedony_pickaxe"));
        axeRecipe(ModItems.chalcedony_axe, ModItems.chalcedony).save(consumer, locTools("chalcedony_axe"));
        shovelRecipe(ModItems.chalcedony_shovel, ModItems.chalcedony).save(consumer, locTools("chalcedony_shovel"));

        largeCompressRecipe(ModItems.aura_cluster.get(), ModItems.aura_residue.get()).save(consumer, loc("aura_cluster"));
        largeCompressRecipe(ModItems.bismuth_crystal.get(), ModItems.bismuth_residue.get()).save(consumer, loc("bismuth_crystal"));
        drinkRecipe(ModItems.pink_geode_juice, ModItems.pink_geode_slice).save(consumer, loc("pink_geode_juice"));
        drinkRecipe(ModItems.blue_geode_tea, ModItems.blue_geode_slice).save(consumer, loc("blue_geode_tea"));
        drinkRecipe(ModItems.green_geode_ale, ModItems.green_geode_slice).save(consumer, loc("green_geode_ale"));
        drinkRecipe(ModItems.purple_geode_soda, ModItems.purple_geode_slice).save(consumer, loc("purple_geode_soda"));
        sliceRecipe(ModItems.pink_geode_slice, ModItems.pink_geode).save(consumer, loc("pink_geode_slice"));
        sliceRecipe(ModItems.blue_geode_slice, ModItems.blue_geode).save(consumer, loc("blue_geode_slice"));
        sliceRecipe(ModItems.green_geode_slice, ModItems.green_geode).save(consumer, loc("green_geode_slice"));
        sliceRecipe(ModItems.purple_geode_slice, ModItems.purple_geode).save(consumer, loc("purple_geode_slice"));
        largeCompressRecipe(ModBlocks.cloudy_glass.get(), ModItems.cloudy_shard.get()).save(consumer, loc("cloudy_glass"));
        smallCompressRecipe(ModBlocks.gaia_stone_bricks.get(), ModBlocks.gaia_stone.get(), 4).save(consumer, loc("gaia_stone_bricks"));
        crustBricks(ModBlocks.crusted_gaia_stone_bricks, ModBlocks.gaia_stone_bricks).save(consumer, loc("crusted_gaia_stone_bricks"));
        smallCompressRecipe(ModBlocks.jade_bricks.get(), ModBlocks.raw_jade.get(), 4).save(consumer, loc("jade_bricks"));
        crustBricks(ModBlocks.crusted_jade_bricks, ModBlocks.jade_bricks).save(consumer, loc("crusted_jade_bricks"));
        slabRecipe(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks).save(consumer, loc("jade_brick_slab"));
        stairsRecipe(ModBlocks.jade_brick_stairs, ModBlocks.jade_bricks).save(consumer, loc("jade_brick_stairs"));
        smallCompressRecipe(ModBlocks.copal_bricks.get(), ModBlocks.raw_copal.get(), 4).save(consumer, loc("copal_bricks"));
        crustBricks(ModBlocks.crusted_copal_bricks, ModBlocks.copal_bricks).save(consumer, loc("crusted_copal_bricks"));
        slabRecipe(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks).save(consumer, loc("copal_brick_slab"));
        stairsRecipe(ModBlocks.copal_brick_stairs, ModBlocks.copal_bricks).save(consumer, loc("copal_brick_stairs"));
        smallCompressRecipe(ModBlocks.jet_bricks.get(), ModBlocks.raw_jet.get(), 4).save(consumer, loc("jet_bricks"));
        crustBricks(ModBlocks.crusted_jet_bricks, ModBlocks.jet_bricks).save(consumer, loc("crusted_jet_bricks"));
        slabRecipe(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks).save(consumer, loc("jet_brick_slab"));
        stairsRecipe(ModBlocks.jet_brick_stairs, ModBlocks.jet_bricks).save(consumer, loc("jet_brick_stairs"));
        smallCompressRecipe(ModBlocks.amethyst_bricks.get(), ModBlocks.raw_amethyst.get(), 4).save(consumer, loc("amethyst_bricks"));
        crustBricks(ModBlocks.crusted_amethyst_bricks, ModBlocks.amethyst_bricks).save(consumer, loc("crusted_amethyst_bricks"));
        slabRecipe(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks).save(consumer, loc("amethyst_brick_slab"));
        stairsRecipe(ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_bricks).save(consumer, loc("amethyst_brick_stairs"));
        largeCompressRecipe(ModItems.goldstone.get(), ModItems.goldstone_residue.get()).save(consumer, loc("goldstone"));
        smallCompressRecipe(ModBlocks.gummy_glitter_block.get(), ModItems.sweet_muckball.get()).save(consumer, loc("gummy_glitter_block"));
        largeCompressRecipe(ModItems.pink_goo.get(), ModItems.pink_essence.get()).save(consumer, loc("pink_goo"));
        largeCompressRecipe(ModBlocks.pink_sludge_block.get(), ModItems.pink_goo.get()).save(consumer, loc("pink_sludge_block"));
        tiliRecipe(ModItems.tilipi, ModBlocks.spotted_kersei).save(consumer, loc("tilipi"));
        tiliRecipe(ModItems.tilibl, ModBlocks.thorny_wiltha).save(consumer, loc("tilibl"));
        tiliRecipe(ModItems.tiligr, ModBlocks.roofed_agaric).save(consumer, loc("tiligr"));
        tiliRecipe(ModItems.tilipu, ModBlocks.bulbous_hobina).save(consumer, loc("tilipu"));
        tiliRecipe(ModItems.tiliol, ModBlocks.stickly_cupsir).save(consumer, loc("tiliol"));
        tiliRecipe(ModItems.tilimy, ModBlocks.mystical_murgni).save(consumer, loc("tilimy"));
        tiliRecipe(ModItems.plagued_tiliey, ModBlocks.corrupted_varloom).save(consumer, loc("plagued_tiliey"));
        tiliRecipe(ModItems.tiliou, ModBlocks.ouzium).save(consumer, loc("tiliou"));
        smallCompressRecipe(ModBlocks.saltstone.get(), ModBlocks.salt.get()).save(consumer, loc("saltstone"));
        smallCompressRecipe(ModItems.sugar_cluster.get(), ModItems.sugar_crystals.get()).save(consumer, loc("sugar_cluster"));
        largeCompressRecipe(ModItems.tektite.get(), ModItems.black_residue.get()).save(consumer, loc("tektite"));
        crustBricks(ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_bricks).save(consumer, loc("malachite_crusted_bricks"));
        stairsRecipe(ModBlocks.malachite_brick_stairs, ModBlocks.malachite_bricks).save(consumer, loc("malachite_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_cracked_bricks).save(consumer, loc("malachite_cracked_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_crusted_bricks).save(consumer, loc("malachite_crusted_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_chisel_bricks).save(consumer, loc("malachite_chisel_stairs"));
        stairsRecipe(ModBlocks.malachite_tile_stairs, ModBlocks.malachite_tiles).save(consumer, loc("malachite_tile_stairs"));
        stairsRecipe(ModBlocks.malachite_pillar_stairs, ModBlocks.malachite_pillar).save(consumer, loc("malachite_pillar_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_bricks).save(consumer, loc("malachite_pulsing_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_tiles).save(consumer, loc("malachite_pulsing_tile_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pulsing_chisel).save(consumer, loc("malachite_pulsing_chisel_stairs"));
        slabRecipe(ModBlocks.malachite_brick_slab, ModBlocks.malachite_bricks).save(consumer, loc("malachite_brick_slab"));
        slabRecipe(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_cracked_bricks).save(consumer, loc("malachite_cracked_brick_slab"));
        slabRecipe(ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_crusted_bricks).save(consumer, loc("malachite_crusted_brick_slab"));
        slabRecipe(ModBlocks.malachite_tile_slab, ModBlocks.malachite_tiles).save(consumer, loc("malachite_tile_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.agate_arrow.get(), 4)
                .pattern("#")
                .pattern("/")
                .pattern("%")
                .define('#', ModItems.sturdy_pebble.get())
                .define('/', ModItems.agate_stick.get())
                .define('%', ModItems.agate_fabric.get())
                .unlockedBy("has_pebble", has(ModItems.sturdy_pebble.get()))
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(consumer, loc("agate_arrow"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.agate_cup.get(), 8)
                .pattern("# #")
                .pattern(" # ")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(consumer, loc("agate_cup"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.agate_stick.get(), 4)
                .requires(GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(consumer, loc("agate_stick"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.bolstered_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', ModBlocks.reinforced_bricks.get())
                .define('%', ModBlocks.goldstone_block.get())
                .unlockedBy("has_brick", has(ModBlocks.reinforced_bricks.get()))
                .unlockedBy("has_goldstone", has(ModBlocks.goldstone_block.get()))
                .save(consumer, loc("bolstered_bricks"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.bolstered_bricks.get(), 2)
                .pattern("#%")
                .pattern("%#")
                .define('#', ModBlocks.reinforced_bricks.get())
                .define('%', ModBlocks.goldstone_block.get())
                .unlockedBy("has_brick", has(ModBlocks.reinforced_bricks.get()))
                .unlockedBy("has_goldstone", has(ModBlocks.goldstone_block.get()))
                .save(consumer, loc("bolstered_bricks_2"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.corrupted_grass.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', ModBlocks.glitter_grass.get())
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(consumer, loc("corrupted_grass"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.corrupted_soil.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', ModBlocks.heavy_soil.get())
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(consumer, loc("corrupted_soil"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.corrupted_sapling.get())
                .pattern(" / ")
                .pattern("/#/")
                .pattern(" / ")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()))
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(consumer, loc("corrupted_sapling"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.agate_crafting_table.get())
                .pattern("##")
                .pattern("##")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(consumer, loc("crafting_table"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.pearly_geode_elixir.get())
                .requires(ModItems.pink_geode_slice.get())
                .requires(ModItems.blue_geode_slice.get())
                .requires(ModItems.green_geode_slice.get())
                .requires(ModItems.purple_geode_slice.get())
                .requires(ModItems.sugar_cluster.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()))
                .save(consumer, loc("elixir_drink"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.frail_glitter_block.get(), 4)
                .requires(ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_glitter", has(ModBlocks.frail_glitter_block.get()))
                .save(consumer, loc("frail_glitter_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.gaia_stone_furnace.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', ModBlocks.gaia_cobblestone.get())
                .unlockedBy("has_stone", has(ModBlocks.gaia_stone.get()))
                .save(consumer, loc("furnace"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.gemstone_pouch.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(consumer, loc("gemstone_pouch"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItems.glint_and_gold.get())
                .requires(Items.DIAMOND)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(consumer, loc("glint_and_gold"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.keystone_block.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', ModItems.crystallized_lapis_lazuli.get())
                .define('%', ModItems.crystallized_redstone.get())
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_lapis", has(ModItems.crystallized_lapis_lazuli.get()))
                .unlockedBy("has_redstone", has(ModItems.crystallized_redstone.get()))
                .save(consumer, loc("keystone_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.mega_storage_crate.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', GaiaTags.Items.TILES)
                .define('%', ModBlocks.thick_glitter_block.get())
                .define('#', ModBlocks.crude_storage_crate.get())
                .unlockedBy("has_crate", has(ModBlocks.crude_storage_crate.get()))
                .save(consumer, loc("large_chest"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.mega_storage_crate.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('%', GaiaTags.Items.TILES)
                .define('*', ModBlocks.thick_glitter_block.get())
                .define('#', ModBlocks.crude_storage_crate.get())
                .unlockedBy("has_crate", has(ModBlocks.crude_storage_crate.get()))
                .save(consumer, loc("large_chest_2"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.old_bow.get())
                .pattern("#/ ")
                .pattern("# /")
                .pattern("#/ ")
                .define('#', ModItems.twined_thread.get())
                .define('/', ModItems.shiny_bone.get())
                .unlockedBy("has_bone", has(ModItems.shiny_bone.get()))
                .save(consumer, locTools("old_bow"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.purifier.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModBlocks.reinforced_bricks.get())
                .define('#', ModBlocks.restructurer.get())
                .unlockedBy("has_bricks", has(ModBlocks.reinforced_bricks.get()))
                .save(consumer, loc("purifier"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.PYRITE_TORCH.get(), 4)
                .pattern("#")
                .pattern("/")
                .define('#', ModItems.pyrite.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_pyrite", has(ModItems.pyrite.get()))
                .save(consumer, loc("pyrite_torch"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.reinforced_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', GaiaTags.Items.GAIA_BRICKS)
                .define('%', ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_brick", has(GaiaTags.Items.GAIA_BRICKS))
                .unlockedBy("has_goldstone", has(ModBlocks.thick_glitter_block.get()))
                .save(consumer, loc("reinforced_bricks"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.reinforced_bricks.get(), 2)
                .pattern("#%")
                .pattern("%#")
                .define('#', GaiaTags.Items.GAIA_BRICKS)
                .define('%', ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_brick", has(GaiaTags.Items.GAIA_BRICKS))
                .unlockedBy("has_goldstone", has(ModBlocks.thick_glitter_block.get()))
                .save(consumer, loc("reinforced_bricks_2"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.restructurer.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModBlocks.reinforced_bricks.get())
                .define('#', ModBlocks.gaia_stone_furnace.get())
                .unlockedBy("has_bricks", has(ModBlocks.reinforced_bricks.get()))
                .save(consumer, loc("restructurer"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.scaynyx_bucket.get())
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.scaynyx_ingot.get())
                .unlockedBy("has_ingot", has(ModItems.scaynyx_ingot.get()))
                .save(consumer, loc("scaynyx_bucket"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.crude_storage_crate.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(consumer, loc("small_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.twined_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_thread", has(ModItems.fine_thread.get()))
                .save(consumer, loc("twined_thread"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.malachite_chisel_bricks.get())
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.malachite_brick_slab.get())
                .unlockedBy("has_malachite_slab", has(ModBlocks.malachite_brick_slab.get()))
                .save(consumer, loc("malachite_chisel_bricks"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 3)
                .requires(ModItems.shiny_bone.get())
                .group("bonemeal")
                .unlockedBy("has_bone", has(ModItems.shiny_bone.get()))
                .save(consumer, loc("bone_meal"));


        smeltingRecipe(ModItems.blue_opal.get(), ModBlocks.blue_opal_ore.get(), 0.3F).save(consumer, locSmelt("blue_opal_smelt"));
        smeltingRecipe(ModBlocks.fire_agate_sapling.get(), ModBlocks.burnt_sapling.get(), 0.1F).save(consumer, locSmelt("fire_agate_sapling"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()), RecipeCategory.DECORATIONS, ModBlocks.burnt_sapling.get(), 0.1F, 200)
                .unlockedBy("has_sapling", has(ModBlocks.pink_agate_sapling.get()))
                .save(consumer, locSmelt("burnt_agate_sapling"));
        smeltingRecipe(ModItems.cinnabar.get(), ModBlocks.cinnabar_ore.get(), 0.3F).save(consumer, locSmelt("cinnabar_smelt"));
        smeltingRecipe(ModItems.cloudy_shard.get(), ModItems.fine_dust.get(), 0.1F).save(consumer, locSmelt("cloudy_shard"));
        smeltingRecipe(ModItems.cooked_luggeroth_chop.get(), ModItems.luggeroth_chop.get(), 0.2F).save(consumer, locSmelt("cooked_luggeroth_chop"));
        smeltingRecipe(ModItems.crystallized_lapis_lazuli.get(), Items.LAPIS_LAZULI, 0.25F).save(consumer, locSmelt("crystal_lapis"));
        smeltingRecipe(ModItems.crystallized_redstone.get(), Items.REDSTONE, 0.25F).save(consumer, locSmelt("crystal_redstone"));
        smeltingRecipe(ModBlocks.foggy_glass.get(), ModBlocks.salt.get(), 0.1F).save(consumer, locSmelt("foggy_glass"));
        smeltingRecipe(ModBlocks.gaia_stone.get(), ModBlocks.gaia_cobblestone.get(), 0.1F).save(consumer, locSmelt("gaia_stone"));
        smeltingRecipe(ModItems.goldstone_residue.get(), ModItems.goldstone_dust.get(), 0.1F).save(consumer, locSmelt("golstone_residue"));
        smeltingRecipe(ModItems.green_opal.get(), ModBlocks.green_opal_ore.get(), 0.3F).save(consumer, locSmelt("green_opal_smelt"));
        smeltingRecipe(ModItems.hematite.get(), ModBlocks.hematite_ore.get(), 0.3F).save(consumer, locSmelt("hematite_smelt"));
        smeltingRecipe(ModItems.labradorite.get(), ModBlocks.labradorite_ore.get(), 0.3F).save(consumer, locSmelt("labradorite_smelt"));
        smeltingRecipe(ModItems.large_calamari.get(), ModItems.large_tentacle.get(), 0.2F).save(consumer, locSmelt("large_calamari"));
        smeltingRecipe(ModItems.lurmorus_steak.get(), ModItems.lurmorus_meat.get(), 0.2F).save(consumer, locSmelt("lurmorus_steak"));
        smeltingRecipe(ModItems.moonstone.get(), ModBlocks.moonstone_ore.get(), 0.3F).save(consumer, locSmelt("moonstone_smelt"));
        smeltingRecipe(ModItems.pink_essence.get(), ModBlocks.gaia_stone.get(), 0.1F).save(consumer, locSmelt("pink_essence"));
        smeltingRecipe(ModItems.pyrite.get(), ModBlocks.pyrite_ore.get(), 0.3F).save(consumer, locSmelt("pyrite_smelt"));
        smeltingRecipe(ModItems.red_opal.get(), ModBlocks.red_opal_ore.get(), 0.3F).save(consumer, locSmelt("red_opal_smelt"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.precious_rock.get(), 0.8F, 4).save(consumer, locSmelt("scaynyx_large"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.coarse_rock.get(), 0.4F, 2).save(consumer, locSmelt("scaynyx_medium"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.speckled_rock.get(), 0.2F).save(consumer, locSmelt("scaynyx_small"));
        smeltingRecipe(ModItems.small_calamari.get(), ModItems.small_tentacle.get(), 0.2F).save(consumer, locSmelt("small_calamari"));
        smeltingRecipe(ModItems.sugilite.get(), ModBlocks.sugilite_ore.get(), 0.3F).save(consumer, locSmelt("sugilite_smelt"));
        smeltingRecipe(ModBlocks.thick_glitter_block.get(), ModBlocks.gummy_glitter_block.get(), 0.1F).save(consumer, locSmelt("thick_glitter_block"));
        smeltingRecipe(ModItems.white_opal.get(), ModBlocks.white_opal_ore.get(), 1.0F).save(consumer, locSmelt("white_opal_smelt"));

        restructureBlackResidue(ModItems.benitoite, ModItems.blue_opal, 0.3F, 1).save(consumer, locRestructure("benitoite"));
        restructuringTektite(ModBlocks.benitoite_block, ModBlocks.blue_opal_block, 2.7F, 1).save(consumer, locRestructure("benitoite_block"));
        restructureBlackResidue(ModItems.carnelian, ModItems.red_opal, 0.3F, 1).save(consumer, locRestructure("carnelian"));
        restructuringTektite(ModBlocks.carnelian_block, ModBlocks.red_opal_block, 2.7F, 1).save(consumer, locRestructure("carnelian_block"));
        restructureBlackResidue(ModItems.chalcedony, ModItems.white_opal, 0.3F, 1).save(consumer, locRestructure("chalcedony"));
        restructuringTektite(ModBlocks.chalcedony_block, ModBlocks.white_opal_block, 2.7F, 1).save(consumer, locRestructure("chalcedony_block"));
        restructureBlackResidue(ModItems.diopside, ModItems.green_opal, 0.3F, 1).save(consumer, locRestructure("diopside"));
        restructuringTektite(ModBlocks.diopside_block, ModBlocks.green_opal_block, 2.7F, 1).save(consumer, locRestructure("diopside_block"));
        restructureBlackResidue(ModItems.euclase, ModItems.labradorite, 0.3F, 1).save(consumer, locRestructure("euclase"));
        restructuringTektite(ModBlocks.euclase_block, ModBlocks.labradorite_block, 2.7F, 1).save(consumer, locRestructure("euclase_block"));
        restructureBlackResidue(ModItems.ixiolite, ModItems.hematite, 0.3F, 1).save(consumer, locRestructure("ixiolite"));
        restructuringTektite(ModBlocks.ixiolite_block, ModBlocks.hematite_block, 2.7F, 1).save(consumer, locRestructure("ixiolite_block"));
        restructureBlackResidue(ModItems.leucite, ModItems.moonstone, 0.3F, 1).save(consumer, locRestructure("leucite"));
        restructuringTektite(ModBlocks.leucite_block, ModBlocks.moonstone_block, 2.7F, 1).save(consumer, locRestructure("leucite_block"));
        restructureBlackResidue(ModItems.proustite, ModItems.cinnabar, 0.3F, 1).save(consumer, locRestructure("proustite"));
        restructuringTektite(ModBlocks.proustite_block, ModBlocks.cinnabar_block, 2.7F, 1).save(consumer, locRestructure("proustite_block"));
        restructuringItems(ModItems.aura_cluster.get(), ModItems.bismuth_crystal.get(), ModBlocks.pyrite_block.get(), 2.7F, 1).save(consumer, locRestructure("aura_and_bismuth"));
        restructuringItems(ModItems.crystallized_lapis_lazuli.get(), ModItems.pink_essence.get(), ModItems.benitoite.get(), 0.1F, 1).save(consumer, locRestructure("crystal_lapis"));
        restructuringItems(ModItems.crystallized_redstone.get(), ModItems.pink_essence.get(), ModItems.carnelian.get(), 0.1F, 1).save(consumer, locRestructure("crystal_redstone"));
        restructuringItems(Items.DIAMOND, ModItems.pink_essence.get(), ModItems.bismuth_crystal.get(), 0.1F, 1).save(consumer, locRestructure("diamond"));
        restructuringItems(Items.GOLD_INGOT, ModItems.pink_essence.get(), ModItems.scaynyx_ingot.get(), 0.1F, 1).save(consumer, locRestructure("gold_ingot"));
        restructuringItems(ModItems.aura_residue.get(), ModItems.bismuth_residue.get(), ModItems.pyrite.get(), 0.2F, 1).save(consumer, locRestructure("residues"));
        purifyingItems(ModBlocks.glitter_grass.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_grass.get(), 0.3F, 1, 1).save(consumer, locPurify("glitter_grass"));
        purifyingItems(ModBlocks.heavy_soil.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_soil.get(), 0.3F, 1, 1).save(consumer, locPurify("heavy_soil"));
        purifyingItems(ModBlocks.pink_agate_leaves.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_leaves.get(), 0.3F, 1, 1).save(consumer, locPurify("pink_agate_leaves"));
        purifyingItems(ModBlocks.pink_agate_log.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_log.get(), 0.3F, 1, 2).save(consumer, locPurify("pink_agate_log"));
        purifyingItems(ModBlocks.stripped_pink_agate_log.get(), ModItems.goldstone_residue.get(), ModBlocks.stripped_corrupted_log.get(), 0.3F, 1, 2).save(consumer, locPurify("stripped_pink_agate_log"));
        purifyingItems(ModBlocks.pink_agate_wood.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_wood.get(), 0.3F, 1, 2).save(consumer, locPurify("pink_agate_wood"));
        purifyingItems(ModBlocks.stripped_pink_agate_wood.get(), ModItems.goldstone_residue.get(), ModBlocks.stripped_corrupted_wood.get(), 0.3F, 1, 2).save(consumer, locPurify("stripped_pink_agate_wood"));
        purifyingItems(ModBlocks.pink_agate_tiles.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tiles.get(), 0.3F, 1, 1).save(consumer, locPurify("pink_agate_tiles"));
        purifyingItems(ModBlocks.pink_agate_tile_stairs.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tile_stairs.get(), 0.3F, 1, 1).save(consumer, locPurify("pink_agate_tile_stairs"));
        purifyingItems(ModBlocks.pink_agate_tile_slab.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tile_slab.get(), 0.3F, 1, 1).save(consumer, locPurify("pink_agate_tile_slab"));
        purifyingItems(ModBlocks.pink_agate_sapling.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_sapling.get(), 0.3F, 1, 1).save(consumer, locPurify("pink_agate_sapling"));
        purifyingItems(ModBlocks.varloom.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_varloom.get(), 0.3F, 1, 1).save(consumer, "varloom");
    }

    private ResourceLocation loc(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, name);
    }

    private ResourceLocation locArmor(String name) {
        return loc("armor/" + name);
    }

    private ResourceLocation locStorage(String name) {
        return loc("storage_blocks/" + name);
    }

    private ResourceLocation locTools(String name) {
        return loc("tools/" + name);
    }

    private ResourceLocation locWood(String name) {
        return loc("wood/" + name);
    }

    private ResourceLocation locSmelt(String name) {
        return loc("smelting/" + name);
    }

    private ResourceLocation locRestructure(String name) {
        return loc("restructuring/" + name);
    }

    private ResourceLocation locPurify(String name) {
        return loc("purifying/" + name);
    }
}
