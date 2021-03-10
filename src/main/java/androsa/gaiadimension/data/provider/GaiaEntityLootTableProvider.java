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
        return LootTable.builder();
    }

    public LootTable.Builder sapperTable(Supplier<Item> geode) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(geode.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(TableLootEntry.builder(ModEntities.GROWTH_SAPPER.getLootTable())));
    }

    public LootTable.Builder singleDropTable(Supplier<Item> drop, float minCount, float maxCount) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount, maxCount)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableSingleDropTable(Supplier<Item> raw, float minCount, float maxCount) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(raw.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount, maxCount)))
                                .acceptFunction(Smelt.func_215953_b()
                                        .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder cookableDoubleDropTable(Supplier<Item> cookable, Supplier<Item> drop, float minCount1, float maxCount1, float minCount2, float maxCount2) {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(cookable.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount1, maxCount1)))
                                .acceptFunction(Smelt.func_215953_b()
                                        .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(drop.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(minCount2, maxCount2)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder warriorTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.scaynyx_ingot.get()))
                        .acceptCondition(KilledByPlayer.builder())
                        .acceptCondition(RandomChanceWithLooting.builder(0.025F, 0.01F)))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.shiny_bone.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder extractorTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.pink_geode.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.blue_geode.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.green_geode.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.purple_geode.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }

    public LootTable.Builder malachiteGuardTable() {
        return LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_baton.get())))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_headgear.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_brace.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_gear.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))))
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(ModItems.malachite_guard_boots.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))));
    }
}
