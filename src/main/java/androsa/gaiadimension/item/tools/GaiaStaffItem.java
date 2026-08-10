package androsa.gaiadimension.item.tools;

import androsa.gaiadimension.entity.projectile.StaffProjectile;
import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import androsa.gaiadimension.registry.registration.ModSounds;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/*
 * Core component: element
 * Head component: behaviour
 * Rod component: stat
 *
 * Element:
 * Physical - No element    - (base) Crystal Core
 * Fire - Fire Damage       - Spitfire Heart
 * Electric - Static damage - Shockshooter Soul
 * Poison - Poison damage   - Moss Agate Claw
 * Frost - Frost damage     - Howlite Fragment
 * Magic - Magic damage     - Spellbound Core
 * Energy - Energy damage   - Bismuth Horn
 *
 * Behaviour:
 * Basic - No behaviour                                - (base) Sugilite
 * Scatter - Multiple projectiles in random directions - Stibnite
 * Ricochet - Bounces 3 times before expiring          - Euclase
 * Blast - Small ranged explosion on contact           - Carnelian
 * Linger - Creates a small AoE of the element         - Benitoite
 * Burst - More projectiles on contact                 - Goshenite
 *
 * Stat:
 * Standard - No stat boost   - (base) Agate Stick
 * Power - More damage        - Scaynyx Ingot
 * Speed - Faster projectile  - Glitter Rod (smelted from Glitter Dust (dropped by Frail Glitter randomly, can also craft Frail Glitter))
 * Recharge - Faster cooldown - Shiny Bone
 * Force - More knockback     - Magnetite Rod (smelted from Magnetite (dropped by Charged Mineral, what to do?))
 * Sustain - Longer life      - Aura Rod
 */
public class GaiaStaffItem extends Item {

    public GaiaStaffItem(Properties props) {
        super(props.component(ModDataComponents.STAFF_ELEMENT, GaiaStaffItem.Element.PHYSICAL).component(ModDataComponents.STAFF_BEHAVIOR, GaiaStaffItem.Behavior.BASIC).component(ModDataComponents.STAFF_STAT, GaiaStaffItem.Stat.STANDARD));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!(stack.isDamageableItem() && stack.getDamageValue() >= stack.getMaxDamage())) {
            Element element = stack.getOrDefault(ModDataComponents.STAFF_ELEMENT.get(), Element.PHYSICAL);
            Behavior behavior = stack.getOrDefault(ModDataComponents.STAFF_BEHAVIOR.get(), Behavior.BASIC);
            Stat stat = stack.getOrDefault(ModDataComponents.STAFF_STAT.get(), Stat.STANDARD);

            if (level instanceof ServerLevel server) {
                if (behavior == Behavior.SCATTER) {
                    //angle: 10
                    float f1 = 2.0F * 10.0F / (3.0F - 1);
                    float f2 = (3.0F - 1) % 2 * f1 / 2.0F;
                    float f3 = 1.0F;

                    for (int i = 0; i < 3; i++) {
                        StaffProjectile projectile = new StaffProjectile(player, server);
                        projectile.setElement(element);
                        projectile.setBehavior(behavior);
                        projectile.setStat(stat);
                        float f4 = f2 + f3 * (float)((i + 1) / 2) * f1;
                        f3 = -f3;
                        //5.0, -10.0, 15.0
                        Projectile.spawnProjectile(
                                projectile,
                                server,
                                stack,
                                p -> this.shootProjectile(player, p, 1.0F, 1.0F, f4, null));
                    }
                } else {
                    StaffProjectile projectile = new StaffProjectile(player, server);
                    projectile.setElement(element);
                    projectile.setBehavior(behavior);
                    projectile.setStat(stat);
                    Projectile.spawnProjectile(projectile, server, stack, p -> p.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, stat == Stat.SPEED ? 2.0F : 1.0F, 1.0F));
                }
            }

