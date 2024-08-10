package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.entity.data.ThreeStagePhase;
import androsa.gaiadimension.entity.projectile.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, GaiaDimensionMod.MODID);
    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, GaiaDimensionMod.MODID);

    //Projectiles
    public static final DeferredHolder<EntityType<?>, EntityType<AgateArrowEntity>> AGATE_ARROW = registerProjectile("agate_arrow", AgateArrowEntity::new, true, 150, 1, 0.5F, 0.5F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownPebbleEntity>> THROWN_PEBBLE = registerProjectile("thrown_pebble", ThrownPebbleEntity::new, true, 150, 2, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteAmmoEntity>> MOOKAITE_AMMO_BULLET = registerProjectile("mookaite_ammo_bullet", MookaiteAmmoEntity::new, true, 150, 2, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteMagicEntity>> MOOKAITE_MAGIC_BULLET = registerProjectile("mookaite_magic_bullet", MookaiteMagicEntity::new, true, 150, 10, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteAreaEntity>> MOOKAITE_MAGIC_AREA = registerProjectile("mookaite_magic_area", MookaiteAreaEntity::new, true, 150, Integer.MAX_VALUE, 6.0F, 0.5F, true);

    //Mobs
    public static final DeferredHolder<EntityType<?>, EntityType<AgateGolemEntity>> AGATE_GOLEM = registerEntity("agate_golem", AgateGolemEntity::new, MobCategory.MONSTER, 1.2F, 2.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<AncientLagrahkEntity>> ANCIENT_LAGRAHK = registerEntity("ancient_lagrahk", AncientLagrahkEntity::new, MobCategory.MONSTER, 1.5F, 4.0F, 3.55F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ArchaicWarriorEntity>> ARCHAIC_WARRIOR = registerEntity("archaic_warrior", ArchaicWarriorEntity::new, MobCategory.MONSTER, 0.6F, 1.95F, 1.74F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<BismuthUletrusEntity>> BISMUTH_ULETRUS = registerEntity("bismuth_uletrus", BismuthUletrusEntity::new, MobCategory.CREATURE, 2.0F, 1.8F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CavernTickEntity>> CAVERN_TICK = registerEntity("cavern_tick", CavernTickEntity::new, MobCategory.MONSTER, 0.4F, 0.3F, 0.1F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ContortedNagaEntity>> CONTORTED_NAGA = registerEntity("contorted_naga", ContortedNagaEntity::new, MobCategory.MONSTER, 1.0F, 2.6F, 2.3F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CorruptSapperEntity>> CORRUPT_SAPPER = registerEntity("corrupt_sapper", CorruptSapperEntity::new, MobCategory.MONSTER, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = registerEntity("crystal_golem", CrystalGolemEntity::new, MobCategory.CREATURE, 1.2F, 2.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<GrowthSapperEntity>> GROWTH_SAPPER = registerEntity("growth_sapper", GrowthSapperEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<HowliteWolfEntity>> HOWLITE_WOLF = registerEntity("howlite_wolf", HowliteWolfEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.68F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<LesserShockshooterEntity>> LESSER_SHOCKSHOOTER = registerEntity("lesser_shockshooter", LesserShockshooterEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, 1.8F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<LesserSpitfireEntity>> LESSER_SPITFIRE = registerEntity("lesser_spitfire", LesserSpitfireEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, 1.8F, true);
    public static final DeferredHolder<EntityType<?>, EntityType<MarkuzarPlantEntity>> MARKUZAR_PLANT = registerEntity("markuzar_plant", MarkuzarPlantEntity::new, MobCategory.AMBIENT, 0.6F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MineralArenthisEntity>> MINERAL_ARENTHIS = registerEntity("mineral_arenthis", MineralArenthisEntity::new, MobCategory.WATER_CREATURE, 1.5F, 1.5F, 0.85F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MucklingEntity>> MUCKLING = registerEntity("muckling", MucklingEntity::new, MobCategory.MONSTER, 2.0F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MutantGrowthExtractorEntity>> MUTANT_GROWTH_EXTRACTOR = registerEntity("mutant_growth_extractor", MutantGrowthExtractorEntity::new, MobCategory.CREATURE, 1.0F, 1.5F, 1.3F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<NomadicLagrahkEntity>> NOMADIC_LAGRAHK = registerEntity("nomadic_lagrahk", NomadicLagrahkEntity::new, MobCategory.CREATURE, 1.5F, 4.0F, 3.55F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<PrimalBeastEntity>> PRIMAL_BEAST = registerEntity("primal_beast", PrimalBeastEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, 1.9F, true);
    public static final DeferredHolder<EntityType<?>, EntityType<RockyLuggerothEntity>> ROCKY_LUGGEROTH = registerEntity("rocky_luggeroth", RockyLuggerothEntity::new, MobCategory.CREATURE, 1.0F, 1.6F, 0.35F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<RuggedLurmorusEntity>> RUGGED_LURMORUS = registerEntity("rugged_lurmorus", RuggedLurmorusEntity::new, MobCategory.CREATURE, 3.5F, 8.0F, 7.6F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<SaltionEntity>> SALTION = registerEntity("saltion", SaltionEntity::new, MobCategory.CREATURE, 1.0F, 0.3F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ShallowArenthisEntity>> SHALLOW_ARENTHIS = registerEntity("shallow_arenthis", ShallowArenthisEntity::new, MobCategory.WATER_CREATURE, 0.6F, 0.6F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ShalurkerEntity>> SHALURKER = registerEntity("shalurker", ShalurkerEntity::new, MobCategory.MONSTER, 0.6F, 1.9F, 1.75F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<SpellElementEntity>> SPELLBOUND_ELEMENTAL = registerEntity("spellbound_elemental", SpellElementEntity::new, MobCategory.CREATURE, 0.5F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MalachiteDroneEntity>> MALACHITE_DRONE = registerEntity("malachite_drone", MalachiteDroneEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteConstructEntity>> MOOKAITE_CONSTRUCT = registerEntity("mookaite_construct", MookaiteConstructEntity::new, MobCategory.CREATURE, 1.2F, 3.2F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<OpaliteContructEntity>> OPALITE_CONSTRUCT = registerEntity("opalite_construct", OpaliteContructEntity::new, MobCategory.CREATURE, 1.0F, 1.5F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<GrowthGrazerEntity>> GROWTH_GRAZER = registerEntity("growth_grazer", GrowthGrazerEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<AureateEvraunEntity>> AUREATE_EVRAUN = registerEntity("aureate_evraun", AureateEvraunEntity::new, MobCategory.CREATURE, 2.7F, 3.8F, false);

    //Mini Bosses
    public static final DeferredHolder<EntityType<?>, EntityType<BlueHowliteWolfEntity>> BLUE_HOWLITE_WOLF = registerEntity("blue_howlite_wolf", BlueHowliteWolfEntity::new, MobCategory.MONSTER, 1.2F, 2.2F, 2.1F, false);

    //Bosses
    public static final DeferredHolder<EntityType<?>, EntityType<MalachiteGuardEntity>> MALACHITE_GUARD = registerEntity("malachite_guard", MalachiteGuardEntity::new, MobCategory.MONSTER, 0.8F, 3.3F, 3.0F, false);

    //EntityDataSerializers
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<MookaitePartType>> MOOKAITE_PART = ENTITY_DATA_SERIALIZERS.register("mookaite_part",
            () -> EntityDataSerializer.forValueType(MookaitePartType.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<GuardPhase>> GUARD_PHASE = ENTITY_DATA_SERIALIZERS.register("guard_phase",
            () -> EntityDataSerializer.forValueType(GuardPhase.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<ThreeStagePhase>> THREE_STAGE_PHASE = ENTITY_DATA_SERIALIZERS.register("three_stage_phase",
            () -> EntityDataSerializer.forValueType(ThreeStagePhase.STREAM_CODEC));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerProjectile(String name, EntityType.EntityFactory<E> entity, boolean updates, int range, int interval, float width, float height, boolean fireproof) {
        EntityType.Builder<E> entitytype = makeBuilder(entity, MobCategory.MISC, width, height)
                .setShouldReceiveVelocityUpdates(updates)
                .setTrackingRange(range)
                .setUpdateInterval(interval);
        if (fireproof) entitytype.fireImmune();
        return ENTITY_TYPES.register(name, () -> entitytype.build(name));
    }

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height, boolean fireproof) {
        return registerEntity(name, entity, classification, width, height, 0.0F, fireproof);
    }

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height, float eye, boolean fireproof) {
        EntityType.Builder<E> type = makeBuilder(entity, classification, width, height);
        if (fireproof) type.fireImmune();
        if (eye > 0.0F) type.eyeHeight(eye);
        return ENTITY_TYPES.register(name, () -> type.build(name));
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height) {
        return EntityType.Builder.of(entity, classification).sized(width, height);
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        registerPlacement(event, AGATE_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        registerPlacement(event, ANCIENT_LAGRAHK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        registerPlacement(event, ARCHAIC_WARRIOR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, BISMUTH_ULETRUS, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        registerPlacement(event, BLUE_HOWLITE_WOLF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        registerPlacement(event, CAVERN_TICK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, CONTORTED_NAGA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        registerPlacement(event, CORRUPT_SAPPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        registerPlacement(event, CRYSTAL_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        registerPlacement(event, GROWTH_SAPPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        registerPlacement(event, HOWLITE_WOLF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        registerPlacement(event, LESSER_SHOCKSHOOTER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        registerPlacement(event, LESSER_SPITFIRE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        registerPlacement(event, MARKUZAR_PLANT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        registerPlacement(event, MINERAL_ARENTHIS, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        registerPlacement(event, MUCKLING, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        registerPlacement(event, MUTANT_GROWTH_EXTRACTOR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        registerPlacement(event, NOMADIC_LAGRAHK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        registerPlacement(event, PRIMAL_BEAST, SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        registerPlacement(event, ROCKY_LUGGEROTH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        registerPlacement(event, RUGGED_LURMORUS, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        registerPlacement(event, SALTION, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        registerPlacement(event, SHALLOW_ARENTHIS, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        registerPlacement(event, SHALURKER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, SPELLBOUND_ELEMENTAL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
        registerPlacement(event, MALACHITE_DRONE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        registerPlacement(event, MOOKAITE_CONSTRUCT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MookaiteConstructEntity::canSpawnHere);
        registerPlacement(event, OPALITE_CONSTRUCT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OpaliteContructEntity::canSpawnHere);
        registerPlacement(event, GROWTH_GRAZER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthGrazerEntity::canSpawnHere);
        registerPlacement(event, AUREATE_EVRAUN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AureateEvraunEntity::canSpawnHere);
    }

    private static <E extends Mob, T extends EntityType<E>> void registerPlacement(RegisterSpawnPlacementsEvent event, Supplier<T> entity, SpawnPlacementType type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<E> predicate) {
        event.register(entity.get(), type, heightmap, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent evt) {
        setAttributes(evt, AGATE_GOLEM, AgateGolemEntity.registerAttributes());
        setAttributes(evt, ANCIENT_LAGRAHK, AncientLagrahkEntity.registerAttributes());
        setAttributes(evt, ARCHAIC_WARRIOR, ArchaicWarriorEntity.registerAttributes());
        setAttributes(evt, BISMUTH_ULETRUS, BismuthUletrusEntity.registerAttributes());
        setAttributes(evt, CAVERN_TICK, CavernTickEntity.registerAttributes());
        setAttributes(evt, CONTORTED_NAGA, ContortedNagaEntity.registerAttributes());
        setAttributes(evt, CORRUPT_SAPPER, CorruptSapperEntity.registerAttributes());
        setAttributes(evt, CRYSTAL_GOLEM, CrystalGolemEntity.registerAttributes());
        setAttributes(evt, GROWTH_SAPPER, GrowthSapperEntity.registerAttributes());
        setAttributes(evt, HOWLITE_WOLF, HowliteWolfEntity.registerAttributes());
        setAttributes(evt, LESSER_SHOCKSHOOTER, LesserShockshooterEntity.registerAttributes());
        setAttributes(evt, LESSER_SPITFIRE, LesserSpitfireEntity.registerAttributes());
        setAttributes(evt, MARKUZAR_PLANT, MarkuzarPlantEntity.registerAttributes());
        setAttributes(evt, MINERAL_ARENTHIS, MineralArenthisEntity.registerAttributes());
        setAttributes(evt, MUCKLING, Monster.createMonsterAttributes());
        setAttributes(evt, MUTANT_GROWTH_EXTRACTOR, MutantGrowthExtractorEntity.registerAttributes());
        setAttributes(evt, NOMADIC_LAGRAHK, NomadicLagrahkEntity.registerAttributes());
        setAttributes(evt, PRIMAL_BEAST, PrimalBeastEntity.registerAttributes());
        setAttributes(evt, ROCKY_LUGGEROTH, RockyLuggerothEntity.registerAttributes());
        setAttributes(evt, RUGGED_LURMORUS, RuggedLurmorusEntity.registerAttributes());
        setAttributes(evt, SALTION, SaltionEntity.registerAttributes());
        setAttributes(evt, SHALLOW_ARENTHIS, ShallowArenthisEntity.registerAttributes());
        setAttributes(evt, SHALURKER, ShalurkerEntity.registerAttributes());
        setAttributes(evt, SPELLBOUND_ELEMENTAL, SpellElementEntity.registerAttributes());
        setAttributes(evt, MALACHITE_DRONE, MalachiteDroneEntity.registerAttributes());
        setAttributes(evt, MOOKAITE_CONSTRUCT, MookaiteConstructEntity.registerAttributes());
        setAttributes(evt, OPALITE_CONSTRUCT, OpaliteContructEntity.registerAttributes());
        setAttributes(evt, GROWTH_GRAZER, GrowthGrazerEntity.registerAttributes());
        setAttributes(evt, AUREATE_EVRAUN, AureateEvraunEntity.registerAttributes());
        setAttributes(evt, BLUE_HOWLITE_WOLF, BlueHowliteWolfEntity.registerAttributes());
        setAttributes(evt, MALACHITE_GUARD, MalachiteGuardEntity.registerAttributes());
    }

    private static <E extends LivingEntity> void setAttributes(EntityAttributeCreationEvent evt, Supplier<EntityType<E>> entity, AttributeSupplier.Builder builder) {
        evt.put(entity.get(), builder.build());
    }
}