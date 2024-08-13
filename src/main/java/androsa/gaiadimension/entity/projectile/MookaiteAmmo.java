package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MookaiteAmmo extends ThrowableItemProjectile {
    public MookaiteAmmo(EntityType<? extends MookaiteAmmo> entity, Level level) {
        super(entity, level);
    }

    public MookaiteAmmo(Level level, LivingEntity entity) {
        super(ModEntities.MOOKAITE_AMMO_BULLET.get(), entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModBlocks.ivory_mookaite.get().asItem();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 3.0F);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
