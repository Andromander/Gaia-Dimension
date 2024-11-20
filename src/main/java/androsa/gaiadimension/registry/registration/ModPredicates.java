package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.advancement.criterion.SapperPredicate;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPredicates {

    public static final DeferredRegister<MapCodec<? extends EntitySubPredicate>> ENTITY_SUB_PREDICATES = DeferredRegister.create(Registries.ENTITY_SUB_PREDICATE_TYPE, GaiaDimensionMod.MODID);

    public static final DeferredHolder<MapCodec<? extends EntitySubPredicate>, MapCodec<SapperPredicate>> SAPPER = ENTITY_SUB_PREDICATES.register("sapper", () -> SapperPredicate.CODEC);
}
