package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.item.ItemFood;

public class GDFood extends ItemFood implements ModelRegisterCallback {

    public GDFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }
}
