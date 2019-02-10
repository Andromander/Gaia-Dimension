package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class GDGrowthSapper extends EntityCreature implements IAnimals {
    private static final DataParameter<Integer> SAPPER_VARIANT = EntityDataManager.createKey(GDGrowthSapper.class, DataSerializers.VARINT);
    public static final ResourceLocation PINK_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/common_sapper");
    public static final ResourceLocation BLUE_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/chilled_sapper");
    public static final ResourceLocation GREN_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/nutrient_sapper");
    public static final ResourceLocation PURP_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/mystified_sapper");

    public GDGrowthSapper(World world) {
        super(world);

        this.setSize(1.0F, 1.0F);

        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SAPPER_VARIANT, 0);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.5D));
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
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("SapperVariant", this.getEntityVariant());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSapperVariant(compound.getInteger("SapperVariant"));
    }

    @Override
    public float getEyeHeight() {
        return 0.70F;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        Object data = super.onInitialSpawn(difficulty, livingData);
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

        return (IEntityLivingData)data;
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        switch (getEntityVariant()) {
            case 0:
                return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                        world.getLight(blockpos) > 8 &&
                        world.getBiome(new BlockPos(this)) == GDBiomes.pink_agate_forest ||
                        world.getBiome(new BlockPos(this)) == GDBiomes.crystal_plains ||
                        world.getBiome(new BlockPos(this)) == GDBiomes.mutant_agate_wildwood;
            case 1:
                return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                        world.getLight(blockpos) > 8 &&
                        world.getBiome(new BlockPos(this)) == GDBiomes.blue_agate_taiga ||
                        world.getBiome(new BlockPos(this)) == GDBiomes.mutant_agate_wildwood;
            case 2:
                return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                        world.getLight(blockpos) > 8 &&
                        world.getBiome(new BlockPos(this)) == GDBiomes.green_agate_jungle ||
                        world.getBiome(new BlockPos(this)) == GDBiomes.mutant_agate_wildwood;
            case 3:
                return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                        world.getLight(blockpos) > 8 &&
                        world.getBiome(new BlockPos(this)) == GDBiomes.purple_agate_swamp ||
                        world.getBiome(new BlockPos(this)) == GDBiomes.mutant_agate_wildwood;
            default:
                return false;

        }
    }

    @Override
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
    }
}
