package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.GeyserBlockEntity;
import androsa.gaiadimension.registry.helpers.PropertiesHandler;
import androsa.gaiadimension.registry.registration.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class GeyserBlock extends Block implements EntityBlock {

    public GeyserBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GeyserBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entity) {
        if (level.isClientSide()) {
            return ModBlockEntities.GEYSER.get() == entity ? PropertiesHandler.getTicker(GeyserBlockEntity::tick) : null;
        }
        return null;
    }
}
