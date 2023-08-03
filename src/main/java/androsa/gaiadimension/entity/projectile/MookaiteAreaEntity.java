package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.registry.registration.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class MookaiteAreaEntity extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(MookaiteAreaEntity.class, EntityDataSerializers.FLOAT);
    private int duration = 600;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    @Nullable
    private UUID companionUUID;
    @Nullable
    private UUID bonderUUID;

    public MookaiteAreaEntity(EntityType<? extends MookaiteAreaEntity> entity, Level level) {
        super(entity, level);
        this.noPhysics = true;
    }

    public MookaiteAreaEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOOKAITE_MAGIC_AREA.get(), level);
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_RADIUS, 3.0F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.tickCount = tag.getInt("Age");
        this.duration = tag.getInt("Duration");
        this.setRadius(tag.getFloat("Radius"));
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }
        if (tag.hasUUID("Companion")) {
            this.companionUUID = tag.getUUID("Companion");
        }
        if (tag.hasUUID("Bonder")) {
            this.bonderUUID = tag.getUUID("Bonder");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("Age", this.tickCount);
        tag.putInt("Duration", this.duration);
        tag.putFloat("Radius", this.getRadius());
        if (this.ownerUUID != null) {
            tag.putUUID("Owner", this.ownerUUID);
        }
        if (this.companionUUID != null) {
            tag.putUUID("Companion", this.companionUUID);
        }
        if (this.bonderUUID != null) {
            tag.putUUID("Bonder", this.bonderUUID);
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        if (DATA_RADIUS.equals(data)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(data);
    }

    @Override
    public void refreshDimensions() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        super.refreshDimensions();
        this.setPos(x, y, z);
    }

    public float getRadius() {
        return this.getEntityData().get(DATA_RADIUS);
    }

    public void setRadius(float radius) {
        if (!this.level().isClientSide()) {
            this.getEntityData().set(DATA_RADIUS, Mth.clamp(radius, 0.0F, 32.0F));
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int time) {
        this.duration = time;
    }

    @Override
    public void tick() {
        super.tick();
        float radius = this.getRadius();
        if (this.level().isClientSide) {
            int area = Mth.ceil((float)Math.PI * radius * radius);

            for(int j = 0; j < area; ++j) {
                float wave = this.random.nextFloat() * ((float)Math.PI * 2F);
                float dist = Mth.sqrt(this.random.nextFloat()) * radius;
                double x = this.getX() + (double)(Mth.cos(wave) * dist);
                double y = this.getY();
                double z = this.getZ() + (double)(Mth.sin(wave) * dist);
                double vx = (0.5D - this.random.nextDouble()) * 0.15D;
                double vy = 0.01D;
                double vz = (0.5D - this.random.nextDouble()) * 0.15D;

                this.level().addAlwaysVisibleParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, vx, vy, vz);
            }
        } else {
            if (this.tickCount >= this.duration) {
                this.discard();
                return;
            }

            if (this.tickCount % 5 == 0) {
                List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox(), (entity) ->
                        validateUUID(entity, this.ownerUUID) && validateUUID(entity, this.companionUUID) && validateUUID(entity, this.bonderUUID));

                if (!entities.isEmpty()) {
                    for(LivingEntity livingentity : entities) {
                        double x = livingentity.getX() - this.getX();
                        double z = livingentity.getZ() - this.getZ();
                        double dist = x * x + z * z;
                        if (dist <= (double)(radius * radius)) {
                            livingentity.hurt(this.level().damageSources().magic(), 2.0F);
                        }
                    }
                }
            }
        }
    }

    private boolean validateUUID(LivingEntity entity, UUID id) {
        if (id != null) {
            return !entity.getUUID().equals(id);
        }
        return false;
    }

    public void setOwner(@Nullable LivingEntity entity) {
        this.owner = entity;
        this.ownerUUID = entity == null ? null : entity.getUUID();

        if (entity instanceof MookaiteConstructEntity mookaite) {
            if (mookaite.getOpaliteCompanion() != null) {
                this.companionUUID = mookaite.getOpaliteCompanion();
            }
            if (mookaite.getBonder() != null) {
                this.bonderUUID = mookaite.getBonder();
            }
        }
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    public PushReaction getPistonPushReaction() {
        return PushReaction.IGNORE;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(this.getRadius() * 2.0F, 0.5F);
    }
}
