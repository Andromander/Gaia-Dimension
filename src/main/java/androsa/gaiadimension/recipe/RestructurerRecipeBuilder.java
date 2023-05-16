package androsa.gaiadimension.recipe;

import androsa.gaiadimension.registry.registration.ModRecipes;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RestructurerRecipeBuilder implements RecipeBuilder {

    private final Ingredient ingredient;
    private final Item result;
    private final Item byproduct;
    private final float experience;
    private final int cookingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group;

    private RestructurerRecipeBuilder(Ingredient ingredient, ItemLike result, ItemLike byproduct, float experience, int time) {
        this.ingredient = ingredient;
        this.result = result.asItem();
        this.byproduct = byproduct.asItem();
        this.experience = experience;
        this.cookingTime = time;
    }

    public static RestructurerRecipeBuilder restructuring(Ingredient ingredient, ItemLike result, ItemLike byproduct, float experience, int time) {
        return new RestructurerRecipeBuilder(ingredient, result, byproduct, experience, time);
    }

    @Override
    public RestructurerRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criteria) {
        this.advancement.addCriterion(name, criteria);
        return this;
    }

    @Override
    public RestructurerRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation location) {
        this.validate(location);
        this.advancement
                .parent(ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location))
                .rewards(AdvancementRewards.Builder.recipe(location))
                .requirements(RequirementsStrategy.OR);
        consumer.accept(new RestructurerRecipeBuilder.Result(location, this.group == null ? "" : this.group, this.ingredient, this.result, this.byproduct, this.experience, this.cookingTime, location, this.advancement));
    }

    private void validate(ResourceLocation location) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }

    static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final Item byproduct;
        private final float experience;
        private final int cookingTime;
        private final ResourceLocation advancementID;
        private final Advancement.Builder advancement;

        public Result(ResourceLocation id, String group, Ingredient ingredient, Item result, Item byproduct, float experience, int cookingTime, ResourceLocation advancementID, Advancement.Builder advancement) {
            this.id = id;
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.byproduct = byproduct;
            this.experience = experience;
            this.cookingTime = cookingTime;
            this.advancementID = advancementID.withPrefix("recipes/restructuring/");
            this.advancement = advancement;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("ingredient", this.ingredient.toJson());
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.addProperty("byproduct", ForgeRegistries.ITEMS.getKey(this.byproduct).toString());
            json.addProperty("experience", this.experience);
            json.addProperty("cookingtime", this.cookingTime);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipes.RESTRUCTURING_SERIALIZER.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementID;
        }
    }
}
