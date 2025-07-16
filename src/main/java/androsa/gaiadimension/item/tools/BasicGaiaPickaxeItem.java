package androsa.gaiadimension.item.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class BasicGaiaPickaxeItem extends Item {

    public BasicGaiaPickaxeItem(ToolMaterial material, Properties props) {
        super(props.pickaxe(material, 1, -2.8F));
    }

    //TODO This class looks redundant, but it will be revisited for honing
}
