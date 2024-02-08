package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AgateArrowEntity extends AbstractArrow {

    private static final ItemStack arrow = new ItemStack(ModItems.agate_arrow.get());

    public AgateArrowEntity(EntityType<AgateArrowEntity> entity, Level worldIn) {
        super(entity, worldIn, arrow);
        setBaseDamage(4.0D);
    }

    public AgateArrowEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.AGATE_ARROW.get(), x, y, z, worldIn, arrow);
    }

    public AgateArrowEntity(Level worldIn, LivingEntity shooter) {
        super(ModEntities.AGATE_ARROW.get(), shooter, worldIn, arrow);
    }
}
