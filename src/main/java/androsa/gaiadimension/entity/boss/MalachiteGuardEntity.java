package androsa.gaiadimension.entity.boss;

import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MalachiteGuardEntity extends MonsterEntity {

    private static final DataParameter<Integer> PHASE = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> IS_CHARGING = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_CHARGED = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.BOOLEAN);
    private int dronesLeft;
    private boolean hasSpawnedDrones;
    private int cooldownTimer;
    private float bideDamage;

    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS);

    public MalachiteGuardEntity(EntityType<? extends MalachiteGuardEntity> entity, World world) {
        super(entity, world);
        this.maxUpStep = 1.5F;
        this.xpReward = 75;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D);
    }

    /**
     * Phase 0: Defence
     * Phase 1: Attack
     * Phase 2: Resist
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PHASE, 0);
        this.entityData.define(IS_CHARGING, false);
        this.entityData.define(IS_CHARGED, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DefendGoal());
        //Spin attack. In a short area, the Guard will spin in place, dealing more knockback and melee damage
        //Stomping shockwave. If the target is in a specific range and is on the same level, create a shockwave attack, kicking the targets up
        //Malachite Blast. In a bigger area and the target is above or below a certain height, create a blast attack dealing magic damage. When charging, damage is accumulated
        this.goalSelector.addGoal(1, new BlastAttackGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.6D, 32.0F));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setPhase(nbt.getInt("Phase"));
        this.setCharging(nbt.getBoolean("IsCharging"));
        this.setCharged(nbt.getBoolean("IsCharged"));
        this.dronesLeft = nbt.getInt("DronesLeft");
        this.hasSpawnedDrones = nbt.getBoolean("IsSpawned");
        this.cooldownTimer = nbt.getInt("CooldownTimer");
        this.bideDamage = nbt.getFloat("BideDamage");
        if (hasCustomName()) {
            this.bossInfo.setName(getDisplayName());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Phase", getPhase());
        nbt.putBoolean("IsCharging", isCharging());
        nbt.putBoolean("IsCharged", isCharged());
        nbt.putInt("DronesLeft", dronesLeft);
        nbt.putBoolean("IsSpawned", hasSpawnedDrones);
        nbt.putInt("CooldownTimer", cooldownTimer);
        nbt.putFloat("BideDamage", bideDamage);
    }

    @Override
    public void setCustomName(@Nullable ITextComponent text) {
        super.setCustomName(text);
        this.bossInfo.setName(getDisplayName());
    }

    /** Our phase. In order: Defending, Attacking, Resisting. Anything higher restarts the fight and sets to 0 */
    public int getPhase() {
        return this.entityData.get(PHASE);
    }

    private void setPhase(int id) {
        if (id > 2 || id < 0) { // Fine, I'll just make you redo this fight
            id = 0;
            this.hasSpawnedDrones = false;
        }
        this.entityData.set(PHASE, id);
    }

    /** Is the Malachite Guard in a Charging state? Reduce and bide damage if so */
    public boolean isCharging() {
        return this.entityData.get(IS_CHARGING);
    }

    public void setCharging(boolean flag) {
        this.entityData.set(IS_CHARGING, flag);
    }

    /** Is the Malachite Guard releasing power? If so, it is charged */
    public boolean isCharged() {
        return this.entityData.get(IS_CHARGED);
    }

    public void setCharged(boolean flag) {
        this.entityData.set(IS_CHARGED, flag);
    }

    /**
     * Do not move if we are in Phase 1.
     */
    @Override
    public void move(MoverType type, Vector3d motion) {
        if (getPhase() != 0 && !isCharging() && !isCharged()) {
            super.move(type, motion);
        }
    }

    /**
     * Do not apply knockback in Phase 1 as we are unmoving
     * Do not apply knockback in Phase 3 as we are enraged
     * Do not apply knockback if we are Charged or Charging
     */
    @Override
    public void knockback(float amount, double x, double z) {
        if (getPhase() == 1 || isCharging() || isCharged()) {
            super.knockback(amount, x, z);
        }
    }

    /**
     * Guard is too heavy to be dealt fall damage
     */
    @Override
    public boolean causeFallDamage(float dist, float mul) {
        return false;
    }

    /**
     * Ignore anything that's not a player if we are in Normal or Hard
     */
    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        if (level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) {
            if (entity instanceof PlayerEntity) {
                if (EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(entity)) {
                    super.setTarget(entity);
                }
            }
        } else if (level.getDifficulty() == Difficulty.EASY) {
            if (entity instanceof PlayerEntity) {
                if (EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(entity)) {
                    super.setTarget(entity);
                }
            } else {
                super.setTarget(entity);
            }
        }
    }

    //TODO: Make hurt sound
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLAZE_HURT;
    }

    //TODO: Made damage sound
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    //TODO: Keep if we make new sounds?
    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void tick() {
        super.tick();

        if (isCharging()) {
            if (level.isClientSide()) {
                for (int i = 0; i < 5; i++) {
                    level.addParticle(ParticleTypes.FLAME, getRandomX(3.0D), this.getY() + (random.nextDouble() * 0.25D), getRandomZ(3.0D), 0.0D, 0.0D, 0.0D);
                }
            }
        }

        if (!level.isClientSide()) {
            this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        }
    }

    /**
     * Spawns our drones. Easy: 3. Normal: 4. Hard: 5.
     */
    private void spawnDrones() {
        BlockPos guardPos = this.blockPosition();
        int gX = guardPos.getX();
        int gy = guardPos.getY();
        int gZ = guardPos.getZ();
        Difficulty difficulty = this.level.getDifficulty();

        //Easy Modes
        BlockPos bpLeft = new BlockPos(gX - 2, gy, gZ);
        BlockPos bpRight = new BlockPos(gX + 2, gy, gZ);
        //Normal and Hard modes
        BlockPos bpTopLeft = new BlockPos(gX - 2, gy, gZ + 1);
        BlockPos bpLowLeft = new BlockPos(gX - 2, gy, gZ - 1);
        BlockPos bpTopRight = new BlockPos(gX + 2, gy, gZ + 1);
        BlockPos bpLowRight = new BlockPos(gX + 2, gy, gZ - 1);
        //Middle Drone for Easy and Hard
        BlockPos bpMid = new BlockPos(gX, gy, gZ + 1);

        //Spawn Drones per difficulty
        if (difficulty == Difficulty.EASY) {
            createDrone(bpLeft);
            createDrone(bpMid);
            createDrone(bpRight);
        } if (difficulty == Difficulty.NORMAL) {
            createDrone(bpTopLeft);
            createDrone(bpLowLeft);
            createDrone(bpTopRight);
            createDrone(bpLowRight);
        } if (difficulty == Difficulty.HARD) {
            createDrone(bpTopLeft);
            createDrone(bpLowLeft);
            createDrone(bpTopRight);
            createDrone(bpLowRight);
            createDrone(bpMid);
        }
    }

    private void createDrone(BlockPos pos) {
        MalachiteDroneEntity drone = new MalachiteDroneEntity(ModEntities.MALACHITE_DRONE, this.level);
        drone.moveTo(pos, 0.0F, 0.0F);
        if (!level.isClientSide()) {
            drone.finalizeSpawn((IServerWorld)this.level, this.level.getCurrentDifficultyAt(pos), SpawnReason.MOB_SUMMONED, null, null);
        }
        drone.setOwner(this);
        this.level.addFreshEntity(drone);
        this.dronesLeft++;
    }

    /**
     * Pointless as it is, this is to safely count down the number of drones left
     */
    public void onDroneKilled() {
        this.dronesLeft--;
    }

    @Override
    public void aiStep() {
        if (getPhase() == 0) {
            //Don't move, except falling
            Vector3d motion = this.getDeltaMovement();
            this.setDeltaMovement(0.0D, motion.y(), 0.0D);

            //Check if we spawned drones in this phase
            if (!hasSpawnedDrones) {
                this.spawnDrones();
                this.hasSpawnedDrones = true;
            }

            if (dronesLeft <= 0 && hasSpawnedDrones) {
                //No more drones, time for the next phase
                this.setPhase(1);
            }
        }

        if (getPhase() == 1 && getHealth() < getMaxHealth() / 2) {
            //Sufficiently weak enough. Change phase
            this.setPhase(2);
        }

        //Half speed at Phase 3. Phase 1 doesn't move, anyway
        float movespeed = (float) this.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
        if (getPhase() == 2) {
            //Move at half the speed in this phase
            movespeed *= 0.35F;
        } else if (getPhase() == 0) {
            //No moving in this phase
            movespeed = 0.0F;
        }
        this.setSpeed(movespeed);

        //Cooldown for blast attack
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }

        super.aiStep();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected boolean isAffectedByFluids() {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean flag = super.doHurtTarget(target);
        Difficulty difficulty = level.getDifficulty();

        //Just have this happen in Normal or Hard
        if (difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD) {
            if (target instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) target;
                NonNullList<ItemStack> armor = player.inventory.armor;
                int slot = random.nextInt(armor.size());
                ItemStack stack = armor.get(slot);
                EquipmentSlotType slotType = EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, slot);

                //Normal: 1:16 chance. Hard: 1:8 chance. Chances decrease if the slot is empty
                if ((difficulty == Difficulty.NORMAL && random.nextInt(16) == 0) || (difficulty == Difficulty.HARD && random.nextInt(8) == 0)) {
                    //Remove your piece of armor
                    player.drop(stack, true, false);
                    player.setItemSlot(slotType, ItemStack.EMPTY);
                }
            }
        }

        return flag;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isCharging()) {
            if (isAllowedToDamage(source)) {
                if (level.getDifficulty() == Difficulty.EASY) {
                    amount /= 4;
                    bideDamage += amount;
                    amount *= 3;
                } else if (level.getDifficulty() == Difficulty.NORMAL) {
                    amount /= 2;
                    bideDamage += amount;
                } else if (level.getDifficulty() == Difficulty.HARD) {
                    amount /= 4;
                    bideDamage += amount * 3;
                }
            }
        }

        if (getPhase() == 0) {
            //Don't take any damage until we are sufficiently out of world. We're in Defence mode
            return this.blockPosition().getY() < -64 && super.hurt(source, amount);

        } else if (getPhase() == 1) {
            //Take damage as normal. However, we stop at the threshold (minus a little) to change phase
            float threshold = (getMaxHealth() / 2.0F) - 2.0F;
            float remaining = getHealth() - threshold;
            if (amount > remaining) {
                amount = remaining;
            }
            return super.hurt(source, amount);

        } else if (getPhase() == 2) { //Start Resist phase
            //Take damage from appropriate sources
            if (isAllowedToDamage(source)) {
                //Calculate a modifier
                float multiply = getMultiplier(amount);

                return super.hurt(source, amount * multiply);
            } else {
                //Not unless you're falling out of the world
                return this.blockPosition().getY() < -64 && super.hurt(source, amount);
            }
        }

        //We aren't any of these phases somehow. Just behave as normal
        return super.hurt(source, amount);
    }

    /**
     * Easy mode allows all direct damage from living entities. Normal and Hard requires the player
     */
    private boolean isAllowedToDamage(DamageSource source) {
        Entity entity = source.getDirectEntity();

        if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
            return entity instanceof PlayerEntity;
        } else {
            return entity instanceof LivingEntity;
        }
    }

    /**
     * Easy mode has softer weakeners. Normal is more strict. Hard has less attacking range.
     */
    private float getMultiplier(float base) {
        switch (this.level.getDifficulty()) {
            case EASY:
                return base > 50.0F ? 0.25F : base > 25.0F ? 0.5F : base > 10.0F ? 0.75F : 1.0F;
            case NORMAL:
                return base > 100.0F ? 0.0F : base > 50.0F ? 0.125F : base > 25.0F ? 0.25F : base > 10.0F ? 0.5F : 1.0F;
            case HARD:
                return base > 75.0F ? 0.0F : base > 50.0F ? 0.125F : base > 25.0F ? 0.25F : base > 10.0F ? 0.5F : 1.0F;
            case PEACEFUL:
            default:
                //We aren't here in Peaceful, but fall onto this
                return base;
        }
    }

    @Override
    public boolean canBeAffected(EffectInstance effectInstance) {
        return level.getDifficulty() == Difficulty.HARD && effectInstance.getEffect().isBeneficial();
    }

    @Override
    public void kill() {
        this.setHealth(0.0F);
        super.kill();
    }

    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }

    @Override
    public void checkDespawn() {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.spawnAtLocation(ModItems.mock_malachite.get(), 1);
            this.remove();
        }
        super.checkDespawn();
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 3.0F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    class DefendGoal extends Goal {

        public DefendGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return MalachiteGuardEntity.this.getPhase() == 0;
        }
    }

    static class BlastAttackGoal extends Goal {

        private final MalachiteGuardEntity guard;
        private int attackPhase;
        private int chargeTimer;
        private int explodeTime;

        public BlastAttackGoal(MalachiteGuardEntity entity) {
            this.guard = entity;

            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        /**
         * Run if we are in phase 2 or 3, just anything that isn't the Defend phase
         * Run if there are players above or below the Guard
         * Run if they are alive
         */
        @Override
        public boolean canUse() {
            if (guard.getPhase() != 0 && guard.cooldownTimer <= 0) {
                List<Entity> list = guard.level.getEntities(guard, guard.getBoundingBox().inflate(3.0F), (entity) -> {
                    EntityType<?> type = entity.getType();
                    if (type == EntityType.PLAYER) {
                        return EntityPredicates.NO_CREATIVE_OR_SPECTATOR.test(entity);
                    }
                    return false;
                });
                for (Entity entity : list) {
                    double targetY = entity.blockPosition().getY();
                    double guardY = guard.blockPosition().getY();
                    if (targetY < guardY - 0.5D || targetY > guardY + 1.0D) {
                        return entity.isAlive();
                    }
                }
            }
            return false;
        }

        /** Stop if the timer is no longer set to 0 */
        @Override
        public boolean canContinueToUse() {
            return guard.cooldownTimer <= 0;
        }

        @Override
        public void start() {
            guard.getNavigation().stop();
            guard.setCharging(true);
            attackPhase = 0;
            chargeTimer = 100;
            explodeTime = 0;
        }

        @Override
        public void stop() {
            guard.setCharged(false);
            guard.bideDamage = 0.0F;
        }

        @Override
        public void tick() {
            /*
             * 1. Start the charging. Set the state to charging and start the timer until explosion.
             * 2. Timer reached 0? Time to explode. If not, we need to charge more.
             * 3. Explode. Set out of charging into charged. Affect all entities in an area that isn't us or Malachite Drones. Start timer for charged state.
             * 4. Charged state reached 0? Start cooldown, tracked in the entity itself. If not, we are still charged and exploding.
             */
            chargeTimer--;
            if (chargeTimer <= 0) {
                if (attackPhase == 0) {
                    guard.setCharging(false);
                    guard.setCharged(true);
                    attackPhase++;
                }

                if (explodeTime == 0) {
                    List<Entity> targets = guard.level.getEntities(guard, guard.getBoundingBox().inflate(4.0F), (entity) -> {
                        EntityType<?> type = entity.getType();
                        return type != ModEntities.MALACHITE_GUARD && type != ModEntities.MALACHITE_DRONE;
                    });
                    guard.playSound(SoundEvents.WITHER_SPAWN, 1.0F, 1.0F);

                    for (Entity entity : targets) {
                        Vector3d explosion = new Vector3d(guard.getX(), guard.getY(), guard.getZ());
                        Vector3d direction = entity.position().subtract(explosion).normalize();

                        entity.hurt(DamageSource.MAGIC, 8.0F + guard.bideDamage);
                        entity.setDeltaMovement(direction.x(), direction.y() + 0.2F, direction.z());
                    }
                }

                if (explodeTime < 10) {
                    if (!guard.level.isClientSide()) {
                        ((ServerWorld)guard.level).sendParticles(ParticleTypes.FLAME, guard.getRandomX(1.0D), guard.getRandomY(), guard.getRandomZ(1.0D), 10, (guard.random.nextDouble()) - 0.5D, guard.random.nextDouble() * 0.5D, (guard.random.nextDouble()) - 0.5D, 0.5D);
                    }
                }

                explodeTime++;
                if (explodeTime >= 20) {
                    guard.cooldownTimer = 60; //This will stop the task from running as it is no longer 0;
                }
            }
        }
    }
}
