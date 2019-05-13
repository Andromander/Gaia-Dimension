package androsa.gaiadimension.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GDBismuthUletrus extends EntityCreature implements IAnimals {

    private static final DataParameter<Boolean> RESTING = EntityDataManager.createKey(GDBismuthUletrus.class, DataSerializers.BOOLEAN);

    public GDBismuthUletrus(World world) {
        super(world);
        setSize(2.0F, 1.8F);
        experienceValue = 5;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(RESTING, false);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D));

    }

    public boolean getResting() {
        return this.dataManager.get(RESTING);
    }

    public void setResting(boolean rest) {
        this.dataManager.set(RESTING, rest);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setResting(compound.getBoolean("UletrusResting"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("UletrusResting", this.getResting());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        this.setResting(false);
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.getResting()) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.down();

        if (this.getResting()) {
            if (this.world.getBlockState(blockpos1).isNormalCube()) {
                if (rand.nextInt(1000) == 0) {
                    this.setResting(false);
                }
            } else {
                this.setResting(false);
            }
        } else {
            if (this.motionX == 0 && this.motionZ == 0) {
                if (this.rand.nextInt(1000) == 0 && this.world.getBlockState(blockpos1).isNormalCube()) {
                    this.setResting(true);
                }
            }
        }
    }
}
