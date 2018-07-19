package androsa.gaiadimension.registry;

import androsa.gaiadimension.recipe.RecipeHandler;
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

        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.pink_agate_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.blue_agate_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.green_agate_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.purple_agate_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.fossilized_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.corrupted_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.crusty_planks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.heated_planks, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addSmelting(new ItemStack(GDItems.lurmorusMeat, 1), new ItemStack(GDItems.lurmorusSteak, 1), 0.0F);
        GameRegistry.addSmelting(new ItemStack(GDItems.fineDust, 1), new ItemStack(GDItems.cloudyShard, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDItems.goldstoneDust, 1), new ItemStack(GDItems.goldstoneResidue, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaia_sapling, 1, 0), new ItemStack(GDBlocks.gaia_sapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaia_sapling, 1, 1), new ItemStack(GDBlocks.gaia_sapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaia_sapling, 1, 2), new ItemStack(GDBlocks.gaia_sapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaia_sapling, 1, 3), new ItemStack(GDBlocks.gaia_sapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaia_sapling, 1, 6), new ItemStack(GDBlocks.gaia_sapling, 1, 7), 0.1F);
        GameRegistry.addSmelting(GDBlocks.sugilite_ore, new ItemStack(GDItems.sugilite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.hematite_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.pyrite_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.cinnabar_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.labradorite_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.moonstone_ore, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opal_ore, 1, 0), new ItemStack(GDItems.opalRed, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opal_ore, 1, 1), new ItemStack(GDItems.opalBlue, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opal_ore, 1, 2), new ItemStack(GDItems.opalGreen, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opal_ore, 1, 3), new ItemStack(GDItems.opalWhite, 1), 1.0F);

        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.hematite, 1), new ItemStack(GDItems.ixiolite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.cinnabar, 1), new ItemStack(GDItems.proustite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.labradorite, 1), new ItemStack(GDItems.euclase, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.moonstone, 1), new ItemStack(GDItems.leucite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalRed, 1), new ItemStack(GDItems.carnelian, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalBlue, 1), new ItemStack(GDItems.benitoite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalGreen, 1), new ItemStack(GDItems.diopside, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalWhite, 1), new ItemStack(GDItems.chalcedony, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.hematite_block, new ItemStack(GDBlocks.ixiolite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.cinnabar_block, new ItemStack(GDBlocks.proustite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.labradorite_block, new ItemStack(GDBlocks.euclase_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.moonstone_block, new ItemStack(GDBlocks.leucite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_red, new ItemStack(GDBlocks.carnelian_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_blue, new ItemStack(GDBlocks.benitoite_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_green, new ItemStack(GDBlocks.diopside_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.opal_block_white, new ItemStack(GDBlocks.chalcedony_block, 1), new ItemStack(GDItems.tektite, 1), 2.7F);

        RecipeHandler.addPurifying(GDBlocks.corrupt_soil, new ItemStack(GDBlocks.heavy_soil), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corrupt_grass, new ItemStack(GDBlocks.glitter_grass), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(new ItemStack(GDBlocks.special_gaia_leaves, 1, 2), new ItemStack(GDBlocks.gaia_leaves, 1, 0), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(new ItemStack(GDBlocks.special_gaia_log, 1, 2), new ItemStack(GDBlocks.gaia_log, 1, 0), new ItemStack(GDItems.goldstoneResidue, 2), 0.3F);
    }
}
