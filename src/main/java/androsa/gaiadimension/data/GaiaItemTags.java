package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaItemTagsProvider;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GaiaItemTags extends GaiaItemTagsProvider {
    ImmutableList<Supplier<Item>> BEACON_PAYMENTS = ImmutableList.of(
            ModItems.sugilite, ModItems.hematite, ModItems.cinnabar, ModItems.labradorite, ModItems.moonstone, ModItems.red_opal, ModItems.blue_opal, ModItems.green_opal,
            ModItems.white_opal, ModItems.stibnite, ModItems.proustite, ModItems.euclase, ModItems.albite, ModItems.carnelian, ModItems.benitoite, ModItems.diopside,
            ModItems.goshenite, ModItems.pyrite, ModItems.tektite, ModItems.goldstone, ModItems.aura_cluster, ModItems.bismuth_crystal, ModItems.opalite, ModItems.celestine
    );
    ImmutableList<TagKey<Item>> GEM_TAGS = ImmutableList.of(
            GaiaTags.Items.GEMS_SUGILITE, GaiaTags.Items.GEMS_HEMATITE, GaiaTags.Items.GEMS_CINNABAR, GaiaTags.Items.GEMS_LABRADORITE, GaiaTags.Items.GEMS_MOONSTONE,
            GaiaTags.Items.GEMS_RED_OPAL, GaiaTags.Items.GEMS_BLUE_OPAL, GaiaTags.Items.GEMS_GREEN_OPAL, GaiaTags.Items.GEMS_WHITE_OPAL, GaiaTags.Items.GEMS_STIBNITE,
            GaiaTags.Items.GEMS_PROUSTITE, GaiaTags.Items.GEMS_EUCLASE, GaiaTags.Items.GEMS_ALBITE, GaiaTags.Items.GEMS_CARNELIAN, GaiaTags.Items.GEMS_BENITOITE,
            GaiaTags.Items.GEMS_DIOPSIDE, GaiaTags.Items.GEMS_GOSHENITE, GaiaTags.Items.GEMS_PYRITE, GaiaTags.Items.GEMS_TEKTITE, GaiaTags.Items.GEMS_GOLDSTONE,
            GaiaTags.Items.GEMS_AURA, GaiaTags.Items.GEMS_BISMUTH, GaiaTags.Items.GEMS_OPALITE, GaiaTags.Items.GEMS_CELESTINE
    );
    ImmutableList<TagKey<Item>> ORE_TAGS = ImmutableList.of(
            GaiaTags.Items.ORES_SUGILITE, GaiaTags.Items.ORES_HEMATITE, GaiaTags.Items.ORES_CINNABAR, GaiaTags.Items.ORES_LABRADORITE, GaiaTags.Items.ORES_MOONSTONE,
            GaiaTags.Items.ORES_RED_OPAL, GaiaTags.Items.ORES_BLUE_OPAL, GaiaTags.Items.ORES_GREEN_OPAL, GaiaTags.Items.ORES_WHITE_OPAL, GaiaTags.Items.ORES_PYRITE
    );
    ImmutableList<TagKey<Item>> STORAGE_TAGS = ImmutableList.of(
            GaiaTags.Items.STORAGE_BLOCKS_SUGILITE, GaiaTags.Items.STORAGE_BLOCKS_HEMATITE, GaiaTags.Items.STORAGE_BLOCKS_CINNABAR, GaiaTags.Items.STORAGE_BLOCKS_LABRADORITE,
            GaiaTags.Items.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Items.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Items.STORAGE_BLOCKS_BLUE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_GREEN_OPAL,
            GaiaTags.Items.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_PYRITE, GaiaTags.Items.STORAGE_BLOCKS_TEKTITE, GaiaTags.Items.STORAGE_BLOCKS_GOLDSTONE,
            GaiaTags.Items.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Items.STORAGE_BLOCKS_BISMUTH, GaiaTags.Items.STORAGE_BLOCKS_STIBNITE, GaiaTags.Items.STORAGE_BLOCKS_PROUSTITE,
            GaiaTags.Items.STORAGE_BLOCKS_EUCLASE, GaiaTags.Items.STORAGE_BLOCKS_ALBITE, GaiaTags.Items.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Items.STORAGE_BLOCKS_BENITOITE,
            GaiaTags.Items.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Items.STORAGE_BLOCKS_GOSHENITE
    );

    public GaiaItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blocktags, ExistingFileHelper existingFileHelper) {
        super(output, provider, blocktags, GaiaDimensionMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //Sigh
        tag(GaiaTags.Items.SHULKER_BOXES).add(Blocks.SHULKER_BOX.asItem(), Blocks.BLACK_SHULKER_BOX.asItem(), Blocks.BLUE_SHULKER_BOX.asItem(), Blocks.BROWN_SHULKER_BOX.asItem(),
                Blocks.CYAN_SHULKER_BOX.asItem(), Blocks.GRAY_SHULKER_BOX.asItem(), Blocks.GREEN_SHULKER_BOX.asItem(), Blocks.LIGHT_BLUE_SHULKER_BOX.asItem(), Blocks.LIGHT_GRAY_SHULKER_BOX.asItem(),
                Blocks.LIME_SHULKER_BOX.asItem(), Blocks.MAGENTA_SHULKER_BOX.asItem(), Blocks.ORANGE_SHULKER_BOX.asItem(), Blocks.PINK_SHULKER_BOX.asItem(), Blocks.PURPLE_SHULKER_BOX.asItem(),
                Blocks.RED_SHULKER_BOX.asItem(), Blocks.WHITE_SHULKER_BOX.asItem(), Blocks.YELLOW_SHULKER_BOX.asItem());

        tag(ItemTags.ARROWS).add(ModItems.agate_arrow.get());
        addTag(ItemTags.BEACON_PAYMENT_ITEMS, BEACON_PAYMENTS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);

        copy(GaiaTags.Blocks.MOOKAITE, GaiaTags.Items.MOOKAITE);
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
        copy(GaiaTags.Blocks.GOLDEN_LOGS, GaiaTags.Items.GOLDEN_LOGS);
        copy(GaiaTags.Blocks.GAIA_BRICKS, GaiaTags.Items.GAIA_BRICKS);
        copy(GaiaTags.Blocks.AMETHYST_BRICKS, GaiaTags.Items.AMETHYST_BRICKS);
        copy(GaiaTags.Blocks.COPAL_BRICKS, GaiaTags.Items.COPAL_BRICKS);
        copy(GaiaTags.Blocks.JADE_BRICKS, GaiaTags.Items.JADE_BRICKS);
        copy(GaiaTags.Blocks.JET_BRICKS, GaiaTags.Items.JET_BRICKS);
        addTag(GaiaTags.Items.GEM_POUCH_ITEMS, BEACON_PAYMENTS);
        tag(GaiaTags.Items.CRUDE_STORAGE_BLACKLIST).addTag(GaiaTags.Items.SHULKER_BOXES).add(ModBlocks.crude_storage_crate.get().asItem(), ModBlocks.mega_storage_crate.get().asItem());
        tag(GaiaTags.Items.MEGA_STORAGE_BLACKLIST).addTag(GaiaTags.Items.SHULKER_BOXES).add(ModBlocks.mega_storage_crate.get().asItem());

        tag(GaiaTags.Items.DUSTS_FINE).add(ModItems.fine_dust.get());
        tag(GaiaTags.Items.DUSTS_GOLDSTONE).add(ModItems.goldstone_dust.get());
        tag(GaiaTags.Items.DUSTS_HOT).add(ModItems.hot_dust.get());
        tag(GaiaTags.Items.INGOTS_SCAYNYX).add(ModItems.scaynyx_ingot.get());
        tag(GaiaTags.Items.RODS_AGATE).add(ModItems.agate_stick.get());
        tag(GaiaTags.Items.GEMS_SUGILITE).add(ModItems.sugilite.get());
        tag(GaiaTags.Items.GEMS_HEMATITE).add(ModItems.hematite.get());
        tag(GaiaTags.Items.GEMS_CINNABAR).add(ModItems.cinnabar.get());
        tag(GaiaTags.Items.GEMS_LABRADORITE).add(ModItems.labradorite.get());
        tag(GaiaTags.Items.GEMS_MOONSTONE).add(ModItems.moonstone.get());
        tag(GaiaTags.Items.GEMS_RED_OPAL).add(ModItems.red_opal.get());
        tag(GaiaTags.Items.GEMS_BLUE_OPAL).add(ModItems.blue_opal.get());
        tag(GaiaTags.Items.GEMS_GREEN_OPAL).add(ModItems.green_opal.get());
        tag(GaiaTags.Items.GEMS_WHITE_OPAL).add(ModItems.white_opal.get());
        tag(GaiaTags.Items.GEMS_STIBNITE).add(ModItems.stibnite.get());
        tag(GaiaTags.Items.GEMS_PROUSTITE).add(ModItems.proustite.get());
        tag(GaiaTags.Items.GEMS_EUCLASE).add(ModItems.euclase.get());
        tag(GaiaTags.Items.GEMS_ALBITE).add(ModItems.albite.get());
        tag(GaiaTags.Items.GEMS_CARNELIAN).add(ModItems.carnelian.get());
        tag(GaiaTags.Items.GEMS_BENITOITE).add(ModItems.benitoite.get());
        tag(GaiaTags.Items.GEMS_DIOPSIDE).add(ModItems.diopside.get());
        tag(GaiaTags.Items.GEMS_GOSHENITE).add(ModItems.goshenite.get());
        tag(GaiaTags.Items.GEMS_PYRITE).add(ModItems.pyrite.get());
        tag(GaiaTags.Items.GEMS_TEKTITE).add(ModItems.tektite.get());
        tag(GaiaTags.Items.GEMS_GOLDSTONE).add(ModItems.goldstone.get());
        tag(GaiaTags.Items.GEMS_AURA).add(ModItems.aura_cluster.get());
        tag(GaiaTags.Items.GEMS_BISMUTH).add(ModItems.bismuth_crystal.get());
        tag(GaiaTags.Items.GEMS_OPALITE).add(ModItems.opalite.get());
        tag(GaiaTags.Items.GEMS_CELESTINE).add(ModItems.celestine.get());

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
        copy(GaiaTags.Blocks.ORES_OPALITE, GaiaTags.Items.ORES_OPALITE);
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
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_STIBNITE, GaiaTags.Items.STORAGE_BLOCKS_STIBNITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE, GaiaTags.Items.STORAGE_BLOCKS_PROUSTITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE, GaiaTags.Items.STORAGE_BLOCKS_EUCLASE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_ALBITE, GaiaTags.Items.STORAGE_BLOCKS_ALBITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Items.STORAGE_BLOCKS_CARNELIAN);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE, GaiaTags.Items.STORAGE_BLOCKS_BENITOITE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Items.STORAGE_BLOCKS_DIOPSIDE);
        copy(GaiaTags.Blocks.STORAGE_BLOCKS_GOSHENITE, GaiaTags.Items.STORAGE_BLOCKS_GOSHENITE);

        tag(Tags.Items.BONES).add(ModItems.shiny_bone.get());
        tag(Tags.Items.DUSTS).addTags(GaiaTags.Items.DUSTS_FINE, GaiaTags.Items.DUSTS_GOLDSTONE, GaiaTags.Items.DUSTS_HOT);
        for (TagKey<Item> tag : GEM_TAGS) {
            tag(Tags.Items.GEMS).addTag(tag);
        }
        tag(Tags.Items.INGOTS).addTag(GaiaTags.Items.INGOTS_SCAYNYX);
        tag(Tags.Items.RODS).addTag(GaiaTags.Items.RODS_AGATE);
        for (TagKey<Item> tag : ORE_TAGS) {
            tag(Tags.Items.ORES).addTag(tag);
        }
        for (TagKey<Item> tag : STORAGE_TAGS) {
            tag(Tags.Items.STORAGE_BLOCKS).addTag(tag);
        }
    }
}
