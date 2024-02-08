package androsa.gaiadimension.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RestructurerRecipeSerializer<T extends RestructurerRecipe> implements RecipeSerializer<T> {
    private final RestructurerRecipe.EntityFactory<T> factory;
    private final Codec<T> codec;

    public RestructurerRecipeSerializer(RestructurerRecipe.EntityFactory<T> factoryIn, int timeIn) {
        this.codec = RecordCodecBuilder.create(
                instance -> instance.group(
                        ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(obj -> obj.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(obj -> obj.ingredient),
                        ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(obj -> obj.result),
                        ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("byproduct").forGetter(obj -> obj.byproduct),
                        Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(obj -> obj.experience),
                        Codec.INT.fieldOf("cookingtime").orElse(timeIn).forGetter(obj -> obj.cookTime)
                ).apply(instance, factoryIn::create));
        this.factory = factoryIn;
    }

    @Override
    public Codec<T> codec() {
        return this.codec;
    }

    @Override
    public T fromNetwork(FriendlyByteBuf buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        ItemStack itemstack1 = buffer.readItem();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.factory.create(s, ingredient, itemstack, itemstack1, f, i);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeItem(recipe.byproduct);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public RestructurerRecipe create(String group, Ingredient input, ItemStack output, ItemStack byproduct, float experience, int time) {
        return this.factory.create(group, input, output, byproduct, experience, time);
    }
}
