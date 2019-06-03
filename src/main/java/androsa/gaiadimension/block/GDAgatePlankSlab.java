package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class GDAgatePlankSlab extends GDGaiaSlab {

    public GDAgatePlankSlab(boolean isDouble, MapColor color, Supplier<Item> drop) {
        super(isDouble, Material.WOOD, color, SoundType.STONE, "axe", 0, drop);

        this.setHardness(1.5F);
        this.setResistance(2.0F);
    }
}

