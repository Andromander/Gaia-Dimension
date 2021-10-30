package androsa.gaiadimension.block.blockentity.boss;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.registry.ModBlockEntities;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MalachiteGuardSpawnerBlockEntity extends AbstractSpawnerBlockEntity<MalachiteGuardEntity> {

    public MalachiteGuardSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MALACHITE_SPAWNER.get(), pos, state, ModEntities.MALACHITE_GUARD.get());
    }

    @Override
    public double getSpawnerRange() {
        return 9;
    }

    @Override
    public int getColor() {
        return 0x008800;
    }

    @Override
    public int getHomeDistance() {
        return 16;
    }
}
