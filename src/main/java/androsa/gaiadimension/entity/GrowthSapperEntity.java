package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
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

public class GrowthSapperEntity extends CreatureEntity {
    private static final DataParameter<Integer> SAPPER_VARIANT = EntityDataManager.createKey(GrowthSapperEntity.class, DataSerializers.VARINT);
    /*public static final ResourceLocation PINK_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/common_sapper");
    public static final ResourceLocation BLUE_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/chilled_sapper");
    public static final ResourceLocation GREN_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/nutrient_sapper");
    public static final ResourceLocation PURP_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/mystified_sapper");*/

    public GrowthSapperEntity(EntityType<? extends GrowthSapperEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SAPPER_VARIANT, 0);
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
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
        return dataManager.get(SAPPER_VARIANT);
    }

    /**
     * 0 = Common Sapper
     * 1 = Chilled Sapper
     * 2 = Nutrient Sapper
     * 3 = Mystified Sapper
     *
     * @param type The integer variant of the entity
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
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.70F;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));

        if (biome instanceof GDPinkAgateForest || biome instanceof GDCrystalPlains) {
            setSapperVariant(0);
        } else if (biome instanceof GDBlueAgateTaiga) {
            setSapperVariant(1);
        } else if (biome instanceof GDGreenAgateJungle) {
            setSapperVariant(2);
        } else if (biome instanceof GDPurpleAgateSwamp) {
            setSapperVariant(3);
        } else {
            Random rand = new Random();
            setSapperVariant(rand.nextInt(4));
        }

        return spawnDataIn;
    }

    /*@Override
    public ResourceLocation getLootTable() {
        switch (getEntityVariant()) {
            case 0:
                return PINK_TABLE;
            case 1:
                return BLUE_TABLE;
            case 2:
                return GREN_TABLE;
            case 3:
                return PURP_TABLE;
            default:
                return null;
        }
    }*/
}
