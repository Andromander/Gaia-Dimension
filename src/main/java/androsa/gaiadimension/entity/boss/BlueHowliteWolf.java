package androsa.gaiadimension.entity.boss;

import androsa.gaiadimension.entity.HowliteWolf;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
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

public class BlueHowliteWolf extends Monster {

    //TODO: Flesh out and revise
    public BlueHowliteWolf(EntityType<? extends BlueHowliteWolf> entity, Level world) {
        super(entity, world);
        this.xpReward = 30;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, HowliteWolf.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(HowliteWolf.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_BLUE_HOWLITE_WOLF_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_BLUE_HOWLITE_WOLF_HURT.get();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<BlueHowliteWolf> entity, ServerLevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == MobSpawnType.SPAWNER) {
                return isDarkEnoughToSpawn(world, pos, random);
            } else {
                return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getBrightness(LightLayer.SKY, pos) > 8;
            }
        }
        return false;
    }

    //Keep this commented out until later
/*
    @Override
    public boolean canChangeDimensions() {
        return false;
    }
*/
}
