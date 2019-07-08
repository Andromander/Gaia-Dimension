package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import net.minecraft.entity.*;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = GaiaDimension.MODID)
public class ModEntities {

    public static final EntityType<AgateArrowEntity> AGATE_ARROW = EntityType.Builder.create((EntityType.IFactory<AgateArrowEntity>) AgateArrowEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(GaiaEntityNames.AGATE_ARROW.toString());
    public static final EntityType<ThrownPebbleEntity> THROWN_PEBBLE = EntityType.Builder.create((EntityType.IFactory<ThrownPebbleEntity>) ThrownPebbleEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(GaiaEntityNames.THROWN_PEBBLE.toString());

    public static final EntityType<AgateGolemEntity> AGATE_GOLEM = EntityType.Builder.create(AgateGolemEntity::new, EntityClassification.MONSTER).size(1.2F, 2.7F).build(GaiaEntityNames.AGATE_GOLEM.toString());
    public static final EntityType<AncientLagrahkEntity> ANCIENT_LAGRAHK = EntityType.Builder.create(AncientLagrahkEntity::new, EntityClassification.MONSTER).size(1.5F, 4.0F).build(GaiaEntityNames.ANCIENT_LAGRAHK.toString());
    public static final EntityType<ArchaicWarriorEntity> ARCHAIC_WARRIOR = EntityType.Builder.create(ArchaicWarriorEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F).build(GaiaEntityNames.ARCHAIC_WARRIOR.toString());
    public static final EntityType<BismuthUletrusEntity> BISMUTH_ULETRUS = EntityType.Builder.create(BismuthUletrusEntity::new, EntityClassification.CREATURE).size(2.0F, 1.8F).build(GaiaEntityNames.BISMUTH_ULETRUS.toString());
    public static final EntityType<BlueHowliteWolfEntity> BLUE_HOWLITE_WOLF = EntityType.Builder.create(BlueHowliteWolfEntity::new, EntityClassification.MONSTER).size(1.2F, 2.2F).build(GaiaEntityNames.BLUE_HOWLITE_WOLF.toString());
    public static final EntityType<CavernTickEntity> CAVERN_TICK = EntityType.Builder.create(CavernTickEntity::new, EntityClassification.MONSTER).size(0.4F, 0.3F).build(GaiaEntityNames.CAVERN_TICK.toString());
    public static final EntityType<ContortedNagaEntity> CONTORTED_NAGA = EntityType.Builder.create(ContortedNagaEntity::new, EntityClassification.MONSTER).size(1.0F, 2.6F).build(GaiaEntityNames.CONTORTED_NAGA.toString());
    public static final EntityType<CorruptSapperEntity> CORRUPT_SAPPER = EntityType.Builder.create(CorruptSapperEntity::new, EntityClassification.MONSTER).size(1.0F, 1.0F).build(GaiaEntityNames.CORRUPT_SAPPER.toString());
    public static final EntityType<CrystalGolemEntity> CRYSTAL_GOLEM = EntityType.Builder.create(CrystalGolemEntity::new, EntityClassification.CREATURE).size(1.2F, 2.7F).build(GaiaEntityNames.CRYSTAL_GOLEM.toString());
    public static final EntityType<GrowthSapperEntity> GROWTH_SAPPER = EntityType.Builder.create(GrowthSapperEntity::new, EntityClassification.CREATURE).size(1.0F, 1.0F).build(GaiaEntityNames.GROWTH_SAPPER.toString());
    public static final EntityType<HowliteWolfEntity> HOWLITE_WOLF = EntityType.Builder.create(HowliteWolfEntity::new, EntityClassification.CREATURE).size(1.0F, 1.0F).build(GaiaEntityNames.HOWLITE_WOLF.toString());
    public static final EntityType<LesserShockshooterEntity> LESSER_SHOCKSHOOTER = EntityType.Builder.create(LesserShockshooterEntity::new, EntityClassification.MONSTER).size(0.5F, 2.0F).build(GaiaEntityNames.LESSER_SHOCKSHOOTER.toString());
    public static final EntityType<LesserSpitfireEntity> LESSER_SPITFIRE = EntityType.Builder.create(LesserSpitfireEntity::new, EntityClassification.MONSTER).size(0.5F, 2.0F).immuneToFire().build(GaiaEntityNames.LESSER_SPITFIRE.toString());
    public static final EntityType<MarkuzarPlantEntity> MARKUZAR_PLANT = EntityType.Builder.create(MarkuzarPlantEntity::new, EntityClassification.AMBIENT).size(0.6F, 2.0F).build(GaiaEntityNames.MARKUZAR_PLANT.toString());
    public static final EntityType<MineralArenthisEntity> MINERAL_ARENTHIS = EntityType.Builder.create(MineralArenthisEntity::new, EntityClassification.WATER_CREATURE).size(1.5F, 1.5F).build(GaiaEntityNames.MINERAL_ARENTHIS.toString());
    public static final EntityType<MucklingEntity> MUCKLING = EntityType.Builder.create(MucklingEntity::new, EntityClassification.MONSTER).size(2.0F, 2.0F).build(GaiaEntityNames.MUCKLING.toString());
    public static final EntityType<MutantGrowthExtractorEntity> MUTANT_EXTRACTOR = EntityType.Builder.create(MutantGrowthExtractorEntity::new, EntityClassification.CREATURE).size(1.0F, 1.5F).build(GaiaEntityNames.MUTANT_EXTRACTOR.toString());
    public static final EntityType<NomadicLagrahkEntity> NOMADIC_LAGRAHK = EntityType.Builder.create(NomadicLagrahkEntity::new, EntityClassification.CREATURE).size(1.5F, 4.0F).build(GaiaEntityNames.NOMADIC_LAGRAHK.toString());
    public static final EntityType<PrimalBeastEntity> PRIMAL_BEAST = EntityType.Builder.create(PrimalBeastEntity::new, EntityClassification.MONSTER).size(1.0F, 2.0F).immuneToFire().build(GaiaEntityNames.PRIMAL_BEAST.toString());
    public static final EntityType<RockyLuggerothEntity> ROCKY_LUGGEROTH = EntityType.Builder.create(RockyLuggerothEntity::new, EntityClassification.CREATURE).size(1.0F, 1.6F).build(GaiaEntityNames.ROCKY_LUGGEROTH.toString());
    public static final EntityType<RuggedLurmorusEntity> RUGGED_LURMORUS = EntityType.Builder.create(RuggedLurmorusEntity::new, EntityClassification.CREATURE).size(3.5F, 8.0F).build(GaiaEntityNames.RUGGED_LURMORUS.toString());
    public static final EntityType<SaltionEntity> SALTION = EntityType.Builder.create(SaltionEntity::new, EntityClassification.CREATURE).size(1.0F, 0.3F).build(GaiaEntityNames.SALTION.toString());
    public static final EntityType<ShallowArenthisEntity> SHALLOW_ARENTHIS = EntityType.Builder.create(ShallowArenthisEntity::new, EntityClassification.WATER_CREATURE).size(0.6F, 0.6F).build(GaiaEntityNames.SHALLOW_ARENTHIS.toString());
    public static final EntityType<ShalurkerEntity> SHALURKER = EntityType.Builder.create(ShalurkerEntity::new, EntityClassification.MONSTER).size(0.6F, 1.9F).build(GaiaEntityNames.SHALURKER.toString());
    public static final EntityType<SpellElementEntity> SPELLBOUND_ELEMENTAL = EntityType.Builder.create(SpellElementEntity::new, EntityClassification.CREATURE).size(0.5F, 2.0F).build(GaiaEntityNames.SPELLBOUND_ELEMENTAL.toString());

    public static final EntityType<MalachiteGuardEntity> MALACHITE_GUARD = EntityType.Builder.create(MalachiteGuardEntity::new, EntityClassification.MONSTER).size(0.8F, 3.3F).build(GaiaEntityNames.MALACHITE_GUARD.toString());

    /*public static final EntityLiving.SpawnPlacementType IN_LAVA = EnumHelper.addSpawnPlacementType("GD_IN_LAVA", (access, pos) -> {
        IBlockState iblockstate = access.getBlockState(pos);

        if (iblockstate.getMaterial() == Material.LAVA && access.getBlockState(pos.down()).getMaterial() == Material.LAVA && !access.getBlockState(pos.up()).isNormalCube()) {
            return true;
        } else {
            BlockPos blockpos = pos.down();
            IBlockState state = access.getBlockState(blockpos);

            if (!state.getBlock().canCreatureSpawn(state, access, blockpos, EntityLiving.SpawnPlacementType.ON_GROUND)) {
                return false;
            } else {
                Block block = access.getBlockState(blockpos).getBlock();
                boolean flag = block != Blocks.BEDROCK && block != Blocks.BARRIER;
                return flag && isValidEmptySpawnBlock(iblockstate) && isValidEmptySpawnBlock(access.getBlockState(pos.up()));
            }
        }
    });

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        EntityRegistryHelper entity = new EntityRegistryHelper(event.getRegistry());


        entity.registerEntity(GaiaEntityNames.THROWN_PEBBLE, ThrownPebbleEntity.class, ThrownPebbleEntity::new, 150, 2, true);
        entity.registerEntity(GaiaEntityNames.AGATE_ARROW, AgateArrowEntity.class, AgateArrowEntity::new, 150, 1, true);

        entity.registerEntity(GaiaEntityNames.GROWTH_SAPPER, GrowthSapperEntity.class, GrowthSapperEntity::new, 0x5A4514, 0xFF00FF);
        entity.registerEntity(GaiaEntityNames.MUTANT_EXTRACTOR, MutantGrowthExtractorEntity.class, MutantGrowthExtractorEntity::new, 0x5A4514, 0xFFFFCC);
        entity.registerEntity(GaiaEntityNames.HOWLITE_WOLF, HowliteWolfEntity.class, HowliteWolfEntity::new, 0xDDDDDD, 0x3333FF);
        entity.registerEntity(GaiaEntityNames.SPELLBOUND_ELEMENTAL, SpellElementEntity.class, SpellElementEntity::new, 0x885555, 0xCC33CC);
        entity.registerEntity(GaiaEntityNames.ROCKY_LUGGEROTH, RockyLuggerothEntity.class, RockyLuggerothEntity::new, 0xB07700, 0xCC9900);
        entity.registerEntity(GaiaEntityNames.SHALURKER, ShalurkerEntity.class, ShalurkerEntity::new, 0x771177, 0x000000);
        entity.registerEntity(GaiaEntityNames.MUCKLING, MucklingEntity.class, MucklingEntity::new, 0xFF00FF, 0xCC66CC);
        entity.registerEntity(GaiaEntityNames.MARKUZAR_PLANT, MarkuzarPlantEntity.class, MarkuzarPlantEntity::new, 0x00FF66, 0xCC00FF);
        entity.registerEntity(GaiaEntityNames.RUGGED_LURMORUS, RuggedLurmorusEntity.class, RuggedLurmorusEntity::new, 0xCC9933, 0xFF6600);
        entity.registerEntity(GaiaEntityNames.AGATE_GOLEM, AgateGolemEntity.class, AgateGolemEntity::new, 0x660000, 0xBB5555);
        entity.registerEntity(GaiaEntityNames.ANCIENT_LAGRAHK, AncientLagrahkEntity.class, AncientLagrahkEntity::new, 0x772200, 0xAA5500);
        entity.registerEntity(GaiaEntityNames.CRYSTAL_GOLEM, CrystalGolemEntity.class, CrystalGolemEntity::new, 0xFF66CC, 0xFF99CC);
        entity.registerEntity(GaiaEntityNames.SALTION, SaltionEntity.class, SaltionEntity::new, 0x6699FF, 0x6633FF);
        entity.registerEntity(GaiaEntityNames.NOMADIC_LAGRAHK, NomadicLagrahkEntity.class, NomadicLagrahkEntity::new, 0x3366CC, 0x232323);
        entity.registerEntity(GaiaEntityNames.SHALLOW_ARENTHIS, ShallowArenthisEntity.class, ShallowArenthisEntity::new, 0x6699CC, 0x003399);
        entity.registerEntity(GaiaEntityNames.CORRUPT_SAPPER, CorruptSapperEntity.class, CorruptSapperEntity::new, 0x202020, 0xCC3300);
        entity.registerEntity(GaiaEntityNames.CONTORTED_NAGA, ContortedNagaEntity.class, ContortedNagaEntity::new, 0x202020, 0xCC3300);
        entity.registerEntity(GaiaEntityNames.LESSER_SPITFIRE, LesserSpitfireEntity.class, LesserSpitfireEntity::new, 0xFF00FF, 0x202020);
        entity.registerEntity(GaiaEntityNames.LESSER_SHOCKSHOOTER, LesserShockshooterEntity.class, LesserShockshooterEntity::new, 0x00FFFF, 202020);
        entity.registerEntity(GaiaEntityNames.MINERAL_ARENTHIS, MineralArenthisEntity.class, MineralArenthisEntity::new, 0x0066CC, 0x000033);
        entity.registerEntity(GaiaEntityNames.BISMUTH_ULETRUS, BismuthUletrusEntity.class, BismuthUletrusEntity::new, 0x4E3863, 0x303030);
        entity.registerEntity(GaiaEntityNames.ARCHAIC_WARRIOR, ArchaicWarriorEntity.class, ArchaicWarriorEntity::new, 0x996699, 0xCC3366);
        entity.registerEntity(GaiaEntityNames.PRIMAL_BEAST, PrimalBeastEntity.class, PrimalBeastEntity::new, 0x006699, 0x66FFFF);
        entity.registerEntity(GaiaEntityNames.CAVERN_TICK, CavernTickEntity.class, CavernTickEntity::new, 0x9966CC, 0x666699);
        entity.registerEntity(GaiaEntityNames.BLUE_HOWLITE_WOLF, BlueHowliteWolfEntity.class, BlueHowliteWolfEntity::new, 0x0099CC, 0xCC00FF);

        entity.registerEntity(GaiaEntityNames.MALACHITE_GUARD, MalachiteGuardEntity.class, MalachiteGuardEntity::new, 0x339900, 0x33CC99);
    }

    static {
        EntitySpawnPlacementRegistry.setPlacementType(ShallowArenthisEntity.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(MineralArenthisEntity.class, EntityLiving.SpawnPlacementType.IN_WATER);
        EntitySpawnPlacementRegistry.setPlacementType(PrimalBeastEntity.class, IN_LAVA);
    }

    public static class EntityRegistryHelper {
        private final IForgeRegistry<EntityType<?>> registry;

        EntityRegistryHelper(IForgeRegistry<EntityType<?>> registry) {
            this.registry = registry;
        }

        private static String toString(ResourceLocation regName) {
            return regName.getNamespace() + "." + regName.getPath();
        }

        final <T extends Entity> EntityEntryBuilder<T> builder(ResourceLocation regName, Class<T> entity, Function<World, T> factory) {
            return EntityEntryBuilder.<T>create().id(regName, id++).name(toString(regName)).entity(entity).factory(factory);
        }

        //Create Entity with Spawn Egg
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour) {
            registerEntity(regName, entity, factory, backgroundEggColour, foregroundEggColour, 80, 3, true);
        }

        //Create Entity with Spawn Egg and Tracking Range
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
            registry.register(builder(regName, entity, factory).tracker(trackingRange, updateFrequency, sendsVelocityUpdates).egg(backgroundEggColour, foregroundEggColour).build());
        }
/*
        //Create Entity with no Spawn Egg
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory) {
            registerEntity(regName, entity, factory, 80, 3, true);
        }

        //Create Entity with no Spawn Egg and Tracking Range
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
            registry.register(builder(regName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).build());
        }
    }*/
}
