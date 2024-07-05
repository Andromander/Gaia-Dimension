package androsa.gaiadimension.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class BasicGaiaArmorItem extends ArmorItem {

    public BasicGaiaArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Properties props) {
        super(material, slot, props);
    }

    //TODO: Yes, this looks redundant, but keep it here for Honing
}
