package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class GDBow extends ItemBow implements ModelRegisterCallback {

    public GDBow() {
        setCreativeTab(GDTabs.tabTool);
        setMaxDamage(425);
        addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            @Override
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    ItemStack itemStack = entityIn.getActiveItemStack();
                    return !itemStack.isEmpty() && itemStack.getItem() instanceof GDBow ? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F : 0.0F;
                }
            }
        });
    }

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == GDItems.agate_arrow;
    }
}
