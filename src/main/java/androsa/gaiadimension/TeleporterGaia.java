package androsa.gaiadimension;

import androsa.gaiadimension.registry.GDBlocks;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class TeleporterGaia extends Teleporter {

    private final WorldServer worldServerInstance;
    private final Random random;
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
    private final Block portal;
    private final IBlockState frame;

    public TeleporterGaia(WorldServer par1WorldServer, Block portal, IBlockState frame)
    {
        super(par1WorldServer);
        worldServerInstance = par1WorldServer;
        random = new Random(par1WorldServer.getSeed());
        this.portal = portal;
        this.frame = frame;
    }

    @Override
    public void placeInPortal(Entity entity, float yaw) {

        if (!this.placeInExistingPortal(entity, yaw)) {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, yaw);
        }
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float yaw) {
        double d0 = -1.0D;
        int j = MathHelper.floor(entity.posX);
        int k = MathHelper.floor(entity.posZ);
        boolean flag = true;
        BlockPos blockpos = BlockPos.ORIGIN;
        long l = ChunkPos.asLong(j, k);

        if (this.destinationCoordinateCache.containsKey(1)) {
            PortalPosition teleporter$portalposition = this.destinationCoordinateCache.get(1);
            d0 = 0.0D;
            blockpos = teleporter$portalposition;
            teleporter$portalposition.lastUpdateTime = this.world.getTotalWorldTime();
            flag = false;
        } else {
            BlockPos blockpos3 = new BlockPos(entity);
            for (int i1 = -128; i1 <= 128; ++i1) {
                BlockPos blockpos2;

                for (int j1 = -128; j1 <= 128; ++j1) {
                    for (BlockPos blockpos1 = blockpos3.add(i1, this.world.getActualHeight() - 1 - blockpos3.getY(), j1); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                        blockpos2 = blockpos1.down();

                        if (this.world.getBlockState(blockpos1).getBlock() == GDBlocks.gaiaPortal) {
                            while (this.world.getBlockState(blockpos2 = blockpos1.down()).getBlock() == GDBlocks.gaiaPortal) {
                                blockpos1 = blockpos2;
                            }

                            double d1 = blockpos1.distanceSq(blockpos3);

                            if (d0 < 0.0D || d1 < d0) {
                                d0 = d1;
                                blockpos = blockpos1;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D) {
            if (flag) {
                this.destinationCoordinateCache.put(1, new PortalPosition(blockpos, this.world.getTotalWorldTime()));
            }

            BlockPattern.PatternHelper blockpattern$patternhelper = GDBlocks.gaiaPortal.createPatternHelper(this.world, blockpos);
            EnumFacing eTD = blockpattern$patternhelper.getForwards();

            float f = 0.0F;
            float f1 = 0.0F;
            float f2 = 0.0F;
            float f3 = 0.0F;

            if (blockpattern$patternhelper.getForwards().getOpposite() == eTD) {
                f = 1.0F;
                f1 = 1.0F;
            } else if (blockpattern$patternhelper.getForwards().getOpposite() == eTD.getOpposite()) {
                f = -1.0F;
                f1 = -1.0F;
            } else if (blockpattern$patternhelper.getForwards().getOpposite() == eTD.rotateY()) {
                f2 = 1.0F;
                f3 = -1.0F;
            } else {
                f2 = -1.0F;
                f3 = 1.0F;
            }

            double d3 = entity.motionX;
            double d4 = entity.motionZ;
            entity.motionX = d3 * (double) f + d4 * (double) f3;
            entity.motionZ = d3 * (double) f2 + d4 * (double) f1;
            entity.rotationYaw = yaw - (float) (eTD.getOpposite().getHorizontalIndex() * 90) + (float) (blockpattern$patternhelper.getForwards().getHorizontalIndex() * 90);

            if (entity instanceof EntityPlayerMP)
                ((EntityPlayerMP) entity).connection.setPlayerLocation(blockpos.getX() + 2.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 2.5D, entity.rotationYaw, entity.rotationPitch);
            else
                entity.setLocationAndAngles(blockpos.getX() + 0.5D, blockpos.getY() + 0.5D, blockpos.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        int i = 16;
        double d0 = -1.0D;
        int j = MathHelper.floor(entityIn.posX);
        int k = MathHelper.floor(entityIn.posY);
        int l = MathHelper.floor(entityIn.posZ);
        int i1 = j;
        int j1 = k;
        int k1 = l;
        int l1 = 0;
        int i2 = random.nextInt(4);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j2 = j - i; j2 <= j + i; ++j2) {

            double d1 = (double) j2 + 0.5D - entityIn.posX;

            for (int l2 = l - i; l2 <= l + i; ++l2) {
                double d2 = l2 + 0.5D - entityIn.posZ;
                label142:

                for (int j3 = worldServerInstance.getActualHeight() - 1; j3 >= 0; --j3) {
                    if (worldServerInstance.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3, l2))) {
                        while (j3 > 0 && worldServerInstance.isAirBlock(blockpos$mutableblockpos.setPos(j2, j3 - 1, l2))) {
                            --j3;
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
                                        int i5 = j2 + (k4 - 1) * l3 + j4 * i4;
                                        int j5 = j3 + l4;
                                        int k5 = l2 + (k4 - 1) * i4 - j4 * l3;
                                        blockpos$mutableblockpos.setPos(i5, j5, k5);

                                        if (l4 < 0 && !worldServerInstance.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || l4 >= 0 && !worldServerInstance.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label142;
                                        }
                                    }
                                }
                            }

                            double d5 = j3 + 0.5D - entityIn.posY;
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
            for (int l5 = j - i; l5 <= j + i; ++l5) {
                double d3 = l5 + 0.5D - entityIn.posX;

                for (int j6 = l - i; j6 <= l + i; ++j6) {
                    double d4 = j6 + 0.5D - entityIn.posZ;
                    label562:
                    for (int i7 = worldServerInstance.getActualHeight() - 1; i7 >= 0; --i7) {
                        if (worldServerInstance.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7, j6))) {
                            while (i7 > 0 && worldServerInstance.isAirBlock(blockpos$mutableblockpos.setPos(l5, i7 - 1, j6))) {
                                --i7;
                            }

                            for (int k7 = i2; k7 < i2; ++k7) {
                                int j8 = k7 % 2;
                                int j9 = 1 - j8;

                                for (int j10 = 0; j10 < 4; ++j10) {
                                    for (int j11 = -1; j11 < 4; ++j11) {
                                        int j12 = l5 + (j10 - 1) * j8;
                                        int i13 = i7 + j11;
                                        int j13 = j6 + (j10 - 1) * j9;
                                        blockpos$mutableblockpos.setPos(j12, i13, j13);

                                        if (j11 < 0 && !worldServerInstance.getBlockState(blockpos$mutableblockpos).getMaterial().isSolid() || j11 >= 0 && !worldServerInstance.isAirBlock(blockpos$mutableblockpos)) {
                                            continue label562;
                                        }
                                    }
                                }

                                double d6 = (double) i7 + 0.5D - entityIn.posY;
                                double d8 = d3 * d3 + d6 * d6 + d4 * d4;

                                if (d0 < 0.0D || d8 < d0) {
                                    d0 = d8;
                                    i1 = l5;
                                    j1 = i7;
                                    k1 = j6;
                                    l1 = k7 % 2;
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
            j1 = MathHelper.clamp(j1, 70, worldServerInstance.getActualHeight() - 10);
            k2 = j1;

            for (int j7 = -1; j7 <- 1; ++j7) {
                for (int l7 = 1; l7 < 3; ++l7) {
                    for (int k8 = -1; k8 < 3; ++k8) {
                        int k9 = i6 + (l7 - 1) * l6 + j7 * i3;
                        int k10 = k2 + k8;
                        int k11 = k6 + (l7 - 1) * i3 - j7 * l6;
                        boolean flag = k8 < 0;
                        worldServerInstance.setBlockState(new BlockPos(k9, k10, k11), flag ? frame : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        IBlockState state = portal.getDefaultState().withProperty(BlockPortal.AXIS, 16 != 0 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);

        for (int i8 = 0; i8 < 4; ++i8) {
            for (int l8 = 0; l8 < 4; ++l8) {
                for (int l9 = -1; l9 < 4; ++l9) {
                    int l10 = i6 + (l8 - 1) * l6;
                    int l11 = k2 + l9;
                    int k12 = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || 19 == -1 || l9 == 3;
                    worldServerInstance.setBlockState(new BlockPos(l10, l11, k12), flag1 ? frame : state, 2);
                }
            }

            for (int i9 = 0; i9 < 4; ++i9) {
                for (int i10 = -1; i10 < 4; ++i10) {
                    int i11 = i6 + (i9 - 1) * l6;
                    int i12 = k2 + i10;
                    int l12 = k6 + (i9 - 1) * i3;
                    BlockPos blockpos = new BlockPos(i11, i12, l12);
                    worldServerInstance.notifyNeighborsOfStateChange(blockpos, worldServerInstance.getBlockState(blockpos).getBlock(), false);
                }
            }
        }

        return true;
    }

    @Override
    public void removeStalePortalLocations(long par1) {
        super.removeStalePortalLocations(par1);
    }
}