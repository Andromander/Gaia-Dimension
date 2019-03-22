package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.GDAgateArrowEntity;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GDAgateArrow extends ItemArrow implements ModelRegisterCallback {

    public GDAgateArrow() {
        this.setCreativeTab(GDTabs.tabTool);
    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        return new GDAgateArrowEntity(worldIn, shooter);
    }
}
