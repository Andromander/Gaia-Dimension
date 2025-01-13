package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.GaiaBlockStates;
import androsa.gaiadimension.data.GaiaItemModels;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModItems;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.stream.Stream;

public class GaiaModelProvider extends ModelProvider {

    public static final Set<Block> IGNORED_STATES = ImmutableSet.of(
            ModBlocks.gaia_portal.get(),
            ModBlocks.mineral_water.get(),
            ModBlocks.superhot_magma.get(),
            ModBlocks.sweet_muck.get(),
            ModBlocks.liquid_bismuth.get(),
            ModBlocks.liquid_aura.get(),
            ModBlocks.tall_golden_grass.get(),
            ModBlocks.glamelea.get(),
            ModBlocks.golden_vine.get(),
            ModBlocks.sombre_cacti.get(),
            ModBlocks.aura_shoot.get(),
            ModBlocks.malachite_guard_spawner.get());
    public static final Set<Item> IGNORED_ITEMS = ImmutableSet.of(
            ModBlocks.tall_golden_grass.asItem(),
            ModBlocks.glamelea.asItem(),
            ModBlocks.golden_vine.asItem(),
            ModBlocks.aura_shoot.asItem());

    public GaiaModelProvider(PackOutput output) {
        super(output, GaiaDimensionMod.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        new GaiaBlockStates(blockModels).runBlockModels();
        new GaiaItemModels(itemModels).runItemModels();
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return super.getKnownBlocks().filter(h -> !IGNORED_STATES.contains(h.value()));
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return super.getKnownItems().filter(h -> !IGNORED_ITEMS.contains(h.value()));
    }
}
