package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class GDCorruptSapper extends EntityMob {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/corrupt_sapper");

    public GDCorruptSapper(World world) {
        super(world);

        this.setSize(0.8F, 0.8F);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.corrupt_grass &&
                world.getLight(blockpos) > 8 &&
                world.getBiome(new BlockPos(this)) == GDBiomes.goldstonelands;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return GaiaDimension.CORRUPT;
    }

    @Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}
