package androsa.gaiadimension.recipe;

import androsa.gaiadimension.network.BigStreamCodec;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public record PurifierRecipeDisplay(SlotDisplay input, SlotDisplay glitter, SlotDisplay shine, SlotDisplay nulling, SlotDisplay result, SlotDisplay byproduct, SlotDisplay craftingStation, int time, float exp) implements RecipeDisplay {
    public static final MapCodec<PurifierRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    SlotDisplay.CODEC.fieldOf("input").forGetter(PurifierRecipeDisplay::input),
                    SlotDisplay.CODEC.fieldOf("glitter_fuel").forGetter(PurifierRecipeDisplay::glitter),
                    SlotDisplay.CODEC.fieldOf("shining_fuel").forGetter(PurifierRecipeDisplay::shine),
                    SlotDisplay.CODEC.fieldOf("nulling_fuel").forGetter(PurifierRecipeDisplay::nulling),
                    SlotDisplay.CODEC.fieldOf("result").forGetter(PurifierRecipeDisplay::result),
                    SlotDisplay.CODEC.fieldOf("byproduct").forGetter(PurifierRecipeDisplay::byproduct),
                    SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(PurifierRecipeDisplay::craftingStation),
                    Codec.INT.fieldOf("duration").forGetter(PurifierRecipeDisplay::time),
                    Codec.FLOAT.fieldOf("experience").forGetter(PurifierRecipeDisplay::exp)
            ).apply(instance, PurifierRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, PurifierRecipeDisplay> STREAM_CODEC = BigStreamCodec.composite(
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::input,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::glitter,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::shine,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::nulling,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::result,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::byproduct,
            SlotDisplay.STREAM_CODEC,
            PurifierRecipeDisplay::craftingStation,
            ByteBufCodecs.INT,
            PurifierRecipeDisplay::time,
            ByteBufCodecs.FLOAT,
            PurifierRecipeDisplay::exp,
            PurifierRecipeDisplay::new);
    public static final RecipeDisplay.Type<PurifierRecipeDisplay> TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public Type<? extends RecipeDisplay> type() {
        return TYPE;
    }
}
