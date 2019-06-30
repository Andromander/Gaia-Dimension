package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.Tree;


public class AgateSaplingBlock extends SaplingBlock {

    public AgateSaplingBlock(MaterialColor color, Tree tree) {
        super(tree, Properties.create(Material.PLANTS, color).hardnessAndResistance(0.0F).sound(SoundType.GLASS).doesNotBlockMovement().tickRandomly());
    }
}