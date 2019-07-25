package androsa.gaiadimension;

import androsa.gaiadimension.biomes.BaseGaiaBiome;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block e) {
        BlockColors blocks = e.getBlockColors();

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0xF2A3B4,
                ModBlocks.glitter_grass,
                ModBlocks.crystal_growth);

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0x606060,
                ModBlocks.murky_grass);

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0xA0A0A0,
                ModBlocks.soft_grass);

        /*blocks.register((state, worldIn, pos, tintIndex) -> {
            if (worldIn != null && pos != null) {
                int red = (int) ((MathHelper.cos((float) Math.toRadians(pos.getX() * 2)) + 1F) / 2F * 0xFF);
                int green = (int) ((MathHelper.cos((float) Math.toRadians(pos.getY() * 2)) + 1F) / 3F * 0xFF);
                int blue = (int) ((MathHelper.cos((float) Math.toRadians(pos.getZ() * 2)) + 1F) / 2F * 0xFF);

                red = MathHelper.clamp(red, 0, 170);
                green = MathHelper.clamp(green, 0, 160);
                blue = MathHelper.clamp(blue, 0, 180);

                return (blue << 16) | (red << 8) | green;
            } else {
                return 0x808080;
            }
        }, ModBlocks.liquid_bismuth_block);

        blocks.register((state, worldIn, pos, tintIndex) -> {
            if (worldIn != null && pos != null) {
                int red = (int) ((MathHelper.cos((float) Math.toRadians((pos.getX() + 100) * 8)) + 1F) / 2F * 0xFF);
                int green = (int) ((MathHelper.cos((float) Math.toRadians((pos.getY() + 100) * 32)) + 1F) / 2F * 0xFF);
                int blue = (int) ((MathHelper.cos((float) Math.toRadians((pos.getZ() + 100) * 8)) + 1F) / 2F * 0xFF);

                red = MathHelper.clamp(red, 150, 256);
                green = MathHelper.clamp(green, 100, 220);
                blue = MathHelper.clamp(blue, 150, 256);

                return (red << 16) | (green << 8) | blue;
            } else {
                return 0xFFFFFF;
            }

        }, ModBlocks.liquid_aura_block, ModBlocks.aura_leaves);*/

        blocks.register((state, worldIn, pos, tintIndex) -> {
            int hex;

            if (worldIn != null && pos != null) {
                int location = (Math.abs(pos.getX() % 5)) + (Math.abs(pos.getZ() % 5));
                switch (location) {
                    case 0:
                        hex = 0xEA500D;
                        break;
                    case 1:
                        hex = 0xFFC24C;
                        break;
                    case 2:
                        hex = 0xC1ED26;
                        break;
                    case 3:
                        hex = 0x67FFB9;
                        break;
                    case 4:
                        hex = 0x265AEf;
                        break;
                    case 5:
                        hex = 0x5C0AD7;
                        break;
                    case 6:
                        hex = 0x5D3883;
                        break;
                    case 7:
                        hex = 0xC330E8;
                        break;
                    case 8:
                        hex = 0xFF6CAE;
                        break;
                    default:
                        hex = 0x5D3883;
                        break;
                }
            } else {
                hex = 0x1109B7;
            }

            return hex;
        }, ModBlocks.aura_shoot);
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item e) {
        BlockColors blocks = e.getBlockColors();
        ItemColors items = e.getItemColors();

        items.register((stack, tintIndex) -> blocks.getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ModBlocks.glitter_grass,
                ModBlocks.crystal_growth,
                ModBlocks.murky_grass,
                ModBlocks.aura_shoot,
                ModBlocks.soft_grass);
    }

    /*@SubscribeEvent
    public static void textureStitch(TextureStitchEvent.Pre e) {
        TextureMap map = e.getMap();

        map.registerSprite(new ResourceLocation(GaiaDimensionMod.MODID, "particle/yellow_fire"));
        map.registerSprite(new ResourceLocation(GaiaDimensionMod.MODID, "particle/green_fire"));
        map.registerSprite(new ResourceLocation(GaiaDimensionMod.MODID, "particle/portal_sparkle"));
    }*/
}
