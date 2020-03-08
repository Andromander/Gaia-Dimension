package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.inventory.EquipmentSlotType.*;

@SuppressWarnings("unused")
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, GaiaDimensionMod.MODID);

    public static final RegistryObject<Item> crystallized_redstone = ITEMS.register("crystallized_redstone", BasicGaiaItem::new);
    public static final RegistryObject<Item> crystallized_lapis_lazuli = ITEMS.register("crystallized_lapis_lazuli", BasicGaiaItem::new);
    public static final RegistryObject<Item> glint_and_gold = ITEMS.register("glint_and_gold", GlintAndGoldItem::new);
    public static final RegistryObject<Item> agate_stick = ITEMS.register("agate_stick", BasicGaiaItem::new);
    public static final RegistryObject<Item> hot_dust = ITEMS.register("hot_dust", () -> new BasicGaiaItem() {
        @Override
        public int getBurnTime(ItemStack stack) {
            return 100;
        }
    });
    public static final RegistryObject<Item> goldstone_dust = ITEMS.register("goldstone_dust", BasicGaiaItem::new);
    public static final RegistryObject<Item> fine_dust = ITEMS.register("fine_dust", BasicGaiaItem::new);
    public static final RegistryObject<Item> cloudy_shard = ITEMS.register("cloudy_shard", BasicGaiaItem::new);
    public static final RegistryObject<Item> agate_cup = ITEMS.register("agate_cup", BasicGaiaItem::new);
    public static final RegistryObject<Item> scaynyx_ingot = ITEMS.register("scaynyx_ingot", BasicGaiaItem::new);
    public static final RegistryObject<Item> sweet_muckball = ITEMS.register("sweet_muckball", BasicGaiaItem::new);
    public static final RegistryObject<Item> sugar_crystals = ITEMS.register("sugar_crystals", BasicGaiaItem::new);
    public static final RegistryObject<Item> sugar_cluster = ITEMS.register("sugar_cluster", BasicGaiaItem::new);
    public static final RegistryObject<Item> shiny_bone = ITEMS.register("shiny_bone", BasicGaiaItem::new);
    public static final RegistryObject<Item> fine_thread = ITEMS.register("fine_thread", BasicGaiaItem::new);
    public static final RegistryObject<Item> twined_thread = ITEMS.register("twined_thread", BasicGaiaItem::new);
    public static final RegistryObject<Item> pink_essence = ITEMS.register("pink_essence", BasicGaiaItem::new);
    public static final RegistryObject<Item> pink_goo = ITEMS.register("pink_goo", BasicGaiaItem::new);
    public static final RegistryObject<Item> gemstone_pouch = ITEMS.register("gemstone_pouch", GemstonePouchItem::new);
    public static final RegistryObject<Item> agate_fabric = ITEMS.register("agate_fabric", BasicGaiaItem::new);
    public static final RegistryObject<Item> sturdy_pebble = ITEMS.register("sturdy_pebble", SturdyPebbleItem::new);
    public static final RegistryObject<Item> scaynyx_bucket = ITEMS.register("scaynyx_bucket", () -> new ScaynyxBucketItem(() -> Fluids.EMPTY));
    public static final RegistryObject<Item> mineral_water_bucket = ITEMS.register("mineral_water_bucket", () -> new ScaynyxBucketItem(ModFluids.mineral_water_still::get));
    public static final RegistryObject<Item> superhot_magma_bucket = ITEMS.register("superhot_magma_bucket", () -> new ScaynyxBucketItem(ModFluids.superhot_magma_still::get));
    public static final RegistryObject<Item> sweet_muck_bucket = ITEMS.register("sweet_muck_bucket", () -> new ScaynyxBucketItem(ModFluids.sweet_muck_still::get));
    public static final RegistryObject<Item> liquid_bismuth_bucket = ITEMS.register("liquid_bismuth_bucket", () -> new ScaynyxBucketItem(ModFluids.liquid_bismuth_still::get));
    public static final RegistryObject<Item> liquid_aura_bucket = ITEMS.register("liquid_aura_bucket", () -> new ScaynyxBucketItem(ModFluids.liquid_aura_still::get));

    public static final RegistryObject<Item> pink_geode = ITEMS.register("pink_geode", BasicGaiaItem::new);
    public static final RegistryObject<Item> blue_geode = ITEMS.register("blue_geode", BasicGaiaItem::new);
    public static final RegistryObject<Item> green_geode = ITEMS.register("green_geode", BasicGaiaItem::new);
    public static final RegistryObject<Item> purple_geode = ITEMS.register("purple_geode", BasicGaiaItem::new);
    public static final RegistryObject<Item> pink_geode_slice = ITEMS.register("pink_geode_slice", () -> new GeodeSliceItem(GaiaFoods.PINK_SLICE));
    public static final RegistryObject<Item> blue_geode_slice = ITEMS.register("blue_geode_slice", () -> new GeodeSliceItem(GaiaFoods.BLUE_SLICE));
    public static final RegistryObject<Item> green_geode_slice = ITEMS.register("green_geode_slice", () -> new GeodeSliceItem(GaiaFoods.GREEN_SLICE));
    public static final RegistryObject<Item> purple_geode_slice = ITEMS.register("purple_geode_slice", () -> new GeodeSliceItem(GaiaFoods.PURPLE_SLICE));
    public static final RegistryObject<Item> pink_geode_juice = ITEMS.register("pink_geode_juice", () -> new GeodeJuiceItem(GaiaFoods.PINK_JUICE));
    public static final RegistryObject<Item> blue_geode_tea = ITEMS.register("blue_geode_tea", () -> new GeodeJuiceItem(GaiaFoods.BLUE_TEA));
    public static final RegistryObject<Item> green_geode_ale = ITEMS.register("green_geode_ale", () -> new GeodeJuiceItem(GaiaFoods.GREEN_ALE));
    public static final RegistryObject<Item> purple_geode_soda = ITEMS.register("purple_geode_soda", () -> new GeodeJuiceItem(GaiaFoods.PURPLE_SODA));
    public static final RegistryObject<Item> pearly_geode_elixir = ITEMS.register("pearly_geode_elixir", () -> new GeodeJuiceItem(GaiaFoods.PEARLY_ELIXIR));
    public static final RegistryObject<Item> lurmorus_meat = ITEMS.register("lurmorus_meat", () -> new BasicGaiaItem(GaiaFoods.LURMORUS_MEAT));
    public static final RegistryObject<Item> lurmorus_steak = ITEMS.register("lurmorus_steak", () -> new BasicGaiaItem(GaiaFoods.LURMORUS_STEAK));
    public static final RegistryObject<Item> small_tentacle = ITEMS.register("small_tentacle", () -> new BasicGaiaItem(GaiaFoods.SMALL_TENTACLE));
    public static final RegistryObject<Item> small_calamari = ITEMS.register("small_calamari", () -> new BasicGaiaItem(GaiaFoods.SMALL_CALAMARI));
    public static final RegistryObject<Item> large_tentacle = ITEMS.register("large_tentacle", () -> new BasicGaiaItem(GaiaFoods.LARGE_TENTACLE));
    public static final RegistryObject<Item> large_calamari = ITEMS.register("large_calamari", () -> new BasicGaiaItem(GaiaFoods.LARGE_CALAMARI));
    public static final RegistryObject<Item> markuzar_mint = ITEMS.register("markuzar_mint", () -> new BasicGaiaItem(GaiaFoods.MARKUZAR_MINT));
    public static final RegistryObject<Item> luggeroth_chop = ITEMS.register("luggeroth_chop", () -> new BasicGaiaItem(GaiaFoods.LUGGEROTH_CHOP));
    public static final RegistryObject<Item> cooked_luggeroth_chop = ITEMS.register("cooked_luggeroth_chop", () -> new BasicGaiaItem(GaiaFoods.COOKED_LUGGEROTH_CHOP));
    public static final RegistryObject<Item> tilipi = ITEMS.register("tilipi", () -> new BasicGaiaItem(GaiaFoods.TILIPI));
    public static final RegistryObject<Item> tilibl = ITEMS.register("tilibl", () -> new BasicGaiaItem(GaiaFoods.TILIBL));
    public static final RegistryObject<Item> tiligr = ITEMS.register("tiligr", () -> new BasicGaiaItem(GaiaFoods.TILIGR));
    public static final RegistryObject<Item> tilipu = ITEMS.register("tilipu", () -> new BasicGaiaItem(GaiaFoods.TILIPU));
    public static final RegistryObject<Item> tiliol = ITEMS.register("tiliol", () -> new BasicGaiaItem(GaiaFoods.TILIOL));
    public static final RegistryObject<Item> tilimy = ITEMS.register("tilimy", () -> new BasicGaiaItem(GaiaFoods.TILIMY));
    public static final RegistryObject<Item> plagued_tiliey = ITEMS.register("plagued_tiliey", () -> new BasicGaiaItem(GaiaFoods.PLAGUED_TILIEY));
    public static final RegistryObject<Item> tiliou = ITEMS.register("tiliou", () -> new BasicGaiaItem(GaiaFoods.TILIOU));

    public static final RegistryObject<Item> hematite_powder = ITEMS.register("hematite_powder", GroundGemItem::new);
    public static final RegistryObject<Item> cinnabar_powder = ITEMS.register("cinnabar_powder", GroundGemItem::new);
    public static final RegistryObject<Item> labradorite_powder = ITEMS.register("labradorite_powder", GroundGemItem::new);
    public static final RegistryObject<Item> moonstone_powder = ITEMS.register("moonstone_powder", GroundGemItem::new);
    public static final RegistryObject<Item> red_opal_powder = ITEMS.register("red_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> blue_opal_powder = ITEMS.register("blue_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> green_opal_powder = ITEMS.register("green_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> white_opal_grit = ITEMS.register("white_opal_grit", GroundGemItem::new);
    public static final RegistryObject<Item> pyrite_powder = ITEMS.register("pyrite_powder", GroundGemItem::new);

    public static final RegistryObject<Item> sugilite = ITEMS.register("sugilite", BasicGaiaItem::new);
    public static final RegistryObject<Item> hematite = ITEMS.register("hematite", BasicGaiaItem::new);
    public static final RegistryObject<Item> cinnabar = ITEMS.register("cinnabar", BasicGaiaItem::new);
    public static final RegistryObject<Item> labradorite = ITEMS.register("labradorite", BasicGaiaItem::new);
    public static final RegistryObject<Item> moonstone = ITEMS.register("moonstone", BasicGaiaItem::new);
    public static final RegistryObject<Item> red_opal = ITEMS.register("red_opal", BasicGaiaItem::new);
    public static final RegistryObject<Item> blue_opal = ITEMS.register("blue_opal", BasicGaiaItem::new);
    public static final RegistryObject<Item> green_opal = ITEMS.register("green_opal", BasicGaiaItem::new);
    public static final RegistryObject<Item> white_opal = ITEMS.register("white_opal", BasicGaiaItem::new);
    public static final RegistryObject<Item> ixiolite = ITEMS.register("ixiolite", BasicGaiaItem::new);
    public static final RegistryObject<Item> proustite = ITEMS.register("proustite", BasicGaiaItem::new);
    public static final RegistryObject<Item> euclase = ITEMS.register("euclase", BasicGaiaItem::new);
    public static final RegistryObject<Item> leucite = ITEMS.register("leucite", BasicGaiaItem::new);
    public static final RegistryObject<Item> carnelian = ITEMS.register("carnelian", BasicGaiaItem::new);
    public static final RegistryObject<Item> benitoite = ITEMS.register("benitoite", BasicGaiaItem::new);
    public static final RegistryObject<Item> diopside = ITEMS.register("diopside", BasicGaiaItem::new);
    public static final RegistryObject<Item> chalcedony = ITEMS.register("chalcedony", BasicGaiaItem::new);
    public static final RegistryObject<Item> pyrite = ITEMS.register("pyrite", BasicGaiaItem::new);
    public static final RegistryObject<Item> black_residue = ITEMS.register("black_residue", BasicGaiaItem::new);
    public static final RegistryObject<Item> tektite = ITEMS.register("tektite", BasicGaiaItem::new);
    public static final RegistryObject<Item> goldstone_residue = ITEMS.register("goldstone_residue", BasicGaiaItem::new);
    public static final RegistryObject<Item> goldstone = ITEMS.register("goldstone", BasicGaiaItem::new);
    public static final RegistryObject<Item> aura_residue = ITEMS.register("aura_residue", BasicGaiaItem::new);
    public static final RegistryObject<Item> aura_cluster = ITEMS.register("aura_cluster", BasicGaiaItem::new);
    public static final RegistryObject<Item> bismuth_residue = ITEMS.register("bismuth_residue", BasicGaiaItem::new);
    public static final RegistryObject<Item> bismuth_crystal = ITEMS.register("bismuth_crystal", BasicGaiaItem::new);

    public static final RegistryObject<Item> sugilite_helmet = ITEMS.register("sugilite_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.SUGILITE, HEAD));
    public static final RegistryObject<Item> sugilite_chestplate = ITEMS.register("sugilite_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.SUGILITE, CHEST));
    public static final RegistryObject<Item> sugilite_legs = ITEMS.register("sugilite_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.SUGILITE, LEGS));
    public static final RegistryObject<Item> sugilite_boots = ITEMS.register("sugilite_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.SUGILITE, FEET));
    public static final RegistryObject<Item> proustite_helmet = ITEMS.register("proustite_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.PROUSTITE, HEAD));
    public static final RegistryObject<Item> proustite_chestplate = ITEMS.register("proustite_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.PROUSTITE, CHEST));
    public static final RegistryObject<Item> proustite_legs = ITEMS.register("proustite_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.PROUSTITE, LEGS));
    public static final RegistryObject<Item> proustite_boots = ITEMS.register("proustite_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.PROUSTITE, FEET));
    public static final RegistryObject<Item> leucite_helmet = ITEMS.register("leucite_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.LEUCITE, HEAD));
    public static final RegistryObject<Item> leucite_chestplate = ITEMS.register("leucite_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.LEUCITE, CHEST));
    public static final RegistryObject<Item> leucite_legs = ITEMS.register("leucite_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.LEUCITE, LEGS));
    public static final RegistryObject<Item> leucite_boots = ITEMS.register("leucite_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.LEUCITE, FEET));
    public static final RegistryObject<Item> carnelian_helmet = ITEMS.register("carnelian_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CARNELIAN, HEAD));
    public static final RegistryObject<Item> carnelian_chestplate = ITEMS.register("carnelian_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CARNELIAN, CHEST));
    public static final RegistryObject<Item> carnelian_legs = ITEMS.register("carnelian_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CARNELIAN, LEGS));
    public static final RegistryObject<Item> carnelian_boots = ITEMS.register("carnelian_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CARNELIAN, FEET));
    public static final RegistryObject<Item> diopside_helmet = ITEMS.register("diopside_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.DIOPSIDE, HEAD));
    public static final RegistryObject<Item> diopside_chestplate = ITEMS.register("diopside_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.DIOPSIDE, CHEST));
    public static final RegistryObject<Item> diopside_legs = ITEMS.register("diopside_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.DIOPSIDE, LEGS));
    public static final RegistryObject<Item> diopside_boots = ITEMS.register("diopside_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.DIOPSIDE, FEET));
    public static final RegistryObject<Item> chalcedony_helmet = ITEMS.register("chalcedony_helmet", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CHALCEDONY, HEAD));
    public static final RegistryObject<Item> chalcedony_chestplate = ITEMS.register("chalcedony_chestplate", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CHALCEDONY, CHEST));
    public static final RegistryObject<Item> chalcedony_legs = ITEMS.register("chalcedony_legs", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CHALCEDONY, LEGS));
    public static final RegistryObject<Item> chalcedony_boots = ITEMS.register("chalcedony_boots", () -> new BasicGaiaArmorItem(GaiaArmorMaterials.CHALCEDONY, FEET));

    public static final RegistryObject<Item> malachite_guard_headgear = ITEMS.register("malachite_guard_headgear", () -> new MalachiteGuardArmorItem(GaiaArmorMaterials.MALACHITE, HEAD));
    public static final RegistryObject<Item> malachite_guard_brace = ITEMS.register("malachite_guard_brace", () -> new MalachiteGuardArmorItem(GaiaArmorMaterials.MALACHITE, CHEST));
    public static final RegistryObject<Item> malachite_guard_gear = ITEMS.register("malachite_guard_gear", () -> new MalachiteGuardArmorItem(GaiaArmorMaterials.MALACHITE, LEGS));
    public static final RegistryObject<Item> malachite_guard_boots = ITEMS.register("malachite_guard_boots", () -> new MalachiteGuardArmorItem(GaiaArmorMaterials.MALACHITE, FEET));
    public static final RegistryObject<Item> apex_predator_hood = ITEMS.register("apex_predator_hood", () -> new ApexPredatorArmorItem(GaiaArmorMaterials.TIGER_EYE, HEAD));
    public static final RegistryObject<Item> apex_predator_jacket = ITEMS.register("apex_predator_jacket", () -> new ApexPredatorArmorItem(GaiaArmorMaterials.TIGER_EYE, CHEST));
    public static final RegistryObject<Item> apex_predator_trousers = ITEMS.register("apex_predator_trousers", () -> new ApexPredatorArmorItem(GaiaArmorMaterials.TIGER_EYE, LEGS));
    public static final RegistryObject<Item> apex_predator_boots = ITEMS.register("apex_predator_boots", () -> new ApexPredatorArmorItem(GaiaArmorMaterials.TIGER_EYE, FEET));
    public static final RegistryObject<Item> spinel_princess_cowl = ITEMS.register("spinel_princess_cowl", () -> new SpinelPrincessArmorItem(GaiaArmorMaterials.SPINEL, HEAD));
    public static final RegistryObject<Item> spinel_princess_cloak = ITEMS.register("spinel_princess_cloak", () -> new SpinelPrincessArmorItem(GaiaArmorMaterials.SPINEL, CHEST));
    public static final RegistryObject<Item> spinel_princess_dress = ITEMS.register("spinel_princess_dress", () -> new SpinelPrincessArmorItem(GaiaArmorMaterials.SPINEL, LEGS));
    public static final RegistryObject<Item> spinel_princess_heels = ITEMS.register("spinel_princess_heels", () -> new SpinelPrincessArmorItem(GaiaArmorMaterials.SPINEL, FEET));
    public static final RegistryObject<Item> zircon_prince_crown = ITEMS.register("zircon_prince_crown", () -> new ZirconPrinceArmorItem(GaiaArmorMaterials.ZIRCON, HEAD));
    public static final RegistryObject<Item> zircon_prince_chestpiece = ITEMS.register("zircon_prince_chestpiece", () -> new ZirconPrinceArmorItem(GaiaArmorMaterials.ZIRCON, CHEST));
    public static final RegistryObject<Item> zircon_prince_gear = ITEMS.register("zircon_prince_gear", () -> new ZirconPrinceArmorItem(GaiaArmorMaterials.ZIRCON, LEGS));
    public static final RegistryObject<Item> zircon_prince_boots = ITEMS.register("zircon_prince_boots", () -> new ZirconPrinceArmorItem(GaiaArmorMaterials.ZIRCON, FEET));
    public static final RegistryObject<Item> corrupt_warrior_helm = ITEMS.register("corrupt_warrior_helm", () -> new CorruptWarriorArmorItem(GaiaArmorMaterials.CORRUPT, HEAD));
    public static final RegistryObject<Item> corrupt_warrior_guard = ITEMS.register("corrupt_warrior_guard", () -> new CorruptWarriorArmorItem(GaiaArmorMaterials.CORRUPT, CHEST));
    public static final RegistryObject<Item> corrupt_warrior_greaves = ITEMS.register("corrupt_warrior_greaves", () -> new CorruptWarriorArmorItem(GaiaArmorMaterials.CORRUPT, LEGS));
    public static final RegistryObject<Item> corrupt_warrior_boots = ITEMS.register("corrupt_warrior_boots", () -> new CorruptWarriorArmorItem(GaiaArmorMaterials.CORRUPT, FEET));
    public static final RegistryObject<Item> gaia_duchess_helm = ITEMS.register("gaia_duchess_helm", () -> new GaiaDuchessArmorItem(GaiaArmorMaterials.BIXBITE, HEAD));
    public static final RegistryObject<Item> gaia_duchess_guard = ITEMS.register("gaia_duchess_guard", () -> new GaiaDuchessArmorItem(GaiaArmorMaterials.BIXBITE, CHEST));
    public static final RegistryObject<Item> gaia_duchess_greaves = ITEMS.register("gaia_duchess_greaves", () -> new GaiaDuchessArmorItem(GaiaArmorMaterials.BIXBITE, LEGS));
    public static final RegistryObject<Item> gaia_duchess_boots = ITEMS.register("gaia_duchess_boots", () -> new GaiaDuchessArmorItem(GaiaArmorMaterials.BIXBITE, FEET));
    public static final RegistryObject<Item> gaia_baron_mask = ITEMS.register("gaia_baron_mask", () -> new GaiaBaronArmorItem(GaiaArmorMaterials.TSAVORITE, HEAD));
    public static final RegistryObject<Item> gaia_baron_tuxedo = ITEMS.register("gaia_baron_tuxedo", () -> new GaiaBaronArmorItem(GaiaArmorMaterials.TSAVORITE, CHEST));
    public static final RegistryObject<Item> gaia_baron_pants = ITEMS.register("gaia_baron_pants", () -> new GaiaBaronArmorItem(GaiaArmorMaterials.TSAVORITE, LEGS));
    public static final RegistryObject<Item> gaia_baron_shoes = ITEMS.register("gaia_baron_shoes", () -> new GaiaBaronArmorItem(GaiaArmorMaterials.TSAVORITE, FEET));
    public static final RegistryObject<Item> gaia_duke_helm = ITEMS.register("gaia_duke_helm", () -> new GaiaDukeArmorItem(GaiaArmorMaterials.LARVIKITE, HEAD));
    public static final RegistryObject<Item> gaia_duke_guard = ITEMS.register("gaia_duke_guard", () -> new GaiaDukeArmorItem(GaiaArmorMaterials.LARVIKITE, CHEST));
    public static final RegistryObject<Item> gaia_duke_greaves = ITEMS.register("gaia_duke_greaves", () -> new GaiaDukeArmorItem(GaiaArmorMaterials.LARVIKITE, LEGS));
    public static final RegistryObject<Item> gaia_duke_boots = ITEMS.register("gaia_duke_boots", () -> new GaiaDukeArmorItem(GaiaArmorMaterials.LARVIKITE, FEET));
    public static final RegistryObject<Item> gaia_champion_helm = ITEMS.register("gaia_champion_helm", () -> new GaiaChampArmorItem(GaiaArmorMaterials.GAIA_CHAMP, HEAD));
    public static final RegistryObject<Item> gaia_champion_guard = ITEMS.register("gaia_champion_guard", () -> new GaiaChampArmorItem(GaiaArmorMaterials.GAIA_CHAMP, CHEST));
    public static final RegistryObject<Item> gaia_champion_greaves = ITEMS.register("gaia_champion_greaves", () -> new GaiaChampArmorItem(GaiaArmorMaterials.GAIA_CHAMP, LEGS));
    public static final RegistryObject<Item> gaia_champion_boots = ITEMS.register("gaia_champion_boots", () -> new GaiaChampArmorItem(GaiaArmorMaterials.GAIA_CHAMP, FEET));

    public static final RegistryObject<Item> agate_sword = ITEMS.register("agate_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.AGATE));
    public static final RegistryObject<Item> agate_pickaxe = ITEMS.register("agate_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.AGATE));
    public static final RegistryObject<Item> agate_axe = ITEMS.register("agate_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.AGATE));
    public static final RegistryObject<Item> agate_shovel = ITEMS.register("agate_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.AGATE));
    public static final RegistryObject<Item> sugilite_sword = ITEMS.register("sugilite_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.SUGILITE));
    public static final RegistryObject<Item> sugilite_pickaxe = ITEMS.register("sugilite_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.SUGILITE));
    public static final RegistryObject<Item> sugilite_axe = ITEMS.register("sugilite_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.SUGILITE));
    public static final RegistryObject<Item> sugilite_shovel = ITEMS.register("sugilite_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.SUGILITE));
    public static final RegistryObject<Item> ixiolite_sword = ITEMS.register("ixiolite_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.IXIOLITE));
    public static final RegistryObject<Item> ixiolite_pickaxe = ITEMS.register("ixiolite_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.IXIOLITE));
    public static final RegistryObject<Item> ixiolite_axe = ITEMS.register("ixiolite_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.IXIOLITE));
    public static final RegistryObject<Item> ixiolite_shovel = ITEMS.register("ixiolite_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.IXIOLITE));
    public static final RegistryObject<Item> euclase_sword = ITEMS.register("euclase_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.EUCLASE));
    public static final RegistryObject<Item> euclase_pickaxe = ITEMS.register("euclase_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.EUCLASE));
    public static final RegistryObject<Item> euclase_axe = ITEMS.register("euclase_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.EUCLASE));
    public static final RegistryObject<Item> euclase_shovel = ITEMS.register("euclase_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.EUCLASE));
    public static final RegistryObject<Item> carnelian_sword = ITEMS.register("carnelian_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.CARNELIAN));
    public static final RegistryObject<Item> carnelian_pickaxe = ITEMS.register("carnelian_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.CARNELIAN));
    public static final RegistryObject<Item> carnelian_axe = ITEMS.register("carnelian_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.CARNELIAN));
    public static final RegistryObject<Item> carnelian_shovel = ITEMS.register("carnelian_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.CARNELIAN));
    public static final RegistryObject<Item> benitoite_sword = ITEMS.register("benitoite_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.BENITOITE));
    public static final RegistryObject<Item> benitoite_pickaxe = ITEMS.register("benitoite_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.BENITOITE));
    public static final RegistryObject<Item> benitoite_axe = ITEMS.register("benitoite_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.BENITOITE));
    public static final RegistryObject<Item> benitoite_shovel = ITEMS.register("benitoite_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.BENITOITE));
    public static final RegistryObject<Item> chalcedony_sword = ITEMS.register("chalcedony_sword", () -> new BasicGaiaSwordItem(GaiaToolMaterials.CHALCEDONY));
    public static final RegistryObject<Item> chalcedony_pickaxe = ITEMS.register("chalcedony_pickaxe", () -> new BasicGaiaPickaxeItem(GaiaToolMaterials.CHALCEDONY));
    public static final RegistryObject<Item> chalcedony_axe = ITEMS.register("chalcedony_axe", () -> new BasicGaiaAxeItem(GaiaToolMaterials.CHALCEDONY));
    public static final RegistryObject<Item> chalcedony_shovel = ITEMS.register("chalcedony_shovel", () -> new BasicGaiaShovelItem(GaiaToolMaterials.CHALCEDONY));
    public static final RegistryObject<Item> old_bow = ITEMS.register("old_bow", OldBowItem::new);
    public static final RegistryObject<Item> agate_arrow = ITEMS.register("agate_arrow", AgateArrowItem::new);

    public static final RegistryObject<Item> malachite_guard_baton = ITEMS.register("malachite_guard_baton", MalachiteGuardSwordItem::new);
    public static final RegistryObject<Item> apex_predator_mace = ITEMS.register("apex_predator_mace", ApexPredatorSwordItem::new);
    public static final RegistryObject<Item> spinel_princess_flamberge = ITEMS.register("spinel_princess_flamberge", SpinelPrincessSwordItem::new);
    public static final RegistryObject<Item> zircon_prince_razor = ITEMS.register("zircon_prince_razor", ZirconPrinceSwordItem::new);
    public static final RegistryObject<Item> corrupt_warrior_sword = ITEMS.register("corrupt_warrior_sword", CorruptWarriorSwordItem::new);
    public static final RegistryObject<Item> gaia_duchess_khopesh = ITEMS.register("gaia_duchess_khopesh", GaiaDuchessSwordItem::new);
    public static final RegistryObject<Item> gaia_baron_dagger = ITEMS.register("gaia_baron_dagger", GaiaBaronSwordItem::new);
    public static final RegistryObject<Item> gaia_duke_blade = ITEMS.register("gaia_duke_blade", GaiaDukeSwordItem::new);
    public static final RegistryObject<Item> gaia_champion_sword = ITEMS.register("gaia_champion_sword", GaiaChampSwordItem::new);

    public static final RegistryObject<Item> growth_sapper_spawn_egg = registerSpawnEgg("growth_sapper", ModEntities.growth_sapper, 0x5A4514, 0xFF00FF);
    public static final RegistryObject<Item> mutant_growth_extractor_spawn_egg = registerSpawnEgg("mutant_growth_extractor", ModEntities.growth_extractor, 0x5A4514, 0xFFFFCC);
    public static final RegistryObject<Item> howlite_wolf_spawn_egg = registerSpawnEgg("howlite_wolf", ModEntities.howlite_wolf, 0xDDDDDD, 0x3333FF);
    public static final RegistryObject<Item> spellbound_elemental_spawn_egg = registerSpawnEgg("spellbound_elemental", ModEntities.spell_elemental, 0x885555, 0xCC33CC);
    public static final RegistryObject<Item> rocky_luggeroth_spawn_egg = registerSpawnEgg("rocky_luggeroth", ModEntities.rocky_luggeroth, 0xB07700, 0xCC9900);
    public static final RegistryObject<Item> shalurker_spawn_egg = registerSpawnEgg("shalurker", ModEntities.shalurker, 0x771177, 0x000000);
    public static final RegistryObject<Item> muckling_spawn_egg = registerSpawnEgg("muckling", ModEntities.muckling, 0xFF00FF, 0xCC66CC);
    public static final RegistryObject<Item> markuzar_plant_spawn_egg = registerSpawnEgg("markuzar_plant", ModEntities.markuzar_plant, 0x00FF66, 0xCC00FF);
    public static final RegistryObject<Item> rugged_lurmorus_spawn_egg = registerSpawnEgg("rugged_lurmorus", ModEntities.rugged_lurmorus, 0xCC9933, 0xFF6600);
    public static final RegistryObject<Item> agate_golem_spawn_egg = registerSpawnEgg("agate_golem", ModEntities.agate_golem, 0x660000, 0xBB5555);
    public static final RegistryObject<Item> ancient_lagrahk_spawn_egg = registerSpawnEgg("ancient_lagrahk", ModEntities.ancient_lagrahk, 0x772200, 0xAA5500);
    public static final RegistryObject<Item> crystal_golem_spawn_egg = registerSpawnEgg("crystal_golem", ModEntities.crystal_golem, 0xFF66CC, 0xFF99CC);
    public static final RegistryObject<Item> saltion_spawn_egg = registerSpawnEgg("saltion", ModEntities.saltion, 0x6699FF, 0x6633FF);
    public static final RegistryObject<Item> nomadic_lagrahk_spawn_egg = registerSpawnEgg("nomadic_lagrahk", ModEntities.nomadic_lagrahk, 0x3366CC, 0x232323);
    public static final RegistryObject<Item> shallow_arenthis_spawn_egg = registerSpawnEgg("shallow_arenthis", ModEntities.shallow_arenthis, 0x6699CC, 0x003399);
    public static final RegistryObject<Item> corrupt_sapper_spawn_egg = registerSpawnEgg("corrupt_sapper", ModEntities.corrupt_sapper, 0x202020, 0xCC3300);
    public static final RegistryObject<Item> contorted_naga_spawn_egg = registerSpawnEgg("contorted_naga", ModEntities.contorted_naga, 0x202020, 0xCC3300);
    public static final RegistryObject<Item> lesser_spitfire_spawn_egg = registerSpawnEgg("lesser_spitfire", ModEntities.lesser_spitfire, 0xFF00FF, 0x202020);
    public static final RegistryObject<Item> lesser_shockshooter_spawn_egg = registerSpawnEgg("lesser_shockshooter", ModEntities.lesser_shockshooter, 0x00FFFF, 0x202020);
    public static final RegistryObject<Item> mineral_arenthis_spawn_egg = registerSpawnEgg("mineral_arenthis", ModEntities.mineral_arenthis, 0x0066CC, 0x000033);
    public static final RegistryObject<Item> bismuth_uletrus_spawn_egg = registerSpawnEgg("bismuth_uletrus", ModEntities.bismuth_uletrus, 0x4E3863, 0x303030);
    public static final RegistryObject<Item> archaic_warrior_spawn_egg = registerSpawnEgg("archaic_warrior", ModEntities.archaic_warrior, 0x996699, 0xCC3366);
    public static final RegistryObject<Item> primal_beast_spawn_egg = registerSpawnEgg("primal_beast", ModEntities.primal_beast, 0x006699, 0x66FFFF);
    public static final RegistryObject<Item> cavern_tick_spawn_egg = registerSpawnEgg("cavern_tick", ModEntities.cavern_tick, 0x9966CC, 0x666699);
    public static final RegistryObject<Item> blue_howlite_wolf_spawn_egg = registerSpawnEgg("blue_howlite_wolf", ModEntities.blue_howlite_wolf, 0x0099CC, 0xCC00FF);
    public static final RegistryObject<Item> malachite_guard_spawn_egg = registerSpawnEgg("malachite_guard", ModEntities.malachite_guard, 0x339900, 0x33CC99);

    public static final RegistryObject<Item> PYRITE_TORCH = ITEMS.register("pyrite_torch",
            () -> new WallOrFloorItem(ModBlocks.pyrite_torch.get(), ModBlocks.pyrite_wall_torch.get(), new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS)));

    private static RegistryObject<Item> registerSpawnEgg(String name, EntityType<?> entity, int back, int front) {
        return ITEMS.register(name + "_spawn_egg", () -> new GaiaSpawnEggItem(entity, back, front));
    }
}
