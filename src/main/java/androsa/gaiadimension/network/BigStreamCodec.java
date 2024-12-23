package androsa.gaiadimension.network;

import com.mojang.datafixers.util.Function9;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Function;

/**
 * So, here's the deal:
 * StreamCodec only allows for composites of up to 8 values, and I need more.
 * The only way to make a bigger StreamCodec...is to make an even bigger StreamCodec.
 * Lesson learned, kids: do not optimise too hard, you get this recipe situation.
 * It's not even my problem - Mojang just didn't make bigger StreamCodecs.
 */
public class BigStreamCodec {
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> composite(
            final StreamCodec<? super B, T1> stream1,
            final Function<C, T1> param1,
            final StreamCodec<? super B, T2> stream2,
            final Function<C, T2> param2,
            final StreamCodec<? super B, T3> stream3,
            final Function<C, T3> param3,
            final StreamCodec<? super B, T4> stream4,
            final Function<C, T4> param4,
            final StreamCodec<? super B, T5> stream5,
            final Function<C, T5> param5,
            final StreamCodec<? super B, T6> stream6,
            final Function<C, T6> param6,
            final StreamCodec<? super B, T7> stream7,
            final Function<C, T7> param7,
            final StreamCodec<? super B, T8> stream8,
            final Function<C, T8> param8,
            final StreamCodec<? super B, T9> stream9,
            final Function<C, T9> param9,
            final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> function) {
        return new StreamCodec<>() {
            @Override
            public C decode(B buf) {
                T1 t1 = stream1.decode(buf);
                T2 t2 = stream2.decode(buf);
                T3 t3 = stream3.decode(buf);
                T4 t4 = stream4.decode(buf);
                T5 t5 = stream5.decode(buf);
                T6 t6 = stream6.decode(buf);
                T7 t7 = stream7.decode(buf);
                T8 t8 = stream8.decode(buf);
                T9 t9 = stream9.decode(buf);
                return function.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }

            @Override
            public void encode(B buf, C codec) {
                stream1.encode(buf, param1.apply(codec));
                stream2.encode(buf, param2.apply(codec));
                stream3.encode(buf, param3.apply(codec));
                stream4.encode(buf, param4.apply(codec));
                stream5.encode(buf, param5.apply(codec));
                stream6.encode(buf, param6.apply(codec));
                stream7.encode(buf, param7.apply(codec));
                stream8.encode(buf, param8.apply(codec));
                stream9.encode(buf, param9.apply(codec));
            }
        };
    }
}
