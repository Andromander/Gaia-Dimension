package androsa.gaiadimension;

import androsa.gaiadimension.enchantment.GDEnchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GDEventListener {

    @SubscribeEvent
    private static void isArmorHeavy(InventoryPlayer par0InventoryPlayer, EntityPlayer player) {
        //If the armor set is greater than 8, but is 12 or less, apply slowness
        //If the armor set is greater than 12, apply weakness

        int weight = GDEnchantment.getArmorWeightLevel(par0InventoryPlayer);

        if (weight > 8) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 20));
        }

        if (weight > 12) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 20));
        }
    }
}
