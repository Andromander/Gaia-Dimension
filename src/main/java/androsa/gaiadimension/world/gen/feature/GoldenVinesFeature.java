package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GoldenVinesFeature extends Feature<NoneFeatureConfiguration> {

   public GoldenVinesFeature(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
      return place(context.level(), context.origin());
   }

   public boolean place(WorldGenLevel level, BlockPos origin) {
      if (level.isEmptyBlock(origin)) {
         for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN && VineBlock.isAcceptableNeighbour(level, origin.relative(direction), direction)) {
               level.setBlock(origin, ModBlocks.golden_vine.get().defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), true), 2);
               return true;
            }
         }

      }
      return false;
   }
}