package androsa.gaiadimension.world;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.GaiaPortalBlock;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.function.Supplier;

public class GaiaTeleporter extends Teleporter {

    private static final GaiaPortalBlock BLOCK_GAIA_PORTAL = ModBlocks.gaia_portal;
    protected final Map<ColumnPos, PortalPosition> destinationCoordinateCache = Maps.newHashMapWithExpectedSize(4096);
    private final Object2LongMap<ColumnPos> field_222275_f = new Object2LongOpenHashMap<>();

    public GaiaTeleporter(ServerWorld serverWorld) {
        super(serverWorld);
    }

    @Override
    public boolean func_222268_a(Entity entity, float yaw) {
        Vec3d vec3d = entity.getLastPortalVec();
        Direction direction = entity.getTeleportDirection();
        BlockPattern.PortalInfo blockpattern$portalinfo = this.func_222272_a(new BlockPos(entity), entity.getMotion(), direction, vec3d.x, vec3d.y, entity instanceof PlayerEntity);
        if (blockpattern$portalinfo == null) {
            return false;
        } else {
            Vec3d vec3d1 = blockpattern$portalinfo.field_222505_a;
            Vec3d vec3d2 = blockpattern$portalinfo.field_222506_b;
            entity.setMotion(vec3d2);
            entity.rotationYaw = yaw + (float)blockpattern$portalinfo.field_222507_c;
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity)entity).connection.setPlayerLocation(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
                ((ServerPlayerEntity)entity).connection.captureCurrentPosition();
            } else {
                entity.setLocationAndAngles(vec3d1.x, vec3d1.y, vec3d1.z, entity.rotationYaw, entity.rotationPitch);
            }

