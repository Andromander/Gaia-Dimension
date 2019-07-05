package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.GaiaEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AgateArrowEntity extends AbstractArrowEntity {

    public AgateArrowEntity(EntityType<? extends AgateArrowEntity> entity, World worldIn) {
        super(entity, worldIn);
        setDamage(4.0D);
    }

    public AgateArrowEntity(World worldIn, double x, double y, double z) {
        super(GaiaEntities.AGATE_ARROW, x, y, z, worldIn);
    }

    public AgateArrowEntity(World worldIn, LivingEntity shooter) {
        super(GaiaEntities.AGATE_ARROW, shooter, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.agate_arrow);
    }
}
