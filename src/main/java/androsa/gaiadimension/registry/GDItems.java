package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(GaiaDimension.MODID)
public class GDItems {

    public static ItemArmor.ArmorMaterial ARMOR_MALACHITE = EnumHelper.addArmorMaterial("MALACHITE", "malachite_guard", 5120, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3.0F);
    public static ItemArmor.ArmorMaterial ARMOR_GAIA_CHAMP = EnumHelper.addArmorMaterial("GAIA_CHAMP", "gaia_champion", -1, new int[]{4, 7, 9, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 4.0F);

    public static Item.ToolMaterial TOOL_GAIA_CHAMP = EnumHelper.addToolMaterial("GAIA_CHAMP", 4, -1, 8.0F, 16.0F, 25);

    @GameRegistry.ObjectHolder("hematite")
    public static final Item hematite;
    @GameRegistry.ObjectHolder("pyrite")
    public static final Item pyrite;
    @GameRegistry.ObjectHolder("laboradorite")
    public static final Item labradorite;
    @GameRegistry.ObjectHolder("moonstone")
    public static final Item moonstone;
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
        opalRed = null;
        opalBlue = null;
        opalGreen = null;
        opalWhite = null;

        malachiteGuardHeadgear = null;
        malachiteGuardBrace = null;
        malachiteGuardGear = null;
        malachiteGuardBoots = null;
        gaiaChampionHelm = null;
        gaiaChampionGuard = null;
        gaiaChampionGreaves = null;
        gaiaChampionBoots = null;

        gaiaChampionSword = null;
    }

}
