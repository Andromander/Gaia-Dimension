package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static androsa.gaiadimension.registry.GDItems.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

@Mod.EventBusSubscriber()
public class GDItemsRegister {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ItemRegistryHelper items = new ItemRegistryHelper(event.getRegistry());

        items.register("agate_stick", new GDItem().setUnlocalizedName("agate_stick"));
        items.register("hot_dust", new GDItem().setUnlocalizedName("hot_dust"));
        items.register("goldstone_dust", new GDItem().setUnlocalizedName("goldstone_dust"));
        items.register("fine_dust", new GDItem().setUnlocalizedName("fine_dust"));
        items.register("cloudy_shard", new GDItem().setUnlocalizedName("cloudy_shard"));

        items.register("hematite_powder", new GDGroundGem().setUnlocalizedName("hematite_powder"));
        items.register("pyrite_powder", new GDGroundGem().setUnlocalizedName("pyrite_powder"));
        items.register("labradorite_powder", new GDGroundGem().setUnlocalizedName("labradorite_powder"));
        items.register("moonstone_powder", new GDGroundGem().setUnlocalizedName("moonstone_powder"));
        items.register("cinnabar_powder", new GDGroundGem().setUnlocalizedName("cinnabar_powder"));
        items.register("red_opal_powder", new GDGroundGem().setUnlocalizedName("red_opal_powder"));
        items.register("blue_opal_powder", new GDGroundGem().setUnlocalizedName("blue_opal_powder"));
        items.register("green_opal_powder", new GDGroundGem().setUnlocalizedName("green_opal_powder"));
        items.register("white_opal_grit", new GDGroundGem().setUnlocalizedName("white_opal_grit"));

        items.register("property_dust", new GDPropertyDust().setUnlocalizedName("property_mix"));

        items.register("hematite", new GDItem().setUnlocalizedName("hematite"));
        items.register("pyrite", new GDItem().setUnlocalizedName("pyrite"));
        items.register("labradorite", new GDItem().setUnlocalizedName("laboradorite"));
        items.register("moonstone", new GDItem().setUnlocalizedName("moonstone"));
        items.register("cinnabar", new GDItem().setUnlocalizedName("cinnabar"));
        items.register("red_opal", new GDItem().setUnlocalizedName("red_opal"));
        items.register("blue_opal", new GDItem().setUnlocalizedName("blue_opal"));
        items.register("green_opal", new GDItem().setUnlocalizedName("green_opal"));
        items.register("white_opal", new GDItem().setUnlocalizedName("white_opal"));

