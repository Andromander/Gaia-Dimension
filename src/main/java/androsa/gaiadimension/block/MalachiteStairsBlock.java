package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class MalachiteStairsBlock extends StairsBlock {

    private boolean isPulsing;

    public MalachiteStairsBlock(BlockState state, boolean pulsing) {
        super(state, Properties.create(Material.ROCK, MaterialColor.GREEN).hardnessAndResistance(20.0F, 100.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2));

        isPulsing = pulsing;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        if (isPulsing) {
            return BlockRenderLayer.CUTOUT;
        } else {
            return BlockRenderLayer.SOLID;
        }
    }
}
