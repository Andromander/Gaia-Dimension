package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaItemTagsProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaItemTags extends GaiaItemTagsProvider {
    ImmutableList<Supplier<Item>> BEACON_PAYMENTS = ImmutableList.of(
            ModItems.sugilite, ModItems.hematite, ModItems.cinnabar, ModItems.labradorite, ModItems.moonstone, ModItems.red_opal, ModItems.blue_opal, ModItems.green_opal,
            ModItems.white_opal, ModItems.ixiolite, ModItems.proustite, ModItems.euclase, ModItems.leucite, ModItems.carnelian, ModItems.benitoite, ModItems.diopside,
            ModItems.chalcedony, ModItems.pyrite, ModItems.tektite, ModItems.goldstone, ModItems.aura_cluster, ModItems.bismuth_crystal
    );
    ImmutableList<ITag.INamedTag<Item>> GEM_TAGS = ImmutableList.of(
            GaiaTags.Items.GEMS_SUGILITE, GaiaTags.Items.GEMS_HEMATITE, GaiaTags.Items.GEMS_CINNABAR, GaiaTags.Items.GEMS_LABRADORITE, GaiaTags.Items.GEMS_MOONSTONE,
            GaiaTags.Items.GEMS_RED_OPAL, GaiaTags.Items.GEMS_BLUE_OPAL, GaiaTags.Items.GEMS_GREEN_OPAL, GaiaTags.Items.GEMS_WHITE_OPAL, GaiaTags.Items.GEMS_IXIOLITE,
            GaiaTags.Items.GEMS_PROUSTITE, GaiaTags.Items.GEMS_EUCLASE, GaiaTags.Items.GEMS_LEUCITE, GaiaTags.Items.GEMS_CARNELIAN, GaiaTags.Items.GEMS_BENITOITE,
            GaiaTags.Items.GEMS_DIOPSIDE, GaiaTags.Items.GEMS_CHALCEDONY, GaiaTags.Items.GEMS_PYRITE, GaiaTags.Items.GEMS_TEKTITE, GaiaTags.Items.GEMS_GOLDSTONE,
            GaiaTags.Items.GEMS_AURA, GaiaTags.Items.GEMS_BISMUTH
    );
    ImmutableList<ITag.INamedTag<Item>> ORE_TAGS = ImmutableList.of(
            GaiaTags.Items.ORES_SUGILITE, GaiaTags.Items.ORES_HEMATITE, GaiaTags.Items.ORES_CINNABAR, GaiaTags.Items.ORES_LABRADORITE, GaiaTags.Items.ORES_MOONSTONE,
            GaiaTags.Items.ORES_RED_OPAL, GaiaTags.Items.ORES_BLUE_OPAL, GaiaTags.Items.ORES_GREEN_OPAL, GaiaTags.Items.ORES_WHITE_OPAL, GaiaTags.Items.ORES_PYRITE
    );
    ImmutableList<ITag.INamedTag<Item>> STORAGE_TAGS = ImmutableList.of(
            GaiaTags.Items.STORAGE_BLOCKS_SUGILITE, GaiaTags.Items.STORAGE_BLOCKS_HEMATITE, GaiaTags.Items.STORAGE_BLOCKS_CINNABAR, GaiaTags.Items.STORAGE_BLOCKS_LABRADORITE,
            GaiaTags.Items.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Items.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Items.STORAGE_BLOCKS_BLUE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_GREEN_OPAL,
            GaiaTags.Items.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_PYRITE, GaiaTags.Items.STORAGE_BLOCKS_TEKTITE, GaiaTags.Items.STORAGE_BLOCKS_GOLDSTONE,
            GaiaTags.Items.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Items.STORAGE_BLOCKS_BISMUTH, GaiaTags.Items.STORAGE_BLOCKS_IXIOLITE, GaiaTags.Items.STORAGE_BLOCKS_PROUSTITE,
            GaiaTags.Items.STORAGE_BLOCKS_EUCLASE, GaiaTags.Items.STORAGE_BLOCKS_LEUCITE, GaiaTags.Items.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Items.STORAGE_BLOCKS_BENITOITE,
            GaiaTags.Items.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Items.STORAGE_BLOCKS_CHALCEDONY
    );

    public GaiaItemTags(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, provider, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(ItemTags.ARROWS).add(ModItems.agate_arrow.get());
        addTag(ItemTags.BEACON_PAYMENT_ITEMS, BEACON_PAYMENTS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.NON_FLAMMABLE_WOOD, ItemTags.NON_FLAMMABLE_WOOD);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);

        copy(GaiaTags.Blocks.TILES, GaiaTags.Items.TILES);
        copy(GaiaTags.Blocks.PINK_AGATE_LOGS, GaiaTags.Items.PINK_AGATE_LOGS);
        copy(GaiaTags.Blocks.BLUE_AGATE_LOGS, GaiaTags.Items.BLUE_AGATE_LOGS);
        copy(GaiaTags.Blocks.GREEN_AGATE_LOGS, GaiaTags.Items.GREEN_AGATE_LOGS);
        copy(GaiaTags.Blocks.PURPLE_AGATE_LOGS, GaiaTags.Items.PURPLE_AGATE_LOGS);
        copy(GaiaTags.Blocks.FOSSILIZED_LOGS, GaiaTags.Items.FOSSILIZED_LOGS);
        copy(GaiaTags.Blocks.CORRUPTED_LOGS, GaiaTags.Items.CORRUPTED_LOGS);
        copy(GaiaTags.Blocks.BURNT_LOGS, GaiaTags.Items.BURNT_LOGS);
        copy(GaiaTags.Blocks.BURNING_LOGS, GaiaTags.Items.BURNING_LOGS);
        copy(GaiaTags.Blocks.AURA_LOGS, GaiaTags.Items.AURA_LOGS);
        copy(GaiaTags.Blocks.GAIA_BRICKS, GaiaTags.Items.GAIA_BRICKS);
        copy(GaiaTags.Blocks.AMETHYST_BRICKS, GaiaTags.Items.AMETHYST_BRICKS);
        copy(GaiaTags.Blocks.COPAL_BRICKS, GaiaTags.Items.COPAL_BRICKS);
        copy(GaiaTags.Blocks.JADE_BRICKS, GaiaTags.Items.JADE_BRICKS);
        copy(GaiaTags.Blocks.JET_BRICKS, GaiaTags.Items.JET_BRICKS);
        addTag(GaiaTags.Items.GEM_POUCH_ITEMS, BEACON_PAYMENTS);
        getOrCreateBuilder(GaiaTags.Items.CRUDE_STORAGE_BLACKLIST).add(
                //Mojang, actually fuck you for this. "pls dont be mad, we just work under big corporation" and you can't even make an *Item* Tag for shulker boxes???
                Items.SHULKER_BOX, Items.BLACK_SHULKER_BOX, Items.BLUE_SHULKER_BOX, Items.BROWN_SHULKER_BOX, Items.CYAN_SHULKER_BOX, Items.GRAY_SHULKER_BOX, Items.GREEN_SHULKER_BOX,
                Items.LIGHT_BLUE_SHULKER_BOX, Items.LIGHT_GRAY_SHULKER_BOX, Items.LIME_SHULKER_BOX, Items.MAGENTA_SHULKER_BOX, Items.ORANGE_SHULKER_BOX, Items.PINK_SHULKER_BOX,
                Items.PURPLE_SHULKER_BOX, Items.RED_SHULKER_BOX, Items.WHITE_SHULKER_BOX, Items.YELLOW_SHULKER_BOX
        ).add(ModBlocks.crude_storage_crate.asItem(), ModBlocks.mega_storage_crate.asItem());
        getOrCreateBuilder(GaiaTags.Items.CRUDE_STORAGE_BLACKLIST).add(
                //Repeat above. Quit the excuse game, you aren't a bunch of tiny modders anymore.
                Items.SHULKER_BOX, Items.BLACK_SHULKER_BOX, Items.BLUE_SHULKER_BOX, Items.BROWN_SHULKER_BOX, Items.CYAN_SHULKER_BOX, Items.GRAY_SHULKER_BOX, Items.GREEN_SHULKER_BOX,
                Items.LIGHT_BLUE_SHULKER_BOX, Items.LIGHT_GRAY_SHULKER_BOX, Items.LIME_SHULKER_BOX, Items.MAGENTA_SHULKER_BOX, Items.ORANGE_SHULKER_BOX, Items.PINK_SHULKER_BOX,
                Items.PURPLE_SHULKER_BOX, Items.RED_SHULKER_BOX, Items.WHITE_SHULKER_BOX, Items.YELLOW_SHULKER_BOX
        ).add(ModBlocks.mega_storage_crate.asItem());

        getOrCreateBuilder(GaiaTags.Items.DUSTS_FINE).add(ModItems.fine_dust.get());
        getOrCreateBuilder(GaiaTags.Items.DUSTS_GOLDSTONE).add(ModItems.goldstone_dust.get());
        getOrCreateBuilder(GaiaTags.Items.DUSTS_HOT).add(ModItems.hot_dust.get());
        getOrCreateBuilder(GaiaTags.Items.INGOTS_SCAYNYX).add(ModItems.scaynyx_ingot.get());
        getOrCreateBuilder(GaiaTags.Items.RODS_AGATE).add(ModItems.agate_stick.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_SUGILITE).add(ModItems.sugilite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_HEMATITE).add(ModItems.hematite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_CINNABAR).add(ModItems.cinnabar.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_LABRADORITE).add(ModItems.labradorite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_MOONSTONE).add(ModItems.moonstone.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_RED_OPAL).add(ModItems.red_opal.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_BLUE_OPAL).add(ModItems.blue_opal.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_GREEN_OPAL).add(ModItems.green_opal.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_WHITE_OPAL).add(ModItems.white_opal.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_IXIOLITE).add(ModItems.ixiolite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_PROUSTITE).add(ModItems.proustite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_EUCLASE).add(ModItems.euclase.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_LEUCITE).add(ModItems.leucite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_CARNELIAN).add(ModItems.carnelian.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_BENITOITE).add(ModItems.benitoite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_DIOPSIDE).add(ModItems.diopside.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_CHALCEDONY).add(ModItems.chalcedony.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_PYRITE).add(ModItems.pyrite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_TEKTITE).add(ModItems.tektite.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_GOLDSTONE).add(ModItems.goldstone.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_AURA).add(ModItems.aura_cluster.get());
        getOrCreateBuilder(GaiaTags.Items.GEMS_BISMUTH).add(ModItems.bismuth_crystal.get());

        copy(GaiaTags.Blocks.ORES_SUGILITE, GaiaTags.Items.ORES_SUGILITE);
        copy(GaiaTags.Blocks.ORES_HEMATITE, GaiaTags.Items.ORES_HEMATITE);
        copy(GaiaTags.Blocks.ORES_CINNABAR, GaiaTags.Items.ORES_CINNABAR);
        copy(GaiaTags.Blocks.ORES_LABRADORITE, GaiaTags.Items.ORES_LABRADORITE);
        copy(GaiaTags.Blocks.ORES_MOONSTONE, GaiaTags.Items.ORES_MOONSTONE);
        copy(GaiaTags.Blocks.ORES_RED_OPAL, GaiaTags.Items.ORES_RED_OPAL);
        copy(GaiaTags.Blocks.ORES_BLUE_OPAL, GaiaTags.Items.ORES_BLUE_OPAL);
        copy(GaiaTags.Blocks.ORES_GREEN_OPAL, GaiaTags.Items.ORES_GREEN_OPAL);
        copy(GaiaTags.Blocks.ORES_WHITE_OPAL, GaiaTags.Items.ORES_WHITE_OPAL);
        copy(GaiaTags.Blocks.ORES_PYRITE, GaiaTags.Items.ORES_PYRITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE, GaiaTags.Items.STORAGE_BLOCKS_SUGILITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE, GaiaTags.Items.STORAGE_BLOCKS_HEMATITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR, GaiaTags.Items.STORAGE_BLOCKS_CINNABAR);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE, GaiaTags.Items.STORAGE_BLOCKS_LABRADORITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Items.STORAGE_BLOCKS_MOONSTONE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Items.STORAGE_BLOCKS_RED_OPAL);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_BLUE_OPAL);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL, GaiaTags.Items.STORAGE_BLOCKS_GREEN_OPAL);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_WHITE_OPAL);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE, GaiaTags.Items.STORAGE_BLOCKS_PYRITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE, GaiaTags.Items.STORAGE_BLOCKS_TEKTITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE, GaiaTags.Items.STORAGE_BLOCKS_GOLDSTONE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Items.STORAGE_BLOCKS_AURA_CRYSTAL);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH, GaiaTags.Items.STORAGE_BLOCKS_BISMUTH);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_IXIOLITE, GaiaTags.Items.STORAGE_BLOCKS_IXIOLITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE, GaiaTags.Items.STORAGE_BLOCKS_PROUSTITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE, GaiaTags.Items.STORAGE_BLOCKS_EUCLASE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_LEUCITE, GaiaTags.Items.STORAGE_BLOCKS_LEUCITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Items.STORAGE_BLOCKS_CARNELIAN);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE, GaiaTags.Items.STORAGE_BLOCKS_BENITOITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Items.STORAGE_BLOCKS_DIOPSIDE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_CHALCEDONY, GaiaTags.Items.STORAGE_BLOCKS_CHALCEDONY);

        getOrCreateBuilder(Tags.Items.BONES).add(ModItems.shiny_bone.get());
        getOrCreateBuilder(Tags.Items.DUSTS).addTags(GaiaTags.Items.DUSTS_FINE, GaiaTags.Items.DUSTS_GOLDSTONE, GaiaTags.Items.DUSTS_HOT);
        for (ITag.INamedTag<Item> tag : GEM_TAGS) {
            getOrCreateBuilder(Tags.Items.GEMS).addTag(tag);
        }
        getOrCreateBuilder(Tags.Items.INGOTS).addTag(GaiaTags.Items.INGOTS_SCAYNYX);
        getOrCreateBuilder(Tags.Items.RODS).addTag(GaiaTags.Items.RODS_AGATE);
        for (ITag.INamedTag<Item> tag : ORE_TAGS) {
            getOrCreateBuilder(Tags.Items.ORES).addTag(tag);
        }
        for (ITag.INamedTag<Item> tag : STORAGE_TAGS) {
            getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).addTag(tag);
        }
    }
}
