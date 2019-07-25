package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class GaiaFallingBlock extends FallingBlock {

    private int dust;

    public GaiaFallingBlock(MaterialColor color, float hardness, SoundType sound, int dustColor) {
        super(Properties.create(Material.SAND, color).hardnessAndResistance(hardness, 0.0F).harvestTool(ToolType.SHOVEL).sound(sound));

        dust = dustColor;
    }

    @Override
    public int getDustColor(BlockState state) {
        return dust;
    }
}
