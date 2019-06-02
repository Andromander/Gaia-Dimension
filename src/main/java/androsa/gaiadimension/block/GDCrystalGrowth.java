package androsa.gaiadimension.block;

import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class GDCrystalGrowth extends BlockBush implements IShearable, ModelRegisterCallback {

    public GDCrystalGrowth() {
        super(Material.PLANTS);

        this.setTickRandomly(true);
        this.setSoundType(SoundType.GLASS);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Biome biome = worldIn.getBiome(pos);

        if (biome instanceof GDBlueAgateTaiga)
            return MapColor.LIGHT_BLUE;
        if (biome instanceof GDGreenAgateJungle)
            return MapColor.LIME;
        if (biome instanceof GDPurpleAgateSwamp)
            return MapColor.PURPLE;
        if (biome instanceof GDFossilWoodland)
            return MapColor.YELLOW_STAINED_HARDENED_CLAY;
        if (biome instanceof GDMutantAgateWildwood)
            return MapColor.WHITE_STAINED_HARDENED_CLAY;
        if (biome instanceof GDStaticWasteland)
            return MapColor.CYAN;
        if (biome instanceof GDVolcanicLands || biome instanceof GDGoldstoneLands)
            return MapColor.BLACK;
        if (biome instanceof GDShiningGrove)
            return MapColor.BLUE;
        return MapColor.PINK;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this));
        return ret;
    }

    public boolean canPlaceBlockAt(IBlockState state) {
        return state.getBlock() instanceof GDCrystalGrass || state.getBlock() instanceof GDGaiaSoil;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        return super.canBlockStay(worldIn, pos, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
