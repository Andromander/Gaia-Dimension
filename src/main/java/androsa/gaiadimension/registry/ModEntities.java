package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import androsa.gaiadimension.model.*;
import androsa.gaiadimension.renderer.*;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.register;

@ObjectHolder(value = GaiaDimensionMod.MODID)
public class ModEntities {

    //Projectiles
    public static final EntityType<AgateArrowEntity> AGATE_ARROW = registerProjectile("agate_arrow", AgateArrowEntity::new, true, 150, 1, 0.5F, 0.5F);
    public static final EntityType<ThrownPebbleEntity> THROWN_PEBBLE = registerProjectile("thrown_pebble", ThrownPebbleEntity::new, true, 150, 2, 0.25F, 0.25F);

    //Mobs
    public static final EntityType<AgateGolemEntity> AGATE_GOLEM = registerEntity("agate_golem", AgateGolemEntity::new, EntityClassification.MONSTER, 1.2F, 2.7F, false);
    public static final EntityType<AncientLagrahkEntity> ANCIENT_LAGRAHK = registerEntity("ancient_lagrahk", AncientLagrahkEntity::new, EntityClassification.MONSTER, 1.5F, 4.0F, false);
    public static final EntityType<ArchaicWarriorEntity> ARCHAIC_WARRIOR = registerEntity("archaic_warrior", ArchaicWarriorEntity::new, EntityClassification.MONSTER, 0.6F, 1.95F, false);
    public static final EntityType<BismuthUletrusEntity> BISMUTH_ULETRUS = registerEntity("bismuth_uletrus", BismuthUletrusEntity::new, EntityClassification.CREATURE, 2.0F, 1.8F, false);
    public static final EntityType<CavernTickEntity> CAVERN_TICK = registerEntity("cavern_tick", CavernTickEntity::new, EntityClassification.MONSTER, 0.4F, 0.3F, false);
    public static final EntityType<ContortedNagaEntity> CONTORTED_NAGA = registerEntity("contorted_naga", ContortedNagaEntity::new, EntityClassification.MONSTER, 1.0F, 2.6F, false);
    public static final EntityType<CorruptSapperEntity> CORRUPT_SAPPER = registerEntity("corrupt_sapper", CorruptSapperEntity::new, EntityClassification.MONSTER, 1.0F, 1.0F, false);
    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = registerEntity("crystal_golem", CrystalGolemEntity::new, EntityClassification.CREATURE, 1.2F, 2.7F, false);
    public static final EntityType<GrowthSapperEntity> GROWTH_SAPPER = registerEntity("growth_sapper", GrowthSapperEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F, false);
    public static final EntityType<HowliteWolfEntity> HOWLITE_WOLF = registerEntity("howlite_wolf", HowliteWolfEntity::new, EntityClassification.CREATURE, 1.0F, 1.0F, false);
    public static final EntityType<LesserShockshooterEntity> LESSER_SHOCKSHOOTER = registerEntity("lesser_shockshooter", LesserShockshooterEntity::new, EntityClassification.MONSTER, 0.5F, 2.0F, false);
    public static final EntityType<LesserSpitfireEntity> LESSER_SPITFIRE = registerEntity("lesser_spitfire", LesserSpitfireEntity::new, EntityClassification.MONSTER, 0.5F, 2.0F, true);
    public static final EntityType<MarkuzarPlantEntity> MARKUZAR_PLANT = registerEntity("markuzar_plant", MarkuzarPlantEntity::new, EntityClassification.AMBIENT, 0.6F, 2.0F, false);
    public static final EntityType<MineralArenthisEntity> MINERAL_ARENTHIS = registerEntity("mineral_arenthis", MineralArenthisEntity::new, EntityClassification.WATER_CREATURE, 1.5F, 1.5F, false);
    public static final EntityType<MucklingEntity> MUCKLING = registerEntity("muckling", MucklingEntity::new, EntityClassification.MONSTER, 2.0F, 2.0F, false);
    public static final EntityType<MutantGrowthExtractorEntity> MUTANT_GROWTH_EXTRACTOR = registerEntity("mutant_growth_extractor", MutantGrowthExtractorEntity::new, EntityClassification.CREATURE, 1.0F, 1.5F, false);
    public static final EntityType<NomadicLagrahkEntity> NOMADIC_LAGRAHK = registerEntity("nomadic_lagrahk", NomadicLagrahkEntity::new, EntityClassification.CREATURE, 1.5F, 4.0F, false);
    public static final EntityType<PrimalBeastEntity> PRIMAL_BEAST = registerEntity("primal_beast", PrimalBeastEntity::new, EntityClassification.MONSTER, 1.0F, 2.0F, true);
    public static final EntityType<RockyLuggerothEntity> ROCKY_LUGGEROTH = registerEntity("rocky_luggeroth", RockyLuggerothEntity::new, EntityClassification.CREATURE, 1.0F, 1.6F, false);
    public static final EntityType<RuggedLurmorusEntity> RUGGED_LURMORUS = registerEntity("rugged_lurmorus", RuggedLurmorusEntity::new, EntityClassification.CREATURE, 3.5F, 8.0F, false);
    public static final EntityType<SaltionEntity> SALTION = registerEntity("saltion", SaltionEntity::new, EntityClassification.CREATURE, 1.0F, 0.3F, false);
    public static final EntityType<ShallowArenthisEntity> SHALLOW_ARENTHIS = registerEntity("shallow_arenthis", ShallowArenthisEntity::new, EntityClassification.WATER_CREATURE, 0.6F, 0.6F, false);
    public static final EntityType<ShalurkerEntity> SHALURKER = registerEntity("shalurker", ShalurkerEntity::new, EntityClassification.MONSTER, 0.6F, 1.9F, false);
    public static final EntityType<SpellElementEntity> SPELLBOUND_ELEMENTAL = registerEntity("spellbound_elemental", SpellElementEntity::new, EntityClassification.CREATURE, 0.5F, 2.0F, false);
    public static final EntityType<MalachiteDroneEntity> MALACHITE_DRONE = registerEntity("malachite_drone", MalachiteDroneEntity::new, EntityClassification.MONSTER, 1.0F, 2.0F, false);

