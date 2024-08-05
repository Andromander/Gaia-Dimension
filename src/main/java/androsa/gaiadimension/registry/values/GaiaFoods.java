package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.registry.registration.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public class GaiaFoods {

    public static final FoodProperties PINK_SLICE = buildFood(3, 0.5F);
    public static final FoodProperties BLUE_SLICE = buildFood(4, 0.4F);
    public static final FoodProperties GREEN_SLICE = buildFood(5, 0.3F);
    public static final FoodProperties PURPLE_SLICE = buildFood(6, 0.2F);
    public static final FoodProperties PINK_JUICE = buildFoodWithEffect(8, 0.7F, true, () -> new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F);
    public static final FoodProperties BLUE_TEA = buildFoodWithEffect(8, 0.7F, true, () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0), 1.0F);
    public static final FoodProperties GREEN_ALE = buildFoodWithEffect(8, 0.7F, true, () -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 1.0F);
    public static final FoodProperties PURPLE_SODA = buildFoodWithEffect(8, 0.7F, true, () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0), 1.0F);
    public static final FoodProperties PEARLY_ELIXIR = buildFoodWithEffect(12, 0.9F, true, () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0), 1.0F);
    public static final FoodProperties LURMORUS_MEAT = buildFood(4, 0.4F);
    public static final FoodProperties LURMORUS_STEAK = buildFood(9, 0.9F);
    public static final FoodProperties SMALL_TENTACLE = buildFood(3, 0.2F);
    public static final FoodProperties SMALL_CALAMARI = buildFood(6, 0.6F);
    public static final FoodProperties LARGE_TENTACLE = buildFood(4, 0.3F);
    public static final FoodProperties LARGE_CALAMARI = buildFood(8, 0.7F);
    public static final FoodProperties MARKUZAR_MINT = buildFood(2, 0.4F);
    public static final FoodProperties LUGGEROTH_CHOP = buildFood(3, 0.3F);
    public static final FoodProperties COOKED_LUGGEROTH_CHOP = buildFood(8, 0.8F);
    public static final FoodProperties TILIPI = buildFood(5, 0.6F);
    public static final FoodProperties TILIBL = buildFood(5, 0.6F);
    public static final FoodProperties TILIGR = buildFood(7, 0.3F);
    public static final FoodProperties TILIPU = buildFood(7, 0.3F);
    public static final FoodProperties TILIOL = buildFood(5, 0.6F);
    public static final FoodProperties TILIMY = buildFood(7, 0.3F);
    public static final FoodProperties PLAGUED_TILIEY = buildFoodWithEffect(7, 0.3F, false, () -> new MobEffectInstance(ModEffects.goldstone_plague, 100, 0), 1.0F);
    public static final FoodProperties TILIOU = buildFood(5, 0.6F);

    private static FoodProperties buildFood(int hunger, float saturation) {
        return buildFoodWithEffect(hunger, saturation, false, null, 0);
    }

    private static FoodProperties buildFoodWithEffect(int hunger, float saturation, boolean alwaysEdible, Supplier<MobEffectInstance> effect, float duration) {
        FoodProperties.Builder food = new FoodProperties.Builder().nutrition(hunger).saturationModifier(saturation);

        if (alwaysEdible)
            food.alwaysEdible();
        if (effect != null)
            food.effect(effect, duration);

        return food.build();
    }
}
