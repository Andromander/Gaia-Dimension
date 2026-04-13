package androsa.gaiadimension.client;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.fluid.FluidTintSource;

public class BlockTints {

    public static BlockTintSource grassTinting(int baseColor) {
        return new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return baseColor;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                return BiomeColors.getAverageGrassColor(level, pos);
            }
        };
    }

    public static BlockTintSource auraLeaves() {
        return new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return 0xFFFFFF;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                return BlockTints.getAuraColor(pos);
            }
        };
    }

    public static BlockTintSource auraShoot() {
        return new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return 0x1109B7;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                int location = (Math.abs(pos.getX() % 5)) + (Math.abs(pos.getZ() % 5));
                return switch (location) {
                    case 0 -> 0xEA500D;
                    case 1 -> 0xFFC24C;
                    case 2 -> 0xC1ED26;
                    case 3 -> 0x67FFB9;
                    case 4 -> 0x265AEf;
                    case 5 -> 0x5C0AD7;
                    case 7 -> 0xC330E8;
                    case 8 -> 0xFF6CAE;
                    default -> 0x5D3883;
                };
            }
        };
    }

    public static FluidTintSource liquidBismuth() {
        return new FluidTintSource() {
            @Override
            public int color(FluidState state) {
                return 0xFF000000;
            }

            @Override
            public int colorInWorld(FluidState fluidState, BlockState blockState, BlockAndTintGetter level, BlockPos pos) {
                int red = (int) ((Mth.cos((float) Math.toRadians(pos.getX() * 4)) + 1F) / 2F * 0xFF);
                int green = (int) ((Mth.cos((float) Math.toRadians(pos.getY() * 8)) + 1F) / 3F * 0xFF);
                int blue = (int) ((Mth.cos((float) Math.toRadians(pos.getZ() * 4)) + 1F) / 2F * 0xFF);

                red = Mth.clamp(red, 20, 170);
                green = Mth.clamp(green, 20, 160);
                blue = Mth.clamp(blue, 20, 200);

                return (red << 16) | (green << 8) | blue;
            }
        };
    }

    public static FluidTintSource liquidAura() {
        return new FluidTintSource() {
            @Override
            public int color(FluidState state) {
                return 0xFF000000;
            }

            @Override
            public int colorInWorld(FluidState fluid, BlockState state, BlockAndTintGetter level, BlockPos pos) {
                return BlockTints.getAuraColor(pos);
            }
        };
    }

    public static int getAuraColor(BlockPos pos) {
        int red = (int) ((Mth.cos((float) Math.toRadians((pos.getX() + 100) * 8)) + 1F) / 2F * 0xFF);
        int green = (int) ((Mth.cos((float) Math.toRadians((pos.getY() + 100) * 32)) + 1F) / 2F * 0xFF);
        int blue = (int) ((Mth.cos((float) Math.toRadians((pos.getZ() + 100) * 8)) + 1F) / 2F * 0xFF);

        red = Mth.clamp(red, 150, 256);
        green = Mth.clamp(green, 100, 220);
        blue = Mth.clamp(blue, 150, 256);

        return (red << 16) | (green << 8) | blue;
    }
}
