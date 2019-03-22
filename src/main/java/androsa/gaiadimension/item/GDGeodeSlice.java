package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GDGeodeSlice extends GDFood implements ModelRegisterCallback {

    public GDGeodeSlice(int amount, float saturation) {
        super(amount, saturation, false);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(GDItems.agate_fabric);
    }
}
