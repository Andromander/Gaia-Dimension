package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.potion.PotionCorruption;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(value = GaiaDimension.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimension.MODID)
public class GDPotions {

    public static final Potion goldstone_plague = null;

    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> e) {
        e.getRegistry().register(new PotionCorruption(0xF68414, 4.0D)
                .setRegistryName(GaiaDimension.MODID, "goldstone_plague"));
    }
}
