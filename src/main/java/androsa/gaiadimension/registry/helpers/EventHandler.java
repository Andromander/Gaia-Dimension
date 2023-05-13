package androsa.gaiadimension.registry.helpers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventHandler {

    //Glittering Furnace Event
    public static class ItemGlitteredEvent extends PlayerEvent {
        private final ItemStack glittered;

        public ItemGlitteredEvent(Player player, ItemStack crafting) {
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

        public ItemPurifiedEvent(Player player, ItemStack crafting) {
            super(player);
            purified = crafting;
        }

        public ItemStack getPurifiedStack() {
            return purified;
        }
    }
}
