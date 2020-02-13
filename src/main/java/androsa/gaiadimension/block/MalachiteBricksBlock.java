package androsa.gaiadimension.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class MalachiteBricksBlock extends BasicGaiaBlock {

    public MalachiteBricksBlock() {
        super(Material.ROCK, MaterialColor.GREEN, 20.0F, 200.0F, ToolType.PICKAXE, 2);
    }

    //TODO: RenderTypeLookup
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public BlockRenderLayer getRenderLayer() {
//        if (isPulsing) {
//            return BlockRenderLayer.CUTOUT;
//        } else {
//            return BlockRenderLayer.SOLID;
//        }
//    }
}
