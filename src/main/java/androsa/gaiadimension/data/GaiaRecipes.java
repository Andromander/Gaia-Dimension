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
        planksRecipe(ModBlocks.pink_agate_planks, GaiaTags.Items.PINK_AGATE_LOGS).build(consumer, locWood("pink_agate_planks"));
        planksRecipe(ModBlocks.blue_agate_planks, GaiaTags.Items.BLUE_AGATE_LOGS).build(consumer, locWood("blue_agate_planks"));
        planksRecipe(ModBlocks.green_agate_planks, GaiaTags.Items.GREEN_AGATE_LOGS).build(consumer, locWood("green_agate_planks"));
        planksRecipe(ModBlocks.purple_agate_planks, GaiaTags.Items.PURPLE_AGATE_LOGS).build(consumer, locWood("purple_agate_planks"));
        planksRecipe(ModBlocks.fossilized_planks, GaiaTags.Items.FOSSILIZED_LOGS).build(consumer, locWood("fossilized_planks"));
        planksRecipe(ModBlocks.corrupted_planks, GaiaTags.Items.CORRUPTED_LOGS).build(consumer, locWood("corrupted_planks"));
        planksRecipe(ModBlocks.burnt_planks, GaiaTags.Items.BURNT_LOGS).build(consumer, locWood("burnt_planks"));
        planksRecipe(ModBlocks.burning_planks, GaiaTags.Items.BURNING_LOGS).build(consumer, locWood("burning_planks"));
        planksRecipe(ModBlocks.aura_planks, GaiaTags.Items.AURA_LOGS).build(consumer, locWood("aura_planks"));
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
        smallCompressRecipe(ModBlocks.pink_agate_wood.get(), ModBlocks.pink_agate_log.get(), 3).build(consumer, locWood("pink_agate_wood"));
        smallCompressRecipe(ModBlocks.blue_agate_wood.get(), ModBlocks.blue_agate_log.get(), 3).build(consumer, locWood("blue_agate_wood"));
        smallCompressRecipe(ModBlocks.green_agate_wood.get(), ModBlocks.green_agate_log.get(), 3).build(consumer, locWood("green_agate_wood"));
        smallCompressRecipe(ModBlocks.purple_agate_wood.get(), ModBlocks.purple_agate_log.get(), 3).build(consumer, locWood("purple_agate_wood"));
        smallCompressRecipe(ModBlocks.fossilized_wood.get(), ModBlocks.fossilized_log.get(), 3).build(consumer, locWood("fossilized_wood"));
        smallCompressRecipe(ModBlocks.corrupted_wood.get(), ModBlocks.corrupted_log.get(), 3).build(consumer, locWood("corrupted_wood"));
        smallCompressRecipe(ModBlocks.burnt_wood.get(), ModBlocks.burnt_log.get(), 3).build(consumer, locWood("burnt_wood"));
        smallCompressRecipe(ModBlocks.burning_wood.get(), ModBlocks.burning_log.get(), 3).build(consumer, locWood("burning_wood"));
        smallCompressRecipe(ModBlocks.aura_wood.get(), ModBlocks.aura_log.get(), 3).build(consumer, locWood("aura_wood"));

        largeCompressRecipe(ModBlocks.sugilite_block.get(), ModItems.sugilite.get()).build(consumer, locStorage("sugilite_block"));
        largeCompressRecipe(ModBlocks.hematite_block.get(), ModItems.hematite.get()).build(consumer, locStorage("hematite_block"));
        largeCompressRecipe(ModBlocks.cinnabar_block.get(), ModItems.cinnabar.get()).build(consumer, locStorage("cinnabar_block"));
        largeCompressRecipe(ModBlocks.labradorite_block.get(), ModItems.labradorite.get()).build(consumer, locStorage("labradorite_block"));
        largeCompressRecipe(ModBlocks.moonstone_block.get(), ModItems.moonstone.get()).build(consumer, locStorage("moonstone_block"));
        largeCompressRecipe(ModBlocks.opal_block_red.get(), ModItems.red_opal.get()).build(consumer, locStorage("opal_block_red"));
        largeCompressRecipe(ModBlocks.opal_block_blue.get(), ModItems.blue_opal.get()).build(consumer, locStorage("opal_block_blue"));
        largeCompressRecipe(ModBlocks.opal_block_green.get(), ModItems.green_opal.get()).build(consumer, locStorage("opal_block_green"));
        largeCompressRecipe(ModBlocks.opal_block_white.get(), ModItems.white_opal.get()).build(consumer, locStorage("opal_block_white"));
        largeCompressRecipe(ModBlocks.pyrite_block.get(), ModItems.pyrite.get()).build(consumer, locStorage("pyrite_block"));
        largeCompressRecipe(ModBlocks.tektite_block.get(), ModItems.tektite.get()).build(consumer, locStorage("tektite_block"));
        largeCompressRecipe(ModBlocks.goldstone_block.get(), ModItems.goldstone.get()).build(consumer, locStorage("goldstone_block"));
        largeCompressRecipe(ModBlocks.aura_block.get(), ModItems.aura_cluster.get()).build(consumer, locStorage("aura_block"));
        largeCompressRecipe(ModBlocks.bismuth_block.get(), ModItems.bismuth_crystal.get()).build(consumer, locStorage("bismuth_block"));
        largeCompressRecipe(ModBlocks.ixiolite_block.get(), ModItems.ixiolite.get()).build(consumer, locStorage("ixiolite_block"));
        largeCompressRecipe(ModBlocks.proustite_block.get(), ModItems.proustite.get()).build(consumer, locStorage("proustite_block"));
        largeCompressRecipe(ModBlocks.euclase_block.get(), ModItems.euclase.get()).build(consumer, locStorage("euclase_block"));
        largeCompressRecipe(ModBlocks.leucite_block.get(), ModItems.leucite.get()).build(consumer, locStorage("leucite_block"));
        largeCompressRecipe(ModBlocks.carnelian_block.get(), ModItems.carnelian.get()).build(consumer, locStorage("carnelian_block"));
        largeCompressRecipe(ModBlocks.benitoite_block.get(), ModItems.benitoite.get()).build(consumer, locStorage("benitoite_block"));
        largeCompressRecipe(ModBlocks.diopside_block.get(), ModItems.diopside.get()).build(consumer, locStorage("diopside_block"));
        largeCompressRecipe(ModBlocks.chalcedony_block.get(), ModItems.chalcedony.get()).build(consumer, locStorage("chalcedony_block"));

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

        swordRecipeTag(ModItems.agate_sword, GaiaTags.Items.TILES).build(consumer, locTools("agate_sword"));
        pickaxeRecipeTag(ModItems.agate_pickaxe, GaiaTags.Items.TILES).build(consumer, locTools("agate_pickaxe"));
        axeRecipeTag(ModItems.agate_axe, GaiaTags.Items.TILES).build(consumer, locTools("agate_axe"));
        shovelRecipeTag(ModItems.agate_shovel, GaiaTags.Items.TILES).build(consumer, locTools("agate_shovel"));
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

        largeCompressRecipe(ModItems.aura_cluster.get(), ModItems.aura_residue.get()).build(consumer, loc("aura_cluster"));
        largeCompressRecipe(ModItems.bismuth_crystal.get(), ModItems.bismuth_residue.get()).build(consumer, loc("bismuth_crystal"));
        drinkRecipe(ModItems.pink_geode_juice, ModItems.pink_geode_slice).build(consumer, loc("pink_geode_juice"));
        drinkRecipe(ModItems.blue_geode_tea, ModItems.blue_geode_slice).build(consumer, loc("blue_geode_tea"));
        drinkRecipe(ModItems.green_geode_ale, ModItems.green_geode_slice).build(consumer, loc("green_geode_ale"));
        drinkRecipe(ModItems.purple_geode_soda, ModItems.purple_geode_slice).build(consumer, loc("purple_geode_soda"));
        sliceRecipe(ModItems.pink_geode_slice, ModItems.pink_geode).build(consumer, loc("pink_geode_slice"));
        sliceRecipe(ModItems.blue_geode_slice, ModItems.blue_geode).build(consumer, loc("blue_geode_slice"));
        sliceRecipe(ModItems.green_geode_slice, ModItems.green_geode).build(consumer, loc("green_geode_slice"));
        sliceRecipe(ModItems.purple_geode_slice, ModItems.purple_geode).build(consumer, loc("purple_geode_slice"));
        largeCompressRecipe(ModBlocks.cloudy_glass.get(), ModItems.cloudy_shard.get()).build(consumer, loc("cloudy_glass"));
        smallCompressRecipe(ModBlocks.gaia_stone_bricks.get(), ModBlocks.gaia_stone.get(), 4).build(consumer, loc("gaia_stone_bricks"));
        crustBricks(ModBlocks.crusted_gaia_stone_bricks, ModBlocks.gaia_stone_bricks).build(consumer, loc("crusted_gaia_stone_bricks"));
        smallCompressRecipe(ModBlocks.jade_bricks.get(), ModBlocks.raw_jade.get(), 4).build(consumer, loc("jade_bricks"));
        crustBricks(ModBlocks.crusted_jade_bricks, ModBlocks.jade_bricks).build(consumer, loc("crusted_jade_bricks"));
        slabRecipe(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks).build(consumer, loc("jade_brick_slab"));
        stairsRecipe(ModBlocks.jade_brick_stairs, ModBlocks.jade_bricks).build(consumer, loc("jade_brick_stairs"));
        smallCompressRecipe(ModBlocks.copal_bricks.get(), ModBlocks.raw_copal.get(), 4).build(consumer, loc("copal_bricks"));
        crustBricks(ModBlocks.crusted_copal_bricks, ModBlocks.copal_bricks).build(consumer, loc("crusted_copal_bricks"));
        slabRecipe(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks).build(consumer, loc("copal_brick_slab"));
        stairsRecipe(ModBlocks.copal_brick_stairs, ModBlocks.copal_bricks).build(consumer, loc("copal_brick_stairs"));
        smallCompressRecipe(ModBlocks.jet_bricks.get(), ModBlocks.raw_jet.get(), 4).build(consumer, loc("jet_bricks"));
        crustBricks(ModBlocks.crusted_jet_bricks, ModBlocks.jet_bricks).build(consumer, loc("crusted_jet_bricks"));
        slabRecipe(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks).build(consumer, loc("jet_brick_slab"));
        stairsRecipe(ModBlocks.jet_brick_stairs, ModBlocks.jet_bricks).build(consumer, loc("jet_brick_stairs"));
        smallCompressRecipe(ModBlocks.amethyst_bricks.get(), ModBlocks.raw_amethyst.get(), 4).build(consumer, loc("amethyst_bricks"));
        crustBricks(ModBlocks.crusted_amethyst_bricks, ModBlocks.amethyst_bricks).build(consumer, loc("crusted_amethyst_bricks"));
        slabRecipe(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks).build(consumer, loc("amethyst_brick_slab"));
        stairsRecipe(ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_bricks).build(consumer, loc("amethyst_brick_stairs"));
        largeCompressRecipe(ModItems.goldstone.get(), ModItems.goldstone_residue.get()).build(consumer, loc("goldstone"));
        smallCompressRecipe(ModBlocks.gummy_glitter_block.get(), ModItems.sweet_muckball.get()).build(consumer, loc("gummy_glitter_block"));
        largeCompressRecipe(ModItems.pink_goo.get(), ModItems.pink_essence.get()).build(consumer, loc("pink_goo"));
        largeCompressRecipe(ModBlocks.pink_sludge_block.get(), ModItems.pink_goo.get()).build(consumer, loc("pink_sludge_block"));
        tiliRecipe(ModItems.tilipi, ModBlocks.spotted_kersei).build(consumer, loc("tilipi"));
        tiliRecipe(ModItems.tilibl, ModBlocks.thorny_wiltha).build(consumer, loc("tilibl"));
        tiliRecipe(ModItems.tiligr, ModBlocks.roofed_agaric).build(consumer, loc("tiligr"));
        tiliRecipe(ModItems.tilipu, ModBlocks.bulbous_hobina).build(consumer, loc("tilipu"));
        tiliRecipe(ModItems.tiliol, ModBlocks.stickly_cupsir).build(consumer, loc("tiliol"));
        tiliRecipe(ModItems.tilimy, ModBlocks.mystical_murgni).build(consumer, loc("tilimy"));
        tiliRecipe(ModItems.plagued_tiliey, ModBlocks.corrupted_varloom).build(consumer, loc("plagued_tiliey"));
        tiliRecipe(ModItems.tiliou, ModBlocks.ouzium).build(consumer, loc("tiliou"));
        smallCompressRecipe(ModBlocks.saltstone.get(), ModBlocks.salt.get()).build(consumer, loc("saltstone"));
        smallCompressRecipe(ModItems.sugar_cluster.get(), ModItems.sugar_crystals.get()).build(consumer, loc("sugar_cluster"));
        largeCompressRecipe(ModItems.tektite.get(), ModItems.black_residue.get()).build(consumer, loc("tektite"));
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

        ShapedRecipeBuilder.shapedRecipe(ModItems.agate_arrow.get(), 4)
                .patternLine("#")
                .patternLine("/")
                .patternLine("%")
                .key('#', ModItems.sturdy_pebble.get())
                .key('/', ModItems.agate_stick.get())
                .key('%', ModItems.agate_fabric.get())
                .addCriterion("has_pebble", hasItem(ModItems.sturdy_pebble.get()))
                .addCriterion("has_fabric", hasItem(ModItems.agate_fabric.get()))
                .build(consumer, loc("agate_arrow"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.agate_cup.get(), 8)
                .patternLine("# #")
                .patternLine(" # ")
                .key('#', GaiaTags.Items.TILES)
                .addCriterion("has_planks", hasItem(GaiaTags.Items.TILES))
                .build(consumer, loc("agate_cup"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.agate_stick.get(), 4)
                .addIngredient(GaiaTags.Items.TILES)
                .addCriterion("has_planks", hasItem(GaiaTags.Items.TILES))
                .build(consumer, loc("agate_stick"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.bolstered_bricks.get(), 2)
                .patternLine("%#")
                .patternLine("#%")
                .key('#', ModBlocks.reinforced_bricks.get())
                .key('%', ModBlocks.goldstone_block.get())
                .addCriterion("has_brick", hasItem(ModBlocks.reinforced_bricks.get()))
                .addCriterion("has_goldstone", hasItem(ModBlocks.goldstone_block.get()))
                .build(consumer, loc("bolstered_bricks"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.bolstered_bricks.get(), 2)
                .patternLine("#%")
                .patternLine("%#")
                .key('#', ModBlocks.reinforced_bricks.get())
                .key('%', ModBlocks.goldstone_block.get())
                .addCriterion("has_brick", hasItem(ModBlocks.reinforced_bricks.get()))
                .addCriterion("has_goldstone", hasItem(ModBlocks.goldstone_block.get()))
                .build(consumer, loc("bolstered_bricks_2"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupt_grass.get())
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModItems.goldstone_residue.get())
                .key('#', ModBlocks.glitter_grass.get())
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue.get()))
                .build(consumer, loc("corrupt_grass"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupt_soil.get())
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModItems.goldstone_residue.get())
                .key('#', ModBlocks.heavy_soil.get())
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue.get()))
                .build(consumer, loc("corrupt_soil"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.corrupted_sapling.get())
                .patternLine(" / ")
                .patternLine("/#/")
                .patternLine(" / ")
                .key('/', ModItems.goldstone_residue.get())
                .key('#', Ingredient.fromItems(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()))
                .addCriterion("has_residue", hasItem(ModItems.goldstone_residue.get()))
                .build(consumer, loc("corrupted_sapling"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.agate_crafting_table.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', GaiaTags.Items.TILES)
                .addCriterion("has_planks", hasItem(GaiaTags.Items.TILES))
                .build(consumer, loc("crafting_table"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.pearly_geode_elixir.get())
                .addIngredient(ModItems.pink_geode_slice.get())
                .addIngredient(ModItems.blue_geode_slice.get())
                .addIngredient(ModItems.green_geode_slice.get())
                .addIngredient(ModItems.purple_geode_slice.get())
                .addIngredient(ModItems.sugar_cluster.get())
                .addIngredient(ModItems.agate_cup.get())
                .addCriterion("has_cup", hasItem(ModItems.agate_cup.get()))
                .build(consumer, loc("elixir_drink"));
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.frail_glitter_block.get(), 4)
                .addIngredient(ModBlocks.thick_glitter_block.get())
                .addCriterion("has_glitter", hasItem(ModBlocks.frail_glitter_block.get()))
                .build(consumer, loc("frail_glitter_block"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.gaia_stone_furnace.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("###")
                .key('#', ModBlocks.gaia_cobblestone.get())
                .addCriterion("has_stone", hasItem(ModBlocks.gaia_stone.get()))
                .build(consumer, loc("furnace"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.gemstone_pouch.get())
                .addIngredient(ModItems.agate_fabric.get())
                .addIngredient(ModItems.agate_fabric.get())
                .addIngredient(ModItems.agate_fabric.get())
                .addIngredient(ModItems.fine_thread.get())
                .addCriterion("has_fabric", hasItem(ModItems.agate_fabric.get()))
                .build(consumer, loc("gemstone_pouch"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.glint_and_gold.get())
                .addIngredient(Items.DIAMOND)
                .addIngredient(Items.GOLD_INGOT)
                .addCriterion("has_diamond", hasItem(Items.DIAMOND))
                .build(consumer, loc("glint_and_gold"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.keystone_block.get())
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('*', ModItems.crystallized_lapis_lazuli.get())
                .key('%', ModItems.crystallized_redstone.get())
                .key('#', Items.GOLD_INGOT)
                .addCriterion("has_lapis", hasItem(ModItems.crystallized_lapis_lazuli.get()))
                .addCriterion("has_redstone", hasItem(ModItems.crystallized_redstone.get()))
                .build(consumer, loc("keystone_block"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.mega_storage_crate.get())
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('*', GaiaTags.Items.TILES)
                .key('%', ModBlocks.thick_glitter_block.get())
                .key('#', ModBlocks.crude_storage_crate.get())
                .addCriterion("has_crate", hasItem(ModBlocks.crude_storage_crate.get()))
                .build(consumer, loc("large_chest"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.mega_storage_crate.get())
                .patternLine("*%*")
                .patternLine("%#%")
                .patternLine("*%*")
                .key('%', GaiaTags.Items.TILES)
                .key('*', ModBlocks.thick_glitter_block.get())
                .key('#', ModBlocks.crude_storage_crate.get())
                .addCriterion("has_crate", hasItem(ModBlocks.crude_storage_crate.get()))
                .build(consumer, loc("large_chest_2"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.old_bow.get())
                .patternLine("#/ ")
                .patternLine("# /")
                .patternLine("#/ ")
                .key('#', ModItems.twined_thread.get())
                .key('/', ModItems.shiny_bone.get())
                .addCriterion("has_bone", hasItem(ModItems.shiny_bone.get()))
                .build(consumer, locTools("old_bow"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.purifier.get())
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModBlocks.reinforced_bricks.get())
                .key('#', ModBlocks.restructurer.get())
                .addCriterion("has_bricks", hasItem(ModBlocks.reinforced_bricks.get()))
                .build(consumer, loc("purifier"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.PYRITE_TORCH.get(), 4)
                .patternLine("#")
                .patternLine("/")
                .key('#', ModItems.pyrite.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_pyrite", hasItem(ModItems.pyrite.get()))
                .build(consumer, loc("pyrite_torch"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.reinforced_bricks.get(), 2)
                .patternLine("%#")
                .patternLine("#%")
                .key('#', GaiaTags.Items.GAIA_BRICKS)
                .key('%', ModBlocks.thick_glitter_block.get())
                .addCriterion("has_brick", hasItem(GaiaTags.Items.GAIA_BRICKS))
                .addCriterion("has_goldstone", hasItem(ModBlocks.thick_glitter_block.get()))
                .build(consumer, loc("reinforced_bricks"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.reinforced_bricks.get(), 2)
                .patternLine("#%")
                .patternLine("%#")
                .key('#', GaiaTags.Items.GAIA_BRICKS)
                .key('%', ModBlocks.thick_glitter_block.get())
                .addCriterion("has_brick", hasItem(GaiaTags.Items.GAIA_BRICKS))
                .addCriterion("has_goldstone", hasItem(ModBlocks.thick_glitter_block.get()))
                .build(consumer, loc("reinforced_bricks_2"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.restructurer.get())
                .patternLine("///")
                .patternLine("/#/")
                .patternLine("///")
                .key('/', ModBlocks.reinforced_bricks.get())
                .key('#', ModBlocks.gaia_stone_furnace.get())
                .addCriterion("has_bricks", hasItem(ModBlocks.reinforced_bricks.get()))
                .build(consumer, loc("restructurer"));
        ShapedRecipeBuilder.shapedRecipe(ModItems.scaynyx_bucket.get())
                .patternLine("# #")
                .patternLine(" # ")
                .key('#', ModItems.scaynyx_ingot.get())
                .addCriterion("has_ingot", hasItem(ModItems.scaynyx_ingot.get()))
                .build(consumer, loc("scaynyx_bucket"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.crude_storage_crate.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("###")
                .key('#', GaiaTags.Items.TILES)
                .addCriterion("has_planks", hasItem(GaiaTags.Items.TILES))
                .build(consumer, loc("small_chest"));
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.twined_thread.get())
                .addIngredient(ModItems.fine_thread.get())
                .addIngredient(ModItems.fine_thread.get())
                .addIngredient(ModItems.fine_thread.get())
                .addIngredient(ModItems.fine_thread.get())
                .addCriterion("has_thread", hasItem(ModItems.fine_thread.get()))
                .build(consumer, loc("twined_thread"));
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.malachite_chisel_bricks.get())
                .patternLine("#")
                .patternLine("#")
                .key('#', ModBlocks.malachite_brick_slab.get())
                .addCriterion("has_malachite_slab", hasItem(ModBlocks.malachite_brick_slab.get()))
                .build(consumer, loc("malachite_chisel_bricks"));
        ShapelessRecipeBuilder.shapelessRecipe(Items.BONE_MEAL, 3)
                .addIngredient(ModItems.shiny_bone.get())
                .setGroup("bonemeal")
                .addCriterion("has_bone", hasItem(ModItems.shiny_bone.get()))
                .build(consumer, loc("bone_meal"));


        smeltingRecipe(ModItems.blue_opal.get(), ModBlocks.opal_ore_blue.get(), 0.3F).build(consumer, locSmelt("blue_opal_smelt"));
        smeltingRecipe(ModBlocks.burning_sapling.get(), ModBlocks.burnt_sapling.get(), 0.1F).build(consumer, locSmelt("burning_sapling"));
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()), ModBlocks.burnt_sapling.get(), 0.1F, 200)
                .addCriterion("has_sapling", hasItem(ModBlocks.pink_agate_sapling.get()))
                .build(consumer, locSmelt("burnt_sapling"));
        smeltingRecipe(ModItems.cinnabar.get(), ModBlocks.cinnabar_ore.get(), 0.3F).build(consumer, locSmelt("cinnabar_smelt"));
        smeltingRecipe(ModItems.cloudy_shard.get(), ModItems.fine_dust.get(), 0.1F).build(consumer, locSmelt("cloudy_shard"));
        smeltingRecipe(ModItems.cooked_luggeroth_chop.get(), ModItems.luggeroth_chop.get(), 0.2F).build(consumer, locSmelt("cooked_luggeroth_chop"));
        smeltingRecipe(ModItems.crystallized_lapis_lazuli.get(), Items.LAPIS_LAZULI, 0.25F).build(consumer, locSmelt("crystal_lapis"));
        smeltingRecipe(ModItems.crystallized_redstone.get(), Items.REDSTONE, 0.25F).build(consumer, locSmelt("crystal_redstone"));
        smeltingRecipe(ModBlocks.foggy_glass.get(), ModBlocks.salt.get(), 0.1F).build(consumer, locSmelt("foggy_glass"));
        smeltingRecipe(ModBlocks.gaia_stone.get(), ModBlocks.gaia_cobblestone.get(), 0.1F).build(consumer, locSmelt("gaia_stone"));
        smeltingRecipe(ModItems.goldstone_residue.get(), ModItems.goldstone_dust.get(), 0.1F).build(consumer, locSmelt("golstone_residue"));
        smeltingRecipe(ModItems.green_opal.get(), ModBlocks.opal_ore_green.get(), 0.3F).build(consumer, locSmelt("green_opal_smelt"));
        smeltingRecipe(ModItems.hematite.get(), ModBlocks.hematite_ore.get(), 0.3F).build(consumer, locSmelt("hematite_smelt"));
        smeltingRecipe(ModItems.labradorite.get(), ModBlocks.labradorite_ore.get(), 0.3F).build(consumer, locSmelt("labradorite_smelt"));
        smeltingRecipe(ModItems.large_calamari.get(), ModItems.large_tentacle.get(), 0.2F).build(consumer, locSmelt("large_calamari"));
        smeltingRecipe(ModItems.lurmorus_steak.get(), ModItems.lurmorus_meat.get(), 0.2F).build(consumer, locSmelt("lurmorus_steak"));
        smeltingRecipe(ModItems.moonstone.get(), ModBlocks.moonstone_ore.get(), 0.3F).build(consumer, locSmelt("moonstone_smelt"));
        smeltingRecipe(ModItems.pink_essence.get(), ModBlocks.gaia_stone.get(), 0.1F).build(consumer, locSmelt("pink_essence"));
        smeltingRecipe(ModItems.pyrite.get(), ModBlocks.pyrite_ore.get(), 0.3F).build(consumer, locSmelt("pyrite_smelt"));
        smeltingRecipe(ModItems.red_opal.get(), ModBlocks.opal_ore_red.get(), 0.3F).build(consumer, locSmelt("red_opal_smelt"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.precious_rock.get(), 0.8F, 4).build(consumer, locSmelt("scaynyx_large"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.coarse_rock.get(), 0.4F, 2).build(consumer, locSmelt("scaynyx_medium"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.speckled_rock.get(), 0.2F).build(consumer, locSmelt("scaynyx_small"));
        smeltingRecipe(ModItems.small_calamari.get(), ModItems.small_tentacle.get(), 0.2F).build(consumer, locSmelt("small_calamari"));
        smeltingRecipe(ModItems.sugilite.get(), ModBlocks.sugilite_ore.get(), 0.3F).build(consumer, locSmelt("sugilite_smelt"));
        smeltingRecipe(ModBlocks.thick_glitter_block.get(), ModBlocks.gummy_glitter_block.get(), 0.1F).build(consumer, locSmelt("thick_glitter_block"));
        smeltingRecipe(ModItems.white_opal.get(), ModBlocks.opal_ore_white.get(), 1.0F).build(consumer, locSmelt("white_opal_smelt"));
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
