package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class GaiaTags {

    private static final String ID = GaiaDimensionMod.MODID;

    public static final ITag.INamedTag<Item> PINK_AGATE_LOGS = tagItem("pink_agate_logs");
    public static final ITag.INamedTag<Item> BLUE_AGATE_LOGS = tagItem("blue_agate_logs");
    public static final ITag.INamedTag<Item> GREEN_AGATE_LOGS = tagItem("green_agate_logs");
    public static final ITag.INamedTag<Item> PURPLE_AGATE_LOGS = tagItem("purple_agate_logs");
    public static final ITag.INamedTag<Item> FOSSILIZED_LOGS = tagItem("fossilized_logs");
    public static final ITag.INamedTag<Item> CORRUPTED_LOGS = tagItem("corrupted_logs");
    public static final ITag.INamedTag<Item> BURNT_LOGS = tagItem("burnt_logs");
    public static final ITag.INamedTag<Item> BURNING_LOGS = tagItem("burning_logs");
    public static final ITag.INamedTag<Item> AURA_LOGS = tagItem("aura_logs");
    public static final ITag.INamedTag<Item> AGATE_PLANKS = tagItem("agate_planks");
    public static final ITag.INamedTag<Item> GAIA_BRICKS = tagItem("gaia_bricks");
    public static final ITag.INamedTag<Item> GEM_POUCH_ITEMS = tagItem("gem_pouch_items");

    private static ITag.INamedTag<Item> tagItem(String name) {
        return ItemTags.makeWrapperTag(new ResourceLocation(ID, name).toString());
    }
}
