package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolf;
import androsa.gaiadimension.entity.boss.MalachiteGuard;
import androsa.gaiadimension.entity.data.*;
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
    public static final DeferredHolder<EntityType<?>, EntityType<AgateArrow>> AGATE_ARROW = registerProjectile("agate_arrow", AgateArrow::new, true, 150, 1, 0.5F, 0.5F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownPebble>> THROWN_PEBBLE = registerProjectile("thrown_pebble", ThrownPebble::new, true, 150, 2, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteAmmo>> MOOKAITE_AMMO_BULLET = registerProjectile("mookaite_ammo_bullet", MookaiteAmmo::new, true, 150, 2, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteMagic>> MOOKAITE_MAGIC_BULLET = registerProjectile("mookaite_magic_bullet", MookaiteMagic::new, true, 150, 10, 0.25F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteAreaEffect>> MOOKAITE_MAGIC_AREA = registerProjectile("mookaite_magic_area", MookaiteAreaEffect::new, true, 150, Integer.MAX_VALUE, 6.0F, 0.5F, true);

    //Mobs
    public static final DeferredHolder<EntityType<?>, EntityType<AgateGolem>> AGATE_GOLEM = registerEntity("agate_golem", AgateGolem::new, MobCategory.MONSTER, 1.2F, 2.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<AncientLagrahk>> ANCIENT_LAGRAHK = registerEntity("ancient_lagrahk", AncientLagrahk::new, MobCategory.MONSTER, 1.5F, 4.0F, 3.55F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ArchaicWarrior>> ARCHAIC_WARRIOR = registerEntity("archaic_warrior", ArchaicWarrior::new, MobCategory.MONSTER, 0.6F, 1.95F, 1.74F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<BismuthUletrus>> BISMUTH_ULETRUS = registerEntity("bismuth_uletrus", BismuthUletrus::new, MobCategory.CREATURE, 2.0F, 1.8F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CavernTick>> CAVERN_TICK = registerEntity("cavern_tick", CavernTick::new, MobCategory.MONSTER, 0.4F, 0.3F, 0.1F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ContortedNaga>> CONTORTED_NAGA = registerEntity("contorted_naga", ContortedNaga::new, MobCategory.MONSTER, 1.0F, 2.6F, 2.3F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CorruptSapper>> CORRUPT_SAPPER = registerEntity("corrupt_sapper", CorruptSapper::new, MobCategory.MONSTER, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<CrystalGolem>> CRYSTAL_GOLEM = registerEntity("crystal_golem", CrystalGolem::new, MobCategory.CREATURE, 1.2F, 2.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<GrowthSapper>> GROWTH_SAPPER = registerEntity("growth_sapper", GrowthSapper::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<HowliteWolf>> HOWLITE_WOLF = registerEntity("howlite_wolf", HowliteWolf::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.68F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<LesserShockshooter>> LESSER_SHOCKSHOOTER = registerEntity("lesser_shockshooter", LesserShockshooter::new, MobCategory.MONSTER, 0.5F, 2.0F, 1.8F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<LesserSpitfire>> LESSER_SPITFIRE = registerEntity("lesser_spitfire", LesserSpitfire::new, MobCategory.MONSTER, 0.5F, 2.0F, 1.8F, true);
    public static final DeferredHolder<EntityType<?>, EntityType<MarkuzarPlant>> MARKUZAR_PLANT = registerEntity("markuzar_plant", MarkuzarPlant::new, MobCategory.AMBIENT, 0.6F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MineralArenthis>> MINERAL_ARENTHIS = registerEntity("mineral_arenthis", MineralArenthis::new, MobCategory.WATER_CREATURE, 1.5F, 1.5F, 0.85F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<Muckling>> MUCKLING = registerEntity("muckling", Muckling::new, MobCategory.MONSTER, 2.0F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MutantGrowthExtractor>> MUTANT_GROWTH_EXTRACTOR = registerEntity("mutant_growth_extractor", MutantGrowthExtractor::new, MobCategory.CREATURE, 1.0F, 1.5F, 1.3F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<NomadicLagrahk>> NOMADIC_LAGRAHK = registerEntity("nomadic_lagrahk", NomadicLagrahk::new, MobCategory.CREATURE, 1.5F, 4.0F, 3.55F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<PrimalBeast>> PRIMAL_BEAST = registerEntity("primal_beast", PrimalBeast::new, MobCategory.MONSTER, 1.0F, 2.0F, 1.9F, true);
    public static final DeferredHolder<EntityType<?>, EntityType<RockyLuggeroth>> ROCKY_LUGGEROTH = registerEntity("rocky_luggeroth", RockyLuggeroth::new, MobCategory.CREATURE, 1.0F, 1.6F, 0.35F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<RuggedLurmorus>> RUGGED_LURMORUS = registerEntity("rugged_lurmorus", RuggedLurmorus::new, MobCategory.CREATURE, 3.5F, 8.0F, 7.6F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<Saltion>> SALTION = registerEntity("saltion", Saltion::new, MobCategory.CREATURE, 1.0F, 0.3F, 0.25F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<ShallowArenthis>> SHALLOW_ARENTHIS = registerEntity("shallow_arenthis", ShallowArenthis::new, MobCategory.WATER_CREATURE, 0.6F, 0.6F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<Shalurker>> SHALURKER = registerEntity("shalurker", Shalurker::new, MobCategory.MONSTER, 0.6F, 1.9F, 1.75F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<SpellElemental>> SPELLBOUND_ELEMENTAL = registerEntity("spellbound_elemental", SpellElemental::new, MobCategory.CREATURE, 0.5F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MalachiteDrone>> MALACHITE_DRONE = registerEntity("malachite_drone", MalachiteDrone::new, MobCategory.MONSTER, 1.0F, 2.0F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<MookaiteConstruct>> MOOKAITE_CONSTRUCT = registerEntity("mookaite_construct", MookaiteConstruct::new, MobCategory.CREATURE, 1.2F, 3.2F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<OpaliteContruct>> OPALITE_CONSTRUCT = registerEntity("opalite_construct", OpaliteContruct::new, MobCategory.CREATURE, 1.0F, 1.5F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<GrowthGrazer>> GROWTH_GRAZER = registerEntity("growth_grazer", GrowthGrazer::new, MobCategory.CREATURE, 1.0F, 1.0F, 0.7F, false);
    public static final DeferredHolder<EntityType<?>, EntityType<AureateEvraun>> AUREATE_EVRAUN = registerEntity("aureate_evraun", AureateEvraun::new, MobCategory.CREATURE, 2.7F, 3.8F, false);

    //Mini Bosses
    public static final DeferredHolder<EntityType<?>, EntityType<BlueHowliteWolf>> BLUE_HOWLITE_WOLF = registerEntity("blue_howlite_wolf", BlueHowliteWolf::new, MobCategory.MONSTER, 1.2F, 2.2F, 2.1F, false);

    //Bosses
    public static final DeferredHolder<EntityType<?>, EntityType<MalachiteGuard>> MALACHITE_GUARD = registerEntity("malachite_guard", MalachiteGuard::new, MobCategory.MONSTER, 0.8F, 3.3F, 3.0F, false);

    //EntityDataSerializers
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<MookaitePartType>> MOOKAITE_PART = ENTITY_DATA_SERIALIZERS.register("mookaite_part",
            () -> EntityDataSerializer.forValueType(MookaitePartType.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<GuardPhase>> GUARD_PHASE = ENTITY_DATA_SERIALIZERS.register("guard_phase",
            () -> EntityDataSerializer.forValueType(GuardPhase.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<ThreeStagePhase>> THREE_STAGE_PHASE = ENTITY_DATA_SERIALIZERS.register("three_stage_phase",
            () -> EntityDataSerializer.forValueType(ThreeStagePhase.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<SapperVariant>> SAPPER_VARIANT = ENTITY_DATA_SERIALIZERS.register("sapper_variant",
            () -> EntityDataSerializer.forValueType(SapperVariant.STREAM_CODEC));
    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<LagrahkVariant>> LAGRAHK_VARIANT = ENTITY_DATA_SERIALIZERS.register("lagrahk_variant",
            () -> EntityDataSerializer.forValueType(LagrahkVariant.STREAM_CODEC));

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
        registerPlacement(event, AGATE_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AgateGolem::canSpawnHere);
        registerPlacement(event, ANCIENT_LAGRAHK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AncientLagrahk::canSpawnHere);
        registerPlacement(event, ARCHAIC_WARRIOR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, BISMUTH_ULETRUS, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BismuthUletrus::canSpawnHere);
        registerPlacement(event, BLUE_HOWLITE_WOLF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolf::canSpawnHere);
        registerPlacement(event, CAVERN_TICK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, CONTORTED_NAGA, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ContortedNaga::canSpawnHere);
        registerPlacement(event, CORRUPT_SAPPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CorruptSapper::canSpawnHere);
        registerPlacement(event, CRYSTAL_GOLEM, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalGolem::canSpawnHere);
        registerPlacement(event, GROWTH_SAPPER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthSapper::canSpawnHere);
        registerPlacement(event, HOWLITE_WOLF, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HowliteWolf::canSpawnHere);
        registerPlacement(event, LESSER_SHOCKSHOOTER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserShockshooter::canSpawnHere);
        registerPlacement(event, LESSER_SPITFIRE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserSpitfire::canSpawnHere);
        registerPlacement(event, MARKUZAR_PLANT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlant::canSpawnHere);
        registerPlacement(event, MINERAL_ARENTHIS, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MineralArenthis::canSpawnHere);
        registerPlacement(event, MUCKLING, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Muckling::canSpawnHere);
        registerPlacement(event, MUTANT_GROWTH_EXTRACTOR, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractor::canSpawnHere);
        registerPlacement(event, NOMADIC_LAGRAHK, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahk::canSpawnHere);
        registerPlacement(event, PRIMAL_BEAST, SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PrimalBeast::canSpawnHere);
        registerPlacement(event, ROCKY_LUGGEROTH, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RockyLuggeroth::canSpawnHere);
        registerPlacement(event, RUGGED_LURMORUS, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorus::canSpawnHere);
        registerPlacement(event, SALTION, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Saltion::canSpawnHere);
        registerPlacement(event, SHALLOW_ARENTHIS, SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShallowArenthis::canSpawnHere);
        registerPlacement(event, SHALURKER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(event, SPELLBOUND_ELEMENTAL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpellElemental::canSpawnHere);
        registerPlacement(event, MALACHITE_DRONE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
        registerPlacement(event, MOOKAITE_CONSTRUCT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MookaiteConstruct::canSpawnHere);
        registerPlacement(event, OPALITE_CONSTRUCT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OpaliteContruct::canSpawnHere);
        registerPlacement(event, GROWTH_GRAZER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthGrazer::canSpawnHere);
        registerPlacement(event, AUREATE_EVRAUN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AureateEvraun::canSpawnHere);
    }

    private static <E extends Mob, T extends EntityType<E>> void registerPlacement(RegisterSpawnPlacementsEvent event, Supplier<T> entity, SpawnPlacementType type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<E> predicate) {
        event.register(entity.get(), type, heightmap, predicate, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent evt) {
        setAttributes(evt, AGATE_GOLEM, AgateGolem.registerAttributes());
        setAttributes(evt, ANCIENT_LAGRAHK, AncientLagrahk.registerAttributes());
        setAttributes(evt, ARCHAIC_WARRIOR, ArchaicWarrior.registerAttributes());
        setAttributes(evt, BISMUTH_ULETRUS, BismuthUletrus.registerAttributes());
        setAttributes(evt, CAVERN_TICK, CavernTick.registerAttributes());
        setAttributes(evt, CONTORTED_NAGA, ContortedNaga.registerAttributes());
        setAttributes(evt, CORRUPT_SAPPER, CorruptSapper.registerAttributes());
        setAttributes(evt, CRYSTAL_GOLEM, CrystalGolem.registerAttributes());
        setAttributes(evt, GROWTH_SAPPER, GrowthSapper.registerAttributes());
        setAttributes(evt, HOWLITE_WOLF, HowliteWolf.registerAttributes());
        setAttributes(evt, LESSER_SHOCKSHOOTER, LesserShockshooter.registerAttributes());
        setAttributes(evt, LESSER_SPITFIRE, LesserSpitfire.registerAttributes());
        setAttributes(evt, MARKUZAR_PLANT, MarkuzarPlant.registerAttributes());
        setAttributes(evt, MINERAL_ARENTHIS, MineralArenthis.registerAttributes());
        setAttributes(evt, MUCKLING, Monster.createMonsterAttributes());
        setAttributes(evt, MUTANT_GROWTH_EXTRACTOR, MutantGrowthExtractor.registerAttributes());
        setAttributes(evt, NOMADIC_LAGRAHK, NomadicLagrahk.registerAttributes());
        setAttributes(evt, PRIMAL_BEAST, PrimalBeast.registerAttributes());
        setAttributes(evt, ROCKY_LUGGEROTH, RockyLuggeroth.registerAttributes());
        setAttributes(evt, RUGGED_LURMORUS, RuggedLurmorus.registerAttributes());
        setAttributes(evt, SALTION, Saltion.registerAttributes());
        setAttributes(evt, SHALLOW_ARENTHIS, ShallowArenthis.registerAttributes());
        setAttributes(evt, SHALURKER, Shalurker.registerAttributes());
        setAttributes(evt, SPELLBOUND_ELEMENTAL, SpellElemental.registerAttributes());
        setAttributes(evt, MALACHITE_DRONE, MalachiteDrone.registerAttributes());
        setAttributes(evt, MOOKAITE_CONSTRUCT, MookaiteConstruct.registerAttributes());
        setAttributes(evt, OPALITE_CONSTRUCT, OpaliteContruct.registerAttributes());
        setAttributes(evt, GROWTH_GRAZER, GrowthGrazer.registerAttributes());
        setAttributes(evt, AUREATE_EVRAUN, AureateEvraun.registerAttributes());
        setAttributes(evt, BLUE_HOWLITE_WOLF, BlueHowliteWolf.registerAttributes());
        setAttributes(evt, MALACHITE_GUARD, MalachiteGuard.registerAttributes());
    }

    private static <E extends LivingEntity> void setAttributes(EntityAttributeCreationEvent evt, Supplier<EntityType<E>> entity, AttributeSupplier.Builder builder) {
        evt.put(entity.get(), builder.build());
    }
}