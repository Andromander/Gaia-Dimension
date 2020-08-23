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
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.entity.EntitySpawnPlacementRegistry.register;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, GaiaDimensionMod.MODID);

    public static final EntityType<AgateArrowEntity> agate_arrow = EntityType.Builder.<AgateArrowEntity>create(AgateArrowEntity::new, EntityClassification.MISC)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(150).setUpdateInterval(1)
                    .size(0.5F, 0.5F).build("agate_arrow");
    public static final EntityType<ThrownPebbleEntity> thrown_pebble = EntityType.Builder.<ThrownPebbleEntity>create(ThrownPebbleEntity::new, EntityClassification.MISC)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(150).setUpdateInterval(2)
                    .size(0.25F, 0.25F).build("thrown_pebble");

    public static final EntityType<AgateGolemEntity> agate_golem = EntityType.Builder.create(AgateGolemEntity::new, EntityClassification.MONSTER)
            .size(1.2F, 2.7F).build("agate_golem");
    public static final EntityType<AncientLagrahkEntity> ancient_lagrahk = EntityType.Builder.create(AncientLagrahkEntity::new, EntityClassification.MONSTER)
            .size(1.5F, 4.0F).build("ancient_lagrahk");
    public static final EntityType<ArchaicWarriorEntity> archaic_warrior = EntityType.Builder.create(ArchaicWarriorEntity::new, EntityClassification.MONSTER)
            .size(0.6F, 1.95F).build("archaic_warrior");
    public static final EntityType<BismuthUletrusEntity> bismuth_uletrus = EntityType.Builder.create(BismuthUletrusEntity::new, EntityClassification.CREATURE)
            .size(2.0F, 1.8F).build("bismuth_uletrus");
    public static final EntityType<CavernTickEntity> cavern_tick = EntityType.Builder.create(CavernTickEntity::new, EntityClassification.MONSTER)
            .size(0.4F, 0.3F).build("cavern_tick");
    public static final EntityType<ContortedNagaEntity> contorted_naga = EntityType.Builder.create(ContortedNagaEntity::new, EntityClassification.MONSTER)
            .size(1.0F, 2.6F).build("contorted_naga");
    public static final EntityType<CorruptSapperEntity> corrupt_sapper = EntityType.Builder.create(CorruptSapperEntity::new, EntityClassification.MONSTER)
            .size(1.0F, 1.0F).build("corrupt_sapper");
    public static final EntityType<CrystalGolemEntity> crystal_golem = EntityType.Builder.create(CrystalGolemEntity::new, EntityClassification.CREATURE)
            .size(1.2F, 2.7F).build("crystal_golem");
    public static final EntityType<GrowthSapperEntity> growth_sapper = EntityType.Builder.create(GrowthSapperEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 1.0F).build("growth_sapper");
    public static final EntityType<HowliteWolfEntity> howlite_wolf = EntityType.Builder.create(HowliteWolfEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 1.0F).build("howlite_wolf");
    public static final EntityType<LesserShockshooterEntity> lesser_shockshooter = EntityType.Builder.create(LesserShockshooterEntity::new, EntityClassification.MONSTER)
            .size(0.5F, 2.0F).build("lesser_shockshooter");
    public static final EntityType<LesserSpitfireEntity> lesser_spitfire = EntityType.Builder.create(LesserSpitfireEntity::new, EntityClassification.MONSTER)
            .size(0.5F, 2.0F).immuneToFire().build("lesser_spitfire");
    public static final EntityType<MarkuzarPlantEntity> markuzar_plant = EntityType.Builder.create(MarkuzarPlantEntity::new, EntityClassification.AMBIENT)
            .size(0.6F, 2.0F).build("markuzar_plant");
    public static final EntityType<MineralArenthisEntity> mineral_arenthis = EntityType.Builder.create(MineralArenthisEntity::new, EntityClassification.WATER_CREATURE)
            .size(1.5F, 1.5F).build("mineral_arenthis");
    public static final EntityType<MucklingEntity> muckling = EntityType.Builder.create(MucklingEntity::new, EntityClassification.MONSTER)
            .size(2.0F, 2.0F).build("muckling");
    public static final EntityType<MutantGrowthExtractorEntity> growth_extractor = EntityType.Builder.create(MutantGrowthExtractorEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 1.5F).build("mutant_growth_extractor");
    public static final EntityType<NomadicLagrahkEntity> nomadic_lagrahk = EntityType.Builder.create(NomadicLagrahkEntity::new, EntityClassification.CREATURE)
            .size(1.5F, 4.0F).build("nomadic_lagrahk");
    public static final EntityType<PrimalBeastEntity> primal_beast = EntityType.Builder.create(PrimalBeastEntity::new, EntityClassification.MONSTER)
            .size(1.0F, 2.0F).immuneToFire().build("primal_beast");
    public static final EntityType<RockyLuggerothEntity> rocky_luggeroth = EntityType.Builder.create(RockyLuggerothEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 1.6F).build("rocky_luggeroth");
    public static final EntityType<RuggedLurmorusEntity> rugged_lurmorus = EntityType.Builder.create(RuggedLurmorusEntity::new, EntityClassification.CREATURE)
            .size(3.5F, 8.0F).build("rugged_lurmorus");
    public static final EntityType<SaltionEntity> saltion = EntityType.Builder.create(SaltionEntity::new, EntityClassification.CREATURE)
            .size(1.0F, 0.3F).build("saltion");
    public static final EntityType<ShallowArenthisEntity> shallow_arenthis = EntityType.Builder.create(ShallowArenthisEntity::new, EntityClassification.WATER_CREATURE)
            .size(0.6F, 0.6F).build("shallow_arenthis");
    public static final EntityType<ShalurkerEntity> shalurker = EntityType.Builder.create(ShalurkerEntity::new, EntityClassification.MONSTER)
            .size(0.6F, 1.9F).build("shalurker");
    public static final EntityType<SpellElementEntity> spell_elemental = EntityType.Builder.create(SpellElementEntity::new, EntityClassification.CREATURE)
            .size(0.5F, 2.0F).build("spellbound_elemental");
    public static final EntityType<MalachiteDroneEntity> malachite_drone = EntityType.Builder.create(MalachiteDroneEntity::new, EntityClassification.MONSTER)
            .size(1.0F, 2.0F).build("malachite_drone");
    public static final EntityType<BlueHowliteWolfEntity> blue_howlite_wolf = EntityType.Builder.create(BlueHowliteWolfEntity::new, EntityClassification.MONSTER)
            .size(1.2F, 2.2F).build("blue_howlite_wolf");
    public static final EntityType<MalachiteGuardEntity> malachite_guard = EntityType.Builder.create(MalachiteGuardEntity::new, EntityClassification.MONSTER)
            .size(0.8F, 3.3F).build("malachite_guard");

    //Projectiles
    public static final RegistryObject<EntityType<AgateArrowEntity>> AGATE_ARROW = ENTITIES.register("agate_arrow", () -> agate_arrow);
    public static final RegistryObject<EntityType<ThrownPebbleEntity>> THROWN_PEBBLE = ENTITIES.register("thrown_pebble", () -> thrown_pebble);

    //Mobs
    public static final RegistryObject<EntityType<AgateGolemEntity>> AGATE_GOLEM = ENTITIES.register("agate_golem", () -> agate_golem);
    public static final RegistryObject<EntityType<AncientLagrahkEntity>> ANCIENT_LAGRAHK = ENTITIES.register("ancient_lagrahk", () -> ancient_lagrahk);
    public static final RegistryObject<EntityType<ArchaicWarriorEntity>> ARCHAIC_WARRIOR = ENTITIES.register("archaic_warrior", () -> archaic_warrior);
    public static final RegistryObject<EntityType<BismuthUletrusEntity>> BISMUTH_ULETRUS = ENTITIES.register("bismuth_uletrus", () -> bismuth_uletrus);
    public static final RegistryObject<EntityType<CavernTickEntity>> CAVERN_TICK = ENTITIES.register("cavern_tick", () -> cavern_tick);
    public static final RegistryObject<EntityType<ContortedNagaEntity>> CONTORTED_NAGA = ENTITIES.register("contorted_naga", () -> contorted_naga);
    public static final RegistryObject<EntityType<CorruptSapperEntity>> CORRUPT_SAPPER = ENTITIES.register("corrupt_sapper", () -> corrupt_sapper);
    public static final RegistryObject<EntityType<CrystalGolemEntity>> CRYSTAL_GOLEM = ENTITIES.register("crystal_golem", () -> crystal_golem);
    public static final RegistryObject<EntityType<GrowthSapperEntity>> GROWTH_SAPPER = ENTITIES.register("growth_sapper", () -> growth_sapper);
    public static final RegistryObject<EntityType<HowliteWolfEntity>> HOWLITE_WOLF = ENTITIES.register("howlite_wolf", () -> howlite_wolf);
    public static final RegistryObject<EntityType<LesserShockshooterEntity>> LESSER_SHOCKSHOOTER = ENTITIES.register("lesser_shockshooter", () -> lesser_shockshooter);
    public static final RegistryObject<EntityType<LesserSpitfireEntity>> LESSER_SPITFIRE = ENTITIES.register("lesser_spitfire", () -> lesser_spitfire);
    public static final RegistryObject<EntityType<MarkuzarPlantEntity>> MARKUZAR_PLANT = ENTITIES.register("markuzar_plant", () -> markuzar_plant);
    public static final RegistryObject<EntityType<MineralArenthisEntity>> MINERAL_ARENTHIS = ENTITIES.register("mineral_arenthis", () -> mineral_arenthis);
    public static final RegistryObject<EntityType<MucklingEntity>> MUCKLING = ENTITIES.register("muckling", () -> muckling);
    public static final RegistryObject<EntityType<MutantGrowthExtractorEntity>> MUTANT_GROWTH_EXTRACTOR = ENTITIES.register("mutant_growth_extractor", () -> growth_extractor);
    public static final RegistryObject<EntityType<NomadicLagrahkEntity>> NOMADIC_LAGRAHK = ENTITIES.register("nomadic_lagrahk", () -> nomadic_lagrahk);
    public static final RegistryObject<EntityType<PrimalBeastEntity>> PRIMAL_BEAST = ENTITIES.register("primal_beast", () -> primal_beast);
    public static final RegistryObject<EntityType<RockyLuggerothEntity>> ROCKY_LUGGEROTH = ENTITIES.register("rocky_luggeroth", () -> rocky_luggeroth);
    public static final RegistryObject<EntityType<RuggedLurmorusEntity>> RUGGED_LURMORUS = ENTITIES.register("rugged_lurmorus", () -> rugged_lurmorus);
    public static final RegistryObject<EntityType<SaltionEntity>> SALTION = ENTITIES.register("saltion", () -> saltion);
    public static final RegistryObject<EntityType<ShallowArenthisEntity>> SHALLOW_ARENTHIS = ENTITIES.register("shallow_arenthis", () -> shallow_arenthis);
    public static final RegistryObject<EntityType<ShalurkerEntity>> SHALURKER = ENTITIES.register("shalurker", () -> shalurker);
    public static final RegistryObject<EntityType<SpellElementEntity>> SPELLBOUND_ELEMENTAL = ENTITIES.register("spellbound_elemental", () -> spell_elemental);
    public static final RegistryObject<EntityType<MalachiteDroneEntity>> MALACHITE_DRONE = ENTITIES.register("malachite_drone", () -> malachite_drone);

    //Mini Bosses
    public static final RegistryObject<EntityType<BlueHowliteWolfEntity>> BLUE_HOWLITE_WOLF = ENTITIES.register("blue_howlite_wolf", () -> blue_howlite_wolf);

    //Bosses
    public static final RegistryObject<EntityType<MalachiteGuardEntity>> MALACHITE_GUARD = ENTITIES.register("malachite_guard", () -> malachite_guard);

    /* Spawn Placements */
    public static final PlacementType IN_LAVA = PlacementType.create("GD_IN_LAVA", (reader, pos, entity) -> {
        BlockState blockState = reader.getBlockState(pos);
        IFluidState fluidState = reader.getFluidState(pos);
        BlockPos posUp = pos.up();
        BlockPos posDown = pos.down();

        if (fluidState.isTagged(FluidTags.LAVA) && reader.getFluidState(posDown).isTagged(FluidTags.LAVA) && !reader.getBlockState(posUp).isNormalCube(reader, posUp)) {
            return true;
        } else {
            BlockState state = reader.getBlockState(posDown);

            if (!state.canCreatureSpawn(reader, posDown, PlacementType.ON_GROUND, entity)) {
                return false;
            } else {
                return WorldEntitySpawner.isSpawnableSpace(reader, pos, blockState, fluidState) && WorldEntitySpawner.isSpawnableSpace(reader, posUp, reader.getBlockState(posUp), reader.getFluidState(posUp));
            }
        }
    });

    public static void registerSpawnPlacement() {
        register(AGATE_GOLEM.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AgateGolemEntity::canSpawnHere);
        register(ANCIENT_LAGRAHK.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AncientLagrahkEntity::canSpawnHere);
        register(ARCHAIC_WARRIOR.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
        register(BISMUTH_ULETRUS.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BismuthUletrusEntity::canSpawnHere);
        register(BLUE_HOWLITE_WOLF.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BlueHowliteWolfEntity::canSpawnHere);
        register(CAVERN_TICK.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
        register(CONTORTED_NAGA.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ContortedNagaEntity::canSpawnHere);
        register(CORRUPT_SAPPER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CorruptSapperEntity::canSpawnHere);
        register(CRYSTAL_GOLEM.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalGolemEntity::canSpawnHere);
        register(GROWTH_SAPPER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GrowthSapperEntity::canSpawnHere);
        register(HOWLITE_WOLF.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HowliteWolfEntity::canSpawnHere);
        register(LESSER_SHOCKSHOOTER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserShockshooterEntity::canSpawnHere);
        register(LESSER_SPITFIRE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LesserSpitfireEntity::canSpawnHere);
        register(MARKUZAR_PLANT.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MarkuzarPlantEntity::canSpawnHere);
        register(MINERAL_ARENTHIS.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MineralArenthisEntity::canSpawnHere);
        register(MUCKLING.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MucklingEntity::canSpawnHere);
        register(MUTANT_GROWTH_EXTRACTOR.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MutantGrowthExtractorEntity::canSpawnHere);
        register(NOMADIC_LAGRAHK.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, NomadicLagrahkEntity::canSpawnHere);
        register(PRIMAL_BEAST.get(), IN_LAVA, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PrimalBeastEntity::canSpawnHere);
        register(ROCKY_LUGGEROTH.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RockyLuggerothEntity::canSpawnHere);
        register(RUGGED_LURMORUS.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, RuggedLurmorusEntity::canSpawnHere);
        register(SALTION.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SaltionEntity::canSpawnHere);
        register(SHALLOW_ARENTHIS.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShallowArenthisEntity::canSpawnHere);
        register(SHALURKER.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223325_c);
        register(SPELLBOUND_ELEMENTAL.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpellElementEntity::canSpawnHere);
        register(MALACHITE_DRONE.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::func_223324_d);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerEntityRender() {
        // RenderingRegistry.registerEntityRenderingHandler(GDShotGaianEnergy.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(THROWN_PEBBLE.get(), m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(AGATE_ARROW.get(), AgateArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AGATE_GOLEM.get(), m -> new AgateGolemRenderer<>(m, new AgateGolemModel<>(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(ANCIENT_LAGRAHK.get(), m -> new AncientLagrahkRenderer<>(m, new AncientLagrahkModel<>(), 2.0F));
        RenderingRegistry.registerEntityRenderingHandler(ARCHAIC_WARRIOR.get(), m -> new ArchaicWarriorRenderer<>(m, new ArchaicWarriorModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(BISMUTH_ULETRUS.get(), m -> new BismuthUletrusRenderer<>(m, new BismuthUletrusModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(CAVERN_TICK.get(), m -> new CavernTickRenderer<>(m, new CavernTickModel<>(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(CONTORTED_NAGA.get(), m -> new ContortedNagaRenderer<>(m, new ContortedNagaModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(CORRUPT_SAPPER.get(), m -> new CorruptSapperRenderer<>(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(CRYSTAL_GOLEM.get(), m -> new CrystalGolemRenderer<>(m, new CrystalGolemModel<>(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(GROWTH_SAPPER.get(), m -> new GrowthSapperRenderer<>(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(HOWLITE_WOLF.get(), m -> new HowliteWolfRenderer<>(m, new HowliteWolfModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LESSER_SHOCKSHOOTER.get(), m -> new LesserShockshooterRenderer<>(m, new LesserShockshooterModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LESSER_SPITFIRE.get(), m -> new LesserSpitfireRenderer<>(m, new LesserSpitfireModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MARKUZAR_PLANT.get(), m -> new MarkuzarPlantRenderer<>(m, new MarkuzarPlantModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MINERAL_ARENTHIS.get(), m -> new MineralArenthisRenderer<>(m, new MineralArenthisModel<>(), 0.8F));
        RenderingRegistry.registerEntityRenderingHandler(MUCKLING.get(), m -> new MucklingRenderer(m, 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(MUTANT_GROWTH_EXTRACTOR.get(), m -> new GrowthExtractorRenderer<>(m, new GrowthExtractorModel<>(),0.8F));
        RenderingRegistry.registerEntityRenderingHandler(NOMADIC_LAGRAHK.get(), m -> new NomadicLagrahkRenderer<>(m, new NomadicLagrahkModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(PRIMAL_BEAST.get(), m -> new PrimalBeastRenderer<>(m, new PrimalBeastModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ROCKY_LUGGEROTH.get(), m -> new RockyLuggerothRenderer<>(m, new RockyLuggerothModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(RUGGED_LURMORUS.get(), m -> new RuggedLurmorusRenderer<>(m, new RuggedLurmorusModel<>(), 3.0F));
        RenderingRegistry.registerEntityRenderingHandler(SALTION.get(), m -> new SaltionRenderer<>(m, new SaltionModel<>(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(SHALLOW_ARENTHIS.get(), m -> new ShallowArenthisRenderer<>(m, new ShallowArenthisModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SHALURKER.get(), m -> new ShalurkerRenderer<>(m, new ShalurkerModel<>(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SPELLBOUND_ELEMENTAL.get(), m -> new SpellElementRenderer<>(m, new SpellElementModel<>(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(MALACHITE_DRONE.get(), m -> new MalachiteDroneRenderer<>(m, new MalachiteDroneModel<>(), 0.5F));

        RenderingRegistry.registerEntityRenderingHandler(BLUE_HOWLITE_WOLF.get(), m -> new BlueHowliteWolfRenderer<>(m, new BlueHowliteWolfModel<>(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(MALACHITE_GUARD.get(), m -> new MalachiteGuardRenderer<>(m, new MalachiteGuardModel<>(0.0F), 0.7F));
    }
}