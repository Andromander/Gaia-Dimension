package androsa.gaiadimension.entity;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GDMarkuzarPlant extends EntityCreature implements IAnimals {

    public GDMarkuzarPlant(World world) {
        super(world);

        this.setSize(0.6F, 2.0F);
    }

    @Override
    protected final void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getBiome(new BlockPos(this)) == GDBiomes.greenAgateForest;
    }
}
