package androsa.gaiadimension;

import androsa.gaiadimension.biomes.BaseGaiaBiome;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    public static void registerBlockColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0xF2A3B4,
                ModBlocks.glitter_grass.get(),
                ModBlocks.crystal_growth.get());

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0x606060,
                ModBlocks.murky_grass.get());

        blocks.register((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null && worldIn.getBiome(pos) instanceof BaseGaiaBiome ? BiomeColors.getGrassColor(worldIn, pos) : 0xA0A0A0,
                ModBlocks.soft_grass.get());

        blocks.register((state, worldIn, pos, tintIndex) -> {
            if (worldIn != null && pos != null) {
                return getAuraColor(pos);
            } else {
                return 0xFFFFFF;
            }

        }, ModBlocks.aura_leaves.get());

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
        }, ModBlocks.aura_shoot.get());
    }

    public static int getBismuthColor(BlockPos pos) {
        int red = (int) ((MathHelper.cos((float) Math.toRadians(pos.getX() * 4)) + 1F) / 2F * 0xFF);
        int green = (int) ((MathHelper.cos((float) Math.toRadians(pos.getY() * 8)) + 1F) / 3F * 0xFF);
        int blue = (int) ((MathHelper.cos((float) Math.toRadians(pos.getZ() * 4)) + 1F) / 2F * 0xFF);

        red = MathHelper.clamp(red, 20, 170);
        green = MathHelper.clamp(green, 20, 160);
        blue = MathHelper.clamp(blue, 20, 200);

        return (red << 16) | (green << 8) | blue;
    }

    public static int getAuraColor(BlockPos pos) {
        int red = (int) ((MathHelper.cos((float) Math.toRadians((pos.getX() + 100) * 8)) + 1F) / 2F * 0xFF);
        int green = (int) ((MathHelper.cos((float) Math.toRadians((pos.getY() + 100) * 32)) + 1F) / 2F * 0xFF);
        int blue = (int) ((MathHelper.cos((float) Math.toRadians((pos.getZ() + 100) * 8)) + 1F) / 2F * 0xFF);

        red = MathHelper.clamp(red, 150, 256);
        green = MathHelper.clamp(green, 100, 220);
        blue = MathHelper.clamp(blue, 150, 256);

        return (red << 16) | (green << 8) | blue;
    }

    public static void registerItemColors() {
        BlockColors blocks = Minecraft.getInstance().getBlockColors();
        ItemColors items = Minecraft.getInstance().getItemColors();

        items.register((stack, tintIndex) -> blocks.getColor(((BlockItem)stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
                ModBlocks.glitter_grass.get(),
                ModBlocks.crystal_growth.get(),
                ModBlocks.murky_grass.get(),
                ModBlocks.aura_shoot.get(),
                ModBlocks.soft_grass.get());
    }
}
