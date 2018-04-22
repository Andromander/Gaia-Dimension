package androsa.gaiadimension.registry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class GDEvents {

    //Glittering Furnace Event
    public static class ItemGlitteredEvent extends PlayerEvent {
        private final ItemStack glittered;

        public ItemGlitteredEvent(EntityPlayer player, ItemStack crafting) {
            super(player);
            glittered = crafting;
        }

        public ItemStack getGlitteredStack() {
            return glittered;
        }
    }

    //Purifier Event
    public static class ItemPurifiedEvent extends PlayerEvent {
        private final ItemStack purified;

        public ItemPurifiedEvent(EntityPlayer player, ItemStack crafting) {
            super(player);
            purified = crafting;
        }

        public ItemStack getPurifiedStack() {
            return purified;
        }
    }
}
