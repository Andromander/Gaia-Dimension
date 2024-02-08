package androsa.gaiadimension.potion;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.registration.ModEffects;
import androsa.gaiadimension.registry.registration.ModEntities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class CorruptionEffect extends MobEffect {

    public CorruptionEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "ED1B7821-E928-4EC7-8CD7-0FF2DE5E378A", 4.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (living.getMobType() != GaiaDimensionMod.CORRUPT) {
            living.hurt(GaiaDamage.getDamage(living.level(), GaiaDamage.CORRUPTION), 2.0F);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int j = 25 >> amplifier;

        return j <= 0 || duration % j == 0;
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingDamageEvent e) {
        if (e.getEntity().hasEffect(ModEffects.goldstone_plague.get())) {
            if (e.getSource().getDirectEntity() != null && e.getSource().getDirectEntity() instanceof LivingEntity attacker) {
                if (attacker.getMobType() == GaiaDimensionMod.CORRUPT) {
                    e.setAmount(e.getAmount() * 1.5F);
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
        if (e.getEntity().getMobType() == GaiaDimensionMod.CORRUPT) {
            e.setResult(Event.Result.DENY);
        }
    }
}
