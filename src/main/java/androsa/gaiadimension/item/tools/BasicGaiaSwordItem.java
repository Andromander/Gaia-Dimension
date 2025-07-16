package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class BasicGaiaSwordItem extends Item {

    public BasicGaiaSwordItem(ToolMaterial material, Properties props) {
        super(props.sword(material, 3, -2.4F));
    }
}
