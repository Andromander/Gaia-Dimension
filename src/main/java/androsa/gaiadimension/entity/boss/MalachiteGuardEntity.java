package androsa.gaiadimension.entity.boss;

import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModParticles;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class MalachiteGuardEntity extends Monster {

    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(MalachiteGuardEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STOMP_PHASE = SynchedEntityData.defineId(MalachiteGuardEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CHARGE_PHASE = SynchedEntityData.defineId(MalachiteGuardEntity.class, EntityDataSerializers.INT);
    private int dronesLeft;
    private boolean hasSpawnedDrones;
    private int stompCooldown;
    private int chargeCooldown;
    private float bideDamage;

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);

    public MalachiteGuardEntity(EntityType<? extends MalachiteGuardEntity> entity, Level world) {
        super(entity, world);
        this.xpReward = 75;
        this.setMaxUpStep(1.5F);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D);
    }

    /**
     * Phase 0: Defence | Idle     | Idle
     * Phase 1: Attack  | Charging | Stomping
     * Phase 2: Resist  | Charged  | Stomped
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PHASE, 0);
        this.entityData.define(STOMP_PHASE, 0);
        this.entityData.define(CHARGE_PHASE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DefendGoal());
        this.goalSelector.addGoal(1, new StompAttackGoal(this));
        this.goalSelector.addGoal(2, new BlastAttackGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(4, new MoveTowardsTargetGoal(this, 0.6D, 32.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setPhase(nbt.getInt("Phase"));
        this.setStompPhase(nbt.getInt("StompPhase"));
        this.setChargePhase(nbt.getInt("ChargePhase"));
        this.dronesLeft = nbt.getInt("DronesLeft");
        this.hasSpawnedDrones = nbt.getBoolean("IsSpawned");
        this.stompCooldown = nbt.getInt("StompCooldown");
        this.chargeCooldown = nbt.getInt("ChargeCooldown");
        this.bideDamage = nbt.getFloat("BideDamage");
        if (hasCustomName()) {
            this.bossInfo.setName(getDisplayName());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Phase", getPhase());
        nbt.putInt("StompPhase", getStompPhase());
        nbt.putInt("ChargePhase", getChargePhase());
        nbt.putInt("DronesLeft", dronesLeft);
        nbt.putBoolean("IsSpawned", hasSpawnedDrones);
        nbt.putInt("StompCooldown", stompCooldown);
        nbt.putInt("ChargeCooldown", chargeCooldown);
        nbt.putFloat("BideDamage", bideDamage);
    }

    @Override
    public void setCustomName(@Nullable Component text) {
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

    /** Our stomping phase. In order: Inactive, Raising, Stomped. Anything higher assumes we are no longer stomping */
    public int getStompPhase() {
        return this.entityData.get(STOMP_PHASE);
    }

    public void setStompPhase(int id) {
        if (id > 2 || id < 0) { // Guess that means it's supposed to be reset
            id = 0;
        }
        this.entityData.set(STOMP_PHASE, id);
    }

    /** Our charge phase. In order: Inactive, Charging, Charged. Anything higher assumes we are no longer charging or charged */
    public int getChargePhase() {
        return this.entityData.get(CHARGE_PHASE);
    }

    private void setChargePhase(int id) {
        if (id > 2 || id < 0) { // Guess that means it's supposed to be reset
            id = 0;
        }
        this.entityData.set(CHARGE_PHASE, id);
    }

    /**
     * Do not move if we are in Phase 1.
     */
    @Override
    public void move(MoverType type, Vec3 motion) {
        if (getPhase() != 0 && (getChargePhase() == 0 || getChargePhase() == 0)) {
            super.move(type, motion);
        }
    }

    /**
     * Do not apply knockback in Phase 1 as we are unmoving
     * Do not apply knockback in Phase 3 as we are enraged
     * Do not apply knockback if we are Charged or Charging
     */
    @Override
    public void knockback(double amount, double x, double z) {
        if (getPhase() == 1 || getChargePhase() != 0 || getStompPhase() != 0) {
            super.knockback(amount, x, z);
        }
    }

    /**
     * Guard is too heavy to be dealt fall damage
     */
    @Override
    public boolean causeFallDamage(float dist, float mul, DamageSource source) {
        return false;
    }

    /**
     * Ignore anything that's not a player if we are in Normal or Hard
     */
    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        if (level().getDifficulty() == Difficulty.NORMAL || level().getDifficulty() == Difficulty.HARD) {
            if (entity instanceof Player) {
                if (EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity)) {
                    super.setTarget(entity);
                }
            }
        } else if (level().getDifficulty() == Difficulty.EASY) {
            if (entity instanceof Player) {
                if (EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity)) {
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
        return ModSounds.ENTITY_MALACHITE_GUARD_HURT.get();
    }

    //TODO: Made damage sound
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MALACHITE_GUARD_DEATH.get();
    }

    //TODO: Keep if we make new sounds?
    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public void tick() {
        super.tick();

        if (getChargePhase() == 1) {
            if (level().isClientSide()) {
                for (int i = 0; i < 3; i++) {
                    level().addParticle(ModParticles.MALACHITE_MAGIC.get(), getRandomX(3.0D), this.getY() + (random.nextDouble() * 0.25D), getRandomZ(3.0D), 0.0D, 0.0D, 0.0D);
                }
            }
        }

        if (!level().isClientSide()) {
            this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
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
        Difficulty difficulty = this.level().getDifficulty();

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
        MalachiteDroneEntity drone = new MalachiteDroneEntity(ModEntities.MALACHITE_DRONE.get(), this.level());
        drone.moveTo(pos, 0.0F, 0.0F);
        if (!level().isClientSide()) {
            ForgeEventFactory.onFinalizeSpawn(drone, (ServerLevelAccessor)this.level(), this.level().getCurrentDifficultyAt(pos), MobSpawnType.MOB_SUMMONED, null, null);
        }
        drone.setOwner(this);
        this.level().addFreshEntity(drone);
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
            Vec3 motion = this.getDeltaMovement();
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

        //Cooldown for stomp attack
        if (stompCooldown > 0) {
            stompCooldown--;
        }

        //Cooldown for blast attack
        if (chargeCooldown > 0) {
            chargeCooldown--;
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
        Difficulty difficulty = level().getDifficulty();

        //Just have this happen in Normal or Hard
        if (difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD) {
            if (target instanceof Player) {
                Player player = (Player) target;
                NonNullList<ItemStack> armor = player.getInventory().armor;
                int slot = random.nextInt(armor.size());
                ItemStack stack = armor.get(slot);
                EquipmentSlot slotType = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, slot);

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
        if (getChargePhase() == 1) {
            if (isAllowedToDamage(source)) {
                if (level().getDifficulty() == Difficulty.EASY) {
                    amount /= 4;
                    bideDamage += amount;
                    amount *= 3;
                } else if (level().getDifficulty() == Difficulty.NORMAL) {
                    amount /= 2;
                    bideDamage += amount;
                } else if (level().getDifficulty() == Difficulty.HARD) {
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

        if (this.level().getDifficulty() == Difficulty.NORMAL || this.level().getDifficulty() == Difficulty.HARD) {
            return entity instanceof Player;
        } else {
            return entity instanceof LivingEntity;
        }
    }

    /**
     * Easy mode has softer weakeners. Normal is more strict. Hard has less attacking range.
     * Default handler only falls onto Peaceful, but we already despawned by then.
     */
    private float getMultiplier(float base) {
        return switch (this.level().getDifficulty()) {
            case EASY -> base > 50.0F ? 0.25F : base > 25.0F ? 0.5F : base > 10.0F ? 0.75F : 1.0F;
            case NORMAL -> base > 100.0F ? 0.0F : base > 50.0F ? 0.125F : base > 25.0F ? 0.25F : base > 10.0F ? 0.5F : 1.0F;
            case HARD -> base > 75.0F ? 0.0F : base > 50.0F ? 0.125F : base > 25.0F ? 0.25F : base > 10.0F ? 0.5F : 1.0F;
            default -> base;
        };
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return level().getDifficulty() == Difficulty.HARD && effectInstance.getEffect().isBeneficial();
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
        if (this.level().getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            this.spawnAtLocation(ModItems.mock_malachite.get(), 1);
            this.discard();
        }
        super.checkDespawn();
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 3.0F;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
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
            if (guard.getPhase() != 0 && guard.getStompPhase() == 0 && guard.chargeCooldown <= 0) {
                List<Entity> list = guard.level().getEntities(guard, guard.getBoundingBox().inflate(3.0F), (entity) -> {
                    EntityType<?> type = entity.getType();
                    if (type == EntityType.PLAYER) {
                        return EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity);
                    }
                    return false;
                });
                for (Entity entity : list) {
                    double targetY = entity.blockPosition().getY();
                    double guardY = guard.blockPosition().getY();
                    if (targetY < guardY - 1.0D || targetY > guardY + 1.0D) {
                        return entity.isAlive() && guard.onGround();
                    }
                }
            }
            return false;
        }

        /** Stop if the timer is no longer set to 0 */
        @Override
        public boolean canContinueToUse() {
            return guard.chargeCooldown <= 0;
        }

        @Override
        public void start() {
            guard.getNavigation().stop();
            guard.setChargePhase(1);
            attackPhase = 0;
            chargeTimer = 100;
            explodeTime = 0;
        }

        @Override
        public void stop() {
            guard.setChargePhase(0);
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
                    guard.setChargePhase(2);
                    attackPhase++;
                }

                if (explodeTime == 0) {
                    List<Entity> targets = guard.level().getEntities(guard, guard.getBoundingBox().inflate(4.0F), (entity) -> {
                        EntityType<?> type = entity.getType();
                        return type != ModEntities.MALACHITE_GUARD.get() && type != ModEntities.MALACHITE_DRONE.get();
                    });
                    guard.playSound(ModSounds.ENTITY_MALACHITE_GUARD_BLAST.get(), 1.0F, 1.0F);

                    for (Entity entity : targets) {
                        Vec3 explosion = new Vec3(guard.getX(), guard.getY(), guard.getZ());
                        Vec3 direction = entity.position().subtract(explosion).normalize();

                        entity.hurt(GaiaDamage.getDamage(guard.level(), GaiaDamage.MALACHITE_BLAST), 8.0F + guard.bideDamage);
                        entity.setDeltaMovement(direction.x(), direction.y() + 0.2F, direction.z());
                    }
                }

                if (explodeTime < 10) {
                    if (!guard.level().isClientSide()) {
                        for (int i = 0; i < 5; i++) {
                            ((ServerLevel)guard.level()).sendParticles(ModParticles.MALACHITE_MAGIC.get(), guard.getRandomX(1.0D), guard.getRandomY(), guard.getRandomZ(1.0D), 5, (guard.random.nextDouble()) - 0.5D, guard.random.nextDouble() * 0.5D, (guard.random.nextDouble()) - 0.5D, 0.5D);
                        }
                    }
                }

                explodeTime++;
                if (explodeTime >= 20) {
                    guard.chargeCooldown = 60; //This will stop the task from running as it is no longer 0;
                }
            }
        }
    }

    static class StompAttackGoal extends Goal {

        private final MalachiteGuardEntity guard;
        private int stompTime;

        public StompAttackGoal(MalachiteGuardEntity entity) {
            this.guard = entity;

            setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (guard.getPhase() != 0 && guard.getChargePhase() == 0 && guard.stompCooldown <= 0) {
                List<Entity> list = guard.level().getEntities(guard, guard.getBoundingBox().inflate(2.0F), (entity) -> {
                    EntityType<?> type = entity.getType();
                    if (type == EntityType.PLAYER) {
                        return EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity);
                    }
                    return false;
                });
                for (Entity entity : list) {
                    if (entity.onGround() && entity.distanceToSqr(guard) > 1.0F && entity.distanceToSqr(guard) < 4.0F) {
                        return entity.isAlive();
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return guard.stompCooldown <= 0;
        }

        @Override
        public void start() {
            guard.setStompPhase(1);
            this.stompTime = 0;
        }

        @Override
        public void stop() {
            guard.setStompPhase(0);
        }

        @Override
        public void tick() {
            stompTime++;

            if (stompTime == 20) {
                guard.setStompPhase(2);

                List<Entity> targets = guard.level().getEntities(guard, guard.getBoundingBox().inflate(3.0F, -2.0F, 3.0F), (entity) -> {
                    EntityType<?> type = entity.getType();
                    return type != ModEntities.MALACHITE_GUARD.get() && type != ModEntities.MALACHITE_DRONE.get();
                });
                guard.playSound(ModSounds.ENTITY_MALACHITE_GUARD_STOMP.get(), 1.0F, 1.0F);

                for (Entity entity : targets) {
                    Vec3 targetV3D = entity.getDeltaMovement();

                    entity.hurt(guard.damageSources().mobAttack(guard), 5.0F);
                    entity.setDeltaMovement(targetV3D.x() * 0.5F, targetV3D.y() + 0.4F, targetV3D.z() * 0.5F);
                }

                if (!guard.level().isClientSide()) {
                    for (int x = -3; x <= 3; x++) {
                        for (int z = -3; z <= 3; z++) {
                            BlockPos pos = guard.blockPosition().offset(x, 0, z);
                            BlockState state = guard.level().getBlockState(pos.below());
                            ((ServerLevel)guard.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX(), guard.getY() + (guard.random.nextDouble() * 0.25D), pos.getZ(), 5, 0.0F, 0.0F, 0.0F, 0.0D);
                        }
                    }
                }
            }

            if (stompTime >= 30) {
                guard.stompCooldown = 120; //This will stop the task from running as it is no longer 0;
            }
        }
    }
}
