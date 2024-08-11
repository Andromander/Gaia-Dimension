package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import androsa.gaiadimension.registry.values.GaiaFoods;
import androsa.gaiadimension.registry.values.GaiaToolMaterials;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static net.minecraft.world.item.ArmorItem.Type.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GaiaDimensionMod.MODID);

    public static final DeferredItem<Item> crystallized_redstone = register("crystallized_redstone");
    public static final DeferredItem<Item> crystallized_lapis_lazuli = register("crystallized_lapis_lazuli");
    public static final DeferredItem<Item> glint_and_gold = register("glint_and_gold", () -> new GlintAndGoldItem(itemProps().durability(32)));
    public static final DeferredItem<Item> agate_stick = register("agate_stick");
    public static final DeferredItem<Item> hot_dust = register("hot_dust", () -> new Item(itemProps()) {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 100;
        }
    });
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
    public static final DeferredItem<Item> gemstone_pouch = register("gemstone_pouch", () -> new GemstonePouchItem(itemProps().component(ModDataComponents.POUCH_CONTENTS, ItemContainerContents.EMPTY)));
    public static final DeferredItem<Item> agate_fabric = register("agate_fabric");
    public static final DeferredItem<Item> sturdy_pebble = register("sturdy_pebble", () -> new SturdyPebbleItem(itemProps().stacksTo(16)));
    public static final DeferredItem<Item> blank_kit = register("blank_kit", () -> new ConstructKitItem(itemProps(), ConstructKitItem.Kit.BLANK));
    public static final DeferredItem<Item> repair_kit = register("repair_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPAIR));
    public static final DeferredItem<Item> scarlet_augment_kit = register("scarlet_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.SCARLET));
    public static final DeferredItem<Item> auburn_augment_kit = register("auburn_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.AUBURN));
    public static final DeferredItem<Item> gold_augment_kit = register("gold_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.GOLD));
    public static final DeferredItem<Item> mauve_augment_kit = register("mauve_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.MAUVE));
    public static final DeferredItem<Item> beige_augment_kit = register("beige_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.BEIGE));
    public static final DeferredItem<Item> ivory_augment_kit = register("ivory_augment_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.AUGMENT, ConstructKitItem.Color.IVORY));
    public static final DeferredItem<Item> scarlet_replace_kit = register("scarlet_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.SCARLET));
    public static final DeferredItem<Item> auburn_replace_kit = register("auburn_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.AUBURN));
    public static final DeferredItem<Item> gold_replace_kit = register("gold_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.GOLD));
    public static final DeferredItem<Item> mauve_replace_kit = register("mauve_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.MAUVE));
    public static final DeferredItem<Item> beige_replace_kit = register("beige_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.BEIGE));
    public static final DeferredItem<Item> ivory_replace_kit = register("ivory_replace_kit", () -> new ConstructKitItem(itemProps().stacksTo(1), ConstructKitItem.Kit.REPLACE, ConstructKitItem.Color.IVORY));
    public static final DeferredItem<Item> construct_charm = register("construct_charm", () -> new ConstructCharmItem(itemProps().stacksTo(1)));
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
    public static final DeferredItem<Item> pink_geode_slice = register("pink_geode_slice", () -> new GeodeSliceItem(foodProps(GaiaFoods.PINK_SLICE)));
    public static final DeferredItem<Item> blue_geode_slice = register("blue_geode_slice", () -> new GeodeSliceItem(foodProps(GaiaFoods.BLUE_SLICE)));
    public static final DeferredItem<Item> green_geode_slice = register("green_geode_slice", () -> new GeodeSliceItem(foodProps(GaiaFoods.GREEN_SLICE)));
    public static final DeferredItem<Item> purple_geode_slice = register("purple_geode_slice", () -> new GeodeSliceItem(foodProps(GaiaFoods.PURPLE_SLICE)));
    public static final DeferredItem<Item> pink_geode_juice = register("pink_geode_juice", () -> new GeodeJuiceItem(foodProps(GaiaFoods.PINK_JUICE)));
    public static final DeferredItem<Item> blue_geode_tea = register("blue_geode_tea", () -> new GeodeJuiceItem(foodProps(GaiaFoods.BLUE_TEA)));
    public static final DeferredItem<Item> green_geode_ale = register("green_geode_ale", () -> new GeodeJuiceItem(foodProps(GaiaFoods.GREEN_ALE)));
    public static final DeferredItem<Item> purple_geode_soda = register("purple_geode_soda", () -> new GeodeJuiceItem(foodProps(GaiaFoods.PURPLE_SODA)));
    public static final DeferredItem<Item> pearly_geode_elixir = register("pearly_geode_elixir", () -> new GeodeJuiceItem(foodProps(GaiaFoods.PEARLY_ELIXIR)));
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
    public static final DeferredItem<Item> plagued_tiliey = register("plagued_tiliey", GaiaFoods.PLAGUED_TILIEY);
    public static final DeferredItem<Item> tiliou = register("tiliou", GaiaFoods.TILIOU);

    public static final DeferredItem<Item> hematite_powder = register("hematite_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> cinnabar_powder = register("cinnabar_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> labradorite_powder = register("labradorite_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> moonstone_powder = register("moonstone_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> red_opal_powder = register("red_opal_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> blue_opal_powder = register("blue_opal_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> green_opal_powder = register("green_opal_powder", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> white_opal_grit = register("white_opal_grit", () -> new GroundGemItem(itemProps()));
    public static final DeferredItem<Item> pyrite_powder = register("pyrite_powder", () -> new GroundGemItem(itemProps()));

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

    public static final DeferredItem<Item> sugilite_helmet = register("sugilite_helmet", ModArmorMaterials.SUGILITE, HELMET, 77);
    public static final DeferredItem<Item> sugilite_chestplate = register("sugilite_chestplate", ModArmorMaterials.SUGILITE, CHESTPLATE, 77);
    public static final DeferredItem<Item> sugilite_legs = register("sugilite_legs", ModArmorMaterials.SUGILITE, LEGGINGS, 77);
    public static final DeferredItem<Item> sugilite_boots = register("sugilite_boots", ModArmorMaterials.SUGILITE, BOOTS, 77);
    public static final DeferredItem<Item> proustite_helmet = register("proustite_helmet", ModArmorMaterials.PROUSTITE, HELMET, 115);
    public static final DeferredItem<Item> proustite_chestplate = register("proustite_chestplate", ModArmorMaterials.PROUSTITE, CHESTPLATE, 115);
    public static final DeferredItem<Item> proustite_legs = register("proustite_legs", ModArmorMaterials.PROUSTITE, LEGGINGS, 115);
    public static final DeferredItem<Item> proustite_boots = register("proustite_boots", ModArmorMaterials.PROUSTITE, BOOTS, 115);
    public static final DeferredItem<Item> albite_helmet = register("albite_helmet", ModArmorMaterials.ALBITE, HELMET, 100);
    public static final DeferredItem<Item> albite_chestplate = register("albite_chestplate", ModArmorMaterials.ALBITE, CHESTPLATE, 100);
    public static final DeferredItem<Item> albite_legs = register("albite_legs", ModArmorMaterials.ALBITE, LEGGINGS, 100);
    public static final DeferredItem<Item> albite_boots = register("albite_boots", ModArmorMaterials.ALBITE, BOOTS, 100);
    public static final DeferredItem<Item> carnelian_helmet = register("carnelian_helmet", ModArmorMaterials.CARNELIAN, HELMET, 192);
    public static final DeferredItem<Item> carnelian_chestplate = register("carnelian_chestplate", ModArmorMaterials.CARNELIAN, CHESTPLATE, 192);
    public static final DeferredItem<Item> carnelian_legs = register("carnelian_legs", ModArmorMaterials.CARNELIAN, LEGGINGS, 192);
    public static final DeferredItem<Item> carnelian_boots = register("carnelian_boots", ModArmorMaterials.CARNELIAN, BOOTS, 192);
    public static final DeferredItem<Item> diopside_helmet = register("diopside_helmet", ModArmorMaterials.DIOPSIDE, HELMET, 177);
    public static final DeferredItem<Item> diopside_chestplate = register("diopside_chestplate", ModArmorMaterials.DIOPSIDE, CHESTPLATE, 177);
    public static final DeferredItem<Item> diopside_legs = register("diopside_legs", ModArmorMaterials.DIOPSIDE, LEGGINGS, 177);
    public static final DeferredItem<Item> diopside_boots = register("diopside_boots", ModArmorMaterials.DIOPSIDE, BOOTS, 177);
    public static final DeferredItem<Item> goshenite_helmet = register("goshenite_helmet", ModArmorMaterials.GOSHENITE, HELMET, 230);
    public static final DeferredItem<Item> goshenite_chestplate = register("goshenite_chestplate", ModArmorMaterials.GOSHENITE, CHESTPLATE, 230);
    public static final DeferredItem<Item> goshenite_legs = register("goshenite_legs", ModArmorMaterials.GOSHENITE, LEGGINGS, 230);
    public static final DeferredItem<Item> goshenite_boots = register("goshenite_boots", ModArmorMaterials.GOSHENITE, BOOTS, 230);

    public static final DeferredItem<Item> malachite_guard_headgear = register("malachite_guard_headgear", () -> new MalachiteGuardArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> malachite_guard_brace = register("malachite_guard_brace", () -> new MalachiteGuardArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> malachite_guard_gear = register("malachite_guard_gear", () -> new MalachiteGuardArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> malachite_guard_boots = register("malachite_guard_boots", () -> new MalachiteGuardArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> apex_predator_hood = register("apex_predator_hood", () -> new ApexPredatorArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> apex_predator_jacket = register("apex_predator_jacket", () -> new ApexPredatorArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> apex_predator_trousers = register("apex_predator_trousers", () -> new ApexPredatorArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> apex_predator_boots = register("apex_predator_boots", () -> new ApexPredatorArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> spinel_princess_cowl = register("spinel_princess_cowl", () -> new SpinelPrincessArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> spinel_princess_cloak = register("spinel_princess_cloak", () -> new SpinelPrincessArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> spinel_princess_dress = register("spinel_princess_dress", () -> new SpinelPrincessArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> spinel_princess_heels = register("spinel_princess_heels", () -> new SpinelPrincessArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> zircon_prince_crown = register("zircon_prince_crown", () -> new ZirconPrinceArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> zircon_prince_chestpiece = register("zircon_prince_chestpiece", () -> new ZirconPrinceArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> zircon_prince_gear = register("zircon_prince_gear", () -> new ZirconPrinceArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> zircon_prince_boots = register("zircon_prince_boots", () -> new ZirconPrinceArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> corrupt_warrior_helm = register("corrupt_warrior_helm", () -> new CorruptWarriorArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> corrupt_warrior_guard = register("corrupt_warrior_guard", () -> new CorruptWarriorArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> corrupt_warrior_greaves = register("corrupt_warrior_greaves", () -> new CorruptWarriorArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> corrupt_warrior_boots = register("corrupt_warrior_boots", () -> new CorruptWarriorArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duchess_helm = register("gaia_duchess_helm", () -> new GaiaDuchessArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duchess_guard = register("gaia_duchess_guard", () -> new GaiaDuchessArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duchess_greaves = register("gaia_duchess_greaves", () -> new GaiaDuchessArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duchess_boots = register("gaia_duchess_boots", () -> new GaiaDuchessArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_baron_mask = register("gaia_baron_mask", () -> new GaiaBaronArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_baron_tuxedo = register("gaia_baron_tuxedo", () -> new GaiaBaronArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_baron_pants = register("gaia_baron_pants", () -> new GaiaBaronArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_baron_shoes = register("gaia_baron_shoes", () -> new GaiaBaronArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duke_helm = register("gaia_duke_helm", () -> new GaiaDukeArmorItem(HELMET, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duke_guard = register("gaia_duke_guard", () -> new GaiaDukeArmorItem(CHESTPLATE, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duke_greaves = register("gaia_duke_greaves", () -> new GaiaDukeArmorItem(LEGGINGS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duke_boots = register("gaia_duke_boots", () -> new GaiaDukeArmorItem(BOOTS, armorProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_champion_helm = register("gaia_champion_helm", () -> new GaiaChampArmorItem(HELMET, armorProps().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> gaia_champion_guard = register("gaia_champion_guard", () -> new GaiaChampArmorItem(CHESTPLATE, armorProps().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> gaia_champion_greaves = register("gaia_champion_greaves", () -> new GaiaChampArmorItem(LEGGINGS, armorProps().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> gaia_champion_boots = register("gaia_champion_boots", () -> new GaiaChampArmorItem(BOOTS, armorProps().rarity(Rarity.EPIC)));

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
    public static final DeferredItem<Item> old_bow = register("old_bow", () -> new OldBowItem(toolProps().durability(425)));
    public static final DeferredItem<Item> agate_arrow = register("agate_arrow", () -> new AgateArrowItem(itemProps()));

    public static final DeferredItem<Item> malachite_guard_baton = register("malachite_guard_baton", () -> new MalachiteGuardSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> apex_predator_mace = register("apex_predator_mace", () -> new ApexPredatorSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> spinel_princess_flamberge = register("spinel_princess_flamberge", () -> new SpinelPrincessSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> zircon_prince_razor = register("zircon_prince_razor", () -> new ZirconPrinceSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> corrupt_warrior_sword = register("corrupt_warrior_sword", () -> new CorruptWarriorSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duchess_khopesh = register("gaia_duchess_khopesh", () -> new GaiaDuchessSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_baron_dagger = register("gaia_baron_dagger", () -> new GaiaBaronSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_duke_blade = register("gaia_duke_blade", () -> new GaiaDukeSwordItem(toolProps().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> gaia_champion_sword = register("gaia_champion_sword", () -> new GaiaChampSwordItem(toolProps().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> mock_malachite = register("mock_malachite", () -> new MockGemItem(itemProps()));

    public static final DeferredItem<Item> growth_sapper_spawn_egg = registerEgg("growth_sapper", ModEntities.GROWTH_SAPPER, 0x5A4514, 0xFF00FF);
    public static final DeferredItem<Item> mutant_growth_extractor_spawn_egg = registerEgg("mutant_growth_extractor", ModEntities.MUTANT_GROWTH_EXTRACTOR, 0x5A4514, 0xFFFFCC);
    public static final DeferredItem<Item> howlite_wolf_spawn_egg = registerEgg("howlite_wolf", ModEntities.HOWLITE_WOLF, 0xDDDDDD, 0x3333FF);
    public static final DeferredItem<Item> spellbound_elemental_spawn_egg = registerEgg("spellbound_elemental", ModEntities.SPELLBOUND_ELEMENTAL, 0x885555, 0xCC33CC);
    public static final DeferredItem<Item> rocky_luggeroth_spawn_egg = registerEgg("rocky_luggeroth", ModEntities.ROCKY_LUGGEROTH, 0xB07700, 0xCC9900);
    public static final DeferredItem<Item> shalurker_spawn_egg = registerEgg("shalurker", ModEntities.SHALURKER, 0x771177, 0x000000);
    public static final DeferredItem<Item> muckling_spawn_egg = registerEgg("muckling", ModEntities.MUCKLING, 0xFF00FF, 0xCC66CC);
    public static final DeferredItem<Item> markuzar_plant_spawn_egg = registerEgg("markuzar_plant", ModEntities.MARKUZAR_PLANT, 0x00FF66, 0xCC00FF);
    public static final DeferredItem<Item> rugged_lurmorus_spawn_egg = registerEgg("rugged_lurmorus", ModEntities.RUGGED_LURMORUS, 0xCC9933, 0xFF6600);
    public static final DeferredItem<Item> agate_golem_spawn_egg = registerEgg("agate_golem", ModEntities.AGATE_GOLEM, 0x660000, 0xBB5555);
    public static final DeferredItem<Item> ancient_lagrahk_spawn_egg = registerEgg("ancient_lagrahk", ModEntities.ANCIENT_LAGRAHK, 0x772200, 0xAA5500);
    public static final DeferredItem<Item> crystal_golem_spawn_egg = registerEgg("crystal_golem", ModEntities.CRYSTAL_GOLEM, 0xFF66CC, 0xFF99CC);
    public static final DeferredItem<Item> saltion_spawn_egg = registerEgg("saltion", ModEntities.SALTION, 0x6699FF, 0x6633FF);
    public static final DeferredItem<Item> nomadic_lagrahk_spawn_egg = registerEgg("nomadic_lagrahk", ModEntities.NOMADIC_LAGRAHK, 0x3366CC, 0x232323);
    public static final DeferredItem<Item> shallow_arenthis_spawn_egg = registerEgg("shallow_arenthis", ModEntities.SHALLOW_ARENTHIS, 0x6699CC, 0x003399);
    public static final DeferredItem<Item> corrupt_sapper_spawn_egg = registerEgg("corrupt_sapper", ModEntities.CORRUPT_SAPPER, 0x202020, 0xCC3300);
    public static final DeferredItem<Item> contorted_naga_spawn_egg = registerEgg("contorted_naga", ModEntities.CONTORTED_NAGA, 0x202020, 0xCC3300);
    public static final DeferredItem<Item> lesser_spitfire_spawn_egg = registerEgg("lesser_spitfire", ModEntities.LESSER_SPITFIRE, 0xFF00FF, 0x202020);
    public static final DeferredItem<Item> lesser_shockshooter_spawn_egg = registerEgg("lesser_shockshooter", ModEntities.LESSER_SHOCKSHOOTER, 0x00FFFF, 0x202020);
    public static final DeferredItem<Item> mineral_arenthis_spawn_egg = registerEgg("mineral_arenthis", ModEntities.MINERAL_ARENTHIS, 0x0066CC, 0x000033);
    public static final DeferredItem<Item> bismuth_uletrus_spawn_egg = registerEgg("bismuth_uletrus", ModEntities.BISMUTH_ULETRUS, 0x4E3863, 0x303030);
    public static final DeferredItem<Item> archaic_warrior_spawn_egg = registerEgg("archaic_warrior", ModEntities.ARCHAIC_WARRIOR, 0x996699, 0xCC3366);
    public static final DeferredItem<Item> primal_beast_spawn_egg = registerEgg("primal_beast", ModEntities.PRIMAL_BEAST, 0x006699, 0x66FFFF);
    public static final DeferredItem<Item> cavern_tick_spawn_egg = registerEgg("cavern_tick", ModEntities.CAVERN_TICK, 0x9966CC, 0x666699);
    public static final DeferredItem<Item> malachite_drone_spawn_egg = registerEgg("malachite_drone", ModEntities.MALACHITE_DRONE, 0x00AA33, 0x33AA00);
    public static final DeferredItem<Item> mookaite_construct_spawn_egg = registerEgg("mookaite_construct", ModEntities.MOOKAITE_CONSTRUCT, 0X6F442F, 0X39190E);
    public static final DeferredItem<Item> opalite_construct_spawn_egg = registerEgg("opalite_construct", ModEntities.OPALITE_CONSTRUCT, 0X698A9F, 0X7BFDFE);
    public static final DeferredItem<Item> growth_grazer_spawn_egg = registerEgg("growth_grazer", ModEntities.GROWTH_GRAZER, 0x5A4514, 0xFFD700);
    public static final DeferredItem<Item> aureate_evraun_spawn_egg = registerEgg("aureate_evraun", ModEntities.AUREATE_EVRAUN, 0X53432F, 0XE2D366);
    public static final DeferredItem<Item> blue_howlite_wolf_spawn_egg = registerEgg("blue_howlite_wolf", ModEntities.BLUE_HOWLITE_WOLF, 0x0099CC, 0xCC00FF);
    public static final DeferredItem<Item> malachite_guard_spawn_egg = registerEgg("malachite_guard", ModEntities.MALACHITE_GUARD, 0x339900, 0x33CC99);

    public static final DeferredItem<Item> PYRITE_TORCH = ITEMS.register("pyrite_torch", () -> new StandingAndWallBlockItem(ModBlocks.pyrite_torch.get(), ModBlocks.pyrite_wall_torch.get(), basicProps(), Direction.DOWN));
    public static final DeferredItem<Item> CRUDE_STORAGE_CRATE = ITEMS.register("crude_storage_crate", () -> new BlockItem(ModBlocks.crude_storage_crate.get(), itemProps().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)));
    public static final DeferredItem<Item> MEGA_STORAGE_CRATE = ITEMS.register("mega_storage_crate", () -> new BlockItem(ModBlocks.mega_storage_crate.get(), itemProps().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)));

    private static DeferredItem<Item> register(String name) {
        return register(name, () -> new Item(basicProps()));
    }

    private static DeferredItem<Item> register(String name, FoodProperties props) {
        return register(name, () -> new Item(foodProps(props)));
    }

    private static DeferredItem<Item> register(String name, DeferredHolder<ArmorMaterial, ArmorMaterial> material, ArmorItem.Type slot, int durability) {
        return register(name, () -> new BasicGaiaArmorItem(material, slot, basicProps().durability(slot.getDurability(durability))));
    }

    private static DeferredItem<Item> registerBucket(String name, Supplier<? extends Fluid> fluid) {
        return register(name, () -> new ScaynyxBucketItem(itemProps().stacksTo(1), fluid));
    }

    public static DeferredItem<Item> registerSword(String name, Tier tier) {
        return register(name, () -> new BasicGaiaSwordItem(tier, toolProps()));
    }

    public static DeferredItem<Item> registerPickaxe(String name, Tier tier) {
        return register(name, () -> new BasicGaiaPickaxeItem(tier, toolProps()));
    }

    public static DeferredItem<Item> registerAxe(String name, Tier tier) {
        return register(name, () -> new BasicGaiaAxeItem(tier, toolProps()));
    }

    public static DeferredItem<Item> registerShovel(String name, Tier tier) {
        return register(name, () -> new BasicGaiaShovelItem(tier, toolProps()));
    }

    public static DeferredItem<Item> registerEgg(String name, Supplier<? extends EntityType<? extends Mob>> entity, int back, int front) {
        return register(name + "_spawn_egg", () -> new DeferredSpawnEggItem(entity, back, front, new Item.Properties()));
    }

    private static DeferredItem<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    private static Item.Properties itemProps() {
        return basicProps();
    }

    private static Item.Properties foodProps(FoodProperties food) {
        return basicProps().food(food);
    }

    private static Item.Properties toolProps() {
        return basicProps();
    }

    private static Item.Properties armorProps() {
        return basicProps();
    }

    private static Item.Properties basicProps() {
        return new Item.Properties();
    }

    public static void addItemProperties() {
        ItemProperties.register(old_bow.get(), ResourceLocation.withDefaultNamespace("pull"), ((stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        }));
        ItemProperties.register(old_bow.get(), ResourceLocation.withDefaultNamespace("pulling"), (stack, world, entity, seed) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
        ItemProperties.register(construct_charm.get(), ResourceLocation.withDefaultNamespace("mookaite"), (stack, world, entity, seed) ->
                entity != null && stack.has(ModDataComponents.MOOKAITE_UUID) ? 1.0F : 0.0F);
        ItemProperties.register(construct_charm.get(), ResourceLocation.withDefaultNamespace("opalite"), (stack, world, entity, seed) ->
                entity != null && stack.has(ModDataComponents.OPALITE_UUID) ? 1.0F : 0.0F);
    }
}
