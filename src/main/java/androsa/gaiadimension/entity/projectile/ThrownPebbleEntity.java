package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrownPebbleEntity extends ProjectileItemEntity {

    public ThrownPebbleEntity(EntityType<? extends ThrownPebbleEntity> entity, World world) {
        super(entity, world);
    }

    public ThrownPebbleEntity(World worldIn, LivingEntity throwerIn) {
        super(ModEntities.THROWN_PEBBLE, throwerIn, worldIn);
    }

    public ThrownPebbleEntity(World worldIn, double x, double y, double z) {
        super(ModEntities.THROWN_PEBBLE, x, y, z, worldIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.getParticleData();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(iparticledata, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData getParticleData() {
        ItemStack itemstack = this.getItemRaw();
        return itemstack.isEmpty() ? ModParticles.ITEM_PEBBLE : new ItemParticleData(ParticleTypes.ITEM, itemstack);
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.hurt(DamageSource.thrown(this, this.getOwner()), 3.0F);
        }

        if (!this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.sturdy_pebble.get();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
