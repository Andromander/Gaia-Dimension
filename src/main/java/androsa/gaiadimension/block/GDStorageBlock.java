package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GDStorageBlock extends Block implements ModelRegisterCallback {

    public GDStorageBlock(MapColor color) {
        super(Material.IRON, color);

        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(10.0F);
        setCreativeTab(GDTabs.tabBlock);
    }

    public boolean isBeaconBase(IBlockAccess access, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
