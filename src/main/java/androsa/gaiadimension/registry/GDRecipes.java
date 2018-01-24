package androsa.gaiadimension.registry;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class GDRecipes {

    @SubscribeEvent
    public static void ragisterRecipes(RegistryEvent.Register<IRecipe> event) {

        GameRegistry.addSmelting(new ItemStack(GDItems.fineDust, 1), new ItemStack(GDItems.cloudyShard, 1), 0.1F);
        GameRegistry.addSmelting(GDBlocks.hematiteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.pyriteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.cinnabarOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.labradoriteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.moonstoneOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 0), new ItemStack(GDItems.opalRed, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 1), new ItemStack(GDItems.opalBlue, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 2), new ItemStack(GDItems.opalGreen, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 3), new ItemStack(GDItems.opalWhite, 1), 1.0F);
    }
}
