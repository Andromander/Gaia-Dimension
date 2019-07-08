package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.potion.CorruptionEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimension.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimension.MODID)
public class ModEffects {

    public static final Effect goldstone_plague = new CorruptionEffect(0xF68414, 4.0D);

    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Effect> e) {
        e.getRegistry().register(goldstone_plague.setRegistryName(GaiaDimension.MODID, "goldstone_plague"));
    }
}
