package androsa.gaiadimension.world;

import androsa.gaiadimension.block.GaiaPortalBlock;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModPOIs;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;

import java.util.Comparator;
import java.util.Optional;

public class GaiaTeleporter {

    private static final Block KEYSTONE = ModBlocks.keystone_block.get();
    private final ServerLevel world;

    public GaiaTeleporter(ServerLevel world) {
        this.world = world;
    }

    public Optional<BlockPos> getExistingPortal(BlockPos pos, WorldBorder border) {
        PoiManager poimanager = this.world.getPoiManager();
        int i = 16;
        poimanager.ensureLoadedAndValid(this.world, pos, i);
        return poimanager.getInSquare(type ->
                type.is(ModPOIs.GAIA_PORTAL.getKey()), pos, i, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(border::isWithinBounds)
                .filter(poi -> GaiaTeleporter.this.world.getBlockState(poi).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(poi ->
                        poi.distSqr(pos))
                        .thenComparingInt(Vec3i::getY));
    }

    /**
     * Create a portal at the teleport location.
     */
    public Optional<BlockUtil.FoundRectangle> makePortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder border = this.world.getWorldBorder();
        int height = Math.min(this.world.getMaxBuildHeight(), this.world.getMinBuildHeight() + this.world.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutable = pos.mutable();

        for (BlockPos.MutableBlockPos mut : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            if (border.isWithinBounds(mut) && border.isWithinBounds(mut.move(direction, 1))) {
                mut.move(direction.getOpposite(), 1);

                for(int l = height; l >= this.world.getMinBuildHeight(); --l) {
                    mut.setY(l);
                    if (this.canReplaceBlock(mut)) {
                        int i1 = l;
                        while (l > this.world.getMinBuildHeight() && this.canReplaceBlock(mut.move(Direction.DOWN))) {
                            --l;
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
            blockpos = (new BlockPos(pos.getX(), Mth.clamp(pos.getY(), 70, world.getHeight() - 10), pos.getZ())).immutable();
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

        return Optional.of(new BlockUtil.FoundRectangle(blockpos.immutable(), 2, 3));
    }

    //VanillaCopy of Teleporter.checkRegionForPlacement
    private boolean checkRegionForPlacement(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction directionIn, int offsetScale) {
        Direction direction = directionIn.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, directionIn.getStepX() * i + direction.getStepX() * offsetScale, j, directionIn.getStepZ() * i + direction.getStepZ() * offsetScale);
                if (j < 0 && !this.world.getBlockState(offsetPos).isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.world.isEmptyBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canReplaceBlock(BlockPos.MutableBlockPos mutable) {
        BlockState state = this.world.getBlockState(mutable);
        return state.canBeReplaced() && state.getFluidState().isEmpty();
    }
}