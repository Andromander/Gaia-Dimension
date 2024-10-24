package androsa.gaiadimension.entity;

import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.item.ConstructKitItem;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModSounds;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.neoforged.neoforge.common.CommonHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class OpaliteContruct extends PathfinderMob {

    private static final EntityDataAccessor<Optional<UUID>> BOND_CREATOR_UUID = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Optional<UUID>> MOOKAITE_COMPANION_UUID = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<CompoundTag> CONSTRUCT_KIT_DATA = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.COMPOUND_TAG);
    private static final EntityDataAccessor<Boolean> IS_CONSTRUCTING = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> OPALITE_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SCARLET_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> AUBURN_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GOLD_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MAUVE_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BEIGE_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IVORY_STACK = SynchedEntityData.defineId(OpaliteContruct.class, EntityDataSerializers.INT);
    private static final Map<Item, EntityDataAccessor<Integer>> BLOCK_TO_DATA = ImmutableMap.of(
            ModBlocks.scarlet_mookaite.get().asItem(), SCARLET_STACK,
            ModBlocks.auburn_mookaite.get().asItem(), AUBURN_STACK,
            ModBlocks.gold_mookaite.get().asItem(), GOLD_STACK,
            ModBlocks.mauve_mookaite.get().asItem(), MAUVE_STACK,
            ModBlocks.beige_mookaite.get().asItem(), BEIGE_STACK,
            ModBlocks.ivory_mookaite.get().asItem(), IVORY_STACK);
    private static final Map<ConstructKitItem.Color, EntityDataAccessor<Integer>> COLOR_TO_DATA = ImmutableMap.of(
            ConstructKitItem.Color.SCARLET, SCARLET_STACK,
            ConstructKitItem.Color.AUBURN, AUBURN_STACK,
            ConstructKitItem.Color.GOLD, GOLD_STACK,
            ConstructKitItem.Color.MAUVE, MAUVE_STACK,
            ConstructKitItem.Color.BEIGE, BEIGE_STACK,
            ConstructKitItem.Color.IVORY, IVORY_STACK);

    public OpaliteContruct(EntityType<? extends OpaliteContruct> entity, Level level) {
        super(entity, level);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ARMOR, 0.5D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BOND_CREATOR_UUID, Optional.empty());
        builder.define(MOOKAITE_COMPANION_UUID, Optional.empty());
        builder.define(CONSTRUCT_KIT_DATA, new CompoundTag());
        builder.define(IS_CONSTRUCTING, false);
        builder.define(OPALITE_STACK, 0);
        builder.define(SCARLET_STACK, 0);
        builder.define(AUBURN_STACK, 0);
        builder.define(GOLD_STACK, 0);
        builder.define(MAUVE_STACK, 0);
        builder.define(BEIGE_STACK, 0);
        builder.define(IVORY_STACK, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setConstructing(tag.getBoolean("IsConstructing"));
        this.setOpaliteAmount(tag.getInt("OpaliteStack"));
        this.setMookaiteAmount(SCARLET_STACK, tag.getInt("ScarletStack"));
        this.setMookaiteAmount(AUBURN_STACK, tag.getInt("AuburnStack"));
        this.setMookaiteAmount(GOLD_STACK, tag.getInt("GoldStack"));
        this.setMookaiteAmount(MAUVE_STACK, tag.getInt("MauveStack"));
        this.setMookaiteAmount(BEIGE_STACK, tag.getInt("BeigeStack"));
        this.setMookaiteAmount(IVORY_STACK, tag.getInt("IvoryStack"));
        if (tag.hasUUID("BonderUUID")) {
            this.setBonder(tag.getUUID("BonderUUID"));
        }
        if (tag.hasUUID("MookaiteUUID")) {
            this.setMookaiteCompanion(tag.getUUID("MookaiteUUID"));
        }
        if (tag.contains("ConstructKit", 10)) {
            this.setKitData(tag.getCompound("ConstructKit"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("IsConstructing", this.isConstructing());
        tag.putInt("OpaliteStack", this.getOpaliteAmount());
        tag.putInt("ScarletStack", this.getMookaiteAmount(SCARLET_STACK));
        tag.putInt("AuburnStack", this.getMookaiteAmount(AUBURN_STACK));
        tag.putInt("GoldStack", this.getMookaiteAmount(GOLD_STACK));
        tag.putInt("MauveStack", this.getMookaiteAmount(MAUVE_STACK));
        tag.putInt("BeigeStack", this.getMookaiteAmount(BEIGE_STACK));
        tag.putInt("IvoryStack", this.getMookaiteAmount(IVORY_STACK));
        if (this.getMookaiteCompanion() != null) {
            tag.putUUID("MookaiteUUID", this.getMookaiteCompanion());
        }
        if (this.getBonder() != null) {
            tag.putUUID("BonderUUID", this.getBonder());
        }
        if (!this.getKitData().isEmpty()) {
            tag.put("ConstructKit", this.getKitData());
        }
    }

    public void setBonder(UUID id) {
        this.entityData.set(BOND_CREATOR_UUID, Optional.ofNullable(id));
    }

    public UUID getBonder() {
        return this.entityData.get(BOND_CREATOR_UUID).orElse(null);
    }

    public void setMookaiteCompanion(UUID id) {
        this.entityData.set(MOOKAITE_COMPANION_UUID, Optional.ofNullable(id));
    }

    public UUID getMookaiteCompanion() {
        return this.entityData.get(MOOKAITE_COMPANION_UUID).orElse(null);
    }

    public CompoundTag getKitData() {
        return entityData.get(CONSTRUCT_KIT_DATA);
    }

    public void setKitData(CompoundTag tag) {
        entityData.set(CONSTRUCT_KIT_DATA, tag);
    }

    public boolean isConstructing() {
        return entityData.get(IS_CONSTRUCTING);
    }

    public void setConstructing(boolean flag) {
        entityData.set(IS_CONSTRUCTING, flag);
    }

    public boolean validateStacks(ConstructKitItem.Kit kit, ConstructKitItem.Color color) {
        boolean flag = false;

        switch (kit) {
            case REPAIR -> flag = this.getOpaliteAmount() >= 10;
            case AUGMENT -> flag = this.getOpaliteAmount() >= 5 && this.getMookaiteAmount(COLOR_TO_DATA.get(color)) >= 10; //TODO: No?
            case REPLACE -> flag = this.getMookaiteAmount(COLOR_TO_DATA.get(color)) >= 5; //TODO: No?
        }

        return flag;
    }

    public boolean validateKit(ConstructKitItem.Kit kit, ConstructKitItem.Part part, ConstructKitItem.Color color) {
        boolean flag = false;
        MookaiteConstruct mookaite = this.getFollowing();
        if (mookaite != null) {
            switch (kit) {
                //Must not be Opalite or missing
                case REPAIR -> flag = mookaite.getPart(part.getPart()) != MookaitePartType.OPALITE && mookaite.getPart(part.getPart()).isPresent();
                //Must be missing
                case AUGMENT -> flag = !mookaite.getPart(part.getPart()).isPresent();
                //Must exist and not the same colour
                case REPLACE -> flag = mookaite.getPart(part.getPart()).isPresent() && mookaite.getPart(part.getPart()) != color.getPartColor();
            }
        }

        return flag;
    }

    public boolean validateActivity() {
        if (this.getFollowing() != null) {
            if (this.distanceToSqr(this.getFollowing()) < 100.0D) {
                return this.getFollowing().getTarget() == null;
            }
        }
        return false;
    }

    public void writeKitData(ConstructKitItem.Kit kit, ConstructKitItem.Part part, ConstructKitItem.Color color) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("KitID", kit.ordinal());
        tag.putInt("PartID", part.ordinal());
        if (color != null) tag.putInt("ColorID", color.ordinal());
        this.setKitData(tag);
    }

    public void clearKitData() {
        CompoundTag tag = new CompoundTag();
        this.setKitData(tag);
    }

    public void setOpaliteAmount(int amount) {
        this.entityData.set(OPALITE_STACK, amount);
    }

    public int getOpaliteAmount() {
        return this.entityData.get(OPALITE_STACK);
    }

    public void setMookaiteAmount(EntityDataAccessor<Integer> mookaite, int amount) {
        this.entityData.set(mookaite, amount);
    }

    public int getMookaiteAmount(EntityDataAccessor<Integer> mookaite) {
        return this.entityData.get(mookaite);
    }

    @Nullable
    public MookaiteConstruct getFollowing() {
        if (getMookaiteCompanion() != null && this.level() instanceof ServerLevel server) {
            Entity entity = server.getEntity(getMookaiteCompanion());
            if (entity instanceof MookaiteConstruct) {
                return (MookaiteConstruct) entity;
            }
        }
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RepairCompanionGoal(this));
        this.goalSelector.addGoal(2, new FollowCompanionGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.3D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_OPALITE_CONSTRUCT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_OPALITE_CONSTRUCT_DEATH.get();
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource source, boolean player) {
        super.dropCustomDeathLoot(level, source, player);

        if (getOpaliteAmount() > 0) {
            ItemStack opalite = new ItemStack(ModItems.opalite.get(), this.getOpaliteAmount());
            this.spawnAtLocation(opalite);
        }
        for (Map.Entry<Item, EntityDataAccessor<Integer>> entry : BLOCK_TO_DATA.entrySet()) {
            ItemStack mookaite = new ItemStack(entry.getKey(), this.getMookaiteAmount(entry.getValue()));
            this.spawnAtLocation(mookaite);
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();

        /*
         * Opalite: add to the Opalite Stack.
         * Mookaite (all colours): add to the Mookaite Stack.
         * Kits: Check any of the stacks if there's enough of each item. If there's enough of the required stacks, begin the building.
         */
        if (this.getBonder() != null && player.getUUID().equals(this.getBonder())) {
            if (stack.is(ModItems.opalite.get())) {
                if (this.getOpaliteAmount() >= 10) {
                    player.displayClientMessage(Component.translatable("gaiadimension.opalite_construct.too_many_opalite"), true);
                    return InteractionResult.PASS;
                } else {
                    this.setOpaliteAmount(this.getOpaliteAmount() + 1);
                    stack.shrink(1);
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                }
            }

            if (BLOCK_TO_DATA.containsKey(item)) {
                EntityDataAccessor<Integer> data = BLOCK_TO_DATA.get(item);
                if (this.getMookaiteAmount(data) >= 10) {
                    player.displayClientMessage(Component.translatable("gaiadimension.opalite_construct.too_many_mookaite"), true);
                    return InteractionResult.PASS;
                } else {
                    this.setMookaiteAmount(data, this.getMookaiteAmount(data) + 1);
                    stack.shrink(1);
                    return InteractionResult.sidedSuccess(this.level().isClientSide());
                }
            }
        }

        return super.mobInteract(player, hand);
    }

    public static boolean canSpawnHere(EntityType<OpaliteContruct> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        return spawn == MobSpawnType.SPAWNER || world.getBlockState(pos.below()).isValidSpawn(world, pos.below(), entity) && world.getRawBrightness(pos, 0) > 8;
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.getMookaiteCompanion() != null;
    }

    @Override
    public void die(DamageSource source) {
        if (CommonHooks.onLivingDeath(this, source)) return;

        if (this.level() instanceof ServerLevel level && this.getMookaiteCompanion() != null) {
            Entity entity = level.getEntity(this.getMookaiteCompanion());
            if (entity instanceof MookaiteConstruct mookaite) {
                mookaite.setOpaliteCompanion(null);
                mookaite.setBonder(null);
                mookaite.setConstructing(false);
            }
        }

        super.die(source);
    }

    static class FollowCompanionGoal extends Goal {
        private final OpaliteContruct opalite;
        private MookaiteConstruct mookaite;
        private final LevelAccessor world;
        private final double followSpeed = 0.4D;
        private final PathNavigation navigator;
        private int timeToRecalcPath;
        private final float maxDist = 2.0F;
        private final float minDist = 10.0F;
        private float oldWaterCost;

        public FollowCompanionGoal(OpaliteContruct entity) {
            this.opalite = entity;
            this.world = entity.level();
            this.navigator = entity.getNavigation();
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            MookaiteConstruct mookaite = this.opalite.getFollowing();
            if (mookaite == null) {
                return false;
            } else if (mookaite.isSpectator()) {
                return false;
            } else if (this.opalite.distanceToSqr(mookaite) < (double)(this.minDist * this.minDist)) {
                return false;
            } else {
                this.mookaite = mookaite;
                return true;
            }
        }

        public boolean canContinueToUse() {
            return !this.navigator.isDone() && this.opalite.distanceToSqr(this.mookaite) > (double) (this.maxDist * this.maxDist);
        }

        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = this.opalite.getPathfindingMalus(PathType.WATER);
            this.opalite.setPathfindingMalus(PathType.WATER, 0.0F);
        }

        @Override
        public void stop() {
            this.mookaite = null;
            this.navigator.stop();
            this.opalite.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
        }

        @Override
        public void tick() {
            this.opalite.getLookControl().setLookAt(this.mookaite, 10.0F, (float)this.opalite.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                if (!this.opalite.isLeashed() && !this.opalite.isPassenger()) {
                    if (this.opalite.distanceToSqr(this.mookaite) >= 144.0D) {
                        this.tryTeleport();
                    } else {
                        this.navigator.moveTo(this.mookaite, this.followSpeed);
                    }
                }
            }
        }

        private void tryTeleport() {
            BlockPos guardpos = new BlockPos(mookaite.blockPosition());

            for(int chance = 0; chance < 10; ++chance) {
                int rx = this.getRandomInt(-3, 3);
                int ry = this.getRandomInt(-1, 1);
                int rz = this.getRandomInt(-3, 3);
                boolean teleport = this.tryTeleportTo(guardpos.getX() + rx, guardpos.getY() + ry, guardpos.getZ() + rz);
                if (teleport) {
                    return;
                }
            }
        }

        private boolean tryTeleportTo(int x, int y, int z) {
            if (Math.abs((double)x - this.mookaite.getX()) < 2.0D && Math.abs((double)z - this.mookaite.getZ()) < 2.0D) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
                return false;
            } else {
                this.opalite.moveTo((float)x + 0.5F, y, (float)z + 0.5F, this.opalite.getYRot(), this.opalite.getXRot());
                this.navigator.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            PathType nodeType = WalkNodeEvaluator.getPathTypeStatic(this.opalite, pos.mutable());
            if (nodeType != PathType.WALKABLE) {
                return false;
            } else {
                BlockState state = this.world.getBlockState(pos.below());
                if (state.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos posDown = pos.subtract(this.opalite.blockPosition());
                    return this.world.noCollision(this.opalite, this.opalite.getBoundingBox().move(posDown));
                }
            }
        }

        private int getRandomInt(int min, int max) {
            return this.opalite.getRandom().nextInt(max - min + 1) + min;
        }
    }

    static class RepairCompanionGoal extends Goal {

        private final OpaliteContruct opalite;
        private MookaiteConstruct mookaite;
        private int repairTime;
        private boolean isDone;

        public RepairCompanionGoal(OpaliteContruct entity) {
            this.opalite = entity;
            this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return !this.opalite.getKitData().isEmpty() && this.opalite.getMookaiteCompanion() != null
                    && this.opalite.getFollowing() != null && this.opalite.distanceToSqr(this.opalite.getFollowing()) <= 100.0D;
        }

        @Override
        public boolean canContinueToUse() {
            return !isDone && this.opalite.getMookaiteCompanion() != null;
        }

        @Override
        public void start() {
            super.start();
            this.mookaite = this.opalite.getFollowing();
            this.mookaite.setConstructing(true);
            this.repairTime = 10;
            this.isDone = false;
        }

        @Override
        public void stop() {
            super.stop();
            this.mookaite.setConstructing(false);
            this.opalite.clearKitData();
            this.opalite.setConstructing(false);
        }

        @Override
        public void tick() {
            if (this.opalite.level() instanceof ServerLevel server) {
                if (server.getEntity(this.opalite.getMookaiteCompanion()) instanceof MookaiteConstruct) {
                    opalite.getNavigation().moveTo(mookaite, 0.5D);
                    opalite.getLookControl().setLookAt(mookaite);
                    if (opalite.getNavigation().isDone()) {
                        if (this.repairTime == 10) {
                            this.opalite.setConstructing(true);
                            this.opalite.playSound(SoundEvents.ANVIL_USE);
                        }
                        if (this.repairTime == 0) {
                            ConstructKitItem.Kit kit = ConstructKitItem.Kit.values()[this.opalite.getKitData().getInt("KitID")];
                            MookaiteConstruct.MookaitePart part = ConstructKitItem.Part.values()[this.opalite.getKitData().getInt("PartID")].getPart();
                            ConstructKitItem.Color kitcolor = ConstructKitItem.Color.values()[this.opalite.getKitData().getInt("ColorID")];
                            MookaitePartType color = kitcolor.getPartColor();

                            if (kit == ConstructKitItem.Kit.REPAIR) {
                                color = MookaitePartType.OPALITE;
                            }

                            //Subtract materials
                            switch (kit) {
                                case REPAIR -> opalite.setOpaliteAmount(opalite.getOpaliteAmount() - 10);
                                case AUGMENT -> {
                                    opalite.setOpaliteAmount(opalite.getOpaliteAmount() - 5);
                                    opalite.setMookaiteAmount(COLOR_TO_DATA.get(kitcolor), opalite.getMookaiteAmount(COLOR_TO_DATA.get(kitcolor)) - 10); //TODO: No?
                                }
                                case REPLACE -> opalite.setMookaiteAmount(COLOR_TO_DATA.get(kitcolor), opalite.getMookaiteAmount(COLOR_TO_DATA.get(kitcolor)) - 5); //TODO: No?
                            }
                            //Set part
                            mookaite.setPart(part, color);
                            this.isDone = true;
                        }
                        repairTime--;
                    }

                }
            }
        }
    }
}
