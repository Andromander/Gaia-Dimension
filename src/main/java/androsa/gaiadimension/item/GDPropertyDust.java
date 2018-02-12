package androsa.gaiadimension.item;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GDPropertyDust extends Item implements ModelRegisterCallback {

    public GDPropertyDust() {
        this.setCreativeTab(GDTabs.tabItem);
    }

    @SideOnly(Side.CLIENT)
    public static NBTBase getExplosionTag(ItemStack stack, String key) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("Property");

            if (nbttagcompound != null) {
                return nbttagcompound.getTag(key);
            }
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbttagcompound = stack.getTagCompound().getCompoundTag("Explosion");

            if (nbttagcompound != null) {
                addPropertyInfo(nbttagcompound, tooltip);
            }
        }
    }

    public static void addPropertyInfo(NBTTagCompound nbt, List<String> tooltip) {
        byte b0 = nbt.getByte("Type");

        if (b0 >= 0 && b0 <= 4) {
            tooltip.add(I18n.format("item.fireworksCharge.type." + b0).trim());
        } else {
            tooltip.add(I18n.format("item.fireworksCharge.type").trim());
        }


        boolean flag1 = nbt.getBoolean("Trail");

        if (flag1) {
            tooltip.add(I18n.format("item.fireworksCharge.trail"));
        }

        boolean flag2 = nbt.getBoolean("Flicker");

        if (flag2) {
            tooltip.add(I18n.format("item.fireworksCharge.flicker"));
        }
    }
}
