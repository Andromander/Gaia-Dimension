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
    private static final ILootCondition.IBuilder has_silk_touch = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder has_shears = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder shears_or_silk = has_shears.alternative(has_silk_touch);
    private static final ILootCondition.IBuilder silk_or_shears = shears_or_silk.inverted();

    public <T extends Block> void dropSelf(Supplier<T> block) {
        registerDropSelfLootTable(block.get());
    }

    public void dropTable(Supplier<Block> block, Function<Block, LootTable.Builder> table) {
        registerLootTable(block.get(), table.apply(block.get()));
    }

    public void dropSlab(Supplier<SlabBlock> block) {
        super.registerLootTable(block.get(), BlockLootTables::droppingSlab);
    }

    public void dropWithFortune(Supplier<Block> block, Supplier<Item> drop) {
        super.registerLootTable(block.get(), (result) -> droppingItemWithFortune(result, drop.get()));
    }

    public void dropOnlySilk(Supplier<Block> block) {
        registerSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<Block> block, Supplier<Block> drop) {
        registerLootTable(block.get(), (result) -> droppingWithSilkTouch(result, drop.get()));
    }

    public void dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        registerLootTable(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
    }

    public void dropChanceAlternative(Supplier<? extends Block> block, Supplier<? extends Block> drop, Supplier<Item> item, float... chances) {
        registerLootTable(block.get(), (result) -> withChanceAdditional(block.get(), drop.get(), item.get(), chances));
    }

    public void dropAlternative(Supplier<Block> block, Supplier<Item> drop) {
        registerLootTable(block.get(), (result) -> droppingWithSilkTouch(result, withSurvivesExplosion(result, ItemLootEntry.builder(drop.get()).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F)).alternatively(ItemLootEntry.builder(result)))));
    }

    public void dropPot(RegistryObject<FlowerPotBlock> flowerpot) {
        this.registerLootTable(flowerpot.get(), (pot) -> droppingAndFlowerPot(((FlowerPotBlock)pot).getFlower()));
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

    protected static LootTable.Builder smallCrate(RegistryObject<Block> block) {
        return LootTable.builder()
                .addLootPool(withSurvivesExplosion(block.get(), LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block.get())
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
        return droppingWithShears(block, withExplosionDecay(block, ItemLootEntry.builder(ModItems.crystal_shard.get())
                .acceptCondition(RandomChance.builder(0.125F))
                .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2))));
    }
}
