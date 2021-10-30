package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class GlitterFuelRecipeMaker {

    private GlitterFuelRecipeMaker() {
    }

    public static List<GlitterFuelRecipe> getGlitterRecipes(IIngredientManager manager, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        Collection<ItemStack> allStacks = manager.getAllIngredients(VanillaTypes.ITEM);
        List<GlitterFuelRecipe> glitterFuel = new ArrayList<>();

        for (ItemStack stack : allStacks) {
            int burnTime = getBurnTime(stack);
            if (burnTime > 0) {
                glitterFuel.add(new GlitterFuelRecipe(guiHelper, Collections.singleton(stack), burnTime));
            }
        }

        return glitterFuel;
    }

    private static int getBurnTime(ItemStack itemStack) {
        try {
            return RestructurerBlockEntity.getFuelBurnTime().getOrDefault(itemStack.getItem(), 0);
        } catch (LinkageError | RuntimeException var3) {
            GaiaDimensionMod.LOGGER.error("Failed to check if item is fuel {}.", itemStack.getItem().getRegistryName(), var3);
            return 0;
        }
    }
}
