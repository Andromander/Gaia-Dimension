package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GDGeodeJuice extends GDFood implements ModelRegisterCallback {

    public GDGeodeJuice(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    //TODO: Potion Effects on consumption

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(GDItems.agate_cup);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
}
