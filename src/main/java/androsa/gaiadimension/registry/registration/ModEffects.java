package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.potion.CorruptionEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, GaiaDimensionMod.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> goldstone_plague = MOB_EFFECTS.register("goldstone_plague", () -> new CorruptionEffect(0xF68414));
}
