package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CavernTickEntity extends MonsterEntity {

    public CavernTickEntity(EntityType<? extends CavernTickEntity> entity, World worldIn) {
        super(entity, worldIn);
        this.xpReward = 5;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.5D, 30));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.5D, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.1F;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_CAVERN_TICK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ENTITY_CAVERN_TICK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_CAVERN_TICK_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.ENTITY_CAVERN_TICK_STEP, 0.15F, 1.0F);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos) {
        return this.level.getBlockState(pos.below()).getBlock() == ModBlocks.gaia_stone.get() ? 10.0F : super.getWalkTargetValue(pos);
    }
}
