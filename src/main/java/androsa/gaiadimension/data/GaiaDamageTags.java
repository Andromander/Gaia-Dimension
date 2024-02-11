package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaDamageTagProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
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
    }
}
