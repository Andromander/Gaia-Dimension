package androsa.gaiadimension.proxy;

import androsa.gaiadimension.block.inventory.*;
import androsa.gaiadimension.block.tileentity.*;
import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.entity.boss.GDBlueHowliteWolf;
import androsa.gaiadimension.entity.boss.GDMalachiteGuard;
import androsa.gaiadimension.item.GDGemstonePouch;
import androsa.gaiadimension.item.inventory.ContainerGemPouch;
import androsa.gaiadimension.item.inventory.GuiGemPouch;
import androsa.gaiadimension.item.inventory.InventoryGemPouch;
import androsa.gaiadimension.model.*;
import androsa.gaiadimension.particle.ParticleGeyserSmoke;
import androsa.gaiadimension.registry.EnumParticlesGD;
import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.renderer.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public GuiContainer getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiID guiID = GuiID.values()[ID];
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);

        switch (guiID) {
            case AGATE_CRAFT:
                return new GuiAgateCraftingTable(player.inventory, world, pos);
            case SMALL_CRATE:
                return new GuiSmallCrate(player.inventory, (TileEntitySmallCrate) tile);
            case LARGE_CRATE:
                return new GuiLargeCrate(player.inventory, (TileEntityLargeCrate) tile);
            case GAIA_STONE_FURNACE:
                return new GuiGaiaStoneFurnace(player.inventory, (TileEntityGaiaStoneFurnace) tile);
            case GLITTER_FURNACE:
                return new GuiRestructurer(player.inventory, (TileEntityRestructurer) tile);
            case PURIFIER:
                return new GuiPurifier(player.inventory, (TileEntityPurifier) tile);
            case GEM_POUCH:
                ItemStack item = player.getHeldItemMainhand();
                if(item.isEmpty() || !(item.getItem() instanceof GDGemstonePouch)) {
                    item = player.getHeldItemOffhand();
                }
                if(!item.isEmpty() && item.getItem() instanceof GDGemstonePouch) {
                    String name = item.hasDisplayName() ? item.getDisplayName() : I18n.format("container.gaiadimension.gem_pouch");
                    return new GuiGemPouch(new ContainerGemPouch(player.inventory, new InventoryGemPouch(item, name)));
                }
            default:
                return null;
        }
    }
    @Override
    public void doPreLoadRegistration() {
       // RenderingRegistry.registerEntityRenderingHandler(GDShotGaianEnergy.class, m -> new RenderSnowball<>(m, Items.ENDER_PEARL, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(GDThrownPebble.class, m -> new RenderThrownPebble<>(m, GDItems.sturdy_pebble, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(GDAgateArrowEntity.class, RenderAgateArrow::new);

        RenderingRegistry.registerEntityRenderingHandler(GDGrowthSapper.class, m -> new EntityRenderGrowthSapper(m, new ModelGrowthSapper(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(GDMutantGrowthExtractor.class, m -> new EntityRenderGrowthExtractor(m, new ModelGrowthExtractor(),0.8F));
        RenderingRegistry.registerEntityRenderingHandler(GDHowliteWolf.class, m -> new EntityRenderHowliteWolf(m, new ModelHowliteWolf(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDSpellElement.class, m -> new EntityRenderSpellElement(m, new ModelSpellElement(), 0.4F));
        RenderingRegistry.registerEntityRenderingHandler(GDRockyLuggeroth.class, m -> new EntityRenderRockyLuggeroth(m, new ModelRockyLuggeroth(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(GDShalurker.class, m -> new EntityRenderShalurker(m, new ModelShalurker(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDMuckling.class, m -> new EntityRenderMuckling(m, 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(GDMarkuzarPlant.class, m -> new EntityRenderMarkuzarPlant(m, new ModelMarkuzarPlant(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDRuggedLurmorus.class, m -> new EntityRenderRuggedLurmorus(m, new ModelRuggedLurmorus(), 4.0F));
        RenderingRegistry.registerEntityRenderingHandler(GDAgateGolem.class, m -> new EntityRenderAgateGolem(m, new ModelAgateGolem(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(GDAncientLagrahk.class, m -> new EntityRenderAncientLagrahk(m, new ModelAncientLagrahk(), 2.0F));
        RenderingRegistry.registerEntityRenderingHandler(GDCrystalGolem.class, m -> new EntityRenderCrystalGolem(m, new ModelCrystalGolem(), 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(GDSaltion.class, m -> new EntityRenderSaltion(m, new ModelSaltion(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(GDNomadicLagrahk.class, m -> new EntityRenderNomadicLagrahk(m, new ModelNomadicLagrahk(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(GDShallowArenthis.class, m -> new EntityRenderShallowArenthis(m, new ModelShallowArenthis(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDCorruptSapper.class, m -> new EntityRenderCorruptSapper(m, new ModelGrowthSapper(), 0.6F));
        RenderingRegistry.registerEntityRenderingHandler(GDContortedNaga.class, m -> new EntityRenderContortedNaga(m, new ModelContortedNaga(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(GDLesserSpitfire.class, m -> new EntityRenderLesserSpitfire(m, new ModelLesserSpitfire(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDLesserShockshooter.class, m -> new EntityRenderLesserShockshooter(m, new ModelLesserShockshooter(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDMineralArenthis.class, m -> new EntityRenderMineralArenthis(m, new ModelMineralArenthis(), 0.8F));
        RenderingRegistry.registerEntityRenderingHandler(GDBismuthUletrus.class, m -> new EntityRenderBismuthUletrus(m, new ModelBismuthUletrus(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(GDArchaicWarrior.class, m -> new EntityRenderArchaicWarrior(m, new ModelArchaicWarrior(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDPrimalBeast.class, m -> new EntityRenderPrimalBeast(m, new ModelPrimalBeast(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(GDCavernTick.class, m -> new EntityRenderCavernTick(m, new ModelCavernTick(), 0.2F));
        RenderingRegistry.registerEntityRenderingHandler(GDBlueHowliteWolf.class, m -> new EntityRenderBlueHowliteWolf(m, new ModelBlueHowliteWolf(), 1.0F));

        RenderingRegistry.registerEntityRenderingHandler(GDMalachiteGuard.class, m -> new EntityRenderMalachiteGuard(m, new ModelMalachiteGuard(), 0.7F));
    }

    @Override
    public void spawnParticle(EnumParticlesGD particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity entity = mc.getRenderViewEntity();
        World world = mc.world;

        if (entity != null && mc.effectRenderer != null) {
            int i = mc.gameSettings.particleSetting;

            if(i == 1 && world.rand.nextInt(3) == 0) {
                i = 2;
            }

            double eX = entity.posX - x;
            double eY = entity.posY - y;
            double eZ = entity.posZ - z;

            if (eX * eX + eY * eY + eZ * eZ <= 1024D && i <= 1) {
                Particle part = null;

                switch (particle) {
                    case GEYSER_SMOKE:
                        part = new ParticleGeyserSmoke(world, x, y, z, velocityX, velocityY, velocityZ);
                        break;
                }

                if (part != null) {
                    mc.effectRenderer.addEffect(part);
                }
            }
        }
    }
}
