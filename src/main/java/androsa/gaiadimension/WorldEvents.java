package androsa.gaiadimension;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.GaiaTeleporter;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class WorldEvents {

    public static GaiaTeleporter gaiaTeleporter;

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        gaiaTeleporter = new GaiaTeleporter((ServerWorld)event.world);

        if (event.side == LogicalSide.SERVER) {
            gaiaTeleporter.tick(event.world.getGameTime());
        }
    }
}
