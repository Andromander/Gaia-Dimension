package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import androsa.gaiadimension.registry.values.GaiaFoods;
import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.item.equipment.ArmorType.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GaiaDimensionMod.MODID);

    public static final DeferredItem<Item> crystallized_redstone = register("crystallized_redstone");
    public static final DeferredItem<Item> crystallized_lapis_lazuli = register("crystallized_lapis_lazuli");
    public static final DeferredItem<Item> glint_and_gold = register("glint_and_gold", GlintAndGoldItem::new, props().durability(32));
    public static final DeferredItem<Item> agate_stick = register("agate_stick");
    public static final DeferredItem<Item> hot_dust = register("hot_dust", props -> new Item(props) {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues values) {
            return 100;
        }
    }, props());
    public static final DeferredItem<Item> goldstone_dust = register("goldstone_dust");
    public static final DeferredItem<Item> fine_dust = register("fine_dust");
    public static final DeferredItem<Item> cloudy_shard = register("cloudy_shard");
    public static final DeferredItem<Item> agate_cup = register("agate_cup");
    public static final DeferredItem<Item> scaynyx_ingot = register("scaynyx_ingot");
    public static final DeferredItem<Item> sweet_muckball = register("sweet_muckball");
    public static final DeferredItem<Item> sugar_crystals = register("sugar_crystals");
    public static final DeferredItem<Item> sugar_cluster = register("sugar_cluster");
    public static final DeferredItem<Item> shiny_bone = register("shiny_bone");
    public static final DeferredItem<Item> fine_thread = register("fine_thread");
    public static final DeferredItem<Item> twined_thread = register("twined_thread");
    public static final DeferredItem<Item> pink_essence = register("pink_essence");
    public static final DeferredItem<Item> pink_goo = register("pink_goo");
    public static final DeferredItem<Item> gemstone_pouch = register("gemstone_pouch", props -> new GemstonePouchItem(props.component(ModDataComponents.POUCH_CONTENTS, ItemContainerContents.EMPTY)), props());
    public static final DeferredItem<Item> agate_fabric = register("agate_fabric");
    public static final DeferredItem<Item> sturdy_pebble = register("sturdy_pebble", SturdyPebbleItem::new, props().stacksTo(16));
    public static final DeferredItem<Item> blank_kit = registerKit("blank_kit", ConstructKitItem.Kit.BLANK, null);
    public static final DeferredItem<Item> repair_kit = registerKit("repair_kit", ConstructKitItem.Kit.REPAIR, null);
    public static final DeferredItem<Item> scarlet_augment_kit = registerKit("scarlet_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.SCARLET);
    public static final DeferredItem<Item> auburn_augment_kit = registerKit("auburn_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.AUBURN);
    public static final DeferredItem<Item> gold_augment_kit = registerKit("gold_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.GOLD);
    public static final DeferredItem<Item> mauve_augment_kit = registerKit("mauve_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.MAUVE);
    public static final DeferredItem<Item> beige_augment_kit = registerKit("beige_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.BEIGE);
    public static final DeferredItem<Item> ivory_augment_kit = registerKit("ivory_augment_kit", ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.IVORY);
    public static final DeferredItem<Item> scarlet_replace_kit = registerKit("scarlet_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.SCARLET);
    public static final DeferredItem<Item> auburn_replace_kit = registerKit("auburn_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.AUBURN);
    public static final DeferredItem<Item> gold_replace_kit = registerKit("gold_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.GOLD);
    public static final DeferredItem<Item> mauve_replace_kit = registerKit("mauve_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.MAUVE);
    public static final DeferredItem<Item> beige_replace_kit = registerKit("beige_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.BEIGE);
    public static final DeferredItem<Item> ivory_replace_kit = registerKit("ivory_replace_kit", ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.IVORY);
    public static final DeferredItem<Item> construct_charm = register("construct_charm", ConstructCharmItem::new, props().stacksTo(1));
    public static final DeferredItem<Item> scaynyx_bucket = registerBucket("scaynyx_bucket", () -> Fluids.EMPTY);
    public static final DeferredItem<Item> mineral_water_bucket = registerBucket("mineral_water_bucket", ModFluids.mineral_water_still);
    public static final DeferredItem<Item> superhot_magma_bucket = registerBucket("superhot_magma_bucket", ModFluids.superhot_magma_still);
    public static final DeferredItem<Item> sweet_muck_bucket = registerBucket("sweet_muck_bucket", ModFluids.sweet_muck_still);
    public static final DeferredItem<Item> liquid_bismuth_bucket = registerBucket("liquid_bismuth_bucket", ModFluids.liquid_bismuth_still);
    public static final DeferredItem<Item> liquid_aura_bucket = registerBucket("liquid_aura_bucket", ModFluids.liquid_aura_still);
    public static final DeferredItem<Item> crystal_shard = register("crystal_shard");

    public static final DeferredItem<Item> pink_geode = register("pink_geode");
    public static final DeferredItem<Item> blue_geode = register("blue_geode");
    public static final DeferredItem<Item> green_geode = register("green_geode");
    public static final DeferredItem<Item> purple_geode = register("purple_geode");
    public static final DeferredItem<Item> pink_geode_slice = register("pink_geode_slice", GeodeSliceItem::new, props().food(GaiaFoods.PINK_SLICE));
    public static final DeferredItem<Item> blue_geode_slice = register("blue_geode_slice", GeodeSliceItem::new, props().food(GaiaFoods.BLUE_SLICE));
    public static final DeferredItem<Item> green_geode_slice = register("green_geode_slice", GeodeSliceItem::new, props().food(GaiaFoods.GREEN_SLICE));
    public static final DeferredItem<Item> purple_geode_slice = register("purple_geode_slice", GeodeSliceItem::new, props().food(GaiaFoods.PURPLE_SLICE));
    public static final DeferredItem<Item> pink_geode_juice = register("pink_geode_juice", GeodeJuiceItem::new, props().food(GaiaFoods.PINK_JUICE, GaiaFoods.PINK_JUICE_EFFECT));
    public static final DeferredItem<Item> blue_geode_tea = register("blue_geode_tea", GeodeJuiceItem::new, props().food(GaiaFoods.BLUE_TEA, GaiaFoods.BLUE_TEA_EFFECT));
    public static final DeferredItem<Item> green_geode_ale = register("green_geode_ale", GeodeJuiceItem::new, props().food(GaiaFoods.GREEN_ALE, GaiaFoods.GREEN_ALE_EFFECT));
    public static final DeferredItem<Item> purple_geode_soda = register("purple_geode_soda", GeodeJuiceItem::new, props().food(GaiaFoods.PURPLE_SODA, GaiaFoods.PURPLE_SODA_EFFECT));
    public static final DeferredItem<Item> pearly_geode_elixir = register("pearly_geode_elixir", GeodeJuiceItem::new, props().food(GaiaFoods.PEARLY_ELIXIR, GaiaFoods.PEARLY_ELIXIR_EFFECT));
    public static final DeferredItem<Item> lurmorus_meat = register("lurmorus_meat", GaiaFoods.LURMORUS_MEAT);
    public static final DeferredItem<Item> lurmorus_steak = register("lurmorus_steak", GaiaFoods.LURMORUS_STEAK);
    public static final DeferredItem<Item> small_tentacle = register("small_tentacle", GaiaFoods.SMALL_TENTACLE);
    public static final DeferredItem<Item> small_calamari = register("small_calamari", GaiaFoods.SMALL_CALAMARI);
    public static final DeferredItem<Item> large_tentacle = register("large_tentacle", GaiaFoods.LARGE_TENTACLE);
    public static final DeferredItem<Item> large_calamari = register("large_calamari", GaiaFoods.LARGE_CALAMARI);
    public static final DeferredItem<Item> markuzar_mint = register("markuzar_mint", GaiaFoods.MARKUZAR_MINT);
    public static final DeferredItem<Item> luggeroth_chop = register("luggeroth_chop", GaiaFoods.LUGGEROTH_CHOP);
    public static final DeferredItem<Item> cooked_luggeroth_chop = register("cooked_luggeroth_chop", GaiaFoods.COOKED_LUGGEROTH_CHOP);
    public static final DeferredItem<Item> tilipi = register("tilipi", GaiaFoods.TILIPI);
    public static final DeferredItem<Item> tilibl = register("tilibl", GaiaFoods.TILIBL);
    public static final DeferredItem<Item> tiligr = register("tiligr", GaiaFoods.TILIGR);
    public static final DeferredItem<Item> tilipu = register("tilipu", GaiaFoods.TILIPU);
    public static final DeferredItem<Item> tiliol = register("tiliol", GaiaFoods.TILIOL);
    public static final DeferredItem<Item> tilimy = register("tilimy", GaiaFoods.TILIMY);
    public static final DeferredItem<Item> plagued_tiliey = register("plagued_tiliey", GaiaFoods.PLAGUED_TILIEY, GaiaFoods.PLAGUED_TILIEY_EFFECT);
    public static final DeferredItem<Item> tiliou = register("tiliou", GaiaFoods.TILIOU);

    public static final DeferredItem<Item> hematite_powder = register("hematite_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> cinnabar_powder = register("cinnabar_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> labradorite_powder = register("labradorite_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> moonstone_powder = register("moonstone_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> red_opal_powder = register("red_opal_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> blue_opal_powder = register("blue_opal_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> green_opal_powder = register("green_opal_powder", GroundGemItem::new, props());
    public static final DeferredItem<Item> white_opal_grit = register("white_opal_grit", GroundGemItem::new, props());
    public static final DeferredItem<Item> pyrite_powder = register("pyrite_powder", GroundGemItem::new, props());

    public static final DeferredItem<Item> sugilite = register("sugilite");
    public static final DeferredItem<Item> hematite = register("hematite");
    public static final DeferredItem<Item> cinnabar = register("cinnabar");
    public static final DeferredItem<Item> labradorite = register("labradorite");
    public static final DeferredItem<Item> moonstone = register("moonstone");
    public static final DeferredItem<Item> red_opal = register("red_opal");
    public static final DeferredItem<Item> blue_opal = register("blue_opal");
    public static final DeferredItem<Item> green_opal = register("green_opal");
    public static final DeferredItem<Item> white_opal = register("white_opal");
    public static final DeferredItem<Item> stibnite = register("stibnite");
    public static final DeferredItem<Item> proustite = register("proustite");
    public static final DeferredItem<Item> euclase = register("euclase");
    public static final DeferredItem<Item> albite = register("albite");
    public static final DeferredItem<Item> carnelian = register("carnelian");
    public static final DeferredItem<Item> benitoite = register("benitoite");
    public static final DeferredItem<Item> diopside = register("diopside");
    public static final DeferredItem<Item> goshenite = register("goshenite");
    public static final DeferredItem<Item> pyrite = register("pyrite");
    public static final DeferredItem<Item> black_residue = register("black_residue");
    public static final DeferredItem<Item> tektite = register("tektite");
    public static final DeferredItem<Item> goldstone_residue = register("goldstone_residue");
    public static final DeferredItem<Item> goldstone = register("goldstone");
    public static final DeferredItem<Item> aura_residue = register("aura_residue");
    public static final DeferredItem<Item> aura_cluster = register("aura_cluster");
    public static final DeferredItem<Item> bismuth_residue = register("bismuth_residue");
    public static final DeferredItem<Item> bismuth_crystal = register("bismuth_crystal");
    public static final DeferredItem<Item> opalite = register("opalite");
    public static final DeferredItem<Item> celestine = register("celestine");

    public static final DeferredItem<Item> sugilite_helmet = register("sugilite_helmet", GaiaArmorMaterials.SUGILITE, HELMET);
    public static final DeferredItem<Item> sugilite_chestplate = register("sugilite_chestplate", GaiaArmorMaterials.SUGILITE, CHESTPLATE);
    public static final DeferredItem<Item> sugilite_legs = register("sugilite_legs", GaiaArmorMaterials.SUGILITE, LEGGINGS);
    public static final DeferredItem<Item> sugilite_boots = register("sugilite_boots", GaiaArmorMaterials.SUGILITE, BOOTS);
    public static final DeferredItem<Item> proustite_helmet = register("proustite_helmet", GaiaArmorMaterials.PROUSTITE, HELMET);
    public static final DeferredItem<Item> proustite_chestplate = register("proustite_chestplate", GaiaArmorMaterials.PROUSTITE, CHESTPLATE);
    public static final DeferredItem<Item> proustite_legs = register("proustite_legs", GaiaArmorMaterials.PROUSTITE, LEGGINGS);
    public static final DeferredItem<Item> proustite_boots = register("proustite_boots", GaiaArmorMaterials.PROUSTITE, BOOTS);
    public static final DeferredItem<Item> albite_helmet = register("albite_helmet", GaiaArmorMaterials.ALBITE, HELMET);
    public static final DeferredItem<Item> albite_chestplate = register("albite_chestplate", GaiaArmorMaterials.ALBITE, CHESTPLATE);
    public static final DeferredItem<Item> albite_legs = register("albite_legs", GaiaArmorMaterials.ALBITE, LEGGINGS);
    public static final DeferredItem<Item> albite_boots = register("albite_boots", GaiaArmorMaterials.ALBITE, BOOTS);
    public static final DeferredItem<Item> carnelian_helmet = register("carnelian_helmet", GaiaArmorMaterials.CARNELIAN, HELMET);
    public static final DeferredItem<Item> carnelian_chestplate = register("carnelian_chestplate", GaiaArmorMaterials.CARNELIAN, CHESTPLATE);
    public static final DeferredItem<Item> carnelian_legs = register("carnelian_legs", GaiaArmorMaterials.CARNELIAN, LEGGINGS);
    public static final DeferredItem<Item> carnelian_boots = register("carnelian_boots", GaiaArmorMaterials.CARNELIAN, BOOTS);
    public static final DeferredItem<Item> diopside_helmet = register("diopside_helmet", GaiaArmorMaterials.DIOPSIDE, HELMET);
    public static final DeferredItem<Item> diopside_chestplate = register("diopside_chestplate", GaiaArmorMaterials.DIOPSIDE, CHESTPLATE);
    public static final DeferredItem<Item> diopside_legs = register("diopside_legs", GaiaArmorMaterials.DIOPSIDE, LEGGINGS);
    public static final DeferredItem<Item> diopside_boots = register("diopside_boots", GaiaArmorMaterials.DIOPSIDE, BOOTS);
    public static final DeferredItem<Item> goshenite_helmet = register("goshenite_helmet", GaiaArmorMaterials.GOSHENITE, HELMET);
    public static final DeferredItem<Item> goshenite_chestplate = register("goshenite_chestplate", GaiaArmorMaterials.GOSHENITE, CHESTPLATE);
    public static final DeferredItem<Item> goshenite_legs = register("goshenite_legs", GaiaArmorMaterials.GOSHENITE, LEGGINGS);
    public static final DeferredItem<Item> goshenite_boots = register("goshenite_boots", GaiaArmorMaterials.GOSHENITE, BOOTS);

    public static final DeferredItem<Item> malachite_guard_headgear = register("malachite_guard_headgear", props -> new MalachiteGuardArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> malachite_guard_brace = register("malachite_guard_brace", props -> new MalachiteGuardArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> malachite_guard_gear = register("malachite_guard_gear", props -> new MalachiteGuardArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> malachite_guard_boots = register("malachite_guard_boots", props -> new MalachiteGuardArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> apex_predator_hood = register("apex_predator_hood", props -> new ApexPredatorArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> apex_predator_jacket = register("apex_predator_jacket", props -> new ApexPredatorArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> apex_predator_trousers = register("apex_predator_trousers", props -> new ApexPredatorArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> apex_predator_boots = register("apex_predator_boots", props -> new ApexPredatorArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> spinel_princess_cowl = register("spinel_princess_cowl", props -> new SpinelPrincessArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> spinel_princess_cloak = register("spinel_princess_cloak", props -> new SpinelPrincessArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> spinel_princess_dress = register("spinel_princess_dress", props -> new SpinelPrincessArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> spinel_princess_heels = register("spinel_princess_heels", props -> new SpinelPrincessArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> zircon_prince_crown = register("zircon_prince_crown", props -> new ZirconPrinceArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> zircon_prince_chestpiece = register("zircon_prince_chestpiece", props -> new ZirconPrinceArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> zircon_prince_gear = register("zircon_prince_gear", props -> new ZirconPrinceArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> zircon_prince_boots = register("zircon_prince_boots", props -> new ZirconPrinceArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> corrupt_warrior_helm = register("corrupt_warrior_helm", props -> new CorruptWarriorArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> corrupt_warrior_guard = register("corrupt_warrior_guard", props -> new CorruptWarriorArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> corrupt_warrior_greaves = register("corrupt_warrior_greaves", props -> new CorruptWarriorArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> corrupt_warrior_boots = register("corrupt_warrior_boots", props -> new CorruptWarriorArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duchess_helm = register("gaia_duchess_helm", props -> new GaiaDuchessArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duchess_guard = register("gaia_duchess_guard", props -> new GaiaDuchessArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duchess_greaves = register("gaia_duchess_greaves", props -> new GaiaDuchessArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duchess_boots = register("gaia_duchess_boots", props -> new GaiaDuchessArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_baron_mask = register("gaia_baron_mask", props -> new GaiaBaronArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_baron_tuxedo = register("gaia_baron_tuxedo", props -> new GaiaBaronArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_baron_pants = register("gaia_baron_pants", props -> new GaiaBaronArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_baron_shoes = register("gaia_baron_shoes", props -> new GaiaBaronArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duke_helm = register("gaia_duke_helm", props -> new GaiaDukeArmorItem(HELMET, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duke_guard = register("gaia_duke_guard", props -> new GaiaDukeArmorItem(CHESTPLATE, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duke_greaves = register("gaia_duke_greaves", props -> new GaiaDukeArmorItem(LEGGINGS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duke_boots = register("gaia_duke_boots", props -> new GaiaDukeArmorItem(BOOTS, props), props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_champion_helm = register("gaia_champion_helm", props -> new GaiaChampArmorItem(HELMET, props), props().rarity(Rarity.EPIC));
    public static final DeferredItem<Item> gaia_champion_guard = register("gaia_champion_guard", props -> new GaiaChampArmorItem(CHESTPLATE, props), props().rarity(Rarity.EPIC));
    public static final DeferredItem<Item> gaia_champion_greaves = register("gaia_champion_greaves", props -> new GaiaChampArmorItem(LEGGINGS, props), props().rarity(Rarity.EPIC));
    public static final DeferredItem<Item> gaia_champion_boots = register("gaia_champion_boots", props -> new GaiaChampArmorItem(BOOTS, props), props().rarity(Rarity.EPIC));

    public static final DeferredItem<Item> agate_sword = registerSword("agate_sword", GaiaToolMaterials.AGATE);
    public static final DeferredItem<Item> agate_pickaxe = registerPickaxe("agate_pickaxe", GaiaToolMaterials.AGATE);
    public static final DeferredItem<Item> agate_axe = registerAxe("agate_axe", GaiaToolMaterials.AGATE);
    public static final DeferredItem<Item> agate_shovel = registerShovel("agate_shovel", GaiaToolMaterials.AGATE);
    public static final DeferredItem<Item> sugilite_sword = registerSword("sugilite_sword", GaiaToolMaterials.SUGILITE);
    public static final DeferredItem<Item> sugilite_pickaxe = registerPickaxe("sugilite_pickaxe", GaiaToolMaterials.SUGILITE);
    public static final DeferredItem<Item> sugilite_axe = registerAxe("sugilite_axe", GaiaToolMaterials.SUGILITE);
    public static final DeferredItem<Item> sugilite_shovel = registerShovel("sugilite_shovel", GaiaToolMaterials.SUGILITE);
    public static final DeferredItem<Item> stibnite_sword = registerSword("stibnite_sword", GaiaToolMaterials.STIBNITE);
    public static final DeferredItem<Item> stibnite_pickaxe = registerPickaxe("stibnite_pickaxe", GaiaToolMaterials.STIBNITE);
    public static final DeferredItem<Item> stibnite_axe = registerAxe("stibnite_axe", GaiaToolMaterials.STIBNITE);
    public static final DeferredItem<Item> stibnite_shovel = registerShovel("stibnite_shovel", GaiaToolMaterials.STIBNITE);
    public static final DeferredItem<Item> euclase_sword = registerSword("euclase_sword", GaiaToolMaterials.EUCLASE);
    public static final DeferredItem<Item> euclase_pickaxe = registerPickaxe("euclase_pickaxe", GaiaToolMaterials.EUCLASE);
    public static final DeferredItem<Item> euclase_axe = registerAxe("euclase_axe", GaiaToolMaterials.EUCLASE);
    public static final DeferredItem<Item> euclase_shovel = registerShovel("euclase_shovel", GaiaToolMaterials.EUCLASE);
    public static final DeferredItem<Item> carnelian_sword = registerSword("carnelian_sword", GaiaToolMaterials.CARNELIAN);
    public static final DeferredItem<Item> carnelian_pickaxe = registerPickaxe("carnelian_pickaxe", GaiaToolMaterials.CARNELIAN);
    public static final DeferredItem<Item> carnelian_axe = registerAxe("carnelian_axe", GaiaToolMaterials.CARNELIAN);
    public static final DeferredItem<Item> carnelian_shovel = registerShovel("carnelian_shovel", GaiaToolMaterials.CARNELIAN);
    public static final DeferredItem<Item> benitoite_sword = registerSword("benitoite_sword", GaiaToolMaterials.BENITOITE);
    public static final DeferredItem<Item> benitoite_pickaxe = registerPickaxe("benitoite_pickaxe", GaiaToolMaterials.BENITOITE);
    public static final DeferredItem<Item> benitoite_axe = registerAxe("benitoite_axe", GaiaToolMaterials.BENITOITE);
    public static final DeferredItem<Item> benitoite_shovel = registerShovel("benitoite_shovel", GaiaToolMaterials.BENITOITE);
    public static final DeferredItem<Item> goshenite_sword = registerSword("goshenite_sword", GaiaToolMaterials.GOSHENITE);
    public static final DeferredItem<Item> goshenite_pickaxe = registerPickaxe("goshenite_pickaxe", GaiaToolMaterials.GOSHENITE);
    public static final DeferredItem<Item> goshenite_axe = registerAxe("goshenite_axe", GaiaToolMaterials.GOSHENITE);
    public static final DeferredItem<Item> goshenite_shovel = registerShovel("goshenite_shovel", GaiaToolMaterials.GOSHENITE);
    public static final DeferredItem<Item> old_bow = register("old_bow", OldBowItem::new, props().durability(425));
    public static final DeferredItem<Item> agate_arrow = register("agate_arrow", AgateArrowItem::new, props());

    public static final DeferredItem<Item> malachite_guard_baton = register("malachite_guard_baton", MalachiteGuardSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> apex_predator_mace = register("apex_predator_mace", ApexPredatorSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> spinel_princess_flamberge = register("spinel_princess_flamberge", SpinelPrincessSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> zircon_prince_razor = register("zircon_prince_razor", ZirconPrinceSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> corrupt_warrior_sword = register("corrupt_warrior_sword", CorruptWarriorSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duchess_khopesh = register("gaia_duchess_khopesh", GaiaDuchessSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_baron_dagger = register("gaia_baron_dagger", GaiaBaronSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_duke_blade = register("gaia_duke_blade", GaiaDukeSwordItem::new, props().rarity(Rarity.RARE));
    public static final DeferredItem<Item> gaia_champion_sword = register("gaia_champion_sword", GaiaChampSwordItem::new, props().rarity(Rarity.EPIC));

    public static final DeferredItem<Item> mock_malachite = register("mock_malachite", MockGemItem::new, props());

    public static final DeferredItem<Item> growth_sapper_spawn_egg = registerEgg("growth_sapper", ModEntities.GROWTH_SAPPER);
    public static final DeferredItem<Item> mutant_growth_extractor_spawn_egg = registerEgg("mutant_growth_extractor", ModEntities.MUTANT_GROWTH_EXTRACTOR);
    public static final DeferredItem<Item> howlite_wolf_spawn_egg = registerEgg("howlite_wolf", ModEntities.HOWLITE_WOLF);
    public static final DeferredItem<Item> spellbound_elemental_spawn_egg = registerEgg("spellbound_elemental", ModEntities.SPELLBOUND_ELEMENTAL);
    public static final DeferredItem<Item> rocky_luggeroth_spawn_egg = registerEgg("rocky_luggeroth", ModEntities.ROCKY_LUGGEROTH);
    public static final DeferredItem<Item> shalurker_spawn_egg = registerEgg("shalurker", ModEntities.SHALURKER);
    public static final DeferredItem<Item> muckling_spawn_egg = registerEgg("muckling", ModEntities.MUCKLING);
    public static final DeferredItem<Item> markuzar_plant_spawn_egg = registerEgg("markuzar_plant", ModEntities.MARKUZAR_PLANT);
    public static final DeferredItem<Item> rugged_lurmorus_spawn_egg = registerEgg("rugged_lurmorus", ModEntities.RUGGED_LURMORUS);
    public static final DeferredItem<Item> agate_golem_spawn_egg = registerEgg("agate_golem", ModEntities.AGATE_GOLEM);
    public static final DeferredItem<Item> ancient_lagrahk_spawn_egg = registerEgg("ancient_lagrahk", ModEntities.ANCIENT_LAGRAHK);
    public static final DeferredItem<Item> crystal_golem_spawn_egg = registerEgg("crystal_golem", ModEntities.CRYSTAL_GOLEM);
    public static final DeferredItem<Item> saltion_spawn_egg = registerEgg("saltion", ModEntities.SALTION);
    public static final DeferredItem<Item> nomadic_lagrahk_spawn_egg = registerEgg("nomadic_lagrahk", ModEntities.NOMADIC_LAGRAHK);
    public static final DeferredItem<Item> shallow_arenthis_spawn_egg = registerEgg("shallow_arenthis", ModEntities.SHALLOW_ARENTHIS);
    public static final DeferredItem<Item> corrupt_sapper_spawn_egg = registerEgg("corrupt_sapper", ModEntities.CORRUPT_SAPPER);
    public static final DeferredItem<Item> contorted_naga_spawn_egg = registerEgg("contorted_naga", ModEntities.CONTORTED_NAGA);
    public static final DeferredItem<Item> lesser_spitfire_spawn_egg = registerEgg("lesser_spitfire", ModEntities.LESSER_SPITFIRE);
    public static final DeferredItem<Item> lesser_shockshooter_spawn_egg = registerEgg("lesser_shockshooter", ModEntities.LESSER_SHOCKSHOOTER);
    public static final DeferredItem<Item> mineral_arenthis_spawn_egg = registerEgg("mineral_arenthis", ModEntities.MINERAL_ARENTHIS);
    public static final DeferredItem<Item> bismuth_uletrus_spawn_egg = registerEgg("bismuth_uletrus", ModEntities.BISMUTH_ULETRUS);
    public static final DeferredItem<Item> archaic_warrior_spawn_egg = registerEgg("archaic_warrior", ModEntities.ARCHAIC_WARRIOR);
    public static final DeferredItem<Item> primal_beast_spawn_egg = registerEgg("primal_beast", ModEntities.PRIMAL_BEAST);
    public static final DeferredItem<Item> cavern_tick_spawn_egg = registerEgg("cavern_tick", ModEntities.CAVERN_TICK);
    public static final DeferredItem<Item> malachite_drone_spawn_egg = registerEgg("malachite_drone", ModEntities.MALACHITE_DRONE);
    public static final DeferredItem<Item> mookaite_construct_spawn_egg = registerEgg("mookaite_construct", ModEntities.MOOKAITE_CONSTRUCT);
    public static final DeferredItem<Item> opalite_construct_spawn_egg = registerEgg("opalite_construct", ModEntities.OPALITE_CONSTRUCT);
    public static final DeferredItem<Item> growth_grazer_spawn_egg = registerEgg("growth_grazer", ModEntities.GROWTH_GRAZER);
    public static final DeferredItem<Item> aureate_evraun_spawn_egg = registerEgg("aureate_evraun", ModEntities.AUREATE_EVRAUN);
    public static final DeferredItem<Item> blue_howlite_wolf_spawn_egg = registerEgg("blue_howlite_wolf", ModEntities.BLUE_HOWLITE_WOLF);
    public static final DeferredItem<Item> malachite_guard_spawn_egg = registerEgg("malachite_guard", ModEntities.MALACHITE_GUARD);

    public static final DeferredItem<Item> PYRITE_TORCH = registerBlock("pyrite_torch", props -> new StandingAndWallBlockItem(ModBlocks.pyrite_torch.get(), ModBlocks.pyrite_wall_torch.get(), Direction.DOWN, props), props());
    public static final DeferredItem<Item> CRUDE_STORAGE_CRATE = registerBlock("crude_storage_crate", props -> new BlockItem(ModBlocks.crude_storage_crate.get(), props), props().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
    public static final DeferredItem<Item> MEGA_STORAGE_CRATE = registerBlock("mega_storage_crate", props -> new BlockItem(ModBlocks.mega_storage_crate.get(), props), props().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

    private static DeferredItem<Item> register(String name) {
        return register(name, Item::new, props());
    }

    private static DeferredItem<Item> register(String name, FoodProperties food) {
        return register(name, Item::new, props().food(food));
    }

    private static DeferredItem<Item> register(String name, FoodProperties food, Consumable consumable) {
        return register(name, Item::new, props().food(food, consumable));
    }

    private static DeferredItem<Item> register(String name, ArmorMaterial material, ArmorType slot) {
        return register(name, props -> new BasicGaiaArmorItem(material, slot, props), props());
    }

    private static DeferredItem<Item> registerKit(String name, ConstructKitItem.Kit kit, ConstructKitItem.Color color) {
        Function<Item.Properties, ? extends Item> function = color != null ? props -> new ConstructKitItem(props, kit, color) : props -> new ConstructKitItem(props, kit);
        Item.Properties props = props();
        if (kit != ConstructKitItem.Kit.BLANK) props.stacksTo(1);
        return register(name, function, props);
    }

    private static DeferredItem<Item> registerBucket(String name, Supplier<? extends Fluid> fluid) {
        return register(name, props -> new ScaynyxBucketItem(props.stacksTo(1), fluid), props());
    }

    public static DeferredItem<Item> registerSword(String name, ToolMaterial tier) {
        return register(name, props -> new BasicGaiaSwordItem(tier, props), props());
    }

    public static DeferredItem<Item> registerPickaxe(String name, ToolMaterial tier) {
        return register(name, props -> new BasicGaiaPickaxeItem(tier, props), props());
    }

    public static DeferredItem<Item> registerAxe(String name, ToolMaterial tier) {
        return register(name, props -> new BasicGaiaAxeItem(tier, props), props());
    }

    public static DeferredItem<Item> registerShovel(String name, ToolMaterial tier) {
        return register(name, props -> new BasicGaiaShovelItem(tier, props), props());
    }

    public static DeferredItem<Item> registerEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity) {
        return register(name + "_spawn_egg", props -> new SpawnEggItem(entity.get(), props), props());
    }

    private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> func, Item.Properties props) {
        return ITEMS.register(name, key -> func.apply(itemProps(name, props)));
    }

    private static <I extends Item> DeferredItem<I> registerBlock(String name, Function<Item.Properties, ? extends I> func, Item.Properties props) {
        return ITEMS.register(name, key -> func.apply(itemProps(name, props).useBlockDescriptionPrefix()));
    }

    private static Item.Properties props() {
        return new Item.Properties();
    }

    private static Item.Properties itemProps(String name, Item.Properties props) {
        return props.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name)));
    }
}
