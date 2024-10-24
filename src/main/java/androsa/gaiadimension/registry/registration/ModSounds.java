package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, GaiaDimensionMod.MODID);

    public static final Supplier<SoundEvent> STURDY_PEBBLE_THROW                  = makeEntitySound("sturdy_pebble", "throw");
    public static final Supplier<SoundEvent> ENTITY_AGATE_GOLEM_DEATH             = makeDeathSound("agate_golem");
    public static final Supplier<SoundEvent> ENTITY_AGATE_GOLEM_HURT              = makeHurtSound("agate_golem");
    public static final Supplier<SoundEvent> ENTITY_ANCIENT_LAGRAHK_DEATH         = makeDeathSound("ancient_lagrahk");
    public static final Supplier<SoundEvent> ENTITY_ANCIENT_LAGRAHK_HURT          = makeHurtSound("ancient_lagrahk");
    public static final Supplier<SoundEvent> ENTITY_ARCHAIC_WARRIOR_AMBIENT       = makeAmbientSound("archaic_warrior");
    public static final Supplier<SoundEvent> ENTITY_ARCHAIC_WARRIOR_DEATH         = makeDeathSound("archaic_warrior");
    public static final Supplier<SoundEvent> ENTITY_ARCHAIC_WARRIOR_HURT          = makeHurtSound("archaic_warrior");
    public static final Supplier<SoundEvent> ENTITY_ARCHAIC_WARRIOR_STEP          = makeStepSound("archaic_warrior");
    public static final Supplier<SoundEvent> ENTITY_BISMUTH_ULETRUS_DEATH         = makeDeathSound("bismuth_uletrus");
    public static final Supplier<SoundEvent> ENTITY_BISMUTH_ULETRUS_HURT          = makeHurtSound("bismuth_uletrus");
    public static final Supplier<SoundEvent> ENTITY_CAVERN_TICK_AMBIENT           = makeAmbientSound("cavern_tick");
    public static final Supplier<SoundEvent> ENTITY_CAVERN_TICK_DEATH             = makeDeathSound("cavern_tick");
    public static final Supplier<SoundEvent> ENTITY_CAVERN_TICK_HURT              = makeHurtSound("cavern_tick");
    public static final Supplier<SoundEvent> ENTITY_CAVERN_TICK_STEP              = makeStepSound("cavern_tick");
    public static final Supplier<SoundEvent> ENTITY_CONTORTED_NAGA_DEATH          = makeDeathSound("contorted_naga");
    public static final Supplier<SoundEvent> ENTITY_CONTORTED_NAGA_HURT           = makeHurtSound("contorted_naga");
    public static final Supplier<SoundEvent> ENTITY_CORRUPT_SAPPER_DEATH          = makeDeathSound("corrupt_sapper");
    public static final Supplier<SoundEvent> ENTITY_CORRUPT_SAPPER_HURT           = makeHurtSound("corrupt_sapper");
    public static final Supplier<SoundEvent> ENTITY_CRYSTAL_GOLEM_DEATH           = makeDeathSound("crystal_golem");
    public static final Supplier<SoundEvent> ENTITY_CRYSTAL_GOLEM_HURT            = makeHurtSound("crystal_golem");
    public static final Supplier<SoundEvent> ENTITY_GROWTH_SAPPER_DEATH           = makeDeathSound("growth_sapper");
    public static final Supplier<SoundEvent> ENTITY_GROWTH_SAPPER_HURT            = makeHurtSound("growth_sapper");
    public static final Supplier<SoundEvent> ENTITY_HOWLITE_WOLF_DEATH            = makeDeathSound("howlite_wolf");
    public static final Supplier<SoundEvent> ENTITY_HOWLITE_WOLF_HURT             = makeHurtSound("howlite_wolf");
    public static final Supplier<SoundEvent> ENTITY_LESSER_SHOCKSHOOTER_DEATH     = makeDeathSound("lesser_shockshooter");
    public static final Supplier<SoundEvent> ENTITY_LESSER_SHOCKSHOOTER_HURT      = makeHurtSound("lesser_shockshooter");
    public static final Supplier<SoundEvent> ENTITY_LESSER_SPITFIRE_DEATH         = makeDeathSound("lesser_spitfire");
    public static final Supplier<SoundEvent> ENTITY_LESSER_SPITFIRE_HURT          = makeHurtSound("lesser_spitfire");
    public static final Supplier<SoundEvent> ENTITY_MARKUZAR_PLANT_DEATH          = makeDeathSound("markuzar_plant");
    public static final Supplier<SoundEvent> ENTITY_MARKUZAR_PLANT_HURT           = makeHurtSound("markuzar_plant");
    public static final Supplier<SoundEvent> ENTITY_MINERAL_ARENTHIS_DEATH        = makeDeathSound("mineral_arenthis");
    public static final Supplier<SoundEvent> ENTITY_MINERAL_ARENTHIS_HURT         = makeHurtSound("mineral_arenthis");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_DEATH                = makeDeathSound("muckling");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_HURT                 = makeHurtSound("muckling");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_JUMP                 = makeEntitySound("muckling", "jump");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_SQUISH               = makeEntitySound("muckling", "squish");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_DEATH_SMALL          = makeDeathSound("muckling_small");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_HURT_SMALL           = makeHurtSound("muckling_small");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_JUMP_SMALL           = makeEntitySound("muckling_small", "jump");
    public static final Supplier<SoundEvent> ENTITY_MUCKLING_SQUISH_SMALL         = makeEntitySound("muckling_small", "squish");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_DEATH      = makeDeathSound("mookaite_construct");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_HURT       = makeHurtSound("mookaite_construct");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_STOMP      = makeEntitySound("mookaite_construct", "stomp");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_BREATH     = makeEntitySound("mookaite_construct", "breath");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_CAST       = makeEntitySound("mookaite_construct", "cast");
    public static final Supplier<SoundEvent> ENTITY_MOOKAITE_CONSTRUCT_SHOOT      = makeEntitySound("mookaite_construct", "shoot");
    public static final Supplier<SoundEvent> ENTITY_MUTANT_GROWTH_EXTRACTOR_DEATH = makeDeathSound("mutant_growth_extractor");
    public static final Supplier<SoundEvent> ENTITY_MUTANT_GROWTH_EXTRACTOR_HURT  = makeHurtSound("mutant_growth_extractor");
    public static final Supplier<SoundEvent> ENTITY_NOMADIC_LAGRAHK_DEATH         = makeDeathSound("nomadic_lagrahk");
    public static final Supplier<SoundEvent> ENTITY_NOMADIC_LAGRAHK_HURT          = makeHurtSound("nomadic_lagrahk");
    public static final Supplier<SoundEvent> ENTITY_OPALITE_CONSTRUCT_DEATH       = makeDeathSound("opalite_construct");
    public static final Supplier<SoundEvent> ENTITY_OPALITE_CONSTRUCT_HURT        = makeHurtSound("opalite_construct");
    public static final Supplier<SoundEvent> ENTITY_PRIMAL_BEAST_AMBIENT          = makeAmbientSound("primal_beast");
    public static final Supplier<SoundEvent> ENTITY_PRIMAL_BEAST_DEATH            = makeDeathSound("primal_beast");
    public static final Supplier<SoundEvent> ENTITY_PRIMAL_BEAST_HURT             = makeHurtSound("primal_beast");
    public static final Supplier<SoundEvent> ENTITY_ROCKY_LUGGEROTH_DEATH         = makeDeathSound("rocky_luggeroth");
    public static final Supplier<SoundEvent> ENTITY_ROCKY_LUGGEROTH_HURT          = makeHurtSound("rocky_luggeroth");
    public static final Supplier<SoundEvent> ENTITY_RUGGED_LURMORUS_DEATH         = makeDeathSound("rugged_lurmorus");
    public static final Supplier<SoundEvent> ENTITY_RUGGED_LURMORUS_HURT          = makeHurtSound("rugged_lurmorus");
    public static final Supplier<SoundEvent> ENTITY_SALTION_DEATH                 = makeDeathSound("saltion");
    public static final Supplier<SoundEvent> ENTITY_SALTION_HURT                  = makeHurtSound("saltion");
    public static final Supplier<SoundEvent> ENTITY_SHALLOW_ARENTHIS_DEATH        = makeDeathSound("shallow_arenthis");
    public static final Supplier<SoundEvent> ENTITY_SHALLOW_ARENTHIS_HURT         = makeHurtSound("shallow_arenthis");
    public static final Supplier<SoundEvent> ENTITY_SHALURKER_DEATH               = makeDeathSound("shalurker");
    public static final Supplier<SoundEvent> ENTITY_SHALURKER_HURT                = makeHurtSound("shalurker");
    public static final Supplier<SoundEvent> ENTITY_SPELLBOUND_ELEMENTAL_DEATH    = makeDeathSound("spellbound_elemental");
    public static final Supplier<SoundEvent> ENTITY_SPELLBOUND_ELEMENTAL_HURT     = makeHurtSound("spellbound_elemental");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_DRONE_DEATH         = makeDeathSound("malachite_drone");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_DRONE_DESYNC        = makeEntitySound("malachite_drone", "desync");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_DRONE_HURT          = makeHurtSound("malachite_drone");
    public static final Supplier<SoundEvent> ENTITY_BLUE_HOWLITE_WOLF_DEATH       = makeDeathSound("blue_howlite_wolf");
    public static final Supplier<SoundEvent> ENTITY_BLUE_HOWLITE_WOLF_HURT        = makeHurtSound("blue_howlite_wolf");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_GUARD_BLAST         = makeEntitySound("malachite_guard", "blast");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_GUARD_DEATH         = makeDeathSound("malachite_guard");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_GUARD_HURT          = makeHurtSound("malachite_guard");
    public static final Supplier<SoundEvent> ENTITY_MALACHITE_GUARD_STOMP         = makeEntitySound("malachite_guard", "stomp");

    private static Supplier<SoundEvent> makeAmbientSound(String name) {
        return makeEntitySound(name, "ambient");
    }

    private static Supplier<SoundEvent> makeDeathSound(String name) {
        return makeEntitySound(name, "death");
    }

    private static Supplier<SoundEvent> makeHurtSound(String name) {
        return makeEntitySound(name, "hurt");
    }

    private static Supplier<SoundEvent> makeStepSound(String name) {
        return makeEntitySound(name, "step");
    }

    private static Supplier<SoundEvent> makeEntitySound(String name, String suffix) {
        return makeSound("entity." + name + "." + suffix);
    }

    private static Supplier<SoundEvent> makeSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name)));
    }
}
