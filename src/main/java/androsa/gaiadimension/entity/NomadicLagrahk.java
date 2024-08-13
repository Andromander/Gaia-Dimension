package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class NomadicLagrahk extends PathfinderMob {
    private static final EntityDataAccessor<Integer> LAGRAHK_VARIANT = SynchedEntityData.defineId(NomadicLagrahk.class, EntityDataSerializers.INT);

    public NomadicLagrahk(EntityType<? extends NomadicLagrahk> entity, Level world) {
        super(entity, world);
        this.xpReward = 10;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LAGRAHK_VARIANT, 0);
    }

    /**
     * Get the variant integer
     */
    public int getEntityVariant() {
        return Mth.clamp(entityData.get(LAGRAHK_VARIANT), 0, 3);
    }

    /**
     * 0 = No biome specified (either a fallback, or for later use)
     * 1 = Salt Dunes variant
     * 2 = Static Wasteland variant
     * 3 = Volcaniclands variant
     */
    public void setLagrahkVariant(int type) {
        entityData.set(LAGRAHK_VARIANT, type);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setLagrahkVariant(compound.getInt("LagrahkVariant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LagrahkVariant", this.getEntityVariant());
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_NOMADIC_LAGRAHK_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_NOMADIC_LAGRAHK_HURT.get();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        Optional<ResourceKey<Biome>> biome = level().getBiome(this.blockPosition()).unwrapKey();

        if (Objects.equals(biome, Optional.of(GaiaBiomes.salt_dunes)) || Objects.equals(biome, Optional.of(GaiaBiomes.static_wasteland)) || Objects.equals(biome, Optional.of(GaiaBiomes.volcanic_lands))) {
            return 4;
        } else {
            return 1;
        }
    }

    public static boolean canSpawnHere(EntityType<NomadicLagrahk> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn) {
        Optional<ResourceKey<Biome>> biome = worldIn.getBiome(this.blockPosition()).unwrapKey();

        if (Objects.equals(biome, Optional.of(GaiaBiomes.salt_dunes))) {
            setLagrahkVariant(1);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.static_wasteland))) {
            setLagrahkVariant(2);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.volcanic_lands))) {
            setLagrahkVariant(3);
        } else {
            setLagrahkVariant(0);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
    }
}
