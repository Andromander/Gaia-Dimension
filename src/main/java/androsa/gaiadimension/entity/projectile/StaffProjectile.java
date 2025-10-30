package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.item.tools.GaiaStaffItem;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModParticles;
import androsa.gaiadimension.registry.registration.ModSounds;
import it.unimi.dsi.fastutil.doubles.DoubleDoubleImmutablePair;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

public class StaffProjectile extends AbstractHurtingProjectile {
    private static final EntityDataAccessor<GaiaStaffItem.Element> ELEMENT = SynchedEntityData.defineId(StaffProjectile.class, ModEntities.STAFF_ELEMENT.get());
    private static final EntityDataAccessor<GaiaStaffItem.Behavior> BEHAVIOR = SynchedEntityData.defineId(StaffProjectile.class, ModEntities.STAFF_BEHAVIOR.get());
    private static final EntityDataAccessor<GaiaStaffItem.Stat> STAT = SynchedEntityData.defineId(StaffProjectile.class, ModEntities.STAFF_STAT.get());
    private static final EntityDataAccessor<Integer> TIME_LIFE = SynchedEntityData.defineId(StaffProjectile.class, EntityDataSerializers.INT);

    private int bounceLife = 5;

    public StaffProjectile(EntityType<? extends StaffProjectile> entity, Level level) {
        super(entity, level);
    }

    public StaffProjectile(LivingEntity owner, Level level) {
        super(ModEntities.STAFF_PROJECTILE.get(), owner.getX(), owner.getEyeY() - 0.5F, owner.getZ(), level);
        this.setOwner(owner);
    }

    public StaffProjectile(Level level, double x, double y, double z) {
        super(ModEntities.STAFF_PROJECTILE.get(), x, y, z, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ELEMENT, GaiaStaffItem.Element.PHYSICAL);
        builder.define(BEHAVIOR, GaiaStaffItem.Behavior.BASIC);
        builder.define(STAT, GaiaStaffItem.Stat.STANDARD);
        builder.define(TIME_LIFE, 20);
    }

    public GaiaStaffItem.Element getElement() {
        return this.entityData.get(ELEMENT);
    }

    public void setElement(GaiaStaffItem.Element element) {
        this.entityData.set(ELEMENT, element);
    }

    public GaiaStaffItem.Behavior getBehavior() {
        return this.entityData.get(BEHAVIOR);
    }

    public void setBehavior(GaiaStaffItem.Behavior behavior) {
        this.entityData.set(BEHAVIOR, behavior);
    }

    public GaiaStaffItem.Stat getStat() {
        return this.entityData.get(STAT);
    }

    public void setStat(GaiaStaffItem.Stat stat) {
        this.entityData.set(STAT, stat);
        this.setTime(stat == GaiaStaffItem.Stat.SUSTAIN ? 40 : 20);
    }

    public int getTime() {
        return this.entityData.get(TIME_LIFE);
    }

    public void setTime(int time) {
        this.entityData.set(TIME_LIFE, time);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ColorParticleOption.create(ModParticles.MAGIC_STAFF_TRAIL.get(),this.getElement().getColor());
    }

