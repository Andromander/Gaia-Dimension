package androsa.gaiadimension.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum GaiaToolMaterials implements IItemTier {
    AGATE(1, 150, 2.5F, 1.0F, 5, () -> Ingredient.fromTag(ModItemTags.AGATE_PLANKS)),
    SUGILITE(2, 800, 3.0F, 1.5F, 10, () -> Ingredient.fromTag(ModItemTags.SUGILITE)),
    IXIOLITE(2, 1500, 4.0F, 2.0F, 10, () -> Ingredient.fromTag(ModItemTags.IXIOLITE)),
    EUCLASE(2, 3000, 4.0F, 2.0F, 10, () -> Ingredient.fromTag(ModItemTags.EUCLASE)),
    CARNELIAN(3, 2500, 5.0F, 3.0F, 10, () -> Ingredient.fromTag(ModItemTags.CARNELIAN)),
    BENITOITE(3, 3500, 5.0F, 3.0F, 10, () -> Ingredient.fromTag(ModItemTags.BENITOITE)),
    CHALCEDONY(4, 4000, 6.0F, 4.0F, 10, () -> Ingredient.fromTag(ModItemTags.CHALCEDONY)),

    MALACHITE(3, 5120, 8.0F, 4.0F, 10, null),
    TIGER_EYE(3, 4096, 8.0F, 5.0F, 10, null),
    SPINEL(3, 5120, 8.0F, 4.0F, 15, null),
    ZIRCON(3, 6144, 8.0F, 5.0F, 15, null),
    CORRUPT(4, 13000, 10.0F, 21.0F, 25, null),
    BIXBITE(3,8192, 8.0F, 1.5F, 20, null),
    TSAVORITE(3, 9216, 8.0F, 1.0F, 20, null),
    LARVIKITE(3, 10240, 8.0F, 5.0F, 20, null),
    GAIA_CHAMP(4, 13000, 10.0F, 16.0F, 25, null);

    private final int harvestLevel;
    private final int maximumUse;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    GaiaToolMaterials(int level, int maxUse, float efficiency, float attack, int enchant, Supplier<Ingredient> ingredient) {
        harvestLevel = level;
        maximumUse = maxUse;
        toolEfficiency = efficiency;
        attackDamage = attack;
        enchantability = enchant;
        repairMaterial = ingredient;
    }

    public int getMaxUses() {
        return maximumUse;
    }

    public float getEfficiency() {
        return toolEfficiency;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public int getEnchantability() {
        return enchantability;
    }

    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
