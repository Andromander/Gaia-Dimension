package androsa.gaiadimension.block;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class AgateLeavesBlock extends LeavesBlock {

    public AgateLeavesBlock(MaterialColor color) {
        this(color, 0);
    }

    public AgateLeavesBlock(MaterialColor color, int light) {
        super(Properties.create(Material.LEAVES, color).hardnessAndResistance(0.3F, 0.0F).sound(SoundType.GLASS).lightValue(light));
    }

    /* //TODO: Get this to work with Agate Logs
    private static int getDistance(BlockState neighbor) {
      if (BlockTags.LOGS.contains(neighbor.getBlock())) {
         return 0;
      } else {
         return neighbor.getBlock() instanceof LeavesBlock ? neighbor.get(DISTANCE) : 7;
      }
   }
     */
}
