package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SaltionEntity extends MonsterEntity {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/saltion");

    public SaltionEntity(EntityType<? extends SaltionEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 1 + rand.nextInt(3);
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.5D, false));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.25F;
    }

    public static boolean canSpawnHere(IWorld world, SpawnReason spawn, BlockPos pos) {
        return world.getDifficulty() != Difficulty.PEACEFUL &&
                isValidSpawn(world, spawn, pos);
    }

    public static boolean isValidSpawn(IWorld world, SpawnReason spawn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return spawn == SpawnReason.SPAWNER ||
                world.getBlockState(blockpos).getBlock() == ModBlocks.corrupt_grass && world.getLight(blockpos) > 8 && world.getBiome(blockpos) == ModBiomes.salt_dunes;
    }

    /*@Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }*/
}
