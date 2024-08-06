package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.CurtainBlock;
import androsa.gaiadimension.recipe.PurifierRecipeBuilder;
import androsa.gaiadimension.recipe.RestructurerRecipeBuilder;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class GaiaRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public GaiaRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    public ShapedRecipeBuilder smallCompressRecipe(ItemLike result, ItemLike ingredient) {
        return smallCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder smallCompressRecipe(ItemLike result, ItemLike ingredient, int count) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem(), has(ingredient));
    }

    public ShapedRecipeBuilder largeCompressRecipe(ItemLike result, ItemLike ingredient) {
        return largeCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder largeCompressRecipe(ItemLike result, ItemLike ingredient, int count) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem(), has(ingredient));
    }

    public ShapelessRecipeBuilder planksRecipe(Supplier<Block> result, TagKey<Item> ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .requires(ingredient)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder slabRecipe(Supplier<SlabBlock> result, DeferredBlock<Block> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairBlock> result, DeferredBlock<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(Supplier<Item> result, DeferredBlock<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 9)
                .requires(ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder helmetRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("###")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder chestRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder legsRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder bootsRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipeTag(Supplier<Item> result, TagKey<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder pickaxeRecipeTag(Supplier<Item> result, TagKey<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder shovelRecipeTag(Supplier<Item> result, TagKey<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder swordRecipe(Supplier<Item> result, DeferredItem<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder swordRecipeTag(Supplier<Item> result, TagKey<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public ShapelessRecipeBuilder drinkRecipe(Supplier<Item> result, Supplier<Item> geode) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result.get())
                .requires(geode.get())
                .requires(ModItems.sugar_crystals.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()));
    }

    public ShapelessRecipeBuilder sliceRecipe(Supplier<Item> result, Supplier<Item> geode) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result.get(), 4)
                .requires(geode.get())
                .unlockedBy("has_geode", has(geode.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(Supplier<Item> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result.get())
                .requires(ingredient.get())
                .requires(ModBlocks.thiscus.get())
                .unlockedBy("has_thiscus", has(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder crustBricks(Supplier<Block> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                .requires(ingredient.get())
                .requires(ModItems.crystal_shard.get())
                .unlockedBy("has_shard", has(ModItems.crystal_shard.get()));
    }

    public ShapedRecipeBuilder curtainRecipe(Supplier<CurtainBlock> result, DeferredBlock<Block> curtain, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result.get(), amount)
                .pattern("//")
                .pattern("##")
                .pattern("##")
                .define('/', ModItems.agate_stick)
                .define('#', curtain.get())
                .unlockedBy("has_" + curtain.getId().getPath(), has(curtain));
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, DeferredBlock<? extends Block> ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, DeferredItem<Item> ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, DeferredBlock<? extends Block> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient.get(), count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, DeferredItem<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient.get(), count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public RestructurerRecipeBuilder restructureBlackResidue(Supplier<Item> result, DeferredItem<Item> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result.get(), ModItems.black_residue.get(), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringTektite(Supplier<Block> result, DeferredBlock<Block> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result.get(), ModItems.tektite.get(), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringItems(ItemLike result, ItemLike byproduct, DeferredItem<Item> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result, byproduct, exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public RestructurerRecipeBuilder restructuringItems(ItemLike result, ItemLike byproduct, DeferredBlock<Block> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result, byproduct, exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public PurifierRecipeBuilder purifyingItems(ItemLike result, ItemLike byproduct, DeferredItem<Item> ingredient, float exp, int count, int bycount) {
        return PurifierRecipeBuilder.purifying(Ingredient.of(ingredient.get()), new ItemStack(result, count), new ItemStack(byproduct, bycount), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public PurifierRecipeBuilder purifyingItems(ItemLike result, ItemLike byproduct, DeferredBlock<? extends Block> ingredient, float exp, int count, int bycount) {
        return PurifierRecipeBuilder.purifying(Ingredient.of(ingredient.get()), new ItemStack(result, count), new ItemStack(byproduct, bycount), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }
}
