package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.GDItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDThrownPebble extends EntityThrowable {

    public GDThrownPebble(World worldIn) {
        super(worldIn);
    }

    public GDThrownPebble(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, Item.getIdFromItem(GDItems.sturdy_pebble));
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            int i = 3;
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
