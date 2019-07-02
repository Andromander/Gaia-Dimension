package androsa.gaiadimension.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {

    public static final Food PINK_SLICE = (new Food.Builder()).hunger(3).saturation(0.5F).build();
    public static final Food BLUE_SLICE = (new Food.Builder()).hunger(4).saturation(0.4F).build();
    public static final Food GREEN_SLICE = (new Food.Builder()).hunger(5).saturation(0.3F).build();
    public static final Food PURPLE_SLICE = (new Food.Builder()).hunger(6).saturation(0.2F).build();
    public static final Food PINK_JUICE = (new Food.Builder()).hunger(8).saturation(0.7F).setAlwaysEdible().effect(new EffectInstance(Effects.REGENERATION, 100, 0), 1.0F).build();
    public static final Food BLUE_TEA = (new Food.Builder()).hunger(8).saturation(0.7F).setAlwaysEdible().effect(new EffectInstance(Effects.FIRE_RESISTANCE, 100, 0), 1.0F).build();
    public static final Food GREEN_ALE = (new Food.Builder()).hunger(8).saturation(0.7F).setAlwaysEdible().effect(new EffectInstance(Effects.SPEED, 100, 0), 1.0F).build();
    public static final Food PURPLE_SODA = (new Food.Builder()).hunger(8).saturation(0.7F).setAlwaysEdible().effect(new EffectInstance(Effects.STRENGTH, 100, 0), 1.0F).build();
    public static final Food PEARLY_ELIXIR = (new Food.Builder()).hunger(12).saturation(0.9F).setAlwaysEdible().effect(new EffectInstance(Effects.RESISTANCE, 100, 0), 1.0F).build();
    public static final Food LURMORUS_MEAT = (new Food.Builder()).hunger(4).saturation(0.4F).build();
    public static final Food LURMORUS_STEAK = (new Food.Builder()).hunger(9).saturation(0.9F).build();
    public static final Food SMALL_TENTACLE = (new Food.Builder()).hunger(3).saturation(0.2F).build();
    public static final Food SMALL_CALAMARI = (new Food.Builder()).hunger(6).saturation(0.6F).build();
    public static final Food LARGE_TENTACLE = (new Food.Builder()).hunger(4).saturation(0.3F).build();
    public static final Food LARGE_CALAMARI = (new Food.Builder()).hunger(8).saturation(0.7F).build();
    public static final Food MARKUZAR_MINT = (new Food.Builder()).hunger(2).saturation(0.4F).build();
    public static final Food LUGGEROTH_CHOP = (new Food.Builder()).hunger(3).saturation(0.3F).build();
    public static final Food COOKED_LUGGEROTH_CHOP = (new Food.Builder()).hunger(8).saturation(0.8F).build();
    public static final Food TILIPI = (new Food.Builder().hunger(5).saturation(0.6F).build());
    public static final Food TILIBL = (new Food.Builder().hunger(5).saturation(0.6F).build());
    public static final Food TILIGR = (new Food.Builder().hunger(7).saturation(0.3F).build());
    public static final Food TILIPU = (new Food.Builder().hunger(7).saturation(0.3F).build());
    public static final Food TILIOL = (new Food.Builder().hunger(5).saturation(0.6F).build());
    public static final Food TILIMY = (new Food.Builder().hunger(7).saturation(0.3F).build());
    public static final Food PLAGUED_TILIEY = (new Food.Builder().hunger(7).saturation(0.3F).effect(new EffectInstance(ModEffects.goldstone_plague, 100, 0), 1.0F).build());
    public static final Food TILIOU = (new Food.Builder().hunger(5).saturation(0.6F).build());
}
