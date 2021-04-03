package androsa.gaiadimension.potion;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModEffects;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID)
public class CorruptionEffect extends Effect {

    private final double bonusPerLevel;

    public CorruptionEffect(int color, double damage) {
        super(EffectType.HARMFUL, color);
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
        if (e.getEntityLiving().hasEffect(ModEffects.goldstone_plague) &&
                e.getSource().equals(DamageSource.mobAttack((LivingEntity) e.getSource().getEntity()))) {

            if (e.getEntityLiving().getMobType() != GaiaDimensionMod.CORRUPT &&
                    ((LivingEntity)e.getSource().getEntity()).getMobType() == GaiaDimensionMod.CORRUPT) {

                e.setAmount(e.getAmount() * 1.5F);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent e) {
        Entity corrputSpawn = ModEntities.CORRUPT_SAPPER.create(e.getEntity().getCommandSenderWorld());

        if (e.getSource() == GaiaDimensionMod.CORRUPTION) {
            e.getEntity().getCommandSenderWorld().addFreshEntity(corrputSpawn);
        }
    }
}
