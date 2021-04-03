package androsa.gaiadimension.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

//TODO: Random colours of the petals
public class MarkuzarPlantEntity extends CreatureEntity {

    public MarkuzarPlantEntity(EntityType<? extends MarkuzarPlantEntity> entity, World world) {
        super(entity, world);
        this.xpReward = 1 + random.nextInt(3);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    public void knockback(float distance, double x, double y) { }

    @Override
    public void move(MoverType type, Vector3d pos) {
        if (type == MoverType.PISTON) {
            super.move(type, pos);
        }
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        return true;
    }

    public static boolean canSpawnHere(EntityType<MarkuzarPlantEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.below();
        return world.getBlockState(blockpos).isValidSpawn(world, blockpos, entity) && world.getBrightness(LightType.SKY, blockpos) > 8;
    }
}
