package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

import static net.minecraft.inventory.EquipmentSlotType.*;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GaiaDimensionMod.MODID);

    public static final RegistryObject<Item> crystallized_redstone = register("crystallized_redstone");
    public static final RegistryObject<Item> crystallized_lapis_lazuli = register("crystallized_lapis_lazuli");
    public static final RegistryObject<Item> glint_and_gold = register("glint_and_gold", GlintAndGoldItem::new);
    public static final RegistryObject<Item> agate_stick = register("agate_stick");
    public static final RegistryObject<Item> hot_dust = register("hot_dust", () -> new BasicGaiaItem() {
        @Override
        public int getBurnTime(ItemStack stack) {
            return 100;
        }
    });
    public static final RegistryObject<Item> goldstone_dust = register("goldstone_dust");
    public static final RegistryObject<Item> fine_dust = register("fine_dust");
    public static final RegistryObject<Item> cloudy_shard = register("cloudy_shard");
    public static final RegistryObject<Item> agate_cup = register("agate_cup");
    public static final RegistryObject<Item> scaynyx_ingot = register("scaynyx_ingot");
    public static final RegistryObject<Item> sweet_muckball = register("sweet_muckball");
    public static final RegistryObject<Item> sugar_crystals = register("sugar_crystals");
    public static final RegistryObject<Item> sugar_cluster = register("sugar_cluster");
    public static final RegistryObject<Item> shiny_bone = register("shiny_bone");
    public static final RegistryObject<Item> fine_thread = register("fine_thread");
    public static final RegistryObject<Item> twined_thread = register("twined_thread");
    public static final RegistryObject<Item> pink_essence = register("pink_essence");
    public static final RegistryObject<Item> pink_goo = register("pink_goo");
    public static final RegistryObject<Item> gemstone_pouch = register("gemstone_pouch", GemstonePouchItem::new);
    public static final RegistryObject<Item> agate_fabric = register("agate_fabric");
    public static final RegistryObject<Item> sturdy_pebble = register("sturdy_pebble", SturdyPebbleItem::new);
    public static final RegistryObject<Item> scaynyx_bucket = registerBucket("scaynyx_bucket", () -> Fluids.EMPTY);
    public static final RegistryObject<Item> mineral_water_bucket = registerBucket("mineral_water_bucket", ModFluids.mineral_water_still);
    public static final RegistryObject<Item> superhot_magma_bucket = registerBucket("superhot_magma_bucket", ModFluids.superhot_magma_still);
    public static final RegistryObject<Item> sweet_muck_bucket = registerBucket("sweet_muck_bucket", ModFluids.sweet_muck_still);
    public static final RegistryObject<Item> liquid_bismuth_bucket = registerBucket("liquid_bismuth_bucket", ModFluids.liquid_bismuth_still);
    public static final RegistryObject<Item> liquid_aura_bucket = registerBucket("liquid_aura_bucket", ModFluids.liquid_aura_still);
    public static final RegistryObject<Item> crystal_shard = register("crystal_shard");

    public static final RegistryObject<Item> pink_geode = register("pink_geode");
    public static final RegistryObject<Item> blue_geode = register("blue_geode");
    public static final RegistryObject<Item> green_geode = register("green_geode");
    public static final RegistryObject<Item> purple_geode = register("purple_geode");
    public static final RegistryObject<Item> pink_geode_slice = register("pink_geode_slice", () -> new GeodeSliceItem(GaiaFoods.PINK_SLICE));
    public static final RegistryObject<Item> blue_geode_slice = register("blue_geode_slice", () -> new GeodeSliceItem(GaiaFoods.BLUE_SLICE));
    public static final RegistryObject<Item> green_geode_slice = register("green_geode_slice", () -> new GeodeSliceItem(GaiaFoods.GREEN_SLICE));
    public static final RegistryObject<Item> purple_geode_slice = register("purple_geode_slice", () -> new GeodeSliceItem(GaiaFoods.PURPLE_SLICE));
    public static final RegistryObject<Item> pink_geode_juice = register("pink_geode_juice", () -> new GeodeJuiceItem(GaiaFoods.PINK_JUICE));
    public static final RegistryObject<Item> blue_geode_tea = register("blue_geode_tea", () -> new GeodeJuiceItem(GaiaFoods.BLUE_TEA));
    public static final RegistryObject<Item> green_geode_ale = register("green_geode_ale", () -> new GeodeJuiceItem(GaiaFoods.GREEN_ALE));
    public static final RegistryObject<Item> purple_geode_soda = register("purple_geode_soda", () -> new GeodeJuiceItem(GaiaFoods.PURPLE_SODA));
    public static final RegistryObject<Item> pearly_geode_elixir = register("pearly_geode_elixir", () -> new GeodeJuiceItem(GaiaFoods.PEARLY_ELIXIR));
    public static final RegistryObject<Item> lurmorus_meat = register("lurmorus_meat", GaiaFoods.LURMORUS_MEAT);
    public static final RegistryObject<Item> lurmorus_steak = register("lurmorus_steak", GaiaFoods.LURMORUS_STEAK);
    public static final RegistryObject<Item> small_tentacle = register("small_tentacle", GaiaFoods.SMALL_TENTACLE);
    public static final RegistryObject<Item> small_calamari = register("small_calamari", GaiaFoods.SMALL_CALAMARI);
    public static final RegistryObject<Item> large_tentacle = register("large_tentacle", GaiaFoods.LARGE_TENTACLE);
    public static final RegistryObject<Item> large_calamari = register("large_calamari", GaiaFoods.LARGE_CALAMARI);
    public static final RegistryObject<Item> markuzar_mint = register("markuzar_mint", GaiaFoods.MARKUZAR_MINT);
    public static final RegistryObject<Item> luggeroth_chop = register("luggeroth_chop", GaiaFoods.LUGGEROTH_CHOP);
    public static final RegistryObject<Item> cooked_luggeroth_chop = register("cooked_luggeroth_chop", GaiaFoods.COOKED_LUGGEROTH_CHOP);
    public static final RegistryObject<Item> tilipi = register("tilipi", GaiaFoods.TILIPI);
    public static final RegistryObject<Item> tilibl = register("tilibl", GaiaFoods.TILIBL);
    public static final RegistryObject<Item> tiligr = register("tiligr", GaiaFoods.TILIGR);
    public static final RegistryObject<Item> tilipu = register("tilipu", GaiaFoods.TILIPU);
    public static final RegistryObject<Item> tiliol = register("tiliol", GaiaFoods.TILIOL);
    public static final RegistryObject<Item> tilimy = register("tilimy", GaiaFoods.TILIMY);
    public static final RegistryObject<Item> plagued_tiliey = register("plagued_tiliey", GaiaFoods.PLAGUED_TILIEY);
    public static final RegistryObject<Item> tiliou = register("tiliou", GaiaFoods.TILIOU);

    public static final RegistryObject<Item> hematite_powder = register("hematite_powder", GroundGemItem::new);
    public static final RegistryObject<Item> cinnabar_powder = register("cinnabar_powder", GroundGemItem::new);
    public static final RegistryObject<Item> labradorite_powder = register("labradorite_powder", GroundGemItem::new);
    public static final RegistryObject<Item> moonstone_powder = register("moonstone_powder", GroundGemItem::new);
    public static final RegistryObject<Item> red_opal_powder = register("red_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> blue_opal_powder = register("blue_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> green_opal_powder = register("green_opal_powder", GroundGemItem::new);
    public static final RegistryObject<Item> white_opal_grit = register("white_opal_grit", GroundGemItem::new);
    public static final RegistryObject<Item> pyrite_powder = register("pyrite_powder", GroundGemItem::new);

    public static final RegistryObject<Item> sugilite = register("sugilite");
    public static final RegistryObject<Item> hematite = register("hematite");
    public static final RegistryObject<Item> cinnabar = register("cinnabar");
    public static final RegistryObject<Item> labradorite = register("labradorite");
    public static final RegistryObject<Item> moonstone = register("moonstone");
    public static final RegistryObject<Item> red_opal = register("red_opal");
    public static final RegistryObject<Item> blue_opal = register("blue_opal");
    public static final RegistryObject<Item> green_opal = register("green_opal");
    public static final RegistryObject<Item> white_opal = register("white_opal");
    public static final RegistryObject<Item> ixiolite = register("ixiolite");
    public static final RegistryObject<Item> proustite = register("proustite");
    public static final RegistryObject<Item> euclase = register("euclase");
    public static final RegistryObject<Item> leucite = register("leucite");
    public static final RegistryObject<Item> carnelian = register("carnelian");
    public static final RegistryObject<Item> benitoite = register("benitoite");
    public static final RegistryObject<Item> diopside = register("diopside");
    public static final RegistryObject<Item> chalcedony = register("chalcedony");
    public static final RegistryObject<Item> pyrite = register("pyrite");
    public static final RegistryObject<Item> black_residue = register("black_residue");
    public static final RegistryObject<Item> tektite = register("tektite");
    public static final RegistryObject<Item> goldstone_residue = register("goldstone_residue");
    public static final RegistryObject<Item> goldstone = register("goldstone");
    public static final RegistryObject<Item> aura_residue = register("aura_residue");
    public static final RegistryObject<Item> aura_cluster = register("aura_cluster");
    public static final RegistryObject<Item> bismuth_residue = register("bismuth_residue");
    public static final RegistryObject<Item> bismuth_crystal = register("bismuth_crystal");

    public static final RegistryObject<Item> sugilite_helmet = register("sugilite_helmet", GaiaArmorMaterials.SUGILITE, HEAD);
    public static final RegistryObject<Item> sugilite_chestplate = register("sugilite_chestplate", GaiaArmorMaterials.SUGILITE, CHEST);
    public static final RegistryObject<Item> sugilite_legs = register("sugilite_legs", GaiaArmorMaterials.SUGILITE, LEGS);
    public static final RegistryObject<Item> sugilite_boots = register("sugilite_boots", GaiaArmorMaterials.SUGILITE, FEET);
    public static final RegistryObject<Item> proustite_helmet = register("proustite_helmet", GaiaArmorMaterials.PROUSTITE, HEAD);
    public static final RegistryObject<Item> proustite_chestplate = register("proustite_chestplate", GaiaArmorMaterials.PROUSTITE, CHEST);
    public static final RegistryObject<Item> proustite_legs = register("proustite_legs", GaiaArmorMaterials.PROUSTITE, LEGS);
    public static final RegistryObject<Item> proustite_boots = register("proustite_boots", GaiaArmorMaterials.PROUSTITE, FEET);
    public static final RegistryObject<Item> leucite_helmet = register("leucite_helmet", GaiaArmorMaterials.LEUCITE, HEAD);
    public static final RegistryObject<Item> leucite_chestplate = register("leucite_chestplate", GaiaArmorMaterials.LEUCITE, CHEST);
    public static final RegistryObject<Item> leucite_legs = register("leucite_legs", GaiaArmorMaterials.LEUCITE, LEGS);
    public static final RegistryObject<Item> leucite_boots = register("leucite_boots", GaiaArmorMaterials.LEUCITE, FEET);
    public static final RegistryObject<Item> carnelian_helmet = register("carnelian_helmet", GaiaArmorMaterials.CARNELIAN, HEAD);
    public static final RegistryObject<Item> carnelian_chestplate = register("carnelian_chestplate", GaiaArmorMaterials.CARNELIAN, CHEST);
    public static final RegistryObject<Item> carnelian_legs = register("carnelian_legs", GaiaArmorMaterials.CARNELIAN, LEGS);
    public static final RegistryObject<Item> carnelian_boots = register("carnelian_boots", GaiaArmorMaterials.CARNELIAN, FEET);
    public static final RegistryObject<Item> diopside_helmet = register("diopside_helmet", GaiaArmorMaterials.DIOPSIDE, HEAD);
    public static final RegistryObject<Item> diopside_chestplate = register("diopside_chestplate", GaiaArmorMaterials.DIOPSIDE, CHEST);
    public static final RegistryObject<Item> diopside_legs = register("diopside_legs", GaiaArmorMaterials.DIOPSIDE, LEGS);
    public static final RegistryObject<Item> diopside_boots = register("diopside_boots", GaiaArmorMaterials.DIOPSIDE, FEET);
    public static final RegistryObject<Item> chalcedony_helmet = register("chalcedony_helmet", GaiaArmorMaterials.CHALCEDONY, HEAD);
    public static final RegistryObject<Item> chalcedony_chestplate = register("chalcedony_chestplate", GaiaArmorMaterials.CHALCEDONY, CHEST);
    public static final RegistryObject<Item> chalcedony_legs = register("chalcedony_legs", GaiaArmorMaterials.CHALCEDONY, LEGS);
    public static final RegistryObject<Item> chalcedony_boots = register("chalcedony_boots", GaiaArmorMaterials.CHALCEDONY, FEET);

    public static final RegistryObject<Item> malachite_guard_headgear = register("malachite_guard_headgear", () -> new MalachiteGuardArmorItem(HEAD));
    public static final RegistryObject<Item> malachite_guard_brace = register("malachite_guard_brace", () -> new MalachiteGuardArmorItem(CHEST));
    public static final RegistryObject<Item> malachite_guard_gear = register("malachite_guard_gear", () -> new MalachiteGuardArmorItem(LEGS));
    public static final RegistryObject<Item> malachite_guard_boots = register("malachite_guard_boots", () -> new MalachiteGuardArmorItem(FEET));
    public static final RegistryObject<Item> apex_predator_hood = register("apex_predator_hood", () -> new ApexPredatorArmorItem(HEAD));
    public static final RegistryObject<Item> apex_predator_jacket = register("apex_predator_jacket", () -> new ApexPredatorArmorItem(CHEST));
    public static final RegistryObject<Item> apex_predator_trousers = register("apex_predator_trousers", () -> new ApexPredatorArmorItem(LEGS));
    public static final RegistryObject<Item> apex_predator_boots = register("apex_predator_boots", () -> new ApexPredatorArmorItem(FEET));
    public static final RegistryObject<Item> spinel_princess_cowl = register("spinel_princess_cowl", () -> new SpinelPrincessArmorItem(HEAD));
    public static final RegistryObject<Item> spinel_princess_cloak = register("spinel_princess_cloak", () -> new SpinelPrincessArmorItem(CHEST));
    public static final RegistryObject<Item> spinel_princess_dress = register("spinel_princess_dress", () -> new SpinelPrincessArmorItem(LEGS));
    public static final RegistryObject<Item> spinel_princess_heels = register("spinel_princess_heels", () -> new SpinelPrincessArmorItem(FEET));
    public static final RegistryObject<Item> zircon_prince_crown = register("zircon_prince_crown", () -> new ZirconPrinceArmorItem(HEAD));
    public static final RegistryObject<Item> zircon_prince_chestpiece = register("zircon_prince_chestpiece", () -> new ZirconPrinceArmorItem(CHEST));
    public static final RegistryObject<Item> zircon_prince_gear = register("zircon_prince_gear", () -> new ZirconPrinceArmorItem(LEGS));
    public static final RegistryObject<Item> zircon_prince_boots = register("zircon_prince_boots", () -> new ZirconPrinceArmorItem(FEET));
    public static final RegistryObject<Item> corrupt_warrior_helm = register("corrupt_warrior_helm", () -> new CorruptWarriorArmorItem(HEAD));
    public static final RegistryObject<Item> corrupt_warrior_guard = register("corrupt_warrior_guard", () -> new CorruptWarriorArmorItem(CHEST));
    public static final RegistryObject<Item> corrupt_warrior_greaves = register("corrupt_warrior_greaves", () -> new CorruptWarriorArmorItem(LEGS));
    public static final RegistryObject<Item> corrupt_warrior_boots = register("corrupt_warrior_boots", () -> new CorruptWarriorArmorItem(FEET));
    public static final RegistryObject<Item> gaia_duchess_helm = register("gaia_duchess_helm", () -> new GaiaDuchessArmorItem(HEAD));
    public static final RegistryObject<Item> gaia_duchess_guard = register("gaia_duchess_guard", () -> new GaiaDuchessArmorItem(CHEST));
    public static final RegistryObject<Item> gaia_duchess_greaves = register("gaia_duchess_greaves", () -> new GaiaDuchessArmorItem(LEGS));
    public static final RegistryObject<Item> gaia_duchess_boots = register("gaia_duchess_boots", () -> new GaiaDuchessArmorItem(FEET));
    public static final RegistryObject<Item> gaia_baron_mask = register("gaia_baron_mask", () -> new GaiaBaronArmorItem(HEAD));
    public static final RegistryObject<Item> gaia_baron_tuxedo = register("gaia_baron_tuxedo", () -> new GaiaBaronArmorItem(CHEST));
    public static final RegistryObject<Item> gaia_baron_pants = register("gaia_baron_pants", () -> new GaiaBaronArmorItem(LEGS));
    public static final RegistryObject<Item> gaia_baron_shoes = register("gaia_baron_shoes", () -> new GaiaBaronArmorItem(FEET));
    public static final RegistryObject<Item> gaia_duke_helm = register("gaia_duke_helm", () -> new GaiaDukeArmorItem(HEAD));
    public static final RegistryObject<Item> gaia_duke_guard = register("gaia_duke_guard", () -> new GaiaDukeArmorItem(CHEST));
    public static final RegistryObject<Item> gaia_duke_greaves = register("gaia_duke_greaves", () -> new GaiaDukeArmorItem(LEGS));
    public static final RegistryObject<Item> gaia_duke_boots = register("gaia_duke_boots", () -> new GaiaDukeArmorItem(FEET));
    public static final RegistryObject<Item> gaia_champion_helm = register("gaia_champion_helm", () -> new GaiaChampArmorItem(HEAD));
    public static final RegistryObject<Item> gaia_champion_guard = register("gaia_champion_guard", () -> new GaiaChampArmorItem(CHEST));
    public static final RegistryObject<Item> gaia_champion_greaves = register("gaia_champion_greaves", () -> new GaiaChampArmorItem(LEGS));
    public static final RegistryObject<Item> gaia_champion_boots = register("gaia_champion_boots", () -> new GaiaChampArmorItem(FEET));

    public static final RegistryObject<Item> agate_sword = registerSword("agate_sword", GaiaToolMaterials.AGATE);
    public static final RegistryObject<Item> agate_pickaxe = registerPickaxe("agate_pickaxe", GaiaToolMaterials.AGATE);
    public static final RegistryObject<Item> agate_axe = registerAxe("agate_axe", GaiaToolMaterials.AGATE);
    public static final RegistryObject<Item> agate_shovel = registerShovel("agate_shovel", GaiaToolMaterials.AGATE);
    public static final RegistryObject<Item> sugilite_sword = registerSword("sugilite_sword", GaiaToolMaterials.SUGILITE);
    public static final RegistryObject<Item> sugilite_pickaxe = registerPickaxe("sugilite_pickaxe", GaiaToolMaterials.SUGILITE);
    public static final RegistryObject<Item> sugilite_axe = registerAxe("sugilite_axe", GaiaToolMaterials.SUGILITE);
    public static final RegistryObject<Item> sugilite_shovel = registerShovel("sugilite_shovel", GaiaToolMaterials.SUGILITE);
    public static final RegistryObject<Item> ixiolite_sword = registerSword("ixiolite_sword", GaiaToolMaterials.IXIOLITE);
    public static final RegistryObject<Item> ixiolite_pickaxe = registerPickaxe("ixiolite_pickaxe", GaiaToolMaterials.IXIOLITE);
    public static final RegistryObject<Item> ixiolite_axe = registerAxe("ixiolite_axe", GaiaToolMaterials.IXIOLITE);
    public static final RegistryObject<Item> ixiolite_shovel = registerShovel("ixiolite_shovel", GaiaToolMaterials.IXIOLITE);
    public static final RegistryObject<Item> euclase_sword = registerSword("euclase_sword", GaiaToolMaterials.EUCLASE);
    public static final RegistryObject<Item> euclase_pickaxe = registerPickaxe("euclase_pickaxe", GaiaToolMaterials.EUCLASE);
    public static final RegistryObject<Item> euclase_axe = registerAxe("euclase_axe", GaiaToolMaterials.EUCLASE);
    public static final RegistryObject<Item> euclase_shovel = registerShovel("euclase_shovel", GaiaToolMaterials.EUCLASE);
    public static final RegistryObject<Item> carnelian_sword = registerSword("carnelian_sword", GaiaToolMaterials.CARNELIAN);
    public static final RegistryObject<Item> carnelian_pickaxe = registerPickaxe("carnelian_pickaxe", GaiaToolMaterials.CARNELIAN);
    public static final RegistryObject<Item> carnelian_axe = registerAxe("carnelian_axe", GaiaToolMaterials.CARNELIAN);
    public static final RegistryObject<Item> carnelian_shovel = registerShovel("carnelian_shovel", GaiaToolMaterials.CARNELIAN);
    public static final RegistryObject<Item> benitoite_sword = registerSword("benitoite_sword", GaiaToolMaterials.BENITOITE);
    public static final RegistryObject<Item> benitoite_pickaxe = registerPickaxe("benitoite_pickaxe", GaiaToolMaterials.BENITOITE);
    public static final RegistryObject<Item> benitoite_axe = registerAxe("benitoite_axe", GaiaToolMaterials.BENITOITE);
    public static final RegistryObject<Item> benitoite_shovel = registerShovel("benitoite_shovel", GaiaToolMaterials.BENITOITE);
    public static final RegistryObject<Item> chalcedony_sword = registerSword("chalcedony_sword", GaiaToolMaterials.CHALCEDONY);
    public static final RegistryObject<Item> chalcedony_pickaxe = registerPickaxe("chalcedony_pickaxe", GaiaToolMaterials.CHALCEDONY);
    public static final RegistryObject<Item> chalcedony_axe = registerAxe("chalcedony_axe", GaiaToolMaterials.CHALCEDONY);
    public static final RegistryObject<Item> chalcedony_shovel = registerShovel("chalcedony_shovel", GaiaToolMaterials.CHALCEDONY);
    public static final RegistryObject<Item> old_bow = register("old_bow", OldBowItem::new);
    public static final RegistryObject<Item> agate_arrow = register("agate_arrow", AgateArrowItem::new);

    public static final RegistryObject<Item> malachite_guard_baton = register("malachite_guard_baton", MalachiteGuardSwordItem::new);
    public static final RegistryObject<Item> apex_predator_mace = register("apex_predator_mace", ApexPredatorSwordItem::new);
    public static final RegistryObject<Item> spinel_princess_flamberge = register("spinel_princess_flamberge", SpinelPrincessSwordItem::new);
    public static final RegistryObject<Item> zircon_prince_razor = register("zircon_prince_razor", ZirconPrinceSwordItem::new);
    public static final RegistryObject<Item> corrupt_warrior_sword = register("corrupt_warrior_sword", CorruptWarriorSwordItem::new);
    public static final RegistryObject<Item> gaia_duchess_khopesh = register("gaia_duchess_khopesh", GaiaDuchessSwordItem::new);
    public static final RegistryObject<Item> gaia_baron_dagger = register("gaia_baron_dagger", GaiaBaronSwordItem::new);
    public static final RegistryObject<Item> gaia_duke_blade = register("gaia_duke_blade", GaiaDukeSwordItem::new);
    public static final RegistryObject<Item> gaia_champion_sword = register("gaia_champion_sword", GaiaChampSwordItem::new);

    public static final RegistryObject<Item> mock_malachite = register("mock_malachite", MockGemItem::new);

    public static final RegistryObject<Item> growth_sapper_spawn_egg = registerEgg("growth_sapper", ModEntities.GROWTH_SAPPER, 0x5A4514, 0xFF00FF);
    public static final RegistryObject<Item> mutant_growth_extractor_spawn_egg = registerEgg("mutant_growth_extractor", ModEntities.MUTANT_GROWTH_EXTRACTOR, 0x5A4514, 0xFFFFCC);
    public static final RegistryObject<Item> howlite_wolf_spawn_egg = registerEgg("howlite_wolf", ModEntities.HOWLITE_WOLF, 0xDDDDDD, 0x3333FF);
    public static final RegistryObject<Item> spellbound_elemental_spawn_egg = registerEgg("spellbound_elemental", ModEntities.SPELLBOUND_ELEMENTAL, 0x885555, 0xCC33CC);
    public static final RegistryObject<Item> rocky_luggeroth_spawn_egg = registerEgg("rocky_luggeroth", ModEntities.ROCKY_LUGGEROTH, 0xB07700, 0xCC9900);
    public static final RegistryObject<Item> shalurker_spawn_egg = registerEgg("shalurker", ModEntities.SHALURKER, 0x771177, 0x000000);
    public static final RegistryObject<Item> muckling_spawn_egg = registerEgg("muckling", ModEntities.MUCKLING, 0xFF00FF, 0xCC66CC);
    public static final RegistryObject<Item> markuzar_plant_spawn_egg = registerEgg("markuzar_plant", ModEntities.MARKUZAR_PLANT, 0x00FF66, 0xCC00FF);
    public static final RegistryObject<Item> rugged_lurmorus_spawn_egg = registerEgg("rugged_lurmorus", ModEntities.RUGGED_LURMORUS, 0xCC9933, 0xFF6600);
    public static final RegistryObject<Item> agate_golem_spawn_egg = registerEgg("agate_golem", ModEntities.AGATE_GOLEM, 0x660000, 0xBB5555);
    public static final RegistryObject<Item> ancient_lagrahk_spawn_egg = registerEgg("ancient_lagrahk", ModEntities.ANCIENT_LAGRAHK, 0x772200, 0xAA5500);
    public static final RegistryObject<Item> crystal_golem_spawn_egg = registerEgg("crystal_golem", ModEntities.CRYSTAL_GOLEM, 0xFF66CC, 0xFF99CC);
    public static final RegistryObject<Item> saltion_spawn_egg = registerEgg("saltion", ModEntities.SALTION, 0x6699FF, 0x6633FF);
    public static final RegistryObject<Item> nomadic_lagrahk_spawn_egg = registerEgg("nomadic_lagrahk", ModEntities.NOMADIC_LAGRAHK, 0x3366CC, 0x232323);
    public static final RegistryObject<Item> shallow_arenthis_spawn_egg = registerEgg("shallow_arenthis", ModEntities.SHALLOW_ARENTHIS, 0x6699CC, 0x003399);
    public static final RegistryObject<Item> corrupt_sapper_spawn_egg = registerEgg("corrupt_sapper", ModEntities.CORRUPT_SAPPER, 0x202020, 0xCC3300);
    public static final RegistryObject<Item> contorted_naga_spawn_egg = registerEgg("contorted_naga", ModEntities.CONTORTED_NAGA, 0x202020, 0xCC3300);
    public static final RegistryObject<Item> lesser_spitfire_spawn_egg = registerEgg("lesser_spitfire", ModEntities.LESSER_SPITFIRE, 0xFF00FF, 0x202020);
    public static final RegistryObject<Item> lesser_shockshooter_spawn_egg = registerEgg("lesser_shockshooter", ModEntities.LESSER_SHOCKSHOOTER, 0x00FFFF, 0x202020);
    public static final RegistryObject<Item> mineral_arenthis_spawn_egg = registerEgg("mineral_arenthis", ModEntities.MINERAL_ARENTHIS, 0x0066CC, 0x000033);
    public static final RegistryObject<Item> bismuth_uletrus_spawn_egg = registerEgg("bismuth_uletrus", ModEntities.BISMUTH_ULETRUS, 0x4E3863, 0x303030);
    public static final RegistryObject<Item> archaic_warrior_spawn_egg = registerEgg("archaic_warrior", ModEntities.ARCHAIC_WARRIOR, 0x996699, 0xCC3366);
    public static final RegistryObject<Item> primal_beast_spawn_egg = registerEgg("primal_beast", ModEntities.PRIMAL_BEAST, 0x006699, 0x66FFFF);
    public static final RegistryObject<Item> cavern_tick_spawn_egg = registerEgg("cavern_tick", ModEntities.CAVERN_TICK, 0x9966CC, 0x666699);
    public static final RegistryObject<Item> malachite_drone_spawn_egg = registerEgg("malachite_drone", ModEntities.MALACHITE_DRONE, 0x00AA33, 0x33AA00);
    public static final RegistryObject<Item> blue_howlite_wolf_spawn_egg = registerEgg("blue_howlite_wolf", ModEntities.BLUE_HOWLITE_WOLF, 0x0099CC, 0xCC00FF);
    public static final RegistryObject<Item> malachite_guard_spawn_egg = registerEgg("malachite_guard", ModEntities.MALACHITE_GUARD, 0x339900, 0x33CC99);

    public static final RegistryObject<Item> PYRITE_TORCH = ITEMS.register("pyrite_torch", () -> new WallOrFloorItem(ModBlocks.pyrite_torch, ModBlocks.pyrite_wall_torch, new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS)));

    private static RegistryObject<Item> register(String name) {
        return register(name, BasicGaiaItem::new);
    }

    private static RegistryObject<Item> register(String name, Food food) {
        return register(name, () -> new BasicGaiaItem(food));
    }

    private static RegistryObject<Item> register(String name, IArmorMaterial material, EquipmentSlotType slot) {
        return register(name, () -> new BasicGaiaArmorItem(material, slot));
    }

    private static RegistryObject<Item> registerBucket(String name, Supplier<? extends Fluid> fluid) {
        return register(name, () -> new ScaynyxBucketItem(fluid));
    }

    public static RegistryObject<Item> registerSword(String name, IItemTier tier) {
        return register(name, () -> new BasicGaiaSwordItem(tier));
    }

    public static RegistryObject<Item> registerPickaxe(String name, IItemTier tier) {
        return register(name, () -> new BasicGaiaPickaxeItem(tier));
    }

    public static RegistryObject<Item> registerAxe(String name, IItemTier tier) {
        return register(name, () -> new BasicGaiaAxeItem(tier));
    }

    public static RegistryObject<Item> registerShovel(String name, IItemTier tier) {
        return register(name, () -> new BasicGaiaAxeItem(tier));
    }

    public static RegistryObject<Item> registerEgg(String name, EntityType<?> entity, int back, int front) {
        return register(name + "_spawn_egg", () -> new GaiaSpawnEggItem(entity, back, front));
    }

    private static RegistryObject<Item> register(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static void addItemProperties() {
        ItemModelsProperties.registerProperty(old_bow.get(), new ResourceLocation("pull"), ((stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return !(entity.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
            }
        }));
        ItemModelsProperties.registerProperty(old_bow.get(), new ResourceLocation("pulling"), (stack, world, entity) ->
                entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item: RegistryHelper.BLOCK_ITEMS) {
            registry.register(item);
        }
    }
}
