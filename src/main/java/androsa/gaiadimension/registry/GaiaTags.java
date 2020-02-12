package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class GaiaTags {

    private static final String ID = GaiaDimensionMod.MODID;

    public static final Tag<Item> PINK_AGATE_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "pink_agate_logs"));
    public static final Tag<Item> BLUE_AGATE_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "blue_agate_logs"));
    public static final Tag<Item> GREEN_AGATE_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "green_agate_logs"));
    public static final Tag<Item> PURPLE_AGATE_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "purple_agate_logs"));
    public static final Tag<Item> FOSSILIZED_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "fossilized_logs"));
    public static final Tag<Item> CORRUPTED_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "corrupted_logs"));
    public static final Tag<Item> BURNT_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "burnt_logs"));
    public static final Tag<Item> BURNING_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "burning_logs"));
    public static final Tag<Item> AURA_LOGS = new ItemTags.Wrapper(new ResourceLocation(ID, "aura_logs"));
    public static final Tag<Item> AGATE_PLANKS = new ItemTags.Wrapper(new ResourceLocation(ID, "agate_planks"));
    public static final Tag<Item> GAIA_BRICKS = new ItemTags.Wrapper(new ResourceLocation(ID, "gaia_bricks"));
    public static final Tag<Item> GEM_POUCH_ITEMS = new ItemTags.Wrapper(new ResourceLocation(ID, "gem_pouch_items"));
}
