package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class GDMalachiteBrickSlab extends GDGaiaSlab {

    public GDMalachiteBrickSlab(boolean isDouble, Supplier<Item> drop) {
        super(isDouble, Material.ROCK, MapColor.GREEN, SoundType.STONE, "pickaxe", 2, drop);

        this.setHardness(20F);
        this.setResistance(100F);
    }
}
