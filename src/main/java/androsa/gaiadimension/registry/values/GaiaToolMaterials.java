package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.registry.registration.ModItems;
import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum GaiaToolMaterials implements Tier {
    AGATE(GaiaTags.Blocks.INCORRECT_FOR_AGATE, 150, 2.5F, 1.0F, 5, () -> Ingredient.of(GaiaTags.Items.TILES)),
    SUGILITE(GaiaTags.Blocks.INCORRECT_FOR_SUGILITE, 800, 3.0F, 1.5F, 10, () -> Ingredient.of(ModItems.sugilite.get())),
    STIBNITE(GaiaTags.Blocks.INCORRECT_FOR_STIBNITE, 1500, 4.0F, 2.0F, 10, () -> Ingredient.of(ModItems.stibnite.get())),
    EUCLASE(GaiaTags.Blocks.INCORRECT_FOR_EUCLASE, 3000, 4.0F, 2.0F, 10, () -> Ingredient.of(ModItems.euclase.get())),
    CARNELIAN(GaiaTags.Blocks.INCORRECT_FOR_CARNELIAN, 2500, 5.0F, 3.0F, 10, () -> Ingredient.of(ModItems.carnelian.get())),
    BENITOITE(GaiaTags.Blocks.INCORRECT_FOR_BENITOITE, 3500, 5.0F, 3.0F, 10, () -> Ingredient.of(ModItems.benitoite.get())),
    GOSHENITE(GaiaTags.Blocks.INCORRECT_FOR_GOSHENITE, 4000, 6.0F, 4.0F, 10, () -> Ingredient.of(ModItems.goshenite.get())),

    MALACHITE(GaiaTags.Blocks.INCORRECT_FOR_MALACHITE, 5120, 8.0F, 4.0F, 10, () -> Ingredient.EMPTY),
    TIGER_EYE(GaiaTags.Blocks.INCORRECT_FOR_TIGER_EYE, 4096, 8.0F, 5.0F, 10, () -> Ingredient.EMPTY),
    SPINEL(GaiaTags.Blocks.INCORRECT_FOR_SPINEL, 5120, 8.0F, 4.0F, 15, () -> Ingredient.EMPTY),
    ZIRCON(GaiaTags.Blocks.INCORRECT_FOR_ZIRCON, 6144, 8.0F, 5.0F, 15, () -> Ingredient.EMPTY),
    CORRUPT(GaiaTags.Blocks.INCORRECT_FOR_CORRUPT, 13000, 10.0F, 21.0F, 25, () -> Ingredient.EMPTY),
    BIXBITE(GaiaTags.Blocks.INCORRECT_FOR_BIXBITE, 8192, 8.0F, 1.5F, 20, () -> Ingredient.EMPTY),
    TSAVORITE(GaiaTags.Blocks.INCORRECT_FOR_TSAVORITE, 9216, 8.0F, 1.0F, 20, () -> Ingredient.EMPTY),
    LARVIKITE(GaiaTags.Blocks.INCORRECT_FOR_LARVIKITE, 10240, 8.0F, 5.0F, 20, () -> Ingredient.EMPTY),
    GAIA_CHAMP(GaiaTags.Blocks.INCORRECT_FOR_CHAMPION, 13000, 10.0F, 16.0F, 25, () -> Ingredient.EMPTY);

    private final TagKey<Block> incorrectDropsTag;
    private final int maximumUse;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    GaiaToolMaterials(TagKey<Block> tag, int maxUse, float efficiency, float attack, int enchant, Supplier<Ingredient> ingredient) {
        incorrectDropsTag = tag;
        maximumUse = maxUse;
        toolEfficiency = efficiency;
        attackDamage = attack;
        enchantability = enchant;
        repairMaterial = Suppliers.memoize(ingredient::get);
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectDropsTag;
    }

    @Override
    public int getUses() {
        return maximumUse;
    }

    @Override
    public float getSpeed() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
