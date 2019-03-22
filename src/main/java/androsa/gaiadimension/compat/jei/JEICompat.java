package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.block.container.ContainerAgateCraftingTable;
import androsa.gaiadimension.block.container.ContainerGaiaStoneFurnace;
import androsa.gaiadimension.block.container.ContainerPurifier;
import androsa.gaiadimension.block.container.ContainerRestructurer;
import androsa.gaiadimension.compat.jei.purifier.NullFuelCategory;
import androsa.gaiadimension.compat.jei.purifier.NullFuelRecipeMaker;
import androsa.gaiadimension.compat.jei.purifier.PurifierCategory;
import androsa.gaiadimension.compat.jei.purifier.PurifierRecipeMaker;
import androsa.gaiadimension.compat.jei.restructurer.*;
import androsa.gaiadimension.registry.GDBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEICompat implements IModPlugin {

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

    @Override
    public void register(IModRegistry registry) {

        JEICompatUtils utils = new JEICompatUtils(registry.getIngredientRegistry());
        registry.addRecipes(RestructurerRecipeMaker.getRestructurerRecipes(registry.getJeiHelpers()), GDRecipeCategoryUid.RESTRUCTURE);
        registry.addRecipes(GlitterFuelRecipeMaker.getGlitterRecipes(utils, registry.getJeiHelpers()), GDRecipeCategoryUid.GOLD);
        registry.addRecipes(ShineFuelRecipeMaker.getShineRecipes(utils, registry.getJeiHelpers()), GDRecipeCategoryUid.SHINE);
        registry.addRecipes(PurifierRecipeMaker.getPurifierRecipes(registry.getJeiHelpers()), GDRecipeCategoryUid.PURIFY);
        registry.addRecipes(NullFuelRecipeMaker.getNullRecipes(utils, registry.getJeiHelpers()), GDRecipeCategoryUid.NULLING);

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerAgateCraftingTable.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerGaiaStoneFurnace.class, VanillaRecipeCategoryUid.SMELTING, 0, 1, 3, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerGaiaStoneFurnace.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerRestructurer.class, GDRecipeCategoryUid.RESTRUCTURE, 0, 1, 5, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerRestructurer.class, GDRecipeCategoryUid.GOLD, 1, 1, 5, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerRestructurer.class, GDRecipeCategoryUid.SHINE, 2, 1, 5, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPurifier.class, GDRecipeCategoryUid.PURIFY, 0, 1, 6, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPurifier.class, GDRecipeCategoryUid.GOLD, 1, 1, 6, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPurifier.class, GDRecipeCategoryUid.SHINE, 2, 1, 6, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPurifier.class, GDRecipeCategoryUid.NULLING, 3, 1, 6, 36);

        registry.addRecipeCatalyst(new ItemStack(GDBlocks.agate_crafting_table), VanillaRecipeCategoryUid.CRAFTING);
        registry.addRecipeCatalyst(new ItemStack(GDBlocks.gaia_stone_furnace_idle), VanillaRecipeCategoryUid.SMELTING);
    }
}
