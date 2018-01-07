package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.item.GDGaiaChampArmor;
import androsa.gaiadimension.item.GDGaiaChampSword;
import androsa.gaiadimension.item.GDItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static androsa.gaiadimension.registry.GDItems.ARMOR_GAIA_CHAMP;
import static androsa.gaiadimension.registry.GDItems.TOOL_GAIA_CHAMP;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

@Mod.EventBusSubscriber()
public class GDItemsRegister {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ItemRegistryHelper items = new ItemRegistryHelper(event.getRegistry());

        items.register("hematite", new GDItem().setUnlocalizedName("hematite"));
        items.register("pyrite", new GDItem().setUnlocalizedName("pyrite"));
        items.register("labradorite", new GDItem().setUnlocalizedName("laboradorite"));
        items.register("moonstone", new GDItem().setUnlocalizedName("moonstone"));
        items.register("red_opal", new GDItem().setUnlocalizedName("red_opal"));
        items.register("blue_opal", new GDItem().setUnlocalizedName("blue_opal"));
        items.register("green_opal", new GDItem().setUnlocalizedName("green_opal"));
        items.register("white_opal", new GDItem().setUnlocalizedName("white_opal"));

        items.register("gaia_champion_helm", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, HEAD).setUnlocalizedName("gaia_champion_helm").setMaxStackSize(1));
        items.register("gaia_champion_guard", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, CHEST).setUnlocalizedName("gaia_champion_guard").setMaxStackSize(1));
        items.register("gaia_champion_greaves", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, LEGS).setUnlocalizedName("gaia_champion_greaves").setMaxStackSize(1));
        items.register("gaia_champion_boots", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, FEET).setUnlocalizedName("gaia_champion_boots").setMaxStackSize(1));

        items.register("gaia_champion_sword", new GDGaiaChampSword(TOOL_GAIA_CHAMP).setUnlocalizedName("gaia_champion_sword").setMaxStackSize(1));

        items.registerBlock(GDBlocks.heavySoil);
        items.registerBlock(GDBlocks.glitterGrass);
        items.registerSubItemBlock(GDBlocks.gaiaLeaves);
        items.registerSubItemBlock(GDBlocks.gaiaLeavesSpecial);
        items.registerSubItemBlock(GDBlocks.gaiaLog);
        items.registerSubItemBlock(GDBlocks.gaiaLogSpecial);
        items.registerBlock(GDBlocks.gaiaStone);
        items.registerSubItemBlock(GDBlocks.gaiaStoneBricks);

        items.registerSubItemBlock(GDBlocks.opalBlock);
        items.registerBlock(GDBlocks.hematiteBlock);
        items.registerBlock(GDBlocks.labradoriteBlock);
        items.registerBlock(GDBlocks.pyriteBlock);
        items.registerBlock(GDBlocks.moonstoneBlock);

        items.registerBlock(GDBlocks.hematiteOre);
        items.registerBlock(GDBlocks.pyriteOre);
        items.registerSubItemBlock(GDBlocks.opalOre);
        items.registerBlock(GDBlocks.labradoriteOre);
        items.registerBlock(GDBlocks.moonstoneOre);
    }

    private static class ItemRegistryHelper {
        private final IForgeRegistry<Item> registry;

        ItemRegistryHelper(IForgeRegistry<Item> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Item item) {
            item.setRegistryName(GaiaDimension.MODID, registryName);
            registry.register(item);
        }

        private void registerBlock(Block block) {
            ItemBlock metaItemBlock = new ItemBlock(block);
            register(metaItemBlock);
        }

        private void registerSubItemBlock(Block block) {
            registerSubItemBlock(block, true);
        }

        private void registerSubItemBlock(Block block, boolean shouldAppendNumber) {
            GDItemBlockMeta metaItemBlock = new GDItemBlockMeta(block).setAppend(shouldAppendNumber);
            register(metaItemBlock);
        }

        private void register(ItemBlock item) {
            item.setRegistryName(item.getBlock().getRegistryName());
            item.setUnlocalizedName(item.getBlock().getUnlocalizedName());
            registry.register(item);
        }
    }
}
