package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GDCommonGrowthSapper extends EntityCreature implements IAnimals {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/common_sapper");

    public GDCommonGrowthSapper(World world) {
        super(world);

        this.setSize(0.8F, 0.8F);
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

    @Override
    public boolean getCanSpawnHere() {
        return world.getBiome(new BlockPos(this)) == GDBiomes.pinkAgateForest ||
                world.getBiome(new BlockPos(this)) == GDBiomes.crystalPlains ||
                world.getBiome(new BlockPos(this)) == GDBiomes.mutantAgateWildwood;
    }

    @Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }

}
