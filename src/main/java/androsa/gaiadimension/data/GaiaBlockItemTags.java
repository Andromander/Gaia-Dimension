package androsa.gaiadimension.data;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Supplier;

public abstract class GaiaBlockItemTags {
    public void run() {
        tag(BlockTags.LEAVES, ItemTags.LEAVES).add(array(GaiaBlockTags.LEAVES));
        tag(BlockTags.LOGS, ItemTags.LOGS)
                .addTag(GaiaTags.Blocks.PINK_AGATE_LOGS)
                .addTag(GaiaTags.Blocks.BLUE_AGATE_LOGS)
                .addTag(GaiaTags.Blocks.GREEN_AGATE_LOGS)
                .addTag(GaiaTags.Blocks.PURPLE_AGATE_LOGS)
                .addTag(GaiaTags.Blocks.FOSSILIZED_LOGS)
                .addTag(GaiaTags.Blocks.CORRUPTED_LOGS)
                .addTag(GaiaTags.Blocks.BURNT_LOGS)
                .addTag(GaiaTags.Blocks.BURNING_LOGS)
                .addTag(GaiaTags.Blocks.AURA_LOGS)
                .addTag(GaiaTags.Blocks.GOLDEN_LOGS);
        tag(BlockTags.SAPLINGS, ItemTags.SAPLINGS).add(array(GaiaBlockTags.SAPLINGS));
        tag(BlockTags.SLABS, ItemTags.SLABS).add(array(GaiaBlockTags.SLABS));
        tag(BlockTags.STAIRS, ItemTags.STAIRS).add(array(GaiaBlockTags.STAIRS));

        tag(GaiaTags.Blocks.MOOKAITE, GaiaTags.Items.MOOKAITE)
                .add(ModBlocks.scarlet_mookaite.get(), ModBlocks.auburn_mookaite.get(), ModBlocks.gold_mookaite.get(), ModBlocks.mauve_mookaite.get(), ModBlocks.beige_mookaite.get(), ModBlocks.ivory_mookaite.get());
        tag(GaiaTags.Blocks.TILES, GaiaTags.Items.TILES).add(array(GaiaBlockTags.TILES));
        tag(GaiaTags.Blocks.PINK_AGATE_LOGS, GaiaTags.Items.PINK_AGATE_LOGS).add(array(GaiaBlockTags.PINK_AGATE_LOGS));
        tag(GaiaTags.Blocks.BLUE_AGATE_LOGS, GaiaTags.Items.BLUE_AGATE_LOGS).add(array(GaiaBlockTags.BLUE_AGATE_LOGS));
        tag(GaiaTags.Blocks.GREEN_AGATE_LOGS, GaiaTags.Items.GREEN_AGATE_LOGS).add(array(GaiaBlockTags.GREEN_AGATE_LOGS));
        tag(GaiaTags.Blocks.PURPLE_AGATE_LOGS, GaiaTags.Items.PURPLE_AGATE_LOGS).add(array(GaiaBlockTags.PURPLE_AGATE_LOGS));
        tag(GaiaTags.Blocks.FOSSILIZED_LOGS, GaiaTags.Items.FOSSILIZED_LOGS).add(array(GaiaBlockTags.FOSSILIZED_LOGS));
        tag(GaiaTags.Blocks.CORRUPTED_LOGS, GaiaTags.Items.CORRUPTED_LOGS).add(array(GaiaBlockTags.CORRUPTED_LOGS));
        tag(GaiaTags.Blocks.BURNT_LOGS, GaiaTags.Items.BURNT_LOGS).add(array(GaiaBlockTags.BURNT_LOGS));
        tag(GaiaTags.Blocks.BURNING_LOGS, GaiaTags.Items.BURNING_LOGS).add(array(GaiaBlockTags.BURNING_LOGS));
        tag(GaiaTags.Blocks.AURA_LOGS, GaiaTags.Items.AURA_LOGS).add(array(GaiaBlockTags.AURA_LOGS));
        tag(GaiaTags.Blocks.GOLDEN_LOGS, GaiaTags.Items.GOLDEN_LOGS).add(array(GaiaBlockTags.GOLDEN_LOGS));
        tag(GaiaTags.Blocks.GAIA_BRICKS, GaiaTags.Items.GAIA_BRICKS).add(array(GaiaBlockTags.GAIA_BRICKS));
        tag(GaiaTags.Blocks.AMETHYST_BRICKS, GaiaTags.Items.AMETHYST_BRICKS).add(array(GaiaBlockTags.AMETHYST_BRICKS));
        tag(GaiaTags.Blocks.COPAL_BRICKS, GaiaTags.Items.COPAL_BRICKS).add(array(GaiaBlockTags.COPAL_BRICKS));
        tag(GaiaTags.Blocks.JADE_BRICKS, GaiaTags.Items.JADE_BRICKS).add(array(GaiaBlockTags.JADE_BRICKS));
        tag(GaiaTags.Blocks.JET_BRICKS, GaiaTags.Items.JET_BRICKS).add(array(GaiaBlockTags.JET_BRICKS));

        tag(GaiaTags.Blocks.ORES_SUGILITE, GaiaTags.Items.ORES_SUGILITE).add(ModBlocks.sugilite_ore.get());
        tag(GaiaTags.Blocks.ORES_HEMATITE, GaiaTags.Items.ORES_HEMATITE).add(ModBlocks.hematite_ore.get());
        tag(GaiaTags.Blocks.ORES_CINNABAR, GaiaTags.Items.ORES_CINNABAR).add(ModBlocks.cinnabar_ore.get());
        tag(GaiaTags.Blocks.ORES_LABRADORITE, GaiaTags.Items.ORES_LABRADORITE).add(ModBlocks.labradorite_ore.get());
        tag(GaiaTags.Blocks.ORES_MOONSTONE, GaiaTags.Items.ORES_MOONSTONE).add(ModBlocks.moonstone_ore.get());
        tag(GaiaTags.Blocks.ORES_RED_OPAL, GaiaTags.Items.ORES_RED_OPAL).add(ModBlocks.red_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_BLUE_OPAL, GaiaTags.Items.ORES_BLUE_OPAL).add(ModBlocks.blue_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_GREEN_OPAL, GaiaTags.Items.ORES_GREEN_OPAL).add(ModBlocks.green_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_WHITE_OPAL, GaiaTags.Items.ORES_WHITE_OPAL).add(ModBlocks.white_opal_ore.get());
        tag(GaiaTags.Blocks.ORES_PYRITE, GaiaTags.Items.ORES_PYRITE).add(ModBlocks.pyrite_ore.get());
        tag(GaiaTags.Blocks.ORES_OPALITE, GaiaTags.Items.ORES_OPALITE)
                .add(ModBlocks.scarlet_opalite_ore.get(), ModBlocks.auburn_opalite_ore.get(), ModBlocks.gold_opalite_ore.get(), ModBlocks.mauve_opalite_ore.get(), ModBlocks.beige_opalite_ore.get(), ModBlocks.ivory_opalite_ore.get());
        tag(GaiaTags.Blocks.ORES_CELESTINE, GaiaTags.Items.ORES_CELESTINE).add(ModBlocks.celestine_ore.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_SCAYNYX, GaiaTags.Items.STORAGE_BLOCKS_SCAYNYX).add(ModBlocks.scaynyx_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_SUGILITE, GaiaTags.Items.STORAGE_BLOCKS_SUGILITE).add(ModBlocks.sugilite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_HEMATITE, GaiaTags.Items.STORAGE_BLOCKS_HEMATITE).add(ModBlocks.hematite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CINNABAR, GaiaTags.Items.STORAGE_BLOCKS_CINNABAR).add(ModBlocks.cinnabar_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_LABRADORITE, GaiaTags.Items.STORAGE_BLOCKS_LABRADORITE).add(ModBlocks.labradorite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_MOONSTONE, GaiaTags.Items.STORAGE_BLOCKS_MOONSTONE).add(ModBlocks.moonstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_RED_OPAL, GaiaTags.Items.STORAGE_BLOCKS_RED_OPAL).add(ModBlocks.red_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BLUE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_BLUE_OPAL).add(ModBlocks.blue_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GREEN_OPAL, GaiaTags.Items.STORAGE_BLOCKS_GREEN_OPAL).add(ModBlocks.green_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_WHITE_OPAL, GaiaTags.Items.STORAGE_BLOCKS_WHITE_OPAL).add(ModBlocks.white_opal_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PYRITE, GaiaTags.Items.STORAGE_BLOCKS_PYRITE).add(ModBlocks.pyrite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_TEKTITE, GaiaTags.Items.STORAGE_BLOCKS_TEKTITE).add(ModBlocks.tektite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GOLDSTONE, GaiaTags.Items.STORAGE_BLOCKS_GOLDSTONE).add(ModBlocks.goldstone_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_AURA_CRYSTAL, GaiaTags.Items.STORAGE_BLOCKS_AURA_CRYSTAL).add(ModBlocks.aura_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BISMUTH, GaiaTags.Items.STORAGE_BLOCKS_BISMUTH).add(ModBlocks.bismuth_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_OPALITE, GaiaTags.Items.STORAGE_BLOCKS_OPALITE).add(ModBlocks.opalite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_STIBNITE, GaiaTags.Items.STORAGE_BLOCKS_STIBNITE).add(ModBlocks.stibnite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_PROUSTITE, GaiaTags.Items.STORAGE_BLOCKS_PROUSTITE).add(ModBlocks.proustite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_EUCLASE, GaiaTags.Items.STORAGE_BLOCKS_EUCLASE).add(ModBlocks.euclase_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_ALBITE, GaiaTags.Items.STORAGE_BLOCKS_ALBITE).add(ModBlocks.albite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CARNELIAN, GaiaTags.Items.STORAGE_BLOCKS_CARNELIAN).add(ModBlocks.carnelian_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_BENITOITE, GaiaTags.Items.STORAGE_BLOCKS_BENITOITE).add(ModBlocks.benitoite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_DIOPSIDE, GaiaTags.Items.STORAGE_BLOCKS_DIOPSIDE).add(ModBlocks.diopside_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_GOSHENITE, GaiaTags.Items.STORAGE_BLOCKS_GOSHENITE).add(ModBlocks.goshenite_block.get());
        tag(GaiaTags.Blocks.STORAGE_BLOCKS_CELESTINE, GaiaTags.Items.STORAGE_BLOCKS_CELESTINE).add(ModBlocks.celestine_block.get());
    }

    protected abstract TagAppender<Block, Block> tag(TagKey<Block> blocktag, TagKey<Item> itemtag);

    private Block[] array(ImmutableList<Supplier<? extends Block>> list) {
        List<Block> blocks = Lists.newArrayList();

        for (Supplier<? extends Block> block : list) {
            blocks.add(block.get());
        }

        return blocks.toArray(new Block[0]);
    }
}
