package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GaiaDimensionMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, GaiaDimensionMod.MODID);

    //RecipeType
    public static RegistryObject<RecipeType<RestructurerRecipe>> RESTRUCTURING = RECIPE_TYPES.register("restructuring", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return RESTRUCTURING.getId().toString();
        }
    });
    public static RegistryObject<RecipeType<PurifierRecipe>> PURIFYING = RECIPE_TYPES.register("purifying", () -> new RecipeType<>() {
        @Override
        public String toString() {
            return PURIFYING.getId().toString();
        }
    });

    //RecipeSerializer
    public static final RegistryObject<RestructurerRecipeSerializer<RestructurerRecipe>> RESTRUCTURING_SERIALIZER = RECIPE_SERIALIZERS.register("restructuring",
            () -> new RestructurerRecipeSerializer<>(RestructurerRecipe::new, 200));
    public static final RegistryObject<PurifierRecipeSerializer<PurifierRecipe>> PURIFYING_SERIALIZER = RECIPE_SERIALIZERS.register("purifying",
            () -> new PurifierRecipeSerializer<>(PurifierRecipe::new, 200));
}