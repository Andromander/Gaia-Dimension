package androsa.gaiadimension.registry;

import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.spawner.WorldEntitySpawner;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.register;

public class GaiaSpawnPlacements {

    public static final EntitySpawnPlacementRegistry.PlacementType IN_LAVA = EntitySpawnPlacementRegistry.PlacementType.create("GD_IN_LAVA", (reader, pos, entity) -> {
        BlockState blockState = reader.getBlockState(pos);
        IFluidState fluidState = reader.getFluidState(pos);
        BlockPos posUp = pos.up();
        BlockPos posDown = pos.down();

        if (fluidState.isTagged(FluidTags.LAVA) && reader.getFluidState(posDown).isTagged(FluidTags.LAVA) && !reader.getBlockState(posUp).isNormalCube(reader, posUp)) {
            return true;
        } else {
            BlockState state = reader.getBlockState(posDown);

            if (!state.canCreatureSpawn(reader, posDown, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, entity)) {
                return false;
            } else {
                return WorldEntitySpawner.isSpawnableSpace(reader, pos, blockState, fluidState) && WorldEntitySpawner.isSpawnableSpace(reader, posUp, reader.getBlockState(posUp), reader.getFluidState(posUp));
            }
        }
    });

    static {
        register(ModEntities.AGATE_GOLEM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        register(ModEntities.ANCIENT_LAGRAHK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        register(ModEntities.ARCHAIC_WARRIOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ArchaicWarriorEntity::func_223325_c);
        register(ModEntities.BISMUTH_ULETRUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        register(ModEntities.BLUE_HOWLITE_WOLF, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        register(ModEntities.CAVERN_TICK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CavernTickEntity::func_223325_c);
        register(ModEntities.CONTORTED_NAGA, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        register(ModEntities.CORRUPT_SAPPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        register(ModEntities.CRYSTAL_GOLEM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        register(ModEntities.GROWTH_SAPPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        register(ModEntities.HOWLITE_WOLF, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        register(ModEntities.LESSER_SHOCKSHOOTER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        register(ModEntities.LESSER_SPITFIRE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        register(ModEntities.MARKUZAR_PLANT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        register(ModEntities.MINERAL_ARENTHIS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        register(ModEntities.MUCKLING, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        register(ModEntities.MUTANT_EXTRACTOR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        register(ModEntities.NOMADIC_LAGRAHK, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        register(ModEntities.PRIMAL_BEAST, GaiaSpawnPlacements.IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        register(ModEntities.ROCKY_LUGGEROTH, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        register(ModEntities.RUGGED_LURMORUS, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        register(ModEntities.SALTION, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        register(ModEntities.SHALLOW_ARENTHIS, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        register(ModEntities.SHALURKER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShalurkerEntity::func_223325_c);
        register(ModEntities.SPELLBOUND_ELEMENTAL, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
    }
}
