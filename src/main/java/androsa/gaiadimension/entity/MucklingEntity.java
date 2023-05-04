package androsa.gaiadimension.entity;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Optional;

public class MucklingEntity extends Slime {

    public MucklingEntity(EntityType<? extends MucklingEntity> entity, Level par1World) {
        super(entity, par1World);
    }

    @Override
    public void setSize(int size, boolean resetHealth) {
        super.setSize(size, resetHealth);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)size + 3);
        this.xpReward += 3;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return isTiny() ? ModSounds.ENTITY_MUCKLING_DEATH_SMALL.get() : ModSounds.ENTITY_MUCKLING_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return isTiny() ? ModSounds.ENTITY_MUCKLING_HURT_SMALL.get() : ModSounds.ENTITY_MUCKLING_HURT.get();
    }

    @Override
    protected SoundEvent getJumpSound() {
        return isTiny() ? ModSounds.ENTITY_MUCKLING_JUMP_SMALL.get() : ModSounds.ENTITY_MUCKLING_JUMP.get();
    }

    @Override
    protected SoundEvent getSquishSound() {
        return isTiny() ? ModSounds.ENTITY_MUCKLING_SQUISH_SMALL.get() : ModSounds.ENTITY_MUCKLING_SQUISH.get();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
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
            float f2 = Mth.sin(f) * (float) i * 0.5F * f1;
            float f3 = Mth.cos(f) * (float) i * 0.5F * f1;
            Level world = this.level;
            double d0 = this.getX() + (double) f2;
            double d1 = this.getZ() + (double) f3;
            BlockState state = ModBlocks.gummy_glitter_block.get().defaultBlockState();
            world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), d0, this.getBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
        }
        return true;
    }

    public static boolean canSpawnHere(EntityType<MucklingEntity> entity, LevelAccessor world, MobSpawnType spawn, BlockPos pos, RandomSource random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (spawn == MobSpawnType.SPAWNER && world.getBrightness(LightLayer.SKY, pos) < 8) {
                return true;
            } else {
                Optional<ResourceKey<Biome>> biome = world.getBiome(pos).unwrapKey();
                if (Objects.equals(biome, Optional.of(ModBiomes.purple_agate_swamp)) || pos.getY() < 40 && random.nextFloat() < 0.5F) {
                    return checkMobSpawnRules(entity, world, spawn, pos, random);
                }
            }
        }
        return false;
    }
}
