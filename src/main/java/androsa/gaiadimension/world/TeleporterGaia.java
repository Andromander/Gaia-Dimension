package androsa.gaiadimension.world;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import java.util.Random;

public class TeleporterGaia extends Teleporter {

    private final Random random;
    @SuppressWarnings("unchecked")
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
    private final Block portal;
    private final IBlockState frame;

    public TeleporterGaia(WorldServer par1WorldServer, Block portal, IBlockState frame) {
        super(par1WorldServer);
        random = new Random(par1WorldServer.getSeed());
        this.portal = portal;
        this.frame = frame;
    }

    /**
     * Check whether a new portal should be created or not.
     * If we cannot place in an already made portal, make one, them put the entity in there.
     */
    @Override
    public void placeInPortal(@Nonnull Entity entity, float yaw) {
        if (!this.placeInExistingPortal(entity, yaw)) {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, yaw);
        }
    }

    /**
     * If we find an already-made portal, put the entity there.
     */
    @Override
    public boolean placeInExistingPortal(Entity entity, float yaw) {
        double d0 = -1.0D;
        boolean flag = true;
        BlockPos blockpos = BlockPos.ORIGIN;

        if (this.destinationCoordinateCache.containsKey(1)) {
            PortalPosition teleporter$portalposition = this.destinationCoordinateCache.get(1);
            d0 = 0.0D;
            blockpos = teleporter$portalposition;
            teleporter$portalposition.lastUpdateTime = this.world.getTotalWorldTime();
            flag = false;
        } else {
            BlockPos blockpos3 = new BlockPos(entity);
            for (int eX = -128; eX <= 128; ++eX) {
                BlockPos blockpos2;

                for (int eY = -128; eY <= 128; ++eY) {
                    for (BlockPos blockpos1 = blockpos3.add(eX, this.world.getActualHeight() - 1 - blockpos3.getY(), eY); blockpos1.getY() >= 0; blockpos1 = blockpos2) {
                        blockpos2 = blockpos1.down();

                        if (this.world.getBlockState(blockpos1).getBlock() == GDBlocks.gaia_portal) {
                            while (this.world.getBlockState(blockpos2 = blockpos1.down()).getBlock() == GDBlocks.gaia_portal) {
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

            BlockPattern.PatternHelper blockpattern$patternhelper = GDBlocks.gaia_portal.createPatternHelper(this.world, blockpos);
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

            double mX = entity.motionX;
            double mZ = entity.motionZ;
            entity.motionX = mX * (double) f + mZ * (double) f3;
            entity.motionZ = mX * (double) f2 + mZ * (double) f1;
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

    /**
     * Create a portal at the teleport location.
     */
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
                        this.world.setBlockState(new BlockPos(frameX, frameY, frameZ), flag ? frame : Blocks.AIR.getDefaultState());
                    }
                }
            }
        }

        IBlockState iblockstate = portal.getDefaultState().withProperty(BlockPortal.AXIS, l6 == 0 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);

        for (int i8 = 0; i8 < 4; ++i8) {
            for (int l8 = 0; l8 < 4; ++l8) {
                for (int l9 = -1; l9 < 4; ++l9) {
                    int portalX = i6 + (l8 - 1) * l6;
                    int portalY = k2 + l9;
                    int portalZ = k6 + (l8 - 1) * i3;
                    boolean flag1 = l8 == 0 || l8 == 3 || l9 == -1 || l9 == 3;
                    this.world.setBlockState(new BlockPos(portalX, portalY, portalZ), flag1 ? frame : iblockstate, 2);
                }
            }

            for (int i9 = 0; i9 < 4; ++i9) {
                for (int i10 = -1; i10 < 4; ++i10) {
                    int i11 = i6 + (i9 - 1) * l6;
                    int i12 = k2 + i10;
                    int l12 = k6 + (i9 - 1) * i3;
                    BlockPos blockpos = new BlockPos(i11, i12, l12);
                    this.world.notifyNeighborsOfStateChange(blockpos, this.world.getBlockState(blockpos).getBlock(), false);
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