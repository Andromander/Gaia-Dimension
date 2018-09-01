package androsa.gaiadimension.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDTabs {

    public static void init() {
        //Call this so the tabs can load
    }

    //Tab for Blocks
    public static final CreativeTabs tabBlock = new CreativeTabs("gaia_blocks") {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GDBlocks.glitter_grass);
        }
    };

    //Tab for generic Items
    public static final CreativeTabs tabItem = new CreativeTabs("gaia_items") {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GDItems.hematite);
        }
    };

    //Tab for Tools, including Swords. Basically anything able to be used in combat
    //Yes, I'm saying that Hoes can be used for combat
    //Even throwable projectiles, if I ever get to implementing them
    public static final CreativeTabs tabTool = new CreativeTabs("gaia_tools") {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GDItems.gaia_champion_sword);
        }
    };

    //Tab for anything wearable
    //Look, I'm just going to say that I will not look at Baubles, but decorative Armor
    public static final CreativeTabs tabArmor = new CreativeTabs("gaia_armor") {
        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack createIcon() {
            return new ItemStack(GDItems.gaia_champion_helm);
        }
    };
}
