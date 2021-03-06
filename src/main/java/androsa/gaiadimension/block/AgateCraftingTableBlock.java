package androsa.gaiadimension.block;

import androsa.gaiadimension.block.container.AgateCraftingTableContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

public class AgateCraftingTableBlock extends Block {
    private static final ITextComponent NAME = new TranslationTextComponent("gaiadimension.container.crafting");

    public AgateCraftingTableBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        player.openMenu(state.getMenuProvider(world, pos));
        return ActionResultType.SUCCESS;
    }

    @Override
    @Deprecated
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, worldPos) -> new AgateCraftingTableContainer(id, inventory, IWorldPosCallable.create(worldIn, pos)), NAME);
    }
}
