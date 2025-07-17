package androsa.gaiadimension.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import java.util.concurrent.CompletableFuture;

public abstract class GaiaDamageTagProvider extends KeyTagProvider<DamageType> {

    public GaiaDamageTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(output, Registries.DAMAGE_TYPE, provider, modid);
    }

    protected void addTag(TagKey<DamageType> tag, ResourceKey<DamageType>... damages) {
        TagAppender<ResourceKey<DamageType>, DamageType> builder = this.tag(tag);
        builder.add(damages);
    }
}
