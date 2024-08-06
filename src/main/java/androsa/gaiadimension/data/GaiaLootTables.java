package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBlockLootTableProvider;
import androsa.gaiadimension.data.provider.GaiaEntityLootTableProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.values.GaiaBuiltinTables;
import androsa.gaiadimension.registry.values.GaiaChestTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GaiaLootTables extends LootTableProvider {

    public static final float[] leaf_chances = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public GaiaLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, GaiaBuiltinTables.builtin(), List.of(
                new LootTableProvider.SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(Entities::new, LootContextParamSets.ENTITY),
                new LootTableProvider.SubProviderEntry(Chests::new, LootContextParamSets.CHEST)
        ), provider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector collector) {
    }

    public static class Blocks extends GaiaBlockLootTableProvider {
        protected Blocks(HolderLookup.Provider provider) {
            super(provider);
        }

        @Override
        protected void generate() {
            //No Drops
            noDrops(ModBlocks.malachite_guard_spawner);

            //Utility Blocks
            dropSelf(ModBlocks.keystone_block);
            dropSelf(ModBlocks.pyrite_torch);
            dropSelf(ModBlocks.agate_crafting_table);
            dropTable(ModBlocks.crude_storage_crate, smallCrate(ModBlocks.crude_storage_crate.get()));
            dropTable(ModBlocks.mega_storage_crate, largeCrate(ModBlocks.mega_storage_crate.get()));
            dropTable(ModBlocks.gaia_stone_furnace, withName(ModBlocks.gaia_stone_furnace.get()));
            dropTable(ModBlocks.restructurer, withName(ModBlocks.restructurer.get()));
            dropTable(ModBlocks.purifier, withName(ModBlocks.purifier.get()));

            //Natural Blocks
            dropSelf(ModBlocks.heavy_soil);
            dropSelf(ModBlocks.corrupted_soil);
            dropSelf(ModBlocks.boggy_soil);
            dropSelf(ModBlocks.light_soil);
            dropSelf(ModBlocks.aurum_soil);
            dropWithSilk(ModBlocks.glitter_grass, ModBlocks.heavy_soil);
            dropWithSilk(ModBlocks.corrupted_grass, ModBlocks.corrupted_soil);
            dropWithSilk(ModBlocks.murky_grass, ModBlocks.boggy_soil);
            dropWithSilk(ModBlocks.soft_grass, ModBlocks.light_soil);
            dropWithSilk(ModBlocks.gilded_grass, ModBlocks.aurum_soil);
            dropOnlySilk(ModBlocks.frail_glitter_block);
            dropSelf(ModBlocks.thick_glitter_block);
            dropSelf(ModBlocks.gummy_glitter_block);
            dropSelf(ModBlocks.pink_sludge_block);

            //Plants
            dropTable(ModBlocks.crystal_growth, withShards(ModBlocks.crystal_growth.get()));
            dropTable(ModBlocks.crystal_growth_red, withShards(ModBlocks.crystal_growth_red.get()));
            dropTable(ModBlocks.crystal_growth_black, withShards(ModBlocks.crystal_growth_black.get()));
            dropTable(ModBlocks.crystal_growth_seared, withShards(ModBlocks.crystal_growth_seared.get()));
            dropTable(ModBlocks.crystal_growth_mutant, withShards(ModBlocks.crystal_growth_mutant.get()));
            dropTable(ModBlocks.crystal_growth_aura, withShards(ModBlocks.crystal_growth_aura.get()));
            dropTable(ModBlocks.golden_grass, createShearsOnlyDrop(ModBlocks.golden_grass.get()));
            dropTable(ModBlocks.tall_golden_grass, (block) -> GaiaBlockLootTableProvider.doubleShearsOnly(block, ModBlocks.golden_grass.get()));
            dropSelf(ModBlocks.thiscus);
            dropSelf(ModBlocks.ouzium);
            dropSelf(ModBlocks.agathum);
            dropSelf(ModBlocks.varloom);
            dropSelf(ModBlocks.corrupted_varloom);
            dropSelf(ModBlocks.glamelea);
            dropSelf(ModBlocks.missingno_plant);
            dropSelf(ModBlocks.spotted_kersei);
            dropSelf(ModBlocks.thorny_wiltha);
            dropSelf(ModBlocks.roofed_agaric);
            dropSelf(ModBlocks.bulbous_hobina);
            dropSelf(ModBlocks.stickly_cupsir);
            dropSelf(ModBlocks.mystical_murgni);
            dropSelf(ModBlocks.corrupted_gaia_eye);
            dropSelf(ModBlocks.twinkling_gilsri);
            dropSelf(ModBlocks.elder_imklia);
            dropSelf(ModBlocks.gold_orb_tucher);
            dropSelf(ModBlocks.missingno_fungus);
            dropTable(ModBlocks.golden_vine, createShearsOnlyDrop(ModBlocks.golden_vine.get()));
            dropSelf(ModBlocks.sombre_cacti);
            dropTable(ModBlocks.sombre_shrub, createShearsOnlyDrop(ModBlocks.sombre_shrub.get()));

            //Tree Blocks
            dropSelf(ModBlocks.pink_agate_sapling);
            dropSelf(ModBlocks.blue_agate_sapling);
            dropSelf(ModBlocks.green_agate_sapling);
            dropSelf(ModBlocks.purple_agate_sapling);
            dropSelf(ModBlocks.fossilized_sapling);
            dropSelf(ModBlocks.corrupted_sapling);
            dropSelf(ModBlocks.burnt_sapling);
            dropSelf(ModBlocks.fire_agate_sapling);
            dropSelf(ModBlocks.aura_sapling);
            dropSelf(ModBlocks.golden_sapling);
            dropChance(ModBlocks.pink_agate_leaves, ModBlocks.pink_agate_sapling, leaf_chances);
            dropChance(ModBlocks.blue_agate_leaves, ModBlocks.blue_agate_sapling, leaf_chances);
            dropChance(ModBlocks.green_agate_leaves, ModBlocks.green_agate_sapling, leaf_chances);
            dropChance(ModBlocks.purple_agate_leaves, ModBlocks.purple_agate_sapling, leaf_chances);
            dropChanceAlternative(ModBlocks.fossilized_leaves, ModBlocks.fossilized_sapling, ModItems.fine_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.corrupted_leaves, ModBlocks.corrupted_sapling, ModItems.goldstone_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.burnt_leaves, ModBlocks.burnt_sapling, () -> Items.GUNPOWDER, leaf_chances);
            dropChanceAlternative(ModBlocks.fire_agate_leaves, ModBlocks.fire_agate_sapling, ModItems.hot_dust, leaf_chances);
            dropChance(ModBlocks.aura_leaves, ModBlocks.aura_sapling, leaf_chances);
            dropChance(ModBlocks.golden_leaves, ModBlocks.golden_sapling, leaf_chances);
            dropSelf(ModBlocks.pink_agate_log);
            dropSelf(ModBlocks.blue_agate_log);
            dropSelf(ModBlocks.green_agate_log);
            dropSelf(ModBlocks.purple_agate_log);
            dropSelf(ModBlocks.fossilized_log);
            dropSelf(ModBlocks.corrupted_log);
            dropSelf(ModBlocks.burnt_log);
            dropSelf(ModBlocks.fire_agate_log);
            dropSelf(ModBlocks.aura_log);
            dropSelf(ModBlocks.golden_log);
            dropSelf(ModBlocks.stripped_pink_agate_log);
            dropSelf(ModBlocks.stripped_blue_agate_log);
            dropSelf(ModBlocks.stripped_green_agate_log);
            dropSelf(ModBlocks.stripped_purple_agate_log);
            dropSelf(ModBlocks.stripped_fossilized_log);
            dropSelf(ModBlocks.stripped_corrupted_log);
            dropSelf(ModBlocks.stripped_burnt_log);
            dropSelf(ModBlocks.stripped_fire_agate_log);
            dropSelf(ModBlocks.stripped_aura_log);
            dropSelf(ModBlocks.stripped_golden_log);
            dropSelf(ModBlocks.pink_agate_wood);
            dropSelf(ModBlocks.blue_agate_wood);
            dropSelf(ModBlocks.green_agate_wood);
            dropSelf(ModBlocks.purple_agate_wood);
            dropSelf(ModBlocks.fossilized_wood);
            dropSelf(ModBlocks.corrupted_wood);
            dropSelf(ModBlocks.burnt_wood);
            dropSelf(ModBlocks.fire_agate_wood);
            dropSelf(ModBlocks.aura_wood);
            dropSelf(ModBlocks.golden_wood);
            dropSelf(ModBlocks.stripped_pink_agate_wood);
            dropSelf(ModBlocks.stripped_blue_agate_wood);
            dropSelf(ModBlocks.stripped_green_agate_wood);
            dropSelf(ModBlocks.stripped_purple_agate_wood);
            dropSelf(ModBlocks.stripped_fossilized_wood);
            dropSelf(ModBlocks.stripped_corrupted_wood);
            dropSelf(ModBlocks.stripped_burnt_wood);
            dropSelf(ModBlocks.stripped_fire_agate_wood);
            dropSelf(ModBlocks.stripped_aura_wood);
            dropSelf(ModBlocks.stripped_golden_wood);

            dropSelf(ModBlocks.salt);
            dropSelf(ModBlocks.saltstone);
            dropAlternative(ModBlocks.pebbles, ModItems.sturdy_pebble);
            dropWithSilk(ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone);
            dropSelf(ModBlocks.gaia_cobblestone);
            dropSelf(ModBlocks.wasteland_stone);
            dropSelf(ModBlocks.static_stone);
            dropOnlySilk(ModBlocks.charged_mineral);
            dropSelf(ModBlocks.volcanic_rock);
            dropSelf(ModBlocks.searing_rock);
            dropSelf(ModBlocks.primal_mass);
            dropSelf(ModBlocks.nexustone);
            dropSelf(ModBlocks.impure_rock);
            dropSelf(ModBlocks.active_rock);
            dropSelf(ModBlocks.impure_sludge);
            dropSelf(ModBlocks.geyser_block);
            dropOnlySilk(ModBlocks.sparkling_rock);
            dropSelf(ModBlocks.aura_shoot);
            dropSelf(ModBlocks.golden_stone);
            dropSelf(ModBlocks.tough_golden_stone);
            dropSelf(ModBlocks.brilliant_stone);
            dropSelf(ModBlocks.gilded_brilliant_stone);
            dropSelf(ModBlocks.aurum_mud);
            dropSelf(ModBlocks.golden_sand);
            dropSelf(ModBlocks.scarlet_mookaite);
            dropSelf(ModBlocks.auburn_mookaite);
            dropSelf(ModBlocks.gold_mookaite);
            dropSelf(ModBlocks.mauve_mookaite);
            dropSelf(ModBlocks.beige_mookaite);
            dropSelf(ModBlocks.ivory_mookaite);

            //Tiles
            dropSelf(ModBlocks.pink_agate_tiles);
            dropSelf(ModBlocks.blue_agate_tiles);
            dropSelf(ModBlocks.green_agate_tiles);
            dropSelf(ModBlocks.purple_agate_tiles);
            dropSelf(ModBlocks.fossilized_tiles);
            dropSelf(ModBlocks.corrupted_tiles);
            dropSelf(ModBlocks.burnt_tiles);
            dropSelf(ModBlocks.fire_agate_tiles);
            dropSelf(ModBlocks.aura_tiles);
            dropSelf(ModBlocks.golden_tiles);
            dropSlab(ModBlocks.pink_agate_tile_slab);
            dropSlab(ModBlocks.blue_agate_tile_slab);
            dropSlab(ModBlocks.green_agate_tile_slab);
            dropSlab(ModBlocks.purple_agate_tile_slab);
            dropSlab(ModBlocks.fossilized_tile_slab);
            dropSlab(ModBlocks.corrupted_tile_slab);
            dropSlab(ModBlocks.burnt_tile_slab);
            dropSlab(ModBlocks.fire_agate_tile_slab);
            dropSlab(ModBlocks.aura_tile_slab);
            dropSlab(ModBlocks.golden_tile_slab);
            dropSelf(ModBlocks.pink_agate_tile_stairs);
            dropSelf(ModBlocks.blue_agate_tile_stairs);
            dropSelf(ModBlocks.green_agate_tile_stairs);
            dropSelf(ModBlocks.purple_agate_tile_stairs);
            dropSelf(ModBlocks.fossilized_tile_stairs);
            dropSelf(ModBlocks.corrupted_tile_stairs);
            dropSelf(ModBlocks.burnt_tile_stairs);
            dropSelf(ModBlocks.fire_agate_tile_stairs);
            dropSelf(ModBlocks.aura_tile_stairs);
            dropSelf(ModBlocks.golden_tile_stairs);
            dropCurtain(ModBlocks.pink_agate_curtain);
            dropCurtain(ModBlocks.blue_agate_curtain);
            dropCurtain(ModBlocks.green_agate_curtain);
            dropCurtain(ModBlocks.purple_agate_curtain);
            dropCurtain(ModBlocks.fossilized_curtain);
            dropCurtain(ModBlocks.corrupted_curtain);
            dropCurtain(ModBlocks.burnt_agate_curtain);
            dropCurtain(ModBlocks.fire_agate_curtain);
            dropCurtain(ModBlocks.aura_curtain);
            dropCurtain(ModBlocks.golden_curtain);

            //Manufactured
            dropOnlySilk(ModBlocks.cloudy_glass);
            dropOnlySilk(ModBlocks.foggy_glass);
            dropSelf(ModBlocks.gaia_stone_bricks);
            dropSelf(ModBlocks.cracked_gaia_stone_bricks);
            dropSelf(ModBlocks.crusted_gaia_stone_bricks);

            dropSelf(ModBlocks.raw_jade);
            dropSelf(ModBlocks.jade_bricks);
            dropSlab(ModBlocks.jade_brick_slab);
            dropSelf(ModBlocks.jade_brick_stairs);
            dropSelf(ModBlocks.cracked_jade_bricks);
            dropSlab(ModBlocks.cracked_jade_brick_slab);
            dropSelf(ModBlocks.cracked_jade_brick_stairs);
            dropSelf(ModBlocks.crusted_jade_bricks);
            dropSlab(ModBlocks.crusted_jade_brick_slab);
            dropSelf(ModBlocks.crusted_jade_brick_stairs);
            dropSelf(ModBlocks.raw_copal);
            dropSelf(ModBlocks.copal_bricks);
            dropSlab(ModBlocks.copal_brick_slab);
            dropSelf(ModBlocks.copal_brick_stairs);
            dropSelf(ModBlocks.cracked_copal_bricks);
            dropSlab(ModBlocks.cracked_copal_brick_slab);
            dropSelf(ModBlocks.cracked_copal_brick_stairs);
            dropSelf(ModBlocks.crusted_copal_bricks);
            dropSlab(ModBlocks.crusted_copal_brick_slab);
            dropSelf(ModBlocks.crusted_copal_brick_stairs);
            dropSelf(ModBlocks.raw_jet);
            dropSelf(ModBlocks.jet_bricks);
            dropSlab(ModBlocks.jet_brick_slab);
            dropSelf(ModBlocks.jet_brick_stairs);
            dropSelf(ModBlocks.cracked_jet_bricks);
            dropSlab(ModBlocks.cracked_jet_brick_slab);
            dropSelf(ModBlocks.cracked_jet_brick_stairs);
            dropSelf(ModBlocks.crusted_jet_bricks);
            dropSlab(ModBlocks.crusted_jet_brick_slab);
            dropSelf(ModBlocks.crusted_jet_brick_stairs);
            dropSelf(ModBlocks.raw_amethyst);
            dropSelf(ModBlocks.amethyst_bricks);
            dropSlab(ModBlocks.amethyst_brick_slab);
            dropSelf(ModBlocks.amethyst_brick_stairs);
            dropSelf(ModBlocks.cracked_amethyst_bricks);
            dropSlab(ModBlocks.cracked_amethyst_brick_slab);
            dropSelf(ModBlocks.cracked_amethyst_brick_stairs);
            dropSelf(ModBlocks.crusted_amethyst_bricks);
            dropSlab(ModBlocks.crusted_amethyst_brick_slab);
            dropSelf(ModBlocks.crusted_amethyst_brick_stairs);

            dropSelf(ModBlocks.reinforced_bricks);
            dropSelf(ModBlocks.bolstered_bricks);
            dropSelf(ModBlocks.malachite_bricks);
            dropSelf(ModBlocks.malachite_cracked_bricks);
            dropSelf(ModBlocks.malachite_crusted_bricks);
            dropSelf(ModBlocks.malachite_tiles);
            dropSelf(ModBlocks.malachite_chisel_bricks);
            dropSelf(ModBlocks.malachite_pulsing_bricks);
            dropSelf(ModBlocks.malachite_pulsing_tiles);
            dropSelf(ModBlocks.malachite_pulsing_chisel);
            dropSlab(ModBlocks.malachite_brick_slab);
            dropSlab(ModBlocks.malachite_cracked_brick_slab);
            dropSlab(ModBlocks.malachite_crusted_brick_slab);
            dropSlab(ModBlocks.malachite_tile_slab);
            dropSelf(ModBlocks.malachite_pillar);
            dropSelf(ModBlocks.malachite_brick_stairs);
            dropSelf(ModBlocks.malachite_cracked_brick_stairs);
            dropSelf(ModBlocks.malachite_crusted_brick_stairs);
            dropSelf(ModBlocks.malachite_tile_stairs);
            dropSelf(ModBlocks.malachite_chisel_stairs);
            dropSelf(ModBlocks.malachite_pulsing_brick_stairs);
            dropSelf(ModBlocks.malachite_pulsing_floor_stairs);
            dropSelf(ModBlocks.malachite_pulsing_chisel_stairs);
            dropSelf(ModBlocks.malachite_pillar_stairs);

            //Storage Blocks
            dropSelf(ModBlocks.sugilite_block);
            dropSelf(ModBlocks.hematite_block);
            dropSelf(ModBlocks.cinnabar_block);
            dropSelf(ModBlocks.labradorite_block);
            dropSelf(ModBlocks.moonstone_block);
            dropSelf(ModBlocks.red_opal_block);
            dropSelf(ModBlocks.blue_opal_block);
            dropSelf(ModBlocks.green_opal_block);
            dropSelf(ModBlocks.white_opal_block);
            dropSelf(ModBlocks.pyrite_block);
            dropSelf(ModBlocks.tektite_block);
            dropSelf(ModBlocks.goldstone_block);
            dropSelf(ModBlocks.aura_block);
            dropSelf(ModBlocks.bismuth_block);
            dropSelf(ModBlocks.opalite_block);
            dropSelf(ModBlocks.stibnite_block);
            dropSelf(ModBlocks.proustite_block);
            dropSelf(ModBlocks.euclase_block);
            dropSelf(ModBlocks.albite_block);
            dropSelf(ModBlocks.carnelian_block);
            dropSelf(ModBlocks.benitoite_block);
            dropSelf(ModBlocks.diopside_block);
            dropSelf(ModBlocks.goshenite_block);
            dropSelf(ModBlocks.celestine_block);

            //Ores
            dropWithFortune(ModBlocks.sugilite_ore, ModItems.sugilite);
            dropWithFortune(ModBlocks.hematite_ore, ModItems.hematite);
            dropWithFortune(ModBlocks.cinnabar_ore, ModItems.cinnabar);
            dropWithFortune(ModBlocks.labradorite_ore, ModItems.labradorite);
            dropWithFortune(ModBlocks.moonstone_ore, ModItems.moonstone);
            dropWithFortune(ModBlocks.red_opal_ore, ModItems.red_opal);
            dropWithFortune(ModBlocks.blue_opal_ore, ModItems.blue_opal);
            dropWithFortune(ModBlocks.green_opal_ore, ModItems.green_opal);
            dropWithFortune(ModBlocks.white_opal_ore, ModItems.white_opal);
            dropWithFortune(ModBlocks.pyrite_ore, ModItems.pyrite);
            dropSelf(ModBlocks.speckled_rock);
            dropSelf(ModBlocks.coarse_rock);
            dropSelf(ModBlocks.precious_rock);
            dropWithMultiple(ModBlocks.scarlet_opalite_ore, ModItems.opalite);
            dropWithMultiple(ModBlocks.auburn_opalite_ore, ModItems.opalite);
            dropWithMultiple(ModBlocks.gold_opalite_ore, ModItems.opalite);
            dropWithMultiple(ModBlocks.mauve_opalite_ore, ModItems.opalite);
            dropWithMultiple(ModBlocks.beige_opalite_ore, ModItems.opalite);
            dropWithMultiple(ModBlocks.ivory_opalite_ore, ModItems.opalite);
            dropWithFortune(ModBlocks.celestine_ore, ModItems.celestine);

            //Flower Pots
            dropPot(ModBlocks.potted_thiscus);
            dropPot(ModBlocks.potted_ouzium);
            dropPot(ModBlocks.potted_agathum);
            dropPot(ModBlocks.potted_varloom);
            dropPot(ModBlocks.potted_corrupted_varloom);
            dropPot(ModBlocks.potted_missingno_plant);
            dropPot(ModBlocks.potted_spotted_kersei);
            dropPot(ModBlocks.potted_thorny_wiltha);
            dropPot(ModBlocks.potted_roofed_agaric);
            dropPot(ModBlocks.potted_bulbous_hobina);
            dropPot(ModBlocks.potted_stickly_cupsir);
            dropPot(ModBlocks.potted_mystical_murgni);
            dropPot(ModBlocks.potted_corrupted_gaia_eye);
            dropPot(ModBlocks.potted_twinkling_gilsri);
            dropPot(ModBlocks.potted_elder_imklia);
            dropPot(ModBlocks.potted_gold_orb_tucher);
            dropPot(ModBlocks.potted_missingno_fungus);
            dropPot(ModBlocks.potted_pink_agate_sapling);
            dropPot(ModBlocks.potted_blue_agate_sapling);
            dropPot(ModBlocks.potted_green_agate_sapling);
            dropPot(ModBlocks.potted_purple_agate_sapling);
            dropPot(ModBlocks.potted_fossilized_sapling);
            dropPot(ModBlocks.potted_corrupted_sapling);
            dropPot(ModBlocks.potted_burnt_sapling);
            dropPot(ModBlocks.potted_fire_agate_sapling);
            dropPot(ModBlocks.potted_aura_sapling);
            dropPot(ModBlocks.potted_golden_sapling);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    public static class Entities extends GaiaEntityLootTableProvider {
        protected Entities(HolderLookup.Provider provider) {
            super(provider);
        }

        @Override
        public void generate() {
            addTable(ModEntities.AGATE_GOLEM, blankTable());
            addTable(ModEntities.ANCIENT_LAGRAHK, blankTable());
            addTable(ModEntities.ARCHAIC_WARRIOR, warriorTable());
            addTable(ModEntities.BISMUTH_ULETRUS, blankTable());
            addTable(ModEntities.CAVERN_TICK, singleDropTable(ModItems.fine_thread, 0.0F, 1.0F));
            addTable(ModEntities.CONTORTED_NAGA, singleDropTable(ModItems.goldstone, 0.0F, 2.0F));
            addTable(ModEntities.CORRUPT_SAPPER, singleDropTable(ModItems.goldstone_residue, 0.0F, 2.0F));
            addTable(ModEntities.CRYSTAL_GOLEM, blankTable());
            addTable(ModEntities.GROWTH_SAPPER, blankTable());
            addTable(ModEntities.GROWTH_SAPPER, GaiaBuiltinTables.PINK_SAPPER_TABLE, sapperTable(ModItems.pink_geode));
            addTable(ModEntities.GROWTH_SAPPER, GaiaBuiltinTables.BLUE_SAPPER_TABLE, sapperTable(ModItems.blue_geode));
            addTable(ModEntities.GROWTH_SAPPER, GaiaBuiltinTables.GREEN_SAPPER_TABLE, sapperTable(ModItems.green_geode));
            addTable(ModEntities.GROWTH_SAPPER, GaiaBuiltinTables.PURPLE_SAPPER_TABLE, sapperTable(ModItems.purple_geode));
            addTable(ModEntities.HOWLITE_WOLF, blankTable());
            addTable(ModEntities.LESSER_SHOCKSHOOTER, singleDropTable(ModItems.crystallized_lapis_lazuli, 0.0F, 2.0F));
            addTable(ModEntities.LESSER_SPITFIRE, singleDropTable(ModItems.crystallized_redstone, 0.0F, 2.0F));
            addTable(ModEntities.MARKUZAR_PLANT, singleDropTable(ModItems.markuzar_mint, 0.0F, 2.0F));
            addTable(ModEntities.MINERAL_ARENTHIS, cookableDoubleDropTable(ModItems.large_tentacle, ModItems.sugar_cluster, 0.0F, 3.0F, 0.0F, 2.0F));
            addTable(ModEntities.MUCKLING, singleDropTable(ModItems.sweet_muckball, 0.0F, 2.0F));
            addTable(ModEntities.MUTANT_GROWTH_EXTRACTOR, extractorTable());
            addTable(ModEntities.NOMADIC_LAGRAHK, blankTable());
            addTable(ModEntities.PRIMAL_BEAST, blankTable());
            addTable(ModEntities.ROCKY_LUGGEROTH, cookableSingleDropTable(ModItems.luggeroth_chop, 0.0F, 3.0F));
            addTable(ModEntities.RUGGED_LURMORUS, cookableSingleDropTable(ModItems.lurmorus_meat, 0.0F, 3.0F));
            addTable(ModEntities.SALTION, singleDropTable(ModItems.fine_thread, 0.0F, 2.0F));
            addTable(ModEntities.SHALLOW_ARENTHIS, cookableDoubleDropTable(ModItems.small_tentacle, ModItems.sugar_crystals, 0.0F, 3.0F, 0.0F, 2.0F));
            addTable(ModEntities.SHALURKER, blankTable());
            addTable(ModEntities.SPELLBOUND_ELEMENTAL, blankTable());
            addTable(ModEntities.MALACHITE_DRONE, blankTable());
            addTable(ModEntities.MOOKAITE_CONSTRUCT, blankTable());
            addTable(ModEntities.OPALITE_CONSTRUCT, blankTable());
            addTable(ModEntities.GROWTH_GRAZER, blankTable());
            addTable(ModEntities.AUREATE_EVRAUN, blankTable());

            addTable(ModEntities.BLUE_HOWLITE_WOLF, blankTable());

            addTable(ModEntities.MALACHITE_GUARD, malachiteGuardTable());
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return ModEntities.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::value);
        }
    }

    public record Chests(HolderLookup.Provider provider) implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_AMETHYST, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 8.0F))
                            .add(LootItem.lootTableItem(ModItems.purple_geode_slice.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.scaynyx_ingot.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.sugilite_axe.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.proustite.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.amethyst_bricks.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                            .add(LootItem.lootTableItem(ModItems.old_bow.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.agate_arrow.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.white_opal.get()))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_COPAL, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 8.0F))
                            .add(LootItem.lootTableItem(ModItems.pink_geode_slice.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.scaynyx_ingot.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.sugilite_axe.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.albite.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.copal_bricks.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                            .add(LootItem.lootTableItem(ModItems.old_bow.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.agate_arrow.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.white_opal.get()))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_JADE, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 8.0F))
                            .add(LootItem.lootTableItem(ModItems.green_geode_slice.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.scaynyx_ingot.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.sugilite_axe.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.euclase.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.jade_bricks.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                            .add(LootItem.lootTableItem(ModItems.old_bow.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.agate_arrow.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.white_opal.get()))));
            consumer.accept(GaiaChestTables.CHESTS_MINITOWER_JET, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 8.0F))
                            .add(LootItem.lootTableItem(ModItems.blue_geode_slice.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.scaynyx_ingot.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.sugilite_axe.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.stibnite.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.jet_bricks.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 9.0F))))
                            .add(LootItem.lootTableItem(ModItems.old_bow.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.agate_arrow.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))))
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(ModItems.white_opal.get()))));
            consumer.accept(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(2.0F, 8.0F))
                            .add(LootItem.lootTableItem(ModBlocks.malachite_bricks.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.malachite_tiles.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.malachite_pillar.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.stibnite_sword.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(ModItems.euclase_sword.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.tiligr.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                            .add(LootItem.lootTableItem(ModItems.proustite_helmet.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(ModItems.albite_helmet.get())
                                    .setWeight(2)))
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 3.0F))
                            .add(LootItem.lootTableItem(ModItems.stibnite.get())
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.proustite.get())
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.euclase.get())
                                    .setWeight(4)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.albite.get())
                                    .setWeight(4)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.green_opal.get())
                                    .setWeight(4)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.white_opal.get())
                                    .setWeight(2)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));
        }
    }
}
