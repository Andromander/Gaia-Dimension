package androsa.gaiadimension.world.chunk.warp;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.minecraft.Util;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.feature.NoiseEffect;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * I MADE THIS FOR THE SOLE PURPOSE OF A NO-OP BEARDIFIER, BITE ME MOJANK
 */
public class GaiaBeardifier implements DensityFunctions.BeardifierOrMarker {
    public static final GaiaBeardifier NO_OP = new GaiaBeardifier();
    private static final float[] BEARD_KERNEL = Util.make(new float[13824], (kernels) -> {
        for(int z = 0; z < 24; ++z) {
            for(int x = 0; x < 24; ++x) {
                for(int y = 0; y < 24; ++y) {
                    kernels[z * 24 * 24 + x * 24 + y] = (float)computeBeardContribution(x - 12, y - 12, z - 12);
                }
            }
        }

    });
    protected final ObjectList<StructurePiece> rigids;
    protected final ObjectList<JigsawJunction> junctions;
    protected final ObjectListIterator<StructurePiece> pieceIterator;
    protected final ObjectListIterator<JigsawJunction> junctionIterator;

    public GaiaBeardifier() {
        this.rigids = new ObjectArrayList<>();
        this.junctions = new ObjectArrayList<>();
        this.pieceIterator = this.rigids.iterator();
        this.junctionIterator = this.junctions.iterator();
    }

    //TODO: Will we ever use?
    public GaiaBeardifier(StructureFeatureManager manager, ChunkAccess access) {
        ChunkPos chunkpos = access.getPos();
        int minX = chunkpos.getMinBlockX();
        int minZ = chunkpos.getMinBlockZ();
        this.junctions = new ObjectArrayList<>(32);
        this.rigids = new ObjectArrayList<>(10);

        manager.startsForFeature(SectionPos.bottomOf(access), (structurefeature) -> structurefeature.adaptNoise).forEach((start) -> {
            for(StructurePiece structurepiece : start.getPieces()) {
                if (structurepiece.isCloseToChunk(chunkpos, 12)) {
                    if (structurepiece instanceof PoolElementStructurePiece poolelement) {
                        StructureTemplatePool.Projection projection = poolelement.getElement().getProjection();
                        if (projection == StructureTemplatePool.Projection.RIGID) {
                            this.rigids.add(poolelement);
                        }

                        for(JigsawJunction jigsawjunction : poolelement.getJunctions()) {
                            int sX = jigsawjunction.getSourceX();
                            int sZ = jigsawjunction.getSourceZ();
                            if (sX > minX - 12 && sZ > minZ - 12 && sX < minX + 15 + 12 && sZ < minZ + 15 + 12) {
                                this.junctions.add(jigsawjunction);
                            }
                        }
                    } else {
                        this.rigids.add(structurepiece);
                    }
                }
            }

        });

        this.pieceIterator = this.rigids.iterator();
        this.junctionIterator = this.junctions.iterator();
    }

    @Override
    public double compute(FunctionContext context) {
        int x = context.blockX();
        int y = context.blockY();
        int z = context.blockZ();
        double d0 = 0.0D;

        while(this.pieceIterator.hasNext()) {
            StructurePiece structurepiece = this.pieceIterator.next();
            BoundingBox boundingbox = structurepiece.getBoundingBox();
            int maxX = Math.max(0, Math.max(boundingbox.minX() - x, x - boundingbox.maxX()));
            int maxY = y - (boundingbox.minY() + (structurepiece instanceof PoolElementStructurePiece ? ((PoolElementStructurePiece)structurepiece).getGroundLevelDelta() : 0));
            int maxZ = Math.max(0, Math.max(boundingbox.minZ() - z, z - boundingbox.maxZ()));
            NoiseEffect noiseeffect = structurepiece.getNoiseEffect();
            if (noiseeffect == NoiseEffect.BURY) {
                d0 += getBuryContribution(maxX, maxY, maxZ);
            } else if (noiseeffect == NoiseEffect.BEARD) {
                d0 += getBeardContribution(maxX, maxY, maxZ) * 0.8D;
            }
        }

        this.pieceIterator.back(this.rigids.size());

        while(this.junctionIterator.hasNext()) {
            JigsawJunction jigsawjunction = this.junctionIterator.next();
            int sX = x - jigsawjunction.getSourceX();
            int sY = y - jigsawjunction.getSourceGroundY();
            int sZ = z - jigsawjunction.getSourceZ();
            d0 += getBeardContribution(sX, sY, sZ) * 0.4D;
        }

        this.junctionIterator.back(this.junctions.size());
        return d0;
    }

    @Override
    public double minValue() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public double maxValue() {
        return Double.POSITIVE_INFINITY;
    }

    protected static double getBuryContribution(int x, int y, int z) {
        double d0 = Mth.length(x, (double)y / 2.0D, z);
        return Mth.clampedMap(d0, 0.0D, 6.0D, 1.0D, 0.0D);
    }

    protected static double getBeardContribution(int x, int y, int z) {
        int posX = x + 12;
        int posY = y + 12;
        int posZ = z + 12;
        if (posX >= 0 && posX < 24) {
            if (posY >= 0 && posY < 24) {
                return posZ >= 0 && posZ < 24 ? (double)BEARD_KERNEL[posZ * 24 * 24 + posX * 24 + posY] : 0.0D;
            } else {
                return 0.0D;
            }
        } else {
            return 0.0D;
        }
    }

    private static double computeBeardContribution(int x, int y, int z) {
        double cellW = x * x + z * z;
        double height = (double)y + 0.5D;
        double cellH = height * height;
        double d3 = Math.pow(Math.E, -(cellH / 16.0D + cellW / 16.0D));
        double d4 = -height * Mth.fastInvSqrt(cellH / 2.0D + cellW / 2.0D) / 2.0D;
        return d4 * d3;
    }
}
