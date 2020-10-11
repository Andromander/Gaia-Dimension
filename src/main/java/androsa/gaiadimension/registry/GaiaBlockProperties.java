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
        return AbstractBlock.Properties.create(Material.GLASS, color)
                .hardnessAndResistance(hardness, 0.0F)
                .sound(SoundType.GLASS)
                .notSolid();
    }

    public static AbstractBlock.Properties sandProps(MaterialColor color, float hardness, SoundType sound) {
        return AbstractBlock.Properties.create(Material.SAND, color)
                .hardnessAndResistance(hardness, 0.0F)
                .harvestTool(ToolType.SHOVEL)
                .sound(sound);
    }

    public static AbstractBlock.Properties stoneProps(MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return stoneProps(Material.ROCK, color, hardness, resistance, toolClass, harvestLevel);
    }

    public static AbstractBlock.Properties stoneProps(Material material, MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return AbstractBlock.Properties.create(material, color)
                .hardnessAndResistance(hardness, resistance)
                .sound(SoundType.STONE)
                .harvestTool(toolClass)
                .harvestLevel(harvestLevel);
    }

    public static AbstractBlock.Properties stoneToolProps(MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return stoneToolProps(Material.ROCK, color, hardness, resistance, toolClass, harvestLevel);
    }

    public static AbstractBlock.Properties stoneToolProps(Material material, MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        return AbstractBlock.Properties.create(material, color)
                .hardnessAndResistance(hardness, resistance)
                .sound(SoundType.STONE)
                .setRequiresTool()
                .harvestTool(toolClass)
                .harvestLevel(harvestLevel);
    }

    public static AbstractBlock.Properties soilProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.EARTH, color)
                .hardnessAndResistance(0.9F, 0.0F)
                .sound(SoundType.GROUND)
                .harvestTool(ToolType.SHOVEL)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties grassProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.ORGANIC, color)
                .hardnessAndResistance(0.9F, 0.0F)
                .sound(SoundType.PLANT).harvestTool(ToolType.SHOVEL)
                .harvestLevel(0)
                .tickRandomly();
    }

    public static AbstractBlock.Properties saplingProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.PLANTS, color)
                .hardnessAndResistance(0.0F)
                .sound(SoundType.GLASS)
                .doesNotBlockMovement()
                .tickRandomly();
    }

    public static AbstractBlock.Properties leavesProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.LEAVES, color)
                .hardnessAndResistance(0.3F, 0.0F)
                .sound(SoundType.GLASS)
                .notSolid();
    }

    public static AbstractBlock.Properties logProps(MaterialColor color) {
        return logProps(color, color);
    }

    public static AbstractBlock.Properties logProps(MaterialColor top, MaterialColor side) {
        return AbstractBlock.Properties.create(Material.WOOD, (state) ->
                state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .hardnessAndResistance(1.5F, 2.0F)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.AXE)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties tileProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.WOOD, color)
                .hardnessAndResistance(10.0F, 150.0F)
                .sound(SoundType.STONE)
                .harvestTool(ToolType.AXE)
                .harvestLevel(0);
    }

    public static AbstractBlock.Properties storageProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.IRON, color)
                .hardnessAndResistance(5.0F, 10.0F)
                .sound(SoundType.METAL)
                .setRequiresTool()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2);
    }

    public static AbstractBlock.Properties oreProps(MaterialColor color, int level) {
        return stoneToolProps(color, 4.0F, 25.0F, ToolType.PICKAXE, level);
    }

    public static AbstractBlock.Properties bloomProps() {
        return plantProps(MaterialColor.MAGENTA_TERRACOTTA);
    }

    public static AbstractBlock.Properties plantProps(MaterialColor color) {
        return AbstractBlock.Properties.create(Material.PLANTS, color)
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT)
                .doesNotBlockMovement();
    }

    public static AbstractBlock.Properties sludgeProps() {
        return AbstractBlock.Properties.create(Material.EARTH, MaterialColor.YELLOW_TERRACOTTA)
                .hardnessAndResistance(0.6F, 0.0F)
                .sound(SoundType.GROUND)
                .setAllowsSpawn((state, reader, pos, entity) -> entity == ModEntities.BISMUTH_ULETRUS);
    }

    public static AbstractBlock.Properties gaiaBrickProps() {
        return stoneToolProps(MaterialColor.MAGENTA, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties jadeProps() {
        return stoneToolProps(MaterialColor.LIME, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties copalProps() {
        return stoneToolProps(MaterialColor.ORANGE_TERRACOTTA, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties jetProps() {
        return stoneToolProps(MaterialColor.BLACK, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties amethystProps() {
        return stoneToolProps(MaterialColor.PURPLE, 2.0F, 20.0F, ToolType.PICKAXE, 1);
    }

    public static AbstractBlock.Properties malachiteProps() {
        return stoneToolProps(MaterialColor.GREEN, 20.0F, 200.0F, ToolType.PICKAXE, 2);
    }

    public static AbstractBlock.Properties spawnerProps() {
        return AbstractBlock.Properties.create(Material.IRON)
                .hardnessAndResistance(-1F)
                .sound(SoundType.METAL)
                .notSolid()
                .noDrops();
    }

    public static AbstractBlock.Properties torchProps() {
        return AbstractBlock.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(0.0F)
                .setLightLevel((state) -> 14)
                .doesNotBlockMovement();
    }
}
