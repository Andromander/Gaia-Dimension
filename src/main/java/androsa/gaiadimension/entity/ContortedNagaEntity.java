package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModEffects;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import java.util.Random;

public class ContortedNagaEntity extends MonsterEntity {

    public ContortedNagaEntity(EntityType<? extends ContortedNagaEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 15;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 150.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.ARMOR, 1.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.4D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.4D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 2.3F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof LivingEntity) {
                int i;

                switch (this.world.getDifficulty()) {
                    case EASY:
                        i = 5;
                        break;
                    case NORMAL:
                        i = 10;
                        break;
                    case HARD:
                        i = 20;
                        break;
                    default:
                        i = 0;
                }

                if (i > 0) {
                    ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(ModEffects.goldstone_plague, i * 20, 0));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canSpawn(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<ContortedNagaEntity> entity, IServerWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == SpawnReason.SPAWNER) {
                return isValidLightLevel(world, pos, random);
            } else {
                return world.getBlockState(pos.down()).canEntitySpawn(world, pos.down(), entity) && world.getLightFor(LightType.SKY, pos) > 8;
            }
        }
        return false;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return GaiaDimensionMod.CORRUPT;
    }
}
