package androsa.gaiadimension.honing;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

public class HoningEventHandler {

    public void equipmentChange(LivingEquipmentChangeEvent evt) {
        //Whenever the player switches equipment, this event will recalculate what the player has.

        //Check if the item is a valid item - don't want to check every change, just the relevant one
        if (evt.getFrom().getItem() instanceof HoningEquipment || evt.getTo().getItem() instanceof HoningEquipment) {

            //Only account for humanoid armor and main hand. Off-hand and non-human don't count
            if (evt.getSlot().getType() == EquipmentSlot.Type.HUMANOID_ARMOR || evt.getSlot() == EquipmentSlot.MAINHAND) {

                //Calculate skills available. There are skill caps. How can we check the overall skills available on the player?
                // Maybe a keybind that a player can hold when hovered over valid equipment?
            }
        }
    }
}
