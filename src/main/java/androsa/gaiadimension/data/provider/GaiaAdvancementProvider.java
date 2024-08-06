package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GaiaAdvancementProvider extends AdvancementProvider {

    public GaiaAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper, List<AdvancementGenerator> list) {
        super(output, provider, helper, list);
    }

    protected static MutableComponent title(String name) {
        return advancement(name + ".title");
    }

    protected static MutableComponent description(String name) {
        return advancement(name + ".description");
    }

    protected static MutableComponent advancement(String name) {
        return Component.translatable("advancements.gaia." + name);
    }

    protected static String loc(String name) {
        return ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia/" + name).toString();
    }

    protected static Criterion<?> biome(HolderLookup.Provider provider, ResourceKey<Biome> define) {
        HolderGetter<Biome> registry = provider.lookupOrThrow(Registries.BIOME);
        return PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registry.getOrThrow(define)));
    }

    protected static Criterion<?> structure(HolderLookup.Provider provider, ResourceKey<Structure> define) {
        HolderGetter<Structure> registry = provider.lookupOrThrow(Registries.STRUCTURE);
        return PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(registry.getOrThrow(define)));
    }

    protected static Criterion<?> item(Supplier<? extends Item> item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item.get());
    }
}
