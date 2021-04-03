package androsa.gaiadimension.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class BismuthUletrusEntity extends CreatureEntity {

    private static final DataParameter<Boolean> RESTING = EntityDataManager.defineId(BismuthUletrusEntity.class, DataSerializers.BOOLEAN);

    public BismuthUletrusEntity(EntityType<? extends BismuthUletrusEntity> entity, World world) {
        super(entity, world);
        xpReward = 5;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RESTING, false);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.8D));

    }

    public boolean getResting() {
        return this.entityData.get(RESTING);
    }

    public void setResting(boolean rest) {
        this.entityData.set(RESTING, rest);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setResting(compound.getBoolean("UletrusResting"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("UletrusResting", this.getResting());
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        this.setResting(false);
        return super.hurt(source, amount);
    }

    public static boolean canSpawnHere(EntityType<BismuthUletrusEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return spawn == SpawnReason.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getBrightness(LightType.SKY, pos) > 8;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getResting()) {
            this.setDeltaMovement(getDeltaMovement().multiply(0.0D, 0.0D, 0.0D));
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        BlockPos blockpos = this.blockPosition();
        BlockPos blockpos1 = blockpos.below();

        if (this.getResting()) {
            if (this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos)) {
                if (random.nextInt(1000) == 0) {
                    this.setResting(false);
                }
            } else {
                this.setResting(false);
            }
        } else {
            if (this.getDeltaMovement().x() == 0 && this.getDeltaMovement().z() == 0) {
                if (this.random.nextInt(1000) == 0 && this.level.getBlockState(blockpos1).isRedstoneConductor(this.level, blockpos)) {
                    this.setResting(true);
                }
            }
        }
    }
}
