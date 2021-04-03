package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class MucklingEntity extends SlimeEntity {

    public MucklingEntity(EntityType<? extends MucklingEntity> entity, World par1World) {
        super(entity, par1World);
    }

    @Override
    public void setSize(int size, boolean resetHealth) {
        super.setSize(size, resetHealth);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)size + 3);
        this.xpReward += 3;
    }

    public static boolean canSpawnHere(EntityType<MucklingEntity> entity, IWorld world, SpawnReason spawn, BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == SpawnReason.SPAWNER && world.getBrightness(LightType.SKY, pos) < 8) {
                return true;
            } else {
                Optional<RegistryKey<Biome>> biome = world.getBiomeName(pos);
                if (Objects.equals(biome, Optional.of(ModBiomes.purple_agate_swamp)) || pos.getY() < 40 && random.nextFloat() < 0.5F) {
                    return checkMobSpawnRules(entity, world, spawn, pos, random);
                }
            }
        }
        return false;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D);
    }

    @Override
    protected boolean isDealsDamage() {
        return true;
    }

    @Override
    protected boolean spawnCustomParticles() {
        int i = getSize();
        for (int j = 0; j < i * 8; ++j) {
            float f = this.random.nextFloat() * ((float) Math.PI * 2F);
            float f1 = this.random.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
            World world = this.level;
            double d0 = this.getX() + (double) f2;
            double d1 = this.getZ() + (double) f3;
            BlockState state = ModBlocks.gummy_glitter_block.get().defaultBlockState();
            world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state), d0, this.getBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
        }
        return true;
    }
}
