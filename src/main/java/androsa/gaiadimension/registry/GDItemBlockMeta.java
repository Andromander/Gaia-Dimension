package androsa.gaiadimension.registry;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GDItemBlockMeta extends ItemBlock {
    private boolean appendNumber = true;

    public GDItemBlockMeta(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    public GDItemBlockMeta setAppend(boolean doAppend) {
        this.appendNumber = doAppend;
        return this;
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }

    @Override
    public String getTranslationKey(ItemStack itemstack) {
        if (appendNumber) {
            int meta = itemstack.getItemDamage();
            return (new StringBuilder()).append(super.getTranslationKey()).append(".").append(meta).toString();
        } else return super.getTranslationKey();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
    }
}
