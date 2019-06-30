package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class GaiaFallingBlock extends FallingBlock {

    private int dust;

    public GaiaFallingBlock(MaterialColor color, float hardness, SoundType sound, int dustColor) {
        super(Properties.create(Material.SAND, color).hardnessAndResistance(hardness, 0.0F).sound(sound));

        dust = dustColor;
    }

    @Nullable
    @Override
    public ToolType getHarvestTool(BlockState state) {
        return ToolType.SHOVEL;
    }

    @Override
    public int getDustColor(BlockState state) {
        return dust;
    }
}
