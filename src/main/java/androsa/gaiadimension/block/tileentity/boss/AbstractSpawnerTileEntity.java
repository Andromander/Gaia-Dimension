package androsa.gaiadimension.block.tileentity.boss;

import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public abstract class AbstractSpawnerTileEntity<T extends MobEntity> extends TileEntity implements ITickableTileEntity {

    private final EntityType<T> bossEntity;
    private boolean spawnedBoss = false;
    private Random random = new Random();

    public AbstractSpawnerTileEntity(TileEntityType<?> type, EntityType<T> boss) {
        super(type);
        bossEntity = boss;
    }

    @Override
    public void tick() {
        //Don't do a thing if we are out of range, because duh
        if (isInRange() && !spawnedBoss) {
            World world = getWorld();

            if (world.isRemote()) {
                //CLIENT: Spawn the particles...and that's it
                float r = (float)(getColor() >> 16 & 255) / 255.0F;
                float g = (float)(getColor() >> 8 & 255) / 255.0F;
                float b = (float)(getColor() >> 0 & 255) / 255.0F;
                double xPos = (double)pos.getX() + 0.5D + (random.nextDouble() - 0.5D);
                double yPos = (double)pos.getY() + 0.5D + (random.nextDouble() - 0.5D);
                double zPos = (double)pos.getZ() + 0.5D + (random.nextDouble() - 0.5D);

                world.addParticle(ModParticles.SPAWNER_CORE, xPos, yPos, zPos, r, g, b);
            } else {
                //SERVER: Spawning logic
                if (world.getDifficulty() != Difficulty.PEACEFUL) {
                    if (canSpawnBoss((ServerWorld)world)) {
                        world.destroyBlock(getPos(), false);
                        spawnedBoss = true;
                    }
                }
            }
        }
    }

    public boolean isInRange() {
        return this.getWorld().isPlayerWithin((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, getSpawnerRange());
    }

    public abstract double getSpawnerRange();

    public abstract int getColor();

    private boolean canSpawnBoss(ServerWorld world) {
        MobEntity entity = bossEntity.create(world);

        entity.moveToBlockPosAndAngles(getPos(), 0.0F, 0.0F);
        entity.onInitialSpawn(world, world.getDifficultyForLocation(getPos()), SpawnReason.SPAWNER, null, null);
        entity.setHomePosAndDistance(getPos(), getHomeDistance());

        return world.addEntity(entity);
    }

    public abstract int getHomeDistance();

    @Override
    public boolean onlyOpsCanSetNbt() {
        return true;
    }
}
