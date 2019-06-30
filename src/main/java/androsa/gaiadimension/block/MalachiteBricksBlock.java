package androsa.gaiadimension.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class MalachiteBricksBlock extends BasicGaiaBlock {

    private boolean isPulsing;

    public MalachiteBricksBlock(boolean pulsing) {
        super(Material.ROCK, MaterialColor.GREEN, 20.0F, 200.0F, ToolType.PICKAXE, 2);

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
