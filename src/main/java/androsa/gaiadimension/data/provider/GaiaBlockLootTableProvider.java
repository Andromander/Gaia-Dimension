package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBlockLootTableProvider extends BlockLoot {
    private static final LootItemCondition.Builder has_silk_touch = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder has_shears = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final LootItemCondition.Builder shears_or_silk = has_shears.or(has_silk_touch);
    private static final LootItemCondition.Builder silk_or_shears = shears_or_silk.invert();

    public <T extends Block> void dropSelf(Supplier<T> block) {
        dropSelf(block.get());
    }

    public void dropTable(Supplier<Block> block, Function<Block, LootTable.Builder> table) {
        add(block.get(), table.apply(block.get()));
    }

    public void dropSlab(Supplier<SlabBlock> block) {
        super.add(block.get(), BlockLoot::createSlabItemTable);
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
                createSilkTouchDispatchTable(result, applyExplosionCondition(result, LootItem.lootTableItem(drop.get())
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                        .otherwise(LootItem.lootTableItem(result)))));
    }

    public void dropPot(RegistryObject<FlowerPotBlock> flowerpot) {
        this.add(flowerpot.get(), (pot) -> createPotFlowerItemTable(((FlowerPotBlock)pot).getContent()));
    }

    protected static LootTable.Builder smallCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContainerContents.setContents()
                                        .withEntry(DynamicLoot.dynamicEntry(SmallCrateBlock.NAME))))));
    }

    protected static LootTable.Builder largeCrate(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("Lock", "BlockEntityTag.Lock")
                                        .copy("LootTable", "BlockEntityTag.LootTable")
                                        .copy("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                .apply(SetContainerContents.setContents()
                                        .withEntry(DynamicLoot.dynamicEntry(LargeCrateBlock.NAME))))));
    }

    protected static LootTable.Builder withName(Block block) {
        return LootTable.lootTable()
                .withPool(applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(drop))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)));
    }

    protected static LootTable.Builder withChanceAdditional(Block block, Block sapling, Item item, float... chances) {
        return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(sapling))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(silk_or_shears)
                        .add(applyExplosionDecay(block, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
    }

    protected static LootTable.Builder withShards(Block block) {
        return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.crystal_shard.get())
                .when(LootItemRandomChanceCondition.randomChance(0.125F))
                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
    }
}
