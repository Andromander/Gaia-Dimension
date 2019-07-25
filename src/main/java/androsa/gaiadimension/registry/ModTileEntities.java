package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {
    public static final TileEntityType<GaiaStoneFurnaceTileEntity> GAIA_STONE_FURNACE = TileEntityType.Builder.create(GaiaStoneFurnaceTileEntity::new, ModBlocks.gaia_stone_furnace).build(null);
    public static final TileEntityType<GeyserTileEntity> GEYSER = TileEntityType.Builder.create(GeyserTileEntity::new, ModBlocks.geyser_block).build(null);
    public static final TileEntityType<LargeCrateTileEntity> LARGE_CRATE = TileEntityType.Builder.create(LargeCrateTileEntity::new, ModBlocks.mega_storage_crate).build(null);
    public static final TileEntityType<PurifierTileEntity> PURIFIER = TileEntityType.Builder.create(PurifierTileEntity::new, ModBlocks.purifier).build(null);
    public static final TileEntityType<RestructurerTileEntity> RESTRUCTURER = TileEntityType.Builder.create(RestructurerTileEntity::new, ModBlocks.restructurer).build(null);
    public static final TileEntityType<SmallCrateTileEntity> SMALL_CRATE = TileEntityType.Builder.create(SmallCrateTileEntity::new, ModBlocks.crude_storage_crate).build(null);

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> e) {
        final IForgeRegistry<TileEntityType<?>> registry = e.getRegistry();

        registry.register(GAIA_STONE_FURNACE.setRegistryName("gaia_stone_furnace"));
        registry.register(GEYSER.setRegistryName("geyser"));
        registry.register(LARGE_CRATE.setRegistryName("large_crate"));
        registry.register(PURIFIER.setRegistryName("purifier"));
        registry.register(RESTRUCTURER.setRegistryName("restructurer"));
        registry.register(SMALL_CRATE.setRegistryName("small_crate"));
    }
}
