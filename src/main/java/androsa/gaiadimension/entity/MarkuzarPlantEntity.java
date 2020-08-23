package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

//TODO: Random colours of the petals
public class MarkuzarPlantEntity extends CreatureEntity {

    public MarkuzarPlantEntity(EntityType<? extends MarkuzarPlantEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    public void knockBack(Entity entity, float distance, double x, double y) { }

    @Override
    public void move(MoverType type, Vec3d pos) {
        if (type == MoverType.PISTON) {
            super.move(type, pos);
        }
    }

    @Override
    public boolean canSpawn(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<MarkuzarPlantEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.down();
        return world.getBlockState(blockpos).canEntitySpawn(world, blockpos, entity) && world.getLightLevel(LightType.SKY, blockpos) > 8;
    }
}
