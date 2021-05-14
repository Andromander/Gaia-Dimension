package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModItems;
import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

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

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    @Override
    public void aiStep() {
        if (this.isSunBurnTick()) {
            this.setSecondsOnFire(8);
        }

        super.aiStep();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_ARCHAIC_WARRIOR_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ENTITY_ARCHAIC_WARRIOR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_ARCHAIC_WARRIOR_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.ENTITY_ARCHAIC_WARRIOR_STEP, 0.15F, 1.0F);
    }

    @Override
    public CreatureAttribute getMobType() {
        return GaiaDimensionMod.GAIAN;
    }

    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(difficulty);

        if (this.random.nextFloat() < (this.level.getDifficulty() == Difficulty.NORMAL ? 0.05F : 0.01F)) {
            this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.sugilite_sword.get()));
        }
    }

    @Override
    public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.74F;
    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        this.populateDefaultEquipmentSlots(difficultyIn);
        this.populateDefaultEquipmentEnchantments(difficultyIn);

        return spawnDataIn;
    }
}
