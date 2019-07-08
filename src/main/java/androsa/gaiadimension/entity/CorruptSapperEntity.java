package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CorruptSapperEntity extends MonsterEntity {

    //public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/corrupt_sapper");

    public CorruptSapperEntity(EntityType<? extends CorruptSapperEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = (1 + rand.nextInt(3) * 2);
    }

    @Override
    protected final void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.70F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof LivingEntity) {
                int i;

                switch (this.world.getDifficulty()) {
                    case EASY:
                        i = 3;
                        break;
                    case NORMAL:
                        i = 6;
                        break;
                    case HARD:
                        i = 12;
                        break;
                    default:
                        i = 0;
                }

                if (i > 0) {
                    ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(ModEffects.goldstone_plague, i * 20, 0));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean canSpawnHere(IWorld world, SpawnReason spawn, BlockPos pos) {
        return world.getDifficulty() != Difficulty.PEACEFUL &&
                isValidSpawn(world, spawn, pos);
    }

    public static boolean isValidSpawn(IWorld world, SpawnReason spawn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return spawn == SpawnReason.SPAWNER ||
                world.getBlockState(blockpos).getBlock() == ModBlocks.corrupt_grass && world.getLight(blockpos) > 8 && world.getBiome(blockpos) == ModBiomes.goldstonelands;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return GaiaDimension.CORRUPT;
    }

    /*@Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }*/
}
