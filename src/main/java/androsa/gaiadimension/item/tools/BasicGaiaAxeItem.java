package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;

public class BasicGaiaAxeItem extends AxeItem {

    public BasicGaiaAxeItem(ToolMaterial material, Properties props) {
        super(material, 6F, -3.2F, props);
    }

    //TODO: This may be redundant, but it may be necessary later for Honing
}
