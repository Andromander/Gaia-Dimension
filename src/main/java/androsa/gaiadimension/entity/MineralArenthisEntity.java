package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
        this.experienceValue = 5;
        this.rand.setSeed((long) (1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MineralArenthisEntity.MoveRandomGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.getHeight() * 0.85F;
    }

//    @Override
//    protected boolean canTriggerWalking() {
//        return false;
//    }

    @Override
    public void livingTick() {
        super.livingTick();
        this.prevArenthisPitch = this.arenthisPitch;
        this.prevArenthisYaw = this.arenthisYaw;
        this.prevArenthisRotation = this.arenthisRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.arenthisRotation += this.rotationVelocity;
        if ((double)this.arenthisRotation > (Math.PI * 2D)) {
            if (this.world.isRemote) {
                this.arenthisRotation = ((float)Math.PI * 2F);
            } else {
                this.arenthisRotation = (float)((double)this.arenthisRotation - (Math.PI * 2D));
                if (this.rand.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte)19);
            }
        }

        if (this.isInWaterOrBubbleColumn()) {
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

            if (!this.world.isRemote) {
                this.setMotion((double)(this.randomMotionVecX * this.randomMotionSpeed), (double)(this.randomMotionVecY * this.randomMotionSpeed), (double)(this.randomMotionVecZ * this.randomMotionSpeed));
            }

            Vector3d vec3d = this.getMotion();
            float f1 = MathHelper.sqrt(horizontalMag(vec3d));
            this.renderYawOffset += (-((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180F / (float)Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.arenthisYaw = (float)((double)this.arenthisYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.arenthisPitch += (-((float)MathHelper.atan2((double)f1, vec3d.y)) * (180F / (float)Math.PI) - this.arenthisPitch) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.arenthisRotation)) * (float)Math.PI * 0.25F;
            if (!this.world.isRemote) {
                double d0 = this.getMotion().y;
                if (this.isPotionActive(Effects.LEVITATION)) {
                    d0 = 0.05D * (double)(this.getActivePotionEffect(Effects.LEVITATION).getAmplifier() + 1);
                } else if (!this.hasNoGravity()) {
                    d0 -= 0.08D;
                }

                this.setMotion(0.0D, d0 * (double)0.98F, 0.0D);
            }

            this.arenthisPitch = (float)((double)this.arenthisPitch + (double)(-90.0F - this.arenthisPitch) * 0.02D);
        }
    }

    public static boolean canSpawnHere(EntityType<MineralArenthisEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) {
        return pos.getY() > 30 && pos.getY() < 50;
    }

    @Override
    public void travel(Vector3d motion) {
        this.move(MoverType.SELF, this.getMotion());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 19) {
            this.arenthisRotation = 0.0F;
        } else {
            super.handleStatusUpdate(id);
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

        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            int i = this.arenthis.getIdleTime();

            if (i > 100) {
                this.arenthis.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.arenthis.getRNG().nextInt(50) == 0 || !this.arenthis.inWater || !this.arenthis.hasMovementVector()) {
                float randomVec = this.arenthis.getRNG().nextFloat() * ((float)Math.PI * 2F);
                float vecX = MathHelper.cos(randomVec) * 0.2F;
                float vecY = -0.1F + this.arenthis.getRNG().nextFloat() * 0.2F;
                float vecZ = MathHelper.sin(randomVec) * 0.2F;
                this.arenthis.setMovementVector(vecX, vecY, vecZ);
            }
        }
    }
}
