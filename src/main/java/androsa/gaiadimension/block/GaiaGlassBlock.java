package androsa.gaiadimension.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class GaiaGlassBlock extends AbstractGlassBlock {

    public GaiaGlassBlock(MaterialColor color, float hardness) {
        super(Properties.create(Material.GLASS, color).hardnessAndResistance(hardness, 0.0F).sound(SoundType.GLASS).nonOpaque());
    }
}
