package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.*;
import androsa.gaiadimension.block.blockentity.boss.MalachiteGuardSpawnerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<BlockEntityType<GaiaStoneFurnaceBlockEntity>> GAIA_STONE_FURNACE =  TILE_ENTITIES.register(
            "gaia_stone_furnace", () -> BlockEntityType.Builder.of(GaiaStoneFurnaceBlockEntity::new, ModBlocks.gaia_stone_furnace.get()).build(null));
    public static final RegistryObject<BlockEntityType<GeyserBlockEntity>> GEYSER = TILE_ENTITIES.register(
            "geyser", () -> BlockEntityType.Builder.of(GeyserBlockEntity::new, ModBlocks.geyser_block.get()).build(null));
    public static final RegistryObject<BlockEntityType<LargeCrateBlockEntity>> LARGE_CRATE = TILE_ENTITIES.register(
            "large_crate", () -> BlockEntityType.Builder.of(LargeCrateBlockEntity::new, ModBlocks.mega_storage_crate.get()).build(null));
    public static final RegistryObject<BlockEntityType<PurifierBlockEntity>> PURIFIER = TILE_ENTITIES.register(
            "purifier", () -> BlockEntityType.Builder.of(PurifierBlockEntity::new, ModBlocks.purifier.get()).build(null));
    public static final RegistryObject<BlockEntityType<RestructurerBlockEntity>> RESTRUCTURER = TILE_ENTITIES.register(
            "restructurer", () -> BlockEntityType.Builder.of(RestructurerBlockEntity::new, ModBlocks.restructurer.get()).build(null));
    public static final RegistryObject<BlockEntityType<SmallCrateBlockEntity>> SMALL_CRATE = TILE_ENTITIES.register(
            "small_crate", () -> BlockEntityType.Builder.of(SmallCrateBlockEntity::new, ModBlocks.crude_storage_crate.get()).build(null));

    public static final RegistryObject<BlockEntityType<MalachiteGuardSpawnerBlockEntity>> MALACHITE_SPAWNER = TILE_ENTITIES.register(
            "malachite_spawner", () -> BlockEntityType.Builder.of(MalachiteGuardSpawnerBlockEntity::new, ModBlocks.malachite_guard_spawner.get()).build(null));
}
