package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.PurifierTileEntity;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;

import java.util.*;

public class NullFuelRecipeMaker {

    private NullFuelRecipeMaker() {
    }

    public static List<NullFuelRecipe> getNullRecipes(IIngredientManager manager, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        Collection<ItemStack> allStacks = manager.getAllIngredients(VanillaTypes.ITEM);
        List<NullFuelRecipe> nullFuel = new ArrayList<>();
        Iterator iterator = allStacks.iterator();

        while(iterator.hasNext()) {
            ItemStack stack = (ItemStack)iterator.next();
            int burnTime = getBurnTime(stack);
            if (burnTime > 0) {
                nullFuel.add(new NullFuelRecipe(guiHelper, Collections.singleton(stack), burnTime));
            }
        }
        return nullFuel;
    }

    private static int getBurnTime(ItemStack itemStack) {
        try {
            return PurifierTileEntity.getThirdFuelBurnTime().getOrDefault(itemStack.getItem(), 0);
        } catch (LinkageError | RuntimeException var3) {
            GaiaDimensionMod.LOGGER.error("Failed to check if item is fuel {}.", itemStack.getItem().getRegistryName(), var3);
            return 0;
        }
    }
}
