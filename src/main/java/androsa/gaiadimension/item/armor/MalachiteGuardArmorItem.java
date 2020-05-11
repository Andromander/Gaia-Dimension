package androsa.gaiadimension.item.armor;

import androsa.gaiadimension.registry.GaiaArmorMaterials;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.UUID;

public class MalachiteGuardArmorItem extends BasicGaiaArmorItem {

    public MalachiteGuardArmorItem(EquipmentSlotType slot) {
        super(GaiaArmorMaterials.MALACHITE, slot);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot);
        UUID[] modifier = ObfuscationReflectionHelper.getPrivateValue(ArmorItem.class, null, "field_185084_n");

        if (slot == this.slot) {
            multimap.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(),
                    new AttributeModifier(modifier[slot.getIndex()], "Armor knockback resist", 0.5D, AttributeModifier.Operation.ADDITION));
        }

        return multimap;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltips, flags);
        tooltips.add(new TranslationTextComponent("malachite_armor.tooltip"));
    }
}
