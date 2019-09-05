package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class GoldFireBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;

    public GoldFireBlock() {
        super(Properties.create(Material.FIRE, MaterialColor.GOLD).hardnessAndResistance(0.0F).doesNotBlockMovement().tickRandomly().lightValue(15));
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    @Deprecated
    public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(state, world, currentPos) ? this.getDefaultState().with(AGE, state.get(AGE)) : Blocks.AIR.getDefaultState();
    }

    @Override
    @Deprecated
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return Block.hasSolidSide(worldIn.getBlockState(blockpos), worldIn, blockpos, Direction.UP);
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return 30;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (!worldIn.isAreaLoaded(pos, 2)) return;
            if (!state.isValidPosition(worldIn, pos)) {
                worldIn.removeBlock(pos, false);
            }

            BlockState other = worldIn.getBlockState(pos.down());
            boolean flag = other.isFireSource(worldIn, pos.down(), Direction.UP);
            int i = state.get(AGE);
            if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && random.nextFloat() < 0.2F + (float)i * 0.03F) {
                worldIn.removeBlock(pos, false);
            } else {
                int j = Math.min(15, i + random.nextInt(3) / 2);
                if (i != j) {
                    state = state.with(AGE, j);
                    worldIn.setBlockState(pos, state, 4);
                }

                if (!flag) {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + random.nextInt(10));
                    BlockPos blockpos = pos.down();
                    if (!Block.hasSolidSide(worldIn.getBlockState(blockpos), worldIn, blockpos, Direction.UP) || i > 3) {
                        worldIn.removeBlock(pos, false);
                    }

                    if (i == 15 && random.nextInt(4) == 0) {
                        worldIn.removeBlock(pos, false);
                        return;
                    }
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for(int l = -1; l <= 1; ++l) {
                    for(int i1 = -1; i1 <= 1; ++i1) {
                        for(int j1 = -1; j1 <= 4; ++j1) {
                            if (l != 0 || j1 != 0 || i1 != 0) {
                                blockpos$mutableblockpos.setPos(pos).move(l, j1, i1);
                            }
                        }
                    }
                }

            }
        }
    }

    protected boolean canDie(World worldIn, BlockPos pos) {
        return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
    }

    @Override
    @Deprecated
    public void onBlockAdded(BlockState state1, World worldIn, BlockPos pos, BlockState state2, boolean flag) {
        if (state2.getBlock() != state1.getBlock()) {
            if (worldIn.dimension.getType() != DimensionType.OVERWORLD && worldIn.dimension.getType() != GaiaDimensionMod.gaia_dimension || !ModBlocks.gaia_portal.tryToCreatePortal(worldIn, pos)) {
                if (!state1.isValidPosition(worldIn, pos)) {
                    worldIn.removeBlock(pos, false);
                } else {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(24) == 0) {
            worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        for(int i = 0; i < 3; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.addParticle(ParticleTypes.CRIT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
