package androsa.gaiadimension.item;

public interface HoningEquipment {

    /*
     * If the tool can be honed. Generally, this will be used to test if equipment has an enchantment.
     * In case later on I consider it, other conditions that prevent honing.
     */
    boolean canHone();

    /*
     * If the tool has been honed. Generally, this will be true if it has been honed.
     * That's it. This will be used to check if it can be enchanted - no enchanting if this is true.
     */
    boolean isHoned();
}
