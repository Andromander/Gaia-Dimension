package androsa.gaiadimension.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record RestructurerRecipeDisplay(SlotDisplay input, SlotDisplay glitter, SlotDisplay shine, SlotDisplay result, SlotDisplay byproduct, SlotDisplay craftingStation, int time, float exp) implements RecipeDisplay {
    public static final MapCodec<RestructurerRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    SlotDisplay.CODEC.fieldOf("input").forGetter(RestructurerRecipeDisplay::input),
                    SlotDisplay.CODEC.fieldOf("glitter_fuel").forGetter(RestructurerRecipeDisplay::glitter),
                    SlotDisplay.CODEC.fieldOf("shining_fuel").forGetter(RestructurerRecipeDisplay::shine),
                    SlotDisplay.CODEC.fieldOf("result").forGetter(RestructurerRecipeDisplay::result),
                    SlotDisplay.CODEC.fieldOf("byproduct").forGetter(RestructurerRecipeDisplay::byproduct),
                    SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(RestructurerRecipeDisplay::craftingStation),
                    Codec.INT.fieldOf("duration").forGetter(RestructurerRecipeDisplay::time),
                    Codec.FLOAT.fieldOf("experience").forGetter(RestructurerRecipeDisplay::exp)
            ).apply(instance, RestructurerRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, RestructurerRecipeDisplay> STREAM_CODEC = StreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::input,
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::glitter,
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::shine,
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::result,
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::byproduct,
            SlotDisplay.STREAM_CODEC,
            RestructurerRecipeDisplay::craftingStation,
            ByteBufCodecs.INT,
            RestructurerRecipeDisplay::time,
            ByteBufCodecs.FLOAT,
            RestructurerRecipeDisplay::exp,
            RestructurerRecipeDisplay::new);
    public static final RecipeDisplay.Type<RestructurerRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Type<? extends RecipeDisplay> type() {
        return TYPE;
    }
}
