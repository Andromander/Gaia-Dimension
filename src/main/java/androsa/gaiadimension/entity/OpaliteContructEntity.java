package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class OpaliteContructEntity extends PathfinderMob {

    private static final EntityDataAccessor<Optional<UUID>> MOOKAITE_COMPANION_UUID = SynchedEntityData.defineId(OpaliteContructEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    public OpaliteContructEntity(EntityType<? extends OpaliteContructEntity> entity, Level level) {
        super(entity, level);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ARMOR, 0.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MOOKAITE_COMPANION_UUID, Optional.empty());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("MookaiteUUID")) {
            this.setMookaiteCompanion(tag.getUUID("MookaiteUUID"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.getMookaiteCompanion() != null) {
            tag.putUUID("MookaiteUUID", this.getMookaiteCompanion());
        }
    }

    public void setMookaiteCompanion(UUID id) {
        this.entityData.set(MOOKAITE_COMPANION_UUID, Optional.ofNullable(id));
    }

    public UUID getMookaiteCompanion() {
        return this.entityData.get(MOOKAITE_COMPANION_UUID).orElse(null);
    }

    @Nullable
    public MookaiteConstructEntity getFollowing() {
        if (getMookaiteCompanion() != null && this.level instanceof ServerLevel server) {
            Entity entity = server.getEntity(getMookaiteCompanion());
            if (entity instanceof MookaiteConstructEntity) {
                return (MookaiteConstructEntity) entity;
            }
        }
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new FollowCompanionGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_OPALITE_CONSTRUCT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_OPALITE_CONSTRUCT_DEATH.get();
    }

    public static boolean canSpawnHere(EntityType<OpaliteContructEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public void die(DamageSource source) {
        if (ForgeHooks.onLivingDeath(this, source)) return;

        if (this.level instanceof ServerLevel level && this.getMookaiteCompanion() != null) {
            Entity entity = level.getEntity(this.getMookaiteCompanion());
            if (entity instanceof MookaiteConstructEntity mookaite) {
                mookaite.setOpaliteCompanion(null);
            }
        }

        super.die(source);
    }

    static class FollowCompanionGoal extends Goal {
        private final OpaliteContructEntity opalite;
        private MookaiteConstructEntity mookaite;
        private final LevelAccessor world;
        private final double followSpeed = 0.4D;
        private final PathNavigation navigator;
        private int timeToRecalcPath;
        private final float maxDist = 2.0F;
        private final float minDist = 10.0F;
        private float oldWaterCost;

        public FollowCompanionGoal(OpaliteContructEntity entity) {
            this.opalite = entity;
            this.world = entity.level;
            this.navigator = entity.getNavigation();
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            MookaiteConstructEntity mookaite = this.opalite.getFollowing();
            if (mookaite == null) {
                return false;
            } else if (mookaite.isSpectator()) {
                return false;
            } else if (this.opalite.distanceToSqr(mookaite) < (double)(this.minDist * this.minDist)) {
                return false;
            } else {
                this.mookaite = mookaite;
                return true;
            }
        }

        public boolean canContinueToUse() {
            return !this.navigator.isDone() && this.opalite.distanceToSqr(this.mookaite) > (double) (this.maxDist * this.maxDist);
        }

        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.opalite.getPathfindingMalus(BlockPathTypes.WATER);
            this.opalite.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        }

        @Override
        public void stop() {
            this.mookaite = null;
            this.navigator.stop();
            this.opalite.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
        }

        @Override
        public void tick() {
            this.opalite.getLookControl().setLookAt(this.mookaite, 10.0F, (float)this.opalite.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                if (!this.opalite.isLeashed() && !this.opalite.isPassenger()) {
                    if (this.opalite.distanceToSqr(this.mookaite) >= 144.0D) {
                        this.tryTeleport();
                    } else {
                        this.navigator.moveTo(this.mookaite, this.followSpeed);
                    }
                }
            }
        }

        private void tryTeleport() {
            BlockPos guardpos = new BlockPos(mookaite.blockPosition());

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
            if (Math.abs((double)x - this.mookaite.getX()) < 2.0D && Math.abs((double)z - this.mookaite.getZ()) < 2.0D) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
                return false;
            } else {
                this.opalite.moveTo((float)x + 0.5F, y, (float)z + 0.5F, this.opalite.getYRot(), this.opalite.getXRot());
                this.navigator.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            BlockPathTypes nodeType = WalkNodeEvaluator.getBlockPathTypeStatic(this.world, pos.mutable());
            if (nodeType != BlockPathTypes.WALKABLE) {
                return false;
            } else {
                BlockState state = this.world.getBlockState(pos.below());
                if (state.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos posDown = pos.subtract(this.opalite.blockPosition());
                    return this.world.noCollision(this.opalite, this.opalite.getBoundingBox().move(posDown));
                }
            }
        }

        private int getRandomInt(int min, int max) {
            return this.opalite.getRandom().nextInt(max - min + 1) + min;
        }
    }
}
