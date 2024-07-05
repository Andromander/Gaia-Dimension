package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, GaiaDimensionMod.MODID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SUGILITE = create("sugilite", new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ModItems.sugilite.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PROUSTITE = create("proustite", new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ModItems.proustite.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ALBITE = create("albite", new int[]{1, 4, 6, 1}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, () -> Ingredient.of(ModItems.albite.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CARNELIAN = create("carnelian", new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ModItems.carnelian.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DIOPSIDE = create("diopside", new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(ModItems.diopside.get()));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GOSHENITE = create("goshenite", new int[]{3, 6, 8, 3}, 5, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> Ingredient.of(ModItems.goshenite.get()));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MALACHITE = create("malachite_guard", new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.5F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TIGER_EYE = create("apex_predator", new int[]{2, 5, 7, 2}, 10, SoundEvents.ARMOR_EQUIP_GOLD, 1.5F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SPINEL = create("spinel_princess", new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ZIRCON = create("zircon_prince", new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> CORRUPT = create("corrupt_warrior", new int[]{4, 7, 9, 4}, 30, BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BLAZE_HURT), 4.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BIXBITE = create("gaia_duchess", new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TSAVORITE = create("gaia_baron", new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.5F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LARVIKITE = create("gaia_duke", new int[]{3, 6, 8, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.0F, () -> Ingredient.EMPTY);
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GAIA_CHAMP = create("gaia_champion", new int[]{4, 7, 9, 4}, 30, SoundEvents.ARMOR_EQUIP_DIAMOND, 4.0F, 0.0F, () -> Ingredient.EMPTY);

    public static DeferredHolder<ArmorMaterial, ArmorMaterial> create(String name, int[] reduction, int enchant, Holder<SoundEvent> sound, float toughness, float resistance, Supplier<Ingredient> ingredient) {
        Map<ArmorItem.Type, Integer> defense = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.HELMET, reduction[0]);
            map.put(ArmorItem.Type.CHESTPLATE, reduction[1]);
            map.put(ArmorItem.Type.LEGGINGS, reduction[2]);
            map.put(ArmorItem.Type.BOOTS, reduction[3]);
            map.put(ArmorItem.Type.BODY, reduction[1]);
        });
        List<ArmorMaterial.Layer> defaultlayers = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name)));

        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(defense, enchant, sound, ingredient, defaultlayers, toughness, resistance));
    }
}
