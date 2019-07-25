package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

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

    public static boolean canSpawnHere(EntityType<MarkuzarPlantEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL &&
                isValidSpawn(world, spawn, pos);
    }

    public static boolean isValidSpawn(IWorld world, SpawnReason spawn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return spawn == SpawnReason.SPAWNER ||
                world.getBlockState(blockpos).getBlock() == ModBlocks.glitter_grass && world.getLight(blockpos) > 8 && world.getBiome(blockpos) == ModBiomes.green_agate_jungle;
    }
}
