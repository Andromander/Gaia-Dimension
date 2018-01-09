package androsa.gaiadimension;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.WorldProviderGaia;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.Random;


public class TeleporterGaia extends Teleporter {

    private static Block PORTAL_BLOCK;
    private static Block PORTAL_FRAME_BLOCK;

    private final WorldServer world;
    private final Random random;

    public TeleporterGaia(WorldServer world) {
        super(world);
        //PORTAL_BLOCK = GDBlocks.gaiaPortal;
        PORTAL_FRAME_BLOCK = Blocks.GOLD_BLOCK;
        this.world = world;
        this.random = new Random(world.getSeed());
    }

    @Override
    public void placeInPortal(Entity entity, float yaw) {

        if (!placeInExistingPortal(entity, yaw)) {
            makePortal(entity);
            placeInExistingPortal(entity, yaw);
        }
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float f) {
        int searchArea = 148;
        double closestPortal = -1D;
        int foundX = 0;
        int foundY = 0;
        int foundZ = 0;
        int entityX = MathHelper.floor(entity.posX);
        int entityZ = MathHelper.floor(entity.posZ);
        BlockPos blockpos = BlockPos.ORIGIN;

        long j1 = ChunkPos.asLong(entityX, entityZ);

        for (int x = entityX - searchArea; x <= entityX + searchArea; x++) {
            double distX = x + 0.5D - entity.posX;

            for (int z = entityZ - searchArea; z <= entityZ + searchArea; z++) {
                double distZ = z + 0.5D - entity.posZ;

                for (int y = world.getActualHeight() - 1; y >= 0; y--) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (world.getBlockState(pos).getBlock() == PORTAL_BLOCK) {
                        pos = pos.down();
                        while (world.getBlockState(pos).getBlock() == PORTAL_BLOCK) {
                            --y;
                            pos = pos.down();
                        }

                        double distY = y + 0.5D - entity.posY;
                        double distance = distX * distX + distY * distY + distZ * distZ;
                        if (closestPortal < 0.0D || distance < closestPortal) {
                            closestPortal = distance;
                            foundX = x;
                            foundY = y;
                            foundZ = z;
                        }
                    }
                }
            }
        }

        if (closestPortal >= 0.0D)
        {
            int x = foundX;
            int y = foundY;
            int z = foundZ;
            double newLocX = x + 0.5D;
            double newLocY = y + 0.5D;
            double newLocZ = z + 0.5D;

            BlockPos pos = new BlockPos(x, y, z);

            if (world.getBlockState(pos.offset(EnumFacing.WEST)).getBlock() == PORTAL_BLOCK)
            {
                newLocX -= 0.5D;
            }
            if (world.getBlockState(pos.offset(EnumFacing.EAST)).getBlock() == PORTAL_BLOCK)
            {
                newLocX += 0.5D;
            }
            if (world.getBlockState(pos.offset(EnumFacing.NORTH)).getBlock() == PORTAL_BLOCK)
            {
                newLocZ -= 0.5D;
            }
            if (world.getBlockState(pos.offset(EnumFacing.SOUTH)).getBlock() == PORTAL_BLOCK)
            {
                newLocZ += 0.5D;
            }
            entity.setLocationAndAngles(newLocX, newLocY + 2, newLocZ, entity.rotationYaw, 0.0F);
            int worldSpawnX = MathHelper.floor(newLocX);
            int worldSpawnZ = MathHelper.floor(newLocZ);
            int worldSpawnY = world.getHeight(new BlockPos(worldSpawnX, 0, worldSpawnZ)).getY() + 3;

            entity.motionX = entity.motionY = entity.motionZ = 0.0D;

            return true;
        } else {
            return false;
        }
    }
}
