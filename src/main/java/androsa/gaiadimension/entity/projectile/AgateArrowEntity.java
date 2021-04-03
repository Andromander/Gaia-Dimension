package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class AgateArrowEntity extends AbstractArrowEntity {

    public AgateArrowEntity(EntityType<? extends AgateArrowEntity> entity, World worldIn) {
        super(entity, worldIn);
        setBaseDamage(4.0D);
    }

    public AgateArrowEntity(World worldIn, double x, double y, double z) {
        super(ModEntities.AGATE_ARROW, x, y, z, worldIn);
    }

    public AgateArrowEntity(World worldIn, LivingEntity shooter) {
        super(ModEntities.AGATE_ARROW, shooter, worldIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.agate_arrow.get());
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
