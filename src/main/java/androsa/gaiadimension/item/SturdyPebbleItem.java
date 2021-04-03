package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.projectile.ThrownPebbleEntity;
import androsa.gaiadimension.registry.GaiaItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class SturdyPebbleItem extends Item {

    public SturdyPebbleItem() {
        super(new Properties().stacksTo(16).tab(GaiaItemGroups.GAIA_ITEMS));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isClientSide()) {
            ThrownPebbleEntity pebble = new ThrownPebbleEntity(worldIn, playerIn);
            pebble.setItem(itemstack);
            pebble.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(pebble);
        }

        playerIn.awardStat(Stats.ITEM_USED.get(this));
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
