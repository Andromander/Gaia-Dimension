package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class StorageBlock extends BasicGaiaBlock {

    public StorageBlock(MaterialColor color) {
        this(color, 0);
    }

    public StorageBlock(MaterialColor color, int light) {
        super(Material.IRON, color, 5.0F, 10.0F, SoundType.METAL, ToolType.PICKAXE, 2, light);
    }

    @Override
    public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
