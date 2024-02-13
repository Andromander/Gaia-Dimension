package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.PurifierBlockEntity;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.List;

public class NullFuelRecipeMaker {

    private NullFuelRecipeMaker() {
    }

    public static List<NullFuelRecipe> getNullRecipes(IIngredientManager manager) {
        return manager.getAllItemStacks().stream()
                .<NullFuelRecipe>mapMulti((stack, consumer) -> {
                    int burnTime = getBurnTime(stack);
                    if (burnTime > 0) {
                        consumer.accept(new NullFuelRecipe(List.of(stack), burnTime));
                    }
                })
                .sorted(Comparator.comparingInt(NullFuelRecipe::getBurnTime))
                .toList();
    }

    private static int getBurnTime(ItemStack itemStack) {
        try {
            return PurifierBlockEntity.getThirdFuelBurnTime().getOrDefault(itemStack.getItem(), 0);
        } catch (LinkageError | RuntimeException var3) {
            GaiaDimensionMod.LOGGER.error("Failed to check if item is fuel {}.", itemStack.getItem().toString(), var3);
            return 0;
        }
    }
}
