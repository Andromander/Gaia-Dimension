package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class MalachiteStairsBlock extends StairsBlock {

    public MalachiteStairsBlock(Supplier<? extends Block> state) {
        super(() -> state.get().getDefaultState(), Properties.create(Material.ROCK, MaterialColor.GREEN).hardnessAndResistance(20.0F, 100.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2));
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
