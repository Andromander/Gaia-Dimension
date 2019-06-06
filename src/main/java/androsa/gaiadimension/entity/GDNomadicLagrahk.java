package androsa.gaiadimension.entity;

import androsa.gaiadimension.biomes.GDSaltDunes;
import androsa.gaiadimension.biomes.GDStaticWasteland;
import androsa.gaiadimension.biomes.GDVolcanicLands;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class GDNomadicLagrahk extends EntityCreature {
    private static final DataParameter<Integer> LAGRAHK_VARIANT = EntityDataManager.createKey(GDNomadicLagrahk.class, DataSerializers.VARINT);

    public GDNomadicLagrahk(World world) {
        super(world);

        this.setSize(1.5F, 4.0F);

        this.experienceValue = 10;
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(LAGRAHK_VARIANT, 0);
    }

    @Override
    public float getEyeHeight() {
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
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.5D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("LagrahkVariant", this.getEntityVariant());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setLagrahkVariant(compound.getInteger("LagrahkVariant"));
    }

    @Override
    public int getMaxSpawnedInChunk() {
        Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));

        if (biome instanceof GDSaltDunes || biome instanceof GDStaticWasteland || biome instanceof GDVolcanicLands) {
            return 4;
        } else {
            return 1;
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingData) {
        Object data = super.onInitialSpawn(difficulty, livingData);
        Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));

        if (biome instanceof GDSaltDunes) {
            setLagrahkVariant(1);
        } else if (biome instanceof GDStaticWasteland) {
            setLagrahkVariant(2);
        } else if (biome instanceof GDVolcanicLands) {
            setLagrahkVariant(3);
        } else {
            setLagrahkVariant(0);
        }

        return (IEntityLivingData)data;
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getMaterial().isSolid() &&
                world.getLight(blockpos) > 8 &&
                super.getCanSpawnHere();
    }
}
