package androsa.gaiadimension.honing;

import androsa.gaiadimension.item.HoningEquipment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;

public class HoningEventHandler {

    public void equipmentChange(LivingEquipmentChangeEvent evt) {
        //Whenever the player switches equipment, this event will recalculate what the player has.

        //Off-hand is ignored as Honed equipment in this slot are not accounted for.
        if (evt.getSlot() != EquipmentSlot.OFFHAND) {
            //Will only run if the changing items are Honed. If neither are honed equipment, skip.
            if (evt.getFrom().getItem() instanceof HoningEquipment || evt.getTo().getItem() instanceof HoningEquipment) {
                //calculate equipment
            }
        }
    }
}
