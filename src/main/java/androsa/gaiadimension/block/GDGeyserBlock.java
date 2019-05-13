package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.TileEntityGeyser;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GDGeyserBlock extends GDBlock implements ITileEntityProvider {

    public GDGeyserBlock() {
        super(Material.ROCK, MapColor.SILVER);
        setHardness(5.0F);
        setResistance(10.0F);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityGeyser();
    }
}
