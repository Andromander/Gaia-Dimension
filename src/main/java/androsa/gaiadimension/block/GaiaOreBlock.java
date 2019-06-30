package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class GaiaOreBlock extends BasicGaiaBlock {

    private final int minDrop;
    private final int maxDrop;

    public GaiaOreBlock(MaterialColor color, int harvestLevel) {
        this(color, harvestLevel, 0, 0);
    }

    public GaiaOreBlock(MaterialColor color, int harvestLevel, int minExp, int maxExp) {
        this(color, harvestLevel, minExp, maxExp, 0);
    }

    public GaiaOreBlock(MaterialColor color, int harvestLevel, int minExp, int maxExp, int light) {
        super(Material.ROCK, color, 4.0F, 25.0F, SoundType.STONE, ToolType.PICKAXE, harvestLevel, light);

        minDrop = minExp;
        maxDrop = maxExp;
    }

    @Override
    @Deprecated
    public void spawnAdditionalDrops(BlockState state, World worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 0) {
            if (minDrop != 0 && maxDrop != 0) {
                return MathHelper.nextInt(RANDOM, minDrop, maxDrop);
            }
        }
        return 0;
    }
}
