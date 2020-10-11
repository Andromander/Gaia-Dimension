package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.*;
import androsa.gaiadimension.block.tileentity.boss.MalachiteGuardSpawnerTileEntity;
import com.google.common.collect.Sets;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = GaiaDimensionMod.MODID)
public class ModTileEntities {

    public static final TileEntityType<GaiaStoneFurnaceTileEntity> GAIA_STONE_FURNACE = RegistryHelper.registerTileEntity("gaia_stone_furnace", new GaiaStoneFurnaceTileEntity(), Sets.newHashSet(ModBlocks.gaia_stone_furnace));
    public static final TileEntityType<GeyserTileEntity> GEYSER = RegistryHelper.registerTileEntity("geyser", new GeyserTileEntity(), Sets.newHashSet(ModBlocks.geyser_block));
    public static final TileEntityType<LargeCrateTileEntity> LARGE_CRATE = RegistryHelper.registerTileEntity("large_crate", new LargeCrateTileEntity(), Sets.newHashSet(ModBlocks.mega_storage_crate));
    public static final TileEntityType<PurifierTileEntity> PURIFIER = RegistryHelper.registerTileEntity("purifier", new PurifierTileEntity(), Sets.newHashSet(ModBlocks.purifier));
    public static final TileEntityType<RestructurerTileEntity> RESTRUCTURER = RegistryHelper.registerTileEntity("restructurer", new RestructurerTileEntity(), Sets.newHashSet(ModBlocks.restructurer));
    public static final TileEntityType<SmallCrateTileEntity> SMALL_CRATE = RegistryHelper.registerTileEntity("small_crate", new SmallCrateTileEntity(), Sets.newHashSet(ModBlocks.crude_storage_crate));

    public static final TileEntityType<MalachiteGuardSpawnerTileEntity> MALACHITE_SPAWNER = RegistryHelper.registerTileEntity("malachite_spawner", new MalachiteGuardSpawnerTileEntity(), Sets.newHashSet(ModBlocks.malachite_guard_spawner));
}
