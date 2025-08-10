package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaDamageTagProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaDamageTags extends GaiaDamageTagProvider {

    public GaiaDamageTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, GaiaDimensionMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addTag(DamageTypeTags.BYPASSES_ARMOR, GaiaDamage.ENERGETIC, GaiaDamage.STATIC, GaiaDamage.CORRUPTION, GaiaDamage.MALACHITE_BLAST);
        this.tag(DamageTypeTags.BYPASSES_SHIELD).addTag(DamageTypeTags.BYPASSES_ARMOR);
        this.addTag(DamageTypeTags.BYPASSES_RESISTANCE, GaiaDamage.MALACHITE_BLAST);
        this.addTag(DamageTypeTags.BYPASSES_ENCHANTMENTS, GaiaDamage.MALACHITE_BLAST);
        this.addTag(Tags.DamageTypes.IS_MAGIC,
                GaiaDamage.PHYSICAL_MAGIC,
                GaiaDamage.FIRE_MAGIC,
                GaiaDamage.ELECTRIC_MAGIC,
                GaiaDamage.POISON_MAGIC,
                GaiaDamage.FROST_MAGIC,
                GaiaDamage.LIGHT_MAGIC,
                GaiaDamage.ENERGY_MAGIC);
        this.addTag(DamageTypeTags.BYPASSES_ARMOR,
                GaiaDamage.PHYSICAL_MAGIC,
                GaiaDamage.FIRE_MAGIC,
                GaiaDamage.ELECTRIC_MAGIC,
                GaiaDamage.POISON_MAGIC,
                GaiaDamage.FROST_MAGIC,
                GaiaDamage.LIGHT_MAGIC,
                GaiaDamage.ENERGY_MAGIC);
        this.addTag(DamageTypeTags.IS_FIRE, GaiaDamage.FIRE_MAGIC);
        this.addTag(GaiaTags.Damage.IS_ELECTRIC, GaiaDamage.ELECTRIC_MAGIC);
        this.addTag(Tags.DamageTypes.IS_POISON, GaiaDamage.POISON_MAGIC);
        this.addTag(DamageTypeTags.IS_FREEZING, GaiaDamage.FROST_MAGIC);
        this.addTag(DamageTypeTags.BYPASSES_EFFECTS, GaiaDamage.LIGHT_MAGIC);
        this.addTag(DamageTypeTags.BYPASSES_INVULNERABILITY, GaiaDamage.ENERGY_MAGIC);
        this.addTag(DamageTypeTags.NO_KNOCKBACK, GaiaDamage.CORRUPTION);
    }
}
