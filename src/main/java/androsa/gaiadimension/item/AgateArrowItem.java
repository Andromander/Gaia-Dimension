package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AgateArrowItem extends ArrowItem {

    public AgateArrowItem() {
        super(new Properties().group(GaiaItemGroups.GAIA_TOOLS));
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity entity) {
        return new AgateArrowEntity(worldIn, entity);
    }
}
