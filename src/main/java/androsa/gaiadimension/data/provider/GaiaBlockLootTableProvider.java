package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import androsa.gaiadimension.registry.registration.ModBlockEntities;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBlockLootTableProvider extends BlockLootSubProvider {
    private static final LootItemCondition.Builder has_silk_touch = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder has_shears = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder shears_or_silk = has_shears.or(has_silk_touch);
    private static final LootItemCondition.Builder silk_or_shears = shears_or_silk.invert();

    protected GaiaBlockLootTableProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    public <T extends Block> void noDrops(Supplier<T> block) {
        add(block.get(), noDrop());
    }

    public <T extends Block> void dropSelf(Supplier<T> block) {
        dropSelf(block.get());
    }

    public void dropTable(Supplier<Block> block, Function<Block, LootTable.Builder> table) {
        add(block.get(), table.apply(block.get()));
    }

    public void dropTable(Supplier<Block> block, LootTable.Builder table) {
        add(block.get(), table);
    }

    public void dropSlab(Supplier<SlabBlock> block) {
        add(block.get(), this.createSlabItemTable(block.get()));
    }

    public void dropWithFortune(Supplier<Block> block, Supplier<Item> drop) {
        super.add(block.get(), (result) -> createOreDrop(result, drop.get()));
    }

    public void dropWithMultiple(Supplier<Block> block, Supplier<Item> drop) {
        super.add(block.get(), (result) -> multipleOreDrops(result, drop.get()));
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
                createSilkTouchDispatchTable(result, applyExplosionCondition(result, LootItem.lootTableItem(drop.get())
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                        .otherwise(LootItem.lootTableItem(result)))));
    }

    public void dropPot(Supplier<FlowerPotBlock> flowerpot) {
        this.add(flowerpot.get(), (pot) -> createPotFlowerItemTable(((FlowerPotBlock)pot).getPotted()));
    }

    protected LootTable.Builder smallCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContainerContents.setContents(ModBlockEntities.SMALL_CRATE.get())
                                        .withEntry(DynamicLoot.dynamicEntry(SmallCrateBlock.NAME))))));
    }

    protected LootTable.Builder largeCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContainerContents.setContents(ModBlockEntities.LARGE_CRATE.get())
                                        .withEntry(DynamicLoot.dynamicEntry(LargeCrateBlock.NAME))))));
    }

    public LootTable.Builder withName(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))));
    }

    protected LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(drop))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)));
    }

    protected LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(sapling))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(silk_or_shears)
                        .add(applyExplosionDecay(block, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected LootTable.Builder withShards(Block block) {
        return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.crystal_shard.get())
                .when(LootItemRandomChanceCondition.randomChance(0.125F))
                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }

    protected LootTable.Builder multipleOreDrops(Block ore, Item drop) {
        return createSilkTouchDispatchTable(ore, applyExplosionDecay(ore, LootItem.lootTableItem(drop)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected static LootTable.Builder doubleShearsOnly(Block block, Block half) {
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(half)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                .when(has_shears);
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().add(builder)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))), new BlockPos(0, 1, 0))))
                .withPool(LootPool.lootPool()
                        .add(builder)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                        .when(LocationCheck.checkLocation(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))), new BlockPos(0, -1, 0))));
    }
}
