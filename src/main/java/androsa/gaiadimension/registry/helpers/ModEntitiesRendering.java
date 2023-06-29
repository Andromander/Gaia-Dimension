package androsa.gaiadimension.registry.helpers;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.model.*;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.renderer.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntitiesRendering {
    public static final ModelLayerLocation AGATE_GOLEM = baselayer("agate_golem");
    public static final ModelLayerLocation ANCIENT_LAGRAHK = baselayer("ancient_lagrahk");
    public static final ModelLayerLocation ARCHAIC_WARRIOR = baselayer("archaic_warrior");
    public static final ModelLayerLocation BISMUTH_ULETRUS = baselayer("bismuth_uletrus");
    public static final ModelLayerLocation CAVERN_TICK = baselayer("cavern_tick");
    public static final ModelLayerLocation CONTORTED_NAGA = baselayer("contorted_naga");
    public static final ModelLayerLocation CRYSTAL_GOLEM = baselayer("crystal_golem");
    public static final ModelLayerLocation GROWTH_SAPPER = baselayer("growth_sapper");
    public static final ModelLayerLocation HOWLITE_WOLF = baselayer("howlite_wolf");
    public static final ModelLayerLocation LESSER_SHOCKSHOOTER = baselayer("lesser_shockshooter");
    public static final ModelLayerLocation LESSER_SPITFIRE = baselayer("lesser_spitfire");
    public static final ModelLayerLocation MARKUZAR_PLANT = baselayer("markuzar_plant");
    public static final ModelLayerLocation MINERAL_ARENTHIS = baselayer("mineral_arenthis");
    public static final ModelLayerLocation MUTANT_GROWTH_EXTRACTOR = baselayer("mutant_growth_extractor");
    public static final ModelLayerLocation NOMADIC_LAGRAHK = baselayer("nomadic_lagrahk");
    public static final ModelLayerLocation PRIMAL_BEAST = baselayer("primal_beast");
    public static final ModelLayerLocation ROCKY_LUGGEROTH = baselayer("rocky_luggeroth");
    public static final ModelLayerLocation RUGGED_LURMORUS = baselayer("rugged_lurmorus");
    public static final ModelLayerLocation SALTION = baselayer("saltion");
    public static final ModelLayerLocation SHALLOW_ARENTHIS = baselayer("shallow_arenthis");
    public static final ModelLayerLocation SHALURKER = baselayer("shalurker");
    public static final ModelLayerLocation SPELLBOUND_ELEMENTAL = baselayer("spellbound_elemental");
    public static final ModelLayerLocation MALACHITE_DRONE = baselayer("malachite_drone");
    public static final ModelLayerLocation MOOKAITE_CONSTRUCT = baselayer("mookaite_construct");
    public static final ModelLayerLocation OPALITE_CONSTRUCT = baselayer("opalite_construct");

    public static final ModelLayerLocation BLUE_HOWLITE_WOLF = baselayer("blue_howlite_wolf");

    public static final ModelLayerLocation MALACHITE_GUARD = baselayer("malachite_guard");
    public static final ModelLayerLocation MALACHITE_GUARD_DEFENCE = layer("malachite_guard", "defence");
    public static final ModelLayerLocation MALACHITE_GUARD_RESIST = layer("malachite_guard", "resist");

    public static final String TEXTURE_DIRECTORY = "textures/entity/";

    private static ModelLayerLocation baselayer(String name) {
        return layer(name, "main");
    }

    private static ModelLayerLocation layer(String name, String layer) {
        return new ModelLayerLocation(new ResourceLocation(GaiaDimensionMod.MODID, name), layer);
    }

    public static ResourceLocation makeTexture(Entity entity, String path) {
        String name = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).getPath();
        String dir = path.isEmpty() ? name : name + "_" + path;
        return makeTexture(name + "/" + dir);
    }

    public static ResourceLocation makeTextureNoPrefix(Entity entity, String path) {
        String name = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).getPath();
        return makeTexture(name + "/" + path);
    }

    public static ResourceLocation makeTexture(Entity entity) {
        return makeTexture(ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()).getPath());
    }

    public static ResourceLocation makeTexture(String path) {
        return new ResourceLocation(GaiaDimensionMod.MODID, TEXTURE_DIRECTORY + path + ".png");
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AGATE_GOLEM, AgateGolemModel::makeBodyLayer);
        event.registerLayerDefinition(ANCIENT_LAGRAHK, AncientLagrahkModel::makeBodyLayer);
        event.registerLayerDefinition(ARCHAIC_WARRIOR, ArchaicWarriorModel::makeBodyLayer);
        event.registerLayerDefinition(BISMUTH_ULETRUS, BismuthUletrusModel::makeBodyLayer);
        event.registerLayerDefinition(CAVERN_TICK, CavernTickModel::makeBodyLayer);
        event.registerLayerDefinition(CONTORTED_NAGA, ContortedNagaModel::makeBodyLayer);
        event.registerLayerDefinition(CRYSTAL_GOLEM, CrystalGolemModel::makeBodyLayer);
        event.registerLayerDefinition(GROWTH_SAPPER, GrowthSapperModel::makeBodyLayer);
        event.registerLayerDefinition(HOWLITE_WOLF, HowliteWolfModel::makeBodyLayer);
        event.registerLayerDefinition(LESSER_SHOCKSHOOTER, LesserShockshooterModel::makeBodyLayer);
        event.registerLayerDefinition(LESSER_SPITFIRE, LesserSpitfireModel::makeBodyLayer);
        event.registerLayerDefinition(MARKUZAR_PLANT, MarkuzarPlantModel::makeBodyLayer);
        event.registerLayerDefinition(MINERAL_ARENTHIS, MineralArenthisModel::makeBodyLayer);
        event.registerLayerDefinition(MUTANT_GROWTH_EXTRACTOR, GrowthExtractorModel::makeBodyLayer);
        event.registerLayerDefinition(NOMADIC_LAGRAHK, NomadicLagrahkModel::makeBodyLayer);
        event.registerLayerDefinition(PRIMAL_BEAST, PrimalBeastModel::makeBodyLayer);
        event.registerLayerDefinition(ROCKY_LUGGEROTH, RockyLuggerothModel::makeBodyLayer);
        event.registerLayerDefinition(RUGGED_LURMORUS, RuggedLurmorusModel::makeBodyLayer);
        event.registerLayerDefinition(SALTION, SaltionModel::makeBodyLayer);
        event.registerLayerDefinition(SHALLOW_ARENTHIS, ShallowArenthisModel::makeBodyLayer);
        event.registerLayerDefinition(SHALURKER, ShalurkerModel::makeBodyLayer);
        event.registerLayerDefinition(SPELLBOUND_ELEMENTAL, SpellElementModel::makeBodyLayer);
        event.registerLayerDefinition(MALACHITE_DRONE, MalachiteDroneModel::makeBodyLayer);
        event.registerLayerDefinition(MOOKAITE_CONSTRUCT, MookaiteConstructModel::makeBodyLayer);
        event.registerLayerDefinition(OPALITE_CONSTRUCT, OpaliteConstructModel::makeBodyLayer);

        event.registerLayerDefinition(BLUE_HOWLITE_WOLF, BlueHowliteWolfModel::makeBodyLayer);

        event.registerLayerDefinition(MALACHITE_GUARD, () -> MalachiteGuardModel.makeBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(MALACHITE_GUARD_DEFENCE, () -> MalachiteGuardModel.makeBodyLayer(new CubeDeformation(0.5F)));
        event.registerLayerDefinition(MALACHITE_GUARD_RESIST, () -> MalachiteGuardModel.makeBodyLayer(new CubeDeformation(0.25F)));
    }

    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        // RenderingRegistry.registerEntityRenderingHandler(GDShotGaianEnergy.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
        event.registerEntityRenderer(ModEntities.THROWN_PEBBLE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.AGATE_ARROW.get(), AgateArrowRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOKAITE_AMMO_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOKAITE_MAGIC_BULLET.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.MOOKAITE_MAGIC_AREA.get(), NoopRenderer::new);

        event.registerEntityRenderer(ModEntities.AGATE_GOLEM.get(), m -> new BasicEntityRenderer<>(m, new AgateGolemModel<>(m.bakeLayer(AGATE_GOLEM)), 0.9F));
        event.registerEntityRenderer(ModEntities.ANCIENT_LAGRAHK.get(), m -> new BasicEntityRenderer<>(m, new AncientLagrahkModel<>(m.bakeLayer(ANCIENT_LAGRAHK)), 2.0F));
        event.registerEntityRenderer(ModEntities.ARCHAIC_WARRIOR.get(), m -> new ArchaicWarriorRenderer<>(m, new ArchaicWarriorModel<>(m.bakeLayer(ARCHAIC_WARRIOR)), 0.5F));
        event.registerEntityRenderer(ModEntities.BISMUTH_ULETRUS.get(), m -> new BasicEntityRenderer<>(m, new BismuthUletrusModel<>(m.bakeLayer(BISMUTH_ULETRUS)), 1.0F));
        event.registerEntityRenderer(ModEntities.CAVERN_TICK.get(), m -> new BasicEntityRenderer<>(m, new CavernTickModel<>(m.bakeLayer(CAVERN_TICK)), 0.2F));
        event.registerEntityRenderer(ModEntities.CONTORTED_NAGA.get(), m -> new BasicEntityRenderer<>(m, new ContortedNagaModel<>(m.bakeLayer(CONTORTED_NAGA)), 0.7F));
        event.registerEntityRenderer(ModEntities.CORRUPT_SAPPER.get(), m -> new BasicEntityRenderer<>(m, new GrowthSapperModel<>(m.bakeLayer(GROWTH_SAPPER)), 0.6F));
        event.registerEntityRenderer(ModEntities.CRYSTAL_GOLEM.get(), m -> new BasicEntityRenderer<>(m, new CrystalGolemModel<>(m.bakeLayer(CRYSTAL_GOLEM)), 0.9F));
        event.registerEntityRenderer(ModEntities.GROWTH_SAPPER.get(), m -> new GrowthSapperRenderer<>(m, new GrowthSapperModel<>(m.bakeLayer(GROWTH_SAPPER)), 0.6F));
        event.registerEntityRenderer(ModEntities.HOWLITE_WOLF.get(), m -> new BasicEntityRenderer<>(m, new HowliteWolfModel<>(m.bakeLayer(HOWLITE_WOLF)), 0.5F));
        event.registerEntityRenderer(ModEntities.LESSER_SHOCKSHOOTER.get(), m -> new BasicEntityRenderer<>(m, new LesserShockshooterModel<>(m.bakeLayer(LESSER_SHOCKSHOOTER)), 0.5F));
        event.registerEntityRenderer(ModEntities.LESSER_SPITFIRE.get(), m -> new BasicEntityRenderer<>(m, new LesserSpitfireModel<>(m.bakeLayer(LESSER_SPITFIRE)), 0.5F));
        event.registerEntityRenderer(ModEntities.MARKUZAR_PLANT.get(), m -> new BasicEntityRenderer<>(m, new MarkuzarPlantModel<>(m.bakeLayer(MARKUZAR_PLANT)), 0.5F));
        event.registerEntityRenderer(ModEntities.MINERAL_ARENTHIS.get(), m -> new MineralArenthisRenderer<>(m, new MineralArenthisModel<>(m.bakeLayer(MINERAL_ARENTHIS)), 0.8F));
        event.registerEntityRenderer(ModEntities.MUCKLING.get(), m -> new MucklingRenderer(m, 0.625F));
        event.registerEntityRenderer(ModEntities.MUTANT_GROWTH_EXTRACTOR.get(), m -> new BasicEntityRenderer<>(m, new GrowthExtractorModel<>(m.bakeLayer(MUTANT_GROWTH_EXTRACTOR)),0.8F));
        event.registerEntityRenderer(ModEntities.NOMADIC_LAGRAHK.get(), m -> new NomadicLagrahkRenderer<>(m, new NomadicLagrahkModel<>(m.bakeLayer(NOMADIC_LAGRAHK)), 1.0F));
        event.registerEntityRenderer(ModEntities.PRIMAL_BEAST.get(), m -> new PrimalBeastRenderer<>(m, new PrimalBeastModel<>(m.bakeLayer(PRIMAL_BEAST)), 0.5F));
        event.registerEntityRenderer(ModEntities.ROCKY_LUGGEROTH.get(), m -> new BasicEntityRenderer<>(m, new RockyLuggerothModel<>(m.bakeLayer(ROCKY_LUGGEROTH)), 0.7F));
        event.registerEntityRenderer(ModEntities.RUGGED_LURMORUS.get(), m -> new RuggedLurmorusRenderer<>(m, new RuggedLurmorusModel<>(m.bakeLayer(RUGGED_LURMORUS)), 3.0F));
        event.registerEntityRenderer(ModEntities.SALTION.get(), m -> new BasicEntityRenderer<>(m, new SaltionModel<>(m.bakeLayer(SALTION)), 0.7F));
        event.registerEntityRenderer(ModEntities.SHALLOW_ARENTHIS.get(), m -> new ShallowArenthisRenderer<>(m, new ShallowArenthisModel<>(m.bakeLayer(SHALLOW_ARENTHIS)), 0.5F));
        event.registerEntityRenderer(ModEntities.SHALURKER.get(), m -> new BasicEntityRenderer<>(m, new ShalurkerModel<>(m.bakeLayer(SHALURKER)), 0.5F));
        event.registerEntityRenderer(ModEntities.SPELLBOUND_ELEMENTAL.get(), m -> new BasicEntityRenderer<>(m, new SpellElementModel<>(m.bakeLayer(SPELLBOUND_ELEMENTAL)), 0.4F));
        event.registerEntityRenderer(ModEntities.MALACHITE_DRONE.get(), m -> new MalachiteDroneRenderer<>(m, new MalachiteDroneModel<>(m.bakeLayer(MALACHITE_DRONE)), 0.5F));
        event.registerEntityRenderer(ModEntities.MOOKAITE_CONSTRUCT.get(), m -> new MookaiteConstructRenderer<>(m, new MookaiteConstructModel<>(m.bakeLayer(MOOKAITE_CONSTRUCT)), 0.7F));
        event.registerEntityRenderer(ModEntities.OPALITE_CONSTRUCT.get(), m -> new BasicEntityRenderer<>(m, new OpaliteConstructModel<>(m.bakeLayer(OPALITE_CONSTRUCT)), 0.5F));

        event.registerEntityRenderer(ModEntities.BLUE_HOWLITE_WOLF.get(), m -> new BasicEntityRenderer<>(m, new BlueHowliteWolfModel<>(m.bakeLayer(BLUE_HOWLITE_WOLF)), 1.0F));
        event.registerEntityRenderer(ModEntities.MALACHITE_GUARD.get(), m -> new MalachiteGuardRenderer<>(m, new MalachiteGuardModel<>(m.bakeLayer(MALACHITE_GUARD)), 0.7F));
    }
}
