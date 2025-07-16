package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.registry.registration.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class GaiaFoods {

    public static final FoodProperties PINK_SLICE = buildFood(3, 0.5F, false);
    public static final FoodProperties BLUE_SLICE = buildFood(4, 0.4F, false);
    public static final FoodProperties GREEN_SLICE = buildFood(5, 0.3F, false);
    public static final FoodProperties PURPLE_SLICE = buildFood(6, 0.2F, false);
    public static final FoodProperties PINK_JUICE = buildFood(8, 0.7F, true);
    public static final FoodProperties BLUE_TEA = buildFood(8, 0.7F, true);
    public static final FoodProperties GREEN_ALE = buildFood(8, 0.7F, true);
    public static final FoodProperties PURPLE_SODA = buildFood(8, 0.7F, true);
    public static final FoodProperties PEARLY_ELIXIR = buildFood(12, 0.9F, true);
    public static final FoodProperties LURMORUS_MEAT = buildFood(4, 0.4F, false);
    public static final FoodProperties LURMORUS_STEAK = buildFood(9, 0.9F, false);
    public static final FoodProperties SMALL_TENTACLE = buildFood(3, 0.2F, false);
    public static final FoodProperties SMALL_CALAMARI = buildFood(6, 0.6F, false);
    public static final FoodProperties LARGE_TENTACLE = buildFood(4, 0.3F, false);
    public static final FoodProperties LARGE_CALAMARI = buildFood(8, 0.7F, false);
    public static final FoodProperties MARKUZAR_MINT = buildFood(2, 0.4F, false);
    public static final FoodProperties LUGGEROTH_CHOP = buildFood(3, 0.3F, false);
    public static final FoodProperties COOKED_LUGGEROTH_CHOP = buildFood(8, 0.8F, false);
    public static final FoodProperties TILIPI = buildFood(5, 0.6F, false);
    public static final FoodProperties TILIBL = buildFood(5, 0.6F, false);
    public static final FoodProperties TILIGR = buildFood(7, 0.3F, false);
    public static final FoodProperties TILIPU = buildFood(7, 0.3F, false);
    public static final FoodProperties TILIOL = buildFood(5, 0.6F, false);
    public static final FoodProperties TILIMY = buildFood(7, 0.3F, false);
    public static final FoodProperties PLAGUED_TILIEY = buildFood(7, 0.3F, false);
    public static final FoodProperties TILIOU = buildFood(5, 0.6F, false);

    public static final Consumable PINK_JUICE_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.REGENERATION, 100, 0)))
            .build();
    public static final Consumable BLUE_TEA_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0)))
            .build();
    public static final Consumable GREEN_ALE_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.SPEED, 100, 0)))
            .build();
    public static final Consumable PURPLE_SODA_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.STRENGTH, 100, 0)))
            .build();
    public static final Consumable PEARLY_ELIXIR_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.RESISTANCE, 100, 0)))
            .build();
    public static final Consumable PLAGUED_TILIEY_EFFECT = Consumables.defaultDrink()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(ModEffects.goldstone_plague, 100, 0)))
            .build();

    private static FoodProperties buildFood(int hunger, float saturation, boolean alwaysEdible) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(hunger).saturationModifier(saturation);

        if (alwaysEdible)
            food.alwaysEdible();

        return food.build();
    }
}
