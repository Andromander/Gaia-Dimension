package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.boss.MalachiteGuardSpawnerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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

    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 15.0D, 14.0D);
    private BossType bossType;

    public BossSpawnerBlock(BossType type) {
        super(Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(-1F).sound(SoundType.METAL).nonOpaque().noDrops());
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
        return bossType.getTileEntity();
    }

    @Override
    @Deprecated
    public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    @Override
    @Deprecated
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider)tileentity : null;
    }

    @Override
    @Deprecated
    public ItemStack getItem(IBlockReader reader, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public enum BossType {
        MALACHITE(MalachiteGuardSpawnerTileEntity::new);

        private Supplier<TileEntity> tileEntity;

        BossType(Supplier<TileEntity> tileentity) {
            this.tileEntity = tileentity;
        }

        public TileEntity getTileEntity() {
            return tileEntity.get();
        }
    }
}
