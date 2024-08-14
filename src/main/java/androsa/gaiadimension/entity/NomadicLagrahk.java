package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.data.LagrahkVariant;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
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
    private static final EntityDataAccessor<LagrahkVariant> LAGRAHK_VARIANT = SynchedEntityData.defineId(NomadicLagrahk.class, ModEntities.LAGRAHK_VARIANT.get());

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
        builder.define(LAGRAHK_VARIANT, LagrahkVariant.BASE);
    }

    public LagrahkVariant getEntityVariant() {
        return entityData.get(LAGRAHK_VARIANT);
    }

    public void setLagrahkVariant(LagrahkVariant type) {
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
        this.setLagrahkVariant(LagrahkVariant.getVariant(compound.getInt("LagrahkVariant")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LagrahkVariant", this.getEntityVariant().getId());
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
            setLagrahkVariant(LagrahkVariant.SALTY);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.static_wasteland))) {
            setLagrahkVariant(LagrahkVariant.STATIC);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.volcanic_lands))) {
            setLagrahkVariant(LagrahkVariant.VOLCANIC);
        } else {
            setLagrahkVariant(LagrahkVariant.BASE);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
    }
}
