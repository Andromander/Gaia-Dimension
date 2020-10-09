package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.potion.CorruptionEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {

    public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, GaiaDimensionMod.MODID);

    public static final Effect corrupt = new CorruptionEffect(0xF68414, 4.0D);

    public static final RegistryObject<Effect> goldstone_plague = POTIONS.register("goldstone_plague", () -> corrupt);
}
