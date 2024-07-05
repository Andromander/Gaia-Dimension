package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaPickaxeItem extends PickaxeItem {

    public BasicGaiaPickaxeItem(Tier material, Properties props) {
        super(material, props.attributes(createAttributes(material, 1, -2.8F)));
    }

    //TODO This class looks redundant, but it will be revisited for honing
}
