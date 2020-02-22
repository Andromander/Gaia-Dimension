package androsa.gaiadimension;

import androsa.gaiadimension.biomes.BaseGaiaBiome;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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

    public static void registerBlockRenderers() {
        RenderType cutout = RenderType.getCutout();
        RenderType cutoutM = RenderType.getCutoutMipped();
        RenderType translucent = RenderType.getTranslucent();

        renderBlock(ModBlocks.gaia_portal, translucent);
        renderBlock(ModBlocks.gold_fire , cutout);
        renderBlock(ModBlocks.pyrite_torch, cutout);
        renderBlock(ModBlocks.pyrite_wall_torch, cutout);
        renderBlock(ModBlocks.mineral_water, translucent);
        renderBlock(ModBlocks.sweet_muck, translucent);
        renderBlock(ModBlocks.glitter_grass, cutoutM);
        renderBlock(ModBlocks.corrupt_grass, cutoutM);
        renderBlock(ModBlocks.murky_grass, cutoutM);
        renderBlock(ModBlocks.soft_grass, cutoutM);
        renderBlock(ModBlocks.frail_glitter_block, translucent);
        renderBlock(ModBlocks.gummy_glitter_block, translucent);
        renderBlock(ModBlocks.crystal_growth, translucent);
        renderBlock(ModBlocks.crystal_growth_red, translucent);
        renderBlock(ModBlocks.crystal_growth_black, translucent);
        renderBlock(ModBlocks.crystal_growth_seared, translucent);
        renderBlock(ModBlocks.crystal_growth_mutant, translucent);
        renderBlock(ModBlocks.crystal_growth_aura, cutoutM);
        renderBlock(ModBlocks.thiscus, cutoutM);
        renderBlock(ModBlocks.ouzium, cutoutM);
        renderBlock(ModBlocks.agathum, cutoutM);
        renderBlock(ModBlocks.varloom, cutoutM);
        renderBlock(ModBlocks.corrupted_varloom, cutoutM);
        renderBlock(ModBlocks.missingno_plant, cutoutM);
        renderBlock(ModBlocks.spotted_kersei, cutoutM);
        renderBlock(ModBlocks.thorny_wiltha, cutoutM);
        renderBlock(ModBlocks.roofed_agaric, cutoutM);
        renderBlock(ModBlocks.bulbous_hobina, cutoutM);
        renderBlock(ModBlocks.stickly_cupsir, cutoutM);
        renderBlock(ModBlocks.mystical_murgni, cutoutM);
        renderBlock(ModBlocks.corrupted_gaia_eye, cutoutM);
        renderBlock(ModBlocks.elder_imklia, cutoutM);
        renderBlock(ModBlocks.gold_orb_tucher, cutoutM);
        renderBlock(ModBlocks.missingno_fungus, cutoutM);
        renderBlock(ModBlocks.pink_agate_sapling, cutout);
        renderBlock(ModBlocks.blue_agate_sapling, cutout);
        renderBlock(ModBlocks.green_agate_sapling, cutout);
        renderBlock(ModBlocks.purple_agate_sapling, cutout);
        renderBlock(ModBlocks.fossilized_sapling, cutout);
        renderBlock(ModBlocks.corrupted_sapling, cutout);
        renderBlock(ModBlocks.burnt_sapling, cutout);
        renderBlock(ModBlocks.burning_sapling, cutout);
        renderBlock(ModBlocks.aura_sapling, cutout);
        renderBlock(ModBlocks.pink_agate_leaves, cutoutM);
        renderBlock(ModBlocks.blue_agate_leaves, cutoutM);
        renderBlock(ModBlocks.green_agate_leaves, cutoutM);
        renderBlock(ModBlocks.purple_agate_leaves, cutoutM);
        renderBlock(ModBlocks.fossilized_leaves, cutoutM);
        renderBlock(ModBlocks.corrupted_leaves, cutoutM);
        renderBlock(ModBlocks.burnt_leaves, cutoutM);
        renderBlock(ModBlocks.burning_leaves, cutoutM);
        renderBlock(ModBlocks.aura_leaves, cutoutM);
        renderBlock(ModBlocks.static_stone, translucent);
        renderBlock(ModBlocks.charged_mineral, translucent);
        renderBlock(ModBlocks.searing_rock, translucent);
        renderBlock(ModBlocks.active_rock, cutout);
        renderBlock(ModBlocks.cloudy_glass, translucent);
        renderBlock(ModBlocks.foggy_glass, translucent);
        renderBlock(ModBlocks.malachite_pulsing_bricks, cutout);
        renderBlock(ModBlocks.malachite_pulsing_tiles, cutout);
        renderBlock(ModBlocks.malachite_pulsing_chisel, cutout);
        renderBlock(ModBlocks.malachite_pulsing_brick_stairs, cutout);
        renderBlock(ModBlocks.malachite_pulsing_floor_stairs, cutout);
        renderBlock(ModBlocks.malachite_pulsing_chisel_stairs, cutout);
        renderBlock(ModBlocks.potted_thiscus, cutout);
        renderBlock(ModBlocks.potted_ouzium, cutout);
        renderBlock(ModBlocks.potted_agathum, cutout);
        renderBlock(ModBlocks.potted_varloom, cutout);
        renderBlock(ModBlocks.potted_corrupted_varloom, cutout);
        renderBlock(ModBlocks.potted_missingno_plant, cutout);
        renderBlock(ModBlocks.potted_spotted_kersei, cutout);
        renderBlock(ModBlocks.potted_thorny_wiltha, cutout);
        renderBlock(ModBlocks.potted_roofed_agaric, cutout);
        renderBlock(ModBlocks.potted_bulbous_hobina, cutout);
        renderBlock(ModBlocks.potted_stickly_cupsir, cutout);
        renderBlock(ModBlocks.potted_mystical_murgni, cutout);
        renderBlock(ModBlocks.potted_corrupted_gaia_eye, cutout);
        renderBlock(ModBlocks.potted_elder_imklia, cutout);
        renderBlock(ModBlocks.potted_gold_orb_tucher, cutout);
        renderBlock(ModBlocks.potted_missingno_fungus, cutout);
        renderBlock(ModBlocks.potted_pink_agate_sapling, cutout);
        renderBlock(ModBlocks.potted_blue_agate_sapling, cutout);
        renderBlock(ModBlocks.potted_green_agate_sapling, cutout);
        renderBlock(ModBlocks.potted_purple_agate_sapling, cutout);
        renderBlock(ModBlocks.potted_fossilized_sapling, cutout);
        renderBlock(ModBlocks.potted_corrupted_sapling, cutout);
        renderBlock(ModBlocks.potted_burnt_sapling, cutout);
        renderBlock(ModBlocks.potted_burning_sapling, cutout);
        renderBlock(ModBlocks.potted_aura_sapling, cutout);
    }

    private static void renderBlock(Supplier<? extends Block> block, RenderType render) {
        RenderTypeLookup.setRenderLayer(block.get(), render);
    }
}
