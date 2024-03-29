package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, GaiaDimensionMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, GaiaDimensionMod.MODID);

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
    public static final DeferredHolder<RecipeSerializer<?>, RestructurerRecipeSerializer<RestructurerRecipe>> RESTRUCTURING_SERIALIZER = RECIPE_SERIALIZERS.register("restructuring",
            () -> new RestructurerRecipeSerializer<>(RestructurerRecipe::new, 200));


    public static final DeferredHolder<RecipeSerializer<?>, PurifierRecipeSerializer<PurifierRecipe>> PURIFYING_SERIALIZER = RECIPE_SERIALIZERS.register("purifying",
            () -> new PurifierRecipeSerializer<>(PurifierRecipe::new, 200));
}