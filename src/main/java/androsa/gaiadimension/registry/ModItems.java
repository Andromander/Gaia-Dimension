package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.*;
import androsa.gaiadimension.item.armor.*;
import androsa.gaiadimension.item.tools.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static net.minecraft.inventory.EquipmentSlotType.*;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final Item crystallized_redstone = registerBasicItem("crystallized_redstone");
    public static final Item crystallized_lapis_lazuli = registerBasicItem("crystallized_lapis_lazuli");
    public static final Item glint_and_gold = RegistryHelper.registerItem("glint_and_gold", new GlintAndGoldItem());
    public static final Item agate_stick = registerBasicItem("agate_stick");
    public static final Item hot_dust = RegistryHelper.registerItem("hot_dust", new BasicGaiaItem() {
        @Override
        public int getBurnTime(ItemStack stack) {
            return 100;
        }
    });
    public static final Item goldstone_dust = registerBasicItem("goldstone_dust");
    public static final Item fine_dust = registerBasicItem("fine_dust");
    public static final Item cloudy_shard = registerBasicItem("cloudy_shard");
    public static final Item agate_cup = registerBasicItem("agate_cup");
    public static final Item scaynyx_ingot = registerBasicItem("scaynyx_ingot");
    public static final Item sweet_muckball = registerBasicItem("sweet_muckball");
    public static final Item sugar_crystals = registerBasicItem("sugar_crystals");
    public static final Item sugar_cluster = registerBasicItem("sugar_cluster");
    public static final Item shiny_bone = registerBasicItem("shiny_bone");
    public static final Item fine_thread = registerBasicItem("fine_thread");
    public static final Item twined_thread = registerBasicItem("twined_thread");
    public static final Item pink_essence = registerBasicItem("pink_essence");
    public static final Item pink_goo = registerBasicItem("pink_goo");
    public static final Item gemstone_pouch = RegistryHelper.registerItem("gemstone_pouch", new GemstonePouchItem());
    public static final Item agate_fabric = registerBasicItem("agate_fabric");
    public static final Item sturdy_pebble = RegistryHelper.registerItem("sturdy_pebble", new SturdyPebbleItem());
    public static final Item scaynyx_bucket = RegistryHelper.registerItem("scaynyx_bucket", new ScaynyxBucketItem(() -> Fluids.EMPTY));
    public static final Item mineral_water_bucket = RegistryHelper.registerItem("mineral_water_bucket", new ScaynyxBucketItem(() -> ModFluids.mineral_water_still));
    public static final Item superhot_magma_bucket = RegistryHelper.registerItem("superhot_magma_bucket", new ScaynyxBucketItem(() -> ModFluids.superhot_magma_still));
    public static final Item sweet_muck_bucket = RegistryHelper.registerItem("sweet_muck_bucket", new ScaynyxBucketItem(() -> ModFluids.sweet_muck_still));
    public static final Item liquid_bismuth_bucket = RegistryHelper.registerItem("liquid_bismuth_bucket", new ScaynyxBucketItem(() -> ModFluids.liquid_bismuth_still));
    public static final Item liquid_aura_bucket = RegistryHelper.registerItem("liquid_aura_bucket", new ScaynyxBucketItem(() -> ModFluids.liquid_aura_still));

    public static final Item pink_geode = registerBasicItem("pink_geode");
    public static final Item blue_geode = registerBasicItem("blue_geode");
    public static final Item green_geode = registerBasicItem("green_geode");
    public static final Item purple_geode = registerBasicItem("purple_geode");
    public static final Item pink_geode_slice = RegistryHelper.registerItem("pink_geode_slice", new GeodeSliceItem(GaiaFoods.PINK_SLICE));
    public static final Item blue_geode_slice = RegistryHelper.registerItem("blue_geode_slice", new GeodeSliceItem(GaiaFoods.BLUE_SLICE));
    public static final Item green_geode_slice = RegistryHelper.registerItem("green_geode_slice", new GeodeSliceItem(GaiaFoods.GREEN_SLICE));
    public static final Item purple_geode_slice = RegistryHelper.registerItem("purple_geode_slice", new GeodeSliceItem(GaiaFoods.PURPLE_SLICE));
    public static final Item pink_geode_juice = RegistryHelper.registerItem("pink_geode_juice", new GeodeJuiceItem(GaiaFoods.PINK_JUICE));
    public static final Item blue_geode_tea = RegistryHelper.registerItem("blue_geode_tea", new GeodeJuiceItem(GaiaFoods.BLUE_TEA));
    public static final Item green_geode_ale = RegistryHelper.registerItem("green_geode_ale", new GeodeJuiceItem(GaiaFoods.GREEN_ALE));
    public static final Item purple_geode_soda = RegistryHelper.registerItem("purple_geode_soda", new GeodeJuiceItem(GaiaFoods.PURPLE_SODA));
    public static final Item pearly_geode_elixir = RegistryHelper.registerItem("pearly_geode_elixir", new GeodeJuiceItem(GaiaFoods.PEARLY_ELIXIR));
    public static final Item lurmorus_meat = RegistryHelper.registerItem("lurmorus_meat", new BasicGaiaItem(GaiaFoods.LURMORUS_MEAT));
    public static final Item lurmorus_steak = RegistryHelper.registerItem("lurmorus_steak", new BasicGaiaItem(GaiaFoods.LURMORUS_STEAK));
    public static final Item small_tentacle = RegistryHelper.registerItem("small_tentacle", new BasicGaiaItem(GaiaFoods.SMALL_TENTACLE));
    public static final Item small_calamari = RegistryHelper.registerItem("small_calamari", new BasicGaiaItem(GaiaFoods.SMALL_CALAMARI));
    public static final Item large_tentacle = RegistryHelper.registerItem("large_tentacle", new BasicGaiaItem(GaiaFoods.LARGE_TENTACLE));
    public static final Item large_calamari = RegistryHelper.registerItem("large_calamari", new BasicGaiaItem(GaiaFoods.LARGE_CALAMARI));
    public static final Item markuzar_mint = RegistryHelper.registerItem("markuzar_mint", new BasicGaiaItem(GaiaFoods.MARKUZAR_MINT));
    public static final Item luggeroth_chop = RegistryHelper.registerItem("luggeroth_chop", new BasicGaiaItem(GaiaFoods.LUGGEROTH_CHOP));
    public static final Item cooked_luggeroth_chop = RegistryHelper.registerItem("cooked_luggeroth_chop", new BasicGaiaItem(GaiaFoods.COOKED_LUGGEROTH_CHOP));
    public static final Item tilipi = RegistryHelper.registerItem("tilipi", new BasicGaiaItem(GaiaFoods.TILIPI));
    public static final Item tilibl = RegistryHelper.registerItem("tilibl", new BasicGaiaItem(GaiaFoods.TILIBL));
    public static final Item tiligr = RegistryHelper.registerItem("tiligr", new BasicGaiaItem(GaiaFoods.TILIGR));
    public static final Item tilipu = RegistryHelper.registerItem("tilipu", new BasicGaiaItem(GaiaFoods.TILIPU));
    public static final Item tiliol = RegistryHelper.registerItem("tiliol", new BasicGaiaItem(GaiaFoods.TILIOL));
    public static final Item tilimy = RegistryHelper.registerItem("tilimy", new BasicGaiaItem(GaiaFoods.TILIMY));
    public static final Item plagued_tiliey = RegistryHelper.registerItem("plagued_tiliey", new BasicGaiaItem(GaiaFoods.PLAGUED_TILIEY));
    public static final Item tiliou = RegistryHelper.registerItem("tiliou", new BasicGaiaItem(GaiaFoods.TILIOU));

    public static final Item hematite_powder = RegistryHelper.registerItem("hematite_powder", new GroundGemItem());
    public static final Item cinnabar_powder = RegistryHelper.registerItem("cinnabar_powder", new GroundGemItem());
    public static final Item labradorite_powder = RegistryHelper.registerItem("labradorite_powder", new GroundGemItem());
    public static final Item moonstone_powder = RegistryHelper.registerItem("moonstone_powder", new GroundGemItem());
    public static final Item red_opal_powder = RegistryHelper.registerItem("red_opal_powder", new GroundGemItem());
    public static final Item blue_opal_powder = RegistryHelper.registerItem("blue_opal_powder", new GroundGemItem());
    public static final Item green_opal_powder = RegistryHelper.registerItem("green_opal_powder", new GroundGemItem());
    public static final Item white_opal_grit = RegistryHelper.registerItem("white_opal_grit", new GroundGemItem());
    public static final Item pyrite_powder = RegistryHelper.registerItem("pyrite_powder", new GroundGemItem());

    public static final Item sugilite = registerBasicItem("sugilite");
    public static final Item hematite = registerBasicItem("hematite");
    public static final Item cinnabar = registerBasicItem("cinnabar");
    public static final Item labradorite = registerBasicItem("labradorite");
    public static final Item moonstone = registerBasicItem("moonstone");
    public static final Item red_opal = registerBasicItem("red_opal");
    public static final Item blue_opal = registerBasicItem("blue_opal");
    public static final Item green_opal = registerBasicItem("green_opal");
    public static final Item white_opal = registerBasicItem("white_opal");
    public static final Item ixiolite = registerBasicItem("ixiolite");
    public static final Item proustite = registerBasicItem("proustite");
    public static final Item euclase = registerBasicItem("euclase");
    public static final Item leucite = registerBasicItem("leucite");
    public static final Item carnelian = registerBasicItem("carnelian");
    public static final Item benitoite = registerBasicItem("benitoite");
    public static final Item diopside = registerBasicItem("diopside");
    public static final Item chalcedony = registerBasicItem("chalcedony");
    public static final Item pyrite = registerBasicItem("pyrite");
    public static final Item black_residue = registerBasicItem("black_residue");
    public static final Item tektite = registerBasicItem("tektite");
    public static final Item goldstone_residue = registerBasicItem("goldstone_residue");
    public static final Item goldstone = registerBasicItem("goldstone");
    public static final Item aura_residue = registerBasicItem("aura_residue");
    public static final Item aura_cluster = registerBasicItem("aura_cluster");
    public static final Item bismuth_residue = registerBasicItem("bismuth_residue");
    public static final Item bismuth_crystal = registerBasicItem("bismuth_crystal");

    public static final Item sugilite_helmet = registerArmorItem("sugilite_helmet", GaiaArmorMaterials.SUGILITE, HEAD);
    public static final Item sugilite_chestplate = registerArmorItem("sugilite_chestplate", GaiaArmorMaterials.SUGILITE, CHEST);
    public static final Item sugilite_legs = registerArmorItem("sugilite_legs", GaiaArmorMaterials.SUGILITE, LEGS);
    public static final Item sugilite_boots = registerArmorItem("sugilite_boots", GaiaArmorMaterials.SUGILITE, FEET);
    public static final Item proustite_helmet = registerArmorItem("proustite_helmet", GaiaArmorMaterials.PROUSTITE, HEAD);
    public static final Item proustite_chestplate = registerArmorItem("proustite_chestplate", GaiaArmorMaterials.PROUSTITE, CHEST);
    public static final Item proustite_legs = registerArmorItem("proustite_legs", GaiaArmorMaterials.PROUSTITE, LEGS);
    public static final Item proustite_boots = registerArmorItem("proustite_boots", GaiaArmorMaterials.PROUSTITE, FEET);
    public static final Item leucite_helmet = registerArmorItem("leucite_helmet", GaiaArmorMaterials.LEUCITE, HEAD);
    public static final Item leucite_chestplate = registerArmorItem("leucite_chestplate", GaiaArmorMaterials.LEUCITE, CHEST);
    public static final Item leucite_legs = registerArmorItem("leucite_legs", GaiaArmorMaterials.LEUCITE, LEGS);
    public static final Item leucite_boots = registerArmorItem("leucite_boots", GaiaArmorMaterials.LEUCITE, FEET);
    public static final Item carnelian_helmet = registerArmorItem("carnelian_helmet", GaiaArmorMaterials.CARNELIAN, HEAD);
    public static final Item carnelian_chestplate = registerArmorItem("carnelian_chestplate", GaiaArmorMaterials.CARNELIAN, CHEST);
    public static final Item carnelian_legs = registerArmorItem("carnelian_legs", GaiaArmorMaterials.CARNELIAN, LEGS);
    public static final Item carnelian_boots = registerArmorItem("carnelian_boots", GaiaArmorMaterials.CARNELIAN, FEET);
    public static final Item diopside_helmet = registerArmorItem("diopside_helmet", GaiaArmorMaterials.DIOPSIDE, HEAD);
    public static final Item diopside_chestplate = registerArmorItem("diopside_chestplate", GaiaArmorMaterials.DIOPSIDE, CHEST);
    public static final Item diopside_legs = registerArmorItem("diopside_legs", GaiaArmorMaterials.DIOPSIDE, LEGS);
    public static final Item diopside_boots = registerArmorItem("diopside_boots", GaiaArmorMaterials.DIOPSIDE, FEET);
    public static final Item chalcedony_helmet = registerArmorItem("chalcedony_helmet", GaiaArmorMaterials.CHALCEDONY, HEAD);
    public static final Item chalcedony_chestplate = registerArmorItem("chalcedony_chestplate", GaiaArmorMaterials.CHALCEDONY, CHEST);
    public static final Item chalcedony_legs = registerArmorItem("chalcedony_legs", GaiaArmorMaterials.CHALCEDONY, LEGS);
    public static final Item chalcedony_boots = registerArmorItem("chalcedony_boots", GaiaArmorMaterials.CHALCEDONY, FEET);

    public static final Item malachite_guard_headgear = RegistryHelper.registerItem("malachite_guard_headgear", new MalachiteGuardArmorItem(HEAD));
    public static final Item malachite_guard_brace = RegistryHelper.registerItem("malachite_guard_brace", new MalachiteGuardArmorItem(CHEST));
    public static final Item malachite_guard_gear = RegistryHelper.registerItem("malachite_guard_gear", new MalachiteGuardArmorItem(LEGS));
    public static final Item malachite_guard_boots = RegistryHelper.registerItem("malachite_guard_boots", new MalachiteGuardArmorItem(FEET));
    public static final Item apex_predator_hood = RegistryHelper.registerItem("apex_predator_hood", new ApexPredatorArmorItem(HEAD));
    public static final Item apex_predator_jacket = RegistryHelper.registerItem("apex_predator_jacket", new ApexPredatorArmorItem(CHEST));
    public static final Item apex_predator_trousers = RegistryHelper.registerItem("apex_predator_trousers", new ApexPredatorArmorItem(LEGS));
    public static final Item apex_predator_boots = RegistryHelper.registerItem("apex_predator_boots", new ApexPredatorArmorItem(FEET));
    public static final Item spinel_princess_cowl = RegistryHelper.registerItem("spinel_princess_cowl", new SpinelPrincessArmorItem(HEAD));
    public static final Item spinel_princess_cloak = RegistryHelper.registerItem("spinel_princess_cloak", new SpinelPrincessArmorItem(CHEST));
    public static final Item spinel_princess_dress = RegistryHelper.registerItem("spinel_princess_dress", new SpinelPrincessArmorItem(LEGS));
    public static final Item spinel_princess_heels = RegistryHelper.registerItem("spinel_princess_heels", new SpinelPrincessArmorItem(FEET));
    public static final Item zircon_prince_crown = RegistryHelper.registerItem("zircon_prince_crown", new ZirconPrinceArmorItem(HEAD));
    public static final Item zircon_prince_chestpiece = RegistryHelper.registerItem("zircon_prince_chestpiece", new ZirconPrinceArmorItem(CHEST));
    public static final Item zircon_prince_gear = RegistryHelper.registerItem("zircon_prince_gear", new ZirconPrinceArmorItem(LEGS));
    public static final Item zircon_prince_boots = RegistryHelper.registerItem("zircon_prince_boots", new ZirconPrinceArmorItem(FEET));
    public static final Item corrupt_warrior_helm = RegistryHelper.registerItem("corrupt_warrior_helm", new CorruptWarriorArmorItem(HEAD));
    public static final Item corrupt_warrior_guard = RegistryHelper.registerItem("corrupt_warrior_guard", new CorruptWarriorArmorItem(CHEST));
    public static final Item corrupt_warrior_greaves = RegistryHelper.registerItem("corrupt_warrior_greaves", new CorruptWarriorArmorItem(LEGS));
    public static final Item corrupt_warrior_boots = RegistryHelper.registerItem("corrupt_warrior_boots", new CorruptWarriorArmorItem(FEET));
    public static final Item gaia_duchess_helm = RegistryHelper.registerItem("gaia_duchess_helm", new GaiaDuchessArmorItem(HEAD));
    public static final Item gaia_duchess_guard = RegistryHelper.registerItem("gaia_duchess_guard", new GaiaDuchessArmorItem(CHEST));
    public static final Item gaia_duchess_greaves = RegistryHelper.registerItem("gaia_duchess_greaves", new GaiaDuchessArmorItem(LEGS));
    public static final Item gaia_duchess_boots = RegistryHelper.registerItem("gaia_duchess_boots", new GaiaDuchessArmorItem(FEET));
    public static final Item gaia_baron_mask = RegistryHelper.registerItem("gaia_baron_mask", new GaiaBaronArmorItem(HEAD));
    public static final Item gaia_baron_tuxedo = RegistryHelper.registerItem("gaia_baron_tuxedo", new GaiaBaronArmorItem(CHEST));
    public static final Item gaia_baron_pants = RegistryHelper.registerItem("gaia_baron_pants", new GaiaBaronArmorItem(LEGS));
    public static final Item gaia_baron_shoes = RegistryHelper.registerItem("gaia_baron_shoes", new GaiaBaronArmorItem(FEET));
    public static final Item gaia_duke_helm = RegistryHelper.registerItem("gaia_duke_helm", new GaiaDukeArmorItem(HEAD));
    public static final Item gaia_duke_guard = RegistryHelper.registerItem("gaia_duke_guard", new GaiaDukeArmorItem(CHEST));
    public static final Item gaia_duke_greaves = RegistryHelper.registerItem("gaia_duke_greaves", new GaiaDukeArmorItem(LEGS));
    public static final Item gaia_duke_boots = RegistryHelper.registerItem("gaia_duke_boots", new GaiaDukeArmorItem(FEET));
    public static final Item gaia_champion_helm = RegistryHelper.registerItem("gaia_champion_helm", new GaiaChampArmorItem(HEAD));
    public static final Item gaia_champion_guard = RegistryHelper.registerItem("gaia_champion_guard", new GaiaChampArmorItem(CHEST));
    public static final Item gaia_champion_greaves = RegistryHelper.registerItem("gaia_champion_greaves", new GaiaChampArmorItem(LEGS));
    public static final Item gaia_champion_boots = RegistryHelper.registerItem("gaia_champion_boots", new GaiaChampArmorItem(FEET));

    public static final Item agate_sword = registerSwordItem("agate_sword", GaiaToolMaterials.AGATE);
    public static final Item agate_pickaxe = registerPickaxeItem("agate_pickaxe", GaiaToolMaterials.AGATE);
    public static final Item agate_axe = registerAxeItem("agate_axe", GaiaToolMaterials.AGATE);
    public static final Item agate_shovel = registerShovelItem("agate_shovel", GaiaToolMaterials.AGATE);
    public static final Item sugilite_sword = registerSwordItem("sugilite_sword", GaiaToolMaterials.SUGILITE);
    public static final Item sugilite_pickaxe = registerPickaxeItem("sugilite_pickaxe", GaiaToolMaterials.SUGILITE);
    public static final Item sugilite_axe = registerAxeItem("sugilite_axe", GaiaToolMaterials.SUGILITE);
    public static final Item sugilite_shovel = registerShovelItem("sugilite_shovel", GaiaToolMaterials.SUGILITE);
    public static final Item ixiolite_sword = registerSwordItem("ixiolite_sword", GaiaToolMaterials.IXIOLITE);
    public static final Item ixiolite_pickaxe = registerPickaxeItem("ixiolite_pickaxe", GaiaToolMaterials.IXIOLITE);
    public static final Item ixiolite_axe = registerAxeItem("ixiolite_axe", GaiaToolMaterials.IXIOLITE);
    public static final Item ixiolite_shovel = registerShovelItem("ixiolite_shovel", GaiaToolMaterials.IXIOLITE);
    public static final Item euclase_sword = registerSwordItem("euclase_sword", GaiaToolMaterials.EUCLASE);
    public static final Item euclase_pickaxe = registerPickaxeItem("euclase_pickaxe", GaiaToolMaterials.EUCLASE);
    public static final Item euclase_axe = registerAxeItem("euclase_axe", GaiaToolMaterials.EUCLASE);
    public static final Item euclase_shovel = registerShovelItem("euclase_shovel", GaiaToolMaterials.EUCLASE);
    public static final Item carnelian_sword = registerSwordItem("carnelian_sword", GaiaToolMaterials.CARNELIAN);
    public static final Item carnelian_pickaxe = registerPickaxeItem("carnelian_pickaxe", GaiaToolMaterials.CARNELIAN);
    public static final Item carnelian_axe = registerAxeItem("carnelian_axe", GaiaToolMaterials.CARNELIAN);
    public static final Item carnelian_shovel = registerShovelItem("carnelian_shovel", GaiaToolMaterials.CARNELIAN);
    public static final Item benitoite_sword = registerSwordItem("benitoite_sword", GaiaToolMaterials.BENITOITE);
    public static final Item benitoite_pickaxe = registerPickaxeItem("benitoite_pickaxe", GaiaToolMaterials.BENITOITE);
    public static final Item benitoite_axe = registerAxeItem("benitoite_axe", GaiaToolMaterials.BENITOITE);
    public static final Item benitoite_shovel = registerShovelItem("benitoite_shovel", GaiaToolMaterials.BENITOITE);
    public static final Item chalcedony_sword = registerSwordItem("chalcedony_sword", GaiaToolMaterials.CHALCEDONY);
    public static final Item chalcedony_pickaxe = registerPickaxeItem("chalcedony_pickaxe", GaiaToolMaterials.CHALCEDONY);
    public static final Item chalcedony_axe = registerAxeItem("chalcedony_axe", GaiaToolMaterials.CHALCEDONY);
    public static final Item chalcedony_shovel = registerShovelItem("chalcedony_shovel", GaiaToolMaterials.CHALCEDONY);
    public static final Item old_bow = RegistryHelper.registerItem("old_bow", new OldBowItem());
    public static final Item agate_arrow = RegistryHelper.registerItem("agate_arrow", new AgateArrowItem());

    public static final Item malachite_guard_baton = RegistryHelper.registerItem("malachite_guard_baton", new MalachiteGuardSwordItem());
    public static final Item apex_predator_mace = RegistryHelper.registerItem("apex_predator_mace", new ApexPredatorSwordItem());
    public static final Item spinel_princess_flamberge = RegistryHelper.registerItem("spinel_princess_flamberge", new SpinelPrincessSwordItem());
    public static final Item zircon_prince_razor = RegistryHelper.registerItem("zircon_prince_razor", new ZirconPrinceSwordItem());
    public static final Item corrupt_warrior_sword = RegistryHelper.registerItem("corrupt_warrior_sword", new CorruptWarriorSwordItem());
    public static final Item gaia_duchess_khopesh = RegistryHelper.registerItem("gaia_duchess_khopesh", new GaiaDuchessSwordItem());
    public static final Item gaia_baron_dagger = RegistryHelper.registerItem("gaia_baron_dagger", new GaiaBaronSwordItem());
    public static final Item gaia_duke_blade = RegistryHelper.registerItem("gaia_duke_blade", new GaiaDukeSwordItem());
    public static final Item gaia_champion_sword = RegistryHelper.registerItem("gaia_champion_sword", new GaiaChampSwordItem());

    public static final Item mock_malachite = RegistryHelper.registerItem("mock_malachite", new MockGemItem());

    public static final Item growth_sapper_spawn_egg = registerSpawnEgg("growth_sapper", ModEntities.GROWTH_SAPPER, 0x5A4514, 0xFF00FF);
    public static final Item mutant_growth_extractor_spawn_egg = registerSpawnEgg("mutant_growth_extractor", ModEntities.MUTANT_GROWTH_EXTRACTOR, 0x5A4514, 0xFFFFCC);
    public static final Item howlite_wolf_spawn_egg = registerSpawnEgg("howlite_wolf", ModEntities.HOWLITE_WOLF, 0xDDDDDD, 0x3333FF);
    public static final Item spellbound_elemental_spawn_egg = registerSpawnEgg("spellbound_elemental", ModEntities.SPELLBOUND_ELEMENTAL, 0x885555, 0xCC33CC);
    public static final Item rocky_luggeroth_spawn_egg = registerSpawnEgg("rocky_luggeroth", ModEntities.ROCKY_LUGGEROTH, 0xB07700, 0xCC9900);
    public static final Item shalurker_spawn_egg = registerSpawnEgg("shalurker", ModEntities.SHALURKER, 0x771177, 0x000000);
    public static final Item muckling_spawn_egg = registerSpawnEgg("muckling", ModEntities.MUCKLING, 0xFF00FF, 0xCC66CC);
    public static final Item markuzar_plant_spawn_egg = registerSpawnEgg("markuzar_plant", ModEntities.MARKUZAR_PLANT, 0x00FF66, 0xCC00FF);
    public static final Item rugged_lurmorus_spawn_egg = registerSpawnEgg("rugged_lurmorus", ModEntities.RUGGED_LURMORUS, 0xCC9933, 0xFF6600);
    public static final Item agate_golem_spawn_egg = registerSpawnEgg("agate_golem", ModEntities.AGATE_GOLEM, 0x660000, 0xBB5555);
    public static final Item ancient_lagrahk_spawn_egg = registerSpawnEgg("ancient_lagrahk", ModEntities.ANCIENT_LAGRAHK, 0x772200, 0xAA5500);
    public static final Item crystal_golem_spawn_egg = registerSpawnEgg("crystal_golem", ModEntities.CRYSTAL_GOLEM, 0xFF66CC, 0xFF99CC);
    public static final Item saltion_spawn_egg = registerSpawnEgg("saltion", ModEntities.SALTION, 0x6699FF, 0x6633FF);
    public static final Item nomadic_lagrahk_spawn_egg = registerSpawnEgg("nomadic_lagrahk", ModEntities.NOMADIC_LAGRAHK, 0x3366CC, 0x232323);
    public static final Item shallow_arenthis_spawn_egg = registerSpawnEgg("shallow_arenthis", ModEntities.SHALLOW_ARENTHIS, 0x6699CC, 0x003399);
    public static final Item corrupt_sapper_spawn_egg = registerSpawnEgg("corrupt_sapper", ModEntities.CORRUPT_SAPPER, 0x202020, 0xCC3300);
    public static final Item contorted_naga_spawn_egg = registerSpawnEgg("contorted_naga", ModEntities.CONTORTED_NAGA, 0x202020, 0xCC3300);
    public static final Item lesser_spitfire_spawn_egg = registerSpawnEgg("lesser_spitfire", ModEntities.LESSER_SPITFIRE, 0xFF00FF, 0x202020);
    public static final Item lesser_shockshooter_spawn_egg = registerSpawnEgg("lesser_shockshooter", ModEntities.LESSER_SHOCKSHOOTER, 0x00FFFF, 0x202020);
    public static final Item mineral_arenthis_spawn_egg = registerSpawnEgg("mineral_arenthis", ModEntities.MINERAL_ARENTHIS, 0x0066CC, 0x000033);
    public static final Item bismuth_uletrus_spawn_egg = registerSpawnEgg("bismuth_uletrus", ModEntities.BISMUTH_ULETRUS, 0x4E3863, 0x303030);
    public static final Item archaic_warrior_spawn_egg = registerSpawnEgg("archaic_warrior", ModEntities.ARCHAIC_WARRIOR, 0x996699, 0xCC3366);
    public static final Item primal_beast_spawn_egg = registerSpawnEgg("primal_beast", ModEntities.PRIMAL_BEAST, 0x006699, 0x66FFFF);
    public static final Item cavern_tick_spawn_egg = registerSpawnEgg("cavern_tick", ModEntities.CAVERN_TICK, 0x9966CC, 0x666699);
    public static final Item malachite_drone_spawn_egg = registerSpawnEgg("malachite_drone", ModEntities.MALACHITE_DRONE, 0x00AA33, 0x33AA00);
    public static final Item blue_howlite_wolf_spawn_egg = registerSpawnEgg("blue_howlite_wolf", ModEntities.BLUE_HOWLITE_WOLF, 0x0099CC, 0xCC00FF);
    public static final Item malachite_guard_spawn_egg = registerSpawnEgg("malachite_guard", ModEntities.MALACHITE_GUARD, 0x339900, 0x33CC99);

    public static final Item PYRITE_TORCH = RegistryHelper.registerWallOrFloorItem("pyrite_torch", ModBlocks.pyrite_torch, ModBlocks.pyrite_wall_torch);

    private static Item registerBasicItem(String name) {
        return RegistryHelper.registerItem(name, new BasicGaiaItem());
    }

    private static Item registerArmorItem(String name, IArmorMaterial material, EquipmentSlotType slot) {
        return RegistryHelper.registerItem(name, new BasicGaiaArmorItem(material, slot));
    }

    private static Item registerSwordItem(String name, IItemTier tier) {
        return RegistryHelper.registerItem(name, new BasicGaiaSwordItem(tier));
    }

    private static Item registerPickaxeItem(String name, IItemTier tier) {
        return RegistryHelper.registerItem(name, new BasicGaiaPickaxeItem(tier));
    }

    private static Item registerAxeItem(String name, IItemTier tier) {
        return RegistryHelper.registerItem(name, new BasicGaiaAxeItem(tier));
    }

    private static Item registerShovelItem(String name, IItemTier tier) {
        return RegistryHelper.registerItem(name, new BasicGaiaShovelItem(tier));
    }

    private static Item registerSpawnEgg(String name, EntityType<?> entity, int back, int front) {
        return RegistryHelper.registerItem(name + "_spawn_egg", new GaiaSpawnEggItem(entity, back, front));
    }

    public static void addItemProperties() {
        ItemModelsProperties.registerProperty(old_bow, new ResourceLocation("pull"), ((stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return !(entity.getActiveItemStack().getItem() instanceof BowItem) ? 0.0F : (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
            }
        }));
        ItemModelsProperties.registerProperty(old_bow, new ResourceLocation("pulling"), (stack, world, entity) ->
                entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : RegistryHelper.ITEMS) {
            registry.register(item);
        }
        for (Item item: RegistryHelper.BLOCK_ITEMS) {
            registry.register(item);
        }
    }
}
