package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class PrimalBeastEntity extends MonsterEntity {

    public PrimalBeastEntity(EntityType<? extends PrimalBeastEntity> entity, World world) {
        super(entity, world);
        this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.xpReward = 15;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
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
        return ModSounds.ENTITY_PRIMAL_BEAST_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ENTITY_PRIMAL_BEAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_PRIMAL_BEAST_DEATH;
    }

    @Override
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.9F;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos) {
        return 0.0F;
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<PrimalBeastEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && pos.getY() < 20.0D && pos.getY() > 0.0D;
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader world) {
        return world.isUnobstructed(this);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        boolean attacked = super.doHurtTarget(entityIn);

        if (attacked) {
            float diff = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            entityIn.setSecondsOnFire(2 * (int)diff);
        }
        return attacked;
    }
}
