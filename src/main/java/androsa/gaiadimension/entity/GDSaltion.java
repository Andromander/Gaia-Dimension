package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GDSaltion extends EntityMob {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/saltion");

    public GDSaltion(World world) {
        super(world);

        this.setSize(1.0F, 0.3F);

        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.5D, false));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, 0.5D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public float getEyeHeight() {
        return 0.25F;
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.salt &&
                world.getLight(blockpos) > 8 &&
                world.getBiome(new BlockPos(this)) == GDBiomes.salt_dunes;
    }

    @Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}
