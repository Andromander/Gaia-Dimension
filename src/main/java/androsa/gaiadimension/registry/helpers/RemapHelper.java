package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RemapHelper {

    @SubscribeEvent
    public static void remap(MissingMappingsEvent event) {
        if (event.getKey() == ForgeRegistries.Keys.BLOCKS) {
            for (MissingMappingsEvent.Mapping<Block> mapping : event.getAllMappings(ForgeRegistries.Keys.BLOCKS)) {
                ResourceLocation key = mapping.getKey();

                if (key.getNamespace().equals(GaiaDimensionMod.MODID)) {
                    remapBlock(mapping, "corrupt_soil", "corrupted_soil");
                    remapBlock(mapping, "corrupt_grass", "corrupted_grass");
                    remapBlock(mapping, "burnt_sapling", "burnt_agate_sapling");
                    remapBlock(mapping, "burning_sapling", "fire_agate_sapling");
                    remapBlock(mapping, "burnt_leaves", "burnt_agate_leaves");
                    remapBlock(mapping, "burning_leaves", "fire_agate_leaves");
                    remapBlock(mapping, "burnt_log", "burnt_agate_log");
                    remapBlock(mapping, "burning_log", "fire_agate_log");
                    remapBlock(mapping, "stripped_burnt_log", "stripped_burnt_agate_log");
                    remapBlock(mapping, "stripped_burning_log", "stripped_fire_agate_log");
                    remapBlock(mapping, "burnt_wood", "burnt_agate_wood");
                    remapBlock(mapping, "burning_wood", "fire_agate_wood");
                    remapBlock(mapping, "stripped_burnt_wood", "stripped_burnt_agate_wood");
                    remapBlock(mapping, "stripped_burning_wood", "stripped_fire_agate_wood");
                    remapBlock(mapping, "pink_agate_planks", "pink_agate_tiles");
                    remapBlock(mapping, "blue_agate_planks", "blue_agate_tiles");
                    remapBlock(mapping, "green_agate_planks", "green_agate_tiles");
                    remapBlock(mapping, "purple_agate_planks", "purple_agate_tiles");
                    remapBlock(mapping, "fossilized_planks", "fossilized_tiles");
                    remapBlock(mapping, "corrupted_planks", "corrupted_tiles");
                    remapBlock(mapping, "burnt_planks", "burnt_agate_tiles");
                    remapBlock(mapping, "burning_planks", "fire_agate_tiles");
                    remapBlock(mapping, "aura_planks", "aura_tiles");
                    remapBlock(mapping, "pink_agate_plank_slab", "pink_agate_tile_slab");
                    remapBlock(mapping, "blue_agate_plank_slab", "blue_agate_tile_slab");
                    remapBlock(mapping, "green_agate_plank_slab", "green_agate_tile_slab");
                    remapBlock(mapping, "purple_agate_plank_slab", "purple_agate_tile_slab");
                    remapBlock(mapping, "fossilized_plank_slab", "fossilized_tile_slab");
                    remapBlock(mapping, "corrupted_plank_slab", "corrupted_tile_slab");
                    remapBlock(mapping, "burnt_plank_slab", "burnt_agate_tile_slab");
                    remapBlock(mapping, "burning_plank_slab", "fire_agate_tile_slab");
                    remapBlock(mapping, "aura_plank_slab", "aura_tile_slab");
                    remapBlock(mapping, "pink_agate_plank_stairs", "pink_agate_tile_stairs");
                    remapBlock(mapping, "blue_agate_plank_stairs", "blue_agate_tile_stairs");
                    remapBlock(mapping, "green_agate_plank_stairs", "green_agate_tile_stairs");
                    remapBlock(mapping, "purple_agate_plank_stairs", "purple_agate_tile_stairs");
                    remapBlock(mapping, "fossilized_plank_stairs", "fossilized_tile_stairs");
                    remapBlock(mapping, "corrupted_plank_stairs", "corrupted_tile_stairs");
                    remapBlock(mapping, "burnt_plank_stairs", "burnt_agate_tile_stairs");
                    remapBlock(mapping, "burning_plank_stairs", "fire_agate_tile_stairs");
                    remapBlock(mapping, "aura_plank_stairs", "aura_tile_stairs");
                    remapBlock(mapping, "opal_ore_red", "red_opal_ore");
                    remapBlock(mapping, "opal_ore_blue", "blue_opal_ore");
                    remapBlock(mapping, "opal_ore_green", "green_opal_ore");
                    remapBlock(mapping, "opal_ore_white", "white_opal_ore");
                    remapBlock(mapping, "opal_block_red", "red_opal_block");
                    remapBlock(mapping, "opal_block_blue", "blue_opal_block");
                    remapBlock(mapping, "opal_block_green", "green_opal_block");
                    remapBlock(mapping, "opal_block_white", "white_opal_block");
                    remapBlock(mapping, "malachite_floor_tiles", "malachite_tiles");
                    remapBlock(mapping, "malachite_floor_slab", "malachite_tile_slab");
                    remapBlock(mapping, "malachite_floor_stairs", "malachite_tile_stairs");
                    remapBlock(mapping, "malachite_pulsing_floor_stairs", "malachite_pulsing_tile_stairs");
                    remapBlock(mapping, "potted_burnt_sapling", "potted_burnt_agate_sapling");
                    remapBlock(mapping, "potted_burning_sapling", "potted_fire_agate_sapling");
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
}
