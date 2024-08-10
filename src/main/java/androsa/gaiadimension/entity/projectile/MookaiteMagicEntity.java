package androsa.gaiadimension.entity.projectile;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class MookaiteMagicEntity extends ThrowableItemProjectile {

    public MookaiteMagicEntity(EntityType<? extends MookaiteMagicEntity> entity, Level level) {
        super(entity, level);
    }

    public MookaiteMagicEntity(Level level, LivingEntity entity) {
        super(ModEntities.MOOKAITE_MAGIC_BULLET.get(), entity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModBlocks.gold_mookaite.get().asItem();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.05F;
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.applyArea();
            this.discard();
        }
    }

    private void applyArea() {
        MookaiteAreaEntity area = new MookaiteAreaEntity(this.level(), this.getX(), this.getY(), this.getZ());
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity living) {
            area.setOwner(living);

            if (living instanceof MookaiteConstructEntity mookaite) {
                area.setDuration(40 + 20 * mookaite.countColors(MookaitePartType.MAUVE));
            }
        }

        area.setRadius(3.0F);

        this.level().addFreshEntity(area);
    }
}
