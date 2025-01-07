package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaRecipeProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class GaiaRecipes extends GaiaRecipeProvider {

    public GaiaRecipes(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        planksRecipe(ModBlocks.pink_agate_tiles, GaiaTags.Items.PINK_AGATE_LOGS).save(this.output, locWood("pink_agate_tiles"));
        planksRecipe(ModBlocks.blue_agate_tiles, GaiaTags.Items.BLUE_AGATE_LOGS).save(this.output, locWood("blue_agate_tiles"));
        planksRecipe(ModBlocks.green_agate_tiles, GaiaTags.Items.GREEN_AGATE_LOGS).save(this.output, locWood("green_agate_tiles"));
        planksRecipe(ModBlocks.purple_agate_tiles, GaiaTags.Items.PURPLE_AGATE_LOGS).save(this.output, locWood("purple_agate_tiles"));
        planksRecipe(ModBlocks.fossilized_tiles, GaiaTags.Items.FOSSILIZED_LOGS).save(this.output, locWood("fossilized_tiles"));
        planksRecipe(ModBlocks.corrupted_tiles, GaiaTags.Items.CORRUPTED_LOGS).save(this.output, locWood("corrupted_tiles"));
        planksRecipe(ModBlocks.burnt_tiles, GaiaTags.Items.BURNT_LOGS).save(this.output, locWood("burnt_agate_tiles"));
        planksRecipe(ModBlocks.fire_agate_tiles, GaiaTags.Items.BURNING_LOGS).save(this.output, locWood("fire_agate_tiles"));
        planksRecipe(ModBlocks.aura_tiles, GaiaTags.Items.AURA_LOGS).save(this.output, locWood("aura_tiles"));
        planksRecipe(ModBlocks.golden_tiles, GaiaTags.Items.GOLDEN_LOGS).save(this.output, locWood("golden_tiles"));
        slabRecipe(ModBlocks.pink_agate_tile_slab, ModBlocks.pink_agate_tiles).save(this.output, locWood("pink_agate_tile_slab"));
        slabRecipe(ModBlocks.blue_agate_tile_slab, ModBlocks.blue_agate_tiles).save(this.output, locWood("blue_agate_tile_slab"));
        slabRecipe(ModBlocks.green_agate_tile_slab, ModBlocks.green_agate_tiles).save(this.output, locWood("green_agate_tile_slab"));
        slabRecipe(ModBlocks.purple_agate_tile_slab, ModBlocks.purple_agate_tiles).save(this.output, locWood("purple_agate_tile_slab"));
        slabRecipe(ModBlocks.fossilized_tile_slab, ModBlocks.fossilized_tiles).save(this.output, locWood("fossilized_tile_slab"));
        slabRecipe(ModBlocks.corrupted_tile_slab, ModBlocks.corrupted_tiles).save(this.output, locWood("corrupted_tile_slab"));
        slabRecipe(ModBlocks.burnt_tile_slab, ModBlocks.burnt_tiles).save(this.output, locWood("burnt_agate_tile_slab"));
        slabRecipe(ModBlocks.fire_agate_tile_slab, ModBlocks.fire_agate_tiles).save(this.output, locWood("fire_agate_tile_slab"));
        slabRecipe(ModBlocks.aura_tile_slab, ModBlocks.aura_tiles).save(this.output, locWood("aura_tile_slab"));
        slabRecipe(ModBlocks.golden_tile_slab, ModBlocks.golden_tiles).save(this.output, locWood("golden_tile_slab"));
        stairsRecipe(ModBlocks.pink_agate_tile_stairs, ModBlocks.pink_agate_tiles).save(this.output, locWood("pink_agate_tile_stairs"));
        stairsRecipe(ModBlocks.blue_agate_tile_stairs, ModBlocks.blue_agate_tiles).save(this.output, locWood("blue_agate_tile_stairs"));
        stairsRecipe(ModBlocks.green_agate_tile_stairs, ModBlocks.green_agate_tiles).save(this.output, locWood("green_agate_tile_stairs"));
        stairsRecipe(ModBlocks.purple_agate_tile_stairs, ModBlocks.purple_agate_tiles).save(this.output, locWood("purple_agate_tile_stairs"));
        stairsRecipe(ModBlocks.fossilized_tile_stairs, ModBlocks.fossilized_tiles).save(this.output, locWood("fossilized_tile_stairs"));
        stairsRecipe(ModBlocks.corrupted_tile_stairs, ModBlocks.corrupted_tiles).save(this.output, locWood("corrupted_tile_stairs"));
        stairsRecipe(ModBlocks.burnt_tile_stairs, ModBlocks.burnt_tiles).save(this.output, locWood("burnt_tile_stairs"));
        stairsRecipe(ModBlocks.fire_agate_tile_stairs, ModBlocks.fire_agate_tiles).save(this.output, locWood("fire_agate_tile_stairs"));
        stairsRecipe(ModBlocks.aura_tile_stairs, ModBlocks.aura_tiles).save(this.output, locWood("aura_tile_stairs"));
        smallCompressRecipe(ModBlocks.pink_agate_wood.get(), ModBlocks.pink_agate_log.get(), 3).save(this.output, locWood("pink_agate_wood"));
        smallCompressRecipe(ModBlocks.blue_agate_wood.get(), ModBlocks.blue_agate_log.get(), 3).save(this.output, locWood("blue_agate_wood"));
        smallCompressRecipe(ModBlocks.green_agate_wood.get(), ModBlocks.green_agate_log.get(), 3).save(this.output, locWood("green_agate_wood"));
        smallCompressRecipe(ModBlocks.purple_agate_wood.get(), ModBlocks.purple_agate_log.get(), 3).save(this.output, locWood("purple_agate_wood"));
        smallCompressRecipe(ModBlocks.fossilized_wood.get(), ModBlocks.fossilized_log.get(), 3).save(this.output, locWood("fossilized_wood"));
        smallCompressRecipe(ModBlocks.corrupted_wood.get(), ModBlocks.corrupted_log.get(), 3).save(this.output, locWood("corrupted_wood"));
        smallCompressRecipe(ModBlocks.burnt_wood.get(), ModBlocks.burnt_log.get(), 3).save(this.output, locWood("burnt_agate_wood"));
        smallCompressRecipe(ModBlocks.fire_agate_wood.get(), ModBlocks.fire_agate_log.get(), 3).save(this.output, locWood("fire_agate_wood"));
        smallCompressRecipe(ModBlocks.aura_wood.get(), ModBlocks.aura_log.get(), 3).save(this.output, locWood("aura_wood"));
        smallCompressRecipe(ModBlocks.golden_wood.get(), ModBlocks.golden_log.get(), 3).save(this.output, locWood("golden_wood"));
        curtainRecipe(ModBlocks.pink_agate_curtain, ModBlocks.pink_agate_tiles, 2).save(this.output, loc("pink_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.pink_agate_curtain, ModBlocks.pink_agate_leaves, 4).save(this.output, loc("pink_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.blue_agate_curtain, ModBlocks.blue_agate_tiles, 2).save(this.output, loc("blue_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.blue_agate_curtain, ModBlocks.blue_agate_leaves, 4).save(this.output, loc("blue_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.green_agate_curtain, ModBlocks.green_agate_tiles, 2).save(this.output, loc("green_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.green_agate_curtain, ModBlocks.green_agate_leaves, 4).save(this.output, loc("green_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.purple_agate_curtain, ModBlocks.purple_agate_tiles, 2).save(this.output, loc("purple_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.purple_agate_curtain, ModBlocks.purple_agate_leaves, 4).save(this.output, loc("purple_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.fossilized_curtain, ModBlocks.fossilized_tiles, 2).save(this.output, loc("fossilized_curtain_tiles"));
        curtainRecipe(ModBlocks.fossilized_curtain, ModBlocks.fossilized_leaves, 4).save(this.output, loc("fossilized_curtain_leaves"));
        curtainRecipe(ModBlocks.corrupted_curtain, ModBlocks.corrupted_tiles, 2).save(this.output, loc("corrupted_curtain_tiles"));
        curtainRecipe(ModBlocks.corrupted_curtain, ModBlocks.corrupted_leaves, 4).save(this.output, loc("corrupted_curtain_leaves"));
        curtainRecipe(ModBlocks.burnt_agate_curtain, ModBlocks.burnt_tiles, 2).save(this.output, loc("burnt_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.burnt_agate_curtain, ModBlocks.burnt_leaves, 4).save(this.output, loc("burnt_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.fire_agate_curtain, ModBlocks.fire_agate_tiles, 2).save(this.output, loc("fire_agate_curtain_tiles"));
        curtainRecipe(ModBlocks.fire_agate_curtain, ModBlocks.fire_agate_leaves, 4).save(this.output, loc("fire_agate_curtain_leaves"));
        curtainRecipe(ModBlocks.aura_curtain, ModBlocks.aura_tiles, 2).save(this.output, loc("aura_curtain_tiles"));
        curtainRecipe(ModBlocks.aura_curtain, ModBlocks.aura_leaves, 4).save(this.output, loc("aura_curtain_leaves"));
        curtainRecipe(ModBlocks.golden_curtain, ModBlocks.golden_tiles, 2).save(this.output, loc("golden_curtain_tiles"));
        curtainRecipe(ModBlocks.golden_curtain, ModBlocks.golden_leaves, 4).save(this.output, loc("golden_curtain_leaves"));

        largeCompressRecipe(ModBlocks.sugilite_block.get(), ModItems.sugilite.get()).save(this.output, locStorage("sugilite_block"));
        largeCompressRecipe(ModBlocks.hematite_block.get(), ModItems.hematite.get()).save(this.output, locStorage("hematite_block"));
        largeCompressRecipe(ModBlocks.cinnabar_block.get(), ModItems.cinnabar.get()).save(this.output, locStorage("cinnabar_block"));
        largeCompressRecipe(ModBlocks.labradorite_block.get(), ModItems.labradorite.get()).save(this.output, locStorage("labradorite_block"));
        largeCompressRecipe(ModBlocks.moonstone_block.get(), ModItems.moonstone.get()).save(this.output, locStorage("moonstone_block"));
        largeCompressRecipe(ModBlocks.red_opal_block.get(), ModItems.red_opal.get()).save(this.output, locStorage("red_opal_block"));
        largeCompressRecipe(ModBlocks.blue_opal_block.get(), ModItems.blue_opal.get()).save(this.output, locStorage("blue_opal_block"));
        largeCompressRecipe(ModBlocks.green_opal_block.get(), ModItems.green_opal.get()).save(this.output, locStorage("green_opal_block"));
        largeCompressRecipe(ModBlocks.white_opal_block.get(), ModItems.white_opal.get()).save(this.output, locStorage("white_opal_block"));
        largeCompressRecipe(ModBlocks.pyrite_block.get(), ModItems.pyrite.get()).save(this.output, locStorage("pyrite_block"));
        largeCompressRecipe(ModBlocks.tektite_block.get(), ModItems.tektite.get()).save(this.output, locStorage("tektite_block"));
        largeCompressRecipe(ModBlocks.goldstone_block.get(), ModItems.goldstone.get()).save(this.output, locStorage("goldstone_block"));
        largeCompressRecipe(ModBlocks.aura_block.get(), ModItems.aura_cluster.get()).save(this.output, locStorage("aura_block"));
        largeCompressRecipe(ModBlocks.bismuth_block.get(), ModItems.bismuth_crystal.get()).save(this.output, locStorage("bismuth_block"));
        largeCompressRecipe(ModBlocks.opalite_block.get(), ModItems.opalite.get()).save(this.output, locStorage("opalite_block"));
        largeCompressRecipe(ModBlocks.stibnite_block.get(), ModItems.stibnite.get()).save(this.output, locStorage("stibnite_block"));
        largeCompressRecipe(ModBlocks.proustite_block.get(), ModItems.proustite.get()).save(this.output, locStorage("proustite_block"));
        largeCompressRecipe(ModBlocks.euclase_block.get(), ModItems.euclase.get()).save(this.output, locStorage("euclase_block"));
        largeCompressRecipe(ModBlocks.albite_block.get(), ModItems.albite.get()).save(this.output, locStorage("albite_block"));
        largeCompressRecipe(ModBlocks.carnelian_block.get(), ModItems.carnelian.get()).save(this.output, locStorage("carnelian_block"));
        largeCompressRecipe(ModBlocks.benitoite_block.get(), ModItems.benitoite.get()).save(this.output, locStorage("benitoite_block"));
        largeCompressRecipe(ModBlocks.diopside_block.get(), ModItems.diopside.get()).save(this.output, locStorage("diopside_block"));
        largeCompressRecipe(ModBlocks.goshenite_block.get(), ModItems.goshenite.get()).save(this.output, locStorage("goshenite_block"));
        largeCompressRecipe(ModBlocks.celestine_block.get(), ModItems.celestine.get()).save(this.output, locStorage("celestine_block"));

        blockToItemRecipe(ModItems.sugilite, ModBlocks.sugilite_block).save(this.output, locStorage("sugilite_block_item"));
        blockToItemRecipe(ModItems.hematite, ModBlocks.hematite_block).save(this.output, locStorage("hematite_block_item"));
        blockToItemRecipe(ModItems.cinnabar, ModBlocks.cinnabar_block).save(this.output, locStorage("cinnabar_block_item"));
        blockToItemRecipe(ModItems.labradorite, ModBlocks.labradorite_block).save(this.output, locStorage("labradorite_block_item"));
        blockToItemRecipe(ModItems.moonstone, ModBlocks.moonstone_block).save(this.output, locStorage("moonstone_block_item"));
        blockToItemRecipe(ModItems.red_opal, ModBlocks.red_opal_block).save(this.output, locStorage("red_opal_block_item"));
        blockToItemRecipe(ModItems.blue_opal, ModBlocks.blue_opal_block).save(this.output, locStorage("blue_opal_block_item"));
        blockToItemRecipe(ModItems.green_opal, ModBlocks.green_opal_block).save(this.output, locStorage("green_opal_block_item"));
        blockToItemRecipe(ModItems.white_opal, ModBlocks.white_opal_block).save(this.output, locStorage("white_opal_block_item"));
        blockToItemRecipe(ModItems.stibnite, ModBlocks.stibnite_block).save(this.output, locStorage("stibnite_block_item"));
        blockToItemRecipe(ModItems.proustite, ModBlocks.proustite_block).save(this.output, locStorage("proustite_block_item"));
        blockToItemRecipe(ModItems.euclase, ModBlocks.euclase_block).save(this.output, locStorage("euclase_block_item"));
        blockToItemRecipe(ModItems.albite, ModBlocks.albite_block).save(this.output, locStorage("albite_block_item"));
        blockToItemRecipe(ModItems.carnelian, ModBlocks.carnelian_block).save(this.output, locStorage("carnelian_block_item"));
        blockToItemRecipe(ModItems.benitoite, ModBlocks.benitoite_block).save(this.output, locStorage("benitoite_block_item"));
        blockToItemRecipe(ModItems.diopside, ModBlocks.diopside_block).save(this.output, locStorage("diopside_block_item"));
        blockToItemRecipe(ModItems.goshenite, ModBlocks.goshenite_block).save(this.output, locStorage("goshenite_block_item"));
        blockToItemRecipe(ModItems.pyrite, ModBlocks.pyrite_block).save(this.output, locStorage("pyrite_block_item"));
        blockToItemRecipe(ModItems.tektite, ModBlocks.tektite_block).save(this.output, locStorage("tektite_block_item"));
        blockToItemRecipe(ModItems.goldstone, ModBlocks.goldstone_block).save(this.output, locStorage("goldstone_block_item"));
        blockToItemRecipe(ModItems.aura_cluster, ModBlocks.aura_block).save(this.output, locStorage("aura_cluster_block_item"));
        blockToItemRecipe(ModItems.bismuth_crystal, ModBlocks.bismuth_block).save(this.output, locStorage("bismuth_crystal_block_item"));
        blockToItemRecipe(ModItems.opalite, ModBlocks.opalite_block).save(this.output, locStorage("opalite_block_item"));
        blockToItemRecipe(ModItems.celestine, ModBlocks.celestine_block).save(this.output, locStorage("celestine_block_item"));

        helmetRecipe(ModItems.sugilite_helmet, ModItems.sugilite).save(this.output, locArmor("sugilite_helmet"));
        chestRecipe(ModItems.sugilite_chestplate, ModItems.sugilite).save(this.output, locArmor("sugilite_chestplate"));
        legsRecipe(ModItems.sugilite_legs, ModItems.sugilite).save(this.output, locArmor("sugilite_legs"));
        bootsRecipe(ModItems.sugilite_boots, ModItems.sugilite).save(this.output, locArmor("sugilite_boots"));
        helmetRecipe(ModItems.proustite_helmet, ModItems.proustite).save(this.output, locArmor("proustite_helmet"));
        chestRecipe(ModItems.proustite_chestplate, ModItems.proustite).save(this.output, locArmor("proustite_chestplate"));
        legsRecipe(ModItems.proustite_legs, ModItems.proustite).save(this.output, locArmor("proustite_legs"));
        bootsRecipe(ModItems.proustite_boots, ModItems.proustite).save(this.output, locArmor("proustite_boots"));
        helmetRecipe(ModItems.albite_helmet, ModItems.albite).save(this.output, locArmor("albite_helmet"));
        chestRecipe(ModItems.albite_chestplate, ModItems.albite).save(this.output, locArmor("albite_chestplate"));
        legsRecipe(ModItems.albite_legs, ModItems.albite).save(this.output, locArmor("albite_legs"));
        bootsRecipe(ModItems.albite_boots, ModItems.albite).save(this.output, locArmor("albite_boots"));
        helmetRecipe(ModItems.carnelian_helmet, ModItems.carnelian).save(this.output, locArmor("carnelian_helmet"));
        chestRecipe(ModItems.carnelian_chestplate, ModItems.carnelian).save(this.output, locArmor("carnelian_chestplate"));
        legsRecipe(ModItems.carnelian_legs, ModItems.carnelian).save(this.output, locArmor("carnelian_legs"));
        bootsRecipe(ModItems.carnelian_boots, ModItems.carnelian).save(this.output, locArmor("carnelian_boots"));
        helmetRecipe(ModItems.diopside_helmet, ModItems.diopside).save(this.output, locArmor("diopside_helmet"));
        chestRecipe(ModItems.diopside_chestplate, ModItems.diopside).save(this.output, locArmor("diopside_chestplate"));
        legsRecipe(ModItems.diopside_legs, ModItems.diopside).save(this.output, locArmor("diopside_legs"));
        bootsRecipe(ModItems.diopside_boots, ModItems.diopside).save(this.output, locArmor("diopside_boots"));
        helmetRecipe(ModItems.goshenite_helmet, ModItems.goshenite).save(this.output, locArmor("goshenite_helmet"));
        chestRecipe(ModItems.goshenite_chestplate, ModItems.goshenite).save(this.output, locArmor("goshenite_chestplate"));
        legsRecipe(ModItems.goshenite_legs, ModItems.goshenite).save(this.output, locArmor("goshenite_legs"));
        bootsRecipe(ModItems.goshenite_boots, ModItems.goshenite).save(this.output, locArmor("goshenite_boots"));

        swordRecipeTag(ModItems.agate_sword, GaiaTags.Items.TILES).save(this.output, locTools("agate_sword"));
        pickaxeRecipeTag(ModItems.agate_pickaxe, GaiaTags.Items.TILES).save(this.output, locTools("agate_pickaxe"));
        axeRecipeTag(ModItems.agate_axe, GaiaTags.Items.TILES).save(this.output, locTools("agate_axe"));
        shovelRecipeTag(ModItems.agate_shovel, GaiaTags.Items.TILES).save(this.output, locTools("agate_shovel"));
        swordRecipe(ModItems.sugilite_sword, ModItems.sugilite).save(this.output, locTools("sugilite_sword"));
        pickaxeRecipe(ModItems.sugilite_pickaxe, ModItems.sugilite).save(this.output, locTools("sugilite_pickaxe"));
        axeRecipe(ModItems.sugilite_axe, ModItems.sugilite).save(this.output, locTools("sugilite_axe"));
        shovelRecipe(ModItems.sugilite_shovel, ModItems.sugilite).save(this.output, locTools("sugilite_shovel"));
        swordRecipe(ModItems.stibnite_sword, ModItems.stibnite).save(this.output, locTools("stibnite_sword"));
        pickaxeRecipe(ModItems.stibnite_pickaxe, ModItems.stibnite).save(this.output, locTools("stibnite_pickaxe"));
        axeRecipe(ModItems.stibnite_axe, ModItems.stibnite).save(this.output, locTools("stibnite_axe"));
        shovelRecipe(ModItems.stibnite_shovel, ModItems.stibnite).save(this.output, locTools("stibnite_shovel"));
        swordRecipe(ModItems.euclase_sword, ModItems.euclase).save(this.output, locTools("euclase_sword"));
        pickaxeRecipe(ModItems.euclase_pickaxe, ModItems.euclase).save(this.output, locTools("euclase_pickaxe"));
        axeRecipe(ModItems.euclase_axe, ModItems.euclase).save(this.output, locTools("euclase_axe"));
        shovelRecipe(ModItems.euclase_shovel, ModItems.euclase).save(this.output, locTools("euclase_shovel"));
        swordRecipe(ModItems.carnelian_sword, ModItems.carnelian).save(this.output, locTools("carnelian_sword"));
        pickaxeRecipe(ModItems.carnelian_pickaxe, ModItems.carnelian).save(this.output, locTools("carnelian_pickaxe"));
        axeRecipe(ModItems.carnelian_axe, ModItems.carnelian).save(this.output, locTools("carnelian_axe"));
        shovelRecipe(ModItems.carnelian_shovel, ModItems.carnelian).save(this.output, locTools("carnelian_shovel"));
        swordRecipe(ModItems.benitoite_sword, ModItems.benitoite).save(this.output, locTools("benitoite_sword"));
        pickaxeRecipe(ModItems.benitoite_pickaxe, ModItems.benitoite).save(this.output, locTools("benitoite_pickaxe"));
        axeRecipe(ModItems.benitoite_axe, ModItems.benitoite).save(this.output, locTools("benitoite_axe"));
        shovelRecipe(ModItems.benitoite_shovel, ModItems.benitoite).save(this.output, locTools("benitoite_shovel"));
        swordRecipe(ModItems.goshenite_sword, ModItems.goshenite).save(this.output, locTools("goshenite_sword"));
        pickaxeRecipe(ModItems.goshenite_pickaxe, ModItems.goshenite).save(this.output, locTools("goshenite_pickaxe"));
        axeRecipe(ModItems.goshenite_axe, ModItems.goshenite).save(this.output, locTools("goshenite_axe"));
        shovelRecipe(ModItems.goshenite_shovel, ModItems.goshenite).save(this.output, locTools("goshenite_shovel"));

        largeCompressRecipe(ModItems.aura_cluster.get(), ModItems.aura_residue.get()).save(this.output, loc("aura_cluster"));
        largeCompressRecipe(ModItems.bismuth_crystal.get(), ModItems.bismuth_residue.get()).save(this.output, loc("bismuth_crystal"));
        drinkRecipe(ModItems.pink_geode_juice, ModItems.pink_geode_slice).save(this.output, loc("pink_geode_juice"));
        drinkRecipe(ModItems.blue_geode_tea, ModItems.blue_geode_slice).save(this.output, loc("blue_geode_tea"));
        drinkRecipe(ModItems.green_geode_ale, ModItems.green_geode_slice).save(this.output, loc("green_geode_ale"));
        drinkRecipe(ModItems.purple_geode_soda, ModItems.purple_geode_slice).save(this.output, loc("purple_geode_soda"));
        sliceRecipe(ModItems.pink_geode_slice, ModItems.pink_geode).save(this.output, loc("pink_geode_slice"));
        sliceRecipe(ModItems.blue_geode_slice, ModItems.blue_geode).save(this.output, loc("blue_geode_slice"));
        sliceRecipe(ModItems.green_geode_slice, ModItems.green_geode).save(this.output, loc("green_geode_slice"));
        sliceRecipe(ModItems.purple_geode_slice, ModItems.purple_geode).save(this.output, loc("purple_geode_slice"));
        largeCompressRecipe(ModBlocks.cloudy_glass.get(), ModItems.cloudy_shard.get()).save(this.output, loc("cloudy_glass"));
        smallCompressRecipe(ModBlocks.gaia_stone_bricks.get(), ModBlocks.gaia_stone.get(), 4).save(this.output, loc("gaia_stone_bricks"));
        crustBricks(ModBlocks.crusted_gaia_stone_bricks, ModBlocks.gaia_stone_bricks).save(this.output, loc("crusted_gaia_stone_bricks"));
        smallCompressRecipe(ModBlocks.jade_bricks.get(), ModBlocks.raw_jade.get(), 4).save(this.output, loc("jade_bricks"));
        crustBricks(ModBlocks.crusted_jade_bricks, ModBlocks.jade_bricks).save(this.output, loc("crusted_jade_bricks"));
        slabRecipe(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks).save(this.output, loc("jade_brick_slab"));
        stairsRecipe(ModBlocks.jade_brick_stairs, ModBlocks.jade_bricks).save(this.output, loc("jade_brick_stairs"));
        smallCompressRecipe(ModBlocks.copal_bricks.get(), ModBlocks.raw_copal.get(), 4).save(this.output, loc("copal_bricks"));
        crustBricks(ModBlocks.crusted_copal_bricks, ModBlocks.copal_bricks).save(this.output, loc("crusted_copal_bricks"));
        slabRecipe(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks).save(this.output, loc("copal_brick_slab"));
        stairsRecipe(ModBlocks.copal_brick_stairs, ModBlocks.copal_bricks).save(this.output, loc("copal_brick_stairs"));
        smallCompressRecipe(ModBlocks.jet_bricks.get(), ModBlocks.raw_jet.get(), 4).save(this.output, loc("jet_bricks"));
        crustBricks(ModBlocks.crusted_jet_bricks, ModBlocks.jet_bricks).save(this.output, loc("crusted_jet_bricks"));
        slabRecipe(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks).save(this.output, loc("jet_brick_slab"));
        stairsRecipe(ModBlocks.jet_brick_stairs, ModBlocks.jet_bricks).save(this.output, loc("jet_brick_stairs"));
        smallCompressRecipe(ModBlocks.amethyst_bricks.get(), ModBlocks.raw_amethyst.get(), 4).save(this.output, loc("amethyst_bricks"));
        crustBricks(ModBlocks.crusted_amethyst_bricks, ModBlocks.amethyst_bricks).save(this.output, loc("crusted_amethyst_bricks"));
        slabRecipe(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks).save(this.output, loc("amethyst_brick_slab"));
        stairsRecipe(ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_bricks).save(this.output, loc("amethyst_brick_stairs"));
        largeCompressRecipe(ModItems.goldstone.get(), ModItems.goldstone_residue.get()).save(this.output, loc("goldstone"));
        smallCompressRecipe(ModBlocks.gummy_glitter_block.get(), ModItems.sweet_muckball.get()).save(this.output, loc("gummy_glitter_block"));
        largeCompressRecipe(ModItems.pink_goo.get(), ModItems.pink_essence.get()).save(this.output, loc("pink_goo"));
        largeCompressRecipe(ModBlocks.pink_sludge_block.get(), ModItems.pink_goo.get()).save(this.output, loc("pink_sludge_block"));
        tiliRecipe(ModItems.tilipi, ModBlocks.spotted_kersei).save(this.output, loc("tilipi"));
        tiliRecipe(ModItems.tilibl, ModBlocks.thorny_wiltha).save(this.output, loc("tilibl"));
        tiliRecipe(ModItems.tiligr, ModBlocks.roofed_agaric).save(this.output, loc("tiligr"));
        tiliRecipe(ModItems.tilipu, ModBlocks.bulbous_hobina).save(this.output, loc("tilipu"));
        tiliRecipe(ModItems.tiliol, ModBlocks.stickly_cupsir).save(this.output, loc("tiliol"));
        tiliRecipe(ModItems.tilimy, ModBlocks.mystical_murgni).save(this.output, loc("tilimy"));
        tiliRecipe(ModItems.plagued_tiliey, ModBlocks.corrupted_varloom).save(this.output, loc("plagued_tiliey"));
        tiliRecipe(ModItems.tiliou, ModBlocks.ouzium).save(this.output, loc("tiliou"));
        smallCompressRecipe(ModBlocks.saltstone.get(), ModBlocks.salt.get()).save(this.output, loc("saltstone"));
        smallCompressRecipe(ModItems.sugar_cluster.get(), ModItems.sugar_crystals.get()).save(this.output, loc("sugar_cluster"));
        largeCompressRecipe(ModItems.tektite.get(), ModItems.black_residue.get()).save(this.output, loc("tektite"));
        crustBricks(ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_bricks).save(this.output, loc("malachite_crusted_bricks"));
        stairsRecipe(ModBlocks.malachite_brick_stairs, ModBlocks.malachite_bricks).save(this.output, loc("malachite_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_cracked_bricks).save(this.output, loc("malachite_cracked_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_crusted_bricks).save(this.output, loc("malachite_crusted_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_chisel_bricks).save(this.output, loc("malachite_chisel_stairs"));
        stairsRecipe(ModBlocks.malachite_tile_stairs, ModBlocks.malachite_tiles).save(this.output, loc("malachite_tile_stairs"));
        stairsRecipe(ModBlocks.malachite_pillar_stairs, ModBlocks.malachite_pillar).save(this.output, loc("malachite_pillar_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_bricks).save(this.output, loc("malachite_pulsing_brick_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_tiles).save(this.output, loc("malachite_pulsing_tile_stairs"));
        stairsRecipe(ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pulsing_chisel).save(this.output, loc("malachite_pulsing_chisel_stairs"));
        slabRecipe(ModBlocks.malachite_brick_slab, ModBlocks.malachite_bricks).save(this.output, loc("malachite_brick_slab"));
        slabRecipe(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_cracked_bricks).save(this.output, loc("malachite_cracked_brick_slab"));
        slabRecipe(ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_crusted_bricks).save(this.output, loc("malachite_crusted_brick_slab"));
        slabRecipe(ModBlocks.malachite_tile_slab, ModBlocks.malachite_tiles).save(this.output, loc("malachite_tile_slab"));

        this.shaped(RecipeCategory.COMBAT, ModItems.agate_arrow.get(), 4)
                .pattern("#")
                .pattern("/")
                .pattern("%")
                .define('#', ModItems.sturdy_pebble.get())
                .define('/', ModItems.agate_stick.get())
                .define('%', ModItems.agate_fabric.get())
                .unlockedBy("has_pebble", has(ModItems.sturdy_pebble.get()))
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(this.output, loc("agate_arrow"));
        this.shaped(RecipeCategory.MISC, ModItems.agate_cup.get(), 8)
                .pattern("# #")
                .pattern(" # ")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output, loc("agate_cup"));
        this.shapeless(RecipeCategory.MISC, ModItems.agate_stick.get(), 4)
                .requires(GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output, loc("agate_stick"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.bolstered_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', ModBlocks.reinforced_bricks.get())
                .define('%', ModBlocks.goldstone_block.get())
                .unlockedBy("has_brick", has(ModBlocks.reinforced_bricks.get()))
                .unlockedBy("has_goldstone", has(ModBlocks.goldstone_block.get()))
                .save(this.output, loc("bolstered_bricks"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.bolstered_bricks.get(), 2)
                .pattern("#%")
                .pattern("%#")
                .define('#', ModBlocks.reinforced_bricks.get())
                .define('%', ModBlocks.goldstone_block.get())
                .unlockedBy("has_brick", has(ModBlocks.reinforced_bricks.get()))
                .unlockedBy("has_goldstone", has(ModBlocks.goldstone_block.get()))
                .save(this.output, loc("bolstered_bricks_2"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.corrupted_grass.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', ModBlocks.glitter_grass.get())
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(this.output, loc("corrupted_grass"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.corrupted_soil.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', ModBlocks.heavy_soil.get())
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(this.output, loc("corrupted_soil"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.corrupted_sapling.get())
                .pattern(" / ")
                .pattern("/#/")
                .pattern(" / ")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()))
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(this.output, loc("corrupted_sapling"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.agate_crafting_table.get())
                .pattern("##")
                .pattern("##")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output, loc("crafting_table"));
        this.shapeless(RecipeCategory.FOOD, ModItems.pearly_geode_elixir.get())
                .requires(ModItems.pink_geode_slice.get())
                .requires(ModItems.blue_geode_slice.get())
                .requires(ModItems.green_geode_slice.get())
                .requires(ModItems.purple_geode_slice.get())
                .requires(ModItems.sugar_cluster.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()))
                .save(this.output, loc("elixir_drink"));
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.frail_glitter_block.get(), 4)
                .requires(ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_glitter", has(ModBlocks.frail_glitter_block.get()))
                .save(this.output, loc("frail_glitter_block"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.gaia_stone_furnace.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', ModBlocks.gaia_cobblestone.get())
                .unlockedBy("has_stone", has(ModBlocks.gaia_stone.get()))
                .save(this.output, loc("furnace"));
        this.shapeless(RecipeCategory.TOOLS, ModItems.gemstone_pouch.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(this.output, loc("gemstone_pouch"));
        this.shapeless(RecipeCategory.TOOLS, ModItems.glint_and_gold.get())
                .requires(Items.DIAMOND)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(this.output, loc("glint_and_gold"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.keystone_block.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', ModItems.crystallized_lapis_lazuli.get())
                .define('%', ModItems.crystallized_redstone.get())
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_lapis", has(ModItems.crystallized_lapis_lazuli.get()))
                .unlockedBy("has_redstone", has(ModItems.crystallized_redstone.get()))
                .save(this.output, loc("keystone_block"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.mega_storage_crate.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', GaiaTags.Items.TILES)
                .define('%', ModBlocks.thick_glitter_block.get())
                .define('#', ModBlocks.crude_storage_crate.get())
                .unlockedBy("has_crate", has(ModBlocks.crude_storage_crate.get()))
                .save(this.output, loc("large_chest"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.mega_storage_crate.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('%', GaiaTags.Items.TILES)
                .define('*', ModBlocks.thick_glitter_block.get())
                .define('#', ModBlocks.crude_storage_crate.get())
                .unlockedBy("has_crate", has(ModBlocks.crude_storage_crate.get()))
                .save(this.output, loc("large_chest_2"));
        this.shaped(RecipeCategory.COMBAT, ModItems.old_bow.get())
                .pattern("#/ ")
                .pattern("# /")
                .pattern("#/ ")
                .define('#', ModItems.twined_thread.get())
                .define('/', ModItems.shiny_bone.get())
                .unlockedBy("has_bone", has(ModItems.shiny_bone.get()))
                .save(this.output, locTools("old_bow"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.purifier.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModBlocks.reinforced_bricks.get())
                .define('#', ModBlocks.restructurer.get())
                .unlockedBy("has_bricks", has(ModBlocks.reinforced_bricks.get()))
                .save(this.output, loc("purifier"));
        this.shaped(RecipeCategory.DECORATIONS, ModItems.PYRITE_TORCH.get(), 4)
                .pattern("#")
                .pattern("/")
                .define('#', ModItems.pyrite.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_pyrite", has(ModItems.pyrite.get()))
                .save(this.output, loc("pyrite_torch"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.reinforced_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', GaiaTags.Items.GAIA_BRICKS)
                .define('%', ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_brick", has(GaiaTags.Items.GAIA_BRICKS))
                .unlockedBy("has_goldstone", has(ModBlocks.thick_glitter_block.get()))
                .save(this.output, loc("reinforced_bricks"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.reinforced_bricks.get(), 2)
                .pattern("#%")
                .pattern("%#")
                .define('#', GaiaTags.Items.GAIA_BRICKS)
                .define('%', ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_brick", has(GaiaTags.Items.GAIA_BRICKS))
                .unlockedBy("has_goldstone", has(ModBlocks.thick_glitter_block.get()))
                .save(this.output, loc("reinforced_bricks_2"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.restructurer.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModBlocks.reinforced_bricks.get())
                .define('#', ModBlocks.gaia_stone_furnace.get())
                .unlockedBy("has_bricks", has(ModBlocks.reinforced_bricks.get()))
                .save(this.output, loc("restructurer"));
        this.shaped(RecipeCategory.TOOLS, ModItems.scaynyx_bucket.get())
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.scaynyx_ingot.get())
                .unlockedBy("has_ingot", has(ModItems.scaynyx_ingot.get()))
                .save(this.output, loc("scaynyx_bucket"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.crude_storage_crate.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output, loc("small_chest"));
        this.shapeless(RecipeCategory.MISC, ModItems.twined_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_thread", has(ModItems.fine_thread.get()))
                .save(this.output, loc("twined_thread"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.malachite_chisel_bricks.get())
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.malachite_brick_slab.get())
                .unlockedBy("has_malachite_slab", has(ModBlocks.malachite_brick_slab.get()))
                .save(this.output, loc("malachite_chisel_bricks"));
        this.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 3)
                .requires(ModItems.shiny_bone.get())
                .group("bonemeal")
                .unlockedBy("has_bone", has(ModItems.shiny_bone.get()))
                .save(this.output, loc("bone_meal"));
        this.shaped(RecipeCategory.MISC, ModItems.blank_kit.get())
                .pattern(" / ")
                .pattern("/#/")
                .pattern(" / ")
                .define('/', GaiaTags.Items.MOOKAITE)
                .define('#', ItemTags.PICKAXES)
                .unlockedBy("has_mookaite", has(GaiaTags.Items.MOOKAITE))
                .save(this.output, loc("blank_kit"));
        this.shapeless(RecipeCategory.MISC, ModItems.construct_charm.get())
                .requires(ModItems.celestine.get())
                .requires(GaiaTags.Items.MOOKAITE)
                .requires(ModItems.opalite.get())
                .unlockedBy("has_celestine", has(ModItems.celestine.get()))
                .save(this.output, loc("construct_charm"));

        smeltingRecipe(ModItems.blue_opal.get(), ModBlocks.blue_opal_ore, 0.3F).save(this.output, locSmelt("blue_opal_smelt"));
        smeltingRecipe(ModItems.celestine.get(), ModBlocks.celestine_ore, 1.5F).save(this.output, locSmelt("celestine_smelt"));
        smeltingRecipe(ModBlocks.fire_agate_sapling.get(), ModBlocks.burnt_sapling, 0.1F).save(this.output, locSmelt("fire_agate_sapling"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()), RecipeCategory.DECORATIONS, ModBlocks.burnt_sapling.get(), 0.1F, 200)
                .unlockedBy("has_sapling", has(ModBlocks.pink_agate_sapling.get()))
                .save(this.output, locSmelt("burnt_agate_sapling"));
        smeltingRecipe(ModItems.cinnabar.get(), ModBlocks.cinnabar_ore, 0.3F).save(this.output, locSmelt("cinnabar_smelt"));
        smeltingRecipe(ModItems.cloudy_shard.get(), ModItems.fine_dust, 0.1F).save(this.output, locSmelt("cloudy_shard"));
        smeltingRecipe(ModItems.cooked_luggeroth_chop.get(), ModItems.luggeroth_chop, 0.2F).save(this.output, locSmelt("cooked_luggeroth_chop"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.LAPIS_LAZULI), RecipeCategory.MISC, ModItems.crystallized_lapis_lazuli.get(), 0.25F, 200)
                .unlockedBy("has_lapis", has(Items.LAPIS_LAZULI))
                .save(this.output, locSmelt("crystal_lapis"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.REDSTONE), RecipeCategory.MISC, ModItems.crystallized_redstone.get(), 0.25F, 200)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(this.output, locSmelt("crystal_redstone"));
        smeltingRecipe(ModBlocks.foggy_glass.get(), ModBlocks.salt, 0.1F).save(this.output, locSmelt("foggy_glass"));
        smeltingRecipe(ModBlocks.gaia_stone.get(), ModBlocks.gaia_cobblestone, 0.1F).save(this.output, locSmelt("gaia_stone"));
        smeltingRecipe(ModItems.goldstone_residue.get(), ModItems.goldstone_dust, 0.1F).save(this.output, locSmelt("golstone_residue"));
        smeltingRecipe(ModItems.green_opal.get(), ModBlocks.green_opal_ore, 0.3F).save(this.output, locSmelt("green_opal_smelt"));
        smeltingRecipe(ModItems.hematite.get(), ModBlocks.hematite_ore, 0.3F).save(this.output, locSmelt("hematite_smelt"));
        smeltingRecipe(ModItems.labradorite.get(), ModBlocks.labradorite_ore, 0.3F).save(this.output, locSmelt("labradorite_smelt"));
        smeltingRecipe(ModItems.large_calamari.get(), ModItems.large_tentacle, 0.2F).save(this.output, locSmelt("large_calamari"));
        smeltingRecipe(ModItems.lurmorus_steak.get(), ModItems.lurmorus_meat, 0.2F).save(this.output, locSmelt("lurmorus_steak"));
        smeltingRecipe(ModItems.moonstone.get(), ModBlocks.moonstone_ore, 0.3F).save(this.output, locSmelt("moonstone_smelt"));
        smeltingRecipe(ModItems.pink_essence.get(), ModBlocks.gaia_stone, 0.1F).save(this.output, locSmelt("pink_essence"));
        smeltingRecipe(ModItems.pyrite.get(), ModBlocks.pyrite_ore, 0.3F).save(this.output, locSmelt("pyrite_smelt"));
        smeltingRecipe(ModItems.red_opal.get(), ModBlocks.red_opal_ore, 0.3F).save(this.output, locSmelt("red_opal_smelt"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.precious_rock, 0.8F, 4).save(this.output, locSmelt("scaynyx_large"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.coarse_rock, 0.4F, 2).save(this.output, locSmelt("scaynyx_medium"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), ModBlocks.speckled_rock, 0.2F).save(this.output, locSmelt("scaynyx_small"));
        smeltingRecipe(ModItems.small_calamari.get(), ModItems.small_tentacle, 0.2F).save(this.output, locSmelt("small_calamari"));
        smeltingRecipe(ModItems.sugilite.get(), ModBlocks.sugilite_ore, 0.3F).save(this.output, locSmelt("sugilite_smelt"));
        smeltingRecipe(ModBlocks.thick_glitter_block.get(), ModBlocks.gummy_glitter_block, 0.1F).save(this.output, locSmelt("thick_glitter_block"));
        smeltingRecipe(ModItems.white_opal.get(), ModBlocks.white_opal_ore, 1.0F).save(this.output, locSmelt("white_opal_smelt"));

        restructureBlackResidue(ModItems.benitoite, ModItems.blue_opal, 0.3F, 1).save(this.output, locRestructure("benitoite"));
        restructuringTektite(ModBlocks.benitoite_block, ModBlocks.blue_opal_block, 2.7F, 1).save(this.output, locRestructure("benitoite_block"));
        restructureBlackResidue(ModItems.carnelian, ModItems.red_opal, 0.3F, 1).save(this.output, locRestructure("carnelian"));
        restructuringTektite(ModBlocks.carnelian_block, ModBlocks.red_opal_block, 2.7F, 1).save(this.output, locRestructure("carnelian_block"));
        restructureBlackResidue(ModItems.goshenite, ModItems.white_opal, 0.3F, 1).save(this.output, locRestructure("goshenite"));
        restructuringTektite(ModBlocks.goshenite_block, ModBlocks.white_opal_block, 2.7F, 1).save(this.output, locRestructure("goshenite_block"));
        restructureBlackResidue(ModItems.diopside, ModItems.green_opal, 0.3F, 1).save(this.output, locRestructure("diopside"));
        restructuringTektite(ModBlocks.diopside_block, ModBlocks.green_opal_block, 2.7F, 1).save(this.output, locRestructure("diopside_block"));
        restructureBlackResidue(ModItems.euclase, ModItems.labradorite, 0.3F, 1).save(this.output, locRestructure("euclase"));
        restructuringTektite(ModBlocks.euclase_block, ModBlocks.labradorite_block, 2.7F, 1).save(this.output, locRestructure("euclase_block"));
        restructureBlackResidue(ModItems.stibnite, ModItems.hematite, 0.3F, 1).save(this.output, locRestructure("stibnite"));
        restructuringTektite(ModBlocks.stibnite_block, ModBlocks.hematite_block, 2.7F, 1).save(this.output, locRestructure("stibnite_block"));
        restructureBlackResidue(ModItems.albite, ModItems.moonstone, 0.3F, 1).save(this.output, locRestructure("albite"));
        restructuringTektite(ModBlocks.albite_block, ModBlocks.moonstone_block, 2.7F, 1).save(this.output, locRestructure("albite_block"));
        restructureBlackResidue(ModItems.proustite, ModItems.cinnabar, 0.3F, 1).save(this.output, locRestructure("proustite"));
        restructuringTektite(ModBlocks.proustite_block, ModBlocks.cinnabar_block, 2.7F, 1).save(this.output, locRestructure("proustite_block"));
        restructuringItems(ModItems.aura_cluster.get(), ModItems.bismuth_crystal.get(), ModBlocks.pyrite_block, 2.7F, 1).save(this.output, locRestructure("aura_and_bismuth"));
        restructuringItems(ModItems.crystallized_lapis_lazuli.get(), ModItems.pink_essence.get(), ModItems.benitoite, 0.1F, 1).save(this.output, locRestructure("crystal_lapis"));
        restructuringItems(ModItems.crystallized_redstone.get(), ModItems.pink_essence.get(), ModItems.carnelian, 0.1F, 1).save(this.output, locRestructure("crystal_redstone"));
        restructuringItems(Items.DIAMOND, ModItems.pink_essence.get(), ModItems.bismuth_crystal, 0.1F, 1).save(this.output, locRestructure("diamond"));
        restructuringItems(Items.GOLD_INGOT, ModItems.pink_essence.get(), ModItems.scaynyx_ingot, 0.1F, 1).save(this.output, locRestructure("gold_ingot"));
        restructuringItems(ModItems.aura_residue.get(), ModItems.bismuth_residue.get(), ModItems.pyrite, 0.2F, 1).save(this.output, locRestructure("residues"));
        purifyingItems(ModBlocks.glitter_grass.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_grass, 0.3F, 1, 1).save(this.output, locPurify("glitter_grass"));
        purifyingItems(ModBlocks.heavy_soil.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_soil, 0.3F, 1, 1).save(this.output, locPurify("heavy_soil"));
        purifyingItems(ModBlocks.pink_agate_leaves.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_leaves, 0.3F, 1, 1).save(this.output, locPurify("pink_agate_leaves"));
        purifyingItems(ModBlocks.pink_agate_log.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_log, 0.3F, 1, 2).save(this.output, locPurify("pink_agate_log"));
        purifyingItems(ModBlocks.stripped_pink_agate_log.get(), ModItems.goldstone_residue.get(), ModBlocks.stripped_corrupted_log, 0.3F, 1, 2).save(this.output, locPurify("stripped_pink_agate_log"));
        purifyingItems(ModBlocks.pink_agate_wood.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_wood, 0.3F, 1, 2).save(this.output, locPurify("pink_agate_wood"));
        purifyingItems(ModBlocks.stripped_pink_agate_wood.get(), ModItems.goldstone_residue.get(), ModBlocks.stripped_corrupted_wood, 0.3F, 1, 2).save(this.output, locPurify("stripped_pink_agate_wood"));
        purifyingItems(ModBlocks.pink_agate_tiles.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tiles, 0.3F, 1, 1).save(this.output, locPurify("pink_agate_tiles"));
        purifyingItems(ModBlocks.pink_agate_tile_stairs.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tile_stairs, 0.3F, 1, 1).save(this.output, locPurify("pink_agate_tile_stairs"));
        purifyingItems(ModBlocks.pink_agate_tile_slab.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_tile_slab, 0.3F, 1, 1).save(this.output, locPurify("pink_agate_tile_slab"));
        purifyingItems(ModBlocks.pink_agate_sapling.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_sapling, 0.3F, 1, 1).save(this.output, locPurify("pink_agate_sapling"));
        purifyingItems(ModBlocks.varloom.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_varloom, 0.3F, 1, 1).save(this.output, "varloom");
    }

    private String loc(String name) {
        return ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name).toString();
    }

    private String locArmor(String name) {
        return loc("armor/" + name);
    }

    private String locStorage(String name) {
        return loc("storage_blocks/" + name);
    }

    private String locTools(String name) {
        return loc("tools/" + name);
    }

    private String locWood(String name) {
        return loc("wood/" + name);
    }

    private String locSmelt(String name) {
        return loc("smelting/" + name);
    }

    private String locRestructure(String name) {
        return loc("restructuring/" + name);
    }

    private String locPurify(String name) {
        return loc("purifying/" + name);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
            super(output, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new GaiaRecipes(provider, output);
        }

        @Override
        public String getName() {
            return "Gaia Dimension Recipes";
        }
    }
}
