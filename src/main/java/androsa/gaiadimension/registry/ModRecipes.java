package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.recipe.PurifierRecipe;
import androsa.gaiadimension.recipe.PurifierRecipeSerializer;
import androsa.gaiadimension.recipe.RestructurerRecipe;
import androsa.gaiadimension.recipe.RestructurerRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
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
    public static void registerSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        registerRecipeTypes();
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        registry.register(RESTRUCTURING_SERIALIZER.setRegistryName("restructuring"));
        registry.register(PURIFYING_SERIALIZER.setRegistryName("purifying"));
    }

    public static void registerRecipeTypes() {
        Registry.register(Registry.RECIPE_TYPE, RESTRUCTURING.toString(), RESTRUCTURING);
        Registry.register(Registry.RECIPE_TYPE, PURIFYING.toString(), PURIFYING);
    }
}