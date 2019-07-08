package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(func_213887_n(), this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData func_213887_n() {
        ItemStack itemstack = this.func_213882_k();
        return new ItemParticleData(ParticleTypes.ITEM, itemstack);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 3.0F);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected Item func_213885_i() {
        return ModItems.sturdy_pebble;
    }
}
