package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import androsa.gaiadimension.block.menu.PurifierMenu;
import androsa.gaiadimension.block.menu.RestructurerMenu;
import androsa.gaiadimension.block.screen.AgateCraftingScreen;
import androsa.gaiadimension.block.screen.GaiaStoneFurnaceScreen;
import androsa.gaiadimension.block.screen.PurifierScreen;
import androsa.gaiadimension.block.screen.RestructurerScreen;
import androsa.gaiadimension.compat.jei.purifier.NullFuelCategory;
import androsa.gaiadimension.compat.jei.purifier.NullFuelRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifierRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifyingCategory;
import androsa.gaiadimension.compat.jei.restructurer.*;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModMenus;
import androsa.gaiadimension.registry.registration.ModRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.client.gui.screens.inventory.FurnaceScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEICompat implements IModPlugin {

    private RestructuringCategory restructuringCategory;
    private PurifyingCategory purifyingCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, GaiaDimensionMod.MODID); //This dumb? Probably...
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(
                new GlitterFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                new ShineFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                restructuringCategory = new RestructuringCategory(registry.getJeiHelpers().getGuiHelper()),
                new NullFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                purifyingCategory = new PurifyingCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        IIngredientManager manager = registry.getIngredientManager();

        RestructurerRecipeMaker restructurer = new RestructurerRecipeMaker();
        PurifierRecipeMaker purifier = new PurifierRecipeMaker();

        registry.addRecipes(GaiaRecipeTypes.RESTRUCTURE, restructurer.getRestructurerRecipes());
        registry.addRecipes(GaiaRecipeTypes.GOLD, GlitterFuelRecipeMaker.getGlitterRecipes(manager));
        registry.addRecipes(GaiaRecipeTypes.SHINE, ShineFuelRecipeMaker.getShineRecipes(manager));
        registry.addRecipes(GaiaRecipeTypes.PURIFY, purifier.getPurifierRecipes());
        registry.addRecipes(GaiaRecipeTypes.NULLING, NullFuelRecipeMaker.getNullRecipes(manager));
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AgateCraftingTableMenu.class, ModMenus.AGATE_CRAFTING_TABLE.get(), RecipeTypes.CRAFTING, 1, 9, 10, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceMenu.class, ModMenus.GAIA_STONE_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceMenu.class, ModMenus.GAIA_STONE_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, ModMenus.RESTRUCTURER.get(), GaiaRecipeTypes.RESTRUCTURE, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, ModMenus.RESTRUCTURER.get(), GaiaRecipeTypes.GOLD, 1, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, ModMenus.RESTRUCTURER.get(), GaiaRecipeTypes.SHINE, 2, 1, 5, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, ModMenus.PURIFIER.get(), GaiaRecipeTypes.PURIFY, 0, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, ModMenus.PURIFIER.get(), GaiaRecipeTypes.GOLD, 1, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, ModMenus.PURIFIER.get(), GaiaRecipeTypes.SHINE, 2, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, ModMenus.PURIFIER.get(), GaiaRecipeTypes.NULLING, 3, 1, 6, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.agate_crafting_table.get()), RecipeTypes.CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.gaia_stone_furnace.get()), RecipeTypes.SMELTING);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AgateCraftingScreen.class, 88, 32, 28, 23, RecipeTypes.CRAFTING);
        registration.addRecipeClickArea(GaiaStoneFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
        registration.addRecipeClickArea(RestructurerScreen.class, 79, 53, 18, 24, GaiaRecipeTypes.RESTRUCTURE, GaiaRecipeTypes.GOLD, GaiaRecipeTypes.SHINE);
        registration.addRecipeClickArea(PurifierScreen.class, 65, 82, 46, 24, GaiaRecipeTypes.PURIFY, GaiaRecipeTypes.GOLD, GaiaRecipeTypes.SHINE, GaiaRecipeTypes.NULLING);
    }
}
