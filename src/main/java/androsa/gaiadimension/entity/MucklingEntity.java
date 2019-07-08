package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class MucklingEntity extends SlimeEntity {
    //public static final ResourceLocation LOOT_TABLE = new ResourceLocation(GaiaDimension.MODID, "entities/muckling");

    public MucklingEntity(EntityType<? extends MucklingEntity> entity, World par1World) {
        super(entity, par1World);
    }

    @Override
    public void setSlimeSize(int size, boolean resetHealth) {
        super.setSlimeSize(size, resetHealth);
        this.experienceValue += 3;
    }

    public static boolean canSpawnHere(EntityType<MucklingEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Biome biome = world.getBiome(pos);
            if (biome == ModBiomes.purple_agate_swamp || pos.getY() < 40 && random.nextFloat() < 0.5F) {
                return func_223315_a(entity, world, spawn, pos, random);
            }
        }
        return false;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    protected boolean canDamagePlayer() {
        return true;
    }

    @Override
    protected int getAttackStrength() {
        return super.getAttackStrength() + 3;
    }

    @Override
    protected boolean spawnCustomParticles() {
        int i = getSlimeSize();
        for (int j = 0; j < i * 8; ++j) {
            float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
            World world = this.world;
            double d0 = this.posX + (double) f2;
            double d1 = this.posZ + (double) f3;
            BlockState state = ModBlocks.gummy_glitter_block.getDefaultState();
            world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state), d0, this.getBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
        }
        return true;
    }

    /*@Override
    public ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }*/
}
