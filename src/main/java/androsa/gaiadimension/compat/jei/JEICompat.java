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
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModMenus;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEICompat implements IModPlugin {

    private RestructuringCategory restructuringCategory;
    private PurifyingCategory purifyingCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(GaiaDimensionMod.MODID, GaiaDimensionMod.MODID); //This dumb? Probably...
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

        RestructurerRecipeMaker restructurer = new RestructurerRecipeMaker(manager);
        PurifierRecipeMaker purifier = new PurifierRecipeMaker(manager);

        registry.addRecipes(GaiaRecipeTypes.RESTRUCTURE, restructurer.getRestructurerRecipes(restructuringCategory));
        registry.addRecipes(GaiaRecipeTypes.GOLD, GlitterFuelRecipeMaker.getGlitterRecipes(manager));
        registry.addRecipes(GaiaRecipeTypes.SHINE, ShineFuelRecipeMaker.getShineRecipes(manager));
        registry.addRecipes(GaiaRecipeTypes.PURIFY, purifier.getPurifierRecipes(purifyingCategory));
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
}
