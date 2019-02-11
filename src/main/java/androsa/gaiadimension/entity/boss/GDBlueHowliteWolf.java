package androsa.gaiadimension.entity.boss;

import androsa.gaiadimension.entity.GDHowliteWolf;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GDBlueHowliteWolf extends EntityMob implements IMob {

    public GDBlueHowliteWolf(World world) {
        super(world);

        this.setSize(1.2F, 2.2F);

        this.experienceValue = 30;
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.9D);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, GDHowliteWolf.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    @Override
    public float getEyeHeight() {
        return 2.1F;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    //Keep this commented out until later
/*
    @Override
    public boolean isNonBoss() {
        return false;
    }
*/
}
