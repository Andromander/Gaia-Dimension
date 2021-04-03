package androsa.gaiadimension.world;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.GaiaPortalBlock;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModDimensions;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.DimensionType;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public class GaiaTeleporter implements ITeleporter {

    private static final Block KEYSTONE = ModBlocks.keystone_block.get();
    private final ServerWorld world;

    public GaiaTeleporter(ServerWorld world) {
        this.world = world;
    }

    public Optional<TeleportationRepositioner.Result> getExistingPortal(BlockPos pos) {
        PointOfInterestManager poimanager = this.world.getPoiManager();
        int i = 64; //TODO: correct?
        poimanager.ensureLoadedAndValid(this.world, pos, i);
        Optional<PointOfInterest> optional = poimanager.getInSquare(poiType ->
                poiType == ModDimensions.GAIA_PORTAL.get(), pos, i, PointOfInterestManager.Status.ANY)
                .sorted(Comparator.comparingDouble((ToDoubleFunction<PointOfInterest>) poi ->
                        poi.getPos().distSqr(pos))
                        .thenComparingInt(poi ->
                                poi.getPos().getY()))
                .filter(poi ->
                        GaiaTeleporter.this.world.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .findFirst();
        return optional.map((poi) -> {
            BlockPos blockpos = poi.getPos();
            this.world.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.world.getBlockState(blockpos);
            return TeleportationRepositioner.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posIn) -> this.world.getBlockState(posIn) == blockstate);
        });
    }

    /**
     * Create a portal at the teleport location.
     */
    public Optional<TeleportationRepositioner.Result> makePortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder border = this.world.getWorldBorder();
        int height = this.world.getHeight() - 1;
        BlockPos.Mutable mutable = pos.mutable();

        for (BlockPos.Mutable mut : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(height, this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, mut.getX(), mut.getZ()));
            if (border.isWithinBounds(mut) && border.isWithinBounds(mut.move(direction, 1))) {
                mut.move(direction.getOpposite(), 1);

                for(int l = j; l >= 0; --l) {
                    mut.setY(l);
                    if (this.world.isEmptyBlock(mut)) {
                        int i1;
                        for(i1 = l; l > 0 && this.world.isEmptyBlock(mut.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= height) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                mut.setY(l);
                                if (this.checkRegionForPlacement(mut, mutable, direction, 0)) {
                                    double d2 = pos.distSqr(mut);
                                    if (this.checkRegionForPlacement(mut, mutable, direction, -1) && this.checkRegionForPlacement(mut, mutable, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = mut.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = mut.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        //Place the frame blocks
        if (d0 == -1.0D) {
            blockpos = (new BlockPos(pos.getX(), MathHelper.clamp(pos.getY(), 70, world.getHeight() - 10), pos.getZ())).immutable();
            Direction drotated = direction.getClockWise();
            if (!border.isWithinBounds(blockpos)) {
                return Optional.empty();
            }

            for (int fOffset = -1; fOffset < 2; ++fOffset) {
                for (int fWidth = 0; fWidth < 2; ++fWidth) {
                    for (int fHeight = -1; fHeight < 3; ++fHeight) {
                        boolean flag = fHeight < 0;
                        mutable.setWithOffset(blockpos, fWidth * direction.getStepX() + fOffset * drotated.getStepX(), fHeight, fWidth * direction.getStepZ() + fOffset * direction.getStepZ());
                        world.setBlockAndUpdate(mutable, flag ? KEYSTONE.defaultBlockState() : Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        for (int fWidth = -1; fWidth < 3; ++fWidth) {
            for (int fHeight = -1; fHeight < 4; ++fHeight) {
                if (fWidth == -1 || fWidth == 2 || fHeight == -1 || fHeight == 3) {
                    mutable.setWithOffset(blockpos, fWidth * direction.getStepX(), fHeight, fWidth * direction.getStepZ());
                    world.setBlockAndUpdate(mutable, KEYSTONE.defaultBlockState());
                }
            }
        }

        //Place the portal blocks
        BlockState portal = ModBlocks.gaia_portal.get().defaultBlockState().setValue(GaiaPortalBlock.AXIS, axis);
        for (int pWidth = 0; pWidth < 2; ++pWidth) {
            for (int pHeight = 0; pHeight < 3; ++pHeight) {
                mutable.setWithOffset(blockpos, pWidth * direction.getStepX(), pHeight, pWidth * direction.getStepZ());
                world.setBlock(mutable, portal, 18);
            }
        }

        return Optional.of(new TeleportationRepositioner.Result(blockpos.immutable(), 2, 3));
    }

    //VanillaCopy of Teleporter.checkRegionForPlacement
    private boolean checkRegionForPlacement(BlockPos originalPos, BlockPos.Mutable offsetPos, Direction directionIn, int offsetScale) {
        Direction direction = directionIn.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, directionIn.getStepX() * i + direction.getStepX() * offsetScale, j, directionIn.getStepZ() * i + direction.getStepZ() * offsetScale);
                if (j < 0 && !this.world.getBlockState(offsetPos).getMaterial().isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.world.isEmptyBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        return repositionEntity.apply(false);
    }

    @Nullable
    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerWorld destWorld, Function<ServerWorld, PortalInfo> defaultPortalInfo) {
        boolean toGaia = destWorld.dimension() == ModDimensions.gaia_world;
        if (entity.level.dimension() != ModDimensions.gaia_world && !toGaia) {
            return null;
        } else {
            WorldBorder border = destWorld.getWorldBorder();
            double minX = Math.max(-2.9999872E7D, border.getMinX() + 16.0D);
            double minZ = Math.max(-2.9999872E7D, border.getMinZ() + 16.0D);
            double maxX = Math.min(2.9999872E7D, border.getMaxX() - 16.0D);
            double maxZ = Math.min(2.9999872E7D, border.getMaxZ() - 16.0D);
            double offset = DimensionType.getTeleportationScale(entity.level.dimensionType(), destWorld.dimensionType());
            BlockPos blockpos = new BlockPos(MathHelper.clamp(entity.getX() * offset, minX, maxX), entity.getY(), MathHelper.clamp(entity.getZ() * offset, minZ, maxZ));
            return this.getPortalLogic(entity, blockpos).map((portalresult) -> {
                BlockState blockstate = entity.level.getBlockState(entity.portalEntrancePos);
                Direction.Axis axis;
                Vector3d vector3d;
                if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                    axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                    TeleportationRepositioner.Result result = TeleportationRepositioner.getLargestRectangleAround(entity.portalEntrancePos, axis, 21, Direction.Axis.Y, 21, (pos) -> entity.level.getBlockState(pos) == blockstate);
                    vector3d = entity.getRelativePortalPosition(axis, result);
                } else {
                    axis = Direction.Axis.X;
                    vector3d = new Vector3d(0.5D, 0.0D, 0.0D);
                }

                return PortalSize.createPortalInfo(destWorld, portalresult, axis, vector3d, entity.getDimensions(entity.getPose()), entity.getDeltaMovement(), entity.yRot, entity.xRot);
            }).orElse(null);
        }
    }

    private Optional<TeleportationRepositioner.Result> getPortalLogic(Entity entity, BlockPos pos) {
        Optional<TeleportationRepositioner.Result> existing = this.getExistingPortal(pos);
        if (entity instanceof ServerPlayerEntity) { //ServerPlayerEntity seems to do the portal creation
            if (existing.isPresent()) {
                return existing;
            } else {
                Direction.Axis axis = entity.level.getBlockState(entity.portalEntrancePos).getOptionalValue(GaiaPortalBlock.AXIS).orElse(Direction.Axis.X);
                Optional<TeleportationRepositioner.Result> portal = this.makePortal(pos, axis);
                if (!portal.isPresent()) {
                    GaiaDimensionMod.LOGGER.error("Unable to create a portal, likely target out of worldborder");
                }

                return portal;
            }
        } else { //Otherwise, we don't care about a dimension unless it does exist
            return existing;
        }
    }
}