package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class AgatePlanksBlock extends BasicGaiaBlock {

    public AgatePlanksBlock(MaterialColor color) {
        this(color, 0);
    }

    public AgatePlanksBlock(MaterialColor color, int light) {
        super(Material.WOOD, color, 10.0F, 150.0F, SoundType.STONE, ToolType.AXE, 0, light);
    }
}
