package androsa.gaiadimension.block;

import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AgateCraftingTableBlock extends Block {
    private static final Component NAME = new TranslatableComponent("gaiadimension.container.crafting");

    public AgateCraftingTableBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        player.openMenu(state.getMenuProvider(world, pos));
        return InteractionResult.SUCCESS;
    }

    @Override
    @Deprecated
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, worldPos) -> new AgateCraftingTableMenu(id, inventory, ContainerLevelAccess.create(worldIn, pos)), NAME);
    }
}
