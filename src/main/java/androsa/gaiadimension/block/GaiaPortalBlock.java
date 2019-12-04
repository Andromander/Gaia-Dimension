package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModGaiaConfig;
import androsa.gaiadimension.registry.ModParticles;
import androsa.gaiadimension.world.GaiaTeleporter;
import com.google.common.cache.LoadingCache;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class GaiaPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public GaiaPortalBlock() {
        super(Properties.create(Material.PORTAL, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(-1.0F).doesNotBlockMovement().tickRandomly().lightValue(15).noDrops());
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.get(AXIS)) {
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
        Biome biome = world.getBiome(pos);

        if (world.dimension.getType() == DimensionType.OVERWORLD) {
            return !ModGaiaConfig.portalCheck.get() || BiomeDictionary.hasType(biome, Type.HOT) || BiomeDictionary.hasType(biome, Type.MOUNTAIN) || BiomeDictionary.hasType(biome, Type.DRY);
        } else {
            return world.dimension.getType() == GaiaDimensionMod.gaia_dimension;
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
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis directionAxis = facing.getAxis();
        Direction.Axis directionAxis1 = stateIn.get(AXIS);
        boolean flag = directionAxis1 != directionAxis && directionAxis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new GaiaPortalBlock.Size(worldIn, currentPos, directionAxis1)).canCreatePortal() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        //RANT: Oh piss off. I would appreciate the patch some day, but no, gotta complain about making changes and inconvenience everybody else.
        //TODO: review some cleanups
        if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && entityIn.timeUntilPortal <= 0 && entityIn instanceof ServerPlayerEntity) {
            MinecraftServer server = worldIn.getServer();
            DimensionType dimType = worldIn.dimension.getType() == GaiaDimensionMod.gaia_dimension ? DimensionType.OVERWORLD : GaiaDimensionMod.gaia_dimension;

            if (server != null && entityIn.timeUntilPortal <= 0) {
                entityIn.timeUntilPortal = entityIn.getPortalCooldown() + 200;

                if (entityIn.timeUntilPortal > 0) {
                    entityIn.timeUntilPortal = entityIn.getPortalCooldown() + 200;
                }

                if (entityIn instanceof ServerPlayerEntity) {
                    this.changeDimension((ServerPlayerEntity)entityIn, dimType);

                    entityIn.timeUntilPortal = entityIn.getPortalCooldown() + 200;
                }
            } else {
                entityIn.timeUntilPortal = Math.max(entityIn.getPortalCooldown(), 200);
            }
        }
    }

    //Copy of ServerPlayerEntity.changeDimension, with relevant changes
    public void changeDimension(ServerPlayerEntity entity, DimensionType destination) {
        if (!ForgeHooks.onTravelToDimension(entity, destination))
            return;
        entity.invulnerableDimensionChange = true;
        DimensionType dimensiontype = entity.dimension;
        ServerWorld serverworld = entity.server.getWorld(dimensiontype);
        entity.dimension = destination;
        ServerWorld serverdest = entity.server.getWorld(destination);
        GaiaTeleporter gaiaTeleporter = new GaiaTeleporter(serverdest);
        WorldInfo worldinfo = entity.world.getWorldInfo();
        NetworkHooks.sendDimensionDataPacket(entity.connection.netManager, entity);
        entity.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), entity.interactionManager.getGameType()));
        entity.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = entity.server.getPlayerList();
        playerlist.updatePermissionLevel(entity);
        serverworld.removeEntity(entity, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        entity.revive();
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        float rotate = entity.rotationPitch;
        float yaw = entity.rotationYaw;

        serverworld.getProfiler().startSection("moving");
        double moveFactor = serverworld.getDimension().getMovementFactor() / serverdest.getDimension().getMovementFactor();
        x *= moveFactor;
        z *= moveFactor;
        entity.setLocationAndAngles(x + 0.5D, y - 2.0D, z + 0.5D, yaw, rotate);
        serverworld.getProfiler().endSection();

        serverworld.getProfiler().startSection("placing");
        double minX = Math.min(-2.9999872E7D, serverdest.getWorldBorder().minX() + 16.0D);
        double minZ = Math.min(-2.9999872E7D, serverdest.getWorldBorder().minZ() + 16.0D);
        double maxX = Math.min(2.9999872E7D, serverdest.getWorldBorder().maxX() - 16.0D);
        double maxZ = Math.min(2.9999872E7D, serverdest.getWorldBorder().maxZ() - 16.0D);
        x = MathHelper.clamp(x, minX, maxX);
        z = MathHelper.clamp(z, minZ, maxZ);
        entity.setLocationAndAngles(x + 0.5D, y - 2.0D, z + 0.5D, yaw, rotate);
        entity.timeUntilPortal = entity.getPortalCooldown();
        if (!gaiaTeleporter.placeInPortal(entity, yaw)) {
            gaiaTeleporter.makePortal(entity);
            gaiaTeleporter.placeInPortal(entity, yaw);
        }
        serverworld.getProfiler().endSection();
        entity.setWorld(serverdest);
        serverdest.func_217447_b(entity);
        CriteriaTriggers.CHANGED_DIMENSION.trigger(entity, dimensiontype, destination);
        entity.connection.setPlayerLocation(entity.posX + 0.5D, entity.posY - 2.0D, entity.posZ + 0.5D, yaw, rotate);
        entity.interactionManager.setWorld(serverdest);
        entity.connection.sendPacket(new SPlayerAbilitiesPacket(entity.abilities));
        playerlist.sendWorldInfo(entity, serverdest);
        playerlist.sendInventory(entity);
        for(EffectInstance effectinstance : entity.getActivePotionEffects()) {
            entity.connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
        }
        entity.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        //entity.lastExperience = -1;
        //entity.lastHealth = -1.0F;
        //entity.lastFoodLevel = -1;
        BasicEventHooks.firePlayerChangedDimensionEvent(entity, dimensiontype, destination);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            worldIn.addParticle(ModParticles.PORTAL.get(), d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot) {
        switch(rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch(state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos pos) {
        Direction.Axis direction$axis = Direction.Axis.Z;
        GaiaPortalBlock.Size gaiaportalblock$size = new GaiaPortalBlock.Size(worldIn, pos, Direction.Axis.X);
        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
        if (!gaiaportalblock$size.isValid()) {
            direction$axis = Direction.Axis.X;
            gaiaportalblock$size = new GaiaPortalBlock.Size(worldIn, pos, Direction.Axis.Z);
        }

        if (!gaiaportalblock$size.isValid()) {
            return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction direction = gaiaportalblock$size.rightDir.rotateYCCW();
            BlockPos blockpos = gaiaportalblock$size.bottomLeft.up(gaiaportalblock$size.getHeight() - 1);

            for(Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection ? blockpos : blockpos.offset(gaiaportalblock$size.rightDir, gaiaportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, direction$axis), Direction.UP, loadingcache, gaiaportalblock$size.getWidth(), gaiaportalblock$size.getHeight(), 1);

                for(int i = 0; i < gaiaportalblock$size.getWidth(); ++i) {
                    for(int j = 0; j < gaiaportalblock$size.getHeight(); ++j) {
                        CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.translateOffset(i, j, 1);
                        if (!cachedblockinfo.getBlockState().isAir()) {
                            ++aint[direction$axisdirection.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

            for(Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
                if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
                    direction$axisdirection1 = direction$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(gaiaportalblock$size.rightDir, gaiaportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, gaiaportalblock$size.getWidth(), gaiaportalblock$size.getHeight(), 1);
        }
    }

    //TODO: Once porting is done, we're redoing this
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
            while (pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && isEmptyBlock(worldIn.getBlockState(pos.down()))) {
                pos = pos.down();
            }

            int i = getDistanceUntilEdge(pos, leftDir) - 1;

            if (i >= 0) {
                bottomLeft = pos.offset(leftDir, i);
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
                BlockPos blockpos = pos.offset(facing, i);

                if (!isEmptyBlock(world.getBlockState(blockpos)) || world.getBlockState(blockpos.down()) != KEYSTONE.getDefaultState()) {
                    break;
                }
            }

            Block block = world.getBlockState(pos.offset(facing, i)).getBlock();
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
                    BlockPos blockpos = bottomLeft.offset(rightDir, i).up(height);
                    BlockState blockstate = world.getBlockState(blockpos);

                    if (!isEmptyBlock(blockstate)) {
                        break label56;
                    }

                    if (blockstate.getBlock() == ModBlocks.gaia_portal.get()) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        blockstate = world.getBlockState(blockpos.offset(leftDir));

                        if (blockstate != KEYSTONE.getDefaultState()) {
                            break label56;
                        }
                    } else if (i == this.width) {
                        blockstate = world.getBlockState(blockpos.offset(rightDir));

                        if (blockstate != KEYSTONE.getDefaultState()) {
                            break label56;
                        }
                    }
                }
            }

            for (int j = 0; j < width; ++j) {
                if (world.getBlockState(bottomLeft.offset(rightDir, j).up(height)) != KEYSTONE.getDefaultState()) {
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

            return state.isAir() || block == ModBlocks.gold_fire.get() || block == ModBlocks.gaia_portal.get();
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && this.height <= 21;
        }

        void placePortalBlocks() {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = bottomLeft.offset(rightDir, i);

                for (int j = 0; j < height; ++j) {
                    world.setBlockState(blockpos.up(j), ModBlocks.gaia_portal.get().getDefaultState().with(AXIS, axis), 2);
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