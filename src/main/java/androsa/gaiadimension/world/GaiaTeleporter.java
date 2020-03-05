package androsa.gaiadimension.world;

import androsa.gaiadimension.block.GaiaPortalBlock;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GaiaTeleporter extends Teleporter {

    public GaiaTeleporter(ServerWorld serverWorld) {
        super(serverWorld);
    }

    public boolean placeInPortal(Entity entity, float yaw) {
        Vec3d vec3d = entity.getLastPortalVec();
        Direction direction = entity.getTeleportDirection();
        BlockPattern.PortalInfo pattern = this.placeInExistingPortal(new BlockPos(entity), entity.getMotion(), direction, vec3d.x, vec3d.y, entity instanceof PlayerEntity);
        if (pattern == null) {
            return false;
        } else {
            Vec3d vec3d1 = pattern.pos;
            Vec3d vec3d2 = pattern.motion;
            entity.setMotion(vec3d2);
            entity.rotationYaw = yaw + (float)pattern.rotation;
            entity.positAfterTeleport(vec3d1.x, vec3d1.y, vec3d1.z);
            return true;
        }
    }

    @Nullable
    @Override
    public BlockPattern.PortalInfo placeInExistingPortal(BlockPos pos, Vec3d motion, Direction direction, double x, double y, boolean isPlayer) {
        PointOfInterestManager poiManager = this.world.getPointOfInterestManager();
        poiManager.func_226347_a_(this.world, pos, 128);
        List<PointOfInterest> points = poiManager.func_226353_b_((type) ->
                type == ModWorldgen.GAIA_PORTAL.get(), pos, 128, PointOfInterestManager.Status.ANY).collect(Collectors.toList());
        Optional<PointOfInterest> optional = points.stream().min(Comparator.<PointOfInterest>comparingDouble((type) ->
                type.getPos().distanceSq(pos)).thenComparingInt((type) ->
                type.getPos().getY()));
        return optional.map((type) -> {
            BlockPos blockpos = type.getPos();
            this.world.getChunkProvider().registerTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockPattern.PatternHelper pattern = GaiaPortalBlock.createPatternHelper(this.world, blockpos);
            return pattern.getPortalInfo(direction, blockpos, y, motion, x);
        }).orElse(null);
    }

    /**
     * Create a portal at the teleport location.
     */
//    @Override
//    public boolean makePortal(Entity entityIn) {
//        double axis = -1.0D;
//        int eX = MathHelper.floor(entityIn.getX());
//        int eY = MathHelper.floor(entityIn.getY());
//        int eZ = MathHelper.floor(entityIn.getZ());
//        int i1 = eX;
//        int j1 = eY;
//        int k1 = eZ;
//        int l1 = 0;
//        int i2 = this.random.nextInt(4);
//        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
//
//        for(int x = eX - 16; x <= eX + 16; ++x) {
//            double d1 = (double)x + 0.5D - entityIn.getX();
//
//            for(int z = eZ - 16; z <= eZ + 16; ++z) {
//                double d2 = (double)z + 0.5D - entityIn.getZ();
//
//                label276:
//                for(int y = this.world.getActualHeight() - 1; y >= 0; --y) {
//                    if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(x, y, z))) {
//                        while(y > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(x, y - 1, z))) {
//                            --y;
//                        }
//
//                        for(int k3 = i2; k3 < i2 + 4; ++k3) {
//                            int l3 = k3 % 2;
//                            int i4 = 1 - l3;
//                            if (k3 % 4 >= 2) {
//                                l3 = -l3;
//                                i4 = -i4;
//                            }
//
//                            for(int j4 = 0; j4 < 3; ++j4) {
//                                for(int k4 = 0; k4 < 4; ++k4) {
//                                    for(int l4 = -1; l4 < 4; ++l4) {
//                                        int bX = x + (k4 - 1) * l3 + j4 * i4;
//                                        int bY = y + l4;
//                                        int bZ = z + (k4 - 1) * i4 - j4 * l3;
//                                        blockpos$mutableblockpos.setPos(bX, bY, bZ);
//                                        if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
//                                            continue label276;
//                                        }
//                                    }
//                                }
//                            }
//
//                            double d5 = (double)y + 0.5D - entityIn.getY();
//                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;
//                            if (axis < 0.0D || d7 < axis) {
//                                axis = d7;
//                                i1 = x;
//                                j1 = y;
//                                k1 = z;
//                                l1 = k3 % 4;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (axis < 0.0D) {
//            for(int x = eX - 16; x <= eX + 16; ++x) {
//                double d3 = (double)x + 0.5D - entityIn.getX();
//
//                for(int z = eZ - 16; z <= eZ + 16; ++z) {
//                    double d4 = (double)z + 0.5D - entityIn.getZ();
//
//                    label214:
//                    for(int y = this.world.getActualHeight() - 1; y >= 0; --y) {
//                        if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(x, y, z))) {
//                            while(y > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(x, y - 1, z))) {
//                                --y;
//                            }
//
//                            for(int l7 = i2; l7 < i2 + 2; ++l7) {
//                                int l8 = l7 % 2;
//                                int k9 = 1 - l8;
//
//                                for(int i10 = 0; i10 < 4; ++i10) {
//                                    for(int k10 = -1; k10 < 4; ++k10) {
//                                        int bX = x + (i10 - 1) * l8;
//                                        int bY = y + k10;
//                                        int bZ = z + (i10 - 1) * k9;
//                                        blockpos$mutableblockpos.setPos(bX, bY, bZ);
//                                        if (k10 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || k10 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
//                                            continue label214;
//                                        }
//                                    }
//                                }
//
//                                double d6 = (double)y + 0.5D - entityIn.getY();
//                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
//                                if (axis < 0.0D || d8 < axis) {
//                                    axis = d8;
//                                    i1 = x;
//                                    j1 = y;
//                                    k1 = z;
//                                    l1 = l7 % 2;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        int i6 = i1;
//        int k2 = j1;
//        int k6 = k1;
//        int l6 = l1 % 2;
//        int i3 = 1 - l6;
//        if (l1 % 4 >= 2) {
//            l6 = -l6;
//            i3 = -i3;
//        }
//
//        if (axis < 0.0D) {
//            j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
//            k2 = j1;
//
//            for(int j7 = -1; j7 <= 1; ++j7) {
//                for(int i8 = 1; i8 < 3; ++i8) {
//                    for(int i9 = -1; i9 < 3; ++i9) {
//                        int bX = i6 + (i8 - 1) * l6 + j7 * i3;
//                        int bY = k2 + i9;
//                        int bZ = k6 + (i8 - 1) * i3 - j7 * l6;
//                        boolean isFrame = i9 < 0;
//                        blockpos$mutableblockpos.setPos(bX, bY, bZ);
//                        this.world.setBlockState(blockpos$mutableblockpos, isFrame ? BLOCK_KEYSTONE.getDefaultState() : Blocks.AIR.getDefaultState());
//                    }
//                }
//            }
//        }
//
//        for(int k7 = -1; k7 < 3; ++k7) {
//            for(int j8 = -1; j8 < 4; ++j8) {
//                if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
//                    blockpos$mutableblockpos.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
//                    this.world.setBlockState(blockpos$mutableblockpos, BLOCK_KEYSTONE.getDefaultState(), 3);
//                }
//            }
//        }
//
//        BlockState blockstate = BLOCK_GAIA_PORTAL.getDefaultState().with(GaiaPortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);
//
//        for(int k8 = 0; k8 < 2; ++k8) {
//            for(int j9 = 0; j9 < 3; ++j9) {
//                blockpos$mutableblockpos.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
//                this.world.setBlockState(blockpos$mutableblockpos, blockstate, 18);
//            }
//        }
//
//        return true;
//    }

    public boolean makePortal(Entity p_85188_1_) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(p_85188_1_.getX());
        int k = MathHelper.floor(p_85188_1_.getY());
        int l = MathHelper.floor(p_85188_1_.getZ());
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int j2 = j - 16; j2 <= j + 16; ++j2) {
            double d1 = (double)j2 + 0.5D - p_85188_1_.getX();

            for(int l2 = l - 16; l2 <= l + 16; ++l2) {
                double d2 = (double)l2 + 0.5D - p_85188_1_.getZ();

                label276:
                for(int j3 = this.world.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (this.world.isAirBlock(blockpos$mutable.setPos(j2, j3, l2))) {
                        while(j3 > 0 && this.world.isAirBlock(blockpos$mutable.setPos(j2, j3 - 1, l2))) {
                            --j3;
                        }

                        for(int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for(int j4 = 0; j4 < 3; ++j4) {
                                for(int k4 = 0; k4 < 4; ++k4) {
                                    for(int l4 = -1; l4 < 4; ++l4) {
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutable.setPos(i5, j5, k5);
                                        if (l4 < 0 && !this.world.getBlockState(blockpos$mutable).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockpos$mutable)) {
                                            continue label276;
                                        }
                                    }
                                }
                            }

                            double d5 = (double)j3 + 0.5D - p_85188_1_.getY();
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;
                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                i1 = j2;
                                j1 = j3;
                                k1 = l2;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for(int l5 = j - 16; l5 <= j + 16; ++l5) {
                double d3 = (double)l5 + 0.5D - p_85188_1_.getX();

                for(int j6 = l - 16; j6 <= l + 16; ++j6) {
                    double d4 = (double)j6 + 0.5D - p_85188_1_.getZ();

                    label214:
                    for(int i7 = this.world.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (this.world.isAirBlock(blockpos$mutable.setPos(l5, i7, j6))) {
                            while(i7 > 0 && this.world.isAirBlock(blockpos$mutable.setPos(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for(int l7 = i2; l7 < i2 + 2; ++l7) {
                                int l8 = l7 % 2;
                                int k9 = 1 - l8;

                                for(int i10 = 0; i10 < 4; ++i10) {
                                    for(int k10 = -1; k10 < 4; ++k10) {
                                        int i11 = l5 + (i10 - 1) * l8;
                                        int j11 = i7 + k10;
                                        int k11 = j6 + (i10 - 1) * k9;
                                        blockpos$mutable.setPos(i11, j11, k11);
                                        if (k10 < 0 && !this.world.getBlockState(blockpos$mutable).getMaterial().isSolid() || k10 >= 0 && !this.world.isAirBlock(blockpos$mutable)) {
                                            continue label214;
                                        }
                                    }
                                }

                                double d6 = (double)i7 + 0.5D - p_85188_1_.getY();
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = l7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = i1;
        int k2 = j1;
        int k6 = k1;
        int l6 = l1 % 2;
        int i3 = 1 - l6;
        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            j1 = MathHelper.clamp(j1, 70, this.world.getActualHeight() - 10);
            k2 = j1;

            for(int j7 = -1; j7 <= 1; ++j7) {
                for(int i8 = 1; i8 < 3; ++i8) {
                    for(int i9 = -1; i9 < 3; ++i9) {
                        int l9 = i6 + (i8 - 1) * l6 + j7 * i3;
                        int j10 = k2 + i9;
                        int l10 = k6 + (i8 - 1) * i3 - j7 * l6;
                        boolean flag = i9 < 0;
                        blockpos$mutable.setPos(l9, j10, l10);
                        this.world.setBlockState(blockpos$mutable, flag ? ModBlocks.keystone_block.get().getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for(int k7 = -1; k7 < 3; ++k7) {
            for(int j8 = -1; j8 < 4; ++j8) {
                if (k7 == -1 || k7 == 2 || j8 == -1 || j8 == 3) {
                    blockpos$mutable.setPos(i6 + k7 * l6, k2 + j8, k6 + k7 * i3);
                    this.world.setBlockState(blockpos$mutable, ModBlocks.keystone_block.get().getDefaultState(), 3);
                }
            }
        }

        BlockState blockstate = ModBlocks.gaia_portal.get().getDefaultState().with(GaiaPortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for(int k8 = 0; k8 < 2; ++k8) {
            for(int j9 = 0; j9 < 3; ++j9) {
                blockpos$mutable.setPos(i6 + k8 * l6, k2 + j9, k6 + k8 * i3);
                this.world.setBlockState(blockpos$mutable, blockstate, 18);
            }
        }

        return true;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        makePortal(entity);
        placeInPortal(entity, yaw);
        return repositionEntity.apply(false);
    }

//    @Override
//    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
//        Entity teleportEntity = repositionEntity.apply(false);
//        BlockPos pos = teleportEntity.getPosition();
//        BlockPattern.PatternHelper pattern = GaiaPortalBlock.createPatternHelper(teleportEntity.world, pos);
//        double axis = pattern.getForwards().getAxis() == Direction.Axis.X ? (double)pattern.getFrontTopLeft().getZ() : (double)pattern.getFrontTopLeft().getX();
//        double x = Math.abs(MathHelper.pct((pattern.getForwards().getAxis() == Direction.Axis.X ? teleportEntity.getZ() : teleportEntity.getX()) - (double)(pattern.getForwards().rotateY().getAxisDirection() == Direction.AxisDirection.NEGATIVE ? 1 : 0), axis, axis - (double)pattern.getWidth()));
//        double y = MathHelper.pct(teleportEntity.getY() - 1.0D, (double)pattern.getFrontTopLeft().getY(), (double)(pattern.getFrontTopLeft().getY() - pattern.getHeight()));
//
//        teleportEntity.setPosition(x, y, 0.0D);
//
//        if (!placeInPortal(teleportEntity, yaw)) {
//            makePortal(teleportEntity);
//            placeInPortal(teleportEntity, yaw);
//        }
//
//        return teleportEntity;
//    }
}