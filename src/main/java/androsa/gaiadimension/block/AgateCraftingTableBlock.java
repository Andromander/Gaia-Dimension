package androsa.gaiadimension.block;

import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class AgateCraftingTableBlock extends BasicGaiaBlock {
    private static final ITextComponent NAME = new TranslationTextComponent("gaiadimension.container.crafting");

    public AgateCraftingTableBlock() {
        super(Material.WOOD, MaterialColor.PINK_TERRACOTTA, 1.5F, 2.0F, ToolType.AXE, 0);
    }

    @Override
    @Deprecated
    public ActionResultType onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        player.openContainer(state.getContainer(world, pos));
        return ActionResultType.SUCCESS;
    }

    @Override
    @Deprecated
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, worldPos) -> new AgateCraftingTableContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)), NAME);
    }
}
