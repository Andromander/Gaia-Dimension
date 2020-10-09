package androsa.gaiadimension.entity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class BismuthUletrusEntity extends CreatureEntity {

    private static final DataParameter<Boolean> RESTING = EntityDataManager.createKey(BismuthUletrusEntity.class, DataSerializers.BOOLEAN);

    public BismuthUletrusEntity(EntityType<? extends BismuthUletrusEntity> entity, World world) {
        super(entity, world);
        experienceValue = 5;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(RESTING, false);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 120.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.8D));

    }

    public boolean getResting() {
        return this.dataManager.get(RESTING);
    }

    public void setResting(boolean rest) {
        this.dataManager.set(RESTING, rest);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setResting(compound.getBoolean("UletrusResting"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("UletrusResting", this.getResting());
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        this.setResting(false);
        return super.attackEntityFrom(source, amount);
    }

    public static boolean canSpawnHere(EntityType<BismuthUletrusEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        return spawn == SpawnReason.SPAWNER || world.getBlockState(pos.down()).canEntitySpawn(world, pos.down(), entity) && world.getLightFor(LightType.SKY, pos) > 8;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getResting()) {
            this.setMotion(getMotion().mul(0.0D, 0.0D, 0.0D));
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        BlockPos blockpos = this.getPosition();
        BlockPos blockpos1 = blockpos.down();

        if (this.getResting()) {
            if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
                if (rand.nextInt(1000) == 0) {
                    this.setResting(false);
                }
            } else {
                this.setResting(false);
            }
        } else {
            if (this.getMotion().getX() == 0 && this.getMotion().getZ() == 0) {
                if (this.rand.nextInt(1000) == 0 && this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
                    this.setResting(true);
                }
            }
        }
    }
}
