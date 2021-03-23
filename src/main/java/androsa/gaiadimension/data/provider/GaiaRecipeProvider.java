package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
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
import net.minecraftforge.fml.RegistryObject;

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
        return ShapedRecipeBuilder.shapedRecipe(result, count)
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + ingredient.asItem().toString(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder planksRecipe(Supplier<Block> result, ITag.INamedTag<Item> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 4)
                .addIngredient(ingredient)
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder slabRecipe(RegistryObject<SlabBlock> result, RegistryObject<Block> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get(), 6)
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairsBlock> result, RegistryObject<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get(), 8)
                .patternLine("#  ")
                .patternLine("## ")
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(RegistryObject<Item> result, RegistryObject<Block> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 9)
                .addIngredient(ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder helmetRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder chestRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("# #")
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder legsRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder bootsRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("# #")
                .patternLine("# #")
                .key('#', ingredient.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("##")
                .patternLine("#/")
                .patternLine(" /")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder pickaxeRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("###")
                .patternLine(" / ")
                .patternLine(" / ")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder shovelRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("/")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapedRecipeBuilder swordRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient.get())
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getId().getPath(), hasItem(ingredient.get()));
    }

    public ShapedRecipeBuilder swordRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shapedRecipe(result.get())
                .patternLine("#")
                .patternLine("#")
                .patternLine("/")
                .key('#', ingredient)
                .key('/', ModItems.agate_stick.get())
                .addCriterion("has_" + ingredient.getName().getPath(), hasItem(ingredient));
    }

    public ShapelessRecipeBuilder drinkRecipe(RegistryObject<Item> result, RegistryObject<Item> geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(geode.get())
                .addIngredient(ModItems.sugar_crystals.get())
                .addIngredient(ModItems.agate_cup.get())
                .addCriterion("has_cup", hasItem(ModItems.agate_cup.get()));
    }

    public ShapelessRecipeBuilder sliceRecipe(RegistryObject<Item> result, RegistryObject<Item> geode) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get(), 4)
                .addIngredient(geode.get())
                .addCriterion("has_geode", hasItem(geode.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Block ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(ingredient)
                .addIngredient(ModBlocks.thiscus.get())
                .addCriterion("has_thiscus", hasItem(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(ingredient.get())
                .addIngredient(ModBlocks.thiscus.get())
                .addCriterion("has_thiscus", hasItem(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder crustBricks(Supplier<Block> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapelessRecipe(result.get())
                .addIngredient(ingredient.get())
                .addIngredient(ModItems.crystal_shard.get())
                .addCriterion("has_shard", hasItem(ModItems.crystal_shard.get()));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smeltingRecipe(Ingredient.fromStacks(new ItemStack(ingredient, count)), result, exp, 200)
                .addCriterion("has_" + ingredient.asItem().getRegistryName(), hasItem(ingredient));
    }
}
