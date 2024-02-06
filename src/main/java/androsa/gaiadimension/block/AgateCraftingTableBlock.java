package androsa.gaiadimension.block;

import androsa.gaiadimension.block.menu.AgateCraftingTableMenu;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
    public static final MapCodec<? extends AgateCraftingTableBlock> CODEC = simpleCodec(AgateCraftingTableBlock::new);
    private static final Component NAME = Component.translatable("gaiadimension.container.crafting");

    public AgateCraftingTableBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
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
