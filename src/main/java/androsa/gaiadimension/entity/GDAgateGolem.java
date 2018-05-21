package androsa.gaiadimension.entity;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class GDAgateGolem extends EntityMob {

    public GDAgateGolem(World world) {
        super(world);
        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.3D, true));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 0.2D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, false));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.setDead();
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getBiome(new BlockPos(this)) == GDBiomes.pinkAgateForest;
    }
}
