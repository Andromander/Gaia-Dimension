package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
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
import net.minecraft.util.IItemProvider;

public abstract class GaiaBlockLootTableProvider extends BlockLootTables {
    private static final ILootCondition.IBuilder has_silk_touch = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder has_shears = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder shears_or_silk = has_shears.alternative(has_silk_touch);
    private static final ILootCondition.IBuilder silk_or_shears = shears_or_silk.inverted();

    public void dropSlab(Block block) {
        super.registerLootTable(block, BlockLootTables::droppingSlab);
    }

    public void dropWithFortune(Block block, Item drop) {
        super.registerLootTable(block, (result) -> droppingItemWithFortune(result, drop));
    }

    public void dropWithSilk(Block block, IItemProvider drop) {
        registerLootTable(block, (result) -> droppingWithSilkTouch(result, drop));
    }

    public void dropChance(Block block, Block drop, float... chances) {
        registerLootTable(block, (result) -> withChance(block, drop, chances));
    }

    public void dropChanceAlternative(Block block, Block drop, Item item, float... chances) {
        registerLootTable(block, (result) -> withChanceAdditional(block, drop, item, chances));
    }

    public void dropAlternative(Block block, Item drop) {
        registerLootTable(block, (result) -> droppingWithSilkTouch(result, withSurvivesExplosion(result, ItemLootEntry.builder(drop).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F)).alternatively(ItemLootEntry.builder(result)))));
    }

    protected static LootTable.Builder smallCrate(Block block) {
        return LootTable.builder()
                .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block)
                                .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                                .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                        .replaceOperation("Lock", "BlockEntityTag.Lock")
                                        .replaceOperation("LootTable", "BlockEntityTag.LootTable")
                                        .replaceOperation("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .acceptFunction(SetContents.builderIn()
                                        .addLootEntry(DynamicLootEntry.func_216162_a(SmallCrateBlock.NAME))))));
    }

    protected static LootTable.Builder largeCrate(Block block) {
        return LootTable.builder()
                .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block)
                                .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                                .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                        .replaceOperation("Lock", "BlockEntityTag.Lock")
                                        .replaceOperation("LootTable", "BlockEntityTag.LootTable")
                                        .replaceOperation("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .acceptFunction(SetContents.builderIn()
                                        .addLootEntry(DynamicLootEntry.func_216162_a(LargeCrateBlock.NAME))))));
    }

    protected static LootTable.Builder withName(Block block) {
        return LootTable.builder()
                .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block)
                                .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)))));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(drop))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(sapling))
                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)))
                .addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
                        .acceptCondition(silk_or_shears)
                        .addEntry(withExplosionDecay(block, ItemLootEntry.builder(item)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                                .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder withShards(Block block) {
        return droppingWithShears(block, withExplosionDecay(block, ItemLootEntry.builder(ModItems.crystal_shard)
                .acceptCondition(RandomChance.builder(0.125F))
                .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2))));
    }
}
