package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBlockLootTableProvider;
import androsa.gaiadimension.data.provider.GaiaEntityLootTableProvider;
import androsa.gaiadimension.registry.*;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GaiaLootTables extends LootTableProvider {

    public static final float[] leaf_chances = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public GaiaLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "Gaia Dimension Loot Tables";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Entities::new, LootParameterSets.ENTITY), Pair.of(Chests::new, LootParameterSets.CHEST));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationresults) {
    }

    public static class Blocks extends GaiaBlockLootTableProvider {
        @Override
        protected void addTables() {
            //Utility Blocks
            registerDropSelfLootTable(ModBlocks.keystone_block);
            registerDropSelfLootTable(ModBlocks.pyrite_torch);
            registerDropSelfLootTable(ModBlocks.agate_crafting_table);
            registerLootTable(ModBlocks.crude_storage_crate, GaiaBlockLootTableProvider::smallCrate);
            registerLootTable(ModBlocks.mega_storage_crate, GaiaBlockLootTableProvider::largeCrate);
            registerLootTable(ModBlocks.gaia_stone_furnace, GaiaBlockLootTableProvider::withName);
            registerLootTable(ModBlocks.restructurer, GaiaBlockLootTableProvider::withName);
            registerLootTable(ModBlocks.purifier, GaiaBlockLootTableProvider::withName);

            //Natural Blocks
            registerDropSelfLootTable(ModBlocks.heavy_soil);
            registerDropSelfLootTable(ModBlocks.corrupt_soil);
            registerDropSelfLootTable(ModBlocks.boggy_soil);
            registerDropSelfLootTable(ModBlocks.light_soil);
            dropWithSilk(ModBlocks.glitter_grass, ModBlocks.heavy_soil);
            dropWithSilk(ModBlocks.corrupt_grass, ModBlocks.corrupt_soil);
            dropWithSilk(ModBlocks.murky_grass, ModBlocks.boggy_soil);
            dropWithSilk(ModBlocks.soft_grass, ModBlocks.light_soil);
            registerSilkTouch(ModBlocks.frail_glitter_block);
            registerDropSelfLootTable(ModBlocks.thick_glitter_block);
            registerDropSelfLootTable(ModBlocks.gummy_glitter_block);
            registerDropSelfLootTable(ModBlocks.pink_sludge_block);

            //Plants
            registerLootTable(ModBlocks.crystal_growth, BlockLootTables::onlyWithShears);
            registerLootTable(ModBlocks.crystal_growth_red, BlockLootTables::onlyWithShears);
            registerLootTable(ModBlocks.crystal_growth_black, BlockLootTables::onlyWithShears);
            registerLootTable(ModBlocks.crystal_growth_seared, BlockLootTables::onlyWithShears);
            registerLootTable(ModBlocks.crystal_growth_mutant, BlockLootTables::onlyWithShears);
            registerLootTable(ModBlocks.crystal_growth_aura, BlockLootTables::onlyWithShears);
            registerDropSelfLootTable(ModBlocks.thiscus);
            registerDropSelfLootTable(ModBlocks.ouzium);
            registerDropSelfLootTable(ModBlocks.agathum);
            registerDropSelfLootTable(ModBlocks.varloom);
            registerDropSelfLootTable(ModBlocks.corrupted_varloom);
            registerDropSelfLootTable(ModBlocks.missingno_plant);
            registerDropSelfLootTable(ModBlocks.spotted_kersei);
            registerDropSelfLootTable(ModBlocks.thorny_wiltha);
            registerDropSelfLootTable(ModBlocks.roofed_agaric);
            registerDropSelfLootTable(ModBlocks.bulbous_hobina);
            registerDropSelfLootTable(ModBlocks.stickly_cupsir);
            registerDropSelfLootTable(ModBlocks.mystical_murgni);
            registerDropSelfLootTable(ModBlocks.corrupted_gaia_eye);
            registerDropSelfLootTable(ModBlocks.elder_imklia);
            registerDropSelfLootTable(ModBlocks.gold_orb_tucher);
            registerDropSelfLootTable(ModBlocks.missingno_fungus);

            //Tree Blocks
            registerDropSelfLootTable(ModBlocks.pink_agate_sapling);
            registerDropSelfLootTable(ModBlocks.blue_agate_sapling);
            registerDropSelfLootTable(ModBlocks.green_agate_sapling);
            registerDropSelfLootTable(ModBlocks.purple_agate_sapling);
            registerDropSelfLootTable(ModBlocks.fossilized_sapling);
            registerDropSelfLootTable(ModBlocks.corrupted_sapling);
            registerDropSelfLootTable(ModBlocks.burnt_sapling);
            registerDropSelfLootTable(ModBlocks.burning_sapling);
            registerDropSelfLootTable(ModBlocks.aura_sapling);
            dropChance(ModBlocks.pink_agate_leaves, ModBlocks.pink_agate_sapling, leaf_chances);
            dropChance(ModBlocks.blue_agate_leaves, ModBlocks.blue_agate_sapling, leaf_chances);
            dropChance(ModBlocks.green_agate_leaves, ModBlocks.green_agate_sapling, leaf_chances);
            dropChance(ModBlocks.purple_agate_leaves, ModBlocks.purple_agate_sapling, leaf_chances);
            dropChanceAlternative(ModBlocks.fossilized_leaves, ModBlocks.fossilized_sapling, ModItems.fine_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.corrupted_leaves, ModBlocks.corrupted_sapling, ModItems.goldstone_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.burnt_leaves, ModBlocks.burnt_sapling, Items.GUNPOWDER, leaf_chances);
            dropChanceAlternative(ModBlocks.burning_leaves, ModBlocks.burning_sapling, ModItems.hot_dust, leaf_chances);
            dropChance(ModBlocks.aura_leaves, ModBlocks.aura_sapling, leaf_chances);
            registerDropSelfLootTable(ModBlocks.pink_agate_log);
            registerDropSelfLootTable(ModBlocks.blue_agate_log);
            registerDropSelfLootTable(ModBlocks.green_agate_log);
            registerDropSelfLootTable(ModBlocks.purple_agate_log);
            registerDropSelfLootTable(ModBlocks.fossilized_log);
            registerDropSelfLootTable(ModBlocks.corrupted_log);
            registerDropSelfLootTable(ModBlocks.burnt_log);
            registerDropSelfLootTable(ModBlocks.burning_log);
            registerDropSelfLootTable(ModBlocks.aura_log);
            registerDropSelfLootTable(ModBlocks.stripped_pink_agate_log);
            registerDropSelfLootTable(ModBlocks.stripped_blue_agate_log);
            registerDropSelfLootTable(ModBlocks.stripped_green_agate_log);
            registerDropSelfLootTable(ModBlocks.stripped_purple_agate_log);
            registerDropSelfLootTable(ModBlocks.stripped_fossilized_log);
            registerDropSelfLootTable(ModBlocks.stripped_corrupted_log);
            registerDropSelfLootTable(ModBlocks.stripped_burnt_log);
            registerDropSelfLootTable(ModBlocks.stripped_burning_log);
            registerDropSelfLootTable(ModBlocks.stripped_aura_log);
            registerDropSelfLootTable(ModBlocks.pink_agate_wood);
            registerDropSelfLootTable(ModBlocks.blue_agate_wood);
            registerDropSelfLootTable(ModBlocks.green_agate_wood);
            registerDropSelfLootTable(ModBlocks.purple_agate_wood);
            registerDropSelfLootTable(ModBlocks.fossilized_wood);
            registerDropSelfLootTable(ModBlocks.corrupted_wood);
            registerDropSelfLootTable(ModBlocks.burnt_wood);
            registerDropSelfLootTable(ModBlocks.burning_wood);
            registerDropSelfLootTable(ModBlocks.aura_wood);
            registerDropSelfLootTable(ModBlocks.stripped_pink_agate_wood);
            registerDropSelfLootTable(ModBlocks.stripped_blue_agate_wood);
            registerDropSelfLootTable(ModBlocks.stripped_green_agate_wood);
            registerDropSelfLootTable(ModBlocks.stripped_purple_agate_wood);
            registerDropSelfLootTable(ModBlocks.stripped_fossilized_wood);
            registerDropSelfLootTable(ModBlocks.stripped_corrupted_wood);
            registerDropSelfLootTable(ModBlocks.stripped_burnt_wood);
            registerDropSelfLootTable(ModBlocks.stripped_burning_wood);
            registerDropSelfLootTable(ModBlocks.stripped_aura_wood);

            registerDropSelfLootTable(ModBlocks.salt);
            registerDropSelfLootTable(ModBlocks.saltstone);
            dropAlternative(ModBlocks.pebbles, ModItems.sturdy_pebble);
            dropWithSilk(ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone);
            registerDropSelfLootTable(ModBlocks.gaia_cobblestone);
            registerDropSelfLootTable(ModBlocks.wasteland_stone);
            registerDropSelfLootTable(ModBlocks.static_stone);
            registerSilkTouch(ModBlocks.charged_mineral);
            registerDropSelfLootTable(ModBlocks.volcanic_rock);
            registerDropSelfLootTable(ModBlocks.searing_rock);
            registerDropSelfLootTable(ModBlocks.primal_mass);
            registerDropSelfLootTable(ModBlocks.impure_rock);
            registerDropSelfLootTable(ModBlocks.active_rock);
            registerDropSelfLootTable(ModBlocks.impure_sludge);
            registerDropSelfLootTable(ModBlocks.geyser_block);
            registerSilkTouch(ModBlocks.sparkling_rock);
            registerDropSelfLootTable(ModBlocks.aura_shoot);

            //Planks (Tiles)
            registerDropSelfLootTable(ModBlocks.pink_agate_planks);
            registerDropSelfLootTable(ModBlocks.blue_agate_planks);
            registerDropSelfLootTable(ModBlocks.green_agate_planks);
            registerDropSelfLootTable(ModBlocks.purple_agate_planks);
            registerDropSelfLootTable(ModBlocks.fossilized_planks);
            registerDropSelfLootTable(ModBlocks.corrupted_planks);
            registerDropSelfLootTable(ModBlocks.burnt_planks);
            registerDropSelfLootTable(ModBlocks.burning_planks);
            registerDropSelfLootTable(ModBlocks.aura_planks);
            dropSlab(ModBlocks.pink_agate_plank_slab);
            dropSlab(ModBlocks.blue_agate_plank_slab);
            dropSlab(ModBlocks.green_agate_plank_slab);
            dropSlab(ModBlocks.purple_agate_plank_slab);
            dropSlab(ModBlocks.fossilized_plank_slab);
            dropSlab(ModBlocks.corrupted_plank_slab);
            dropSlab(ModBlocks.burnt_plank_slab);
            dropSlab(ModBlocks.burning_plank_slab);
            dropSlab(ModBlocks.aura_plank_slab);
            registerDropSelfLootTable(ModBlocks.pink_agate_plank_stairs);
            registerDropSelfLootTable(ModBlocks.blue_agate_plank_stairs);
            registerDropSelfLootTable(ModBlocks.green_agate_plank_stairs);
            registerDropSelfLootTable(ModBlocks.purple_agate_plank_stairs);
            registerDropSelfLootTable(ModBlocks.fossilized_plank_stairs);
            registerDropSelfLootTable(ModBlocks.corrupted_plank_stairs);
            registerDropSelfLootTable(ModBlocks.burnt_plank_stairs);
            registerDropSelfLootTable(ModBlocks.burning_plank_stairs);
            registerDropSelfLootTable(ModBlocks.aura_plank_stairs);

            //Manufactured
            registerSilkTouch(ModBlocks.cloudy_glass);
            registerSilkTouch(ModBlocks.foggy_glass);
            registerDropSelfLootTable(ModBlocks.gaia_stone_bricks);
            registerDropSelfLootTable(ModBlocks.cracked_gaia_stone_bricks);
            registerDropSelfLootTable(ModBlocks.crusted_gaia_stone_bricks);

            registerDropSelfLootTable(ModBlocks.raw_jade);
            registerDropSelfLootTable(ModBlocks.jade_bricks);
            dropSlab(ModBlocks.jade_brick_slab);
            registerDropSelfLootTable(ModBlocks.jade_brick_stairs);
            registerDropSelfLootTable(ModBlocks.cracked_jade_bricks);
            dropSlab(ModBlocks.cracked_jade_brick_slab);
            registerDropSelfLootTable(ModBlocks.cracked_jade_brick_stairs);
            registerDropSelfLootTable(ModBlocks.crusted_jade_bricks);
            dropSlab(ModBlocks.crusted_jade_brick_slab);
            registerDropSelfLootTable(ModBlocks.crusted_jade_brick_stairs);
            registerDropSelfLootTable(ModBlocks.raw_copal);
            registerDropSelfLootTable(ModBlocks.copal_bricks);
            dropSlab(ModBlocks.copal_brick_slab);
            registerDropSelfLootTable(ModBlocks.copal_brick_stairs);
            registerDropSelfLootTable(ModBlocks.cracked_copal_bricks);
            dropSlab(ModBlocks.cracked_copal_brick_slab);
            registerDropSelfLootTable(ModBlocks.cracked_copal_brick_stairs);
            registerDropSelfLootTable(ModBlocks.crusted_copal_bricks);
            dropSlab(ModBlocks.crusted_copal_brick_slab);
            registerDropSelfLootTable(ModBlocks.crusted_copal_brick_stairs);
            registerDropSelfLootTable(ModBlocks.raw_jet);
            registerDropSelfLootTable(ModBlocks.jet_bricks);
            dropSlab(ModBlocks.jet_brick_slab);
            registerDropSelfLootTable(ModBlocks.jet_brick_stairs);
            registerDropSelfLootTable(ModBlocks.cracked_jet_bricks);
            dropSlab(ModBlocks.cracked_jet_brick_slab);
            registerDropSelfLootTable(ModBlocks.cracked_jet_brick_stairs);
            registerDropSelfLootTable(ModBlocks.crusted_jet_bricks);
            dropSlab(ModBlocks.crusted_jet_brick_slab);
            registerDropSelfLootTable(ModBlocks.crusted_jet_brick_stairs);
            registerDropSelfLootTable(ModBlocks.raw_amethyst);
            registerDropSelfLootTable(ModBlocks.amethyst_bricks);
            dropSlab(ModBlocks.amethyst_brick_slab);
            registerDropSelfLootTable(ModBlocks.amethyst_brick_stairs);
            registerDropSelfLootTable(ModBlocks.cracked_amethyst_bricks);
            dropSlab(ModBlocks.cracked_amethyst_brick_slab);
            registerDropSelfLootTable(ModBlocks.cracked_amethyst_brick_stairs);
            registerDropSelfLootTable(ModBlocks.crusted_amethyst_bricks);
            dropSlab(ModBlocks.crusted_amethyst_brick_slab);
            registerDropSelfLootTable(ModBlocks.crusted_amethyst_brick_stairs);

            registerDropSelfLootTable(ModBlocks.reinforced_bricks);
            registerDropSelfLootTable(ModBlocks.bolstered_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_cracked_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_crusted_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_floor_tiles);
            registerDropSelfLootTable(ModBlocks.malachite_chisel_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_bricks);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_tiles);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_chisel);
            dropSlab(ModBlocks.malachite_brick_slab);
            dropSlab(ModBlocks.malachite_cracked_brick_slab);
            dropSlab(ModBlocks.malachite_crusted_brick_slab);
            dropSlab(ModBlocks.malachite_floor_slab);
            registerDropSelfLootTable(ModBlocks.malachite_pillar);
            registerDropSelfLootTable(ModBlocks.malachite_brick_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_cracked_brick_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_crusted_brick_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_floor_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_chisel_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_brick_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_floor_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_pulsing_chisel_stairs);
            registerDropSelfLootTable(ModBlocks.malachite_pillar_stairs);

            //Storage Blocks
            registerDropSelfLootTable(ModBlocks.sugilite_block);
            registerDropSelfLootTable(ModBlocks.hematite_block);
            registerDropSelfLootTable(ModBlocks.cinnabar_block);
            registerDropSelfLootTable(ModBlocks.labradorite_block);
            registerDropSelfLootTable(ModBlocks.moonstone_block);
            registerDropSelfLootTable(ModBlocks.opal_block_red);
            registerDropSelfLootTable(ModBlocks.opal_block_blue);
            registerDropSelfLootTable(ModBlocks.opal_block_green);
            registerDropSelfLootTable(ModBlocks.opal_block_white);
            registerDropSelfLootTable(ModBlocks.pyrite_block);
            registerDropSelfLootTable(ModBlocks.tektite_block);
            registerDropSelfLootTable(ModBlocks.goldstone_block);
            registerDropSelfLootTable(ModBlocks.aura_block);
            registerDropSelfLootTable(ModBlocks.bismuth_block);
            registerDropSelfLootTable(ModBlocks.ixiolite_block);
            registerDropSelfLootTable(ModBlocks.proustite_block);
            registerDropSelfLootTable(ModBlocks.euclase_block);
            registerDropSelfLootTable(ModBlocks.leucite_block);
            registerDropSelfLootTable(ModBlocks.carnelian_block);
            registerDropSelfLootTable(ModBlocks.benitoite_block);
            registerDropSelfLootTable(ModBlocks.diopside_block);
            registerDropSelfLootTable(ModBlocks.chalcedony_block);

            //Ores
            dropWithFortune(ModBlocks.sugilite_ore, ModItems.sugilite);
            dropWithFortune(ModBlocks.hematite_ore, ModItems.hematite);
            dropWithFortune(ModBlocks.cinnabar_ore, ModItems.cinnabar);
            dropWithFortune(ModBlocks.labradorite_ore, ModItems.labradorite);
            dropWithFortune(ModBlocks.moonstone_ore, ModItems.moonstone);
            dropWithFortune(ModBlocks.opal_ore_red, ModItems.red_opal);
            dropWithFortune(ModBlocks.opal_ore_blue, ModItems.blue_opal);
            dropWithFortune(ModBlocks.opal_ore_green, ModItems.green_opal);
            dropWithFortune(ModBlocks.opal_ore_white, ModItems.white_opal);
            dropWithFortune(ModBlocks.pyrite_ore, ModItems.pyrite);
            registerDropSelfLootTable(ModBlocks.speckled_rock);
            registerDropSelfLootTable(ModBlocks.coarse_rock);
            registerDropSelfLootTable(ModBlocks.precious_rock);

            //Flower Pots
            registerFlowerPot(ModBlocks.potted_thiscus);
            registerFlowerPot(ModBlocks.potted_ouzium);
            registerFlowerPot(ModBlocks.potted_agathum);
            registerFlowerPot(ModBlocks.potted_varloom);
            registerFlowerPot(ModBlocks.potted_corrupted_varloom);
            registerFlowerPot(ModBlocks.potted_missingno_plant);
            registerFlowerPot(ModBlocks.potted_spotted_kersei);
            registerFlowerPot(ModBlocks.potted_thorny_wiltha);
            registerFlowerPot(ModBlocks.potted_roofed_agaric);
            registerFlowerPot(ModBlocks.potted_bulbous_hobina);
            registerFlowerPot(ModBlocks.potted_stickly_cupsir);
            registerFlowerPot(ModBlocks.potted_mystical_murgni);
            registerFlowerPot(ModBlocks.potted_corrupted_gaia_eye);
            registerFlowerPot(ModBlocks.potted_elder_imklia);
            registerFlowerPot(ModBlocks.potted_gold_orb_tucher);
            registerFlowerPot(ModBlocks.potted_missingno_fungus);
            registerFlowerPot(ModBlocks.potted_pink_agate_sapling);
            registerFlowerPot(ModBlocks.potted_blue_agate_sapling);
            registerFlowerPot(ModBlocks.potted_green_agate_sapling);
            registerFlowerPot(ModBlocks.potted_purple_agate_sapling);
            registerFlowerPot(ModBlocks.potted_fossilized_sapling);
            registerFlowerPot(ModBlocks.potted_corrupted_sapling);
            registerFlowerPot(ModBlocks.potted_burnt_sapling);
            registerFlowerPot(ModBlocks.potted_burning_sapling);
            registerFlowerPot(ModBlocks.potted_aura_sapling);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return new ArrayList<>(RegistryHelper.BLOCKS); //TODO
        }
    }

    public static class Entities extends GaiaEntityLootTableProvider {
        public static final ResourceLocation PINK_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/common_sapper");
        public static final ResourceLocation BLUE_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/chilled_sapper");
        public static final ResourceLocation GREEN_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/nutrient_sapper");
        public static final ResourceLocation PURPLE_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/mystified_sapper");

        @Override
        protected void addTables() {

            registerLootTable(ModEntities.AGATE_GOLEM, blankTable());
            registerLootTable(ModEntities.ANCIENT_LAGRAHK, blankTable());
            registerLootTable(ModEntities.ARCHAIC_WARRIOR, warriorTable());
            registerLootTable(ModEntities.BISMUTH_ULETRUS, blankTable());
            registerLootTable(ModEntities.CAVERN_TICK, singleDropTable(ModItems.fine_thread, 0.0F, 1.0F));
            registerLootTable(ModEntities.CONTORTED_NAGA, singleDropTable(ModItems.goldstone, 0.0F, 2.0F));
            registerLootTable(ModEntities.CORRUPT_SAPPER, singleDropTable(ModItems.goldstone_residue, 0.0F, 2.0F));
            registerLootTable(ModEntities.CRYSTAL_GOLEM, blankTable());
            registerLootTable(ModEntities.GROWTH_SAPPER, blankTable());
            registerLootTable(PINK_SAPPER_TABLE, sapperTable(ModItems.pink_geode));
            registerLootTable(BLUE_SAPPER_TABLE, sapperTable(ModItems.blue_geode));
            registerLootTable(GREEN_SAPPER_TABLE, sapperTable(ModItems.green_geode));
            registerLootTable(PURPLE_SAPPER_TABLE, sapperTable(ModItems.purple_geode));
            registerLootTable(ModEntities.HOWLITE_WOLF, blankTable());
            registerLootTable(ModEntities.LESSER_SHOCKSHOOTER, singleDropTable(ModItems.crystallized_lapis_lazuli, 0.0F, 2.0F));
            registerLootTable(ModEntities.LESSER_SPITFIRE, singleDropTable(ModItems.crystallized_redstone, 0.0F, 2.0F));
            registerLootTable(ModEntities.MARKUZAR_PLANT, singleDropTable(ModItems.markuzar_mint, 0.0F, 2.0F));
            registerLootTable(ModEntities.MINERAL_ARENTHIS, cookableDoubleDropTable(ModItems.large_tentacle, ModItems.sugar_cluster, 0.0F, 3.0F, 0.0F, 2.0F));
            registerLootTable(ModEntities.MUCKLING, singleDropTable(ModItems.sweet_muckball, 0.0F, 2.0F));
            registerLootTable(ModEntities.MUTANT_GROWTH_EXTRACTOR, extractorTable());
            registerLootTable(ModEntities.NOMADIC_LAGRAHK, blankTable());
            registerLootTable(ModEntities.PRIMAL_BEAST, blankTable());
            registerLootTable(ModEntities.ROCKY_LUGGEROTH, cookableSingleDropTable(ModItems.luggeroth_chop, 0.0F, 3.0F));
            registerLootTable(ModEntities.RUGGED_LURMORUS, cookableSingleDropTable(ModItems.lurmorus_meat, 0.0F, 3.0F));
            registerLootTable(ModEntities.SALTION, singleDropTable(ModItems.fine_thread, 0.0F, 2.0F));
            registerLootTable(ModEntities.SHALLOW_ARENTHIS, cookableDoubleDropTable(ModItems.small_tentacle, ModItems.sugar_crystals, 0.0F, 3.0F, 0.0F, 2.0F));
            registerLootTable(ModEntities.SHALURKER, blankTable());
            registerLootTable(ModEntities.SPELLBOUND_ELEMENTAL, blankTable());
            registerLootTable(ModEntities.MALACHITE_DRONE, blankTable());

            registerLootTable(ModEntities.BLUE_HOWLITE_WOLF, blankTable());

            registerLootTable(ModEntities.MALACHITE_GUARD, malachiteGuardTable());
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITIES.getValues().stream()
                    .filter(entity -> GaiaDimensionMod.MODID.equals(entity.getRegistryName().getNamespace()))
                    .collect(Collectors.toList());
        }
    }

    public static class Chests extends ChestLootTables {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_AMETHYST, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(2.0F, 8.0F))
                            .addEntry(ItemLootEntry.builder(ModItems.purple_geode_slice)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.pyrite)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.sugilite_axe)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.proustite)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.amethyst_bricks)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.old_bow)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.agate_arrow)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F)))))
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(ModItems.white_opal))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_COPAL, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(2.0F, 8.0F))
                            .addEntry(ItemLootEntry.builder(ModItems.pink_geode_slice)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.pyrite)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.sugilite_axe)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.leucite)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.copal_bricks)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.old_bow)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.agate_arrow)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F)))))
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(ModItems.white_opal))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_JADE, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(2.0F, 8.0F))
                            .addEntry(ItemLootEntry.builder(ModItems.green_geode_slice)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.pyrite)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.sugilite_axe)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.euclase)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.jade_bricks)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.old_bow)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.agate_arrow)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F)))))
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(ModItems.white_opal))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_JET, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(2.0F, 8.0F))
                            .addEntry(ItemLootEntry.builder(ModItems.blue_geode_slice)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.pyrite)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.sugilite_axe)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.ixiolite)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.jet_bricks)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.old_bow)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.agate_arrow)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F)))))
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(ModItems.white_opal))));
            consumer.accept(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(2.0F, 8.0F))
                            .addEntry(ItemLootEntry.builder(ModBlocks.malachite_bricks)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.malachite_floor_tiles)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModBlocks.malachite_pillar)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 5.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.pyrite)
                                    .weight(15)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.ixiolite_sword)
                                    .weight(5))
                            .addEntry(ItemLootEntry.builder(ModItems.euclase_sword)
                                    .weight(2))
                            .addEntry(ItemLootEntry.builder(ModItems.tiligr)
                                    .weight(10)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.proustite_helmet)
                                    .weight(5))
                            .addEntry(ItemLootEntry.builder(ModItems.leucite_helmet)
                                    .weight(2)))
                    .addLootPool(LootPool.builder()
                            .rolls(RandomValueRange.of(1.0F, 3.0F))
                            .addEntry(ItemLootEntry.builder(ModItems.ixiolite)
                                    .weight(6)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.proustite)
                                    .weight(6)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.euclase)
                                    .weight(4)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.leucite)
                                    .weight(4)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.green_opal)
                                    .weight(4)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                            .addEntry(ItemLootEntry.builder(ModItems.white_opal)
                                    .weight(2)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))));
        }
    }
}
