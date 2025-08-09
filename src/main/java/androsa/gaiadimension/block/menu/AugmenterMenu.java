package androsa.gaiadimension.block.menu;

import androsa.gaiadimension.item.tools.GaiaStaffItem;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import androsa.gaiadimension.registry.registration.ModItems;
import androsa.gaiadimension.registry.registration.ModMenus;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class AugmenterMenu extends ItemCombinerMenu {

    public static final int INPUT_SLOT = 0;
    public static final int CORE_INGREDIENT_SLOT = 1;
    public static final int HEAD_INGREDIENT_SLOT = 2;
    public static final int ROD_INGREDIENT_SLOT = 3;
    public static final int OUTPUT_SLOT = 4;

    protected static final Map<ItemLike, GaiaStaffItem.Element> ITEM_TO_ELEMENT_MAP = Map.of(
            ModItems.tektite.asItem(), GaiaStaffItem.Element.PHYSICAL,
            ModItems.crystal_core.asItem(), GaiaStaffItem.Element.PHYSICAL,
            ModItems.spitfire_heart.asItem(), GaiaStaffItem.Element.FIRE,
            ModItems.shockshooter_soul.asItem(), GaiaStaffItem.Element.ELECTRIC,
            ModItems.moss_agate_claw.asItem(), GaiaStaffItem.Element.POISON,
            ModItems.howlite_fang.asItem(), GaiaStaffItem.Element.FROST,
            ModItems.spellbound_core.asItem(), GaiaStaffItem.Element.MAGIC,
            ModItems.bismuth_horn.asItem(), GaiaStaffItem.Element.ENERGY);
    protected static final Map<ItemLike, GaiaStaffItem.Behavior> ITEM_TO_BEHAVIOR_MAP = Map.of(
            ModItems.tektite.asItem(), GaiaStaffItem.Behavior.BASIC,
            ModItems.stibnite.asItem(), GaiaStaffItem.Behavior.SCATTER,
            ModItems.euclase.asItem(), GaiaStaffItem.Behavior.RICOCHET,
            ModItems.carnelian.asItem(), GaiaStaffItem.Behavior.BLAST,
            ModItems.benitoite.asItem(), GaiaStaffItem.Behavior.LINGER,
            ModItems.goshenite.asItem(), GaiaStaffItem.Behavior.BURST);
    protected static final Map<ItemLike, GaiaStaffItem.Stat> ITEM_TO_STAT_MAP = Map.of(
            ModItems.tektite.asItem(), GaiaStaffItem.Stat.STANDARD,
            ModItems.scaynyx_ingot.asItem(), GaiaStaffItem.Stat.POWER,
            ModItems.glitter_rod.asItem(), GaiaStaffItem.Stat.SPEED,
            ModItems.shiny_bone.asItem(), GaiaStaffItem.Stat.RECHARGE,
            ModItems.magnetite_rod.asItem(), GaiaStaffItem.Stat.FORCE,
            ModItems.aura_rod.asItem(), GaiaStaffItem.Stat.SUSTAIN);

    public AugmenterMenu(int id, Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    public AugmenterMenu(int id, Inventory inv, ContainerLevelAccess access) {
        super(ModMenus.AUGMENTER.get(), id, inv, access, createDefinition());
    }

    private static ItemCombinerMenuSlotDefinition createDefinition() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(INPUT_SLOT, 29, 36, stack -> stack.is(ModItems.magic_staff))
                .withSlot(CORE_INGREDIENT_SLOT, 80, 18, stack -> ITEM_TO_ELEMENT_MAP.containsKey(stack.getItem()))
                .withSlot(HEAD_INGREDIENT_SLOT, 80, 36, stack -> ITEM_TO_BEHAVIOR_MAP.containsKey(stack.getItem()))
                .withSlot(ROD_INGREDIENT_SLOT, 80, 54, stack -> ITEM_TO_STAT_MAP.containsKey(stack.getItem()))
                .withResultSlot(OUTPUT_SLOT, 131, 36)
                .build();
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(ModBlocks.augmenter.get());
    }

    @Override
    protected void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player, stack.getCount());
        ItemStack staff = this.inputSlots.getItem(INPUT_SLOT);

        ItemStack core = this.inputSlots.getItem(CORE_INGREDIENT_SLOT);
        if (isBasic(staff, ModDataComponents.STAFF_ELEMENT.get(), GaiaStaffItem.Element.PHYSICAL) && (!core.is(ModItems.tektite.get()) || ! core.is(ModItems.crystal_core))
                || !isBasic(staff, ModDataComponents.STAFF_ELEMENT.get(), GaiaStaffItem.Element.PHYSICAL) && (core.is(ModItems.tektite.get()) || ITEM_TO_ELEMENT_MAP.containsKey(core.getItem()))) {
            this.shrink(CORE_INGREDIENT_SLOT);
        }

        ItemStack head = this.inputSlots.getItem(HEAD_INGREDIENT_SLOT);
        if (isBasic(staff, ModDataComponents.STAFF_BEHAVIOR.get(), GaiaStaffItem.Behavior.BASIC) && !head.is(ModItems.tektite.get())
                || !isBasic(staff, ModDataComponents.STAFF_BEHAVIOR.get(), GaiaStaffItem.Behavior.BASIC) && head.is(ModItems.tektite.get())) {
            this.shrink(HEAD_INGREDIENT_SLOT);
        }

        ItemStack rod = this.inputSlots.getItem(ROD_INGREDIENT_SLOT);
        if (isBasic(staff, ModDataComponents.STAFF_STAT.get(), GaiaStaffItem.Stat.STANDARD) && !rod.is(ModItems.tektite.get())
                || !isBasic(staff, ModDataComponents.STAFF_STAT.get(), GaiaStaffItem.Stat.STANDARD) && rod.is(ModItems.tektite.get())) {
            this.shrink(ROD_INGREDIENT_SLOT);
        }

        this.shrink(INPUT_SLOT);

        //TODO: Sound?
    }

    private void shrink(int slot) {
        ItemStack stack = this.inputSlots.getItem(slot);
        if (!stack.isEmpty()) {
            stack.shrink(1);
            this.inputSlots.setItem(slot, stack);
        }
    }

    @Override
    public void createResult() {
        ItemStack staff = this.inputSlots.getItem(INPUT_SLOT);

        if (staff.is(ModItems.magic_staff)) {
            ItemStack clone = staff.copy();

            ItemStack core = this.inputSlots.getItem(CORE_INGREDIENT_SLOT);
            if (!core.isEmpty() && ITEM_TO_ELEMENT_MAP.containsKey(core.getItem())) {
                if (isBasic(staff, ModDataComponents.STAFF_ELEMENT.get(), GaiaStaffItem.Element.PHYSICAL) || core.is(ModItems.tektite.get())) {
                    if (core.is(ModItems.crystal_core)) {
                        clone.setDamageValue(0);
                    } else {
                        clone.set(ModDataComponents.STAFF_ELEMENT, ITEM_TO_ELEMENT_MAP.get(this.inputSlots.getItem(CORE_INGREDIENT_SLOT).getItem()));
                    }
                } else if (ITEM_TO_ELEMENT_MAP.get(core.getItem()).equals(staff.get(ModDataComponents.STAFF_ELEMENT.get()))) {
                    clone.setDamageValue(0);
                }
            }

            ItemStack head = this.inputSlots.getItem(HEAD_INGREDIENT_SLOT);
            if (!head.isEmpty() && ITEM_TO_BEHAVIOR_MAP.containsKey(head.getItem())) {
                if (isBasic(staff, ModDataComponents.STAFF_BEHAVIOR.get(), GaiaStaffItem.Behavior.BASIC) || head.is(ModItems.tektite.get())) {
                    clone.set(ModDataComponents.STAFF_BEHAVIOR, ITEM_TO_BEHAVIOR_MAP.get(this.inputSlots.getItem(HEAD_INGREDIENT_SLOT).getItem()));
                }
            }

            ItemStack rod = this.inputSlots.getItem(ROD_INGREDIENT_SLOT);
            if (!rod.isEmpty() && ITEM_TO_STAT_MAP.containsKey(rod.getItem())) {
                if (isBasic(staff, ModDataComponents.STAFF_STAT.get(), GaiaStaffItem.Stat.STANDARD) || rod.is(ModItems.tektite.get())) {
                    clone.set(ModDataComponents.STAFF_STAT, ITEM_TO_STAT_MAP.get(this.inputSlots.getItem(ROD_INGREDIENT_SLOT).getItem()));
                }
            }

            if (ItemStack.matches(clone, staff)) {
                this.resultSlots.setItem(0, ItemStack.EMPTY);
            } else {
                this.resultSlots.setItem(0, clone);
            }
        } else {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        }
    }

    private <T> boolean isBasic(ItemStack stack, DataComponentType<T> component, T basic) {
        T value = stack.get(component);
        return (value == null || value == basic);
    }
}
