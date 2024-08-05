package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModSounds;
import androsa.gaiadimension.registry.values.GaiaBuiltinTables;
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
import java.util.Random;

public class GrowthSapperEntity extends PathfinderMob {
    private static final EntityDataAccessor<Integer> SAPPER_VARIANT = SynchedEntityData.defineId(GrowthSapperEntity.class, EntityDataSerializers.INT);

    public GrowthSapperEntity(EntityType<? extends GrowthSapperEntity> entity, Level world) {
        super(entity, world);
        this.xpReward = 1 + random.nextInt(3);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SAPPER_VARIANT, 0);
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

    public int getEntityVariant() {
        return Mth.clamp(entityData.get(SAPPER_VARIANT), 0, 3);
    }

    /**
     * 0 = Common Sapper
     * 1 = Chilled Sapper
     * 2 = Nutrient Sapper
     * 3 = Mystified Sapper
     *
     * //@param type The integer variant of the entity
     */
    public void setSapperVariant(int type) {
        entityData.set(SAPPER_VARIANT, type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSapperVariant(compound.getInt("SapperVariant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("SapperVariant", this.getEntityVariant());
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
        return switch (this.getEntityVariant()) {
            default -> GaiaBuiltinTables.PINK_SAPPER_TABLE;
            case 1 -> GaiaBuiltinTables.BLUE_SAPPER_TABLE;
            case 2 -> GaiaBuiltinTables.GREEN_SAPPER_TABLE;
            case 3 -> GaiaBuiltinTables.PURPLE_SAPPER_TABLE;
        };
    }

    public static boolean canSpawnHere(EntityType<GrowthSapperEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn) {
        Optional<ResourceKey<Biome>> biome = worldIn.getBiome(this.blockPosition()).unwrapKey();

        if (Objects.equals(biome, Optional.of(GaiaBiomes.pink_agate_forest)) || Objects.equals(biome, Optional.of(GaiaBiomes.crystal_plains))) {
            setSapperVariant(0);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.blue_agate_taiga))) {
            setSapperVariant(1);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.green_agate_jungle))) {
            setSapperVariant(2);
        } else if (Objects.equals(biome, Optional.of(GaiaBiomes.purple_agate_swamp))) {
            setSapperVariant(3);
        } else {
            Random rand = new Random();
            setSapperVariant(rand.nextInt(4));
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
    }
}
