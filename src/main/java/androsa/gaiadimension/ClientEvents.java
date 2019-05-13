package androsa.gaiadimension;

import androsa.gaiadimension.biomes.GDBiomeBase;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDBlocksRegister;
import androsa.gaiadimension.registry.GDItemsRegister;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = GaiaDimension.MODID, value = Side.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block e) {
        BlockColors blocks = e.getBlockColors();

        blocks.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof GDBiomeBase ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : 0xF2A3B4,
                GDBlocks.glitter_grass,
                GDBlocks.crystal_growth);

        blocks.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof GDBiomeBase ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : 0x606060,
                GDBlocks.murky_grass);

        blocks.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
            int red = (int) ((MathHelper.cos((float) Math.toRadians(pos.getX() * 2)) + 1F) / 2F * 0xFF);
            int green = (int) ((MathHelper.cos((float) Math.toRadians(pos.getY() * 2)) + 1F) / 3F * 0xFF);
            int blue = (int) ((MathHelper.cos((float) Math.toRadians(pos.getZ() * 2)) + 1F) / 2F * 0xFF);

            red = MathHelper.clamp(red, 0, 170);
            green = MathHelper.clamp(green, 0, 160);
            blue = MathHelper.clamp(blue, 0, 180);

            return (blue << 16) | (red << 8) | green;
        }, GDBlocks.liquid_bismuth_block);
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item e) {
        BlockColors blocks = e.getBlockColors();
        ItemColors items = e.getItemColors();

        items.registerItemColorHandler((stack, tintIndex) -> blocks.colorMultiplier(((ItemBlock)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                GDBlocks.glitter_grass,
                GDBlocks.crystal_growth,
                GDBlocks.murky_grass);
    }

    @SubscribeEvent
    public static void onModelRegistryReady(ModelRegistryEvent event) {
        for (ModelRegisterCallback b : GDBlocksRegister.getBlockModels()) b.registerModel();

        for (ModelRegisterCallback i : GDItemsRegister.ItemRegistryHelper.getItemModels()) i.registerModel();
    }
}
