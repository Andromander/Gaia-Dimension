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
        return ShapedRecipeBuilder.shaped(result, count)
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem().toString(), has(ingredient));
    }

    public ShapedRecipeBuilder largeCompressRecipe(IItemProvider result, IItemProvider ingredient) {
        return largeCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder largeCompressRecipe(IItemProvider result, IItemProvider ingredient, int count) {
        return ShapedRecipeBuilder.shaped(result, count)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem().toString(), has(ingredient));
    }

    public ShapelessRecipeBuilder planksRecipe(Supplier<Block> result, ITag.INamedTag<Item> ingredient) {
        return ShapelessRecipeBuilder.shapeless(result.get(), 4)
                .requires(ingredient)
                .unlockedBy("has_" + ingredient.getName().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder slabRecipe(RegistryObject<SlabBlock> result, RegistryObject<Block> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get(), 6)
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairsBlock> result, RegistryObject<? extends Block> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapelessRecipeBuilder blockToItemRecipe(RegistryObject<Item> result, RegistryObject<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(result.get(), 9)
                .requires(ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder helmetRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder chestRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder legsRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder bootsRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder axeRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("##")
                .pattern("#/")
                .pattern(" /")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getName().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder pickaxeRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder pickaxeRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("###")
                .pattern(" / ")
                .pattern(" / ")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getName().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder shovelRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder shovelRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("#")
                .pattern("/")
                .pattern("/")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getName().getPath(), has(ingredient));
    }

    public ShapedRecipeBuilder swordRecipe(RegistryObject<Item> result, RegistryObject<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', ingredient.get())
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder swordRecipeTag(RegistryObject<Item> result, ITag.INamedTag<Item> ingredient) {
        return ShapedRecipeBuilder.shaped(result.get())
                .pattern("#")
                .pattern("#")
                .pattern("/")
                .define('#', ingredient)
                .define('/', ModItems.agate_stick.get())
                .unlockedBy("has_" + ingredient.getName().getPath(), has(ingredient));
    }

    public ShapelessRecipeBuilder drinkRecipe(RegistryObject<Item> result, RegistryObject<Item> geode) {
        return ShapelessRecipeBuilder.shapeless(result.get())
                .requires(geode.get())
                .requires(ModItems.sugar_crystals.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()));
    }

    public ShapelessRecipeBuilder sliceRecipe(RegistryObject<Item> result, RegistryObject<Item> geode) {
        return ShapelessRecipeBuilder.shapeless(result.get(), 4)
                .requires(geode.get())
                .unlockedBy("has_geode", has(geode.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Block ingredient) {
        return ShapelessRecipeBuilder.shapeless(result.get())
                .requires(ingredient)
                .requires(ModBlocks.thiscus.get())
                .unlockedBy("has_thiscus", has(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(RegistryObject<Item> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(result.get())
                .requires(ingredient.get())
                .requires(ModBlocks.thiscus.get())
                .unlockedBy("has_thiscus", has(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder crustBricks(Supplier<Block> result, Supplier<Block> ingredient) {
        return ShapelessRecipeBuilder.shapeless(result.get())
                .requires(ingredient.get())
                .requires(ModItems.crystal_shard.get())
                .unlockedBy("has_shard", has(ModItems.crystal_shard.get()));
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    public CookingRecipeBuilder smeltingRecipe(IItemProvider result, IItemProvider ingredient, float exp, int count) {
        return CookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), result, exp, 200)
                .unlockedBy("has_" + ingredient.asItem().getRegistryName(), has(ingredient));
    }
}
