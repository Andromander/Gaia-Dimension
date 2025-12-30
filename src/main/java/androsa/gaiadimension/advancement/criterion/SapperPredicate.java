package androsa.gaiadimension.advancement.criterion;

import androsa.gaiadimension.entity.GrowthSapper;
import androsa.gaiadimension.entity.data.SapperVariant;
import androsa.gaiadimension.registry.registration.ModPredicates;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record SapperPredicate(Optional<SapperVariant> variant) implements EntitySubPredicate {
    public static final MapCodec<SapperPredicate> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            SapperVariant.CODEC.optionalFieldOf("variant").forGetter(SapperPredicate::variant)
                    )
                    .apply(instance, SapperPredicate::new)
    );

    @Override
    public MapCodec<? extends EntitySubPredicate> codec() {
        return ModPredicates.SAPPER.get();
    }

    @Override
    public boolean matches(Entity entity, ServerLevel level, @Nullable Vec3 pos) {
        if (entity instanceof GrowthSapper sapper) {
            return this.variant.isEmpty() || sapper.getEntityVariant() == this.variant.get();
        }
        return false;
    }

    public static SapperPredicate isVariant(SapperVariant variant) {
        return new SapperPredicate(Optional.of(variant));
    }
}
