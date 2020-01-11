package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.LargeCrateBlock;
import androsa.gaiadimension.block.SmallCrateBlock;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.TableBonus;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBlockLootTableProvider extends BlockLootTables {

    private static final ILootCondition.IBuilder silk_touch_condition = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder shears_condition = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder alt_condition = shears_condition.alternative(silk_touch_condition);
    private static final ILootCondition.IBuilder inv_condition = alt_condition.inverted();

    public void registerTable(Supplier<? extends Block> block, Function<Block, LootTable.Builder> factory) {
        super.registerLootTable(block.get(), factory);
    }

    public void dropSelf(Supplier<? extends Block> block) {
        super.registerDropSelfLootTable(block.get());
    }

    public void dropSlab(Supplier<? extends Block> block) {
        super.registerLootTable(block.get(), BlockLootTables::droppingSlab);
    }

    public void dropFlowerPot(Supplier<? extends Block> block) {
        super.registerFlowerPot(block.get());
    }

    public void dropWithFortune(Supplier<? extends Block> block, Supplier<? extends Item> drop) {
        super.registerLootTable(block.get(), (result) -> droppingItemWithFortune(result, drop.get()));
    }

    public void dropAsSilk(Supplier<? extends Block> block) {
        super.registerSilkTouch(block.get());
    }

    public void dropWithSilk(Supplier<? extends Block> block, Supplier<? extends IItemProvider> drop) {
        registerLootTable(block.get(), (result) -> droppingWithSilkTouch(result, drop.get()));
    }

    public void dropChance(Supplier<? extends Block> block, Supplier<? extends Block> drop, float... chances) {
        registerLootTable(block.get(), (result) -> withChance(block.get(), drop.get(), chances));
    }

    public void dropAlternative(Supplier<? extends Block> block, Supplier<? extends IItemProvider> drop) {
        registerLootTable(block.get(), (result) -> droppingWithSilkTouch(result, withSurvivesExplosion(result, ItemLootEntry.builder(drop.get()).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F)).alternatively(ItemLootEntry.builder(result)))));
    }

    protected static LootTable.Builder smallCrate(Block block) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY).func_216056_a("Lock", "BlockEntityTag.Lock").func_216056_a("LootTable", "BlockEntityTag.LootTable").func_216056_a("LootTableSeed", "BlockEntityTag.LootTableSeed")).acceptFunction(SetContents.func_215920_b().func_216075_a(DynamicLootEntry.func_216162_a(SmallCrateBlock.NAME))))));
    }

    protected static LootTable.Builder largeCrate(Block block) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)).acceptFunction(CopyNbt.func_215881_a(CopyNbt.Source.BLOCK_ENTITY).func_216056_a("Lock", "BlockEntityTag.Lock").func_216056_a("LootTable", "BlockEntityTag.LootTable").func_216056_a("LootTableSeed", "BlockEntityTag.LootTableSeed")).acceptFunction(SetContents.func_215920_b().func_216075_a(DynamicLootEntry.func_216162_a(LargeCrateBlock.NAME))))));
    }

    protected static LootTable.Builder withName(Block block) {
        return LootTable.builder().addLootPool(withSurvivesExplosion(block, LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block).acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)))));
    }

    protected static LootTable.Builder withChance(Block block, Block drop, float... chances) {
        return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(drop)).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)));
    }
}
