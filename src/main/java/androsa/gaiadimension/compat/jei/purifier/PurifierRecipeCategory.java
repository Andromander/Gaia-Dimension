package androsa.gaiadimension.compat.jei.purifier;

import mezz.jei.api.recipe.category.IRecipeCategory;

public abstract class PurifierRecipeCategory<T> implements IRecipeCategory<T> {
    protected static final int inputSlot = 0;
    protected static final int nullFuelSlot = 3;
    protected static final int outputSlot1 = 4;
    protected static final int outputSlot2 = 5;
}
