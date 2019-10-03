package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.*;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, GaiaDimensionMod.MODID);

    public static final RegistryObject<TileEntityType<GaiaStoneFurnaceTileEntity>> GAIA_STONE_FURNACE =  TILE_ENTITIES.register(
            "gaia_stone_furnace", () -> new TileEntityType<>(GaiaStoneFurnaceTileEntity::new, Sets.newHashSet(ModBlocks.gaia_stone_furnace.get()), null));
    public static final RegistryObject<TileEntityType<GeyserTileEntity>> GEYSER = TILE_ENTITIES.register(
            "geyser", () -> new TileEntityType<>(GeyserTileEntity::new, Sets.newHashSet(ModBlocks.geyser_block.get()), null));
    public static final RegistryObject<TileEntityType<LargeCrateTileEntity>> LARGE_CRATE = TILE_ENTITIES.register(
            "large_crate", () -> new TileEntityType<>(LargeCrateTileEntity::new, Sets.newHashSet(ModBlocks.mega_storage_crate.get()), null));
    public static final RegistryObject<TileEntityType<PurifierTileEntity>> PURIFIER = TILE_ENTITIES.register(
            "purifier", () -> new TileEntityType<>(PurifierTileEntity::new, Sets.newHashSet(ModBlocks.purifier.get()), null));
    public static final RegistryObject<TileEntityType<RestructurerTileEntity>> RESTRUCTURER = TILE_ENTITIES.register(
            "restructurer", () -> new TileEntityType<>(RestructurerTileEntity::new, Sets.newHashSet(ModBlocks.restructurer.get()), null));
    public static final RegistryObject<TileEntityType<SmallCrateTileEntity>> SMALL_CRATE = TILE_ENTITIES.register(
            "small_crate", () -> new TileEntityType<>(SmallCrateTileEntity::new, Sets.newHashSet(ModBlocks.crude_storage_crate.get()), null));
}
