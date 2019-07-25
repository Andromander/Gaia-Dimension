package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import net.minecraft.entity.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
        final IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registry.register(AGATE_ARROW.setRegistryName("agate_arrow"));
        registry.register(THROWN_PEBBLE.setRegistryName("thrown_pebble"));
        registry.register(AGATE_GOLEM.setRegistryName("agate_golem"));
        registry.register(ANCIENT_LAGRAHK.setRegistryName("ancient_lagrahk"));
        registry.register(ARCHAIC_WARRIOR.setRegistryName("archaic_warrior"));
        registry.register(BISMUTH_ULETRUS.setRegistryName("bismuth_uletrus"));
        registry.register(BLUE_HOWLITE_WOLF.setRegistryName("blue_howlite_wolf"));
        registry.register(CAVERN_TICK.setRegistryName("cavern_tick"));
        registry.register(CONTORTED_NAGA.setRegistryName("contorted_naga"));
        registry.register(CORRUPT_SAPPER.setRegistryName("corrupt_sapper"));
        registry.register(CRYSTAL_GOLEM.setRegistryName("crystal_golem"));
        registry.register(GROWTH_SAPPER.setRegistryName("growth_sapper"));
        registry.register(HOWLITE_WOLF.setRegistryName("howlite_wolf"));
        registry.register(LESSER_SHOCKSHOOTER.setRegistryName("lesser_shockshooter"));
        registry.register(LESSER_SPITFIRE.setRegistryName("lesser_spitfire"));
        registry.register(MARKUZAR_PLANT.setRegistryName("markuzar_plant"));
        registry.register(MINERAL_ARENTHIS.setRegistryName("mineral_arenthis"));
        registry.register(MUCKLING.setRegistryName("muckling"));
        registry.register(MUTANT_EXTRACTOR.setRegistryName("mutant_growth_extractor"));
        registry.register(NOMADIC_LAGRAHK.setRegistryName("nomadic_lagrahk"));
        registry.register(PRIMAL_BEAST.setRegistryName("primal_beast"));
        registry.register(ROCKY_LUGGEROTH.setRegistryName("rocky_luggeroth"));
        registry.register(RUGGED_LURMORUS.setRegistryName("rugged_lurmorus"));
        registry.register(SALTION.setRegistryName("saltion"));
        registry.register(SHALLOW_ARENTHIS.setRegistryName("shallow_arenthis"));
        registry.register(SHALURKER.setRegistryName("shalurker"));
        registry.register(SPELLBOUND_ELEMENTAL.setRegistryName("spellbound_elemental"));
        registry.register(MALACHITE_GUARD.setRegistryName("malachite_guard"));
    }
}
