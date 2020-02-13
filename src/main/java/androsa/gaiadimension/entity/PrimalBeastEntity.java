package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class PrimalBeastEntity extends MonsterEntity {

    public PrimalBeastEntity(EntityType<? extends PrimalBeastEntity> entity, World world) {
        super(entity, world);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.experienceValue = 15;
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.4D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    protected float getSoundPitch() {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.9F;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public int getBrightnessForRender() {
//        return 15728880;
//    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return 0.0F;
    }

    @Override
    public boolean canSpawn(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<PrimalBeastEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && pos.getY() < 20.0D && pos.getY() > 0.0D;
    }

    @Override
    public boolean isNotColliding(IWorldReader world) {
        return world.intersectsEntities(this);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean attacked = super.attackEntityAsMob(entityIn);

        if (attacked) {
            float diff = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            entityIn.setFire(2 * (int)diff);
        }
        return attacked;
    }
}
