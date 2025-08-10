package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModParticles;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class StaffAreaEffect extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(StaffAreaEffect.class, EntityDataSerializers.INT);
    private float radius = 2.0F;
    private int duration = 100;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    public StaffAreaEffect(EntityType<? extends StaffAreaEffect> entity, Level level) {
        super(entity, level);
    }

    public StaffAreaEffect(Level level, double x, double y, double z, int color) {
        this(ModEntities.STAFF_AREA_EFFECT.get(), level);
        this.setPos(x, y, z);
        this.setColor(color);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(COLOR, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.tickCount = tag.getInt("Age");
        this.duration = tag.getInt("Duration");
        this.radius = tag.getFloat("Radius");
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }
        this.setColor(tag.getInt("Color"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("Age", this.tickCount);
        tag.putInt("Duration", this.duration);
        tag.putFloat("Radius", this.radius);
        if (this.ownerUUID != null) {
            tag.putUUID("Owner", this.ownerUUID);
        }
        tag.putInt("Color", this.getColor());
    }

    public int getColor() {
        return this.entityData.get(COLOR);
    }

    public void setColor(int color) {
        this.entityData.set(COLOR, color);
    }

    @Override
    public void refreshDimensions() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        super.refreshDimensions();
        this.setPos(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
        float radius = 2.0F;
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
                ParticleOptions option = ColorParticleOption.create(ModParticles.MAGIC_STAFF_TRAIL.get(), this.getColor());

                this.level().addAlwaysVisibleParticle(option, x, y, z, vx, vy, vz);
            }
        } else {
            if (this.duration-- <= 0) {
                this.discard();
                return;
            }

            if (this.tickCount % 5 == 0) {
                List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox(), (entity) ->
                        validateUUID(entity, this.ownerUUID));

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

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
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
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel server) {
            Entity entity = server.getEntity(this.ownerUUID);
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
        return EntityDimensions.scalable(this.radius * 2.0F, 0.5F);
    }
}
