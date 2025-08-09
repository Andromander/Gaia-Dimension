package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
    public static final ResourceKey<DamageType> PHYSICAL_MAGIC = makeDamage("physical_magic");
    public static final ResourceKey<DamageType> FIRE_MAGIC = makeDamage("fire_magic");
    public static final ResourceKey<DamageType> ELECTRIC_MAGIC = makeDamage("electric_magic");
    public static final ResourceKey<DamageType> POISON_MAGIC = makeDamage("poison_magic");
    public static final ResourceKey<DamageType> FROST_MAGIC = makeDamage("frost_magic");
    public static final ResourceKey<DamageType> LIGHT_MAGIC = makeDamage("light_magic");
    public static final ResourceKey<DamageType> ENERGY_MAGIC = makeDamage("energy_magic");

    private static ResourceKey<DamageType> makeDamage(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name));
    }

    public static DamageSource getDamage(Level level, ResourceKey<DamageType> damage) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(damage));
    }

    public static void init(BootstrapContext<DamageType> context) {
        context.register(ENERGETIC, new DamageType("gaiadimension.energetic", 0.0F));
        context.register(STATIC, new DamageType("gaiadimension.static", 0.0F));
        context.register(CORRUPTION, new DamageType("gaiadimension.corruption", 0.0F));
        context.register(MALACHITE_BLAST, new DamageType("gaiadimension.malachiteBlast", 0.0F));
        context.register(PHYSICAL_MAGIC, new DamageType("gaiadimension.physicalMagic", 0.0F));
        context.register(FIRE_MAGIC, new DamageType("gaiadimension.fireMagic", 0.0F));
        context.register(ELECTRIC_MAGIC, new DamageType("gaiadimension.electricMagic", 0.0F));
        context.register(POISON_MAGIC, new DamageType("gaiadimension.poisonMagic", 0.0F));
        context.register(FROST_MAGIC, new DamageType("gaiadimension.frostMagic", 0.0F));
        context.register(LIGHT_MAGIC, new DamageType("gaiadimension.lightMagic", 0.0F));
        context.register(ENERGY_MAGIC, new DamageType("gaiadimension.energyMagic", 0.0F));
    }
}
