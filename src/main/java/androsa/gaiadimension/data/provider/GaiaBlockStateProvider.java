package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.AuraShootBlock;
import androsa.gaiadimension.block.CurtainBlock;
import androsa.gaiadimension.registry.registration.ModBlocks;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public abstract class GaiaBlockStateProvider {

    private final BlockModelGenerators blockModels;

    public static final TextureSlot OVERLAY = TextureSlot.create("overlay");
    public static final TextureSlot BOTTOM2 = TextureSlot.create("bottom2");
    public static final TextureSlot TOP2 = TextureSlot.create("top2");
    public static final TextureSlot SIDE2 = TextureSlot.create("side2");
    public static final ModelTemplate CUBE_ALL_LAYERED_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .parent(ResourceLocation.parse("gaiadimension:block/util/cube_all_2_layer"))
            .requiredTextureSlot(TextureSlot.ALL).requiredTextureSlot(OVERLAY)
            .build();
    public static final ModelTemplate LEAVES_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .parent(ModelTemplates.CUBE_ALL.model.get())
            .requiredTextureSlot(TextureSlot.ALL)
            .renderType("cutout")
            .build();
    public static final ModelTemplate TRANSLUCENT_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .parent(ModelTemplates.CUBE_ALL.model.get())
            .requiredTextureSlot(TextureSlot.ALL)
            .renderType("translucent")
            .build();
    public static final ModelTemplate GRASS_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .parent(ResourceLocation.parse("gaiadimension:block/util/grass_block"))
            .requiredTextureSlot(TextureSlot.BOTTOM).requiredTextureSlot(TextureSlot.TOP).requiredTextureSlot(TextureSlot.SIDE).requiredTextureSlot(OVERLAY).requiredTextureSlot(TextureSlot.PARTICLE)
            .renderType("cutout")
            .build();
    public static final ModelTemplate CURTAIN_BOTTOM = curtainTemplate("_bottom");
    public static final ModelTemplate CURTAIN_TOP = curtainTemplate("_top");
    public static final ModelTemplate CURTAIN_BOTTOM_OPEN = curtainTemplate("_bottom_open");
    public static final ModelTemplate CURTAIN_TOP_OPEN = curtainTemplate("_top_open");
    public static final ModelTemplate CURTAIN_BOTTOM_LEFT = curtainTemplate("_bottom_left");
    public static final ModelTemplate CURTAIN_BOTTOM_RIGHT = curtainTemplate("_bottom_right");
    public static final ModelTemplate CURTAIN_TOP_LEFT = curtainTemplate("_top_left");
    public static final ModelTemplate CURTAIN_TOP_RIGHT = curtainTemplate("_top_right");
    public static final ModelTemplate CURTAIN_BOTTOM_LEFT_OPEN = curtainTemplate("_bottom_left_open");
    public static final ModelTemplate CURTAIN_BOTTOM_RIGHT_OPEN = curtainTemplate("_bottom_right_open");
    public static final ModelTemplate CURTAIN_TOP_LEFT_OPEN = curtainTemplate("_top_left_open");
    public static final ModelTemplate CURTAIN_TOP_RIGHT_OPEN = curtainTemplate("_top_right_open");
    public static final ModelTemplate POT_CROSS_CUTOUT_TEMPLATE = ExtendedModelTemplateBuilder.builder()
            .parent(ModelTemplates.FLOWER_POT_CROSS.model.get())
            .requiredTextureSlot(TextureSlot.PLANT)
            .renderType("cutout")
            .build();
    public static final TexturedModel.Provider LEAVES_MODEL = TexturedModel.createDefault(TextureMapping::cube, LEAVES_TEMPLATE);
    public static final TexturedModel.Provider TRANSLUCENT_MODEL = TexturedModel.createDefault(TextureMapping::cube, TRANSLUCENT_TEMPLATE);

    public GaiaBlockStateProvider(BlockModelGenerators blockModels) {
        this.blockModels = blockModels;
    }

    public static ModelTemplate curtainTemplate(String suffix) {
        return ExtendedModelTemplateBuilder.builder()
                .parent(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/util/curtain_surface"))
                .requiredTextureSlot(TextureSlot.TEXTURE)
                .suffix(suffix)
                .renderType("translucent")
                .build();
    }

    public static TextureMapping cubeAllTwoLayer(String bottom, String top) {
        return new TextureMapping()
                .put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + bottom))
                .put(OVERLAY, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + top));
    }

    public static TextureMapping log(DeferredBlock<? extends Block> base) {
        return new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(base.get(), "_side"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(base.get(), "_end"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(base.get(), "_side"));
    }

    public static TextureMapping grass(Block block, DeferredBlock<? extends Block> bottom) {
        return new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(bottom.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side"))
                .put(OVERLAY, TextureMapping.getBlockTexture(block, "_overlay"));
    }

    public static TextureMapping stair(DeferredBlock<Block> base) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(base.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(base.get()))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(base.get()));
    }

    public static TextureMapping pillarStair(DeferredBlock<? extends Block> base) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(base.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(base.get(), "_side"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(base.get(), "_top"));
    }

    public static TextureMapping slab(DeferredBlock<Block> base) {
        return new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(base.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(base.get()))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(base.get()));
    }

    public static TextureMapping curtain(DeferredBlock<? extends Block> block, String suffix) {
        return new TextureMapping()
                .put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block.get(), suffix));
    }

    public void basicBlock(Supplier<? extends Block> block) {
        blockModels.createTrivialCube(block.get());
    }

    public void leavesBlock(DeferredBlock<? extends Block> block) {
        blockModels.createTrivialBlock(block.get(), LEAVES_MODEL);
    }

    public void translucentBlock(DeferredBlock<? extends Block> block) {
        blockModels.createTrivialBlock(block.get(), TRANSLUCENT_MODEL);
    }

    public void pillarBlock(DeferredBlock<? extends Block> block) {
        blockModels.createRotatedPillarWithHorizontalVariant(block.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
    }

    public void simpleColumnBlock(DeferredBlock<Block> block) {
        blockModels.createTrivialBlock(block.get(), TexturedModel.COLUMN_ALT);
    }

    public void topBottomBlock(DeferredBlock<Block> block) {
        blockModels.createTrivialBlock(block.get(), TexturedModel.CUBE_TOP_BOTTOM);
    }

    protected static MultiVariant plain(ResourceLocation location) {
        return BlockModelGenerators.plainVariant(location);
    }

    public void sidedBlock(DeferredBlock<Block> block, String topSuffix, String bottomSuffix, String northSuffix, String eastSuffix, String southSuffix, String westSuffix) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block.get(), topSuffix))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block.get(), bottomSuffix))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block.get(), northSuffix))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block.get(), eastSuffix))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block.get(), southSuffix))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block.get(), westSuffix))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block.get(), northSuffix));
        ResourceLocation model = ModelTemplates.CUBE.create(block.get(), mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), plain(model)));
    }

    public void basicBlockRotated(DeferredBlock<Block> block) {
        basicBlockRotated(block, "solid");
    }

    public void basicBlockRotated(DeferredBlock<Block> block, String type) {
        ResourceLocation model = ModelTemplates.CUBE_ALL.extend().renderType(type).build().create(block.get(), TextureMapping.cube(block.get()), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get(), BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(model))));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(model));
    }

    public void basicBlockLayered(DeferredBlock<Block> block, String suffix, String type) {
        String blockname = block.getId().getPath();
        basicBlockLayered(block, blockname, blockname + suffix, type);
    }

    public void basicBlockLayered(DeferredBlock<Block> block, String bottom, String top, String type) {
        ModelTemplate template = CUBE_ALL_LAYERED_TEMPLATE.extend().renderType(type).build();
        ResourceLocation location = template.create(block.get(), cubeAllTwoLayer(bottom, top), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), plain(location)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(location));
    }

    public void layeredEmissive(DeferredBlock<Block> block, String suffix) {
        String blockname = block.getId().getPath();
        layeredEmissive(block, blockname, blockname + suffix);
    }

    public void layeredEmissive(DeferredBlock<Block> block, String all, String overlay) {
        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/util/emissive_2_layer");
        ModelTemplate template = ExtendedModelTemplateBuilder.builder()
                .parent(parent)
                .requiredTextureSlot(TextureSlot.ALL).requiredTextureSlot(OVERLAY)
                .renderType("cutout")
                .build();
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.ALL, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + all))
                .put(OVERLAY, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + overlay));
        ResourceLocation model = template.create(block.get(), mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), plain(model)));
    }

    public void logBlock(DeferredBlock<? extends Block> block) {
        MultiVariant vertical = plain(ModelTemplates.CUBE_COLUMN.create(block.get(), log(block), blockModels.modelOutput));
        MultiVariant horizontal = plain(ModelTemplates.CUBE_COLUMN_HORIZONTAL.create(block.get(), log(block), blockModels.modelOutput));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createRotatedPillarWithHorizontalVariant(block.get(), vertical, horizontal));
    }

    public void woodBlock(DeferredBlock<RotatedPillarBlock> block, DeferredBlock<RotatedPillarBlock> log) {
        TextureMapping base = log(log);
        TextureMapping mapping = base.copyAndUpdate(TextureSlot.END, base.get(TextureSlot.SIDE));
        ResourceLocation model = ModelTemplates.CUBE_COLUMN.create(block.get(), mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block.get(), plain(model)));
    }

    public void stairsBlock(DeferredBlock<StairBlock> block, DeferredBlock<Block> base) {
        ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(block.get(), stair(base), blockModels.modelOutput);
        ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create(block.get(), stair(base), blockModels.modelOutput);
        ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(block.get(), stair(base), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(), BlockModelGenerators.plainVariant(inner), BlockModelGenerators.plainVariant(straight), BlockModelGenerators.plainVariant(outer)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(straight));
    }

    public void pillarStairsBlock(DeferredBlock<StairBlock> block, DeferredBlock<? extends Block> base) {
        ResourceLocation inner = ModelTemplates.STAIRS_INNER.create(block.get(), pillarStair(base), blockModels.modelOutput);
        ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create(block.get(), pillarStair(base), blockModels.modelOutput);
        ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create(block.get(), pillarStair(base), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(), BlockModelGenerators.plainVariant(inner), BlockModelGenerators.plainVariant(straight), BlockModelGenerators.plainVariant(outer)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(straight));
    }

    public void stairsBlockLayered(DeferredBlock<StairBlock> block, String base, String overlay, String type) {
        ResourceLocation inner_parent = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/util/inner_stairs_2_layer");
        ResourceLocation straight_parent = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/util/stairs_2_layer");
        ResourceLocation outer_parent = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/util/outer_stairs_2_layer");
        ModelTemplate inner_template = ExtendedModelTemplateBuilder.builder()
                .requiredTextureSlot(TextureSlot.SIDE).requiredTextureSlot(TextureSlot.TOP).requiredTextureSlot(TextureSlot.BOTTOM)
                .requiredTextureSlot(SIDE2).requiredTextureSlot(TOP2).requiredTextureSlot(BOTTOM2)
                .parent(inner_parent)
                .suffix("_inner")
                .renderType("cutout")
                .build();
        ModelTemplate straight_template = ExtendedModelTemplateBuilder.builder()
                .requiredTextureSlot(TextureSlot.SIDE).requiredTextureSlot(TextureSlot.TOP).requiredTextureSlot(TextureSlot.BOTTOM)
                .requiredTextureSlot(SIDE2).requiredTextureSlot(TOP2).requiredTextureSlot(BOTTOM2)
                .parent(straight_parent)
                .suffix("_straight")
                .renderType("cutout")
                .build();
        ModelTemplate outer_template = ExtendedModelTemplateBuilder.builder()
                .requiredTextureSlot(TextureSlot.SIDE).requiredTextureSlot(TextureSlot.TOP).requiredTextureSlot(TextureSlot.BOTTOM)
                .requiredTextureSlot(SIDE2).requiredTextureSlot(TOP2).requiredTextureSlot(BOTTOM2)
                .parent(outer_parent)
                .suffix("_outer")
                .renderType("cutout")
                .build();
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.TOP, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + base))
                .put(TextureSlot.BOTTOM, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + base))
                .put(TextureSlot.SIDE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + base))
                .put(TOP2, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + overlay))
                .put(BOTTOM2, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + overlay))
                .put(SIDE2, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "block/" + overlay));
        ResourceLocation inner = inner_template.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation straight = straight_template.create(block.get(), mapping, blockModels.modelOutput);
        ResourceLocation outer = outer_template.create(block.get(), mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(block.get(), BlockModelGenerators.plainVariant(inner), BlockModelGenerators.plainVariant(straight), BlockModelGenerators.plainVariant(outer)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(straight));
    }

    public void slabBlock(DeferredBlock<SlabBlock> block, DeferredBlock<Block> doubleBlock) {
        ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create(block.get(), slab(doubleBlock), blockModels.modelOutput);
        ResourceLocation top = ModelTemplates.SLAB_TOP.create(block.get(), slab(doubleBlock), blockModels.modelOutput);
        ResourceLocation full = ModelLocationUtils.getModelLocation(doubleBlock.get());
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSlab(block.get(), BlockModelGenerators.plainVariant(bottom), BlockModelGenerators.plainVariant(top), BlockModelGenerators.plainVariant(full)));
        blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(bottom));
    }

    public void crossBlock(DeferredBlock<? extends Block> block, String type) {
        ResourceLocation location = ModelTemplates.CROSS.extend().renderType(type).build().create(block.get(), TextureMapping.cross(block.get()), blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), plain(location)));
        if (block.asItem() != Items.AIR) {
            blockModels.registerSimpleItemModel(block.asItem(), blockModels.createFlatItemModelWithBlockTexture(block.asItem(), block.get()));
        }
    }

    public void crossBlockTinted(DeferredBlock<Block> block) {
        ResourceLocation location = ModelTemplates.TINTED_CROSS.extend().renderType("translucent").build().create(block.get(), TextureMapping.cross(block.get()), blockModels.modelOutput);
        ResourceLocation item = blockModels.createFlatItemModelWithBlockTexture(block.asItem(), block.get());
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), plain(location)));
        blockModels.registerSimpleTintedItemModel(block.get(), item, ItemModelUtils.constantTint(0xF2A3B4));
    }

    public void auraShoot() {
        ResourceLocation base = ModelLocationUtils.getModelLocation(ModBlocks.aura_shoot.get());
        ResourceLocation tip = ModelLocationUtils.getModelLocation(ModBlocks.aura_shoot.get(), "_top");
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.aura_shoot.get())
                .with(BlockModelGenerators.createBooleanModelDispatch(AuraShootBlock.IS_TOP, BlockModelGenerators.plainVariant(tip), BlockModelGenerators.plainVariant(base))));
        blockModels.registerSimpleTintedItemModel(ModBlocks.aura_shoot.get(), tip, ItemModelUtils.constantTint(0x1109B7));
    }

    public void orientableBlockLit(DeferredBlock<Block> block) {
        ResourceLocation off = TexturedModel.ORIENTABLE_ONLY_TOP.create(block.get(), blockModels.modelOutput);
        ResourceLocation frontlit = TextureMapping.getBlockTexture(block.get(), "_front_lit");
        ResourceLocation on = TexturedModel.ORIENTABLE_ONLY_TOP.get(block.get())
                .updateTextures(t -> t.put(TextureSlot.FRONT, frontlit))
                .createWithSuffix(block.get(), "_lit", blockModels.modelOutput);
        orientableBlock(block, BlockModelGenerators.plainVariant(on), BlockModelGenerators.plainVariant(off));
    }

    public void orientableBlockBasicLit(DeferredBlock<Block> block) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block.get(), "_side"))
                .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block.get(), "_front"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block.get(), "_side"));
        MultiVariant off = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE.create(block.get(), mapping, blockModels.modelOutput));
        TextureMapping mappinglit = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block.get(), "_side"))
                .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block.get(), "_front_lit"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block.get(), "_side"));
        MultiVariant on = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE.extend().suffix("_lit").build().create(block.get(), mappinglit, blockModels.modelOutput));
        orientableBlock(block, on, off);
    }

    public void orientableBlock(DeferredBlock<Block> block, MultiVariant lit, MultiVariant unlit) {
        this.blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block.get())
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, lit, unlit))
                        .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    public void grassBlock(DeferredBlock<Block> block, DeferredBlock<Block> bottom, int tint) {
        if (tint >= 0) {
            ResourceLocation location = GRASS_TEMPLATE.create(block.get(), grass(block.get(), bottom), blockModels.modelOutput);
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get(), BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(location))));
            blockModels.registerSimpleTintedItemModel(block.get(), location, ItemModelUtils.constantTint(tint));
        } else {
            TextureMapping mapping = new TextureMapping()
                    .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block.get(), "_side"))
                    .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block.get(), "_top"))
                    .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(bottom.get()))
                    .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block.get(), "_side"));
            ResourceLocation model = ModelTemplates.CUBE_BOTTOM_TOP.create(block.get(), mapping, blockModels.modelOutput);
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block.get(), BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(model))));
            blockModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(model));
        }
    }

    public void pottedPlantBlock(DeferredBlock<? extends Block> plant, DeferredBlock<? extends Block> pot) {
        //plant
        TextureMapping plantmap = TextureMapping.cross(plant.get());
        ResourceLocation plantloc = ModelTemplates.CROSS.extend().renderType("cutout").build().create(plant.get(), plantmap, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(plant.get(), BlockModelGenerators.plainVariant(plantloc)));
        //pot
        TextureMapping potmap = TextureMapping.plant(plant.get());
        ResourceLocation potloc = POT_CROSS_CUTOUT_TEMPLATE.create(pot.get(), potmap, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pot.get(), BlockModelGenerators.plainVariant(potloc)));
        //item
        blockModels.registerSimpleItemModel(plant.asItem(), blockModels.createFlatItemModelWithBlockTexture(plant.asItem(), plant.get()));
    }

    public void torchBlock(DeferredBlock<Block> block, DeferredBlock<Block> wall) {
        TextureMapping texturemapping = TextureMapping.torch(block.get());
        ResourceLocation wallmodel = ModelTemplates.WALL_TORCH.extend().renderType("cutout").build().create(wall.get(), texturemapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block.get(), BlockModelGenerators.plainVariant(ModelTemplates.TORCH.extend().renderType("cutout").build().create(block.get(), texturemapping, blockModels.modelOutput))));
        blockModels.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(wall.get(), BlockModelGenerators.plainVariant(wallmodel))
                                .with(BlockModelGenerators.ROTATION_TORCH));

        blockModels.registerSimpleFlatItemModel(block.get());
    }

    public void curtainProps(PropertyDispatch.C4<MultiVariant, Direction.Axis, DoubleBlockHalf, CurtainBlock.CurtainSide, Boolean> base, Direction.Axis axis, DoubleBlockHalf half, CurtainBlock.CurtainSide side, boolean open, MultiVariant model) {
        base.select(axis, half, side, open, model.with(axis == Direction.Axis.Z ? BlockModelGenerators.Y_ROT_90 : VariantMutator.Y_ROT.withValue(Quadrant.R0)));
    }

    public void curtainBlock(DeferredBlock<? extends Block> block) {
        Block curtain = block.get();
        MultiVariant upper = plain(CURTAIN_TOP.create(curtain, curtain(block, "_top"), blockModels.modelOutput));
        MultiVariant lower = plain(CURTAIN_BOTTOM.create(curtain, curtain(block, "_bottom"), blockModels.modelOutput));
        MultiVariant upper_left = plain(CURTAIN_TOP_LEFT.create(curtain, curtain(block, "_top_left"), blockModels.modelOutput));
        MultiVariant lower_left = plain(CURTAIN_BOTTOM_LEFT.create(curtain, curtain(block, "_bottom_left"), blockModels.modelOutput));
        MultiVariant upper_right = plain(CURTAIN_TOP_RIGHT.create(curtain, curtain(block, "_top_right"), blockModels.modelOutput));
        MultiVariant lower_right = plain(CURTAIN_BOTTOM_RIGHT.create(curtain, curtain(block, "_bottom_right"), blockModels.modelOutput));
        MultiVariant upper_open = plain(CURTAIN_TOP_OPEN.create(curtain, curtain(block, "_top_open"), blockModels.modelOutput));
        MultiVariant lower_open = plain(CURTAIN_BOTTOM_OPEN.create(curtain, curtain(block, "_bottom_open"), blockModels.modelOutput));
        MultiVariant upper_left_open = plain(CURTAIN_TOP_LEFT_OPEN.create(curtain, curtain(block, "_top_left_open"), blockModels.modelOutput));
        MultiVariant lower_left_open = plain(CURTAIN_BOTTOM_LEFT_OPEN.create(curtain, curtain(block, "_bottom_left_open"), blockModels.modelOutput));
        MultiVariant upper_right_open = plain(CURTAIN_TOP_RIGHT_OPEN.create(curtain, curtain(block, "_top_right_open"), blockModels.modelOutput));
        MultiVariant lower_right_open = plain(CURTAIN_BOTTOM_RIGHT_OPEN.create(curtain, curtain(block, "_bottom_right_open"), blockModels.modelOutput));

        PropertyDispatch.C4<MultiVariant, Direction.Axis, DoubleBlockHalf, CurtainBlock.CurtainSide, Boolean> dispatch = PropertyDispatch.initial(
                CurtainBlock.FACING,
                CurtainBlock.HALF,
                CurtainBlock.SIDE,
                CurtainBlock.OPEN);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.SINGLE, false, upper);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.SINGLE, false, lower);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.LEFT, false, upper_left);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.LEFT, false, lower_left);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.RIGHT, false, upper_right);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.RIGHT, false, lower_right);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.SINGLE, true, upper_open);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.SINGLE, true, lower_open);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.LEFT, true, upper_left_open);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.LEFT, true, lower_left_open);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.RIGHT, true, upper_right_open);
        curtainProps(dispatch, Direction.Axis.X, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.RIGHT, true, lower_right_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.SINGLE, false, upper);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.SINGLE, false, lower);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.LEFT, false, upper_left);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.LEFT, false, lower_left);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.RIGHT, false, upper_right);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.RIGHT, false, lower_right);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.SINGLE, true, upper_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.SINGLE, true, lower_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.LEFT, true, upper_left_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.LEFT, true, lower_left_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.UPPER, CurtainBlock.CurtainSide.RIGHT, true, upper_right_open);
        curtainProps(dispatch, Direction.Axis.Z, DoubleBlockHalf.LOWER, CurtainBlock.CurtainSide.RIGHT, true, lower_right_open);

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block.get())
                        .with(dispatch));
        blockModels.registerSimpleFlatItemModel(block.asItem());
    }
}
