package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipes {
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
    @ObjectHolder("gaiadimension:restructuring")
    public static final RestructurerRecipeSerializer<RestructurerRecipe> RESTRUCTURING_SERIALIZER = new RestructurerRecipeSerializer<>(RestructurerRecipe::new, 200);
    @ObjectHolder("gaiadimension:purifying")
    public static final PurifierRecipeSerializer<PurifierRecipe> PURIFYING_SERIALIZER = new PurifierRecipeSerializer<>(PurifierRecipe::new, 200);

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> e) {
        e.getRegistry().register(RESTRUCTURING_SERIALIZER.setRegistryName("restructuring"));
        e.getRegistry().register(PURIFYING_SERIALIZER.setRegistryName("purifying"));
    }

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
