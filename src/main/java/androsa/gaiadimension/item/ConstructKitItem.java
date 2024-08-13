package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.MookaiteConstruct;
import androsa.gaiadimension.entity.OpaliteContruct;
import androsa.gaiadimension.entity.data.MookaitePartType;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

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
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);
        components.add(Component.translatable("gaiadimension.construct_kit." + kit.getName()).withStyle(ChatFormatting.GRAY));

        if (kit.canCycleParts()) {
            if (stack.has(ModDataComponents.KIT_PART)) {
                components.add(Component.translatable("gaiadimension.construct_kit.part").withStyle(ChatFormatting.GRAY).append(CommonComponents.SPACE).append(getPart(stack.get(ModDataComponents.KIT_PART))));
                components.add(Component.translatable("gaiaidmension.construct_kit.part.instruction").withStyle(ChatFormatting.DARK_GRAY));
            }
        }
        if (partColor != null) {
            components.add(Component.translatable("gaiadimension.construct_kit.color").withStyle(ChatFormatting.GRAY).append(CommonComponents.SPACE).append(getColor(partColor)));
        }
    }

    private static Component getPart(Part part) {
        return Component.translatable("gaiadimension.construct_kit.part." + part.getPart().name()).withStyle(style -> style.withColor(0x8599ff));
    }

    private static Component getColor(Color part) {
        return Component.translatable("gaiadimension.construct_kit.color." + part.getPartColor().getSerializedName()).withStyle(style -> style.withColor(part.getColor()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (kit.canCycleParts() && player.isSecondaryUseActive()) {
            if (!stack.has(ModDataComponents.KIT_PART)) {
                //Failed to find a CompoundTag, here's one.
                stack.set(ModDataComponents.KIT_PART, Part.LEFT_HORN);
            } else {
                int maxSize = Part.values().length - 1;
                int next = stack.get(ModDataComponents.KIT_PART).getId() + 1;
                if (next > maxSize)
                    next = 0; //Cycle back to the start;
                if (next < 0)
                    next = maxSize; //This shouldn't really happen, but if for whatever reason this becomes negative, cycle to the end.
                stack.set(ModDataComponents.KIT_PART, Part.byId(next));
                player.displayClientMessage(Component.translatable("gaiadimension.construct_kit.part.swap").append(CommonComponents.SPACE).append(getPart(stack.get(ModDataComponents.KIT_PART))), true);
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        //Blanks do nothing
        if (this.kit != Kit.BLANK) {
            //Make sure it's an Opalite Construct
            if (entity.isAlive() && entity instanceof OpaliteContruct opalite) {
                if (!entity.level().isClientSide()) {
                    //First, make sure we are bonded, otherwise we shouldn't do anything
                    if (opalite.getMookaiteCompanion() == null) {
                        return this.fail(player, Error.NO_BOND);
                    }
                    //Also make sure the bond is a Mookaite Construct
                    if (opalite.getFollowing() == null) {
                        return this.fail(player, Error.FOLLOWER);
                    }
                    //Then, check we don't have kit data already, make sure we do one task at a time
                    if (!opalite.getKitData().isEmpty()) {
                        return this.fail(player, Error.IN_USE);
                    }
                    Part part = stack.get(ModDataComponents.KIT_PART);
                    //Failsafe check for Part tag
                    if (part == null) {
                        return this.fail(player, Error.NO_PART);
                    }
                    //Validate if we have enough to perform the action
                    if (!opalite.validateStacks(this.kit, this.partColor)) {
                        return this.fail(player, Error.RESOURCES);
                    }
                    //Validate if the kit can be used
                    if (!opalite.validateKit(this.kit, part, this.partColor)) {
                        return this.fail(player, Error.INCOMPATIBLE);
                    }
                    //Validate if the Mookaite Construct is in action
                    if (!opalite.validateActivity()) {
                        return this.fail(player, Error.IN_COMBAT);
                    }
                    //Complete the action because we passed
                    opalite.writeKitData(this.kit, part, this.partColor);
                    stack.shrink(1);
                    return InteractionResult.sidedSuccess(player.level().isClientSide());
                } else {
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }

    private InteractionResult fail(Player player, Error failure) {
        player.displayClientMessage(failure.getLangKey(), true);
        return InteractionResult.FAIL;
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

    public enum Part implements StringRepresentable {
        LEFT_HORN(0, () -> MookaiteConstruct.LEFT_HORN),
        RIGHT_HORN(1, () -> MookaiteConstruct.RIGHT_HORN),
        LEFT_EYE(2, () -> MookaiteConstruct.LEFT_EYE),
        RIGHT_EYE(3, () -> MookaiteConstruct.RIGHT_EYE),
        LEFT_SHOULDER(4, () -> MookaiteConstruct.LEFT_SHOULDER),
        RIGHT_SHOULDER(5, () -> MookaiteConstruct.RIGHT_SHOULDER),
        LEFT_ARM(6, () -> MookaiteConstruct.LEFT_ARM),
        RIGHT_ARM(7, () -> MookaiteConstruct.RIGHT_ARM),
        LEFT_LEG(8, () -> MookaiteConstruct.LEFT_LEG),
        RIGHT_LEG(9, () -> MookaiteConstruct.RIGHT_LEG);

        private static final IntFunction<Part> ID = ByIdMap.continuous(Part::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Part> CODEC = StringRepresentable.fromValues(Part::values);
        public static final StreamCodec<ByteBuf, Part> STREAM_CODEC = ByteBufCodecs.idMapper(ID, part -> part.id);
        private final int id;
        private final Supplier<MookaiteConstruct.MookaitePart> part;

        Part(int id, Supplier<MookaiteConstruct.MookaitePart> part) {
            this.id = id;
            this.part = part;
        }

        public MookaiteConstruct.MookaitePart getPart() {
            return part.get();
        }

        public int getId() {
            return id;
        }

        public static Part byId(int id) {
            return ID.apply(id);
        }

        @Override
        public String getSerializedName() {
            return part.get().name();
        }
    }

    public enum Color {
        SCARLET(0xc83133, MookaitePartType.SCARLET),
        AUBURN(0xc15707, MookaitePartType.AUBURN),
        GOLD(0xce9531, MookaitePartType.GOLD),
        MAUVE(0x905090, MookaitePartType.MAUVE),
        BEIGE(0xccb393, MookaitePartType.BEIGE),
        IVORY(0xd6e2f9, MookaitePartType.IVORY);

        private final int color;
        private final MookaitePartType part;

        Color(int color, MookaitePartType part) {
            this.color = color;
            this.part = part;
        }

        public int getColor() {
            return color;
        }

        public MookaitePartType getPartColor() {
            return part;
        }
    }
    
    public enum Error {
        NO_BOND("no_bond"),
        FOLLOWER("follower"),
        IN_USE("in_use"),
        NO_PART("no_part"),
        RESOURCES("resources"),
        INCOMPATIBLE("incompatible"),
        IN_COMBAT("in_combat");

        private final String lang;

        Error(String lang) {
            this.lang = lang;
        }

        public Component getLangKey() {
            return Component.translatable("gaiadimension.construct_kit.invalid." + lang).withStyle(ChatFormatting.RED);
        }
    }
}
