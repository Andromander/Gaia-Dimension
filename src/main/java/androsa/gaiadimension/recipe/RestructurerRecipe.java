package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.registration.ModSlotDisplay;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import java.util.List;

public class RestructurerRecipe extends DoubleOutputRecipe {

    public RestructurerRecipe(String groupIn, Ingredient ingredientIn, ItemStack resultIn, ItemStack byproductIn, float experienceIn, int cookTimeIn) {
        super(groupIn, ingredientIn, resultIn, byproductIn, experienceIn, cookTimeIn);
    }

    @Override
    public List<RecipeDisplay> display() {
        return List.of(
                new RestructurerRecipeDisplay(
                        this.input().display(),
                        ModSlotDisplay.GlitterFuel.INSTANCE,
                        ModSlotDisplay.ShineFuel.INSTANCE,
                        new SlotDisplay.ItemStackSlotDisplay(this.result()),
                        new SlotDisplay.ItemStackSlotDisplay(this.byproduct()),
                        new SlotDisplay.ItemSlotDisplay(ModBlocks.restructurer.asItem()),
                        cookTime,
                        experience
                ));
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipes.RESTRUCTURING.get();
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipes.RESTRUCTURING_SERIALIZER.get();
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ModRecipes.RESTRUCTURING_CATEGORY.get();
    }
}
