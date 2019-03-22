package androsa.gaiadimension.compat.jei;

import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import com.google.common.collect.ImmutableList;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static androsa.gaiadimension.compat.jei.GDRecipeCategoryUid.*;

public class JEICompatUtils {

    private final ImmutableList<ItemStack> goldFuels;
    private final ImmutableList<ItemStack> shineFuels;
    private final ImmutableList<ItemStack> nullFuels;

    public JEICompatUtils(IIngredientRegistry registry) {
        List<ItemStack> goldList = new ArrayList<>();
        List<ItemStack> shineList = new ArrayList<>();
        List<ItemStack> nullList = new ArrayList<>();

        for (ItemStack stack : registry.getAllIngredients(VanillaTypes.ITEM)) {
            addItemStack(stack, GOLD, goldList);
            addItemStack(stack, SHINE, shineList);
            addItemStack(stack, NULLING, nullList);
        }

        goldFuels = ImmutableList.copyOf(goldList);
        shineFuels = ImmutableList.copyOf(shineList);
        nullFuels = ImmutableList.copyOf(nullList);
    }

    public ImmutableList<ItemStack> getGoldFuels() {
        return goldFuels;
    }

    public ImmutableList<ItemStack> getShineFuels() {
        return shineFuels;
    }

    public ImmutableList<ItemStack> getNullFuels() {
        return nullFuels;
    }

    private void addItemStack(ItemStack stack, String type, List<ItemStack> fuel) {

        switch (type) {
            case GOLD:
                if (TileEntityRestructurer.getFuelBurnTime(stack) > 0 || TileEntityPurifier.getFuelBurnTime(stack) > 0)
                    fuel.add(stack);
                break;
            case SHINE:
                if (TileEntityRestructurer.getSecondFuelBurnTime(stack) > 0 || TileEntityPurifier.getSecondFuelBurnTime(stack) > 0)
                    fuel.add(stack);
            case NULLING:
                if (TileEntityPurifier.getThirdFuelBurnTime(stack) > 0)
                    fuel.add(stack);
            default:
                break;
        }
    }
}
