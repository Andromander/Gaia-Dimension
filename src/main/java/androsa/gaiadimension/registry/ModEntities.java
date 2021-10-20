package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.entity.SpawnPlacements.Type;
import static net.minecraft.world.entity.SpawnPlacements.register;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    //Projectiles
    public static final EntityType<AgateArrowEntity> AGATE_ARROW = registerProjectile("agate_arrow", AgateArrowEntity::new, true, 150, 1, 0.5F, 0.5F);
    public static final EntityType<ThrownPebbleEntity> THROWN_PEBBLE = registerProjectile("thrown_pebble", ThrownPebbleEntity::new, true, 150, 2, 0.25F, 0.25F);

    //Mobs
    public static final EntityType<AgateGolemEntity> AGATE_GOLEM = registerEntity("agate_golem", AgateGolemEntity::new, MobCategory.MONSTER, 1.2F, 2.7F, false);
    public static final EntityType<AncientLagrahkEntity> ANCIENT_LAGRAHK = registerEntity("ancient_lagrahk", AncientLagrahkEntity::new, MobCategory.MONSTER, 1.5F, 4.0F, false);
    public static final EntityType<ArchaicWarriorEntity> ARCHAIC_WARRIOR = registerEntity("archaic_warrior", ArchaicWarriorEntity::new, MobCategory.MONSTER, 0.6F, 1.95F, false);
    public static final EntityType<BismuthUletrusEntity> BISMUTH_ULETRUS = registerEntity("bismuth_uletrus", BismuthUletrusEntity::new, MobCategory.CREATURE, 2.0F, 1.8F, false);
    public static final EntityType<CavernTickEntity> CAVERN_TICK = registerEntity("cavern_tick", CavernTickEntity::new, MobCategory.MONSTER, 0.4F, 0.3F, false);
    public static final EntityType<ContortedNagaEntity> CONTORTED_NAGA = registerEntity("contorted_naga", ContortedNagaEntity::new, MobCategory.MONSTER, 1.0F, 2.6F, false);
    public static final EntityType<CorruptSapperEntity> CORRUPT_SAPPER = registerEntity("corrupt_sapper", CorruptSapperEntity::new, MobCategory.MONSTER, 1.0F, 1.0F, false);
    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = registerEntity("crystal_golem", CrystalGolemEntity::new, MobCategory.CREATURE, 1.2F, 2.7F, false);
    public static final EntityType<GrowthSapperEntity> GROWTH_SAPPER = registerEntity("growth_sapper", GrowthSapperEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, false);
    public static final EntityType<HowliteWolfEntity> HOWLITE_WOLF = registerEntity("howlite_wolf", HowliteWolfEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, false);
    public static final EntityType<LesserShockshooterEntity> LESSER_SHOCKSHOOTER = registerEntity("lesser_shockshooter", LesserShockshooterEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, false);
    public static final EntityType<LesserSpitfireEntity> LESSER_SPITFIRE = registerEntity("lesser_spitfire", LesserSpitfireEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, true);
    public static final EntityType<MarkuzarPlantEntity> MARKUZAR_PLANT = registerEntity("markuzar_plant", MarkuzarPlantEntity::new, MobCategory.AMBIENT, 0.6F, 2.0F, false);
    public static final EntityType<MineralArenthisEntity> MINERAL_ARENTHIS = registerEntity("mineral_arenthis", MineralArenthisEntity::new, MobCategory.WATER_CREATURE, 1.5F, 1.5F, false);
    public static final EntityType<MucklingEntity> MUCKLING = registerEntity("muckling", MucklingEntity::new, MobCategory.MONSTER, 2.0F, 2.0F, false);
    public static final EntityType<MutantGrowthExtractorEntity> MUTANT_GROWTH_EXTRACTOR = registerEntity("mutant_growth_extractor", MutantGrowthExtractorEntity::new, MobCategory.CREATURE, 1.0F, 1.5F, false);
    public static final EntityType<NomadicLagrahkEntity> NOMADIC_LAGRAHK = registerEntity("nomadic_lagrahk", NomadicLagrahkEntity::new, MobCategory.CREATURE, 1.5F, 4.0F, false);
    public static final EntityType<PrimalBeastEntity> PRIMAL_BEAST = registerEntity("primal_beast", PrimalBeastEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, true);
    public static final EntityType<RockyLuggerothEntity> ROCKY_LUGGEROTH = registerEntity("rocky_luggeroth", RockyLuggerothEntity::new, MobCategory.CREATURE, 1.0F, 1.6F, false);
    public static final EntityType<RuggedLurmorusEntity> RUGGED_LURMORUS = registerEntity("rugged_lurmorus", RuggedLurmorusEntity::new, MobCategory.CREATURE, 3.5F, 8.0F, false);
    public static final EntityType<SaltionEntity> SALTION = registerEntity("saltion", SaltionEntity::new, MobCategory.CREATURE, 1.0F, 0.3F, false);
    public static final EntityType<ShallowArenthisEntity> SHALLOW_ARENTHIS = registerEntity("shallow_arenthis", ShallowArenthisEntity::new, MobCategory.WATER_CREATURE, 0.6F, 0.6F, false);
    public static final EntityType<ShalurkerEntity> SHALURKER = registerEntity("shalurker", ShalurkerEntity::new, MobCategory.MONSTER, 0.6F, 1.9F, false);
    public static final EntityType<SpellElementEntity> SPELLBOUND_ELEMENTAL = registerEntity("spellbound_elemental", SpellElementEntity::new, MobCategory.CREATURE, 0.5F, 2.0F, false);
    public static final EntityType<MalachiteDroneEntity> MALACHITE_DRONE = registerEntity("malachite_drone", MalachiteDroneEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, false);

    //Mini Bosses
    public static final EntityType<BlueHowliteWolfEntity> BLUE_HOWLITE_WOLF = registerEntity("blue_howlite_wolf", BlueHowliteWolfEntity::new, MobCategory.MONSTER, 1.2F, 2.2F, false);

    //Bosses
    public static final EntityType<MalachiteGuardEntity> MALACHITE_GUARD = registerEntity("malachite_guard", MalachiteGuardEntity::new, MobCategory.MONSTER, 0.8F, 3.3F, false);

    public static <E extends Entity> EntityType<E> registerProjectile(String name, EntityType.EntityFactory<E> entity, boolean updates, int range, int interval, float width, float height) {
        EntityType<E> entitytype = makeBuilder(entity, MobCategory.MISC, width, height)
                .setShouldReceiveVelocityUpdates(updates)
                .setTrackingRange(range)
                .setUpdateInterval(interval)
                .build(name);
        entitytype.setRegistryName(new ResourceLocation(GaiaDimensionMod.MODID, name));
        return RegistryHelper.registerEntity(entitytype);
    }

    public static <E extends Entity> EntityType<E> registerEntity(String name, EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height, boolean fireproof) {
        EntityType.Builder<E> type = makeBuilder(entity, classification, width, height);
        if (fireproof) type.fireImmune();
        EntityType<E> entitytype = type.build(name);
        entitytype.setRegistryName(new ResourceLocation(GaiaDimensionMod.MODID, name));
        return RegistryHelper.registerEntity(entitytype);
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height) {
        return EntityType.Builder.of(entity, classification).sized(width, height);
    }

    /* Spawn Placements */
    public static Type IN_LAVA = Type.create("GD_IN_LAVA", (reader, pos, entity) -> {
        BlockState blockState = reader.getBlockState(pos);
        FluidState fluidState = reader.getFluidState(pos);
        BlockPos posUp = pos.above();
        BlockPos posDown = pos.below();

        if (fluidState.is(FluidTags.LAVA) && reader.getFluidState(posDown).is(FluidTags.LAVA) && !reader.getBlockState(posUp).isRedstoneConductor(reader, posUp)) {
            return true;
        } else {
            BlockState state = reader.getBlockState(posDown);

            if (!state.canCreatureSpawn(reader, posDown, Type.ON_GROUND, entity)) {
                return false;
            } else {
                return NaturalSpawner.isValidEmptySpawnBlock(reader, pos, blockState, fluidState, entity) && NaturalSpawner.isValidEmptySpawnBlock(reader, posUp, reader.getBlockState(posUp), reader.getFluidState(posUp), entity);
            }
        }
    });

    public static void registerSpawnPlacement() {
        register(AGATE_GOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        register(ANCIENT_LAGRAHK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        register(ARCHAIC_WARRIOR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        register(BISMUTH_ULETRUS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        register(BLUE_HOWLITE_WOLF, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        register(CAVERN_TICK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        register(CONTORTED_NAGA, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        register(CORRUPT_SAPPER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        register(CRYSTAL_GOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        register(GROWTH_SAPPER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        register(HOWLITE_WOLF, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        register(LESSER_SHOCKSHOOTER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        register(LESSER_SPITFIRE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        register(MARKUZAR_PLANT, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        register(MINERAL_ARENTHIS, Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        register(MUCKLING, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        register(MUTANT_GROWTH_EXTRACTOR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        register(NOMADIC_LAGRAHK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        register(PRIMAL_BEAST, IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        register(ROCKY_LUGGEROTH, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        register(RUGGED_LURMORUS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        register(SALTION, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        register(SHALLOW_ARENTHIS, Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        register(SHALURKER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        register(SPELLBOUND_ELEMENTAL, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
        register(MALACHITE_DRONE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent evt) {
        evt.put(AGATE_GOLEM, AgateGolemEntity.registerAttributes().build());
        evt.put(ANCIENT_LAGRAHK, AncientLagrahkEntity.registerAttributes().build());
        evt.put(ARCHAIC_WARRIOR, ArchaicWarriorEntity.registerAttributes().build());
        evt.put(BISMUTH_ULETRUS, BismuthUletrusEntity.registerAttributes().build());
        evt.put(CAVERN_TICK, CavernTickEntity.registerAttributes().build());
        evt.put(CONTORTED_NAGA, ContortedNagaEntity.registerAttributes().build());
        evt.put(CORRUPT_SAPPER, CorruptSapperEntity.registerAttributes().build());
        evt.put(CRYSTAL_GOLEM, CrystalGolemEntity.registerAttributes().build());
        evt.put(GROWTH_SAPPER, GrowthSapperEntity.registerAttributes().build());
        evt.put(HOWLITE_WOLF, HowliteWolfEntity.registerAttributes().build());
        evt.put(LESSER_SHOCKSHOOTER, LesserShockshooterEntity.registerAttributes().build());
        evt.put(LESSER_SPITFIRE, LesserSpitfireEntity.registerAttributes().build());
        evt.put(MARKUZAR_PLANT, MarkuzarPlantEntity.registerAttributes().build());
        evt.put(MINERAL_ARENTHIS, MineralArenthisEntity.registerAttributes().build());
        evt.put(MUCKLING, Monster.createMonsterAttributes().build());
        evt.put(MUTANT_GROWTH_EXTRACTOR, MutantGrowthExtractorEntity.registerAttributes().build());
        evt.put(NOMADIC_LAGRAHK, NomadicLagrahkEntity.registerAttributes().build());
        evt.put(PRIMAL_BEAST, PrimalBeastEntity.registerAttributes().build());
        evt.put(ROCKY_LUGGEROTH, RockyLuggerothEntity.registerAttributes().build());
        evt.put(RUGGED_LURMORUS, RuggedLurmorusEntity.registerAttributes().build());
        evt.put(SALTION, SaltionEntity.registerAttributes().build());
        evt.put(SHALLOW_ARENTHIS, ShallowArenthisEntity.registerAttributes().build());
        evt.put(SHALURKER, ShalurkerEntity.registerAttributes().build());
        evt.put(SPELLBOUND_ELEMENTAL, SpellElementEntity.registerAttributes().build());
        evt.put(MALACHITE_DRONE, MalachiteDroneEntity.registerAttributes().build());
        evt.put(BLUE_HOWLITE_WOLF, BlueHowliteWolfEntity.registerAttributes().build());
        evt.put(MALACHITE_GUARD, MalachiteGuardEntity.registerAttributes().build());
    }
}