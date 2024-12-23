package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, GaiaDimensionMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, GaiaDimensionMod.MODID);
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, GaiaDimensionMod.MODID);

    //RecipeType
    public static DeferredHolder<RecipeType<?>, RecipeType<RestructurerRecipe>> RESTRUCTURING = RECIPE_TYPES.register("restructuring", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return RESTRUCTURING.getId().toString();
        }
    });
    public static DeferredHolder<RecipeType<?>, RecipeType<PurifierRecipe>> PURIFYING = RECIPE_TYPES.register("purifying", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return PURIFYING.getId().toString();
        }
    });

    //RecipeSerializer
    public static final DeferredHolder<RecipeSerializer<?>, DoubleOutputRecipe.Serializer<RestructurerRecipe>> RESTRUCTURING_SERIALIZER = RECIPE_SERIALIZERS.register("restructuring",
            () -> new DoubleOutputRecipe.Serializer<>(RestructurerRecipe::new, 200));

    public static final DeferredHolder<RecipeSerializer<?>, DoubleOutputRecipe.Serializer<PurifierRecipe>> PURIFYING_SERIALIZER = RECIPE_SERIALIZERS.register("purifying",
            () -> new DoubleOutputRecipe.Serializer<>(PurifierRecipe::new, 200));

    //RecipeBookCategory
    public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> RESTRUCTURING_CATEGORY = RECIPE_BOOK_CATEGORIES.register("restructuring", RecipeBookCategory::new);
    public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> PURIFYING_CATEGORY = RECIPE_BOOK_CATEGORIES.register("purifying", RecipeBookCategory::new);

    //RecipePropertySet
    public static final ResourceKey<RecipePropertySet> RESTRUCTURER_INPUT = ResourceKey.create(RecipePropertySet.TYPE_KEY, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "restructurer_input"));
    public static final ResourceKey<RecipePropertySet> PURIFIER_INPUT = ResourceKey.create(RecipePropertySet.TYPE_KEY, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "purifier_input"));
}