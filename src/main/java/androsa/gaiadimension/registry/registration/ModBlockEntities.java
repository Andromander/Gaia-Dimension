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
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GaiaDimensionMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GaiaStoneFurnaceBlockEntity>> GAIA_STONE_FURNACE = TILE_ENTITIES.register(
            "gaia_stone_furnace", () -> BlockEntityType.Builder.of(GaiaStoneFurnaceBlockEntity::new, ModBlocks.gaia_stone_furnace.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GeyserBlockEntity>> GEYSER = TILE_ENTITIES.register(
            "geyser", () -> BlockEntityType.Builder.of(GeyserBlockEntity::new, ModBlocks.geyser_block.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LargeCrateBlockEntity>> LARGE_CRATE = TILE_ENTITIES.register(
            "large_crate", () -> BlockEntityType.Builder.of(LargeCrateBlockEntity::new, ModBlocks.mega_storage_crate.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PurifierBlockEntity>> PURIFIER = TILE_ENTITIES.register(
            "purifier", () -> BlockEntityType.Builder.of(PurifierBlockEntity::new, ModBlocks.purifier.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RestructurerBlockEntity>> RESTRUCTURER = TILE_ENTITIES.register(
            "restructurer", () -> BlockEntityType.Builder.of(RestructurerBlockEntity::new, ModBlocks.restructurer.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmallCrateBlockEntity>> SMALL_CRATE = TILE_ENTITIES.register(
            "small_crate", () -> BlockEntityType.Builder.of(SmallCrateBlockEntity::new, ModBlocks.crude_storage_crate.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MalachiteGuardSpawnerBlockEntity>> MALACHITE_SPAWNER = TILE_ENTITIES.register(
            "malachite_spawner", () -> BlockEntityType.Builder.of(MalachiteGuardSpawnerBlockEntity::new, ModBlocks.malachite_guard_spawner.get()).build(null));

    @SubscribeEvent
    public static void registerHandlers(RegisterCapabilitiesEvent e) {
        e.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GAIA_STONE_FURNACE.get(), SidedInvWrapper::new);
        e.registerBlockEntity(Capabilities.ItemHandler.BLOCK, RESTRUCTURER.get(), SidedInvWrapper::new);
        e.registerBlockEntity(Capabilities.ItemHandler.BLOCK, PURIFIER.get(), SidedInvWrapper::new);
    }
}
