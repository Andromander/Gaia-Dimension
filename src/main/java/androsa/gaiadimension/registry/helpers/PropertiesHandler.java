package androsa.gaiadimension.registry.helpers;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

public class PropertiesHandler {

    public static BlockBehaviour.Properties glassProps(MapColor color, float hardness) {
        return basicProps(color, SoundType.GLASS, hardness, 0.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties sandProps(MapColor color, float hardness, SoundType sound) {
        return basicProps(color, sound, hardness, 0.0F);
    }

    public static BlockBehaviour.Properties stoneProps(MapColor color, float hardness, float resistance) {
        return stoneProps(color, hardness, resistance, false);
    }

    public static BlockBehaviour.Properties stoneProps(MapColor color, float hardness, float resistance, boolean tool) {
        BlockBehaviour.Properties props = basicProps(color, SoundType.STONE, hardness, resistance);
        if (tool) props.requiresCorrectToolForDrops();
        return props;
    }

    public static BlockBehaviour.Properties soilProps(MapColor color) {
        return basicProps(color, SoundType.GRAVEL, 0.9F, 0.0F);
    }

    public static BlockBehaviour.Properties grassProps(MapColor color) {
        return basicProps(color, SoundType.GRASS, 0.9F, 0.0F).randomTicks();
    }

    public static BlockBehaviour.Properties saplingProps(MapColor color) {
        return basicProps(color, SoundType.GLASS, 0.0F).noCollission().randomTicks();
    }

    public static BlockBehaviour.Properties leavesProps(MapColor color) {
        return basicProps(color, SoundType.GLASS, 0.3F, 0.0F).noOcclusion();
    }

    public static BlockBehaviour.Properties logProps(MapColor color) {
        return logProps(color, color);
    }

    public static BlockBehaviour.Properties logProps(MapColor top, MapColor side) {
        return BlockBehaviour.Properties.of()
                .mapColor((state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side)
                .strength(1.5F, 2.0F)
                .sound(SoundType.STONE);
    }

    public static BlockBehaviour.Properties tileProps(MapColor color) {
        return stoneProps(color, 10.0F, 150.0F);
    }

    public static BlockBehaviour.Properties storageProps(MapColor color) {
        return basicProps(color, SoundType.METAL, 5.0F, 10.0F).requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties oreProps(MapColor color) {
        return stoneProps(color, 4.0F, 25.0F, true);
    }

    public static BlockBehaviour.Properties bloomProps() {
        return plantProps(MapColor.TERRACOTTA_MAGENTA, false);
    }

    public static BlockBehaviour.Properties plantProps(MapColor color, boolean isGlass) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(0.0F)
                .sound(isGlass ? SoundType.GLASS : SoundType.GRASS)
                .noCollission();
    }

    public static BlockBehaviour.Properties muckyProps(MapColor color, float speed, float jump) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(0.6F, 0.0F)
                .sound(SoundType.MUD)
                .speedFactor(speed)
                .jumpFactor(jump)
                .isRedstoneConductor((state, getter, pos) -> true)
                .isViewBlocking((state, getter, pos) -> true)
                .isSuffocating((state, getter, pos) -> true);
    }

    public static BlockBehaviour.Properties gaiaBrickProps() {
        return stoneProps(MapColor.COLOR_MAGENTA, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties jadeProps() {
        return stoneProps(MapColor.COLOR_LIGHT_GREEN, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties copalProps() {
        return stoneProps(MapColor.TERRACOTTA_ORANGE, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties jetProps() {
        return stoneProps(MapColor.COLOR_BLACK, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties amethystProps() {
        return stoneProps(MapColor.COLOR_PURPLE, 2.0F, 20.0F, true);
    }

    public static BlockBehaviour.Properties malachiteProps() {
        return stoneProps(MapColor.COLOR_GREEN, 20.0F, 200.0F, true);
    }

    public static BlockBehaviour.Properties spawnerProps() {
        return BlockBehaviour.Properties.of()
                .strength(-1F)
                .sound(SoundType.METAL)
                .noOcclusion();
    }

    public static BlockBehaviour.Properties torchProps() {
        return BlockBehaviour.Properties.of()
                .strength(0.0F)
                .lightLevel((state) -> 14)
                .noCollission();
    }

    public static BlockBehaviour.Properties liquidProps(MapColor color) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .replaceable()
                .noCollission()
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .sound(SoundType.EMPTY)
                .liquid();
    }

    public static BlockBehaviour.Properties basicProps(MapColor color, SoundType sound, float strength) {
        return basicProps(color, sound, strength, strength);
    }

    public static BlockBehaviour.Properties basicProps(MapColor color, SoundType sound, float hardness, float resistance) {
        return BlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(hardness, resistance)
                .sound(sound);
    }

    @Nullable
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> getTicker(BlockEntityTicker<? super E> ticker) {
        return (BlockEntityTicker<A>)ticker;
    }
}
