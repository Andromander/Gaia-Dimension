package androsa.gaiadimension.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import javax.annotation.Nullable;

public class GaiaBlockProperties {

    public static BlockBehaviour.Properties glassProps(MaterialColor color, float hardness) {
        return basicProps(Material.GLASS, color, SoundType.GLASS, hardness, 0.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties sandProps(MaterialColor color, float hardness, SoundType sound) {
        return basicProps(Material.SAND, color, sound, hardness, 0.0F);
    }

    public static BlockBehaviour.Properties stoneProps(MaterialColor color, float hardness, float resistance) {
        return stoneProps(Material.STONE, color, hardness, resistance, false);
    }

    public static BlockBehaviour.Properties stoneProps(MaterialColor color, float hardness, float resistance, boolean tool) {
        return stoneProps(Material.STONE, color, hardness, resistance, tool);
    }

    public static BlockBehaviour.Properties stoneProps(Material material, MaterialColor color, float hardness, float resistance, boolean tool) {
        BlockBehaviour.Properties props = basicProps(material, color, SoundType.STONE, hardness, resistance);
        if (tool)
            props.requiresCorrectToolForDrops();
        return props;
    }

    public static BlockBehaviour.Properties soilProps(MaterialColor color) {
        return basicProps(Material.DIRT, color, SoundType.GRAVEL, 0.9F, 0.0F);
    }

    public static BlockBehaviour.Properties grassProps(MaterialColor color) {
        return basicProps(Material.GRASS, color, SoundType.GRASS, 0.9F, 0.0F).randomTicks();
    }

    public static BlockBehaviour.Properties saplingProps(MaterialColor color) {
        return basicProps(Material.PLANT, color, SoundType.GLASS, 0.0F).noCollission().randomTicks();
    }

    public static BlockBehaviour.Properties leavesProps(MaterialColor color) {
        return basicProps(Material.LEAVES, color, SoundType.GLASS, 0.3F, 0.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties logProps(MaterialColor color) {
        return logProps(color, color);
    }

    public static BlockBehaviour.Properties logProps(MaterialColor top, MaterialColor side) {
        return BlockBehaviour.Properties.of(Material.WOOD, (state) ->
                state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .strength(1.5F, 2.0F)
                .sound(SoundType.STONE);
    }

    public static BlockBehaviour.Properties tileProps(MaterialColor color) {
        return stoneProps(Material.WOOD, color, 10.0F, 150.0F, false);
    }

    public static BlockBehaviour.Properties storageProps(MaterialColor color) {
        return basicProps(Material.METAL, color, SoundType.METAL, 5.0F, 10.0F).requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties oreProps(MaterialColor color) {
        return stoneProps(color, 4.0F, 25.0F, true);
    }

    public static BlockBehaviour.Properties bloomProps() {
        return plantProps(MaterialColor.TERRACOTTA_MAGENTA, false);
    }

    public static BlockBehaviour.Properties plantProps(MaterialColor color, boolean isGlass) {
        return BlockBehaviour.Properties.of(Material.PLANT, color)
                .strength(0.0F)
                .sound(isGlass ? SoundType.GLASS : SoundType.GRASS)
                .noCollission();
    }

    public static BlockBehaviour.Properties sludgeProps() {
        return BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_YELLOW)
                .strength(0.6F, 0.0F)
                .sound(SoundType.GRAVEL)
                .isValidSpawn((state, reader, pos, entity) -> entity == ModEntities.BISMUTH_ULETRUS.get());
    }

    public static BlockBehaviour.Properties gaiaBrickProps() {
        return stoneProps(MaterialColor.COLOR_MAGENTA, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties jadeProps() {
        return stoneProps(MaterialColor.COLOR_LIGHT_GREEN, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties copalProps() {
        return stoneProps(MaterialColor.TERRACOTTA_ORANGE, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties jetProps() {
        return stoneProps(MaterialColor.COLOR_BLACK, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties amethystProps() {
        return stoneProps(MaterialColor.COLOR_PURPLE, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties malachiteProps() {
        return stoneProps(MaterialColor.COLOR_GREEN, 20.0F, 200.0F, true);
    }

    public static BlockBehaviour.Properties spawnerProps() {
        return BlockBehaviour.Properties.of(Material.METAL)
                .strength(-1F)
                .sound(SoundType.METAL)
                .noOcclusion()
                .noDrops();
    }

    public static BlockBehaviour.Properties torchProps() {
        return BlockBehaviour.Properties.of(Material.DECORATION)
                .strength(0.0F)
                .lightLevel((state) -> 14)
                .noCollission();
    }

    public static BlockBehaviour.Properties basicProps(Material material, MaterialColor color, SoundType sound, float strength) {
        return basicProps(material, color, sound, strength, strength);
    }

    public static BlockBehaviour.Properties basicProps(Material material, MaterialColor color, SoundType sound, float hardness, float resistance) {
        return BlockBehaviour.Properties.of(material, color)
                .strength(hardness, resistance)
                .sound(sound);
    }

    @Nullable
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> getTicker(BlockEntityTicker<? super E> ticker) {
        return (BlockEntityTicker<A>)ticker;
    }
}
