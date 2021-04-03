package androsa.gaiadimension.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class PurifierRecipeSerializer<T extends PurifierRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    private final int cookTime;
    private final PurifierRecipeSerializer.IFactory<T> factory;

    public PurifierRecipeSerializer(PurifierRecipeSerializer.IFactory<T> factoryIn, int timeIn) {
        this.cookTime = timeIn;
        this.factory = factoryIn;
    }

    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String s = JSONUtils.getAsString(json, "group", "");
        JsonElement jsonelement = JSONUtils.isArrayNode(json, "ingredient") ? JSONUtils.getAsJsonObject(json, "ingredient") : JSONUtils.getAsJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        //RESULT
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack resultStack;
        if (json.get("result").isJsonObject())
            resultStack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            resultStack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }

        //BYPRODUCT
        if (!json.has("byproduct"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack byStack;
        if (json.get("byproduct").isJsonObject())
            byStack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "byproduct"));
        else {
            String s2 = JSONUtils.getAsString(json, "byproduct");
            ResourceLocation resourcelocation = new ResourceLocation(s2);
            byStack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s2 + " does not exist")));
        }
        float f = JSONUtils.getAsFloat(json, "experience", 0.0F);
        int i = JSONUtils.getAsInt(json, "cookingtime", this.cookTime);
        return this.factory.create(recipeId, s, ingredient, resultStack, byStack, f, i);
    }

    @Override
    public T fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        ItemStack itemstack1 = buffer.readItem();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.factory.create(recipeId, s, ingredient, itemstack, itemstack1, f, i);
    }

    @Override
    public void toNetwork(PacketBuffer buffer, T recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeItem(recipe.byproduct);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public interface IFactory<T extends PurifierRecipe> {
        T create(ResourceLocation id, String group, Ingredient ingredientIn, ItemStack outputIn, ItemStack byproductIn, float experienceIn, int timeIn);
    }
}
