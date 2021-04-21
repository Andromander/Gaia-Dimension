package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class MalachiteDroneEntity extends MonsterEntity {

    private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.defineId(MalachiteDroneEntity.class, DataSerializers.OPTIONAL_UUID);
    private LivingEntity owner;

    public MalachiteDroneEntity(EntityType<? extends MalachiteDroneEntity> entity, World world) {
        super(entity, world);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, false));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.6D, 16.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new FollowGuardGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setOwnerUniqueId(UUID.fromString(nbt.getString("OwnerUUID")));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.getOwnerUniqueId() != null) {
            nbt.putString("OwnerUUID", this.getOwnerUniqueId().toString());
        }
    }

    @Nullable
    public LivingEntity getOwner() {
        if (getOwnerUniqueId() != null && this.level instanceof ServerWorld) {
            Entity entity = ((ServerWorld) level).getEntity(getOwnerUniqueId());
            if (entity instanceof LivingEntity) {
                return (LivingEntity) entity;
            }
        }
        return owner;
    }

    public void setOwner(@Nullable LivingEntity entity) {
        this.owner = entity;
        setOwnerUniqueId(entity == null ? null : entity.getUUID());
    }

    @Nullable
    public UUID getOwnerUniqueId() {
        return this.entityData.get(OWNER_UNIQUE_ID).orElse(null);
    }

    public void setOwnerUniqueId(@Nullable UUID id) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(id));
    }

    @Override
    public void tick() {
        super.tick();

        if (level instanceof ServerWorld) {
            if (getOwnerUniqueId() != null) {
                //Check if we have been removed too far away from the Guard.
                Entity entity = ((ServerWorld) level).getEntity(getOwnerUniqueId());

                //Wrong dimension, sever the link
                if (this.level.dimension() != entity.level.dimension()) {
                    ownerRemoved(entity);
                }

                //Too far away, and we can't go back
                if (this.distanceToSqr(entity) > 500.0D && (this.isLeashed() || this.isPassenger())) {
                    ownerRemoved(entity);
                }
            }
        }
    }

    private void ownerRemoved(Entity owner) {
        if (owner instanceof MalachiteGuardEntity) {
            ((MalachiteGuardEntity) owner).onDroneKilled();
        }
        this.setOwnerUniqueId(null);

        if (level.isClientSide()) {
            double px = this.getX() + this.random.nextFloat() * this.getBbWidth() * 2.0F - this.getBbWidth();
            double py = this.getY() + this.random.nextFloat() * this.getBbHeight();
            double pz = this.getZ() + this.random.nextFloat() * this.getBbWidth() * 2.0F - this.getBbWidth();
            this.level.addParticle(ModParticles.SPAWNER_CORE, px, py, pz, 0, 128, 0);
        }

        level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.TOTEM_USE, SoundCategory.HOSTILE, 1.0F, 1.0F);
    }

    @Override
    public void die(DamageSource source) {
        if (ForgeHooks.onLivingDeath(this,  source)) //The event was cancelled, therefore do not decrease the Guard's tracker.
            return;

        if (level instanceof ServerWorld) {
            @Nullable Entity entity = ((ServerWorld) level).getEntity(getOwnerUniqueId());

            //Were we a follower, and was it a Guard? If so, detract that Guard's counter
            if (entity != null) {
                if (entity instanceof MalachiteGuardEntity) {
                    ((MalachiteGuardEntity) entity).onDroneKilled();
                }
            }
        }
        super.die(source);
    }

    @Override
    public boolean removeWhenFarAway(double dist) {
        //Don't despawn if we have an owner. Disregard whether it's a Guard because we don't want to disappear in general.
        return ((ServerWorld) level).getEntity(getOwnerUniqueId()) != null;
    }

    @Override
    public void remove(boolean keepData) {
        //If you are a mod that removes an entity with this, be ashamed of yourself. Just kill or despawn it.
        if (level instanceof ServerWorld) {
            @Nullable Entity entity = ((ServerWorld) level).getEntity(getOwnerUniqueId());

            if (entity != null) {
                if (entity instanceof MalachiteGuardEntity) {
                    ((MalachiteGuardEntity) entity).onDroneKilled();
                }
            }
        }
        super.remove(keepData);
    }

    static class FollowGuardGoal extends Goal {
        private final MalachiteDroneEntity drone;
        private MalachiteGuardEntity guard;
        private final IWorldReader world;
        private final double followSpeed = 0.4D;
        private final PathNavigator navigator;
        private int timeToRecalcPath;
        private final float maxDist = 2.0F;
        private final float minDist = 10.0F;
        private float oldWaterCost;

        public FollowGuardGoal(MalachiteDroneEntity entity) {
            this.drone = entity;
            this.world = entity.level;
            this.navigator = entity.getNavigation();
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity owner = this.drone.getOwner();
            if (owner == null) {
                return false;
            } else if (owner.isSpectator()) {
                return false;
            } else if (this.drone.distanceToSqr(owner) < (double)(this.minDist * this.minDist)) {
                return false;
            } else if (!(owner instanceof MalachiteGuardEntity)) {
                return false;
            } else {
                this.guard = (MalachiteGuardEntity) owner;
                return true;
            }
        }

        public boolean shouldContinueExecuting() {
            return !this.navigator.isDone() && this.drone.distanceToSqr(this.guard) > (double) (this.maxDist * this.maxDist);
        }

        public void startExecuting() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.drone.getPathfindingMalus(PathNodeType.WATER);
            this.drone.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        }

        @Override
        public void stop() {
            this.guard = null;
            this.navigator.stop();
            this.drone.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
        }

        @Override
        public void tick() {
            this.drone.getLookControl().setLookAt(this.guard, 10.0F, (float)this.drone.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                if (!this.drone.isLeashed() && !this.drone.isPassenger()) {
                    if (this.drone.distanceToSqr(this.guard) >= 144.0D) {
                        this.tryTeleport();
                    } else {
                        this.navigator.moveTo(this.guard, this.followSpeed);
                    }
                }
            }
        }

        private void tryTeleport() {
            BlockPos guardpos = new BlockPos(guard.blockPosition());

            for(int chance = 0; chance < 10; ++chance) {
                int rx = this.getRandomInt(-3, 3);
                int ry = this.getRandomInt(-1, 1);
                int rz = this.getRandomInt(-3, 3);
                boolean teleport = this.tryTeleportTo(guardpos.getX() + rx, guardpos.getY() + ry, guardpos.getZ() + rz);
                if (teleport) {
                    return;
                }
            }
        }

        private boolean tryTeleportTo(int x, int y, int z) {
            if (Math.abs((double)x - this.guard.getX()) < 2.0D && Math.abs((double)z - this.guard.getZ()) < 2.0D) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
                return false;
            } else {
                this.drone.moveTo((double)((float)x + 0.5F), (double)y, (double)((float)z + 0.5F), this.drone.yRot, this.drone.xRot);
                this.navigator.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            PathNodeType nodeType = WalkNodeProcessor.getBlockPathTypeStatic(this.world, pos.mutable());
            if (nodeType != PathNodeType.WALKABLE) {
                return false;
            } else {
                BlockState state = this.world.getBlockState(pos.below());
                if (state.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos posDown = pos.subtract(this.drone.blockPosition());
                    return this.world.noCollision(this.drone, this.drone.getBoundingBox().move(posDown));
                }
            }
        }

        private int getRandomInt(int min, int max) {
            return this.drone.getRandom().nextInt(max - min + 1) + min;
        }
    }
}
