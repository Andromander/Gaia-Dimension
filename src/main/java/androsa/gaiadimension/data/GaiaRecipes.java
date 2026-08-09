package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaRecipeProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class GaiaRecipes extends GaiaRecipeProvider {

    public GaiaRecipes(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override
    protected void buildRecipes() {
        planksRecipe(ModBlocks.pink_agate_tiles, GaiaTags.Items.PINK_AGATE_LOGS, this.output);
        planksRecipe(ModBlocks.blue_agate_tiles, GaiaTags.Items.BLUE_AGATE_LOGS, this.output);
        planksRecipe(ModBlocks.green_agate_tiles, GaiaTags.Items.GREEN_AGATE_LOGS, this.output);
        planksRecipe(ModBlocks.purple_agate_tiles, GaiaTags.Items.PURPLE_AGATE_LOGS, this.output);
        planksRecipe(ModBlocks.fossilized_tiles, GaiaTags.Items.FOSSILIZED_LOGS, this.output);
        planksRecipe(ModBlocks.corrupted_tiles, GaiaTags.Items.CORRUPTED_LOGS, this.output);
        planksRecipe(ModBlocks.burnt_tiles, GaiaTags.Items.BURNT_LOGS, this.output);
        planksRecipe(ModBlocks.fire_agate_tiles, GaiaTags.Items.BURNING_LOGS, this.output);
        planksRecipe(ModBlocks.aura_tiles, GaiaTags.Items.AURA_LOGS, this.output);
        planksRecipe(ModBlocks.golden_tiles, GaiaTags.Items.GOLDEN_LOGS, this.output);
        woodRecipe(slabRecipe(ModBlocks.pink_agate_tile_slab, ModBlocks.pink_agate_tiles), this.output, ModBlocks.pink_agate_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.blue_agate_tile_slab, ModBlocks.blue_agate_tiles), this.output, ModBlocks.blue_agate_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.green_agate_tile_slab, ModBlocks.green_agate_tiles), this.output, ModBlocks.green_agate_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.purple_agate_tile_slab, ModBlocks.purple_agate_tiles), this.output, ModBlocks.purple_agate_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.fossilized_tile_slab, ModBlocks.fossilized_tiles), this.output, ModBlocks.fossilized_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.corrupted_tile_slab, ModBlocks.corrupted_tiles), this.output, ModBlocks.corrupted_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.burnt_tile_slab, ModBlocks.burnt_tiles), this.output, ModBlocks.burnt_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.fire_agate_tile_slab, ModBlocks.fire_agate_tiles), this.output, ModBlocks.fire_agate_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.aura_tile_slab, ModBlocks.aura_tiles), this.output, ModBlocks.aura_tile_slab.get().asItem());
        woodRecipe(slabRecipe(ModBlocks.golden_tile_slab, ModBlocks.golden_tiles), this.output, ModBlocks.golden_tile_slab.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.pink_agate_tile_stairs, ModBlocks.pink_agate_tiles), this.output, ModBlocks.pink_agate_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.blue_agate_tile_stairs, ModBlocks.blue_agate_tiles), this.output, ModBlocks.blue_agate_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.green_agate_tile_stairs, ModBlocks.green_agate_tiles), this.output, ModBlocks.green_agate_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.purple_agate_tile_stairs, ModBlocks.purple_agate_tiles), this.output, ModBlocks.purple_agate_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.fossilized_tile_stairs, ModBlocks.fossilized_tiles), this.output, ModBlocks.fossilized_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.corrupted_tile_stairs, ModBlocks.corrupted_tiles), this.output, ModBlocks.corrupted_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.burnt_tile_stairs, ModBlocks.burnt_tiles), this.output, ModBlocks.burnt_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.fire_agate_tile_stairs, ModBlocks.fire_agate_tiles), this.output, ModBlocks.fire_agate_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.aura_tile_stairs, ModBlocks.aura_tiles), this.output, ModBlocks.aura_tile_stairs.get().asItem());
        woodRecipe(stairsRecipe(ModBlocks.golden_tile_stairs, ModBlocks.golden_tiles), this.output, ModBlocks.golden_tile_stairs.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.pink_agate_wood.get(), ModBlocks.pink_agate_log.get(), 3), this.output, ModBlocks.pink_agate_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.blue_agate_wood.get(), ModBlocks.blue_agate_log.get(), 3), this.output, ModBlocks.blue_agate_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.green_agate_wood.get(), ModBlocks.green_agate_log.get(), 3), this.output, ModBlocks.green_agate_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.purple_agate_wood.get(), ModBlocks.purple_agate_log.get(), 3), this.output, ModBlocks.purple_agate_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.fossilized_wood.get(), ModBlocks.fossilized_log.get(), 3), this.output, ModBlocks.fossilized_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.corrupted_wood.get(), ModBlocks.corrupted_log.get(), 3), this.output, ModBlocks.corrupted_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.burnt_wood.get(), ModBlocks.burnt_log.get(), 3), this.output, ModBlocks.burnt_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.fire_agate_wood.get(), ModBlocks.fire_agate_log.get(), 3), this.output, ModBlocks.fire_agate_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.aura_wood.get(), ModBlocks.aura_log.get(), 3), this.output, ModBlocks.aura_wood.get().asItem());
        woodRecipe(smallCompressRecipe(ModBlocks.golden_wood.get(), ModBlocks.golden_log.get(), 3), this.output, ModBlocks.golden_wood.get().asItem());
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
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.aura_curtain.get(), 2)
                .pattern("//")
                .pattern("##")
                .pattern("##")
                .define('/', ModItems.agate_stick)
                .define('#', ModItems.aura_rod.get())
                .unlockedBy("has_aura_rod", has(ModItems.aura_rod.get()))
                .save(this.output, loc("aura_curtain_rods"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.pink_agate_curtain.get(), 2)
                .pattern("//")
                .pattern("##")
                .pattern("##")
                .define('/', ModItems.agate_stick)
                .define('#', ModItems.glitter_rod.get())
                .unlockedBy("has_glitter_rod", has(ModItems.glitter_rod.get()))
                .save(this.output, loc("pink_agate_curtain_rods"));
        smallCompressRecipe(ModBlocks.smooth_pink_calcite.get(), ModBlocks.pink_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_red_calcite.get(), ModBlocks.red_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_blue_calcite.get(), ModBlocks.blue_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_light_blue_calcite.get(), ModBlocks.light_blue_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_green_calcite.get(), ModBlocks.green_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_purple_calcite.get(), ModBlocks.purple_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_beige_calcite.get(), ModBlocks.beige_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_yellow_calcite.get(), ModBlocks.yellow_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_black_calcite.get(), ModBlocks.black_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_orange_calcite.get(), ModBlocks.orange_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_navy_calcite.get(), ModBlocks.navy_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_gold_calcite.get(), ModBlocks.gold_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_white_calcite.get(), ModBlocks.white_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_clear_calcite.get(), ModBlocks.clear_calcite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_pink_aragonite.get(), ModBlocks.pink_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_blue_aragonite.get(), ModBlocks.blue_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_light_blue_aragonite.get(), ModBlocks.light_blue_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_green_aragonite.get(), ModBlocks.green_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_purple_aragonite.get(), ModBlocks.purple_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_tan_aragonite.get(), ModBlocks.tan_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_yellow_aragonite.get(), ModBlocks.yellow_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_black_aragonite.get(), ModBlocks.black_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_brown_aragonite.get(), ModBlocks.brown_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_white_aragonite.get(), ModBlocks.white_aragonite).save(this.output);
        smallCompressRecipe(ModBlocks.smooth_clear_aragonite.get(), ModBlocks.clear_aragonite).save(this.output);

        largeCompressRecipe(ModBlocks.scaynyx_block.get(), ModItems.scaynyx_ingot.get()).save(this.output);
        largeCompressRecipe(ModBlocks.sugilite_block.get(), ModItems.sugilite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.hematite_block.get(), ModItems.hematite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.cinnabar_block.get(), ModItems.cinnabar.get()).save(this.output);
        largeCompressRecipe(ModBlocks.labradorite_block.get(), ModItems.labradorite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.moonstone_block.get(), ModItems.moonstone.get()).save(this.output);
        largeCompressRecipe(ModBlocks.red_opal_block.get(), ModItems.red_opal.get()).save(this.output);
        largeCompressRecipe(ModBlocks.blue_opal_block.get(), ModItems.blue_opal.get()).save(this.output);
        largeCompressRecipe(ModBlocks.green_opal_block.get(), ModItems.green_opal.get()).save(this.output);
        largeCompressRecipe(ModBlocks.white_opal_block.get(), ModItems.white_opal.get()).save(this.output);
        largeCompressRecipe(ModBlocks.pyrite_block.get(), ModItems.pyrite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.tektite_block.get(), ModItems.tektite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.goldstone_block.get(), ModItems.goldstone.get()).save(this.output);
        largeCompressRecipe(ModBlocks.aura_block.get(), ModItems.aura_cluster.get()).save(this.output);
        largeCompressRecipe(ModBlocks.bismuth_block.get(), ModItems.bismuth_crystal.get()).save(this.output);
        largeCompressRecipe(ModBlocks.opalite_block.get(), ModItems.opalite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.stibnite_block.get(), ModItems.stibnite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.proustite_block.get(), ModItems.proustite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.euclase_block.get(), ModItems.euclase.get()).save(this.output);
        largeCompressRecipe(ModBlocks.albite_block.get(), ModItems.albite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.carnelian_block.get(), ModItems.carnelian.get()).save(this.output);
        largeCompressRecipe(ModBlocks.benitoite_block.get(), ModItems.benitoite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.diopside_block.get(), ModItems.diopside.get()).save(this.output);
        largeCompressRecipe(ModBlocks.goshenite_block.get(), ModItems.goshenite.get()).save(this.output);
        largeCompressRecipe(ModBlocks.celestine_block.get(), ModItems.celestine.get()).save(this.output);
        largeCompressRecipe(ModBlocks.magnetite_block.get(), ModItems.magnetite.get()).save(this.output);

        blockToItemRecipe(ModItems.scaynyx_ingot, ModBlocks.scaynyx_block, this.output, "scaynyx_block_item");
        blockToItemRecipe(ModItems.sugilite, ModBlocks.sugilite_block, this.output, "sugilite_block_item");
        blockToItemRecipe(ModItems.hematite, ModBlocks.hematite_block, this.output, "hematite_block_item");
        blockToItemRecipe(ModItems.cinnabar, ModBlocks.cinnabar_block, this.output, "cinnabar_block_item");
        blockToItemRecipe(ModItems.labradorite, ModBlocks.labradorite_block, this.output, "labradorite_block_item");
        blockToItemRecipe(ModItems.moonstone, ModBlocks.moonstone_block, this.output, "moonstone_block_item");
        blockToItemRecipe(ModItems.red_opal, ModBlocks.red_opal_block, this.output, "red_opal_block_item");
        blockToItemRecipe(ModItems.blue_opal, ModBlocks.blue_opal_block, this.output, "blue_opal_block_item");
        blockToItemRecipe(ModItems.green_opal, ModBlocks.green_opal_block, this.output, "green_opal_block_item");
        blockToItemRecipe(ModItems.white_opal, ModBlocks.white_opal_block, this.output, "white_opal_block_item");
        blockToItemRecipe(ModItems.stibnite, ModBlocks.stibnite_block, this.output, "stibnite_block_item");
        blockToItemRecipe(ModItems.proustite, ModBlocks.proustite_block, this.output, "proustite_block_item");
        blockToItemRecipe(ModItems.euclase, ModBlocks.euclase_block, this.output, "euclase_block_item");
        blockToItemRecipe(ModItems.albite, ModBlocks.albite_block, this.output, "albite_block_item");
        blockToItemRecipe(ModItems.carnelian, ModBlocks.carnelian_block, this.output, "carnelian_block_item");
        blockToItemRecipe(ModItems.benitoite, ModBlocks.benitoite_block, this.output, "benitoite_block_item");
        blockToItemRecipe(ModItems.diopside, ModBlocks.diopside_block, this.output, "diopside_block_item");
        blockToItemRecipe(ModItems.goshenite, ModBlocks.goshenite_block, this.output, "goshenite_block_item");
        blockToItemRecipe(ModItems.pyrite, ModBlocks.pyrite_block, this.output, "pyrite_block_item");
        blockToItemRecipe(ModItems.tektite, ModBlocks.tektite_block, this.output, "tektite_block_item");
        blockToItemRecipe(ModItems.goldstone, ModBlocks.goldstone_block, this.output, "goldstone_block_item");
        blockToItemRecipe(ModItems.aura_cluster, ModBlocks.aura_block, this.output, "aura_cluster_block_item");
        blockToItemRecipe(ModItems.bismuth_crystal, ModBlocks.bismuth_block, this.output, "bismuth_crystal_block_item");
        blockToItemRecipe(ModItems.opalite, ModBlocks.opalite_block, this.output, "opalite_block_item");
        blockToItemRecipe(ModItems.celestine, ModBlocks.celestine_block, this.output, "celestine_block_item");
        blockToItemRecipe(ModItems.magnetite, ModBlocks.magnetite_block, this.output, "magnetite_block_item");

        helmetRecipe(ModItems.sugilite_helmet, ModItems.sugilite, this.output);
        chestRecipe(ModItems.sugilite_chestplate, ModItems.sugilite, this.output);
        legsRecipe(ModItems.sugilite_legs, ModItems.sugilite, this.output);
        bootsRecipe(ModItems.sugilite_boots, ModItems.sugilite, this.output);
        helmetRecipe(ModItems.proustite_helmet, ModItems.proustite, this.output);
        chestRecipe(ModItems.proustite_chestplate, ModItems.proustite, this.output);
        legsRecipe(ModItems.proustite_legs, ModItems.proustite, this.output);
        bootsRecipe(ModItems.proustite_boots, ModItems.proustite, this.output);
        helmetRecipe(ModItems.albite_helmet, ModItems.albite, this.output);
        chestRecipe(ModItems.albite_chestplate, ModItems.albite, this.output);
        legsRecipe(ModItems.albite_legs, ModItems.albite, this.output);
        bootsRecipe(ModItems.albite_boots, ModItems.albite, this.output);
        helmetRecipe(ModItems.carnelian_helmet, ModItems.carnelian, this.output);
        chestRecipe(ModItems.carnelian_chestplate, ModItems.carnelian, this.output);
        legsRecipe(ModItems.carnelian_legs, ModItems.carnelian, this.output);
        bootsRecipe(ModItems.carnelian_boots, ModItems.carnelian, this.output);
        helmetRecipe(ModItems.diopside_helmet, ModItems.diopside, this.output);
        chestRecipe(ModItems.diopside_chestplate, ModItems.diopside, this.output);
        legsRecipe(ModItems.diopside_legs, ModItems.diopside, this.output);
        bootsRecipe(ModItems.diopside_boots, ModItems.diopside, this.output);
        helmetRecipe(ModItems.goshenite_helmet, ModItems.goshenite, this.output);
        chestRecipe(ModItems.goshenite_chestplate, ModItems.goshenite, this.output);
        legsRecipe(ModItems.goshenite_legs, ModItems.goshenite, this.output);
        bootsRecipe(ModItems.goshenite_boots, ModItems.goshenite, this.output);

        swordRecipeTag(ModItems.agate_sword, GaiaTags.Items.TILES, this.output);
        pickaxeRecipeTag(ModItems.agate_pickaxe, GaiaTags.Items.TILES, this.output);
        axeRecipeTag(ModItems.agate_axe, GaiaTags.Items.TILES, this.output);
        shovelRecipeTag(ModItems.agate_shovel, GaiaTags.Items.TILES, this.output);
        swordRecipe(ModItems.sugilite_sword, ModItems.sugilite, this.output);
        pickaxeRecipe(ModItems.sugilite_pickaxe, ModItems.sugilite, this.output);
        axeRecipe(ModItems.sugilite_axe, ModItems.sugilite, this.output);
        shovelRecipe(ModItems.sugilite_shovel, ModItems.sugilite, this.output);
        swordRecipe(ModItems.stibnite_sword, ModItems.stibnite, this.output);
        pickaxeRecipe(ModItems.stibnite_pickaxe, ModItems.stibnite, this.output);
        axeRecipe(ModItems.stibnite_axe, ModItems.stibnite, this.output);
        shovelRecipe(ModItems.stibnite_shovel, ModItems.stibnite, this.output);
        swordRecipe(ModItems.euclase_sword, ModItems.euclase, this.output);
        pickaxeRecipe(ModItems.euclase_pickaxe, ModItems.euclase, this.output);
        axeRecipe(ModItems.euclase_axe, ModItems.euclase, this.output);
        shovelRecipe(ModItems.euclase_shovel, ModItems.euclase, this.output);
        swordRecipe(ModItems.carnelian_sword, ModItems.carnelian, this.output);
        pickaxeRecipe(ModItems.carnelian_pickaxe, ModItems.carnelian, this.output);
        axeRecipe(ModItems.carnelian_axe, ModItems.carnelian, this.output);
        shovelRecipe(ModItems.carnelian_shovel, ModItems.carnelian, this.output);
        swordRecipe(ModItems.benitoite_sword, ModItems.benitoite, this.output);
        pickaxeRecipe(ModItems.benitoite_pickaxe, ModItems.benitoite, this.output);
        axeRecipe(ModItems.benitoite_axe, ModItems.benitoite, this.output);
        shovelRecipe(ModItems.benitoite_shovel, ModItems.benitoite, this.output);
        swordRecipe(ModItems.goshenite_sword, ModItems.goshenite, this.output);
        pickaxeRecipe(ModItems.goshenite_pickaxe, ModItems.goshenite, this.output);
        axeRecipe(ModItems.goshenite_axe, ModItems.goshenite, this.output);
        shovelRecipe(ModItems.goshenite_shovel, ModItems.goshenite, this.output);

        largeCompressRecipe(ModItems.aura_cluster.get(), ModItems.aura_residue.get()).save(this.output);
        largeCompressRecipe(ModItems.bismuth_crystal.get(), ModItems.bismuth_residue.get()).save(this.output);
        largeCompressRecipe(ModItems.magnetite.get(), ModItems.metallic_fragment.get()).save(this.output);
        drinkRecipe(ModItems.pink_geode_juice, ModItems.pink_geode_slice).save(this.output);
        drinkRecipe(ModItems.blue_geode_tea, ModItems.blue_geode_slice).save(this.output);
        drinkRecipe(ModItems.green_geode_ale, ModItems.green_geode_slice).save(this.output);
        drinkRecipe(ModItems.purple_geode_soda, ModItems.purple_geode_slice).save(this.output);
        sliceRecipe(ModItems.pink_geode_slice, ModItems.pink_geode).save(this.output);
        sliceRecipe(ModItems.blue_geode_slice, ModItems.blue_geode).save(this.output);
        sliceRecipe(ModItems.green_geode_slice, ModItems.green_geode).save(this.output);
        sliceRecipe(ModItems.purple_geode_slice, ModItems.purple_geode).save(this.output);
        largeCompressRecipe(ModBlocks.cloudy_glass.get(), ModItems.cloudy_shard.get()).save(this.output);
        smallCompressRecipe(ModBlocks.gaia_stone_bricks.get(), ModBlocks.gaia_stone.get(), 4).save(this.output);
        crustBricks(ModBlocks.crusted_gaia_stone_bricks, ModBlocks.gaia_stone_bricks).save(this.output);
        smallCompressRecipe(ModBlocks.jade_bricks.get(), ModBlocks.raw_jade.get(), 4).save(this.output);
        crustBricks(ModBlocks.crusted_jade_bricks, ModBlocks.jade_bricks).save(this.output);
        slabRecipe(ModBlocks.jade_brick_slab, ModBlocks.jade_bricks).save(this.output);
        stairsRecipe(ModBlocks.jade_brick_stairs, ModBlocks.jade_bricks).save(this.output);
        smallCompressRecipe(ModBlocks.copal_bricks.get(), ModBlocks.raw_copal.get(), 4).save(this.output);
        crustBricks(ModBlocks.crusted_copal_bricks, ModBlocks.copal_bricks).save(this.output);
        slabRecipe(ModBlocks.copal_brick_slab, ModBlocks.copal_bricks).save(this.output);
        stairsRecipe(ModBlocks.copal_brick_stairs, ModBlocks.copal_bricks).save(this.output);
        smallCompressRecipe(ModBlocks.jet_bricks.get(), ModBlocks.raw_jet.get(), 4).save(this.output);
        crustBricks(ModBlocks.crusted_jet_bricks, ModBlocks.jet_bricks).save(this.output);
        slabRecipe(ModBlocks.jet_brick_slab, ModBlocks.jet_bricks).save(this.output);
        stairsRecipe(ModBlocks.jet_brick_stairs, ModBlocks.jet_bricks).save(this.output);
        smallCompressRecipe(ModBlocks.amethyst_bricks.get(), ModBlocks.raw_amethyst.get(), 4).save(this.output);
        crustBricks(ModBlocks.crusted_amethyst_bricks, ModBlocks.amethyst_bricks).save(this.output);
        slabRecipe(ModBlocks.amethyst_brick_slab, ModBlocks.amethyst_bricks).save(this.output);
        stairsRecipe(ModBlocks.amethyst_brick_stairs, ModBlocks.amethyst_bricks).save(this.output);
        largeCompressRecipe(ModItems.goldstone.get(), ModItems.goldstone_residue.get()).save(this.output);
        smallCompressRecipe(ModBlocks.gummy_glitter_block.get(), ModItems.sweet_muckball.get()).save(this.output);
        largeCompressRecipe(ModItems.pink_goo.get(), ModItems.pink_essence.get()).save(this.output);
        largeCompressRecipe(ModBlocks.pink_sludge_block.get(), ModItems.pink_goo.get()).save(this.output);
        tiliRecipe(ModItems.tilipi, ModBlocks.spotted_kersei).save(this.output);
        tiliRecipe(ModItems.tilibl, ModBlocks.thorny_wiltha).save(this.output);
        tiliRecipe(ModItems.tiligr, ModBlocks.roofed_agaric).save(this.output);
        tiliRecipe(ModItems.tilipu, ModBlocks.bulbous_hobina).save(this.output);
        tiliRecipe(ModItems.tiliol, ModBlocks.stickly_cupsir).save(this.output);
        tiliRecipe(ModItems.tilimy, ModBlocks.mystical_murgni).save(this.output);
        tiliRecipe(ModItems.plagued_tiliey, ModBlocks.corrupted_varloom).save(this.output);
        tiliRecipe(ModItems.tiliou, ModBlocks.ouzium).save(this.output);
        smallCompressRecipe(ModBlocks.saltstone.get(), ModBlocks.salt.get()).save(this.output);
        smallCompressRecipe(ModItems.sugar_cluster.get(), ModItems.sugar_crystals.get()).save(this.output);
        largeCompressRecipe(ModItems.tektite.get(), ModItems.black_residue.get()).save(this.output);
        crustBricks(ModBlocks.malachite_crusted_bricks, ModBlocks.malachite_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_brick_stairs, ModBlocks.malachite_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_cracked_brick_stairs, ModBlocks.malachite_cracked_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_crusted_brick_stairs, ModBlocks.malachite_crusted_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_chisel_stairs, ModBlocks.malachite_chisel_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_tile_stairs, ModBlocks.malachite_tiles).save(this.output);
        stairsRecipe(ModBlocks.malachite_pillar_stairs, ModBlocks.malachite_pillar).save(this.output);
        stairsRecipe(ModBlocks.malachite_pulsing_brick_stairs, ModBlocks.malachite_pulsing_bricks).save(this.output);
        stairsRecipe(ModBlocks.malachite_pulsing_floor_stairs, ModBlocks.malachite_pulsing_tiles).save(this.output);
        stairsRecipe(ModBlocks.malachite_pulsing_chisel_stairs, ModBlocks.malachite_pulsing_chisel).save(this.output);
        slabRecipe(ModBlocks.malachite_brick_slab, ModBlocks.malachite_bricks).save(this.output);
        slabRecipe(ModBlocks.malachite_cracked_brick_slab, ModBlocks.malachite_cracked_bricks).save(this.output);
        slabRecipe(ModBlocks.malachite_crusted_brick_slab, ModBlocks.malachite_crusted_bricks).save(this.output);
        slabRecipe(ModBlocks.malachite_tile_slab, ModBlocks.malachite_tiles).save(this.output);

        this.shaped(RecipeCategory.COMBAT, ModItems.agate_arrow.get(), 4)
                .pattern("#")
                .pattern("/")
                .pattern("%")
                .define('#', ModItems.sturdy_pebble.get())
                .define('/', ModItems.agate_stick.get())
                .define('%', ModItems.agate_fabric.get())
                .unlockedBy("has_pebble", has(ModItems.sturdy_pebble.get()))
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModItems.agate_cup.get(), 8)
                .pattern("# #")
                .pattern(" # ")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModItems.agate_stick.get(), 4)
                .pattern("#")
                .pattern("#")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModItems.aura_rod.get(), 4)
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.aura_shoot)
                .unlockedBy("has_aura_shoot", has(ModBlocks.aura_shoot))
                .save(this.output);
        this.shaped(RecipeCategory.MISC, ModItems.magnetite_rod.get(), 4)
                .pattern("#")
                .pattern("#")
                .define('#', ModItems.magnetite)
                .unlockedBy("has_magnetite", has(ModItems.magnetite))
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.bolstered_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', ModBlocks.reinforced_bricks.get())
                .define('%', ModBlocks.goldstone_block.get())
                .unlockedBy("has_brick", has(ModBlocks.reinforced_bricks.get()))
                .unlockedBy("has_goldstone", has(ModBlocks.goldstone_block.get()))
                .save(this.output);
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
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.corrupted_soil.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', ModBlocks.heavy_soil.get())
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.corrupted_sapling.get())
                .pattern(" / ")
                .pattern("/#/")
                .pattern(" / ")
                .define('/', ModItems.goldstone_residue.get())
                .define('#', Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()))
                .unlockedBy("has_residue", has(ModItems.goldstone_residue.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.agate_crafting_table.get())
                .pattern("##")
                .pattern("##")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output);
        this.shapeless(RecipeCategory.FOOD, ModItems.pearly_geode_elixir.get())
                .requires(ModItems.pink_geode_slice.get())
                .requires(ModItems.blue_geode_slice.get())
                .requires(ModItems.green_geode_slice.get())
                .requires(ModItems.purple_geode_slice.get())
                .requires(ModItems.sugar_cluster.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.frail_glitter_block.get(), 4)
                .requires(ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_glitter", has(ModBlocks.frail_glitter_block.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.gaia_stone_furnace.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', ModBlocks.gaia_cobblestone.get())
                .unlockedBy("has_stone", has(ModBlocks.gaia_stone.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.TOOLS, ModItems.gemstone_pouch.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.agate_fabric.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_fabric", has(ModItems.agate_fabric.get()))
                .save(this.output);
        this.shapeless(RecipeCategory.TOOLS, ModItems.glint_and_gold.get())
                .requires(Items.DIAMOND)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.keystone_block.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', ModItems.crystallized_lapis_lazuli.get())
                .define('%', ModItems.crystallized_redstone.get())
                .define('#', Items.GOLD_INGOT)
                .unlockedBy("has_lapis", has(ModItems.crystallized_lapis_lazuli.get()))
                .unlockedBy("has_redstone", has(ModItems.crystallized_redstone.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.mega_storage_crate.get())
                .pattern("*%*")
                .pattern("%#%")
                .pattern("*%*")
                .define('*', GaiaTags.Items.TILES)
                .define('%', ModBlocks.thick_glitter_block.get())
                .define('#', ModBlocks.crude_storage_crate.get())
                .unlockedBy("has_crate", has(ModBlocks.crude_storage_crate.get()))
                .save(this.output);
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
                .save(this.output, loc("tools/old_bow"));
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.purifier.get())
                .pattern("///")
                .pattern("/#/")
                .pattern("///")
                .define('/', ModBlocks.reinforced_bricks.get())
                .define('#', ModBlocks.restructurer.get())
                .unlockedBy("has_bricks", has(ModBlocks.reinforced_bricks.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModItems.PYRITE_TORCH.get(), 4)
                .pattern("#")
                .pattern("/")
                .define('#', ModItems.pyrite.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_pyrite", has(ModItems.pyrite.get()))
                .save(this.output);
        smallCompressRecipe(ModBlocks.frail_glitter_block.get(), ModItems.glitter_dust).save(this.output, loc("frail_glitter_block_dust"));
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.reinforced_bricks.get(), 2)
                .pattern("%#")
                .pattern("#%")
                .define('#', GaiaTags.Items.GAIA_BRICKS)
                .define('%', ModBlocks.thick_glitter_block.get())
                .unlockedBy("has_brick", has(GaiaTags.Items.GAIA_BRICKS))
                .unlockedBy("has_goldstone", has(ModBlocks.thick_glitter_block.get()))
                .save(this.output);
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
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ModItems.scaynyx_bucket.get())
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.scaynyx_ingot.get())
                .unlockedBy("has_ingot", has(ModItems.scaynyx_ingot.get()))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.crude_storage_crate.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .define('#', GaiaTags.Items.TILES)
                .unlockedBy("has_tiles", has(GaiaTags.Items.TILES))
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.twined_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .requires(ModItems.fine_thread.get())
                .unlockedBy("has_thread", has(ModItems.fine_thread.get()))
                .save(this.output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.malachite_chisel_bricks.get())
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.malachite_brick_slab.get())
                .unlockedBy("has_malachite_slab", has(ModBlocks.malachite_brick_slab.get()))
                .save(this.output);
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
                .save(this.output);
        this.shapeless(RecipeCategory.MISC, ModItems.construct_charm.get())
                .requires(ModItems.celestine.get())
                .requires(GaiaTags.Items.MOOKAITE)
                .requires(ModItems.opalite.get())
                .unlockedBy("has_celestine", has(ModItems.celestine.get()))
                .save(this.output);
        repairKit().save(output);
        augmentKit(ModItems.scarlet_augment_kit, ModBlocks.scarlet_mookaite).save(output);
        augmentKit(ModItems.auburn_augment_kit, ModBlocks.auburn_mookaite).save(output);
        augmentKit(ModItems.gold_augment_kit, ModBlocks.gold_mookaite).save(output);
        augmentKit(ModItems.mauve_augment_kit, ModBlocks.mauve_mookaite).save(output);
        augmentKit(ModItems.beige_augment_kit, ModBlocks.beige_mookaite).save(output);
        augmentKit(ModItems.ivory_augment_kit, ModBlocks.ivory_mookaite).save(output);
        replaceKit(ModItems.scarlet_replace_kit, ModBlocks.scarlet_mookaite).save(output);
        replaceKit(ModItems.auburn_replace_kit, ModBlocks.auburn_mookaite).save(output);
        replaceKit(ModItems.gold_replace_kit, ModBlocks.gold_mookaite).save(output);
        replaceKit(ModItems.mauve_replace_kit, ModBlocks.mauve_mookaite).save(output);
        replaceKit(ModItems.beige_replace_kit, ModBlocks.beige_mookaite).save(output);
        replaceKit(ModItems.ivory_replace_kit, ModBlocks.ivory_mookaite).save(output);
        this.shaped(RecipeCategory.TOOLS, ModItems.magic_staff.get())
                .pattern("#")
                .pattern("^")
                .pattern("/")
                .define('#', ModItems.crystal_core)
                .define('^', ModItems.sugilite)
                .define('/', ModItems.agate_stick)
                .unlockedBy("has_crystal_core", has(ModItems.crystal_core))
                .save(this.output);
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.augmenter)
                .pattern("^/^")
                .pattern("###")
                .define('#', ModBlocks.gaia_stone_bricks)
                .define('^', ModItems.sugilite)
                .define('/', ModItems.scaynyx_ingot)
                .unlockedBy("has_scaynyx_ingot", has(ModItems.scaynyx_ingot))
                .save(this.output);


        smeltingRecipe(ModItems.blue_opal.get(), CookingBookCategory.MISC, ModBlocks.blue_opal_ore, 0.3F).save(this.output, locSmelt("blue_opal"));
        smeltingRecipe(ModItems.celestine.get(), CookingBookCategory.MISC, ModBlocks.celestine_ore, 1.5F).save(this.output, locSmelt("celestine"));
        smeltingRecipe(ModBlocks.fire_agate_sapling.get(), CookingBookCategory.BLOCKS, ModBlocks.burnt_sapling, 0.1F).save(this.output, locSmelt("fire_agate_sapling"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.pink_agate_sapling.get(), ModBlocks.blue_agate_sapling.get(), ModBlocks.green_agate_sapling.get(), ModBlocks.purple_agate_sapling.get()), RecipeCategory.DECORATIONS, CookingBookCategory.BLOCKS, ModBlocks.burnt_sapling.get(), 0.1F, 200)
                .unlockedBy("has_sapling", has(ModBlocks.pink_agate_sapling.get()))
                .save(this.output, locSmelt("burnt_agate_sapling"));
        smeltingRecipe(ModItems.cinnabar.get(), CookingBookCategory.MISC, ModBlocks.cinnabar_ore, 0.3F).save(this.output, locSmelt("cinnabar"));
        smeltingRecipe(ModItems.cloudy_shard.get(), CookingBookCategory.MISC, ModItems.fine_dust, 0.1F).save(this.output, locSmelt("cloudy_shard"));
        smeltingRecipe(ModItems.cooked_luggeroth_chop.get(), CookingBookCategory.MISC, ModItems.luggeroth_chop, 0.2F).save(this.output, locSmelt("cooked_luggeroth_chop"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.LAPIS_LAZULI), RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.crystallized_lapis_lazuli.get(), 0.25F, 200)
                .unlockedBy("has_lapis", has(Items.LAPIS_LAZULI))
                .save(this.output, locSmelt("crystal_lapis"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.REDSTONE), RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.crystallized_redstone.get(), 0.25F, 200)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(this.output, locSmelt("crystal_redstone"));
        smeltingRecipe(ModBlocks.foggy_glass.get(), CookingBookCategory.BLOCKS, ModBlocks.salt, 0.1F).save(this.output, locSmelt("foggy_glass"));
        smeltingRecipe(ModBlocks.gaia_stone.get(), CookingBookCategory.BLOCKS, ModBlocks.gaia_cobblestone, 0.1F).save(this.output, locSmelt("gaia_stone"));
        smeltingRecipe(ModItems.goldstone_residue.get(), CookingBookCategory.MISC, ModItems.goldstone_dust, 0.1F).save(this.output, locSmelt("golstone_residue"));
        smeltingRecipe(ModItems.green_opal.get(), CookingBookCategory.MISC, ModBlocks.green_opal_ore, 0.3F).save(this.output, locSmelt("green_opal"));
        smeltingRecipe(ModItems.hematite.get(), CookingBookCategory.MISC, ModBlocks.hematite_ore, 0.3F).save(this.output, locSmelt("hematite"));
        smeltingRecipe(ModItems.labradorite.get(), CookingBookCategory.MISC, ModBlocks.labradorite_ore, 0.3F).save(this.output, locSmelt("labradorite"));
        smeltingRecipe(ModItems.large_calamari.get(), CookingBookCategory.FOOD, ModItems.large_tentacle, 0.2F).save(this.output, locSmelt("large_calamari"));
        smeltingRecipe(ModItems.lurmorus_steak.get(), CookingBookCategory.FOOD, ModItems.lurmorus_meat, 0.2F).save(this.output, locSmelt("lurmorus_steak"));
        smeltingRecipe(ModItems.moonstone.get(), CookingBookCategory.MISC, ModBlocks.moonstone_ore, 0.3F).save(this.output, locSmelt("moonstone"));
        smeltingRecipe(ModItems.pink_essence.get(), CookingBookCategory.MISC, ModBlocks.gaia_stone, 0.1F).save(this.output, locSmelt("pink_essence"));
        smeltingRecipe(ModItems.pyrite.get(), CookingBookCategory.MISC, ModBlocks.pyrite_ore, 0.3F).save(this.output, locSmelt("pyrite"));
        smeltingRecipe(ModItems.red_opal.get(), CookingBookCategory.MISC, ModBlocks.red_opal_ore, 0.3F).save(this.output, locSmelt("red_opal"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), CookingBookCategory.MISC, ModBlocks.precious_rock, 0.8F, 4).save(this.output, locSmelt("scaynyx_large"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), CookingBookCategory.MISC, ModBlocks.coarse_rock, 0.4F, 2).save(this.output, locSmelt("scaynyx_medium"));
        smeltingRecipe(ModItems.scaynyx_ingot.get(), CookingBookCategory.MISC, ModBlocks.speckled_rock, 0.2F).save(this.output, locSmelt("scaynyx_small"));
        smeltingRecipe(ModItems.small_calamari.get(), CookingBookCategory.FOOD, ModItems.small_tentacle, 0.2F).save(this.output, locSmelt("small_calamari"));
        smeltingRecipe(ModItems.sugilite.get(), CookingBookCategory.MISC, ModBlocks.sugilite_ore, 0.3F).save(this.output, locSmelt("sugilite"));
        smeltingRecipe(ModBlocks.thick_glitter_block.get(), CookingBookCategory.BLOCKS, ModBlocks.gummy_glitter_block, 0.1F).save(this.output, locSmelt("thick_glitter_block"));
        smeltingRecipe(ModItems.white_opal.get(), CookingBookCategory.MISC, ModBlocks.white_opal_ore, 1.0F).save(this.output, locSmelt("white_opal"));
        smeltingRecipe(ModItems.glitter_rod.get(), CookingBookCategory.MISC, ModItems.glitter_dust, 0.0F).save(this.output, locSmelt("glitter_dust"));
        smeltingRecipe(ModItems.magnetite.get(), CookingBookCategory.MISC, ModBlocks.static_stone, 0.0F).save(this.output, locSmelt("magnetite_from_static_stone"));
        smeltingRecipe(ModItems.magnetite.get(), CookingBookCategory.MISC, ModBlocks.charged_mineral, 0.0F, 2).save(this.output, locSmelt("magnetite_from_charged_mineral"));
        smeltingRecipe(ModBlocks.clear_calcite.get(), CookingBookCategory.BLOCKS, ModBlocks.white_calcite, 0.0F).save(this.output, locSmelt("clear_calcite"));
        smeltingRecipe(ModBlocks.clear_aragonite.get(), CookingBookCategory.BLOCKS, ModBlocks.clear_aragonite, 0.0F).save(this.output, locSmelt("clear_aragonite"));

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
        restructuringCalcite(ModBlocks.pink_calcite, ModBlocks.spotted_kersei).save(this.output, locRestructure("spotted_kersei_pink_calcite"));
        restructuringCalcite(ModBlocks.red_calcite, ModBlocks.spotted_kersei).save(this.output, locRestructure("spotted_kersei_red_calcite"));
        restructuringCalcite(ModBlocks.blue_calcite, ModBlocks.thorny_wiltha).save(this.output, locRestructure("thorny_wiltha_blue_calcite"));
        restructuringCalcite(ModBlocks.light_blue_calcite, ModBlocks.thorny_wiltha).save(this.output, locRestructure("thorny_wiltha_light_blue_calcite"));
        restructuringCalcite(ModBlocks.green_calcite, ModBlocks.roofed_agaric).save(this.output, locRestructure("roofed_agaric_green_calcite"));
        restructuringCalcite(ModBlocks.purple_calcite, ModBlocks.bulbous_hobina).save(this.output, locRestructure("bulbous_hobina_purple_calcite"));
        restructuringCalcite(ModBlocks.beige_calcite, ModBlocks.stickly_cupsir).save(this.output, locRestructure("stickly_cupsir_beige_calcite"));
        restructuringCalcite(ModBlocks.yellow_calcite, ModBlocks.mystical_murgni).save(this.output, locRestructure("mystical_murgni_yellow_calcite"));
        restructuringCalcite(ModBlocks.black_calcite, ModBlocks.corrupted_gaia_eye).save(this.output, locRestructure("corrupted_gaia_eye_black_calcite"));
        restructuringCalcite(ModBlocks.navy_calcite, ModBlocks.corrupted_gaia_eye).save(this.output, locRestructure("corrupted_gaia_eye_navy_calcite"));
        restructuringCalcite(ModBlocks.orange_calcite, ModBlocks.corrupted_gaia_eye).save(this.output, locRestructure("corrupted_gaia_eye_orange_calcite"));
        restructuringCalcite(ModBlocks.gold_calcite, ModBlocks.twinkling_gilsri).save(this.output, locRestructure("twinkling_gilsri_gold_calcite"));
        restructuringAragonite(ModBlocks.pink_aragonite, ModBlocks.spotted_kersei).save(this.output, locRestructure("spotted_kersei_pink_aragonite"));
        restructuringAragonite(ModBlocks.blue_aragonite, ModBlocks.thorny_wiltha).save(this.output, locRestructure("thorny_wiltha_blue_aragonite"));
        restructuringAragonite(ModBlocks.light_blue_aragonite, ModBlocks.thorny_wiltha).save(this.output, locRestructure("thorny_wiltha_light_blue_aragonite"));
        restructuringAragonite(ModBlocks.green_aragonite, ModBlocks.roofed_agaric).save(this.output, locRestructure("roofed_agaric_green_aragonite"));
        restructuringAragonite(ModBlocks.purple_aragonite, ModBlocks.bulbous_hobina).save(this.output, locRestructure("bulbous_hobina_purple_aragonite"));
        restructuringAragonite(ModBlocks.tan_aragonite, ModBlocks.stickly_cupsir).save(this.output, locRestructure("stickly_cupsir_tan_aragonite"));
        restructuringAragonite(ModBlocks.yellow_aragonite, ModBlocks.mystical_murgni).save(this.output, locRestructure("mystical_murgni_yellow_aragonite"));
        restructuringAragonite(ModBlocks.black_aragonite, ModBlocks.corrupted_gaia_eye).save(this.output, locRestructure("corrupted_gaia_eye_black_aragonite"));
        restructuringAragonite(ModBlocks.brown_aragonite, ModBlocks.twinkling_gilsri).save(this.output, locRestructure("twinkling_gilsri_brown_aragonite"));
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
        purifyingItems(ModBlocks.varloom.get(), ModItems.goldstone_residue.get(), ModBlocks.corrupted_varloom, 0.3F, 1, 1).save(this.output, locPurify("varloom"));
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
