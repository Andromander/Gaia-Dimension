package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class AgateGolemEntity extends MonsterEntity {

    public AgateGolemEntity(EntityType<? extends AgateGolemEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 15;
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3D, true));
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.3D, 32.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GolemEntity.class, false));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.world.setEntityState(this, (byte)4);
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(6 + this.rand.nextInt(15)));

        if (flag) {
            entityIn.setMotion(entityIn.getMotion().add(0.0D, 0.4D, 0.0D));
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public static boolean canSpawnHere(IWorld world, SpawnReason spawn, BlockPos pos) {
        return world.getDifficulty() != Difficulty.PEACEFUL &&
                isValidSpawn(world, spawn, pos);
    }

    public static boolean isValidSpawn(IWorld world, SpawnReason spawn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return spawn == SpawnReason.SPAWNER ||
                world.getBlockState(blockpos).getBlock() == ModBlocks.glitter_grass && world.getLight(blockpos) > 8 && world.getBiome(blockpos) == ModBiomes.pink_agate_forest;
    }
}
