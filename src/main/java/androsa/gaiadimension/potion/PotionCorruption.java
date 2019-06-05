package androsa.gaiadimension.potion;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDCorruptSapper;
import androsa.gaiadimension.registry.GDPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GaiaDimension.MODID)
public class PotionCorruption extends Potion {

    private final double bonusPerLevel;

    public PotionCorruption(int color, double damage) {
        super(true, color);
        this.bonusPerLevel = damage;
        this.setPotionName(GaiaDimension.MODID + ".effect.goldstone_plague");
        this.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "ED1B7821-E928-4EC7-8CD7-0FF2DE5E378A", 0.0D, 0);
        this.setIconIndex(0, 0);
    }

    @Override
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(GaiaDimension.POTION_TEXTURES);
        return super.getStatusIconIndex();
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = 25 >> amplifier;

        return j <= 0 || duration % j == 0;
    }

    @Override
    public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
        return this.bonusPerLevel * (double)(amplifier + 1);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getCreatureAttribute() != GaiaDimension.CORRUPT) {
            entityLivingBaseIn.attackEntityFrom(GaiaDimension.CORRUPTION, 2.0F);
        }
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingDamageEvent e) {
        if (e.getEntityLiving().isPotionActive(GDPotions.goldstone_plague) &&
                e.getSource() == DamageSource.causeMobDamage((EntityLivingBase)e.getSource().getTrueSource())) {

            if (e.getEntityLiving().getCreatureAttribute() != GaiaDimension.CORRUPT &&
                    ((EntityLivingBase)e.getSource().getTrueSource()).getCreatureAttribute() == GaiaDimension.CORRUPT) {

                e.setAmount(e.getAmount() * 1.5F);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent e) {
        Entity corrputSpawn = new GDCorruptSapper(e.getEntity().getEntityWorld());

        if (e.getSource() == GaiaDimension.CORRUPTION) {
            e.getEntity().getEntityWorld().spawnEntity(corrputSpawn);
        }
    }
}
