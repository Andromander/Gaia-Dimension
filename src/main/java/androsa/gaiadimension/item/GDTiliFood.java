package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GDTiliFood extends GDFood {

    private final boolean plagued;

    public GDTiliFood(int amount, float saturation, boolean isPlaged) {
        super(amount, saturation, false);
        plagued = isPlaged;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);

        if (plagued) {
            entityLiving.addPotionEffect(new PotionEffect(GDPotions.goldstone_plague, 100));
        }

        return stack;
    }
}
