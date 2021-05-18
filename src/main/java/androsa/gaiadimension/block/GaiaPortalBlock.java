package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModGaiaConfig;
import androsa.gaiadimension.registry.ModParticles;
import androsa.gaiadimension.world.GaiaTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
        if (world.dimension().location().equals(ModGaiaConfig.startDimRL)) {
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
                    RegistryKey<World> registrykey = entity.level.dimension() == ModDimensions.gaia_world ? ModGaiaConfig.startDimRK : ModDimensions.gaia_world;
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
            double x = (float)pos.getX() + rand.nextFloat();
            double y = (float)pos.getY() + rand.nextFloat();
            double z = (float)pos.getZ() + rand.nextFloat();
            double sX = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double sY = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double sZ = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int mul = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)mul;
                sX = rand.nextFloat() * 2.0F * (float)mul;
            } else {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)mul;
                sZ = rand.nextFloat() * 2.0F * (float)mul;
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

    //TODO: If ever the portal changes, update this. Hopefully even remove it
    public static class Size {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private int portalBlockCount;
        private BlockPos bottomLeft;
        private int height;
        private int width;
        private static final IPositionPredicate FRAME_TEST = (state, reader, pos) -> state.getBlock() == ModBlocks.keystone_block.get();
        private final Block PORTAL = ModBlocks.gaia_portal.get();

        public Size(IWorld worldIn, BlockPos pos, Direction.Axis facing) {
            world = worldIn;
            axis = facing;
            rightDir = facing == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
            bottomLeft = calculateBottomLeft(pos);
            if (bottomLeft == null) {
                bottomLeft = pos;
                width = 1;
                height = 1;
            } else {
                width = calculatePortalWidth();
                if (width > 0) {
                    height = calculatePortalHeight();
                }
            }
        }

        @Nullable
        private BlockPos calculateBottomLeft(BlockPos pos) {
            int i = Math.max(0, pos.getY() - 21);
            while (pos.getY() > i && isEmptyBlock(this.world.getBlockState(pos.below()))) {
                pos = pos.below();
            }

            Direction direction = this.rightDir.getOpposite();
            int j = this.getDistanceUntilEdge(pos, direction) - 1;
            return j < 0 ? null : pos.relative(direction, j);
        }

        private int getDistanceUntilEdge(BlockPos pos, Direction facing) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int i = 0; i < 22; ++i) {
                mutable.set(pos).move(facing, i);
                BlockState state = this.world.getBlockState(mutable);

                if (!isEmptyBlock(state)) {
                    if (FRAME_TEST.test(state, world, mutable)) {
                        return i;
                    }
                    break;
                }

                BlockState state1 = this.world.getBlockState(mutable.move(Direction.DOWN));
                if (!FRAME_TEST.test(state1, world, mutable)) {
                    break;
                }
            }

            return 0;
        }

        private int calculatePortalWidth() {
            int dist = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
            return dist >= 2 && dist <= 21 ? dist : 0;
        }

        private int calculatePortalHeight() {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            int dist = this.getDistanceUntilTop(mutable);
            return dist >= 3 && dist <= 21 && hasTopFrame(mutable, dist) ? dist : 0;
        }

        private int getDistanceUntilTop(BlockPos.Mutable mutable) {
            for (int i = 0; i < 21; ++i) {
                mutable.set(bottomLeft).move(Direction.UP, i).move(rightDir, -1);
                if (!FRAME_TEST.test(this.world.getBlockState(mutable), this.world, mutable)) {
                    return i;
                }

                mutable.set(bottomLeft).move(Direction.UP, i).move(rightDir, width);
                if (!FRAME_TEST.test(this.world.getBlockState(mutable), this.world, mutable)) {
                    return i;
                }


                for (int j = 0; j < width; ++j) {
                    mutable.set(bottomLeft).move(Direction.UP, i).move(rightDir, j);
                    BlockState blockstate = world.getBlockState(mutable);

                    if (!isEmptyBlock(blockstate)) {
                        return i;
                    }

                    if (blockstate.getBlock() == PORTAL) {
                        ++this.portalBlockCount;
                    }
                }
            }

            return 21;
        }

        private boolean hasTopFrame(BlockPos.Mutable mutable, int offset) {
            for (int i = 0; i < this.width; i++) {
                BlockPos.Mutable mutablepos = mutable.set(bottomLeft).move(Direction.UP, offset).move(rightDir, i);
                if (!FRAME_TEST.test(this.world.getBlockState(mutablepos), world, mutablepos)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isEmptyBlock(BlockState state) {
            Block block = state.getBlock();
            return state.isAir() || block == ModBlocks.gold_fire.get() || block == PORTAL;
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && this.height <= 21;
        }

        public void placePortalBlocks() {
            BlockState state = PORTAL.defaultBlockState().setValue(GaiaPortalBlock.AXIS, this.axis);
            BlockPos.betweenClosed(bottomLeft, bottomLeft.relative(Direction.UP, height - 1).relative(rightDir, width - 1)).forEach((pos) -> {
                this.world.setBlock(pos, state, 18);
            });
        }

        public boolean canCreatePortal() {
            return this.isValid() && this.isLargeEnough();
        }

        private boolean isLargeEnough() {
            return this.portalBlockCount == this.width * this.height;
        }
    }
}