package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.GeyserTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class GeyserBlock extends BasicGaiaBlock {

    public GeyserBlock() {
        super(Material.ROCK, MaterialColor.IRON, 5.0F, 10.0F, ToolType.PICKAXE, 1);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GeyserTileEntity();
    }
}