    //Mini Bosses
    public static final EntityType<BlueHowliteWolfEntity> BLUE_HOWLITE_WOLF = registerEntity("blue_howlite_wolf", BlueHowliteWolfEntity::new, EntityClassification.MONSTER, 1.2F, 2.2F, false);

    //Bosses
    public static final EntityType<MalachiteGuardEntity> MALACHITE_GUARD = registerEntity("malachite_guard", MalachiteGuardEntity::new, EntityClassification.MONSTER, 0.8F, 3.3F, false);

    public static <E extends Entity> EntityType<E> registerProjectile(String name, EntityType.IFactory<E> entity, boolean updates, int range, int interval, float width, float height) {
        EntityType<E> entitytype = makeBuilder(entity, EntityClassification.MISC, width, height)
                .setShouldReceiveVelocityUpdates(updates)
                .setTrackingRange(range)
                .setUpdateInterval(interval)
                .build(name);
        return RegistryHelper.registerEntity(name, entitytype);
    }

    public static <E extends Entity> EntityType<E> registerEntity(String name, EntityType.IFactory<E> entity, EntityClassification classification, float width, float height, boolean fireproof) {
        EntityType.Builder<E> type = EntityType.Builder.create(entity, classification).size(width, height);
        if (fireproof) type.immuneToFire();
        EntityType<E> entitytype = type.build(name);
        return RegistryHelper.registerEntity(name, entitytype);
    }

    private static <E extends Entity> EntityType.Builder<E> makeBuilder(EntityType.IFactory<E> entity, EntityClassification classification, float width, float height) {
        return EntityType.Builder.create(entity, classification).size(width, height);
    }

    /* Spawn Placements */
    public static PlacementType IN_LAVA = PlacementType.create("GD_IN_LAVA", (reader, pos, entity) -> {
        BlockState blockState = reader.getBlockState(pos);
        FluidState fluidState = reader.getFluidState(pos);
        BlockPos posUp = pos.up();
        BlockPos posDown = pos.down();

        if (fluidState.isTagged(FluidTags.LAVA) && reader.getFluidState(posDown).isTagged(FluidTags.LAVA) && !reader.getBlockState(posUp).isNormalCube(reader, posUp)) {
            return true;
        } else {
            BlockState state = reader.getBlockState(posDown);

            if (!state.canCreatureSpawn(reader, posDown, PlacementType.ON_GROUND, entity)) {
                return false;
            } else {
                return WorldEntitySpawner.func_234968_a_(reader, pos, blockState, fluidState, entity) && WorldEntitySpawner.func_234968_a_(reader, posUp, reader.getBlockState(posUp), reader.getFluidState(posUp), entity);
            }
        }
    });

