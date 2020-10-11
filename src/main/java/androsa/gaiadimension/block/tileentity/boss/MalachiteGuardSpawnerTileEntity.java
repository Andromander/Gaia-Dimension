package androsa.gaiadimension.block.tileentity.boss;

import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.ModTileEntities;

public class MalachiteGuardSpawnerTileEntity extends AbstractSpawnerTileEntity<MalachiteGuardEntity> {

    public MalachiteGuardSpawnerTileEntity() {
        super(ModTileEntities.MALACHITE_SPAWNER, ModEntities.MALACHITE_GUARD);
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
