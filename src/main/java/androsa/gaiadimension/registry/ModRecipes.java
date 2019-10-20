package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, GaiaDimensionMod.MODID);

    //RecipeType
    public static final IRecipeType<RestructurerRecipe> RESTRUCTURING = new IRecipeType<RestructurerRecipe>() {
        @Override
        public String toString() {
            return "gaiadimension:restructuring";
        }
    };
    public static final IRecipeType<PurifierRecipe> PURIFYING = new IRecipeType<PurifierRecipe>() {
        @Override
        public String toString() {
            return "gaiadimension:purifying";
        }
    };

    //RecipeSerializer
    public static final RegistryObject<RestructurerRecipeSerializer<RestructurerRecipe>> RESTRUCTURING_SERIALIZER = RECIPE_SERIALIZERS.register(
            "restructuring", () -> new RestructurerRecipeSerializer<>(RestructurerRecipe::new, 200));
    public static final RegistryObject<PurifierRecipeSerializer<PurifierRecipe>> PURIFYING_SERIALIZER = RECIPE_SERIALIZERS.register(
            "purifying", () -> new PurifierRecipeSerializer<>(PurifierRecipe::new, 200));

    public static void registerRecipeTypes() {
        Registry.register(Registry.RECIPE_TYPE, RESTRUCTURING.toString(), RESTRUCTURING);
        Registry.register(Registry.RECIPE_TYPE, PURIFYING.toString(), PURIFYING);
    }
}
