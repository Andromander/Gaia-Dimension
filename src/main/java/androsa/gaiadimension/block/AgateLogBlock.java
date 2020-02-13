package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class AgateLogBlock extends LogBlock {

    private final Supplier<Block> strippedBlock;

    public AgateLogBlock(Supplier<Block> stripped, MaterialColor topColor, MaterialColor baseColor) {
        this(stripped, topColor, baseColor, 0);
    }

    public AgateLogBlock(Supplier<Block> stripped, MaterialColor topColor, MaterialColor baseColor, int light) {
        super(topColor, Properties.create(Material.WOOD, baseColor).hardnessAndResistance(1.5F, 2.0F).sound(SoundType.STONE).harvestTool(ToolType.AXE).harvestLevel(0).lightValue(light));
        strippedBlock = stripped;
    }

    public AgateLogBlock(MaterialColor topColor, MaterialColor baseColor) {
        this(topColor, baseColor, 0);
    }

    public AgateLogBlock(MaterialColor topColor, MaterialColor baseColor, int light) {
        super(topColor, Properties.create(Material.WOOD, baseColor).hardnessAndResistance(1.5F, 2.0F).sound(SoundType.STONE).harvestTool(ToolType.AXE).harvestLevel(0).lightValue(light));
        strippedBlock = null;
    }

    @Override
    @Deprecated
    public ActionResultType onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (strippedBlock != null) {
            if (player.getHeldItem(hand).getItem() instanceof AxeItem) {
                if (!world.isRemote) {
                    world.setBlockState(pos, strippedBlock.get().getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)), 11);
                    if (player != null) {
                        player.getHeldItem(hand).damageItem(1, player, (consumer) -> consumer.sendBreakAnimation(hand));
                    }
                }
                world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.PASS;
    }
}
