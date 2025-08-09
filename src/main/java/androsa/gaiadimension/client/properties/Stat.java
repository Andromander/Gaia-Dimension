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

public record Stat() implements SelectItemModelProperty<GaiaStaffItem.Stat> {

    public static final Codec<GaiaStaffItem.Stat> VALUE_CODEC = GaiaStaffItem.Stat.CODEC;
    public static final SelectItemModelProperty.Type<Stat, GaiaStaffItem.Stat> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new Stat()), VALUE_CODEC);

    @Nullable
    @Override
    public GaiaStaffItem.Stat get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int id, ItemDisplayContext context) {
        GaiaStaffItem.Stat stat = stack.get(ModDataComponents.STAFF_STAT);

        return Objects.requireNonNullElse(stat, GaiaStaffItem.Stat.STANDARD);
    }

    @Override
    public Codec<GaiaStaffItem.Stat> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public Type<? extends SelectItemModelProperty<GaiaStaffItem.Stat>, GaiaStaffItem.Stat> type() {
        return TYPE;
    }
}
