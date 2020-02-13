package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IShearable;

public class CrystalGrowthBlock extends BushBlock implements IShearable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public CrystalGrowthBlock(MaterialColor color) {
        super(Properties.create(Material.PLANTS, color).hardnessAndResistance(0.0F).sound(SoundType.GLASS).doesNotBlockMovement());
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock() instanceof AbstractGaiaGrassBlock || state.getBlock() instanceof GaiaSoilBlock;
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
//        return BlockRenderLayer.TRANSLUCENT;
//    }
}
