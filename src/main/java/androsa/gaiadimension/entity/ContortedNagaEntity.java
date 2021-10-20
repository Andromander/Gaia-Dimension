package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModEffects;
import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;

public class ContortedNagaEntity extends Monster {

    public ContortedNagaEntity(EntityType<? extends ContortedNagaEntity> entity, Level world) {
        super(entity, world);
        this.xpReward = 15;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.ATTACK_DAMAGE, 7.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 1.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_CONTORTED_NAGA_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_CONTORTED_NAGA_HURT;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 2.3F;
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof LivingEntity living) {
                int i = switch (this.level.getDifficulty()) {
                    case EASY -> 5;
                    case NORMAL -> 10;
                    case HARD -> 20;
                    default -> 0;
                };

                if (i > 0) {
                    living.addEffect(new MobEffectInstance(ModEffects.goldstone_plague, i * 20, 0));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<ContortedNagaEntity> entity, ServerLevelAccessor world, MobSpawnType spawn, BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == MobSpawnType.SPAWNER) {
                return isDarkEnoughToSpawn(world, pos, random);
            } else {
                return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getBrightness(LightLayer.SKY, pos) > 8;
            }
        }
        return false;
    }

    @Override
    public MobType getMobType() {
        return GaiaDimensionMod.CORRUPT;
    }
}
