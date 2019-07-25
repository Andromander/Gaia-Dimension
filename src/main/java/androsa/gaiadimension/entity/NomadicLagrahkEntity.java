package androsa.gaiadimension.entity;

import androsa.gaiadimension.biomes.SaltDunesBiome;
import androsa.gaiadimension.biomes.StaticWastelandBiome;
import androsa.gaiadimension.biomes.VolcaniclandsBiome;
import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.Random;

public class NomadicLagrahkEntity extends CreatureEntity {
    private static final DataParameter<Integer> LAGRAHK_VARIANT = EntityDataManager.createKey(NomadicLagrahkEntity.class, DataSerializers.VARINT);

    public NomadicLagrahkEntity(EntityType<? extends NomadicLagrahkEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 10;
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LAGRAHK_VARIANT, 0);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 3.55F;
    }

    /**
     * Get the variant integer
     */
    public int getEntityVariant() {
        return dataManager.get(LAGRAHK_VARIANT);
    }

    /**
     * 0 = No biome specified (either a fallback, or for later use)
     * 1 = Salt Dunes variant
     * 2 = Static Wasteland variant
     * 3 = Volcaniclands variant
     */
    public void setLagrahkVariant(int type) {
        dataManager.set(LAGRAHK_VARIANT, type);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setLagrahkVariant(compound.getInt("LagrahkVariant"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("LagrahkVariant", this.getEntityVariant());
    }

    @Override
    public int getMaxSpawnedInChunk() {
        Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));

        if (biome instanceof SaltDunesBiome || biome instanceof StaticWastelandBiome || biome instanceof VolcaniclandsBiome) {
            return 4;
        } else {
            return 1;
        }
    }

    public static boolean canSpawnHere(EntityType<NomadicLagrahkEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        BlockState state = world.getBlockState(pos.down());
        return (state.getBlock() instanceof AbstractGaiaGrassBlock && state.getBlock() != ModBlocks.corrupt_grass)
                || state.getBlock() == ModBlocks.wasteland_stone
                || state.getBlock() == ModBlocks.volcanic_rock
                || state.getBlock() == ModBlocks.salt
                && world.getLightSubtracted(pos, 0) > 8;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));

        if (biome instanceof SaltDunesBiome) {
            setLagrahkVariant(1);
        } else if (biome instanceof StaticWastelandBiome) {
            setLagrahkVariant(2);
        } else if (biome instanceof VolcaniclandsBiome) {
            setLagrahkVariant(3);
        } else {
            setLagrahkVariant(0);
        }

        return spawnDataIn;
    }
}
