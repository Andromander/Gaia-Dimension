package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.CurtainBlock;
import androsa.gaiadimension.item.ConstructKitItem;
import androsa.gaiadimension.recipe.PurifierRecipeBuilder;
import androsa.gaiadimension.recipe.RestructurerRecipeBuilder;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jspecify.annotations.NullMarked;

import java.util.function.Supplier;

@NullMarked
public abstract class GaiaRecipeProvider extends RecipeProvider {

    protected final HolderGetter<Item> hack;

    public GaiaRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
        this.hack = provider.lookupOrThrow(Registries.ITEM);
    }

    protected String loc(String name) {
        return Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, name).toString();
    }

    public ShapedRecipeBuilder smallCompressRecipe(ItemLike result, ItemLike ingredient) {
        return smallCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder smallCompressRecipe(ItemLike result, ItemLike ingredient, int count) {
        return this.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .pattern("##")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem(), has(ingredient));
    }

    public ShapedRecipeBuilder largeCompressRecipe(ItemLike result, ItemLike ingredient) {
        return largeCompressRecipe(result, ingredient, 1);
    }

    public ShapedRecipeBuilder largeCompressRecipe(ItemLike result, ItemLike ingredient, int count) {
        return this.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + ingredient.asItem(), has(ingredient));
    }

    public void planksRecipe(Supplier<Block> result, TagKey<Item> ingredient, RecipeOutput output) {
        woodRecipe(this.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 4)
                .requires(ingredient)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient)),
                output, result.get().asItem());
    }

    public ShapedRecipeBuilder slabRecipe(Supplier<SlabBlock> result, DeferredBlock<Block> ingredient) {
        return this.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 6)
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public ShapedRecipeBuilder stairsRecipe(Supplier<StairBlock> result, DeferredBlock<? extends Block> ingredient) {
        return this.shaped(RecipeCategory.BUILDING_BLOCKS, result.get(), 8)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public void blockToItemRecipe(Supplier<Item> result, DeferredBlock<Block> ingredient, RecipeOutput output, String name) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get(), 9)
                .requires(ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()))
                .save(output, loc("storage_blocks/" + name));
    }

    public void helmetRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        armorRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("###")
                        .pattern("# #")
                        .define('#', ingredient.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void chestRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        armorRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .define('#', ingredient.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void legsRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        armorRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', ingredient.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void bootsRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        armorRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("# #")
                        .pattern("# #")
                        .define('#', ingredient.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void armorRecipe(ShapedRecipeBuilder recipe, RecipeOutput output, Supplier<Item> result) {
        String path = loc("armor/" + BuiltInRegistries.ITEM.getKey(result.get()).getPath());
        recipe.save(output, path);
    }

    public void axeRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("##")
                        .pattern("#/")
                        .pattern(" /")
                        .define('#', ingredient.get())
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void axeRecipeTag(Supplier<Item> result, TagKey<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("##")
                        .pattern("#/")
                        .pattern(" /")
                        .define('#', ingredient)
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient)),
                output, result);
    }

    public void pickaxeRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("###")
                        .pattern(" / ")
                        .pattern(" / ")
                        .define('#', ingredient.get())
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void pickaxeRecipeTag(Supplier<Item> result, TagKey<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("###")
                        .pattern(" / ")
                        .pattern(" / ")
                        .define('#', ingredient)
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient)),
                output, result);
    }

    public void shovelRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("#")
                        .pattern("/")
                        .pattern("/")
                        .define('#', ingredient.get())
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void shovelRecipeTag(Supplier<Item> result, TagKey<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.TOOLS, result.get())
                        .pattern("#")
                        .pattern("/")
                        .pattern("/")
                        .define('#', ingredient)
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient)),
                output, result);
    }

    public void swordRecipe(Supplier<Item> result, DeferredItem<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("#")
                        .pattern("#")
                        .pattern("/")
                        .define('#', ingredient.get())
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get())),
                output, result);
    }

    public void swordRecipeTag(Supplier<Item> result, TagKey<Item> ingredient, RecipeOutput output) {
        toolRecipe(this.shaped(RecipeCategory.COMBAT, result.get())
                        .pattern("#")
                        .pattern("#")
                        .pattern("/")
                        .define('#', ingredient)
                        .define('/', ModItems.agate_stick.get())
                        .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient)),
                output, result);
    }

    private void toolRecipe(ShapedRecipeBuilder recipe, RecipeOutput output, Supplier<Item> result) {
        recipe.save(output, loc("tools/"  + BuiltInRegistries.ITEM.getKey(result.get()).getPath()));
    }

    public ShapelessRecipeBuilder drinkRecipe(Supplier<Item> result, Supplier<Item> geode) {
        return this.shapeless(RecipeCategory.FOOD, result.get())
                .requires(geode.get())
                .requires(ModItems.sugar_crystals.get())
                .requires(ModItems.agate_cup.get())
                .unlockedBy("has_cup", has(ModItems.agate_cup.get()));
    }

    public ShapelessRecipeBuilder sliceRecipe(Supplier<Item> result, Supplier<Item> geode) {
        return this.shapeless(RecipeCategory.FOOD, result.get(), 4)
                .requires(geode.get())
                .unlockedBy("has_geode", has(geode.get()));
    }

    public ShapelessRecipeBuilder tiliRecipe(Supplier<Item> result, Supplier<Block> ingredient) {
        return this.shapeless(RecipeCategory.FOOD, result.get())
                .requires(ingredient.get())
                .requires(ModBlocks.thiscus.get())
                .unlockedBy("has_thiscus", has(ModBlocks.thiscus.get()));
    }

    public ShapelessRecipeBuilder crustBricks(Supplier<Block> result, Supplier<Block> ingredient) {
        return this.shapeless(RecipeCategory.BUILDING_BLOCKS, result.get())
                .requires(ingredient.get())
                .requires(ModItems.crystal_shard.get())
                .unlockedBy("has_shard", has(ModItems.crystal_shard.get()));
    }

    public ShapedRecipeBuilder curtainRecipe(Supplier<CurtainBlock> result, DeferredBlock<Block> curtain, int amount) {
        return this.shaped(RecipeCategory.DECORATIONS, result.get(), amount)
                .pattern("//")
                .pattern("##")
                .pattern("##")
                .define('/', ModItems.agate_stick)
                .define('#', curtain.get())
                .unlockedBy("has_" + curtain.getId().getPath(), has(curtain));
    }

    public ShapelessRecipeBuilder repairKit() {
        ItemStackTemplate stack = new ItemStackTemplate(ModItems.repair_kit, DataComponentPatch.builder().set(ModDataComponents.KIT_PART.value(), ConstructKitItem.Part.LEFT_HORN).build());

        return this.shapeless(RecipeCategory.MISC, stack)
                .requires(ModItems.blank_kit)
                .requires(ModItems.opalite)
                .unlockedBy("has_opalite", has(ModItems.opalite));
    }

    public ShapelessRecipeBuilder augmentKit(Supplier<Item> result, DeferredBlock<Block> ingredient) {
        ItemStackTemplate stack = new ItemStackTemplate(result.get(), DataComponentPatch.builder().set(ModDataComponents.KIT_PART.value(), ConstructKitItem.Part.LEFT_HORN).build());

        return this.shapeless(RecipeCategory.MISC, stack)
                .requires(ModItems.blank_kit)
                .requires(ModItems.opalite)
                .requires(ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public ShapelessRecipeBuilder replaceKit(Supplier<Item> result, DeferredBlock<Block> ingredient) {
        ItemStackTemplate stack = new ItemStackTemplate(result.get(), DataComponentPatch.builder().set(ModDataComponents.KIT_PART.value(), ConstructKitItem.Part.LEFT_HORN).build());

        return this.shapeless(RecipeCategory.MISC, stack)
                .requires(ModItems.blank_kit)
                .requires(ingredient.get())
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public void woodRecipe(RecipeBuilder recipe, RecipeOutput output, Item result) {
        recipe.save(output, loc("wood/" + BuiltInRegistries.ITEM.getKey(result).getPath()));
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, CookingBookCategory cooking, DeferredBlock<? extends Block> ingredient, float exp) {
        return smeltingRecipe(result, cooking, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, CookingBookCategory cooking, DeferredItem<Item> ingredient, float exp) {
        return smeltingRecipe(result, cooking, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, CookingBookCategory cooking, DeferredBlock<? extends Block> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, cooking, new ItemStackTemplate(result.asItem(), count), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, CookingBookCategory cooking, DeferredItem<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, cooking, new ItemStackTemplate(result.asItem(), count), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public RestructurerRecipeBuilder restructureBlackResidue(ItemLike result, DeferredItem<Item> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(result.asItem(), count), new ItemStackTemplate(ModItems.black_residue.get().asItem(), 1), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringTektite(Supplier<Block> result, DeferredBlock<Block> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(result.get().asItem(), count), new ItemStackTemplate(ModItems.tektite.get(), 1), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringCalcite(DeferredBlock<Block> ingredient, DeferredBlock<Block> fungi) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(fungi.asItem()), new ItemStackTemplate(ModBlocks.white_calcite.asItem()), 0.0F, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringAragonite(DeferredBlock<Block> ingredient, DeferredBlock<Block> fungi) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(fungi.asItem()), new ItemStackTemplate(ModBlocks.white_aragonite.asItem()), 0.0F, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient.get()));
    }

    public RestructurerRecipeBuilder restructuringItems(ItemLike result, ItemLike byproduct, DeferredItem<Item> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(result.asItem(), count), new ItemStackTemplate(byproduct.asItem(), 1), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public RestructurerRecipeBuilder restructuringItems(ItemLike result, ItemLike byproduct, DeferredBlock<Block> ingredient, float exp, int count) {
        return RestructurerRecipeBuilder.restructuring(Ingredient.of(ingredient), new ItemStackTemplate(result.asItem(), count), new ItemStackTemplate(byproduct.asItem(), 1), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public PurifierRecipeBuilder purifyingItems(ItemLike result, ItemLike byproduct, DeferredItem<Item> ingredient, float exp, int count, int bycount) {
        return PurifierRecipeBuilder.purifying(Ingredient.of(ingredient.get()), new ItemStackTemplate(result.asItem(), count), new ItemStackTemplate(byproduct.asItem(), bycount), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }

    public PurifierRecipeBuilder purifyingItems(ItemLike result, ItemLike byproduct, DeferredBlock<? extends Block> ingredient, float exp, int count, int bycount) {
        return PurifierRecipeBuilder.purifying(Ingredient.of(ingredient.get()), new ItemStackTemplate(result.asItem(), count), new ItemStackTemplate(byproduct.asItem(), bycount), exp, 200)
                .unlockedBy("has_" + ingredient.getId().getPath(), has(ingredient));
    }
}
