package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class RuggedLurmorusEntity extends CreatureEntity {

    public RuggedLurmorusEntity(EntityType<? extends RuggedLurmorusEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 5;
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, AncientLagrahkEntity.class, 10.0F, 0.35D, 0.4D));
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 7.6F;
    }

    public static boolean canSpawnHere(EntityType<RuggedLurmorusEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).getBlock() == ModBlocks.glitter_grass && world.getLightSubtracted(pos, 0) > 8;
    }
}
