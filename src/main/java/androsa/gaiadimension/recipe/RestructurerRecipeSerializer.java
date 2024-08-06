package androsa.gaiadimension.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RestructurerRecipeSerializer<T extends RestructurerRecipe> implements RecipeSerializer<T> {
    private final RestructurerRecipe.EntityFactory<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

    public RestructurerRecipeSerializer(RestructurerRecipe.EntityFactory<T> factoryIn, int timeIn) {
        this.codec = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(obj -> obj.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(obj -> obj.ingredient),
                        ItemStack.CODEC.fieldOf("result").forGetter(obj -> obj.result),
                        ItemStack.CODEC.fieldOf("byproduct").forGetter(obj -> obj.byproduct),
                        Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(obj -> obj.experience),
                        Codec.INT.fieldOf("cookingtime").orElse(timeIn).forGetter(obj -> obj.cookTime)
                ).apply(instance, factoryIn::create));
        this.factory = factoryIn;
        this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
    }

    @Override
    public MapCodec<T> codec() {
        return this.codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        return this.streamCodec;
    }

    public T fromNetwork(RegistryFriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
        ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
        ItemStack itemstack1 = ItemStack.STREAM_CODEC.decode(buffer);
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.factory.create(s, ingredient, itemstack, itemstack1, f, i);
    }

    public void toNetwork(RegistryFriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.group);
        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient);
        ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        ItemStack.STREAM_CODEC.encode(buffer, recipe.byproduct);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public RestructurerRecipe create(String group, Ingredient input, ItemStack output, ItemStack byproduct, float experience, int time) {
        return this.factory.create(group, input, output, byproduct, experience, time);
    }
}
