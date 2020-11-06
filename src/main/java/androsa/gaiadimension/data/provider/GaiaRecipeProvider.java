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
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

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
        return ShapedRecipeBuilder.shapedRecipe(result, count)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.asItem().toString(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder planksRecipe(Block result, ITag.INamedTag<Item> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result, 4)
                .addIngredient(ingredient)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder slabRecipe(Block result, Block ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result, 6)
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder stairsRecipe(Block result, Block ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result, 8)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(Item result, Block ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result, 9)
                .addIngredient(ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder helmetRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("###")
                .patternLine("# #")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder chestRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder legsRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder bootsRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder axeRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder axeRecipeTag(Item result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipeTag(Item result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipeTag(Item result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder swordRecipe(Item result, Item ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getRegistryName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder swordRecipeTag(Item result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result)
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder drinkRecipe(Item result, Item geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(geode)
                .addIngredient(ModItems.sugar_crystals)
                .addIngredient(ModItems.agate_cup)
                .addCriterion("has_cup", hasItem(ModItems.agate_cup));
    }

    public ShapelessRecipeBuilder sliceRecipe(Item result, Item geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result, 4)
                .addIngredient(geode)
                .addCriterion("has_geode", hasItem(geode));
    }

    public ShapelessRecipeBuilder tiliRecipe(Item result, Block ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(ingredient)
                .addIngredient(ModBlocks.thiscus)
                .addCriterion("has_thiscus", hasItem(ModBlocks.thiscus));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 200)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }
}
