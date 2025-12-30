package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModAttributes {

    //TODO: Leave this registry out until it's good to go
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, GaiaDimensionMod.MODID);

    public static final RangedAttribute ATTACK_BOOST = new RangedAttribute("attack_boost", 0, -1, 3);
    public static final RangedAttribute DEFENSE_BOOST = new RangedAttribute("defense_boost", 0, -1, 3);
    public static final RangedAttribute HEALTH_BOOST = new RangedAttribute("health_boost", 0, -1, 5);
    public static final RangedAttribute CRITICAL_BOOST = new RangedAttribute("critical_boost", 0, -1, 3);
    public static final RangedAttribute CRITICAL_DAMAGE = new RangedAttribute("critical_damage", 0, -1, 3);
    public static final RangedAttribute PEAK_BOOST = new RangedAttribute("peak_boost", 0, -1, 5);
    public static final RangedAttribute HEROIC_BOOST = new RangedAttribute("heroic_boost", 0, -1, 5);
    public static final RangedAttribute DEBUFFED_BOOST = new RangedAttribute("debuffed_boost", 0, -1, 2);
    public static final RangedAttribute FULLNESS_BOOST = new RangedAttribute("fullness_boost", 0, -1, 5);
    public static final RangedAttribute SHARPNESS = new RangedAttribute("sharpness", 0, -1, 2);
    public static final RangedAttribute DULLNESS = new RangedAttribute("dullness", 0, -1, 2);
    public static final RangedAttribute STRIKEBACK = new RangedAttribute("strikeback", 0, -1, 2);
    public static final RangedAttribute LUCKY_DEFENSE = new RangedAttribute("lucky_defense", 0, -1, 2);
    public static final RangedAttribute FUNGUS_EATER = new RangedAttribute("fungus_eater", 0, -1, 2);
    public static final RangedAttribute FREE_MEAL = new RangedAttribute("free_meal", 0, -1, 4);
    public static final RangedAttribute BIDING_STRIKE = new RangedAttribute("biding_strike", 0, -1, 2);
    public static final RangedAttribute BRAZEN = new RangedAttribute("brazen", 0, -1, 1);
    public static final RangedAttribute STEALTH_STRIKE = new RangedAttribute("stealth_strike", 0, -1, 4);
    public static final RangedAttribute EXPLOITER = new RangedAttribute("exploiter", 0, -1, 3);
    public static final RangedAttribute FOCUS_STRIKE = new RangedAttribute("focus_strike", 0, -1, 3);
    public static final RangedAttribute AIR_STRIKE = new RangedAttribute("air_strike", 0, -1, 2);
    public static final RangedAttribute SPEED_EATING = new RangedAttribute("speed_eating", 0, -1, 1);
    public static final RangedAttribute IMMUNITY = new RangedAttribute("immunity", -1, -1, 1);
    public static final RangedAttribute JUMP_MASTER = new RangedAttribute("jump_master", 4, -1, 4);
    public static final RangedAttribute KEEN_EYE = new RangedAttribute("keen_eye", 0, -1, 3);
    public static final RangedAttribute WATER_MASTER = new RangedAttribute("water_master", 0, -1, 3);
    public static final RangedAttribute DETOXIFY = new RangedAttribute("detoxify", 0, -1, 2);
    public static final RangedAttribute GUARD = new RangedAttribute("guard", 0, -1, 3);
}
