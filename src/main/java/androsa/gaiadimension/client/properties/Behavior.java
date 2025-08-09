package androsa.gaiadimension.client.properties;

import androsa.gaiadimension.item.tools.GaiaStaffItem;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public record Behavior() implements SelectItemModelProperty<GaiaStaffItem.Behavior> {
    public static final Codec<GaiaStaffItem.Behavior> VALUE_CODEC = GaiaStaffItem.Behavior.CODEC;
    public static final SelectItemModelProperty.Type<Behavior, GaiaStaffItem.Behavior> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new Behavior()), VALUE_CODEC);

    @Nullable
    @Override
    public GaiaStaffItem.Behavior get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int id, ItemDisplayContext context) {
        GaiaStaffItem.Behavior behavior = stack.get(ModDataComponents.STAFF_BEHAVIOR);

        return Objects.requireNonNullElse(behavior, GaiaStaffItem.Behavior.BASIC);
    }

    @Override
    public Codec<GaiaStaffItem.Behavior> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public Type<? extends SelectItemModelProperty<GaiaStaffItem.Behavior>, GaiaStaffItem.Behavior> type() {
        return TYPE;
    }
}
