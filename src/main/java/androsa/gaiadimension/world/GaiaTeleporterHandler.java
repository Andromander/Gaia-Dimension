package androsa.gaiadimension.world;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.GaiaTeleporter;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class GaiaTeleporterHandler {

    public static GaiaTeleporter gaiaTeleporter;

    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        gaiaTeleporter = new GaiaTeleporter((ServerWorld)event.world);

        if (event.side == LogicalSide.SERVER) {
            gaiaTeleporter.tick(event.world.getGameTime());
        }
    }
}
