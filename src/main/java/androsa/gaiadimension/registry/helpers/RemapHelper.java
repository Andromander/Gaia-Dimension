package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.Map;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RemapHelper {
    private static final Map<String, String> BLOCK_MAPPINGS = ImmutableMap.<String, String>ofEntries(
            Map.entry("corrupt_soil", "corrupted_soil"),
            Map.entry("corrupt_grass", "corrupted_grass"),
            Map.entry("burnt_sapling", "burnt_agate_sapling"),
            Map.entry("burning_sapling", "fire_agate_sapling"),
            Map.entry("burnt_leaves", "burnt_agate_leaves"),
            Map.entry("burning_leaves", "fire_agate_leaves"),
            Map.entry("burnt_log", "burnt_agate_log"),
            Map.entry("burning_log", "fire_agate_log"),
            Map.entry("stripped_burnt_log", "stripped_burnt_agate_log"),
            Map.entry("stripped_burning_log", "stripped_fire_agate_log"),
            Map.entry("burnt_wood", "burnt_agate_wood"),
            Map.entry("burning_wood", "fire_agate_wood"),
            Map.entry("stripped_burnt_wood", "stripped_burnt_agate_wood"),
            Map.entry("stripped_burning_wood", "stripped_fire_agate_wood"),
            Map.entry("pink_agate_planks", "pink_agate_tiles"),
            Map.entry("blue_agate_planks", "blue_agate_tiles"),
            Map.entry("green_agate_planks", "green_agate_tiles"),
            Map.entry("purple_agate_planks", "purple_agate_tiles"),
            Map.entry("fossilized_planks", "fossilized_tiles"),
            Map.entry("corrupted_planks", "corrupted_tiles"),
            Map.entry("burnt_planks", "burnt_agate_tiles"),
            Map.entry("burning_planks", "fire_agate_tiles"),
            Map.entry("aura_planks", "aura_tiles"),
            Map.entry("pink_agate_plank_slab", "pink_agate_tile_slab"),
            Map.entry("blue_agate_plank_slab", "blue_agate_tile_slab"),
            Map.entry("green_agate_plank_slab", "green_agate_tile_slab"),
            Map.entry("purple_agate_plank_slab", "purple_agate_tile_slab"),
            Map.entry("fossilized_plank_slab", "fossilized_tile_slab"),
            Map.entry("corrupted_plank_slab", "corrupted_tile_slab"),
            Map.entry("burnt_plank_slab", "burnt_agate_tile_slab"),
            Map.entry("burning_plank_slab", "fire_agate_tile_slab"),
            Map.entry("aura_plank_slab", "aura_tile_slab"),
            Map.entry("pink_agate_plank_stairs", "pink_agate_tile_stairs"),
            Map.entry("blue_agate_plank_stairs", "blue_agate_tile_stairs"),
            Map.entry("green_agate_plank_stairs", "green_agate_tile_stairs"),
            Map.entry("purple_agate_plank_stairs", "purple_agate_tile_stairs"),
            Map.entry("fossilized_plank_stairs", "fossilized_tile_stairs"),
            Map.entry("corrupted_plank_stairs", "corrupted_tile_stairs"),
            Map.entry("burnt_plank_stairs", "burnt_agate_tile_stairs"),
            Map.entry("burning_plank_stairs", "fire_agate_tile_stairs"),
            Map.entry("aura_plank_stairs", "aura_tile_stairs"),
            Map.entry("opal_ore_red", "red_opal_ore"),
            Map.entry("opal_ore_blue", "blue_opal_ore"),
            Map.entry("opal_ore_green", "green_opal_ore"),
            Map.entry("opal_ore_white", "white_opal_ore"),
            Map.entry("opal_block_red", "red_opal_block"),
            Map.entry("opal_block_blue", "blue_opal_block"),
            Map.entry("opal_block_green", "green_opal_block"),
            Map.entry("opal_block_white", "white_opal_block"),
            Map.entry("malachite_floor_tiles", "malachite_tiles"),
            Map.entry("malachite_floor_slab", "malachite_tile_slab"),
            Map.entry("malachite_floor_stairs", "malachite_tile_stairs"),
            Map.entry("malachite_pulsing_floor_stairs", "malachite_pulsing_tile_stairs"),
            Map.entry("potted_burnt_sapling", "potted_burnt_agate_sapling"),
            Map.entry("potted_burning_sapling", "potted_fire_agate_sapling"),
            Map.entry("ixiolite_block", "stibnite_block"),
            Map.entry("leucite_block", "albite_block"),
            Map.entry("chalcedony_block", "goshenite_block"));

    @SubscribeEvent
    public static void remap(MissingMappingsEvent event) {
        if (event.getKey() == ForgeRegistries.Keys.BLOCKS) {
            for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, GaiaDimensionMod.MODID)) {
                ResourceLocation key = mapping.getKey();

                for (Map.Entry<String, String> entry : BLOCK_MAPPINGS.entrySet()) {
                    remapBlock(mapping, entry.getKey(), entry.getValue());
                }
            }
        }

        if (event.getKey() == ForgeRegistries.Keys.ITEMS) {
            for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, GaiaDimensionMod.MODID)) {
                ResourceLocation key = mapping.getKey();

                if (key.getNamespace().equals(GaiaDimensionMod.MODID)) {
                    //Remaps blocks
                    for (Map.Entry<String, String> entry : BLOCK_MAPPINGS.entrySet()) {
                        remapItem(mapping, entry.getKey(), entry.getValue());
                    }

                    remapItem(mapping, "ixiolite", "stibnite");
                    remapItem(mapping, "ixiolite_sword", "stibnite_sword");
                    remapItem(mapping, "ixiolite_pickaxe", "stibnite_pickaxe");
                    remapItem(mapping, "ixiolite_axe", "stibnite_axe");
                    remapItem(mapping, "ixiolite_shovel", "stibnite_shovel");
                    remapItem(mapping, "leucite", "albite");
                    remapItem(mapping, "leucite_helmet", "albite_helmet");
                    remapItem(mapping, "leucite_chestplate", "albite_chestplate");
                    remapItem(mapping, "leucite_legs", "albite_legs");
                    remapItem(mapping, "leucite_boots", "albite_boots");
                    remapItem(mapping, "chalcedony", "goshenite");
                    remapItem(mapping, "chalcedony_sword", "goshenite_sword");
                    remapItem(mapping, "chalcedony_pickaxe", "goshenite_pickaxe");
                    remapItem(mapping, "chalcedony_axe", "goshenite_axe");
                    remapItem(mapping, "chalcedony_shovel", "goshenite_shovel");
                    remapItem(mapping, "chalcedony_helmet", "goshenite_helmet");
                    remapItem(mapping, "chalcedony_chestplate", "goshenite_chestplate");
                    remapItem(mapping, "chalcedony_legs", "goshenite_legs");
                    remapItem(mapping, "chalcedony_boots", "goshenite_boots");
                }
            }
        }
    }

    private static void remapBlock(MissingMappingsEvent.Mapping<Block> mapping, String old, String path) {
        if (mapping.getKey().getPath().equals(old)) {
            ResourceLocation location = new ResourceLocation(GaiaDimensionMod.MODID, path);
            mapping.remap(ForgeRegistries.BLOCKS.getValue(location));
        }
    }

    private static void remapItem(MissingMappingsEvent.Mapping<Item> mapping, String old, String path) {
        if (mapping.getKey().getPath().equals(old)) {
            ResourceLocation location = new ResourceLocation(GaiaDimensionMod.MODID, path);
            mapping.remap(ForgeRegistries.ITEMS.getValue(location));
        }
    }
}
