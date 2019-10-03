package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
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

    /*
    //===GLITTER FURNACE===//
    public static void addGlitterRefactoring(Block input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipe.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(Item input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipe.instance().glittering(input, output1, output2, xp);
    }

    public static void addGlitterRefactoring(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        RestructurerRecipe.instance().glittering(input, output1, output2, xp);
    }

    //===PURIFIER===//
    public static void addPurifying(Block input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipe.instance().purifying(input, output1, output2, xp);
    }

    public static void addPurifying(Item input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipe.instance().purifying(input, output1, output2, xp);
    }

    public static void addPurifying(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        PurifierRecipe.instance().purifying(input, output1, output2, xp);
    }*/
}
