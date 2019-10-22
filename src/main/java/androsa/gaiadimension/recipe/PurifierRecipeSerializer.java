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
    public T read(ResourceLocation recipeId, JsonObject json) {
        String s = JSONUtils.getString(json, "group", "");
        JsonElement jsonelement = JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient");
        Ingredient ingredient = Ingredient.deserialize(jsonelement);
        //RESULT
        if (!json.has("result"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack resultStack;
        if (json.get("result").isJsonObject())
            resultStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            resultStack = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s1 + " does not exist")));
        }

        //BYPRODUCT
        if (!json.has("byproduct"))
            throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack byStack;
        if (json.get("byproduct").isJsonObject())
            byStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "byproduct"));
        else {
            String s2 = JSONUtils.getString(json, "byproduct");
            ResourceLocation resourcelocation = new ResourceLocation(s2);
            byStack = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> new IllegalStateException("Item: " + s2 + " does not exist")));
        }
        float f = JSONUtils.getFloat(json, "experience", 0.0F);
        int i = JSONUtils.getInt(json, "cookingtime", this.cookTime);
        return this.factory.create(recipeId, s, ingredient, resultStack, byStack, f, i);
    }

    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        String s = buffer.readString(32767);
        Ingredient ingredient = Ingredient.read(buffer);
        ItemStack itemstack = buffer.readItemStack();
        ItemStack itemstack1 = buffer.readItemStack();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.factory.create(recipeId, s, ingredient, itemstack, itemstack1, f, i);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        buffer.writeString(recipe.group);
        recipe.ingredient.write(buffer);
        buffer.writeItemStack(recipe.result);
        buffer.writeItemStack(recipe.byproduct);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
    }

    public interface IFactory<T extends PurifierRecipe> {
        T create(ResourceLocation id, String group, Ingredient ingredientIn, ItemStack outputIn, ItemStack byproductIn, float experienceIn, int timeIn);
    }
}
