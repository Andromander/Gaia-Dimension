package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import androsa.gaiadimension.block.container.GaiaStoneFurnaceContainer;
import androsa.gaiadimension.block.container.PurifierContainer;
import androsa.gaiadimension.block.container.RestructurerContainer;
import androsa.gaiadimension.compat.jei.purifier.NullFuelCategory;
import androsa.gaiadimension.compat.jei.purifier.NullFuelRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifierCategory;
import androsa.gaiadimension.compat.jei.purifier.PurifierRecipeMaker;
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
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

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
                new PurifierCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    public static <C extends IInventory, T extends IRecipe<C>> Collection<T> getRecipes(RecipeManager recipeManager, IRecipeType<T> recipeType) {
        Map<ResourceLocation, IRecipe<C>> recipesMap = recipeManager.getRecipes(recipeType);
        //noinspection unchecked
        return (Collection<T>)recipesMap.values();
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
        registration.addRecipeTransferHandler(AgateCraftingTableContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceContainer.class, VanillaRecipeCategoryUid.FURNACE, 0, 1, 3, 36);
        registration.addRecipeTransferHandler(GaiaStoneFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
        registration.addRecipeTransferHandler(RestructurerContainer.class, GDRecipeCategoryUid.RESTRUCTURE, 0, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerContainer.class, GDRecipeCategoryUid.GOLD, 1, 1, 5, 36);
        registration.addRecipeTransferHandler(RestructurerContainer.class, GDRecipeCategoryUid.SHINE, 2, 1, 5, 36);
        registration.addRecipeTransferHandler(PurifierContainer.class, GDRecipeCategoryUid.PURIFY, 0, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierContainer.class, GDRecipeCategoryUid.GOLD, 1, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierContainer.class, GDRecipeCategoryUid.SHINE, 2, 1, 6, 36);
        registration.addRecipeTransferHandler(PurifierContainer.class, GDRecipeCategoryUid.NULLING, 3, 1, 6, 36);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.agate_crafting_table), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.gaia_stone_furnace), VanillaRecipeCategoryUid.FURNACE);
    }
}
