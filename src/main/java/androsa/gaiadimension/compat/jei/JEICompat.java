package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import androsa.gaiadimension.block.menu.PurifierMenu;
import androsa.gaiadimension.block.menu.RestructurerMenu;
import androsa.gaiadimension.compat.jei.purifier.NullFuelCategory;
import androsa.gaiadimension.compat.jei.purifier.NullFuelRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifierRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifyingCategory;
import androsa.gaiadimension.compat.jei.restructurer.*;
import androsa.gaiadimension.registry.ModBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collection;
import java.util.Map;

@JeiPlugin
public class JEICompat implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(GaiaDimensionMod.MODID, GaiaDimensionMod.MODID); //This dumb? Probably...
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(
                new GlitterFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                new ShineFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                new RestructuringCategory(registry.getJeiHelpers().getGuiHelper()),
                new NullFuelCategory(registry.getJeiHelpers().getGuiHelper()),
                new PurifyingCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    public static <C extends Container, T extends Recipe<C>> Collection<Recipe<C>> getRecipes(RecipeManager recipeManager, RecipeType<T> recipeType) {
        Map<ResourceLocation, Recipe<C>> recipes = recipeManager.byType(recipeType);
        return recipes.values();
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        IJeiHelpers helper = registry.getJeiHelpers();
        IIngredientManager manager = registry.getIngredientManager();

        registry.addRecipes(RestructurerRecipeMaker.getRestructurerRecipes(), GDRecipeCategoryUid.RESTRUCTURE);
        registry.addRecipes(GlitterFuelRecipeMaker.getGlitterRecipes(manager, helper), GDRecipeCategoryUid.GOLD);
        registry.addRecipes(ShineFuelRecipeMaker.getShineRecipes(manager, helper), GDRecipeCategoryUid.SHINE);
        registry.addRecipes(PurifierRecipeMaker.getPurifierRecipes(), GDRecipeCategoryUid.PURIFY);
        registry.addRecipes(NullFuelRecipeMaker.getNullRecipes(manager, helper), GDRecipeCategoryUid.NULLING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(AgateCraftingTableMenu.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceMenu.class, VanillaRecipeCategoryUid.FURNACE, 0, 1, 3, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceMenu.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, GDRecipeCategoryUid.RESTRUCTURE, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, GDRecipeCategoryUid.GOLD, 1, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerMenu.class, GDRecipeCategoryUid.SHINE, 2, 1, 5, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, GDRecipeCategoryUid.PURIFY, 0, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, GDRecipeCategoryUid.GOLD, 1, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, GDRecipeCategoryUid.SHINE, 2, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierMenu.class, GDRecipeCategoryUid.NULLING, 3, 1, 6, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.agate_crafting_table.get()), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.gaia_stone_furnace.get()), VanillaRecipeCategoryUid.FURNACE);
    }
}
