package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.IntFunction;

public class ConstructKitItem extends Item {

    private final Kit kit;
    private final Color partColor;

    public ConstructKitItem(Properties props, Kit kit) {
        this(props, kit, null);
    }

    public ConstructKitItem(Properties props, Kit kit, Color color) {
        super(props);
        this.kit = kit;
        this.partColor = color;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        components.add(Component.translatable("gaiadimension.construct_kit." + kit.getName()).withStyle(ChatFormatting.GRAY));

        if (kit.canCycleParts()) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("Part")) {
                components.add(Component.translatable("gaiadimension.construct_kit.part").withStyle(ChatFormatting.GRAY).append(CommonComponents.SPACE).append(getPart(tag)));
            }
        }
        if (partColor != null) {
            components.add(Component.translatable("gaiadimension.construct_kit.color").withStyle(ChatFormatting.GRAY).append(CommonComponents.SPACE).append(getColor(partColor)));
        }
    }

    private static Component getPart(CompoundTag tag) {
        return Component.translatable("gaiadimension.construct_kit.part." + Part.byId(tag.getInt("Part")).getName()).withStyle(style -> style.withColor(0x8599ff));
    }

    private static Component getColor(Color part) {
        return Component.translatable("gaiadimension.construct_kit.color." + MookaiteConstructEntity.INT_TO_COLOR.get(part.getPartColor())).withStyle(style -> style.withColor(part.getColor()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (kit.canCycleParts() && player.isSecondaryUseActive()) {
            CompoundTag tag = stack.getTag();

            if (tag == null || !tag.contains("Part")) {
                //Failed to find a CompoundTag, here's one.
                stack.getOrCreateTag().putInt("Part", 0);
            } else {
                int maxSize = Part.values().length - 1;
                int next = tag.getInt("Part") + 1;
                if (next > maxSize)
                    next = 0; //Cycle back to the start;
                if (next < 0)
                    next = maxSize; //This shouldn't really happen, but if for whatever reason this becomes negative, cycle to the end.
                tag.putInt("Part", next);
                player.displayClientMessage(Component.translatable("gaiadimension.construct_kit.part.swap").append(CommonComponents.SPACE).append(getPart(tag)), true);
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }

    public enum Kit {
        BLANK("blank", false),
        REPAIR("repair", true),
        AUGMENT("augment", true),
        REPLACE("replace", true);

        private final String name;
        private final boolean cycleParts;

        Kit(String name, boolean cycle) {
            this.name = name;
            this.cycleParts = cycle;
        }

        public String getName() {
            return name;
        }

        public boolean canCycleParts() {
            return cycleParts;
        }
    }

    private enum Part {
        LEFT_HORN(0, "left_horn"),
        RIGHT_HORN(1, "right_horn"),
        LEFT_EYE(2, "left_eye"),
        RIGHT_EYE(3, "right_eye"),
        LEFT_SHOULDER(4, "left_shoulder"),
        RIGHT_SHOULDER(5, "right_shoulder"),
        LEFT_ARM(6, "left_arm"),
        RIGHT_ARM(7, "right_arm"),
        LEFT_LEG(8, "left_leg"),
        RIGHT_LEG(9, "right_leg");

        private static final IntFunction<Part> ID = ByIdMap.continuous(Part::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final int id;
        private final String name;

        Part(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Part byId(int id) {
            return ID.apply(id);
        }
    }

    public enum Color {
        SCARLET(0xc83133, 1),
        AUBURN(0xc15707, 2),
        GOLD(0xce9531, 3),
        MAUVE(0x905090, 4),
        BEIGE(0xccb393, 5),
        IVORY(0xd6e2f9, 6);

        private final int color;
        private final int part;

        Color(int color, int part) {
            this.color = color;
            this.part = part;
        }

        public int getColor() {
            return color;
        }

        public int getPartColor() {
            return part;
        }
    }
}
