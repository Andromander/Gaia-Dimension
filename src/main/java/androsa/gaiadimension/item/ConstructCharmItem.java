package androsa.gaiadimension.item;

import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.entity.OpaliteContructEntity;
import androsa.gaiadimension.registry.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
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
                if (this.getUUID(stack, "MookaiteUUID") == null) {
                    //Check if the Mookaite Construct has a bond already
                    if (mookaite.getOpaliteCompanion() == null) {
                        if (!player.level.isClientSide()) {
                            this.setUUID(stack, mookaite.getUUID(), "MookaiteUUID");
                        }
                    } else {
                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.mookaite_already_bonded"), true);
                        return InteractionResult.PASS;
                    }
                } else {
                    player.displayClientMessage(new TranslatableComponent("construct_charm.message.has_mookaite_bond"), true);
                    return InteractionResult.PASS;
                }
            }
            //Opalite Construct check
            if (entity instanceof OpaliteContructEntity opalite) {
                //Check if we already have an Opalite Construct's UUID stored
                if (this.getUUID(stack, "OpaliteUUID") == null) {
                    //Check if the Mookaite Construct has a bond already
                    if (opalite.getMookaiteCompanion() == null) {
                        if (!player.level.isClientSide()) {
                            this.setUUID(stack, opalite.getUUID(), "OpaliteUUID");
                        }
                    } else {
                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.opalite_already_bonded"), true);
                        return InteractionResult.PASS;
                    }
                } else {
                    player.displayClientMessage(new TranslatableComponent("construct_charm.message.has_opalite_bond"), true);
                    return InteractionResult.PASS;
                }
            }

            //Check if we can begin the bond process
            if (this.getUUID(stack, "MookaiteUUID") != null && this.getUUID(stack, "OpaliteUUID") != null) {
                boolean mFlag = false;
                boolean oFlag = false;
                Entity mookaite = null;
                Entity opalite = null;

                //Check if mobs are in range
                if (!player.level.isClientSide()) {
                    List<Entity> entities = player.level.getEntities(player, player.getBoundingBox().inflate(16.0F), (e) -> {
                        EntityType<?> type = e.getType();
                        return type == ModEntities.MOOKAITE_CONSTRUCT.get() || type == ModEntities.OPALITE_CONSTRUCT.get();
                    });
                    for (Entity e : entities) {
                        if (e.getUUID().equals(this.getUUID(stack, "MookaiteUUID"))) {
                            mFlag = true;
                            mookaite = e;
                        }
                        if (e.getUUID().equals(this.getUUID(stack, "OpaliteUUID"))) {
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
                    if (mFlag && oFlag) {
                        MookaiteConstructEntity mEntity = (MookaiteConstructEntity) mookaite;
                        OpaliteContructEntity oEntity = (OpaliteContructEntity) opalite;

                        if (!player.level.isClientSide()) {
                            mEntity.setOpaliteCompanion(oEntity.getUUID());
                            oEntity.setMookaiteCompanion(mEntity.getUUID());
                            stack.shrink(1);
                        }

                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.success"), true);
                        return InteractionResult.CONSUME;
                    } else {
                        if (!mFlag && !oFlag) {
                            player.displayClientMessage(new TranslatableComponent("construct_charm.message.both_invalid"), true);
                        } else if (!mFlag) {
                            player.displayClientMessage(new TranslatableComponent("construct_charm.message.mookaite_invalid"), true);
                        } else if (!oFlag) {
                            player.displayClientMessage(new TranslatableComponent("construct_charm.message.opalite_invalid"), true);
                        }

                        if (!player.level.isClientSide()) {
                            this.removeUUID(stack, "MookaiteUUID");
                            this.removeUUID(stack, "OpaliteUUID");
                        }
                        return InteractionResult.PASS;
                    }
                } else {
                    if (!mFlag && !oFlag) {
                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.both_out_of_range"), true);
                    } else if (!mFlag) {
                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.mookaite_out_of_range"), true);
                    } else if (!oFlag) {
                        player.displayClientMessage(new TranslatableComponent("construct_charm.message.opalite_out_of_range"), true);
                    }

                    if (!player.level.isClientSide()) {
                        this.removeUUID(stack, "MookaiteUUID");
                        this.removeUUID(stack, "OpaliteUUID");
                    }
                    return InteractionResult.PASS;
                }
            }

        } else {
            player.displayClientMessage(new TranslatableComponent("construct_charm.message.invalid_entity"), true);
            return InteractionResult.PASS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide()) {
                this.removeUUID(stack, "MookaiteUUID");
                this.removeUUID(stack, "OpaliteUUID");
            }
            player.displayClientMessage(new TranslatableComponent("construct_charm.message.reset"), true);

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
        }

        return InteractionResultHolder.pass(stack);
    }

    public void setUUID(ItemStack stack, UUID id, String tagname) {
        stack.getOrCreateTag().putUUID(tagname, id);
    }

    public UUID getUUID(ItemStack stack, String tagname) {
        CompoundTag tag = stack.getTag();
        return tag != null && tag.hasUUID(tagname) ? tag.getUUID(tagname) : null;
    }

    public void removeUUID(ItemStack stack, String tagname) {
        stack.removeTagKey(tagname);
    }
}
