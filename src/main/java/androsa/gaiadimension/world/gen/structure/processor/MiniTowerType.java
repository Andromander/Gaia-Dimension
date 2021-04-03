package androsa.gaiadimension.world.gen.structure.processor;

import androsa.gaiadimension.registry.GaiaChestTables;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public enum MiniTowerType implements IStringSerializable {
    AMETHYST("AMETHYST", GaiaChestTables.CHESTS_MINITOWER_AMETHYST,
            ModBlocks.amethyst_bricks, ImmutableList.of(ModBlocks.cracked_amethyst_bricks, ModBlocks.crusted_amethyst_bricks),
            ModBlocks.amethyst_brick_stairs, ImmutableList.of(ModBlocks.cracked_amethyst_brick_stairs, ModBlocks.crusted_amethyst_brick_stairs),
            ModBlocks.amethyst_brick_slab, ImmutableList.of(ModBlocks.cracked_amethyst_brick_slab, ModBlocks.crusted_amethyst_brick_slab)
    ),
    COPAL("COPAL", GaiaChestTables.CHESTS_MINITOWER_COPAL,
            ModBlocks.copal_bricks, ImmutableList.of(ModBlocks.cracked_copal_bricks, ModBlocks.crusted_copal_bricks),
            ModBlocks.copal_brick_stairs, ImmutableList.of(ModBlocks.cracked_copal_brick_stairs, ModBlocks.crusted_copal_brick_stairs),
            ModBlocks.copal_brick_slab, ImmutableList.of(ModBlocks.cracked_copal_brick_slab, ModBlocks.crusted_copal_brick_slab)
    ),
    JADE("JADE", GaiaChestTables.CHESTS_MINITOWER_JADE,
            ModBlocks.jade_bricks, ImmutableList.of(ModBlocks.cracked_jade_bricks, ModBlocks.crusted_jade_bricks),
            ModBlocks.jade_brick_stairs, ImmutableList.of(ModBlocks.cracked_jade_brick_stairs, ModBlocks.crusted_jade_brick_stairs),
            ModBlocks.jade_brick_slab, ImmutableList.of(ModBlocks.cracked_jade_brick_slab, ModBlocks.crusted_jade_brick_slab)
    ),
    JET("JET", GaiaChestTables.CHESTS_MINITOWER_JET,
            ModBlocks.jet_bricks, ImmutableList.of(ModBlocks.cracked_jet_bricks, ModBlocks.crusted_jet_bricks),
            ModBlocks.jet_brick_stairs, ImmutableList.of(ModBlocks.cracked_jet_brick_stairs, ModBlocks.crusted_jet_brick_stairs),
            ModBlocks.jet_brick_slab, ImmutableList.of(ModBlocks.cracked_jet_brick_slab, ModBlocks.crusted_jet_brick_slab)
    );

    public static final Codec<MiniTowerType> CODEC = IStringSerializable.fromEnum(MiniTowerType::values, MiniTowerType::getType);

    private final String name;
    private final ResourceLocation chestLoot;
    private final Supplier<Block> brickBlock;
    private final ImmutableList<Supplier<Block>> brickDegrades;
    private final Supplier<StairsBlock> stairsBlock;
    private final ImmutableList<Supplier<StairsBlock>> stairsDegrades;
    private final Supplier<SlabBlock> slabBlock;
    private final ImmutableList<Supplier<SlabBlock>> slabDegrades;

    private static final Map<String, MiniTowerType> typeid = Util.make(Maps.newHashMap(), (hashmap) -> {
        for(MiniTowerType type : values()) {
            hashmap.put(type.name, type);
        }
    });

    MiniTowerType(String name, ResourceLocation loot, Supplier<Block> brick, ImmutableList<Supplier<Block>> breakbricks, Supplier<StairsBlock> stairs, ImmutableList<Supplier<StairsBlock>> breakstairs, Supplier<SlabBlock> slab, ImmutableList<Supplier<SlabBlock>> breakslab) {
        this.name = name;
        chestLoot = loot;
        brickBlock = brick;
        brickDegrades = breakbricks;
        stairsBlock = stairs;
        stairsDegrades = breakstairs;
        slabBlock = slab;
        slabDegrades = breakslab;
    }

    public static MiniTowerType getType(String name) {
        return typeid.get(name);
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public ResourceLocation getChestLoot() {
        return chestLoot;
    }

    public BlockState getBrick() {
        return brickBlock.get().defaultBlockState();
    }

    public BlockState getBrickDecay(Random rand) {
        return brickDegrades.get(rand.nextInt(brickDegrades.size())).get().defaultBlockState();
    }

    public BlockState getStairs() {
        return stairsBlock.get().defaultBlockState();
    }

    public BlockState getStairsDecay(Random rand) {
        return stairsDegrades.get(rand.nextInt(stairsDegrades.size())).get().defaultBlockState();
    }

    public BlockState getSlab() {
        return slabBlock.get().defaultBlockState();
    }

    public BlockState getSlabDecay(Random rand) {
        return slabDegrades.get(rand.nextInt(slabDegrades.size())).get().defaultBlockState();
    }
}
