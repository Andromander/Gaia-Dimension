package androsa.gaiadimension.block.blockentity.boss;

import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public abstract class AbstractSpawnerBlockEntity<T extends Mob> extends BlockEntity {

    private final EntityType<T> bossEntity;
    private boolean spawnedBoss = false;
    private final Random random = new Random();

    public AbstractSpawnerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, EntityType<T> boss) {
        super(type, pos, state);
        bossEntity = boss;
    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, AbstractSpawnerBlockEntity entity) {
        if (entity.isInRange() && !entity.spawnedBoss) {
            //CLIENT: Spawn the particles...and that's it
            float r = (float)(entity.getColor() >> 16 & 255) / 255.0F;
            float g = (float)(entity.getColor() >> 8 & 255) / 255.0F;
            float b = (float)(entity.getColor() >> 0 & 255) / 255.0F;
            double xPos = (double)pos.getX() + 0.5D + (entity.random.nextDouble() - 0.5D);
            double yPos = (double)pos.getY() + 0.5D + (entity.random.nextDouble() - 0.5D);
            double zPos = (double)pos.getZ() + 0.5D + (entity.random.nextDouble() - 0.5D);

            level.addParticle(ModParticles.SPAWNER_CORE, xPos, yPos, zPos, r, g, b);
        }
    }

    public static void spawnTick(Level level, BlockPos pos, BlockState state, AbstractSpawnerBlockEntity entity) {
        if (entity.isInRange() && !entity.spawnedBoss) {
            //SERVER: Spawning logic
            if (level.getDifficulty() != Difficulty.PEACEFUL) {
                if (entity.canSpawnBoss((ServerLevel)level)) {
                    level.destroyBlock(entity.getBlockPos(), false);
                    entity.spawnedBoss = true;
                }
            }
        }
    }

    public boolean isInRange() {
        return this.getLevel().hasNearbyAlivePlayer((double)worldPosition.getX() + 0.5D, (double)worldPosition.getY() + 0.5D, (double)worldPosition.getZ() + 0.5D, getSpawnerRange());
    }

    public abstract double getSpawnerRange();

    public abstract int getColor();

    private boolean canSpawnBoss(ServerLevel world) {
        Mob entity = bossEntity.create(world);

        entity.moveTo(getBlockPos(), 0.0F, 0.0F);
        entity.finalizeSpawn(world, world.getCurrentDifficultyAt(getBlockPos()), MobSpawnType.SPAWNER, null, null);
        entity.restrictTo(getBlockPos(), getHomeDistance());

        return world.addFreshEntity(entity);
    }

    public abstract int getHomeDistance();

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }
}
