package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.registration.ModFluids;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;

import javax.annotation.Nullable;

public class ShallowArenthisEntity extends WaterAnimal {

    public float arenthisPitch;
    public float prevArenthisPitch;
    public float arenthisYaw;
    public float prevArenthisYaw;
    public float arenthisRotation;
    public float prevArenthisRotation;
    public float tentacleAngle;
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public ShallowArenthisEntity(EntityType<? extends ShallowArenthisEntity> entity, Level worldIn) {
        super(entity, worldIn);
        this.xpReward = 1 + random.nextInt(3);
        this.random.setSeed((long) (1 + this.getId()));
        this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return type == ModFluids.MINERAL_WATER.get() || type == NeoForgeMod.WATER_TYPE.value();
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return type != ModFluids.MINERAL_WATER.get() && type != NeoForgeMod.WATER_TYPE.value();
    }

    @Override
    protected void handleAirSupply(int amount) {
        if (this.isAlive() && !this.isInWaterOrBubble() && this.isInFluidType((type, height) -> this.canDrownInFluidType(type), true)) {
            this.setAirSupply(amount - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new ShallowArenthisEntity.AIMoveRandom(this));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_SHALLOW_ARENTHIS_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_SHALLOW_ARENTHIS_HURT.get();
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.prevArenthisPitch = this.arenthisPitch;
        this.prevArenthisYaw = this.arenthisYaw;
        this.prevArenthisRotation = this.arenthisRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.arenthisRotation += this.rotationVelocity;
        if ((double)this.arenthisRotation > (Math.PI * 2D)) {
            if (this.level().isClientSide()) {
                this.arenthisRotation = ((float)Math.PI * 2F);
            } else {
                this.arenthisRotation = (float)((double)this.arenthisRotation - (Math.PI * 2D));
                if (this.random.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.level().broadcastEntityEvent(this, (byte)19);
            }
        }

        if (this.isInWaterOrBubble() || this.isInFluidType((type, height) -> this.canSwimInFluidType(type))) {
            if (this.arenthisRotation < (float)Math.PI) {
                float f = this.arenthisRotation / (float)Math.PI;
                this.tentacleAngle = Mth.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
                if ((double)f > 0.75D) {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.level().isClientSide()) {
                this.setDeltaMovement(this.randomMotionVecX * this.randomMotionSpeed, this.randomMotionVecY * this.randomMotionSpeed, this.randomMotionVecZ * this.randomMotionSpeed);
            }

            Vec3 vec3d = this.getDeltaMovement();
            double dist = vec3d.horizontalDistance();
            this.yBodyRot += (-((float)Mth.atan2(vec3d.x, vec3d.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
            this.setYRot(this.yBodyRot);
            this.arenthisYaw = (float)((double)this.arenthisYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.arenthisPitch += (-((float)Mth.atan2(dist, vec3d.y)) * (180F / (float)Math.PI) - this.arenthisPitch) * 0.1F;
        } else {
            this.tentacleAngle = Mth.abs(Mth.sin(this.arenthisRotation)) * (float)Math.PI * 0.25F;
            if (!this.level().isClientSide()) {
                double d0 = this.getDeltaMovement().y;
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    d0 = 0.05D * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
                } else if (!this.isNoGravity()) {
                    d0 -= 0.08D;
                }

                this.setDeltaMovement(0.0D, d0 * (double)0.98F, 0.0D);
            }

            this.arenthisPitch = (float)((double)this.arenthisPitch + (double)(-90.0F - this.arenthisPitch) * 0.02D);
        }
    }

    public static boolean canSpawnHere(EntityType<ShallowArenthisEntity> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) {
        return pos.getY() > 30 && pos.getY() < world.getSeaLevel();
    }

    @Override
    public void travel(Vec3 motion) {
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 19) {
            this.arenthisRotation = 0.0F;
        } else {
            super.handleEntityEvent(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector() {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    static class AIMoveRandom extends Goal {
        private final ShallowArenthisEntity arenthis;

        public AIMoveRandom(ShallowArenthisEntity entity) {
            this.arenthis = entity;
        }

        public boolean canUse() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            int i = this.arenthis.getNoActionTime();

            if (i > 100) {
                this.arenthis.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.arenthis.getRandom().nextInt(reducedTickDelay(50)) == 0 || !(this.arenthis.wasTouchingWater || this.arenthis.isInFluidType((fluidType, height) -> this.arenthis.canSwimInFluidType(fluidType))) || !this.arenthis.hasMovementVector()) {
                float randomVec = this.arenthis.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float vecX = Mth.cos(randomVec) * 0.2F;
                float vecY = -0.1F + this.arenthis.getRandom().nextFloat() * 0.2F;
                float vecZ = Mth.sin(randomVec) * 0.2F;
                this.arenthis.setMovementVector(vecX, vecY, vecZ);
            }
        }
    }
}