            return true;
        }
    }

    @Override
    public BlockPattern.PortalInfo func_222272_a(BlockPos pos, Vec3d vec3d, Direction directon, double vecX, double vecY, boolean isPlayer) {
        boolean flag = true;
        BlockPos blockpos = null;
        ColumnPos columnpos = new ColumnPos(pos);
        if (!isPlayer && this.field_222275_f.containsKey(columnpos)) {
            return null;
        } else {
            GaiaTeleporter.PortalPosition teleporter$portalposition = this.destinationCoordinateCache.get(columnpos);
            if (teleporter$portalposition != null) {
                blockpos = teleporter$portalposition.blockPos;
                teleporter$portalposition.lastUpdateTime = this.world.getGameTime();
                flag = false;
            } else {
                double d0 = Double.MAX_VALUE;

                for(int j = -128; j <= 128; ++j) {
                    BlockPos blockpos2;
                    for(int k = -128; k <= 128; ++k) {
                        for(BlockPos blockpos1 = pos.add(j, this.world.getActualHeight() - 1 - pos.getY(), k); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                            blockpos2 = blockpos1.down();
                            if (this.world.getBlockState(blockpos1).getBlock() == BLOCK_GAIA_PORTAL) {
                                for(blockpos2 = blockpos1.down(); this.world.getBlockState(blockpos2).getBlock() == BLOCK_GAIA_PORTAL; blockpos2 = blockpos2.down()) {
                                    blockpos1 = blockpos2;
                                }

                                double d1 = blockpos1.distanceSq(pos);
                                if (d0 < 0.0D || d1 < d0) {
                                    d0 = d1;
                                    blockpos = blockpos1;
                                }
                            }
                        }
                    }
                }
            }

            if (blockpos == null) {
                long l = this.world.getGameTime() + 300L;
                this.field_222275_f.put(columnpos, l);
                return null;
            } else {
                if (flag) {
                    this.destinationCoordinateCache.put(columnpos, new GaiaTeleporter.PortalPosition(blockpos, this.world.getGameTime()));
                    Logger logger = GaiaDimensionMod.LOGGER;
                    Supplier[] asupplier = new Supplier[2];
                    Dimension dimension = this.world.getDimension();
                    asupplier[0] = dimension::getType;
                    asupplier[1] = () -> columnpos;
                    logger.debug("Adding nether portal ticket for {}:{}", asupplier[0], asupplier[1]);
                    this.world.getChunkProvider().func_217228_a(TicketType.PORTAL, new ChunkPos(blockpos), 3, columnpos);
                }

                BlockPattern.PatternHelper blockpattern$patternhelper = BLOCK_GAIA_PORTAL.createPatternHelper(this.world, blockpos);
                return blockpattern$patternhelper.func_222504_a(directon, blockpos, vecY, vec3d, vecX);
            }
        }
    }

    /**
     * Create a portal at the teleport location.
     */
    @Override
    public boolean makePortal(Entity entityIn) {
        int i = 16;
        double d0 = -1.0D;
        int entityX = MathHelper.floor(entityIn.posX);
        int entityY = MathHelper.floor(entityIn.posY);
        int entityZ = MathHelper.floor(entityIn.posZ);
        int eX = entityX;
        int eY = entityY;
        int eZ = entityZ;
        int l1 = 0;
        int i2 = this.random.nextInt(4);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int ePosX = entityX - i; ePosX <= entityX + i; ++ePosX) {
            double d1 = (double)ePosX + 0.5D - entityIn.posX;

            for (int ePosZ = entityZ - i; ePosZ <= entityZ + i; ++ePosZ) {
                double d2 = (double)ePosZ + 0.5D - entityIn.posZ;
                label293:

                for (int ePosY = this.world.getActualHeight() - 1; ePosY >= 0; --ePosY) {
                    if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(ePosX, ePosY, ePosZ))) {
                        while (ePosY > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(ePosX, ePosY - 1, ePosZ))) {
                            --ePosY;
                        }

                        for (int k3 = i2; k3 < i2 + 4; ++k3) {
                            int l3 = k3 % 2;
                            int i4 = 1 - l3;

                            if (k3 % 4 >= 2) {
                                l3 = -l3;
                                i4 = -i4;
                            }

                            for (int j4 = 0; j4 < 3; ++j4) {
                                for (int k4 = 0; k4 < 4; ++k4) {
                                    for (int l4 = -1; l4 < 4; ++l4) {
                                        int mbX = ePosX + (k4 - 1) * l3 + j4 * i4;
                                        int mbY = ePosY + l4;
                                        int mbZ = ePosZ + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutableblockpos.setPos(mbX, mbY, mbZ);

                                        if (l4 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label293;
                                        }
                                    }
                                }
                            }

                            double d5 = (double)ePosY + 0.5D - entityIn.posY;
                            double d7 = d1 * d1 + d5 * d5 + d2 * d2;

                            if (d0 < 0.0D || d7 < d0) {
                                d0 = d7;
                                eX = ePosX;
                                eY = ePosY;
                                eZ = ePosZ;
                                l1 = k3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (int iX = entityX - i; iX <= entityX + i; ++iX) {
                double mX = (double)iX + 0.5D - entityIn.posX;

                for (int iZ = entityZ - i; iZ <= entityZ + i; ++iZ) {
                    double mZ = (double)iZ + 0.5D - entityIn.posZ;
                    label231:

                    for (int iY = this.world.getActualHeight() - 1; iY >= 0; --iY) {
                        if (this.world.isAirBlock(blockpos$mutableblockpos.setPos(iX, iY, iZ))) {
                            while (iY > 0 && this.world.isAirBlock(blockpos$mutableblockpos.setPos(iX, iY - 1, iZ))) {
                                --iY;
                            }

                            for (int k7 = i2; k7 < i2 + 2; ++k7) {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10) {
                                    for (int j11 = -1; j11 < 4; ++j11) {
                                        int jX = iX + (j10 - 1) * j8;
                                        int jY = iY + j11;
                                        int jZ = iZ + (j10 - 1) * j9;
                                        blockpos$mutableblockpos.setPos(jX, jY, jZ);

                                        if (j11 < 0 && !this.world.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || j11 >= 0 && !this.world.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label231;
                                        }
                                    }
                                }

                                double d6 = (double)iY + 0.5D - entityIn.posY;
                                double d8 = mX * mX + d6 * d6 + mZ * mZ;

                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    eX = iX;
                                    eY = iY;
                                    eZ = iZ;
                                    l1 = k7 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i6 = eX;
        int k2 = eY;
        int k6 = eZ;
        int l6 = l1 % 2;
        int i3 = 1 - l6;

        if (l1 % 4 >= 2) {
            l6 = -l6;
            i3 = -i3;
        }

        if (d0 < 0.0D) {
            eY = MathHelper.clamp(eY, 70, this.world.getActualHeight() - 10);
            k2 = eY;

            for (int j7 = -1; j7 <= 1; ++j7) {
                for (int l7 = 1; l7 < 3; ++l7) {
                    for (int k8 = -1; k8 < 3; ++k8) {
                        int frameX = i6 + (l7 - 1) * l6 + j7 * i3;
                        int frameY = k2 + k8;
                        int frameZ = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        this.world.setBlockState(new BlockPos(frameX, frameY, frameZ), flag ? ModBlocks.keystone_block.getDefaultState() : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        BlockState iblockstate = ModBlocks.gaia_portal.getDefaultState().with(GaiaPortalBlock.AXIS, l6 == 0 ? Direction.Axis.Z : Direction.Axis.X);

        for (int i8 = 0; i8 < 4; ++i8) {
            for (int l8 = 0; l8 < 4; ++l8) {
                for (int l9 = -1; l9 < 4; ++l9) {
                    int portalX = i6 + (l8 - 1) * l6;
                    int portalY = k2 + l9;
                    int portalZ = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
                    this.world.setBlockState(new BlockPos(portalX, portalY, portalZ), flag1 ? ModBlocks.keystone_block.getDefaultState() : iblockstate, 2);
                }
            }

            for (int i9 = 0; i9 < 4; ++i9) {
                for (int i10 = -1; i10 < 4; ++i10) {
                    int i11 = i6 + (i9 - 1) * l6;
                    int i12 = k2 + i10;
                    int l12 = k6 + (i9 - 1) * i3;
                    BlockPos blockpos = new BlockPos(i11, i12, l12);
                    this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock());
                }
            }
        }

        return true;
    }

    static class PortalPosition {
        public final BlockPos blockPos;
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long time) {
            this.blockPos = pos;
            this.lastUpdateTime = time;
        }
    }
}