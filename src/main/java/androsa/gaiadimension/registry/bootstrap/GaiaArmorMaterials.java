package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;
import java.util.Map;

public class GaiaArmorMaterials {

    public static final ResourceKey<EquipmentAsset> SUGILITE_MODEL = location("sugilite");
    public static final ResourceKey<EquipmentAsset> PROUSTITE_MODEL = location("proustite");
    public static final ResourceKey<EquipmentAsset> ALBITE_MODEL = location("albite");
    public static final ResourceKey<EquipmentAsset> CARNELIAN_MODEL = location("carnelian");
    public static final ResourceKey<EquipmentAsset> DIOPSIDE_MODEL = location("diopside");
    public static final ResourceKey<EquipmentAsset> GOSHENITE_MODEL = location("goshenite");
    public static final ResourceKey<EquipmentAsset> MALACHITE_MODEL = location("malachite_guard");
    public static final ResourceKey<EquipmentAsset> TIGER_EYE_MODEL = location("apex_predator");
    public static final ResourceKey<EquipmentAsset> SPINEL_MODEL = location("spinel_princess");
    public static final ResourceKey<EquipmentAsset> ZIRCON_MODEL = location("zircon_prince");
    public static final ResourceKey<EquipmentAsset> CORRUPT_MODEL = location("corrupt_warrior");
    public static final ResourceKey<EquipmentAsset> BIXBITE_MODEL = location("gaia_duchess");
    public static final ResourceKey<EquipmentAsset> TSAVORITE_MODEL = location("gaia_baron");
    public static final ResourceKey<EquipmentAsset> LARVIKITE_MODEL = location("gaia_duke");
    public static final ResourceKey<EquipmentAsset> GAIA_CHAMP_MODEL = location("gaia_champion");

    public static final ArmorMaterial SUGILITE = create(SUGILITE_MODEL, 77, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, GaiaTags.Items.SUGILITE_ARMOR_MATERIAL);
    public static final ArmorMaterial PROUSTITE = create(PROUSTITE_MODEL, 115, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, GaiaTags.Items.PROUSTITE_ARMOR_MATERIAL);
    public static final ArmorMaterial ALBITE = create(ALBITE_MODEL, 100, new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, GaiaTags.Items.ALBITE_ARMOR_MATERIAL);
    public static final ArmorMaterial CARNELIAN = create(CARNELIAN_MODEL, 192, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, GaiaTags.Items.CARNELIAN_ARMOR_MATERIAL);
    public static final ArmorMaterial DIOPSIDE = create(DIOPSIDE_MODEL, 177, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, GaiaTags.Items.DIOPSIDE_ARMOR_MATERIAL);
    public static final ArmorMaterial GOSHENITE = create(GOSHENITE_MODEL, 230, new int[]{3, 6, 8, 3}, 5, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, GaiaTags.Items.GOSHENITE_ARMOR_MATERIAL);

    public static final ArmorMaterial MALACHITE = create(MALACHITE_MODEL, 394, new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.5F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial TIGER_EYE = create(TIGER_EYE_MODEL, 315, new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 1.5F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial SPINEL = create(SPINEL_MODEL, 394, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial ZIRCON = create(ZIRCON_MODEL, 472, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial CORRUPT = create(CORRUPT_MODEL, 1000, new int[]{4, 7, 9, 4}, 30, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BLAZE_HURT), 4.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial BIXBITE = create(BIXBITE_MODEL, 630, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial TSAVORITE = create(TSAVORITE_MODEL, 709, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial LARVIKITE = create(LARVIKITE_MODEL, 788, new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);
    public static final ArmorMaterial GAIA_CHAMP = create(GAIA_CHAMP_MODEL, 1000, new int[]{4, 7, 9, 4}, 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 4.0F, 0.0F, GaiaTags.Items.UNOBTAINIUM);

    private static ResourceKey<EquipmentAsset> location(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name));
    }

    public static ArmorMaterial create(ResourceKey<EquipmentAsset> model, int durability, int[] reduction, int enchant, Holder<SoundEvent> sound, float toughness, float resistance, TagKey<Item> repair) {
        Map<ArmorType, Integer> defense = Util.make(new EnumMap<>(ArmorType.class), map -> {
            map.put(ArmorType.HELMET, reduction[0]);
            map.put(ArmorType.CHESTPLATE, reduction[1]);
            map.put(ArmorType.LEGGINGS, reduction[2]);
            map.put(ArmorType.BOOTS, reduction[3]);
            map.put(ArmorType.BODY, reduction[1]);
        });

        return new ArmorMaterial(durability, defense, enchant, sound, toughness, resistance, repair, model);
    }
}
