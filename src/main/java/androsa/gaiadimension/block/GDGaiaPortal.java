package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDFluids;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import com.google.common.cache.LoadingCache;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDGaiaPortal extends BlockPortal implements ModelRegisterCallback {

    protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public GDGaiaPortal() {
        super();

        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean trySpawnPortal(World par1World, BlockPos pos) {
        GDGaiaPortal.Size blockportal$size = new GDGaiaPortal.Size(par1World, pos, EnumFacing.Axis.X);
        if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
            blockportal$size.placePortalBlocks();
            return true;
        } else {
            GDGaiaPortal.Size blockportal$size1 = new GDGaiaPortal.Size(par1World, pos, EnumFacing.Axis.Z);

            if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
                blockportal$size1.placePortalBlocks();
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X) {
            GDGaiaPortal.Size blockportal$size = new GDGaiaPortal.Size(world, pos, EnumFacing.Axis.X);

            if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        } else if (enumfacing$axis == EnumFacing.Axis.Z) {
            GDGaiaPortal.Size blockportal$size1 = new GDGaiaPortal.Size(world, pos, EnumFacing.Axis.Z);

            if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch ((EnumFacing.Axis) state.getValue(AXIS)) {
            case X:
                return X_AABB;
            case Y:
            default:
                return Y_AABB;
            case Z:
                return Z_AABB;
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        for (int l = 0; l < 4; ++l) {
            double d0 = (double) ((float) x + rand.nextFloat());
            double d1 = (double) ((float) y + rand.nextFloat());
            double d2 = (double) ((float) z + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;

            if (worldIn.getBlockState(pos.add(-1, 0, 0)).getBlock() != this && worldIn.getBlockState(pos.add(1, 0, 0)).getBlock() != this) {
                d0 = (double) x + 0.5D + 0.25D * (double) i1;
                d3 = (double) (rand.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) z + 0.5D + 0.25D * (double) i1;
                d5 = (double) (rand.nextFloat() * 2.0F * (float) i1);
            }
        }
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        pos = pos.offset(side);
        EnumFacing.Axis enumfacing$axis = null;

        if (blockState.getBlock() == this) {
            enumfacing$axis = (EnumFacing.Axis) blockState.getValue(AXIS);

            if (enumfacing$axis == null) {
                return false;
            }

            if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST) {
                return false;
            }

            if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
                return false;
            }
        }

        boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
        boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;
        return flag4 && side == EnumFacing.WEST ? true : (flag4 && side == EnumFacing.EAST ? true : (flag5 && side == EnumFacing.NORTH ? true : flag5 && side == EnumFacing.SOUTH));
    }

    public BlockPattern.PatternHelper createPatternHelper(World p_181089_1_, BlockPos p_181089_2_) {
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
        GDGaiaPortal.Size blockportal$size = new GDGaiaPortal.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.X);
        LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(p_181089_1_, true);

        if (!blockportal$size.isValid()) {
            enumfacing$axis = EnumFacing.Axis.X;
            blockportal$size = new GDGaiaPortal.Size(p_181089_1_, p_181089_2_, EnumFacing.Axis.Z);
        }

        if (!blockportal$size.isValid()) {
            return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[EnumFacing.AxisDirection.values().length];
            EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
            BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

            for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
                BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

                for (int i = 0; i < blockportal$size.getWidth(); ++i) {
                    for (int j = 0; j < blockportal$size.getHeight(); ++j) {
                        BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);

                        if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR) {
                            ++aint[enumfacing$axisdirection.ordinal()];
                        }
                    }
                }
            }

            EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;

            for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
                if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
                    enumfacing$axisdirection1 = enumfacing$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
        }
    }

    public static class Size {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing rightDir;
        private final EnumFacing leftDir;
        private int portalBlockCount = 0;
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
            world = worldIn;
            axis = p_i45694_3_;

            if (p_i45694_3_ == EnumFacing.Axis.X) {
                leftDir = EnumFacing.EAST;
                rightDir = EnumFacing.WEST;
            } else {
                leftDir = EnumFacing.NORTH;
                rightDir = EnumFacing.SOUTH;
            }

            for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
                ;
            }

            int i = getDistanceUntilEdge(p_i45694_2_, leftDir) - 1;

            if (i >= 0) {
                bottomLeft = p_i45694_2_.offset(leftDir, i);
                width = getDistanceUntilEdge(bottomLeft, rightDir);

                if (width < 2 || width > 21) {
                    bottomLeft = null;
                    width = 0;
                }
            }

            if (bottomLeft != null) {
                height = calculatePortalHeight();
            }
        }

        protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

                if (!isEmptyBlock(world.getBlockState(blockpos).getBlock()) || world.getBlockState(blockpos.down()).getBlock() != GDBlocks.hematiteBlock) {
                    break;
                }
            }

            Block block = world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
            return block == GDBlocks.hematiteBlock ? i : 0;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        protected int calculatePortalHeight() {
            label24:

            for (height = 0; height < 21; ++height) {
                for (int i = 0; i < width; ++i) {
                    BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
                    Block block = world.getBlockState(blockpos).getBlock();

                    if (!isEmptyBlock(block)) {
                        break label24;
                    }

                    if (block == GDBlocks.gaiaPortal) {
                        ++portalBlockCount;
                    }

                    if (i == 0) {
                        block = world.getBlockState(blockpos.offset(leftDir)).getBlock();

                        if (block != GDBlocks.gaiaPortal) {
                            break label24;
                        }
                    } else if (i == width - 1) {
                        block = world.getBlockState(blockpos.offset(rightDir)).getBlock();

                        if (block != GDBlocks.gaiaPortal) {
                            break label24;
                        }
                    }
                }
            }

            for (int j = 0; j < width; ++j) {
                if (world.getBlockState(bottomLeft.offset(rightDir, j).up(height)).getBlock() != GDBlocks.hematiteBlock) {
                    height = 0;
                    break;
                }
            }

            if (height <= 21 && height >= 3) {
                return height;
            } else {
                bottomLeft = null;
                width = 0;
                height = 0;
                return 0;
            }
        }

        protected boolean isEmptyBlock(Block blockIn) {
            return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == GDFluids.mineralWaterBlock || blockIn == GDBlocks.gaiaPortal;
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < width; ++i) {
                BlockPos blockpos = bottomLeft.offset(rightDir, i);

                for (int j = 0; j < height; ++j) {
                    world.setBlockState(blockpos.up(j), GDBlocks.gaiaPortal.getDefaultState().withProperty(GDGaiaPortal.AXIS, axis), 2);
                }
            }
        }
    }

}
