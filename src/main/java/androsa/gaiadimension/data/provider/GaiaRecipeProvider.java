package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Supplier;

public abstract class GaiaRecipeProvider extends ForgeRecipeProvider implements IConditionBuilder {

    public GaiaRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    public ShapedRecipeBuilder smallCompressRecipe(IItemProvider result, IItemProvider ingredient) {
        return smallCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder smallCompressRecipe(IItemProvider result, IItemProvider ingredient, int count) {
        return ShapedRecipeBuilder.shapedRecipe(result, count)
                .patternLine("##")
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.asItem().toString(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder largeCompressRecipe(IItemProvider result, IItemProvider ingredient) {
        return largeCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder largeCompressRecipe(IItemProvider result, IItemProvider ingredient, int count) {
        return ShapedRecipeBuilder.shapedRecipe(result, 3)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.asItem().toString(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder planksRecipe(Supplier<? extends Block> result, Tag<Item> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 4)
                .addIngredient(ingredient)
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder slabRecipe(Supplier<? extends Block> result, Supplier<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<? extends Block> result, Supplier<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get(), 8)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(Supplier<? extends Item> result, Supplier<? extends Block> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 9)
                .addIngredient(ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder helmetRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder chestRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder legsRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder bootsRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipeTag(Supplier<? extends Item> result, Tag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder pickaxeRecipeTag(Supplier<? extends Item> result, Tag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder shovelRecipeTag(Supplier<? extends Item> result, Tag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder swordRecipe(Supplier<? extends Item> result, Supplier<? extends Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.get().getRegistryName().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder swordRecipeTag(Supplier<? extends Item> result, Tag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder drinkRecipe(Supplier<? extends Item> result, Supplier<? extends Item> geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(geode.get())
                .addIngredient(ModItems.sugar_crystals.get())
                .addIngredient(ModItems.agate_cup.get())
                .addCriterion("has_cup", hasItem(ModItems.agate_cup.get()));
    }

    public ShapelessRecipeBuilder sliceRecipe(Supplier<? extends Item> result, Supplier<? extends Item> geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 4)
                .addIngredient(geode.get())
                .addCriterion("has_geode", hasItem(geode.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(Supplier<? extends Item> result, Supplier<? extends Block> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(ingredient.get())
                .addIngredient(ModBlocks.thiscus.get())
                .addCriterion("has_thiscus", hasItem(ModBlocks.thiscus.get()));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 200)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }
}
