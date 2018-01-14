package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(GaiaDimension.MODID)
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

    @GameRegistry.ObjectHolder("hematite")
    public static final Item hematite;
    @GameRegistry.ObjectHolder("pyrite")
    public static final Item pyrite;
    @GameRegistry.ObjectHolder("laboradorite")
    public static final Item labradorite;
    @GameRegistry.ObjectHolder("moonstone")
    public static final Item moonstone;
  //  @GameRegistry.ObjectHolder("sugilite")
  //  public static final Item sugilite;
    @GameRegistry.ObjectHolder("red_opal")
    public static final Item opalRed;
    @GameRegistry.ObjectHolder("blue_opal")
    public static final Item opalBlue;
    @GameRegistry.ObjectHolder("green_opal")
    public static final Item opalGreen;
    @GameRegistry.ObjectHolder("white_opal")
    public static final Item opalWhite;

    @GameRegistry.ObjectHolder("malachite_guard_headgear")
    public static final Item malachiteGuardHeadgear;
    @GameRegistry.ObjectHolder("malachite_guard_brace")
    public static final Item malachiteGuardBrace;
    @GameRegistry.ObjectHolder("malachite_guard_gear")
    public static final Item malachiteGuardGear;
    @GameRegistry.ObjectHolder("malachite_guard_boots")
    public static final Item malachiteGuardBoots;
    @GameRegistry.ObjectHolder("apex_predator_hood")
    public static final Item apexPredatorHood;
    @GameRegistry.ObjectHolder("apex_predator_jacket")
    public static final Item apexPredatorJacket;
    @GameRegistry.ObjectHolder("apex_predator_trousers")
    public static final Item apexPredatorTrousers;
    @GameRegistry.ObjectHolder("apex_predator_boots")
    public static final Item apexPredatorBoots;
    @GameRegistry.ObjectHolder("spinel_princess_cowl")
    public static final Item spinelPrincessCowl;
    @GameRegistry.ObjectHolder("spinel_princess_cloak")
    public static final Item spinelPrincessCloak;
    @GameRegistry.ObjectHolder("spinel_princess_dress")
    public static final Item spinelPrincessDress;
    @GameRegistry.ObjectHolder("spinel_princess_heels")
    public static final Item spinelPrincessHeels;
    @GameRegistry.ObjectHolder("zircon_prince_crown")
    public static final Item zirconPrinceCrown;
    @GameRegistry.ObjectHolder("zircon_prince_chestpiece")
    public static final Item zirconPrinceChestpiece;
    @GameRegistry.ObjectHolder("zircon_prince_gear")
    public static final Item zirconPrinceGear;
    @GameRegistry.ObjectHolder("zirconPrinceBoots")
    public static final Item zirconPrinceBoots;
    @GameRegistry.ObjectHolder("corrupt_warrior_helm")
    public static final Item corruptWarriorHelm;
    @GameRegistry.ObjectHolder("corrupt_warrior_guard")
    public static final Item corruptWarriorGuard;
    @GameRegistry.ObjectHolder("corrupt_warrior_greaves")
    public static final Item corruptWarriorGreaves;
    @GameRegistry.ObjectHolder("corrupt_warrior_boots")
    public static final Item corruptWarriorBoots;
    @GameRegistry.ObjectHolder("gaia_duchess_helm")
    public static final Item gaiaDuchessHelm;
    @GameRegistry.ObjectHolder("gaia_duchess_guard")
    public static final Item gaiaDuchessGuard;
    @GameRegistry.ObjectHolder("gaia_duchess_greaves")
    public static final Item gaiaDuchessGreaves;
    @GameRegistry.ObjectHolder("gaia_duchess_boots")
    public static final Item gaiaDuchessBoots;
    @GameRegistry.ObjectHolder("gaia_baron_mask")
    public static final Item gaiaBaronMask;
    @GameRegistry.ObjectHolder("gaia_baron_tuxedo")
    public static final Item gaiaBaronTuxedo;
    @GameRegistry.ObjectHolder("gaia_baron_pants")
    public static final Item gaiaBaronPants;
    @GameRegistry.ObjectHolder("gaia_baron_shoes")
    public static final Item gaiaBaronShoes;
    @GameRegistry.ObjectHolder("gaia_duke_helm")
    public static final Item gaiaDukeHelm;
    @GameRegistry.ObjectHolder("gaia_duke_guard")
    public static final Item gaiaDukeGuards;
    @GameRegistry.ObjectHolder("gaia_duke_greaves")
    public static final Item gaiaDukeGreaves;
    @GameRegistry.ObjectHolder("gaia_duke_boots")
    public static final Item gaiaDukeBoots;
    @GameRegistry.ObjectHolder("gaia_champion_helm")
    public static final Item gaiaChampionHelm;
    @GameRegistry.ObjectHolder("gaia_champion_guard")
    public static final Item gaiaChampionGuard;
    @GameRegistry.ObjectHolder("gaia_champion_greaves")
    public static final Item gaiaChampionGreaves;
    @GameRegistry.ObjectHolder("gaia_champion_boots")
    public static final Item gaiaChampionBoots;

    @GameRegistry.ObjectHolder("gaia_champion_sword")
    public static final Item gaiaChampionSword;

    static {
        hematite = null;
        pyrite = null;
        labradorite = null;
        moonstone = null;
       // sugilite = null;
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
