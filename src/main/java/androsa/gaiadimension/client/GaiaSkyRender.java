package androsa.gaiadimension.client;

import androsa.gaiadimension.registry.ModGaiaConfig;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

import java.util.Optional;
import java.util.Random;

/**
 * Custom sky render for Gaia:
 * Allowing for manipulation of Star brightness based on config values or biome.
 * Removes the moon as it is not required.
 * TODO: Can we add our own sky effects to Smoldering Bog, Shining Grove, Volcanic Lands, Goldstone Lands, and Static Wasteland? Maybe even the whole of Gaia?
 * TODO: Also fix sky colour blending here?
 * TODO: Could we make our own Sun?
 * If possible, add extra renders for other biomes.
 */
public class GaiaSkyRender implements ISkyRenderHandler {

    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("minecraft:textures/environment/sun.png");
    private VertexBuffer starVBO;
    private final VertexFormat vertexBufferFormat = DefaultVertexFormats.POSITION;

    public GaiaSkyRender() {
        generateStars();
    }

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrix, ClientWorld world, Minecraft mc) {
        WorldRenderer renderer = mc.worldRenderer;

        RenderSystem.disableTexture();
        Vector3d skycol = world.getSkyColor(mc.gameRenderer.getActiveRenderInfo().getBlockPos(), partialTicks);
        float sRed = (float) skycol.x;
        float sGreen = (float) skycol.y;
        float sBlue = (float) skycol.z;
        FogRenderer.applyFog();
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthMask(false);
        RenderSystem.enableFog();
        RenderSystem.color3f(sRed, sGreen, sBlue);
        renderer.skyVBO.bindBuffer();
        this.vertexBufferFormat.setupBufferState(0L);
        renderer.skyVBO.draw(matrix.getLast().getMatrix(), 7);
        VertexBuffer.unbindBuffer();
        this.vertexBufferFormat.clearBufferState();
        RenderSystem.disableFog();
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float[] setcol = world.func_239132_a_().func_230492_a_(world.func_242415_f(partialTicks), partialTicks);
        if (setcol != null) {
            RenderSystem.disableTexture();
            RenderSystem.shadeModel(7425);
            matrix.push();
            matrix.rotate(Vector3f.XP.rotationDegrees(90.0F));
            float f3 = MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F;
            matrix.rotate(Vector3f.ZP.rotationDegrees(f3));
            matrix.rotate(Vector3f.ZP.rotationDegrees(90.0F));
            float ssRed = setcol[0];
            float ssGreen = setcol[1];
            float ssBlue = setcol[2];
            Matrix4f matrix4f = matrix.getLast().getMatrix();
            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
            bufferbuilder.pos(matrix4f, 0.0F, 100.0F, 0.0F).color(ssRed, ssGreen, ssBlue, setcol[3]).endVertex();

            for (int j = 0; j <= 16; ++j) {
                float f7 = (float) j * ((float) Math.PI * 2F) / 16.0F;
                float f8 = MathHelper.sin(f7);
                float f9 = MathHelper.cos(f7);
                bufferbuilder.pos(matrix4f, f8 * 120.0F, f9 * 120.0F, -f9 * 40.0F * setcol[3]).color(setcol[0], setcol[1], setcol[2], 0.0F).endVertex();
            }

            bufferbuilder.finishDrawing();
            WorldVertexBufferUploader.draw(bufferbuilder);
            matrix.pop();
            RenderSystem.shadeModel(7424);
        }

        RenderSystem.enableTexture();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        matrix.push();
//      float f11 = 1.0F - this.world.getRainStrength(p_228424_2_);
//      RenderSystem.color4f(1.0F, 1.0F, 1.0F, f11);
        matrix.rotate(Vector3f.YP.rotationDegrees(-90.0F));
        matrix.rotate(Vector3f.XP.rotationDegrees(world.func_242415_f(partialTicks) * 360.0F));
        Matrix4f matrix4f1 = matrix.getLast().getMatrix();

        //Sun
        float f12 = 30.0F;
        renderer.textureManager.bindTexture(SUN_TEXTURES);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(matrix4f1, -f12, 100.0F, -f12).tex(0.0F, 0.0F).endVertex();
        bufferbuilder.pos(matrix4f1, f12, 100.0F, -f12).tex(1.0F, 0.0F).endVertex();
        bufferbuilder.pos(matrix4f1, f12, 100.0F, f12).tex(1.0F, 1.0F).endVertex();
        bufferbuilder.pos(matrix4f1, -f12, 100.0F, f12).tex(0.0F, 1.0F).endVertex();
        bufferbuilder.finishDrawing();
        WorldVertexBufferUploader.draw(bufferbuilder);

        RenderSystem.disableTexture();
        float f10 = this.getStarBrightness(world, partialTicks);
        if (f10 > 0.0F) {
            RenderSystem.color4f(f10, f10, f10, f10);
            this.starVBO.bindBuffer();
            this.vertexBufferFormat.setupBufferState(0L);
            this.starVBO.draw(matrix.getLast().getMatrix(), 7);
            VertexBuffer.unbindBuffer();
            this.vertexBufferFormat.clearBufferState();
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableFog();
        matrix.pop();
        RenderSystem.disableTexture();
        RenderSystem.color3f(0.0F, 0.0F, 0.0F);
        double x = mc.player.getEyePosition(partialTicks).y - world.getWorldInfo().getVoidFogHeight();
        if (x < 0.0D) {
            matrix.push();
            matrix.translate(0.0D, 12.0D, 0.0D);
            renderer.sky2VBO.bindBuffer();
            this.vertexBufferFormat.setupBufferState(0L);
            renderer.sky2VBO.draw(matrix.getLast().getMatrix(), 7);
            VertexBuffer.unbindBuffer();
            this.vertexBufferFormat.clearBufferState();
            matrix.pop();
        }

        RenderSystem.color3f(sRed * 0.2F + 0.04F, sGreen * 0.2F + 0.04F, sBlue * 0.6F + 0.1F);
        RenderSystem.enableTexture();
        RenderSystem.depthMask(true);
        RenderSystem.disableFog();
    }

    //VanillaCopy of WorldRenderer.generateStars, with VanillaCopy of WorldRenderer.renderStars mashed in
    private void generateStars() {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if (this.starVBO != null) {
            this.starVBO.close();
        }

        this.starVBO = new VertexBuffer(this.vertexBufferFormat);

        Random random = new Random(10842L);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);

        //renderStars
        for(int i = 0; i < 1500; ++i) {
            double x = (double)(random.nextFloat() * 2.0F - 1.0F);
            double y = (double)(random.nextFloat() * 2.0F - 1.0F);
            double z = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
            double area = x * x + y * y + z * z;
            if (area < 1.0D && area > 0.01D) {
                area = 1.0D / Math.sqrt(area);
                x = x * area;
                y = y * area;
                z = z * area;
                double xPos = x * 100.0D;
                double yPos = y * 100.0D;
                double zPos = z * 100.0D;
                double d8 = Math.atan2(x, z);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(x * x + z * z), y);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for(int j = 0; j < 4; ++j) {
                    double d18 = (double)((j & 2) - 1) * d3;
                    double d19 = (double)((j + 1 & 2) - 1) * d3;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    bufferbuilder.pos(xPos + d25, yPos + d23, zPos + d26).endVertex();
                }
            }
        }

        bufferbuilder.finishDrawing();
        this.starVBO.upload(bufferbuilder);
    }

    @OnlyIn(Dist.CLIENT)
    public float getStarBrightness(ClientWorld world, float par1) {
        PlayerEntity player = Minecraft.getInstance().player;
        Optional<RegistryKey<Biome>> biome = world.func_242406_i(new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()));

        return biome.filter(ModGaiaConfig::canDisplayStars).map(biomeRegistryKey -> 0.5F).orElseGet(() -> world.getStarBrightness(par1));
    }
}
