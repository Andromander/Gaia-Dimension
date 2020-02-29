package androsa.gaiadimension.block;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class AgateLeavesBlock extends LeavesBlock {

    public AgateLeavesBlock(MaterialColor color) {
        this(color, 0);
    }

    public AgateLeavesBlock(MaterialColor color, int light) {
        super(Properties.create(Material.LEAVES, color).hardnessAndResistance(0.3F, 0.0F).sound(SoundType.GLASS).lightValue(light).nonOpaque());
    }
}
