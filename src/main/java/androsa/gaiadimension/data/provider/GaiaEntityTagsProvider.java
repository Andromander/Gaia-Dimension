package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GaiaEntityTagsProvider extends EntityTypeTagsProvider {
    public GaiaEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper) {
        super(output, provider, modid, helper);
    }

    protected void addTag(TagKey<EntityType<?>> tag, ImmutableList<Supplier<? extends EntityType<?>>> list) {
        IntrinsicHolderTagsProvider.IntrinsicTagAppender<EntityType<?>> builder = this.tag(tag);
        for (Supplier<? extends EntityType<?>> entity : list) {
            builder.add(entity.get());
        }
    }
}
