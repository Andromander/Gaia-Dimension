package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;

public class LesserSpitfireEntity extends MonsterEntity implements ISpitfireMob {

    public LesserSpitfireEntity(EntityType<? extends LesserSpitfireEntity> entity, World worldIn) {
        super(entity, worldIn);
        this.xpReward = 10;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.8D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.8D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.8D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MonsterEntity.class, 10, false, true, entity -> entity != null && entity instanceof IShockshooterMob));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23000000417232513D);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.8F;
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<LesserSpitfireEntity> entity, IServerWorld world, SpawnReason spawn, BlockPos pos, Random random) {
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
