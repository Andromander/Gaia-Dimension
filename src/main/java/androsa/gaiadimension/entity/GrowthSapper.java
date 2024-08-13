package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.data.SapperVariant;
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
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class GrowthSapper extends PathfinderMob {
    private static final EntityDataAccessor<SapperVariant> SAPPER_VARIANT = SynchedEntityData.defineId(GrowthSapper.class, ModEntities.SAPPER_VARIANT.get());

    public GrowthSapper(EntityType<? extends GrowthSapper> entity, Level world) {
        super(entity, world);
        this.xpReward = 1 + random.nextInt(3);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SAPPER_VARIANT, SapperVariant.COMMON);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.5D));
    }

    public SapperVariant getEntityVariant() {
        return entityData.get(SAPPER_VARIANT);
    }

    public void setSapperVariant(SapperVariant type) {
        entityData.set(SAPPER_VARIANT, type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSapperVariant(SapperVariant.getVariant(compound.getInt("SapperVariant")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("SapperVariant", this.getEntityVariant().getId());
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_GROWTH_SAPPER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_GROWTH_SAPPER_HURT.get();
    }

    @Override
    public ResourceKey<LootTable> getDefaultLootTable() {
        return this.getEntityVariant().getLootTable();
    }

    public static boolean canSpawnHere(EntityType<GrowthSapper> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn) {
        Optional<ResourceKey<Biome>> biome = worldIn.getBiome(this.blockPosition()).unwrapKey();

        if (Objects.equals(biome, Optional.of(GaiaBiomes.pink_agate_forest)) || Objects.equals(biome, Optional.of(GaiaBiomes.crystal_plains))) {
            setSapperVariant(SapperVariant.COMMON);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.blue_agate_taiga))) {
            setSapperVariant(SapperVariant.CHILLED);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.green_agate_jungle))) {
            setSapperVariant(SapperVariant.NUTRIENT);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.purple_agate_swamp))) {
            setSapperVariant(SapperVariant.MYSTIFIED);
        } else {
            setSapperVariant(SapperVariant.getRandomVariant(worldIn.getRandom()));
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
    }
}
