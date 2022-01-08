package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class GaiaAdvancementProvider extends AdvancementProvider {

    public GaiaAdvancementProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, helper);
    }

    protected static TranslatableComponent title(String name) {
        return advancement(name + ".title");
    }

    protected static TranslatableComponent description(String name) {
        return advancement(name + ".description");
    }

    protected static TranslatableComponent advancement(String name) {
        return new TranslatableComponent("advancements.gaia." + name);
    }

    protected static String loc(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "gaia/" + name).toString();
    }

    protected static CriterionTriggerInstance biome(ResourceKey<Biome> define) {
        return LocationTrigger.TriggerInstance.located(LocationPredicate.inBiome(define));
    }

    protected static CriterionTriggerInstance item(RegistryObject<? extends Item> item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item.get());
    }
}
