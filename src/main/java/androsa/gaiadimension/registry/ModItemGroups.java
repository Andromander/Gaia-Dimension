package androsa.gaiadimension.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroups {

    //Tab for Blocks
    public static final ItemGroup GAIA_BLOCKS = new ItemGroup("gaia_blocks") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.glitter_grass);
        }
    };

    //Tab for generic Items
    public static final ItemGroup GAIA_ITEMS = new ItemGroup("gaia_items") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModItems.hematite);
        }
    };

    //Tab for Tools, including Swords. Basically anything able to be used in combat
    //Yes, I'm saying that Hoes can be used for combat
    public static final ItemGroup GAIA_TOOLS = new ItemGroup("gaia_tools") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModItems.gaia_champion_sword);
        }
    };

    //Tab for anything wearable
    //Look, I'm just going to say that I will not look at Baubles, but decorative Armor
    public static final ItemGroup GAIA_ARMOR = new ItemGroup("gaia_armor") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModItems.gaia_champion_helm);
        }
    };
}
