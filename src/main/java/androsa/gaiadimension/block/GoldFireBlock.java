package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModGaiaConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class GoldFireBlock extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;

    public GoldFireBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, world, currentPos) ? this.defaultBlockState().setValue(AGE, state.getValue(AGE)) : Blocks.AIR.defaultBlockState();
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return worldIn.getBlockState(blockpos).isFaceSturdy(worldIn, blockpos, Direction.UP);
    }

    public int tickRate() {
        return 30;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            if (!worldIn.isAreaLoaded(pos, 2)) return;
            if (!state.canSurvive(worldIn, pos)) {
                worldIn.removeBlock(pos, false);
            }

            BlockState other = worldIn.getBlockState(pos.below());
            boolean flag = other.isFireSource(worldIn, pos.below(), Direction.UP);
            int i = state.getValue(AGE);
            if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && random.nextFloat() < 0.2F + (float)i * 0.03F) {
                worldIn.removeBlock(pos, false);
            } else {
                int j = Math.min(15, i + random.nextInt(3) / 2);
                if (i != j) {
                    state = state.setValue(AGE, j);
                    worldIn.setBlock(pos, state, 4);
                }

                if (!flag) {
                    worldIn.getBlockTicks().scheduleTick(pos, this, this.tickRate() + random.nextInt(10));
                    BlockPos blockpos = pos.below();
                    if (!worldIn.getBlockState(blockpos).isFaceSturdy(worldIn, blockpos, Direction.UP) || i > 3) {
                        worldIn.removeBlock(pos, false);
                    }

                    if (i == 15 && random.nextInt(4) == 0) {
                        worldIn.removeBlock(pos, false);
                        return;
                    }
                }

                BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

                for(int l = -1; l <= 1; ++l) {
                    for(int i1 = -1; i1 <= 1; ++i1) {
                        for(int j1 = -1; j1 <= 4; ++j1) {
                            if (l != 0 || j1 != 0 || i1 != 0) {
                                blockpos$mutableblockpos.set(pos).move(l, j1, i1);
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
    public void onPlace(BlockState state1, World worldIn, BlockPos pos, BlockState state2, boolean flag) {
        if (state2.getBlock() != state1.getBlock()) {
            if (!worldIn.dimension().location().equals(ModGaiaConfig.startDimRL) && worldIn.dimension() != ModDimensions.gaia_world || !ModBlocks.gaia_portal.get().tryToCreatePortal(worldIn, pos)) {
                if (!state1.canSurvive(worldIn, pos)) {
                    worldIn.removeBlock(pos, false);
                } else {
                    worldIn.getBlockTicks().scheduleTick(pos, this, this.tickRate() + worldIn.random.nextInt(10));
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(24) == 0) {
            worldIn.playLocalSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        for(int i = 0; i < 3; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double)pos.getZ() + rand.nextDouble();
            worldIn.addParticle(ParticleTypes.CRIT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
