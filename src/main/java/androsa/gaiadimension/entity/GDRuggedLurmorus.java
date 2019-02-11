package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GDRuggedLurmorus extends EntityCreature implements IAnimals {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/rugged_lurmorus");

    public GDRuggedLurmorus(World world) {
        super(world);

        this.setSize(3.5F, 8.0F);

        this.experienceValue = 5;
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIWander(this, 0.3D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, GDAncientLagrahk.class, 10.0F, 0.35D, 0.4D));

    }

    @Override
    public float getEyeHeight() {
        return 7.6F;
    }

    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                world.getLight(blockpos) > 8 &&
                world.getBiome(new BlockPos(this)) == GDBiomes.fossil_woodland;
    }

    @Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}
