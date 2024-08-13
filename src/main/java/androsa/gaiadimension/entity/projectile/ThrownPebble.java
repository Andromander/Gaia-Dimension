package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModParticles;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownPebble extends ThrowableItemProjectile {

    public ThrownPebble(EntityType<? extends ThrownPebble> entity, Level world) {
        super(entity, world);
    }

    public ThrownPebble(Level worldIn, LivingEntity throwerIn) {
        super(ModEntities.THROWN_PEBBLE.get(), throwerIn, worldIn);
    }

    public ThrownPebble(Level worldIn, double x, double y, double z) {
        super(ModEntities.THROWN_PEBBLE.get(), x, y, z, worldIn);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions iparticledata = this.getParticleData();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    private ParticleOptions getParticleData() {
        ItemStack itemstack = this.getItem();
        return itemstack.isEmpty() ? ModParticles.ITEM_PEBBLE.get() : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 3.0F);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.sturdy_pebble.get();
    }
}
