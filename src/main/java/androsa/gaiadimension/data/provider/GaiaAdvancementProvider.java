package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PositionTrigger;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;

public class GaiaAdvancementProvider extends AdvancementProvider {

    public GaiaAdvancementProvider(DataGenerator generator) {
        super(generator);
    }

    protected static TranslationTextComponent title(String name) {
        return advancement(name + ".title");
    }

    protected static TranslationTextComponent description(String name) {
        return advancement(name + ".description");
    }

    protected static TranslationTextComponent advancement(String name) {
        return new TranslationTextComponent("advancements.gaia." + name);
    }

    protected static String loc(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "gaia/" + name).toString();
    }

    protected static ICriterionInstance biome(RegistryKey<Biome> key) {
        return PositionTrigger.Instance.forLocation(LocationPredicate.forBiome(key));
    }
}
