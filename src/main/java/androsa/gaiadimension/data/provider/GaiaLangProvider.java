package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Supplier;

public abstract class GaiaLangProvider extends LanguageProvider {

    public GaiaLangProvider(PackOutput output, String locale) {
        super(output, GaiaDimensionMod.MODID, locale);
    }

    public void addFluid(Supplier<? extends Block> block, Supplier<? extends FluidType> fluid, String name) {
        addBlock(block, name);
        add(fluid.get().getDescriptionId(), name);
    }

    public void addDeathMessage(String key, String message, String playermessage) {
        add("death.attack.gaiadimension." + key, message);
        add("death.attack.gaiadimension." + key + ".player", playermessage);
    }

    public void addGui(String key, String message) {
        add("gui.gaiadimension.category." + key, message);
    }

    public void addAdvancement(String key, String title, String description) {
        add("advancements.gaia." + key + ".title", title);
        add("advancements.gaia." + key + ".description", description);
    }

    public void addEntitySubtitles(String entity, String name) {
        addSubtitle("entity." + entity + ".hurt", name + " hurts");
        addSubtitle("entity." + entity + ".death", name + " dies");
    }

    public void addEntitySubtitle(String entity, String key, String name, String subtitle) {
        addSubtitle("entity." + entity + "." + key, name + " " + subtitle);
    }

    public void addSubtitle(String key, String subtitle) {
        add("subtitles." + key, subtitle);
    }
}
