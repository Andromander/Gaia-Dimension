package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.entity.SpawnPlacements.Type;
import static net.minecraft.world.entity.SpawnPlacements.register;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, GaiaDimensionMod.MODID);

    //Projectiles
    public static final RegistryObject<EntityType<AgateArrowEntity>> AGATE_ARROW = registerProjectile("agate_arrow", AgateArrowEntity::new, true, 150, 1, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<ThrownPebbleEntity>> THROWN_PEBBLE = registerProjectile("thrown_pebble", ThrownPebbleEntity::new, true, 150, 2, 0.25F, 0.25F);

    //Mobs
    public static final RegistryObject<EntityType<AgateGolemEntity>> AGATE_GOLEM = registerEntity("agate_golem", AgateGolemEntity::new, MobCategory.MONSTER, 1.2F, 2.7F, false);
    public static final RegistryObject<EntityType<AncientLagrahkEntity>> ANCIENT_LAGRAHK = registerEntity("ancient_lagrahk", AncientLagrahkEntity::new, MobCategory.MONSTER, 1.5F, 4.0F, false);
    public static final RegistryObject<EntityType<ArchaicWarriorEntity>> ARCHAIC_WARRIOR = registerEntity("archaic_warrior", ArchaicWarriorEntity::new, MobCategory.MONSTER, 0.6F, 1.95F, false);
    public static final RegistryObject<EntityType<BismuthUletrusEntity>> BISMUTH_ULETRUS = registerEntity("bismuth_uletrus", BismuthUletrusEntity::new, MobCategory.CREATURE, 2.0F, 1.8F, false);
    public static final RegistryObject<EntityType<CavernTickEntity>> CAVERN_TICK = registerEntity("cavern_tick", CavernTickEntity::new, MobCategory.MONSTER, 0.4F, 0.3F, false);
    public static final RegistryObject<EntityType<ContortedNagaEntity>> CONTORTED_NAGA = registerEntity("contorted_naga", ContortedNagaEntity::new, MobCategory.MONSTER, 1.0F, 2.6F, false);
    public static final RegistryObject<EntityType<CorruptSapperEntity>> CORRUPT_SAPPER = registerEntity("corrupt_sapper", CorruptSapperEntity::new, MobCategory.MONSTER, 1.0F, 1.0F, false);
    public static final RegistryObject<EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = registerEntity("crystal_golem", CrystalGolemEntity::new, MobCategory.CREATURE, 1.2F, 2.7F, false);
    public static final RegistryObject<EntityType<GrowthSapperEntity>> GROWTH_SAPPER = registerEntity("growth_sapper", GrowthSapperEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, false);
    public static final RegistryObject<EntityType<HowliteWolfEntity>> HOWLITE_WOLF = registerEntity("howlite_wolf", HowliteWolfEntity::new, MobCategory.CREATURE, 1.0F, 1.0F, false);
    public static final RegistryObject<EntityType<LesserShockshooterEntity>> LESSER_SHOCKSHOOTER = registerEntity("lesser_shockshooter", LesserShockshooterEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, false);
    public static final RegistryObject<EntityType<LesserSpitfireEntity>> LESSER_SPITFIRE = registerEntity("lesser_spitfire", LesserSpitfireEntity::new, MobCategory.MONSTER, 0.5F, 2.0F, true);
    public static final RegistryObject<EntityType<MarkuzarPlantEntity>> MARKUZAR_PLANT = registerEntity("markuzar_plant", MarkuzarPlantEntity::new, MobCategory.AMBIENT, 0.6F, 2.0F, false);
    public static final RegistryObject<EntityType<MineralArenthisEntity>> MINERAL_ARENTHIS = registerEntity("mineral_arenthis", MineralArenthisEntity::new, MobCategory.WATER_CREATURE, 1.5F, 1.5F, false);
    public static final RegistryObject<EntityType<MucklingEntity>> MUCKLING = registerEntity("muckling", MucklingEntity::new, MobCategory.MONSTER, 2.0F, 2.0F, false);
    public static final RegistryObject<EntityType<MutantGrowthExtractorEntity>> MUTANT_GROWTH_EXTRACTOR = registerEntity("mutant_growth_extractor", MutantGrowthExtractorEntity::new, MobCategory.CREATURE, 1.0F, 1.5F, false);
    public static final RegistryObject<EntityType<NomadicLagrahkEntity>> NOMADIC_LAGRAHK = registerEntity("nomadic_lagrahk", NomadicLagrahkEntity::new, MobCategory.CREATURE, 1.5F, 4.0F, false);
    public static final RegistryObject<EntityType<PrimalBeastEntity>> PRIMAL_BEAST = registerEntity("primal_beast", PrimalBeastEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, true);
    public static final RegistryObject<EntityType<RockyLuggerothEntity>> ROCKY_LUGGEROTH = registerEntity("rocky_luggeroth", RockyLuggerothEntity::new, MobCategory.CREATURE, 1.0F, 1.6F, false);
    public static final RegistryObject<EntityType<RuggedLurmorusEntity>> RUGGED_LURMORUS = registerEntity("rugged_lurmorus", RuggedLurmorusEntity::new, MobCategory.CREATURE, 3.5F, 8.0F, false);
    public static final RegistryObject<EntityType<SaltionEntity>> SALTION = registerEntity("saltion", SaltionEntity::new, MobCategory.CREATURE, 1.0F, 0.3F, false);
    public static final RegistryObject<EntityType<ShallowArenthisEntity>> SHALLOW_ARENTHIS = registerEntity("shallow_arenthis", ShallowArenthisEntity::new, MobCategory.WATER_CREATURE, 0.6F, 0.6F, false);
    public static final RegistryObject<EntityType<ShalurkerEntity>> SHALURKER = registerEntity("shalurker", ShalurkerEntity::new, MobCategory.MONSTER, 0.6F, 1.9F, false);
    public static final RegistryObject<EntityType<SpellElementEntity>> SPELLBOUND_ELEMENTAL = registerEntity("spellbound_elemental", SpellElementEntity::new, MobCategory.CREATURE, 0.5F, 2.0F, false);
    public static final RegistryObject<EntityType<MalachiteDroneEntity>> MALACHITE_DRONE = registerEntity("malachite_drone", MalachiteDroneEntity::new, MobCategory.MONSTER, 1.0F, 2.0F, false);

    //Mini Bosses
    public static final RegistryObject<EntityType<BlueHowliteWolfEntity>> BLUE_HOWLITE_WOLF = registerEntity("blue_howlite_wolf", BlueHowliteWolfEntity::new, MobCategory.MONSTER, 1.2F, 2.2F, false);

    //Bosses
    public static final RegistryObject<EntityType<MalachiteGuardEntity>> MALACHITE_GUARD = registerEntity("malachite_guard", MalachiteGuardEntity::new, MobCategory.MONSTER, 0.8F, 3.3F, false);

    public static <E extends Entity> RegistryObject<EntityType<E>> registerProjectile(String name, EntityType.EntityFactory<E> entity, boolean updates, int range, int interval, float width, float height) {
        EntityType.Builder<E> entitytype = makeBuilder(entity, MobCategory.MISC, width, height)
                .setShouldReceiveVelocityUpdates(updates)
                .setTrackingRange(range)
                .setUpdateInterval(interval);
        return ENTITY_TYPES.register(name, () -> entitytype.build(name));
    }

    public static <E extends Entity> RegistryObject<EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height, boolean fireproof) {
        EntityType.Builder<E> type = makeBuilder(entity, classification, width, height);
        if (fireproof) type.fireImmune();
        return ENTITY_TYPES.register(name, () -> type.build(name));
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.EntityFactory<E> entity, MobCategory classification, float width, float height) {
        return EntityType.Builder.of(entity, classification).sized(width, height);
    }

    public static void registerSpawnPlacement() {
        registerPlacement(AGATE_GOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        registerPlacement(ANCIENT_LAGRAHK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        registerPlacement(ARCHAIC_WARRIOR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(BISMUTH_ULETRUS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        registerPlacement(BLUE_HOWLITE_WOLF, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        registerPlacement(CAVERN_TICK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(CONTORTED_NAGA, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        registerPlacement(CORRUPT_SAPPER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        registerPlacement(CRYSTAL_GOLEM, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        registerPlacement(GROWTH_SAPPER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        registerPlacement(HOWLITE_WOLF, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        registerPlacement(LESSER_SHOCKSHOOTER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        registerPlacement(LESSER_SPITFIRE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        registerPlacement(MARKUZAR_PLANT, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        registerPlacement(MINERAL_ARENTHIS, Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        registerPlacement(MUCKLING, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        registerPlacement(MUTANT_GROWTH_EXTRACTOR, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        registerPlacement(NOMADIC_LAGRAHK, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        registerPlacement(PRIMAL_BEAST, Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        registerPlacement(ROCKY_LUGGEROTH, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        registerPlacement(RUGGED_LURMORUS, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        registerPlacement(SALTION, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        registerPlacement(SHALLOW_ARENTHIS, Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        registerPlacement(SHALURKER, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        registerPlacement(SPELLBOUND_ELEMENTAL, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
        registerPlacement(MALACHITE_DRONE, Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkAnyLightMonsterSpawnRules);
    }

    private static <E extends Mob, T extends EntityType<E>> void registerPlacement(Supplier<T> entity, Type type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<E> predicate) {
        register(entity.get(), type, heightmap, predicate);
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
        setAttributes(evt, BLUE_HOWLITE_WOLF, BlueHowliteWolfEntity.registerAttributes());
        setAttributes(evt, MALACHITE_GUARD, MalachiteGuardEntity.registerAttributes());
    }

    private static <E extends LivingEntity> void setAttributes(EntityAttributeCreationEvent evt, Supplier<EntityType<E>> entity, AttributeSupplier.Builder builder) {
        evt.put(entity.get(), builder.build());
    }

    public static void addStructureSpawns(StructureSpawnListGatherEvent evt) {
        if (evt.getStructure() == ModWorldgen.MALACHITE_WATCHTOWER.get()) {
            evt.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.MALACHITE_DRONE.get(), 10, 1, 1));
            evt.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.SHALURKER.get(), 5, 1, 2));
            evt.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.ARCHAIC_WARRIOR.get(), 8, 1, 2));
            evt.addEntitySpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(ModEntities.CAVERN_TICK.get(), 3, 2, 3));
        }
    }
}