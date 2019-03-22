package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GDStorageBlock extends GDBlock {

    public GDStorageBlock(MapColor color) {
        super(Material.IRON, color, SoundType.METAL);

        setHardness(5.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 2);
    }

    public boolean isBeaconBase(IBlockAccess access, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
