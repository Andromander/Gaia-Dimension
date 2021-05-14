package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class MineralArenthisEntity extends WaterMobEntity {

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

    public MineralArenthisEntity(EntityType<? extends MineralArenthisEntity> entity, World worldIn) {
        super(entity, worldIn);
        this.xpReward = 5;
        this.random.setSeed((long) (1 + this.getId()));
        this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MineralArenthisEntity.MoveRandomGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MINERAL_ARENTHIS_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_MINERAL_ARENTHIS_HURT;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.getBbHeight() * 0.85F;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
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
            if (this.level.isClientSide()) {
                this.arenthisRotation = ((float)Math.PI * 2F);
            } else {
                this.arenthisRotation = (float)((double)this.arenthisRotation - (Math.PI * 2D));
                if (this.random.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }

                this.level.broadcastEntityEvent(this, (byte)19);
            }
        }

        if (this.isInWaterOrBubble()) {
            if (this.arenthisRotation < (float)Math.PI) {
                float f = this.arenthisRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;
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

            if (!this.level.isClientSide()) {
                this.setDeltaMovement((double)(this.randomMotionVecX * this.randomMotionSpeed), (double)(this.randomMotionVecY * this.randomMotionSpeed), (double)(this.randomMotionVecZ * this.randomMotionSpeed));
            }

            Vector3d vec3d = this.getDeltaMovement();
            float f1 = MathHelper.sqrt(getHorizontalDistanceSqr(vec3d));
            this.yBodyRot += (-((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
            this.yRot = this.yBodyRot;
            this.arenthisYaw = (float)((double)this.arenthisYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.arenthisPitch += (-((float)MathHelper.atan2((double)f1, vec3d.y)) * (180F / (float)Math.PI) - this.arenthisPitch) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.arenthisRotation)) * (float)Math.PI * 0.25F;
            if (!this.level.isClientSide()) {
                double d0 = this.getDeltaMovement().y;
                if (this.hasEffect(Effects.LEVITATION)) {
                    d0 = 0.05D * (double)(this.getEffect(Effects.LEVITATION).getAmplifier() + 1);
                } else if (!this.isNoGravity()) {
                    d0 -= 0.08D;
                }

                this.setDeltaMovement(0.0D, d0 * (double)0.98F, 0.0D);
            }

            this.arenthisPitch = (float)((double)this.arenthisPitch + (double)(-90.0F - this.arenthisPitch) * 0.02D);
        }
    }

    public static boolean canSpawnHere(EntityType<MineralArenthisEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > 30 && pos.getY() < 50;
    }

    @Override
    public void travel(Vector3d motion) {
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
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

    static class MoveRandomGoal extends Goal {
        private final MineralArenthisEntity arenthis;

        public MoveRandomGoal(MineralArenthisEntity entity) {
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
            } else if (this.arenthis.getRandom().nextInt(50) == 0 || !this.arenthis.wasTouchingWater || !this.arenthis.hasMovementVector()) {
                float randomVec = this.arenthis.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float vecX = MathHelper.cos(randomVec) * 0.2F;
                float vecY = -0.1F + this.arenthis.getRandom().nextFloat() * 0.2F;
                float vecZ = MathHelper.sin(randomVec) * 0.2F;
                this.arenthis.setMovementVector(vecX, vecY, vecZ);
            }
        }
    }
}
