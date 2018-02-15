package androsa.gaiadimension.enchantment;

import androsa.gaiadimension.item.GDMalachiteGuardArmor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class GDEnchantment extends Enchantment {

    protected GDEnchantment(Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slots) {
        super(rarity, type, slots);
    }

    /* Each Armor Set has it's own weight, and the player has a weight threshold.
    If the player is below or exact to the weight threshold, nothing will happen
    But, once the player exceeds the weight threshold, they will be slowed
    The threshold for a player is 8, with each piece at Weight 2. If it is greater than that, apply slowness.
    As an example, Weight 1 sets incur nothing. Weight 2 is the limit, so anything more and the player is affected.
    Weight 3 means the player will be affected by Slowness I
    Weight 4 means the player will be affected by Weakness I along with Slowness I
     */
    public static int getArmorWeightLevel(InventoryPlayer par0InventoryPlayer) {
        int modifier = 0;

        for (ItemStack armor : par0InventoryPlayer.armorInventory) {
            if (!armor.isEmpty() && armor.getItem() instanceof GDMalachiteGuardArmor) {
                modifier += 5;
            }
        }
        return modifier;
    }
}
