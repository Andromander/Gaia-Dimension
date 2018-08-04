package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class GDGaiaChampSword extends ItemSword implements ModelRegisterCallback {

    public GDGaiaChampSword(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
        this.setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }

    //TODO: Deals extra damage to Corrupt and Non-Gaian mobs
    //TODO: [FUTURE] Can this be used to unlock every strucure?

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.world.isRemote) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = entity.posX + itemRand.nextFloat() * entity.width * 2.0F - entity.width;
                double py = entity.posY + itemRand.nextFloat() * entity.height;
                double pz = entity.posZ + itemRand.nextFloat() * entity.width * 2.0F - entity.width;
                entity.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, px, py, pz, 1, 1, 1, Block.getStateId(Blocks.PORTAL.getDefaultState()));
            }
        }
        return false;
    }
}
