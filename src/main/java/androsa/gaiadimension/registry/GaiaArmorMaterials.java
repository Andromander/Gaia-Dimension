package androsa.gaiadimension.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum GaiaArmorMaterials implements IArmorMaterial {
    SUGILITE("sugilite", 77, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(ModItems.sugilite)),
    PROUSTITE("proustite", 115, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(ModItems.proustite)),
    LEUCITE("leucite", 100, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, () -> Ingredient.fromItems(ModItems.leucite)),
    CARNELIAN("carnelian", 192, new int[]{2, 5, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.fromItems(ModItems.carnelian)),
    DIOPSIDE("diopside", 177, new int[]{2, 5, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, () -> Ingredient.fromItems(ModItems.diopside)),
    CHALCEDONY("chalcedony", 230, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F, () -> Ingredient.fromItems(ModItems.chalcedony)),

    MALACHITE("malachite_guard", 394, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F, null),
    TIGER_EYE("apex_predator", 315, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F, null),
    SPINEL("spinel_princess", 394, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, null),
    ZIRCON("zircon_prince", 472, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, null),
    CORRUPT("corrupt_warrior", 1000, new int[]{4, 7, 9, 4}, 30, SoundEvents.ENTITY_BLAZE_HURT, 4.0F, null),
    BIXBITE("gaia_duchess", 630, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F, null),
    TSAVORITE("gaia_baron", 709, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.5F, null),
    LARVIKITE("gaia_duke", 788, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0F, null),
    GAIA_CHAMP("gaia_champion", 1000, new int[]{4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F, null);

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String armorName;
    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final Supplier<Ingredient> repairMaterial;

    GaiaArmorMaterials(String name, int durability, int[] reduction, int enchant, SoundEvent sound, float toughness, Supplier<Ingredient> ingredient) {
        armorName = name;
        durabilityFactor = durability;
        damageReduction = reduction;
        enchantability = enchant;
        equipSound = sound;
        armorToughness = toughness;
        repairMaterial = ingredient;
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * durabilityFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReduction[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return enchantability;
    }

    public SoundEvent getSoundEvent() {
        return equipSound;
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return armorName;
    }

    public float getToughness() {
        return armorToughness;
    }
}
