package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(GaiaDimension.MODID)
public class GDItems {

    public static ItemArmor.ArmorMaterial ARMOR_MALACHITE = EnumHelper.addArmorMaterial("MALACHITE", "malachite_guard", 5120, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public static ItemArmor.ArmorMaterial ARMOR_TIGER_EYE = EnumHelper.addArmorMaterial("TIGER_EYE", "apex_predator", 4096, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.5F);
    public static ItemArmor.ArmorMaterial ARMOR_SPINEL = EnumHelper.addArmorMaterial("SPINEL", "spinel_princess", 5120, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_ZIRCON = EnumHelper.addArmorMaterial("ZIRCON", "zircon_prince", 6144, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F);
    public static ItemArmor.ArmorMaterial ARMOR_CORRUPT = EnumHelper.addArmorMaterial("CORRUPT", "corrupt_warrior", -1, new int[]{4, 7, 9, 4}, 30, SoundEvents.ENTITY_BLAZE_HURT, 4.0F);
    public static ItemArmor.ArmorMaterial ARMOR_BIXBITE = EnumHelper.addArmorMaterial("BIXBITE", "gaia_duchess", 8192, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F);
    public static ItemArmor.ArmorMaterial ARMOR_TSVAROVITE = EnumHelper.addArmorMaterial("TSVAROVITE", "gaia_baron", 9216, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.5F);
    public static ItemArmor.ArmorMaterial ARMOR_LARVIKITE = EnumHelper.addArmorMaterial("LARVAKITE", "gaia_duke", 10240, new int[]{3, 6, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0F);
    public static ItemArmor.ArmorMaterial ARMOR_GAIA_CHAMP = EnumHelper.addArmorMaterial("GAIA_CHAMP", "gaia_champion", -1, new int[]{4, 7, 9, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);

    public static Item.ToolMaterial TOOL_GAIA_CHAMP = EnumHelper.addToolMaterial("GAIA_CHAMP", 4, -1, 8.0F, 16.0F, 25);

    @ObjectHolder("hot_dust")
    public static final Item hotDust;
    @ObjectHolder("goldstone_dust")
    public static final Item goldstoneDust;
    @ObjectHolder("fine_dust")
    public static final Item fineDust;
    @ObjectHolder("cloudy_shard")
    public static final Item cloudyShard;

    @ObjectHolder("hematite")
    public static final Item hematite;
    @ObjectHolder("pyrite")
    public static final Item pyrite;
    @ObjectHolder("laboradorite")
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

    @ObjectHolder("gaia_champion_sword")
    public static final Item gaiaChampionSword;

    static {
        hotDust = null;
        goldstoneDust = null;
        fineDust = null;
        cloudyShard = null;

        hematite = null;
        pyrite = null;
        labradorite = null;
        moonstone = null;
        cinnabar = null;
        opalRed = null;
        opalBlue = null;
        opalGreen = null;
        opalWhite = null;

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

        gaiaChampionSword = null;
    }

}
