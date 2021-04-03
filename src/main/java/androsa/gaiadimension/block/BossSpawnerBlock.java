package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.boss.MalachiteGuardSpawnerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BossSpawnerBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(2.0D, 1.0D, 2.0D, 14.0D, 15.0D, 14.0D);
    private BossType bossType;

    public BossSpawnerBlock(BossType type, Properties props) {
        super(props);
        this.bossType = type;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return bossType.getBlockEntity();
    }

    @Override
    @Deprecated
    public boolean triggerEvent(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.triggerEvent(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity != null && tileentity.triggerEvent(id, param);
    }

    @Override
    @Deprecated
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider)tileentity : null;
    }

    @Override
    @Deprecated
    public ItemStack getCloneItemStack(IBlockReader reader, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public enum BossType {
        MALACHITE(MalachiteGuardSpawnerTileEntity::new);

        private Supplier<TileEntity> tileEntity;

        BossType(Supplier<TileEntity> tileentity) {
            this.tileEntity = tileentity;
        }

        public TileEntity getBlockEntity() {
            return tileEntity.get();
        }
    }
}
