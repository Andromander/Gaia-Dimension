package androsa.gaiadimension.registry;

import androsa.gaiadimension.item.GDItem;
import androsa.gaiadimension.recipe.RecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class GDRecipes {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.pinkAgatePlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.blueAgatePlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.greenAgatePlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.purpleAgatePlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.fossilizedPlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.corruptedPlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.crustyPlanks, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("planksAgate", new ItemStack(GDBlocks.heatedPlanks, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addSmelting(new ItemStack(GDItems.lurmorusMeat, 1), new ItemStack(GDItems.lurmorusSteak, 1), 0.0F);
        GameRegistry.addSmelting(new ItemStack(GDItems.fineDust, 1), new ItemStack(GDItems.cloudyShard, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDItems.goldstoneDust, 1), new ItemStack(GDItems.goldstoneResidue, 1), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaiaSapling, 1, 0), new ItemStack(GDBlocks.gaiaSapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaiaSapling, 1, 1), new ItemStack(GDBlocks.gaiaSapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaiaSapling, 1, 2), new ItemStack(GDBlocks.gaiaSapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaiaSapling, 1, 3), new ItemStack(GDBlocks.gaiaSapling, 1, 6), 0.1F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.gaiaSapling, 1, 6), new ItemStack(GDBlocks.gaiaSapling, 1, 7), 0.1F);
        GameRegistry.addSmelting(GDBlocks.sugiliteOre, new ItemStack(GDItems.sugilite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.hematiteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.pyriteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.cinnabarOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.labradoriteOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(GDBlocks.moonstoneOre, new ItemStack(GDItems.hematite, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 0), new ItemStack(GDItems.opalRed, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 1), new ItemStack(GDItems.opalBlue, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 2), new ItemStack(GDItems.opalGreen, 1), 0.3F);
        GameRegistry.addSmelting(new ItemStack(GDBlocks.opalOre, 1, 3), new ItemStack(GDItems.opalWhite, 1), 1.0F);

        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.hematite, 1), new ItemStack(GDItems.ixiolite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.cinnabar, 1), new ItemStack(GDItems.proustite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.labradorite, 1), new ItemStack(GDItems.euclase, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.moonstone, 1), new ItemStack(GDItems.leucite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalRed, 1), new ItemStack(GDItems.carnelian, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalBlue, 1), new ItemStack(GDItems.benitoite, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalGreen, 1), new ItemStack(GDItems.diopside, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDItems.opalWhite, 1), new ItemStack(GDItems.chalcedony, 1), new ItemStack(GDItems.blackResidue, 1), 0.3F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.hematiteBlock, new ItemStack(GDBlocks.ixioliteBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.cinnabarBlock, new ItemStack(GDBlocks.proustiteBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.labradoriteBlock, new ItemStack(GDBlocks.euclaseBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(GDBlocks.moonstoneBlock, new ItemStack(GDBlocks.leuciteBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDBlocks.opalBlock, 1, 0), new ItemStack(GDBlocks.carnelianBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDBlocks.opalBlock, 1, 1), new ItemStack(GDBlocks.benitoiteBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDBlocks.opalBlock, 1, 2), new ItemStack(GDBlocks.diopsideBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);
        RecipeHandler.addGlitterRefactoring(new ItemStack(GDBlocks.opalBlock, 1, 3), new ItemStack(GDBlocks.chalcedonyBlock, 1), new ItemStack(GDItems.tektite, 1), 2.7F);

        RecipeHandler.addPurifying(GDBlocks.corruptSoil, new ItemStack(GDBlocks.heavySoil), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(GDBlocks.corruptGrass, new ItemStack(GDBlocks.glitterGrass), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(new ItemStack(GDBlocks.gaiaLeavesSpecial, 1, 2), new ItemStack(GDBlocks.gaiaLeaves, 1, 0), new ItemStack(GDItems.goldstoneResidue, 1), 0.3F);
        RecipeHandler.addPurifying(new ItemStack(GDBlocks.gaiaLogSpecial, 1, 2), new ItemStack(GDBlocks.gaiaLog, 1, 0), new ItemStack(GDItems.goldstoneResidue, 2), 0.3F);
    }
}
