package androsa.gaiadimension.potion;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModEffects;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class CorruptionEffect extends MobEffect {

    private final double bonusPerLevel;

    public CorruptionEffect(int color, double damage) {
        super(MobEffectCategory.HARMFUL, color);
        this.bonusPerLevel = damage;
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "ED1B7821-E928-4EC7-8CD7-0FF2DE5E378A", 0.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getMobType() != GaiaDimensionMod.CORRUPT) {
            entityLivingBaseIn.hurt(GaiaDimensionMod.CORRUPTION, 2.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int j = 25 >> amplifier;

        return j <= 0 || duration % j == 0;
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier) {
        return this.bonusPerLevel * (double)(amplifier + 1);
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingDamageEvent e) {
        if (e.getEntityLiving().hasEffect(ModEffects.goldstone_plague)) {
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

        if (e.getSource() == GaiaDimensionMod.CORRUPTION) {
            e.getEntity().getCommandSenderWorld().addFreshEntity(corrputSpawn);
        }
    }

    //Do not apply to Corrupted mobs
    @SubscribeEvent
    public static void applyEffect(PotionEvent.PotionApplicableEvent e) {
        if (e.getEntityLiving().getMobType() == GaiaDimensionMod.CORRUPT) {
            e.setResult(Event.Result.DENY);
        }
    }
}
