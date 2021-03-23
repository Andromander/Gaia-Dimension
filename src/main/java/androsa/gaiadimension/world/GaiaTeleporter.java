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
        PointOfInterestManager poimanager = this.world.getPointOfInterestManager();
        int i = 64; //TODO: correct?
        poimanager.ensureLoadedAndValid(this.world, pos, i);
        Optional<PointOfInterest> optional = poimanager.getInSquare(poiType ->
                poiType == ModDimensions.GAIA_PORTAL.get(), pos, i, PointOfInterestManager.Status.ANY)
                .sorted(Comparator.comparingDouble((ToDoubleFunction<PointOfInterest>) poi ->
                        poi.getPos().distanceSq(pos))
                        .thenComparingInt(poi ->
                                poi.getPos().getY()))
                .filter(poi ->
                        GaiaTeleporter.this.world.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .findFirst();
        return optional.map((poi) -> {
            BlockPos blockpos = poi.getPos();
            this.world.getChunkProvider().registerTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.world.getBlockState(blockpos);
            return TeleportationRepositioner.findLargestRectangle(blockpos, blockstate.get(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posIn) -> this.world.getBlockState(posIn) == blockstate);
        });
    }

    /**
     * Create a portal at the teleport location.
     */
    public Optional<TeleportationRepositioner.Result> makePortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.getFacingFromAxis(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder border = this.world.getWorldBorder();
        int height = this.world.func_234938_ad_() - 1;
        BlockPos.Mutable mutable = pos.toMutable();

        for (BlockPos.Mutable mut : BlockPos.func_243514_a(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(height, this.world.getHeight(Heightmap.Type.MOTION_BLOCKING, mut.getX(), mut.getZ()));
            if (border.contains(mut) && border.contains(mut.move(direction, 1))) {
                mut.move(direction.getOpposite(), 1);

                for(int l = j; l >= 0; --l) {
                    mut.setY(l);
                    if (this.world.isAirBlock(mut)) {
                        int i1;
                        for(i1 = l; l > 0 && this.world.isAirBlock(mut.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= height) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                mut.setY(l);
                                if (this.checkRegionForPlacement(mut, mutable, direction, 0)) {
                                    double d2 = pos.distanceSq(mut);
                                    if (this.checkRegionForPlacement(mut, mutable, direction, -1) && this.checkRegionForPlacement(mut, mutable, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = mut.toImmutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = mut.toImmutable();
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
            blockpos = (new BlockPos(pos.getX(), MathHelper.clamp(pos.getY(), 70, world.func_234938_ad_() - 10), pos.getZ())).toImmutable();
            Direction drotated = direction.rotateY();
            if (!border.contains(blockpos)) {
                return Optional.empty();
            }

            for (int fOffset = -1; fOffset < 2; ++fOffset) {
                for (int fWidth = 0; fWidth < 2; ++fWidth) {
                    for (int fHeight = -1; fHeight < 3; ++fHeight) {
                        boolean flag = fHeight < 0;
                        mutable.setAndOffset(blockpos, fWidth * direction.getXOffset() + fOffset * drotated.getXOffset(), fHeight, fWidth * direction.getZOffset() + fOffset * direction.getZOffset());
                        world.setBlockState(mutable, flag ? KEYSTONE.getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for (int fWidth = -1; fWidth < 3; ++fWidth) {
            for (int fHeight = -1; fHeight < 4; ++fHeight) {
                if (fWidth == -1 || fWidth == 2 || fHeight == -1 || fHeight == 3) {
                    mutable.setAndOffset(blockpos, fWidth * direction.getXOffset(), fHeight, fWidth * direction.getZOffset());
                    world.setBlockState(mutable, KEYSTONE.getDefaultState(), 3);
                }
            }
        }

        //Place the portal blocks
        BlockState portal = ModBlocks.gaia_portal.get().getDefaultState().with(GaiaPortalBlock.AXIS, axis);
        for (int pWidth = 0; pWidth < 2; ++pWidth) {
            for (int pHeight = 0; pHeight < 3; ++pHeight) {
                mutable.setAndOffset(blockpos, pWidth * direction.getXOffset(), pHeight, pWidth * direction.getZOffset());
                world.setBlockState(mutable, portal, 18);
            }
        }

        return Optional.of(new TeleportationRepositioner.Result(blockpos.toImmutable(), 2, 3));
    }

    //VanillaCopy of Teleporter.checkRegionForPlacement
    private boolean checkRegionForPlacement(BlockPos originalPos, BlockPos.Mutable offsetPos, Direction directionIn, int offsetScale) {
        Direction direction = directionIn.rotateY();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setAndOffset(originalPos, directionIn.getXOffset() * i + direction.getXOffset() * offsetScale, j, directionIn.getZOffset() * i + direction.getZOffset() * offsetScale);
                if (j < 0 && !this.world.getBlockState(offsetPos).getMaterial().isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.world.isAirBlock(offsetPos)) {
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
        boolean toGaia = destWorld.getDimensionKey() == ModDimensions.gaia_world;
        if (entity.world.getDimensionKey() != ModDimensions.gaia_world && !toGaia) {
            return null;
        } else {
            WorldBorder border = destWorld.getWorldBorder();
            double minX = Math.max(-2.9999872E7D, border.minX() + 16.0D);
            double minZ = Math.max(-2.9999872E7D, border.minZ() + 16.0D);
            double maxX = Math.min(2.9999872E7D, border.maxX() - 16.0D);
            double maxZ = Math.min(2.9999872E7D, border.maxZ() - 16.0D);
            double offset = DimensionType.getCoordinateDifference(entity.world.getDimensionType(), destWorld.getDimensionType());
            BlockPos blockpos = new BlockPos(MathHelper.clamp(entity.getPosX() * offset, minX, maxX), entity.getPosY(), MathHelper.clamp(entity.getPosZ() * offset, minZ, maxZ));
            return this.getPortalLogic(entity, blockpos).map((portalresult) -> {
                BlockState blockstate = entity.world.getBlockState(entity.field_242271_ac);
                Direction.Axis axis;
                Vector3d vector3d;
                if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                    axis = blockstate.get(BlockStateProperties.HORIZONTAL_AXIS);
                    TeleportationRepositioner.Result result = TeleportationRepositioner.findLargestRectangle(entity.field_242271_ac, axis, 21, Direction.Axis.Y, 21, (pos) -> entity.world.getBlockState(pos) == blockstate);
                    vector3d = entity.func_241839_a(axis, result);
                } else {
                    axis = Direction.Axis.X;
                    vector3d = new Vector3d(0.5D, 0.0D, 0.0D);
                }

                return PortalSize.func_242963_a(destWorld, portalresult, axis, vector3d, entity.getSize(entity.getPose()), entity.getMotion(), entity.rotationYaw, entity.rotationPitch);
            }).orElse(null);
        }
    }

    private Optional<TeleportationRepositioner.Result> getPortalLogic(Entity entity, BlockPos pos) {
        Optional<TeleportationRepositioner.Result> existing = this.getExistingPortal(pos);
        if (entity instanceof ServerPlayerEntity) { //ServerPlayerEntity seems to do the portal creation
            if (existing.isPresent()) {
                return existing;
            } else {
                Direction.Axis axis = entity.world.getBlockState(entity.field_242271_ac).func_235903_d_(GaiaPortalBlock.AXIS).orElse(Direction.Axis.X);
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