package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.boss.MalachiteGuard;
import androsa.gaiadimension.registry.registration.ModParticles;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class MalachiteDrone extends Monster {

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(MalachiteDrone.class, EntityDataSerializers.OPTIONAL_UUID);
    private LivingEntity owner;

    public MalachiteDrone(EntityType<? extends MalachiteDrone> entity, Level world) {
        super(entity, world);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OWNER_UNIQUE_ID, Optional.empty());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, false));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.6D, 16.0F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new FollowGuardGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setOwnerUniqueId(UUID.fromString(nbt.getString("OwnerUUID")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.getOwnerUniqueId() != null) {
            nbt.putString("OwnerUUID", this.getOwnerUniqueId().toString());
        }
    }

    @Nullable
    public LivingEntity getOwner() {
        if (getOwnerUniqueId() != null && this.level() instanceof ServerLevel server) {
            Entity entity = server.getEntity(getOwnerUniqueId());
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
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MALACHITE_DRONE_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_MALACHITE_DRONE_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (level() instanceof ServerLevel server) {
            if (getOwnerUniqueId() != null) {
                //Check if we have been removed too far away from the Guard.
                Entity entity = server.getEntity(getOwnerUniqueId());

                if (entity != null) {
                    //Wrong dimension, sever the link
                    if (this.level().dimension() != entity.level().dimension()) {
                        ownerRemoved(entity);
                    }

                    //Too far away, and we can't go back
                    if (this.distanceToSqr(entity) > 500.0D && (this.isLeashed() || this.isPassenger())) {
                        ownerRemoved(entity);
                    }
                } else {
                    //We've lost out owner
                    setOwnerUniqueId(null);
                }

            }
        }
    }

    private void ownerRemoved(Entity owner) {
        if (owner instanceof MalachiteGuard guard) {
            guard.onDroneKilled();
        }
        this.setOwnerUniqueId(null);

        if (level().isClientSide()) {
            double px = this.getX() + this.random.nextFloat() * this.getBbWidth() * 2.0F - this.getBbWidth();
            double py = this.getY() + this.random.nextFloat() * this.getBbHeight();
            double pz = this.getZ() + this.random.nextFloat() * this.getBbWidth() * 2.0F - this.getBbWidth();
            this.level().addParticle(ModParticles.SPAWNER_CORE.get(), px, py, pz, 0, 128, 0);
        }

        level().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.ENTITY_MALACHITE_DRONE_DESYNC.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    @Override
    public void remove(RemovalReason reason) {
        if (reason == RemovalReason.DISCARDED) {
            if (this.getOwnerUniqueId() != null) {
                this.ownerRemoved(this.getOwner());
            }
        }
        super.remove(reason);
    }

    @Override
    public void die(DamageSource source) {
        if (CommonHooks.onLivingDeath(this,  source)) //The event was cancelled, therefore do not decrease the Guard's tracker.
            return;

        if (level() instanceof ServerLevel server) {
            @Nullable Entity entity = server.getEntity(getOwnerUniqueId());

            //Were we a follower, and was it a Guard? If so, detract that Guard's counter
            if (entity != null) {
                if (entity instanceof MalachiteGuard guard) {
                    guard.onDroneKilled();
                }
            }
        }
        super.die(source);
    }

    @Override
    public boolean removeWhenFarAway(double dist) {
        //Don't despawn if we have an owner. Disregard whether it's a Guard because we don't want to disappear in general.
        return this.getOwnerUniqueId() == null;
    }

    static class FollowGuardGoal extends Goal {
        private final MalachiteDrone drone;
        private MalachiteGuard guard;
        private final LevelAccessor world;
        private final double followSpeed = 0.4D;
        private final PathNavigation navigator;
        private int timeToRecalcPath;
        private final float maxDist = 2.0F;
        private final float minDist = 10.0F;
        private float oldWaterCost;

        public FollowGuardGoal(MalachiteDrone entity) {
            this.drone = entity;
            this.world = entity.level();
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
            } else if (!(owner instanceof MalachiteGuard)) {
                return false;
            } else {
                this.guard = (MalachiteGuard) owner;
                return true;
            }
        }

        public boolean canContinueToUse() {
            return !this.navigator.isDone() && this.drone.distanceToSqr(this.guard) > (double) (this.maxDist * this.maxDist);
        }

        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.drone.getPathfindingMalus(PathType.WATER);
            this.drone.setPathfindingMalus(PathType.WATER, 0.0F);
        }

        @Override
        public void stop() {
            this.guard = null;
            this.navigator.stop();
            this.drone.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
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
                this.drone.moveTo((float)x + 0.5F, y, (float)z + 0.5F, this.drone.getYRot(), this.drone.getXRot());
                this.navigator.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            PathType nodeType = WalkNodeEvaluator.getPathTypeStatic(this.drone, pos);
            if (nodeType != PathType.WALKABLE) {
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
