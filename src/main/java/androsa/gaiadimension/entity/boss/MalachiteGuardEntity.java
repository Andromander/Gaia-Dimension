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
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import javax.annotation.Nullable;

public class MalachiteGuardEntity extends MonsterEntity {

    private static final DataParameter<Integer> PHASE = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> DRONES_LEFT = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> IS_SPAWNED = EntityDataManager.defineId(MalachiteGuardEntity.class, DataSerializers.BOOLEAN);

    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS);

    public MalachiteGuardEntity(EntityType<? extends MalachiteGuardEntity> entity, World world) {
        super(entity, world);
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
        this.entityData.define(DRONES_LEFT, 0);
        this.entityData.define(IS_SPAWNED, false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MalachiteGuardEntity.AttackGoal());
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.6D, 32.0F));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        this.setPhase(nbt.getInt("Phase"));
        this.setDronesLeft(nbt.getInt("DronesLeft"));
        this.setSpawnedDrones(nbt.getBoolean("IsSpawned"));
        if (hasCustomName()) {
            this.bossInfo.setName(getDisplayName());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Phase", getPhase());
        nbt.putInt("DronesLeft", getDronesLeft());
        nbt.putBoolean("IsSpawned", hasSpawnedDrones());
    }

    @Override
    public void setCustomName(@Nullable ITextComponent text) {
        super.setCustomName(text);
        this.bossInfo.setName(getDisplayName());
    }

    public int getPhase() {
        return this.entityData.get(PHASE);
    }

    /**
     * Nobody should be touching this, like, at all.
     */
    private void setPhase(int id) {
        if (id > 2 || id < 0) { //Fine, I'll just make you redo this fight
            id = 0;
            setSpawnedDrones(false);
        }
        this.entityData.set(PHASE, id);
    }

    public int getDronesLeft() {
        return this.entityData.get(DRONES_LEFT);
    }

    /**
     * Nobody touch this. Very bad
     */
    private void setDronesLeft(int drones) {
        this.entityData.set(DRONES_LEFT, drones);
    }

    public boolean hasSpawnedDrones() {
        return this.entityData.get(IS_SPAWNED);
    }

    /**
     * Shouldn't let anyone set this outside of the Malachite Guard
     */
    private void setSpawnedDrones(boolean spawned) {
        this.entityData.set(IS_SPAWNED, spawned);
    }

    /**
     * Do not move if we are in Phase 1.
     */
    @Override
    public void move(MoverType type, Vector3d motion) {
        if (getPhase() != 0) {
            super.move(type, motion);
        }
    }

    /**
     * Do not apply knockback in Phase 1 as we are unmoving
     * Do not apply knockback in Phase 3 as we are enraged
     */
    @Override
    public void knockback(float amount, double x, double z) {
        if (getPhase() == 1) {
            super.knockback(amount, x, z);
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

        if (getPhase() == 0) {
            //Don't move, except falling
            Vector3d motion = this.getDeltaMovement();
            this.setDeltaMovement(0.0D, motion.y(), 0.0D);

            //Check if we spawned drones in this phase
            if (!hasSpawnedDrones()) {
                spawnDrones();
                setSpawnedDrones(true);
            }
        }

        if (getPhase() == 0 && getDronesLeft() == 0 && hasSpawnedDrones()) {
            //No more drones, time for the next phase
            this.setPhase(1);
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
            setDronesLeft(3);
        } if (difficulty == Difficulty.NORMAL) {
            createDrone(bpTopLeft);
            createDrone(bpLowLeft);
            createDrone(bpTopRight);
            createDrone(bpLowRight);
            setDronesLeft(4);
        } if (difficulty == Difficulty.HARD) {
            createDrone(bpTopLeft);
            createDrone(bpLowLeft);
            createDrone(bpTopRight);
            createDrone(bpLowRight);
            createDrone(bpMid);
            setDronesLeft(5);
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
    }

    /**
     * Pointless as it is, this is to safely count down the number of drones left
     * We don't want others to set drones left recklessly, so this exists to count down outside this entity.
     */
    public void onDroneKilled() {
        //Only tick down when necessary
        if (getDronesLeft() > 0)
            setDronesLeft(getDronesLeft() - 1);
    }

    @Override
    protected int decreaseAirSupply(int amount) {
        return amount;
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
        if (this.level.getDifficulty() == Difficulty.NORMAL || this.level.getDifficulty() == Difficulty.HARD) {
            return source.getDirectEntity() instanceof PlayerEntity;
        } else {
            return source.getDirectEntity() instanceof LivingEntity;
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

    class AttackGoal extends MeleeAttackGoal {

        /**
         * MeleeAttackGoal, but it won't execute if it is Phase 1
         */
        public AttackGoal() {
            super(MalachiteGuardEntity.this, 0.6D, true);
        }

        @Override
        public boolean canUse() {
            return MalachiteGuardEntity.this.getPhase() != 0 && super.canUse();
        }
    }
}