        items.register("malachite_guard_headgear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, HEAD).setUnlocalizedName("malachite_guard_headgear").setMaxStackSize(1));
        items.register("malachite_guard_brace", new GDMalachiteGuardArmor(ARMOR_MALACHITE, CHEST).setUnlocalizedName("malachite_guard_brace").setMaxStackSize(1));
        items.register("malachite_guard_gear", new GDMalachiteGuardArmor(ARMOR_MALACHITE, LEGS).setUnlocalizedName("malachite_guard_gear").setMaxStackSize(1));
        items.register("malachite_guard_boots", new GDMalachiteGuardArmor(ARMOR_MALACHITE, FEET).setUnlocalizedName("malachite_guard_boots").setMaxStackSize(1));
        items.register("apex_predator_hood", new GDApexPredatorArmor(ARMOR_TIGER_EYE, HEAD).setUnlocalizedName("apex_predator_hood").setMaxStackSize(1));
        items.register("apex_predator_jacket", new GDApexPredatorArmor(ARMOR_TIGER_EYE, CHEST).setUnlocalizedName("apex_predator_jacket").setMaxStackSize(1));
        items.register("apex_predator_trousers", new GDApexPredatorArmor(ARMOR_TIGER_EYE, LEGS).setUnlocalizedName("apex_predator_trousers").setMaxStackSize(1));
        items.register("apex_predator_boots", new GDApexPredatorArmor(ARMOR_TIGER_EYE, FEET).setUnlocalizedName("apex_predator_boots").setMaxStackSize(1));
        items.register("spinel_princess_cowl", new GDSpinelPrincessArmor(ARMOR_SPINEL, HEAD).setUnlocalizedName("spinel_princess_cowl").setMaxStackSize(1));
        items.register("spinel_princess_cloak", new GDSpinelPrincessArmor(ARMOR_SPINEL, CHEST).setUnlocalizedName("spinel_princess_cloak").setMaxStackSize(1));
        items.register("spinel_princess_dress", new GDSpinelPrincessArmor(ARMOR_SPINEL, LEGS).setUnlocalizedName("spinel_princess_dress").setMaxStackSize(1));
        items.register("spinel_princess_heels", new GDSpinelPrincessArmor(ARMOR_SPINEL, FEET).setUnlocalizedName("spinel_princess_heel").setMaxStackSize(1));
        items.register("zircon_prince_crown", new GDZirconPrinceArmor(ARMOR_ZIRCON, HEAD).setUnlocalizedName("zircon_prince_crown").setMaxStackSize(1));
        items.register("zircon_prince_chestpiece", new GDZirconPrinceArmor(ARMOR_ZIRCON, CHEST).setUnlocalizedName("zircon_prince_chestpiece").setMaxStackSize(1));
        items.register("zircon_prince_gear", new GDZirconPrinceArmor(ARMOR_ZIRCON, LEGS).setUnlocalizedName("zircon_prince_gear").setMaxStackSize(1));
        items.register("zircon_prince_boots", new GDZirconPrinceArmor(ARMOR_ZIRCON, FEET).setUnlocalizedName("zircon_prince_boots").setMaxStackSize(1));
        items.register("corrupt_warrior_helm", new GDCorruptWarriorArmor(ARMOR_CORRUPT, HEAD).setUnlocalizedName("corrupt_warrior_helm").setMaxStackSize(1));
        items.register("corrupt_warrior_guard", new GDCorruptWarriorArmor(ARMOR_CORRUPT, CHEST).setUnlocalizedName("corrupt_warrior_guard").setMaxStackSize(1));
        items.register("corrupt_warrior_greaves", new GDCorruptWarriorArmor(ARMOR_CORRUPT, LEGS).setUnlocalizedName("corrupt_warrior_greaves").setMaxStackSize(1));
        items.register("corrupt_warrior_boots", new GDCorruptWarriorArmor(ARMOR_CORRUPT, FEET).setUnlocalizedName("corrupt_warrior_boots").setMaxStackSize(1));
        items.register("gaia_duchess_helm", new GDGaiaDuchessArmor(ARMOR_BIXBITE, HEAD).setUnlocalizedName("gaia_duchess_helm").setMaxStackSize(1));
        items.register("gaia_duchess_guard", new GDGaiaDuchessArmor(ARMOR_BIXBITE, CHEST).setUnlocalizedName("gaia_duchess_guard").setMaxStackSize(1));
        items.register("gaia_duchess_greaves", new GDGaiaDuchessArmor(ARMOR_BIXBITE, LEGS).setUnlocalizedName("gaia_duchess_greaves").setMaxStackSize(1));
        items.register("gaia_duchess_boots", new GDGaiaDuchessArmor(ARMOR_BIXBITE, FEET).setUnlocalizedName("gaia_duchess_boots").setMaxStackSize(1));
        items.register("gaia_baron_mask", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, HEAD).setUnlocalizedName("gaia_baron_mask").setMaxStackSize(1));
        items.register("gaia_baron_tuxedo", new GDGaiaBaronArmor(ARMOR_TSVAROVITE,CHEST).setUnlocalizedName("gaia_baron_tuxedo").setMaxStackSize(1));
        items.register("gaia_baron_pants", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, LEGS).setUnlocalizedName("gaia_baron_pants").setMaxStackSize(1));
        items.register("gaia_baron_shoes", new GDGaiaBaronArmor(ARMOR_TSVAROVITE, FEET).setUnlocalizedName("gaia_baron_shoes").setMaxStackSize(1));
        items.register("gaia_duke_helm", new GDGaiaDukeArmor(ARMOR_LARVIKITE, HEAD).setUnlocalizedName("gaia_duke_helm").setMaxStackSize(1));
        items.register("gaia_duke_guard", new GDGaiaDukeArmor(ARMOR_LARVIKITE, CHEST).setUnlocalizedName("gaia_duke_guard").setMaxStackSize(1));
        items.register("gaia_duke_greaves", new GDGaiaDukeArmor(ARMOR_LARVIKITE, LEGS).setUnlocalizedName("gaia_duke_greaves").setMaxStackSize(1));
        items.register("gaia_duke_boots", new GDGaiaDukeArmor(ARMOR_LARVIKITE, FEET).setUnlocalizedName("gaia_duke_boots").setMaxStackSize(1));
        items.register("gaia_champion_helm", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, HEAD).setUnlocalizedName("gaia_champion_helm").setMaxStackSize(1));
        items.register("gaia_champion_guard", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, CHEST).setUnlocalizedName("gaia_champion_guard").setMaxStackSize(1));
        items.register("gaia_champion_greaves", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, LEGS).setUnlocalizedName("gaia_champion_greaves").setMaxStackSize(1));
        items.register("gaia_champion_boots", new GDGaiaChampArmor(ARMOR_GAIA_CHAMP, FEET).setUnlocalizedName("gaia_champion_boots").setMaxStackSize(1));

        items.register("malachite_guard_sword", new GDMalachiteGuardSword(TOOL_MALACHITE).setUnlocalizedName("malachite_guard_baton").setMaxStackSize(1));
        items.register("apex_predator_sword", new GDApexPredatorSword(TOOL_TIGER_EYE).setUnlocalizedName("apex_predator_mace").setMaxStackSize(1));
        items.register("spinel_princess_sword", new GDSpinelPrincessSword(TOOL_SPINEL).setUnlocalizedName("spinel_princess_flamberge").setMaxStackSize(1));
        items.register("zircon_prince_sword", new GDZirconPrinceSword(TOOL_ZIRCON).setUnlocalizedName("zircon_prince_razor").setMaxStackSize(1));
        items.register("corrupt_warrior_sword", new GDCorruptWarriorSword(TOOL_CORRUPT).setUnlocalizedName("corrupt_warrior_sword").setMaxStackSize(1));
        items.register("gaia_duchess_sword", new GDGaiaDuchessSword(TOOL_BIXBITE).setUnlocalizedName("gaia_duchess_khopesh").setMaxStackSize(1));
        items.register("gaia_baron_sword", new GDGaiaBaronSword(TOOL_TSVAROVITE).setUnlocalizedName("gaia_baron_dagger").setMaxStackSize(1));
        items.register("gaia_duke_sword", new GDGaiaDukeSword(TOOL_LARVIKITE).setUnlocalizedName("gaia_duke_blade").setMaxStackSize(1));
        items.register("gaia_champion_sword", new GDGaiaChampSword(TOOL_GAIA_CHAMP).setUnlocalizedName("gaia_champion_sword").setMaxStackSize(1));

        items.registerSubItemBlock(GDBlocks.gaiaPortal);

        items.registerBlock(GDBlocks.heavySoil);
        items.registerBlock(GDBlocks.glitterGrass);
        items.registerBlock(GDBlocks.crystalGrowth);
        items.registerSubItemBlock(GDBlocks.gaiaSapling);
        items.registerSubItemBlock(GDBlocks.gaiaLeaves);
        items.registerSubItemBlock(GDBlocks.gaiaLeavesSpecial);
        items.registerSubItemBlock(GDBlocks.gaiaLog);
        items.registerSubItemBlock(GDBlocks.gaiaLogSpecial);

        items.registerBlock(GDBlocks.pinkAgatePlanks);
        items.registerBlock(GDBlocks.blueAgatePlanks);
        items.registerBlock(GDBlocks.greenAgatePlanks);
        items.registerBlock(GDBlocks.purpleAgatePlanks);
        items.registerBlock(GDBlocks.fossilizedPlanks);
        items.registerBlock(GDBlocks.corruptedPlanks);
        items.registerBlock(GDBlocks.crustyPlanks);
        items.registerBlock(GDBlocks.heatedPlanks);
        items.register(new ItemSlab(GDBlocks.pinkAgatePlankSlab, GDBlocks.pinkAgatePlankSlab, GDBlocks.pinkAgatePlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.blueAgatePlankSlab, GDBlocks.blueAgatePlankSlab, GDBlocks.blueAgatePlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.greenAgatePlankSlab, GDBlocks.greenAgatePlankSlab, GDBlocks.greenAgatePlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.purpleAgatePlankSlab, GDBlocks.purpleAgatePlankSlab, GDBlocks.purpleAgatePlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.fossilizedPlankSlab, GDBlocks.fossilizedPlankSlab, GDBlocks.fossilizedPlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.corruptedPlankSlab, GDBlocks.corruptedPlankSlab, GDBlocks.corruptedPlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.crustyPlankSlab, GDBlocks.crustyPlankSlab, GDBlocks.crustyPlankSlabDouble));
        items.register(new ItemSlab(GDBlocks.heatedPlankSlab, GDBlocks.heatedPlankSlab, GDBlocks.heatedPlankSlabDouble));
        items.registerBlock(GDBlocks.pinkAgatePlankStairs);
        items.registerBlock(GDBlocks.blueAgatePlankStairs);
        items.registerBlock(GDBlocks.greenAgatePlankStairs);
        items.registerBlock(GDBlocks.purpleAgatePlankStairs);
        items.registerBlock(GDBlocks.fossilizedPlankStairs);
        items.registerBlock(GDBlocks.corruptedPlankStairs);
        items.registerBlock(GDBlocks.crustyPlankStairs);
        items.registerBlock(GDBlocks.heatedPlankStairs);

        items.registerBlock(GDBlocks.gaiaStone);
        items.registerSubItemBlock(GDBlocks.gaiaStoneBricks);
        items.registerSubItemBlock(GDBlocks.malachiteBricks);
        items.register(new ItemSlab(GDBlocks.malachiteBrickSlab, GDBlocks.malachiteBrickSlab, GDBlocks.malachiteBrickSlabDouble));
        items.registerBlock(GDBlocks.malachiteBrickPillar);
        items.registerBlock(GDBlocks.malachiteBrickStairs);
        items.registerBlock(GDBlocks.malachiteChiselStairs);
        items.registerBlock(GDBlocks.malachitePulsingBrickStairs);
        items.registerBlock(GDBlocks.malachitePillarStairs);
        items.registerBlock(GDBlocks.malachiteFloorStairs);
        items.registerBlock(GDBlocks.malachitePulsingFloorStairs);
        items.registerBlock(GDBlocks.volcanicRock);

        items.registerSubItemBlock(GDBlocks.opalBlock);
        items.registerBlock(GDBlocks.hematiteBlock);
        items.registerBlock(GDBlocks.labradoriteBlock);
        items.registerBlock(GDBlocks.pyriteBlock);
        items.registerBlock(GDBlocks.moonstoneBlock);
        items.registerBlock(GDBlocks.cinnabarBlock);
        items.registerBlock(GDBlocks.tektiteBlock);

        items.registerBlock(GDBlocks.hematiteOre);
        items.registerBlock(GDBlocks.pyriteOre);
        items.registerSubItemBlock(GDBlocks.opalOre);
        items.registerBlock(GDBlocks.labradoriteOre);
        items.registerBlock(GDBlocks.moonstoneOre);
        items.registerBlock(GDBlocks.cinnabarOre);
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
