package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.boss.GDBlueHowliteWolf;
import androsa.gaiadimension.entity.boss.GDMalachiteGuard;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = GaiaDimension.MODID)
public class GaiaEntities {

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityRegistryHelper entity = new EntityRegistryHelper(event.getRegistry());

        entity.registerEntity(GaiaEntityNames.COMMON_SAPPER, GDCommonGrowthSapper.class, GDCommonGrowthSapper::new, 0x5A4514, 0xFF00FF);
        entity.registerEntity(GaiaEntityNames.CHILLED_SAPPER, GDChilledGrowthSapper.class, GDChilledGrowthSapper::new, 0x5A4514, 0x0080A0);
        entity.registerEntity(GaiaEntityNames.NUTRIENT_SAPPER, GDNutrientGrowthSapper.class, GDNutrientGrowthSapper::new, 0x5A4514, 0x00FF10);
        entity.registerEntity(GaiaEntityNames.MYSTIFIED_SAPPER, GDMystifiedGrowthSapper.class, GDMystifiedGrowthSapper::new, 0x5A4514, 0x800080);
        entity.registerEntity(GaiaEntityNames.MUTANT_EXTRACTOR, GDMutantGrowthExtractor.class, GDMutantGrowthExtractor::new, 0xFFFFFF, 0x999999);
        entity.registerEntity(GaiaEntityNames.HOWLITE_WOLF, GDHowliteWolf.class, GDHowliteWolf::new, 0xFF0000, 0x0000FF);
        entity.registerEntity(GaiaEntityNames.SPELLBOUND_ELEMENTAL, GDSpellElement.class, GDSpellElement::new, 0xFFFF00, 0x0000FF);
        entity.registerEntity(GaiaEntityNames.ROCKY_LUGGEROTH, GDRockyLuggeroth.class, GDRockyLuggeroth::new, 0x00FF00, 0xFF00FF);
        entity.registerEntity(GaiaEntityNames.SHALURKER, GDShalurker.class, GDShalurker::new, 0xF0F0F0, 0x0F0F0F);
        entity.registerEntity(GaiaEntityNames.MUCKLING, GDMuckling.class, GDMuckling::new, 0xF00000, 0x00000F);
        entity.registerEntity(GaiaEntityNames.MARKUZAR_PLANT, GDMarkuzarPlant.class, GDMarkuzarPlant::new, 0x00FF00, 0x800080);
        entity.registerEntity(GaiaEntityNames.RUGGED_LURMORUS, GDRuggedLurmorus.class, GDRuggedLurmorus::new, 0x294934, 0x204750);
        entity.registerEntity(GaiaEntityNames.AGATE_GOLEM, GDAgateGolem.class, GDAgateGolem::new, 0x946353, 0x122534);
        entity.registerEntity(GaiaEntityNames.ANCIENT_LAGRAHK, GDAncientLagrahk.class, GDAncientLagrahk::new, 0x999999, 0x987654);
        entity.registerEntity(GaiaEntityNames.CRYSTAL_GOLEM, GDCrystalGolem.class, GDCrystalGolem::new, 0xff7777, 0x905555);
        entity.registerEntity(GaiaEntityNames.SALTION, GDSaltion.class, GDSaltion::new, 0x98afc6, 0x424676);
        entity.registerEntity(GaiaEntityNames.NOMADIC_LAGRAHK, GDNomadicLagrahk.class, GDNomadicLagrahk::new, 0x4c5477, 0x4da69f);
        entity.registerEntity(GaiaEntityNames.SHALLOW_ARENTHIS, GDShallowArenthis.class, GDShallowArenthis::new, 0x000000, 0x000000);
        entity.registerEntity(GaiaEntityNames.CORRUPT_SAPPER, GDCorruptSapper.class, GDCorruptSapper::new, 0x444444, 0x444444);
        entity.registerEntity(GaiaEntityNames.CONTORTED_NAGA, GDContortedNaga.class, GDContortedNaga::new, 0x777777, 0x777777);
        entity.registerEntity(GaiaEntityNames.LESSER_SPITFIRE, GDLesserSpitfire.class, GDLesserSpitfire::new, 0xFF00FF, 0x800080);
        entity.registerEntity(GaiaEntityNames.LESSER_SHOCKSHOOTER, GDLesserShockshooter.class, GDLesserShockshooter::new, 0x00FFFF, 0x008080);
        entity.registerEntity(GaiaEntityNames.MINERAL_ARENTHIS, GDMineralArenthis.class, GDMineralArenthis::new, 0x0000FF, 0x00FFFF);
        entity.registerEntity(GaiaEntityNames.ARCHAIC_WARRIOR, GDArchaicWarrior.class, GDArchaicWarrior::new, 0xFF00FF, 0xFF0000);

        entity.registerEntity(GaiaEntityNames.PRIMAL_BEAST, GDPrimalBeast.class, GDPrimalBeast::new, 0x0080FF, 0x00FFFF);
        entity.registerEntity(GaiaEntityNames.CAVERN_TICK, GDCavernTick.class, GDCavernTick::new, 0x708090, 0x203040);
        entity.registerEntity(GaiaEntityNames.BLUE_HOWLITE_WOLF, GDBlueHowliteWolf.class, GDBlueHowliteWolf::new, 0x00FF00, 0xFF00FF);
        entity.registerEntity(GaiaEntityNames.MALACHITE_GUARD, GDMalachiteGuard.class, GDMalachiteGuard::new, 0x0000FF, 0x00FF00);
    }

    public static class EntityRegistryHelper {
        private final IForgeRegistry<EntityEntry> registry;

        private int id = 0;

        EntityRegistryHelper(IForgeRegistry<EntityEntry> registry) {
            this.registry = registry;
        }

        private static String toString(ResourceLocation regName) {
            return regName.getNamespace() + "." + regName.getPath();
        }

        final <T extends Entity> EntityEntryBuilder<T> builder(ResourceLocation regName, Class<T> entity, Function<World, T> factory) {
            return EntityEntryBuilder.<T>create().id(regName, id++).name(toString(regName)).entity(entity).factory(factory);
        }

        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour) {
            registerEntity(regName, entity, factory, backgroundEggColour, foregroundEggColour, 80, 3, true);
        }

        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
            registry.register(builder(regName, entity, factory).tracker(trackingRange, updateFrequency, sendsVelocityUpdates).egg(backgroundEggColour, foregroundEggColour).build());
        }
/*
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory) {
            registerEntity(regName, entity, factory, 80, 3, true);
        }*/
/*
        final <T extends Entity> void registerEntity(ResourceLocation regName, Class<T> entity, Function<World, T> factory, int trackingRange, int updateInterval, boolean sendVelocityUpdates) {
            registry.register(builder(regName, entity, factory).tracker(trackingRange, updateInterval, sendVelocityUpdates).build());
        }*/
    }
}
