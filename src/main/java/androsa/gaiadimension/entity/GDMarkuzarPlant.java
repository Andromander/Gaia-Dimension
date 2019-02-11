package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GDMarkuzarPlant extends EntityCreature implements IAnimals {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/markuzar_plant");

    public GDMarkuzarPlant(World world) {
        super(world);

        this.setSize(0.6F, 2.0F);

        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D); //How far should this go?
    }

    @Override
    public void knockBack(Entity entity, float distance, double x, double y)
    {
    }

    @Override
    public void move(MoverType type, double x, double y, double z) {
        if (type == MoverType.PISTON) {
            super.move(type, x, y, z);
        }
    }


    @Override
    public boolean getCanSpawnHere() {
        int x = MathHelper.floor(this.posX);
        int y = MathHelper.floor(this.getEntityBoundingBox().minY);
        int z = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(x, y, z);

        return world.getBlockState(blockpos.down()).getBlock() == GDBlocks.glitter_grass &&
                world.getLight(blockpos) > 8 &&
                world.getBiome(new BlockPos(this)) == GDBiomes.green_agate_jungle;
    }

    @Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}
