package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
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

public class GrowthSapperEntity extends CreatureEntity {
    private static final DataParameter<Integer> SAPPER_VARIANT = EntityDataManager.createKey(GrowthSapperEntity.class, DataSerializers.VARINT);

    public GrowthSapperEntity(EntityType<? extends GrowthSapperEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SAPPER_VARIANT, 0);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5D));
    }

    public int getEntityVariant() {
        return MathHelper.clamp(dataManager.get(SAPPER_VARIANT), 0, 3);
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
        dataManager.set(SAPPER_VARIANT, type);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSapperVariant(compound.getInt("SapperVariant"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("SapperVariant", this.getEntityVariant());
    }

    @Override
    public ResourceLocation getLootTable() {
        switch (this.getEntityVariant()) {
            case 0:
            default:
                return getLocation("common_sapper");
            case 1:
                return getLocation("chilled_sapper");
            case 2:
                return getLocation("nutrient_sapper");
            case 3:
                return getLocation("mystified_sapper");
        }
    }

    private ResourceLocation getLocation(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "entities/" + name);
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.70F;
    }

    public static boolean canSpawnHere(EntityType<GrowthSapperEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return spawn == SpawnReason.SPAWNER || world.getBlockState(pos.down()).canEntitySpawn(world, pos.down(), entity) && world.getLightSubtracted(pos, 0) > 8;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        Optional<RegistryKey<Biome>> biome = worldIn.func_242406_i(this.getPosition());

        if (Objects.equals(biome, Optional.of(ModBiomes.pink_agate_forest)) || Objects.equals(biome, Optional.of(ModBiomes.crystal_plains))) {
            setSapperVariant(0);
        } else if (Objects.equals(biome, Optional.of(ModBiomes.blue_agate_taiga))) {
            setSapperVariant(1);
        } else if (Objects.equals(biome, Optional.of(ModBiomes.green_agate_jungle))) {
            setSapperVariant(2);
        } else if (Objects.equals(biome, Optional.of(ModBiomes.pink_agate_forest))) {
            setSapperVariant(3);
        } else {
            Random rand = new Random();
            setSapperVariant(rand.nextInt(4));
        }

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}
