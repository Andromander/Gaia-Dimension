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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class BismuthUletrus extends PathfinderMob {

    private static final EntityDataAccessor<Boolean> RESTING = SynchedEntityData.defineId(BismuthUletrus.class, EntityDataSerializers.BOOLEAN);

    public BismuthUletrus(EntityType<? extends BismuthUletrus> entity, Level world) {
        super(entity, world);
        xpReward = 5;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(RESTING, false);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8D));

    }

    public boolean getResting() {
        return this.entityData.get(RESTING);
    }

    public void setResting(boolean rest) {
        this.entityData.set(RESTING, rest);
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.setResting(compound.getBooleanOr("UletrusResting", false));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("UletrusResting", this.getResting());
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_BISMUTH_ULETRUS_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_BISMUTH_ULETRUS_HURT.get();
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        this.setResting(false);
        return super.hurtServer(level, source, amount);
    }

    public static boolean canSpawnHere(EntityType<BismuthUletrus> entity, LevelAccessor world, EntitySpawnReason spawn, BlockPos pos, RandomSource random) {
        return spawn == EntitySpawnReason.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getBrightness(LightLayer.SKY, pos) > 8;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getResting()) {
            this.setDeltaMovement(getDeltaMovement().multiply(0.0D, 0.0D, 0.0D));
        }
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.below();

        if (this.getResting()) {
            if (level.getBlockState(blockpos1).isRedstoneConductor(level, blockpos)) {
                if (random.nextInt(1000) == 0) {
                    this.setResting(false);
                }
            } else {
                this.setResting(false);
            }
        } else {
            if (this.getDeltaMovement().x() == 0 && this.getDeltaMovement().z() == 0) {
                if (this.random.nextInt(1000) == 0 && level.getBlockState(blockpos1).isRedstoneConductor(level, blockpos)) {
                    this.setResting(true);
                }
            }
        }
    }
}
