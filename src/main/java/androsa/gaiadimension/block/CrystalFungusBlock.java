package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.values.GaiaTags;
import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import androsa.gaiadimension.world.gen.feature.fungi.HugeCrystalFungiFeature;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class CrystalFungusBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<? extends CrystalFungusBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                            Codec.BOOL.fieldOf("cavernous").forGetter(obj -> obj.cavernous),
                            ResourceKey.codec(Registries.CONFIGURED_FEATURE).optionalFieldOf("feature").forGetter(p -> p.hugeFungus),
                            propertiesCodec())
                    .apply(instance, CrystalFungusBlock::new));
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    private final boolean cavernous;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> hugeFungus;

    public CrystalFungusBlock(boolean isCave, Properties props) {
        this(isCave, Optional.empty(), props);
    }

    public CrystalFungusBlock(boolean isCave, Optional<ResourceKey<ConfiguredFeature<?, ?>> > feature, Properties props) {
        super(props);

        cavernous = isCave;
        hugeFungus = feature;
    }

    @Override
    public MapCodec<BushBlock> codec() {
        return (MapCodec<BushBlock>)(MapCodec<?>) CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos){
        if (cavernous) {
            return state.getBlock() == ModBlocks.gaia_stone.get() ||
                    state.getBlock() == ModBlocks.primal_mass.get() ||
                    state.getBlock() == ModBlocks.wasteland_stone.get() ||
                    state.getBlock() == ModBlocks.volcanic_rock.get();
        } else {
            return state.is(GaiaTags.Blocks.CRYSTAL_FUNGI_SURFACE_PLACEABLE);
        }
    }

    //TODO: Grow into giant Fungus?

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;

        worldIn.addParticle(ParticleTypes.MYCELIUM, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        if (level instanceof ServerLevel server) {
            if (this.hugeFungus.isPresent()) {
                Optional<? extends Holder<ConfiguredFeature<?, ?>>> featureHolder = server.registryAccess()
                        .lookupOrThrow(Registries.CONFIGURED_FEATURE)
                        .get(this.hugeFungus.get());
                if (featureHolder.isPresent()) {
                    ConfiguredFeature<?, ?> configuredFeature = featureHolder.get().value();
                    if (configuredFeature.feature() instanceof HugeCrystalFungiFeature feature
                            && configuredFeature.config() instanceof CrystalFungiConfig config) {
                        int minHeight = config.stemHeight() + feature.getCapHeight();
                        return level.isInsideBuildHeight(pos.above(minHeight));
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return random.nextFloat() < 0.4F;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.growFeature(level, pos, state, random);
    }

    public boolean growFeature(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (this.hugeFungus.isPresent()) {
            Optional<? extends Holder<ConfiguredFeature<?, ?>>> feature = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(this.hugeFungus.get());
            var event = net.neoforged.neoforge.event.EventHooks.fireBlockGrowFeature(level, random, pos, feature.orElse(null));
            if (event.isCanceled()) {
                return false;
            }
            feature = Optional.ofNullable(event.getFeature());

            if (feature.isEmpty()) {
                return false;
            } else {
                level.removeBlock(pos, false);
                if (feature.get().value().place(level, level.getChunkSource().getGenerator(), random, pos)) {
                    return true;
                } else {
                    level.setBlock(pos, state, 3);
                    return false;
                }
            }
        }

        return false;
    }
}
