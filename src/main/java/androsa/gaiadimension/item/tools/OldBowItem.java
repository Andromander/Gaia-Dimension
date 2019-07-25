package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

public class OldBowItem extends BowItem {

    public static final Predicate<ItemStack> ARROW = (stack) -> stack.getItem() == ModBlocks.agate_arrow;

    public OldBowItem() {
        super(new Properties().defaultMaxDamage(425).group(GaiaItemGroups.GAIA_TOOLS));

        this.addPropertyOverride(new ResourceLocation("pull"), (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return !(entity.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return ARROW;
    }
}
