package androsa.gaiadimension.potion;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.registration.ModEffects;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class CorruptionEffect extends MobEffect {

    public static final ResourceLocation NAME = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "attack_boost");

    public CorruptionEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, NAME, 4.0D, AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.getType().is(GaiaTags.Entities.CORRUPT)) {
            living.hurt(GaiaDamage.getDamage(living.level(), GaiaDamage.CORRUPTION), 2.0F);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int j = 25 >> amplifier;

        return j <= 0 || duration % j == 0;
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingDamageEvent.Pre e) {
        if (e.getEntity().hasEffect(ModEffects.goldstone_plague)) {
            DamageSource source = e.getContainer().getSource();
            if (source.getDirectEntity() != null && source.getDirectEntity() instanceof LivingEntity attacker) {
                if (attacker.getType().is(GaiaTags.Entities.CORRUPT)) {
                    e.getContainer().setNewDamage(e.getOriginalDamage() * 1.5F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent e) {
        Entity corrputSpawn = ModEntities.CORRUPT_SAPPER.get().create(e.getEntity().getCommandSenderWorld());

        if (e.getSource().is(GaiaDamage.CORRUPTION)) {
            e.getEntity().getCommandSenderWorld().addFreshEntity(corrputSpawn);
        }
    }

    //Do not apply to Corrupted mobs
    @SubscribeEvent
    public static void applyEffect(MobEffectEvent.Applicable e) {
        if (e.getEntity().getType().is(GaiaTags.Entities.CORRUPT)) {
            e.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }
}
