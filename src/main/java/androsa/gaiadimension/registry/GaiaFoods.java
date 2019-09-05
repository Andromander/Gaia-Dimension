package androsa.gaiadimension.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class GaiaFoods {

    public static final Food PINK_SLICE = buildFood(3, 0.5F);
    public static final Food BLUE_SLICE = buildFood(4, 0.4F);
    public static final Food GREEN_SLICE = buildFood(5, 0.3F);
    public static final Food PURPLE_SLICE = buildFood(6, 0.2F);
    public static final Food PINK_JUICE = buildFoodWithEffect(8, 0.7F, true, new EffectInstance(Effects.REGENERATION, 100, 0), 1.0F);
    public static final Food BLUE_TEA = buildFoodWithEffect(8, 0.7F, true, new EffectInstance(Effects.FIRE_RESISTANCE, 100, 0), 1.0F);
    public static final Food GREEN_ALE = buildFoodWithEffect(8, 0.7F, true, new EffectInstance(Effects.SPEED, 100, 0), 1.0F);
    public static final Food PURPLE_SODA = buildFoodWithEffect(8, 0.7F, true, new EffectInstance(Effects.STRENGTH, 100, 0), 1.0F);
    public static final Food PEARLY_ELIXIR = buildFoodWithEffect(12, 0.9F, true, new EffectInstance(Effects.RESISTANCE, 100, 0), 1.0F);
    public static final Food LURMORUS_MEAT = buildFood(4, 0.4F);
    public static final Food LURMORUS_STEAK = buildFood(9, 0.9F);
    public static final Food SMALL_TENTACLE = buildFood(3, 0.2F);
    public static final Food SMALL_CALAMARI = buildFood(6, 0.6F);
    public static final Food LARGE_TENTACLE = buildFood(4, 0.3F);
    public static final Food LARGE_CALAMARI = buildFood(8, 0.7F);
    public static final Food MARKUZAR_MINT = buildFood(2, 0.4F);
    public static final Food LUGGEROTH_CHOP = buildFood(3, 0.3F);
    public static final Food COOKED_LUGGEROTH_CHOP = buildFood(8, 0.8F);
    public static final Food TILIPI = buildFood(5, 0.6F);
    public static final Food TILIBL = buildFood(5, 0.6F);
    public static final Food TILIGR = buildFood(7, 0.3F);
    public static final Food TILIPU = buildFood(7, 0.3F);
    public static final Food TILIOL = buildFood(5, 0.6F);
    public static final Food TILIMY = buildFood(7, 0.3F);
    public static final Food PLAGUED_TILIEY = buildFoodWithEffect(7, 0.3F, false, new EffectInstance(ModEffects.goldstone_plague, 100, 0), 1.0F);
    public static final Food TILIOU = buildFood(5, 0.6F);

    private static Food buildFood(int hunger, float saturation) {
        return buildFoodWithEffect(hunger, saturation, false, null, 0);
    }

    private static Food buildFoodWithEffect(int hunger, float saturation, boolean alwaysEdible, EffectInstance effect, float duration) {
        Food.Builder food = new Food.Builder().hunger(hunger).saturation(saturation);

        if (alwaysEdible)
            food.setAlwaysEdible();
        if (effect != null)
            food.effect(effect, duration);

        return food.build();
    }
}
