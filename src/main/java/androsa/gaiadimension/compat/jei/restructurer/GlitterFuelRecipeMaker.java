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

public class GlitterFuelRecipeMaker {

    public static List<GlitterFuelRecipe> getGlitterRecipes(JEICompatUtils registry, IJeiHelpers helpers) {
        IGuiHelper guiHelper = helpers.getGuiHelper();
        IStackHelper stackHelper = helpers.getStackHelper();
        List<ItemStack> fuel = registry.getGoldFuels();
        List<GlitterFuelRecipe> glitterFuel = new ArrayList<>(fuel.size());
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

                    int burnTimeR = getGlitterTimeR(oreDictFuelSet.get(0));
                    int burnTimeP = getGlitterTimeP(oreDictFuelSet.get(0));

                    if (burnTimeR > 0) {
                        glitterFuel.add(new GlitterFuelRecipe(guiHelper, oreDictFuelSet, burnTimeR));
                    } else if (burnTimeP > 0) {
                        glitterFuel.add(new GlitterFuelRecipe(guiHelper, oreDictFuelSet, burnTimeP));
                    }
                }
            } else {
                List<ItemStack> subtypes = stackHelper.getSubtypes(stack);
                List<ItemStack> fuels = new ArrayList<>();

                for (ItemStack subtype : subtypes) {
                    if (TileEntityRestructurer.getFuelBurnTime(subtype) > 0) {
                        fuels.add(subtype);
                    } else if (TileEntityPurifier.getFuelBurnTime(subtype) > 0) {
                        fuels.add(subtype);
                    }
                }

                if (fuels.isEmpty())
                    continue;

                int burnTimeR = getGlitterTimeR(fuels.get(0));
                int burnTimeP = getGlitterTimeP(fuels.get(0));

                if (burnTimeR > 0) {
                    glitterFuel.add(new GlitterFuelRecipe(guiHelper, fuels, burnTimeR));
                } else if (burnTimeP > 0) {
                    glitterFuel.add(new GlitterFuelRecipe(guiHelper, fuels, burnTimeP));
                }
            }
        }
        return glitterFuel;
    }

    private static void removeNonFuel(Collection<ItemStack> stacks) {
        stacks.removeIf(stack -> getGlitterTimeR(stack) == 0 || getGlitterTimeP(stack) == 0);
    }

    private static int getGlitterTimeR(ItemStack stack) {
        return TileEntityRestructurer.getFuelBurnTime(stack);
    }

    private static int getGlitterTimeP(ItemStack stack) {
        return TileEntityPurifier.getFuelBurnTime(stack);
    }
}
