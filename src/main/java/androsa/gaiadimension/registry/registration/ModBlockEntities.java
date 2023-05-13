package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.*;
import androsa.gaiadimension.block.blockentity.boss.MalachiteGuardSpawnerBlockEntity;
import com.google.common.collect.Sets;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<BlockEntityType<GaiaStoneFurnaceBlockEntity>> GAIA_STONE_FURNACE =  TILE_ENTITIES.register(
            "gaia_stone_furnace", () -> new BlockEntityType<>(GaiaStoneFurnaceBlockEntity::new, Sets.newHashSet(ModBlocks.gaia_stone_furnace.get()), null));
    public static final RegistryObject<BlockEntityType<GeyserBlockEntity>> GEYSER = TILE_ENTITIES.register(
            "geyser", () -> new BlockEntityType<>(GeyserBlockEntity::new, Sets.newHashSet(ModBlocks.geyser_block.get()), null));
    public static final RegistryObject<BlockEntityType<LargeCrateBlockEntity>> LARGE_CRATE = TILE_ENTITIES.register(
            "large_crate", () -> new BlockEntityType<>(LargeCrateBlockEntity::new, Sets.newHashSet(ModBlocks.mega_storage_crate.get()), null));
    public static final RegistryObject<BlockEntityType<PurifierBlockEntity>> PURIFIER = TILE_ENTITIES.register(
            "purifier", () -> new BlockEntityType<>(PurifierBlockEntity::new, Sets.newHashSet(ModBlocks.purifier.get()), null));
    public static final RegistryObject<BlockEntityType<RestructurerBlockEntity>> RESTRUCTURER = TILE_ENTITIES.register(
            "restructurer", () -> new BlockEntityType<>(RestructurerBlockEntity::new, Sets.newHashSet(ModBlocks.restructurer.get()), null));
    public static final RegistryObject<BlockEntityType<SmallCrateBlockEntity>> SMALL_CRATE = TILE_ENTITIES.register(
            "small_crate", () -> new BlockEntityType<>(SmallCrateBlockEntity::new, Sets.newHashSet(ModBlocks.crude_storage_crate.get()), null));

    public static final RegistryObject<BlockEntityType<MalachiteGuardSpawnerBlockEntity>> MALACHITE_SPAWNER = TILE_ENTITIES.register(
            "malachite_spawner", () -> new BlockEntityType<>(MalachiteGuardSpawnerBlockEntity::new, Sets.newHashSet(ModBlocks.malachite_guard_spawner.get()), null));
}
