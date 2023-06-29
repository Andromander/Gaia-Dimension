package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.projectile.MookaiteAmmoEntity;
import androsa.gaiadimension.entity.projectile.MookaiteMagicEntity;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.registration.ModSounds;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MookaiteConstructEntity extends PathfinderMob {

    private static final EntityDataAccessor<Optional<UUID>> BOND_CREATOR_UUID = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> OPALITE_COMPANION_UUID = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> IS_BURNING = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> LEFT_HORN_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_HORN_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_EYE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_EYE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_SHOULDER_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_SHOULDER_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_ARM_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_ARM_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_LEG_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_LEG_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final Map<Integer, String> INT_TO_COLOR = Util.make(Maps.newHashMap(), (map) -> {
        map.put(0, "none");
        map.put(1, "scarlet");
        map.put(2, "auburn");
        map.put(3, "gold");
        map.put(4, "mauve");
        map.put(5, "beige");
        map.put(6, "ivory");
        map.put(7, "opalite");
    });
    public static final List<EntityDataAccessor<Integer>> PARTS = ImmutableList.of(
            LEFT_HORN_TYPE, RIGHT_HORN_TYPE,
            LEFT_EYE_TYPE, RIGHT_EYE_TYPE,
            LEFT_SHOULDER_TYPE, RIGHT_SHOULDER_TYPE,
            LEFT_ARM_BRACE_TYPE, RIGHT_ARM_BRACE_TYPE,
            LEFT_LEG_BRACE_TYPE, RIGHT_LEG_BRACE_TYPE
    );
    protected int attackCooldown = 200;

    public MookaiteConstructEntity(EntityType<? extends MookaiteConstructEntity> entity, Level level) {
        super(entity, level);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
                .add(Attributes.ARMOR, 1.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.5F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOND_CREATOR_UUID, Optional.empty());
        this.entityData.define(OPALITE_COMPANION_UUID, Optional.empty());
        this.entityData.define(IS_BURNING, false);
        this.entityData.define(LEFT_HORN_TYPE, 0);
        this.entityData.define(RIGHT_HORN_TYPE, 0);
        this.entityData.define(LEFT_EYE_TYPE, 0);
        this.entityData.define(RIGHT_EYE_TYPE, 0);
        this.entityData.define(LEFT_SHOULDER_TYPE, 0);
        this.entityData.define(RIGHT_SHOULDER_TYPE, 0);
        this.entityData.define(LEFT_ARM_BRACE_TYPE, 0);
        this.entityData.define(RIGHT_ARM_BRACE_TYPE, 0);
        this.entityData.define(LEFT_LEG_BRACE_TYPE, 0);
        this.entityData.define(RIGHT_LEG_BRACE_TYPE, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("BonderUUID")) {
            this.setBonder(tag.getUUID("BonderUUID"));
        }
        if (tag.hasUUID("OpaliteUUID")) {
            this.setOpaliteCompanion(tag.getUUID("OpaliteUUID"));
        }
        this.setBurning(tag.getBoolean("IsBurning"));
        this.setPart(LEFT_HORN_TYPE, tag.getInt("LeftHornType"));
        this.setPart(RIGHT_HORN_TYPE, tag.getInt("RightHornType"));
        this.setPart(LEFT_EYE_TYPE, tag.getInt("LeftEyeType"));
        this.setPart(RIGHT_EYE_TYPE, tag.getInt("RightEyeType"));
        this.setPart(LEFT_SHOULDER_TYPE, tag.getInt("LeftShoulderType"));
        this.setPart(RIGHT_SHOULDER_TYPE, tag.getInt("RightShoulderType"));
        this.setPart(LEFT_ARM_BRACE_TYPE, tag.getInt("LeftArmBraceType"));
        this.setPart(RIGHT_ARM_BRACE_TYPE, tag.getInt("RightArmBraceType"));
        this.setPart(LEFT_LEG_BRACE_TYPE, tag.getInt("LeftLegBraceType"));
        this.setPart(RIGHT_LEG_BRACE_TYPE, tag.getInt("RightLegBraceType"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.getBonder() != null) {
            tag.putUUID("BonderUUID", this.getBonder());
        }
        if (this.getOpaliteCompanion() != null) {
            tag.putUUID("OpaliteUUID", this.getOpaliteCompanion());
        }
        tag.putBoolean("IsBurning", isBurning());
        tag.putInt("LeftHornType", getPart(LEFT_HORN_TYPE));
        tag.putInt("RightHornType", getPart(RIGHT_HORN_TYPE));
        tag.putInt("LeftEyeType", getPart(LEFT_EYE_TYPE));
        tag.putInt("RightEyeType", getPart(RIGHT_EYE_TYPE));
        tag.putInt("LeftShoulderType", getPart(LEFT_SHOULDER_TYPE));
        tag.putInt("RightShoulderType", getPart(RIGHT_SHOULDER_TYPE));
        tag.putInt("LeftArmBraceType", getPart(LEFT_ARM_BRACE_TYPE));
        tag.putInt("RightArmBraceType", getPart(RIGHT_ARM_BRACE_TYPE));
        tag.putInt("LeftLegBraceType", getPart(LEFT_LEG_BRACE_TYPE));
        tag.putInt("RightLegBraceType", getPart(RIGHT_LEG_BRACE_TYPE));
    }

    public void setBonder(UUID id) {
        this.entityData.set(BOND_CREATOR_UUID, Optional.ofNullable(id));
    }

    public UUID getBonder() {
        return this.entityData.get(BOND_CREATOR_UUID).orElse(null);
    }

    public void setOpaliteCompanion(UUID id) {
        this.entityData.set(OPALITE_COMPANION_UUID, Optional.ofNullable(id));
    }

    public UUID getOpaliteCompanion() {
        return this.entityData.get(OPALITE_COMPANION_UUID).orElse(null);
    }

    public void setBurning(boolean flag) {
        this.entityData.set(IS_BURNING, flag);
    }

    public boolean isBurning() {
        return this.entityData.get(IS_BURNING);
    }

    public boolean isPresent(EntityDataAccessor<Integer> part) {
        int value = this.getPart(part);
        return value > 0 && value <= 7;
    }

    public int getPart(EntityDataAccessor<Integer> part) {
        return entityData.get(part);
    }

    public void setPart(EntityDataAccessor<Integer> part, int value) {
        if (value < 0 || value > 7) {
            value = random.nextInt(7);
        }
        entityData.set(part, value);
    }

    public int countColors(int colour) {
        int amount = 0;
        for (EntityDataAccessor<Integer> part : PARTS) {
            if (this.getPart(part) == colour) amount++;
        }
        return amount;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ScarletShockwaveGoal(this));
        this.goalSelector.addGoal(1, new AuburnFireGoal(this));
        this.goalSelector.addGoal(1, new GoldMagicGoal(this));
        this.goalSelector.addGoal(1, new MauveElectricGoal(this));
        this.goalSelector.addGoal(1, new IvoryProjectileGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.3D, 32.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_MOOKAITE_CONSTRUCT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MOOKAITE_CONSTRUCT_DEATH.get();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawn, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        this.setPart(LEFT_HORN_TYPE, random.nextInt(7));
        this.setPart(RIGHT_HORN_TYPE, random.nextInt(7));
        this.setPart(LEFT_EYE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_EYE_TYPE, random.nextInt(7));
        this.setPart(LEFT_SHOULDER_TYPE, random.nextInt(7));
        this.setPart(RIGHT_SHOULDER_TYPE, random.nextInt(7));
        this.setPart(LEFT_ARM_BRACE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_ARM_BRACE_TYPE, random.nextInt(7));
        this.setPart(LEFT_LEG_BRACE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_LEG_BRACE_TYPE, random.nextInt(7));
        return super.finalizeSpawn(level, difficulty, spawn, data, tag);
    }

    public static boolean canSpawnHere(EntityType<MookaiteConstructEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.is(DamageTypeTags.IS_EXPLOSION)) {
            return this.countColors(1) >= 10; //Full Scarlet is Explosion immune
        }
        if (source.is(DamageTypeTags.IS_FIRE)) {
            return this.countColors(2) >= 10; //Full Auburn is Fire immune
        }
        if (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC)) {
            return this.countColors(3) >= 10; //Full Gold is Magic immune
        }
        if (source.is(GaiaDamage.STATIC)) {
            return this.countColors(4) >= 10; //Full Mauve is Static immune
        }
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            return this.countColors(6) >= 10; //Full Ivory is Projectile immune
        }

        return super.isInvulnerableTo(source);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        //Any amount of Beige heals
        if (this.countColors(5) > 0) {
            //More Beige shortens time between heals
            int ticks = 220 - this.countColors(5) * 20;
            if (this.level().getGameTime() % ticks == 0) {
                this.setHealth(this.getHealth() + 1.0F);
            }
        }

        if (this.isBurning()) {
            Vec3 look = this.getLookAngle();
            double dist = 0.7F;
            double targetX = this.getX() + look.x * dist;
            double targetY = this.getEyeY() + look.y;
            double targetZ = this.getZ() + look.z * dist;

            for (int i = 0; i < 3; i++) {
                double velX = look.x();
                double velY = look.y();
                double velZ = look.z();

                double spread = 5.0D + this.getRandom().nextDouble() * 1.5D;
                double velocity = 0.35D + this.getRandom().nextDouble() * 0.15D;
                velX += this.getRandom().nextGaussian() * 0.03D * spread;
                velY += this.getRandom().nextGaussian() * 0.03D * spread;
                velZ += this.getRandom().nextGaussian() * 0.03D * spread;
                velX *= velocity;
                velY *= velocity;
                velZ *= velocity;
                level().addParticle(ParticleTypes.FLAME, targetX, targetY, targetZ, velX, velY, velZ);
            }
        }

        //Count down to when Mookaite Construct is allowed to use a special attack.
        attackCooldown--;
    }

    @Override
    public void die(DamageSource source) {
        if (ForgeHooks.onLivingDeath(this, source)) return;

        if (this.level() instanceof ServerLevel level && this.getOpaliteCompanion() != null) {
            Entity entity = level.getEntity(this.getOpaliteCompanion());
            if (entity instanceof OpaliteContructEntity opalite) {
                opalite.setMookaiteCompanion(null);
            }
        }

        super.die(source);
    }

    static abstract class TimedGoal extends Goal {

        protected final MookaiteConstructEntity mookaite;
        protected final int color;

        public TimedGoal(MookaiteConstructEntity entity, int color) {
            this.mookaite = entity;
            this.color = color;
        }

        @Override
        public boolean canUse() {
            //Don't execute attacks if we are not bonded, not advanced enough.
            if (mookaite.getOpaliteCompanion() != null & mookaite.getBonder() != null) {
                if (mookaite.countColors(color) > 0 && mookaite.attackCooldown <= 0) {
                    return testAttack();
                }
            }
            return false;
        }

        protected abstract boolean testAttack();

        @Override
        public void stop() {
            mookaite.attackCooldown = 200;
        }
    }

    static class ScarletShockwaveGoal extends TimedGoal {
        private int stompTime;

        public ScarletShockwaveGoal(MookaiteConstructEntity entity) {
            super(entity, 1);
            setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
        }

        @Override
        protected boolean testAttack() {
            List<Entity> list = mookaite.level().getEntities(mookaite, mookaite.getBoundingBox().inflate(2.0F), (entity) -> {
                if (entity instanceof LivingEntity living && living == mookaite.getTarget()) {
                    return true;
                }
                if (entity instanceof Player player && player == mookaite.getTarget()) {
                    return EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity);
                }
                return false;
            });
            for (Entity entity : list) {
                if (entity.onGround() && entity.distanceToSqr(entity) > 1.0F && entity.distanceToSqr(entity) < 4.0F) {
                    if (entity.isAlive()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.stompTime <= 20;
        }

        @Override
        public void start() {
            this.stompTime = 0;
        }

        @Override
        public void tick() {
            if (stompTime >= 10) {
                float range = 2.0F + (float) mookaite.countColors(this.color);
                List<Entity> targets = mookaite.level().getEntities(mookaite, mookaite.getBoundingBox().inflate(range), (entity) ->
                        entity.getUUID() != mookaite.getOpaliteCompanion() && entity.getUUID() != mookaite.getBonder() && entity.onGround());
                mookaite.playSound(ModSounds.ENTITY_MOOKAITE_CONSTRUCT_STOMP.get(), 1.0F, 1.0F);

                for (Entity entity : targets) {
                    Vec3 targetV3D = entity.getDeltaMovement();

                    entity.hurt(mookaite.damageSources().mobAttack(mookaite), 3.0F);
                    entity.setDeltaMovement(targetV3D.x() * 0.25F, targetV3D.y() + 0.2F, targetV3D.z() * 0.25F);
                }

                if (!mookaite.level().isClientSide()) {
                    int size = 2 + mookaite.countColors(this.color);
                    for (int x = -size; x <= size; x++) {
                        for (int z = -size; z <= size; z++) {
                            BlockPos pos = mookaite.blockPosition().offset(x, 0, z);
                            BlockState state = mookaite.level().getBlockState(pos.below());
                            ((ServerLevel) mookaite.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX(), mookaite.getY() + (mookaite.random.nextDouble() * 0.25D), pos.getZ(), 5, 0.0F, 0.0F, 0.0F, 0.0D);
                        }
                    }
                }
            }

            stompTime++;
        }
    }

    static class AuburnFireGoal extends TimedGoal {
        private LivingEntity target;
        private int burnTime;

        public AuburnFireGoal(MookaiteConstructEntity entity) {
            super(entity, 2);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        protected boolean testAttack() {
            LivingEntity living = this.mookaite.getTarget();
            if (living != null) {
                if (!living.fireImmune() && this.mookaite.distanceTo(living) > 2.0F /*&& this.mookaite.distanceTo(living) > 4.0F*/ && this.mookaite.hasLineOfSight(living)) {
                    this.target = living;
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.target.isAlive() && this.mookaite.isAlive() && this.burnTime > 0;
        }

        @Override
        public void start() {
            this.burnTime = 20 * this.mookaite.countColors(this.color);
            this.mookaite.setBurning(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.mookaite.setBurning(false);
        }

        @Override
        public void tick() {
            this.mookaite.getLookControl().setLookAt(this.target);
            this.faceVec(this.target.getEyePosition(), 100.0F, 100.0F);

            if (burnTime > 5) {
                for (Entity entity : this.targetsInRange()) {
                    if (entity != null) {
                        this.target.hurt(this.mookaite.damageSources().inFire(), 1.0F);
                    }
                }

                //cast sound
                if (!this.mookaite.level().isClientSide()) {
                    if (burnTime % 5 == 0) {
                        this.mookaite.playSound(ModSounds.ENTITY_MOOKAITE_CONSTRUCT_BREATH.get(), 1.0F, 1.0F);
                    }
                }
            }

            this.burnTime--;
        }

        public void faceVec(Vec3 pos, float yawConstraint, float pitchConstraint) {
            double xPos = pos.x() - mookaite.getX();
            double zPos = pos.z() - mookaite.getZ();
            double yPos = (mookaite.getY() + 0.25) - pos.y();

            double distance = Mth.sqrt((float) (xPos * xPos + zPos * zPos));
            float xyAngle = (float) ((Math.atan2(zPos, xPos) * 180D) / Math.PI) - 90F;
            float zdAngle = (float) (-((Math.atan2(yPos, distance) * 180D) / Math.PI));
            mookaite.setXRot(-updateRotation(mookaite.getXRot(), zdAngle, pitchConstraint));
            mookaite.setYRot(updateRotation(mookaite.getYRot(), xyAngle, yawConstraint));

        }

        private float updateRotation(float current, float target, float maxDelta) {
            float delta = Mth.clamp(Mth.wrapDegrees(target - current), -maxDelta, maxDelta);

            return current + delta;
        }

        private List<Entity> targetsInRange() {
            double range = 30.0D;
            double offset = 5.0D;
            Vec3 srcVec = new Vec3(this.mookaite.getX(), this.mookaite.getY() + 0.25, this.mookaite.getZ());
            Vec3 lookVec = this.mookaite.getViewVector(1.0F);
            Vec3 destVec = srcVec.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);
            float area = 3.0F;
            double hitDist = 0;
            List<Entity> affectedList = Lists.newArrayList();
            List<Entity> possibleList = this.mookaite.level().getEntities(this.mookaite, this.mookaite.getBoundingBox().move(lookVec.x * offset, lookVec.y * offset, lookVec.z * offset).inflate(area), (entity) ->
                    entity.isPickable() &&
                    entity.getUUID() != this.mookaite.getOpaliteCompanion() &&
                    entity.getUUID() != this.mookaite.getBonder() &&
                    EntitySelector.NO_CREATIVE_OR_SPECTATOR.and(EntitySelector.LIVING_ENTITY_STILL_ALIVE).test(entity));

            for (Entity possibleEntity : possibleList) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        affectedList.add(possibleEntity);
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        affectedList.add(possibleEntity);
                        hitDist = possibleDist;
                    }
                }
            }
            return affectedList;
        }
    }

    static class GoldMagicGoal extends TimedGoal {
        private LivingEntity target;
        private boolean fired;

        public GoldMagicGoal(MookaiteConstructEntity entity) {
            super(entity, 3);
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        @Override
        protected boolean testAttack() {
            LivingEntity livingentity = this.mookaite.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                boolean flag = this.mookaite.getSensing().hasLineOfSight(livingentity);
                double dist = this.mookaite.distanceToSqr(livingentity);

                if (dist < 12.0F * 12.0F && flag && !livingentity.hasEffect(MobEffects.POISON)) {
                    this.target = livingentity;
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return !this.fired;
        }

        @Override
        public void start() {
            this.fired = false;
        }

        @Override
        public void tick() {
            this.mookaite.getLookControl().setLookAt(this.target, 10.0F, 10.0F);
            double tx = target.getX() - this.mookaite.getX();
            double ty = target.getEyeY() - (double)1.1F;
            double tz = target.getZ() - this.mookaite.getZ();

            MookaiteMagicEntity bullet = new MookaiteMagicEntity(this.mookaite.level(), this.mookaite);
            bullet.shoot(tx, ty - bullet.getY(), tz, 1.6F, 12.0F);
            mookaite.playSound(ModSounds.ENTITY_MOOKAITE_CONSTRUCT_CAST.get(), 1.0F, 1.0F / (mookaite.getRandom().nextFloat() * 0.4F + 0.8F));
            this.mookaite.level().addFreshEntity(bullet);

            this.fired = true;
        }
    }

    static class MauveElectricGoal extends TimedGoal {
        private int duration;

        public MauveElectricGoal(MookaiteConstructEntity entity) {
            super(entity, 4);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        @Override
        protected boolean testAttack() {
            return this.mookaite.getTarget() != null && this.mookaite.getRandom().nextInt(10) == 0;
        }

        @Override
        public boolean canContinueToUse() {
            return this.duration > 0;
        }

        @Override
        public void start() {
            this.mookaite.getNavigation().stop();
            this.duration = 20 * (5 + this.mookaite.countColors(color));
        }

        @Override
        public void tick() {
            List<Entity> targets = mookaite.level().getEntities(mookaite, mookaite.getBoundingBox().inflate(2.0F), (entity) ->
                    entity.getUUID() != mookaite.getOpaliteCompanion() && entity.getUUID() != mookaite.getBonder());

            for (Entity entity : targets) {
                Vec3 targetV3D = entity.getDeltaMovement();

                entity.hurt(mookaite.damageSources().mobAttack(mookaite), 2.0F);
                entity.setDeltaMovement(targetV3D.x() * 0.35F, targetV3D.y() + 0.15F, targetV3D.z() * 0.35F);
            }

            if (!mookaite.level().isClientSide()) {
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        ((ServerLevel) mookaite.level()).sendParticles(ParticleTypes.ELECTRIC_SPARK, mookaite.getRandomX(2.0D), this.mookaite.getRandomY(), this.mookaite.getRandomZ(2.0D), 10, 0.0F, 0.0F, 0.0F, 0.0D);
                    }
                }
            }

            duration--;
        }
    }

    static class IvoryProjectileGoal extends TimedGoal {
        private LivingEntity target;
        private int attackTime;
        private int attackStep;
        private int maxSteps;

        public IvoryProjectileGoal(MookaiteConstructEntity entity) {
            super(entity, 6);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        protected boolean testAttack() {
            LivingEntity livingentity = this.mookaite.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                boolean flag = this.mookaite.getSensing().hasLineOfSight(livingentity);
                double dist = this.mookaite.distanceToSqr(livingentity);

                if (dist < 32.0F * 32.0F && flag) {
                    this.target = livingentity;
                    return true;
                }
            }
            return false;
        }

        public boolean canContinueToUse() {
            return this.target.isAlive() && this.attackStep < this.maxSteps;
        }

        @Override
        public void start() {
            this.mookaite.getNavigation().stop();
            this.maxSteps = this.mookaite.countColors(6) + 1;
            this.attackStep = 0;
            this.attackTime = 0;
        }

        @Override
        public void tick() {
            this.mookaite.getLookControl().setLookAt(this.target, 10.0F, 10.0F);
            double tx = target.getX() - this.mookaite.getX();
            double ty = target.getEyeY();
            double tz = target.getZ() - this.mookaite.getZ();

            --this.attackTime;

            if (attackTime <= 0) {
                MookaiteAmmoEntity bullet = new MookaiteAmmoEntity(this.mookaite.level(), this.mookaite);
                bullet.shoot(tx, ty - bullet.getY(), tz, 1.6F, 12.0F);
                mookaite.playSound(ModSounds.ENTITY_MOOKAITE_CONSTRUCT_SHOOT.get(), 1.0F, 1.0F / (mookaite.getRandom().nextFloat() * 0.4F + 0.8F));
                this.mookaite.level().addFreshEntity(bullet);

                attackStep++;
                attackTime = 5;
            }

            super.tick();
        }
    }
}
