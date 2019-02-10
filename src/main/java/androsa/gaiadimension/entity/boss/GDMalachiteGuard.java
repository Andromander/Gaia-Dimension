package androsa.gaiadimension.entity.boss;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class GDMalachiteGuard extends EntityMob {

    public GDMalachiteGuard(World world) {
        super(world);

        this.setSize(0.8F, 3.3F);

        this.experienceValue = 75;
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.5D));
    }

    @Override
    public float getEyeHeight() {
        return 3.0F;
    }

    //Keep this commented out until later
/*
    @Override
    public boolean isNonBoss() {
        return false;
    }
*/
}
