package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
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

public class GDSpinelPrincessSword extends ItemSword implements ModelRegisterCallback {

    public GDSpinelPrincessSword(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(GDTabs.tabTool);
        this.setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (player.world.isRemote) {
            for (int var1 = 0; var1 < 20; ++var1) {
                double px = entity.posX + itemRand.nextFloat() * entity.width;
                double py = entity.posY + itemRand.nextFloat() * entity.height;
                double pz = entity.posZ + itemRand.nextFloat() * entity.width;
                entity.world.spawnParticle(EnumParticleTypes.FLAME, px, py, pz, 1, 1, 1);
            }
        }

        entity.setFire(10);

        return false;
    }

    //TODO: [FUTURE] Can be used to unlock Unknown Zircon Prince Structure

    //We will need this in order to make the weapon slow
    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName()); //This is not our speed anymore

            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.5, 0)); //Speed is set to 1; 0.6 less than normal
        }

        return multimap;
    }
}
