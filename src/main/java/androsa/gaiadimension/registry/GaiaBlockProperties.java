package androsa.gaiadimension.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

public class GaiaBlockProperties {

    public static AbstractBlock.Properties glassProps(MaterialColor color, float hardness) {
        return AbstractBlock.Properties.of(Material.GLASS, color)
                .strength(hardness, 0.0F)
                .sound(SoundType.GLASS)
                .noOcclusion();
    }

    public static AbstractBlock.Properties sandProps(MaterialColor color, float hardness, SoundType sound) {
        return AbstractBlock.Properties.of(Material.SAND, color)
                .strength(hardness, 0.0F)
                .harvestTool(ToolType.SHOVEL)
                .sound(sound);
    }

    public static AbstractBlock.Properties stoneProps(MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return stoneProps(Material.STONE, color, hardness, resistance, toolClass, harvestLevel);
    }

    public static AbstractBlock.Properties stoneProps(Material material, MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return AbstractBlock.Properties.of(material, color)
                .strength(hardness, resistance)
                .sound(SoundType.STONE)
                .harvestTool(toolClass)
                .harvestLevel(harvestLevel);
    }

    public static AbstractBlock.Properties stoneToolProps(MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return stoneToolProps(Material.STONE, color, hardness, resistance, toolClass, harvestLevel);
    }

    public static AbstractBlock.Properties stoneToolProps(Material material, MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return AbstractBlock.Properties.of(material, color)
                .strength(hardness, resistance)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .harvestTool(toolClass)
                .harvestLevel(harvestLevel);
    }

    public static AbstractBlock.Properties soilProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.DIRT, color)
                .strength(0.9F, 0.0F)
                .sound(SoundType.GRAVEL)
                .harvestTool(ToolType.SHOVEL)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties grassProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.GRASS, color)
                .strength(0.9F, 0.0F)
                .sound(SoundType.GRASS).harvestTool(ToolType.SHOVEL)
                .harvestLevel(0)
                .randomTicks();
    }

    public static AbstractBlock.Properties saplingProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.PLANT, color)
                .strength(0.0F)
                .sound(SoundType.GLASS)
                .noCollission()
                .randomTicks();
    }

    public static AbstractBlock.Properties leavesProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.LEAVES, color)
                .strength(0.3F, 0.0F)
                .sound(SoundType.GLASS)
                .noOcclusion();
    }

    public static AbstractBlock.Properties logProps(MaterialColor color) {
        return logProps(color, color);
    }

    public static AbstractBlock.Properties logProps(MaterialColor top, MaterialColor side) {
        return AbstractBlock.Properties.of(Material.WOOD, (state) ->
                state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .strength(1.5F, 2.0F)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.AXE)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties tileProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.WOOD, color)
                .strength(10.0F, 150.0F)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.AXE)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties storageProps(MaterialColor color) {
        return AbstractBlock.Properties.of(Material.METAL, color)
                .strength(5.0F, 10.0F)
                .sound(SoundType.METAL)
                .requiresCorrectToolForDrops()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2);
    }

    public static AbstractBlock.Properties oreProps(MaterialColor color, int level) {
        return stoneToolProps(color, 4.0F, 25.0F, ToolType.PICKAXE, level);
    }

    public static AbstractBlock.Properties bloomProps() {
        return plantProps(MaterialColor.TERRACOTTA_MAGENTA, false);
    }

    public static AbstractBlock.Properties plantProps(MaterialColor color, boolean isGlass) {
        return AbstractBlock.Properties.of(Material.PLANT, color)
                .strength(0.0F)
                .sound(isGlass ? SoundType.GLASS : SoundType.GRASS)
                .noCollission();
    }

    public static AbstractBlock.Properties sludgeProps() {
        return AbstractBlock.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_YELLOW)
                .strength(0.6F, 0.0F)
                .sound(SoundType.GRAVEL)
                .isValidSpawn((state, reader, pos, entity) -> entity == ModEntities.BISMUTH_ULETRUS);
    }

    public static AbstractBlock.Properties gaiaBrickProps() {
        return stoneToolProps(MaterialColor.COLOR_MAGENTA, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties jadeProps() {
        return stoneToolProps(MaterialColor.COLOR_LIGHT_GREEN, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties copalProps() {
        return stoneToolProps(MaterialColor.TERRACOTTA_ORANGE, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties jetProps() {
        return stoneToolProps(MaterialColor.COLOR_BLACK, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties amethystProps() {
        return stoneToolProps(MaterialColor.COLOR_PURPLE, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties malachiteProps() {
        return stoneToolProps(MaterialColor.COLOR_GREEN, 20.0F, 200.0F, ToolType.PICKAXE, 2);
    }

    public static AbstractBlock.Properties spawnerProps() {
        return AbstractBlock.Properties.of(Material.METAL)
                .strength(-1F)
                .sound(SoundType.METAL)
                .noOcclusion()
                .noDrops();
    }

    public static AbstractBlock.Properties torchProps() {
        return AbstractBlock.Properties.of(Material.DECORATION)
                .strength(0.0F)
                .lightLevel((state) -> 14)
                .noCollission();
    }
}
