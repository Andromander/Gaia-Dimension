package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GDGeodeJuice extends GDFood implements ModelRegisterCallback {

    private final JuiceType juiceType;

    public GDGeodeJuice(int amount, float saturation, JuiceType juice) {
        super(amount, saturation, false);

        setAlwaysEdible();
        juiceType = juice;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        EntityPlayer player = (EntityPlayer)entityLiving;
        ItemStack cup = new ItemStack(GDItems.agate_cup);

        super.onItemUseFinish(stack, worldIn, entityLiving);

        switch (juiceType) {
            case JUICE:
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
                break;
            case TEA:
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 100));
                break;
            case ALE:
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100));
                break;
            case SODA:
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 100));
                break;
            case ELIXIR:
                entityLiving.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100));
                break;
        }

        if (!player.inventory.addItemStackToInventory(cup.copy())) {
            player.dropItem(cup, false);
        }

        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public enum JuiceType {
        JUICE,
        TEA,
        ALE,
        SODA,
        ELIXIR
    }
}
