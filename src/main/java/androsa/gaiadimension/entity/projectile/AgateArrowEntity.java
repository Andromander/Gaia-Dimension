package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class AgateArrowEntity extends AbstractArrow {

    public AgateArrowEntity(EntityType<? extends AgateArrowEntity> entity, Level worldIn) {
        super(entity, worldIn);
        setBaseDamage(4.0D);
    }

    public AgateArrowEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.AGATE_ARROW.get(), x, y, z, worldIn);
    }

    public AgateArrowEntity(Level worldIn, LivingEntity shooter) {
        super(ModEntities.AGATE_ARROW.get(), shooter, worldIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.agate_arrow.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
