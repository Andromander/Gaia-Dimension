package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.*;
import androsa.gaiadimension.block.blockentity.boss.MalachiteGuardSpawnerBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GaiaDimensionMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GaiaStoneFurnaceBlockEntity>> GAIA_STONE_FURNACE = TILE_ENTITIES.register(
            "gaia_stone_furnace", () -> new BlockEntityType<>(GaiaStoneFurnaceBlockEntity::new, ModBlocks.gaia_stone_furnace.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeyserBlockEntity>> GEYSER = TILE_ENTITIES.register(
            "geyser", () -> new BlockEntityType<>(GeyserBlockEntity::new, ModBlocks.geyser_block.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LargeCrateBlockEntity>> LARGE_CRATE = TILE_ENTITIES.register(
            "large_crate", () -> new BlockEntityType<>(LargeCrateBlockEntity::new, ModBlocks.mega_storage_crate.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PurifierBlockEntity>> PURIFIER = TILE_ENTITIES.register(
            "purifier", () -> new BlockEntityType<>(PurifierBlockEntity::new, ModBlocks.purifier.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RestructurerBlockEntity>> RESTRUCTURER = TILE_ENTITIES.register(
            "restructurer", () -> new BlockEntityType<>(RestructurerBlockEntity::new, ModBlocks.restructurer.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmallCrateBlockEntity>> SMALL_CRATE = TILE_ENTITIES.register(
            "small_crate", () -> new BlockEntityType<>(SmallCrateBlockEntity::new, ModBlocks.crude_storage_crate.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MalachiteGuardSpawnerBlockEntity>> MALACHITE_SPAWNER = TILE_ENTITIES.register(
            "malachite_spawner", () -> new BlockEntityType<>(MalachiteGuardSpawnerBlockEntity::new, ModBlocks.malachite_guard_spawner.get()));

    @SubscribeEvent
    public static void registerHandlers(RegisterCapabilitiesEvent e) {
        e.registerBlockEntity(Capabilities.Item.BLOCK, GAIA_STONE_FURNACE.get(), WorldlyContainerWrapper::new);
        e.registerBlockEntity(Capabilities.Item.BLOCK, RESTRUCTURER.get(), WorldlyContainerWrapper::new);
        e.registerBlockEntity(Capabilities.Item.BLOCK, PURIFIER.get(), WorldlyContainerWrapper::new);
    }
}
