package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.registration.ModItems;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MookaiteConstructEntity extends PathfinderMob {

    private static final EntityDataAccessor<Optional<UUID>> OPALITE_COMPANION_UUID = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    public static final EntityDataAccessor<Integer> LEFT_HORN_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_HORN_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_EYE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_EYE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_SHOULDER_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_SHOULDER_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_ARM_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_ARM_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> LEFT_LEG_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> RIGHT_LEG_BRACE_TYPE = SynchedEntityData.defineId(MookaiteConstructEntity.class, EntityDataSerializers.INT);
    public static final Map<Integer, String> INT_TO_COLOR = Util.make(Maps.newHashMap(), (map) -> {
        map.put(0, "none");
        map.put(1, "scarlet");
        map.put(2, "auburn");
        map.put(3, "gold");
        map.put(4, "mauve");
        map.put(5, "beige");
        map.put(6, "ivory");
        map.put(7, "opalite");
    });

    public MookaiteConstructEntity(EntityType<? extends MookaiteConstructEntity> entity, Level level) {
        super(entity, level);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
                .add(Attributes.ARMOR, 1.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.5F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OPALITE_COMPANION_UUID, Optional.empty());
        this.entityData.define(LEFT_HORN_TYPE, 0);
        this.entityData.define(RIGHT_HORN_TYPE, 0);
        this.entityData.define(LEFT_EYE_TYPE, 0);
        this.entityData.define(RIGHT_EYE_TYPE, 0);
        this.entityData.define(LEFT_SHOULDER_TYPE, 0);
        this.entityData.define(RIGHT_SHOULDER_TYPE, 0);
        this.entityData.define(LEFT_ARM_BRACE_TYPE, 0);
        this.entityData.define(RIGHT_ARM_BRACE_TYPE, 0);
        this.entityData.define(LEFT_LEG_BRACE_TYPE, 0);
        this.entityData.define(RIGHT_LEG_BRACE_TYPE, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("OpaliteUUID")) {
            this.setOpaliteCompanion(tag.getUUID("OpaliteUUID"));
        }
        this.setPart(LEFT_HORN_TYPE, tag.getInt("LeftHornType"));
        this.setPart(RIGHT_HORN_TYPE, tag.getInt("RightHornType"));
        this.setPart(LEFT_EYE_TYPE, tag.getInt("LeftEyeType"));
        this.setPart(RIGHT_EYE_TYPE, tag.getInt("RightEyeType"));
        this.setPart(LEFT_SHOULDER_TYPE, tag.getInt("LeftShoulderType"));
        this.setPart(RIGHT_SHOULDER_TYPE, tag.getInt("RightShoulderType"));
        this.setPart(LEFT_ARM_BRACE_TYPE, tag.getInt("LeftArmBraceType"));
        this.setPart(RIGHT_ARM_BRACE_TYPE, tag.getInt("RightArmBraceType"));
        this.setPart(LEFT_LEG_BRACE_TYPE, tag.getInt("LeftLegBraceType"));
        this.setPart(RIGHT_LEG_BRACE_TYPE, tag.getInt("RightLegBraceType"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.getOpaliteCompanion() != null) {
            tag.putUUID("OpaliteUUID", this.getOpaliteCompanion());
        }
        tag.putInt("LeftHornType", getPart(LEFT_HORN_TYPE));
        tag.putInt("RightHornType", getPart(RIGHT_HORN_TYPE));
        tag.putInt("LeftEyeType", getPart(LEFT_EYE_TYPE));
        tag.putInt("RightEyeType", getPart(RIGHT_EYE_TYPE));
        tag.putInt("LeftShoulderType", getPart(LEFT_SHOULDER_TYPE));
        tag.putInt("RightShoulderType", getPart(RIGHT_SHOULDER_TYPE));
        tag.putInt("LeftArmBraceType", getPart(LEFT_ARM_BRACE_TYPE));
        tag.putInt("RightArmBraceType", getPart(RIGHT_ARM_BRACE_TYPE));
        tag.putInt("LeftLegBraceType", getPart(LEFT_LEG_BRACE_TYPE));
        tag.putInt("RightLegBraceType", getPart(RIGHT_LEG_BRACE_TYPE));
    }

    public void setOpaliteCompanion(UUID id) {
        this.entityData.set(OPALITE_COMPANION_UUID, Optional.ofNullable(id));
    }

    public UUID getOpaliteCompanion() {
        return this.entityData.get(OPALITE_COMPANION_UUID).orElse(null);
    }

    public boolean isPresent(EntityDataAccessor<Integer> part) {
        int value = this.getPart(part);
        return value > 0 && value <= 7;
    }

    public int getPart(EntityDataAccessor<Integer> part) {
        return entityData.get(part);
    }

    public void setPart(EntityDataAccessor<Integer> part, int value) {
        if (value < 0 || value > 7) {
            value = random.nextInt(7);
        }
        entityData.set(part, value);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.3D, 32.0F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawn, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
        this.setPart(LEFT_HORN_TYPE, random.nextInt(7));
        this.setPart(RIGHT_HORN_TYPE, random.nextInt(7));
        this.setPart(LEFT_EYE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_EYE_TYPE, random.nextInt(7));
        this.setPart(LEFT_SHOULDER_TYPE, random.nextInt(7));
        this.setPart(RIGHT_SHOULDER_TYPE, random.nextInt(7));
        this.setPart(LEFT_ARM_BRACE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_ARM_BRACE_TYPE, random.nextInt(7));
        this.setPart(LEFT_LEG_BRACE_TYPE, random.nextInt(7));
        this.setPart(RIGHT_LEG_BRACE_TYPE, random.nextInt(7));
        return super.finalizeSpawn(level, difficulty, spawn, data, tag);
    }

    public static boolean canSpawnHere(EntityType<MookaiteConstructEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).getItem() != ModItems.construct_charm.get()) {
            String output = this.getOpaliteCompanion() != null ? this.getOpaliteCompanion().toString() : "No companion set";
            System.out.println(output);
            return InteractionResult.sidedSuccess(this.level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    @Override
    public void die(DamageSource source) {
        if (ForgeHooks.onLivingDeath(this, source)) return;

        if (this.level instanceof ServerLevel level && this.getOpaliteCompanion() != null) {
            Entity entity = level.getEntity(this.getOpaliteCompanion());
            if (entity instanceof OpaliteContructEntity opalite) {
                opalite.setMookaiteCompanion(null);
            }
        }

        super.die(source);
    }
}
