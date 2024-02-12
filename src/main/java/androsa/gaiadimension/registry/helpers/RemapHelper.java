package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RemapHelper {
    public static void remapEntries() {
        DeferredRegister.Blocks blockreg = ModBlocks.BLOCKS;
        DeferredRegister.Items itemreg = ModItems.ITEMS;

        remapBlock(blockreg, itemreg, "corrupt_soil", "corrupted_soil");
        remapBlock(blockreg, itemreg, "corrupt_grass", "corrupted_grass");
        remapBlock(blockreg, itemreg, "burnt_sapling", "burnt_agate_sapling");
        remapBlock(blockreg, itemreg, "burning_sapling", "fire_agate_sapling");
        remapBlock(blockreg, itemreg, "burnt_leaves", "burnt_agate_leaves");
        remapBlock(blockreg, itemreg, "burning_leaves", "fire_agate_leaves");
        remapBlock(blockreg, itemreg, "burnt_log", "burnt_agate_log");
        remapBlock(blockreg, itemreg, "burning_log", "fire_agate_log");
        remapBlock(blockreg, itemreg, "stripped_burnt_log", "stripped_burnt_agate_log");
        remapBlock(blockreg, itemreg, "stripped_burning_log", "stripped_fire_agate_log");
        remapBlock(blockreg, itemreg, "burnt_wood", "burnt_agate_wood");
        remapBlock(blockreg, itemreg, "burning_wood", "fire_agate_wood");
        remapBlock(blockreg, itemreg, "stripped_burnt_wood", "stripped_burnt_agate_wood");
        remapBlock(blockreg, itemreg, "stripped_burning_wood", "stripped_fire_agate_wood");
        remapBlock(blockreg, itemreg, "pink_agate_planks", "pink_agate_tiles");
        remapBlock(blockreg, itemreg, "blue_agate_planks", "blue_agate_tiles");
        remapBlock(blockreg, itemreg, "green_agate_planks", "green_agate_tiles");
        remapBlock(blockreg, itemreg, "purple_agate_planks", "purple_agate_tiles");
        remapBlock(blockreg, itemreg, "fossilized_planks", "fossilized_tiles");
        remapBlock(blockreg, itemreg, "corrupted_planks", "corrupted_tiles");
        remapBlock(blockreg, itemreg, "burnt_planks", "burnt_agate_tiles");
        remapBlock(blockreg, itemreg, "burning_planks", "fire_agate_tiles");
        remapBlock(blockreg, itemreg, "aura_planks", "aura_tiles");
        remapBlock(blockreg, itemreg, "pink_agate_plank_slab", "pink_agate_tile_slab");
        remapBlock(blockreg, itemreg, "blue_agate_plank_slab", "blue_agate_tile_slab");
        remapBlock(blockreg, itemreg, "green_agate_plank_slab", "green_agate_tile_slab");
        remapBlock(blockreg, itemreg, "purple_agate_plank_slab", "purple_agate_tile_slab");
        remapBlock(blockreg, itemreg, "fossilized_plank_slab", "fossilized_tile_slab");
        remapBlock(blockreg, itemreg, "corrupted_plank_slab", "corrupted_tile_slab");
        remapBlock(blockreg, itemreg, "burnt_plank_slab", "burnt_agate_tile_slab");
        remapBlock(blockreg, itemreg, "burning_plank_slab", "fire_agate_tile_slab");
        remapBlock(blockreg, itemreg, "aura_plank_slab", "aura_tile_slab");
        remapBlock(blockreg, itemreg, "pink_agate_plank_stairs", "pink_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "blue_agate_plank_stairs", "blue_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "green_agate_plank_stairs", "green_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "purple_agate_plank_stairs", "purple_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "fossilized_plank_stairs", "fossilized_tile_stairs");
        remapBlock(blockreg, itemreg, "corrupted_plank_stairs", "corrupted_tile_stairs");
        remapBlock(blockreg, itemreg, "burnt_plank_stairs", "burnt_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "burning_plank_stairs", "fire_agate_tile_stairs");
        remapBlock(blockreg, itemreg, "aura_plank_stairs", "aura_tile_stairs");
        remapBlock(blockreg, itemreg, "opal_ore_red", "red_opal_ore");
        remapBlock(blockreg, itemreg, "opal_ore_blue", "blue_opal_ore");
        remapBlock(blockreg, itemreg, "opal_ore_green", "green_opal_ore");
        remapBlock(blockreg, itemreg, "opal_ore_white", "white_opal_ore");
        remapBlock(blockreg, itemreg, "opal_block_red", "red_opal_block");
        remapBlock(blockreg, itemreg, "opal_block_blue", "blue_opal_block");
        remapBlock(blockreg, itemreg, "opal_block_green", "green_opal_block");
        remapBlock(blockreg, itemreg, "opal_block_white", "white_opal_block");
        remapBlock(blockreg, itemreg, "malachite_floor_tiles", "malachite_tiles");
        remapBlock(blockreg, itemreg, "malachite_floor_slab", "malachite_tile_slab");
        remapBlock(blockreg, itemreg, "malachite_floor_stairs", "malachite_tile_stairs");
        remapBlock(blockreg, itemreg, "malachite_pulsing_floor_stairs", "malachite_pulsing_tile_stairs");
        remapBlock(blockreg, itemreg, "potted_burnt_sapling", "potted_burnt_agate_sapling");
        remapBlock(blockreg, itemreg, "potted_burning_sapling", "potted_fire_agate_sapling");
        remapBlock(blockreg, itemreg, "ixiolite_block", "stibnite_block");
        remapBlock(blockreg, itemreg, "leucite_block", "albite_block");
        remapBlock(blockreg, itemreg, "chalcedony_block", "goshenite_block");

        remapItem(itemreg, "ixiolite", "stibnite");
        remapItem(itemreg, "ixiolite_sword", "stibnite_sword");
        remapItem(itemreg, "ixiolite_pickaxe", "stibnite_pickaxe");
        remapItem(itemreg, "ixiolite_axe", "stibnite_axe");
        remapItem(itemreg, "ixiolite_shovel", "stibnite_shovel");
        remapItem(itemreg, "leucite", "albite");
        remapItem(itemreg, "leucite_helmet", "albite_helmet");
        remapItem(itemreg, "leucite_chestplate", "albite_chestplate");
        remapItem(itemreg, "leucite_legs", "albite_legs");
        remapItem(itemreg, "leucite_boots", "albite_boots");
        remapItem(itemreg, "chalcedony", "goshenite");
        remapItem(itemreg, "chalcedony_sword", "goshenite_sword");
        remapItem(itemreg, "chalcedony_pickaxe", "goshenite_pickaxe");
        remapItem(itemreg, "chalcedony_axe", "goshenite_axe");
        remapItem(itemreg, "chalcedony_shovel", "goshenite_shovel");
        remapItem(itemreg, "chalcedony_helmet", "goshenite_helmet");
        remapItem(itemreg, "chalcedony_chestplate", "goshenite_chestplate");
        remapItem(itemreg, "chalcedony_legs", "goshenite_legs");
        remapItem(itemreg, "chalcedony_boots", "goshenite_boots");
    }

    private static void remapBlock(DeferredRegister.Blocks blockreg, DeferredRegister.Items itemreg, String old, String path) {
        blockreg.addAlias(new ResourceLocation(GaiaDimensionMod.MODID, old), new ResourceLocation(GaiaDimensionMod.MODID, path));
        remapItem(itemreg, old, path);
    }

    private static void remapItem(DeferredRegister.Items registry, String old, String path) {
        registry.addAlias(new ResourceLocation(GaiaDimensionMod.MODID, old), new ResourceLocation(GaiaDimensionMod.MODID, path));
    }
}
