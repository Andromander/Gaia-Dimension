package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.boss.AbstractSpawnerBlockEntity;
import androsa.gaiadimension.registry.helpers.PropertiesHandler;
import androsa.gaiadimension.registry.registration.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BossSpawnerBlock extends Block implements EntityBlock {

    public static final MapCodec<? extends BossSpawnerBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(BossType.CODEC.fieldOf("boss").forGetter(obj -> obj.bossType),
                            propertiesCodec())
                    .apply(instance, BossSpawnerBlock::new));
    private static final VoxelShape SHAPE = Block.box(2.0D, 1.0D, 2.0D, 14.0D, 15.0D, 14.0D);
    private final BossType bossType;

    public BossSpawnerBlock(BossType type, Properties props) {
        super(props);
        this.bossType = type;
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return bossType.getBlockEntity().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entity) {
        return level.isClientSide() ? PropertiesHandler.getTicker((BlockEntityTicker<AbstractSpawnerBlockEntity<?>>) AbstractSpawnerBlockEntity::particleTick) : PropertiesHandler.getTicker((BlockEntityTicker<AbstractSpawnerBlockEntity<?>>) AbstractSpawnerBlockEntity::spawnTick);
    }

    @Override
    @Deprecated
    public boolean triggerEvent(BlockState state, Level worldIn, BlockPos pos, int id, int param) {
        super.triggerEvent(state, worldIn, pos, id, param);
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity != null && tileentity.triggerEvent(id, param);
    }

    @Override
    @Deprecated
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity instanceof MenuProvider ? (MenuProvider)tileentity : null;
    }

    @Override
    @Deprecated
    public ItemStack getCloneItemStack(LevelReader reader, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    public enum BossType implements StringRepresentable {
        MALACHITE("malachite", ModBlockEntities.MALACHITE_SPAWNER::get);

        public static final StringRepresentable.EnumCodec<BossType> CODEC = StringRepresentable.fromEnum(BossType::values);
        private String name;
        private Supplier<BlockEntityType<? extends AbstractSpawnerBlockEntity<?>>> tileEntity;

        BossType(String name, Supplier<BlockEntityType<? extends AbstractSpawnerBlockEntity<?>>> tileentity) {
            this.name = name;
            this.tileEntity = tileentity;
        }

        public BlockEntityType<? extends AbstractSpawnerBlockEntity<?>> getBlockEntity() {
            return tileEntity.get();
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
