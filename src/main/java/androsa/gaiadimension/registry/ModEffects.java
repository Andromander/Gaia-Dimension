package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.potion.CorruptionEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects {

    public static final MobEffect goldstone_plague = new CorruptionEffect(0xF68414, 4.0D);

    @SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<MobEffect> event) {
        event.getRegistry().register(goldstone_plague.setRegistryName("goldstone_plague"));
    }
}
