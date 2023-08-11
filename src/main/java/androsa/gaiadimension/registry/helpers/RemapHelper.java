package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Maps;
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

    @SubscribeEvent
    public static void remap(MissingMappingsEvent event) {
        Map<String, String> blockMappings = Maps.newHashMap();

        if (event.getKey() == ForgeRegistries.Keys.BLOCKS) {
            for (MissingMappingsEvent.Mapping<Block> mapping : event.getAllMappings(ForgeRegistries.Keys.BLOCKS)) {
                ResourceLocation key = mapping.getKey();

                if (key.getNamespace().equals(GaiaDimensionMod.MODID)) {
                    remapBlock(mapping, blockMappings, "corrupt_soil", "corrupted_soil");
                    remapBlock(mapping, blockMappings, "corrupt_grass", "corrupted_grass");
                    remapBlock(mapping, blockMappings, "burnt_sapling", "burnt_agate_sapling");
                    remapBlock(mapping, blockMappings, "burning_sapling", "fire_agate_sapling");
                    remapBlock(mapping, blockMappings, "burnt_leaves", "burnt_agate_leaves");
                    remapBlock(mapping, blockMappings, "burning_leaves", "fire_agate_leaves");
                    remapBlock(mapping, blockMappings, "burnt_log", "burnt_agate_log");
                    remapBlock(mapping, blockMappings, "burning_log", "fire_agate_log");
                    remapBlock(mapping, blockMappings, "stripped_burnt_log", "stripped_burnt_agate_log");
                    remapBlock(mapping, blockMappings, "stripped_burning_log", "stripped_fire_agate_log");
                    remapBlock(mapping, blockMappings, "burnt_wood", "burnt_agate_wood");
                    remapBlock(mapping, blockMappings, "burning_wood", "fire_agate_wood");
                    remapBlock(mapping, blockMappings, "stripped_burnt_wood", "stripped_burnt_agate_wood");
                    remapBlock(mapping, blockMappings, "stripped_burning_wood", "stripped_fire_agate_wood");
                    remapBlock(mapping, blockMappings, "pink_agate_planks", "pink_agate_tiles");
                    remapBlock(mapping, blockMappings, "blue_agate_planks", "blue_agate_tiles");
                    remapBlock(mapping, blockMappings, "green_agate_planks", "green_agate_tiles");
                    remapBlock(mapping, blockMappings, "purple_agate_planks", "purple_agate_tiles");
                    remapBlock(mapping, blockMappings, "fossilized_planks", "fossilized_tiles");
                    remapBlock(mapping, blockMappings, "corrupted_planks", "corrupted_tiles");
                    remapBlock(mapping, blockMappings, "burnt_planks", "burnt_agate_tiles");
                    remapBlock(mapping, blockMappings, "burning_planks", "fire_agate_tiles");
                    remapBlock(mapping, blockMappings, "aura_planks", "aura_tiles");
                    remapBlock(mapping, blockMappings, "pink_agate_plank_slab", "pink_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "blue_agate_plank_slab", "blue_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "green_agate_plank_slab", "green_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "purple_agate_plank_slab", "purple_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "fossilized_plank_slab", "fossilized_tile_slab");
                    remapBlock(mapping, blockMappings, "corrupted_plank_slab", "corrupted_tile_slab");
                    remapBlock(mapping, blockMappings, "burnt_plank_slab", "burnt_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "burning_plank_slab", "fire_agate_tile_slab");
                    remapBlock(mapping, blockMappings, "aura_plank_slab", "aura_tile_slab");
                    remapBlock(mapping, blockMappings, "pink_agate_plank_stairs", "pink_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "blue_agate_plank_stairs", "blue_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "green_agate_plank_stairs", "green_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "purple_agate_plank_stairs", "purple_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "fossilized_plank_stairs", "fossilized_tile_stairs");
                    remapBlock(mapping, blockMappings, "corrupted_plank_stairs", "corrupted_tile_stairs");
                    remapBlock(mapping, blockMappings, "burnt_plank_stairs", "burnt_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "burning_plank_stairs", "fire_agate_tile_stairs");
                    remapBlock(mapping, blockMappings, "aura_plank_stairs", "aura_tile_stairs");
                    remapBlock(mapping, blockMappings, "opal_ore_red", "red_opal_ore");
                    remapBlock(mapping, blockMappings, "opal_ore_blue", "blue_opal_ore");
                    remapBlock(mapping, blockMappings, "opal_ore_green", "green_opal_ore");
                    remapBlock(mapping, blockMappings, "opal_ore_white", "white_opal_ore");
                    remapBlock(mapping, blockMappings, "opal_block_red", "red_opal_block");
                    remapBlock(mapping, blockMappings, "opal_block_blue", "blue_opal_block");
                    remapBlock(mapping, blockMappings, "opal_block_green", "green_opal_block");
                    remapBlock(mapping, blockMappings, "opal_block_white", "white_opal_block");
                    remapBlock(mapping, blockMappings, "malachite_floor_tiles", "malachite_tiles");
                    remapBlock(mapping, blockMappings, "malachite_floor_slab", "malachite_tile_slab");
                    remapBlock(mapping, blockMappings, "malachite_floor_stairs", "malachite_tile_stairs");
                    remapBlock(mapping, blockMappings, "malachite_pulsing_floor_stairs", "malachite_pulsing_tile_stairs");
                    remapBlock(mapping, blockMappings, "potted_burnt_sapling", "potted_burnt_agate_sapling");
                    remapBlock(mapping, blockMappings, "potted_burning_sapling", "potted_fire_agate_sapling");
                    remapBlock(mapping, blockMappings, "ixiolite_block", "stibnite_block");
                }
            }
        }

        if (event.getKey() == ForgeRegistries.Keys.ITEMS) {
            for (MissingMappingsEvent.Mapping<Item> mapping : event.getAllMappings(ForgeRegistries.Keys.ITEMS)) {
                ResourceLocation key = mapping.getKey();

                if (key.getNamespace().equals(GaiaDimensionMod.MODID)) {
                    //Remaps blocks
                    for (Map.Entry<String, String> entry : blockMappings.entrySet()) {
                        remapItem(mapping, entry.getKey(), entry.getValue());
                    }

                    remapItem(mapping, "ixiolite", "stibnite");
                    remapItem(mapping, "ixiolite_sword", "stibnite_sword");
                    remapItem(mapping, "ixiolite_pickaxe", "stibnite_pickaxe");
                    remapItem(mapping, "ixiolite_axe", "stibnite_axe");
                    remapItem(mapping, "ixiolite_shovel", "stibnite_shovel");
                }
            }
        }
    }

    private static void remapBlock(MissingMappingsEvent.Mapping<Block> mapping, Map<String, String> map, String old, String path) {
        if (mapping.getKey().getPath().equals(old)) {
            ResourceLocation location = new ResourceLocation(GaiaDimensionMod.MODID, path);
            mapping.remap(ForgeRegistries.BLOCKS.getValue(location));
            map.put(old, path);
        }
    }

    private static void remapItem(MissingMappingsEvent.Mapping<Item> mapping, String old, String path) {
        if (mapping.getKey().getPath().equals(old)) {
            ResourceLocation location = new ResourceLocation(GaiaDimensionMod.MODID, path);
            mapping.remap(ForgeRegistries.ITEMS.getValue(location));
        }
    }
}
