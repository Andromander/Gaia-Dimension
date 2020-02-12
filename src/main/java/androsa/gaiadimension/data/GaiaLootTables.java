package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBlockLootTableProvider;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationResults;

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
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationResults validationresults) { }

    public static class Blocks extends GaiaBlockLootTableProvider {
        @Override
        protected void addTables() {
            //Utility Blocks
            dropSelf(ModBlocks.keystone_block);
            dropSelf(ModBlocks.pyrite_torch);
            dropSelf(ModBlocks.agate_crafting_table);
            registerTable(ModBlocks.crude_storage_crate, GaiaBlockLootTableProvider::smallCrate);
            registerTable(ModBlocks.mega_storage_crate, GaiaBlockLootTableProvider::largeCrate);
            registerTable(ModBlocks.gaia_stone_furnace, GaiaBlockLootTableProvider::withName);
            registerTable(ModBlocks.restructurer, GaiaBlockLootTableProvider::withName);
            registerTable(ModBlocks.purifier, GaiaBlockLootTableProvider::withName);

            //Natural Blocks
            dropSelf(ModBlocks.heavy_soil);
            dropSelf(ModBlocks.corrupt_soil);
            dropSelf(ModBlocks.boggy_soil);
            dropSelf(ModBlocks.light_soil);
            dropWithSilk(ModBlocks.glitter_grass, ModBlocks.heavy_soil);
            dropWithSilk(ModBlocks.corrupt_grass, ModBlocks.corrupt_soil);
            dropWithSilk(ModBlocks.murky_grass, ModBlocks.boggy_soil);
            dropWithSilk(ModBlocks.soft_grass, ModBlocks.light_soil);
            dropAsSilk(ModBlocks.frail_glitter_block);
            dropSelf(ModBlocks.thick_glitter_block);
            dropSelf(ModBlocks.gummy_glitter_block);
            dropSelf(ModBlocks.pink_sludge_block);

            //Plants
            registerTable(ModBlocks.crystal_growth, BlockLootTables::onlyWithShears);
            registerTable(ModBlocks.crystal_growth_red, BlockLootTables::onlyWithShears);
            registerTable(ModBlocks.crystal_growth_black, BlockLootTables::onlyWithShears);
            registerTable(ModBlocks.crystal_growth_seared, BlockLootTables::onlyWithShears);
            registerTable(ModBlocks.crystal_growth_mutant, BlockLootTables::onlyWithShears);
            registerTable(ModBlocks.crystal_growth_aura, BlockLootTables::onlyWithShears);
            dropSelf(ModBlocks.thiscus);
            dropSelf(ModBlocks.ouzium);
            dropSelf(ModBlocks.agathum);
            dropSelf(ModBlocks.varloom);
            dropSelf(ModBlocks.corrupted_varloom);
            dropSelf(ModBlocks.missingno_plant);
            dropSelf(ModBlocks.spotted_kersei);
            dropSelf(ModBlocks.thorny_wiltha);
            dropSelf(ModBlocks.roofed_agaric);
            dropSelf(ModBlocks.bulbous_hobina);
            dropSelf(ModBlocks.stickly_cupsir);
            dropSelf(ModBlocks.mystical_murgni);
            dropSelf(ModBlocks.corrupted_gaia_eye);
            dropSelf(ModBlocks.elder_imklia);
            dropSelf(ModBlocks.gold_orb_tucher);
            dropSelf(ModBlocks.missingno_fungus);

            //Tree Blocks
            dropSelf(ModBlocks.pink_agate_sapling);
            dropSelf(ModBlocks.blue_agate_sapling);
            dropSelf(ModBlocks.green_agate_sapling);
            dropSelf(ModBlocks.purple_agate_sapling);
            dropSelf(ModBlocks.fossilized_sapling);
            dropSelf(ModBlocks.corrupted_sapling);
            dropSelf(ModBlocks.burnt_sapling);
            dropSelf(ModBlocks.burning_sapling);
            dropSelf(ModBlocks.aura_sapling);
            dropChance(ModBlocks.pink_agate_leaves, ModBlocks.pink_agate_sapling, leaf_chances);
            dropChance(ModBlocks.blue_agate_leaves, ModBlocks.blue_agate_sapling, leaf_chances);
            dropChance(ModBlocks.green_agate_leaves, ModBlocks.green_agate_sapling, leaf_chances);
            dropChance(ModBlocks.purple_agate_leaves, ModBlocks.purple_agate_sapling, leaf_chances);
            dropChanceAlternative(ModBlocks.fossilized_leaves, ModBlocks.fossilized_sapling, ModItems.fine_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.corrupted_leaves, ModBlocks.corrupted_sapling, ModItems.goldstone_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.burnt_leaves, ModBlocks.burnt_sapling, Items.GUNPOWDER, leaf_chances);
            dropChanceAlternative(ModBlocks.burning_leaves, ModBlocks.burning_sapling, ModItems.hot_dust, leaf_chances);
            dropChance(ModBlocks.aura_leaves, ModBlocks.aura_sapling, leaf_chances);
            dropSelf(ModBlocks.pink_agate_log);
            dropSelf(ModBlocks.blue_agate_log);
            dropSelf(ModBlocks.green_agate_log);
            dropSelf(ModBlocks.purple_agate_log);
            dropSelf(ModBlocks.fossilized_log);
            dropSelf(ModBlocks.corrupted_log);
            dropSelf(ModBlocks.burnt_log);
            dropSelf(ModBlocks.burning_log);
            dropSelf(ModBlocks.aura_log);
            dropSelf(ModBlocks.stripped_pink_agate_log);
            dropSelf(ModBlocks.stripped_blue_agate_log);
            dropSelf(ModBlocks.stripped_green_agate_log);
            dropSelf(ModBlocks.stripped_purple_agate_log);
            dropSelf(ModBlocks.stripped_fossilized_log);
            dropSelf(ModBlocks.stripped_corrupted_log);
            dropSelf(ModBlocks.stripped_burnt_log);
            dropSelf(ModBlocks.stripped_burning_log);
            dropSelf(ModBlocks.stripped_aura_log);
            dropSelf(ModBlocks.pink_agate_wood);
            dropSelf(ModBlocks.blue_agate_wood);
            dropSelf(ModBlocks.green_agate_wood);
            dropSelf(ModBlocks.purple_agate_wood);
            dropSelf(ModBlocks.fossilized_wood);
            dropSelf(ModBlocks.corrupted_wood);
            dropSelf(ModBlocks.burnt_wood);
            dropSelf(ModBlocks.burning_wood);
            dropSelf(ModBlocks.aura_wood);
            dropSelf(ModBlocks.stripped_pink_agate_wood);
            dropSelf(ModBlocks.stripped_blue_agate_wood);
            dropSelf(ModBlocks.stripped_green_agate_wood);
            dropSelf(ModBlocks.stripped_purple_agate_wood);
            dropSelf(ModBlocks.stripped_fossilized_wood);
            dropSelf(ModBlocks.stripped_corrupted_wood);
            dropSelf(ModBlocks.stripped_burnt_wood);
            dropSelf(ModBlocks.stripped_burning_wood);
            dropSelf(ModBlocks.stripped_aura_wood);

            dropSelf(ModBlocks.salt);
            dropSelf(ModBlocks.saltstone);
            dropAlternative(ModBlocks.pebbles, ModItems.sturdy_pebble);
            dropWithSilk(ModBlocks.gaia_stone, ModBlocks.gaia_cobblestone);
            dropSelf(ModBlocks.gaia_cobblestone);
            dropSelf(ModBlocks.wasteland_stone);
            dropSelf(ModBlocks.static_stone);
            dropAsSilk(ModBlocks.charged_mineral);
            dropSelf(ModBlocks.volcanic_rock);
            dropSelf(ModBlocks.searing_rock);
            dropSelf(ModBlocks.primal_mass);
            dropSelf(ModBlocks.impure_rock);
            dropSelf(ModBlocks.active_rock);
            dropSelf(ModBlocks.impure_sludge);
            dropSelf(ModBlocks.geyser_block);
            dropAsSilk(ModBlocks.sparkling_rock);
            dropSelf(ModBlocks.aura_shoot);

            //Planks (Tiles)
            dropSelf(ModBlocks.pink_agate_planks);
            dropSelf(ModBlocks.blue_agate_planks);
            dropSelf(ModBlocks.green_agate_planks);
            dropSelf(ModBlocks.purple_agate_planks);
            dropSelf(ModBlocks.fossilized_planks);
            dropSelf(ModBlocks.corrupted_planks);
            dropSelf(ModBlocks.burnt_planks);
            dropSelf(ModBlocks.burning_planks);
            dropSelf(ModBlocks.aura_planks);
            dropSlab(ModBlocks.pink_agate_plank_slab);
            dropSlab(ModBlocks.blue_agate_plank_slab);
            dropSlab(ModBlocks.green_agate_plank_slab);
            dropSlab(ModBlocks.purple_agate_plank_slab);
            dropSlab(ModBlocks.fossilized_plank_slab);
            dropSlab(ModBlocks.corrupted_plank_slab);
            dropSlab(ModBlocks.burnt_plank_slab);
            dropSlab(ModBlocks.burning_plank_slab);
            dropSlab(ModBlocks.aura_plank_slab);
            dropSelf(ModBlocks.pink_agate_plank_stairs);
            dropSelf(ModBlocks.blue_agate_plank_stairs);
            dropSelf(ModBlocks.green_agate_plank_stairs);
            dropSelf(ModBlocks.purple_agate_plank_stairs);
            dropSelf(ModBlocks.fossilized_plank_stairs);
            dropSelf(ModBlocks.corrupted_plank_stairs);
            dropSelf(ModBlocks.burnt_plank_stairs);
            dropSelf(ModBlocks.burning_plank_stairs);
            dropSelf(ModBlocks.aura_plank_stairs);

            //Manufactured
            dropAsSilk(ModBlocks.cloudy_glass);
            dropAsSilk(ModBlocks.foggy_glass);
            dropSelf(ModBlocks.gaia_stone_bricks);
            dropSelf(ModBlocks.cracked_gaia_stone_bricks);
            dropSelf(ModBlocks.crusted_gaia_stone_bricks);
            dropSelf(ModBlocks.reinforced_bricks);
            dropSelf(ModBlocks.bolstered_bricks);
            dropSelf(ModBlocks.malachite_bricks);
            dropSelf(ModBlocks.malachite_cracked_bricks);
            dropSelf(ModBlocks.malachite_crusted_bricks);
            dropSelf(ModBlocks.malachite_floor_tiles);
            dropSelf(ModBlocks.malachite_chisel_bricks);
            dropSelf(ModBlocks.malachite_pulsing_bricks);
            dropSelf(ModBlocks.malachite_pulsing_tiles);
            dropSelf(ModBlocks.malachite_pulsing_chisel);
            dropSlab(ModBlocks.malachite_brick_slab);
            dropSlab(ModBlocks.malachite_floor_slab);
            dropSelf(ModBlocks.malachite_pillar);
            dropSelf(ModBlocks.malachite_brick_stairs);
            dropSelf(ModBlocks.malachite_floor_stairs);
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
            dropSelf(ModBlocks.opal_block_red);
            dropSelf(ModBlocks.opal_block_blue);
            dropSelf(ModBlocks.opal_block_green);
            dropSelf(ModBlocks.opal_block_white);
            dropSelf(ModBlocks.pyrite_block);
            dropSelf(ModBlocks.tektite_block);
            dropSelf(ModBlocks.goldstone_block);
            dropSelf(ModBlocks.aura_block);
            dropSelf(ModBlocks.bismuth_block);
            dropSelf(ModBlocks.ixiolite_block);
            dropSelf(ModBlocks.proustite_block);
            dropSelf(ModBlocks.euclase_block);
            dropSelf(ModBlocks.leucite_block);
            dropSelf(ModBlocks.carnelian_block);
            dropSelf(ModBlocks.benitoite_block);
            dropSelf(ModBlocks.diopside_block);
            dropSelf(ModBlocks.chalcedony_block);

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
            dropSelf(ModBlocks.speckled_rock);
            dropSelf(ModBlocks.coarse_rock);
            dropSelf(ModBlocks.precious_rock);

            //Flower Pots
            dropFlowerPot(ModBlocks.potted_thiscus);
            dropFlowerPot(ModBlocks.potted_ouzium);
            dropFlowerPot(ModBlocks.potted_agathum);
            dropFlowerPot(ModBlocks.potted_varloom);
            dropFlowerPot(ModBlocks.potted_corrupted_varloom);
            dropFlowerPot(ModBlocks.potted_missingno_plant);
            dropFlowerPot(ModBlocks.potted_spotted_kersei);
            dropFlowerPot(ModBlocks.potted_thorny_wiltha);
            dropFlowerPot(ModBlocks.potted_roofed_agaric);
            dropFlowerPot(ModBlocks.potted_bulbous_hobina);
            dropFlowerPot(ModBlocks.potted_stickly_cupsir);
            dropFlowerPot(ModBlocks.potted_mystical_murgni);
            dropFlowerPot(ModBlocks.potted_corrupted_gaia_eye);
            dropFlowerPot(ModBlocks.potted_elder_imklia);
            dropFlowerPot(ModBlocks.potted_gold_orb_tucher);
            dropFlowerPot(ModBlocks.potted_missingno_fungus);
            dropFlowerPot(ModBlocks.potted_pink_agate_sapling);
            dropFlowerPot(ModBlocks.potted_blue_agate_sapling);
            dropFlowerPot(ModBlocks.potted_green_agate_sapling);
            dropFlowerPot(ModBlocks.potted_purple_agate_sapling);
            dropFlowerPot(ModBlocks.potted_fossilized_sapling);
            dropFlowerPot(ModBlocks.potted_corrupted_sapling);
            dropFlowerPot(ModBlocks.potted_burnt_sapling);
            dropFlowerPot(ModBlocks.potted_burning_sapling);
            dropFlowerPot(ModBlocks.potted_aura_sapling);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
