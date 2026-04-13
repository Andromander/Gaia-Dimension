package androsa.gaiadimension.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class DoubleOutputRecipe implements Recipe<SingleRecipeInput> {
    private final String group;
    private final Ingredient input;
    private final ItemStackTemplate result;
    private final ItemStackTemplate byproduct;
    protected final float experience;
    protected final int cookTime;
    private PlacementInfo placement;

    public DoubleOutputRecipe(String group, Ingredient input, ItemStackTemplate result, ItemStackTemplate byproduct, float exp, int time) {
        this.group = group;
        this.input = input;
        this.result = result;
        this.byproduct = byproduct;
        this.experience = exp;
        this.cookTime = time;
    }

    public static <T extends DoubleOutputRecipe> MapCodec<T> codec(Factory<T> factoryIn, int timeIn) {
        return RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(DoubleOutputRecipe::group),
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(DoubleOutputRecipe::input),
                        ItemStackTemplate.CODEC.fieldOf("result").forGetter(DoubleOutputRecipe::result),
                        ItemStackTemplate.CODEC.fieldOf("byproduct").forGetter(DoubleOutputRecipe::byproduct),
                        Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(DoubleOutputRecipe::experience),
                        Codec.INT.fieldOf("cookingtime").orElse(timeIn).forGetter(DoubleOutputRecipe::cookTime)
                ).apply(instance, factoryIn::create));
    }

    public static <T extends DoubleOutputRecipe> StreamCodec<RegistryFriendlyByteBuf, T> streamCodec(Factory<T> factoryIn) {
        return StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                Recipe::group,
                Ingredient.CONTENTS_STREAM_CODEC,
                DoubleOutputRecipe::input,
                ItemStackTemplate.STREAM_CODEC,
                DoubleOutputRecipe::result,
                ItemStackTemplate.STREAM_CODEC,
                DoubleOutputRecipe::byproduct,
                ByteBufCodecs.FLOAT,
                DoubleOutputRecipe::experience,
                ByteBufCodecs.INT,
                DoubleOutputRecipe::cookTime,
                factoryIn::create);
    }

    @Override
    public String group() {
        return this.group;
    }

    public Ingredient input() {
        return this.input;
    }

    public ItemStackTemplate result() {
        return this.result;
    }

    public ItemStackTemplate byproduct() {
        return this.byproduct;
    }

    //TODO
    @Override
    public boolean showNotification() {
        return false;
    }

    public float experience() {
        return this.experience;
    }

    public int cookTime() {
        return this.cookTime;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.input.test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return this.result.create();
    }

    public ItemStack assembleByproduct(SingleRecipeInput input) {
        return this.byproduct.create();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.placement == null) {
            this.placement = PlacementInfo.create(this.input);
        }
        return this.placement;
    }

    @FunctionalInterface
    public interface Factory<T extends DoubleOutputRecipe> {
        T create(String group, Ingredient ingredientIn, ItemStackTemplate outputIn, ItemStackTemplate byproductIn, float experienceIn, int timeIn);
    }
}
