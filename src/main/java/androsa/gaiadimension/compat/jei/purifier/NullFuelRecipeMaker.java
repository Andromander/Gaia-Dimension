package androsa.gaiadimension.compat.jei.purifier;

import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.compat.jei.JEICompatUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class NullFuelRecipeMaker {

    public static List<NullFuelRecipe> getNullRecipes(JEICompatUtils registry, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        IStackHelper stackHelper = helpers.getStackHelper();
        List<ItemStack> fuel = registry.getNullFuels();
        List<NullFuelRecipe> nullFuel = new ArrayList<>(fuel.size());
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

                    int burnTime = getNullTime(oreDictFuelSet.get(0));

                    if (burnTime > 0)
                        nullFuel.add(new NullFuelRecipe(guiHelper, oreDictFuelSet, burnTime));
                }
            } else {
                List<ItemStack> subtypes = stackHelper.getSubtypes(stack);
                List<ItemStack> fuels = new ArrayList<>();

                for (ItemStack subtype : subtypes) {
                    if (TileEntityPurifier.getThirdFuelBurnTime(subtype) > 0)
                        fuels.add(subtype);
                }

                if (fuels.isEmpty())
                    continue;

                int burnTime = getNullTime(fuels.get(0));

                if (burnTime > 0)
                    nullFuel.add(new NullFuelRecipe(guiHelper, fuels, burnTime));
            }
        }
        return nullFuel;
    }

    private static void removeNonFuel(Collection<ItemStack> stacks) {
        stacks.removeIf(stack -> getNullTime(stack) == 0);
    }

    private static int getNullTime(ItemStack stack) {
        return TileEntityPurifier.getThirdFuelBurnTime(stack);
    }
}
