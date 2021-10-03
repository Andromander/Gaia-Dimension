package androsa.gaiadimension.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GaiaItemGroups {

    //Tab for Blocks
    public static final CreativeModeTab GAIA_BLOCKS = new CreativeModeTab("gaia_blocks") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.glitter_grass.get());
        }
    };

    //Tab for generic Items
    public static final CreativeModeTab GAIA_ITEMS = new CreativeModeTab("gaia_items") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.hematite.get());
        }
    };

    //Tab for Tools, including Swords. Basically anything able to be used in combat
    //Yes, I'm saying that Hoes can be used for combat
    public static final CreativeModeTab GAIA_TOOLS = new CreativeModeTab("gaia_tools") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.gaia_champion_sword.get());
        }
    };

    //Tab for anything wearable
    //Look, I'm just going to say that I will not look at Baubles, but decorative Armor
    public static final CreativeModeTab GAIA_ARMOR = new CreativeModeTab("gaia_armor") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.gaia_champion_helm.get());
        }
    };
}
