package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class CrystalGolemEntity extends GolemEntity {

    public CrystalGolemEntity(EntityType<? extends CrystalGolemEntity> entity, World world) {
        super(entity, world);
        this.xpReward = 15;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.3D, 32.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AgateGolemEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 10, false, true, apply -> apply instanceof IMob && !(apply instanceof CreeperEntity)));
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        this.level.broadcastEntityEvent(this, (byte)4);
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)(6 + this.random.nextInt(15)));

        if (flag) {
            entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, 0.4D, 0.0D));
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ENTITY_CRYSTAL_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_CRYSTAL_GOLEM_DEATH;
    }

    public static boolean canSpawnHere(EntityType<CrystalGolemEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return spawn == SpawnReason.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }
}
