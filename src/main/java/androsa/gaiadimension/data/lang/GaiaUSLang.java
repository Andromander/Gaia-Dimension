package androsa.gaiadimension.data.lang;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.data.PackOutput;

public class GaiaUSLang extends GaiaEnglishLang {
    public GaiaUSLang(PackOutput output) {
        super(output, "en_us");
    }

    @Override
    public void genSpecific() {
        addTree(ModBlocks.fossilized_sapling, ModBlocks.fossilized_leaves,
                ModBlocks.fossilized_log, ModBlocks.stripped_fossilized_log,
                ModBlocks.fossilized_wood, ModBlocks.stripped_fossilized_wood,
                ModBlocks.fossilized_tiles, ModBlocks.fossilized_tile_stairs, ModBlocks.fossilized_tile_slab, ModBlocks.fossilized_curtain,
                ModBlocks.potted_fossilized_sapling, "Fossilized");
        addDeathMessage("malachiteBlast", "%1$s was vaporised", "%1$s was vaporised before %2$s");
        addBiome(GaiaBiomes.smoldering_bog, "Smoldering Bog");
    }

    @Override
    public void addStaffHeadings() {
        super.addStaffHeadings();
        addStaffTip("desc.behavior", "Behavior");
    }

    @Override
    public void addKitTooltips() {
        super.addKitTooltips();
        addKitTip("replace", "Use this to make a part a different Mookaite color");
        addKitTip("color", "This sets the color:");
    }
}