    @Override
    public DoubleDoubleImmutablePair calculateHorizontalHurtKnockbackDirection(LivingEntity target, DamageSource source) {
        if (this.getStat() == GaiaStaffItem.Stat.FORCE) {
            double x = this.getDeltaMovement().x * 10.0D;
            double z = this.getDeltaMovement().z * 10.0D;
            return DoubleDoubleImmutablePair.of(x, z);
        }
        return super.calculateHorizontalHurtKnockbackDirection(target, source);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTime() <= 0) {
            this.discard();
        }
        this.setTime(this.getTime() - 1);
    }

    @Override
    protected float getBlockSpeedFactor() {
        return super.getBlockSpeedFactor();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            if (this.getBehavior() == GaiaStaffItem.Behavior.RICOCHET) {
                this.bounceProjectile(result);
            } else if (this.getBehavior() == GaiaStaffItem.Behavior.BLAST) {
                this.explodeProjectile();
            } else if (this.getBehavior() == GaiaStaffItem.Behavior.LINGER) {
                this.lingerProjectile();
            }else if (this.getBehavior() == GaiaStaffItem.Behavior.BURST) {
                this.splitProjectile();
            } else {
                this.expire();
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        entity.hurt(this.damageSources().source(this.getElement().getDamage()), this.getStat() == GaiaStaffItem.Stat.POWER ? 6.0F : 4.0F);

        if (entity instanceof LivingEntity living && this.getStat() == GaiaStaffItem.Stat.FORCE) {
            living.knockback(1.0F,
                    -Mth.sin(this.getYRot() * ((float)Math.PI / 180F)),
                    Mth.cos(this.getYRot() * ((float)Math.PI / 180F)));
        }
    }

    private void bounceProjectile(HitResult result) {
        Vec3 movement = this.getDeltaMovement();

        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityresult = (EntityHitResult) result;

            movement = new Vec3(movement.toVector3f().reflect(entityresult.getEntity().getDirection().getUnitVec3f()));
        } else if (result.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockresult = (BlockHitResult) result;

            movement = new Vec3(movement.toVector3f().reflect(blockresult.getDirection().getUnitVec3f()));
        }

        if (bounceLife-- <= 0) {
            this.expire();
        }

        this.setTime(this.getStat() == GaiaStaffItem.Stat.SUSTAIN ? 40 : 20);
        this.setDeltaMovement(movement);
        this.playSound(ModSounds.ENTITY_MAGIC_PROJECTILE_BOUNCE.get());
    }

    private void explodeProjectile() {
        List<Entity> aabb = level().getEntities(this, this.getBoundingBox().inflate(2.0F));

        for (Entity entity : aabb) {
            entity.hurt(this.damageSources().source(this.getElement().getDamage()), this.getStat() == GaiaStaffItem.Stat.POWER ? 8.0F : 6.0F);
        }

        if (!this.level().isClientSide()) {
            ParticleOptions options = ColorParticleOption.create(ModParticles.MAGIC_STAFF_TRAIL.get(), this.getElement().getColor());
            for (int i = 0; i < 10; i++) {
                ((ServerLevel)this.level()).sendParticles(options, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), 5, (this.random.nextDouble()) - 0.5D, this.random.nextDouble() * 0.5D, (this.random.nextDouble()) - 0.5D, 0.5D);
            }
        }

        this.playSound(ModSounds.ENTITY_MAGIC_PROJECTILE_EXPLODE.get());
        this.expire();
    }

    private void lingerProjectile() {
        StaffAreaEffect effect = new StaffAreaEffect(this.level(), this.getX(), this.getY(), this.getZ(), this.getElement().getColor());
        effect.setOwner(this.getOwner() instanceof LivingEntity living ? living : null);
        this.level().addFreshEntity(effect);
        this.expire();
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return entity.getType() != ModEntities.STAFF_PROJECTILE.get();
    }

    private void splitProjectile() {
        for (int i = 0; i < 5; i++) {
            Vec3 looking = this.getLookAngle().reverse().normalize().scale(-1.5D);
            StaffProjectile split = new StaffProjectile(this.level(), this.getX() - looking.x(), this.getY() - looking.y(), this.getZ() - looking.z());
            split.setElement(this.getElement());
            split.setBehavior(GaiaStaffItem.Behavior.BASIC);
            split.setStat(this.getStat());

            Vector3f vector3f;
            float angle = 72.0F * i;
            Vec3 vec3 = this.position();
            vector3f = vec3.yRot((float)Math.toRadians(angle)).toVector3f().normalize();
            float vel = vec3.toVector3f().normalize().length();
            this.level().addFreshEntity(split);
            split.shoot(vector3f.x(), vector3f.y(), vector3f.z(), vel, 1.0F);
        }

        this.expire();
    }

    private void expire() {
        this.playSound(ModSounds.ENTITY_MAGIC_PROJECTILE_BREAK.get());
        this.discard();
    }
}
