package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaRecipeProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class GaiaRecipes extends GaiaRecipeProvider {

    public GaiaRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        planksRecipe(ModBlocks.pink_agate_planks, GaiaTags.PINK_AGATE_LOGS).build(consumer, locWood("pink_agate_planks"));
        planksRecipe(ModBlocks.blue_agate_planks, GaiaTags.BLUE_AGATE_LOGS).build(consumer, locWood("blue_agate_planks"));
        planksRecipe(ModBlocks.green_agate_planks, GaiaTags.GREEN_AGATE_LOGS).build(consumer, locWood("green_agate_planks"));
        planksRecipe(ModBlocks.purple_agate_planks, GaiaTags.PURPLE_AGATE_LOGS).build(consumer, locWood("purple_agate_planks"));
        planksRecipe(ModBlocks.fossilized_planks, GaiaTags.FOSSILIZED_LOGS).build(consumer, locWood("fossilized_planks"));
        planksRecipe(ModBlocks.corrupted_planks, GaiaTags.CORRUPTED_LOGS).build(consumer, locWood("corrupted_planks"));
        planksRecipe(ModBlocks.burnt_planks, GaiaTags.BURNT_LOGS).build(consumer, locWood("burnt_planks"));
        planksRecipe(ModBlocks.burning_planks, GaiaTags.BURNING_LOGS).build(consumer, locWood("burning_planks"));
        planksRecipe(ModBlocks.aura_planks, GaiaTags.AURA_LOGS).build(consumer, locWood("aura_planks"));
        slabRecipe(ModBlocks.pink_agate_plank_slab, ModBlocks.pink_agate_planks).build(consumer, locWood("pink_agate_plank_slab"));
        slabRecipe(ModBlocks.blue_agate_plank_slab, ModBlocks.blue_agate_planks).build(consumer, locWood("blue_agate_plank_slab"));
        slabRecipe(ModBlocks.green_agate_plank_slab, ModBlocks.green_agate_planks).build(consumer, locWood("green_agate_plank_slab"));
        slabRecipe(ModBlocks.purple_agate_plank_slab, ModBlocks.purple_agate_planks).build(consumer, locWood("purple_agate_plank_slab"));
        slabRecipe(ModBlocks.fossilized_plank_slab, ModBlocks.fossilized_planks).build(consumer, locWood("fossilized_plank_slab"));
        slabRecipe(ModBlocks.corrupted_plank_slab, ModBlocks.corrupted_planks).build(consumer, locWood("corrupted_plank_slab"));
        slabRecipe(ModBlocks.burnt_plank_slab, ModBlocks.burnt_planks).build(consumer, locWood("burnt_plank_slab"));
        slabRecipe(ModBlocks.burning_plank_slab, ModBlocks.burning_planks).build(consumer, locWood("burning_plank_slab"));
        slabRecipe(ModBlocks.aura_plank_slab, ModBlocks.aura_planks).build(consumer, locWood("aura_plank_slab"));
        stairsRecipe(ModBlocks.pink_agate_plank_stairs, ModBlocks.pink_agate_planks).build(consumer, locWood("pink_agate_plank_stairs"));
        stairsRecipe(ModBlocks.blue_agate_plank_stairs, ModBlocks.blue_agate_planks).build(consumer, locWood("blue_agate_plank_stairs"));
        stairsRecipe(ModBlocks.green_agate_plank_stairs, ModBlocks.green_agate_planks).build(consumer, locWood("green_agate_plank_stairs"));
        stairsRecipe(ModBlocks.purple_agate_plank_stairs, ModBlocks.purple_agate_planks).build(consumer, locWood("purple_agate_plank_stairs"));
        stairsRecipe(ModBlocks.fossilized_plank_stairs, ModBlocks.fossilized_planks).build(consumer, locWood("fossilized_plank_stairs"));
        stairsRecipe(ModBlocks.corrupted_plank_stairs, ModBlocks.corrupted_planks).build(consumer, locWood("corrupted_plank_stairs"));
        stairsRecipe(ModBlocks.burnt_plank_stairs, ModBlocks.burnt_planks).build(consumer, locWood("burnt_plank_stairs"));
        stairsRecipe(ModBlocks.burning_plank_stairs, ModBlocks.burning_planks).build(consumer, locWood("burning_plank_stairs"));
        stairsRecipe(ModBlocks.aura_plank_stairs, ModBlocks.aura_planks).build(consumer, locWood("aura_plank_stairs"));
        smallCompressRecipe(ModBlocks.pink_agate_wood, ModBlocks.pink_agate_log, 3).build(consumer, locWood("pink_agate_wood"));
        smallCompressRecipe(ModBlocks.blue_agate_wood, ModBlocks.blue_agate_log, 3).build(consumer, locWood("blue_agate_wood"));
        smallCompressRecipe(ModBlocks.green_agate_wood, ModBlocks.green_agate_log, 3).build(consumer, locWood("green_agate_wood"));
        smallCompressRecipe(ModBlocks.purple_agate_wood, ModBlocks.purple_agate_log, 3).build(consumer, locWood("purple_agate_wood"));
        smallCompressRecipe(ModBlocks.fossilized_wood, ModBlocks.fossilized_log, 3).build(consumer, locWood("fossilized_wood"));
        smallCompressRecipe(ModBlocks.corrupted_wood, ModBlocks.corrupted_log, 3).build(consumer, locWood("corrupted_wood"));
        smallCompressRecipe(ModBlocks.burnt_wood, ModBlocks.burnt_log, 3).build(consumer, locWood("burnt_wood"));
        smallCompressRecipe(ModBlocks.burning_wood, ModBlocks.burning_log, 3).build(consumer, locWood("burning_wood"));
        smallCompressRecipe(ModBlocks.aura_wood, ModBlocks.aura_log, 3).build(consumer, locWood("aura_wood"));

        largeCompressRecipe(ModBlocks.sugilite_block, ModItems.sugilite).build(consumer, locStorage("sugilite_block"));
        largeCompressRecipe(ModBlocks.hematite_block, ModItems.hematite).build(consumer, locStorage("hematite_block"));
        largeCompressRecipe(ModBlocks.cinnabar_block, ModItems.cinnabar).build(consumer, locStorage("cinnabar_block"));
        largeCompressRecipe(ModBlocks.labradorite_block, ModItems.labradorite).build(consumer, locStorage("labradorite_block"));
        largeCompressRecipe(ModBlocks.moonstone_block, ModItems.moonstone).build(consumer, locStorage("moonstone_block"));
        largeCompressRecipe(ModBlocks.opal_block_red, ModItems.red_opal).build(consumer, locStorage("opal_block_red"));
        largeCompressRecipe(ModBlocks.opal_block_blue, ModItems.blue_opal).build(consumer, locStorage("opal_block_blue"));
        largeCompressRecipe(ModBlocks.opal_block_green, ModItems.green_opal).build(consumer, locStorage("opal_block_green"));
        largeCompressRecipe(ModBlocks.opal_block_white, ModItems.white_opal).build(consumer, locStorage("opal_block_white"));
        largeCompressRecipe(ModBlocks.pyrite_block, ModItems.pyrite).build(consumer, locStorage("pyrite_block"));
        largeCompressRecipe(ModBlocks.tektite_block, ModItems.tektite).build(consumer, locStorage("tektite_block"));
        largeCompressRecipe(ModBlocks.goldstone_block, ModItems.goldstone).build(consumer, locStorage("goldstone_block"));
        largeCompressRecipe(ModBlocks.aura_block, ModItems.aura_cluster).build(consumer, locStorage("aura_block"));
        largeCompressRecipe(ModBlocks.bismuth_block, ModItems.bismuth_crystal).build(consumer, locStorage("bismuth_block"));
        largeCompressRecipe(ModBlocks.ixiolite_block, ModItems.ixiolite).build(consumer, locStorage("ixiolite_block"));
        largeCompressRecipe(ModBlocks.proustite_block, ModItems.proustite).build(consumer, locStorage("proustite_block"));
        largeCompressRecipe(ModBlocks.euclase_block, ModItems.euclase).build(consumer, locStorage("euclase_block"));
        largeCompressRecipe(ModBlocks.leucite_block, ModItems.leucite).build(consumer, locStorage("leucite_block"));
        largeCompressRecipe(ModBlocks.carnelian_block, ModItems.carnelian).build(consumer, locStorage("carnelian_block"));
        largeCompressRecipe(ModBlocks.benitoite_block, ModItems.benitoite).build(consumer, locStorage("benitoite_block"));
        largeCompressRecipe(ModBlocks.diopside_block, ModItems.diopside).build(consumer, locStorage("diopside_block"));
        largeCompressRecipe(ModBlocks.chalcedony_block, ModItems.chalcedony).build(consumer, locStorage("chalcedony_block"));

        blockToItemRecipe(ModItems.sugilite, ModBlocks.sugilite_block).build(consumer, locStorage("sugilite_block_item"));
        blockToItemRecipe(ModItems.hematite, ModBlocks.hematite_block).build(consumer, locStorage("hematite_block_item"));
        blockToItemRecipe(ModItems.cinnabar, ModBlocks.cinnabar_block).build(consumer, locStorage("cinnabar_block_item"));
        blockToItemRecipe(ModItems.labradorite, ModBlocks.labradorite_block).build(consumer, locStorage("labradorite_block_item"));
        blockToItemRecipe(ModItems.moonstone, ModBlocks.moonstone_block).build(consumer, locStorage("moonstone_block_item"));
        blockToItemRecipe(ModItems.red_opal, ModBlocks.opal_block_red).build(consumer, locStorage("red_opal_block_item"));
        blockToItemRecipe(ModItems.blue_opal, ModBlocks.opal_block_blue).build(consumer, locStorage("blue_opal_block_item"));
        blockToItemRecipe(ModItems.green_opal, ModBlocks.opal_block_green).build(consumer, locStorage("green_opal_block_item"));
        blockToItemRecipe(ModItems.white_opal, ModBlocks.opal_block_white).build(consumer, locStorage("white_opal_block_item"));
        blockToItemRecipe(ModItems.ixiolite, ModBlocks.ixiolite_block).build(consumer, locStorage("ixiolite_block_item"));
        blockToItemRecipe(ModItems.proustite, ModBlocks.proustite_block).build(consumer, locStorage("proustite_block_item"));
        blockToItemRecipe(ModItems.euclase, ModBlocks.euclase_block).build(consumer, locStorage("euclase_block_item"));
        blockToItemRecipe(ModItems.leucite, ModBlocks.leucite_block).build(consumer, locStorage("leucite_block_item"));
        blockToItemRecipe(ModItems.carnelian, ModBlocks.carnelian_block).build(consumer, locStorage("carnelian_block_item"));
        blockToItemRecipe(ModItems.benitoite, ModBlocks.benitoite_block).build(consumer, locStorage("benitoite_block_item"));
        blockToItemRecipe(ModItems.diopside, ModBlocks.diopside_block).build(consumer, locStorage("diopside_block_item"));
        blockToItemRecipe(ModItems.chalcedony, ModBlocks.chalcedony_block).build(consumer, locStorage("chalcedony_block_item"));
        blockToItemRecipe(ModItems.pyrite, ModBlocks.pyrite_block).build(consumer, locStorage("pyrite_block_item"));
        blockToItemRecipe(ModItems.tektite, ModBlocks.tektite_block).build(consumer, locStorage("tektite_block_item"));
        blockToItemRecipe(ModItems.goldstone, ModBlocks.goldstone_block).build(consumer, locStorage("goldstone_block_item"));
        blockToItemRecipe(ModItems.aura_cluster, ModBlocks.aura_block).build(consumer, locStorage("aura_cluster_block_item"));
        blockToItemRecipe(ModItems.bismuth_crystal, ModBlocks.bismuth_block).build(consumer, locStorage("bismuth_crystal_block_item"));

        helmetRecipe(ModItems.sugilite_helmet, ModItems.sugilite).build(consumer, locArmor("sugilite_helmet"));
        chestRecipe(ModItems.sugilite_chestplate, ModItems.sugilite).build(consumer, locArmor("sugilite_chestplate"));
        legsRecipe(ModItems.sugilite_legs, ModItems.sugilite).build(consumer, locArmor("sugilite_legs"));
        bootsRecipe(ModItems.sugilite_boots, ModItems.sugilite).build(consumer, locArmor("sugilite_boots"));
        helmetRecipe(ModItems.proustite_helmet, ModItems.proustite).build(consumer, locArmor("proustite_helmet"));
        chestRecipe(ModItems.proustite_chestplate, ModItems.proustite).build(consumer, locArmor("proustite_chestplate"));
        legsRecipe(ModItems.proustite_legs, ModItems.proustite).build(consumer, locArmor("proustite_legs"));
        bootsRecipe(ModItems.proustite_boots, ModItems.proustite).build(consumer, locArmor("proustite_boots"));
        helmetRecipe(ModItems.leucite_helmet, ModItems.leucite).build(consumer, locArmor("leucite_helmet"));
        chestRecipe(ModItems.leucite_chestplate, ModItems.leucite).build(consumer, locArmor("leucite_chestplate"));
        legsRecipe(ModItems.leucite_legs, ModItems.leucite).build(consumer, locArmor("leucite_legs"));
        bootsRecipe(ModItems.leucite_boots, ModItems.leucite).build(consumer, locArmor("leucite_boots"));
        helmetRecipe(ModItems.carnelian_helmet, ModItems.carnelian).build(consumer, locArmor("carnelian_helmet"));
        chestRecipe(ModItems.carnelian_chestplate, ModItems.carnelian).build(consumer, locArmor("carnelian_chestplate"));
        legsRecipe(ModItems.carnelian_legs, ModItems.carnelian).build(consumer, locArmor("carnelian_legs"));
        bootsRecipe(ModItems.carnelian_boots, ModItems.carnelian).build(consumer, locArmor("carnelian_boots"));
        helmetRecipe(ModItems.diopside_helmet, ModItems.diopside).build(consumer, locArmor("diopside_helmet"));
        chestRecipe(ModItems.diopside_chestplate, ModItems.diopside).build(consumer, locArmor("diopside_chestplate"));
        legsRecipe(ModItems.diopside_legs, ModItems.diopside).build(consumer, locArmor("diopside_legs"));
        bootsRecipe(ModItems.diopside_boots, ModItems.diopside).build(consumer, locArmor("diopside_boots"));
        helmetRecipe(ModItems.chalcedony_helmet, ModItems.chalcedony).build(consumer, locArmor("chalcedony_helmet"));
        chestRecipe(ModItems.chalcedony_chestplate, ModItems.chalcedony).build(consumer, locArmor("chalcedony_chestplate"));
        legsRecipe(ModItems.chalcedony_legs, ModItems.chalcedony).build(consumer, locArmor("chalcedony_legs"));
        bootsRecipe(ModItems.chalcedony_boots, ModItems.chalcedony).build(consumer, locArmor("chalcedony_boots"));

        swordRecipeTag(ModItems.agate_sword, GaiaTags.AGATE_PLANKS).build(consumer, locTools("agate_sword"));
        pickaxeRecipeTag(ModItems.agate_pickaxe, GaiaTags.AGATE_PLANKS).build(consumer, locTools("agate_pickaxe"));
        axeRecipeTag(ModItems.agate_axe, GaiaTags.AGATE_PLANKS).build(consumer, locTools("agate_axe"));
        shovelRecipeTag(ModItems.agate_shovel, GaiaTags.AGATE_PLANKS).build(consumer, locTools("agate_shovel"));
        swordRecipe(ModItems.sugilite_sword, ModItems.sugilite).build(consumer, locTools("sugilite_sword"));
        pickaxeRecipe(ModItems.sugilite_pickaxe, ModItems.sugilite).build(consumer, locTools("sugilite_pickaxe"));
        axeRecipe(ModItems.sugilite_axe, ModItems.sugilite).build(consumer, locTools("sugilite_axe"));
        shovelRecipe(ModItems.sugilite_shovel, ModItems.sugilite).build(consumer, locTools("sugilite_shovel"));
        swordRecipe(ModItems.ixiolite_sword, ModItems.ixiolite).build(consumer, locTools("ixiolite_sword"));
        pickaxeRecipe(ModItems.ixiolite_pickaxe, ModItems.ixiolite).build(consumer, locTools("ixiolite_pickaxe"));
        axeRecipe(ModItems.ixiolite_axe, ModItems.ixiolite).build(consumer, locTools("ixiolite_axe"));
        shovelRecipe(ModItems.ixiolite_shovel, ModItems.ixiolite).build(consumer, locTools("ixiolite_shovel"));
        swordRecipe(ModItems.euclase_sword, ModItems.euclase).build(consumer, locTools("euclase_sword"));
        pickaxeRecipe(ModItems.euclase_pickaxe, ModItems.euclase).build(consumer, locTools("euclase_pickaxe"));
        axeRecipe(ModItems.euclase_axe, ModItems.euclase).build(consumer, locTools("euclase_axe"));
        shovelRecipe(ModItems.euclase_shovel, ModItems.euclase).build(consumer, locTools("euclase_shovel"));
        swordRecipe(ModItems.carnelian_sword, ModItems.carnelian).build(consumer, locTools("carnelian_sword"));
        pickaxeRecipe(ModItems.carnelian_pickaxe, ModItems.carnelian).build(consumer, locTools("carnelian_pickaxe"));
        axeRecipe(ModItems.carnelian_axe, ModItems.carnelian).build(consumer, locTools("carnelian_axe"));
        shovelRecipe(ModItems.carnelian_shovel, ModItems.carnelian).build(consumer, locTools("carnelian_shovel"));
        swordRecipe(ModItems.benitoite_sword, ModItems.benitoite).build(consumer, locTools("benitoite_sword"));
        pickaxeRecipe(ModItems.benitoite_pickaxe, ModItems.benitoite).build(consumer, locTools("benitoite_pickaxe"));
        axeRecipe(ModItems.benitoite_axe, ModItems.benitoite).build(consumer, locTools("benitoite_axe"));
        shovelRecipe(ModItems.benitoite_shovel, ModItems.benitoite).build(consumer, locTools("benitoite_shovel"));
        swordRecipe(ModItems.chalcedony_sword, ModItems.chalcedony).build(consumer, locTools("chalcedony_sword"));
        pickaxeRecipe(ModItems.chalcedony_pickaxe, ModItems.chalcedony).build(consumer, locTools("chalcedony_pickaxe"));
        axeRecipe(ModItems.chalcedony_axe, ModItems.chalcedony).build(consumer, locTools("chalcedony_axe"));
        shovelRecipe(ModItems.chalcedony_shovel, ModItems.chalcedony).build(consumer, locTools("chalcedony_shovel"));

        largeCompressRecipe(ModItems.aura_cluster, ModItems.aura_residue).build(consumer, loc("aura_cluster"));
        largeCompressRecipe(ModItems.bismuth_crystal, ModItems.bismuth_residue).build(consumer, loc("bismuth_crystal"));
        drinkRecipe(ModItems.pink_geode_juice, ModItems.pink_geode_slice).build(consumer, loc("pink_geode_juice"));
        drinkRecipe(ModItems.blue_geode_tea, ModItems.blue_geode_slice).build(consumer, loc("blue_geode_tea"));
        drinkRecipe(ModItems.green_geode_ale, ModItems.green_geode_slice).build(consumer, loc("green_geode_ale"));
        drinkRecipe(ModItems.purple_geode_soda, ModItems.purple_geode_slice).build(consumer, loc("purple_geode_soda"));
        sliceRecipe(ModItems.pink_geode_slice, ModItems.pink_geode).build(consumer, loc("pink_geode_slice"));
        sliceRecipe(ModItems.blue_geode_slice, ModItems.blue_geode).build(consumer, loc("blue_geode_slice"));
        sliceRecipe(ModItems.green_geode_slice, ModItems.green_geode).build(consumer, loc("green_geode_slice"));
        sliceRecipe(ModItems.purple_geode_slice, ModItems.purple_geode).build(consumer, loc("purple_geode_slice"));
        largeCompressRecipe(ModBlocks.cloudy_glass, ModItems.cloudy_shard).build(consumer, loc("cloudy_glass"));
        smallCompressRecipe(ModBlocks.gaia_stone_bricks, ModBlocks.gaia_stone, 4).build(consumer, loc("gaia_stone_bricks"));
        crustBricks(ModBlocks.crusted_gaia_stone_bricks, ModBlocks.gaia_stone_bricks).build(consumer, loc("crusted_gaia_stone_bricks"));
        smallCompressRecipe(ModBlocks.jade_bricks, ModBlocks.raw_jade, 4).build(consumer, loc("jade_bricks"));
        crustBricks(ModBlocks.crusted_jade_bricks, ModBlocks.jade_bricks).build(consumer, loc("crusted_jade_bricks"));
        slabRecipe(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks).build(consumer, loc("jade_brick_slab"));
        stairsRecipe(ModBlocks.jade_brick_stairs, ModBlocks.jade_bricks).build(consumer, loc("jade_brick_stairs"));
        smallCompressRecipe(ModBlocks.copal_bricks, ModBlocks.raw_copal, 4).build(consumer, loc("copal_bricks"));
        crustBricks(ModBlocks.crusted_copal_bricks, ModBlocks.copal_bricks).build(consumer, loc("crusted_copal_bricks"));
        slabRecipe(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks).build(consumer, loc("copal_brick_slab"));
        stairsRecipe(ModBlocks.copal_brick_stairs, ModBlocks.copal_bricks).build(consumer, loc("copal_brick_stairs"));
        smallCompressRecipe(ModBlocks.jet_bricks, ModBlocks.raw_jet, 4).build(consumer, loc("jet_bricks"));
        crustBricks(ModBlocks.crusted_jet_bricks, ModBlocks.jet_bricks).build(consumer, loc("crusted_jet_bricks"));
        slabRecipe(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks).build(consumer, loc("jet_brick_slab"));
        stairsRecipe(ModBlocks.jet_brick_stairs, ModBlocks.jet_bricks).build(consumer, loc("jet_brick_stairs"));
        smallCompressRecipe(ModBlocks.amethyst_bricks, ModBlocks.raw_amethyst, 4).build(consumer, loc("amethyst_bricks"));
        crustBricks(ModBlocks.crusted_amethyst_bricks, ModBlocks.amethyst_bricks).build(consumer, loc("crusted_amethyst_bricks"));
        slabRecipe(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks).build(consumer, loc("amethyst_brick_slab"));
        stairsRecipe(ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_bricks).build(consumer, loc("amethyst_brick_stairs"));
        largeCompressRecipe(ModItems.goldstone, ModItems.goldstone_residue).build(consumer, loc("goldstone"));
        smallCompressRecipe(ModBlocks.gummy_glitter_block, ModItems.sweet_muckball).build(consumer, loc("gummy_glitter_block"));
        largeCompressRecipe(ModItems.pink_goo, ModItems.pink_essence).build(consumer, loc("pink_goo"));
        largeCompressRecipe(ModBlocks.pink_sludge_block, ModItems.pink_goo).build(consumer, loc("pink_sludge_block"));
        tiliRecipe(ModItems.tilipi, ModBlocks.spotted_kersei).build(consumer, loc("tilipi"));
        tiliRecipe(ModItems.tilibl, ModBlocks.thorny_wiltha).build(consumer, loc("tilibl"));
        tiliRecipe(ModItems.tiligr, ModBlocks.roofed_agaric).build(consumer, loc("tiligr"));
        tiliRecipe(ModItems.tilipu, ModBlocks.bulbous_hobina).build(consumer, loc("tilipu"));
        tiliRecipe(ModItems.tiliol, ModBlocks.stickly_cupsir).build(consumer, loc("tiliol"));
        tiliRecipe(ModItems.tilimy, ModBlocks.mystical_murgni).build(consumer, loc("tilimy"));
        tiliRecipe(ModItems.plagued_tiliey, ModBlocks.corrupted_varloom).build(consumer, loc("plagued_tiliey"));
        tiliRecipe(ModItems.tiliou, ModBlocks.ouzium).build(consumer, loc("tiliou"));
        smallCompressRecipe(ModBlocks.saltstone, ModBlocks.salt).build(consumer, loc("saltstone"));
        smallCompressRecipe(ModItems.sugar_cluster, ModItems.sugar_crystals).build(consumer, loc("sugar_cluster"));
        largeCompressRecipe(ModItems.tektite, ModItems.black_residue).build(consumer, loc("tektite"));
        crustBricks(ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_bricks).build(consumer, loc("malachite_crusted_bricks"));
        stairsRecipe(ModBlocks.malachite_brick_stairs, ModBlocks.malachite_bricks).build(consumer, loc("malachite_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_cracked_bricks).build(consumer, loc("malachite_cracked_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_crusted_bricks).build(consumer, loc("malachite_crusted_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_chisel_bricks).build(consumer, loc("malachite_chisel_stairs"));
        stairsRecipe(ModBlocks.malachite_floor_stairs, ModBlocks.malachite_floor_tiles).build(consumer, loc("malachite_floor_stairs"));
        stairsRecipe(ModBlocks.malachite_pillar_stairs, ModBlocks.malachite_pillar).build(consumer, loc("malachite_pillar_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_bricks).build(consumer, loc("malachite_pulsing_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_tiles).build(consumer, loc("malachite_pulsing_floor_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pulsing_chisel).build(consumer, loc("malachite_pulsing_chisel_stairs"));
        slabRecipe(ModBlocks.malachite_brick_slab, ModBlocks.malachite_bricks).build(consumer, loc("malachite_brick_slab"));
        slabRecipe(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_cracked_bricks).build(consumer, loc("malachite_cracked_brick_slab"));
        slabRecipe(ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_crusted_bricks).build(consumer, loc("malachite_crusted_brick_slab"));
        slabRecipe(ModBlocks.malachite_floor_slab, ModBlocks.malachite_floor_tiles).build(consumer, loc("malachite_floor_slab"));

        ShapedRecipeBuilder.shapedRecipe(ModItems.agate_arrow, 4)
                .patternLine("#")
                .patternLine("/")
                .patternLine("%")
                .key('#', ModItems.sturdy_pebble)
                .key('/', ModItems.agate_stick)
                .key('%', ModItems.agate_fabric)
                .addCriterion("has_pebble", hasItem(ModItems.sturdy_pebble))
                .addCriterion("has_fabric", hasItem(ModItems.agate_fabric))
                .build(consumer, loc("agate_arrow"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.agate_cup, 8)
                .patternLine("# #")
                .patternLine(" # ")
                .key('#', GaiaTags.AGATE_PLANKS)
                .addCriterion("has_planks", hasItem(GaiaTags.AGATE_PLANKS))
                .build(consumer, loc("agate_cup"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.agate_stick, 4)
                .addIngredient(GaiaTags.AGATE_PLANKS)
                .addCriterion("has_planks", hasItem(GaiaTags.AGATE_PLANKS))
                .build(consumer, loc("agate_stick"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.bolstered_bricks, 2)
                .patternLine("%#")
                .patternLine("#%")
                .key('#', ModBlocks.reinforced_bricks)
                .key('%', ModBlocks.goldstone_block)
                .addCriterion("has_brick", hasItem(ModBlocks.reinforced_bricks))
                .addCriterion("has_goldstone", hasItem(ModBlocks.goldstone_block))
                .build(consumer, loc("bolstered_bricks"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.bolstered_bricks, 2)
                .patternLine("#%")
                .patternLine("%#")
                .key('#', ModBlocks.reinforced_bricks)
                .key('%', ModBlocks.goldstone_block)
                .addCriterion("has_brick", hasItem(ModBlocks.reinforced_bricks))
                .addCriterion("has_goldstone", hasItem(ModBlocks.goldstone_block))
                .build(consumer, loc("bolstered_bricks_2"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupt_grass)
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModItems.goldstone_residue)
                .key('#', ModBlocks.glitter_grass)
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue))
                .build(consumer, loc("corrupt_grass"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupt_soil)
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModItems.goldstone_residue)
                .key('#', ModBlocks.heavy_soil)
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue))
                .build(consumer, loc("corrupt_soil"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupted_sapling)
                .patternLine(" / ")
                .patternLine("/#/")
                .patternLine(" / ")
                .key('/', ModItems.goldstone_residue)
                .key('#', Ingredient.fromItems(ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling))
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue))
                .build(consumer, loc("corrupted_sapling"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.agate_crafting_table)
                .patternLine("##")
                .patternLine("##")
                .key('#', GaiaTags.AGATE_PLANKS)
                .addCriterion("has_planks", hasItem(GaiaTags.AGATE_PLANKS))
                .build(consumer, loc("crafting_table"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.pearly_geode_elixir)
                .addIngredient(ModItems.pink_geode_slice)
                .addIngredient(ModItems.blue_geode_slice)
                .addIngredient(ModItems.green_geode_slice)
                .addIngredient(ModItems.purple_geode_slice)
                .addIngredient(ModItems.sugar_cluster)
                .addIngredient(ModItems.agate_cup)
                .addCriterion("has_cup", hasItem(ModItems.agate_cup))
                .build(consumer, loc("elixir_drink"));
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.frail_glitter_block, 4)
                .addIngredient(ModBlocks.thick_glitter_block)
                .addCriterion("has_glitter", hasItem(ModBlocks.frail_glitter_block))
                .build(consumer, loc("frail_glitter_block"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.gaia_stone_furnace)
                .patternLine("###")
                .patternLine("# #")
                .patternLine("###")
                .key('#', ModBlocks.gaia_cobblestone)
                .addCriterion("has_stone", hasItem(ModBlocks.gaia_stone))
                .build(consumer, loc("furnace"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.gemstone_pouch)
                .addIngredient(ModItems.agate_fabric)
                .addIngredient(ModItems.agate_fabric)
                .addIngredient(ModItems.agate_fabric)
                .addIngredient(ModItems.fine_thread)
                .addCriterion("has_fabric", hasItem(ModItems.agate_fabric))
                .build(consumer, loc("gemstone_pouch"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.glint_and_gold)
                .addIngredient(Items.DIAMOND)
                .addIngredient(Items.GOLD_INGOT)
                .addCriterion("has_diamond", hasItem(Items.DIAMOND))
                .build(consumer, loc("glint_and_gold"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.keystone_block)
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('*', ModItems.crystallized_lapis_lazuli)
                .key('%', ModItems.crystallized_redstone)
                .key('#', Items.GOLD_INGOT)
                .addCriterion("has_lapis", hasItem(ModItems.crystallized_lapis_lazuli))
                .addCriterion("has_redstone", hasItem(ModItems.crystallized_redstone))
                .build(consumer, loc("keystone_block"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.mega_storage_crate)
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('*', GaiaTags.AGATE_PLANKS)
                .key('%', ModBlocks.thick_glitter_block)
                .key('#', ModBlocks.crude_storage_crate)
                .addCriterion("has_crate", hasItem(ModBlocks.crude_storage_crate))
                .build(consumer, loc("large_chest"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.mega_storage_crate)
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('%', GaiaTags.AGATE_PLANKS)
                .key('*', ModBlocks.thick_glitter_block)
                .key('#', ModBlocks.crude_storage_crate)
                .addCriterion("has_crate", hasItem(ModBlocks.crude_storage_crate))
                .build(consumer, loc("large_chest_2"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.old_bow)
                .patternLine("#/ ")
                .patternLine("# /")
                .patternLine("#/ ")
                .key('#', ModItems.twined_thread)
                .key('/', ModItems.shiny_bone)
                .addCriterion("has_bone", hasItem(ModItems.shiny_bone))
                .build(consumer, locTools("old_bow"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.purifier)
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModBlocks.reinforced_bricks)
                .key('#', ModBlocks.restructurer)
                .addCriterion("has_bricks", hasItem(ModBlocks.reinforced_bricks))
                .build(consumer, loc("purifier"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.PYRITE_TORCH, 4)
                .patternLine("#")
                .patternLine("/")
                .key('#', ModItems.pyrite)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_pyrite", hasItem(ModItems.pyrite))
                .build(consumer, loc("pyrite_torch"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.reinforced_bricks, 2)
                .patternLine("%#")
                .patternLine("#%")
                .key('#', GaiaTags.GAIA_BRICKS)
                .key('%', ModBlocks.thick_glitter_block)
                .addCriterion("has_brick", hasItem(GaiaTags.GAIA_BRICKS))
                .addCriterion("has_goldstone", hasItem(ModBlocks.thick_glitter_block))
                .build(consumer, loc("reinforced_bricks"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.reinforced_bricks, 2)
                .patternLine("#%")
                .patternLine("%#")
                .key('#', GaiaTags.GAIA_BRICKS)
                .key('%', ModBlocks.thick_glitter_block)
                .addCriterion("has_brick", hasItem(GaiaTags.GAIA_BRICKS))
                .addCriterion("has_goldstone", hasItem(ModBlocks.thick_glitter_block))
                .build(consumer, loc("reinforced_bricks_2"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.restructurer)
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModBlocks.reinforced_bricks)
                .key('#', ModBlocks.gaia_stone_furnace)
                .addCriterion("has_bricks", hasItem(ModBlocks.reinforced_bricks))
                .build(consumer, loc("restructurer"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.scaynyx_bucket)
                .patternLine("# #")
                .patternLine(" # ")
                .key('#', ModItems.scaynyx_ingot)
                .addCriterion("has_ingot", hasItem(ModItems.scaynyx_ingot))
                .build(consumer, loc("scaynyx_bucket"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.crude_storage_crate)
                .patternLine("###")
                .patternLine("# #")
                .patternLine("###")
                .key('#', GaiaTags.AGATE_PLANKS)
                .addCriterion("has_planks", hasItem(GaiaTags.AGATE_PLANKS))
                .build(consumer, loc("small_chest"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.twined_thread)
                .addIngredient(ModItems.fine_thread)
                .addIngredient(ModItems.fine_thread)
                .addIngredient(ModItems.fine_thread)
                .addIngredient(ModItems.fine_thread)
                .addCriterion("has_thread", hasItem(ModItems.fine_thread))
                .build(consumer, loc("twined_thread"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.malachite_chisel_bricks)
                .patternLine("#")
                .patternLine("#")
                .key('#', ModBlocks.malachite_brick_slab)
                .addCriterion("has_malachite_slab", hasItem(ModBlocks.malachite_brick_slab))
                .build(consumer, loc("malachite_chisel_bricks"));

        smeltingRecipe(ModItems.blue_opal, ModBlocks.opal_ore_blue, 0.3F).build(consumer, locSmelt("blue_opal_smelt"));
        smeltingRecipe(ModBlocks.burning_sapling, ModBlocks.burnt_sapling, 0.1F).build(consumer, locSmelt("burning_sapling"));
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.pink_agate_sapling, ModBlocks.blue_agate_sapling, ModBlocks.green_agate_sapling, ModBlocks.purple_agate_sapling), ModBlocks.burnt_sapling, 0.1F, 200)
                .addCriterion("has_sapling", hasItem(ModBlocks.pink_agate_sapling))
                .build(consumer, locSmelt("burnt_sapling"));
        smeltingRecipe(ModItems.cinnabar, ModBlocks.cinnabar_ore, 0.3F).build(consumer, locSmelt("cinnabar_smelt"));
        smeltingRecipe(ModItems.cloudy_shard, ModItems.fine_dust, 0.1F).build(consumer, locSmelt("cloudy_shard"));
        smeltingRecipe(ModItems.cooked_luggeroth_chop, ModItems.luggeroth_chop, 0.2F).build(consumer, locSmelt("cooked_luggeroth_chop"));
        smeltingRecipe(ModItems.crystallized_lapis_lazuli, Items.LAPIS_LAZULI, 0.25F).build(consumer, locSmelt("crystal_lapis"));
        smeltingRecipe(ModItems.crystallized_redstone, Items.REDSTONE, 0.25F).build(consumer, locSmelt("crystal_redstone"));
        smeltingRecipe(ModBlocks.foggy_glass, ModBlocks.salt, 0.1F).build(consumer, locSmelt("foggy_glass"));
        smeltingRecipe(ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone, 0.1F).build(consumer, locSmelt("gaia_stone"));
        smeltingRecipe(ModItems.goldstone_residue, ModItems.goldstone_dust, 0.1F).build(consumer, locSmelt("golstone_residue"));
        smeltingRecipe(ModItems.green_opal, ModBlocks.opal_block_green, 0.3F).build(consumer, locSmelt("green_opal_smelt"));
        smeltingRecipe(ModItems.hematite, ModBlocks.hematite_ore, 0.3F).build(consumer, locSmelt("hematite_smelt"));
        smeltingRecipe(ModItems.labradorite, ModBlocks.labradorite_ore, 0.3F).build(consumer, locSmelt("labradorite_smelt"));
        smeltingRecipe(ModItems.large_calamari, ModItems.large_tentacle, 0.2F).build(consumer, locSmelt("large_calamari"));
        smeltingRecipe(ModItems.lurmorus_steak, ModItems.lurmorus_meat, 0.2F).build(consumer, locSmelt("lurmorus_steak"));
        smeltingRecipe(ModItems.moonstone, ModBlocks.moonstone_ore, 0.3F).build(consumer, locSmelt("moonstone_smelt"));
        smeltingRecipe(ModItems.pink_essence, ModBlocks.gaia_stone, 0.1F).build(consumer, locSmelt("pink_essence"));
        smeltingRecipe(ModItems.pyrite, ModBlocks.pyrite_ore, 0.3F).build(consumer, locSmelt("pyrite_smelt"));
        smeltingRecipe(ModItems.red_opal, ModBlocks.opal_block_red, 0.3F).build(consumer, locSmelt("red_opal_smelt"));
        smeltingRecipe(ModItems.scaynyx_ingot, ModBlocks.precious_rock, 0.8F, 4).build(consumer, locSmelt("scaynyx_large"));
        smeltingRecipe(ModItems.scaynyx_ingot, ModBlocks.coarse_rock, 0.4F, 2).build(consumer, locSmelt("scaynyx_medium"));
        smeltingRecipe(ModItems.scaynyx_ingot, ModBlocks.speckled_rock, 0.2F).build(consumer, locSmelt("scaynyx_small"));
        smeltingRecipe(ModItems.small_calamari, ModItems.small_tentacle, 0.2F).build(consumer, locSmelt("small_calamari"));
        smeltingRecipe(ModItems.sugilite, ModBlocks.sugilite_ore, 0.3F).build(consumer, locSmelt("sugilite_smelt"));
        smeltingRecipe(ModBlocks.thick_glitter_block, ModBlocks.gummy_glitter_block, 0.1F).build(consumer, locSmelt("thick_glitter_block"));
        smeltingRecipe(ModItems.white_opal, ModBlocks.opal_ore_white, 1.0F).build(consumer, locSmelt("white_opal_smelt"));
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
}
