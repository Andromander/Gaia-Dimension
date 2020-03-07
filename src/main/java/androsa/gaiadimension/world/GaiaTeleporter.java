package androsa.gaiadimension.world;

import androsa.gaiadimension.block.GaiaPortalBlock;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GaiaTeleporter implements ITeleporter {

    public boolean placeInPortal(ServerWorld world, Entity entity, float yaw) {
        Vec3d vec3d = entity.getLastPortalVec();
        Direction direction = entity.getTeleportDirection();
        BlockPattern.PortalInfo pattern = this.placeInExistingPortal(world, new BlockPos(entity.getX(), entity.getY(), entity.getZ()), entity.getMotion(), direction, vec3d.x, vec3d.y);
        if (pattern == null) {
            return false;
        } else {
            Vec3d vec3d1 = pattern.pos;
            Vec3d vec3d2 = pattern.motion;
            entity.setMotion(vec3d2);
            entity.rotationYaw = yaw + (float) pattern.rotation;
            entity.positAfterTeleport(vec3d1.x, vec3d1.y, vec3d1.z);
            return true;
        }
    }

    @Nullable
    public BlockPattern.PortalInfo placeInExistingPortal(ServerWorld world, BlockPos pos, Vec3d motion, Direction direction, double x, double y) {
        PointOfInterestManager poiManager = world.getPointOfInterestManager();
        poiManager.func_226347_a_(world, pos, 128);
        List<PointOfInterest> points = poiManager.func_226353_b_((type) -> type == ModWorldgen.GAIA_PORTAL.get(),
                pos, 128, PointOfInterestManager.Status.ANY).collect(Collectors.toList());

        Optional<PointOfInterest> optional = points.stream().min(Comparator.<PointOfInterest>comparingDouble((type) ->
                type.getPos().distanceSq(pos)).thenComparingInt((type) -> type.getPos().getY()));

        return optional.map((type) -> {
            BlockPos blockpos = type.getPos();
            world.getChunkProvider().registerTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockPattern.PatternHelper pattern = GaiaPortalBlock.createPatternHelper(world, blockpos);
            return pattern.getPortalInfo(direction, blockpos, y, motion, x);
        }).orElse(null);
    }

    /**
     * Create a portal at the teleport location.
     */
    public void makePortal(ServerWorld world, Entity entity) {
        Random random = new Random(world.getSeed());
        double d0 = -1.0D;
        int entityX = MathHelper.floor(entity.getX());
        int entityY = MathHelper.floor(entity.getY());
        int entityZ = MathHelper.floor(entity.getZ());
        int xPos = entityX;
        int yPos = entityY;
        int zPos = entityZ;
        int baseAxis = 0;
        int randAxis = random.nextInt(4);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int startX = entityX - 16; startX <= entityX + 16; ++startX) {
            double ePosX = (double) startX + 0.5D - entity.getX();

            for (int startZ = entityZ - 16; startZ <= entityZ + 16; ++startZ) {
                double ePosZ = (double) startZ + 0.5D - entity.getZ();

                searchpos:
                for (int startY = world.getActualHeight() - 1; startY >= 0; --startY) {
                    if (world.isAirBlock(mutable.setPos(startX, startY, startZ))) {
                        while (startY > 0 && world.isAirBlock(mutable.setPos(startX, startY - 1, startZ))) {
                            --startY;
                        }

                        for (int k3 = randAxis; k3 < randAxis + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;
                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int portalHeight = -1; portalHeight < 4; ++portalHeight) {
                                        int sPosX = startX + (k4 - 1) * l3 + j4 * i4;
                                        int sPosY = startY + portalHeight;
                                        int sPosZ = startZ + (k4 - 1) * i4 - j4 * l3;
                                        mutable.setPos(sPosX, sPosY, sPosZ);
                                        System.out.println(world.getBlockState(mutable));
                                        if (portalHeight < 0 && !world.getBlockState(mutable).getMaterial().isSolid() || portalHeight >= 0 && !world.isAirBlock(mutable)) {
                                            continue searchpos;
                                        }
                                    }
                                }
                            }

                            double ePosY = (double) startY + 0.5D - entity.getY();
                            double eArea = ePosX * ePosX + ePosY * ePosY + ePosZ * ePosZ;
                            if (d0 < 0.0D || eArea < d0) {
                                d0 = eArea;
                                xPos = startX;
                                yPos = startY;
                                zPos = startZ;
                                baseAxis = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int startX2 = entityX - 16; startX2 <= entityX + 16; ++startX2) {
                double d3 = (double) startX2 + 0.5D - entity.getX();

                for (int startZ2 = entityZ - 16; startZ2 <= entityZ + 16; ++startZ2) {
                    double d4 = (double) startZ2 + 0.5D - entity.getZ();

                    label214:
                    for (int startY2 = world.getActualHeight() - 1; startY2 >= 0; --startY2) {
                        if (world.isAirBlock(mutable.setPos(startX2, startY2, startZ2))) {
                            while (startY2 > 0 && world.isAirBlock(mutable.setPos(startX2, startY2 - 1, startZ2))) {
                                --startY2;
                            }

                            for (int l7 = randAxis; l7 < randAxis + 2; ++l7) {
                                int l8 = l7 % 2;
                                int k9 = 1 - l8;

                                for (int i10 = 0; i10 < 4; ++i10) {
                                    for (int portalHeight2 = -1; portalHeight2 < 4; ++portalHeight2) {
                                        int sPosX2 = startX2 + (i10 - 1) * l8;
                                        int sPosY2 = startY2 + portalHeight2;
                                        int sPosZ2 = startZ2 + (i10 - 1) * k9;
                                        mutable.setPos(sPosX2, sPosY2, sPosZ2);
                                        if (portalHeight2 < 0 && !world.getBlockState(mutable).getMaterial().isSolid() || portalHeight2 >= 0 && !world.isAirBlock(mutable)) {
                                            continue label214;
                                        }
                                    }
                                }

                                double d6 = (double) startY2 + 0.5D - entity.getY();
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;
                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    xPos = startX2;
                                    yPos = startY2;
                                    zPos = startZ2;
                                    baseAxis = l7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int baseX = xPos;
        int baseY = yPos;
        int baseZ = zPos;
        int xAxis = baseAxis % 2;
        int zAxis = 1 - xAxis;
        if (baseAxis % 4 >= 2) {
            xAxis = -xAxis;
            zAxis = -zAxis;
        }

        if (d0 < 0.0D) {
            yPos = MathHelper.clamp(yPos, 70, world.getActualHeight() - 10);
            baseY = yPos;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int i8 = 1; i8 < 3; ++i8) {
                    for (int i9 = -1; i9 < 3; ++i9) {
                        int frameX = baseX + (i8 - 1) * xAxis + j7 * zAxis;
                        int frameY = baseY + i9;
                        int frameZ = baseZ + (i8 - 1) * zAxis - j7 * xAxis;
                        boolean flag = i9 < 0;
                        mutable.setPos(frameX, frameY, frameZ);
                        world.setBlockState(mutable, flag ? ModBlocks.keystone_block.get().getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        for (int fWidth = -1; fWidth < 3; ++fWidth) {
            for (int fHeight = -1; fHeight < 4; ++fHeight) {
                if (fWidth == -1 || fWidth == 2 || fHeight == -1 || fHeight == 3) {
                    mutable.setPos(baseX + fWidth * xAxis, baseY + fHeight, baseZ + fWidth * zAxis);
                    world.setBlockState(mutable, ModBlocks.keystone_block.get().getDefaultState(), 3);
                }
            }
        }

        BlockState portal = ModBlocks.gaia_portal.get().getDefaultState().with(GaiaPortalBlock.AXIS, xAxis == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for (int pWidth = 0; pWidth < 2; ++pWidth) {
            for (int pHeight = 0; pHeight < 3; ++pHeight) {
                mutable.setPos(baseX + pWidth * xAxis, baseY + pHeight, baseZ + pWidth * zAxis);
                world.setBlockState(mutable, portal, 18);
            }
        }

    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity newEntity = repositionEntity.apply(false);

        if (!placeInPortal(destWorld, newEntity, newEntity.rotationYaw)) {
            makePortal(destWorld, newEntity);
            placeInPortal(destWorld, newEntity, newEntity.rotationYaw);
        }

        return newEntity;
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