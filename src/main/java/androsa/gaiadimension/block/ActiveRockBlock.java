package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ActiveRockBlock extends Block {

    public static final MapCodec<? extends ActiveRockBlock> CODEC = simpleCodec(ActiveRockBlock::new);

    public ActiveRockBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        if (entityIn instanceof LivingEntity) {
            entityIn.hurt(GaiaDamage.getDamage(worldIn, GaiaDamage.ENERGETIC), 2.0F);
        }

        super.stepOn(worldIn, pos, state, entityIn);
    }
}
