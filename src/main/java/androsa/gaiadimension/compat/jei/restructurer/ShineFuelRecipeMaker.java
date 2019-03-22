package androsa.gaiadimension.compat.jei.restructurer;

import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.block.tileentity.TileEntityRestructurer;
import androsa.gaiadimension.compat.jei.JEICompatUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class ShineFuelRecipeMaker {

    public static List<ShineFuelRecipe> getShineRecipes(JEICompatUtils registry, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        IStackHelper stackHelper = helpers.getStackHelper();
        List<ItemStack> fuel = registry.getShineFuels();
        List<ShineFuelRecipe> shineFuel = new ArrayList<>(fuel.size());
        Set<String> oreDictNames = new HashSet<>();

        for (ItemStack stack : fuel) {
            if (fuel == null)
                continue;

            int[] oreIDs = OreDictionary.getOreIDs(stack);

            if (oreIDs.length > 0) {
                for (int oreID : oreIDs) {
                    String name = OreDictionary.getOreName(oreID);
                    if (oreDictNames.contains(name))
                        continue;

                    oreDictNames.add(name);
                    List<ItemStack> oreDictFuel = OreDictionary.getOres(name);
                    List<ItemStack> oreDictFuelSet = stackHelper.getAllSubtypes(oreDictFuel);
                    removeNonFuel(oreDictFuelSet);

                    if (oreDictFuelSet.isEmpty())
                        continue;

                    int burnTimeR = getShineTimeR(oreDictFuelSet.get(0));
                    int burnTimeP = getShineTimeP(oreDictFuelSet.get(0));

                    if (burnTimeR > 0) {
                        shineFuel.add(new ShineFuelRecipe(guiHelper, oreDictFuelSet, burnTimeR));
                    } else if (burnTimeP > 0) {
                        shineFuel.add(new ShineFuelRecipe(guiHelper, oreDictFuelSet, burnTimeP));
                    }
                }
            } else {
                List<ItemStack> subtypes = stackHelper.getSubtypes(stack);
                List<ItemStack> fuels = new ArrayList<>();

                for (ItemStack subtype : subtypes) {
                    if (TileEntityRestructurer.getSecondFuelBurnTime(subtype) > 0) {
                        fuels.add(subtype);
                    } else if (TileEntityPurifier.getSecondFuelBurnTime(subtype) > 0) {
                        fuels.add(subtype);
                    }
                }

                if (fuels.isEmpty())
                    continue;

                int burnTimeR = getShineTimeR(fuels.get(0));
                int burnTimeP = getShineTimeP(fuels.get(0));

                if (burnTimeR > 0) {
                    shineFuel.add(new ShineFuelRecipe(guiHelper, fuels, burnTimeR));
                } else if (burnTimeP > 0) {
                    shineFuel.add(new ShineFuelRecipe(guiHelper, fuels, burnTimeP));
                }
            }
        }
        return shineFuel;
    }

    private static void removeNonFuel(Collection<ItemStack> stacks) {
        stacks.removeIf(stack -> getShineTimeR(stack) == 0 || getShineTimeP(stack) == 0);
    }

    private static int getShineTimeR(ItemStack stack) {
        return TileEntityRestructurer.getSecondFuelBurnTime(stack);
    }

    private static int getShineTimeP(ItemStack stack) {
        return TileEntityPurifier.getSecondFuelBurnTime(stack);
    }
}
