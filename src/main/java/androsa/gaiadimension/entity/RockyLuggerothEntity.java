package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;
import java.util.Random;

public class RockyLuggerothEntity extends PathfinderMob {

    public RockyLuggerothEntity(EntityType<? extends RockyLuggerothEntity> entity, Level world) {
        super(entity, world);
        this.xpReward = 1 + random.nextInt(3);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.5D));
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_ROCKY_LUGGEROTH_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_ROCKY_LUGGEROTH_HURT;
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 0.35F;
    }

    public static boolean canSpawnHere(EntityType<RockyLuggerothEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }
}
