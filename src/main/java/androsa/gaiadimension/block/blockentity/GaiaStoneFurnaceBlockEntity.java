package androsa.gaiadimension.block.blockentity;

import androsa.gaiadimension.block.menu.GaiaStoneFurnaceMenu;
import androsa.gaiadimension.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GaiaStoneFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public GaiaStoneFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GAIA_STONE_FURNACE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("gaiadimension.container.gaia_stone_furnace");
    }

    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new GaiaStoneFurnaceMenu(id, inventory, this, this.dataAccess);
    }
}