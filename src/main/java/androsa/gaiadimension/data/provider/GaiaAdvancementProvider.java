package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GaiaAdvancementProvider extends ForgeAdvancementProvider {

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
        return new ResourceLocation(GaiaDimensionMod.MODID, "gaia/" + name).toString();
    }

    protected static CriterionTriggerInstance biome(ResourceKey<Biome> define) {
        return PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(define));
    }

    protected static CriterionTriggerInstance item(RegistryObject<? extends Item> item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item.get());
    }
}
