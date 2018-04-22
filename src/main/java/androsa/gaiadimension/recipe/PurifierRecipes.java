package androsa.gaiadimension.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

public class PurifierRecipes {

    private static final PurifierRecipes purifyingBase = new PurifierRecipes();
    private final Map<ItemStack, ItemStack[]> purifyingList = new HashMap<ItemStack, ItemStack[]>();
    private final Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

    public static PurifierRecipes instance() {
        return purifyingBase;
    }

    private PurifierRecipes() {}

    public void purifying(Block input, ItemStack output1, ItemStack output2, float xp) {
        purifying(Item.getItemFromBlock(input), output1, output2, xp);
    }

    public void purifying(Item input, ItemStack output1, ItemStack output2, float xp) {
        purifying(new ItemStack(input, 1, OreDictionary.WILDCARD_VALUE), output1, output2, xp);
    }

    public void purifying(ItemStack input, ItemStack output1, ItemStack output2, float xp) {
        purifyingList.put(input, new ItemStack[]{ output1, output2 });
        experienceList.put(output1, Float.valueOf(xp));
    }

    public ItemStack[] getPurifyingResult(ItemStack stack) {
        for (Map.Entry<ItemStack, ItemStack[]> entry : purifyingList.entrySet()) {
            if (areStacksEqual(stack, entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    private boolean areStacksEqual(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == par1ItemStack.getItem() && (par2ItemStack.getItemDamage() == OreDictionary.WILDCARD_VALUE|| par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage());
    }

    public Map<ItemStack, ItemStack[]> getGlitteringList() {
        return purifyingList;
    }

    public float getExperience(ItemStack stack) {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Map.Entry<ItemStack, Float> entry : experienceList.entrySet()) {
            if (areStacksEqual(stack, entry.getKey()));
            return entry.getValue().floatValue();
        }

        return 0.0F;
    }
}
