package androsa.gaiadimension.item;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.entity.OpaliteContructEntity;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class ConstructCharmItem extends Item {

    public ConstructCharmItem(Properties props) {
        super(props);
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (entity instanceof MookaiteConstructEntity || entity instanceof OpaliteContructEntity) {
            //Mookaite Construct check
            if (entity instanceof MookaiteConstructEntity mookaite) {
                //Check if we already have a Mookaite Construct's UUID stored
                if (!stack.has(ModDataComponents.MOOKAITE_UUID)) {
                    //Check if the Mookaite Construct has a bond already
                    if (mookaite.getOpaliteCompanion() == null) {
                        if (!player.level().isClientSide()) {
                            this.setUUID(stack, mookaite.getUUID(), ModDataComponents.MOOKAITE_UUID);
                        }
                    } else {
                        this.displayMessage(player, "mookaite_already_bonded");
                        return InteractionResult.PASS;
                    }
                } else {
                    this.displayMessage(player, "has_opalite_bond");
                    return InteractionResult.PASS;
                }
            }
            //Opalite Construct check
            if (entity instanceof OpaliteContructEntity opalite) {
                //Check if we already have an Opalite Construct's UUID stored
                if (!stack.has(ModDataComponents.OPALITE_UUID)) {
                    //Check if the Mookaite Construct has a bond already
                    if (opalite.getMookaiteCompanion() == null) {
                        if (!player.level().isClientSide()) {
                            this.setUUID(stack, opalite.getUUID(), ModDataComponents.OPALITE_UUID);
                        }
                    } else {
                        this.displayMessage(player, "opalite_already_bonded");
                        return InteractionResult.PASS;
                    }
                } else {
                    this.displayMessage(player, "has_mookaite_bond");
                    return InteractionResult.PASS;
                }
            }

            //Check if we can begin the bond process
            if (stack.has(ModDataComponents.MOOKAITE_UUID) && stack.has(ModDataComponents.OPALITE_UUID)) {
                boolean mFlag = false;
                boolean oFlag = false;
                Entity mookaite = null;
                Entity opalite = null;

                //Check if mobs are in range
                if (!player.level().isClientSide()) {
                    List<Entity> entities = player.level().getEntities(player, player.getBoundingBox().inflate(16.0F), (e) -> {
                        EntityType<?> type = e.getType();
                        return type == ModEntities.MOOKAITE_CONSTRUCT.get() || type == ModEntities.OPALITE_CONSTRUCT.get();
                    });
                    for (Entity e : entities) {
                        if (e.getUUID().equals(this.getUUID(stack, ModDataComponents.MOOKAITE_UUID))) {
                            mFlag = true;
                            mookaite = e;
                        }
                        if (e.getUUID().equals(this.getUUID(stack, ModDataComponents.OPALITE_UUID))) {
                            oFlag = true;
                            opalite = e;
                        }
                    }
                }

                //Check if they've been found in range
                if (mFlag && oFlag) {
                    mFlag = mookaite instanceof MookaiteConstructEntity;
                    oFlag = opalite instanceof OpaliteContructEntity;

                    //Failsafe check to make sure they are these specific mobs
                    if (mookaite instanceof MookaiteConstructEntity mEntity && opalite instanceof OpaliteContructEntity oEntity) {
                        if (!player.level().isClientSide()) {
                            mEntity.setBonder(player.getUUID());
                            mEntity.setOpaliteCompanion(oEntity.getUUID());
                            oEntity.setBonder(player.getUUID());
                            oEntity.setMookaiteCompanion(mEntity.getUUID());
                            stack.shrink(1);
                        }

                        this.displayMessage(player, "success");
                        return InteractionResult.CONSUME;
                    } else {
                        if (!mFlag && !oFlag) {
                            this.displayMessage(player, "both_invalid");
                        } else if (!mFlag) {
                            this.displayMessage(player, "mookaite_invalid");
                        } else if (!oFlag) {
                            this.displayMessage(player, "opalite_invalid");
                        }

                        if (!player.level().isClientSide()) {
                            this.reset(stack);
                        }
                        return InteractionResult.PASS;
                    }
                } else {
                    if (!mFlag && !oFlag) {
                        this.displayMessage(player, "both_out_of_range");
                    } else if (!mFlag) {
                        this.displayMessage(player, "mookaite_out_of_range");
                    } else if (!oFlag) {
                        this.displayMessage(player, "opalite_out_of_range");
                    }

                    if (!player.level().isClientSide()) {
                        this.reset(stack);
                    }
                    return InteractionResult.PASS;
                }
            }

        } else {
            this.displayMessage(player, "invalid_entity");
            return InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }

    private void displayMessage(Player player, String name) {
        player.displayClientMessage(Component.translatable("gaiadimension.construct_charm.message." + name), true);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide()) {
                this.reset(stack);
            }
            player.displayClientMessage(Component.translatable("construct_charm.message.reset"), true);

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }

    private void reset(ItemStack stack) {
        this.removeUUID(stack, ModDataComponents.MOOKAITE_UUID);
        this.removeUUID(stack, ModDataComponents.OPALITE_UUID);
    }

    public void setUUID(ItemStack stack, UUID id, Supplier<DataComponentType<UUID>> tagname) {
        stack.set(tagname, id);
    }

    public UUID getUUID(ItemStack stack, Supplier<DataComponentType<UUID>> tagname) {
        return stack.has(tagname) ? stack.get(tagname) : null;
    }

    public void removeUUID(ItemStack stack, Supplier<DataComponentType<UUID>> tagname) {
        stack.remove(tagname);
    }
}