    public static void registerSpawnPlacement() {
        register(AGATE_GOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        register(ANCIENT_LAGRAHK, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        register(ARCHAIC_WARRIOR, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        register(BISMUTH_ULETRUS, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        register(BLUE_HOWLITE_WOLF, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        register(CAVERN_TICK, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        register(CONTORTED_NAGA, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        register(CORRUPT_SAPPER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        register(CRYSTAL_GOLEM, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        register(GROWTH_SAPPER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        register(HOWLITE_WOLF, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        register(LESSER_SHOCKSHOOTER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        register(LESSER_SPITFIRE, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        register(MARKUZAR_PLANT, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        register(MINERAL_ARENTHIS, PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        register(MUCKLING, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        register(MUTANT_GROWTH_EXTRACTOR, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        register(NOMADIC_LAGRAHK, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        register(PRIMAL_BEAST, IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        register(ROCKY_LUGGEROTH, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        register(RUGGED_LURMORUS, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        register(SALTION, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        register(SHALLOW_ARENTHIS, PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        register(SHALURKER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        register(SPELLBOUND_ELEMENTAL, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
        register(MALACHITE_DRONE, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerEntityRender() {
        // RenderingRegistry.registerEntityRenderingHandler(GDShotGaianEnergy.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(THROWN_PEBBLE, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(AGATE_ARROW, AgateArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AGATE_GOLEM, m -> new AgateGolemRenderer<>(m, new AgateGolemModel<>(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(ANCIENT_LAGRAHK, m -> new AncientLagrahkRenderer<>(m, new AncientLagrahkModel<>(), 2.0F));
        RenderingRegistry.registerEntityRenderingHandler(ARCHAIC_WARRIOR, m -> new ArchaicWarriorRenderer<>(m, new ArchaicWarriorModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(BISMUTH_ULETRUS, m -> new BismuthUletrusRenderer<>(m, new BismuthUletrusModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(CAVERN_TICK, m -> new CavernTickRenderer<>(m, new CavernTickModel<>(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(CONTORTED_NAGA, m -> new ContortedNagaRenderer<>(m, new ContortedNagaModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(CORRUPT_SAPPER, m -> new CorruptSapperRenderer<>(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(CRYSTAL_GOLEM, m -> new CrystalGolemRenderer<>(m, new CrystalGolemModel<>(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(GROWTH_SAPPER, m -> new GrowthSapperRenderer<>(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(HOWLITE_WOLF, m -> new HowliteWolfRenderer<>(m, new HowliteWolfModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LESSER_SHOCKSHOOTER, m -> new LesserShockshooterRenderer<>(m, new LesserShockshooterModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LESSER_SPITFIRE, m -> new LesserSpitfireRenderer<>(m, new LesserSpitfireModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MARKUZAR_PLANT, m -> new MarkuzarPlantRenderer<>(m, new MarkuzarPlantModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MINERAL_ARENTHIS, m -> new MineralArenthisRenderer<>(m, new MineralArenthisModel<>(), 0.8F));
        RenderingRegistry.registerEntityRenderingHandler(MUCKLING, m -> new MucklingRenderer(m, 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(MUTANT_GROWTH_EXTRACTOR, m -> new GrowthExtractorRenderer<>(m, new GrowthExtractorModel<>(),0.8F));
        RenderingRegistry.registerEntityRenderingHandler(NOMADIC_LAGRAHK, m -> new NomadicLagrahkRenderer<>(m, new NomadicLagrahkModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(PRIMAL_BEAST, m -> new PrimalBeastRenderer<>(m, new PrimalBeastModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ROCKY_LUGGEROTH, m -> new RockyLuggerothRenderer<>(m, new RockyLuggerothModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(RUGGED_LURMORUS, m -> new RuggedLurmorusRenderer<>(m, new RuggedLurmorusModel<>(), 3.0F));
        RenderingRegistry.registerEntityRenderingHandler(SALTION, m -> new SaltionRenderer<>(m, new SaltionModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(SHALLOW_ARENTHIS, m -> new ShallowArenthisRenderer<>(m, new ShallowArenthisModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SHALURKER, m -> new ShalurkerRenderer<>(m, new ShalurkerModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SPELLBOUND_ELEMENTAL, m -> new SpellElementRenderer<>(m, new SpellElementModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MALACHITE_DRONE, m -> new MalachiteDroneRenderer<>(m, new MalachiteDroneModel<>(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(BLUE_HOWLITE_WOLF, m -> new BlueHowliteWolfRenderer<>(m, new BlueHowliteWolfModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(MALACHITE_GUARD, m -> new MalachiteGuardRenderer<>(m, new MalachiteGuardModel<>(0.0F), 0.7F));
    }

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(AGATE_GOLEM, AgateGolemEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ANCIENT_LAGRAHK, AncientLagrahkEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ARCHAIC_WARRIOR, ArchaicWarriorEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(BISMUTH_ULETRUS, BismuthUletrusEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(CAVERN_TICK, CavernTickEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(CONTORTED_NAGA, ContortedNagaEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(CORRUPT_SAPPER, CorruptSapperEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(CRYSTAL_GOLEM, CrystalGolemEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(GROWTH_SAPPER, GrowthSapperEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(HOWLITE_WOLF, HowliteWolfEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(LESSER_SHOCKSHOOTER, LesserShockshooterEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(LESSER_SPITFIRE, LesserSpitfireEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MARKUZAR_PLANT, MarkuzarPlantEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MINERAL_ARENTHIS, MineralArenthisEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MUCKLING, MucklingEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MUTANT_GROWTH_EXTRACTOR, MutantGrowthExtractorEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(NOMADIC_LAGRAHK, NomadicLagrahkEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(PRIMAL_BEAST, PrimalBeastEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(ROCKY_LUGGEROTH, RockyLuggerothEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(RUGGED_LURMORUS, RuggedLurmorusEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SALTION, SaltionEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SHALLOW_ARENTHIS, ShallowArenthisEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SHALURKER, ShalurkerEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(SPELLBOUND_ELEMENTAL, SpellElementEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MALACHITE_DRONE, MalachiteDroneEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(BLUE_HOWLITE_WOLF, BlueHowliteWolfEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(MALACHITE_GUARD, MalachiteGuardEntity.registerAttributes().create());
    }
}