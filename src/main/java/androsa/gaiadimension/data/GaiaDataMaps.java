package androsa.gaiadimension.data;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModDataMaps;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

public class GaiaDataMaps extends DataMapProvider {

    public GaiaDataMaps(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        Builder<Integer, Item> glitter = this.builder(ModDataMaps.GLITTERING_FUEL);
        glitter.add(Items.GOLD_NUGGET.builtInRegistryHolder(), 20, false);
        glitter.add(Items.GOLD_INGOT.builtInRegistryHolder(), 200, false);
        glitter.add(Items.GOLDEN_AXE.builtInRegistryHolder(), 150, false);
        glitter.add(Items.GOLDEN_HOE.builtInRegistryHolder(), 150, false);
        glitter.add(Items.GOLDEN_PICKAXE.builtInRegistryHolder(), 150, false);
        glitter.add(Items.GOLDEN_SHOVEL.builtInRegistryHolder(), 150, false);
        glitter.add(Items.GOLDEN_SWORD.builtInRegistryHolder(), 150, false);
        glitter.add(Items.GOLDEN_HELMET.builtInRegistryHolder(), 500, false);
        glitter.add(Items.GOLDEN_CHESTPLATE.builtInRegistryHolder(), 500, false);
        glitter.add(Items.GOLDEN_LEGGINGS.builtInRegistryHolder(), 500, false);
        glitter.add(Items.GOLDEN_BOOTS.builtInRegistryHolder(), 500, false);
        glitter.add(Items.GOLDEN_HORSE_ARMOR.builtInRegistryHolder(), 1000, false);
        glitter.add(Blocks.GOLD_BLOCK.asItem().builtInRegistryHolder(), 2000, false);
        glitter.add(Blocks.GOLD_ORE.asItem().builtInRegistryHolder(), 150, false);
        glitter.add(ModItems.pyrite, 500, false);
        glitter.add(ModBlocks.pyrite_block.asItem().builtInRegistryHolder(), 5000, false);
        glitter.add(ModItems.sweet_muckball, 250, false);
        glitter.add(ModBlocks.frail_glitter_block.asItem().builtInRegistryHolder(), 1000, false);
        glitter.add(ModBlocks.thick_glitter_block.asItem().builtInRegistryHolder(), 2000, false);
        glitter.add(ModBlocks.gummy_glitter_block.asItem().builtInRegistryHolder(), 4000, false);
        glitter.add(Items.BLAZE_POWDER.builtInRegistryHolder(), 1200, false);
        glitter.add(Items.BLAZE_ROD.builtInRegistryHolder(), 2400, false);

        Builder<Integer, Item> shine = this.builder(ModDataMaps.SHINING_FUEL);
        shine.add(ModItems.pink_essence, 100, false);
        shine.add(ModItems.pink_goo, 900, false);
        shine.add(ModBlocks.pink_sludge_block.asItem().builtInRegistryHolder(), 8100, false);
        shine.add(ModItems.aura_residue, 200, false);
        shine.add(ModItems.aura_cluster, 1800, false);
        shine.add(ModBlocks.aura_block.asItem().builtInRegistryHolder(), 16200, false);

        Builder<Integer, Item> nulling = this.builder(ModDataMaps.NULLING_FUEL);
        nulling.add(ModItems.bismuth_residue, 200, false);
        nulling.add(ModItems.bismuth_crystal, 1800, false);
        nulling.add(ModBlocks.bismuth_block.asItem().builtInRegistryHolder(), 16200, false);
        nulling.add(ModItems.black_residue, 100, false);
        nulling.add(ModItems.tektite, 900, false);
        nulling.add(ModBlocks.tektite_block.asItem().builtInRegistryHolder(), 8100, false);
    }
}
