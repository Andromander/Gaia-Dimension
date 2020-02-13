package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CrystalBloomBlock extends BushBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public CrystalBloomBlock() {
        super(Properties.create(Material.PLANTS, MaterialColor.MAGENTA_TERRACOTTA).hardnessAndResistance(0.0F).sound(SoundType.PLANT).doesNotBlockMovement());
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof AbstractGaiaGrassBlock;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    //TODO: RenderTypeLookup
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.CUTOUT_MIPPED;
//    }
}
