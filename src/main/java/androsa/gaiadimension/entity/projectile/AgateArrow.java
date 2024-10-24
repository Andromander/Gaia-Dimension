package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AgateArrow extends AbstractArrow {

    private final ItemStack arrow = new ItemStack(ModItems.agate_arrow.get());

    public AgateArrow(EntityType<AgateArrow> entity, Level worldIn) {
        super(entity, worldIn);
        setBaseDamage(4.0D);
    }

    public AgateArrow(Level worldIn, double x, double y, double z, ItemStack projectile) {
        super(ModEntities.AGATE_ARROW.get(), x, y, z, worldIn, projectile, null);
    }

    public AgateArrow(Level worldIn, LivingEntity shooter, ItemStack projectile) {
        super(ModEntities.AGATE_ARROW.get(), shooter, worldIn, projectile, null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return this.arrow;
    }
}
