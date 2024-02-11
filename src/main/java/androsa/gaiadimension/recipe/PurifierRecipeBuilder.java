package androsa.gaiadimension.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PurifierRecipeBuilder implements RecipeBuilder {

    private final Ingredient ingredient;
    private final ItemStack result;
    private final ItemStack byproduct;
    private final float experience;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private String group;

    private PurifierRecipeBuilder(Ingredient ingredient, ItemStack result, ItemStack byproduct, float experience, int time) {
        this.ingredient = ingredient;
        this.result = result;
        this.byproduct = byproduct;
        this.experience = experience;
        this.cookingTime = time;
    }

    public static PurifierRecipeBuilder purifying(Ingredient ingredient, ItemStack result, ItemStack byproduct, float experience, int time) {
        return new PurifierRecipeBuilder(ingredient, result, byproduct, experience, time);
    }

    public static PurifierRecipeBuilder purifying(Ingredient ingredient, ItemLike result, ItemLike byproduct, float experience, int time) {
        return new PurifierRecipeBuilder(ingredient, new ItemStack(result), new ItemStack(byproduct), experience, time);
    }

    @Override
    public PurifierRecipeBuilder unlockedBy(String name, Criterion<?> criteria) {
        this.criteria.put(name, criteria);
        return this;
    }

    @Override
    public PurifierRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result.getItem();
    }

    @Override
    public void save(RecipeOutput consumer, ResourceLocation location) {
        this.validate(location);
        Advancement.Builder builder = consumer.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location))
                .rewards(AdvancementRewards.Builder.recipe(location))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(builder::addCriterion);
        PurifierRecipe recipe = new PurifierRecipe(
                Objects.requireNonNullElse(this.group, ""),
                this.ingredient,
                this.result,
                this.byproduct,
                this.experience,
                this.cookingTime);
        consumer.accept(location, recipe, builder.build(location.withPrefix("recipes/purifying/")));
    }

    private void validate(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
