package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class ShineFuelRecipeMaker {

    private ShineFuelRecipeMaker() {
    }

    public static List<ShineFuelRecipe> getShineRecipes(IIngredientManager manager, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        Collection<ItemStack> allStacks = manager.getAllIngredients(VanillaTypes.ITEM);
        List<ShineFuelRecipe> shineFuel = new ArrayList<>();

        for (ItemStack stack : allStacks) {
            int burnTime = getBurnTime(stack);
            if (burnTime > 0) {
                shineFuel.add(new ShineFuelRecipe(guiHelper, Collections.singleton(stack), burnTime));
            }
        }

        return shineFuel;
    }

    private static int getBurnTime(ItemStack itemStack) {
        try {
            return RestructurerBlockEntity.getSecondFuelBurnTime().getOrDefault(itemStack.getItem(), 0);
        } catch (LinkageError | RuntimeException var3) {
            GaiaDimensionMod.LOGGER.error("Failed to check if item is fuel {}.", itemStack.getItem().getRegistryName(), var3);
            return 0;
        }
    }
}
