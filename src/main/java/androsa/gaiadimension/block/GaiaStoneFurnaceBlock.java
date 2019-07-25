package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.GaiaStoneFurnaceTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class GaiaStoneFurnaceBlock extends FurnaceBlock {

    public GaiaStoneFurnaceBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(20.0F, 300.0F).doesNotBlockMovement().harvestTool(ToolType.PICKAXE).harvestLevel(0).lightValue(13));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GaiaStoneFurnaceTileEntity();
    }

    @Override
    protected void interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = world.getTileEntity(pos);
        if (tileentity instanceof GaiaStoneFurnaceTileEntity) {
            player.openContainer((INamedContainerProvider)tileentity);
        }
    }
}
