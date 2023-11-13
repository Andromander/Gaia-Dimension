package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.honing.HoneSkill;

public class ModSkills {
    /*
     * Custom registration.
     *
     * Skills are the parts of honing that allows for perks.
     * Skills will have certain values and levels, and equipment will have a certain number of slots. Negative skills will add more possible slots.
     * Honed weapons cannot be enchanted. Only Gaian tools may be honed. Lore reasons, yadda yadda.
     * Skills are counted from active slots. Helmet, Chest, Leggings, Boots, and the Main Hand.
     */

    //Attack Plus: +1 to attack value.
    //Attack Minus: -2 to attack value.
    public static final HoneSkill ATTACK_PLUS = new HoneSkill(1, 3);
    public static final HoneSkill ATTACK_MINUS = new HoneSkill(-1, 1);
    //Defense Plus: +1 to defense value.
    //Defense Minus: -2 to defense value.
    public static final HoneSkill DEFENSE_PLUS = new HoneSkill(1, 3);
    public static final HoneSkill DEFENSE_MINUS = new HoneSkill(-1, 1);
    //Health Plus: +2 to max health.
    //Health Minus: -10 to max health.
    public static final HoneSkill HEALTH_PLUS = new HoneSkill(1, 5);
    public static final HoneSkill HEALTH_MINUS = new HoneSkill(-2, 1);
    //Critical Plus: random critical hit chance.
    //Critical Minus: negates other critical hits.
    public static final HoneSkill CRITICAL_PLUS = new HoneSkill(1, 3);
    public static final HoneSkill CRITICAL_MINUS = new HoneSkill(-1, 1);
    //Striker: +1 damage for critical hits
    //Skittish: -2 damage for critical hits
    public static final HoneSkill STRIKER = new HoneSkill(1, 3);
    public static final HoneSkill SKITTISH = new HoneSkill(-1, 1);
    //Peak Performance: +1 to attack when max health is at maximum, proportionate to player's current health.
    //Lax Performance: -3 to attack when max health is at maximum.
    public static final HoneSkill PEAK_PERFORMANCE = new HoneSkill(1, 5);
    public static final HoneSkill LAX_PERFORMANCE = new HoneSkill(-1, 1);
    //Last Stand: +1 to attack when max health is low, proportionate to player's current health.
    //Defeatist: -3 to attack when max health is low.
    public static final HoneSkill LAST_STAND = new HoneSkill(1, 5);
    public static final HoneSkill DEFEATIST = new HoneSkill(-1, 1);
    //Clincher: +2 to attack if affected by negative effect.
    //Cowardly: -3 to attack if affected by negative effect.
    public static final HoneSkill CLINCHER = new HoneSkill(1, 2);
    public static final HoneSkill COWARDLY = new HoneSkill(-1, 1);
    //Satiated: +1 to attack when max hunger is at maximum, proportionate to player's current hunger...if mods tweak the max value.
    //Stuffed: -3 to attack when max hunger is at maximum.
    public static final HoneSkill SATIATED = new HoneSkill(1, 5);
    public static final HoneSkill STUFFED = new HoneSkill(-1, 1);
    //Razor Sharp: 25% chance to keep durability.
    //Blunt Blade: 50% chance to lose more durability.
    public static final HoneSkill RAZOR_SHARP = new HoneSkill(2, 2);
    public static final HoneSkill BLUNT_EDGE = new HoneSkill(-1, 1);
    //Bludgeoner: +2 to attack when durability is low.
    //Brittle: -3 to attack when durability is low.
    public static final HoneSkill BLUDGEONER = new HoneSkill(2, 2);
    public static final HoneSkill BRITTLE = new HoneSkill(-1, 1);
    //Battle Ready: removes a tick of time from the cooldown.
    //Battle Noob: randomly adds an extra tick.
    public static final HoneSkill BATTLE_READY = new HoneSkill(2, 2);
    public static final HoneSkill BATTLE_NOOB = new HoneSkill(-1, 1);
    //Divine Blessing: chance to reduce damage by 25%
    //Cursed Blessing: chance to increase damage by 50%
    public static final HoneSkill DIVINE_BLESSING = new HoneSkill(2, 2);
    public static final HoneSkill CURSED_BLESSING = new HoneSkill(-1, 1);
    //Fung-estatic: allows eating crystal fungi.
    //Fung-stration: allows eating crystal fungi, but afflicts a random negative effect.
    public static final HoneSkill FUNGESTATIC = new HoneSkill(2, 1);
    public static final HoneSkill FUNGSTRATION = new HoneSkill(-1, 1);
    //Rationing: 12.5% chance to not use up an item on eating.
    //Glutton: 50% chance to use up to two items on consuming.
    public static final HoneSkill RATIONING = new HoneSkill(1, 4);
    public static final HoneSkill GLUTTON = new HoneSkill(-1, 1);
    //Retaliate: +2 attack when dealt a certain amount of damage in a single hit.
    //Retreat: -3 attack when dealt a certain amount of damage in a single hit.
    public static final HoneSkill RETALIATE = new HoneSkill(1, 2);
    public static final HoneSkill RETREAT = new HoneSkill(-1, 1);
    //Bleeding Edge: Attack is boosted by half the base defense.
    //Reckless Abandon: Attack is reduced by half the base defense.
    public static final HoneSkill BLEEDING_EDGE = new HoneSkill(2, 1);
    public static final HoneSkill RECKLESS_ABANDON = new HoneSkill(-2, 1);
    //Sneak Attack: +1 damage to mobs not targeting the player. Excludes players due to cheese.
    //Shy Attack: -2 damage to mobs not targeting the player.
    public static final HoneSkill SNEAK_ATTACK = new HoneSkill(1, 4);
    public static final HoneSkill SHY_ATTACK = new HoneSkill(-1, 1);
    //Foray: +1 damage to negatively-affected targets
    //Abstain: -2 damage to negatively-affected targets
    public static final HoneSkill FORAY =  new HoneSkill(1, 3);
    public static final HoneSkill ABSTAIN = new HoneSkill(-1, 1);
    //Geologist: increases chances of mining a block as is.
    //Demolisher: increases chances of destroying a mined block.
    public static final HoneSkill GEOLOGIST = new HoneSkill(1, 2);
    public static final HoneSkill DEMOLISHER = new HoneSkill(-1, 1);
    //Carver: increases chances of extra drops.
    //Wasting: increases chances of missing drops.
    public static final HoneSkill CARVER = new HoneSkill(1, 2);
    public static final HoneSkill WASTING = new HoneSkill(-1, 1);
    //Gobbler: halves eating time
    //Nibbler: doubles eating time
    public static final HoneSkill GOBBLER = new HoneSkill(2, 1);
    public static final HoneSkill NIBBLER = new HoneSkill(-2, 1);
    //Immune Boost: halves negative effect time
    //Immune Suppress: doubles negative effect time
    public static final HoneSkill IMMUNE_BOOST = new HoneSkill(2, 1);
    public static final HoneSkill IMMUNE_SUPPRESS = new HoneSkill(-2, 1);
    //Fleet Foot: -1 from fall damage.
    //Weak Knees: +2 from fall damage.
    public static final HoneSkill FLEET_FOOT = new HoneSkill(1, 4);
    public static final HoneSkill WEAK_KNEES = new HoneSkill(-1, 1);
    //Focal Length: increases reach range.
    //Blind Spot: decreases reach range.
    public static final HoneSkill FOCAL_LENGTH = new HoneSkill(1, 3);
    public static final HoneSkill BLIND_SPOT = new HoneSkill(-1, 1);
    //Iron Lungs: increases time allowed underwater
    //Shallow Breath: decreases time allowed underwater
    public static final HoneSkill IRON_LUNGS = new HoneSkill(1, 3);
    public static final HoneSkill SHALLOW_BREATH = new HoneSkill(-1, 1);
    //Coalescence: attack boost from clearing negative effects.
    //Dissociation: reduced attack from clearing negative effects.
    public static final HoneSkill COALESCENCE = new HoneSkill(1, 2);
    public static final HoneSkill DISSOCIATION = new HoneSkill(-1, 1);
    //Parry: attack boost for blocking perfectly
    //Stagger: reduced attack from blocking any attack
    public static final HoneSkill PARRY = new HoneSkill(1, 3);
    public static final HoneSkill STAGGER = new HoneSkill(-1, 1);
}
