package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.data.loot.EntityLootTables;
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

    public LootTable.Builder blankTable() {
        return LootTable.lootTable();
    }

    public LootTable.Builder sapperTable(Supplier<Item> geode) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(geode.get())
                                .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(TableLootEntry.lootTableReference(ModEntities.GROWTH_SAPPER.getDefaultLootTable())));
    }

    public LootTable.Builder singleDropTable(Supplier<Item> drop, float minCount, float maxCount) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(drop.get())
                                .apply(SetCount.setCount(RandomValueRange.between(minCount, maxCount)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableSingleDropTable(Supplier<Item> raw, float minCount, float maxCount) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(raw.get())
                                .apply(SetCount.setCount(RandomValueRange.between(minCount, maxCount)))
                                .apply(Smelt.smelted()
                                        .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableDoubleDropTable(Supplier<Item> cookable, Supplier<Item> drop, float minCount1, float maxCount1, float minCount2, float maxCount2) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(cookable.get())
                                .apply(SetCount.setCount(RandomValueRange.between(minCount1, maxCount1)))
                                .apply(Smelt.smelted()
                                        .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(drop.get())
                                .apply(SetCount.setCount(RandomValueRange.between(minCount2, maxCount2)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder warriorTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.scaynyx_ingot.get()))
                        .when(KilledByPlayer.killedByPlayer())
                        .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.025F, 0.01F)))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.shiny_bone.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder extractorTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.pink_geode.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.blue_geode.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.green_geode.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.purple_geode.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))));
    }

    public LootTable.Builder malachiteGuardTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.malachite_guard_baton.get())))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.malachite_guard_headgear.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.malachite_guard_brace.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.malachite_guard_gear.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(ModItems.malachite_guard_boots.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))));
    }
}
