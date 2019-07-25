package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ArchaicWarriorEntity extends MonsterEntity {

    public ArchaicWarriorEntity(EntityType<? extends ArchaicWarriorEntity> entity, World worldIn) {
        super(entity, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.8D, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.8D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    }

    @Override
    public void livingTick() {
        if (this.world.isDaytime() && !this.world.isRemote && this.shouldBurnInDay()) {
            float f = this.getBrightness();

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canBlockSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                this.setFire(8);
            }
        }

        super.livingTick();
    }

    protected boolean shouldBurnInDay() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return GaiaDimensionMod.GAIAN;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);

        if (this.rand.nextFloat() < (this.world.getDifficulty() == Difficulty.NORMAL ? 0.05F : 0.01F)) {
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModBlocks.sugilite_sword));
        }
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.74F;
    }

    @Override
    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEnchantmentBasedOnDifficulty(difficultyIn);

        return spawnDataIn;
    }
}
