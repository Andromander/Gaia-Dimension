package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaAxeItem extends AxeItem {

    public BasicGaiaAxeItem(Tier material, Properties props) {
        super(material, props.attributes(createAttributes(material, 6F, -3.2F)));
    }

    //TODO: This may be redundant, but it may be necessary later for Honing
}
