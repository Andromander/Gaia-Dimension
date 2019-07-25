package androsa.gaiadimension.proxy;

import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import androsa.gaiadimension.model.*;
import androsa.gaiadimension.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void doPreLoadRegistration() {
       // RenderingRegistry.registerEntityRenderingHandler(GDShotGaianEnergy.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(ThrownPebbleEntity.class, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(AgateArrowEntity.class, AgateArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(AgateGolemEntity.class, m -> new AgateGolemRenderer(m, new AgateGolemModel(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(AncientLagrahkEntity.class, m -> new AncientLagrahkRenderer(m, new AncientLagrahkModel(), 2.0F));
        RenderingRegistry.registerEntityRenderingHandler(ArchaicWarriorEntity.class, m -> new ArchaicWarriorRenderer(m, new ArchaicWarriorModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(BismuthUletrusEntity.class, m -> new BismuthUletrusRenderer(m, new BismuthUletrusModel(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(CavernTickEntity.class, m -> new CavernTickRenderer(m, new CavernTickModel(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(ContortedNagaEntity.class, m -> new ContortedNagaRenderer(m, new ContortedNagaModel(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(CorruptSapperEntity.class, m -> new CorruptSapperRenderer(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(CrystalGolemEntity.class, m -> new CrystalGolemRenderer(m, new CrystalGolemModel(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(GrowthSapperEntity.class, m -> new GrowthSapperRenderer(m, new GrowthSapperModel<>(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(HowliteWolfEntity.class, m -> new HowliteWolfRenderer(m, new HowliteWolfModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LesserShockshooterEntity.class, m -> new LesserShockshooterRenderer(m, new LesserShockshooterModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(LesserSpitfireEntity.class, m -> new LesserSpitfireRenderer(m, new LesserSpitfireModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MarkuzarPlantEntity.class, m -> new MarkuzarPlantRenderer(m, new MarkuzarPlantModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(MineralArenthisEntity.class, m -> new MineralArenthisRenderer(m, new MineralArenthisModel(), 0.8F));
        RenderingRegistry.registerEntityRenderingHandler(MucklingEntity.class, m -> new MucklingRenderer(m, 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(MutantGrowthExtractorEntity.class, m -> new GrowthExtractorRenderer(m, new GrowthExtractorModel(),0.8F));
        RenderingRegistry.registerEntityRenderingHandler(NomadicLagrahkEntity.class, m -> new NomadicLagrahkRenderer(m, new NomadicLagrahkModel(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(PrimalBeastEntity.class, m -> new PrimalBeastRenderer(m, new PrimalBeastModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(RockyLuggerothEntity.class, m -> new RockyLuggerothRenderer(m, new RockyLuggerothModel(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(RuggedLurmorusEntity.class, m -> new RuggedLurmorusRenderer(m, new RuggedLurmorusModel(), 4.0F));
        RenderingRegistry.registerEntityRenderingHandler(SaltionEntity.class, m -> new SaltionRenderer(m, new SaltionModel(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(ShallowArenthisEntity.class, m -> new ShallowArenthisRenderer(m, new ShallowArenthisModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(ShalurkerEntity.class, m -> new ShalurkerRenderer(m, new ShalurkerModel(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(SpellElementEntity.class, m -> new SpellElementRenderer(m, new SpellElementModel(), 0.4F));

        RenderingRegistry.registerEntityRenderingHandler(BlueHowliteWolfEntity.class, m -> new BlueHowliteWolfRenderer(m, new BlueHowliteWolfModel(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(MalachiteGuardEntity.class, m -> new MalachiteGuardRenderer(m, new MalachiteGuardModel(), 0.7F));
    }
}
