package androsa.gaiadimension.registry;

import androsa.gaiadimension.recipe.RecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class GDRecipes {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.pink_agate_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.blue_agate_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.green_agate_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.purple_agate_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.fossilized_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.corrupted_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.burnt_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.burning_planks, 1));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.aura_planks, 1));
        if (GDConfig.oreDict.sugilite) {
            OreDictionary.registerOre("oreSugilite", new ItemStack(GDBlocks.sugilite_ore, 1));
            OreDictionary.registerOre("gemSugilite", new ItemStack(GDItems.sugilite, 1));
            OreDictionary.registerOre("blockSugilite", new ItemStack(GDBlocks.sugilite_block, 1));
        }
        if (GDConfig.oreDict.hematite) {
            OreDictionary.registerOre("oreHematite", new ItemStack(GDBlocks.hematite_ore, 1));
            OreDictionary.registerOre("gemHematite", new ItemStack(GDItems.hematite, 1));
            OreDictionary.registerOre("blockHematite", new ItemStack(GDBlocks.hematite_block, 1));
        }
        if (GDConfig.oreDict.pyrite) {
            OreDictionary.registerOre("orePyrite", new ItemStack(GDBlocks.pyrite_ore, 1));
            OreDictionary.registerOre("gemPyrite", new ItemStack(GDItems.pyrite, 1));
            OreDictionary.registerOre("blockPyrite", new ItemStack(GDBlocks.pyrite_block, 1));
        }
        if (GDConfig.oreDict.labradorite) {
            OreDictionary.registerOre("oreLabradorite", new ItemStack(GDBlocks.labradorite_ore, 1));
            OreDictionary.registerOre("gemLabradorite", new ItemStack(GDItems.labradorite, 1));
            OreDictionary.registerOre("blockLabradorite", new ItemStack(GDBlocks.labradorite_block, 1));
        }
        if (GDConfig.oreDict.moonstone) {
            OreDictionary.registerOre("oreMoonstone", new ItemStack(GDBlocks.moonstone_ore, 1));
            OreDictionary.registerOre("gemMoonstone", new ItemStack(GDItems.moonstone, 1));
            OreDictionary.registerOre("blockMoonstone", new ItemStack(GDBlocks.moonstone_block, 1));
        }
        if (GDConfig.oreDict.cinnabar) {
            OreDictionary.registerOre("oreCinnabar", new ItemStack(GDBlocks.cinnabar_ore, 1));
            OreDictionary.registerOre("gemCinnabar", new ItemStack(GDItems.cinnabar, 1));
            OreDictionary.registerOre("blockCinnabar", new ItemStack(GDBlocks.cinnabar_block, 1));
        }
        if (GDConfig.oreDict.red_opal) {
            OreDictionary.registerOre("oreRedOpal", new ItemStack(GDBlocks.opal_block_red, 1));
            OreDictionary.registerOre("gemRedOpal", new ItemStack(GDItems.red_opal, 1));
            OreDictionary.registerOre("blockRedOpal", new ItemStack(GDBlocks.opal_ore_red, 1));
        }
        if (GDConfig.oreDict.blue_opal) {
            OreDictionary.registerOre("oreBlueOpal", new ItemStack(GDBlocks.opal_block_blue, 1));
            OreDictionary.registerOre("gemBlueOpal", new ItemStack(GDItems.blue_opal, 1));
            OreDictionary.registerOre("blockBlueOpal", new ItemStack(GDBlocks.opal_ore_blue, 1));
        }
        if (GDConfig.oreDict.green_opal) {
            OreDictionary.registerOre("oreGreenOpal", new ItemStack(GDBlocks.opal_block_green, 1));
            OreDictionary.registerOre("gemGreenOpal", new ItemStack(GDItems.green_opal, 1));
            OreDictionary.registerOre("blockGreenOpal", new ItemStack(GDBlocks.opal_ore_green, 1));
        }
        if (GDConfig.oreDict.white_opal) {
            OreDictionary.registerOre("oreWhiteOpal", new ItemStack(GDBlocks.opal_block_white, 1));
            OreDictionary.registerOre("gemWhiteOpal", new ItemStack(GDItems.white_opal, 1));
            OreDictionary.registerOre("blockWhiteOpal", new ItemStack(GDBlocks.opal_ore_white, 1));
        }
        if (GDConfig.oreDict.ixiolite) {
            OreDictionary.registerOre("gemIxiolite", new ItemStack(GDItems.ixiolite, 1));
            OreDictionary.registerOre("blockIxiolite", new ItemStack(GDBlocks.ixiolite_block, 1));
        }
        if (GDConfig.oreDict.proustite) {
            OreDictionary.registerOre("gemProustite", new ItemStack(GDItems.proustite, 1));
            OreDictionary.registerOre("blockProustite", new ItemStack(GDBlocks.proustite_block, 1));
        }
        if (GDConfig.oreDict.euclase) {
            OreDictionary.registerOre("gemEuclase", new ItemStack(GDItems.euclase, 1));
            OreDictionary.registerOre("blockEuclase", new ItemStack(GDBlocks.euclase_block, 1));
        }
        if (GDConfig.oreDict.leucite) {
            OreDictionary.registerOre("gemLeucite", new ItemStack(GDItems.leucite, 1));
            OreDictionary.registerOre("blockLeucite", new ItemStack(GDBlocks.leucite_block, 1));
        }
        if (GDConfig.oreDict.carnelian) {
            OreDictionary.registerOre("gemCarnelian", new ItemStack(GDItems.carnelian, 1));
            OreDictionary.registerOre("blockCarnelian", new ItemStack(GDBlocks.carnelian_block, 1));
        }
        if (GDConfig.oreDict.benitoite) {
            OreDictionary.registerOre("gemBenitoite", new ItemStack(GDItems.benitoite, 1));
            OreDictionary.registerOre("blockBenitoite", new ItemStack(GDBlocks.benitoite_block, 1));
        }
        if (GDConfig.oreDict.diopside) {
            OreDictionary.registerOre("gemDiopside", new ItemStack(GDItems.diopside, 1));
            OreDictionary.registerOre("blockDiopside", new ItemStack(GDBlocks.diopside_block, 1));
        }
        if (GDConfig.oreDict.chalcedony) {
            OreDictionary.registerOre("gemChalcedony", new ItemStack(GDItems.chalcedony, 1));
            OreDictionary.registerOre("blockChalcedony", new ItemStack(GDBlocks.chalcedony_block, 1));
        }
        if (GDConfig.oreDict.tektite) {
            OreDictionary.registerOre("gemTektite", new ItemStack(GDItems.tektite, 1));
            OreDictionary.registerOre("blockTektite", new ItemStack(GDBlocks.tektite_block, 1));
        }
        if (GDConfig.oreDict.goldstone) {
            OreDictionary.registerOre("gemGoldstone", new ItemStack(GDItems.goldstone, 1));
            OreDictionary.registerOre("blockGoldstone", new ItemStack(GDBlocks.goldstone_block, 1));
        }

        GameRegistry.addSmelting(new ItemStack(Items.REDSTONE, 1), new ItemStack(GDItems.crystallized_redstone, 1), 0.25F);
        GameRegistry.addSmelting(new ItemStack(Items.DYE, 1, 4), new ItemStack(GDItems.crystallized_lapis_lazuli, 1), 0.25F);
        GameRegistry.addSmelting(new ItemStack(GDItems.lurmorus_meat, 1), new ItemStack(GDItems.lurmorus_steak, 1), 0.2F);
        GameRegistry.addSmelting(new ItemStack(GDItems.luggeroth_chop, 1), new ItemStack(GDItems.cooked_luggeroth_chop, 1), 0.2F);
        GameRegistry.addSmelting(new ItemStack(GDItems.small_tentacle, 1), new ItemStack(GDItems.small_calamari, 1), 0.2F);
        GameRegistry.addSmelting(new ItemStack(GDItems.large_tentacle, 1), new ItemStack(GDItems.large_calamari, 1), 0.2F);
        GameRegistry.addSmelting(new ItemStack(GDItems.fine_dust, 1), new ItemStack(GDItems.cloudy_shard, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.salt, new ItemStack(GDBlocks.foggy_glass, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDItems.goldstone_dust, 1), new ItemStack(GDItems.goldstone_residue, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.pink_agate_sapling, new ItemStack(GDBlocks.burnt_sapling, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.blue_agate_sapling, new ItemStack(GDBlocks.burnt_sapling, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.green_agate_sapling, new ItemStack(GDBlocks.burnt_sapling, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.purple_agate_sapling, new ItemStack(GDBlocks.burnt_sapling, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.burnt_sapling, new ItemStack(GDBlocks.burning_sapling, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.sugilite_ore, new ItemStack(GDItems.sugilite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.hematite_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.pyrite_ore, new ItemStack(GDItems.pyrite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.cinnabar_ore, new ItemStack(GDItems.cinnabar, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.labradorite_ore, new ItemStack(GDItems.labradorite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.moonstone_ore, new ItemStack(GDItems.moonstone, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.opal_ore_red, new ItemStack(GDItems.red_opal, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.opal_ore_blue, new ItemStack(GDItems.blue_opal, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.opal_ore_green, new ItemStack(GDItems.green_opal, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.opal_ore_white, new ItemStack(GDItems.white_opal, 1), 1.0F);
        GameRegistry.addSmelting(GDBlocks.speckled_rock, new ItemStack(GDItems.scaynyx_ingot), 0.2F);
        GameRegistry.addSmelting(GDBlocks.coarse_rock, new ItemStack(GDItems.scaynyx_ingot, 2), 0.4F);
        GameRegistry.addSmelting(GDBlocks.precious_rock, new ItemStack(GDItems.scaynyx_ingot, 4), 0.8F);
        GameRegistry.addSmelting(GDBlocks.gaia_cobblestone, new ItemStack(GDBlocks.gaia_stone, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.gaia_stone, new ItemStack(GDItems.pink_essence, 2), 0.1F);
        GameRegistry.addSmelting(GDBlocks.gummy_glitter_block, new ItemStack(GDBlocks.thick_glitter_block, 1), 0.1F);

        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.hematite, 1), new ItemStack(GDItems.ixiolite, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.cinnabar, 1), new ItemStack(GDItems.proustite, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.labradorite, 1), new ItemStack(GDItems.euclase, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.moonstone, 1), new ItemStack(GDItems.leucite, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.red_opal, 1), new ItemStack(GDItems.carnelian, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.blue_opal, 1), new ItemStack(GDItems.benitoite, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.green_opal, 1), new ItemStack(GDItems.diopside, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.white_opal, 1), new ItemStack(GDItems.chalcedony, 1), new ItemStack(GDItems.black_residue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.pyrite, 1), new ItemStack(GDItems.aura_residue, 1), new ItemStack(GDItems.bismuth_residue, 1), 0.2F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.hematite_block, new ItemStack(GDBlocks.ixiolite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.cinnabar_block, new ItemStack(GDBlocks.proustite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.labradorite_block, new ItemStack(GDBlocks.euclase_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.moonstone_block, new ItemStack(GDBlocks.leucite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_red, new ItemStack(GDBlocks.carnelian_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_blue, new ItemStack(GDBlocks.benitoite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_green, new ItemStack(GDBlocks.diopside_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_white, new ItemStack(GDBlocks.chalcedony_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.pyrite_block, new ItemStack(GDItems.aura_cluster, 1), new ItemStack(GDItems.bismuth_crystal, 1), 2.6F);

        RecipeHandler.addPurifying(GDBlocks.corrupt_soil, new ItemStack(GDBlocks.heavy_soil), new ItemStack(GDItems.goldstone_residue, 1), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corrupt_grass, new ItemStack(GDBlocks.glitter_grass), new ItemStack(GDItems.goldstone_residue, 1), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corrupted_leaves, new ItemStack(GDBlocks.pink_agate_leaves), new ItemStack(GDItems.goldstone_residue, 1), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corrupted_log, new ItemStack(GDBlocks.pink_agate_log), new ItemStack(GDItems.goldstone_residue, 2), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corrupt_varloom, new ItemStack(GDBlocks.varloom), new ItemStack(GDItems.goldstone_residue, 1), 0.3F);
    }
}
