package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GDAgateGolem extends EntityMob {

    public GDAgateGolem(World world) {
        super(world);

        this.setSize(1.2F, 2.7F);

        this.experienceValue = 15;
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
    @SuppressWarnings("unchecked")
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.3D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.3D, 32.0F));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.3D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGolem.class, false));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.world.setEntityState(this, (byte)4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(6 + this.rand.nextInt(15)));

        if (flag) {
            entityIn.motionY += 0.4D;
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                world.getLight(blockpos) > 8 &&
                world.getBiome(new BlockPos(this)) == GDBiomes.pink_agate_forest;
    }
}
