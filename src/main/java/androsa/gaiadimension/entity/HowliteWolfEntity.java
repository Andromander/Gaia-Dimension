package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;

public class HowliteWolfEntity extends MonsterEntity {

    public HowliteWolfEntity(EntityType<? extends HowliteWolfEntity> entity, World world) {
        super(entity, world);
        this.xpReward = 5;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, BlueHowliteWolfEntity.class, 16.0F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_HOWLITE_WOLF_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_HOWLITE_WOLF_HURT;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.68F;
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<HowliteWolfEntity> entity, IServerWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == SpawnReason.SPAWNER) {
                return isDarkEnoughToSpawn(world, pos, random);
            } else {
                return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getBrightness(LightType.SKY, pos) > 8;
            }
        }
        return false;
    }
}
