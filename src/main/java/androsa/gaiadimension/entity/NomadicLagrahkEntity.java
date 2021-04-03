package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class NomadicLagrahkEntity extends CreatureEntity {
    private static final DataParameter<Integer> LAGRAHK_VARIANT = EntityDataManager.defineId(NomadicLagrahkEntity.class, DataSerializers.INT);

    public NomadicLagrahkEntity(EntityType<? extends NomadicLagrahkEntity> entity, World world) {
        super(entity, world);
        this.xpReward = 10;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LAGRAHK_VARIANT, 0);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 3.55F;
    }

    /**
     * Get the variant integer
     */
    public int getEntityVariant() {
        return MathHelper.clamp(entityData.get(LAGRAHK_VARIANT), 0, 3);
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
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setLagrahkVariant(compound.getInt("LagrahkVariant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("LagrahkVariant", this.getEntityVariant());
    }

    @Override
    public int getMaxSpawnClusterSize() {
        Optional<RegistryKey<Biome>> biome = level.getBiomeName(new BlockPos(getX(), getY(), getZ()));

        if (Objects.equals(biome, Optional.of(ModBiomes.salt_dunes)) || Objects.equals(biome, Optional.of(ModBiomes.static_wasteland)) || Objects.equals(biome, Optional.of(ModBiomes.volcanic_lands))) {
            return 4;
        } else {
            return 1;
        }
    }

    public static boolean canSpawnHere(EntityType<NomadicLagrahkEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        Optional<RegistryKey<Biome>> biome = worldIn.getBiomeName(new BlockPos(getX(), getY(), getZ()));

        if (Objects.equals(biome, Optional.of(ModBiomes.salt_dunes))) {
            setLagrahkVariant(1);
        } else if (Objects.equals(biome, Optional.of(ModBiomes.static_wasteland))) {
            setLagrahkVariant(2);
        } else if (Objects.equals(biome, Optional.of(ModBiomes.volcanic_lands))) {
            setLagrahkVariant(3);
        } else {
            setLagrahkVariant(0);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}
