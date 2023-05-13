package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class GaiaDamage {
    public static final ResourceKey<DamageType> ENERGETIC = makeDamage("energetic");
    public static final ResourceKey<DamageType> STATIC = makeDamage("static");
    public static final ResourceKey<DamageType> CORRUPTION = makeDamage("corruption");
    public static final ResourceKey<DamageType> MALACHITE_BLAST = makeDamage("malachite_blast");

    private static ResourceKey<DamageType> makeDamage(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    public static DamageSource getDamage(Level level, ResourceKey<DamageType> damage) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(damage));
    }

    public static void init(BootstapContext<DamageType> context) {
        context.register(ENERGETIC, new DamageType("gaiadimension.energetic", 0.0F));
        context.register(STATIC, new DamageType("gaiadimension.static", 0.0F));
        context.register(CORRUPTION, new DamageType("gaiadimension.corruption", 0.0F));
        context.register(MALACHITE_BLAST, new DamageType("gaiadimension.malachiteBlast", 0.0F));
    }
}
