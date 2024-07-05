package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BasicGaiaSwordItem extends SwordItem {

    public BasicGaiaSwordItem(Tier material, Properties props) {
        super(material, props.attributes(createAttributes(material, 3, -2.4F)));
    }
}
