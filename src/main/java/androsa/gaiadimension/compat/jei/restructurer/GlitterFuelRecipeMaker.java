package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.RestructurerBlockEntity;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.List;

public class GlitterFuelRecipeMaker {

    private GlitterFuelRecipeMaker() {
    }

    public static List<GlitterFuelRecipe> getGlitterRecipes(IIngredientManager manager) {
        return manager.getAllItemStacks().stream()
                .<GlitterFuelRecipe>mapMulti((stack, consumer) -> {
                    int burnTime = getBurnTime(stack);
                    if (burnTime > 0) {
                        consumer.accept(new GlitterFuelRecipe(List.of(stack), burnTime));
                    }
                })
                .sorted(Comparator.comparingInt(GlitterFuelRecipe::getBurnTime))
                .toList();
    }

    private static int getBurnTime(ItemStack itemStack) {
        try {
            return RestructurerBlockEntity.getFuelBurnTime(itemStack.getItem());
        } catch (LinkageError | RuntimeException var3) {
            GaiaDimensionMod.LOGGER.error("Failed to check if item is fuel {}.", itemStack.getItem().toString(), var3);
            return 0;
        }
    }
}
