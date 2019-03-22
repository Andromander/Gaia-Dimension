package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.GDItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GDAgateArrowEntity extends EntityArrow {

    public GDAgateArrowEntity(World worldIn) {
        super(worldIn);

        setDamage(4.0D);
    }

    public GDAgateArrowEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public GDAgateArrowEntity(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(GDItems.agate_arrow);
    }
}
