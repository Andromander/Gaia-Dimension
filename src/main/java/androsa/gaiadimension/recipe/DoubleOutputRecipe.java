package androsa.gaiadimension.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class DoubleOutputRecipe implements Recipe<SingleRecipeInput> {
    private final String group;
    private final Ingredient input;
    private final ItemStack result;
    private final ItemStack byproduct;
    protected final float experience;
    protected final int cookTime;
    private PlacementInfo placement;

    public DoubleOutputRecipe(String group, Ingredient input, ItemStack result, ItemStack byproduct, float exp, int time) {
        this.group = group;
        this.input = input;
        this.result = result;
        this.byproduct = byproduct;
        this.experience = exp;
        this.cookTime = time;
    }

    @Override
    public String group() {
        return this.group;
    }

    public Ingredient input() {
        return this.input;
    }

    public ItemStack result() {
        return this.result;
    }

    public ItemStack byproduct() {
        return this.byproduct;
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
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
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
        T create(String group, Ingredient ingredientIn, ItemStack outputIn, ItemStack byproductIn, float experienceIn, int timeIn);
    }

    public static class Serializer<T extends DoubleOutputRecipe> implements RecipeSerializer<T> {
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

        public Serializer(Factory<T> factoryIn, int timeIn) {
            this.codec = RecordCodecBuilder.mapCodec(
                    instance -> instance.group(
                            Codec.STRING.optionalFieldOf("group", "").forGetter(DoubleOutputRecipe::group),
                            Ingredient.CODEC.fieldOf("ingredient").forGetter(DoubleOutputRecipe::input),
                            ItemStack.CODEC.fieldOf("result").forGetter(DoubleOutputRecipe::result),
                            ItemStack.CODEC.fieldOf("byproduct").forGetter(DoubleOutputRecipe::byproduct),
                            Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(DoubleOutputRecipe::experience),
                            Codec.INT.fieldOf("cookingtime").orElse(timeIn).forGetter(DoubleOutputRecipe::cookTime)
                    ).apply(instance, factoryIn::create));
            this.streamCodec = StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    Recipe::group,
                    Ingredient.CONTENTS_STREAM_CODEC,
                    DoubleOutputRecipe::input,
                    ItemStack.STREAM_CODEC,
                    DoubleOutputRecipe::result,
                    ItemStack.STREAM_CODEC,
                    DoubleOutputRecipe::byproduct,
                    ByteBufCodecs.FLOAT,
                    DoubleOutputRecipe::experience,
                    ByteBufCodecs.INT,
                    DoubleOutputRecipe::cookTime,
                    factoryIn::create);
        }

        @Override
        public MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return this.streamCodec;
        }
    }
}
