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

public record Element() implements SelectItemModelProperty<GaiaStaffItem.Element> {

    public static final Codec<GaiaStaffItem.Element> VALUE_CODEC = GaiaStaffItem.Element.CODEC;
    public static final SelectItemModelProperty.Type<Element, GaiaStaffItem.Element> TYPE = SelectItemModelProperty.Type.create(
            MapCodec.unit(new Element()), VALUE_CODEC);

    @Nullable
    @Override
    public GaiaStaffItem.Element get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int id, ItemDisplayContext context) {
        GaiaStaffItem.Element element = stack.get(ModDataComponents.STAFF_ELEMENT);

        return Objects.requireNonNullElse(element, GaiaStaffItem.Element.PHYSICAL);
    }

    @Override
    public Codec<GaiaStaffItem.Element> valueCodec() {
        return VALUE_CODEC;
    }

    @Override
    public Type<? extends SelectItemModelProperty<GaiaStaffItem.Element>, GaiaStaffItem.Element> type() {
        return TYPE;
    }
}
