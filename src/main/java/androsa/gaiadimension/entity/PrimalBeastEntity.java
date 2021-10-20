package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.Random;

public class PrimalBeastEntity extends Monster {

    public PrimalBeastEntity(EntityType<? extends PrimalBeastEntity> entity, Level world) {
        super(entity, world);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.xpReward = 15;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.4D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
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
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
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
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<PrimalBeastEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && pos.getY() < 20.0D && pos.getY() > 0.0D;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
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
