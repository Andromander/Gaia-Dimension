package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(GaiaDimension.MODID)
public class GDItems {

    public static ItemArmor.ArmorMaterial ARMOR_SUGILITE = EnumHelper.addArmorMaterial("SUGILITE", "sugilite", 1000, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ItemArmor.ArmorMaterial ARMOR_PROUSTITE = EnumHelper.addArmorMaterial("PROUSTITE", "proustite", 1500, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
    public static ItemArmor.ArmorMaterial ARMOR_LEUCITE = EnumHelper.addArmorMaterial("LEUCITE", "leucite", 1300, new int[]{1, 4, 6, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_CARNELIAN = EnumHelper.addArmorMaterial("CARNELIAN", "carnelian", 2500, new int[]{2, 5, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static ItemArmor.ArmorMaterial ARMOR_DIOPSIDE = EnumHelper.addArmorMaterial("DIOPSIDE", "diopside", 2300, new int[]{2, 5, 7, 2}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_CHALSEDONY = EnumHelper.addArmorMaterial("CHALSEDONY", "chalsedony", 3000, new int[]{3, 6, 8, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
    public static ItemArmor.ArmorMaterial ARMOR_MALACHITE = EnumHelper.addArmorMaterial("MALACHITE", "malachite_guard", 5120, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public static ItemArmor.ArmorMaterial ARMOR_TIGER_EYE = EnumHelper.addArmorMaterial("TIGER_EYE", "apex_predator", 4096, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F);
    public static ItemArmor.ArmorMaterial ARMOR_SPINEL = EnumHelper.addArmorMaterial("SPINEL", "spinel_princess", 5120, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_ZIRCON = EnumHelper.addArmorMaterial("ZIRCON", "zircon_prince", 6144, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_CORRUPT = EnumHelper.addArmorMaterial("CORRUPT", "corrupt_warrior", -1, new int[]{4, 7, 9, 4}, 30, SoundEvents.ENTITY_BLAZE_HURT, 4.0F);
    public static ItemArmor.ArmorMaterial ARMOR_BIXBITE = EnumHelper.addArmorMaterial("BIXBITE", "gaia_duchess", 8192, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public static ItemArmor.ArmorMaterial ARMOR_TSVAROVITE = EnumHelper.addArmorMaterial("TSVAROVITE", "gaia_baron", 9216, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.5F);
    public static ItemArmor.ArmorMaterial ARMOR_LARVIKITE = EnumHelper.addArmorMaterial("LARVAKITE", "gaia_duke", 10240, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0F);
    public static ItemArmor.ArmorMaterial ARMOR_GAIA_CHAMP = EnumHelper.addArmorMaterial("GAIA_CHAMP", "gaia_champion", -1, new int[]{4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);

    public static Item.ToolMaterial TOOL_AGATE = EnumHelper.addToolMaterial("AGATE", 0, 150, 0.5F, 1.0F, 5);
    public static Item.ToolMaterial TOOL_SUGILITE = EnumHelper.addToolMaterial("SUGILITE", 1, 800, 1.0F, 1.5F, 10);
    public static Item.ToolMaterial TOOL_IXIOLITE = EnumHelper.addToolMaterial("IXIOLITE", 2, 1500, 2.0F, 2.0F, 10);
    public static Item.ToolMaterial TOOL_EUCLASE = EnumHelper.addToolMaterial("EUCLASE", 2, 3000, 2.0F, 2.0F, 10);
    public static Item.ToolMaterial TOOL_CARNELIAN = EnumHelper.addToolMaterial("CARNELIAN", 3, 2500, 3.0F, 3.0F, 10);
    public static Item.ToolMaterial TOOL_BENITOITE = EnumHelper.addToolMaterial("BENITOITE", 3, 3500, 3.0F, 3.0F, 10);
    public static Item.ToolMaterial TOOL_CHALCEDONY = EnumHelper.addToolMaterial("CHALCEDONY", 4, 4000, 4.0F, 4.0F, 10);
    public static Item.ToolMaterial TOOL_MALACHITE = EnumHelper.addToolMaterial("MALACHITE", 3, 5120, 6.0F, 4.0F, 10);
    public static Item.ToolMaterial TOOL_TIGER_EYE = EnumHelper.addToolMaterial("TIGER_EYE", 3, 4096, 6.0F, 5.0F, 10);
    public static Item.ToolMaterial TOOL_SPINEL = EnumHelper.addToolMaterial("SPINEL", 3, 5120, 6.0F, 4.0F, 15);
    public static Item.ToolMaterial TOOL_ZIRCON = EnumHelper.addToolMaterial("ZIRCON", 3, 6144, 6.0F, 5.0F, 15);
    public static Item.ToolMaterial TOOL_CORRUPT = EnumHelper.addToolMaterial("CORRUPT", 4, -1, 8.0F, 21.0F, 25);
    public static Item.ToolMaterial TOOL_BIXBITE = EnumHelper.addToolMaterial("BIXBITE", 3,8192, 6.0F, 1.5F, 20);
    public static Item.ToolMaterial TOOL_TSVAROVITE = EnumHelper.addToolMaterial("TSVAROVITE", 3, 9216, 6.0F, 1.0F, 20);
    public static Item.ToolMaterial TOOL_LARVIKITE = EnumHelper.addToolMaterial("LARVIKITE", 3, 10240, 6.0F, 5.0F, 20);
    public static Item.ToolMaterial TOOL_GAIA_CHAMP = EnumHelper.addToolMaterial("GAIA_CHAMP", 4, -1, 8.0F, 16.0F, 25);

    @ObjectHolder("glint_and_gold")
    public static final Item glintAndGold;
    @ObjectHolder("agate_stick")
    public static final Item agateStick;
    @ObjectHolder("hot_dust")
    public static final Item hotDust;
    @ObjectHolder("goldstone_dust")
    public static final Item goldstoneDust;
    @ObjectHolder("fine_dust")
    public static final Item fineDust;
    @ObjectHolder("cloudy_shard")
    public static final Item cloudyShard;
    @ObjectHolder("goldstone_residue")
    public static final Item goldstoneResidue;

    @ObjectHolder("pink_geode")
    public static final Item pinkGeode;
    @ObjectHolder("blue_geode")
    public static final Item blueGeode;
    @ObjectHolder("green_geode")
    public static final Item greenGeode;
    @ObjectHolder("purple_geode")
    public static final Item purpleGeode;
    @ObjectHolder("pink_geode_slice")
    public static final Item pinkGeodeSlice;
    @ObjectHolder("blue_geode_slice")
    public static final Item blueGeodeSlice;
    @ObjectHolder("green_geode_slice")
    public static final Item greenGeodeSlice;
    @ObjectHolder("purple_geode_slice")
    public static final Item purpleGeodeSlice;

    @ObjectHolder("hematite_powder")
    public static final Item hematitePowder;
    @ObjectHolder("pyrite_powder")
    public static final Item pyritePowder;
    @ObjectHolder("labradorite_powder")
    public static final Item labradoritePowder;
    @ObjectHolder("moonstone_powder")
    public static final Item moonstonePowder;
    @ObjectHolder("cinnabar_powder")
    public static final Item cinnabarPowder;
    @ObjectHolder("red_opal_powder")
    public static final Item redOpalPowder;
    @ObjectHolder("blue_opal_powder")
    public static final Item blueOpalPowder;
    @ObjectHolder("green_opal_powder")
    public static final Item greenOpalPowder;
    @ObjectHolder("white_opal_grit")
    public static final Item whiteOpalGrit;

    @ObjectHolder("sugilite")
    public static final Item sugilite;
    @ObjectHolder("hematite")
    public static final Item hematite;
    @ObjectHolder("pyrite")
    public static final Item pyrite;
    @ObjectHolder("labradorite")
    public static final Item labradorite;
    @ObjectHolder("moonstone")
    public static final Item moonstone;
    @ObjectHolder("cinnabar")
    public static final Item cinnabar;
    @ObjectHolder("red_opal")
    public static final Item opalRed;
    @ObjectHolder("blue_opal")
    public static final Item opalBlue;
    @ObjectHolder("green_opal")
    public static final Item opalGreen;
    @ObjectHolder("white_opal")
    public static final Item opalWhite;
    @ObjectHolder("ixiolite")
    public static final Item ixiolite;
    @ObjectHolder("proustite")
    public static final Item proustite;
    @ObjectHolder("euclase")
    public static final Item euclase;
    @ObjectHolder("leucite")
    public static final Item leucite;
    @ObjectHolder("carnelian")
    public static final Item carnelian;
    @ObjectHolder("benitoite")
    public static final Item benitoite;
    @ObjectHolder("diopside")
    public static final Item diopside;
    @ObjectHolder("chalcedony")
    public static final Item chalcedony;
    @ObjectHolder("black_residue")
    public static final Item blackResidue;
    @ObjectHolder("tektite")
    public static final Item tektite;
    @ObjectHolder("goldstone")
    public static final Item goldstone;

    @ObjectHolder("malachite_guard_headgear")
    public static final Item malachiteGuardHeadgear;
    @ObjectHolder("malachite_guard_brace")
    public static final Item malachiteGuardBrace;
    @ObjectHolder("malachite_guard_gear")
    public static final Item malachiteGuardGear;
    @ObjectHolder("malachite_guard_boots")
    public static final Item malachiteGuardBoots;
    @ObjectHolder("apex_predator_hood")
    public static final Item apexPredatorHood;
    @ObjectHolder("apex_predator_jacket")
    public static final Item apexPredatorJacket;
    @ObjectHolder("apex_predator_trousers")
    public static final Item apexPredatorTrousers;
    @ObjectHolder("apex_predator_boots")
    public static final Item apexPredatorBoots;
    @ObjectHolder("spinel_princess_cowl")
    public static final Item spinelPrincessCowl;
    @ObjectHolder("spinel_princess_cloak")
    public static final Item spinelPrincessCloak;
    @ObjectHolder("spinel_princess_dress")
    public static final Item spinelPrincessDress;
    @ObjectHolder("spinel_princess_heels")
    public static final Item spinelPrincessHeels;
    @ObjectHolder("zircon_prince_crown")
    public static final Item zirconPrinceCrown;
    @ObjectHolder("zircon_prince_chestpiece")
    public static final Item zirconPrinceChestpiece;
    @ObjectHolder("zircon_prince_gear")
    public static final Item zirconPrinceGear;
    @ObjectHolder("zirconPrinceBoots")
    public static final Item zirconPrinceBoots;
    @ObjectHolder("corrupt_warrior_helm")
    public static final Item corruptWarriorHelm;
    @ObjectHolder("corrupt_warrior_guard")
    public static final Item corruptWarriorGuard;
    @ObjectHolder("corrupt_warrior_greaves")
    public static final Item corruptWarriorGreaves;
    @ObjectHolder("corrupt_warrior_boots")
    public static final Item corruptWarriorBoots;
    @ObjectHolder("gaia_duchess_helm")
    public static final Item gaiaDuchessHelm;
    @ObjectHolder("gaia_duchess_guard")
    public static final Item gaiaDuchessGuard;
    @ObjectHolder("gaia_duchess_greaves")
    public static final Item gaiaDuchessGreaves;
    @ObjectHolder("gaia_duchess_boots")
    public static final Item gaiaDuchessBoots;
    @ObjectHolder("gaia_baron_mask")
    public static final Item gaiaBaronMask;
    @ObjectHolder("gaia_baron_tuxedo")
    public static final Item gaiaBaronTuxedo;
    @ObjectHolder("gaia_baron_pants")
    public static final Item gaiaBaronPants;
    @ObjectHolder("gaia_baron_shoes")
    public static final Item gaiaBaronShoes;
    @ObjectHolder("gaia_duke_helm")
    public static final Item gaiaDukeHelm;
    @ObjectHolder("gaia_duke_guard")
    public static final Item gaiaDukeGuards;
    @ObjectHolder("gaia_duke_greaves")
    public static final Item gaiaDukeGreaves;
    @ObjectHolder("gaia_duke_boots")
    public static final Item gaiaDukeBoots;
    @ObjectHolder("gaia_champion_helm")
    public static final Item gaiaChampionHelm;
    @ObjectHolder("gaia_champion_guard")
    public static final Item gaiaChampionGuard;
    @ObjectHolder("gaia_champion_greaves")
    public static final Item gaiaChampionGreaves;
    @ObjectHolder("gaia_champion_boots")
    public static final Item gaiaChampionBoots;

    @ObjectHolder("agate_sword")
    public static final Item agateSword;
    @ObjectHolder("agate_pickaxe")
    public static final Item agatePickaxe;
    @ObjectHolder("agate_axe")
    public static final Item agateAxe;
    @ObjectHolder("agate_shovel")
    public static final Item agateShovel;
    @ObjectHolder("sugilite_sword")
    public static final Item sugiliteSword;
    @ObjectHolder("sugilite_pickaxe")
    public static final Item sugilitePickaxe;
    @ObjectHolder("sugilite_axe")
    public static final Item sugiliteAxe;
    @ObjectHolder("sugilite_shovel")
    public static final Item sugiliteShovel;

    @ObjectHolder("malachite_guard_sword")
    public static final Item malachiteGuardSword;
    @ObjectHolder("apex_predator_sword")
    public static final Item apexPredatorSword;
    @ObjectHolder("spinel_princess_sword")
    public static final Item spinelPrincessSword;
    @ObjectHolder("zircon_prince_sword")
    public static final Item zirconPrinceSword;
    @ObjectHolder("corrupt_warrior_sword")
    public static final Item corruptWarriorSword;
    @ObjectHolder("gaia_duchess_sword")
    public static final Item gaiaDuchessSword;
    @ObjectHolder("gaia_baron_sword")
    public static final Item gaiaBaronSword;
    @ObjectHolder("gaia_duke_sword")
    public static final Item gaiaDukeSword;
    @ObjectHolder("gaia_champion_sword")
    public static final Item gaiaChampionSword;

    static {
        glintAndGold = null;
        agateStick = null;
        hotDust = null;
        goldstoneDust = null;
        fineDust = null;
        cloudyShard = null;
        goldstoneResidue = null;

        pinkGeode = null;
        blueGeode = null;
        greenGeode = null;
        purpleGeode = null;
        pinkGeodeSlice = null;
        blueGeodeSlice = null;
        greenGeodeSlice = null;
        purpleGeodeSlice = null;

        hematitePowder = null;
        pyritePowder = null;
        labradoritePowder = null;
        moonstonePowder = null;
        cinnabarPowder = null;
        redOpalPowder = null;
        blueOpalPowder = null;
        greenOpalPowder = null;
        whiteOpalGrit = null;

        sugilite = null;
        hematite = null;
        pyrite = null;
        labradorite = null;
        moonstone = null;
        cinnabar = null;
        opalRed = null;
        opalBlue = null;
        opalGreen = null;
        opalWhite = null;
        ixiolite = null;
        proustite = null;
        euclase = null;
        leucite = null;
        carnelian = null;
        benitoite = null;
        diopside = null;
        chalcedony = null;
        blackResidue = null;
        tektite = null;
        goldstone = null;

        agateSword = null;
        agatePickaxe = null;
        agateAxe = null;
        agateShovel = null;
        sugiliteSword = null;
        sugilitePickaxe = null;
        sugiliteAxe = null;
        sugiliteShovel = null;

        malachiteGuardHeadgear = null;
        malachiteGuardBrace = null;
        malachiteGuardGear = null;
        malachiteGuardBoots = null;
        apexPredatorHood = null;
        apexPredatorJacket = null;
        apexPredatorTrousers = null;
        apexPredatorBoots = null;
        spinelPrincessCowl = null;
        spinelPrincessCloak = null;
        spinelPrincessDress = null;
        spinelPrincessHeels = null;
        zirconPrinceCrown = null;
        zirconPrinceChestpiece = null;
        zirconPrinceGear = null;
        zirconPrinceBoots = null;
        corruptWarriorHelm = null;
        corruptWarriorGuard = null;
        corruptWarriorGreaves = null;
        corruptWarriorBoots = null;
        gaiaDuchessHelm = null;
        gaiaDuchessGuard = null;
        gaiaDuchessGreaves = null;
        gaiaDuchessBoots = null;
        gaiaBaronMask = null;
        gaiaBaronTuxedo = null;
        gaiaBaronPants = null;
        gaiaBaronShoes = null;
        gaiaDukeHelm = null;
        gaiaDukeGuards = null;
        gaiaDukeGreaves = null;
        gaiaDukeBoots = null;
        gaiaChampionHelm = null;
        gaiaChampionGuard = null;
        gaiaChampionGreaves = null;
        gaiaChampionBoots = null;

        malachiteGuardSword = null;
        apexPredatorSword = null;
        spinelPrincessSword = null;
        zirconPrinceSword = null;
        corruptWarriorSword = null;
        gaiaDuchessSword = null;
        gaiaBaronSword = null;
        gaiaDukeSword = null;
        gaiaChampionSword = null;
    }

}
