package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum GaiaArmorMaterials implements IArmorMaterial {
    SUGILITE("sugilite", 77, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ModItems.sugilite.get())),
    PROUSTITE("proustite", 115, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ModItems.proustite.get())),
    LEUCITE("leucite", 100, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, () -> Ingredient.of(ModItems.leucite.get())),
    CARNELIAN("carnelian", 192, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.carnelian.get())),
    DIOPSIDE("diopside", 177, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(ModItems.diopside.get())),
    CHALCEDONY("chalcedony", 230, new int[]{3, 6, 8, 3}, 5, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> Ingredient.of(ModItems.chalcedony.get())),

    MALACHITE("malachite_guard", 394, new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.5F, () -> Ingredient.EMPTY),
    TIGER_EYE("apex_predator", 315, new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 1.5F, 0.0F, () -> Ingredient.EMPTY),
    SPINEL("spinel_princess", 394, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, () -> Ingredient.EMPTY),
    ZIRCON("zircon_prince", 472, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, () -> Ingredient.EMPTY),
    CORRUPT("corrupt_warrior", 1000, new int[]{4, 7, 9, 4}, 30, SoundEvents.BLAZE_HURT, 4.0F, 0.0F, () -> Ingredient.EMPTY),
    BIXBITE("gaia_duchess", 630, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.0F, () -> Ingredient.EMPTY),
    TSAVORITE("gaia_baron", 709, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.0F, () -> Ingredient.EMPTY),
    LARVIKITE("gaia_duke", 788, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.0F, () -> Ingredient.EMPTY),
    GAIA_CHAMP("gaia_champion", 1000, new int[]{4, 7, 9, 4}, 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 4.0F, 0.0F, () -> Ingredient.EMPTY);

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String armorName;
    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float armorToughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    GaiaArmorMaterials(String name, int durability, int[] reduction, int enchant, SoundEvent sound, float toughness, float resistance, Supplier<Ingredient> ingredient) {
        armorName = name;
        durabilityFactor = durability;
        damageReduction = reduction;
        enchantability = enchant;
        equipSound = sound;
        armorToughness = toughness;
        knockbackResistance = resistance;
        repairMaterial = ingredient;
    }

    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * durabilityFactor;
    }

    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return damageReduction[slotIn.getIndex()];
    }

    public int getEnchantmentValue() {
        return enchantability;
    }

    public SoundEvent getEquipSound() {
        return equipSound;
    }

    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return new ResourceLocation(GaiaDimensionMod.MODID, armorName).toString();
    }

    public float getToughness() {
        return armorToughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
