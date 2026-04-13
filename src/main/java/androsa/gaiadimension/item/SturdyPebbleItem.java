package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.projectile.ThrownPebble;
import androsa.gaiadimension.registry.registration.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SturdyPebbleItem extends Item {

    public SturdyPebbleItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSounds.STURDY_PEBBLE_THROW.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (worldIn.getRandom().nextFloat() * 0.4F + 0.8F));

        if (worldIn instanceof ServerLevel server) {
            Projectile.spawnProjectileFromRotation(ThrownPebble::new, server, itemstack, playerIn, 0.0F, 1.5F, 1.0F);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }
}
