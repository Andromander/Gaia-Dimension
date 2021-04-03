package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModGaiaConfig;
import androsa.gaiadimension.registry.ModParticles;
import androsa.gaiadimension.world.GaiaTeleporter;
import com.google.common.cache.LoadingCache;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class GaiaPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public GaiaPortalBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    public boolean tryToCreatePortal(World worldIn, BlockPos pos) {
        GaiaPortalBlock.Size gaiaPortalSize = this.isPortal(worldIn, pos);
        if (gaiaPortalSize != null && this.canCreatePortalByWorld(worldIn, pos)) {
            gaiaPortalSize.placePortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    // This will check for creation conditions in the Overworld or Gaia
    private boolean canCreatePortalByWorld(World world, BlockPos pos) {
        if (world.dimension() == World.OVERWORLD) {
            //Check if the portal needs to be checking
            if (ModGaiaConfig.portalCheck.get()) {
                Optional<RegistryKey<Biome>> biome = world.getBiomeName(pos);
                ModGaiaConfig.ListType listtype = ModGaiaConfig.listType.get();
                ModGaiaConfig.BiomeType biometype = ModGaiaConfig.biomeType.get();

                //Check the type of list we are looking for
                if (biome.isPresent()) {
                    switch (biometype) {
                        case BIOME:
                            return (listtype == ModGaiaConfig.ListType.WHITELIST) == ModGaiaConfig.biomeList.get().contains(biome.get().location().toString());
                        case CATEGORY:
                            return (listtype == ModGaiaConfig.ListType.WHITELIST) == ModGaiaConfig.categoryList.get().contains(world.getBiome(pos).getBiomeCategory().toString());
                        case TYPE:
                            for (String type : ModGaiaConfig.typeList.get()) {
                                if (BiomeDictionary.hasType(biome.get(), BiomeDictionary.Type.getType(type))) {
                                    return listtype == ModGaiaConfig.ListType.WHITELIST;
                                }
                            }
                            return listtype == ModGaiaConfig.ListType.BLACKLIST;
                    }
                }
                //Somehow checking the biome failed
                GaiaDimensionMod.LOGGER.warn("The biome doesn't appear to exist. Portal could not be created. If this issue persists, disable biome checking in the world's config.");
                return false;
            }
            //Clearly, the option is false, we should be returning true anyway
            return true;
        } else {
            //Gaia is pro-portal
            return world.dimension() == ModDimensions.gaia_world;
        }
    }

    @Nullable
    public GaiaPortalBlock.Size isPortal(IWorld world, BlockPos pos) {
        GaiaPortalBlock.Size gaiaPortalSizeX = new GaiaPortalBlock.Size(world, pos, Direction.Axis.X);
        if (gaiaPortalSizeX.isValid() && gaiaPortalSizeX.portalBlockCount == 0) {
            return gaiaPortalSizeX;
        } else {
            GaiaPortalBlock.Size gaiaPortalSizeZ = new GaiaPortalBlock.Size(world, pos, Direction.Axis.Z);
            return gaiaPortalSizeZ.isValid() && gaiaPortalSizeZ.portalBlockCount == 0 ? gaiaPortalSizeZ : null;
        }
    }

    @Override
    @Deprecated
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis directionAxis = facing.getAxis();
        Direction.Axis directionAxis1 = stateIn.getValue(AXIS);
        boolean flag = directionAxis1 != directionAxis && directionAxis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new GaiaPortalBlock.Size(worldIn, currentPos, directionAxis1)).canCreatePortal() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level.isClientSide() && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }

                if (entity.level instanceof ServerWorld) {
                    ServerWorld serverworld = (ServerWorld)entity.level;
                    MinecraftServer minecraftserver = serverworld.getServer();
                    RegistryKey<World> registrykey = entity.level.dimension() == ModDimensions.gaia_world ? World.OVERWORLD : ModDimensions.gaia_world;
                    ServerWorld serverworld1 = minecraftserver.getLevel(registrykey);
                    if (serverworld1 != null && !entity.isPassenger()) {
                        entity.setPortalCooldown();
                        entity.changeDimension(serverworld1, new GaiaTeleporter(serverworld1));
                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double x = (double)((float)pos.getX() + rand.nextFloat());
            double y = (double)((float)pos.getY() + rand.nextFloat());
            double z = (double)((float)pos.getZ() + rand.nextFloat());
            double sX = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double sY = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double sZ = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int mul = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)mul;
                sX = (double)(rand.nextFloat() * 2.0F * (float)mul);
            } else {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)mul;
                sZ = (double)(rand.nextFloat() * 2.0F * (float)mul);
            }

            worldIn.addParticle(ModParticles.PORTAL, x, y, z, sX, sY, sZ);
        }
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch(state.getValue(AXIS)) {
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos pos) {
        Direction.Axis axis = Direction.Axis.Z;
        GaiaPortalBlock.Size size = new GaiaPortalBlock.Size(worldIn, pos, Direction.Axis.X);
        LoadingCache<BlockPos, CachedBlockInfo> cache = BlockPattern.createLevelCache(worldIn, true);
        if (!size.isValid()) {
            axis = Direction.Axis.X;
            size = new GaiaPortalBlock.Size(worldIn, pos, Direction.Axis.Z);
        }

        if (!size.isValid()) {
            return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, cache, 1, 1, 1);
        } else {
            int[] axes = new int[Direction.AxisDirection.values().length];
            Direction direction = size.rightDir.getCounterClockWise();
            BlockPos blockpos = size.bottomLeft.above(size.getHeight() - 1);

            for(Direction.AxisDirection axisDir : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper helper = new BlockPattern.PatternHelper(direction.getAxisDirection() == axisDir ? blockpos : blockpos.relative(size.rightDir, size.getWidth() - 1), Direction.get(axisDir, axis), Direction.UP, cache, size.getWidth(), size.getHeight(), 1);

                for(int i = 0; i < size.getWidth(); ++i) {
                    for(int j = 0; j < size.getHeight(); ++j) {
                        CachedBlockInfo cacheInfo = helper.getBlock(i, j, 1);
                        if (!cacheInfo.getState().isAir()) {
                            ++axes[axisDir.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection axisDirPos = Direction.AxisDirection.POSITIVE;

            for(Direction.AxisDirection axisDir : Direction.AxisDirection.values()) {
                if (axes[axisDir.ordinal()] < axes[axisDirPos.ordinal()]) {
                    axisDirPos = axisDir;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == axisDirPos ? blockpos : blockpos.relative(size.rightDir, size.getWidth() - 1), Direction.get(axisDirPos, axis), Direction.UP, cache, size.getWidth(), size.getHeight(), 1);
        }
    }

    //TODO: If ever the portal changes, update this. Hopefully even remove it
    public static class Size {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        private BlockPos bottomLeft;
        private int height;
        private int width;
        private final Block KEYSTONE = ModBlocks.keystone_block.get();
        private final Block PORTAL = ModBlocks.gaia_portal.get();

        public Size(IWorld worldIn, BlockPos pos, Direction.Axis facing) {
            world = worldIn;
            axis = facing;

            if (facing == Direction.Axis.X) {
                leftDir = Direction.EAST;
                rightDir = Direction.WEST;
            } else {
                leftDir = Direction.NORTH;
                rightDir = Direction.SOUTH;
            }

            BlockPos blockpos = pos;
            while (pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && isEmptyBlock(worldIn.getBlockState(pos.below()))) {
                pos = pos.below();
            }

            int i = getDistanceUntilEdge(pos, leftDir) - 1;

            if (i >= 0) {
                bottomLeft = pos.relative(leftDir, i);
                width = this.getDistanceUntilEdge(bottomLeft, rightDir);

                if (width < 2 || width > 21) {
                    bottomLeft = null;
                    width = 0;
                }
            }

            if (this.bottomLeft != null) {
                height = calculatePortalHeight();
            }
        }

        int getDistanceUntilEdge(BlockPos pos, Direction facing) {
            int i;

            for (i = 0; i < 22; ++i) {
                BlockPos blockpos = pos.relative(facing, i);

                if (!isEmptyBlock(world.getBlockState(blockpos)) || world.getBlockState(blockpos.below()) != KEYSTONE.defaultBlockState()) {
                    break;
                }
            }

            Block block = world.getBlockState(pos.relative(facing, i)).getBlock();
            return block == KEYSTONE ? i : 0;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        int calculatePortalHeight() {
            label56:

            for (height = 0; height < 21; ++height) {
                for (int i = 0; i < width; ++i) {
                    BlockPos blockpos = bottomLeft.relative(rightDir, i).above(height);
                    BlockState blockstate = world.getBlockState(blockpos);

                    if (!isEmptyBlock(blockstate)) {
                        break label56;
                    }

                    if (blockstate.getBlock() == PORTAL) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        blockstate = world.getBlockState(blockpos.relative(leftDir));

                        if (blockstate != KEYSTONE.defaultBlockState()) {
                            break label56;
                        }
                    } else if (i == this.width) {
                        blockstate = world.getBlockState(blockpos.relative(rightDir));

                        if (blockstate != KEYSTONE.defaultBlockState()) {
                            break label56;
                        }
                    }
                }
            }

            for (int j = 0; j < width; ++j) {
                if (world.getBlockState(bottomLeft.relative(rightDir, j).above(height)) != KEYSTONE.defaultBlockState()) {
                    height = 0;
                    break;
                }
            }

            if (height <= 21 && height >= 3) {
                return height;
            } else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        boolean isEmptyBlock(BlockState state) {
            Block block = state.getBlock();

            return state.isAir() || block == ModBlocks.gold_fire.get() || block == PORTAL;
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && this.height <= 21;
        }

        void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = bottomLeft.relative(rightDir, i);

                for (int j = 0; j < height; ++j) {
                    world.setBlock(blockpos.above(j), PORTAL.defaultBlockState().setValue(AXIS, axis), 2);
                }
            }
        }

        private boolean isLargeEnough() {
            return this.portalBlockCount >= this.width * this.height;
        }

        public boolean canCreatePortal() {
            return this.isValid() && this.isLargeEnough();
        }
    }
}