            //idc if it shoots multiple projectiles, deplete durability once per shot, not per projectile
            this.depleteStaff(stack, player, 1);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.ENTITY_MAGIC_PROJECTILE_SHOOT.get(), player.getSoundSource(), stat == Stat.SPEED ? 2.0F : 1.0F, 1.0F);
            player.getCooldowns().addCooldown(stack, stat == Stat.RECHARGE ? 10 : 20);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    //Copy of CrossbowItem#shootProjectile
    //Moved playSound to use as I just want one sound to play, removing integer parameter
    private void shootProjectile(LivingEntity owner, Projectile projectile, float vel, float inaccuracy, float angle, @Nullable LivingEntity target) {
        Vector3f vector3f;
        if (target != null) {
            double d0 = target.getX() - owner.getX();
            double d1 = target.getZ() - owner.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            double d3 = target.getY(0.3333333333333333) - projectile.getY() + d2 * 0.2F;
            vector3f = CrossbowItem.getProjectileShotVector(owner, new Vec3(d0, d3, d1), angle);
        } else {
            Vec3 vec3 = owner.getUpVector(1.0F);
            Quaternionf quaternionf = new Quaternionf().setAngleAxis(angle * (float) (Math.PI / 180.0), vec3.x, vec3.y, vec3.z);
            Vec3 vec31 = owner.getViewVector(1.0F);
            vector3f = vec31.toVector3f().rotate(quaternionf);
        }

        projectile.shoot(vector3f.x(), vector3f.y(), vector3f.z(), vel, inaccuracy);
    }

    //Copy of Twilight Forest's TFItemStackUtils for durability depletion. Don't know about keeping the enchantment values here
    private void depleteStaff(ItemStack stack, Player player, int amount) {
        if (stack.isDamageableItem()) {
            amount = stack.getItem().damageItem(stack, amount, player, item -> {});
            if (player instanceof ServerPlayer sp && !player.hasInfiniteMaterials()) {
                if (amount > 0) {
                    amount = EnchantmentHelper.processDurabilityChange(sp.level(), stack, amount);
                    if (amount <= 0) {
                        return;
                    }
                }

                if (amount != 0) {
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(sp, stack, stack.getDamageValue() + amount);
                }

                int i = stack.getDamageValue() + amount;
                stack.setDamageValue(i);
            }
        }
    }

    @Override
    @Deprecated
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> component, TooltipFlag flag) {
        super.appendHoverText(stack, context, display, component, flag);

        Element element = stack.get(ModDataComponents.STAFF_ELEMENT);
        component.accept(
                Component.translatable(this.getDescriptionId() + ".desc.element")
                        .withStyle(style -> style.withColor(0xff9eee))
                        .append(Component.literal(":"))
                        .append(CommonComponents.SPACE)
                        .append(Component.translatable(this.getDescriptionId() + ".element." + Objects.requireNonNullElse(element, Element.PHYSICAL).getSerializedName())
                                .withStyle(ChatFormatting.GRAY)));

        if (flag.hasShiftDown()) {
            component.accept(Component.translatable(this.getDescriptionId() + ".element.help." + Objects.requireNonNullElse(element, Element.PHYSICAL).getSerializedName())
                    .withStyle(ChatFormatting.GRAY));
        }

        Behavior behavior = stack.get(ModDataComponents.STAFF_BEHAVIOR);
        component.accept(
                Component.translatable(this.getDescriptionId() + ".desc.behavior")
                        .withStyle(style -> style.withColor(0xff9eee))
                        .append(CommonComponents.SPACE)
                        .append(Component.translatable(this.getDescriptionId() + ".behavior." + Objects.requireNonNullElse(behavior, Behavior.BASIC).getSerializedName())
                                .withStyle(ChatFormatting.GRAY)));

        if (flag.hasShiftDown()) {
            component.accept(Component.translatable(this.getDescriptionId() + ".behavior.help." + Objects.requireNonNullElse(behavior, Behavior.BASIC).getSerializedName())
                    .withStyle(ChatFormatting.GRAY));
        }

        Stat stat = stack.get(ModDataComponents.STAFF_STAT);
        component.accept(
                Component.translatable(this.getDescriptionId() + ".desc.stat")
                        .withStyle(style -> style.withColor(0xff9eee))
                        .append(CommonComponents.SPACE)
                        .append(Component.translatable(this.getDescriptionId() + ".stat." + Objects.requireNonNullElse(stat, Stat.STANDARD).getSerializedName())
                                .withStyle(ChatFormatting.GRAY)));

        if (flag.hasShiftDown()) {
            component.accept(Component.translatable(this.getDescriptionId() + ".stat.help." + Objects.requireNonNullElse(stat, Stat.STANDARD).getSerializedName())
                    .withStyle(ChatFormatting.GRAY));
        }

        if (!flag.hasShiftDown()) {
            component.accept(Component.translatable(this.getDescriptionId() + ".help").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    public enum Element implements StringRepresentable {
        PHYSICAL(0, "physical", GaiaDamage.PHYSICAL_MAGIC, 0xFFFFFF),
        FIRE(1, "fire", GaiaDamage.FIRE_MAGIC, 0xFF6666),
        ELECTRIC(2, "electric", GaiaDamage.ELECTRIC_MAGIC, 0xFFFF66),
        POISON(3, "poison", GaiaDamage.POISON_MAGIC, 0x99FF33),
        FROST(4, "frost", GaiaDamage.FROST_MAGIC, 0x66CCFF),
        MAGIC(5, "magic", GaiaDamage.LIGHT_MAGIC, 0xFF99FF),
        ENERGY(6, "energy", GaiaDamage.ENERGY_MAGIC, 0x9966CC);

        private static final IntFunction<Element> ID = ByIdMap.continuous(Element::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Element> CODEC = StringRepresentable.fromValues(Element::values);
        public static final StreamCodec<ByteBuf, Element> STREAM_CODEC = ByteBufCodecs.idMapper(ID, part -> part.id);
        private final int id;
        private final String name;
        private final ResourceKey<DamageType> damage;
        private final int color;

        Element(int id, String name, ResourceKey<DamageType> damage, int color) {
            this.id = id;
            this.name = name;
            this.damage = damage;
            this.color = color;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public ResourceKey<DamageType> getDamage() {
            return damage;
        }

        public int getColor() {
            return color;
        }
    }

    public enum Behavior implements StringRepresentable {
        BASIC(0, "basic"),
        SCATTER(1, "scatter"),
        RICOCHET(2, "ricochet"),
        BLAST(3, "blast"),
        LINGER(4, "linger"),
        BURST(5, "burst");

        private static final IntFunction<Behavior> ID = ByIdMap.continuous(Behavior::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Behavior> CODEC = StringRepresentable.fromValues(Behavior::values);
        public static final StreamCodec<ByteBuf, Behavior> STREAM_CODEC = ByteBufCodecs.idMapper(ID, part -> part.id);
        private final int id;
        private final String name;

        Behavior(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    public enum Stat implements StringRepresentable {
        STANDARD(0, "standard"),
        POWER(1, "power"),
        SPEED(2, "speed"),
        RECHARGE(3, "recharge"),
        FORCE(4, "force"),
        SUSTAIN(5, "sustain");

        private static final IntFunction<Stat> ID = ByIdMap.continuous(Stat::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        public static final Codec<Stat> CODEC = StringRepresentable.fromValues(Stat::values);
        public static final StreamCodec<ByteBuf, Stat> STREAM_CODEC = ByteBufCodecs.idMapper(ID, part -> part.id);
        private final int id;
        private final String name;

        Stat(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }
}
