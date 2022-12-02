package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBlockLootTableProvider;
import androsa.gaiadimension.data.provider.GaiaEntityLootTableProvider;
import androsa.gaiadimension.registry.GaiaChestTables;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

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
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootContextParamSets.BLOCK), Pair.of(Entities::new, LootContextParamSets.ENTITY), Pair.of(Chests::new, LootContextParamSets.CHEST));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationresults) {
    }

    public static class Blocks extends GaiaBlockLootTableProvider {
        @Override
        protected void addTables() {
            //Utility Blocks
            dropSelf(ModBlocks.keystone_block);
            dropSelf(ModBlocks.pyrite_torch);
            dropSelf(ModBlocks.agate_crafting_table);
            dropTable(ModBlocks.crude_storage_crate, GaiaBlockLootTableProvider::smallCrate);
            dropTable(ModBlocks.mega_storage_crate, GaiaBlockLootTableProvider::largeCrate);
            dropTable(ModBlocks.gaia_stone_furnace, GaiaBlockLootTableProvider::withName);
            dropTable(ModBlocks.restructurer, GaiaBlockLootTableProvider::withName);
            dropTable(ModBlocks.purifier, GaiaBlockLootTableProvider::withName);

            //Natural Blocks
            dropSelf(ModBlocks.heavy_soil);
            dropSelf(ModBlocks.corrupt_soil);
            dropSelf(ModBlocks.boggy_soil);
            dropSelf(ModBlocks.light_soil);
            dropSelf(ModBlocks.aurum_soil);
            dropWithSilk(ModBlocks.glitter_grass, ModBlocks.heavy_soil);
            dropWithSilk(ModBlocks.corrupt_grass, ModBlocks.corrupt_soil);
            dropWithSilk(ModBlocks.murky_grass, ModBlocks.boggy_soil);
            dropWithSilk(ModBlocks.soft_grass, ModBlocks.light_soil);
            dropWithSilk(ModBlocks.gilded_grass, ModBlocks.aurum_soil);
            dropOnlySilk(ModBlocks.frail_glitter_block);
            dropSelf(ModBlocks.thick_glitter_block);
            dropSelf(ModBlocks.gummy_glitter_block);
            dropSelf(ModBlocks.pink_sludge_block);

            //Plants
            dropTable(ModBlocks.crystal_growth, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.crystal_growth_red, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.crystal_growth_black, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.crystal_growth_seared, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.crystal_growth_mutant, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.crystal_growth_aura, GaiaBlockLootTableProvider::withShards);
            dropTable(ModBlocks.golden_grass, BlockLoot::createShearsOnlyDrop);
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
            dropTable(ModBlocks.golden_vine, BlockLoot::createShearsOnlyDrop);
            dropSelf(ModBlocks.sombre_cacti);
            dropTable(ModBlocks.sombre_shrub, BlockLoot::createShearsOnlyDrop);

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
            dropSelf(ModBlocks.golden_sapling);
            dropChance(ModBlocks.pink_agate_leaves, ModBlocks.pink_agate_sapling, leaf_chances);
            dropChance(ModBlocks.blue_agate_leaves, ModBlocks.blue_agate_sapling, leaf_chances);
            dropChance(ModBlocks.green_agate_leaves, ModBlocks.green_agate_sapling, leaf_chances);
            dropChance(ModBlocks.purple_agate_leaves, ModBlocks.purple_agate_sapling, leaf_chances);
            dropChanceAlternative(ModBlocks.fossilized_leaves, ModBlocks.fossilized_sapling, ModItems.fine_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.corrupted_leaves, ModBlocks.corrupted_sapling, ModItems.goldstone_dust, leaf_chances);
            dropChanceAlternative(ModBlocks.burnt_leaves, ModBlocks.burnt_sapling, () -> Items.GUNPOWDER, leaf_chances);
            dropChanceAlternative(ModBlocks.burning_leaves, ModBlocks.burning_sapling, ModItems.hot_dust, leaf_chances);
            dropChance(ModBlocks.aura_leaves, ModBlocks.aura_sapling, leaf_chances);
            dropChance(ModBlocks.golden_leaves, ModBlocks.golden_sapling, leaf_chances);
            dropSelf(ModBlocks.pink_agate_log);
            dropSelf(ModBlocks.blue_agate_log);
            dropSelf(ModBlocks.green_agate_log);
            dropSelf(ModBlocks.purple_agate_log);
            dropSelf(ModBlocks.fossilized_log);
            dropSelf(ModBlocks.corrupted_log);
            dropSelf(ModBlocks.burnt_log);
            dropSelf(ModBlocks.burning_log);
            dropSelf(ModBlocks.aura_log);
            dropSelf(ModBlocks.golden_log);
            dropSelf(ModBlocks.stripped_pink_agate_log);
            dropSelf(ModBlocks.stripped_blue_agate_log);
            dropSelf(ModBlocks.stripped_green_agate_log);
            dropSelf(ModBlocks.stripped_purple_agate_log);
            dropSelf(ModBlocks.stripped_fossilized_log);
            dropSelf(ModBlocks.stripped_corrupted_log);
            dropSelf(ModBlocks.stripped_burnt_log);
            dropSelf(ModBlocks.stripped_burning_log);
            dropSelf(ModBlocks.stripped_aura_log);
            dropSelf(ModBlocks.stripped_golden_log);
            dropSelf(ModBlocks.pink_agate_wood);
            dropSelf(ModBlocks.blue_agate_wood);
            dropSelf(ModBlocks.green_agate_wood);
            dropSelf(ModBlocks.purple_agate_wood);
            dropSelf(ModBlocks.fossilized_wood);
            dropSelf(ModBlocks.corrupted_wood);
            dropSelf(ModBlocks.burnt_wood);
            dropSelf(ModBlocks.burning_wood);
            dropSelf(ModBlocks.aura_wood);
            dropSelf(ModBlocks.golden_wood);
            dropSelf(ModBlocks.stripped_pink_agate_wood);
            dropSelf(ModBlocks.stripped_blue_agate_wood);
            dropSelf(ModBlocks.stripped_green_agate_wood);
            dropSelf(ModBlocks.stripped_purple_agate_wood);
            dropSelf(ModBlocks.stripped_fossilized_wood);
            dropSelf(ModBlocks.stripped_corrupted_wood);
            dropSelf(ModBlocks.stripped_burnt_wood);
            dropSelf(ModBlocks.stripped_burning_wood);
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
            dropSelf(ModBlocks.malachite_floor_tiles);
            dropSelf(ModBlocks.malachite_chisel_bricks);
            dropSelf(ModBlocks.malachite_pulsing_bricks);
            dropSelf(ModBlocks.malachite_pulsing_tiles);
            dropSelf(ModBlocks.malachite_pulsing_chisel);
            dropSlab(ModBlocks.malachite_brick_slab);
            dropSlab(ModBlocks.malachite_cracked_brick_slab);
            dropSlab(ModBlocks.malachite_crusted_brick_slab);
            dropSlab(ModBlocks.malachite_floor_slab);
            dropSelf(ModBlocks.malachite_pillar);
            dropSelf(ModBlocks.malachite_brick_stairs);
            dropSelf(ModBlocks.malachite_cracked_brick_stairs);
            dropSelf(ModBlocks.malachite_crusted_brick_stairs);
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
            dropPot(ModBlocks.potted_burning_sapling);
            dropPot(ModBlocks.potted_aura_sapling);
            dropPot(ModBlocks.potted_golden_sapling);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }

    public static class Entities extends GaiaEntityLootTableProvider {
        public static final ResourceLocation PINK_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/common_sapper");
        public static final ResourceLocation BLUE_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/chilled_sapper");
        public static final ResourceLocation GREEN_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/nutrient_sapper");
        public static final ResourceLocation PURPLE_SAPPER_TABLE = new ResourceLocation(GaiaDimensionMod.MODID, "entities/mystified_sapper");

        @Override
        protected void addTables() {
            addTable(ModEntities.AGATE_GOLEM, blankTable());
            addTable(ModEntities.ANCIENT_LAGRAHK, blankTable());
            addTable(ModEntities.ARCHAIC_WARRIOR, warriorTable());
            addTable(ModEntities.BISMUTH_ULETRUS, blankTable());
            addTable(ModEntities.CAVERN_TICK, singleDropTable(ModItems.fine_thread, 0.0F, 1.0F));
            addTable(ModEntities.CONTORTED_NAGA, singleDropTable(ModItems.goldstone, 0.0F, 2.0F));
            addTable(ModEntities.CORRUPT_SAPPER, singleDropTable(ModItems.goldstone_residue, 0.0F, 2.0F));
            addTable(ModEntities.CRYSTAL_GOLEM, blankTable());
            addTable(ModEntities.GROWTH_SAPPER, blankTable());
            add(PINK_SAPPER_TABLE, sapperTable(ModItems.pink_geode));
            add(BLUE_SAPPER_TABLE, sapperTable(ModItems.blue_geode));
            add(GREEN_SAPPER_TABLE, sapperTable(ModItems.green_geode));
            add(PURPLE_SAPPER_TABLE, sapperTable(ModItems.purple_geode));
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

            addTable(ModEntities.BLUE_HOWLITE_WOLF, blankTable());

            addTable(ModEntities.MALACHITE_GUARD, malachiteGuardTable());
        }

        @Override
        protected Iterable<EntityType<?>> getKnownEntities() {
            return ForgeRegistries.ENTITIES.getValues().stream()
                    .filter(entity -> GaiaDimensionMod.MODID.equals(entity.getRegistryName().getNamespace()))
                    .collect(Collectors.toList());
        }
    }

    public static class Chests extends ChestLoot {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
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
                            .add(LootItem.lootTableItem(ModItems.leucite.get())
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
                            .add(LootItem.lootTableItem(ModItems.ixiolite.get())
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
                            .add(LootItem.lootTableItem(ModBlocks.malachite_floor_tiles.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModBlocks.malachite_pillar.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 5.0F))))
                            .add(LootItem.lootTableItem(ModItems.pyrite.get())
                                    .setWeight(15)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(LootItem.lootTableItem(ModItems.ixiolite_sword.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(ModItems.euclase_sword.get())
                                    .setWeight(2))
                            .add(LootItem.lootTableItem(ModItems.tiligr.get())
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                            .add(LootItem.lootTableItem(ModItems.proustite_helmet.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(ModItems.leucite_helmet.get())
                                    .setWeight(2)))
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 3.0F))
                            .add(LootItem.lootTableItem(ModItems.ixiolite.get())
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.proustite.get())
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.euclase.get())
                                    .setWeight(4)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(ModItems.leucite.get())
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
