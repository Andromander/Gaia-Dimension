package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.recipe.PurifierRecipeBuilder;
import androsa.gaiadimension.recipe.RestructurerRecipeBuilder;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
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
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

public abstract class GaiaRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public GaiaRecipeProvider(PackOutput output) {
        super(output);
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

    public ShapedRecipeBuilder slabRecipe(RegistryObject<SlabBlock> result, RegistryObject<Block> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairBlock> result, RegistryObject<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(Supplier<Item> result, RegistryObject<Block> ingredient) {
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

    public ShapedRecipeBuilder axeRecipeTag(RegistryObject<Item> result, TagKey<Item> ingredient) {
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

    public ShapedRecipeBuilder pickaxeRecipeTag(RegistryObject<Item> result, TagKey<Item> ingredient) {
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

    public ShapedRecipeBuilder shovelRecipeTag(RegistryObject<Item> result, TagKey<Item> ingredient) {
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

    public ShapedRecipeBuilder swordRecipeTag(RegistryObject<Item> result, TagKey<Item> ingredient) {
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

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Block ingredient) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, result.get())
                .requires(ingredient)
                .requires(ModBlocks.thiscus.get())
                .unlockedBy("has_thiscus", has(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Supplier<Block> ingredient) {
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

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()).getPath(), has(ingredient));
    }

    public RestructurerRecipeBuilder restructureBlackResidue(RegistryObject<Item> result, RegistryObject<Item> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result.get(), ModItems.black_residue.get(), exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.get().asItem()).getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringTektite(RegistryObject<Block> result, RegistryObject<Block> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient.get(), count)), result.get(), ModItems.tektite.get(), exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.get().asItem()).getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringItems(ItemLike result, ItemLike byproduct, ItemLike ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(new ItemStack(ingredient, count)), result, byproduct, exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()).getPath(), has(ingredient));
    }

    public PurifierRecipeBuilder purifyingItems(ItemLike result, ItemLike byproduct, ItemLike ingredient, float exp, int count, int bycount) {
        return PurifierRecipeBuilder.purifying(Ingredient.of(new ItemStack(ingredient, count)), result, Ingredient.of(new ItemStack(byproduct, bycount)), exp, 200)
                .unlockedBy("has_" + ForgeRegistries.ITEMS.getKey(ingredient.asItem()).getPath(), has(ingredient));
    }
}
