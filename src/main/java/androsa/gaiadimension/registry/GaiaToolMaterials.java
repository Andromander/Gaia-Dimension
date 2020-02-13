package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public enum GaiaToolMaterials implements IItemTier {
    AGATE(1, 150, 2.5F, 1.0F, 5, () -> Ingredient.fromTag(GaiaTags.AGATE_PLANKS)),
    SUGILITE(2, 800, 3.0F, 1.5F, 10, () -> Ingredient.fromItems(ModItems.sugilite.get())),
    IXIOLITE(2, 1500, 4.0F, 2.0F, 10, () -> Ingredient.fromItems(ModItems.ixiolite.get())),
    EUCLASE(2, 3000, 4.0F, 2.0F, 10, () -> Ingredient.fromItems(ModItems.euclase.get())),
    CARNELIAN(3, 2500, 5.0F, 3.0F, 10, () -> Ingredient.fromItems(ModItems.carnelian.get())),
    BENITOITE(3, 3500, 5.0F, 3.0F, 10, () -> Ingredient.fromItems(ModItems.benitoite.get())),
    CHALCEDONY(4, 4000, 6.0F, 4.0F, 10, () -> Ingredient.fromItems(ModItems.chalcedony.get())),

    MALACHITE(3, 5120, 8.0F, 4.0F, 10, () -> Ingredient.EMPTY),
    TIGER_EYE(3, 4096, 8.0F, 5.0F, 10, () -> Ingredient.EMPTY),
    SPINEL(3, 5120, 8.0F, 4.0F, 15, () -> Ingredient.EMPTY),
    ZIRCON(3, 6144, 8.0F, 5.0F, 15, () -> Ingredient.EMPTY),
    CORRUPT(4, 13000, 10.0F, 21.0F, 25, () -> Ingredient.EMPTY),
    BIXBITE(3,8192, 8.0F, 1.5F, 20, () -> Ingredient.EMPTY),
    TSAVORITE(3, 9216, 8.0F, 1.0F, 20, () -> Ingredient.EMPTY),
    LARVIKITE(3, 10240, 8.0F, 5.0F, 20, () -> Ingredient.EMPTY),
    GAIA_CHAMP(4, 13000, 10.0F, 16.0F, 25, () -> Ingredient.EMPTY);

    private final int harvestLevel;
    private final int maximumUse;
    private final float toolEfficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadBase<Ingredient> repairMaterial;

    GaiaToolMaterials(int level, int maxUse, float efficiency, float attack, int enchant, Supplier<Ingredient> ingredient) {
        harvestLevel = level;
        maximumUse = maxUse;
        toolEfficiency = efficiency;
        attackDamage = attack;
        enchantability = enchant;
        repairMaterial = new LazyLoadBase<>(ingredient);
    }

    @Override
    public int getMaxUses() {
        return maximumUse;
    }

    @Override
    public float getEfficiency() {
        return toolEfficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }
}
