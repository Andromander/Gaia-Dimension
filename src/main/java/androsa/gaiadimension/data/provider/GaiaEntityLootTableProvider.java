package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;

import java.util.function.Supplier;

public class GaiaEntityLootTableProvider extends EntityLootTables {

    public void registerLootTable(Supplier<? extends EntityType<?>> entity, LootTable.Builder table) {
        super.registerLootTable(entity.get(), table);
    }

    public LootTable.Builder blankTable() {
        return LootTable.builder();
    }

    public LootTable.Builder sapperTable(Item geode) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(geode)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(TableLootEntry.builder(ModEntities.GROWTH_SAPPER.get().getLootTable())));
    }

    public LootTable.Builder singleDropTable(Item drop, float minCount, float maxCount) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount, maxCount)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableSingleDropTable(Item raw, float minCount, float maxCount) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(raw)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount, maxCount)))
                                .acceptFunction(Smelt.func_215953_b()
                                        .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableDoubleDropTable(Item cookable, Item drop, float minCount1, float maxCount1, float minCount2, float maxCount2) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(cookable)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount1, maxCount1)))
                                .acceptFunction(Smelt.func_215953_b()
                                        .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount2, maxCount2)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder warriorTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot))
                        .acceptCondition(KilledByPlayer.builder())
                        .acceptCondition(RandomChanceWithLooting.builder(0.025F, 0.01F)))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.shiny_bone)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder extractorTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.pink_geode)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.blue_geode)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.green_geode)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.purple_geode)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder malachiteGuardTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_baton)))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_headgear)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_brace)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_gear)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_boots)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }
}
