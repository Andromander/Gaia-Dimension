package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.registration.ModSlotDisplay;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public class PurifierRecipe extends DoubleOutputRecipe {

    public PurifierRecipe(String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
        super(groupIn, ingredientIn, resultIn, byproductIn, experienceIn, cookTimeIn);
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new PurifierRecipeDisplay(
                        this.input().display(),
                        ModSlotDisplay.GlitterFuel.INSTANCE,
                        ModSlotDisplay.ShineFuel.INSTANCE,
                        ModSlotDisplay.NullingFuel.INSTANCE,
                        new SlotDisplay.ItemStackSlotDisplay(this.result()),
                        new SlotDisplay.ItemStackSlotDisplay(this.byproduct()),
                        new SlotDisplay.ItemSlotDisplay(ModBlocks.purifier.asItem()),
                        cookTime,
                        experience
                ));
    }

    @Override
    public RecipeType<PurifierRecipe> getType() {
        return ModRecipes.PURIFYING.get();
    }

    @Override
    public RecipeSerializer<PurifierRecipe> getSerializer() {
        return ModRecipes.PURIFYING_SERIALIZER.get();
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ModRecipes.PURIFYING_CATEGORY.get();
    }
}
