package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.*;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBlockLootTableProvider extends BlockLootTables {
    private static final ILootCondition.IBuilder has_silk_touch = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder has_shears = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder shears_or_silk = has_shears.or(has_silk_touch);
    private static final ILootCondition.IBuilder silk_or_shears = shears_or_silk.invert();

    public <T extends Block> void dropSelf(Supplier<T> block) {
        dropSelf(block.get());
    }

    public void dropTable(Supplier<Block> block, Function<Block, LootTable.Builder> table) {
        add(block.get(), table.apply(block.get()));
    }

    public void dropSlab(Supplier<SlabBlock> block) {
        super.add(block.get(), BlockLootTables::createSlabItemTable);
    }

    public void dropWithFortune(Supplier<Block> block, Supplier<Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void dropOnlySilk(Supplier<Block> block) {
        dropWhenSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<Block> block, Supplier<Block> drop) {
        add(block.get(), (result) -> createSingleItemTableWithSilkTouch(result, drop.get()));
    }

    public void dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        add(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
    }

    public void dropChanceAlternative(Supplier<? extends Block> block, Supplier<? extends Block> drop, Supplier<Item> item, float... chances) {
        add(block.get(), (result) -> withChanceAdditional(block.get(), drop.get(), item.get(), chances));
    }

    public void dropAlternative(Supplier<Block> block, Supplier<Item> drop) {
        add(block.get(), (result) ->
                createSilkTouchDispatchTable(result, applyExplosionCondition(result, ItemLootEntry.lootTableItem(drop.get())
                        .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                        .otherwise(ItemLootEntry.lootTableItem(result)))));
    }

    public void dropPot(RegistryObject<FlowerPotBlock> flowerpot) {
        this.add(flowerpot.get(), (pot) -> createPotFlowerItemTable(((FlowerPotBlock)pot).getContent()));
    }

    protected static LootTable.Builder smallCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block)
                                .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
                                .apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContents.setContents()
                                        .withEntry(DynamicLootEntry.dynamicEntry(SmallCrateBlock.NAME))))));
    }

    protected static LootTable.Builder largeCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block)
                                .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY))
                                .apply(CopyNbt.copyData(CopyNbt.Source.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContents.setContents()
                                        .withEntry(DynamicLootEntry.dynamicEntry(LargeCrateBlock.NAME))))));
    }

    protected static LootTable.Builder withName(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block)
                                .apply(CopyName.copyName(CopyName.Source.BLOCK_ENTITY)))));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(drop))
                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(sapling))
                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
                        .when(silk_or_shears)
                        .add(applyExplosionDecay(block, ItemLootEntry.lootTableItem(item)
                                .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                                .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder withShards(Block block) {
        return createShearsDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(ModItems.crystal_shard.get())
                .when(RandomChance.randomChance(0.125F))
                .apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }
}
