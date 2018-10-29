package androsa.gaiadimension.entity;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDMineralArenthis extends EntityWaterMob {
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

    public GDMineralArenthis(World worldIn) {
        super(worldIn);
        this.setSize(1.5F, 1.5F);

        this.experienceValue = 5;
        this.rand.setSeed((long) (1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new GDMineralArenthis.AIMoveRandom(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.85F;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevArenthisPitch = this.arenthisPitch;
        this.prevArenthisYaw = this.arenthisYaw;
        this.prevArenthisRotation = this.arenthisRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.arenthisRotation += this.rotationVelocity;

        if ((double) this.arenthisRotation > (Math.PI * 2D)) {
            if (this.world.isRemote) {
                this.arenthisRotation = ((float) Math.PI * 2F);
            } else {
                this.arenthisRotation = (float) ((double) this.arenthisRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte) 19);
            }
        }


        if (this.inWater) {
            if (this.arenthisRotation < (float) Math.PI) {
                float randomVec = this.arenthisRotation / (float) Math.PI;
                this.tentacleAngle = MathHelper.sin(randomVec * randomVec * (float) Math.PI) * (float) Math.PI * 0.15F;

                if ((double) randomVec > 0.75D) {
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
                this.motionX = (double) (this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double) (this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double) (this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float) MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float) Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.arenthisYaw = (float) ((double) this.arenthisYaw + Math.PI * (double) this.rotateSpeed * 1.5D);
            this.arenthisPitch += (-((float) MathHelper.atan2((double) f1, this.motionY)) * (180F / (float) Math.PI) - this.arenthisPitch) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.arenthisRotation)) * (float) Math.PI * 0.15F;

            if (!this.world.isRemote) {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION)) {
                    this.motionY += 0.05D * (double) (this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                } else if (!this.hasNoGravity()) {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.arenthisPitch = (float) ((double) this.arenthisPitch + (double) (-90.0F - this.arenthisPitch) * 0.02D);
        }
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
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

    static class AIMoveRandom extends EntityAIBase {
        private final GDMineralArenthis arenthis;

        public AIMoveRandom(GDMineralArenthis entity) {
            this.arenthis = entity;
        }

        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
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
